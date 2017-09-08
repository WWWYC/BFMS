package cn.bf.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import cn.bf.po.Expense;
import cn.bf.service.ExpenseService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
public class ExpenseController extends SuperClass {
	
        private static final long serialVersionUID = 1L;
	@Autowired
	private ExpenseService expenseService;
	
	/**
	 * 跳转到查询信息列表页面
	 * @return
	 */
	@RequestMapping(value = "/findExpense")
	public String findExpense() {
		
		return "expense/findExpense";
	}
	
	/**
	 * 多条件查询分页信息
	 * @param expense
	 * @param page
	 * @param rows
	 * @param groupId
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/findExpenseByCriteria")
	@ResponseBody
	public Map<String, Object> selectExpenseByCriteria(Expense expense, Integer page, Integer rows, String groupId, String companyId) {
		Map<String, Object> info = Message.getInitInfo();
		try {
			info = expenseService.selectExpenseByCriteria(expense, page, rows, groupId, companyId);
		} catch (MapperException e) {
			info = e.getInfo();
		}

		return info;
	}
	
	/**
	 * 显示费用详细信息
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "showExpense")
	public ModelAndView showExpense(Expense expense) {
		
		ModelAndView model = expenseService.selectExpenseByUuid(expense, "expense/showExpense");
		return model;
		
	}

	/**
	 * 跳转到编辑页面
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "/editExpense")
	public ModelAndView editExpense(Expense expense) {
		
		ModelAndView model = expenseService.selectExpenseByUuid(expense, "expense/editExpense");
		return model;
	}
	
	/**
	 * 更新费用信息
	 * @param expense
	 * @param request
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "/updateExpense", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateExpense(Expense expense, HttpServletRequest request, HttpSession session) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> info = Message.getInitInfo();
		
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		try {
			info = expenseService.updateExpense(expense, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	/**
	 * 删除费用信息
	 * @param expense
	 * @return
	 */
	@RequestMapping(value = "/rmExpense", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rmExpense(@RequestBody Expense expense) {
		Map<String, Object> info = Message.getInitInfo();
		try {
			info = expenseService.deleteExpenseByUuid(expense);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		
		binder.registerCustomEditor(Date.class, editor);
	}
	
}
