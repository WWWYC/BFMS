package cn.bf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import cn.bf.po.Group;
import cn.bf.service.GroupService;
import cn.bf.util.Message;
import cn.bf.util.SuperClass;

@Controller
public class GroupController extends SuperClass {

        private static final long serialVersionUID = 1L;
        
        @Autowired
        private GroupService groupService;
	
        @RequestMapping("/addGroup")
        public String addGroup() {
        	return "group/addGroup";
        }
        
        /**
         * 获取组合编号
         * @return
         */
        @RequestMapping("/getGroupNum")
	@ResponseBody
	public Map<String, Object> getGroupNum() {
        	Map<String, Object> info = Message.getInitInfo();
		info = groupService.getGroupNum();
		return info;
	}
        
        /**
         * 添加组合信息
         * @param group
         * @return
         */
        @RequestMapping(value = "/insertGroup", method=RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> insertGroup(Group group, HttpSession session){
        	
        	// 取出当前用户
        	ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        	
		Map<String, Object> info = Message.getInitInfo();
		try {
			info = groupService.insertGroup(group, activeUser);
		} catch (MapperException e) {
			info = e.getInfo();
		}
		return info;
        }
        
        /**
         * 获取组合列表
         * @return
         */
        @RequestMapping("/getGroupList")
        @ResponseBody
        public List<Group> getGroupList() {
        	List<Group> groupList = groupService.selectGroupList();
        	return groupList;
        }
        
	/**
	 * 跳转到查询组合页面
	 * 
	 * @return
	 */
	@RequestMapping("/findGroup")
	public String findGroup() {
		return "group/findGroup";
	}
        
        /**
         * 查询组合分页信息
         */
        @RequestMapping("/findGroupPageList")
        @ResponseBody
        public Map<String, Object> findGroupPageList(HttpServletRequest request, Integer page, Integer rows) {
        	
        	Map<String, Object> groupList = groupService.findGroupPageList(page, rows);
        	
        	return groupList;
        }
        
	/**
	 * 查看组合信息
	 * 
	 * @param request
	 * @param groupId
	 * @return
	 */
	@RequestMapping("/showGroup")
	public ModelAndView showGroup(HttpServletRequest request, String groupId) {
		ModelAndView model = groupService.showGroup(groupId, "group/showGroup");
		return model;
	}
        
        /**
         * 编辑组合
         * @param request
         * @param groupId
         * @return
         */
        @RequestMapping("/editGroup")
        public ModelAndView editGroup(HttpServletRequest request, String groupId) {
        	ModelAndView model = groupService.showGroup(groupId, "group/editGroup");
        	return model;
        }
        
        /**
         * 更新组合信息
         * @param group
         * @param session
         * @return
         */
        @RequestMapping(value="/updateGroup", method=RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> updateGroup(Group group, HttpSession session) {
        	
        	ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        	
        	Map<String, Object> info = Message.getInitInfo();
        	
        	try {
        		info = groupService.updateGroupByGroupId(group, activeUser);
                } catch (MapperException e) {
                	info = e.getInfo();
                }
        	
        	return info;
        }
        
        /**
         * 删除组合
         * @param group
         * @return
         */
        @RequestMapping(value="/rmGroup", method = RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> rmGroup(@RequestBody Group group) {
        	Map<String, Object> info = Message.getInitInfo();
        	
		try {
			info = groupService.deleteGroupByGroupId(group);
		} catch (MapperException e) {
			info = e.getInfo();
		}
        	
        	return info;
        }
}
