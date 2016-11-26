package com.gzzhwl.admin.task;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.source.service.SourceLinkStatusService;
import com.gzzhwl.admin.source.service.SourceService;

@Service("sourceTask")
public class SourceTask {
	private static Logger logger = LoggerFactory.getLogger(SourceTask.class);

	@Autowired
	private SourceService sourceService;
	@Autowired
	private SourceLinkStatusService sourceLinkStatusService;
	@Value("${superadmin.staffid}")
	private String staffId;

	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("start close source task");
		}
		List<String> sourceIds = sourceService.findSourceClose();
		if (CollectionUtils.isNotEmpty(sourceIds)) {
			Iterator<String> _iSource = sourceIds.iterator();
			while (_iSource.hasNext()) {
				String sourceId = _iSource.next();
				try {
					sourceLinkStatusService.autoCloseSource(sourceId, staffId);
				} catch (Exception e) {
					logger.debug("自动关闭货源发生错误，货源ID：{},信息：{}", sourceId, e.toString());
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish close source task");
		}
	}
}
