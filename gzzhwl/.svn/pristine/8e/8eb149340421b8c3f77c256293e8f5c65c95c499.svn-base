package com.gzzhwl.push.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.PushInfoDao;
import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.data.model.PushInfo;
import com.gzzhwl.core.support.PushDeviceSupport;
import com.gzzhwl.push.exception.PushException;
import com.gzzhwl.push.service.PushNotificationService;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {
	@Autowired
	private PushInfoDao pushInfoDao;

	@Override
	public PushInfo addPushInfo(String channel, String deviceType, String token, String accountId)
			throws PushException {
		PushDevice pushDevice = PushDeviceSupport.getDevice(DeviceType.getDeviceType(deviceType), channel);
		if (pushDevice != null) {
			this.delInfoByToken(token);
			PushInfo pushInfo = getLastestPushInfo(accountId);
			if (pushInfo == null) {
				pushInfo = new PushInfo();
				pushInfo.setAccountId(accountId);
				pushInfo.setDeviceId(pushDevice.getDeviceId());
				pushInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
				pushInfo.setStatus(Global.STATUS_NORMAL.toString());
				pushInfo.setToken(token);
				pushInfoDao.insert(pushInfo);
				return pushInfo;
			} else {
				pushInfo.setToken(token);
				pushInfo.setDeviceId(pushDevice.getDeviceId());
				pushInfoDao.updateSelective(pushInfo);
				return pushInfo;
			}
		}
		throw new PushException(deviceType + " no this channel");
	}

	// 删除该token的所有记录
	private boolean delInfoByToken(String token) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		int row = pushInfoDao.delInfoByToken(params);
		return row > 0;
	}

	@Override
	public PushInfo getLastestPushInfo(String accountId) throws PushException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		List<PushInfo> allPushInfo = pushInfoDao.find(params);
		if (CollectionUtils.isNotEmpty(allPushInfo)) {
			return allPushInfo.get(0);
		}
		return null;
	}

	@Override
	public boolean delPushInfo(String accountId) throws PushException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		int row = pushInfoDao.delInfoByToken(params);
		return row > 0;
	}

}
