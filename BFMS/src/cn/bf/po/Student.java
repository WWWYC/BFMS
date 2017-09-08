package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stuId;

	private String stuNum;

	private String companyId;
	
	private String companyName;

	private String nameZh;

	private String nameJp;

	private String nameKana;

	private String gender;

	private Integer age = -1;

	private String craft;

	private Integer height = -1;

	private Integer weight = -1;

	private String blood;

	private String stuPhone;

	private String qq;

	private String stuPhoto;

	private Date entryDate;

	private String familyPhone;

	private String addressZh;

	private String addressJp;

	private Integer sameClass = -1;

	// private String stuProxy;

	private Date exitDate;

	private String identityNum;

	private String passportNum;

	private String diplomaNum;

	private String destination;

	private String isRegister;

	private String isTattoo;

	private String isHealth;

	private String isDiploma;

	private String isMarried;

	private Float eyeLeft = -1.0F;

	private Float eyeRight = -1.0F;

	private String nation;

	private Date interviewDate;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public String getNameJp() {
		return nameJp;
	}

	public void setNameJp(String nameJp) {
		this.nameJp = nameJp;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCraft() {
		return craft;
	}

	public void setCraft(String craft) {
		this.craft = craft;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getStuPhoto() {
		return stuPhoto;
	}

	public void setStuPhoto(String stuPhoto) {
		this.stuPhoto = stuPhoto;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getAddressZh() {
		return addressZh;
	}

	public void setAddressZh(String addressZh) {
		this.addressZh = addressZh;
	}

	public String getAddressJp() {
		return addressJp;
	}

	public void setAddressJp(String addressJp) {
		this.addressJp = addressJp;
	}

	public Integer getSameClass() {
		return sameClass;
	}

	public void setSameClass(Integer sameClass) {
		this.sameClass = sameClass;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	public String getDiplomaNum() {
		return diplomaNum;
	}

	public void setDiplomaNum(String diplomaNum) {
		this.diplomaNum = diplomaNum;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getIsTattoo() {
		return isTattoo;
	}

	public void setIsTattoo(String isTattoo) {
		this.isTattoo = isTattoo;
	}

	public String getIsHealth() {
		return isHealth;
	}

	public void setIsHealth(String isHealth) {
		this.isHealth = isHealth;
	}

	public String getIsDiploma() {
		return isDiploma;
	}

	public void setIsDiploma(String isDiploma) {
		this.isDiploma = isDiploma;
	}

	public String getIsMarried() {
		return isMarried;
	}

	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}

	public Float getEyeLeft() {
		return eyeLeft;
	}

	public void setEyeLeft(Float eyeLeft) {
		this.eyeLeft = eyeLeft;
	}

	public Float getEyeRight() {
		return eyeRight;
	}

	public void setEyeRight(Float eyeRight) {
		this.eyeRight = eyeRight;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
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
	        return "Student [stuId=" + stuId + ", stuNum=" + stuNum
	                        + ", companyId=" + companyId + ", companyName="
	                        + companyName + ", nameZh=" + nameZh
	                        + ", nameJp=" + nameJp + ", nameKana="
	                        + nameKana + ", gender=" + gender + ", age="
	                        + age + ", craft=" + craft + ", height="
	                        + height + ", weight=" + weight + ", blood="
	                        + blood + ", stuPhone=" + stuPhone + ", qq="
	                        + qq + ", stuPhoto=" + stuPhoto
	                        + ", entryDate=" + entryDate + ", familyPhone="
	                        + familyPhone + ", addressZh=" + addressZh
	                        + ", addressJp=" + addressJp + ", sameClass="
	                        + sameClass + ", exitDate=" + exitDate
	                        + ", identityNum=" + identityNum
	                        + ", passportNum=" + passportNum
	                        + ", diplomaNum=" + diplomaNum
	                        + ", destination=" + destination
	                        + ", isRegister=" + isRegister + ", isTattoo="
	                        + isTattoo + ", isHealth=" + isHealth
	                        + ", isDiploma=" + isDiploma + ", isMarried="
	                        + isMarried + ", eyeLeft=" + eyeLeft
	                        + ", eyeRight=" + eyeRight + ", nation="
	                        + nation + ", interviewDate=" + interviewDate
	                        + ", comment=" + comment + ", createUser="
	                        + createUser + ", createDate=" + createDate
	                        + ", updateUser=" + updateUser
	                        + ", updateDate=" + updateDate + "]";
        }

}