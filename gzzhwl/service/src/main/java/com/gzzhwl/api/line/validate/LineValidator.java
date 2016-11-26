package com.gzzhwl.api.line.validate;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.data.model.LineInfo;
import com.gzzhwl.rest.exception.RestException;

public class LineValidator {

	/**
	 * 添加线路最多5条，重复线路不能添加
	 * 
	 * @param lineList
	 * @param departureCode
	 * @param destinationCode
	 */
	public static void valid_add_line(List<Map<String, String>> lineList) {
		if (lineList.size() >= 5) {
			throw new RestException(ErrorCode.ERROR_21001);
		}
	}

	public static void valid_line_repeat(List<Map<String, String>> lineList, String departureCode,
			String destinationCode) {
		for (Map<String, String> map : lineList) {
			String _depCode = map.get("departureCode");
			String _desCode = map.get("destinationCode");
			if ((_depCode.equals(departureCode) && _desCode.equals(destinationCode))
					|| (_depCode.equals(destinationCode) && _desCode.equals(departureCode))) {
				throw new RestException(ErrorCode.ERROR_21002);
			}
		}
	}

	/**
	 * 验证线路合法性，是否属于当前操作人
	 * 
	 * @param lineList
	 * @param lineInfo
	 * @param driverInfoId
	 * @param accountId
	 */
	public static void valid_line(LineInfo lineInfo, String driverInfoId, String accountId) {
		if (null == lineInfo) {
			throw new RestException(ErrorCode.ERROR_21004);
		}
		String _accountId = lineInfo.getAccountId();
		if (StringUtils.isNotEmpty(_accountId)) {
			if (!_accountId.equals(accountId)) {
				throw new RestException(ErrorCode.ERROR_21005);
			}
		}
		String _driverInfoId = lineInfo.getDriverInfoId();
		if (StringUtils.isNotEmpty(_driverInfoId)) {
			if (!_driverInfoId.equals(driverInfoId)) {
				throw new RestException(ErrorCode.ERROR_21005);
			}
		}
	}

	/**
	 * 删除线路必须保留一条
	 * 
	 * @param lineList
	 */
	public static void valid_del_line(List<Map<String, String>> lineList) {
		if (lineList.size() == 1) {
			throw new RestException(ErrorCode.ERROR_21003);
		}
	}

	/**
	 * 验证线路是否填写完整
	 * 
	 * @param departureCode
	 * @param destinationCode
	 */
	public static void valid_line_complete(String departureCode, String destinationCode) {
		if ((StringUtils.isNotBlank(departureCode) && StringUtils.isBlank(destinationCode))
				|| (StringUtils.isBlank(departureCode) && StringUtils.isNotBlank(destinationCode))) {
			throw new RestException(ErrorCode.ERROR_800023);
		}
	}

	public static void valid_add_line(String departureCode, String destinationCode) {
		if (StringUtils.equals(departureCode, destinationCode)) {
			throw new RestException(ErrorCode.ERROR_800026);
		}

	}

}
