var CBSCustomInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.custId) {
            console.log('init customer data with id: ' + options.custId);
            var url = "/admin/customer/queryCustDetail?custId=" + options.custId;
            var _this = this;
            $.get(global.server + url, function (msg) {
                if (msg && msg.status && msg.status.statusCode == global.status.success) {
                    var data = msg.data;
                    _this.fillContentFromJsonData(data);
                } else {
                	//alert("页面参数错误，或数据已不存在。");
                	//window.close();
                }
            });
        } else {
        	alert("页面参数错误");
        	window.close();
        }
    }
	this.init(options);
	
};

CBSCustomInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });

    var areaCode=data.areaCode;
    var area=_this.getPcdData(areaCode);
    $('.pcd-column').text(area);
    
    var isAgreement=data.isAgreement;
    var type=data.type;
    if(isAgreement=='0') {
    	$('#isAgree').text('否');
    } else if(isAgreement=='1') {
    	$('#isAgree').text('是');
    }
    if(type=='01') {
    	$('#customType').text('个人');
    	$('.toggle_p_c').html('身份证号');
    } else if(type=='02') {
    	$('#customType').text('公司');
    	$('.toggle_p_c').html('税务登记号');
    }
};

CBSCustomInfo.prototype.getPcdData = function(code){
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
	        		result = _result.join("");
	            }
	        }
	    });
	}
	return result;
}
