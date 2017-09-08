package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.Expense;

public interface ExpenseMapper {
	int deleteByPrimaryKey(String uuid);

	int insert(Expense record);

	int insertSelective(Expense record);

	Expense selectByPrimaryKey(String uuid);

	int updateByPrimaryKeySelective(Expense record);

	int updateByPrimaryKey(Expense record);

	List<Expense> selectExpenseByCriteria(Map<String, Object> param);

	Integer selectExpenseCountByCriteria(Map<String, Object> param);

	List<Expense> selectAllExpense(Map<String, Object> param);

	Expense selectExpenseByUuid(Expense expense);

	void insertExpense(Expense expense);

	void updateExpenseByUuid(Expense expense);

	void deleteExpenseByUuid(Expense expense);
}