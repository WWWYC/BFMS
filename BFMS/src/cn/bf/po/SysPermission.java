package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class SysPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String sysPermissionId;

	private String parentId;

	private Integer perLevel;

	private String url;

	private String permission;

	private String text;

	private String state;

	private String iconCls;

	private String status;

	private String comment;

	private String createUser;

	private Date createDate;

	private String updateUser;

	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysPermissionId() {
		return sysPermissionId;
	}

	public void setSysPermissionId(String sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getPerLevel() {
		return perLevel;
	}

	public void setPerLevel(Integer perLevel) {
		this.perLevel = perLevel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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
	        return "SysPermission [id=" + id + ", sysPermissionId="
	                        + sysPermissionId + ", parentId=" + parentId
	                        + ", perLevel=" + perLevel + ", url=" + url
	                        + ", permission=" + permission + ", text="
	                        + text + ", state=" + state + ", iconCls="
	                        + iconCls + ", status=" + status + ", comment="
	                        + comment + ", createUser=" + createUser
	                        + ", createDate=" + createDate
	                        + ", updateUser=" + updateUser
	                        + ", updateDate=" + updateDate + "]";
        }


}