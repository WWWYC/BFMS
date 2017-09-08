package cn.bf.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Vacation;
import cn.bf.service.VacationService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
@RequestMapping(value = "/vacation")
public class VacationController extends SuperClass {
	
        private static final long serialVersionUID = 1L;
        
        @Autowired
        private VacationService vacationService;

	/**
	 * 跳转到查询请假信息页面
	 * @return
	 */
	@RequestMapping(value = "/findVacation", method = RequestMethod.GET)
	public String findVacation() {
		return "vacation/findVacation";
	}
	
	/**
	 * 根据条件查询请假信息
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/getVacationListByCriteria", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getVacationListByCriteria(String name, Date startDate, Date endDate, Integer page, Integer rows) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			info = vacationService.getVacationListByCriteria(name, startDate, endDate, page, rows);
		} catch(MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	/**
	 * 跳转到添加请假信息页面
	 * @return
	 */
	@RequestMapping(value = "/addVacation", method = RequestMethod.GET)
	public String addVacation(){
		return "vacation/addVacation";
	}
	
	/**
	 * 增加一条请假信息
	 * @param vacation
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/insertVacation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertVacation(Vacation vacation, HttpSession session) {
		Map<String, Object> info = Message.getInitInfo();
		
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		try {
			info = vacationService.insertVacation(vacation, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}

		return info;
	}
	
	/**
	 * 查看请假详细信息
	 * @param vacation
	 * @return
	 */
	@RequestMapping(value = "showVacation", method = RequestMethod.GET)
	public ModelAndView showVacation(Vacation vacation) {
		ModelAndView model = null;
		
		model = vacationService.showVacation(vacation, "vacation/showVacation");
		
		return model;
	}
	
	/**
	 * 跳转到编辑请假信息页面
	 * @param vacation
	 * @return
	 */
	@RequestMapping(value = "/editVacation", method = RequestMethod.GET)
	public ModelAndView editVacation(Vacation vacation) {
		ModelAndView model = null;
		
		model = vacationService.showVacation(vacation, "vacation/editVacation");
		
		return model;
	}
	
	/**
	 * 更新请假信息
	 * @param vacation
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateVacation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateVacation(Vacation vacation, HttpSession session) {
		Map<String, Object> info = Message.getInitInfo();
		
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		try {
			info = vacationService.updateVacationByUuid(vacation, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	@RequestMapping(value = "/rmVacation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeVacation(@RequestBody Vacation vacation) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			info = vacationService.deleteVacationByUuid(vacation);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		
		binder.registerCustomEditor(Date.class, editor);
	}
}
