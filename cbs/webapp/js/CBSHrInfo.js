var CBSHrInfo = function(options){
	this.opt = $.extend({}, options);
	this.columns = $('.column');
    var _this = this;
    
    this.init = function (options) {
    	var _this = this;
        if (options && options.staffId) {
            console.log('init hr data with id: ' + options.staffId);
            var url = "/admin/staff/getStaffInfo?staffId=" + options.staffId;
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

CBSHrInfo.prototype.fillContentFromJsonData = function (data) {
    if (!data) return;
    this.data = data;
    var _this = this;
    this.columns.each(function (i, j) {
    	var _this = $(this);
    	var pName = _this.data("column");
    	var pValue = data[pName];
    	_this.text(pValue);
    });
};
