package cn.bf.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Student;

public interface StudentService {

	public Map<String, Object> getStuNum();

	public Map<String, Object> insertStudent(MultipartHttpServletRequest request, ActiveUser activeUser) throws MapperException, IllegalAccessException, InvocationTargetException, IOException;

	public Map<String, Object> selectStudentByCriteria(Integer page, Integer rows, Student student, String groupId);

	public ModelAndView selectStudentByStuId(Student student, String viewName);

	public Map<String, Object> updateStudent(MultipartHttpServletRequest request, ActiveUser activeUser) throws IllegalAccessException, InvocationTargetException;

	public Map<String, Object> deleteStudentByStuId(Student student);

	public List<Student> seleStudentListByCompanyId(String companyId);

}
