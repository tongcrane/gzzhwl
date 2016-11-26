package com.gzzhwl.push.model;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

public class PushItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4275800218096433712L;
	private PushEvent event;
	private Map<String, Object> content;

	public PushItem(PushEvent event, Map<String, Object> content) {
		super();
		this.event = event;
		this.content = content;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> result = Maps.newHashMap();
		if (content != null) {
			result.put("param", content);
		}
		result.put("event", event.getCode());
		return result;
	}
}
