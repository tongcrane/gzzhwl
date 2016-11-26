package com.gzzhwl.api.image.service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.model.ImageSize;
import com.gzzhwl.api.image.model.ReSizeImage;
import com.gzzhwl.api.image.utils.ImageUtils;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestServerException;

/**
 * 图片获取工厂处理
 */
@Component
public class ImageServiceFactory {
	@Autowired
	private ImageService service;

	private static final String IMAGE_URL_PATTERN = "/api/image/{0}";

	/**
	 * 获取图片对象
	 * 
	 * @author anycrane
	 */
	public ReSizeImage getImage(String imageId, String width, String height, String cut) throws RestServerException {
		ReSizeImage domain = service.getImage(imageId);
		if (domain == null) {
			throw new RestServerException("id:" + imageId + " has no the record");
		}
		File dest = new File(domain.getFilePath(), domain.getFileId());
		if (dest.exists()) {
			ImageSize size = ImageUtils.getImageSize(dest);
			domain.setHeight(size.getHeight());
			domain.setWidth(size.getWidth());
			domain.setParam(width, height, cut);
			return domain;
		} else {
			throw new RestServerException(imageId + " file not found");
		}
	}

	/**
	 * 保存图片
	 * 
	 * @param category
	 * @param file
	 * @return
	 * @throws RestServerException
	 */
	public ImageItem saveImage(ImageCategory category, MultipartFile file, String accountId)
			throws RestServerException {
		if (!file.isEmpty()) {
			// TODO 验证文件格式
			// TODO 验证文件大小
			try {
				String fileId = IdentifierUtils.getId().generate().toString();
				String relativePath = service.getRelativePath(category, fileId);
				String fullPath = service.getAbsolutePath(relativePath);
				if (makeFolders(fullPath)) {
					File dest = new File(fullPath, fileId);
					ImageSize size = ImageUtils.getImageSize(file.getInputStream());
					ImageItem imageItem = new ImageItem();
					String imageUrl = this.getImageUrl(fileId);
					imageItem.setUrl(imageUrl);// 图片URL路径
					imageItem.setPath(relativePath);// 存放图片的相对路径
					imageItem.setSize(String.valueOf(file.getSize()));// 图片大小
					imageItem.setFileName(file.getOriginalFilename());// 原始文件名
					imageItem.setWidth(String.valueOf(size.getWidth()));// 宽
					imageItem.setHeight(String.valueOf(size.getHeight()));// 高
					imageItem.setFileId(fileId);
					file.transferTo(dest);
					service.saveImage(imageItem, category, accountId);
					return imageItem;
				}
				throw new RestServerException("folder can not created, may be permission denied");
			} catch (IllegalStateException | IOException e) {
				throw new RestServerException(e);
			}
		} else {
			throw new RestServerException("can not save image with empty file");
		}
	}

	/**
	 * 获取图片访问路径
	 */
	private String getImageUrl(Object... params) {
		return MessageFormat.format(IMAGE_URL_PATTERN, params);
	}

	/**
	 * 创建文件夹
	 */
	private boolean makeFolders(String fullPath) {
		File folder = new File(fullPath);
		if (!folder.exists()) {
			return folder.mkdirs();
		}
		return true;
	}
}
