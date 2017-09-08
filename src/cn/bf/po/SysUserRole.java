package cn.bf.po;

import java.io.Serializable;

public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String sysUserId;

	private String sysRoleId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
        public String toString() {
	        return "SysUserRole [uuid=" + uuid + ", sysUserId=" + sysUserId
	                        + ", sysRoleId=" + sysRoleId + "]";
        }

}