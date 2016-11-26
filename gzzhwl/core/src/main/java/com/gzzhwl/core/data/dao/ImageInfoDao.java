package com.gzzhwl.core.data.dao;

import com.gzzhwl.core.data.model.ImageInfo;

/**
 * 数据访问接口
 *
 */
public interface ImageInfoDao {
	public final static String PREFIX = ImageInfoDao.class.getName();

	public ImageInfo get(java.lang.String imageId);

	public int insert(ImageInfo imageInfo);

}
