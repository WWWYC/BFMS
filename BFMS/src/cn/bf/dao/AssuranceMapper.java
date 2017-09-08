package cn.bf.dao;

import cn.bf.po.Assurance;

public interface AssuranceMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Assurance record);

    int insertSelective(Assurance record);

    Assurance selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Assurance record);

    int updateByPrimaryKey(Assurance record);
}