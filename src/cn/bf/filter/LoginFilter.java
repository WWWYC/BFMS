package cn.bf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bf.po.ActiveUser;
import cn.bf.util.SuperClass;

public class LoginFilter extends SuperClass implements Filter {

        private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	                FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
		
		
		
		/**
		 * 如果用户未登录，则跳转到首页
		 * 如果是请求静态资源，则放行
		 */
		if(activeUser == null){
			httpResponse.sendRedirect((String)httpRequest.getServletContext().getAttribute("basePath"));
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
