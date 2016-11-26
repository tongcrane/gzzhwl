package com.gzzhwl.rest.springmvc.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeFilterable;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.model.ResponseStatusModel;

public class ResponseJsonSerializer extends SerializeFilterable implements ObjectSerializer {

	public final static ResponseJsonSerializer instance = new ResponseJsonSerializer();

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		if (object == null) {
			serializer.writeNull();
		} else {
			ResponseModel value = (ResponseModel) object;
			Map<String, Object> result = new HashMap<String, Object>();
			if (null != value.getData()) {
				result.put("data", value.getData());
			}
			if (value.getStatusModel() != null) {
				result.put("status", value.getStatusModel());
			} else {
				result.put("status", new ResponseStatusModel(ExceptionCodeDef.SC_OK));
			}
			serializer.write(result);
		}

	}

}
