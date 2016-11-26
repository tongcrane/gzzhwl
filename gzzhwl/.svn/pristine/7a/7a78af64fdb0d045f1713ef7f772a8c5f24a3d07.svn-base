package com.gzzhwl.api.image.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.BooleanUtils;

import com.gzzhwl.api.image.model.ImageSize;
import com.gzzhwl.api.image.model.ReSizeImage;
import com.gzzhwl.rest.exception.RestServerException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片工具类
 * 
 * @author anycrane
 *
 */
public class ImageUtils {
	/**
	 * 获取图片真实格式
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getImageFormatName(File file) throws IOException {
		String formatName = null;
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> imageReader = ImageIO.getImageReaders(iis);
		if (imageReader.hasNext()) {
			ImageReader reader = imageReader.next();
			formatName = reader.getFormatName();
		}
		return formatName;
	}

	/**
	 * 获取图片的高宽
	 * 
	 * @param image
	 * @param imageItem
	 * @throws RestServerException
	 */
	public static ImageSize getImageSize(File image) throws RestServerException {
		try {
			BufferedImage bi = Thumbnails.of(image).scale(1.0).asBufferedImage();
			return new ImageSize(bi.getWidth(), bi.getHeight());
		} catch (IOException e) {
			throw new RestServerException(e);
		}
	}

	/**
	 * 获取图片的高宽
	 * 
	 * @param image
	 * @param imageItem
	 * @throws RestServerException
	 */
	public static ImageSize getImageSize(InputStream image) throws RestServerException {
		try {
			BufferedImage bi = Thumbnails.of(image).scale(1.0).asBufferedImage();
			image.close();
			return new ImageSize(bi.getWidth(), bi.getHeight());
		} catch (IOException e) {
			throw new RestServerException(e);
		}
	}

	/**
	 * 调整图片大小
	 * 
	 * @param image
	 * @param out
	 * @throws RestServerException
	 */
	public static void resizeImage(ReSizeImage image, OutputStream out) throws RestServerException {
		File imageFile = new File(image.getFilePath(), image.getFileId());
		ImageSize size = image.getResize();

		Boolean cut = image.getCut();
		if (BooleanUtils.isTrue(cut)) {
			cutImage(imageFile, out, size.getWidth(), size.getHeight(), image.getMaxWidth(), image.getMaxHeight());
		} else {
			compress(imageFile, out, size.getWidth(), size.getHeight());
		}

	}

	private static void compress(File file, OutputStream out, int width, int height) throws RestServerException {
		try {
			Thumbnails.of(file).forceSize(width, height).toOutputStream(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new RestServerException(e);
		}
	}

	private static void cutImage(File file, OutputStream out, int width, int height, int x, int y) throws RestServerException {
		try {
			Thumbnails.of(file).sourceRegion(Positions.CENTER, width, height).size(x, y).toOutputStream(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new RestServerException(e);
		}
	}

}
