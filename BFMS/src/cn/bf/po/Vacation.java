package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Vacation implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private String nameZh;

	private String stuNum;

	private Date startDate;

	private Date endDate;

	private String reason;

	private Double duration;

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

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
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
	        return "Vacation [uuid=" + uuid + ", stuId=" + stuId
	                        + ", nameZh=" + nameZh + ", stuNum=" + stuNum
	                        + ", startDate=" + startDate + ", endDate="
	                        + endDate + ", reason=" + reason
	                        + ", duration=" + duration + ", comment="
	                        + comment + ", createUser=" + createUser
	                        + ", createDate=" + createDate
	                        + ", updateUser=" + updateUser
	                        + ", updateDate=" + updateDate + "]";
        }



}