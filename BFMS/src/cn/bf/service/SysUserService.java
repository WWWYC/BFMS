package cn.bf.service;

import cn.bf.po.SysUser;

public interface SysUserService {

	public SysUser selectSysUserByAccount(String account);
	
	public SysUser selectByPrimaryKey(String sysUserId);

	
}
