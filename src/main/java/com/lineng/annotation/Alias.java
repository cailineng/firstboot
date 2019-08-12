package com.lineng.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 常量值别名
 * @author yuanbao_h
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Alias {

	
	String value() default "";
	
}
