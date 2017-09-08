package cn.bf.po;

import java.io.Serializable;

public class SysRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String sysPermissionId;

	private String sysRoleId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSysPermissionId() {
		return sysPermissionId;
	}

	@Override
        public String toString() {
	        return "SysRolePermission [uuid=" + uuid + ", sysPermissionId="
	                        + sysPermissionId + ", sysRoleId=" + sysRoleId
	                        + "]";
        }

	public void setSysPermissionId(String sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
}