package cn.bf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.bf.util.Log;
import cn.bf.util.SuperClass;

public class Initialization extends SuperClass implements  ServletContextListener{
	
        private static final long serialVersionUID = 1L;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("**************************************************************************************************************************************");
		System.out.println("*************************************************  application StudentMS stop  !! **********************************************************");
		System.out.println("**************************************************************************************************************************************");
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("**************************************************************************************************************************************");
		System.out.println("*************************************************  application StudentMS start  !! **********************************************************");
		System.out.println("**************************************************************************************************************************************");
		
		System.getProperties().put("file.encoding", "UTF-8");
		new Log().info(this.getClass(), "System properties set file.encoding UTF-8");
		
		// 设置初始属性
		setProperties(event);
	}
	
	private void setProperties(ServletContextEvent event){
		// 保存真实路径
		event.getServletContext().setAttribute("realPath", event.getServletContext().getRealPath(""));
		System.setProperty("realPath", event.getServletContext().getRealPath(""));
		
	}

}
