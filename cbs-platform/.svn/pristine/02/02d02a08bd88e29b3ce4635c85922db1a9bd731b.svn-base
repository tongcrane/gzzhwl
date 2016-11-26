package com.gzzhwl.cbs.support;

import java.nio.charset.Charset;
import java.util.Map;

import com.gzzhwl.cbs.exception.HttpException;
import com.gzzhwl.cbs.model.HttpConfig;

public interface HttpSupport {
	public static final String CONTENT_CHARSET = "utf-8";

	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	public static final Charset UTF_8 = Charset.forName(CONTENT_CHARSET);

	/**
	 * get方式请求 URL Parameter 方式传递参数
	 */
	public <K, V> String getAsParam(final String url, final Map<K, V> param, HttpConfig config) throws HttpException;

	/**
	 * post方式请求 URL Parameter 方式传递参数(表单传递)
	 */
	public <K, V> String postAsForm(final String url, final Map<K, V> param, HttpConfig config) throws HttpException;

	/**
	 * post方式请求 Body方式传递参数
	 */
	public String postAsBody(final String url, final String body, HttpConfig config) throws HttpException;
}
