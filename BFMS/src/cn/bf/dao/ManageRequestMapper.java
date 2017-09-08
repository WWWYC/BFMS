package cn.bf.dao;

import cn.bf.po.ManageRequest;

public interface ManageRequestMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ManageRequest record);

    int insertSelective(ManageRequest record);

    ManageRequest selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ManageRequest record);

    int updateByPrimaryKey(ManageRequest record);
}