package com.gzzhwl.admin.vehicle.vo;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.utils.DateUtils;
import com.gzzhwl.rest.exception.RestException;

/**
 * 车辆查询vo
 * @author jly
 *
 */
public class VehicleInfoQryVo {

	private java.lang.String seqNo; // 车辆编号
	
	private java.lang.String plateNumber; // 车牌号
	
	private java.lang.String attributes; // 车辆属性
	
	private java.lang.String ownerPhone; // 车主电话
	
	private java.lang.String ciEffectDateStrat; // 查询强制险有效期开始时间 >
	
	private java.lang.String ciEffectDateEnd; // 查询强制险有效期结束 <
	
	private java.lang.String ciName; // 强制险公司名称
	
	private java.lang.String viEffectDateStrat; // 商业险有效期开始时间
	
	private java.lang.String viEffectDateEnd; // 商业险有效期结束时间
	
	private java.lang.String viName; // 商业险公司名称
	
	private java.lang.String belongDepartName; // 所属部门名称 like
	
	private java.lang.String useDepartName; // 使用部门名称 like 
	
	private java.lang.String departureCode; // 线路出发地
	
	private java.lang.String destinationCode; // 线路目的地
	
	private java.lang.String useStatus; // 使用状态
	
	private java.lang.String createdTimeStart; // 查询创建时间开始时间 >
	
	private java.lang.String createdTimeEnd; // 查询创建时间结束时间 <
	
	//ADD BY 20160620
	private String queryContent;//全文搜索
	
	private String queryType;//查询类型  全文搜索:0 高级搜索:1
	
	private Integer loadWeightStart;//最低核载重量
	
	private Integer loadWeightEnd;//最高核载重量
	
	private Integer lengthStart;//最小车长
	
	private Integer lengthEnd;//最大车长
	
	private String licenseValidDateStart;//行驶证查询开始时间
	
	private String licenseValidDateEnd;//行驶证查询结束时间

	private String ownerName;//车主
	
	private String queryContentLike;
	
	public void likeHandle(){
		
		try{
			if(StringUtils.isNotBlank(queryContent)){
				queryContentLike = "%"+queryContent+"%";
			}else{
				queryContent = null;
			}
			
			if(StringUtils.isNotBlank(ownerName)){
				ownerName = "%"+ownerName+"%";
			}
			
			if(StringUtils.isNotBlank(belongDepartName)){
				belongDepartName = "%"+belongDepartName+"%";
			}
			
			if(StringUtils.isNotBlank(useDepartName)){
				useDepartName = "%"+useDepartName+"%";
			}
			
			if(StringUtils.isNotBlank(ciEffectDateStrat)){
				ciEffectDateStrat = DateUtils.getStartTime(ciEffectDateStrat);
			}
			
			if(StringUtils.isNotBlank(ciEffectDateEnd)){
				ciEffectDateEnd = DateUtils.getEndTime(ciEffectDateEnd);
			}
			
			if(StringUtils.isNotBlank(viEffectDateStrat)){
				viEffectDateStrat = DateUtils.getStartTime(viEffectDateStrat);
			}
			
			if(StringUtils.isNotBlank(viEffectDateEnd)){
				viEffectDateEnd = DateUtils.getEndTime(viEffectDateEnd);
			}
			
			if(StringUtils.isNotBlank(createdTimeStart)){
				createdTimeStart = DateUtils.getStartTime(createdTimeStart);
			}
			
			if(StringUtils.isNotBlank(createdTimeEnd)){
				createdTimeEnd = DateUtils.getEndTime(createdTimeEnd);
			}
			
		}catch(ParseException parseException){
			throw new RestException(ErrorCode.ERROR_900005.getCode(), "日期"+ErrorCode.ERROR_900005.getDesc());
		}
	}
	
	
	
	public String getQueryContentLike() {
		return queryContentLike;
	}



	public void setQueryContentLike(String queryContentLike) {
		this.queryContentLike = queryContentLike;
	}



	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public java.lang.String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(java.lang.String seqNo) {
		this.seqNo = seqNo;
	}

	public java.lang.String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(java.lang.String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public java.lang.String getAttributes() {
		return attributes;
	}

	public void setAttributes(java.lang.String attributes) {
		this.attributes = attributes;
	}

	public java.lang.String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(java.lang.String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public java.lang.String getCiEffectDateStrat() {
		return ciEffectDateStrat;
	}

	public void setCiEffectDateStrat(java.lang.String ciEffectDateStrat) {
		this.ciEffectDateStrat = ciEffectDateStrat;
	}

	public java.lang.String getCiEffectDateEnd() {
		return ciEffectDateEnd;
	}

	public void setCiEffectDateEnd(java.lang.String ciEffectDateEnd) {
		this.ciEffectDateEnd = ciEffectDateEnd;
	}

	public java.lang.String getCiName() {
		return ciName;
	}

	public void setCiName(java.lang.String ciName) {
		this.ciName = ciName;
	}

	public java.lang.String getViEffectDateStrat() {
		return viEffectDateStrat;
	}

	public void setViEffectDateStrat(java.lang.String viEffectDateStrat) {
		this.viEffectDateStrat = viEffectDateStrat;
	}

	public java.lang.String getViEffectDateEnd() {
		return viEffectDateEnd;
	}

	public void setViEffectDateEnd(java.lang.String viEffectDateEnd) {
		this.viEffectDateEnd = viEffectDateEnd;
	}

	public java.lang.String getViName() {
		return viName;
	}

	public void setViName(java.lang.String viName) {
		this.viName = viName;
	}

	public java.lang.String getBelongDepartName() {
		return belongDepartName;
	}

	public void setBelongDepartName(java.lang.String belongDepartName) {
		this.belongDepartName = belongDepartName;
	}

	public java.lang.String getUseDepartName() {
		return useDepartName;
	}

	public void setUseDepartName(java.lang.String useDepartName) {
		this.useDepartName = useDepartName;
	}

	public java.lang.String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(java.lang.String departureCode) {
		this.departureCode = departureCode;
	}

	public java.lang.String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(java.lang.String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public java.lang.String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(java.lang.String useStatus) {
		this.useStatus = useStatus;
	}

	public java.lang.String getCreatedTimeStart() {
		return createdTimeStart;
	}

	public void setCreatedTimeStart(java.lang.String createdTimeStart) {
		this.createdTimeStart = createdTimeStart;
	}

	public java.lang.String getCreatedTimeEnd() {
		return createdTimeEnd;
	}

	public void setCreatedTimeEnd(java.lang.String createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}

	public String getQueryContent() {
		return queryContent;
	}

	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	public Integer getLoadWeightStart() {
		return loadWeightStart;
	}

	public void setLoadWeightStart(Integer loadWeightStart) {
		this.loadWeightStart = loadWeightStart;
	}

	public Integer getLoadWeightEnd() {
		return loadWeightEnd;
	}

	public void setLoadWeightEnd(Integer loadWeightEnd) {
		this.loadWeightEnd = loadWeightEnd;
	}

	public Integer getLengthStart() {
		return lengthStart;
	}

	public void setLengthStart(Integer lengthStart) {
		this.lengthStart = lengthStart;
	}

	public Integer getLengthEnd() {
		return lengthEnd;
	}

	public void setLengthEnd(Integer lengthEnd) {
		this.lengthEnd = lengthEnd;
	}

	public String getLicenseValidDateStart() {
		return licenseValidDateStart;
	}

	public void setLicenseValidDateStart(String licenseValidDateStart) {
		this.licenseValidDateStart = licenseValidDateStart;
	}

	public String getLicenseValidDateEnd() {
		return licenseValidDateEnd;
	}

	public void setLicenseValidDateEnd(String licenseValidDateEnd) {
		this.licenseValidDateEnd = licenseValidDateEnd;
	}

	@Override
	public String toString() {
		return "VehicleInfoQryVo [seqNo=" + seqNo + ", plateNumber=" + plateNumber + ", attributes=" + attributes
				+ ", ownerPhone=" + ownerPhone + ", ciEffectDateStrat=" + ciEffectDateStrat + ", ciEffectDateEnd="
				+ ciEffectDateEnd + ", ciName=" + ciName + ", viEffectDateStrat=" + viEffectDateStrat
				+ ", viEffectDateEnd=" + viEffectDateEnd + ", viName=" + viName + ", belongDepartName="
				+ belongDepartName + ", useDepartName=" + useDepartName + ", departureCode=" + departureCode
				+ ", destinationCode=" + destinationCode + ", useStatus=" + useStatus + ", createdTimeStart="
				+ createdTimeStart + ", createdTimeEnd=" + createdTimeEnd + ", queryContent=" + queryContent
				+ ", queryType=" + queryType + ", loadWeightStart=" + loadWeightStart + ", loadWeightEnd="
				+ loadWeightEnd + ", lengthStart=" + lengthStart + ", lengthEnd=" + lengthEnd
				+ ", licenseValidDateStart=" + licenseValidDateStart + ", licenseValidDateEnd=" + licenseValidDateEnd
				+ "]";
	}
	
	
	
	
	
	
}
