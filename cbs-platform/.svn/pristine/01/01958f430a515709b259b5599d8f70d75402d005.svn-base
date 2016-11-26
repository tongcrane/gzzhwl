package com.gzzhwl.cbs.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gzzhwl.cbs.exception.HttpException;
import com.gzzhwl.cbs.exception.RestException;
import com.gzzhwl.cbs.model.HttpConfig;
import com.gzzhwl.cbs.model.RemotingResp;
import com.gzzhwl.cbs.service.UserService;
import com.gzzhwl.cbs.support.HttpSupport;
import com.gzzhwl.cbs.utils.MD5;
import com.gzzhwl.cbs.vo.LoginVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private HttpSupport httpSupport;

	@Override
	public LoginVO doLogin(String number, String password) throws RestException {
		String url = "/admin/security/login";
		Map<String, String> param = new HashMap<String, String>();
		param.put("number", number);
		param.put("password", MD5.md5(password));
		try {
			String json = httpSupport.postAsForm(url, param, HttpConfig.createDefault());
			RemotingResp<LoginVO> resp = JSON.parseObject(json, new TypeReference<RemotingResp<LoginVO>>() {
			});
			if (resp.isSuccess()) {
				return resp.getData();
			} else {
				throw new RestException(resp.getStatus());
			}
		} catch (HttpException e) {
			throw new RestException("90009", "无法连接到接口服务器");
		} catch (RestException e) {
			throw e;
		} catch (Exception e) {
			throw new RestException("90008", "调用接口发生内部错误");
		}
	}

}
