package com.gzzhwl.core.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合法性验证工具类
 */
public class ValidateUtils {

	/**
	 * 验证对象是否为空
	 * 
	 * @Author : 马恩伟
	 * @Date : 2014-4-25
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {
			isEmpty = true;
		} else if (obj instanceof String) {
			isEmpty = ((String) obj).trim().isEmpty();
		} else if (obj instanceof Collection) {
			isEmpty = (((Collection) obj).size() == 0);
		} else if (obj instanceof Map) {
			isEmpty = ((Map) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			isEmpty = Array.getLength(obj) == 0;
		}
		return isEmpty;
	}

	/**
	 * 获取字符串长度，中文算2个。
	 * 
	 * @author 马恩伟
	 * @date 2014-8-22
	 */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 验证手机号码格式
	 * 
	 * @author mj
	 * @date 2015年1月21日
	 * @param mobiles
	 * @return boolean
	 */
	public static boolean isMobileNo(String mobiles) {
		String pattern = "^1[0-9]{10}$";
		return matcher(pattern, mobiles);
	}

	/**
	 * 验证是否是邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String pattern = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		return matcher(pattern, email);
	}

	/**
	 * 验证用户名为纯数字
	 * 
	 * @author mj
	 * @date 2015年1月21日
	 * @param username
	 * @return boolean
	 */
	public static boolean isAllNumber(String number) {
		String pattern = "^[0-9]+$";
		return matcher(pattern, number);
	}

	/**
	 * 验证密码长度及字符
	 * 
	 * @author mj
	 * @date 2015年1月21日
	 * @param password
	 * @return boolean
	 */
	public static boolean validatePassword(String password, Integer minLength, Integer maxLength) {
		String pattern = "^[0-9a-zA-Z]{" + minLength + "," + maxLength + "}$";
		return matcher(pattern, password);
	}
	
	/**
	 * 验证字符串长度且只包含字母和数字
	 * @param password
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean validatePasswordContain(String password, Integer minLength, Integer maxLength) {
		String pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"+minLength+","+maxLength+"}$";
		return matcher(pattern, password);
	}
	
	/**
	 * 验证中文及长度
	 * @param name
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean validateChinese(String name,Integer minLength,Integer maxLength) {
		String pattern="^[\u4e00-\u9fa5··]{"+minLength+","+maxLength+"}$";
		return matcher(pattern, name);
	}
	
	/**
	 * 验证字符串是否为中文
	 * 
	 * @param name
	 * @param startindex
	 * @param endIndex
	 * @return
	 */
	public static boolean validateIsAllChinese(String name,Integer startindex,Integer endIndex) {
		String code = name.substring(startindex, endIndex);
		String pattern="^[\u4e00-\u9fa5··]*$";
		return matcher(pattern, code);
	}
	
	/**
	 * 验证数字及长度
	 * @param number
	 * @param length
	 * @return
	 */
	public static boolean validateNumber(String number,Integer length) {
		String pattern="^[0-9]{"+length+"}$";
		return matcher(pattern,number);
	}
	
	/**
	 * 验证组织机构代码
	 * @param code
	 * @return
	 */
	public static boolean validateOrgCode(String code) {
		String pattern="[a-zA-Z0-9]{8}-[a-zA-Z0-9]";
		return matcher(pattern, code);
	}
	
	/**
	 * 验证是否包含特殊字符
	 * @author mj
	 * @date 2015年3月6日
	 * @param username
	 * @return
	 * @return boolean
	 */
	public static boolean containSpecialChar(String username) {
		String regEx = "[^+`~!@#$^%&*()=|{}':;\",\\[\\].<>/?~！@#￥……&*（）——|｛｝【】·‘；：”“'。，、？]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(username);
		return m.matches();
	}

	public static boolean matcher(String pattern, String input) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(input);
		return m.matches();
	}
	
	/**
	 * 验证金额格式(数位最多十位，小数为最多为两位，可以无小数位 )
	 * 
	 * @param money
	 * @return boolean
	 */
	public static boolean isMoney(String money) {
		String pattern = "^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$";
		return matcher(pattern, money);
	}
	
	/**
	 * 验证字符串,数字型，最多一位小数，无小数时不显示小数点及后数字
	 * @param code
	 * @return
	 */
	public static boolean validCode(String code) {
		String pattern = "^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1})?))$";
		return matcher(pattern, code);
	}
	
	/**
	 * 验证体积,数字型，最多一位小数，无小数时不显示小数点及后数字，整数部分最多为3位
	 * @param code
	 * @return
	 */
	public static boolean validVolumn(String code) {
		String pattern = "^(([0-9]|([1-9][0-9]{0,2}))((\\.[0-9]{1})?))$";
		return matcher(pattern, code);
	}
	
	/**
	 * 验证重量,数字型，最多一位小数，无小数时不显示小数点及后数字，整数部分最多为5位
	 * @param code
	 * @return
	 */
	public static boolean validWeight(String code) {
		String pattern = "^(([0-9]|([1-9][0-9]{0,4}))((\\.[0-9]{1})?))$";
		return matcher(pattern, code);
	}
	
}
