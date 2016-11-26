package com.gzzhwl.rest.http.model;

public final class HttpConfig {
	private boolean hasLog;
	private int readTimeOut;
	private int connetionTimeOut;

	public boolean isHasLog() {
		return hasLog;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public int getConnetionTimeOut() {
		return connetionTimeOut;
	}

	private HttpConfig() {

	}

	public static HttpConfig createDefault() {
		HttpConfig config = new HttpConfig();
		config.setConnetionTimeOut(6 * 1000);
		config.setReadTimeOut(10 * 1000);
		config.setHasLog(true);
		return config;
	}

	public HttpConfig setHasLog(boolean hasLog) {
		this.hasLog = hasLog;
		return this;
	}

	public HttpConfig setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
		return this;
	}

	public HttpConfig setConnetionTimeOut(int connetionTimeOut) {
		this.connetionTimeOut = connetionTimeOut;
		return this;
	}
}
