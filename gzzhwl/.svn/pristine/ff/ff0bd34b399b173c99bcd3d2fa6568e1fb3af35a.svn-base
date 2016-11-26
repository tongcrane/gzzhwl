package com.gzzhwl.template.callable.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.template.annotations.RowParse;
import com.gzzhwl.template.callable.RowParseCallable;
import com.gzzhwl.template.callable.RowParseStrategyFactory;
import com.gzzhwl.template.exception.TooManyBeanFoundException;

@Service
public class RowParseStrategyFactoryImpl implements RowParseStrategyFactory, ApplicationContextAware {
	private Map<String, RowParseCallable> handlerStrategyMap = Maps.newHashMap();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Collection<RowParseCallable> allCallable = applicationContext.getBeansOfType(RowParseCallable.class).values();
		for (RowParseCallable callable : allCallable) {
			RowParse strategy = callable.getClass().getAnnotation(RowParse.class);
			if (strategy != null) {
				String key = strategy.value();
				boolean exists = handlerStrategyMap.containsKey(key);
				if (!exists) {
					handlerStrategyMap.put(strategy.value(), callable);
				} else {
					throw new TooManyBeanFoundException(key);
				}
			}
		}
	}

	@Override
	public RowParseCallable getRowParse(String strategy) {
		if (strategy == null) {
			return null;
		}
		if (handlerStrategyMap.containsKey(strategy)) {
			return handlerStrategyMap.get(strategy);
		}
		return null;
	}
}
