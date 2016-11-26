package com.gzzhwl.core.identifier.service.impl;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.data.dao.UniqueKeyDao;
import com.gzzhwl.core.data.model.UniqueKey;
import com.gzzhwl.core.identifier.service.UniqueKeyService;

@Component
public class UniqueKeyServiceImpl implements UniqueKeyService {
	static String[] SUFFIX = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	static UniqueKeyService s = null;
	@Autowired
	private UniqueKeyDao dao;
	private DecimalFormat format;
	private static final Logger logger = LoggerFactory.getLogger(UniqueKeyServiceImpl.class);

	public UniqueKeyServiceImpl() {
		s = this;
	}

	public synchronized Serializable generate(SeqNoKey key) {
		int result;
		int rows = 0;
		String currentDate;
		do {
			UniqueKey u = dao.get(key.getSeqName());
			if (u == null) {
				logger.error("could not read a hi value - you need to populate the table :", key);
				throw new RuntimeException();
			}
			if (u.getDateHi() == null) {
				result = 1;
			} else if (!u.getDateHi().equals(u.getCurrentDate())) {
				result = 1;
			} else {
				result = u.getNextHi();
			}
			currentDate = u.getCurrentDate();
			u.setDateHi(u.getCurrentDate());
			u.setNextHi(result + 1);
			dao.update(u);
			rows++;
		} while (rows == 0);
		format = new DecimalFormat("#0000");
		if (key.isEncode()) {
			String value = currentDate.concat(format.format(result));
			Long _value = Long.valueOf(value);
			String hex = Long.toHexString(_value);
			String fix = SUFFIX[RandomUtils.nextInt(0, SUFFIX.length)];
			int index = RandomUtils.nextInt(0, hex.length());

			return key.getSuffix().concat(insert(hex, fix, index)).toUpperCase();
		} else {
			return key.getSuffix().concat(currentDate).concat(format.format(result));
		}
	}

	public static UniqueKeyService getUniqueKeyService() {
		return s;
	}

	public static String insert(String str, String fix, int index) {
		if (str == null) {
			return null;
		}
		if (fix == null) {
			fix = StringUtils.EMPTY;
		}
		int len = str.length();
		if (index < 0) {
			index = 0;
		}
		if (index > len) {
			index = len;
		}
		return new StrBuilder(len + fix.length() + 1).append(str.substring(0, index)).append(fix)
				.append(str.substring(index)).toString();
	}

}
