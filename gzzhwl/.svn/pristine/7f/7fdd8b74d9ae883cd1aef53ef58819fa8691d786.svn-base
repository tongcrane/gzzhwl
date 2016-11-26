package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_feedback_atta表
 * @author mew
 *
 */
@Data
@ToString
public class LoadFeedbackAtta implements Serializable {

	@Length(max = 36, message = "attaId超过长度限制")
	private java.lang.String attaId; // 附件标识

	@Length(max = 36, message = "feedbackId超过长度限制")
	private java.lang.String feedbackId; // 反馈标识

	@Length(max = 36, message = "fileId超过长度限制")
	private java.lang.String fileId; // 文件标识
}