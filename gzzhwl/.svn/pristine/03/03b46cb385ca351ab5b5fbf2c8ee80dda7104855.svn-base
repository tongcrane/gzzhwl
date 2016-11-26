package com.gzzhwl.core.identifier;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.identifier.service.UniqueKeyService;
import com.gzzhwl.core.identifier.service.impl.UniqueKeyServiceImpl;

public class SequenceGenerator implements Generator {
	private static final Logger logger = LoggerFactory.getLogger(SequenceGenerator.class);
	private SeqNoKey key;

	public SequenceGenerator(SeqNoKey key) {
		this.key = key;
	}

	@Override
	public Serializable generate() {
		UniqueKeyService tg = UniqueKeyServiceImpl.getUniqueKeyService();
		if (tg == null) {
			logger.error("could not find to UniqueKeyService:");
			return null;
		}
		return tg.generate(key);
	}

}
