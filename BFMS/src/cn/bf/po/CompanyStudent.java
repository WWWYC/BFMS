package cn.bf.po;

import java.io.Serializable;

public class CompanyStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String stuId;

	private String companyId;

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
		return "CompanyStudent [uuid=" + uuid + ", stuId=" + stuId
		                + ", companyId=" + companyId + "]";
	}

}