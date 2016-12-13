package com.storm.steven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * �Զ���������
 *
 */
public class FirstInterceptor implements HandlerInterceptor{
	//��Ⱦ��ͼ֮��
	//�ͷ���Դ
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}
	//�ķ�����Ŀ��֮�󷽷����ã�������Ⱦ��ͼ֮ǰ  
	//���Զ������� ���Ի�����ͼ�����޸�
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}
	/**
	 * �ķ�����Ŀ��֮ǰ��������
	 * ������true����������ú����ĵ���������Ŀ�귽��
	 * ������false,�򲻻�������ú����ĵ���������Ŀ�귽��
	 * 
	 * 
	 * ���Կ�����Ȩ�ޡ� ��־ �����
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("preHandle");
		return true;		
	}
}
