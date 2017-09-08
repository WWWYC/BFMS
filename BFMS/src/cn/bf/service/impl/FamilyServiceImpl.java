package cn.bf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bf.dao.FamilyMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Family;
import cn.bf.service.FamilyService;
import cn.bf.util.Constant;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;
import cn.bf.util.Validate;

@Service
public class FamilyServiceImpl extends SuperClass implements FamilyService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FamilyMapper familyMapper;

	@Override
	public Map<String, Object> getFamilyList(Family family) {
		Map<String, Object> info = Message.getInitInfo();
		
		List<Family> familyList = null;

		try {
			familyList = familyMapper.selectFamilyByStuId(family);
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询家庭成员信息失败");
			MapperException mp = new MapperException("查询家庭成员信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("familyList", familyList);		
		
		return info;
	}

	@Override
	@Transactional
	public Map<String, Object> updateFamily(Family[] familyList, ActiveUser activeUser) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			for (Family family : familyList) {
				info = validateForm(family);
				// 如果校验失败则抛出异常
				if(!info.get("msg").equals("ok")) {
					MapperException me = new MapperException("联系人信息更新失败", info);
		        		throw me;
		        	}
				
				/**
				 * 如果uuid为null，则表示这是一条新数据，使用insert
				 */
				if(family.getUuid() == null || family.getUuid().isEmpty()) {
					family.setUuid(UUIDUtil.getUuid());
					family.setCreateDate(new Date());
					family.setCreateUser(activeUser.getSysUserId());
					family.setUpdateDate(new Date());
					family.setUpdateUser(activeUser.getSysUserId());
					
					familyMapper.insertFamily(family);
				} else {
					family.setUpdateDate(new Date());
					family.setUpdateUser(activeUser.getSysUserId());
					
					familyMapper.updateFamilyByUuid(family);
				}
			}
		} catch (MapperException e) {
			e.printStackTrace();
//			info.put("status", Constant.DB_ERR);
//                	info.put("msg", "联系人信息更新失败");
//                	MapperException mp = new MapperException("联系人信息更新失败", e);
//                	mp.setInfo(info);
			throw e;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改家庭成员成功");
		
		return info;
	}
	
	/**
	 * 通过主键删除家庭成员
	 */
	@Override
	public Map<String, Object> deleteFamilyByUuid(Family family) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			familyMapper.deleteFamilyByUuid(family);
		} catch (Exception e) {
			e.printStackTrace();
			info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除家庭成员信息失败");
			MapperException mp = new MapperException("删除家庭成员信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除成功");		
		
		return info;
	}

	private Map<String, Object> validateForm(Family family) {
		Map<String, Object> info = Message.getInitInfo();

		String stuId = family.getStuId();
		String familyName = family.getFamilyName();
		String familyRelation = family.getFamilyRelation();
		String familyNum = family.getFamilyNum();
		String familyAddress = family.getFamilyAddress();
		String comment = family.getComment();
		
		/**
		 * 判断学号
		 *   不能为null
		 *   不能为""
		 */
		if(stuId == null || stuId.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "学号不能为空");
			return info;
		}
		
		/**
		 * 判断家庭成员姓名
		 *   不能为空
		 *   必须是汉字
		 *   不能超过6位
		 */
		if(familyName == null || familyName.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "姓名不能为空");
			return info;
		} else if (!Validate.isZh(familyName)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "姓名必须是汉字");
			return info;
		} else if (!Validate.isLength(familyName, 6)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "姓名不能超过6位汉字");
			return info;
		}
		
		/**
		 * 判断关系
		 *   不能为空
		 *   必须是汉字
		 *   不能超过6位
		 */
		if(familyRelation == null || familyRelation.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "成员关系不能为空");
			return info;
		} else if (!Validate.isZh(familyRelation)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "成员关系必须是汉字");
			return info;
		} else if (!Validate.isLength(familyRelation, 6)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "成员关系不能超过6位");
			return info;
		}
		
		/**
		 * 判断电话号码
		 *   不能为空
		 *   不能超过20位
		 *   必须是数字
		 */
		if(familyNum == null || familyNum.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "联系方式不能为空");
			return info;
		} else if (!Validate.isNum(familyNum)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "联系方式必须是数字");
			return info;
		} else if (!Validate.isLength(familyNum, 20)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "联系方式不能超过20位");
			return info;
		}
		
		/**
		 * 判断家庭住址
		 *   不能为空
		 *   不能超过100个汉字
		 */
		if(familyAddress == null || familyAddress.isEmpty()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "住址不能为空");
			return info;
		} else if (!Validate.isLength(familyAddress, 100)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "住址不能超过100个汉字");
			return info;
		}
		
		/**
		 * 判断备注
		 *   不能超过100个汉字
		 */
		if (!Validate.isLength(comment, 100)) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "住址不能超过100个汉字");
			return info;
		}
		
//		info.put("status", "ok");
		info.put("msg", "ok");
		
		return info;
	}

}
