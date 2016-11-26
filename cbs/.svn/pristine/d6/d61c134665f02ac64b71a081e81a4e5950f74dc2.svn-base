	var CBSAccount = function(options){
		this.opt = $.extend({}, options);
		this.columns = $('.column');
		this.imageColumns = $('.car-img');
	    var _this = this;
	    
	    this.init = function (options) {
	    	$('#checkUnpass').hide();
			$('#checkPass').hide();
	    	var _this = this;
	        if (options && options.accountId) {
	            console.log('init account data with id: ' + options.accountId);
	            var url = "/admin/account/getUserDetail?accountId=" + options.accountId;
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
	            }).done(function (msg) {
	            	var data = msg.data;
	            });
	        } 
	    }
		this.init(options);
		
	};

	CBSAccount.prototype.fillContentFromJsonData = function (data) {
	    if (!data) return;
	    this.data = data;
	    var _this = this;
	    this.columns.each(function (i, j) {
	    	var _this = $(this);
	    	var pName = _this.data("column");
	    	var pValue = data[pName];
	    	if(pName=='status') {
	    		if(pValue=='01') {
	    			pValue='待审核';
	    		} else if(pValue=='02') {
	    			pValue='审核未通过'
	    		} else if(pValue=='00') {
	    			pValue='审核通过';
	    		}
	    	}
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
	    }
	};
	
	CBSAccount.prototype.checkUnpass=function(data) {
		$.ajax({
	        type: "post",
	        url: global.server + '/admin/account/checkUnpass',
	        data: data,
	        async: true,
	        success: function(msg) {
	        	if (msg && msg.status && msg.status.statusCode == global.status.success) {
	        		alert('操作成功！');
	        		window.close();
	        		window.opener.location.reload();
	        		//window.location.reload();
	        	} else if(msg.status.statusCode != global.status.success){
	            	return false;
	            }
	        }
	    });
	}
	
	CBSAccount.prototype.checkPass=function(data) {
		$.ajax({
	        type: "post",
	        url: global.server + '/admin/account/checkPass',
	        data: data,
	        async: true,
	        success: function(msg) {
	        	if (msg && msg.status && msg.status.statusCode == global.status.success) {
	        		alert('操作成功！');
	        		window.close();
	        		window.opener.location.reload();
	        		//window.location.reload();
	        	} else if(msg.status.statusCode != global.status.success){
	            	return false;
	            }
	        }
	    });
	}