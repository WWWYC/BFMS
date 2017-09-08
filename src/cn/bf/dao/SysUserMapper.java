package cn.bf.dao;

import cn.bf.po.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String sysuserid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String sysUserId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectSysUserByAccount(String account);
}