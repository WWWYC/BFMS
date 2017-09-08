package cn.bf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bf.util.Log;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	@Autowired
	public Log logger;
	
	// 原FormAuthenticationFilter的认证方法
	@Override
	protected boolean onAccessDenied(ServletRequest request,
	                ServletResponse response) throws Exception {
		// 从session获取正确验证码
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();
		// 取出session的验证码（正确的验证码）
		String verifyCode = (String) session.getAttribute("verifyCode");
		
		
//		System.out.println(request.getParameter("username") + 1);
//		System.out.println(request.getParameter("password") + 2);

		// 取出页面的验证码
		// 输入的验证和session中的验证进行对比
		String login_verifyCode = httpServletRequest.getParameter("verifyCode");
		if (login_verifyCode != null && verifyCode != null && !login_verifyCode.equalsIgnoreCase(verifyCode)) {
			// 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
			httpServletRequest.setAttribute("shiroLoginFailure", "verifyCodeError");
			// 拒绝访问，不再校验账号和密码
			return true;
		}
		boolean result = super.onAccessDenied(request, response);
		return result;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}
	
	
}
