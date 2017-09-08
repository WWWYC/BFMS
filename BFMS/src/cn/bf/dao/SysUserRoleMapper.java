package cn.bf.dao;

import cn.bf.po.SysUserRole;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}