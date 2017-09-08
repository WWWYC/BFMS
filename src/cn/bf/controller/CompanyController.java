package cn.bf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;
import cn.bf.service.CompanyService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
public class CompanyController extends SuperClass {

        private static final long serialVersionUID = 1L;
        
        @Autowired
        private CompanyService companyService;
	
        /**
         * 跳转到添加企业页面
         * @return
         */
        @RequestMapping("/addCompany")
        public String addCompany() {
        	return "company/addCompany";
        }
        
        /**
         * 获取企业编号
         * @return
         */
        @RequestMapping("/getCompanyNum")
	@ResponseBody
	public Map<String, Object> getCompanyNum() {
		return companyService.getCompanyNum();
	}
        
        /**
         * 添加企业信息
         * @param company
         * @return
         */
        @RequestMapping(value = "/insertCompany", method=RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> insertGroup(Company company, HttpSession session){
        	Map<String, Object> info = Message.getInitInfo();
        	
        	// 取出当前用户
        	ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        	
        	try {
        		info = companyService.insertCompany(company, activeUser);
                } catch (MapperException e) {
	                e.printStackTrace();
	                
	                info = e.getInfo();
	                return info;
                }
        	return info;
        }
        
        /**
         * 根据组合ID获取组合下的所有企业
         * @param groupId
         * @return
         */
        @RequestMapping("/getCompanyList")
        @ResponseBody
        public List<Company> getCompanyList(String groupId) {
        	List<Company> companyList = companyService.selectCompanyListByGroupId(groupId);
        	return companyList;
        }
        
	/**
	 * 跳转到查询企业页面
	 * 
	 * @return
	 */
	@RequestMapping("/findCompany")
	public String findCompany() {
		return "company/findCompany";
	}
        
        
        /**
         * 查询企业分页信息
         */
	@RequestMapping("/findCompanyByCriteria")
        @ResponseBody
        public Map<String, Object> findCompanyByCriteria(HttpServletRequest request, String groupId, Integer page, Integer rows) {
        	
        	Map<String, Object> companyList = companyService.findCompanyByCriteria(page, rows, groupId);
        	
        	return companyList;
        }
	
	/**
	 * 查看企业信息
	 * 
	 * @param request
	 * @param company
	 * @return
	 */
	@RequestMapping("/showCompany")
	public ModelAndView showCompany(HttpServletRequest request, Company company) {
		ModelAndView model = companyService.showCompany(company, "company/showCompany");
		return model;
	}
	
	/**
	 * 编辑企业信息
	 * @param request
	 * @param company
	 * @return
	 */
	@RequestMapping("/editCompany")
	public ModelAndView editCompany(HttpServletRequest request, Company company) {
		ModelAndView model = companyService.showCompany(company, "company/editCompany");
		return model;
	}
	
	/**
	 * 更新企业信息
	 * 
	 * @param company
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateCompany")
	@ResponseBody
	public Map<String, Object> updateCompany(Company company, HttpSession session) {
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        	
        	Map<String, Object> info = Message.getInitInfo();
        	
        	try {
        		info = companyService.updateCompanyByCompanyId(company, activeUser);
                } catch (MapperException e) {
                	info = e.getInfo();
                }
        	
        	return info;
	}
	
	@RequestMapping(value = "/rmCompany", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rmCompany(@RequestBody Company company) {
		Map<String, Object> info = Message.getInitInfo();
		try {
			info = companyService.deleteCompanyByCompanyId(company);
		} catch (MapperException e) {
			e.printStackTrace();
			info = e.getInfo();
                }
		
		return info;
	}
}
