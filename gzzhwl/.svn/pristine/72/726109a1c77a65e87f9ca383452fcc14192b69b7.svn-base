package com.gzzhwl.api.image.service.impl;

import java.text.MessageFormat;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.model.ReSizeImage;
import com.gzzhwl.api.image.service.ImageService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.ImageInfoDao;
import com.gzzhwl.core.data.model.ImageInfo;
import com.gzzhwl.rest.exception.RestServerException;

@Service
public class ImageServiceImpl implements ImageService {
	@Value("${setting.upload.path}")
	private String rootPath;

	@Autowired
	private ImageInfoDao loginImagesDao;

	@Override
	public ReSizeImage getImage(String imageId) throws RestServerException {
		ImageInfo loginImage = loginImagesDao.get(imageId);
		if (loginImage != null) {
			ReSizeImage image = new ReSizeImage();
			image.setFileId(loginImage.getImageId());
			image.setFileName(loginImage.getName());
			String relativePath = loginImage.getSrc();
			String fullPath = this.getAbsolutePath(relativePath);
			image.setFilePath(fullPath);
			return image;
		} else {
			return null;
		}
	}

	/**
	 * 根据文件相对路径获取绝对路径
	 * 
	 * @param relativePath
	 * @return
	 */
	@Override
	public String getAbsolutePath(String relativePath) {
		return FilenameUtils.concat(rootPath, relativePath);
	}

	/**
	 * 获取用户图片路径
	 * 
	 * @return
	 */
	@Override
	public String getRelativePath(ImageCategory category, Object... pathVariable) throws RestServerException {
		String currentDate = DateTime.now().toString("yyyyMMdd");
		String relativePath = MessageFormat.format(PATH_PATTERN, category.name(), currentDate);
		return FilenameUtils.normalizeNoEndSeparator(relativePath);
	}

	@Override
	public boolean saveImage(ImageItem imageItem, ImageCategory category, String accountId) throws RestServerException {
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setCategory(category.getCode());
		imageInfo.setAccountId(accountId);
		imageInfo.setHeight(imageItem.getHeight());
		imageInfo.setImageId(imageItem.getFileId());
		imageInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		imageInfo.setName(imageItem.getFileName());
		imageInfo.setSize(imageItem.getSize());
		imageInfo.setSrc(imageItem.getPath());
		imageInfo.setStatus(Global.STATUS_NORMAL.toString());
		imageInfo.setWidth(imageItem.getWidth());
		return loginImagesDao.insert(imageInfo) > 0;
	}
}
