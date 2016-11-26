package com.getui.java.advancedpushmessage;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.http.IGtPush;

public class GetUserTagsDemo {
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
    static String appKey = "rAnoicfrNX7915IxPocAL2";
    static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
	static String CID = "e605a0db5ce3cca9b76b012978064940";
	
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) throws Exception {
        getUserTags();
    }

    public static void getUserTags() {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        IPushResult result = push.getUserTags(appId, CID);
        System.out.println(result.getResponse().toString());
    }
}