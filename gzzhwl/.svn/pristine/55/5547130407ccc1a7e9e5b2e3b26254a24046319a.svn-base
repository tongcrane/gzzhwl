package com.gzzhwl.common.service;

import org.springframework.web.context.request.async.DeferredResult;

public interface DeferredResultStore<T> {
	boolean has(String tokenId);

	void store(String tokenId, DeferredResult<T> result);

	boolean remove(String tokenId);

	DeferredResult<T> get(String tokenId);

	DeferredResult<T> getAndRemove(String tokenId);

	int getSize();

	void empty();

	void initAndStart();

	void cleanAndShutdown();
}
