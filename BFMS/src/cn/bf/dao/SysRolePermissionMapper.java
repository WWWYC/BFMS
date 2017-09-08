package cn.bf.dao;

import cn.bf.po.SysRolePermission;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}