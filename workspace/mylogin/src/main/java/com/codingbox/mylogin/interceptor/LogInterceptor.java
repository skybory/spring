package com.codingbox.mylogin.interceptor;

import java.util.UUID;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor{
	
	public static final String LOG_ID = "logId";
	
	// 로그를 남기는 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String uuid = UUID.randomUUID().toString();
//		request.setAttribute(uuid, response);
		request.setAttribute(LOG_ID, uuid);
		
		System.out.println("[interceptor] uuid : " + uuid);
		System.out.println("[interceptor] requestURI : " + requestURI);
		System.out.println("[interceptor] handler : " + handler);
		
		return true;		// false면 진행X
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[interceptor] postHandle : " + modelAndView);
	
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String) request.getAttribute(LOG_ID);
		System.out.println("[interceptor] logid : " + logId);
		System.out.println("[interceptor] requestURI : " + requestURI);
	}
	
}
