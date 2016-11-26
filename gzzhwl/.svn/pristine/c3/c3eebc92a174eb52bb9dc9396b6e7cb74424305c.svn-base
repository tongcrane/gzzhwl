package com.gzzhwl.api.image.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gzzhwl.api.image.utils.FilePathUtils;

/**
 * 调整图片尺寸模型对象
 * 
 * @author anycrane
 *
 */
public class ReSizeImage {
	private static Logger logger = LoggerFactory.getLogger(ReSizeImage.class);

	private String fileId;// 文件id
	private String filePath;// 文件存放绝对路径
	private String fileName;// 文件名
	private int maxWidth;// 最大宽
	private int maxHeight;// 最大高
	private int width;// 原图宽
	private int height;// 原图高
	private Boolean cut;// 需要裁剪

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileId() {
		return fileId;
	}

	public String getSuffix() {
		return FilePathUtils.getFileSuffix(this.fileName);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Boolean getCut() {
		return cut;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setParam(String width, String height, String cut) {
		if (StringUtils.isNotBlank(height) || StringUtils.isNotBlank(width)) {// 有设定宽或者高
			if (StringUtils.isBlank(width)) {// 如果宽没有设定
				Double _h = NumberUtils.toDouble(height);
				int _height = _h.intValue();
				if (_height > 0) {
					this.maxHeight = _height;
					this.maxWidth = this.width * this.maxHeight / this.height;
					this.cut = false;
				}
			} else if (StringUtils.isBlank(height)) {// 如果高没有设定
				Double _w = NumberUtils.toDouble(width);
				int _width = _w.intValue();
				if (_width > 0) {
					this.maxWidth = _width;
					this.maxHeight = this.height * this.maxWidth / this.width;
					this.cut = false;
				}
			} else {
				Double _w = NumberUtils.toDouble(width);
				Double _h = NumberUtils.toDouble(height);
				int _width = _w.intValue();
				int _height = _h.intValue();
				if (Math.min(_width, _height) > 0) {
					this.maxWidth = _width;
					this.maxHeight = _height;
					if (StringUtils.equals(cut, "1")) {// cut等于1为需要裁剪。
						this.cut = true;
					} else {
						this.cut = false;
					}
				}
			}

		}
	}

	public ImageSize getResize() {
		Boolean cut = this.cut;
		if (null != cut) {
			double _current = (double) this.height / this.width;
			double _need = (double) this.maxHeight / this.maxWidth;
			if (cut) {
				if (_current == _need) {
					this.width = this.maxWidth;
					this.height = this.maxHeight;
				} else if (_current < _need) {
					this.width = this.maxWidth * this.height / this.maxHeight;
				} else {
					this.height = this.maxHeight * this.width / this.maxWidth;
				}
				logger.debug("按指定大小等比压缩，裁剪原图。需要的高为：" + this.maxHeight + "，需要的宽为：" + this.maxWidth);
			} else {
				if (_current == _need) {
					this.width = this.maxWidth;
					this.height = this.maxHeight;
				} else if (_current < _need) {
					this.height = this.height * this.maxWidth / this.width;
					this.width = this.maxWidth;
				} else {
					this.width = this.width * this.maxHeight / this.height;
					this.height = this.maxHeight;
				}
				logger.debug("按指定大小等比压缩，不裁剪原图。需要的高为：" + this.maxHeight + "，需要的宽为：" + this.maxWidth);
			}
			logger.debug("原图将会压缩至：高：" + this.width + "，宽：" + this.height);
		} else {
			logger.debug("没有指定需要的大小，将返回原尺寸。");
		}
		return new ImageSize(this.width, this.height);
	}

}
