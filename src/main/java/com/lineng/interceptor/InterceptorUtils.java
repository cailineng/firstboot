package com.lineng.interceptor;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

/**
 * 提供spirng 拦截器的工具类
 * @author yuanbao
 *
 */
public class InterceptorUtils {
	
	private InterceptorUtils() {}

	/**
	 * 从拦截器中 handler 对象 获取 对应的注解对象
	 * @param handler
	 * @param cls
	 * @return
	 */
	public static <T extends Annotation> T getAnnotation(Object handler, Class<T> cls) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			return handlerMethod.getMethodAnnotation(cls);
		}
		return null;
	}
}
