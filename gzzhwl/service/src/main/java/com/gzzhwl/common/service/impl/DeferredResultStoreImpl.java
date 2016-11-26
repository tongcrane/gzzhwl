package com.gzzhwl.common.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.FastHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.gzzhwl.common.service.DeferredResultStore;

@Component
public class DeferredResultStoreImpl<T> implements DeferredResultStore<T> {
	private FastHashMap store;

	@Override
	public int getSize() {
		return store.size();
	}

	@Override
	public void empty() {
		this.store = new FastHashMap();
	}

	@Override
	@PostConstruct
	public void initAndStart() {
		this.store = new FastHashMap();
		this.store.setFast(true);
	}

	@Override
	public void cleanAndShutdown() {
		store.clear();
	}

	@Override
	public boolean has(String tokenId) {
		return store.containsKey(tokenId);
	}

	@Override
	public boolean remove(String tokenId) {
		if (store.get(tokenId) != null) {
			store.remove(tokenId);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DeferredResult<T> get(String tokenId) {
		Object result = store.get(tokenId);
		return result != null ? (DeferredResult<T>) result : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DeferredResult<T> getAndRemove(String tokenId) {
		Object result = store.remove(tokenId);
		return result != null ? (DeferredResult<T>) result : null;
	}

	@Override
	public void store(String tokenId, DeferredResult<T> result) {
		store.put(tokenId, result);
	}

}
