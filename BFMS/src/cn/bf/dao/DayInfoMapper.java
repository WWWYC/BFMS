package cn.bf.dao;

import cn.bf.po.DayInfo;

public interface DayInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DayInfo record);

    int insertSelective(DayInfo record);

    DayInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(DayInfo record);

    int updateByPrimaryKey(DayInfo record);
}