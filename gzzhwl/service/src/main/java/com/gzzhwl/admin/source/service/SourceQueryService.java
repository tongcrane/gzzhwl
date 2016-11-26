package com.gzzhwl.admin.source.service;

import java.util.Map;

import com.gzzhwl.admin.source.vo.SourceCommVo;
import com.gzzhwl.core.page.Page;

public interface SourceQueryService {
	public Map<String, Object> querySourceDetail(String sourceId);

	public Page<Map<String, Object>> queryOrderSourcePage(SourceCommVo sourceCommVo, int pageIndex, int pageSize);

}
