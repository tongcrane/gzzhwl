package com.gzzhwl.api.image.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.FastHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gzzhwl.api.exception.CaptchaServiceException;
import com.gzzhwl.api.image.model.CaptchaModel;
import com.gzzhwl.api.image.service.CaptchaStore;

@Component
public class FastMapCatpchaStore implements CaptchaStore {
	private static Logger logger = LoggerFactory.getLogger(CaptchaStore.class);
	private static final long TIME_OUT = 2 * 60 * 1000;// 验证码有效期2分钟

	private Map<String, CaptchaModel> store;

	@SuppressWarnings("unchecked")
	public FastMapCatpchaStore() {
		this.store = new FastHashMap();
	}

	@Override
	public boolean hasCaptcha(String id) {
		return store.containsKey(id);
	}

	@Override
	public void storeCaptcha(CaptchaModel captcha) throws CaptchaServiceException {
		store.put(captcha.getId(), captcha);
	}

	@Override
	public boolean removeCaptcha(String id) {
		if (store.get(id) != null) {
			store.remove(id);
			return true;
		}
		return false;
	}

	@Override
	public CaptchaModel getCaptcha(String id) throws CaptchaServiceException {
		Object captcha = store.get(id);
		return captcha != null ? (CaptchaModel) captcha : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void empty() {
		this.store = new FastHashMap();
	}

	@Override
	public void cleanAndShutdown() {
		store.clear();
	}

	@Override
	public Collection<CaptchaModel> values() {
		return store.values();
	}

	@Override
	public void clear() {
		long current = Calendar.getInstance().getTimeInMillis();
		Collection<CaptchaModel> allCaptcha = store.values();
		Iterator<CaptchaModel> it = allCaptcha.iterator();
		while (it.hasNext()) {
			CaptchaModel cm = it.next();
			long diff = current - cm.getTimestamp();
			if (diff > TIME_OUT) {
				logger.debug("clear token:" + cm.getId());
				it.remove();
			}
		}
	}

}
