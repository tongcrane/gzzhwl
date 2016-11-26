package com.gzzhwl.push.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gzzhwl.core.data.dao.PushRecordDao;
import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.data.model.PushInfo;
import com.gzzhwl.core.data.model.PushRecord;
import com.gzzhwl.core.support.PushDeviceSupport;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.JacksonJsonMapper;
import com.gzzhwl.push.annotation.PushProcess;
import com.gzzhwl.push.annotation.PushStrategy;
import com.gzzhwl.push.model.PushItem;
import com.gzzhwl.push.service.PushNotificationService;
import com.gzzhwl.push.service.PushService;

@Component
public class PushServiceImpl implements PushService, BeanPostProcessor, ApplicationContextAware {
	private final Map<String, String> cachedMappings = new HashMap<String, String>();
	private ApplicationContext applicationContext = null;

	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private PushRecordDao pushRecordDao;

	@Override
	public boolean pushToSingleUser(PushItem item, String accountId) {
		PushInfo pushInfo = pushNotificationService.getLastestPushInfo(accountId);
		if (pushInfo != null) {
			String deviceId = pushInfo.getDeviceId();
			PushDevice device = PushDeviceSupport.findDeviceById(deviceId);
			String key = device.getChannel() + "_" + device.getType();
			String beanName = this.cachedMappings.get(key);
			PushProcess service = (PushProcess) applicationContext.getBean(beanName);
			if (service != null) {
				boolean success = service.pushToSingleUser(item, pushInfo.getToken());
				PushRecord pushRecord = new PushRecord();
				String recordId = IdentifierUtils.getId().generate().toString();
				pushRecord.setRecordId(recordId);
				pushRecord.setAccountId(accountId);
				pushRecord.setDeviceId(deviceId);
				pushRecord.setToken(pushInfo.getToken());
				pushRecord.setContent(JacksonJsonMapper.objectToJson(item.getParam()));
				pushRecord.setResult(success ? "01" : "02");
				pushRecordDao.insert(pushRecord);
				return success;
			}
		}
		return false;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof PushProcess) {
			if (bean.getClass().isAnnotationPresent(PushStrategy.class)) {
				PushStrategy strategy = bean.getClass().getAnnotation(PushStrategy.class);
				if (strategy != null) {
					cachedMappings.put(strategy.channel() + "_" + strategy.type().getCode(), beanName);
				}
			}
		}
		return bean;
	}
}
