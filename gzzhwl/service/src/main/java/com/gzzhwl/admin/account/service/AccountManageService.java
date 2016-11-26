package com.gzzhwl.admin.account.service;

import com.gzzhwl.core.data.model.AccountInfo;

public interface AccountManageService {

	boolean createAccountForCBS(String idno, String realName, String telphone, String idFImageRefId,
			String idBImageRefId, String staffId);

	AccountInfo getAccountInfoByParam(String telphone, boolean isDel);

	boolean updateAccountForCBS(String idno, String realName, String telphone, String oldTel, String idFImageRefId,
			String idBImageRefId, String staffId);

}
