package cn.bf.po;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;

	private String sysuserId;

	private Date datetime;

	private String type;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSysuserId() {
		return sysuserId;
	}

	public void setSysuserId(String sysuserId) {
		this.sysuserId = sysuserId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Log [uuid=" + uuid + ", sysuserId=" + sysuserId
		                + ", datetime=" + datetime + ", type=" + type
		                + "]";
	}
}