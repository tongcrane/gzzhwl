package com.gzzhwl.rest.springmvc.fastjson;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

public class FastJsonOptConfig extends FastJsonConfig {

	public FastJsonOptConfig() {
		super();
		getSerializeConfig().put(ResponseModel.class, ResponseJsonSerializer.instance);
		getSerializeConfig().put(Page.class, PageJsonSerializer.instance);
	}
}
