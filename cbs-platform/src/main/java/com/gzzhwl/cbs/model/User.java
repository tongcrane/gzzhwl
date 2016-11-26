package com.gzzhwl.cbs.model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3532297956125296787L;
	private String userName;
	private String passWord;
	private boolean rememberMe;
	private boolean autoLogin;
	private String captcha;

}
