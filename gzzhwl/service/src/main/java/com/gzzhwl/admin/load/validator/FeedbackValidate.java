package com.gzzhwl.admin.load.validator;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.admin.load.vo.LoadFeedbackVo;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.LoadFeedBackType;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class FeedbackValidate {

	public static void valid_gps_feedback(List<LoadFeedbackVo> loadFeedbackVoList) {

		for (int i = 0; i < loadFeedbackVoList.size(); i++) {

			LoadFeedbackVo loadFeedbackVo = loadFeedbackVoList.get(i);

			LoadFeedBackType feedBackType = LoadFeedBackType.getLoadFeedBackType(loadFeedbackVo.getType());

			LoadBillType loadBillType = LoadBillType.getLoadBillType(loadFeedbackVo.getStatus());

			if (!feedBackType.equals(LoadFeedBackType.DRIVER)) {
				throw new RestException(ErrorCode.ERROR_900006.getCode(), "type " + ErrorCode.ERROR_900006.getDesc());
			}

			if (!loadBillType.equals(LoadBillType.DEPART)) {
				throw new RestException(ErrorCode.ERROR_900006.getCode(), "status " + ErrorCode.ERROR_900006.getDesc());
			}

			if (!StringUtils.isNotBlank(loadFeedbackVo.getFeedbackTime())) {
				throw new RestException(ErrorCode.ERROR_900003.getCode(), " 反馈时间 " + ErrorCode.ERROR_900003.getDesc());
			}

		}

	}

	public static void valid_price_feedback(String price) {

		if (StringUtils.isNotBlank(price)) {
			if (Double.parseDouble(price) >= 100000000) {
				throw new RestException(ErrorCode.ERROR_900027);
			}
		}

	}

	public static void valid_cbsdriver_feedback(List<LoadFeedbackVo> loadFeedbackVoList) {

		for (int i = 0; i < loadFeedbackVoList.size(); i++) {

			LoadFeedbackVo loadFeedbackVo = loadFeedbackVoList.get(i);

			LoadFeedBackType feedBackType = LoadFeedBackType.getLoadFeedBackType(loadFeedbackVo.getType());

			if (!feedBackType.equals(LoadFeedBackType.DRIVER)) {
				throw new RestException(ErrorCode.ERROR_900006.getCode(), "type " + ErrorCode.ERROR_900006.getDesc());
			}

			if (!StringUtils.isNotBlank(loadFeedbackVo.getFeedbackTime())) {
				throw new RestException(ErrorCode.ERROR_900003.getCode(), " 反馈时间 " + ErrorCode.ERROR_900003.getDesc());
			}

			if (ValidateUtils.isEmpty(loadFeedbackVo.getImageIdList())) {
				throw new RestException(ErrorCode.ERROR_900016);
			}

			if (loadFeedbackVo.getImageIdList().size() > 5) {
				throw new RestException(ErrorCode.ERROR_900015);
			}

			if (!StringUtils.isNotBlank(loadFeedbackVo.getItemName())) {
				throw new RestException(ErrorCode.ERROR_900003.getCode(), " 反馈时间 " + ErrorCode.ERROR_900003.getDesc());
			}
		}

	}

	public static void valid_ysjdriver_feedback(List<LoadFeedbackVo> loadFeedbackVoList) {

		for (int i = 0; i < loadFeedbackVoList.size(); i++) {

			LoadFeedbackVo loadFeedbackVo = loadFeedbackVoList.get(i);

			LoadFeedBackType feedBackType = LoadFeedBackType.getLoadFeedBackType(loadFeedbackVo.getType());

			if (!feedBackType.equals(LoadFeedBackType.DRIVER)) {
				throw new RestException(ErrorCode.ERROR_900006.getCode(), "type " + ErrorCode.ERROR_900006.getDesc());
			}

			if (!StringUtils.isNotBlank(loadFeedbackVo.getFeedbackTime())) {
				throw new RestException(ErrorCode.ERROR_900003.getCode(), " 反馈时间 " + ErrorCode.ERROR_900003.getDesc());
			}

			if (ValidateUtils.isEmpty(loadFeedbackVo.getImageIdList())) {
				throw new RestException(ErrorCode.ERROR_900016);
			}

			if (loadFeedbackVo.getImageIdList().size() > 5) {
				throw new RestException(ErrorCode.ERROR_900015);
			}

			if (!StringUtils.isNotBlank(loadFeedbackVo.getItemName())) {
				throw new RestException(ErrorCode.ERROR_900003.getCode(), " 反馈时间 " + ErrorCode.ERROR_900003.getDesc());
			}

		}

	}

}
