package com.gzzhwl.push.service;

import com.gzzhwl.core.data.model.PushInfo;
import com.gzzhwl.push.exception.PushException;

public interface PushNotificationService {
	/**
	 * 登记用户的推送设备
	 */
	public PushInfo addPushInfo(String channel, String deviceType, String token, String accountId) throws PushException;

	/**
	 * 获取用户的最新可用推送登记信息
	 */
	public PushInfo getLastestPushInfo(String accountId) throws PushException;

	/**
	 * 注销用户的推送设备
	 */
	public boolean delPushInfo(String accountId) throws PushException;
}
