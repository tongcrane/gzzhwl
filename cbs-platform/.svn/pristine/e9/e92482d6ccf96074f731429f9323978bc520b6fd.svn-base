/**
 * 
 */
var CBSAddOrder = function(options){
	this.opt = $.extend({}, options);
	this.pcdColumns = $('.pcd-column');
	this.pcdList = {};
	this.modify = false;
	this.geo = null;
	var _this = this;
	
	$('select.baseInfo-column').each(function(i, j){
		var minWidth = $(j).data('width');
		$(j).multiselect({
			minWidth : minWidth,
			header : false,
			multiple : false,
			selectedList : 1,
			noneSelectedText: '请选择',
		})
	});
	
	$('.agreement-select').multiselect({
    	header : false,
		multiple : false,
		selectedList : 1,
		noneSelectedText: '请选择',
		click : function(event, ui) {
			_this.selectAgreement(ui.id);
		}
    });
	
	this.selectAgreement = function(agreementId){
		if(agreementId != ''){
			$.get(global.server + '/admin/agreement/getAgreementDetail', {agreementId:agreementId}, function(msg){
				if (msg && msg.status && msg.status.statusCode == global.status.success) {
					var data = msg.data;
					_this.fillAgreementData(data);
				}
			});
		}
	};
	
	this.fillAgreementData = function(data){
		$('input.agreement-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('agreement');
			self.val(data[_p]);
		});
		$('select.agreement-column').each(function(i, j){
			var self = $(j);
			var _p = self.data('agreement');
			self.multiselect('setValue',data[_p]);
		});
		_this.startPC.selectValue(data.startCodeP, data.startCodeC);
		_this.endPC.selectValue(data.endCodeP, data.endCodeC);
		
		if(!this.opt.orderId) {
			_this.fillOtherData(data.customerId,data.agreementId,data);
		}
		
	}
	
	this.fillOtherData=function(customerId,agreementId,data) {
		var otherData = {
			"orderDetailInfo" : {
//				"orderId" : "7d2a69f0-ab56-49af-8134-b36fc4d1c288",
//				"orderNo" : "OR2E2F0D88FAF",
//				"infoId" : "d229a96b-97df-416a-ac83-dfd51baa3dba",
//				"goodsWeight" : "99999.0",
//				"goodsVolume" : "999.0",
//				"status" : "01",
//				"createdBy" : "1c4b00ff-e194-4fbe-8931-7d9188ed8031",
//				"createdTime" : "2016-09-22 16:10:48",
//				"updatedBy" : "7e7cfc7e-ef2c-4256-9288-28bdf48c65fa",
//				"updatedTime" : "2016-09-22 16:55:33",
//				"isDeleted" : "00"
			},
			"orderLineInfo" : {
//				"endCodeCCn" : "天津",
//				"endCodePCn" : "天津",
//				"transferCodeCCn" : "",
//				"startCodeC" : "500100",
//				"startCodePCn" : "重庆",
//				"startCodeCCn" : "重庆",
//				"infoId" : "d229a96b-97df-416a-ac83-dfd51baa3dba",
//				"transferCodePCn" : "",
//				"transferCodeP" : null,
//				"endCodeP" : "120000",
//				"startCodeP" : "500000",
//				"transferCodeC" : null,
//				"endCodeC" : "120100"
			},
			"chargeInfo" : {
//				"chargeId" : "9a6ea2dc-47d6-11e6-b2f0-000c29ce1409",
//				"name" : "公里数",
//				"unit" : "元/公里"
			},
			"orderReceiverInfo" : {
				"customerName" : "东方日升有限公司",
				"area" : [ {
					"regionId" : 1024,
					"regionCode" : "230108",
					"regionName" : "平房区",
					"parentRegionId" : 94,
					"regionLevel" : 3,
					"regionOrder" : 0.0,
					"regionNameEn" : "Pingfang Qu"
				}, {
					"regionId" : 94,
					"regionCode" : "230100",
					"regionName" : "哈尔滨",
					"parentRegionId" : 9,
					"regionLevel" : 2,
					"regionOrder" : 0.0,
					"regionNameEn" : "Harbin Shi"
				}, {
					"regionId" : 9,
					"regionCode" : "230000",
					"regionName" : "黑龙江",
					"parentRegionId" : 1,
					"regionLevel" : 1,
					"regionOrder" : 0.0,
					"regionNameEn" : "Heilongjiang Sheng"
				} ],
				"address" : "收货路1234号",
				"areaCode" : "230108",
				"contactName" : "王云飞",
				"longitude" : "126.534967",
				"infoId" : "d229a96b-97df-416a-ac83-dfd51baa3dba",
				"latitude" : "45.803775",
				"mobile" : "18674983214",
				"telphone" : "0469-87654433"
			},
			"orderBaseInfo" : {
				"addrId" : "82604558-77fb-11e6-b0a3-000c29ce1409",
//				"needStartTime" : "2016-09-24 16:11:00",
//				"customerBillNo" : "单号000001",
//				"remark" : "自动建单数据模板",
//				"unitPrice" : "60.00",
				"type" : "01",
//				"updatedBy" : "7e7cfc7e-ef2c-4256-9288-28bdf48c65fa",
//				"lineAttribute" : "往返",
				"goodsWeight" : "99999.0",
//				"updatedTime" : "2016-09-22 16:55:33",
//				"needLength" : "7.2",
				"departName" : "重庆运营部",
				"goodsVolume" : "999.0",
//				"needArriveTime" : "2016-09-30 16:11:00",
				"orderAdvice" : "身份证",
//				"chargeId" : "9a6ea2dc-47d6-11e6-b2f0-000c29ce1409",
//				"createdTime" : "2016-09-22 16:10:48",
				"orderType" : "整车",
//				"paymentType" : "回单",
				"isPredict" : "02",
//				"agreementId" : "76fcb93d-8399-4f69-8808-37f348d47e16",
				"belongDepartId" : "121",
//				"needImportedVehicles" : "是",
				"infoId" : "d229a96b-97df-416a-ac83-dfd51baa3dba",
				"miles" : "99998.0",
//				"isDeleted" : "00",
//				"needType" : "封闭式货车",
//				"needOwnVehicles" : "是",
//				"pickUpTime" : "2016-09-23 16:11:00",
//				"createdBy" : "1c4b00ff-e194-4fbe-8931-7d9188ed8031",
				"goodsName" : "USB接口电灯"
			},
			"agreementInfo" : {
//				"endCodeCCn" : "天津",
//				"endCodePCn" : "天津",
//				"remark" : null,
//				"carryType" : "整车",
//				"executeCycle" : null,
//				"startCodePCn" : "重庆",
//				"startCodeC" : "500100",
//				"startCodeCCn" : "重庆",
//				"unitPrice" : "60"
//				"endTime" : null,
//				"endCodeP" : "120000",
//				"updatedBy" : "d7dd1a52-5234-11e6-8107-6c92bf2c1729",
//				"startTime" : null,
//				"customerId" : "52ccb9ff-cf9e-49b0-8b56-70a97b9bdf46",
//				"lineAttribute" : "往返",
//				"updatedTime" : "2016-09-22 17:49:26",
//				"needLength" : "7.2",
//				"chargeId" : "9a6ea2dc-47d6-11e6-b2f0-000c29ce1409",
//				"createdTime" : "2016-09-22 17:49:22",
//				"status" : "00",
//				"paymentType" : "回单",
//				"agreementId" : "76fcb93d-8399-4f69-8808-37f348d47e16",
//				"needImportedVehicles" : "是",
//				"isDeleted" : "00",
//				"needType" : "封闭式货车",
//				"transferCodeP" : null,
//				"needOwnVehicles" : "是",
//				"createdBy" : "d7dd1a52-5234-11e6-8107-6c92bf2c1729",
//				"startCodeP" : "500000",
//				"transferCodeC" : null,
//				"endCodeC" : "120100"
			},
			"orderSenderInfo" : {
//				"customerName" : "金城集团",
				"area" : [ {
					"regionId" : 2606,
					"regionCode" : "500112",
					"regionName" : "渝北区",
					"parentRegionId" : 270,
					"regionLevel" : 3,
					"regionOrder" : 0.0,
					"regionNameEn" : "Yubei Qu"
				}, {
					"regionId" : 270,
					"regionCode" : "500100",
					"regionName" : "重庆",
					"parentRegionId" : 23,
					"regionLevel" : 2,
					"regionOrder" : 0.0,
					"regionNameEn" : "Shixiaqu"
				}, {
					"regionId" : 23,
					"regionCode" : "500000",
					"regionName" : "重庆",
					"parentRegionId" : 1,
					"regionLevel" : 1,
					"regionOrder" : 0.0,
					"regionNameEn" : "Chongqing Shi"
				} ],
//				"customerId" : "52ccb9ff-cf9e-49b0-8b56-70a97b9bdf46",
				"address" : "重庆渝北宝环3路100",
				"areaCode" : "500112",
				"contactName" : "张大千",
				"infoId" : "d229a96b-97df-416a-ac83-dfd51baa3dba",
				"mobile" : "13966751909",
				"telphone" : "0482-76548811-1225"
			}
		};
		

//		var agreementId = data.orderBaseInfo.agreementId;
//		var customerId = data.orderSenderInfo.customerId;
		_this.getCustomerData(customerId,agreementId);
		
		var sender = otherData.orderSenderInfo;
		$('.sender-column').each(function(i, j){
			var _p = $(j).data('column');
			$(j).val(sender[_p]);
		});
		
		var receiver = otherData.orderReceiverInfo;
		$('.receiver-column').each(function(i, j){
			var _p = $(j).data('column');
			$(j).val(receiver[_p]);
		});
		
		var lng = receiver.longitude;
		var lat = receiver.latitude;
		if(lng && lat){
			var geo = {
				lng : lng,
				lat : lat
			}
			_this.geo = geo;
		}
		
		_this.pcdColumns.each(function(i, j){
//	 		$(this).append('<select class="pcd-select-p"></select>');
//	 		$(this).append('<select class="pcd-select-c"></select>');
//	 		$(this).append('<select class="pcd-select-d"></select>');
	 		var category = $(this).data('column');
			var pcd = new CBSPcdSelect({width:100,category:category,showEmpty:false,initCode:receiver[category]}, $(this));
			_this.pcdList.receiver = pcd;
	 	});
		
		var baseInfo = $.extend({},otherData.orderBaseInfo,otherData.orderDetailInfo,data);
		$('.baseInfo-column').each(function(i, j){
			var _p = $(j).data('column');
			if(_p == 'needStartTime' || _p == 'needArriveTime' || _p == 'pickUpTime'){
				var value = baseInfo[_p];
				if(value){
					var _v = moment(value, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
					$(j).val(_v);
				}
			} else {
				$(j).val(baseInfo[_p]);
			}
		});
		
		_this.getAddrData(baseInfo.addrId);
		
		
		$('select._baseInfo').each(function(i, j){
			$(j).multiselect('refresh');
		});
	}
	
	this.enableEdit = function(data){
		var status = data.orderDetailInfo.status;
		var consign = data.consignmentInfo;
		if(status == '01' && consign == null){
			return true;
		} else {
			return false;
		}
	}
	
	
	this.init = function(options){
		var _this = this;
		_this.getChargeData();
		_this.startPC = new CBSPCSelect($("#startCodeP"),$('#startCodeC'));
		_this.endPC = new CBSPCSelect($("#endCodeP"),$('#endCodeC'));
		
		$('.use-date').each(function(i, j){
			$(this).datetimepicker({
        		timepicker:true,
        		format:'Y-m-d H:i',
        		autoclose:true,
        		todayHighlight:true,
        		keyboardNavigation:false
    		});
		});
		
		$('.map_btn').click(function(){
			_this.showMap();
		});
		
		if (options && options.orderId) {
			console.log('init order data with id: ' + options.orderId);
            var url = "/admin/order/getOrderDetail?orderId=" + options.orderId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    console.log(data)
                    var enable = _this.enableEdit(data);
                    if(!enable){
                    	alert("当前状态不允许修改订单");
                    	window.close();
                    } else {
                    	_this.modify = true;
                    	$('title').text('修改订单');
                    	_this.fillData(data);
                    }
                }
            });
		} else {
			console.log("init order");
			_this.getCustomerData();
			_this.getAddrData();
			_this.pcdColumns.each(function(i, j){
	     		$(this).append('<select class="pcd-select-p"></select>');
	     		$(this).append('<select class="pcd-select-c"></select>');
	     		$(this).append('<select class="pcd-select-d"></select>');
	     		var category = $(this).data('column');
	     		var pcd = new CBSPcdSelect({width:100,category:category,showEmpty:false}, $(this));
				_this.pcdList.receiver = pcd;
	     	});
		}
	}
	
	this.getChargeData = function(){
		var _this = this;
		$.get(global.server + '/admin/order/charge', function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
                var data = msg.data;
                var el = $('<option data-id="" value="">请选择</option>');
                $('.charge-select').append(el);
                $.each(data, function(i, j){
                	var el = $('<option data-type="' +j.unit + '" data-id="'+ j.chargeId +'" value="' + j.chargeId + '">' + j.name +'</option>');
                	$('.charge-select').append(el);
                });
            } 
        }).done(function(msg){
        	 $('.charge-select').multiselect({
             	header : false,
     			multiple : false,
     			selectedList : 1,
     			noneSelectedText: '请选择',
     			click : function(event, ui) {
     				var data = {type:ui.type,id:ui.id};
     				_this.changeCharge(data);
     			}
             });
        });
	};
	
	
	this.changeCharge = function(data){
		$('.rela_tive .unit').text(data.type);
	}
	
	this.getAddrData = function(initId){
		var _this = this;
		
		new CBSSelect({
			target : $('.addr-select'),
			url:'/admin/store/addr',
			minWidth : $('.addr-select').data('width'),
			method : 'get',
		    valueField :  'addrId',
		    textField : 'addrName',
		    initValue : initId,
		    onChange : function(row){
		    	if(row != null){
		    		var addrd = row.provinceCn + row.cityCn + row.districtCn + row.address;
		    		$('span[data-column="addr-address"]').text(addrd);
		    	} else {
		    		$('span[data-column="addr-address"]').text('');
		    	}
		    }
		});
		
	}
	
	this.getCustomerData = function(initId,agreementId){
		var _this = this;
		new CBSSelect({
			target : $('.customer-select'),
			url:'/admin/customer/queryCustList',
			minWidth : $('.customer-select').data('width'),
			method : 'get',
		    valueField :  'customerId',
		    textField : 'fullName',
		    initValue : initId,
		    onChange : function(row){
		    	if(row != null){
		    		$('.sender-column[data-column="customerName"]').val(row.fullName);
		    		_this.getAgreementData(row.customerId,agreementId);
		    	} else {
		    		$('.sender-column[data-column="customerName"]').val('');
		    		_this.getAgreementData(null);
		    	}
		    }
		});
	};
	
	//清空数据
//	this.emptyData=function() {
//		var otherData = {
//				"orderDetailInfo" : {
//				},
//				"orderLineInfo" : {
//				},
//				"chargeInfo" : {
//				},
//				"orderReceiverInfo" : {
//				},
//				"orderBaseInfo" : {
//				},
//				"agreementInfo" : {
//				},
//				"orderSenderInfo" : {
//				}
//			};
//        var sender = otherData.orderSenderInfo;
//		$('.sender-column').each(function(i, j){
//			var _p = $(j).data('column');
//			$(j).val(sender[_p]);
//		});
//		
//		var receiver = otherData.orderReceiverInfo;
//		$('.receiver-column').each(function(i, j){
//			var _p = $(j).data('column');
//			$(j).val(receiver[_p]);
//		});
//		
//		var lng = receiver.longitude;
//		var lat = receiver.latitude;
//		if(lng && lat){
//			var geo = {
//				lng : lng,
//				lat : lat
//			}
//			_this.geo = geo;
//		}
//		
//		_this.pcdColumns.each(function(i, j){
//	 		var category = $(this).data('column');
//			var pcd = new CBSPcdSelect({width:100,category:category,showEmpty:false,initCode:receiver[category]}, $(this));
//			_this.pcdList.receiver = pcd;
//	 	});
//		
//		var baseInfo = $.extend({},otherData.orderBaseInfo,otherData.orderDetailInfo);
//		$('.baseInfo-column').each(function(i, j){
//			var _p = $(j).data('column');
//			if(_p == 'needStartTime' || _p == 'needArriveTime' || _p == 'pickUpTime'){
//				var value = baseInfo[_p];
//				if(value){
//					var _v = moment(value, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
//					$(j).val(_v);
//				}
//			} else {
//				$(j).val(baseInfo[_p]);
//			}
//		});
//		
//		_this.getAddrData(baseInfo.addrId);
//	}
	
	this.getAgreementData = function(custId, initId){
		_this=this;
		if(custId != null){
			$.get(global.server + '/admin/agreement/getAgreements', {custId:custId}, function(msg){
				if (msg && msg.status && msg.status.statusCode == global.status.success) {
					var data = msg.data;
					$('.agreement-select').empty();
					var el = $('<option data-id="" value="">请选择</option>');
	                $('.agreement-select').append(el);
					$.each(data, function(i, j){
						var el = null;
						if(initId){
							if(initId == j.agreementId){
								el = $('<option selected data-id="'+ j.agreementId +'" value="' + j.agreementId + '">' + j.startCodePCn + "-" + j.endCodePCn + "+" + j.lineAttribute + "+" + j.needLength + "m" +'</option>');
							} else {
								el = $('<option data-id="'+ j.agreementId +'" value="' + j.agreementId + '">' + j.startCodePCn + "-" + j.endCodePCn + "+" + j.lineAttribute + "+" + j.needLength + "m" +'</option>');
							}
						} else {
							el = $('<option data-id="'+ j.agreementId +'" value="' + j.agreementId + '">' + j.startCodePCn + "-" + j.endCodePCn + "+" + j.lineAttribute + "+" + j.needLength + "m" +'</option>');
						}
	                	$('.agreement-select').append(el);
	                });
					$('.agreement-select').multiselect('refresh');
				}
			});
		} else {
			$('.agreement-select').empty();
			var el = $('<option data-id="" value="">请选择</option>');
            $('.agreement-select').append(el);
            $('.agreement-select').multiselect('refresh');
		}
		
	}
	
	
	this.init(options);
}

CBSAddOrder.prototype.fillData = function(data){
	var _this = this;
	
	var orderNo=data.orderDetailInfo.orderNo;
	$('#orderNo').val(orderNo);
	
	var agreementId = data.orderBaseInfo.agreementId;
	if(agreementId){//有合同
		_this.getAgreementData(customerId, agreementId);
    	_this.selectAgreement(agreementId);
	} else {//没有合同
		_this.getAgreementData(customerId);
		var orderLineInfo = data.orderLineInfo;
		
		_this.startPC.selectValue(orderLineInfo.startCodeP, orderLineInfo.startCodeC);
		_this.endPC.selectValue(orderLineInfo.endCodeP, orderLineInfo.endCodeC);
	}
	
	var customerId = data.orderSenderInfo.customerId;
	_this.getCustomerData(customerId,agreementId);
	
	var sender = data.orderSenderInfo;
	$('.sender-column').each(function(i, j){
		var _p = $(j).data('column');
		$(j).val(sender[_p]);
	});
	
	var receiver = data.orderReceiverInfo;
	$('.receiver-column').each(function(i, j){
		var _p = $(j).data('column');
		$(j).val(receiver[_p]);
	});
	
	var lng = receiver.longitude;
	var lat = receiver.latitude;
	if(lng && lat){
		var geo = {
			lng : lng,
			lat : lat
		}
		_this.geo = geo;
	}
	
	_this.pcdColumns.each(function(i, j){
 		$(this).append('<select class="pcd-select-p"></select>');
 		$(this).append('<select class="pcd-select-c"></select>');
 		$(this).append('<select class="pcd-select-d"></select>');
 		var category = $(this).data('column');
		var pcd = new CBSPcdSelect({width:100,category:category,showEmpty:false,initCode:receiver[category]}, $(this));
		_this.pcdList.receiver = pcd;
 	});
	
	var baseInfo = $.extend({},data.orderBaseInfo,data.orderDetailInfo);
	$('.baseInfo-column').each(function(i, j){
		var _p = $(j).data('column');
		if(_p == 'needStartTime' || _p == 'needArriveTime' || _p == 'pickUpTime'){
			var value = baseInfo[_p];
			if(value){
				var _v = moment(value, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
				$(j).val(_v);
			}
		} else {
			$(j).val(baseInfo[_p]);
		}
	});
	
	_this.getAddrData(baseInfo.addrId);
	
	
	$('select.baseInfo-column').each(function(i, j){
		$(j).multiselect('refresh');
	});
	
	
}

CBSAddOrder.prototype.doSave = function () {
    var _this = this;
    var result = _this.getJsonDataFromContent();
    console.log(result)
    var valid = _this.validToPost(result);
    if(valid){
    	if(_this.modify){
    		result.baseInfo.orderId = _this.opt.orderId;
    	}
    	var dataStringify = JSON.stringify(result);
    	
    	var url = '/admin/order/create';
    	if(_this.modify){
    		url = '/admin/order/modify';
    	}
        $.ajax({
            url: global.server + url,
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: dataStringify,
            dataType: "Json",
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	var orderId = msg.data;
                	alert("订单保存成功");
                	window.close();
                	window.opener.location.reload();
                } else {
                	alert(msg.status.errorMsg);
                }
            }
        });
    }
};

CBSAddOrder.prototype.getJsonData = function(selector){
	var data = {};
	$(selector).each(function(i,j){
		var _this = $(this);
		var pName = _this.data("column");
		var pValue = _this.val();
		data[pName] = pValue;
	});
	return data;
}

CBSAddOrder.prototype.getJsonDataFromContent = function(){
	var _this = this;
	var data = {};
	var baseInfo = _this.getJsonData('.baseInfo-column');
	var lineInfo = _this.getJsonData('.lineInfo-column');
	
	var sender = _this.getJsonData('.sender-column');
	
	var receiver = _this.getJsonData('.receiver-column');
	var receiverPCD = _this.pcdList.receiver;
	var receiverData = receiverPCD.getValue();
	receiver[receiverData.category] = receiverData.code;
	
	var geo = _this.geo;
	if(geo != null){
		receiver.longitude = geo.lng;
		receiver.latitude = geo.lat;
	} else {
		//TODO:没有经纬度自动反解析一个点
		receiver.longitude = "";
		receiver.latitude = "";
	}
	
	data.baseInfo = baseInfo;
	data.lineInfo = lineInfo;
	data.receiver = receiver;
	data.sender = sender;
	
	
	return data;
};

CBSAddOrder.prototype.validToPost = function(data){
	var _this = this;
	var customerId = data.sender.customerId;

	if(customerId == ''){
		alert("请选择客户");
		return false;
	}
	var chargeId = data.baseInfo.chargeId;
	if(chargeId == ''){
		alert("请选择计价方式");
		return false;
	}
	var hasError = false;
	var errorCatalog = null;
	var errorProperties = null;
	if(!hasError){
		var baseInfo = data.baseInfo;
		$.each(baseInfo, function(i, j){
			if(i != 'customerBillNo' && i != 'agreementId' && i != 'remark' && i != 'miles'){
				if(j ==''){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = i;
					return false;
				}
			}
		});
		
		if(!hasError){
			var d_a = moment(baseInfo.needArriveTime, "YYYY-MM-DD HH:mm");//到达时限
			var d_s = moment(baseInfo.needStartTime, "YYYY-MM-DD HH:mm");//计划发车
			var d_p = moment(baseInfo.pickUpTime, "YYYY-MM-DD HH:mm");//提货时间
			if(!d_p.isBefore(d_s)){//如果提货在发车之后
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "timeDiffA";
			} else if(!d_s.isBefore(d_a)){//如果发车在到达之后
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "timeDiffB";
			}
		}
		
		if(!hasError){
			var chargeId = baseInfo.chargeId;
			if(chargeId == '9a6ea2dc-47d6-11e6-b2f0-000c29ce1409' && baseInfo.miles == ''){//按公里数计费方式
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = 'miles';
			}
		}
		if(!hasError){
			var miles = parseInt(baseInfo.miles);
			if(isNaN(miles)){
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "miles";
			} else {
				$('.baseInfo-column[data-column="miles"]').val(miles.toFixed(1));
				if(miles <= 0){
			    	hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightMiles";
			    } else if(miles > 100000){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightMiles";
				} else {
			    	baseInfo.miles = miles.toFixed(1);
			    }
			}
		}
		if(!hasError){
			var unitPrice = parseInt(baseInfo.unitPrice);
			if(isNaN(unitPrice)){
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "unitPrice";
			} else {
				$('.baseInfo-column[data-column="unitPrice"]').val(unitPrice.toFixed(2));
				if(unitPrice <= 0){
			    	hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "unitPrice";
			    } else if(unitPrice > 40000){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "unitPrice";
				} else {
			    	baseInfo.unitPrice = unitPrice.toFixed(2);
			    }
			}
		}
		if(!hasError){
			var goodsWeight = parseInt(baseInfo.goodsWeight);
			if(isNaN(goodsWeight)){
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "goodsWeight";
			} else {
				$('.baseInfo-column[data-column="goodsWeight"]').val(goodsWeight.toFixed(1));
				if(goodsWeight <= 0){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightGoodsWeight";
				} else if(goodsWeight > 100000){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightGoodsWeight";
				} else {
					baseInfo.goodsWeight = goodsWeight.toFixed(1);
				}
			}
			
		}
		if(!hasError){
			var goodsVolume = parseInt(baseInfo.goodsVolume);
			if(isNaN(goodsVolume)){
				hasError = true;
				errorCatalog = 'baseInfo';
				errorProperties = "goodsVolume";
			} else {
				$('.baseInfo-column[data-column="goodsVolume"]').val(goodsVolume.toFixed(1));
				if(goodsVolume <= 0){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightGoodsVolume";
				} else if(goodsVolume > 1000){
					hasError = true;
					errorCatalog = 'baseInfo';
					errorProperties = "rightGoodsVolume";
				} else {
					baseInfo.goodsVolume = goodsVolume.toFixed(1);
				}
			}
		}
	}
	
	if(!hasError){
		var lineInfo = data.lineInfo;
		$.each(lineInfo, function(i, j){
			if(i !='startCodeC' && i!= 'endCodeC'){
				if(j ==''){
					hasError = true;
					errorCatalog = 'lineInfo';
					errorProperties = i;
					return false;
				}
			}
		});
	}
	
	if(!hasError){
		var sender = data.sender;
		$.each(sender, function(i, j){
			if(i != 'mobile' && i != 'telphone'){
				if(j ==''){
					hasError = true;
					errorCatalog = 'sender';
					errorProperties = i;
					return false;
				}
			}
		});
		
		if(!hasError){
			if(sender.mobile =='' && sender.telphone == ''){
				errorCatalog = 'sender';
				errorProperties = 'mobile';
				hasError = true;
			}
			if(!hasError){
				if(sender.telphone!=''){
					var reg = /^(0\d{2,3}-)?\d{7,8}(-\d{1,6})?$/;
					var valid = reg.test(sender.telphone);
					if(!valid){
						errorCatalog = 'sender';
						errorProperties = 'telphone';
						hasError = true;
					}
				}
			}
		}
		
	}
	
	if(!hasError){
		var receiver = data.receiver;
		$.each(receiver, function(i, j){
			if(i != 'longitude' && i != 'latitude' && i != 'customerName' && i != 'mobile' && i != 'telphone'){
				if(j ==''){
					hasError = true;
					errorCatalog = 'receiver';
					errorProperties = i;
					return false;
				}
			}
		});
		
		if(!hasError){
			if(receiver.mobile =='' && receiver.telphone == ''){
				errorCatalog = 'receiver';
				errorProperties = 'mobile';
				hasError = true;
			}
			if(!hasError){
				if(receiver.telphone!=''){
					var reg = /^(0\d{2,3}-)?\d{7,8}(-\d{1,6})?$/;
					var valid = reg.test(receiver.telphone);
					if(!valid){
						errorCatalog = 'receiver';
						errorProperties = 'telphone';
						hasError = true;
					}
				}
			}
		}
	}
	if(hasError){
		var message = _this.errorInfo(errorCatalog,errorProperties);
		alert(message);
	}
	return !hasError;

}

CBSAddOrder.prototype.errorInfo = function(catalog, properties){

	var message = {
			baseInfo :{
				pickUpTime :"请填写提货时间",
				lineAttribute :"请填写线路属性",
				needOwnVehicles :"请填写是否允许外请",
				orderType:"请填写订单类型",
				needLength:"请填写车长",
				needType:"请填写车型",
				needImportedVehicles:"请填写是否进口",
				goodsName:"请填写货品名称",
				miles:"请填写公里数",
				rightMiles:"请填写正确的公里数",
				needStartTime:"请填写计划发车",
				paymentType:"请填写结算方式",
				chargeId:"请填写计费方式",
				goodsWeight:"请填写预估重量",
				needArriveTime:"请填写到达时限",
				unitPrice:"请填写正确有效的单价，最大不能超过40000",
				goodsVolume:"请填写体积",
				orderAdvice:"请填写回单要求",
				timeDiffA:"计划发车时间不能早于或等于提货时间",
				timeDiffB:"到达时限不能早于或等于计划发车",
				isPredict:"请填写单价是否预估",
				rightGoodsVolume:"请填写正确的货物预估体积",
				rightGoodsWeight:"请填写正确的货物预估重量",
				addrId:"请填写货场地址"
			},
			sender :{
				customerId:"请填写客户",
				mobile:"请填写发货人的联系方式",
				telphone:"请填写正确的发货人联系电话",
				contactName:"请填写发货联系人"
			},
			receiver : {
				mobile:"请填写收货人的联系方式",
				telphone:"请填写正确的收货人联系电话",
				contactName:"请填写收货人名称",
				areaCode:"请填写联系人地址",
				address:"请填写联系人地址"
			},
			lineInfo :{
				startCodeP:"请填写始发地",
				endCodeP:"请填写目的地"
			}
		};
	return message[catalog][properties];
};

CBSAddOrder.prototype.showCancel = function(){
	var _this = this;
	var options = {
		data : {},
		text : '是否取消修改订单',
		callback:function(data){
			window.close();
		}
	}
	new CBSConfirm(options);
}

CBSAddOrder.prototype.showMap = function(){
	var _this = this;
	var geo = _this.geo;
	var receiverPCD = _this.pcdList.receiver;
	var pcdText = receiverPCD.getText();
	var addr = $('.receiver-column-address').val();
	var fullAddr = pcdText.join('') + addr;
	var param = {
		address : fullAddr,
		geo : geo,
		onClose : function(e){
			if(e != null){
				_this.geo = e.lnglat;
			} else {
				_this.geo = null;
			}
		}
	}
	new CBSOrderMap(param);
}

$(function(){
	var orderId = global.QueryString.orderId;
	var param = {};
	if(orderId){
		param.orderId = orderId;
	} 
	
	var addOrder = new CBSAddOrder(param);
	$('.conserve').click(function(){
		addOrder.doSave();
		
	});
	
	$('.cancel').click(function(){
		addOrder.showCancel();
	});
})