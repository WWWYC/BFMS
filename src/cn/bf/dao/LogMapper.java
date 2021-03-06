package cn.bf.dao;

import cn.bf.po.Log;

public interface LogMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}