package cn.bf.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Student;
import cn.bf.service.StudentService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
public class StudentController extends SuperClass {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentService studentService;

	/**
	 * 跳转到添加学生页面
	 * @return
	 */
	@RequestMapping("/addStudent")
	public String addStudent() {
		return "student/addStudent";
	}

	/**
	 * 获取当前学生学号
	 * @return
	 */
	@RequestMapping("/getStuNum")
	@ResponseBody
	public Map<String, Object> getStuNum() {
		Map<String, Object> info = studentService.getStuNum();
		return info;
	}

	/**
	 * 添加学生信息
	 * @param request
	 * @param session
	 * @return
	 * @throws MapperException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException 
	 */
        @RequestMapping(value = "/insertStudent", method=RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> insertStudent(MultipartHttpServletRequest request, HttpSession session) throws MapperException, IllegalAccessException, InvocationTargetException, IOException {
        	Map<String, Object> info = Message.getInitInfo();
        	
        	// 取出当前用户
        	ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        	
        	try {
	                info = studentService.insertStudent(request, activeUser);
                } catch (MapperException e) {
                	
                	info = e.getInfo();
                }
        	
        	return info;
        }
        
        /**
         * 跳转到查询学生页面
         * @param student
         * @return
         */
        @RequestMapping("/findStudent")
        public String findAllStudent(Student student) {
        	
        	return "student/findStudent";
        }
        
	/**
	 * 根据条件查询学生信息
	 * 
	 * @param student
	 * @param page
	 * @param rows
	 * @param companyId
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping("/findStudentByCriteria")
	@ResponseBody
	public Map<String, Object> findStudentByCriteria(Student student, Integer page, Integer rows, String groupId) throws InterruptedException {
		Map<String, Object> info = Message.getInitInfo();
		try {
			info = studentService.selectStudentByCriteria(page, rows, student, groupId);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		

		return info;
        }
        
        /**
         * 显示学生个人详细信息
         * @param student
         * @return
         */
        @RequestMapping("/showStudent")
        public ModelAndView showStudent(Student student) {
        	// 根据学生ID查询当前学生信息
        	return studentService.selectStudentByStuId(student, "student/showStudent");
        }
        
        /**
         * 编辑学生信息
         * @param student
         * @return
         */
        @RequestMapping("/editStudent")
	public ModelAndView editStudent(Student student) {
		// 根据学生ID查询当前学生信息
		return studentService.selectStudentByStuId(student, "student/editStudent");
	}

        /**
         * 更新学生信息
         * 
         * @param request
         * @param session
         * @return
         * @throws MapperException
         * @throws IllegalAccessException
         * @throws InvocationTargetException
         * @throws IOException
         */
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateStudent(MultipartHttpServletRequest request, HttpSession session) throws MapperException, IllegalAccessException, InvocationTargetException, IOException {
		// 取出当前用户
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");

		Map<String, Object> info = null;
		try {
			info = studentService.updateStudent(request, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}

		return info;
	}
	
	/**
	 * 删除学生
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/rmStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeStudent(@RequestBody Student student) {
		Map<String, Object> info = null;
		try {
			info = studentService.deleteStudentByStuId(student);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		return info;
	}
	
	@RequestMapping(value = "/getStudentList", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> getStudentListByCompanyId(String companyId) {
		
		return studentService.seleStudentListByCompanyId(companyId);
	}

}
