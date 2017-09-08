package cn.bf.dao;

import cn.bf.po.MonthInfo;

public interface MonthInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(MonthInfo record);

    int insertSelective(MonthInfo record);

    MonthInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(MonthInfo record);

    int updateByPrimaryKey(MonthInfo record);
}