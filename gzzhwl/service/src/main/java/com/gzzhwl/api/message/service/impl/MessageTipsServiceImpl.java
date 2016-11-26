package com.gzzhwl.api.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.api.message.validate.MessageValidator;
import com.gzzhwl.api.utils.CardIdConvert;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.MessageTipsDao;
import com.gzzhwl.core.data.model.MessageTips;
import com.gzzhwl.core.message.TipsCategory;
import com.gzzhwl.core.message.TipsSourceType;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.PageParameter;

@Service
public class MessageTipsServiceImpl implements MessageTipsService {

	@Autowired
	private MessageTipsDao messageTipsDao;

	@Override
	public String addMessage(String category, String accountId, String sourceId, String sourceType, String content,
			String createdUser) throws RestException {
		MessageTips message = new MessageTips();
		String messageId = IdentifierUtils.getId().generate().toString();
		message.setMessageId(messageId);
		message.setCategory(category);
		message.setAccountId(accountId);
		message.setSourceId(sourceId);
		message.setSourceType(sourceType);
		message.setContent(content);
		message.setCreatedBy(createdUser);
		message.setUpdatedBy(createdUser);
		message.setStatus(Global.STATUS_NORMAL.toString());
		message.setIsRead(MessageTips.IS_READ_FALSE);
		message.setIsDeleted(Global.ISDEL_NORMAL.toString());
		messageTipsDao.insert(message);
		return messageId;
	}

	@Override
	public void delMessage(String messageId, String createdUser) throws RestException {
		MessageTips message = messageTipsDao.get(messageId);
		MessageValidator.validateDelMessage(message, createdUser);
		message.setIsDeleted(Global.ISDEL_DELETE.toString());
		message.setUpdatedBy(createdUser);
		messageTipsDao.updateSelective(message);
	}

	@Override
	public Page<Map<String, Object>> updateAndPageMessageList(String accountId, String category, PageParameter page)
			throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		if (StringUtils.isNotBlank(category)) {
			params.put("category", category);
		}
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("quotedType", TipsSourceType.TIPS_S01.getCode());
		params.put("loadType", TipsSourceType.TIPS_S02.getCode());
		params.put("loadSource", DataSource.VLORRY.getCode());
		Page<Map<String, Object>> result = messageTipsDao.page(params, page.getPageIndex(), page.getPageSize());
		List<Map<String, Object>> messageIdList = result.getRows();
		if (CollectionUtils.isNotEmpty(messageIdList)) {
			for (Map<String, Object> map : messageIdList) {
				String _category = map.get("category").toString();
				map.put("categoryCn", TipsCategory.getCategoryDesc(_category));
			}
			String[] idArray = CardIdConvert.getColumnData(messageIdList, "messageId");
			// 更新已读状态
			this.updateMessageReadStatus(idArray);
		}
		return result;
	}

	@Override
	public void updateMessageReadStatus(String[] messageIds) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("messageIds", messageIds);
		params.put("isRead", MessageTips.IS_READ_TRUE);
		messageTipsDao.updateMessageStatus(params);
	}

	@Override
	public Integer getUnreadMessageCount(String accountId) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("isRead", MessageTips.IS_READ_FALSE);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		int count = messageTipsDao.getUnreadMessageCount(params);
		return count;
	}

}
