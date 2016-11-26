package com.gzzhwl.api.image.service;

import java.util.Collection;

import com.gzzhwl.api.exception.CaptchaServiceException;
import com.gzzhwl.api.image.model.CaptchaModel;

public interface CaptchaStore {
	public boolean hasCaptcha(String id);

	public void storeCaptcha(CaptchaModel captcha) throws CaptchaServiceException;

	public boolean removeCaptcha(String id);

	public CaptchaModel getCaptcha(String id) throws CaptchaServiceException;

	public void empty();

	public void cleanAndShutdown();

	public Collection<CaptchaModel> values();

	public void clear();

}
