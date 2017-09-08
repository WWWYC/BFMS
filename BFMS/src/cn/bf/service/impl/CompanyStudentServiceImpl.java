package cn.bf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bf.dao.CompanyStudentMapper;
import cn.bf.po.CompanyStudent;
import cn.bf.service.CompanyStudentService;
import cn.bf.util.SuperClass;
import cn.bf.util.UUIDUtil;

@Service
public class CompanyStudentServiceImpl extends SuperClass implements CompanyStudentService {
	
        private static final long serialVersionUID = 1L;
        
	@Autowired
	private CompanyStudentMapper companyStudentMapper;

	@Override
	public void insertCompanyStudent(String companyId, String stuId) {
		// 组装参数
		CompanyStudent companyStudent = new CompanyStudent();
		companyStudent.setUuid(UUIDUtil.getUuid());
		companyStudent.setStuId(stuId);
		companyStudent.setCompanyId(companyId);
		
		companyStudentMapper.insertCompanyStudent(companyStudent);
	}

}
