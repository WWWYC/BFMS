package cn.bf.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.dao.StudentMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;
import cn.bf.po.Expense;
import cn.bf.po.Group;
import cn.bf.po.Student;
import cn.bf.service.CompanyService;
import cn.bf.service.ExpenseService;
import cn.bf.service.GroupService;
import cn.bf.service.StudentService;
import cn.bf.util.ConfigProperties;
import cn.bf.util.Constant;
import cn.bf.util.Formatter;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;
import cn.bf.util.Validate;

@Service
public class StudentServiceImpl extends SuperClass implements StudentService {
        private static final long serialVersionUID = 1L;

        @Autowired
        private StudentMapper studentMapper;
        
        @Autowired
        private CompanyService companyService;
        
        @Autowired
        private GroupService groupService;
        
        @Autowired
        private ExpenseService expenseService;
        
        /**
         * 组合一个新的学号，格式：
         * 	年份 + 区号 + 人数
         * 	201705360001
         */
	@Override
        public Map<String, Object> getStuNum() {
		Map<String, Object> info = Message.getInitInfo();
		
		String year = new SimpleDateFormat("yyyy").format(new Date());
		
		// 组装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("year", year);
		String result = studentMapper.selectMaxStuNumByYear(param);
		
		// 如果没有查询到当年的学生编号，则置为001
		if(result == null){
			result = year + ConfigProperties.getValue("areaCode") + "0001";
			info.put("status", Constant.DB_OK);
			info.put("stuNum", result);
//			return Message.getJSONMsg(info);
			return info;
		} else {
			Long stuNum = Long.parseLong(result);
			stuNum += 1;
			
			info.put("status", Constant.DB_OK);
			info.put("stuNum", stuNum);
//			return Message.getJSONMsg(info);
			return info;
		}
		
		
        }

	/**
	 * 增加学生
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	@Override
	@Transactional
        public Map<String, Object> insertStudent(MultipartHttpServletRequest request, ActiveUser activeUser) throws MapperException, IllegalAccessException, InvocationTargetException, IOException  {
		
		Map<String, Object> info = Message.getInitInfo();
		
		/**
		 * 获取学生信息
		 */
        	Student student = new Student();
        	// 忽略Date为null的属性
        	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        	// 获取表单属性
        	BeanUtils.populate(student, request.getParameterMap());
        	
        	// 获取照片文件信息
        	MultipartFile photo = request.getFile("stuPhoto");
        	
        	// 照片文件名，用于校验
        	student.setStuPhoto(photo.getOriginalFilename().trim());
        	
        	// 字符串的日期需要手动转换为Date类型
        	if(request.getParameter("entryDate") != null && !request.getParameter("entryDate").trim().isEmpty()) {
        		student.setEntryDate(Formatter.toDate(request.getParameter("entryDate").trim()));
        	}
        	if(request.getParameter("exitDate") != null && !request.getParameter("exitDate").trim().isEmpty()) {
        		student.setExitDate(Formatter.toDate(request.getParameter("exitDate").trim()));
        	}
        	if(request.getParameter("interviewDate") != null && !request.getParameter("interviewDate").trim().isEmpty()){
        		student.setInterviewDate(Formatter.toDate(request.getParameter("interviewDate").trim()));
        	}
        	
        	// 设置主键
        	student.setStuId(UUIDUtil.getUuid());
        	
        	// 设置创建、更新信息
        	student.setCreateUser(activeUser.getSysUserId());
        	student.setUpdateUser(activeUser.getSysUserId());
        	student.setCreateDate(new Date());
        	student.setUpdateDate(new Date());
        	
        	
        	// 校验表单数据
        	info = validateForm(student);
        	if(!info.get("msg").equals("ok")) {
        		return info;
        	}
        	
		try {
			// 添加学生信息
			studentMapper.insertStudent(student);
			// 当数据库数据都保存成功后，保存照片文件，如果发生异常，则回滚
			savePhoto(student.getStuId(), photo.getOriginalFilename(), photo.getInputStream());
			
			// 添加一条学生费用信息
			Expense expense = new Expense();
			expense.setUuid(UUIDUtil.getUuid());
			expense.setStuId(student.getStuId());
			expense.setCreateUser(activeUser.getSysUserId());
			expense.setUpdateUser(activeUser.getSysUserId());
			expense.setCreateDate(new Date());
			expense.setUpdateDate(new Date());
			expenseService.insertExpense(expense);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "学生信息添加失败");
			MapperException mp = new MapperException("学生信息添加失败", e);
			mp.setInfo(info);
			throw mp;
		}

		info.put("status", Constant.DB_OK);
		info.put("msg", "添加成功");

		return info;
        }
	
	/**
	 * 保存照片文件
	 * @param student
	 * @param photoName
	 * @param is
	 * @throws IOException
	 */
	private void savePhoto(String stuId, String photoName, InputStream is) throws IOException {
		// 如果没有传入文件，则不进行操作
		if(photoName.trim().equals("") || photoName == null) {
			return;
		}
		/*
                 * 1、得到文件保存的根路径，在此路径下创建多级目录
                 */
                /*
                 * 2、生成二级目录
                 *   1）得到文件名称
                 *   2）得到hashCode
                 *   3）转成16进制
                 *   4）获取前两个字符用来生成目录
                 */

                /*
                 * 获取文件名称，并处理文件名的绝对路径问题
                 */
                int index = photoName.lastIndexOf("\\");
                if(index != -1){
                	photoName = photoName.substring(index + 1);
                }

                /*
                 * 处理文件重名问题，给文件名称添加UUID前缀
                 */
                String saveName = UUIDUtil.getUuid() + "__" + photoName;

                /*
                 * 得到hashCode，并转换成16进制
                 */
                int hashCode = photoName.hashCode();
                String hexCode = Integer.toHexString(hashCode);
                
                /*
                 * 获取hexCode的前两个字符，与根路径连接在一起，生成一个完整的路径
                 * 将此抽象路径传递给File类的构造方法
                 */
                String realPath = System.getProperty("realPath") + "\\resources\\photo";
                File dirFile = new File(realPath, hexCode.charAt(0) + "/" + hexCode.charAt(1));
                
                /*
                 * 创建目录
                 * mkdirs()：创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
                 */
                 dirFile.mkdirs();

                /*
                 * 创建目标文件
                 * 根据parent抽象路径名和child路径名字符串创建一个新 File 实例。
                 */
                File destFile = new File(dirFile, saveName);
                
                /*
                 * 写入硬盘（保存）
                 */
                FileOutputStream fos = new FileOutputStream(destFile);
                int len = is.available();
                byte[] buf = new byte[len];
                is.read(buf);
                fos.write(buf);
                is.close();
                fos.close();
                
                // 因为每次都会生成一个新的照片路径，所以在同一个方法内执行修改照片路径操作
                String stuPhoto = "\\resources\\photo\\" + hexCode.charAt(0) + "\\" + hexCode.charAt(1) + "\\" + saveName;
                Student stuParam = new Student();
                stuParam.setStuPhoto(stuPhoto);
                stuParam.setStuId(stuId);
                try {
                	studentMapper.updatePhotoPathByStuId(stuParam);
                } catch (Exception e) {
                	e.printStackTrace();
                	// 如果更新数据库发生异常，则删除已创建的文件
                	destFile.delete();
                	throw new MapperException("更新照片路径错误", e);
                }
                
	}

	/**
	 * 根据条件查询学生信息
	 */
	@Override
	public Map<String, Object> selectStudentByCriteria(Integer page, Integer rows, Student student, String groupId) {
		
		List<Student> studentList = null;
		Integer count = null;
		List<Company> companyList = null;
		
		// 组装参数
		Map<String, Object> param = Message.getInitInfo();
		param.put("status", Constant.DB_OK);
		param.put("student", student);
		param.put("page", (page - 1) * rows);
		param.put("rows", rows);
		param.put("groupId", groupId);
		
		try {
			// 查询学生
			studentList = studentMapper.selectStudentByCriteria(param);
			// 查询总记录数
			count = studentMapper.selectStudentCountByCriteria(param);
			// 根据与查询学生相同的条件（groupId）查询企业信息
			companyList = companyService.selectCompanyListByGroupId(groupId);
			
			// 为每一个学生的companyName赋值
			for(Student st : studentList) {
				for(Company c : companyList) {
					if(st.getCompanyId().equals(c.getCompanyId())) {
						st.setCompanyName(c.getCompanyName());
					}
				}
			}
		} catch (Exception e) {
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询学生信息失败");
			MapperException mp = new MapperException("查询学生信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		
		// 组装数据
		param.clear();
		param.put("status", Constant.DB_OK);
		param.put("rows", studentList);
		param.put("total", count);
		return param;
	}

	/**
	 * 根据学生ID查询学生信息
	 */
	@Override
	public ModelAndView selectStudentByStuId(Student student, String viewName) {
		ModelAndView model = null;
		Student st = null;
		Company company = null;
		Group group = null;
		try {
			// 查询学生信息
			st = studentMapper.selectStudentByStuId(student.getStuId());
			// 查询企业名
			company = companyService.selectCompanyByCompanyId(student.getCompanyId());
			// 查询组合名
			if(company != null) {
				group = groupService.selectGroupByGroupId(company.getGroupId());
			}
			
			// 将学生信息填充到Model中，并设置跳转路径
	        	model = modelAddAttribute(st, viewName);
	        	if(company != null) {
	        		model.addObject("companyName", company.getCompanyName());
	        	}
	        	
	        	model.addObject("companyId", student.getCompanyId());
	        	
	        	if(group != null) {
	        		model.addObject("groupName", group.getGroupName());
	        	}
	        	
	        	if(company != null) {
	        		model.addObject("groupId", company.getGroupId());
	        	}
	        	
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询学生信息失败");
			MapperException mp = new MapperException("查询学生信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		return model;
	}

	/**
	 * 更新学生信息
	 */
	@Override
	public Map<String, Object> updateStudent(MultipartHttpServletRequest request, ActiveUser activeUser) throws IllegalAccessException, InvocationTargetException {
		/**
		 * 获取学生信息
		 */
        	Student student = new Student();
        	
        	// 忽略Date为null的属性
        	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        	
        	// 获取表单属性
        	BeanUtils.populate(student, request.getParameterMap());
        	
        	// 获取照片文件信息
        	MultipartFile photo = request.getFile("stuPhoto");
        	
        	// 照片文件名，用于校验
        	student.setStuPhoto(photo.getOriginalFilename().trim());
        	
        	// 字符串的日期需要手动转换为Date类型
        	if(request.getParameter("entryDate") != null && !request.getParameter("entryDate").trim().isEmpty()) {
        		student.setEntryDate(Formatter.toDate(request.getParameter("entryDate").trim()));
        	}
        	if(request.getParameter("exitDate") != null && !request.getParameter("exitDate").trim().isEmpty()) {
        		student.setExitDate(Formatter.toDate(request.getParameter("exitDate").trim()));
        	}
        	if(request.getParameter("interviewDate") != null && !request.getParameter("interviewDate").trim().isEmpty()){
        		student.setInterviewDate(Formatter.toDate(request.getParameter("interviewDate").trim()));
        	}
        	
        	// 设置创建、更新信息
        	student.setUpdateUser(activeUser.getSysUserId());
        	student.setUpdateDate(new Date());
        	
        	// 旧照片名
        	String oldPhotoName = "";
        	
        	// 校验表单数据
        	Map<String, Object> info = validateForm(student);
        	if(!info.get("msg").equals("ok")) {
        		return info;
        	}
        	
		try {
			// 查询学生照片路径，在更新信息成功后删除原照片
			oldPhotoName = studentMapper.selectStuPhotoByStuId(student);
			// 添加学生信息
			studentMapper.updateStudentByStuId(student);
			// 当数据库数据都保存成功后，保存照片文件，如果发生异常，则回滚
			savePhoto(student.getStuId(), photo.getOriginalFilename(), photo.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "学生信息添加失败");
			MapperException mp = new MapperException("学生信息添加失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		try {
			// 当新照片保存成功后，删除旧照片，抛出异常后不回滚
			deletePhoto(oldPhotoName);
                } catch (Exception e) {
	                e.printStackTrace();
                }
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改成功");
		
		return info;
		
	}

	/**
	 * 删除照片
	 * @param oldPhotoName
	 */
	private void deletePhoto(String oldPhotoName) {
	        System.out.println(System.getProperty("realPath") + oldPhotoName);
	        File photo = new File(System.getProperty("realPath") + oldPhotoName);
	        photo.delete();
        }

	/**
	 * 删除学生
	 */
	@Override
	public Map<String, Object> deleteStudentByStuId(Student student) {
		Map<String, Object> info = Message.getInitInfo();
		try {
			studentMapper.deleteStudentByStuId(student);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除失败");
			MapperException mp = new MapperException("删除失败", e);
			mp.setInfo(info);
			throw mp;
		}
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除成功");
		return info;
	}
	
	/**
	 * 根据企业ID获取企业下的所有学生信息
	 */
	@Override
	public List<Student> seleStudentListByCompanyId(String companyId) {
		List<Student> studentList = null;
		
		try {
			Map<String, Object> param = Message.getInitInfo();
			param.put("companyId", companyId);
			studentList = studentMapper.selectStudentByCompanyId(param);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return studentList;
	}
	
	

	/**
	 * 表单校验
	 * 
	 * @param student
	 * @return
	 */
	private Map<String, Object> validateForm(Student student) {

		System.out.println(student);

		String stuNum = student.getStuNum().trim();
		String companyId = student.getCompanyId().trim();
		String nameZh = student.getNameZh().trim();
		String nameJp = student.getNameJp().trim();
		String nameKana = student.getNameKana().trim();
		String gender = student.getGender().trim();
		Integer age = student.getAge();
		String craft = student.getCraft().trim();
		Integer height = student.getHeight();
		Integer weight = student.getWeight();
		String blood = student.getBlood().trim();
		String stuPhone = student.getStuPhone().trim();
		String qq = student.getQq().trim();
		String stuPhoto = student.getStuPhoto().trim();
		Date entryDate = student.getEntryDate();
		String familyPhone = student.getFamilyPhone().trim();
		String addressZh = student.getAddressZh().trim();
		String addressJp = student.getAddressJp().trim();
		Integer sameClass = student.getSameClass();
		Date exitDate = student.getExitDate();
		String identityNum = student.getIdentityNum().trim();
		String passportNum = student.getPassportNum().trim();
		String diplomaNum = student.getDiplomaNum().trim();
		String destination = student.getDestination().trim();
		Float eyeLeft = student.getEyeLeft();
		Float eyeRight = student.getEyeRight();
		String nation = student.getNation().trim();
		Date interviewDate = student.getInterviewDate();
		String comment = student.getComment().trim();

		Map<String, Object> param = Message.getInitInfo();

		/**
		 * 判断学号
		 *   非空
		 *   必须是正整数
		 *   长度不能大于12
		 */
		if (stuNum != null) {
			if (stuNum.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "学号不能为空");
				return param;
			} else if (!Validate.isNum(stuNum)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "学号必须是正整数");
				return param;
			} else if (!Validate.isLength(stuNum, 12)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "学号长度不能大于12");
				return param;
			}
		} else {
			param.put("status", Constant.DB_ERR);
			param.put("msg", "学号不能为空");
			return param;
		}

		/**
		 * 判断中文名
		 *   非空
		 *   只能是汉字
		 *   长度不能大于6
		 */
		if (nameZh != null) {
			if (nameZh.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "中文名不能为空");
				return param;
			} else if (!Validate.isZh(nameZh)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "中文名必须是汉字");
				return param;
			} else if (!Validate.isLength(nameZh, 6)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "中文名长度不能大于6");
				return param;
			}
		}

		/**
		 * 判断日文名
		 *   只能是日文字
		 *   长度不能大于12
		 */
		if (nameJp != null) {
			/*if (!Validate.isJp(nameJp)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "日文名必须是日文");
				return param;
			} else */if (!Validate.isLength(nameJp, 12)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "日文名长度不能大于12");
				return param;
			}
		}

		/**
		 * 判断假名
		 *   只能是日文字
		 *   长度不能大于12
		 */
		if (nameKana != null) {
			/*if (!Validate.isJp(nameKana)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "假名必须是日文");
				return param;
			} else*/ if (!Validate.isLength(nameKana, 12)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "假名长度不能大于12");
				return param;
			}
		}

		/**
		 * 判断企业ID 非空
		 */
		if (companyId != null) {
			if (companyId.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "请选择企业");
				return param;
			}
		}
		
		/**
		 * 判断工种
		 *   非空
		 *   只能是汉字
		 *   长度不能大于12
		 */
		if (craft != null) {
			if (craft.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "工种不能为空");
				return param;
			} else if (!Validate.isZh(craft)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "工种必须是汉字");
				return param;
			} else if (!Validate.isLength(craft, 12)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "工种长度不能大于12");
				return param;
			}
		}

		/**
		 * 判断年龄
		 *   必须是正整数
		 *   不能超过130
		 */
		if (age != null && age != 0) {
			if (!Validate.isNum(age + "")) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "年龄必须是正整数");
				return param;
			} else if (!Validate.numRange(Long.valueOf(age + ""),
			                0L, 130L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "年龄必须大于0并且小于130");
				return param;
			}
		}

		/**
		 * 判断身高
		 *   必须是正整数
		 *   不能大于250
		 */
		if (height != null && height != 0) {
			if (!Validate.isNum(height + "")) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "身高必须是整数");
				return param;
			} else if (!Validate.numRange(Long.valueOf(height), 0L,
			                250L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "身高必须大于0并且小于250");
				return param;
			}
		}

		/**
		 * 判断体重
		 *   必须是正整数
		 *   不能大于250
		 */
		if (weight != null && weight != 0) {
			if (!Validate.isNum(weight + "")) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "体重必须是整数");
				return param;
			} else if (!Validate.numRange(Long.valueOf(weight), 0L,
			                250L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "体重必须大于等于0并且小于等于250");
				return param;
			}
		}
		
		/**
		 * 判断血型
		 *   取值：ABO
		 */
		if(blood != null && !blood.isEmpty()) {
			if (!Validate.isBlood(blood)){
				param.put("status", Constant.DB_ERR);
				param.put("msg", "血型取值：A、B、AB、O");
				return param;
			}
		}

		/**
		 * 判断手机号
		 *   非空
		 *   长度不能超过11
		 *   必须是正整数
		 */
		if (stuPhone != null) {
			if (stuPhone.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "手机号不能为空");
				return param;
			} else if (!Validate.isNum(stuPhone)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "手机号必须是整数");
				return param;
			} else if (!Validate.isLength(stuPhone, 11)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "手机号不能大于11位");
				return param;
			}
		}

		/**
		 * 判断照片文件名
		 *   不能包含特殊字符
		 */
		if (stuPhoto != null && !stuPhoto.equals("")) {
			if (!Validate.isRefuseChar(stuPhoto)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "照片名不能包含特殊字符");
				return param;
			}
		}

		/**
		 * 判断入校日
		 *   必须非空
		 */
		if (entryDate == null || entryDate.getTime() == 0) {
			param.put("status", Constant.DB_ERR);
			param.put("msg", "入校日不能为空");
			return param;
		}

		/**
		 * 判断家庭联系方式
		 *   必须是正整数
		 *   不能超过11位
		 */
		if (familyPhone != null) {
			if (!Validate.isNum(familyPhone)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "家庭联系方式必须是整数");
				return param;
			} else if (!Validate.isLength(familyPhone, 11)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "家庭联系方式不能大于11位");
				return param;
			}
		}

		/**
		 * 判断中国地址
		 *   不能超过100
		 */
		if (addressZh != null) {
			if (!Validate.isLength(addressZh, 100)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "家庭住址（中国）不能超过100个字符");
				return param;
			}
		}

		/**
		 * 判断日本地址
		 *   不能超过100
		 */
		if (addressJp != null) {
			if (!Validate.isLength(addressJp, 100)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "家庭住址（日本）不能超过100个字符");
				return param;
			}
		}

		/**
		 * 判断同期生
		 *   必须是正整数
		 *   不能超过100
		 */
		if (sameClass != null && sameClass != 0) {
			if (!Validate.isNum(sameClass + "")) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "同期生必须是正整数");
				return param;
			} else if (!Validate.numRange(Long.valueOf(sameClass),
			                0L, 100L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "同期生必须大于等于0且小于等于100");
				return param;
			}
		}

		/**
		 * 判断出境日
		 *   必须大于入校日和面试日
		 */
		if (exitDate != null) {
			if (exitDate.getTime() <= entryDate.getTime()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "出境日期必须大于入校日期");
				return param;
			} else if (exitDate.getTime() < interviewDate.getTime()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "出境日必须大于面试日期");
				return param;
			}
		}

		/**
		 * 判断面试日期
		 *   面试日期必须小于入校日
		 */
		if (interviewDate != null) {
			if (interviewDate.getTime() >= entryDate.getTime()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "面试日期必须小于入校日期");
				return param;
			}
		}

		/**
		 * 判断身份证号
		 *   非空
		 *   长度必须大于等于0且小于等于18
		 */
		if (identityNum != null) {
			if (identityNum.isEmpty()) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "身份证号不能为空");
				return param;
			} else if (!Validate.isLength(identityNum, 18L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "身份证号必须大于等于0并且小于等于18");
				return param;
			}
		}

		/**
		 * 判断护照编号
		 *   长度必须大于等于0且小于等于8
		 */
		if (passportNum != null) {
			if (!Validate.isLength(passportNum, 8L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "护照编号必须大于等于0并且小于等于8");
				return param;
			}
		}

		/**
		 * 判断毕业证编号
		 *   长度必须大于等于0并且小于等于18
		 */
		if (diplomaNum != null) {
			if (!Validate.isLength(diplomaNum, 18L)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "毕业证编号必须大于等于0并且小于等于18");
				return param;
			}
		}

		/**
		 * 判断去往地
		 *   不能超过100字符
		 */
		if (destination != null) {
			if (!Validate.isLength(destination, 100)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "去往地不能超过100个字符");
				return param;
			}
		}

		/**
		 * 判断左眼视力
		 *   必须大于0
		 */
		if (eyeLeft != null && eyeLeft != 0) {
			if (!Validate.isDecimal(eyeLeft)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "左眼视力格式不正确");
				return param;
			} else if (eyeLeft <= 0) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "左眼视力必须大于0");
				return param;
			}
		}

		/**
		 * 判断右眼视力
		 *   必须大于0
		 */
		if (eyeRight != null && eyeRight != 0) {
			if (!Validate.isDecimal(eyeRight)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "右眼视力格式不正确");
				return param;
			} else if (eyeRight <= 0) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "右眼视力必须大于0");
				return param;
			}
		}

		/**
		 * 判断民族
		 *   不能超过5个字符
		 */
		if (nation != null) {
			if (!Validate.isLength(nation, 5)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "民族不能超过5个字符");
				return param;
			}
		}

		/**
		 * 判断备注
		 *   不能超过200个字符
		 */
		if (comment != null) {
			if (!Validate.isLength(comment, 200)) {
				param.put("status", Constant.DB_ERR);
				param.put("msg", "备注不能超过200个字符");
				return param;
			}
		}

		param.put("msg", "ok");
		return param;
	}
	
	/**
	 * 填充数据
	 * @param st
	 * @param viewName
	 * @return
	 */
	private ModelAndView modelAddAttribute(Student st, String viewName) {
		ModelAndView model = new ModelAndView();
		model.setViewName(viewName);

		model.addObject("stuId", st.getStuId());
		model.addObject("stuNum", st.getStuNum());
		model.addObject("companyId", st.getCompanyId());
		model.addObject("nameZh", st.getNameZh());
		model.addObject("nameJp", st.getNameJp());
		model.addObject("nameKana", st.getNameKana());
		model.addObject("gender", st.getGender());
		if(st.getAge() != 0) {
			model.addObject("age", st.getAge());
		}
		model.addObject("craft", st.getCraft());
		if(st.getHeight() != 0) {
			model.addObject("height", st.getHeight());
		}
		if(st.getWeight() != 0) {
			model.addObject("weight", st.getWeight());
		}
		model.addObject("blood", st.getBlood());
		model.addObject("stuPhone", st.getStuPhone());
		model.addObject("qq", st.getQq());
		model.addObject("stuPhoto", st.getStuPhoto());
		if(st.getEntryDate() != null) {
			model.addObject("entryDate", Formatter.toDateString(st.getEntryDate()));
		}
		
		model.addObject("familyPhone", st.getFamilyPhone());
		model.addObject("addressZh", st.getAddressZh());
		model.addObject("addressJp", st.getAddressJp());
		if(st.getSameClass() != 0) {
			model.addObject("sameClass", st.getSameClass());
		}
		if(st.getExitDate() != null) {
			model.addObject("exitDate", Formatter.toDateString(st.getExitDate()));
		}
		
		model.addObject("identityNum", st.getIdentityNum());
		model.addObject("passportNum", st.getPassportNum());
		model.addObject("diplomaNum", st.getDiplomaNum());
		model.addObject("destination", st.getDestination());
		model.addObject("isRegister", st.getIsRegister());
		model.addObject("isTattoo", st.getIsTattoo());
		model.addObject("isHealth", st.getIsHealth());
		model.addObject("isDiploma", st.getIsDiploma());
		model.addObject("isMarried", st.getIsMarried());
		if(st.getEyeLeft() != 0) {
			model.addObject("eyeLeft", st.getEyeLeft());
		}
		if(st.getEyeRight() != 0) {
			model.addObject("eyeRight", st.getEyeRight());
		}
		
		model.addObject("nation", st.getNation());
		if(st.getInterviewDate() != null) {
			model.addObject("interviewDate", Formatter.toDateString(st.getInterviewDate()));
		}
		
		model.addObject("comment", st.getComment());
		model.addObject("createUser", st.getCreateUser());
		model.addObject("createDate", st.getCreateDate());
		model.addObject("updateUser", st.getUpdateUser());
		model.addObject("updateDate", st.getUpdateDate());

		return model;
	}

}
