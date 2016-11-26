package com.gzzhwl.core.data.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 
 * zh_region_info表
 * 
 * @author mew
 *
 */
@Data
public class RegionInfo implements Serializable {

	private java.lang.Integer regionId; // 区域标识

	@Length(max = 100, message = "regionCode超过长度限制")
	private java.lang.String regionCode; // 区域编码

	@Length(max = 100, message = "regionName超过长度限制")
	private java.lang.String regionName; // 区域名称

	private java.lang.Integer parentRegionId; // 所属区域

	private java.lang.Integer regionLevel; // 区域级别

	private java.lang.Double regionOrder; // 区域排序

	@Length(max = 100, message = "regionNameEn超过长度限制")
	private java.lang.String regionNameEn; // 区域名称全拼

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(regionId + ",");
		sb.append(regionCode + ",");
		sb.append(regionName + ",");
		sb.append(parentRegionId + ",");
		sb.append(regionLevel + ",");
		sb.append(regionOrder);
		return sb.toString();
	}
}