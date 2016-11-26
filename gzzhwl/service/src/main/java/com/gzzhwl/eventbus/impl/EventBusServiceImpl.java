package com.gzzhwl.eventbus.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.gzzhwl.eventbus.EventBusService;
import com.gzzhwl.eventbus.listener.EventBusListener;
import com.gzzhwl.eventbus.model.EventBusData;

@Component
public class EventBusServiceImpl implements EventBusService, InitializingBean {
	@Autowired
	private EventBusListener listener;
	@Autowired
	private ThreadPoolTaskExecutor executor;

	private EventBus eventBus;

	private AsyncEventBus asyncEventBus;

	@Override
	public void post(EventBusData resultData) {
		eventBus.post(resultData);
	}

	@Override
	public void asyncPost(EventBusData resultData) {
		asyncEventBus.post(resultData);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		eventBus = new EventBus();
		eventBus.register(listener);
		asyncEventBus = new AsyncEventBus(executor);
		asyncEventBus.register(listener);
	}
}
