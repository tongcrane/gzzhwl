package com.gzzhwl.eventbus.handler.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.eventbus.annotation.EventStrategy;
import com.gzzhwl.eventbus.handler.MessageHandler;
import com.gzzhwl.eventbus.handler.ServiceStrategyFactory;
import com.gzzhwl.eventbus.model.Strategy;

@Service
public class ServiceStrategyFactoryImpl implements ServiceStrategyFactory, ApplicationContextAware {
	private Map<Strategy, MessageHandler> handlerStrategyMap = Maps.newHashMap();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Collection<MessageHandler> allHandler = applicationContext.getBeansOfType(MessageHandler.class).values();
		for (MessageHandler handler : allHandler) {
			EventStrategy strategy = handler.getClass().getAnnotation(EventStrategy.class);
			if (strategy != null) {
				handlerStrategyMap.put(strategy.value(), handler);
			}
		}
	}

	@Override
	public MessageHandler getMessageHandler(Strategy strategy) {
		if (strategy == null) {
			return null;
		}
		if (handlerStrategyMap.containsKey(strategy)) {
			return handlerStrategyMap.get(strategy);
		}
		return null;
	}
}
