package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.SysPermission;

public interface SysPermissionMapper {
	int deleteByPrimaryKey(String syspermissionid);

	int insert(SysPermission record);

	int insertSelective(SysPermission record);

	SysPermission selectByPrimaryKey(String syspermissionid);

	int updateByPrimaryKeySelective(SysPermission record);

	int updateByPrimaryKey(SysPermission record);

	List<SysPermission> findMenuListBySysUserId(String sysUserId);

	List<SysPermission> findPermissionListBySysUserId(String sysUserId);

	List<SysPermission> findMenuListBySysMenuId(Map<String, String> param);
}