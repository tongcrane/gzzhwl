package com.gzzhwl.rest.springmvc.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.model.ResponseStatusModel;

public class ResponseJsonSerializer extends JsonSerializer<ResponseModel> {
	private ObjectMapper mapper;

	public ResponseJsonSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void serialize(ResponseModel value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != value.getData()) {
			result.put("data", value.getData());
		}
		if (value.getStatusModel() != null) {
			result.put("status", value.getStatusModel());
		} else {
			result.put("status", new ResponseStatusModel(ExceptionCodeDef.SC_OK));
		}
		mapper.writeValue(jgen, result);
	}

}
