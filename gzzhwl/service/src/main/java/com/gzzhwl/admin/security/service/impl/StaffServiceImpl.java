package com.gzzhwl.admin.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.menu.service.MenuService;
import com.gzzhwl.admin.security.model.AuthStaffInfo;
import com.gzzhwl.admin.security.service.StaffLoginHisService;
import com.gzzhwl.admin.security.service.StaffService;
import com.gzzhwl.admin.security.validate.StaffValidator;
import com.gzzhwl.admin.security.validate.TicketValidator;
import com.gzzhwl.common.model.EmailSender;
import com.gzzhwl.common.service.EmailService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.DepartmentInfoDao;
import com.gzzhwl.core.data.dao.ResetPasswordLogDao;
import com.gzzhwl.core.data.dao.StaffInfoDao;
import com.gzzhwl.core.data.dao.StaffMenuInfoDao;
import com.gzzhwl.core.data.dao.StaffOrgRelaDao;
import com.gzzhwl.core.data.model.DepartmentInfo;
import com.gzzhwl.core.data.model.Menu;
import com.gzzhwl.core.data.model.ResetPasswordLog;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.data.model.StaffMenuInfo;
import com.gzzhwl.core.data.model.StaffOrgRela;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.MD5;
import com.gzzhwl.rest.exception.RestException;

@Service
public class StaffServiceImpl implements StaffService {
	private static Logger logger = LoggerFactory.getLogger(StaffService.class);
	@Autowired
	private StaffInfoDao staffDao;
	@Autowired
	private StaffMenuInfoDao staffMenuInfoDao;
	@Autowired
	private DepartmentInfoDao departmentInfoDao;
	@Autowired
	private StaffOrgRelaDao staffOrgRelaDao;
	@Autowired
	private StaffLoginHisService loginHisService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private ResetPasswordLogDao resetPasswordLogDao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private MenuService menuService;
	@Value("${website.url}")
	private String website;
	@Value("${ticket.minute.diff}")
	private Integer minuteDiff;

	private static final String QUERYTYPE_NORMAL = "0";// 搜索

	private static final String QUERYTYPE_ADVANCE = "1";// 高级搜索

	@Override
	public AuthStaffInfo doLogin(String number, String password) throws RestException {
		String _p = MD5.md5(password);
		StaffInfo staffInfo = this.getStaffInfoByNumber(number);
		if (staffInfo == null) {
			logger.debug("用户名不存在{}", number);
			throw new RestException("100010", "用户名不存在");
		}
		String o_password = staffInfo.getPassword();
		if (!StringUtils.equals(_p, o_password)) {
			logger.debug("用户名{}密码错误", number);
			throw new RestException("100011", "密码错误");
		}
		String status = staffInfo.getStatus();
		if (!StringUtils.equals(status, Global.STATUS_NORMAL.toString())) {
			logger.debug("用户名{}已被禁用", number);
			throw new RestException("100012", "账号状态异常");
		}
		AuthStaffInfo info = beanMapper.map(staffInfo, AuthStaffInfo.class);
		String staffId = staffInfo.getStaffId();
		String token = loginHisService.saveUserLoginHis(staffId);// 获取登录token
		info.setToken(token);
		List<Menu> menus = menuService.getUserMenu(staffId);
		info.setMenus(menus);
		List<Integer> funIds = menuService.getUserFunction(staffId);
		info.setFunctionIds(funIds);
		return info;
	}

	private StaffInfo getStaffInfoByNumber(String number) {
		List<StaffInfo> staffList = staffDao.findByNumber(number);
		StaffInfo staffInfo = null;
		if (CollectionUtils.isNotEmpty(staffList)) {
			staffInfo = staffList.get(0);
		}
		return staffInfo;
	}

	@Override
	public boolean doLogout(String staffId) throws RestException {
		return loginHisService.updateInvalidToken(staffId);
	}

	@Override
	public String addStaff(String number, String realName, String position, String telphone, String email,
			Integer departId, String createdBy) throws RestException {
		StaffInfo staff = this.getStaffInfoByNumber(number);
		if (staff == null) {
			String staffId = IdentifierUtils.getId().generate().toString();
			staff = new StaffInfo();
			staff.setStaffId(staffId);
			staff.setNumber(number);
			staff.setPassword(MD5.md5(MD5.md5(String.valueOf(new Random().nextInt(899999) + 100000))));
			staff.setRealName(realName);
			staff.setPosition(position);
			staff.setTelphone(telphone);
			staff.setEmail(email);
			staff.setDepartId(departId);
			staff.setCreatedBy(createdBy);
			staff.setType(StaffInfo.STAFF_TYPE);
			staff.setStatus(Global.STATUS_NORMAL.toString());
			staff.setIsDeleted(Global.ISDEL_NORMAL.toString());
			staffDao.insert(staff);
			return staffId;
		} else {
			throw new RestException("120002", "工号已存在");
		}
	}

	@Override
	public void editStaff(String staffId, String number, String realName, String position, String telphone,
			String email, Integer departId, String createdBy) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, Object>> staffList = staffDao.find(params);
		if (CollectionUtils.isEmpty(staffList)) {
			throw new RestException("120001", "员工不存在");
		} else {
			StaffInfo staff = this.getStaffInfoByNumber(number);
			if (staff == null) {
				staff = new StaffInfo();
				staff.setStaffId(staffId);
				if (StringUtils.isNotEmpty(number)) {
					staff.setNumber(number);
				}
				if (StringUtils.isNotEmpty(realName)) {
					staff.setRealName(realName);
				}
				if (StringUtils.isNotEmpty(position)) {
					staff.setPosition(position);
				}
				if (StringUtils.isNotEmpty(telphone)) {
					staff.setTelphone(telphone);
				}
				if (StringUtils.isNotEmpty(email)) {
					staff.setEmail(email);
				}
				if (StringUtils.isNotEmpty(String.valueOf(departId))) {
					staff.setDepartId(departId);
				}
				staff.setUpdatedTime("1");
				staff.setCreatedBy(createdBy);
				staffDao.updateSelective(staff);
			} else {
				if (staff.getStaffId().equals(staffId)) {
					staff = new StaffInfo();
					staff.setStaffId(staffId);
					if (StringUtils.isNotEmpty(number)) {
						staff.setNumber(number);
					}
					if (StringUtils.isNotEmpty(realName)) {
						staff.setRealName(realName);
					}
					if (StringUtils.isNotEmpty(position)) {
						staff.setPosition(position);
					}
					if (StringUtils.isNotEmpty(telphone)) {
						staff.setTelphone(telphone);
					}
					if (StringUtils.isNotEmpty(email)) {
						staff.setEmail(email);
					}
					if (StringUtils.isNotEmpty(String.valueOf(departId))) {
						staff.setDepartId(departId);
					}
					staff.setUpdatedTime("1");
					staff.setCreatedBy(createdBy);
					staffDao.updateSelective(staff);
				} else {
					throw new RestException("120002", "工号已存在");
				}
			}
		}
	}

	@Override
	public void delStaff(String staffId, String opId) throws RestException {
		if (StringUtils.equals(staffId, opId)) {
			throw new RestException("120001", "您无法执行该操作");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, Object>> staffList = staffDao.find(params);
		if (CollectionUtils.isEmpty(staffList)) {
			throw new RestException("120001", "员工不存在");
		} else {
			StaffInfo staff = new StaffInfo();
			staff.setStaffId(staffId);
			staff.setIsDeleted(Global.ISDEL_DELETE.toString());
			staffDao.updateSelective(staff);
		}
	}

	@Override
	public Page<Map<String, Object>> getStaffList(String keyword, int pageIndex, int pageSize) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("type", StaffInfo.STAFF_TYPE);
		if (StringUtils.isNotEmpty(keyword)) {
			params.put("keyword", "%" + keyword + "%");
		}
		Page<Map<String, Object>> list = staffDao.page(params, pageIndex, pageSize);
		return list;
	}

	@Override
	public Map<String, Object> getStaffInfo(String staffId) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, Object>> staffList = staffDao.find(params);
		if (CollectionUtils.isEmpty(staffList)) {
			throw new RestException("120001", "员工不存在");
		} else {
			Map<String, Object> info = staffDao.findOne(staffId);
			return info;
		}
	}

	@Override
	public void bindStaffMenu(String staffId, Integer[] menuIds, Integer[] departIds) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, Object>> staffList = staffDao.find(params);
		if (CollectionUtils.isEmpty(staffList)) {
			throw new RestException("120001", "员工不存在");
		} else {
			for (Integer menuId : menuIds) {
				if (!menuService.isMenu(menuId)) {
					throw new RestException("120003", "菜单不存在");
				}
			}
			for (Integer departId : departIds) {
				DepartmentInfo dep = departmentInfoDao.get(departId);
				if (null == dep) {
					throw new RestException("120004", "部门不存在");
				}
			}
		}
		staffMenuInfoDao.delStaffMenu(staffId);
		for (Integer menuId : menuIds) {
			StaffMenuInfo staffMenu = new StaffMenuInfo();
			staffMenu.setStaffId(staffId);
			staffMenu.setMenuId(menuId);
			staffMenuInfoDao.insert(staffMenu);
		}
		staffOrgRelaDao.delStaffDep(staffId);
		for (Integer departId : departIds) {
			StaffOrgRela orgRela = new StaffOrgRela();
			orgRela.setStaffId(staffId);
			orgRela.setDepartId(departId);
			staffOrgRelaDao.insert(orgRela);
		}
	}

	@Override
	public Map<String, String> getEmailAndTelphoneByNumber(String number) throws RestException {
		Map<String, String> map = new HashMap<String, String>();
		StaffInfo staff = this.getStaffInfoByNumber(number);
		if (null == staff) {
			throw new RestException("9100030", "工号不存在");
		} else {
			String email = staff.getEmail();
			String telphone = staff.getTelphone();
			map.put("email", email);
			map.put("telphone", telphone);
			return map;
		}
	}

	@Override
	public boolean resetPasswordByEmail(String number) throws RestException {
		StaffInfo staff = this.getStaffInfoByNumber(number);
		if (null == staff) {
			throw new RestException("9100030", "工号不存在");
		} else {
			String ticketId = IdentifierUtils.getId().generate().toString();
			updateTicket(staff, ticketId, staff.getStaffId());
			Map<String, Object> formData = new HashMap<String, Object>();
			formData.put("placeName", staff.getRealName());
			formData.put("ticketId", ticketId);
			formData.put("website", website);
			String email = staff.getEmail();
			emailService.sendEmail(EmailSender.CBS, email, "【志鸿物流】-密码重置", "reset_password", formData);
			return false;
		}
	}

	@Override
	public String resetPasswordByTelphone(String number) throws RestException {
		StaffInfo staff = this.getStaffInfoByNumber(number);
		if (null == staff) {
			throw new RestException("9100030", "工号不存在");
		} else {
			String ticketId = IdentifierUtils.getId().generate().toString();
			updateTicket(staff, ticketId, staff.getStaffId());

			// String telphone = staff.getTelphone();
			// smsService.sendCaptchaSmsToPhone(telphone, SmsType.STAFF);
			return ticketId;
		}
	}

	@Override
	public ResetPasswordLog validTicket(String ticketId) throws RestException {
		ResetPasswordLog ticket = resetPasswordLogDao.get(ticketId);
		TicketValidator.validateTicket(ticket, minuteDiff);
		return ticket;
	}

	@Override
	public void resetPasswordByTicket(String ticketId, String password) throws RestException {
		ResetPasswordLog resetPassword = validTicket(ticketId);
		// StaffValidator.valid_password(password);
		StaffInfo staff = new StaffInfo();
		staff.setStaffId(resetPassword.getStaffId());
		staff.setPassword(MD5.md5(password));
		staffDao.updateSelective(staff);
		resetPassword.setStatus(Global.ISDEL_DELETE.toString());
		resetPassword.setExpirationTime("now()");
		resetPasswordLogDao.updateSelective(resetPassword);
	}

	private void updateTicket(StaffInfo staff, String ticketId, String createdBy) {
		String staffId = staff.getStaffId();
		ResetPasswordLog resetPassword = new ResetPasswordLog();
		resetPassword.setStaffId(staffId);
		resetPassword.setStatus(Global.ISDEL_DELETE.toString());
		// 将staffId所有的token设为失效
		resetPasswordLogDao.updateStatus(resetPassword);

		resetPassword.setTicketId(ticketId);
		resetPassword.setCreatedBy(createdBy);
		resetPassword.setStatus(Global.STATUS_NORMAL.toString());
		// 插入新的token
		resetPasswordLogDao.insert(resetPassword);
	}

	@Override
	public void changePassword(String staffId, String oldPassword, String newPassword) throws RestException {
		StaffInfo staff = staffDao.get(staffId);
		StaffValidator.valid_staff_password(staff, oldPassword, newPassword);
		staff.setStaffId(staffId);
		staff.setPassword(MD5.md5(newPassword));
		staffDao.updateSelective(staff);
	}

	@Override
	public List<Map<String, Object>> getDepartList(String name) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(name)) {
			params.put("name", "%" + name + "%");
		}
		List<Map<String, Object>> list = departmentInfoDao.find(params);
		return list;
	}

	@Override
	public Map<String, Object> getPermissionInfo(String staffId) throws RestException {
		Map<String, Object> staffInfo = getStaffInfo(staffId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		params.put("status", Global.STATUS_NORMAL.toString());
		List<Map<String, Object>> departInfo = staffOrgRelaDao.find(params);
		staffInfo.put("departInfo", departInfo);
		List<Menu> menuInfo = menuService.getUserMenu(staffId);
		if (CollectionUtils.isNotEmpty(menuInfo)) {
			staffInfo.put("menuInfo", menuInfo);
		} else {
			staffInfo.put("menuInfo", new ArrayList<Map<String, Object>>());
		}
		List<Integer> menuIds = menuService.getUserFunction(staffId);
		staffInfo.put("menuIds", menuIds);
		return staffInfo;
	}

	@Override
	public Page<Map<String, Object>> pageStaff(String queryType, String keyword, String number, String realName,
			String departName, String dataDepart, int pageIndex, int pageSize) throws RestException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", StaffInfo.STAFF_TYPE);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if (QUERYTYPE_NORMAL.equals(queryType)) {
			params.put("keyword", "%" + keyword + "%");
		} else if (QUERYTYPE_ADVANCE.equals(queryType)) {
			if (StringUtils.isNotEmpty(number)) {
				params.put("number", "%" + number + "%");
			}
			if (StringUtils.isNotEmpty(realName)) {
				params.put("realName", "%" + realName + "%");
			}
			if (StringUtils.isNotEmpty(departName)) {
				params.put("departName", "%" + departName + "%");
			}
			if (StringUtils.isNotEmpty(dataDepart)) {
				params.put("dataDepart", "%" + dataDepart + "%");
			}
		}
		Page<Map<String, Object>> page = staffDao.pageStaff(params, pageIndex, pageSize);
		List<Map<String, Object>> pageList = page.getRows();
		for (Map<String, Object> map : pageList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("staffId", map.get("staffId").toString());
			param.put("status", Global.STATUS_NORMAL.toString());
			List<Map<String, Object>> departInfo = staffOrgRelaDao.find(param);
			map.put("departInfo", departInfo);
		}
		return page;
	}

}
