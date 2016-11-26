package com.gzzhwl.admin.security.validate;

import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.utils.MD5;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class StaffValidator {
	
	public static void valid_staff_password(StaffInfo staff,String oldPassword,String newPassword) {
		if(staff==null) {
			throw new RestException("1300013", "员工不存在");
		}
		String _password=staff.getPassword();
		if(!_password.equals(MD5.md5(oldPassword))) {
			throw new RestException("1300010", "原密码错误");
		}
		if(oldPassword.equals(newPassword)) {
			throw new RestException("1300011", "新密码和原密码相同");
		}
		//valid_password(newPassword);
	}
	
	public static void valid_password(String password) {
		boolean _result=ValidateUtils.validatePasswordContain(password, 6, 10);
		if(false==_result) {
			throw new RestException("1300012", "密码由字母及数字组成且密码长度为6-10位");
		}
	}

}
