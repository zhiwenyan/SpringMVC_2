package com.storm.steven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	/**
	 * afterCompletion������ִ��Handler���֮��ִ�С�Ӧ�ó�����ͳһ�쳣����ͳһ��־����ȡ�
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
	/**
	 * postHandle����������Handler����֮�󣬷���ModelAndView֮ǰִ�С�
	 * ���Կ����÷������и�modelAndView���βΡ�Ӧ�ó�������modelAndView������
	 * �����õ�ģ�����ݣ�����˵�����֮��ģ������ﴫ����ͼ��Ҳ����������ͬһָ����ͼ��
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
	 * preHandle����������Handler����֮ǰִ�С��������������֤�������Ȩ�����������֤û��ͨ����ʾ�û�û�е�½��
	 * ��Ҫ�˷������ز�������ִ�У�return false��������ͷ��У�return true��2
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}
}
