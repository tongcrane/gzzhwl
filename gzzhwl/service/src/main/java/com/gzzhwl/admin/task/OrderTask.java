package com.gzzhwl.admin.task;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.order.service.OrderService;
import com.gzzhwl.admin.order.vo.OrderBaseInfoVO;
import com.gzzhwl.admin.order.vo.OrderLineInfoVO;
import com.gzzhwl.admin.order.vo.OrderReceiverInfoVO;
import com.gzzhwl.admin.order.vo.OrderSenderInfoVO;
import com.gzzhwl.admin.order.vo.OrderVO;
import com.gzzhwl.core.data.dao.StaffInfoDao;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.utils.DateUtils;

@Service("orderTask")
public class OrderTask {
	private static Logger logger = LoggerFactory.getLogger(OrderTask.class);

	@Autowired
	private OrderService orderService;

	@Value("${superadmin.staffid}")
	private String staffId;
	
	@Autowired
	private StaffInfoDao staffInfoDao;

	public void autoCreate() {
		if (logger.isDebugEnabled()) {
			logger.debug("start autoCreate order task");
		}
		
		try {
			
			OrderVO orderInfo = new OrderVO();
			
			OrderBaseInfoVO baseInfo = new OrderBaseInfoVO();
			OrderSenderInfoVO sender = new OrderSenderInfoVO();
			OrderReceiverInfoVO receiver = new OrderReceiverInfoVO();
			OrderLineInfoVO lineInfo = new OrderLineInfoVO();
			
			baseInfo.setAgreementId("76fcb93d-8399-4f69-8808-37f458d47e16");
			baseInfo.setAddrId("82604558-77fb-11e6-b0a3-000c29ce1409");
			baseInfo.setChargeId("9a6ea2dc-47d6-11e6-b2f0-000c29ce1409");
			sender.setCustomerId("52ccb9ff-cf9e-49b0-8b56-70a97b9bdf46");
			baseInfo.setLineAttribute("闭合");
			baseInfo.setNeedOwnVehicles("否");
			baseInfo.setOrderType("整车");
			baseInfo.setNeedType("封闭式货车");
			baseInfo.setNeedLength("18.5");
			baseInfo.setCustomerBillNo("单号000001");
			baseInfo.setNeedImportedVehicles("否");
			baseInfo.setGoodsName("USB接口电灯");
			baseInfo.setMiles("99998.0");
			baseInfo.setNeedStartTime(DateUtils.toString(DateUtils.add(Calendar.DATE, new Date(), 2), "yyyy-MM-dd HH:mm"));
			baseInfo.setPaymentType("现付");
			baseInfo.setGoodsWeight("99999.0");
			baseInfo.setNeedArriveTime(DateUtils.toString(DateUtils.add(Calendar.DATE, new Date(), 8), "yyyy-MM-dd HH:mm"));
			baseInfo.setPickUpTime(DateUtils.toString(DateUtils.add(Calendar.DATE, new Date(), 1), "yyyy-MM-dd HH:mm"));
			baseInfo.setUnitPrice("123.00");
			baseInfo.setIsPredict("02");
			baseInfo.setGoodsVolume("999.0");
			baseInfo.setOrderAdvice("身份证");
			baseInfo.setRemark("自动建单数据模板");
			
			lineInfo.setStartCodeP("150000");
			lineInfo.setStartCodeC("150100");
			lineInfo.setEndCodeP("230000");
			lineInfo.setEndCodeC("230100");
			
			receiver.setCustomerName("东方日升有限公司");
			receiver.setMobile("18674983214");
			receiver.setContactName("王云飞");
			receiver.setTelphone("0469-87654433");
			receiver.setAreaCode("230108");
			receiver.setAddress("收货路1234号");
			receiver.setLongitude("126.534967");
			receiver.setLatitude("45.803775");
			
			sender.setCustomerName("金城集团");
			sender.setMobile("13966751909");
			sender.setContactName("张大千");
			sender.setTelphone("0482-76548811-1225");
			
			orderInfo.setBaseInfo(baseInfo);
			orderInfo.setLineInfo(lineInfo);
			orderInfo.setReceiver(receiver);
			orderInfo.setSender(sender);
			
			StaffInfo staffInfo = staffInfoDao.get(staffId);
			
			orderService.saveOrder(orderInfo, staffInfo.getDepartId(), staffId);
			
		} catch (Exception e) {
			logger.error("自动生成订单发生错误，信息：{}", e.toString());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("finish autoCreate order task");
		}
	}
}