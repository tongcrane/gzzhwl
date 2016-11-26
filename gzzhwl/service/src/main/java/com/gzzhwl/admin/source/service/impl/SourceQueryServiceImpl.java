package com.gzzhwl.admin.source.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.quoted.service.QuotedManageService;
import com.gzzhwl.admin.source.controller.SourceValidate;
import com.gzzhwl.admin.source.service.SourceQueryService;
import com.gzzhwl.admin.source.vo.SourceCommVo;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.ChargeInfoDao;
import com.gzzhwl.core.data.dao.CustomerInfoDao;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.data.dao.OrderDetailInfoDao;
import com.gzzhwl.core.data.dao.OrderLineInfoDao;
import com.gzzhwl.core.data.dao.OrderReceiverInfoDao;
import com.gzzhwl.core.data.dao.OrderSenderInfoDao;
import com.gzzhwl.core.data.dao.OrderSourceInfoDao;
import com.gzzhwl.core.data.dao.SourceBaseInfoDao;
import com.gzzhwl.core.data.extdao.OrderSourceInfoExtDao;
import com.gzzhwl.core.data.model.ChargeInfo;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderLineInfo;
import com.gzzhwl.core.data.model.OrderReceiverInfo;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.data.model.OrderSourceInfo;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.data.model.SourceBaseInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class SourceQueryServiceImpl implements SourceQueryService {
	@Autowired
	private FlowsService flowService;
	@Autowired
	private OrderDetailInfoDao detailInfoDao;
	@Autowired
	private OrderBaseInfoDao baseInfoDao;
	@Autowired
	private OrderLineInfoDao lineInfoDao;
	@Autowired
	private OrderSourceInfoDao orderSourceInfoDao;
	@Autowired
	private QuotedManageService quotedManageService;
	@Autowired
	private OrderSenderInfoDao senderDao;
	@Autowired
	private OrderReceiverInfoDao receiverDao;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private SourceBaseInfoDao sourceBaseDao;
	@Autowired
	private ChargeInfoDao chargeDao;
	@Autowired
	private OrderSourceInfoExtDao orderSourceInfoExtDao;
	@Autowired
	private CustomerInfoDao custDao;
	@Autowired
	private RegionService reginService;
	// 货源推送管理
	private final String SOURCE_PUSH = "01";
	// 货源列表管理
	private final String SOURCE_LIST = "02";
	// 竞价管理
	private final String SOURCE_QUOTED = "03";

	@Override
	public Map<String, Object> querySourceDetail(String sourceId) {
		Map<String, Object> data = new HashMap<String, Object>();

		// 根据sourceId查询货源信息
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		SourceValidate.valid_source_exist(!ValidateUtils.isEmpty(orderSourceInfo));
		data.put("sourceInfo", orderSourceInfo);

		String sourceInfoId = orderSourceInfo.getInfoId();
		// 根据货源基本信息查询订单ID
		SourceBaseInfo sourceBaeInfo = sourceBaseDao.get(sourceInfoId);

		String orderId = sourceBaeInfo.getOrderId();
		// 查询订单信息
		OrderDetailInfo detailInfo = detailInfoDao.get(orderId);
		data.put("orderDetailInfo", detailInfo);
		data.put("sourceNo", sourceBaeInfo.getSourceNo());
		FlowStatus statusCn = flowService.getStatus(ZHFlow.SOURCE, orderSourceInfo.getStatus());

		data.put("statusCn", statusCn.getName());

		String infoId = detailInfo.getInfoId();

		// 根据orderId查询订单信息
		// 查询订单基本信息
		OrderBaseInfo baseInfo = baseInfoDao.get(infoId);
		SourceValidate.valid_order_exist(!ValidateUtils.isEmpty(baseInfo));
		data.put("orderBaseInfo", baseInfo);

		// 查询计费方式
		ChargeInfo chargeInfo = chargeDao.get(baseInfo.getChargeId());
		data.put("chargeInfo", chargeInfo);

		// 查询订单发货人信息
		OrderSenderInfo sender = senderDao.get(infoId);
		if (!ValidateUtils.isEmpty(sender)) {
			// 根据发货人ID查询发货人信息
			CustomerInfo cust = custDao.get(sender.getCustomerId());
			if (!ValidateUtils.isEmpty(cust)) {
				sender.setCustomerName(cust.getFullName());
			}
			Map<String, Object> senderMap = new HashMap<String, Object>();
			beanMapper.map(sender, senderMap);
			List<RegionInfo> senderArea = reginService.findRootByCode(sender.getAreaCode());
			senderMap.put("area", senderArea);
			data.put("orderSenderInfo", senderMap);
		} else {
			data.put("orderSenderInfo", null);
		}

		if (orderSourceInfo.getStatus().equals(SourceType.FINISH.getCode())
				|| orderSourceInfo.getStatus().equals(SourceType.HASBID.getCode())) {

			QuotedInfo quoted = quotedManageService.getBidQuoted(sourceId);
			if(quoted!=null){
				data.put("bidTime", quoted.getUpdatedTime());
				data.put("accountId", quoted.getAccountId());
			}
		}

		if (orderSourceInfo.getStatus().equals(SourceType.FINISH.getCode())) {
			data.put("isShowVehicleInfo", true);
		} else {
			data.put("isShowVehicleInfo", false);
		}

		data.put("quotedCount", quotedManageService.getSourceQuotedCount(sourceId));

		// 查询订单收货人信息
		OrderReceiverInfo receiver = receiverDao.get(infoId);
		if (!ValidateUtils.isEmpty(receiver)) {
			Map<String, Object> receiverMap = new HashMap<String, Object>();
			beanMapper.map(receiver, receiverMap);
			List<RegionInfo> area = reginService.findRootByCode(receiver.getAreaCode());
			receiverMap.put("area", area);

			data.put("orderReceiverInfo", receiverMap);
		} else {
			data.put("orderReceiverInfo", null);
		}

		// 查询订单路线信息
		OrderLineInfo lineInfo = lineInfoDao.get(infoId);
		if (!ValidateUtils.isEmpty(lineInfo)) {
			Map<String, Object> lineMap = new HashMap<String, Object>();
			beanMapper.map(lineInfo, lineMap);

			String startCodePCn = this.getCodeCn(lineInfo.getStartCodeP());
			lineMap.put("StartCodePCn", startCodePCn);

			String startCodeCCn = this.getCodeCn(lineInfo.getStartCodeC());
			lineMap.put("StartCodeCCn", startCodeCCn);

			String transferCodePCn = this.getCodeCn(lineInfo.getTransferCodeP());
			lineMap.put("transferCodePCn", transferCodePCn);

			String transferCodeCCn = this.getCodeCn(lineInfo.getTransferCodeC());
			lineMap.put("transferCodeCCn", transferCodeCCn);

			String endCodePCn = this.getCodeCn(lineInfo.getEndCodeP());
			lineMap.put("endCodePCn", endCodePCn);

			String endCodeCCn = this.getCodeCn(lineInfo.getEndCodeC());
			lineMap.put("endCodeCCn", endCodeCCn);
			data.put("orderLineInfo", lineMap);
		} else {
			data.put("orderLineInfo", null);
		}

		return data;
	}

	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = reginService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Page<Map<String, Object>> queryOrderSourcePage(SourceCommVo sourceCommVo, int pageIndex, int pageSize) {

		Map<String, Object> params = beanMapper.map(sourceCommVo, Map.class);

		String[] strArray = null;

		if (sourceCommVo.getSourceType().equals(SOURCE_PUSH)) {
			strArray = new String[] { SourceType.TODO.getCode() };
		} else if (sourceCommVo.getSourceType().equals(SOURCE_LIST)) {
			strArray = new String[] { SourceType.PUBLISH.getCode(), SourceType.CLOSE.getCode(),
					SourceType.END.getCode(), SourceType.HASBID.getCode(), SourceType.FINISH.getCode(),
					SourceType.INVALID.getCode() };
		} else if (sourceCommVo.getSourceType().equals(SOURCE_QUOTED)) {
			strArray = new String[] { SourceType.PUBLISH.getCode(), SourceType.HASBID.getCode(),
					SourceType.FINISH.getCode() };
			params.put("isStart", true);
		} else {
			throw new RestException(ErrorCode.ERROR_900006);
		}

		params.put("sourceStatusList", strArray);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		Page<Map<String, Object>> sourcePage = orderSourceInfoExtDao.getOrderSourcePage(params, pageIndex, pageSize);

		// 更新省市区描述 状态描述
		CollectionUtils.transform(sourcePage.getRows(), new Transformer<Map<String, Object>, Map<String, Object>>() {
			@Override
			public Map<String, Object> transform(Map<String, Object> input) {
				String startCodeP = (String) input.get("startCodeP");
				String startCodePCn = this.getCodeCn(startCodeP);
				input.put("startCodePCn", startCodePCn);

				String endCodeP = (String) input.get("endCodeP");
				String endCodePCn = this.getCodeCn(endCodeP);
				input.put("endCodePCn", endCodePCn);

				String startCodeC = (String) input.get("startCodeC");

				String startCodeCCn = this.getCodeCn(startCodeC);
				input.put("startCodeCCn", startCodeCCn);

				String endCodeC = (String) input.get("endCodeC");

				String endCodeCCn = this.getCodeCn(endCodeC);
				input.put("endCodeCCn", endCodeCCn);

				String status = (String) input.get("status");
				if (StringUtils.isNotBlank(status)) {
					FlowStatus statusCn = flowService.getStatus(ZHFlow.SOURCE, status);
					input.put("statusCn", statusCn.getName());
				} else {
					input.put("statusCn", StringUtils.EMPTY);
				}

				input.put("actionList", flowService.whatToDo(ZHFlow.SOURCE, status, FlowActionCategory.CBS_YSJ));

				return input;
			}

			private String getCodeCn(String code) {
				if (StringUtils.isNotBlank(code)) {
					RegionInfo startCodePCn = reginService.findByCode(code);
					if (startCodePCn != null) {
						return startCodePCn.getRegionName();
					}
				}
				return "";
			}
		});

		return sourcePage;
	}
}
