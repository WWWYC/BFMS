package cn.bf.dao;

import java.util.List;

import cn.bf.po.Family;

public interface FamilyMapper {
	int deleteByPrimaryKey(String uuid);

	int insert(Family record);

	int insertSelective(Family record);

	Family selectByPrimaryKey(String uuid);

	int updateByPrimaryKeySelective(Family record);

	int updateByPrimaryKey(Family record);

	List<Family> selectFamilyByStuId(Family family);

	void insertFamily(Family family);

	void updateFamilyByUuid(Family family);

	void deleteFamilyByUuid(Family family);
}