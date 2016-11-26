package com.gzzhwl.core.utils;

import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.identifier.Generator;
import com.gzzhwl.core.identifier.SequenceGenerator;
import com.gzzhwl.core.identifier.UUIDGenerator;

public class IdentifierUtils {
	public static Generator getId() {
		return new UUIDGenerator();
	}

	/**
	 * 生成唯一序列号
	 * 
	 * @param key
	 * @return
	 */
	public static Generator getSequence(SeqNoKey key) {
		return new SequenceGenerator(key);
	}

	public static void main(String[] args) {
		System.out.println(IdentifierUtils.getId().generate().toString());
	}
}
