package com.lineng.util;


import com.github.pagehelper.Page;

public class PageUtil {

	private PageUtil() {
	}

	public static <T> com.lineng.bo.Page<T> page2page(Page<T> page) {
		com.lineng.bo.Page<T> p = new com.lineng.bo.Page<T>();
		p.setPageNum(page.getPageNum());
		p.setPageSize(page.getPageSize());
		p.setStartRow(page.getStartRow());
		p.setEndRow(page.getEndRow());
		p.setTotal(page.getTotal());
		p.setPages(page.getPages());
		p.setResult(page.getResult());
		p.setOrderBy(page.getOrderBy());
		return p;
	}

}
