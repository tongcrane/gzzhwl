var CBSBorrowCarInfo = function(options){
	this.opt = $.extend({}, options);
	this.lendColumns=$('.lend-column');
	this.vehicleColumns=$('.vehicle-column');
	this.driverColumns=$('.driver-column');

	this.init = function(options){
		var _this = this;
		var lendId = _this.opt.lendId;
		_this.getData(lendId);
		//_this.getRemarkHis(lendId);
	};
	
	
	this.getData = function(lendId){
		var _this = this;
		$.get(global.server+'/admin/lend/getLendDetail',{lendId:lendId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.fillVehicleContentFromJsonData(data.lendVehicleInfo);
				_this.fillDriverContentFromJsonData(data.lendDriverInfo);
				_this.fillLendContentFromJsonData(data.lendLogInfo);

			}
		}).done(function(msg){
			var data = msg.data;
			if(data.lendLogInfo.status == '02'){
				$('.return_car').remove();
			} else {
				$('.return_car').click(function(){
					var options = {
						data : {},
						text : '是否归还车辆',
						yes: '确认',
						no: '取消',
						callback:function(data){
							_this.returnCar(lendId);
						}
					}
					new CBSConfirm(options);
				});
			}
			
		});
	};
	
	this.init(options);
};


CBSBorrowCarInfo.prototype.fillVehicleContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    this.vehicleColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pValue) {
    		if(pName == 'length'){
        		if(pValue != '其他'){
        			_this.text(pValue+' 米');
        		} else{
        			_this.text(pValue);
        		}
        	} else if(pName =='loadWeight'){
        		_this.text(pValue+' kg');
        	} else {
        		_this.text(pValue);
        	}
    	} else {
    		_this.text('');
    	}
    });
};

CBSBorrowCarInfo.prototype.fillDriverContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    var realName=$('span[data-column="realName"]');
    var telphone=$('span[data-column="telphone"]');
    var idno=$('span[data-column="idno"]');
    $.each(data,function(i,j){
    	$(realName[i]).text(j.realName);
    	$(telphone[i]).text(j.telphone);
    	$(idno[i]).text(j.idno);
    });
};

CBSBorrowCarInfo.prototype.fillLendContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    this.lendColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue=data[pName];
    	if(pName=='realReturnedTime') {
    		if(pValue){
    			var _date=moment(pValue).format("YYYY-MM-DD");
        		_this.text(_date);
    		}
    	} else {
    		_this.text(pValue);
    	}
    });
    var line = [];
    if(data.departureCodeCn){
    	line.push(data.departureCodeCn);
    }
    if(data.destinationCodeCn){
    	line.push(data.destinationCodeCn);
    }
    
    $('span[data-column="line"]').text(line.join(' - '));
};

//取消提货单
CBSBorrowCarInfo.prototype.returnCar=function(lendId) {
	var url='/admin/lend/return';
	$.ajax({
        url: global.server + url,
        type: "post",
        data: {lendId:lendId},
        success: function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
            	alert('已归还车辆');
            	window.close();
            	window.opener.location.reload();
            } else {
            	alert(msg.status.errorMsg);
            }
        }
    });
}

/*CBSBorrowCarInfo.prototype.getRemarkHis=function(loadId) {
	var url = "/admin/remark/getRecordList";
	$.ajax({
        url: global.server + url,
        type: "get",
        data: {targetId:loadId,remarkType:'04'},
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
}*/


$(function(){
	
	var lendId = global.QueryString.lendId;
	var param = {
		lendId : lendId
	};
	new CBSBorrowCarInfo(param);
	
    //$(".check_hover").hover(function(){$(".hover_content").show()},function(){$(".hover_content").hide()})
	
});
