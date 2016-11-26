package com.gzzhwl.eventbus.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EventBusData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7058442472825493311L;
	private Strategy strategy;
	private Object data;

	public EventBusData(Strategy strategy, Object data) {
		this.strategy = strategy;
		this.data = data;
	}

}
