package com.gzzhwl.api.image.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.gzzhwl.api.image.model.ReSizeImage;
import com.gzzhwl.api.image.service.ImageServiceFactory;
import com.gzzhwl.api.image.utils.ImageUtils;
import com.gzzhwl.rest.exception.RestServerException;
import com.gzzhwl.rest.springmvc.utils.WebRequestResolve;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@RestController
@RequestMapping("/api")
public class ImageController {
	@Autowired
	private ImageServiceFactory imageService;

	/**
	 * 获取图片
	 * 
	 * @param maxWidth
	 * @param maxHeight
	 */
	@RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
	public void getImage(@PathVariable(value = "imageId") String imageId, @RequestParam(required = false) String width,
			@RequestParam(required = false) String height, @RequestParam(required = false) String cut,
			WebRequest request, HttpServletResponse response) throws RestServerException, IOException {
		ParamEmptyValidator.VALID_PARAM_EMPTY(imageId);
		ReSizeImage image = imageService.getImage(imageId, width, height, cut);
		if (image != null) {
			String type = image.getSuffix();
			String contentType = this.getContentType(type);
			response.setContentType(contentType);
			boolean isIE = WebRequestResolve.isIE(request);
			String fileName = image.getFileName();
			if (StringUtils.isBlank(fileName)) {
				fileName = image.getFileId();
			}
			if (StringUtils.isNotBlank(fileName)) {
				String name = StringUtils.replace(fileName, ",", "_");
				if (isIE) {
					response.setHeader("Content-Disposition", "filename=\"" + URLEncoder.encode(name, "UTF-8") + "\"");
				} else {
					response.setHeader("Content-disposition",
							"filename=" + new String(name.getBytes("utf-8"), "iso8859-1"));
				}
			}
			ImageUtils.resizeImage(image, response.getOutputStream());
		}
	}

	private String getContentType(String type) {
		if (StringUtils.isNotEmpty(type)) {
			if (StringUtils.equals("png", type)) {
				return MediaType.IMAGE_PNG_VALUE;
			} else if (StringUtils.equals("gif", type)) {
				return MediaType.IMAGE_GIF_VALUE;
			}
		}
		return MediaType.IMAGE_JPEG_VALUE;
	}
}
