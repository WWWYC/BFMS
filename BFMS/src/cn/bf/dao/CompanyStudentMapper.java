package cn.bf.dao;

import cn.bf.po.CompanyStudent;

public interface CompanyStudentMapper {
	int deleteByPrimaryKey(String uuid);

	int insert(CompanyStudent record);

	int insertSelective(CompanyStudent record);

	CompanyStudent selectByPrimaryKey(String uuid);

	int updateByPrimaryKeySelective(CompanyStudent record);

	int updateByPrimaryKey(CompanyStudent record);

	void insertCompanyStudent(CompanyStudent companyStudent);
}