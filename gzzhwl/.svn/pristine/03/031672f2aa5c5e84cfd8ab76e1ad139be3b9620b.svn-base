package com.getui.java.advancedpushmessage;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;
public class DelBlackCidListDemo{
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    static String appId = "TxzlIyCcfS9KuENjjP4ux1";
    static String appKey = "rAnoicfrNX7915IxPocAL2";
    static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
	static String CID = "e605a0db5ce3cca9b76b012978064940";
    public static void testRestoreCidList() {
        List<String> cidList = new ArrayList<String>();
        cidList.add(CID);
        IGtPush push = new IGtPush(appKey, masterSecret);
        IPushResult pushResult = push.restoreCidListFromBlk(appId, cidList);
        System.out.println("黑名单删除结果：" + pushResult.getResultCode());
    }

    public static void main(String[] args) {
        testRestoreCidList();
    }
}