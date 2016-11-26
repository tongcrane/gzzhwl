package com.gzzhwl.common.cache;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.rest.cache.BaseCacheAspect;

@Component
@Aspect
public class RegionCacheAspect extends BaseCacheAspect {
	private static final String ALL_REGION = "all_region";

	private static final String ALL_REGION_CODE = "all_region_code";

	private static final String ALL_REGION_ID = "all_region_id";

	private static final String ALL_DATA = "all_data";

	public RegionCacheAspect() {
		setCacheName("sys-regionCache");
	}

	@Pointcut(value = "execution(* com.gzzhwl.common.service.RegionService.findRegion())")
	private void allRegionCacheablePointcut() {
	}

	@Around(value = "allRegionCacheablePointcut()", argNames = "pjp")
	public Object allRegionCacheableAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = get(ALL_REGION);
		if (retVal != null) {
			logger.debug("cacheName:{}, method:findRegion", cacheName);
			return retVal;
		} else {
			logger.debug("cacheName:{}, method:findRegion", cacheName);
			retVal = pjp.proceed();
			put(ALL_REGION, retVal);
			return retVal;
		}
	}

	@SuppressWarnings("unchecked")
	private void cacheRegion(Object retVal) {
		List<RegionInfo> all = (List<RegionInfo>) retVal;
		Map<String, RegionInfo> cacheCode = Maps.newHashMap();
		Map<Integer, RegionInfo> cacheId = Maps.newHashMap();
		for (RegionInfo region : all) {
			cacheCode.put(region.getRegionCode(), region);
			cacheId.put(region.getRegionId(), region);
		}
		put(ALL_REGION_CODE, cacheCode);
		put(ALL_REGION_ID, cacheId);
	}

	@Pointcut(value = "execution(* com.gzzhwl.core.data.dao.RegionInfoDao.find())")
	private void allDataDaoCacheablePointcut() {
	}

	@Around(value = "allDataDaoCacheablePointcut()", argNames = "pjp")
	public Object allDataDaoCacheableAdvice(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = get(ALL_DATA);
		if (retVal != null) {
			logger.debug("cacheName:{}, method:findAllRegion", cacheName);
			return retVal;
		} else {
			logger.debug("cacheName:{}, method:findAllRegion", cacheName);
			retVal = pjp.proceed();
			put(ALL_DATA, retVal);
			this.cacheRegion(retVal);
			return retVal;
		}
	}

	@Pointcut(value = "execution(* com.gzzhwl.common.service.RegionService.findByCode(..)) && args(regionCode,..)", argNames = "regionCode")
	private void codeRegionCacheablePointcut(String regionCode) {
	}

	@SuppressWarnings("unchecked")
	@Around(value = "codeRegionCacheablePointcut(regionCode)", argNames = "pjp,regionCode")
	public Object codeRegionCacheableAdvice(ProceedingJoinPoint pjp, String regionCode) throws Throwable {
		Object retVal = get(ALL_REGION_CODE);
		if (retVal != null) {
			Map<String, RegionInfo> _map = (Map<String, RegionInfo>) retVal;
			logger.debug("cacheName:{}, method:findRegion", cacheName);
			return _map.get(regionCode);
		} else {
			return pjp.proceed();
		}
	}

	@Pointcut(value = "execution(* com.gzzhwl.common.service.RegionService.findById(..)) && args(regionId,..)", argNames = "regionId")
	private void idRegionCacheablePointcut(Integer regionId) {
	}

	@SuppressWarnings("unchecked")
	@Around(value = "idRegionCacheablePointcut(regionId)", argNames = "pjp,regionId")
	public Object idRegionCacheableAdvice(ProceedingJoinPoint pjp, Integer regionId) throws Throwable {
		Object retVal = get(ALL_REGION_ID);
		if (retVal != null) {
			Map<Integer, RegionInfo> _map = (Map<Integer, RegionInfo>) retVal;
			logger.debug("cacheName:{}, method:findRegion", cacheName);
			return _map.get(regionId);
		} else {
			return pjp.proceed();
		}
	}
}
