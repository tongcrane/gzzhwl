package com.gzzhwl.admin.remark.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.remark.service.RemarkInfoService;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.RemarkType;
import com.gzzhwl.core.data.dao.RemarkInfoDao;
import com.gzzhwl.core.data.extdao.RemarkInfoExtDao;
import com.gzzhwl.core.data.model.RemarkInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;

@Service
public class RemarkInfoServiceImpl implements RemarkInfoService {

	@Autowired
	private RemarkInfoDao remarkInfoDao;

	@Autowired
	private RemarkInfoExtDao remarkInfoExtDao;

	@Override
	public void saveRemark(String targetId, RemarkType remarkType, String content, String createdBy) {

		if (ValidateUtils.isEmpty(content)) {
			return;
		}

		RemarkInfo remarkInfo = new RemarkInfo();
		String remarkId = IdentifierUtils.getId().generate().toString();
		remarkInfo.setRemarkId(remarkId);
		remarkInfo.setCreatedBy(createdBy);
		remarkInfo.setContent(content);
		remarkInfo.setTargetId(targetId);
		remarkInfo.setType(remarkType.getCode());

		remarkInfoDao.insert(remarkInfo);
	}

	@Override
	public <T, K, V> List<T> getRecordList(String targetId, RemarkType remarkType) {

		if (remarkType.equals(RemarkType.SOURCE)) {
			return remarkInfoExtDao.getSourceRecordList(targetId);
		} else if (remarkType.equals(RemarkType.ORDER)) {
			return remarkInfoExtDao.getOrderRecordList(targetId);
		} else if (remarkType.equals(RemarkType.LOAD)) {
			return remarkInfoExtDao.getLoadRecordList(targetId);
		} else if (remarkType.equals(RemarkType.ONLINE)) {
			String[] statusArray = new String[] { LoadBillType.NOTVEHICLE.getCode(),
					LoadBillType.VEHICLECHECK.getCode(), LoadBillType.CLOSETOSURFACE.getCode(),
					LoadBillType.DEPART.getCode(), LoadBillType.ARRIVED.getCode(), LoadBillType.ELECRECEIPT.getCode(),
					LoadBillType.PRINTRECEIPT.getCode(), LoadBillType.CLOSED.getCode() };
			return remarkInfoExtDao.getLoadRecordList(targetId, statusArray);
		}

		return null;
	}

	@Override
	public Map<String, Object> getOnlineRecordNew(String loadId) {
		
		List<Map<String,Object>> recordList = getRecordList(loadId, RemarkType.ONLINE);
		
		if(!ValidateUtils.isEmpty(recordList)){
			return recordList.get(0);
		}
		
		return null;
	}

}
