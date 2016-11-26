package com.gzzhwl.core.constant;

public enum SeqNoKey {
	VEHICLE("ZHCL", "vehicle", false),

	DRIVER("ZHJSY", "driver", false),

	ORDER("OR", "order", true),

	CUST("ZHKH", "cust", false),

	SOURCE("SO", "source", true),

	SUPPLY("ZHGHS", "supply", false),

	CONSIGN("10", "consign", true),

	LOAD("TH", "load", true),

	LEND("LD", "lend", false),

	CONTRACT("HT", "contract", true);

	private String suffix;
	private String seqName;
	private boolean encode;

	private SeqNoKey(String suffix, String seqName, boolean encode) {
		this.suffix = suffix;
		this.seqName = seqName;
		this.encode = encode;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getSeqName() {
		return seqName;
	}

	public boolean isEncode() {
		return encode;
	}

}
