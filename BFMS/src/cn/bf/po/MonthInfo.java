package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class MonthInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private Date date;

	private String monthInfo;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMonthInfo() {
		return monthInfo;
	}

	public void setMonthInfo(String monthInfo) {
		this.monthInfo = monthInfo;
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
		return "MonthInfo [uuid=" + uuid + ", stuId=" + stuId
		                + ", date=" + date + ", monthInfo=" + monthInfo
		                + ", comment=" + comment + ", createUser="
		                + createUser + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}
}