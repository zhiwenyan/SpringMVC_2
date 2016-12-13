package com.storm.steven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 自定义拦截器
 *
 */
public class FirstInterceptor implements HandlerInterceptor{
	//渲染视图之后
	//释放资源
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}
	//改方法在目标之后方法调用，但在渲染视图之前  
	//可以对请求域 属性或者视图做出修改
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}
	/**
	 * 改方法在目标之前方法调用
	 * 若返回true：则继续调用后续的的拦截器和目标方法
	 * 若返回false,则不会继续调用后续的的拦截器和目标方法
	 * 
	 * 
	 * 可以考虑做权限。 日志 事务等
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle");
		return true;		
	}
}
