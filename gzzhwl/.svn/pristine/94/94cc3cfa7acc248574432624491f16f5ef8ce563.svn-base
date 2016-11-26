package com.getui.java.template;

import com.gexin.rp.sdk.template.TransmissionTemplate;

public class TransmissionTemplateDemo {
	public static final String appId = "TxzlIyCcfS9KuENjjP4ux1";
	public static final String appKey = "rAnoicfrNX7915IxPocAL2";
	public static final String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
	public static final String CID = "e605a0db5ce3cca9b76b012978064940";
	
	public static TransmissionTemplate transmissionTemplateDemo() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent("请输入需要透传的内容");
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
	
	public static void main(String[] args) {
		transmissionTemplateDemo();
	}
	
}
