package cn.bf.service;

import java.util.List;
import java.util.Map;

import cn.bf.po.SysPermission;

public interface SysPermissionService {

	public List<SysPermission> findMenuListBySysUserId(String sysUserId);

	public List<SysPermission> findPermissionListBySysUserId(String sysUserId);

	public List<SysPermission> findMenuListBySysMenuId(Map<String, String> param);

}
