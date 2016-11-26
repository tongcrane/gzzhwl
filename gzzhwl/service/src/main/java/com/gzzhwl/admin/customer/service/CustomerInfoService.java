package com.gzzhwl.admin.customer.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.customer.vo.CustomerInfoVo;
import com.gzzhwl.admin.customer.vo.CustomerQueryVo;
import com.gzzhwl.core.page.Page;

public interface CustomerInfoService {
	//保存客户及客户账户信息
	public String saveCust(CustomerInfoVo customerInfoVo,String staffId,String departId);
	
	//删除客户信息
	public boolean removeCustomer(String custId,String staffId);
	
	//修改客户及客户账户信息
	public boolean updateCustomer(CustomerInfoVo customerInfoVo,String staffId);
	
	//查询客户分页信息
	public Page<Map<String,Object>> pageCustList(CustomerQueryVo queryVo,int currentPage,int pageSize);
	
	//查询客户及客户账户详细信息
	public CustomerInfoVo queryCustDetail(String custId);

	//获取客户列表
	public List<Map<String, Object>> queryCustList();
	
	//生成客户编号
	//public String createCustno();
}
