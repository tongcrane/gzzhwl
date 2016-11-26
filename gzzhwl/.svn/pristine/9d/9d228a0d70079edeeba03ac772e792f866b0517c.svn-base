package com.gzzhwl.admin.security.validate;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.model.ResetPasswordLog;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class TicketValidator {

	public static void validateTicket(ResetPasswordLog resetPassword, final Integer minuteDiff) {

		if (ValidateUtils.isEmpty(resetPassword)) {
			throw new RestException("10027","票据不存在");
		}

		if (Global.ISDEL_DELETE.toString().equals(resetPassword.getStatus())) {
			throw new RestException("10026","票据失效");
		}

		if (minuteDiff.compareTo(resetPassword.getMinuteDiff()) <= 0) {
			throw new RestException("10025","票据过期");
		}

	}
}