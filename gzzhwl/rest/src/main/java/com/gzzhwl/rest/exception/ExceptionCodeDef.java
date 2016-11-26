package com.gzzhwl.rest.exception;

/**
 * 系统级错误码定义
 */
public class ExceptionCodeDef {
	/**
	 * 服务器内部错误
	 */
	public static final String SC_INTERNAL_SERVER_ERROR = "500";
	/**
	 * 请求成功
	 */
	public static final String SC_OK = "200";
	/**
	 * 请求错误-缺少参数
	 */
	public static final String SC_BAD_REQUEST = "400";
	/**
	 * 请求错误-参数为空
	 */
	public static final String SC_EMPTY_REQUEST = "401";
	/**
	 * 请求错误-没有该接口
	 */
	public static final String SC_NOT_FOUND = "404";
	/**
	 * 请求错误-不允许该请求方式
	 */
	public static final String SC_METHOD_NOT_ALLOWED = "405";
	/**
	 * 禁止请求-token不是最新
	 */
	public static final String SC_LASTEST_FORBIDDEN = "403";
	/**
	 * 禁止请求-token为空
	 */
	public static final String SC_EMPTY_FORBIDDEN = "409";
	/**
	 * 禁止请求-token格式错误
	 */
	public static final String SC_FORMAT_FORBIDDEN = "406";
	/**
	 * 禁止请求-token错误
	 */
	public static final String SC_ERROR_FORBIDDEN = "407";
	/**
	 * 禁止请求-token丢失
	 */
	public static final String SC_MISSING_FORBIDDEN = "408";
	/**
	 * 请求超时
	 */
	public static final String SC_REQUEST_TIME_OUT = "410";
	/**
	 * 请求未授权
	 */
	public static final String SC_UN_AUTHORIZE = "700";
}
