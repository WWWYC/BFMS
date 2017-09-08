package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Assurance implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private String assureName;

	private String assureRelation;

	private String isIdentity;

	private String isRegister;

	private String isJob;

	private String isPhoto;

	private String isHouse;

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

	public String getAssureName() {
		return assureName;
	}

	public void setAssureName(String assureName) {
		this.assureName = assureName;
	}

	public String getAssureRelation() {
		return assureRelation;
	}

	public void setAssureRelation(String assureRelation) {
		this.assureRelation = assureRelation;
	}

	public String getIsIdentity() {
		return isIdentity;
	}

	public void setIsIdentity(String isIdentity) {
		this.isIdentity = isIdentity;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getIsJob() {
		return isJob;
	}

	public void setIsJob(String isJob) {
		this.isJob = isJob;
	}

	public String getIsPhoto() {
		return isPhoto;
	}

	public void setIsPhoto(String isPhoto) {
		this.isPhoto = isPhoto;
	}

	public String getIsHouse() {
		return isHouse;
	}

	public void setIsHouse(String isHouse) {
		this.isHouse = isHouse;
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

	@Override
	public String toString() {
		return "Assurance [uuid=" + uuid + ", stuId=" + stuId
		                + ", assureName=" + assureName
		                + ", assureRelation=" + assureRelation
		                + ", isIdentity=" + isIdentity
		                + ", isRegister=" + isRegister + ", isJob="
		                + isJob + ", isPhoto=" + isPhoto + ", isHouse="
		                + isHouse + ", comment=" + comment
		                + ", createUser=" + createUser
		                + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}