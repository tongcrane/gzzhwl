package com.gzzhwl.admin.task;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.lend.service.LendService;
import com.gzzhwl.core.data.model.LendLog;

@Service("lendTask")
public class LendTask {
	private static Logger logger = LoggerFactory.getLogger(LendTask.class);
	@Autowired
	private LendService lendService;

	@Value("${superadmin.staffid}")
	private String staffId;

	public void offLend() {
		if (logger.isDebugEnabled()) {
			logger.debug("start offlend task");
		}
		List<LendLog> logs = lendService.getLogToReturn();
		if (CollectionUtils.isNotEmpty(logs)) {
			for (LendLog log : logs) {
				lendService.offLend(log.getLendId(), staffId);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("finish offlend task");
		}
	}
}
