package com.gzzhwl.api.image.utils;

import org.apache.commons.io.FilenameUtils;

/**
 * 图片上传路径解析工具类
 * 
 * @author anycrane
 *
 */
public class FilePathUtils {
	

	/**
	 * 获取文件后缀名（小写）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		return FilenameUtils.getExtension(fileName).toLowerCase();
	}

}
