package cn.bf.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Family;
import cn.bf.service.FamilyService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
@RequestMapping(value = "/family")
public class FamilyController extends SuperClass {

        private static final long serialVersionUID = 1L;
        
        @Autowired
        private FamilyService familyService;
        
        @RequestMapping(value = "/findFamily")
        public String findFamily() {
        	return "family/findFamily";
        }
        
	@RequestMapping(value = "/showFamily", method = RequestMethod.GET)
	public ModelAndView showFamily(String stuId) {
		ModelAndView model = new ModelAndView();
		model.addObject("stuId", stuId);
		model.setViewName("family/showFamily");
		return model;
	}
        
        
        /**
         * 跳转到编辑家庭成员页面
         * @return
         */
        @RequestMapping(value = "/editFamily")
        public ModelAndView editFamily(String stuId) {
        	ModelAndView model = new ModelAndView();
        	model.addObject("stuId", stuId);
        	model.setViewName("family/editFamily");
        	return model;
//        	return "family/editFamily";
        }
        
        /**
         * 获取当前学生的家庭成员信息
         * @param family
         * @return
         */
        @RequestMapping(value = "/getFamilyList")
        @ResponseBody
        public Map<String, Object> getFamilyList(Family family) {
        	Map<String, Object> info = Message.getInitInfo();
		try {
			info = familyService.getFamilyList(family);
		} catch (MapperException e) {
			info = e.getInfo();
		}
        	return info;
        }
        
        /**
         * 批量更新家庭成员信息
         * @param familyList
         * @param session
         * @return
         */
	@RequestMapping(value = "/updateFamily", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateFamily(@RequestBody Family[] familyList, HttpSession session) {
		Map<String, Object> info = Message.getInitInfo();
		
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		try {
			info = familyService.updateFamily(familyList, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}
	
	@RequestMapping(value = "/rmFamily", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> rmFamily(Family family) {
		Map<String, Object> info = Message.getInitInfo();
		
		
		try {
			info = familyService.deleteFamilyByUuid(family);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		
		return info;
	}

}
