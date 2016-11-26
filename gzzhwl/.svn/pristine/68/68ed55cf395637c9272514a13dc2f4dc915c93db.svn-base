package com.getui.java.advancedpushmessage;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;
public class AliasFunction2 {
   //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
    static String appKey = "rAnoicfrNX7915IxPocAL2";
    static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5"; 
	static String Alias = "个推1";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	public static void main(String[] args) throws Exception {
	    IGtPush push = new IGtPush(host, appKey, masterSecret);
	    IAliasResult queryClient = push.queryClientId(appId, Alias);
	    System.out.println("根据别名获取的CID：" + queryClient.getClientIdList());
	}
}