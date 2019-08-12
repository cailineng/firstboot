package com.lineng.bo;


import com.lineng.constant.ResultConst;

/*
 * ajax 返回的结果成功与否
 * @author ruibiao_z
 *
 */
public class Result {
	
	private String status;
	
	private String code;
	
	private String msg;

	public Result() {
		this.status = ResultConst.Status.TRUE;
		this.code = ResultConst.Code.SUCCESS;
	}

	public Result(String status, String code) {
		this.status = status;
		this.code = code;
	}
	
	
	public Result(String status, String code, String msg) {
		this.status = status;
		this.code = code;
		this.msg = msg;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setExceptionStatus() {
		this.status = ResultConst.Status.FALSE;
		this.code = ResultConst.Code.EXCEPTION;
	}
	
	
	public void setExceptionStatus(String msg) {
		this.status = ResultConst.Status.FALSE;
		this.code = ResultConst.Code.EXCEPTION;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.status = ResultConst.Status.FALSE;
		this.code = ResultConst.Code.ERROR;
		this.msg = msg;
	}
	
	
	
	
	
	public void setTokenExpiried() {
		this.status = ResultConst.Status.FALSE;
		this.code = ResultConst.Code.UNAUTHORIZED;
		this.msg = "token已失效";
	}
	
	
	
	public void setCustomCodeWithMsg(String code, String msg) {
		this.status = ResultConst.Status.FALSE;
		this.code = code;
		this.msg = msg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
