package com.lineng.interceptor;

import com.github.pagehelper.Page;
import com.lineng.annotation.Pagination;
import com.lineng.constant.PageConstant;
import com.lineng.page.PageContext;
import com.lineng.util.PageUtil;
import com.lineng.util.StrUtil;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * 类名: PageInterceptor</br> 
 * 包名：com.freegou.interceptor </br> 
 * 描述: 分页拦截器</br>
 * 发布版本号：</br>
 * 开发人员： ruibiaozhong</br>
 * 创建时间： 2016年7月15日
 */
public class PageInterceptor extends BaseInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (isNeedToPage(handler)) {
			PageContext.setPageNumber(getPageNumber(request));
			PageContext.setPageSize(getPageSize(request));
			PageContext.setOrderBy(getOrderBy(request, handler));
			PageContext.setCancel(false);
			PageContext.setDefaultCount(getDefaultCount(handler));
			PageContext.setCacheFlag(true); // 查询的数据, 基于分页进行缓存
		} else {
			PageContext.setCancel(true);
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (isNeedToPage(handler)) {
			PageContext.remove();
			if (null != modelAndView) {
				ModelMap modelMap = modelAndView.getModelMap();
				Set<Entry<String, Object>> set = modelMap.entrySet();
				for (Entry<String, Object> entry : set) {
					Object value = entry.getValue();
					if (value instanceof Page) {
						String key = entry.getKey();
						Page page = (Page) value;
						modelMap.put(key, PageUtil.page2page(page));
					}
				}
			}

		}

	}

	private boolean isNeedToPage(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
			if (null == pagination) {
				return false;
			}
			return pagination.value();
		}
		return false;
	}

	/** 
	 * 获得pageNumber参数的值 
	 * @param request 
	 * @return 
	 */
	protected Integer getPageNumber(HttpServletRequest request) {
		Integer pageNumber = PageContext.PAGE_NUM;
		try {
			String pg = request.getParameter(PageConstant.PAGE_NUM);
			if (!StringUtils.isEmpty(pg)) {
				pageNumber = Integer.parseInt(pg);
			}
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return pageNumber;
	}

	/** 
	 * 设置默认每页大小 
	 * @return 
	 */
	protected Integer getPageSize(HttpServletRequest request) {
		Integer pageSize = PageContext.PAGE_SIZE;
		try {
			String ps = request.getParameter(PageConstant.PAGE_SIZE);
            if (!StringUtils.isEmpty(ps)) {
				pageSize = Integer.parseInt(ps);
			}
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return pageSize;
	}

	protected String getOrderBy(HttpServletRequest request, Object handler) {
		String orderBy = null;
		try {
			String ps = request.getParameter(PageContext.ORDER_BY);
			if (!StringUtils.isEmpty(ps)) {
				orderBy = StrUtil.humpToLine2(ps);
			} else {
				if (handler instanceof HandlerMethod) {
					HandlerMethod handlerMethod = (HandlerMethod) handler;
					Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
					if (null != pagination) {
						orderBy = pagination.orderBy();
					}
				}
			}
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return orderBy;
	}

	/**
	 * 获取配置的使用默认统计SQL
	 * @param request
	 * @param handler
	 * @return
	 */
	private boolean getDefaultCount(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
			if (null != pagination) {
				return pagination.defaultCount();
			}
		}
		return true;
	}

}