package com.gzzhwl.api.account.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.api.image.utils.CaptchaStoreUtils;
import com.gzzhwl.patchca.service.ConfigurableCaptchaService;
import com.gzzhwl.patchca.utils.encoder.EncoderHelper;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;
import com.gzzhwl.rest.utils.CaptchaUtils;

@RestController
@RequestMapping("/api/account")
public class CaptchaController {
	private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public ResponseModel getCaptcha() throws IOException {
		// ValidateCode code = new ValidateCode(100, 40, 4);
		// BufferedImage bufferedImage = code.getImage();
		ConfigurableCaptchaService cs = CaptchaUtils.getIntance().getCaptchaService();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// ImageIO.write(bufferedImage, "jpg", outputStream);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", outputStream);
		String image = Base64Utils.encodeToString(outputStream.toByteArray());
		// String token = code.getCode();
		String id = CaptchaStoreUtils.store(token);
		if (logger.isDebugEnabled()) {
			logger.debug("id:{},token:{}", id, token);
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("validId", id);
		result.put("image", image);
		return new ResponseModel(result);
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.POST)
	public ResponseModel validCaptcha(@RequestParam String token, @RequestParam String validId) throws IOException {
		ParamEmptyValidator.VALID_PARAM_EMPTY(token, validId);
		boolean result = CaptchaStoreUtils.check(validId, token);
		return new ResponseModel(result);
	}

}
