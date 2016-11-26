var CBSArriveInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns=$('.column');
	this.imageList = [];

	this.init = function(options){
		var _this = this;
		var loadId = _this.opt.loadId;
		_this.getData(loadId);
	};
	
	
	this.getData = function(loadId){
		var _this = this;
		$.get(global.server+'/admin/arrived/getArriveDetail',{loadId:loadId}, function(msg){
			if (msg && msg.status && msg.status.statusCode == global.status.success) {
				var data = msg.data;
				_this.data = data;
				_this.fillContentFromJsonData(data.loadVehicleInfo);
//				_this.fillContentArrive(data.arriveInfo);
				_this.fillContentElecReceipt(data.elecReceipt);
				_this.fillContentPrintReceipt(data.printReceipt);
			}
		}).done(function(msg){
			var data = msg.data;
			
			$('span[data-column="plateNumber"]').click(function(){
				var url = global.getContextPath() + '/checkCarInfomation.html?vehicleInfoId='+msg.data.loadVehicleInfo.vehicleInfoId;
		    	var body = document.getElementsByTagName("body")[0];
		    	var el = document.createElement("a");
		    	body.appendChild(el);
		    	el.href = url;
		    	el.target = '_blank';
		    	el.click();
		    	body.removeChild(el);
			});
		});
	};
	
	this.init(options);
};


CBSArriveInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if (pName=='length'){
    		if(pValue==null||pValue=='') {
    			_this.text('');
    		} else if(pValue!='其他'){
				_this.text(pValue+" 米");
			} else {
    			_this.text(pValue);
    		}
		} else if(pName=='needStartTime'||pName=='tripTime'||pName=='needArriveTime'){
			if(pValue) {
				var _date=moment(pValue).format("YYYY-MM-DD HH:mm");
				_this.text(_date)
			}
		} else {
			_this.text(pValue);
		}
    });
    var startCodePCn=data.startCodePCn;
    var startCodeCCn=data.startCodeCCn;
    var endCodePCn=data.endCodePCn;
    var endCodeCCn=data.endCodeCCn;
    $('span[data-column="_start"]').text(startCodePCn+" - "+startCodeCCn);
    $('span[data-column="_end"]').text(endCodePCn+" - "+endCodeCCn);
    var driverInfo=data.driverInfo;
    if(driverInfo) {
    	if(driverInfo[0]) {
    		$('span[data-column="realName1"]').text(driverInfo[0].realName);
    		$('span[data-column="telphone1"]').text(driverInfo[0].telphone);
    	}
    	if(driverInfo[1]) {
    		$('span[data-column="realName2"]').text(driverInfo[1].realName);
    		$('span[data-column="telphone2"]').text(driverInfo[1].telphone);
    	}
    }
};

//CBSArriveInfo.prototype.fillContentArrive=function(data) {
//	if (!data) return;
//    var _this = this;
//    $('span[data-column="createdTime"]').text(data.createdTime);
//    $('span[data-column="createdBy"]').text(data.realName+'('+data.position+')');
//    $('i[data-column="arriveTime"]').text(data.arriveTime);
//}

CBSArriveInfo.prototype.fillContentElecReceipt=function(data) {
	if(data) {
		var receiptId = data.receiptId;
		if (receiptId) {
			var _t = $('<div class="un_bg">\
						<p>\
							<span class="un_time">2016-08-29 15:34</span>\
							<span class="un_name _name1"></span>\
							<span class="arrive_time _time1">上传回单<i></i></span>\
							<span class="un_name _name2"></span>\
							<span class="arrive_time _time2"><i></i></span>\
							<span class="images image-column" data-column="receiptImageRefId"></span>\
						</p>\
					</div>');
			var createdTime = data.createdTime;
			if (createdTime) {
				createdTime = moment(createdTime).format("YYYY-MM-DD HH:mm");
			}
			var updatedTime = data.updatedTime;
			if (updatedTime) {
				updatedTime = moment(updatedTime).format("YYYY-MM-DD HH:mm");
			}
			var opTime = data.opTime;
			if (opTime) {
				opTime = moment(opTime).format("YYYY-MM-DD HH:mm");
			}
			console.log(updatedTime)
			_t.find('.un_time').text(createdTime);
			_t.find('._name1').text(global.defaultIfBlack(data.subName, '') + '('+ data.subPosition + ')');
			_t.find('._time1>i').text(opTime);
			_t.find('._name2').text(global.defaultIfBlack(data.checkName, '') + '('+ data.checkPosition + ')')
			_t.find('._time2>').html(data.statusCn+'<i>'+updatedTime+'</i>');
			var _image = _t.find('.image-column');
			if (typeof CBSImageInfo === "function") {
				var category = _image.data("column");
				var imageId = data.receiptImageRefId;
				var image = new CBSImageInfo({
					view : _image,
					category : category,
					imageId : imageId
				});
			}
			$('.elecReceipt').append(_t);
		} else {
			$('.vehicle_arrive').remove();
		}
	} else {
		$('.elecReceipt').remove();
	}
}

CBSArriveInfo.prototype.fillContentPrintReceipt=function(data) {
	if(data) {
		var _t=$('<div class="un_bg">\
					<p>\
						<span class="un_time"></span>\
						<span class="un_name"></span>\
						<span class="company"><i></i><b></b></span>\
						<span class="arrive_time">收到时间<i></i></span>\
					</p>\
				</div>');
		var signTime=data.signTime;
		if(signTime) {
			signTime=moment(signTime).format("YYYY-MM-DD HH:mm");
		}
		var createdTime=data.createdTime;
		if(createdTime) {
			createdTime=moment(createdTime).format("YYYY-MM-DD HH:mm");
		}
		_t.find('.un_time').text(createdTime);
		_t.find('.un_name').text(global.defaultIfBlack(data.subName,'')+'('+global.defaultIfBlack(data.subPosition,'')+')');
		_t.find('.company>i').text(data.expressDelivery);
		_t.find('.company>b').text(data.billNo);
		_t.find('.arrive_time>i').text(signTime);
		$('.printReceipt').append(_t);
	} else {
		$('.printReceipt').remove();
	}
}

$(function(){
	
	var loadId = global.QueryString.loadId;
	var param = {
		loadId : loadId
	};
	var arrive=new CBSArriveInfo(param);
	
});