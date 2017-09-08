package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sysUserId;

	private String account;

	private String username;

	private String password;

	private String salt;

	private String tel;

	private String email;

	private Date regTime;

	private String regIp;

	private Date lastLoginTime;

	private Date lastLoginErrTime;

	private String lastLoginIp;

	private String userType;

	private String status;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginErrTime() {
		return lastLoginErrTime;
	}

	public void setLastLoginErrTime(Date lastLoginErrTime) {
		this.lastLoginErrTime = lastLoginErrTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "SysUser [sysUserId=" + sysUserId + ", account="
		                + account + ", username=" + username
		                + ", password=" + password + ", salt=" + salt
		                + ", tel=" + tel + ", email=" + email
		                + ", regTime=" + regTime + ", regIp=" + regIp
		                + ", lastLoginTime=" + lastLoginTime
		                + ", lastLoginErrTime=" + lastLoginErrTime
		                + ", lastLoginIp=" + lastLoginIp
		                + ", userType=" + userType + ", status="
		                + status + ", comment=" + comment
		                + ", createUser=" + createUser
		                + ", createDate=" + createDate
		                + ", updateUser=" + updateUser
		                + ", updateDate=" + updateDate + "]";
	}

}