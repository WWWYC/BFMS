package cn.bf.dao;

import cn.bf.po.Contract;

public interface ContractMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}