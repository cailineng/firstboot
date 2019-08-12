package com.lineng.constant;


import com.lineng.annotation.Alias;

/**
 * 
 * 类名: ResultCode</br> 
 * 包名：com.freegou.dJStore.constant </br> 
 * 描述: 返回的编码</br>
 * 发布版本号：</br>
 * 开发人员： ruibiaozhong</br>
 * 创建时间： 2016年10月19日
 */
public class ResultConst {

	private ResultConst() {
	}

	public static class Status {

		private Status() {
		}

		/**
		 * 失败
		 */
		public static final String FALSE = "false";

		/**
		 * 成功
		 */
		public static final String TRUE = "true";
	}

	public static class Code {
		private Code() {
		}

		/**
		 * 成功
		 */
		@Alias("成功")
		public static final String SUCCESS = "200";

		/**
		 * 未授权
		 */
		@Alias("未授权")
		public static final String UNAUTHORIZED = "401";

		/**
		 * 系统异常
		 */
		@Alias("系统异常")
		public static final String EXCEPTION = "500";

		/**
		 * 验证错误
		 */
		@Alias("验证错误")
		public static final String ERROR = "501";
	}


}
