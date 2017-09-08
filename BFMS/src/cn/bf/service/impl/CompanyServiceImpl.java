package cn.bf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.dao.CompanyMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;
import cn.bf.po.Group;
import cn.bf.po.Student;
import cn.bf.service.CompanyService;
import cn.bf.service.GroupService;
import cn.bf.util.Constant;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;
import cn.bf.util.Validate;

@Service
public class CompanyServiceImpl extends SuperClass implements CompanyService {

        private static final long serialVersionUID = 1L;
        
        @Autowired
        private CompanyMapper companyMapper;
        
        @Autowired
        private GroupService groupService;
        
        /**
         * 获取企业编号
         */
	@Override
        public Map<String, Object> getCompanyNum() {
		Integer companyNum = companyMapper.selectMaxCompanyNum();
		Map<String, Object> info = Message.getInitInfo();
		
		// 如果没有查询到企业编号，则置为001
		if(companyNum == null){
			info.put("companyNum", "001");
			return info;
		} else if (companyNum >= 1 && companyNum < 9) {
			info.put("companyNum", "00" + (companyNum + 1));
			return info;
		} else if (companyNum >= 9 && companyNum < 99) {
			info.put("companyNum", "0" + (companyNum + 1));
			return info;
		} else if (companyNum >= 99 && companyNum < 999) {
			info.put("companyNum", companyNum + 1);
			return info;
		} else {
			info.put("companyNum", "00" + (companyNum + 1));
			return info;
		}
        }

	/**
	 * 添加企业
	 * @throws MapperException 
	 */
	@Override
	@Transactional
        public Map<String, Object> insertCompany(Company company, ActiveUser activeUser) throws MapperException {
		Map<String, Object> info = Message.getInitInfo();
		
		// 设置主键
		company.setCompanyId(UUIDUtil.getUuid());
		
		// 设置createUser
		company.setCreateUser(activeUser.getSysUserId());
		
		// 设置updateUser
		company.setUpdateUser(activeUser.getSysUserId());
		
		// 设置创建、更新时间
		company.setCreateDate(new Date());
		company.setUpdateDate(new Date());
		
		
		// 表单校验
		info = validateForm(company);
		if(info.get("status").equals("1")) {
			return info;
		}
		
		try {
			// 添加一条企业信息
			companyMapper.insertCompany(company);
		} catch (Exception e) {
			e.printStackTrace();
			
			info.put("status", Constant.DB_ERR);
                	info.put("msg", "企业信息添加失败");
                	
                	MapperException mp = new MapperException("企业信息添加失败", e);
                	mp.setInfo(info);
                	
			throw mp;
		}
		
		info.put("status", Constant.DB_OK );
		info.put("msg", "企业信息添加成功");
		return info;
		
        }

	/**
	 * 根据组合ID获取组合下的所有企业
	 */
	@Override
	public List<Company> selectCompanyListByGroupId(String groupId) {
		List<Company> companyList = null;
		try{
			companyList = companyMapper.selectCompanyListByGroupId(groupId);
		} catch (Exception e) {
			
		}
		return companyList;
	}

	@Override
	public Company selectCompanyByCompanyId(String companyId) {
		Company company = companyMapper.selectCompanyByCompanyId(companyId);
		return company;
	}
	
	
	/**
	 * 根据条件查询企业
	 */
	@Override
	public Map<String, Object> findCompanyByCriteria(Integer page, Integer rows, String groupId) {
		Integer count = null;
		List<Company> companyList = null;
		List<Group> groupList = null;
		
		// 组装参数
		Map<String, Object> param = Message.getInitInfo();
		param.put("status", Constant.DB_OK);
		param.put("page", (page - 1) * rows);
		param.put("rows", rows);
		param.put("groupId", groupId);
		
		try {
			// 查询企业
			companyList = companyMapper.findCompanyByCriteria(param);
			// 查询组合
			groupList = groupService.selectGroupList();
			// 为企业中的groupName属性赋值
			for(Group g : groupList) {
				for(Company c :companyList) {
					if(g.getGroupId().equals(c.getGroupId())) {
						c.setGroupName(g.getGroupName());
					}
				}
			}
			
			// 查询总记录数
			count = companyMapper.selectCount();
		} catch (Exception e) {
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询企业信息失败");
			MapperException mp = new MapperException("查询企业信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		
		// 组装数据
		param.clear();
		param.put("status", Constant.DB_OK);
		param.put("rows", companyList);
		param.put("total", count);
		return param;
	}

	@Override
	public ModelAndView showCompany(Company company, String viewName) {
		ModelAndView model = null;
		Group group = null;
		
		try {
			// 查询企业信息
			company = companyMapper.selectCompanyByCompanyId(company.getCompanyId());
			// 查询组合名
			group = groupService.selectGroupByGroupId(company.getGroupId());
			
			// 将企业信息填充到Model中，并设置跳转路径
	        	model = modelAddAttribute(company, viewName);
	        	
	        	if(group != null) {
	        		model.addObject("groupName", group.getGroupName());
	        	}
	        	
	        	model.addObject("groupId", company.getGroupId());
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询企业信息失败");
			MapperException mp = new MapperException("查询企业信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		return model;
	}
	
	/**
	 * 向ModelAndView中填充数据
	 * @param company
	 * @param viewName
	 * @return
	 */
	private ModelAndView modelAddAttribute(Company company, String viewName) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("companyId", company.getCompanyId());
		model.addObject("companyNum", company.getCompanyNum());
		model.addObject("companyName", company.getCompanyName());
		model.addObject("companyContacts", company.getCompanyContacts());
		model.addObject("companyAddress", company.getCompanyAddress());
		model.addObject("companyPhone", company.getCompanyPhone());
		model.addObject("companyEmail", company.getCompanyEmail());
		model.addObject("companyFax", company.getCompanyFax());
		model.addObject("comment", company.getComment());
		
		model.setViewName(viewName);
		
		return model;
	}

	/**
	 * 更新企业信息
	 */
	@Override
	public Map<String, Object> updateCompanyByCompanyId(Company company, ActiveUser activeUser) {
		Map<String, Object> info = Message.getInitInfo();
		// 设置更新信息
		company.setUpdateDate(new Date());
		company.setUpdateUser(activeUser.getSysUserId());
		
		// 表单校验
		info = validateForm(company);
		if(info.get("status").equals("1")) {
			return info;
		}

		try {
			companyMapper.updateCompanyByCompanyId(company);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "企业信息修改失败");
			MapperException mp = new MapperException("企业信息修改失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改成功");
		
		return info;
	}

	/**
	 * 表单校验
	 * @param company
	 * @return
	 */
	private Map<String, Object> validateForm(Company company) {
		Map<String, Object> info = Message.getInitInfo();
		
		String companyNum = company.getCompanyNum();
		String groupId = company.getGroupId();
		String companyName = company.getCompanyName();
		String companyContacts = company.getCompanyContacts();
		String companyAddress = company.getCompanyAddress();
		String companyPhone = company.getCompanyPhone();
		String companyEmail = company.getCompanyEmail();
		String companyFax = company.getCompanyFax();
		String comment = company.getComment();
		
		/**
		 * 校验组合名
		 *   非空
		 */
		if(groupId == null || groupId.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "组合不能为空");
			return info;
		}
		
		
		/**
		 * 校验企业名
		 *   非空
		 *   长度不能大于30
		 */
		if(companyName != null && !companyName.isEmpty()) {
			if(!Validate.isLength(companyName, 30)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业名长度不能超过30");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "企业名不能为空");
			return info;
		}
		
		/**
		 * 校验企业联系人
		 *   长度不能超过20
		 */
		if(companyContacts != null && !companyContacts.isEmpty()) {
			if(!Validate.isLength(companyContacts, 20)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业联系人长度不能超过20");
				return info;
			}
		}
		
		/**
		 * 校验企业地址
		 *   长度不能超过200
		 */
		if(companyAddress != null && !companyAddress.isEmpty()) {
			if(!Validate.isLength(companyAddress, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业地址长度不能超过200");
				return info;
			}
		}
		
		/**
		 * 校验企业联系方式
		 *   长度不能超过20
		 */
		if(companyPhone != null && !companyPhone.isEmpty()) {
			if(!Validate.isLength(companyPhone, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业联系方式长度不能超过20");
				return info;
			}
		}
		
		/**
		 * 校验企业邮箱
		 *   长度不能超过30
		 */
		if(companyEmail != null && !companyEmail.isEmpty()) {
			if(!Validate.isLength(companyEmail, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业邮箱长度不能超过30");
				return info;
			}
		}
		
		/**
		 * 校验企业传真
		 *   长度不能超过30
		 */
		if(companyFax != null && !companyFax.isEmpty()) {
			if(!Validate.isLength(companyFax, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业传真长度不能超过30");
				return info;
			}
		}
		
		/**
		 * 校验企业备注
		 *   长度不能超过200
		 */
		if(comment != null && !comment.isEmpty()) {
			if(!Validate.isLength(comment, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "企业备注长度不能超过200");
				return info;
			}
		}
		
		info.put("status", Constant.DB_OK);
		
		return info;
        }

	
	/**
	 * 通过主键删除企业
	 */
	@Override
	public Map<String, Object> deleteCompanyByCompanyId(Company company) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			companyMapper.deleteCompanyByCompanyId(company);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除企业失败");
			MapperException mp = new MapperException("组合企业失败", e);
			mp.setInfo(info);
			
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除组合成功");
		
		return info;
	}
	
	
}
