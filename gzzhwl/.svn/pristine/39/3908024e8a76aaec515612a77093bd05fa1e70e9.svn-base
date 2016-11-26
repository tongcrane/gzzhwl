package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_driver_contract_info表
 * @author mew
 *
 */
@Data
@ToString
public class DriverContractInfo implements Serializable {

	@Length(max = 36, message = "contractId超过长度限制")
	private java.lang.String contractId; // 合同标识

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识
}