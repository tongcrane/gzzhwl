var CBSOrderInfo = function(options){
	this.opt = $.extend({}, options);

	this.init = function(options){
		var _this = this;
		var orderId = _this.opt.orderId;
		if(orderId){
			_this.getData(orderId);
			_this.getRemarkHis(orderId);
		} else {
			alert('参数错误');
		}
	};
	
	
	this.getData = function(orderId){
		var _this = this;
		$.get(global.getServer()+'/admin/order/getOrderDetail',{orderId:orderId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.fillContentFromJsonData(data);

			}
		}).done(function(msg){
			_this.getWtd(orderId);
		});
	};
	
	this.enableConsign = function(data){
		var consignmentInfo = data.consignmentInfo;
		if(consignmentInfo==null){ //没创建合同
			return true;
		} else {
			return false;
		}
	}
	
	
	this.getWtd = function(orderId){
		var _this = this;
		$('.actions').hide();

		$.get(global.getServer()+'/admin/order/wtd',{orderId:orderId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				
				var consign = _this.enableConsign(_this.data);
				if(data){
					$.each(data, function(i, j){
						if(consign){//未开单
							$('.actions > .action_99').removeClass('disable').data('action', j.code);//生成合同按钮
						}
						
						if(j.code == '02' || j.code == '03' || j.code == '08'){//编辑按钮 取消按钮 关闭按钮
							if(consign){//未开单
								$('.actions > .action_'+j.code).removeClass('disable').data('action', j.code);//编辑按钮 取消按钮
							}
						}else{
							$('.actions > .action_'+j.code).removeClass('disable').data('action', j.code);//其他按钮
						}
					});
				};
				
				var allowReturn = _this.data.allowReturn;
				if(allowReturn){
					$('.actions > .action_98').removeClass('disable').data('action', '98');
				}
				
				
				$('.actions > .disable').remove();
				
				$('.actions').show();
				
				$('.actions > .push_btn').click(function(){
					_this.showPush();
				});
				
				$(".actions > .apply_return_btn").click(function(){
					_this.showApplyReturn();
				})
				
				$(".actions > .cancel_btn").click(function(){
					_this.showCancel();
				})
				
				$(".actions > .consign_btn").click(function(){
					_this.createConsign();
				})
				
				$(".actions > .close_btn").click(function(){
					_this.showClose();
				})
				
				$(".actions > .load_btn").click(function(){
					_this.showLoad();
				});
				
				$(".actions > .edit_btn").click(function(){
					location.href = global.getContextPath() + '/addBusSourceOrder.html?orderId='+orderId;
				})
			}
		});
	}
	
	
	this.init(options);
};



CBSOrderInfo.prototype.getFullAddress = function(info){
	if(info!=null){
		var pcd = [];
		var area = info.area;
		$.each(area, function(i, j){
			pcd.push(j.regionName);
		});
		var str = pcd.reverse().join("");
		return str+info.address;
	} else {
		return "";
	}
};

CBSOrderInfo.prototype.getAgreementName = function(agreementInfo){
	if(agreementInfo != null) {
		var startCodePCn = agreementInfo.startCodePCn;
		var endCodePCn = agreementInfo.endCodePCn;
		var lineAttribute = agreementInfo.lineAttribute;
		var needLength = agreementInfo.needLength;
		return startCodePCn +"-" +endCodePCn + "+" + lineAttribute + "+" + needLength + "m";
	}else {
		return "";
	}
};

CBSOrderInfo.prototype.fillData = function(data, selector){
    $(selector).each(function(i, j){
    	var properties = $(j).data('column');
    	var pList = properties.split(',');
    	var $self = $(j);
    	if(data!=null){
    		var value = [];
    		$.each(pList,function(i, j){
    			if (j=='needLength'){
        			if(data[j]!='其他'){
        				value.push(data[j]+" 米");
        			} else {
            			value.push(data[j]);
            		}
        		} else if(j=='needStartTime'||j=='needArriveTime'||j=='pickUpTime'){
        			if(data[j]) {
        				var _date=moment(data[j]).format("YYYY-MM-DD HH:mm");
        				value.push(_date);
        			}
        		} else {
        			value.push(data[j]);
        		}
        	});
    		$self.text(value.join(' '));
    	} else {
    		$self.text("");
    	}
    });
}



CBSOrderInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    
    var baseInfo = data.orderBaseInfo;
    _this.fillData(baseInfo,'.baseInfo-column');
    var isPredict=baseInfo.isPredict;
    if(isPredict=="01") {
    	$('.isPredict').html("准确");
    } else if(isPredict=="02") {
    	$('.isPredict').html("预估");
    }
    
    var lineInfo = data.orderLineInfo;
    _this.fillData(lineInfo,'.lineInfo-column');
    
    var detailInfo = data.orderDetailInfo
    _this.fillData(detailInfo,'.detailInfo-column');

    var senderInfo = data.orderSenderInfo;
    _this.fillData(senderInfo,'.senderInfo-column');
    
    var senderAddr = _this.getFullAddress(senderInfo);
    $('.senderInfo-addr').text(senderAddr);
    
    var receiverInfo = data.orderReceiverInfo;
    _this.fillData(receiverInfo,'.receiverInfo-column');
    
    var receiverAddr = _this.getFullAddress(receiverInfo);
    $('.receiverInfo-addr').text(receiverAddr);
    
    var chargeInfo = data.chargeInfo;
    _this.fillData(chargeInfo,'.chargeInfo-column');
    
    var hasConsign = data.hasConsign;
    $('.hasConsign').text(hasConsign);
    
    var agreementInfo = data.agreementInfo;
    var agreementName = _this.getAgreementName(agreementInfo);
    $('.agreementName').text(agreementName);
};
//推送
CBSOrderInfo.prototype.showPush = function(){
	var _this = this;
	new CBSOrderPush(_this.data);
}

//申请退回
CBSOrderInfo.prototype.showApplyReturn = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	var options = {
		data : {orderId : orderId},
		text : '是否向运势界申请退回该订单',
		callback:function(data){
			_this.doApplyReturn();
		}
	}
	new CBSConfirm(options);
}
//申请退回
CBSOrderInfo.prototype.doApplyReturn = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	$.post(global.getServer()+'/admin/order/allowReturn',{orderId:orderId}, function(msg){
		if (msg && msg.status && msg.status.statusCode == global.status.success) {
			var allow = msg.data;
			if(allow){
				$.post(global.getServer()+'/admin/order/return',{orderId:orderId}, function(msg){
					if (msg && msg.status && msg.status.statusCode == global.status.success) {
						alert('已申请');
						window.close();
					}
				})
			}else{
				alert("当前订单不能申请退回");
			}
		}
	})
}
//取消
CBSOrderInfo.prototype.showCancel = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	var options = {
			data : {orderId : orderId},
			text : '是否确认取消订单',
			callback:function(data){
				_this.doCancel();
			}
	}
	new CBSConfirm(options);
}
//取消
CBSOrderInfo.prototype.doCancel = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	$.post(global.getServer()+'/admin/order/cancel',{orderId:orderId}, function(msg){
		if (msg && msg.status && msg.status.statusCode == global.status.success) {
			alert('订单已取消');
			window.close();
		}
	})
}

//关闭
CBSOrderInfo.prototype.showClose = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	var options = {
		data : {orderId : orderId},
		text : '是否确认关闭订单',
		callback:function(data){
			_this.doClose();
		}
	}
	new CBSConfirm(options);
}


//关闭
CBSOrderInfo.prototype.doClose = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	$.post(global.getServer()+'/admin/order/close',{orderId:orderId}, function(msg){
		if (msg && msg.status && msg.status.statusCode == global.status.success) {
			alert('订单已关闭');
			window.close();
		}
	})
}

//创建合同
CBSOrderInfo.prototype.createConsign = function(){
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	var url = global.getContextPath() + '/busContract.html?orderId='+orderId;
	var body = document.getElementsByTagName("body")[0];
	var el = document.createElement("a");
	body.appendChild(el);
	el.href = url;
	el.target = '_blank';
	el.click();
	body.removeChild(el);
}

//约车
CBSOrderInfo.prototype.doLoad=function() {
	var _this = this;
	var data = _this.data;
	var orderId = data.orderDetailInfo.orderId;
	$.post(global.getServer()+'/admin/order/toLoad',{orderId:orderId}, function(msg){
		if (msg && msg.status && msg.status.statusCode == global.status.success) {
			alert('已完成约车操作');
			window.close();
			window.opener.location.reload();
		}
	})
}

CBSOrderInfo.prototype.showLoad=function() {
	var _this=this;
	var data=_this.data;
	var orderId=data.orderDetailInfo.orderId;
	var options = {
			data : {orderId : orderId},
			text : '是否确认约车',
			callback:function(data){
				_this.doLoad();
			}
	}
	new CBSConfirm(options);
}

CBSOrderInfo.prototype.getRemarkHis=function(orderId) {
	var url = "/admin/remark/getRecordList";
	$.ajax({
        url: global.getServer() + url,
        type: "get",
        data: {targetId:orderId,remarkType:'03'},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	$('.hover_content').append('<h4>操作记录</h4>');
            	var recordList=msg.data;
            	if(recordList.length>0) {
            		$.each(recordList,function(i,j){
            			$('.hover_content').append('<p><span>'+global.defaultIfBlack(j.real_name,'')+'</span> 推送时间：'+j.createdTime+' 备注:'+j.content+'</p>');
            		})
            	}
            } else {
            	console.log(msg.status.errorMsg);
            }
        }
    });
}


$(function(){
	
	var orderId = global.QueryString.orderId;

	var param = {
		orderId : orderId
	};
	
	new CBSOrderInfo(param);
    $(".check_hover").hover(function(){$(".hover_content").show()},function(){$(".hover_content").hide()})
	
});
