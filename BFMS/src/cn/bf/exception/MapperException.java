package cn.bf.exception;

import java.util.Map;

public class MapperException extends Error {
	private static final long serialVersionUID = 1L;
	private String msg;
	private Exception e;
	private Map<String, Object> info;

	public MapperException(String msg, Exception e) {
		super();
		this.msg = msg;
		this.e = e;
	}
	
	

	public MapperException(String msg, Map<String, Object> info) {
	        super();
	        this.msg = msg;
	        this.info = info;
        }



	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
	
	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

	@Override
        public String toString() {
	        return "MapperException [msg=" + msg + ", e=" + e + ", info="
	                        + info + "]";
        }

}
