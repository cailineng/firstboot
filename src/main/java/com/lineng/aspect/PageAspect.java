package com.lineng.aspect;


import com.github.pagehelper.PageHelper;
import com.lineng.page.PageContext;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * 类名: PageAspect</br> 
 * 包名：com.freegou.dJStore.aspect </br> 
 * 描述: 分页切面类</br>
 * 发布版本号：</br>
 * 开发人员： ruibiaozhong</br>
 * 创建时间： 2016年10月17日
 */
@Component
@Aspect
public class PageAspect {
	
	private Logger logger = Logger.getLogger(PageAspect.class);
	
	@Around("execution (com.github.pagehelper.Page com.lineng.mapper..*.*(..))")
	public Object around(ProceedingJoinPoint pjp) {
		Object result = null;
		try {
			if (!PageContext.isCancel()) {
				PageHelper.startPage(PageContext.getPageNumber(), PageContext.getPageSize(), PageContext.isDefaultCount());
				PageHelper.orderBy(PageContext.getOrderBy());
			}
			result= pjp.proceed();
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
}















