package com.lineng.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;

public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
	

	/**
	 * 日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 获取注解
	 * @param handler
	 * @param cls
	 * @return
	 */
	protected <T extends Annotation> T getAnnotation(Object handler, Class<T> cls) {
		return InterceptorUtils.getAnnotation(handler, cls);
	}
	


	


}