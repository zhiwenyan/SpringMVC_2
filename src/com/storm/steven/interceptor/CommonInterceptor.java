package com.storm.steven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	/**
	 * afterCompletion方法：执行Handler完成之后执行。应用场景：统一异常处理，统一日志处理等。
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	/**
	 * postHandle方法：进入Handler方法之后，返回ModelAndView之前执行。
	 * 可以看到该方法中有个modelAndView的形参。应用场景：从modelAndView出发：
	 * 将公用的模型数据（比如菜单导航之类的）在这里传到视图，也可以在这里同一指定视图。
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		HttpSession session=arg0.getSession();
		String username=(String)session.getAttribute("username");
		if(username!=null&&username.equals("steven")){
			System.out.println("-----"+username);
		}
	}
	/**
	 * preHandle方法：进入Handler方法之前执行。可以用于身份认证、身份授权。比如如果认证没有通过表示用户没有登陆，
	 * 需要此方法拦截不再往下执行（return false），否则就放行（return true）2
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}
}
