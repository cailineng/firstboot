package com.lineng.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD})
@Retention(RUNTIME)
public @interface Pagination {

	/**
	 * true : 需要分页处理, false : 不需要分页处理
	 * @return
	 */
	boolean value() default true;
	
	/**
	 * 排序字段
	 */
	String orderBy() default "";
	
	/**
	 * 使用默认分页
	 * @return
	 */
	boolean defaultCount() default true;
}
