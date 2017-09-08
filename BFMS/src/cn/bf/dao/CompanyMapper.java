package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.Company;

public interface CompanyMapper {
	Integer deleteByPrimaryKey(String companyid);

	Integer insert(Company record);

	Integer insertSelective(Company record);

	Company selectByPrimaryKey(String companyid);

	Integer updateByPrimaryKeySelective(Company record);

	Integer updateByPrimaryKey(Company record);

	Integer selectCount();

	void insertCompany(Company company);

	List<Company> selectCompanyListByGroupId(String groupId);

	Integer selectMaxCompanyNum();

	Company selectCompanyByCompanyId(String companyId);

	List<Company> findCompanyByCriteria(Map<String, Object> param);

	void updateCompanyByCompanyId(Company company);

	void deleteCompanyByCompanyId(Company company);
}