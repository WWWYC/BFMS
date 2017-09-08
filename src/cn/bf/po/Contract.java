package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private String entryPact;

	private String proxyPact;

	private String skillPromise;

	private String housePact;

	private String exitPact;

	private String skillPact;

	private String iou;

	private String practicePact;

	private String ensurePact;

	private String agreePact;

	private String declarePact;

	private String lawyerPact;

	private String inquiryPact;

	private String confirmPact;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

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

	public String getEntryPact() {
		return entryPact;
	}

	public void setEntryPact(String entryPact) {
		this.entryPact = entryPact;
	}

	public String getProxyPact() {
		return proxyPact;
	}

	public void setProxyPact(String proxyPact) {
		this.proxyPact = proxyPact;
	}

	public String getSkillPromise() {
		return skillPromise;
	}

	public void setSkillPromise(String skillPromise) {
		this.skillPromise = skillPromise;
	}

	public String getHousePact() {
		return housePact;
	}

	public void setHousePact(String housePact) {
		this.housePact = housePact;
	}

	public String getExitPact() {
		return exitPact;
	}

	public void setExitPact(String exitPact) {
		this.exitPact = exitPact;
	}

	public String getSkillPact() {
		return skillPact;
	}

	public void setSkillPact(String skillPact) {
		this.skillPact = skillPact;
	}

	public String getIou() {
		return iou;
	}

	public void setIou(String iou) {
		this.iou = iou;
	}

	public String getPracticePact() {
		return practicePact;
	}

	public void setPracticePact(String practicePact) {
		this.practicePact = practicePact;
	}

	public String getEnsurePact() {
		return ensurePact;
	}

	public void setEnsurePact(String ensurePact) {
		this.ensurePact = ensurePact;
	}

	public String getAgreePact() {
		return agreePact;
	}

	public void setAgreePact(String agreePact) {
		this.agreePact = agreePact;
	}

	public String getDeclarePact() {
		return declarePact;
	}

	public void setDeclarePact(String declarePact) {
		this.declarePact = declarePact;
	}

	public String getLawyerPact() {
		return lawyerPact;
	}

	public void setLawyerPact(String lawyerPact) {
		this.lawyerPact = lawyerPact;
	}

	public String getInquiryPact() {
		return inquiryPact;
	}

	public void setInquiryPact(String inquiryPact) {
		this.inquiryPact = inquiryPact;
	}

	public String getConfirmPact() {
		return confirmPact;
	}

	public void setConfirmPact(String confirmPact) {
		this.confirmPact = confirmPact;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Contract [uuid=" + uuid + ", stuId=" + stuId
		                + ", entryPact=" + entryPact + ", proxyPact="
		                + proxyPact + ", skillPromise=" + skillPromise
		                + ", housePact=" + housePact + ", exitPact="
		                + exitPact + ", skillPact=" + skillPact
		                + ", iou=" + iou + ", practicePact="
		                + practicePact + ", ensurePact=" + ensurePact
		                + ", agreePact=" + agreePact + ", declarePact="
		                + declarePact + ", lawyerPact=" + lawyerPact
		                + ", inquiryPact=" + inquiryPact
		                + ", confirmPact=" + confirmPact + ", comment="
		                + comment + ", createUser=" + createUser
		                + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}