package cn.bf.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.dao.ExpenseMapper;
import cn.bf.dao.StudentMapper;
import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Company;
import cn.bf.po.Expense;
import cn.bf.po.Student;
import cn.bf.service.ExpenseService;
import cn.bf.util.Constant;
import cn.bf.util.Formatter;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;
import cn.bf.util.Validate;

@Service
public class ExpenseServiceImpl extends SuperClass implements ExpenseService {
	
        private static final long serialVersionUID = 1L;
        
        @Autowired
        private ExpenseMapper expenseMapper;
        
        @Autowired
        private StudentMapper studentMapper;

        /**
         * 根据条件分页查询费用信息
         */
	@Override
        public Map<String, Object> selectExpenseByCriteria(Expense expense, Integer page, Integer rows, String groupId, String companyId) {
		List<Expense> expenseList = null;
		Integer count = null;
		List<Student> studentList = null;
		
		// 组装参数
		Map<String, Object> param = Message.getInitInfo();
		param.put("status", Constant.DB_OK);
		param.put("expense", expense);
		param.put("page", (page - 1) * rows);
		param.put("rows", rows);
		param.put("companyId", companyId);
		param.put("groupId", groupId);
		
		try {
			// 如果expense为null，则表示查询全部的信息
			expenseList = expenseMapper.selectExpenseByCriteria(param);
			
			// 查询学生
			studentList = studentMapper.selectAllStudent();
			
			// 查询总记录数
			count = expenseMapper.selectExpenseCountByCriteria(param);
			
			// 计算总金额与欠费
			for(Expense e : expenseList) {
				// 总费用
				e.setTotalExp(
						Formatter.nullToInt(e.getManageExp()) + 
						Formatter.nullToInt(e.getTrainExp()) + 
						Formatter.nullToInt(e.getLawyerExp()) + 
						Formatter.nullToInt(e.getQualifiedExp()));
				// 已交费用
				e.setSumExp(
						Formatter.nullToInt(e.getExpenseSt()) + 
						Formatter.nullToInt(e.getExpenseNd()) + 
						Formatter.nullToInt(e.getExpenseRd()) + 
						Formatter.nullToInt(e.getExpenseTh()));
				// 计算欠费
				e.setDebt(
						Formatter.nullToInt(e.getTotalExp()) - 
						Formatter.nullToInt(e.getSumExp()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询费用信息失败");
			MapperException mp = new MapperException("查询费用信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		
		// 组装数据
		param.clear();
		param.put("status", Constant.DB_OK);
		param.put("rows", expenseList);
		param.put("total", count);
		return param;
        }

	/**
	 * 查询费用信息，并跳转到显示页面
	 */
	@Override
	public ModelAndView selectExpenseByUuid(Expense expense, String viewName) {
		ModelAndView model = null;
		
		try {
			expense = expenseMapper.selectExpenseByUuid(expense);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> info = Message.getInitInfo();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "查询费用信息失败");
			MapperException mp = new MapperException("查询费用信息失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		// 将数据填充到ModelAndView中
		model = modelAddAttribute(expense, viewName);
		return model;
	}
	
	/**
	 * 更新费用信息
	 */
	@Transactional
	public Map<String, Object> updateExpense(Expense expense, ActiveUser activeUser){
		Map<String, Object> info = Message.getInitInfo();
		// 设置更新信息
		expense.setUpdateDate(new Date());
		expense.setUpdateUser(activeUser.getSysUserId());
		
		
		// 表单校验
		info = validateForm(expense);
		if(info.get("status").equals("1")) {
			return info;
		}
		
		try {
			expenseMapper.updateExpenseByUuid(expense);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "费用信息修改失败");
			MapperException mp = new MapperException("费用信息修改失败", e);
			mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "修改成功");
		
		return info;
	}
	
	/**
	 * 根据主键删除费用信息
	 */
	@Override
	public Map<String, Object> deleteExpenseByUuid(Expense expense) {
		Map<String, Object> info = Message.getInitInfo();
		try {
			expenseMapper.deleteExpenseByUuid(expense);
		} catch (Exception e) {
			e.printStackTrace();
			info.put("status", Constant.DB_ERR);
			info.put("msg", "删除费用信息失败");
			MapperException mp = new MapperException("组合费用信息失败", e);
			mp.setInfo(info);
			
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "删除费用信息成功");
		
		return info;
	}
	
	/**
	 * 表单校验
	 * @param expense
	 * @return
	 */
	private Map<String, Object> validateForm(Expense expense){
		Map<String, Object> info = Message.getInitInfo();
		
		String uuid = expense.getUuid();
		String stuId = expense.getStuId();
		Integer manageExp = Formatter.nullToInt(expense.getManageExp());
		Integer trainExp = Formatter.nullToInt(expense.getTrainExp());
		Integer lawyerExp = Formatter.nullToInt(expense.getLawyerExp());
		Integer qualifiedExp = Formatter.nullToInt(expense.getQualifiedExp());
		Integer expenseSt = Formatter.nullToInt(expense.getExpenseSt());
		Integer expenseNd = Formatter.nullToInt(expense.getExpenseNd());
		Integer expenseRd = Formatter.nullToInt(expense.getExpenseRd());
		Integer expenseTh = Formatter.nullToInt(expense.getExpenseTh());
		Date payDateSt = expense.getPayDateSt();
		Date payDateNd = expense.getPayDateNd();
		Date payDateRd = expense.getPayDateRd();
		Date payDateTh = expense.getPayDateTh();
		String comment = expense.getComment();
		
		/**
		 * 校验费用
		 *    数字
		 *    非负数
		 */
		if((!Validate.isNum(manageExp + "") || manageExp < 0)
				|| (!Validate.isNum(trainExp + "") || trainExp < 0)
				|| (!Validate.isNum(lawyerExp + "") || lawyerExp < 0)
				|| (!Validate.isNum(qualifiedExp + "") || qualifiedExp < 0)
				|| (!Validate.isNum(expenseSt + "") || expenseSt < 0)
				|| (!Validate.isNum(expenseNd + "") || expenseNd < 0)
				|| (!Validate.isNum(expenseRd + "") || expenseRd < 0)
				|| (!Validate.isNum(expenseTh + "") || expenseTh < 0)) {
			
			info.put("status", Constant.DB_ERR);
			info.put("msg", "费用必须是非负整数");
			return info;
		}
		
		/**
		 * 校验缴费日期
		 *   第二次缴费必须大于第一次缴费
		 */
		/*if(payDateSt != null && payDateSt.getTime() > payDateNd.getTime()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "第二次缴费日期必须大于第一次缴费日期");
			return info;
		}*/
		
		/**
		 * 校验缴费日期
		 *   第三次缴费必须大于第二次缴费
		 */
		/*if(payDateNd != null && payDateNd.getTime() > payDateRd.getTime()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "第三次缴费日期必须大于第二次缴费日期");
			return info;
		}*/
		
		/**
		 * 校验缴费日期
		 *   第四次缴费必须大于第三次缴费
		 */
		/*if(payDateRd != null && payDateRd.getTime() > payDateTh.getTime()) {
			info.put("status", Constant.DB_ERR);
			info.put("msg", "第四次缴费日期必须大于第三次缴费日期");
			return info;
		}*/
		
		info.put("status", Constant.DB_OK);
		
		return info;
	}
	
	/**
	 * 向ModelAndView中填充数据
	 * @param company
	 * @param viewName
	 * @return
	 */
	private ModelAndView modelAddAttribute(Expense expense, String viewName) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("uuid", expense.getUuid());
		model.addObject("stuId", expense.getStuId());
		model.addObject("manageExp", expense.getManageExp());
		model.addObject("trainExp", expense.getTrainExp());
		model.addObject("lawyerExp", expense.getLawyerExp());
		model.addObject("qualifiedExp", expense.getQualifiedExp());
		model.addObject("expenseSt", expense.getExpenseSt());
		model.addObject("expenseNd", expense.getExpenseNd());
		model.addObject("expenseRd", expense.getExpenseRd());
		model.addObject("expenseTh", expense.getExpenseTh());
		model.addObject("debt", expense.getDebt());
		model.addObject("rebate", expense.getRebate());
		model.addObject("payDateSt", Formatter.toDateString(expense.getPayDateSt()));
		model.addObject("payDateNd", Formatter.toDateString(expense.getPayDateNd()));
		model.addObject("payDateRd", Formatter.toDateString(expense.getPayDateRd()));
		model.addObject("payDateTh", Formatter.toDateString(expense.getPayDateTh()));
		model.addObject("comment", expense.getComment());
		
		model.setViewName(viewName);
		
		return model;
	}

	/**
	 * 插入一条费用信息
	 * @return 
	 */
	@Override
	public Map<String, Object> insertExpense(Expense expense) {
		Map<String, Object> info = Message.getInitInfo();
		
		try {
			expenseMapper.insertExpense(expense);
		} catch (Exception e) {
			e.printStackTrace();info.put("status", Constant.DB_ERR);
                	info.put("msg", "费用信息添加失败");
                	MapperException mp = new MapperException("费用信息添加失败", e);
                	mp.setInfo(info);
			throw mp;
		}
		
		info.put("status", Constant.DB_OK);
		info.put("msg", "添加成功");
		
		return info;

	}

}
