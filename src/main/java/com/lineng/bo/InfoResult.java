package com.lineng.bo;


import com.lineng.constant.ResultConst;

public class InfoResult<T> extends Result {

	protected T info;
	
	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		setStatus(ResultConst.Status.TRUE);
		this.info = info;
	}

	
}
