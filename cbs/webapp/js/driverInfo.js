	var CBSDriver = function(options){
		this.opt = $.extend({}, options);
		this.columns = $('.column');
		this.imageColumns = $('.car-img');
	    var _this = this;
	    
	    this.init = function (options) {
	    	$('#checkUnpass').hide();
			$('#checkPass').hide();
	    	var _this = this;
	        if (options && options.driverInfoId) {
	            console.log('init driver data with id: ' + options.driverInfoId);
	            var url = "/admin/driver/queryDriverandVehicleDetail?driverInfoId=" + options.driverInfoId;
	            var _this = this;
	            $.get(global.server + url, function (msg) {
	                if (msg && msg.status && msg.status.statusCode == global.status.success) {
	                    var data = msg.data;
	                    if(data.status=='01') {
	                    	$('#checkUnpass').show();
	            			$('#checkPass').show();
	                    }
	                    _this.fillContentFromJsonData(data);
	                }
	            });
	        } 
	    }
		this.init(options);
		
	};

	CBSDriver.prototype.fillContentFromJsonData = function (data) {
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
	    var url="/admin/line/getLineList?driverInfoId="+this.opt.driverInfoId;
	    //获取常跑线路
        $.get(global.server + url, function (msg) {
            if (msg && msg.status && msg.status.statusCode == global.status.success) {
                var data = msg.data;
                $.each(data,function(i, j){
                	var el = $('<div class="run-rote"> \
						起运地<input type="text" value="'+j.departureCode+'" class="city"> \
						目的地<input type="text" value="'+j.destinationCode+'" class="city"> \
					</div>');
                	$('.rote-data').append(el);
                });
            }
        });
	};
	
	
	CBSDriver.prototype.check=function(data) {
		$.ajax({
	        type: "get",
	        url: global.server + '/admin/driver/driverAndVehicleCheck',
	        data: data,
	        async: true,
	        success: function(msg) {
	        	if (msg && msg.status && msg.status.statusCode == global.status.success) {
	        		alert('操作成功！');
	        		window.location.reload();
	        	} else if(msg.status.statusCode != global.status.success){
	            	return false;
	            }
	        }
	    });
	}