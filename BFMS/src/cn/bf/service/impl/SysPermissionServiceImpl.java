package cn.bf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bf.dao.SysPermissionMapper;
import cn.bf.po.SysPermission;
import cn.bf.service.SysPermissionService;
import cn.bf.util.SuperClass;

@Service
public class SysPermissionServiceImpl extends SuperClass implements SysPermissionService {

        private static final long serialVersionUID = 1L;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Override
	public List<SysPermission> findMenuListBySysUserId(String sysUserId) {
		List<SysPermission> menuList = sysPermissionMapper.findMenuListBySysUserId(sysUserId);
		return menuList;
	}

	@Override
        public List<SysPermission> findPermissionListBySysUserId(String sysUserId) {
		List<SysPermission> permissionList = sysPermissionMapper.findPermissionListBySysUserId(sysUserId);
		return permissionList;
        }

	@Override
        public List<SysPermission> findMenuListBySysMenuId(Map<String, String> param) {
	        List<SysPermission> permissionList = sysPermissionMapper.findMenuListBySysMenuId(param);
	        return permissionList;
        }
}
