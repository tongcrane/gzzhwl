package com.gzzhwl.core.utils;

import org.apache.commons.lang3.RandomUtils;

/**
 * 随机验证码工具类
 *
 */
public class RandomCaptchaUtils {
	/** 字符集合 */
	private final static char[] codeSequence = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/**
	 * 随机生成指定位数的随机数（数字）
	 */
	public static String getRandomNum(final int length) {
		StringBuilder captcha = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			captcha.append(RandomUtils.nextInt(0, 9));
		}
		return captcha.toString();
	}

	/**
	 * 随机生成指定位数的随机数（数字和字母）
	 */
	public static String getRandomString(final int length) {
		StringBuilder captcha = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = RandomUtils.nextInt(0, codeSequence.length);
			captcha.append(codeSequence[index]);
		}
		return captcha.toString();
	}
}
