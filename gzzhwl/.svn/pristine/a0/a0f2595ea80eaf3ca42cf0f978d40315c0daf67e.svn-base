package com.getui.java.advancedpushmessage;

import java.util.ArrayList;
import java.util.List;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class MyJuniorPushTest {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换 
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
	static String appKey = "rAnoicfrNX7915IxPocAL2";
	static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5"; 
    static String devicetoken = "5b6f8c6cdc5ab8352a0f7357888308c17fe115d9d162c2a638ad336bc9f50f85";//iOS设备唯一标识，由苹果那边生成
    static String url ="http://sdk.open.api.igexin.com/apiex.htm";

    public static void apnpush() throws Exception {
       IGtPush push = new IGtPush(url, appKey, masterSecret);

       APNTemplate t = new APNTemplate();
       APNPayload apnpayload = new APNPayload();
       apnpayload.setSound("");
       APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
       //通知文本消息标题
       alertMsg.setTitle("aaaaaa");
       //通知文本消息字符串
       alertMsg.setBody("bbbb");
       //对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
       alertMsg.setTitleLocKey("ccccc");
       //指定执行按钮所使用的Localizable.strings
       alertMsg.setActionLocKey("ddddd");
       apnpayload.setAlertMsg(alertMsg);

       t.setAPNInfo(apnpayload);  
       ListMessage message = new ListMessage();
       message.setData(t);
       String contentId = push.getAPNContentId(appId, message);
       System.out.println(contentId);
       List<String> dtl = new ArrayList<String>();
       dtl.add(devicetoken);
       System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
       IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
       System.out.println(ret.getResponse());
    }

    public static void main(String[] args) throws Exception {
       apnpush();
    }
}