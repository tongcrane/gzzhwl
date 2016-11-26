package com.gzzhwl.api.notice.model;

import java.util.HashMap;
import java.util.Map;

import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.MD5;

public class SmsConfig {
	private String url;
	private String user;
	private String pass;
	private String sign;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Map<String, String> getBaseParam() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("account", this.user);// 用户名（必填）
		param.put("password", MD5.md5(this.pass));// 密码（必填）
		param.put("msgid", IdentifierUtils.getId().generate().toString());// 短信id，查询短信状态报告时需要，（可选）
		param.put("sign", this.sign);// 短信签名（必填）
		return param;
	}

}
