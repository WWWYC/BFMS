package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Family implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private String familyName;

	private String familyRelation;

	private String familyNum;

	private String familyAddress;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;
	
	

	public Family() {
	        super();
        }

	public Family(String uuid, String stuId, String familyName,
                        String familyRelation, String familyNum,
                        String familyAddress, String comment,
                        String createUser, Date createDate, String updateUser,
                        Date updateDate) {
	        super();
	        this.uuid = uuid;
	        this.stuId = stuId;
	        this.familyName = familyName;
	        this.familyRelation = familyRelation;
	        this.familyNum = familyNum;
	        this.familyAddress = familyAddress;
	        this.comment = comment;
	        this.createUser = createUser;
	        this.createDate = createDate;
	        this.updateUser = updateUser;
	        this.updateDate = updateDate;
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

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyRelation() {
		return familyRelation;
	}

	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}

	public String getFamilyNum() {
		return familyNum;
	}

	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
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
		return "Family [uuid=" + uuid + ", stuId=" + stuId
		                + ", familyName=" + familyName
		                + ", familyRelation=" + familyRelation
		                + ", familyNum=" + familyNum
		                + ", familyAddress=" + familyAddress
		                + ", comment=" + comment + ", createUser="
		                + createUser + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}