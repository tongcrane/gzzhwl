package com.gzzhwl.rest.utils;

import java.awt.Color;
import java.util.Random;

import com.gzzhwl.patchca.color.ColorFactory;
import com.gzzhwl.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.gzzhwl.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.gzzhwl.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.gzzhwl.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.gzzhwl.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.gzzhwl.patchca.service.ConfigurableCaptchaService;
import com.gzzhwl.patchca.word.RandomWordFactory;

public class CaptchaUtils {
	private ConfigurableCaptchaService captchaService = null;
	private static final String CHARACTER = "0123456789";
	private static Random random = new Random();
	private static CaptchaUtils instance = null;

	public static CaptchaUtils getIntance() {
		if (instance == null)
			instance = new CaptchaUtils();
		return instance;
	}

	private CaptchaUtils() {
		captchaService = new ConfigurableCaptchaService();
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters(CHARACTER);
		wf.setMaxLength(4);
		wf.setMinLength(4);
		captchaService.setWordFactory(wf);
	}

	public ConfigurableCaptchaService getCaptchaService() {
		ConfigurableCaptchaService captchaService = instance.captchaService;
		captchaService.setColorFactory(new ColorFactory() {
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});

		switch (random.nextInt(5)) {
		case 0:
			captchaService.setFilterFactory(new CurvesRippleFilterFactory(captchaService.getColorFactory()));
			break;
		case 1:
			captchaService.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			captchaService.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			captchaService.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			captchaService.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		return captchaService;
	}

}
