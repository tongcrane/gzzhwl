package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.ImageInfoDao;
import com.gzzhwl.core.data.model.ImageInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class ImageInfoDaoImpl implements ImageInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ImageInfo get(java.lang.String imageId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("imageId", imageId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public int insert(ImageInfo imageInfo) {
		return dao.insert(PREFIX + ".insert", imageInfo);
	}
}


