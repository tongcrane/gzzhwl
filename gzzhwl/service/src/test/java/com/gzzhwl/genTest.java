package com.gzzhwl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.gzzhwl.admin.lend.service.LendService;
import com.gzzhwl.admin.lend.vo.LendQryVo;
import com.gzzhwl.admin.source.service.SourceService;
import com.gzzhwl.admin.source.vo.SourceCommVo;
import com.gzzhwl.core.spring.SpringContext;

public class genTest {
	
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "development");
		
		 new ClassPathXmlApplicationContext("applicationContext.xml");

//		 QuotedHisService service = (QuotedHisService)ac.getBean(ac.getBeanNamesForType(QuotedHisServiceImpl.class)[0]);
		 
//		 System.out.println(JSON.toJSONString(service.getQuotedHisBySourceId("b73a7488-dab0-4a32-a631-804b6e09f622", QuotedType.QUOTED)));
//		 System.out.println(JSON.toJSONString(service.getQuotedHisList("b73a7488-dab0-4a32-a631-804b6e09f622")));
		 
		 
		 LendService service = SpringContext.getBean(LendService.class);
		 
//		 LendQryVo lendQryVo1 = new LendQryVo();
//		 
//		 lendQryVo1.setQueryType("0");
//		 lendQryVo1.setQueryContent("");
//		 
//		 System.out.println(JSON.toJSONString(service.getLendLogPage(lendQryVo1, 0, 10)));
//		 
//		 
//		 LendQryVo lendQryVo2 = new LendQryVo();
//		 
//		 lendQryVo2.setQueryType("1");
//		 lendQryVo2.setSortField("realReturnedTime");
//		 lendQryVo2.setEndLendEndTime("1234");
//		 
//		 System.out.println(JSON.toJSONString(service.getLendLogPage(lendQryVo2, 0, 10)));
//		 
		 
		 System.out.println(JSON.toJSONString(service.getLendLogDetail("0acd97ab-133f-476f-8ee9-e36b68f4e1fe")));
		 
//		 System.out.println(JSON.toJSONString(service.queryOrderSourcePage(sourceCommVo, 1, 10)));
//		 VehicleUsedInfoDao dao = (VehicleUsedInfoDao) ac.getBean(ac.getBeanNamesForType(VehicleUsedInfoDaoImpl.class)[0]);
//		 
//		 System.out.println( JSON.toJSONString(dao.page(null, 0, 10)));
//		 
//		 VehicleUsedInfoExtDao extdao = (VehicleUsedInfoExtDao) ac.getBean(ac.getBeanNamesForType(VehicleUsedInfoExtDaoImpl.class)[0]);
//		 
//		 System.out.println(JSON.toJSONString(extdao.page(null, 0, 10)));
		 
	}
	
}
