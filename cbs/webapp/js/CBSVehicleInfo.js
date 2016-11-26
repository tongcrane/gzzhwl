var CBSVehicleInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.imageColumns = $('.car-img');
	
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.vehicleInfoId) {
            console.log('init vehicle data with id: ' + options.vehicleInfoId);
            var url = "/admin/vehiclemanage/queryDetail?vehicleInfoId=" + options.vehicleInfoId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillContentFromJsonData(data);
                } else {
                	alert("页面参数错误，或数据已不存在。");
                	window.close();
                }
            });
        } else {
        	alert("页面参数错误");
        	window.close();
        }
    }
	this.init(options);
	
};

CBSVehicleInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	if(pName=='ciEffectDate'||pName=='ciValidDate'||pName=='viEffectDate'||pName=='viValidDate') {
    		if(pValue) {
    			var _date=moment(pValue).format("YYYY-MM-DD HH:mm");
        		_this.text(_date);
    		}
    	} else {
    		_this.text(pValue);
    	}
    });
    // 加载照片控件
    if (typeof CBSImageInfo === "function") {
    	this.imageColumns.each(function(i, j){
    		var category = $(this).data("column");
    		var imageId = data[category];
        	var image = new CBSImageInfo({
        		view: $(this),
        		category: $(this).data("column"),
        		imageId: imageId
            });
        });
    };
    var bridgeType=data.bridgeType;
    if(bridgeType=="01") {
    	$('span[data-column="bridgeType"]').text("单桥");
    } else if(bridgeType=="02") {
    	$('span[data-column="bridgeType"]').text("双桥");
    }
    var departureCode = data.departureCode;
    var destinationCode = data.destinationCode;
    var departure = _this.getPcdData(departureCode);
    var destination = _this.getPcdData(destinationCode);
    
    var pcd_result =[];
	if(departure){
		pcd_result.push(departure);
	}
	if(destination){
		pcd_result.push(destination);
	}
	var pcd_str = pcd_result.join(" - ");
    $('.pcd-column').text(pcd_str);
};

CBSVehicleInfo.prototype.getPcdData = function(code){
	var result = null;
	if(code){
		$.ajax({
	        url: global.server + '/api/region/findRootByCode',
	        type: "get",
	        async: false,
	        data: {"regionCode":code},
	        success: function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	var data = msg.data;
	        		var c = "";
	        		$.each(data,function(i, j){
	        			if(j.regionLevel == 1){
	        			} else if(j.regionLevel == 2){
	        				c = j.regionName;
	        			} else if(j.regionLevel == 3){
	        			}
	        		});
	        		var _result = [];
	        		if(c){
	        			_result.push(c);
	        		}
	        		result = _result.join("-");
	            }
	        }
	    });
	}
	return result;
}
