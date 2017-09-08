package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	private String companyId;

	private String companyNum;

	private String groupId;
	
	private String groupName;

	private String companyName;

	private String companyContacts;

	private String companyAddress;

	private String companyPhone;

	private String companyEmail;

	private String companyFax;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(String companyNum) {
		this.companyNum = companyNum;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyContacts() {
		return companyContacts;
	}

	public void setCompanyContacts(String companyContacts) {
		this.companyContacts = companyContacts;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
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
	        return "Company [companyId=" + companyId + ", companyNum="
	                        + companyNum + ", groupId=" + groupId
	                        + ", groupName=" + groupName + ", companyName="
	                        + companyName + ", companyContacts="
	                        + companyContacts + ", companyAddress="
	                        + companyAddress + ", companyPhone="
	                        + companyPhone + ", companyEmail="
	                        + companyEmail + ", companyFax=" + companyFax
	                        + ", comment=" + comment + ", createUser="
	                        + createUser + ", createDate=" + createDate
	                        + ", updateUser=" + updateUser
	                        + ", updateDate=" + updateDate + "]";
        }


}