package com.gzzhwl.rest.security;

import java.util.List;

import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.Subject;

public interface AuthService {
	public List<Integer> getUserFunction(String staffId) throws RestException;

	public void doAuthentication(RequirePerm require, Subject subject) throws RestException;
}
