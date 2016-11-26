package com.gzzhwl.api.utils;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

public class CardIdConvert {
	public static String[] getColumnData(final Iterable<Map<String, Object>> inputCollection, final String column) {
		Collection<String> data = CollectionUtils.collect(inputCollection,
				new Transformer<Map<String, Object>, String>() {
					@Override
					public String transform(Map<String, Object> input) {
						return input.get(column).toString();
					}
				});
		String[] idArray = new String[data.size()];
		return data.toArray(idArray);
	}
}
