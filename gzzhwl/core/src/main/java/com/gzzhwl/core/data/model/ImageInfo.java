package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_image_info表
 * @author mew
 *
 */
@Data
@ToString
public class ImageInfo implements Serializable {

	@Length(max = 36, message = "imageId超过长度限制")
	private java.lang.String imageId; // 图片标识

	@Length(max = 2, message = "category超过长度限制")
	private java.lang.String category; // 分类

	@Length(max = 100, message = "name超过长度限制")
	private java.lang.String name; // 名字

	@Length(max = 255, message = "src超过长度限制")
	private java.lang.String src; // 路径

	@Length(max = 10, message = "height超过长度限制")
	private java.lang.String height; // 高度

	@Length(max = 10, message = "width超过长度限制")
	private java.lang.String width; // 宽度

	@Length(max = 10, message = "size超过长度限制")
	private java.lang.String size; // 大小

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}