package com.getui.java.advancedpushmessage;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class MyJuniorPushDemo {
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
	static String appKey = "rAnoicfrNX7915IxPocAL2";
	static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
    static String devicetoken = "5b6f8c6cdc5ab8352a0f7357888308c17fe115d9d162c2a638ad336bc9f50f85";//iOS设备唯一标识，由苹果那边生成
    static String url = "http://sdk.open.api.igexin.com/apiex.htm";
       public static void apnpush() throws Exception {
              IGtPush push = new IGtPush(url, appKey, masterSecret);  
              APNTemplate t = new APNTemplate();
              APNPayload apnpayload = new APNPayload();
              apnpayload.setSound("");
              //apn高级推送
              APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
              ////通知文本消息标题
              alertMsg.setTitle("aaaaaa");
              //通知文本消息字符串
              alertMsg.setBody("bbbb");
              //对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
              alertMsg.setTitleLocKey("ccccc");
              //指定执行按钮所使用的Localizable.strings
              alertMsg.setActionLocKey("ddddd");
              apnpayload.setAlertMsg(alertMsg);

              t.setAPNInfo(apnpayload);
              SingleMessage sm = new SingleMessage();
              sm.setData(t);
              IPushResult ret0 = push.pushAPNMessageToSingle(appId, devicetoken, sm);
              System.out.println(ret0.getResponse());

       }

       public static void main(String[] args) throws Exception {
              apnpush();
       }
}