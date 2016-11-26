package com.getui.java.advancedpushmessage;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;

public class AliasFunction3 {
   //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
    static String appKey = "rAnoicfrNX7915IxPocAL2";
    static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
	static String CID = "e605a0db5ce3cca9b76b012978064940";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    public static void main(String[] args) throws Exception {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        IAliasResult queryAlias = push.queryAlias(appId, CID);
        System.out.println("根据CID获取的别名：" + queryAlias.getAlias());
    }
}