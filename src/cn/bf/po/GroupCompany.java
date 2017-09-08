package cn.bf.po;

import java.io.Serializable;

public class GroupCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String groupId;

	private String companyId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
        public String toString() {
	        return "GroupCompany [uuid=" + uuid + ", groupId=" + groupId
	                        + ", companyId=" + companyId + "]";
        }
}