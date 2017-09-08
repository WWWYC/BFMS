package cn.bf.util;

import org.apache.log4j.Logger;

public class Log {

	private Logger logger;
	
	
	public void debug(Class<?> t, String log) {
		Logger.getLogger(t).debug(log);
	}

	public void info(Class<?> t, String log) {
		Logger.getLogger(t).info(log);
	}
	
	public void warn(Class<?> t, String log) {
		Logger.getLogger(t).warn(log);
	}
	
	public void error(Class<?> t, String log) {
		Logger.getLogger(t).error(log);
	}
	
	public void fatal(Class<?> t, String log) {
		Logger.getLogger(t).fatal(log);
	}
	
	
	public void debug(Class<?> t, String log, Exception e) {
		Logger.getLogger(t).debug(log + e.getMessage());
	}

	public void info(Class<?> t, String log, Exception e) {
		Logger.getLogger(t).info(log + e.getMessage());
	}
	
	public void warn(Class<?> t, String log, Exception e) {
		Logger.getLogger(t).warn(log + e.getMessage());
	}
	
	public void error(Class<?> t, String log, Exception e) {
		Logger.getLogger(t).error(log + e.getMessage());
	}
	
	public void fatal(Class<?> t, String log, Exception e) {
		Logger.getLogger(t).fatal(log + e.getMessage());
	}

	public Logger getLogger(Class<?> t) {
		if (logger == null) {
			logger = Logger.getLogger(t);
		}
		return logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
