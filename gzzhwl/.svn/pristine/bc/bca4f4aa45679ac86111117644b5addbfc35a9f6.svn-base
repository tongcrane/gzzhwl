package com.gzzhwl.rest.springmvc.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

import java.io.IOException;

public class ResponseJsonMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1808342056245750120L;

	public ResponseJsonMapper() throws JsonMappingException {
		super();
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		// this.configure(SerializationFeature.INDENT_OUTPUT, true);// 格式化输出
		// this.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,
		// true);// 对key进行排序操作
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 忽略未知属性

		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
				jg.writeString("");
			}
		});

		SimpleModule module = new SimpleModule("ResponseJsonModel");
		module.addSerializer(ResponseModel.class, new ResponseJsonSerializer(this));
		module.addSerializer(Page.class, new PageJsonSerializer(this));
		super.registerModule(module);
	}

}