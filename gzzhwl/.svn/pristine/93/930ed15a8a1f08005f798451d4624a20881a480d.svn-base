package com.getui.java.advancedpushmessage;

import java.util.ArrayList;
import java.util.List;
import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;

public class AliasFunction1 {
   //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	static String appId = "TxzlIyCcfS9KuENjjP4ux1";
    static String appKey = "rAnoicfrNX7915IxPocAL2";
    static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";   
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void main(String[] args) {
	  List<Target> Lcids = new ArrayList<Target>();
	  Target target1 = new Target();
	  Target target2 = new Target();
	  target1.setClientId("e605a0db5ce3cca9b76b012978064940");
	  target1.setAlias("个推1");
	  target2.setClientId("23170b169630706f82baf94c8a2b8923");
	  target2.setAlias("个推2");
	  Lcids.add(target1);
	  Lcids.add(target2);
	  IGtPush push = new IGtPush(host, appKey, masterSecret);
	  IAliasResult bindLCid = push.bindAlias(appId, Lcids);
	  System.out.println(bindLCid.getResult());
	  System.out.println(bindLCid.getErrorMsg());
   }
}
