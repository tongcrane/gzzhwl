package com.gzzhwl.rest.http.model;

import com.gzzhwl.core.utils.Base64;

public final class UsernamePasswordCredentials implements Credentials {

	private String username;

	private String password;

	public UsernamePasswordCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String getAuthorization() {
		String str = username + ":" + password;
		String encoded = Base64.encode(str.getBytes(UTF_8));
		return "Basic " + encoded;
	}
}
