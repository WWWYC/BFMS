package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	private String groupId;

	private String groupNum;

	private String groupName;

	private String groupContacts;

	private String groupAddress;

	private String groupPhone;

	private String groupEmail;

	private String groupFax;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupContacts() {
		return groupContacts;
	}

	public void setGroupContacts(String groupContacts) {
		this.groupContacts = groupContacts;
	}

	public String getGroupAddress() {
		return groupAddress;
	}

	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}

	public String getGroupPhone() {
		return groupPhone;
	}

	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}

	public String getGroupEmail() {
		return groupEmail;
	}

	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}

	public String getGroupFax() {
		return groupFax;
	}

	public void setGroupFax(String groupFax) {
		this.groupFax = groupFax;
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
		return "Group [groupId=" + groupId + ", groupNum=" + groupNum
		                + ", groupName=" + groupName
		                + ", groupContacts=" + groupContacts
		                + ", groupAddress=" + groupAddress
		                + ", groupPhone=" + groupPhone
		                + ", groupEmail=" + groupEmail + ", groupFax="
		                + groupFax + ", comment=" + comment
		                + ", createUser=" + createUser
		                + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}