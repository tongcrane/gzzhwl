package com.gzzhwl.core.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期工具
 */
public class JodaDateUtils {
	final static String DEFAULT_FORMAT = "yyyy-MM-dd";

	final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 根据给定的格式化参数，将字符串转换为日期对象
	 */
	public static DateTime parse(String dateString, String dateFormat) {
		DateTimeFormatter format = DateTimeFormat.forPattern(dateFormat);
		DateTime dateTime = DateTime.parse(dateString, format);
		return dateTime;
	}

	/**
	 * 使用默认日期格式将日期字符串转换为日期对象，格式(yyyy-MM-dd)
	 */
	public static DateTime parse(String dateString) {
		return parse(dateString, DEFAULT_FORMAT);
	}

	/**
	 * 根据给定的格式化参数，将日期对象转换为字符串
	 */
	public static String toString(DateTime date, String dateFormat) {
		if (date == null) {
			return null;
		}
		return date.toString(dateFormat);
	}

	/**
	 * 使用默认格式将日期对象转换为字符串，格式(yyyy-MM-dd)
	 */
	public static String toString(DateTime date) {
		return toString(date, DEFAULT_FORMAT);
	}

	/**
	 * 将日期转换为长整型
	 */
	public static long toLong(DateTime date) {
		if (date == null) {
			return 0;
		}
		return date.getMillis();
	}

	/**
	 * 将长整型转换为日期对象
	 */
	public static DateTime toDate(long time) {
		return new DateTime(time);
	}

	/**
	 * 获得系统当前时间
	 */
	public static String currentStringDate() {
		return toString(new DateTime());
	}

	/**
	 * 根据给定的格式化参数，获得系统当前时间
	 */
	public static String currentStringDate(String formate) {
		return toString(new DateTime(), formate);
	}

	/**
	 * 获得系统当前时间
	 */
	public static DateTime currentDate() {
		return new DateTime();
	}

	/**
	 * 标准日期格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDefaultValidDate(String str) {
		return isValidDate(str, DEFAULT_FORMAT);
	}

	/**
	 * 标准日期格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDefaultValidTime(String str) {
		return isValidDate(str, DEFAULT_TIME_FORMAT);
	}

	/**
	 * 日期格式校验
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str, String fmt) {
		DateTimeFormatter format = DateTimeFormat.forPattern(fmt);
		boolean convertSuccess = true;
		try {
			DateTime.parse(str, format);
		} catch (Exception e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

}
