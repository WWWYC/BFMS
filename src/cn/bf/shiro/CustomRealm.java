package cn.bf.shiro;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bf.po.ActiveUser;
import cn.bf.po.SysPermission;
import cn.bf.po.SysUser;
import cn.bf.service.SysPermissionService;
import cn.bf.service.SysUserService;
import cn.bf.util.Log;

public class CustomRealm extends AuthorizingRealm {
	@Autowired
	public Log logger;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysPermissionService sysPermissionService;

	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	// Realm的认证方法，从数据库查询用户信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token中包含用户输入的用户名和密码
		// 第一步从token中取出用户名
		String account = (String) token.getPrincipal();
		// 第二步：根据用户输入的userCode从数据库查询用户
		SysUser sysUser = null;
		sysUser = sysUserService.selectSysUserByAccount(account);

		// 如果查询不到返回null，认证失败
		if(sysUser==null){
			return null;
		}

		// 如果查询到返回认证信息AuthenticationInfo
		
		// activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();
		
		try {
	                BeanUtils.copyProperties(activeUser, sysUser);
                } catch (IllegalAccessException e) {
	                e.printStackTrace();
                } catch (InvocationTargetException e) {
	                e.printStackTrace();
                }
		
		// 根据用户id取出菜单
		List<SysPermission> menus  = null;
		try {
			// 取出用户身份菜单
			menus = sysPermissionService.findMenuListBySysUserId(sysUser.getSysUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将用户菜单设置到activeUser中
		activeUser.setMenus(menus);
		
		// 将activeUser设置到simpleAuthenticationInfo中
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, activeUser.getPassword(),ByteSource.Util.bytes(activeUser.getSalt()), this.getName());

		return simpleAuthenticationInfo;
		
	}
	
	

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("授权操作");
		
		// 从 principals获取主身份信息
		// 将getPrimaryPrincipal方法返回值转为真实身份类型
		// （在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
		ActiveUser activeUser =  (ActiveUser) principals.getPrimaryPrincipal();
		
		// 根据身份信息从数据库获取权限数据
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysPermissionService.findPermissionListBySysUserId(activeUser.getSysUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 单独定一个集合对象 
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(SysPermission sysPermission:permissionList){
				// 将数据库中的权限标签符（字符串）放入集合
				permissions.add(sysPermission.getPermission());
				System.out.println(sysPermission.getPermission());
			}
		}
		
		// 查到权限数据，返回授权信息，完成授权操作(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
	
		return simpleAuthorizationInfo;
		
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}
	
}
