package cn.bf.po;

import java.util.List;


/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * 
 * @author Thinkpad
 * 
 */
public class ActiveUser extends SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<SysPermission> menus;// 菜单
	private List<SysPermission> permissions;// 权限
	public List<SysPermission> getMenus() {
		return menus;
	}
	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}
	public List<SysPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

}
