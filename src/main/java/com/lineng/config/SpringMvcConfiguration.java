package com.lineng.config;


import com.lineng.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {


    /**
     * 方法级别的验证
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
    
    @Bean
    public List<MediaType> supportedMediaTypes() {
    	List<MediaType> supportedMediaTypeList = new ArrayList<>();
    	supportedMediaTypeList.add(MediaType.valueOf("text/json;charset=UTF-8"));
        supportedMediaTypeList.add(MediaType.valueOf("text/plain;charset=UTF-8"));
    	supportedMediaTypeList.add(MediaType.valueOf("application/json; charset=UTF-8"));
    	supportedMediaTypeList.add(MediaType.valueOf("application/x-www-form-urlencoded; charset=UTF-8"));
    	return supportedMediaTypeList;
    }
    
    /**
     * 拦截器 配置地方
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//跨域
         registry.addInterceptor(new PageInterceptor()).addPathPatterns("/**");
    }
    
}
