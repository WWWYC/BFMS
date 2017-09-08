package cn.bf.dao;

import cn.bf.po.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String sysroleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String sysroleid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}