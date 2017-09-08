package cn.bf.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;

public interface CompanyService {

	public Map<String, Object> getCompanyNum();

	public Map<String, Object> insertCompany(Company company, ActiveUser activeUser) throws MapperException;

	public List<Company> selectCompanyListByGroupId(String groupId);

	public Company selectCompanyByCompanyId(String companyId);

	public Map<String, Object> findCompanyByCriteria(Integer page, Integer rows, String groupId);

	public ModelAndView showCompany(Company company, String string);

	public Map<String, Object> updateCompanyByCompanyId(Company company, ActiveUser activeUser);

	public Map<String, Object> deleteCompanyByCompanyId(Company company);

}
