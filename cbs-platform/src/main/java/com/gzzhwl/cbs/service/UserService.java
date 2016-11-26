package com.gzzhwl.cbs.service;

import com.gzzhwl.cbs.exception.RestException;
import com.gzzhwl.cbs.vo.LoginVO;

public interface UserService {
	public LoginVO doLogin(String number, String password) throws RestException;
}
