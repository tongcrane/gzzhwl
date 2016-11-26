package com.gzzhwl.rest.springmvc.exception;

/**
 * 自定义异常获取错误码接口定义
 * 
 * @author anycrane
 *
 */
public interface InternalException {
	/**
	 * 获取错误信息
	 * 
	 * @return
	 */
	public String getErrorMsg();
}
