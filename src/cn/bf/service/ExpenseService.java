package cn.bf.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import cn.bf.po.ActiveUser;
import cn.bf.po.Expense;

public interface ExpenseService {
	public Map<String, Object> selectExpenseByCriteria(Expense expense, Integer page, Integer rows, String groupId, String companyId);

	public ModelAndView selectExpenseByUuid(Expense expense, String string);

	public Map<String, Object> insertExpense(Expense expense);

	public Map<String, Object> updateExpense(Expense expense, ActiveUser activeUser);

	public Map<String, Object> deleteExpenseByUuid(Expense expense);
}
