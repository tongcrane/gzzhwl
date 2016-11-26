package com.gzzhwl.rest.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gzzhwl.rest.exception.HttpException;
import com.gzzhwl.rest.http.HttpService;
import com.gzzhwl.rest.http.model.Credentials;
import com.gzzhwl.rest.http.model.HttpConfig;

@Service
public class HttpUrlServiceImpl implements HttpService {
	private final static Logger LOG = LoggerFactory.getLogger(HttpService.class);

	public HttpUrlServiceImpl() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		} };
		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(DO_NOT_VERIFY);
		} catch (NoSuchAlgorithmException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("初始化 https support 有错误,{}", e.getMessage());
			}
		} catch (KeyManagementException e) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("初始化 https support 有错误,{}", e.getMessage());
			}
		}

	}

	@Override
	public <K, V> String getAsParam(String url, Map<K, V> param, HttpConfig config) throws HttpException {
		List<NameValuePair> pairList = null;
		if (MapUtils.isNotEmpty(param)) {
			pairList = paramToNvp(param);
		}
		return doGet(url, pairList, config);
	}

	@Override
	public <K, V> String postAsForm(final String url, final Map<K, V> param, HttpConfig config) throws HttpException {
		String body = null;
		if (MapUtils.isNotEmpty(param)) {
			List<NameValuePair> pairList = paramToNvp(param);
			body = URLEncodedUtils.format(pairList, UTF_8);
		}
		return doPost(url, body, ContentType.APPLICATION_FORM_URLENCODED.withCharset(UTF_8), null, config);
	}

	@Override
	public <K, V> String postAsForm(final String url, final Map<K, V> param, final Credentials credentials,
			HttpConfig config) throws HttpException {
		String body = null;
		if (MapUtils.isNotEmpty(param)) {
			List<NameValuePair> pairList = paramToNvp(param);
			body = URLEncodedUtils.format(pairList, UTF_8);
		}
		return doPost(url, body, ContentType.APPLICATION_FORM_URLENCODED.withCharset(UTF_8), credentials, config);
	}

	@Override
	public String postAsBody(final String url, final String body, HttpConfig config) throws HttpException {
		return doPost(url, body, ContentType.APPLICATION_JSON.withCharset(UTF_8), null, config);
	}

	@Override
	public String postAsBody(final String url, final String body, final Credentials credentials, HttpConfig config)
			throws HttpException {
		return doPost(url, body, ContentType.APPLICATION_JSON.withCharset(UTF_8), credentials, config);
	}

	private <K, V> List<NameValuePair> paramToNvp(final Map<K, V> params) {
		List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
		for (Map.Entry<K, V> entry : params.entrySet()) {
			V value = entry.getValue();
			if (value != null) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
				pairList.add(pair);
			}
		}
		return pairList;
	}

	private HttpURLConnection getConnection(final String urlString) throws IOException {
		URL url = new URL(urlString);
		return getConnection(url);
	}

	private HttpURLConnection getConnection(final URL url) throws IOException {
		String protocol = url.getProtocol();
		if ("http".equals(protocol)) {
			return (HttpURLConnection) url.openConnection();
		} else if ("https".equals(protocol)) {
			return (HttpsURLConnection) url.openConnection();
		}
		return null;
	}

	private String doGet(final String url, List<NameValuePair> pairList, HttpConfig config) throws HttpException {
		if (config == null) {
			config = HttpConfig.createDefault();
		}
		HttpURLConnection conn = null;
		try {
			URIBuilder uriBuilder = new URIBuilder();
			uriBuilder.setPath(url);
			if (CollectionUtils.isNotEmpty(pairList)) {
				uriBuilder.addParameters(pairList);
				if (LOG.isDebugEnabled()) {
					LOG.debug("访问外部接口地址 : {}，参数 : {}", url, JSON.toJSONString(pairList));
				}
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("访问外部接口地址 : {}，无参数。", url);
				}
			}
			URI uri = uriBuilder.build();
			conn = getConnection(uri.toURL());
			conn.setRequestProperty("Pragma", "no-cache");// 设置不适用缓存
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(config.getConnetionTimeOut());// 设置链接超时时间
			conn.setReadTimeout(config.getReadTimeOut());// 设置数据读取超时时间
			conn.setRequestMethod("GET");
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Connection", "Close");// 不支持Keep-Alive
			conn.connect();// 进行连接，但是实际上connection.getInputStream()函数中才会真正发到
			return convertResponseData(conn, config);
		} catch (HttpException e) {
			if (CollectionUtils.isNotEmpty(pairList)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("地址 : {}，参数 : {}，错误信息：{}", url, JSON.toJSONString(pairList), e.toString());
				}
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("地址 : {}，无参数，错误信息：{}", url, e.toString());
				}
			}
			throw e;
		} catch (Exception e) {
			if (CollectionUtils.isNotEmpty(pairList)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("地址 : {}，参数 : {}，错误信息：{}", url, JSON.toJSONString(pairList), e.toString());
				}
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("地址 : {}，无参数，错误信息：{}", url, e.toString());
				}
			}
			throw new HttpException(StringUtils.defaultString(e.getLocalizedMessage(), e.getClass().getName()));
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("释放http连接出现错误");
					}
				}
			}
		}
	}

	private String doPost(final String url, final String body, final ContentType contentType,
			final Credentials credentials, HttpConfig config) throws HttpException {
		if (config == null) {
			config = HttpConfig.createDefault();
		}
		HttpURLConnection conn = null;
		try {
			conn = getConnection(url);
			conn.setRequestProperty("Pragma", "no-cache");// 设置不适用缓存
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(config.getConnetionTimeOut());// 设置链接超时时间
			conn.setReadTimeout(config.getReadTimeOut());// 设置数据读取超时时间
			conn.setRequestMethod("POST");
			conn.setInstanceFollowRedirects(true);
			if (credentials != null) {
				conn.setRequestProperty("Authorization", credentials.getAuthorization());
			}
			if (contentType != null) {
				conn.setRequestProperty("Content-Type", contentType.toString());
			} else {
				conn.setRequestProperty("Content-Type",
						ContentType.APPLICATION_FORM_URLENCODED.withCharset(UTF_8).toString());
			}
			conn.setRequestProperty("Connection", "Close");// 不支持Keep-Alive
			conn.connect();// 进行连接，但是实际上connection.getInputStream()函数中才会真正发到
			if (body != null) { // 判断是否有POST参数，如果有则将数据传到服务器
				if (LOG.isDebugEnabled()) {
					LOG.debug("访问外部接口地址 : {}，参数 : {}", url, body);
				}
				OutputStream os = conn.getOutputStream();// 获得输出流
				os.write(body.getBytes(CONTENT_CHARSET));
				os.flush();// 刷新输出流，确保数据被传到服务器
				os.close();// 关闭输出流
			} else {
				if (LOG.isDebugEnabled()) {
					LOG.debug("访问外部接口地址 : {}，无参数。", url);
				}
			}
			return convertResponseData(conn, config);
		} catch (HttpException e) {
			if (LOG.isDebugEnabled()) {
				String param = body == null ? StringUtils.EMPTY : body;
				LOG.debug("地址 : {}，参数 : {}，错误信息：{}", url, param, e.toString());
			}
			throw e;
		} catch (Exception e) {
			if (LOG.isDebugEnabled()) {
				String param = body == null ? StringUtils.EMPTY : body;
				LOG.debug("地址 : {}，参数 : {}，错误信息：{}", url, param, e.toString());
			}
			throw new HttpException(StringUtils.defaultString(e.getLocalizedMessage(), e.getClass().getName()));
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("释放http连接出现错误");
					}
				}
			}
		}
	}

	private String convertResponseData(final HttpURLConnection conn, final HttpConfig config)
			throws HttpException, IOException {
		int statusCode = conn.getResponseCode();
		if (HttpURLConnection.HTTP_OK == statusCode) {
			String data = getContent(conn, UTF_8);
			if (data == null) {
				throw new HttpException("外部接口返回 null");
			} else {
				if (config.isHasLog()) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("外部返回数据 : {}", data);
					}
				}
				return data;
			}
		} else {
			throw new HttpException("外部返回状态码 : " + statusCode);
		}
	}

	private String getContent(final HttpURLConnection conn, final Charset defaultCharset)
			throws IOException, ParseException {
		final InputStream instream = conn.getInputStream();
		if (instream == null) {
			return null;
		}
		try {
			int i = conn.getContentLength();
			if (i < 0) {
				i = 4096;
			}
			Charset charset = null;
			String str_contentType = conn.getContentType();
			if (StringUtils.isNotBlank(str_contentType)) {
				try {
					final ContentType contentType = ContentType.parse(str_contentType);
					if (contentType != null) {
						charset = contentType.getCharset();
					}
				} catch (final UnsupportedCharsetException ex) {
					// throw new UnsupportedEncodingException(ex.getMessage());
				}
			}
			if (charset == null) {
				charset = defaultCharset;
			}
			if (charset == null) {
				charset = ISO_8859_1;
			}
			final Reader reader = new InputStreamReader(instream, charset);
			final CharArrayBuffer buffer = new CharArrayBuffer(i);
			final char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return buffer.toString();
		} finally {
			instream.close();
		}
	}

}
