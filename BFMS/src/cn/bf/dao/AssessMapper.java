package cn.bf.dao;

import cn.bf.po.Assess;

public interface AssessMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Assess record);

    int insertSelective(Assess record);

    Assess selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Assess record);

    int updateByPrimaryKey(Assess record);
}