package com.gzzhwl.admin.order.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.order.vo.OrderVO;
import com.gzzhwl.admin.order.vo.PushOrderVO;
import com.gzzhwl.rest.exception.RestException;

public interface OrderService {

	public String saveOrder(OrderVO orderInfo, Integer departId, String staffId) throws RestException;

	public String modifyOrder(OrderVO orderInfo, Integer departId, String staffId) throws RestException;

	public boolean cancelOrder(String orderId, String staffId) throws RestException;

	public boolean cancelOrderBatch(String[] orderArr, String staffId);

	public boolean pushOrder(PushOrderVO orderInfo, String staffId) throws RestException;

	public boolean closeOrder(String orderId, String staffId) throws RestException;

	public List<Map<String, Object>> whatToDo(String orderId) throws RestException;

	public boolean toLoad(String orderId, Integer departId, String staffId) throws RestException;

	public boolean doLoadBatch(String[] orderArr, Integer departId, String staffId) throws RestException;

}
