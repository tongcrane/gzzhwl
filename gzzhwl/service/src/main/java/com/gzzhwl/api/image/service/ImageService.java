package com.gzzhwl.api.image.service;

import java.io.File;

import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.model.ReSizeImage;
import com.gzzhwl.rest.exception.RestServerException;

public interface ImageService {
	public static final String PATH_PATTERN = "{0}" + File.separator + "{1}";

	/**
	 * 根据图片ID，获取图片文件
	 * 
	 * @param imageId
	 * @return
	 * @throws RestServerException
	 */
	public ReSizeImage getImage(String imageId) throws RestServerException;

	/**
	 * 根据图片类型，及路径参数，获取相对路径地址
	 * 
	 * @param category
	 * @param pathVariable
	 * @return
	 */
	public String getRelativePath(ImageCategory category, Object... pathVariable) throws RestServerException;

	/**
	 * 获取绝对路径
	 * 
	 * @param relativePath
	 * @return
	 */
	public String getAbsolutePath(String relativePath) throws RestServerException;

	/**
	 * 保存图片
	 * 
	 * @param imageItem
	 * @return
	 * @throws RestServerException
	 */
	public boolean saveImage(ImageItem imageItem, ImageCategory category, String accountId) throws RestServerException;
}