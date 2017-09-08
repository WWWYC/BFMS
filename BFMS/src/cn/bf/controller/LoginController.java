package cn.bf.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bf.po.ActiveUser;
import cn.bf.util.SuperClass;
import cn.bf.util.VerifyCode;

@Controller
public class LoginController extends SuperClass {
        private static final long serialVersionUID = 1L;

		//系统首页
		@RequestMapping("/welcome")
		public String first(HttpSession session)throws Exception{
			//从shiro的session中取activeUser
			//Subject subject = SecurityUtils.getSubject();
			//取身份信息
			//ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
			//通过model传到页面
			//model.addAttribute("activeUser", activeUser);
			
			// 如果用户已登录，则不再显示登录页面
			ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
			if(activeUser != null) {
				return "redirect:/info";
			}
			
			return "/login/login";
		}
	
	//登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpSession session, String username, String password)throws Exception{
		Subject subject = SecurityUtils.getSubject();
		
		if(subject.isAuthenticated()){ //已经登录，重新登录
			//SecurityUtils.getSecurityManager().logout(subject);
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try{
				subject.login(token);
			} catch(UnknownAccountException e){
				e.printStackTrace();
				return "{'msg' : '账号不存在'}";
			} catch(IncorrectCredentialsException e){
				e.printStackTrace();
				return "{'msg' : '用户名/密码错误'}";
			}  catch(Exception e){
				e.printStackTrace();
				return "{'msg' : '未知异常'}";
			}
		}
		subject.isPermitted("user:create:1");
		/*
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				// throw new CustomException("账号不存在");
				return "{'msg' : '账号不存在'}";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				// throw new CustomException("用户名/密码错误");
				return "{'msg' : '用户名/密码错误'}";
			} else if("verifyCodeError".equals(exceptionClassName)){
				// throw new CustomException("验证码错误");
				return "{'msg' : '验证码错误'}";
			}else {
				// throw new Exception();//最终在异常处理器生成未知错误
				return "{'msg' : '未知异常'}";
			}
		}
		*/
		
		// 将用户设置到Session中
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		session.setAttribute("activeUser", activeUser);
		
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败后跳转login页面
		return "{'msg' : 'ok'}";
	}
	
	/**
	 * 获取验证码
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("verifyCode")
	public void verifyCode(HttpServletResponse response, HttpServletRequest request) throws IOException{
		VerifyCode code = new VerifyCode();
		VerifyCode.output(code.getImage(), response.getOutputStream());
		
		HttpSession session = request.getSession();
		SecurityUtils.getSubject().getSession().setAttribute("verifyCode", code.getText());
		System.out.println(session.getAttribute("verifyCode"));
		System.out.println(session.getId());
	}
	
	/**
	 * 登录成功访问success方法
	 */
	@RequestMapping("success")
	public void success(){
		System.out.println("登录成功");
	}
}
