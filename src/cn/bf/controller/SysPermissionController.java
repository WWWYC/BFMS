package cn.bf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bf.po.ActiveUser;
import cn.bf.po.SysPermission;
import cn.bf.service.SysPermissionService;
import cn.bf.util.SuperClass;

@Controller
public class SysPermissionController extends SuperClass {
	
        private static final long serialVersionUID = 1L;
        
	@Autowired
	private SysPermissionService sysPermissionService;

	@RequestMapping("/getMenuList")
	@ResponseBody
	public List<SysPermission> getMenuList(HttpServletRequest request, HttpSession session){
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		// 如果是第一次打开页面，则直接从当前用户中获取根菜单
		String id = request.getParameter("id");
		if(id == null){
			System.out.println(activeUser.getMenus());
			return activeUser.getMenus();
		} else {
			// 否则递归查询数据库返回菜单
			
			// 组装参数
			Map<String, String> param = new HashMap<String, String>();
			param.put("id", id);
			param.put("sysUserId", activeUser.getSysUserId());
			List<SysPermission> menus = sysPermissionService.findMenuListBySysMenuId(param);
			System.out.println(menus);
			// 返回菜单
			return menus;
		}
		
	}
}
