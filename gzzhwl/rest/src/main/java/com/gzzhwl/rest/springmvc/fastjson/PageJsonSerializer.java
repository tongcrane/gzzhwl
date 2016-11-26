package com.gzzhwl.rest.springmvc.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.gzzhwl.core.page.Page;

public class PageJsonSerializer implements ObjectSerializer {
	public final static PageJsonSerializer instance = new PageJsonSerializer();

	@SuppressWarnings("rawtypes")
	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		if (object == null) {
			serializer.writeNull();
		} else {
			Page value = (Page) object;
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", value.getTotal());
			result.put("pageCount", value.getPageCount());
			result.put("current", value.getCurrent());
			result.put("hasNext", value.isHasNext());
			result.put("hasPrev", value.isHasPrev());
			result.put("pageSize", value.getPageSize());
			result.put("rows", value.getRows());
			serializer.write(result);
		}
	}
}
