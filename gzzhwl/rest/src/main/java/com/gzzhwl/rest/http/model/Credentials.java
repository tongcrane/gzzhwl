package com.gzzhwl.rest.http.model;

import java.nio.charset.Charset;

public interface Credentials {
	public static final Charset UTF_8 = Charset.forName("utf-8");

	public String getAuthorization();
}
