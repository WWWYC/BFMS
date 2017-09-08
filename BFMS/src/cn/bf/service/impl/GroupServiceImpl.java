package cn.bf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.dao.GroupMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Group;
import cn.bf.service.GroupService;
import cn.bf.util.Constant;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;
import cn.bf.util.Validate;

@Service
public class GroupServiceImpl extends SuperClass implements GroupService {

        private static final long serialVersionUID = 1L;
        
        @Autowired
        private GroupMapper groupMapper;

        /**
         * 获取组合编号
         */
	@Override
        public Map<String, Object> getGroupNum() {
		
		Map<String, Object> info = Message.getInitInfo();
		
		Integer groupNum = groupMapper.selectMaxGroupNum();
		
		// 如果没有查询到组合编号，则置为001
		if(groupNum == null){
			info.put("status", Constant.DB_OK);
			info.put("groupNum", "001");
			return info;
		} else if (groupNum >= 1 && groupNum < 9) {
			info.put("status", Constant.DB_OK);
			info.put("groupNum", "00" + (groupNum + 1));
			return info;
		} else if (groupNum >= 9 && groupNum < 99) {
			info.put("status", Constant.DB_OK);
			info.put("groupNum", "0" + (groupNum + 1));
			return info;
		} else if (groupNum >= 99 && groupNum < 999) {
			info.put("status", Constant.DB_OK);
			info.put("groupNum", groupNum + 1);
			return info;
		} else {
			info.put("status", Constant.DB_OK);
			info.put("groupNum", groupNum + 1);
			return info;
		}
        }

	/**
	 * 添加组合
	 * @throws MapperException 
	 */
	@Override
        public Map<String, Object> insertGroup(Group group, ActiveUser activeUser) throws MapperException {
		Map<String, Object> info = Message.getInitInfo();
		
		// 设置主键
		group.setGroupId(UUIDUtil.getUuid());
		
		// 设置createUser
		group.setCreateUser(activeUser.getSysUserId());
		
		// 设置updateUser
		group.setUpdateUser(activeUser.getSysUserId());
		
		// 设置创建、更新时间
		group.setCreateDate(new Date());
		group.setUpdateDate(new Date());
		
		// 表单校验
		info = validateForm(group);
		if(info.get("status").equals("1")) {
			return info;
		}
		
		try {
			groupMapper.insertGroup(group);
		} catch (Exception e) {
//			throw new MapperException("组合信息添加失败", e);
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "学生信息添加失败");
			MapperException mp = new MapperException("组合信息添加失败", e);
			mp.setInfo(info);
			throw mp;
		}
		info.put("status", Constant.DB_OK);
		info.put("msg", "组合信息添加成功");
		
		return info;
		
        }

	@Override
	public List<Group> selectGroupList() {
		List<Group> groupList = groupMapper.selectGroupList();
		return groupList;
	}

	@Override
	public Group selectGroupByGroupId(String groupId) {
		Group group = groupMapper.selectGroupByGroupId(groupId);
		return group;
	}

	/**
	 * 查询分页信息
	 */
	@Override
	public Map<String, Object> findGroupPageList(Integer page, Integer rows) {
		Integer count = null;
		List<Group> groupList = null;
		
		// 组装参数
		Map<String, Object> param = Message.getInitInfo();
		param.put("status", Constant.DB_OK);
		param.put("page", (page - 1) * rows);
		param.put("rows", rows);
		
		try {
			
			groupList = groupMapper.findGroupPageList(param);
			
			// 查询总记录数
			count = groupMapper.selectCount();
		} catch (Exception e) {
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询组合信息失败");
			MapperException mp = new MapperException("查询组合信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		
		// 组装数据
		param.clear();
		param.put("status", Constant.DB_OK);
		param.put("rows", groupList);
		param.put("total", count);
		return param;
	}

	@Override
	public ModelAndView showGroup(String groupId, String viewName) {
		ModelAndView model = new ModelAndView();
		
		Group group = this.selectGroupByGroupId(groupId);
		
		// 将信息填充到ModelAndView中，并设置跳转路径
		model = modelAddAttribute(group, viewName);
		return model;
	}

	
	/**
	 * 向ModelAndView中填充数据
	 * @param group
	 * @param viewName
	 * @return
	 */
	private ModelAndView modelAddAttribute(Group group, String viewName) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("groupId", group.getGroupId());
		model.addObject("groupNum", group.getGroupNum());
		model.addObject("groupName", group.getGroupName());
		model.addObject("groupContacts", group.getGroupContacts());
		model.addObject("groupAddress", group.getGroupAddress());
		model.addObject("groupPhone", group.getGroupPhone());
		model.addObject("groupEmail", group.getGroupEmail());
		model.addObject("groupFax", group.getGroupFax());
		model.addObject("comment", group.getComment());
		
		model.setViewName(viewName);
		
		return model;
	}

	/**
	 * 更新组合
	 */
	@Override
	public Map<String, Object> updateGroupByGroupId(Group group, ActiveUser activeUser) {
		Map<String, Object> info = Message.getInitInfo();
		// 设置更新信息
		group.setUpdateDate(new Date());
		group.setUpdateUser(activeUser.getSysUserId());
		
		// 表单校验
		info = validateForm(group);
		if(info.get("status").equals("1")) {
			return info;
		}
		
		try {
			groupMapper.updateGroupByGroupId(group);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "组合信息修改失败");
			MapperException mp = new MapperException("组合信息修改失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改成功");
		
		return info;
	}
	
	/**
	 * 表单校验
	 * @param group
	 * @return
	 */
	private Map<String, Object> validateForm(Group group) {
		Map<String, Object> info = Message.getInitInfo();
		
		String groupNum = group.getGroupNum();
		String groupName = group.getGroupName();
		String groupContacts = group.getGroupContacts();
		String groupAddress = group.getGroupAddress();
		String groupPhone = group.getGroupPhone();
		String groupEmail = group.getGroupEmail();
		String groupFax = group.getGroupFax();
		String comment = group.getComment();
		
		
		/**
		 * 校验组合名
		 *   非空
		 *   长度不能大于30
		 */
		if(groupName != null && !groupName.isEmpty()) {
			if(!Validate.isLength(groupName, 30)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合名长度不能超过30");
				return info;
			}
		} else {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "组合名不能为空");
			return info;
		}
		
		/**
		 * 校验组合联系人
		 *   长度不能超过20
		 */
		if(groupContacts != null && !groupContacts.isEmpty()) {
			if(!Validate.isLength(groupContacts, 20)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合联系人长度不能超过20");
				return info;
			}
		}
		
		/**
		 * 校验组合地址
		 *   长度不能超过200
		 */
		if(groupAddress != null && !groupAddress.isEmpty()) {
			if(!Validate.isLength(groupAddress, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合地址长度不能超过200");
				return info;
			}
		}
		
		/**
		 * 校验组合联系方式
		 *   长度不能超过20
		 */
		if(groupPhone != null && !groupPhone.isEmpty()) {
			if(!Validate.isLength(groupPhone, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合联系方式长度不能超过20");
				return info;
			}
		}
		
		/**
		 * 校验组合邮箱
		 *   长度不能超过30
		 */
		if(groupEmail != null && !groupEmail.isEmpty()) {
			if(!Validate.isLength(groupEmail, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合邮箱长度不能超过30");
				return info;
			}
		}
		
		/**
		 * 校验组合传真
		 *   长度不能超过30
		 */
		if(groupFax != null && !groupFax.isEmpty()) {
			if(!Validate.isLength(groupFax, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合传真长度不能超过30");
				return info;
			}
		}
		
		/**
		 * 校验组合备注
		 *   长度不能超过200
		 */
		if(comment != null && !comment.isEmpty()) {
			if(!Validate.isLength(comment, 200)) {
				info.put("status", Constant.DB_ERR);
				info.put("msg", "组合备注长度不能超过200");
				return info;
			}
		}
		
		info.put("status", Constant.DB_OK);
		
		return info;
	}

	/**
	 * 通过主键删除组合
	 */
	@Override
        public Map<String, Object> deleteGroupByGroupId(Group group) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			groupMapper.deleteGroupByGroupId(group);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除组合失败");
			MapperException mp = new MapperException("组合删除失败", e);
			mp.setInfo(info);
			
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除组合成功");
		
		return info;
	}
	
}
