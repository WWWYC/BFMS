package cn.bf.dao;

import java.util.Map;

import cn.bf.po.GroupCompany;

public interface GroupCompanyMapper {
	Integer deleteByPrimaryKey(String uuid);

	Integer insert(GroupCompany record);

	Integer insertSelective(GroupCompany record);

	GroupCompany selectByPrimaryKey(String uuid);

	Integer updateByPrimaryKeySelective(GroupCompany record);

	Integer updateByPrimaryKey(GroupCompany record);

	void insertGroupCompany(Map<String, String> param);
}