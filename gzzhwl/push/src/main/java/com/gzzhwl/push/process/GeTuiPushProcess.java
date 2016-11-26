package com.gzzhwl.push.process;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.push.annotation.PushProcess;
import com.gzzhwl.push.annotation.PushStrategy;
import com.gzzhwl.push.model.GeTuiAuth;
import com.gzzhwl.push.model.PushItem;

@PushStrategy(channel = "01", type = DeviceType.ANDROID)
public class GeTuiPushProcess implements PushProcess {
	@Autowired
	private GeTuiAuth auth;
	@Autowired
	private IGtPush push;

	@Override
	public boolean pushToSingleUser(PushItem item, String token) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(auth.getAppId());
		template.setAppkey(auth.getAppKey());
		template.setTransmissionType(2);
		String content = JSON.toJSONString(item.getParam());
		template.setTransmissionContent(content);
		SingleMessage message = new SingleMessage();
		message.setPushNetWorkType(0);
		message.setData(template);
		message.setOffline(false);
		Target target = new Target();
		target.setAppId(auth.getAppId());
		target.setClientId(token);
		IPushResult ret = push.pushMessageToSingle(message, target);
		String result = (String) ret.getResponse().get("result");
		return StringUtils.equals(result, "ok");
	}

}
