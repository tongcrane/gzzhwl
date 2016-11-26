package com.gzzhwl.api.account.validate;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.api.account.vo.Line;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;
import com.gzzhwl.core.constant.AgentType;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.InternalDriver;
import com.gzzhwl.core.data.model.LoginResultModel;
import com.gzzhwl.rest.exception.RestException;

public class LoginInfoValidator {

	public static void valid_exists(boolean exists) throws RestException {
		if (exists) {
			throw new RestException(ErrorCode.ERROR_11017);// 该用户已存在
		}
	}

	public static void valid_not_exists(AccountInfo accountInfo) throws RestException {
		if (accountInfo == null) {
			throw new RestException(ErrorCode.ERROR_11018);
		}
	}

	public static void valid_loginInfo(AccountInfo accountInfo, String password, DataSource logSource) {
		valid_not_exists(accountInfo);
		String loginPassword = accountInfo.getPassword();
		if (!StringUtils.equals(password, loginPassword)) {
			throw new RestException(ErrorCode.ERROR_11027);
		}

		String isDel = accountInfo.getIsDeleted();
		if (StringUtils.equals(isDel, Global.ISDEL_DELETE.toString())) {
			throw new RestException(ErrorCode.ERROR_11024);
		}
		String status = accountInfo.getStatus();
		if (StringUtils.equals(status, Global.STATUS_FROZEN.toString())) {
			throw new RestException(ErrorCode.ERROR_11025);
		}
		String isStaff = accountInfo.getIsStaff();
		if (logSource == DataSource.VLORRY) {// 运势界司机登录
			if (StringUtils.equals(isStaff, InternalDriver.YES)) {//
				throw new RestException(ErrorCode.ERROR_11026);// 内部司机不能登录运势界
			}
		} else {// 内部司机登录
			if (!StringUtils.equals(isStaff, InternalDriver.YES)) {//
				throw new RestException(ErrorCode.ERROR_11026);// 运势界司机不能登录内部司机
			}
		}
	}

	public static void valid_cellphone(boolean cellPhoneExists) throws RestException {
		if (cellPhoneExists) {
			throw new RestException(ErrorCode.ERROR_11002);// 手机号已存在
		}
	}

	public static void valid_password_length(String password) {
		if (password.length() < 6) {
			throw new RestException(ErrorCode.ERROR_11031);
		} else if (password.length() > 16) {
			throw new RestException(ErrorCode.ERROR_11032);
		}
	}

	public static void valid_changePassword(String oldPassword, String newPassword, String loginPassword)
			throws RestException {
		if (!StringUtils.equals(oldPassword, loginPassword)) {
			throw new RestException(ErrorCode.ERROR_11023);// 原密码错误
		}
		if (oldPassword.equals(newPassword)) {
			throw new RestException(ErrorCode.ERROR_11033);
		}
	}

	public static void valid_line(List<Line> lineList) {
		if (CollectionUtils.isEmpty(lineList)) {
			throw new RestException(ErrorCode.ERROR_11034);
		}
		if (CollectionUtils.size(lineList) > 5) {
			throw new RestException(ErrorCode.ERROR_11035);
		}

	}

	public static void valid_vehicle(List<VehicleInfoVo> vehicleList, String agentType) {

		if (AgentType.PERSONAL.getCode().equals(agentType)) {
			if (CollectionUtils.isEmpty(vehicleList)) {
				throw new RestException(ErrorCode.ERROR_11036);
			}
		} else {
			if (CollectionUtils.size(vehicleList) < 2) {
				throw new RestException(ErrorCode.ERROR_11038);
			}
		}

	}

	public static void valid_login(LoginResultModel loginResult, DeviceType deviceType) {
		if (deviceType == DeviceType.WEB) {// 如果是web登录
			String agentType = loginResult.getAgentType();
			String checkStatus = loginResult.getCheckStatus();
			if (StringUtils.equals(agentType, AgentType.COMPANY.getCode())
					&& StringUtils.equals(checkStatus, Global.STATUS_PASSED.toString())) {

			} else {
				throw new RestException(ErrorCode.ERROR_11037);
			}
		}
	}

}
