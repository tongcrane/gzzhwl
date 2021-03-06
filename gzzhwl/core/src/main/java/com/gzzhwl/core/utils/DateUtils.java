package com.gzzhwl.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具
 */
public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	final static String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	final static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 根据给定的格式化参数，将字符串转换为日期对象
	 */
	public static java.util.Date parse(String dateString, String dateFormat) throws ParseException {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>parse(String dateString, String dateFormat)");
		}
		if ("".equals(dateString.trim()) || dateString == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = sdf.parse(dateString);
		return date;
	}
	

	/**
	 * 使用默认日期格式将日期字符串转换为日期对象，格式(yyyy-MM-dd)
	 */
	public static java.util.Date parse(String dateString) throws ParseException {
		return parse(dateString, DEFAULT_FORMAT);
	}

	/**
	 * 根据给定的格式化参数，将日期对象转换为字符串
	 */
	public static String toString(java.util.Date date, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>> toString(java.util.Date date, String dateFormat)");
		}
		if ("".equals(date) || date == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = sdf.format(date);
		if (logger.isDebugEnabled()) {
			logger.debug("<<< toString return String");
		}
		return str;
	}

	/**
	 * 使用默认格式将日期对象转换为字符串，格式(yyyy-MM-dd)
	 */
	public static String toString(java.util.Date date) {
		return toString(date, DEFAULT_FORMAT);
	}

	/**
	 * 将日期转换为长整型
	 */
	public static long toLong(java.util.Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toLong(java.util.Date date)");
		}
		if (date == null) {
			return 0;
		}
		long d = date.getTime();
		if (logger.isDebugEnabled()) {
			logger.debug("<<<toLong return long");
		}
		return d;
	}

	/**
	 * 将长整型转换为日期对象
	 */
	public static java.util.Date toDate(long time) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toDate (long time)");
		}
		if ("".equals(time)) {
			return new Date();
		}
		Date date = new Date(time);
		if (logger.isDebugEnabled()) {
			logger.debug("<<<toDate return date");
		}
		return date;
	}

	/**
	 * 获得系统当前时间
	 */
	public static String currentStringDate() {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();
		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return toString(date);
	}

	/**
	 * 根据给定的格式化参数，获得系统当前时间
	 */
	public static String currentStringDate(String formate) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();
		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return toString(date, formate);
	}

	/**
	 * 获得系统当前时间
	 */
	public static java.util.Date currentDate() {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();
		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return date;
	}

	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间
	 */
	public static Date add(int field, Date date, int value) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>add(int field,Date date,int value)");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, value);
		Date newDate = ca.getTime();
		if (logger.isDebugEnabled()) {
			logger.debug("<<<add() return date");
		}
		return newDate;
	}

	/**
	 * 返回给定日历字段的值
	 */
	public static int getField(int field, Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>get(int field, Date date");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int value = ca.get(field);
		if (logger.isDebugEnabled()) {
			logger.debug("<<<get() return date");
		}
		return value;
	}

	/**
	 * 返回前N个月的日期值
	 */
	public static Date getLastMonth(String month) {
		Calendar ca = Calendar.getInstance();
		int m = 0;
		try {
			m = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		ca.add(Calendar.MONTH, -m);
		return ca.getTime();
	}

	/**
	 * 返回前N天的日期
	 */
	public static String getLastDay(Integer day) {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, day);
		return toString(ca.getTime());
	}
	
	/**
	 * 标准日期格式校验
	 * @param str
	 * @return
	 */
	public static boolean isDefaultValidDate(String str) {
		return isValidDate(str,DEFAULT_FORMAT);
	 }
	
	/**
	 * 标准日期格式校验
	 * @param str
	 * @return
	 */
	public static boolean isDefaultValidTime(String str) {
		return isValidDate(str,DEFAULT_TIME_FORMAT);
	 }
	
	/**
	 * 日期格式校验
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str,String fmt) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		try {
		// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		format.setLenient(false);
		format.parse(str);
		} catch (ParseException e) {
		 convertSuccess=false;
		 } 
		return convertSuccess;
	 }
	
	public static String getStartTime(String startTime) throws ParseException{
		
		if(!isValidDate(startTime, "yyyy-MM-dd HH:mm:ss")){
			if(!isValidDate(startTime, "yyyy-MM-dd HH:mm")){
				if(!isDefaultValidDate(startTime)){
					throw new ParseException("时间格式异常.",0);
				}
				return startTime+" 00:00:00";
			}
			return startTime+":00";
		}
		return startTime;
	}
	
	public static String getEndTime(String endTime) throws ParseException{
		if(!isValidDate(endTime, "yyyy-MM-dd HH:mm:ss")){
			if(!isValidDate(endTime, "yyyy-MM-dd HH:mm")){
				if(!isDefaultValidDate(endTime)){
					throw new ParseException("时间格式异常.",0);
				}
				return endTime+" 23:59:59";
			}
			return endTime+":59";
		}
		return endTime;
	}
	
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(DateUtils.getStartTime("2016-08-22"));
		
		System.out.println(DateUtils.getStartTime("2016-08-22 11:12"));
		
		System.out.println(DateUtils.getStartTime("2016-08-22 11:12:12"));
		
		System.out.println(DateUtils.getEndTime("2016-08-22"));
		
		System.out.println(DateUtils.getEndTime("2016-08-22 11:12"));
		
		System.out.println(DateUtils.getEndTime("2016-08-22 11:12:12"));
		
	}
	
}
