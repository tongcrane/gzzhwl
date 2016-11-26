package com.gzzhwl.admin.load.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadFeedbackService;
import com.gzzhwl.admin.load.service.LoadHistoryService;
import com.gzzhwl.admin.load.validator.FeedbackValidate;
import com.gzzhwl.admin.load.vo.LoadFeedbackListVo;
import com.gzzhwl.admin.load.vo.LoadFeedbackVo;
import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.service.ImageServiceFactory;
import com.gzzhwl.api.load.service.TripService;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.LoadFeedBackType;
import com.gzzhwl.core.data.dao.LoadFeedbackAttaDao;
import com.gzzhwl.core.data.dao.LoadFeedbackLogDao;
import com.gzzhwl.core.data.extdao.LoadFeedbackLogExtDao;
import com.gzzhwl.core.data.model.LoadFeedbackAtta;
import com.gzzhwl.core.data.model.LoadFeedbackLog;
import com.gzzhwl.core.data.model.LoadHis;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class LoadFeedbackServiceImpl implements LoadFeedbackService {

	@Autowired
	private Mapper beanMapper;
	@Autowired
	private LoadFeedbackLogDao loadFeedbackLogDao;
	@Autowired
	private LoadHistoryService loadHistoryService;
	@Autowired
	private ImageServiceFactory imageServiceFactory;
	@Autowired
	private LoadFeedbackAttaDao loadFeedbackAttaDao;
	@Autowired
	private LoadFeedbackLogExtDao loadFeedbackLogExtDao;
	@Autowired
	private LoadBillService loadBillService;
	@Autowired
	private TripService tripService;

	@Override
	public void saveLoadFeedback(LoadFeedbackListVo LoadFeedbackList, String staffId, DataSource source,
			String isException, String isEnd) {

		if (LoadFeedbackList != null) {

			List<LoadFeedbackVo> list = LoadFeedbackList.getLoadFeedbackList();

			if (list != null) {
				for (int i = 0; i < list.size(); i++) {

					LoadFeedbackVo loadFeedbackVo = list.get(i);
					String loadNo = loadFeedbackVo.getLoadNo();
					if (!ValidateUtils.isEmpty(loadNo)) {
						OrderLoadInfo orderLoadInfo = loadBillService.getLoadInfoByLoadNo(loadNo);
						if (orderLoadInfo != null) {
							loadFeedbackVo.setLoadId(orderLoadInfo.getLoadId());
						}
					}

					LoadFeedbackLog loadFeedbackLog = null;
					try {
						loadFeedbackLog = loadFeedbackVo.getLoadFeedbackLog();
					} catch (ParseException e) {
						throw new RestException(ErrorCode.ERROR_900005.getCode(),
								"时间" + ErrorCode.ERROR_900005.getDesc());
					}

					if (source != null) {
						if (LoadFeedbackLog.ISEXCEPTION_YES.equals(isException)) {
							boolean isCanSave = this.isCanSaveYSJFeedback(loadFeedbackVo.getLoadId());
							if (!isCanSave) {
								throw new RestException(ErrorCode.ERROR_900028);
							}
						}
					}

					// LoadValidate.valid_loadFeedback_save(loadFeedbackLog);

					loadFeedbackLog.setCreatedBy(staffId);
					loadFeedbackLog.setFeedbackId(IdentifierUtils.getId().generate().toString());
					loadFeedbackLog.setUpdatedBy(staffId);
					loadFeedbackLog.setIsDeleted(Global.ISDEL_NORMAL.toString());
					loadFeedbackLog.setSource(source.getCode());

					if (isException != null) {
						loadFeedbackLog.setIsException(isException);
					}

					if (isEnd != null) {
						loadFeedbackLog.setIsEnd(isEnd);
					}
					loadFeedbackLogDao.insert(loadFeedbackLog);

					List<LoadFeedbackAtta> loadFeedbackAttaList = loadFeedbackVo.getLoadFeedbackAttaList();
					for (int j = 0; j < loadFeedbackAttaList.size(); j++) {
						LoadFeedbackAtta loadFeedbackAtta = loadFeedbackAttaList.get(j);
						loadFeedbackAtta.setFeedbackId(loadFeedbackLog.getFeedbackId());
						loadFeedbackAtta.setAttaId(IdentifierUtils.getId().generate().toString());
						loadFeedbackAttaDao.insert(loadFeedbackAtta);
					}

				}
			}
		}

	}

	@Override
	public List<Map<String, Object>> getLoadFeedbackList(String loadId, DataSource source, String[] loadFeedBackTypes,
			String isException, String isEnd, String[] loadBillTypes) {

		if (ValidateUtils.isEmpty(loadId)) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "loadId" + ErrorCode.ERROR_900003.getDesc());
		}

		return loadFeedbackLogExtDao.getFeedBackList(loadId, source, loadFeedBackTypes, isException, isEnd,
				loadBillTypes);
	}

	// @Override
	// public void removeLoadFeedback(String feedbackId, String staffId) {
	//
	// LoadFeedbackLog loadFeedbackLog = new LoadFeedbackLog();
	//
	// if (ValidateUtils.isEmpty(feedbackId)) {
	// throw new RestException(ErrorCode.ERROR_900003.getCode(),
	// "loadFeedbackId" + ErrorCode.ERROR_900003.getDesc());
	// }
	// loadFeedbackLog.setFeedbackId(feedbackId);
	// loadFeedbackLog.setIsDeleted(Global.ISDEL_DELETE.toString());
	// loadFeedbackLog.setUpdatedBy(staffId);
	//
	// loadFeedbackLogDao.updateSelective(loadFeedbackLog);
	// }

	@Override
	public String updateImage(MultipartFile file, String staffId) {

		if (file == null) {
			throw new RestException(ErrorCode.ERROR_900001);
		}

		ImageItem imageItem = imageServiceFactory.saveImage(ImageCategory.FEEDBACK, file, staffId);

		return imageItem.getFileId();
	}

	@Override
	public Map<String, Object> getCbsLoadFeedbackList(String loadId, LoadBillType loadBillType) {

		Map<String, Object> parm = new HashMap<>();

		if ((loadBillType != LoadBillType.CLOSETOSURFACE) && (loadBillType != LoadBillType.VEHICLECHECK)
				&& (loadBillType != LoadBillType.NOTVEHICLE)) {
			throw new RestException(ErrorCode.ERROR_900014);
		}

		LoadBillType actionLoadBillType = null;

		if (loadBillType == LoadBillType.NOTVEHICLE) {
			actionLoadBillType = LoadBillType.VEHICLECHECK;
		} else if (loadBillType == LoadBillType.VEHICLECHECK) {
			actionLoadBillType = LoadBillType.CLOSETOSURFACE;
		} else if (loadBillType == LoadBillType.CLOSETOSURFACE) {
			actionLoadBillType = LoadBillType.DEPART;
		}

		// 获取actionTime
		LoadHis loadHis = loadHistoryService.getLoadHistory(loadId, actionLoadBillType);
		String actionTime = "";
		if (loadHis != null) {
			actionTime = loadHis.getActionTime();
		}
		parm.put("actionTime", actionTime);

		String[] loadFeedBackTypes = new String[] { LoadFeedBackType.VEHICLE.getCode(),
				LoadFeedBackType.DRIVER.getCode(), LoadFeedBackType.SOURCEADMIN.getCode() };

		String[] loadBillTypes = new String[] { loadBillType.getCode() };

		List<Map<String, Object>> feedBackList = this.getLoadFeedbackList(loadId, null, loadFeedBackTypes,
				LoadFeedbackLog.ISEXCEPTION_YES, null, loadBillTypes);

		parm.put("feedBackList", feedBackList);

		return parm;
	}

	@Override
	public Map<String, Object> getCbsLoadFeedbackList(String loadId) {

		Map<String, Object> resMap = Maps.newHashMap();

		// 获取车检反馈信息
		Map<String, Object> VCFeedBackList = this.getCbsLoadFeedbackList(loadId, LoadBillType.NOTVEHICLE);
		// 获取靠台异常反馈信息
		Map<String, Object> CTSFeedBackList = this.getCbsLoadFeedbackList(loadId, LoadBillType.VEHICLECHECK);
		// 获取发车异常反馈信息
		Map<String, Object> TripFeedBackList = this.getCbsLoadFeedbackList(loadId, LoadBillType.CLOSETOSURFACE);

		resMap.put("VCFeedBackList", VCFeedBackList);
		resMap.put("CTSFeedBackList", CTSFeedBackList);
		resMap.put("TripFeedBackList", TripFeedBackList);

		return resMap;
	}

	@Override
	public void saveGPSInfo(LoadFeedbackListVo LoadFeedbackList, String accountId) {

		// 验证上传GPS信息
		List<LoadFeedbackVo> list = LoadFeedbackList.getLoadFeedbackList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				LoadFeedbackVo loadFeedbackVo = list.get(i);
				String loadNo = loadFeedbackVo.getLoadNo();
				String loadId = null;
				if (!ValidateUtils.isEmpty(loadNo)) {
					OrderLoadInfo orderLoadInfo = loadBillService.getLoadInfoByLoadNo(loadNo);
					if (orderLoadInfo != null) {
						loadId = orderLoadInfo.getLoadId();
					}else{
						throw new RestException(ErrorCode.ERROR_110002);
					}
				} else {
					loadId = loadFeedbackVo.getLoadId();
				}

				if (StringUtils.isNotBlank(loadId)) {
					boolean isMajoy = tripService.isLoadMajorDriver(loadId, accountId);
					if (!isMajoy) {
						throw new RestException(ErrorCode.ERROR_110011);
					}
				}
			}
		}
		this.saveLoadFeedback(LoadFeedbackList, accountId, DataSource.VLORRY, LoadFeedbackLog.ISEXCEPTION_NO,
				LoadFeedbackLog.ISEND_YES);
	}

	@Override
	public void saveCBSDriverFeedback(LoadFeedbackListVo LoadFeedbackList, String accountId) {

		// 验证上传内部司机信息
		FeedbackValidate.valid_cbsdriver_feedback(LoadFeedbackList.getLoadFeedbackList());
		List<LoadFeedbackVo> list = LoadFeedbackList.getLoadFeedbackList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				LoadFeedbackVo loadFeedbackVo = list.get(i);
				String loadNo = loadFeedbackVo.getLoadNo();
				String loadId = null;
				if (!ValidateUtils.isEmpty(loadNo)) {
					OrderLoadInfo orderLoadInfo = loadBillService.getLoadInfoByLoadNo(loadNo);
					if (orderLoadInfo != null) {
						loadId = orderLoadInfo.getLoadId();
					}
				} else {
					loadId = loadFeedbackVo.getLoadId();
				}

				if (StringUtils.isNotBlank(loadId)) {
					boolean isMajoy = tripService.isLoadMajorDriver(loadId, accountId);
					if (!isMajoy) {
						throw new RestException(ErrorCode.ERROR_110011);
					}
				}
			}
		}
		this.saveLoadFeedback(LoadFeedbackList, accountId, DataSource.VLORRY, LoadFeedbackLog.ISEXCEPTION_YES,
				LoadFeedbackLog.ISEND_NO);
	}

	@Override
	public void saveYSJDriverFeedback(LoadFeedbackListVo LoadFeedbackList, String accountId) {

		// 验证上传运势界司机信息
		FeedbackValidate.valid_ysjdriver_feedback(LoadFeedbackList.getLoadFeedbackList());

		List<LoadFeedbackVo> list = LoadFeedbackList.getLoadFeedbackList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				LoadFeedbackVo loadFeedbackVo = list.get(i);
				String loadNo = loadFeedbackVo.getLoadNo();
				String loadId = null;
				if (!ValidateUtils.isEmpty(loadNo)) {
					OrderLoadInfo orderLoadInfo = loadBillService.getLoadInfoByLoadNo(loadNo);
					if (orderLoadInfo != null) {
						loadId = orderLoadInfo.getLoadId();
					}
				} else {
					loadId = loadFeedbackVo.getLoadId();
				}

				if (StringUtils.isNotBlank(loadId)) {
					boolean isMajoy = tripService.isLoadMajorDriver(loadId, accountId);
					if (!isMajoy) {
						throw new RestException(ErrorCode.ERROR_110011);
					}
				}
			}
		}
		this.saveLoadFeedback(LoadFeedbackList, accountId, DataSource.VLORRY, LoadFeedbackLog.ISEXCEPTION_YES,
				LoadFeedbackLog.ISEND_NO);
	}

	@Override
	public void endFeedback(String feedbackId, String actionTime, String accountId) {

		LoadFeedbackLog loadFeedbackLog = loadFeedbackLogDao.get(feedbackId);

		if (ValidateUtils.isEmpty(loadFeedbackLog)) {
			throw new RestException(ErrorCode.ERROR_900013.getCode(), "feedbackId" + ErrorCode.ERROR_900013.getDesc());
		}

		loadFeedbackLog.setIsEnd(LoadFeedbackLog.ISEND_YES);

		if (!StringUtils.isEmpty(actionTime)) {
			loadFeedbackLog.setEndTime(actionTime);
		}

		loadFeedbackLogDao.updateSelective(loadFeedbackLog);
	}

	@Override
	public List<Map<String, Object>> getTrackList(String loadId) {

		String[] loadBillTypes = new String[] { LoadBillType.DEPART.getCode() };

		List<Map<String, Object>> feedBackList = this.getLoadFeedbackList(loadId, null, null, null, null,
				loadBillTypes);

		return feedBackList;
	}

	@Override
	public List<Map<String, Object>> getElecFeedbackList(String loadId) {

		String[] loadBillTypes = new String[] { LoadBillType.ELECRECEIPT.getCode(), LoadBillType.ARRIVED.getCode() };

		List<Map<String, Object>> feedBackList = this.getLoadFeedbackList(loadId, null, null, null, null,
				loadBillTypes);

		return feedBackList;
	}

	@Override
	public String getRevFeedbackCharges(String loadId) {
		return loadFeedbackLogExtDao.getRevFeedBackCharges(loadId);
	}

	@Override
	public String getPayFeedbackCharges(String loadId) {
		return loadFeedbackLogExtDao.getPayFeedBackCharges(loadId);
	}

	@Override
	public void updateFeedbackCharges(String feedBackId, String itemPrice, String priceSymbol) {
		LoadFeedbackLog loadFeedbackLog = loadFeedbackLogDao.get(feedBackId);
		if (loadFeedbackLog == null) {
			throw new RestException(ErrorCode.ERROR_900013);
		}
		if (StringUtils.isNotBlank(loadFeedbackLog.getItemPrice())) {
			throw new RestException(ErrorCode.ERROR_900023);
		}
		// 异常反馈金额处理
		if (StringUtils.isNotBlank(itemPrice)) {
			double price = Double.parseDouble(itemPrice);
			DecimalFormat df = new DecimalFormat("#0.00");
			if (StringUtils.isNotBlank(priceSymbol)) {
				if (priceSymbol.equals(LoadFeedbackLog.PRICE_ADD)) {
					itemPrice = df.format(Math.abs(price));
				} else {
					itemPrice = "-" + df.format(Math.abs(price));
				}
			} else {
				itemPrice = "-" + df.format(Math.abs(price));
			}
			loadFeedbackLog.setItemPrice(itemPrice);
		}
		loadFeedbackLogDao.updateSelective(loadFeedbackLog);
	}

	@Override
	public boolean revFeedbackValidator(String loadId) {
		Integer count = loadFeedbackLogExtDao.getRevInvalidBackCount(loadId);

		if (count > 0) {
			throw new RestException(ErrorCode.ERROR_900024);
		}
		return true;
	}

	@Override
	public boolean payFeedbackValidator(String loadId) {
		Integer count = loadFeedbackLogExtDao.getPayInvalidBackCount(loadId);

		if (count > 0) {
			throw new RestException(ErrorCode.ERROR_900024);
		}
		return true;
	}

	@Override
	public boolean isCanSaveYSJFeedback(String loadId) {

		List<Map<String, Object>> loadFeedbackList = this.getLoadFeedbackList(loadId, DataSource.VLORRY, null,
				LoadFeedbackLog.ISEXCEPTION_YES, LoadFeedbackLog.ISEND_NO, null);

		return loadFeedbackList.size() > 0 ? false : true;
	}

}
