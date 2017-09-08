package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	// 学生ID
	private String stuId;

	// 学号
	private String stuNum;

	// 学生姓名
	private String nameZh;

	// 培训管理费
	private Integer manageExp;

	// 培训费
	private Integer trainExp;

	// 律师见证费
	private Integer lawyerExp;

	// 培训合格证
	private Integer qualifiedExp;

	// 第一次缴费
	private Integer expenseSt;

	// 第二次缴费
	private Integer expenseNd;

	// 第三次缴费
	private Integer expenseRd;

	// 第四次缴费
	private Integer expenseTh;

	// 欠费
	private Integer debt;

	// 返利费
	private Integer rebate;

	// 第一缴费日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDateSt;

	// 第二次缴费日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDateNd;

	// 第三次缴费日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDateRd;

	// 第四次缴费日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDateTh;

	// 总金额
	private Integer totalExp;

	// 已交费用（合计）
	private Integer sumExp;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public Integer getSumExp() {
		return sumExp;
	}

	public void setSumExp(Integer sumExp) {
		this.sumExp = sumExp;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public Integer getManageExp() {
		return manageExp;
	}

	public void setManageExp(Integer manageExp) {
		this.manageExp = manageExp;
	}

	public Integer getTrainExp() {
		return trainExp;
	}

	public void setTrainExp(Integer trainExp) {
		this.trainExp = trainExp;
	}

	public Integer getLawyerExp() {
		return lawyerExp;
	}

	public void setLawyerExp(Integer lawyerExp) {
		this.lawyerExp = lawyerExp;
	}

	public Integer getQualifiedExp() {
		return qualifiedExp;
	}

	public void setQualifiedExp(Integer qualifiedExp) {
		this.qualifiedExp = qualifiedExp;
	}

	public Integer getExpenseSt() {
		return expenseSt;
	}

	public void setExpenseSt(Integer expenseSt) {
		this.expenseSt = expenseSt;
	}

	public Integer getExpenseNd() {
		return expenseNd;
	}

	public void setExpenseNd(Integer expenseNd) {
		this.expenseNd = expenseNd;
	}

	public Integer getExpenseRd() {
		return expenseRd;
	}

	public void setExpenseRd(Integer expenseRd) {
		this.expenseRd = expenseRd;
	}

	public Integer getExpenseTh() {
		return expenseTh;
	}

	public void setExpenseTh(Integer expenseTh) {
		this.expenseTh = expenseTh;
	}

	public Integer getDebt() {
		return debt;
	}

	public void setDebt(Integer debt) {
		this.debt = debt;
	}

	public Integer getRebate() {
		return rebate;
	}

	public void setRebate(Integer rebate) {
		this.rebate = rebate;
	}

	public Date getPayDateSt() {
		return payDateSt;
	}

	public void setPayDateSt(Date payDateSt) {
		this.payDateSt = payDateSt;
	}

	public Date getPayDateNd() {
		return payDateNd;
	}

	public void setPayDateNd(Date payDateNd) {
		this.payDateNd = payDateNd;
	}

	public Date getPayDateRd() {
		return payDateRd;
	}

	public void setPayDateRd(Date payDateRd) {
		this.payDateRd = payDateRd;
	}

	public Date getPayDateTh() {
		return payDateTh;
	}

	public void setPayDateTh(Date payDateTh) {
		this.payDateTh = payDateTh;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(Integer totalExp) {
		this.totalExp = totalExp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Expense [uuid=" + uuid + ", stuId=" + stuId
		                + ", nameZh=" + nameZh + ", manageExp="
		                + manageExp + ", trainExp=" + trainExp
		                + ", lawyerExp=" + lawyerExp
		                + ", qualifiedExp=" + qualifiedExp
		                + ", expenseSt=" + expenseSt + ", expenseNd="
		                + expenseNd + ", expenseRd=" + expenseRd
		                + ", expenseTh=" + expenseTh + ", debt=" + debt
		                + ", rebate=" + rebate + ", payDateSt="
		                + payDateSt + ", payDateNd=" + payDateNd
		                + ", payDateRd=" + payDateRd + ", payDateTh="
		                + payDateTh + ", comment=" + comment
		                + ", createUser=" + createUser
		                + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}