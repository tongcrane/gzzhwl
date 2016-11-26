var CBSSourceInfo = function(options){
	this.opt = $.extend({}, options);
	this.baseColumns = $('.base .column');
	this.senderColumns=$('.sender .column');
	this.receiverColumns=$('.receiver .column');
	this.goodsColumns=$('.goods .column');
	this.priceBaseColumns=$('.price_base .column');
	this.priceColumns=$('.price .column');
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
    	
    	$('.use-date').each(function(i, j){
    		var time = $(this).data('time');
    		if(time){
    			$(this).datetimepicker({
            		timepicker:true,
            		format:'Y-m-d H:i',
            		autoclose:true,
            		todayHighlight:true,
            		keyboardNavigation:false
        		});
    		} else {
    			$(this).datetimepicker({
            		timepicker:false,
            		format:'Y-m-d',
            		autoclose:true,
            		todayHighlight:true,
            		keyboardNavigation:false
        		});
    		}
        });
    	
        if (options && options.sourceId) {
            console.log('init source data with id: ' + options.sourceId);
            var url = "/admin/source/querySourceDetail?sourceId=" + options.sourceId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    var createdTime=data.orderDetailInfo.createdTime;
                    if(createdTime) {
                    	createdTime=moment(createdTime, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
                    }
                    $('.sourse_num span').html(data.sourceNo);
                    $('.source_status').html(data.statusCn);
                    $('.push_time span').html(createdTime);
                    $("span[data-column='orderNo']").html(data.orderDetailInfo.orderNo);
                    var baseInfo=data.orderBaseInfo;
                    var chargeInfo=data.chargeInfo;
                    $.each(chargeInfo,function(i,j){
                    	baseInfo[i]=j;
                    });
                    _this.fillLineInfoContent(data.orderLineInfo);
                    _this.fillBaseContentFromJsonData(baseInfo);
                    _this.fillSenderContentFromJsonData(data.orderSenderInfo);
                    _this.fillReceiverContentFromJsonData(data.orderReceiverInfo);
                    _this.fillGoodsContentFromJsonData(baseInfo);
                    _this.fillPriceBaseContentFromJsonData(baseInfo);
                    _this.fillPriceContentFromJsonData(data.sourceInfo,baseInfo.miles);
                    _this.getRemarkHis(options.sourceId);
                } else {
                	alert("页面参数错误，或数据已不存在。");
                	window.close();
                }
            }).done(function(msg){
            	var action=options.action;
            	if(action=='return') {
            		_this.getReturnButtons();
            	} else {
            		_this.getButtons();
            	}
            	$('select').multiselect({
					header : false,
					multiple : false,
					selectedList : 1,
					minWidth:135,
					noneSelectedText: '请选择'
				});
            });
        } else {
        	alert("页面参数错误");
        	window.close();
        }
    }
	this.init(options);
	
};

CBSSourceInfo.prototype.fillLineInfoContent=function(data) {
	if(!data) return;
	this.data=data;
	var _this=this;
	var startP=data.StartCodePCn;
	var startC=data.StartCodeCCn;
	var transP=data.transferCodePCn;
	var transC=data.transferCodeCCn;
	var endP=data.endCodePCn;
	var endC=data.endCodeCCn;
	var start=(startP+startC)?(startP+startC):'';
	var trans=(transP+transC)?(transP+transC):'';
	var end=(endP+endC)?(endP+endC):'';
	$("span[data-column='line']").html(start+' - '+end);
	$("span[data-column='transfer']").html(trans);
}

CBSSourceInfo.prototype.fillBaseContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.baseColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pName=='needStartTime'||pName=='needArriveTime') {
    		if(pValue) {
    			pValue = moment(pValue, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
    		}
    	}
    	_this.text(pValue);
    });
};

CBSSourceInfo.prototype.fillSenderContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.senderColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
    var area=data.area;
    var areaArray=new Array();
    $.each(area,function(i,j){
    	areaArray.push(j.regionName);
    });
    areaArray=areaArray.reverse();
    var areaCn='';
    $.each(areaArray,function(i,j){
    	areaCn+=j;
    })
    $("span[data-column='sender_areaCode']").html(areaCn);
};

CBSSourceInfo.prototype.fillReceiverContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.receiverColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
    var area=data.area;
    var areaArray=new Array();
    $.each(area,function(i,j){
    	areaArray.push(j.regionName);
    });
    areaArray=areaArray.reverse();
    var areaCn='';
    $.each(areaArray,function(i,j){
    	areaCn+=j;
    })
    $("span[data-column='receiver_areaCode']").html(areaCn);
};

CBSSourceInfo.prototype.fillGoodsContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.goodsColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
};

CBSSourceInfo.prototype.fillPriceBaseContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.priceBaseColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pName=='unitPrice') {
    		_this.text(pValue+" "+data['unit']);
    	} else {
    		_this.text(pValue);
    	}
    });
};

CBSSourceInfo.prototype.fillPriceContentFromJsonData = function (data,miles) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.priceColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pName == 'startTime' || pName == 'endTime'){
			if(pValue){
				pValue = moment(pValue, "YYYY-MM-DD HH:mm:ss").format("YYYY-MM-DD HH:mm");
			}
    	}
    	_this.val(pValue);
    });
    $("input[data-column='miles']").val(miles);
};


CBSSourceInfo.prototype.getPriceJsonDataFromContent = function(){
	var data = {};
	this.priceColumns.each(function(i, j){
		var _this = $(this);
		var pName = _this.data("column");
		var pValue = _this.val();
		data[pName] = pValue;
	});
	return data;
};


//发布
CBSSourceInfo.prototype.doPublic=function() {
	var _this = this;
    var valid = _this.validToPost();
    if(valid) {
    	var result = _this.getPriceJsonDataFromContent();
    	console.log(result)
    	result.sourceId = _this.opt.sourceId;
        var url = "/admin/source/public";
        $.ajax({
            url: global.server + url,
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(result),
            dataType: "json",
            success: function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                	//alert("发布成功。");
            		window.close();
            		window.opener.location.reload();
                } else {
                	alert(msg.status.errorMsg);
                }
            }
        });
    } else {
    	
    }
}
//拒绝
CBSSourceInfo.prototype.doReject=function() {
	var _this = this;
	var result={};
	var reason=$('#action_reason').val();
	result.sourceId=_this.opt.sourceId;
	result.reason=reason;
	var url = "/admin/source/reject";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	$('.reject_win').hide();
            	alert("操作成功。");
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
} 
//关闭
CBSSourceInfo.prototype.doClose=function() {
	var _this = this;
	var result={};
	var reason=$('#action_reason').val();
	result.sourceId=_this.opt.sourceId;
	result.reason=reason;
	var url = "/admin/source/close";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
} 
//备注
CBSSourceInfo.prototype.doRemark=function() {
	var _this = this;
	var result={};
	var content=$('#action_reason').val();
	result.targetId=_this.opt.sourceId;
	result.content=content;
	result.remarkType='02';
	var url = "/admin/remark/addRemark";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	$('.remark_win').hide();
            	alert("操作成功。");
            	window.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}
//同意退回申请
CBSSourceInfo.prototype.agree=function() {
	var _this = this;
	var result={};
	result.sourceId=_this.opt.sourceId;
	var url = "/admin/orderReturn/agree";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}
//拒绝退回申请
CBSSourceInfo.prototype.disAgree=function() {
	var _this = this;
	var result={};
	result.sourceId=_this.opt.sourceId;
	var url = "/admin/orderReturn/disagree";
	$.ajax({
        url: global.server + url,
        type: "POST",
        data: result,
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

CBSSourceInfo.prototype.getRemarkHis=function(sourceId) {
	var url = "/admin/remark/getRecordList";
	$.ajax({
        url: global.server + url,
        type: "get",
        data: {targetId:sourceId,remarkType:'02'},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	$('.hover_content').append('<h4>操作记录</h4>');
            	var recordList=msg.data;
            	if(recordList.length>0) {
            		$.each(recordList,function(i,j){
            			$('.hover_content').append('<p><span>'+j.real_name+'</span> 推送时间：'+j.createdTime+' 备注:'+j.content+'</p>');
            		})
            	}
            } else {
            	console.log(msg.status.errorMsg);
            }
        }
    });
}

CBSSourceInfo.prototype.validToPost = function () {
    var _this = this;
    var valid = true;
    //todo
    var startTime=$('input[data-column="startTime"]').val();
    var endTime=$('input[data-column="endTime"]').val();
    var _s=moment(startTime,"YYYY-MM-DD HH:mm");
    var _e=moment(endTime,"YYYY-MM-DD HH:mm");
    var time_valid=_e.isAfter(_s);
	if(!time_valid) {
		alert("竞标结束时间必须大于竞标开始时间");
		return false;
	}
    return valid;
};

CBSSourceInfo.prototype.getButtons=function() {
	var _this=this;
	var url="/admin/source/wtd";
	$.ajax({
        url: global.server + url,
        type: "get",
        data: {sourceId:_this.opt.sourceId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	if(msg.data!=undefined) {
            		var buttons=msg.data;
            		$.each(buttons,function(i,j) {
            			$('#action_'+j.code+'').show();
            			if(j.code=='02'||j.code=='03') {
            				$('.check_order').hide();
            				_this.priceColumns.each(function(i, j){
            					var _this = $(this);
            					_this.removeAttr('disabled');
            				});
            				$('select').multiselect('enable');
            			}
            		});
            	}
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

CBSSourceInfo.prototype.getReturnButtons=function() {
	var _this=this;
	var url="/admin/orderReturn/wtd";
	$.ajax({
        url: global.server + url,
        type: "get",
        data: {sourceId:_this.opt.sourceId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	if(msg.data!=undefined) {
            		var buttons=msg.data;
            		$('.check_order').hide();
            		$.each(buttons,function(i,j) {
            			$('#return_'+j.code+'').show();
            		});
            	}
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}
