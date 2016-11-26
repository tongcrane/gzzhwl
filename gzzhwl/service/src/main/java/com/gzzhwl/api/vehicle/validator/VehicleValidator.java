package com.gzzhwl.api.vehicle.validator;

import java.util.Map;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.data.model.RealVehicleInfo;
import com.gzzhwl.core.data.model.RealVehiclePlusInfo;
import com.gzzhwl.core.data.model.VehicleInfo;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.utils.DateUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class VehicleValidator {
	
	public static void tmp_vehicle_validator(VehicleInfo vehicleInfo, VehiclePlusInfo vehicleInfoPlusInfo){
		
		if(ValidateUtils.isEmpty(vehicleInfo.getPlateNumber())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"车牌号"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		//车牌号必须为大写
		vehicleInfo.setPlateNumber(vehicleInfo.getPlateNumber().toUpperCase());
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseImageRefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"行驶证照片ID"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseImage2RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"行驶证2照片ID"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImageRefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"营运证照片ID"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImage2RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"营运证2照片ID"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImage3RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"营运证3照片ID"+ ErrorCode.ERROR_900003.getDesc());
		}
		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiImageRefId())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"强制险图片ID"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViImageRefId())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"商业险图片ID"+ ErrorCode.ERROR_900003.getDesc());
//		}
		
//		if(ValidateUtils.isEmpty(vehicleInfo.getType())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"type"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getLength())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"length"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getRegDate())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regDate"+ ErrorCode.ERROR_900003.getDesc());
//		}
		
//		if(ValidateUtils.isEmpty(vehicleInfo.getBrand())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"brand"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getRegCertCode())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regCertCode"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getRegCertCode())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regCertCode"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiEffectDate())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ciEffectDate"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViEffectDate())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"viEffectDate"+ ErrorCode.ERROR_900003.getDesc());
//		}
		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiName())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ciName"+ ErrorCode.ERROR_900003.getDesc());
//		}
		
//		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViName())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"viName"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseValidDate())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"licenseValidDate"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getOperatingCertIssueDate())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"operatingCertIssueDate"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getLoadWeight())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"loadWeight"+ ErrorCode.ERROR_900003.getDesc());
//		}
//		
//		if(ValidateUtils.isEmpty(vehicleInfo.getVehicleImageRefId())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(),"vehicleImageRefId"+ ErrorCode.ERROR_900003.getDesc());
//		}
		
//		if(vehicleInfo.getBrand().length()>10){
//			throw new RestException(ErrorCode.ERROR_800011.getCode(), ErrorCode.ERROR_800011.getDesc());
//		}
//		
//		if(ValidateUtils.isAllNumber(vehicleInfo.getRegCertCode())&&ValidateUtils.length(vehicleInfo.getRegCertCode())>12){
//			throw new RestException(ErrorCode.ERROR_800012.getCode(), ErrorCode.ERROR_800012.getDesc());
//		}
//		
//		if(vehicleInfo.getLoadWeight().length()>100000){
//			throw new RestException(ErrorCode.ERROR_800013.getCode(), ErrorCode.ERROR_800013.getDesc());
//		}
//		
//		if(!DateUtils.isDefaultValidDate(vehicleInfo.getRegDate())){
//			throw new RestException(ErrorCode.ERROR_900005.getCode(), "regDate"+ErrorCode.ERROR_900005.getDesc());
//		}
//		
//		if(!DateUtils.isDefaultValidDate(vehicleInfo.getLicenseValidDate())){
//			throw new RestException(ErrorCode.ERROR_900005.getCode(), "licenseValidDate"+ErrorCode.ERROR_900005.getDesc());
//		}
//
//		if(!DateUtils.isDefaultValidDate(vehicleInfo.getOperatingCertIssueDate())){
//			throw new RestException(ErrorCode.ERROR_900005.getCode(), "operatingCertIssueDate"+ErrorCode.ERROR_900005.getDesc());
//		}
//		
//		if(!ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiEffectDate())){
//			if(!DateUtils.isDefaultValidDate(vehicleInfoPlusInfo.getCiEffectDate())){
//				throw new RestException(ErrorCode.ERROR_900005.getCode(), "ciEffectDate"+ErrorCode.ERROR_900005.getDesc());
//			}
//		}
//		
//		if(!ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViEffectDate())){
//			if(!DateUtils.isDefaultValidDate(vehicleInfoPlusInfo.getViEffectDate())){
//				throw new RestException(ErrorCode.ERROR_900005.getCode(), "viEffectDate"+ErrorCode.ERROR_900005.getDesc());
//			}
//		}
//		
//		if(!ValidateUtils.isEmpty(vehicleInfo.getOperatingCertValidDate())){
//			if(!DateUtils.isDefaultValidDate(vehicleInfo.getOperatingCertValidDate())){
//				throw new RestException(ErrorCode.ERROR_900005.getCode(), "operatingCertValidDate"+ErrorCode.ERROR_900005.getDesc());
//			}
//		}

	}
	
	public static void vehicle_validator(RealVehicleInfo vehicleInfo, RealVehiclePlusInfo vehicleInfoPlusInfo){
		
		if(ValidateUtils.isEmpty(vehicleInfo.getPlateNumber())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"plateNumber"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getType())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"type"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLength())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"length"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getRegDate())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regDate"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseImageRefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"licenseImageRefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseImage2RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"licenseImage2RefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImageRefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ocImageRefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImage2RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ocImage2RefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOcImage3RefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ocImage3RefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getBrand())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"brand"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getRegCertCode())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regCertCode"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getRegCertCode())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"regCertCode"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiEffectDate())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ciEffectDate"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViEffectDate())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"viEffectDate"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"ciName"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"viName"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLicenseValidDate())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"licenseValidDate"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getOperatingCertIssueDate())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"operatingCertIssueDate"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getLoadWeight())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"loadWeight"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		if(ValidateUtils.isEmpty(vehicleInfo.getVehicleImageRefId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(),"vehicleImageRefId"+ ErrorCode.ERROR_900003.getDesc());
		}
		
		//车牌号必须为大写
		vehicleInfo.setPlateNumber(vehicleInfo.getPlateNumber().toUpperCase());
		
		if(vehicleInfo.getBrand().length()>10){
			throw new RestException(ErrorCode.ERROR_800011.getCode(), ErrorCode.ERROR_800011.getDesc());
		}
		
		if(ValidateUtils.isAllNumber(vehicleInfo.getRegCertCode())&&ValidateUtils.length(vehicleInfo.getRegCertCode())>12){
			throw new RestException(ErrorCode.ERROR_800012.getCode(), ErrorCode.ERROR_800012.getDesc());
		}
		
		if(vehicleInfo.getLoadWeight().length()>100000){
			throw new RestException(ErrorCode.ERROR_800013.getCode(), ErrorCode.ERROR_800013.getDesc());
		}
		
		if(!DateUtils.isDefaultValidDate(vehicleInfo.getRegDate())){
			throw new RestException(ErrorCode.ERROR_900005.getCode(), "regDate"+ErrorCode.ERROR_900005.getDesc());
		}
		
		if(!DateUtils.isDefaultValidDate(vehicleInfo.getLicenseValidDate())){
			throw new RestException(ErrorCode.ERROR_900005.getCode(), "licenseValidDate"+ErrorCode.ERROR_900005.getDesc());
		}

		if(!DateUtils.isDefaultValidDate(vehicleInfo.getOperatingCertIssueDate())){
			throw new RestException(ErrorCode.ERROR_900005.getCode(), "operatingCertIssueDate"+ErrorCode.ERROR_900005.getDesc());
		}
		
		if(!ValidateUtils.isEmpty(vehicleInfoPlusInfo.getCiEffectDate())){
			if(!DateUtils.isDefaultValidDate(vehicleInfoPlusInfo.getCiEffectDate())){
				throw new RestException(ErrorCode.ERROR_900005.getCode(), "ciEffectDate"+ErrorCode.ERROR_900005.getDesc());
			}
		}
		
		if(!ValidateUtils.isEmpty(vehicleInfoPlusInfo.getViEffectDate())){
			if(!DateUtils.isDefaultValidDate(vehicleInfoPlusInfo.getViEffectDate())){
				throw new RestException(ErrorCode.ERROR_900005.getCode(), "viEffectDate"+ErrorCode.ERROR_900005.getDesc());
			}
		}
		
		if(!ValidateUtils.isEmpty(vehicleInfo.getOperatingCertValidDate())){
			if(!DateUtils.isDefaultValidDate(vehicleInfo.getOperatingCertValidDate())){
				throw new RestException(ErrorCode.ERROR_900005.getCode(), "operatingCertValidDate"+ErrorCode.ERROR_900005.getDesc());
			}
		}

	}
	
	public static void valid_exist(Map<String, Object> vehicleMap) {
		if (ValidateUtils.isEmpty(vehicleMap)) {
			throw new RestException(ErrorCode.ERROR_800005);
		}
	}
	
	public static void valid_exist(boolean exist) {
		if (!exist) {
			throw new RestException(ErrorCode.ERROR_800005);
		}
	}
	
	public static void valid_vehicle_id(String vehicleInfoId) {
		if (ValidateUtils.isEmpty(vehicleInfoId)) {
			throw new RestException(ErrorCode.ERROR_800001);
		}
	}
	
}
