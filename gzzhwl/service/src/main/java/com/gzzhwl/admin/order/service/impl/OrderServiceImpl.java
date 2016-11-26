
package com.gzzhwl.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.consignment.service.ConsignmentInfoService;
import com.gzzhwl.admin.load.service.LoadLinkStatusService;
import com.gzzhwl.admin.order.service.OrderHistoryService;
import com.gzzhwl.admin.order.service.OrderService;
import com.gzzhwl.admin.order.validate.OrderValidate;
import com.gzzhwl.admin.order.vo.OrderBaseInfoVO;
import com.gzzhwl.admin.order.vo.OrderLineInfoVO;
import com.gzzhwl.admin.order.vo.OrderReceiverInfoVO;
import com.gzzhwl.admin.order.vo.OrderSenderInfoVO;
import com.gzzhwl.admin.order.vo.OrderVO;
import com.gzzhwl.admin.order.vo.PushOrderVO;
import com.gzzhwl.admin.source.service.SourceLinkStatusService;
import com.gzzhwl.admin.store.service.StoreService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.data.dao.AgreementInfoDao;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.data.dao.OrderDetailInfoDao;
import com.gzzhwl.core.data.dao.OrderLineInfoDao;
import com.gzzhwl.core.data.dao.OrderReceiverInfoDao;
import com.gzzhwl.core.data.dao.OrderSenderInfoDao;
import com.gzzhwl.core.data.model.AgreementInfo;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderLineInfo;
import com.gzzhwl.core.data.model.OrderReceiverInfo;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.data.model.StoreAddrInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private FlowsService flowService;
	@Autowired
	private OrderHistoryService orderHistoryService;
	@Autowired
	private SourceLinkStatusService sourceLinkStatusService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private OrderDetailInfoDao detailInfoDao;
	@Autowired
	private OrderBaseInfoDao baseInfoDao;
	@Autowired
	private OrderLineInfoDao lineInfoDao;
	@Autowired
	private OrderReceiverInfoDao receiverInfoDao;
	@Autowired
	private OrderSenderInfoDao senderInfoDao;
	@Autowired
	private AgreementInfoDao agreementDao;
	@Autowired
	private ConsignmentInfoService consignmentInfoService;
	@Autowired
	private LoadLinkStatusService loadLinkStatusService;
	@Autowired
	private StoreService storeService;

	@Override
	public String saveOrder(OrderVO orderInfo, Integer departId, String staffId) throws RestException {
		OrderValidate.valid_order(orderInfo);
		OrderBaseInfoVO baseInfoVO = orderInfo.getBaseInfo();
		OrderReceiverInfoVO receiverVO = orderInfo.getReceiver();
		OrderSenderInfoVO senderVO = orderInfo.getSender();
		OrderLineInfoVO lineInfoVO = orderInfo.getLineInfo();
		OrderBaseInfo baseInfo = beanMapper.map(baseInfoVO, OrderBaseInfo.class);
		OrderReceiverInfo receiver = beanMapper.map(receiverVO, OrderReceiverInfo.class);
		OrderSenderInfo sender = beanMapper.map(senderVO, OrderSenderInfo.class);
		OrderLineInfo lineInfo = beanMapper.map(lineInfoVO, OrderLineInfo.class);
		return this.saveOrder(baseInfo, receiver, sender, lineInfo, departId, staffId);
	}

	@Override
	public String modifyOrder(OrderVO orderInfo, Integer departId, String staffId) throws RestException {
		String actionCode = "02";// 修改动作
		OrderValidate.valid_order(orderInfo);
		String orderId = orderInfo.getBaseInfo().getOrderId();
		String consignId = consignmentInfoService.getCurrentConsign(orderId);
		if (StringUtils.isNotBlank(consignId)) {
			throw new RestException(ErrorCode.ERROR_20300);
		}
		OrderDetailInfo orderDetailInfo = detailInfoDao.get(orderId);
		String currentStatus = orderDetailInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.ORDER, actionCode, currentStatus, FlowActionCategory.CBS);
		orderDetailInfo.setStatus(flowDef.getTransitionStatus());
		String infoId = orderDetailInfo.getInfoId();
		OrderBaseInfoVO baseInfoVO = orderInfo.getBaseInfo();
		OrderReceiverInfoVO receiverVO = orderInfo.getReceiver();
		OrderSenderInfoVO senderVO = orderInfo.getSender();
		OrderLineInfoVO lineInfoVO = orderInfo.getLineInfo();
		OrderBaseInfo baseInfo = beanMapper.map(baseInfoVO, OrderBaseInfo.class);
		OrderReceiverInfo receiver = beanMapper.map(receiverVO, OrderReceiverInfo.class);
		OrderSenderInfo sender = beanMapper.map(senderVO, OrderSenderInfo.class);
		OrderLineInfo lineInfo = beanMapper.map(lineInfoVO, OrderLineInfo.class);
		baseInfo.setUpdatedBy(staffId);
		orderDetailInfo.setUpdatedBy(staffId);
		orderDetailInfo.setGoodsVolume(baseInfo.getGoodsVolume());
		orderDetailInfo.setGoodsWeight(baseInfo.getGoodsWeight());
		baseInfo.setInfoId(infoId);
		receiver.setInfoId(infoId);
		sender.setInfoId(infoId);
		lineInfo.setInfoId(infoId);

		String addrId = baseInfo.getAddrId();
		StoreAddrInfo storeAddrInfo = storeService.getStoreAddr(addrId);
		sender.setAreaCode(storeAddrInfo.getDistrictCode());
		sender.setAddress(storeAddrInfo.getAddress());
		
		String agreementId = baseInfo.getAgreementId();
		if (StringUtils.isNotBlank(agreementId)) {
			AgreementInfo agreementInfo = agreementDao.get(agreementId);

			baseInfo.setOrderType(agreementInfo.getCarryType());
			baseInfo.setChargeId(agreementInfo.getChargeId());
			baseInfo.setUnitPrice(agreementInfo.getUnitPrice());
			baseInfo.setLineAttribute(agreementInfo.getLineAttribute());
			baseInfo.setNeedType(agreementInfo.getNeedType());
			baseInfo.setNeedLength(agreementInfo.getNeedLength());
			baseInfo.setNeedImportedVehicles(agreementInfo.getNeedImportedVehicles());
			baseInfo.setNeedOwnVehicles(agreementInfo.getNeedOwnVehicles());
			baseInfo.setPaymentType(agreementInfo.getPaymentType());

			sender.setCustomerId(agreementInfo.getCustomerId());
			lineInfo.setStartCodeP(agreementInfo.getStartCodeP());
			lineInfo.setStartCodeC(agreementInfo.getStartCodeC());
			lineInfo.setEndCodeP(agreementInfo.getEndCodeP());
			lineInfo.setEndCodeC(agreementInfo.getEndCodeC());
			lineInfo.setTransferCodeP(agreementInfo.getTransferCodeP());
			lineInfo.setTransferCodeC(agreementInfo.getTransferCodeC());

		} else {

		}
		
		
		baseInfoDao.updateSelective(baseInfo);
		detailInfoDao.updateSelective(orderDetailInfo);
		senderInfoDao.updateSelective(sender);
		receiverInfoDao.updateSelective(receiver);
		lineInfoDao.updateSelective(lineInfo);
		orderHistoryService.saveOrderHistory(orderId, currentStatus, flowDef, staffId);
		return orderId;
	}

	@Override
	public boolean pushOrder(PushOrderVO orderInfo, String staffId) throws RestException {
		String actionCode = "07";// 推送动作
		String orderId = orderInfo.getOrderId();
		// String consignId = consignmentInfoService.getCurrentConsign(orderId);
		// if (StringUtils.isNotBlank(consignId)) {
		// throw new RestException(ErrorCode.ERROR_20300);
		// }
		this.executeFlow(orderId, actionCode, staffId);
		sourceLinkStatusService.addSource(orderId, orderInfo, staffId);
		return true;
	}

	@Override
	public boolean cancelOrder(String orderId, String staffId) throws RestException {
		String actionCode = "03";// 取消动作
		String consignId = consignmentInfoService.getCurrentConsign(orderId);
		if (StringUtils.isNotBlank(consignId)) {
			throw new RestException(ErrorCode.ERROR_20300);
		}
		this.executeFlow(orderId, actionCode, staffId);
		return true;
	}

	@Override
	public boolean closeOrder(String orderId, String staffId) throws RestException {
		String actionCode = "08";// 关闭动作
		String consignId = consignmentInfoService.getCurrentConsign(orderId);
		if (StringUtils.isNotBlank(consignId)) {
			throw new RestException(ErrorCode.ERROR_20300);
		}
		this.executeFlow(orderId, actionCode, staffId);
		return true;
	}

	@Override
	public boolean toLoad(String orderId, Integer departId, String staffId) throws RestException {
		String actionCode = "05";// 约车
		this.executeFlow(orderId, actionCode, staffId);
		loadLinkStatusService.createLoad(orderId, departId, staffId);
		return true;
	}

	@Override
	public boolean doLoadBatch(String[] orderArr, Integer departId, String staffId) throws RestException {
		if (ArrayUtils.isNotEmpty(orderArr)) {
			for (String orderId : orderArr) {
				this.toLoad(orderId, departId, staffId);
			}
		}
		return true;
	}

	private OrderDetailInfo executeFlow(String orderId, String actionCode, String staffId) {
		OrderDetailInfo orderDetailInfo = detailInfoDao.get(orderId);
		String currentStatus = orderDetailInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.ORDER, actionCode, currentStatus, FlowActionCategory.CBS);
		orderDetailInfo.setStatus(flowDef.getTransitionStatus());
		detailInfoDao.updateSelective(orderDetailInfo);
		orderHistoryService.saveOrderHistory(orderId, currentStatus, flowDef, staffId);
		return orderDetailInfo;
	}

	@Override
	public boolean cancelOrderBatch(String[] orderArr, String staffId) {
		if (ArrayUtils.isNotEmpty(orderArr)) {
			for (String orderId : orderArr) {
				this.cancelOrder(orderId, staffId);
			}
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> whatToDo(String orderId) throws RestException {
		OrderDetailInfo orderDetailInfo = detailInfoDao.get(orderId);
		String currentStatus = orderDetailInfo.getStatus();
		return flowService.whatToDo(ZHFlow.ORDER, currentStatus, FlowActionCategory.CBS);
	}

	private String saveOrder(OrderBaseInfo baseInfo, OrderReceiverInfo receiver, OrderSenderInfo sender,
			OrderLineInfo lineInfo, Integer departId, String staffId) throws RestException {
		String infoId = IdentifierUtils.getId().generate().toString();
		String agreementId = baseInfo.getAgreementId();
		if (StringUtils.isNotBlank(agreementId)) {
			AgreementInfo agreementInfo = agreementDao.get(agreementId);

			baseInfo.setOrderType(agreementInfo.getCarryType());
			baseInfo.setChargeId(agreementInfo.getChargeId());
			baseInfo.setUnitPrice(agreementInfo.getUnitPrice());
			baseInfo.setLineAttribute(agreementInfo.getLineAttribute());
			baseInfo.setNeedType(agreementInfo.getNeedType());
			baseInfo.setNeedLength(agreementInfo.getNeedLength());
			baseInfo.setNeedImportedVehicles(agreementInfo.getNeedImportedVehicles());
			baseInfo.setNeedOwnVehicles(agreementInfo.getNeedOwnVehicles());
			baseInfo.setPaymentType(agreementInfo.getPaymentType());

			sender.setCustomerId(agreementInfo.getCustomerId());
			lineInfo.setStartCodeP(agreementInfo.getStartCodeP());
			lineInfo.setStartCodeC(agreementInfo.getStartCodeC());
			lineInfo.setEndCodeP(agreementInfo.getEndCodeP());
			lineInfo.setEndCodeC(agreementInfo.getEndCodeC());
			lineInfo.setTransferCodeP(agreementInfo.getTransferCodeP());
			lineInfo.setTransferCodeC(agreementInfo.getTransferCodeC());

		} else {

		}
		baseInfo.setInfoId(infoId);
		if (departId != null) {
			baseInfo.setBelongDepartId(departId + "");
		}
		String addrId = baseInfo.getAddrId();
		StoreAddrInfo storeAddrInfo = storeService.getStoreAddr(addrId);

		baseInfo.setCreatedBy(staffId);
		baseInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		baseInfo.setUpdatedBy(staffId);
		baseInfo.setType(OrderBaseInfo.ORDER_BILL);
		baseInfoDao.insert(baseInfo);
		String orderId = IdentifierUtils.getId().generate().toString();
		String orderNo = IdentifierUtils.getSequence(SeqNoKey.ORDER).generate().toString();
		OrderDetailInfo orderDetailInfo = new OrderDetailInfo();
		orderDetailInfo.setGoodsVolume(baseInfo.getGoodsVolume());
		orderDetailInfo.setGoodsWeight(baseInfo.getGoodsWeight());
		orderDetailInfo.setInfoId(infoId);
		orderDetailInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		orderDetailInfo.setOrderId(orderId);
		orderDetailInfo.setOrderNo(orderNo);
		orderDetailInfo.setCreatedBy(staffId);
		orderDetailInfo.setUpdatedBy(staffId);
		FlowDef flowDef = flowService.startFlow(ZHFlow.ORDER, FlowActionCategory.CBS);
		orderDetailInfo.setStatus(flowDef.getTransitionStatus());
		detailInfoDao.insert(orderDetailInfo);

		lineInfo.setInfoId(infoId);
		receiver.setInfoId(infoId);

		sender.setAreaCode(storeAddrInfo.getDistrictCode());
		sender.setAddress(storeAddrInfo.getAddress());
		sender.setInfoId(infoId);

		// String customerId = sender.getCustomerId();

		senderInfoDao.insert(sender);
		lineInfoDao.insert(lineInfo);
		receiverInfoDao.insert(receiver);

		orderHistoryService.saveOrderHistory(orderId, flowDef.getLinkedStatus(), flowDef, staffId);
		return orderId;
	}

}
