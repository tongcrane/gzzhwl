package com.gzzhwl.eventbus.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.gzzhwl.eventbus.handler.MessageHandler;
import com.gzzhwl.eventbus.handler.ServiceStrategyFactory;
import com.gzzhwl.eventbus.model.EventBusData;
import com.gzzhwl.eventbus.model.Strategy;

@Component
public class EventBusListener {
	@Autowired
	private ServiceStrategyFactory serviceStrategyFactory;

	@Subscribe
	public void onMessage(EventBusData post) {
		Strategy strategy = post.getStrategy();
		MessageHandler handler = serviceStrategyFactory.getMessageHandler(strategy);
		if (handler != null) {
			Object data = post.getData();
			handler.process(data);
		}
	}

}
