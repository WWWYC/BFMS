package cn.bf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bf.dao.SysUserMapper;
import cn.bf.po.SysUser;
import cn.bf.service.SysUserService;
import cn.bf.util.SuperClass;

@Service
public class SysUserServiceImpl extends SuperClass implements SysUserService {
        private static final long serialVersionUID = 1L;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
        public SysUser selectSysUserByAccount(String account){
		SysUser sysUser = sysUserMapper.selectSysUserByAccount(account);
		return sysUser;
	}

	@Override
        public SysUser selectByPrimaryKey(String sysUserId) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserId);
		return sysUser;
        }
	
	
}
