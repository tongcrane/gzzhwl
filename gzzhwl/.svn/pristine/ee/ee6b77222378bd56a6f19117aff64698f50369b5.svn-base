package com.gzzhwl.rest.springmvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

import com.gzzhwl.rest.exception.AsyncException;

public class DeferredResultInterceptor implements DeferredResultProcessingInterceptor {
	private static Logger logger = LoggerFactory.getLogger(DeferredResultInterceptor.class);

	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult)
			throws Exception {
		long start = System.currentTimeMillis();
		logger.info("visit at {}", start);
	}

	@Override
	public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
	}

	@Override
	public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult)
			throws Exception {
	}

	@Override
	public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
		if (!deferredResult.isSetOrExpired()) {
			deferredResult.setErrorResult(new AsyncException());
		}
		return true;
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
		long start = System.currentTimeMillis();
		logger.info("end at {}", start);
	}
}
