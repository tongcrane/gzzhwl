package com.gzzhwl.rest.springmvc.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gzzhwl.core.page.Page;

@SuppressWarnings("rawtypes")
public class PageJsonSerializer extends JsonSerializer<Page> {
	private ObjectMapper mapper;

	public PageJsonSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void serialize(Page value, JsonGenerator jgen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", value.getTotal());
		result.put("pageCount", value.getPageCount());
		result.put("current", value.getCurrent());
		result.put("hasNext", value.isHasNext());
		result.put("hasPrev", value.isHasPrev());
		result.put("pageSize", value.getPageSize());
		result.put("rows", value.getRows());
		mapper.writeValue(jgen, result);
	}
}
