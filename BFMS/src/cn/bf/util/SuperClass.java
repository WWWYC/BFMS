package cn.bf.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 所有的类都要继承该类
 * @author WYC
 *
 */
public class SuperClass implements Serializable {
        private static final long serialVersionUID = 1L;
        
	@Autowired
	public Log logger;

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}
	
}
