package cn.bf.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.dao.CompanyMapper;
import cn.bf.dao.GroupMapper;
import cn.bf.dao.StudentMapper;
import cn.bf.dao.VacationMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;
import cn.bf.po.Group;
import cn.bf.po.Student;
import cn.bf.po.Vacation;
import cn.bf.service.StudentService;
import cn.bf.service.VacationService;
import cn.bf.util.Constant;
import cn.bf.util.Formatter;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;
import cn.bf.util.Validate;

@Service
public class VacationServiceImpl extends SuperClass implements VacationService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private VacationMapper vacationMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private GroupMapper groupMapper;

	/**
	 * 多条件查询请假信息
	 */
	@Override
	public Map<String, Object> getVacationListByCriteria(String name, Date startDate, Date endDate, Integer page, Integer rows) {
		Map<String, Object> info = Message.getInitInfo();
		
		List<Student> stuIdList = null;
		List<Vacation> vacationList = new ArrayList<Vacation>();;
		int count = 0;
		
		try {
			// 组装参数
			Map<String, Object> param = Message.getInitInfo();
			
			param.put("name", name);
			// 根据姓名查询符合条件的学生ID
			stuIdList = studentMapper.selectStuIdByStuName(param);
			
			param.clear();
			param.put("stuIdList", stuIdList);
			param.put("startDate", Formatter.toDatetimeString(startDate));
			param.put("endDate", Formatter.toDatetimeString(endDate));
			param.put("page", (page - 1) * rows);
			param.put("rows", rows);
			
			/**
			 * 当name不为null（空字符串），并且没有查询到学生时，返回null
			 */
			if(name != null && (!name.isEmpty() && stuIdList.size() == 0)) {
				info.clear();
				info.put("status", Constant.DB_OK);
				info.put("rows", vacationList);
				info.put("total", count);
				return info;
			} else {
				// 查询学生ID对应的请假信息，并同时查询学生姓名
				vacationList = vacationMapper.selectVacationByCriteria(param);
				
				// 查询当前条件下的请假人数
				count = vacationMapper.selectCountByCriteria(param);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询请假信息失败");
			MapperException mp = new MapperException("查询请假信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		
		// 组装数据
		info.clear();
		info.put("status", Constant.DB_OK);
		info.put("rows", vacationList);
		info.put("total", count);		
		
		return info;
	}

	/**
	 * 插入一条请假记录
	 */
	@Override
	public Map<String, Object> insertVacation(Vacation vacation, ActiveUser activeUser) {
		Map<String, Object> info = Message.getInitInfo();
		
		info = validateForm(vacation);
		if(!info.get("msg").equals("ok")) {
			return info;
		}
		try {
			// 填充参数
			vacation.setCreateDate(new Date());
			vacation.setCreateUser(activeUser.getSysUserId());
			vacation.setUuid(UUIDUtil.getUuid());
			vacation.setUpdateDate(new Date());
			vacation.setUpdateUser(activeUser.getSysUserId());
			
			vacationMapper.insertVacation(vacation);
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "添加请假信息失败");
			MapperException mp = new MapperException("添加请假信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		// 组装数据
		info.clear();
		info.put("status", Constant.DB_OK);
		info.put("msg", "添加成功");
		
		return info;
	}

	
	/**
	 * 显示、编辑个人请假信息
	 */
	@Override
	public ModelAndView showVacation(Vacation vacation, String viewName) {
		ModelAndView model = null;
		
		// 查询当前请假信息
		vacation = vacationMapper.selectVacationByUuid(vacation);
		
		model = modelAddAttribute(vacation, viewName);
		
		// 根据学生ID查询学生
		Student student = studentMapper.selectStudentByStuId(vacation.getStuId());
		
		// 如果学生不为null，则通过学生的企业ID查询企业
		if(student != null) {
			Company company = companyMapper.selectCompanyByCompanyId(student.getCompanyId());
			// 如果企业不为null，则通过企业的组合ID查询组合
			if(company != null) {
				Group group = groupMapper.selectGroupByGroupId(company.getGroupId());
				// 将企业名、企业ID、组合名、组合ID填充到Model中
				if(group != null) {
					model.addObject("groupName", group.getGroupName());
					model.addObject("groupId", group);
					model.addObject("companyName", company.getCompanyName());
					model.addObject("companyId", company.getCompanyId());
				}
			}
		}
		
		return model;
	}

	/**
	 * 更新请假信息
	 */
	@Override
	public Map<String, Object> updateVacationByUuid(Vacation vacation, ActiveUser activeUser) {
		Map<String, Object> info = Message.getInitInfo();
		
		info = validateForm(vacation);
		if(!info.get("msg").equals("ok")) {
			return info;
		}
		
		try {
			// 设置更新信息
			vacation.setUpdateDate(new Date());
			vacation.setUpdateUser(activeUser.getSysUserId());
			vacationMapper.updateVacationByUuid(vacation);
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "修改请假信息失败");
			MapperException mp = new MapperException("修改请假信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		// 组装数据
		info.clear();
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改成功");
		
		return info;
	}
	

	/**
	 * 根据主键删除请假信息
	 */
	@Override
	public Map<String, Object> deleteVacationByUuid(Vacation vacation) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			vacationMapper.deleteVacationByUuid(vacation);
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除请假信息失败");
			MapperException mp = new MapperException("删除请假信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		// 组装数据
		info.clear();
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除成功");
		
		return info;
	}
	
	/**
	 * 表单校验
	 * @param vacation
	 * @return
	 */
	private Map<String, Object> validateForm(Vacation vacation) {
		Map<String, Object> info = Message.getInitInfo();
		
		String stuId = vacation.getStuId();
		Date startDate = vacation.getStartDate();
		Date endDate = vacation.getEndDate();
		String reason = vacation.getReason();
		Double duration = vacation.getDuration();
		String comment = vacation.getComment();
		
		/**
		 * 判断学生ID
		 *   非空
		 */
		if (stuId != null) {
			if (stuId.isEmpty()) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "请选择学生");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "请选择学生");
			return info;
		}
		
		/**
		 * 判断请假日期
		 * 开始日期必须小于请假日期
		 */
		if(startDate != null && endDate != null) {
			if(startDate.getTime() >= endDate.getTime()) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "请假开始日期必须小于请假结束日期");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "请假日期不能为空");
			return info;
		}
		
		
		/**
		 * 判断请假原因
		 * 字符串长度不能超过100
		 */
		if(reason != null && !reason.isEmpty()) {
			if(!Validate.isLength(reason, 100)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "请假原因不能超过100个字符");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "请假原因不能为空");
			return info;
		}
		
		/**
		 * 判断请假天数
		 * 不得为空
		 * 必须是整数或小数
		 */
		if(duration != null) {
			if(!Validate.isDecimal(Float.valueOf(duration + ""))) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "请假天数格式不正确");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "请假天数不能为空");
			return info;
		}
		
		/**
		 * 判断备注
		 *   字符串长度不能超过100
		 */
		if(comment != null) {
			if(!Validate.isLength(comment, 100)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "备注不能超过100个字符");
				return info;
			}
		}
		
		info.put("msg", "ok");
		
		return info;
	}

	/**
	 * 填充数据
	 * @param st
	 * @param viewName
	 * @return
	 */
	private ModelAndView modelAddAttribute(Vacation vacation, String viewName) {
		ModelAndView model = new ModelAndView();
		model.setViewName(viewName);
		
		model.addObject("uuid", vacation.getUuid());
		model.addObject("stuId", vacation.getStuId());
		model.addObject("startDate", Formatter.toDatetimeString(vacation.getStartDate()));
		model.addObject("endDate", Formatter.toDatetimeString(vacation.getEndDate()));
		model.addObject("reason", vacation.getReason());
		model.addObject("duration", vacation.getDuration());
		model.addObject("comment", vacation.getComment());
		model.addObject("stuNum", vacation.getStuNum());
		model.addObject("nameZh", vacation.getNameZh());
		
		return model;
	}

}
