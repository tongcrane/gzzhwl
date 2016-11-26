package com.gzzhwl.tms.service;

import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.vo.SourceVO;

public interface TMSApplyReturnService {

	public boolean applyReturn(SourceVO applyReturnVo)throws RestException;

}
