package cn.bf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bf.util.SuperClass;

@Controller
public class InfoController extends SuperClass {
	
	/**
	 * 
	 */
        private static final long serialVersionUID = 1L;

	@RequestMapping("/info")
	public String info(){
		return "info/info";
	}
}
