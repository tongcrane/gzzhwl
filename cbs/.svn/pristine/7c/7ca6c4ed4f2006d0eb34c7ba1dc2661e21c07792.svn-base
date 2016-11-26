var CBSDriverInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
	this.bankColumns = $('.bank-column');
	this.imageColumns = $('.car-img');
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.driverInfoId) {
            console.log('init driver data with id: ' + options.driverInfoId);
            var url = "/admin/driver/queryDriverDetail?driverInfoId=" + options.driverInfoId;
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

CBSDriverInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
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
    _this.fillBankInfo();
};



CBSDriverInfo.prototype.fillBankInfo = function () {
    var _this = this;
    var driverInfoId = _this.opt.driverInfoId;
    var url = '/admin/driver/queryDriverBankDetail';
    $.get(global.server + url,{"driverInfoId":driverInfoId}, function (msg) {
        if (msg && msg.status && msg.status.statusCode == global.status.success) {
        	var data = msg.data;
        	_this.bankColumns.each(function(i, j){
        		var _this = $(this);
        		var pName = _this.data("column");
        		var pValue = data[pName];
        		_this.text(pValue);
        	});
        }
    })
};
