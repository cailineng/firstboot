package com.lineng.bo;


import com.lineng.constant.ResultConst;
import com.lineng.util.PageUtil;

public class PageResult<T> extends InfoResult<Page<T>> {

	public void setInfo(com.github.pagehelper.Page<T> page) {
		setStatus(ResultConst.Status.TRUE);
		this.info = PageUtil.page2page(page);
	}
	
}
