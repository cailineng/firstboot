package com.lineng.util;

import com.google.common.base.Objects;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {
	/**
	 * 验证固定电话号码
	 * 
	 * @param phone
	 *            电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	 *            <p>
	 * 			<b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	 *            数字之后是空格分隔的国家（地区）代码。
	 *            </p>
	 *            <p>
	 * 			<b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	 *            对不使用地区或城市代码的国家（地区），则省略该组件。
	 *            </p>
	 *            <p>
	 * 			<b>电话号码：</b>这包含从 0 到 9 的一个或多个数字
	 *            </p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPhone(String phone) {
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
		return Pattern.matches(regex, phone);
	}

	/**
	 * 匹配中国邮政编码
	 * 
	 * @param postcode
	 *            邮政编码
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPostcode(String postcode) {
		String regex = "([1-9]\\d{5})|(000000)";
		return Pattern.matches(regex, postcode);
	}




	public static boolean checkInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}





	/**
	 * ip地址格式校验
	 * @param ip
	 * @return
	 */
	public static boolean checkIp(String ip) {
		if (StringUtils.isEmpty(ip)) {
			return false;
		}
		if (ip.length() < 7 || ip.length() > 15) {
			return false;
		}
		/**
		 * 判断IP格式和范围
		 */
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

		Pattern pat = Pattern.compile(rexp);

		Matcher mat = pat.matcher(ip);

		return mat.find();
	}



	private static final Pattern humpPattern = Pattern.compile("[A-Z]");

	/**
	 * 驼峰转下划线
	 * @Description	
	 * @param str
	 * @return
	 * @author		sgd
	 * @date		2018年4月11日
	 */
	public static String humpToLine(String str) {

		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	private static final Pattern humpPattern2 = Pattern.compile("[a-z_A-Z]+");

	public static String humpToLine2(String str) {

		Matcher matcher = humpPattern2.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (Objects.equal(group.toLowerCase(), "desc") || Objects.equal(group.toLowerCase(), "asc")) {
				matcher.appendReplacement(sb, group);
				continue;
			}
			matcher.appendReplacement(sb, humpToLine(group));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * @Description	检查字符串是否只包含中英文，数字和_
	 * @param nickName
	 * @return
	 * @author		sgd
	 * @date		2018年4月13日
	 */
	public static boolean checkNickName(String nickName) {
		String regex = "^[_a-zA-Z0-9\u4E00-\u9FA5]+$";
		return Pattern.matches(regex, nickName);
	}

	/**
	 * @Description	检查密码，长度至少8位，数字和字母的组合
	 * @param password
	 * @return
	 * @author		sgd
	 * @date		2018年4月28日
	 */
	public static boolean checkPassword(String password) {
//		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
//		String regex = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\\\W]+$)(?![A-Za-z\\\\W]+$)(?![A-Z0-9\\\\W]+$)[a-zA-Z0-9\\\\W]{8,}$";
		String regex = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%^&*?]{7,20}$";
		return Pattern.matches(regex, password);
	}



}
