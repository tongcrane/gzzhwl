package com.gzzhwl.rest.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.data.dao.MenuInfoDao;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.AuthService;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.AuthType;
import com.gzzhwl.rest.security.model.Subject;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private MenuInfoDao menuDao;

	@Override
	public List<Integer> getUserFunction(String staffId) throws RestException {
		return menuDao.findStaffFunction(staffId);
	}

	@Override
	public void doAuthentication(RequirePerm require, Subject subject) throws RestException {
		if (require == null) {
			this.doAuthc(subject);// do default
		} else {
			AuthType authType = require.type();
			switch (authType) {
			case ANON:
				this.doAnon(subject);
				break;
			case AUTHC:
				this.doAuthc(subject);
				break;
			case MENU:
				this.doMenu(require.value(), subject);
				break;
			default:
				this.doAnon(subject);
				break;
			}
		}
	}

	private void doMenu(int[] values, Subject subject) {
		this.doAuthc(subject);
		String staffId = subject.getStaffId();
		List<Integer> userPerms = this.getUserFunction(staffId);
		boolean contains = false;
		for (int value : values) {
			if (userPerms.contains(value)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new RestException(ExceptionCodeDef.SC_UN_AUTHORIZE, "没有权限访问接口");
		}
	}

	private void doAnon(Subject subject) throws RestException {

	}

	private void doAuthc(Subject subject) throws RestException {
		if (subject == null) {
			throw new RestException(ExceptionCodeDef.SC_MISSING_FORBIDDEN, "尚未登录，请先认证权限。");
		}
	}

}
