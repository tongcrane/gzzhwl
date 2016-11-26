var CBSSupplyInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.supplyColumns=$('.supply_column');
	this.bankColumns=$('.bank_column');
	
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.supplyId) {
            console.log('init supply data with id: ' + options.supplyId);
            var url = "/admin/supply/getSupplyDetail?supplyId=" + options.supplyId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillSupplyContentFromJsonData(data.supplyInfo);
                    _this.fillBankContentFromJsonData(data.bankInfo);
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

CBSSupplyInfo.prototype.fillSupplyContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.supplyColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	if(pName=='type') {
    		var pValue=data[pName];
    		if(pValue=='01') {
    			_this.text('个人');
    	    	$('.toggle_p_c').html('身份证号码');
    	    } else if(pValue=='02') {
    	    	_this.text('公司');
    	    	$('.toggle_p_c').html('税务登记证号');
    	    }
    	} else {
    		var pValue = data[pName];
        	_this.text(pValue);
    	}
    });
    
    var areaCode = data.areaCode;
    var area = _this.getPcdData(areaCode);
    $('.pcd-column').text(area+" "+data.address);
};

CBSSupplyInfo.prototype.fillBankContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.bankColumns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
};

CBSSupplyInfo.prototype.getPcdData = function(code){
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
	            	var p = "";
	        		var c = "";
	        		var d = "";
	        		$.each(data,function(i, j){
	        			if(j.regionLevel == 1){
	        				p = j.regionName;
	        			} else if(j.regionLevel == 2){
	        				c = j.regionName;
	        			} else if(j.regionLevel == 3){
	        				d = j.regionName;
	        			}
	        		});
	        		var _result = [];
	        		if(p){
	        			_result.push(p);
	        		}
	        		if(c){
	        			_result.push(c);
	        		}
	        		if(d){
	        			_result.push(d);
	        		}
	        		result = _result.join(" ");
	            }
	        }
	    });
	}
	return result;
}