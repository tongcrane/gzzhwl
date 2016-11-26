var CBSLineSelect = function(options, container){
	this.opt = $.extend({}, options);
	
	this.init = function(options, container){
		var _this = this;
		
		container.append('<select class="pcd-select-p"></select>');
 		container.append('<select class="pcd-select-c"></select>');
 		
 		
		var _ptarget = container.find('.pcd-select-p');
		var _ctarget = container.find('.pcd-select-c');
		
		this.pSelect = new CBSLineRow(_ptarget, {showEmpty:true});
		this.cSelect = new CBSLineRow(_ctarget, {showEmpty:true});

		_ptarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			minWidth : 116,
			noneSelectedText: '请选择',
			click: function(event, ui){
				_this.cSelect.refresh(ui.id);
		    }
		});
		
		_ctarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			minWidth : 116,
			noneSelectedText: '请选择'
		});
		
		var initCode = options.initCode;
		_this.showDefault(initCode);
	};
	
	
	this.showDefault = function(initCode){
		var _this = this;
		if(initCode){
			_this.showByChild(initCode);
		} else {
			_this.pSelect.refresh(1);
		}
	};
	
	this.showByChild = function(code){
		var _this = this;
		$.ajax({
	        url: global.server + '/api/region/findRootByCode',
	        type: "get",
	        data: {"regionCode":code},
	        success: function (msg) {
	            if (msg && msg.status && msg.status.statusCode == global.status.success) {
	            	var data = msg.data;
	            	var pV = null;
	            	var cV = null;
	            	$.each(data, function(i, j){
	            		if(j.regionLevel == 2){
	            			cV = j.regionCode;
	            		} else if (j.regionLevel == 1){
	            			pV = j.regionCode;
	            		}
	            	});
	            	_this.selectValue(pV, cV);
	            }
	        }
	    });
	}
	
	this.selectValue = function(pV, cV){
		var _this = this;
		if(pV){
			if(cV){
				_this.cSelect.refreshByCode(pV, cV);
				_this.pSelect.refreshByCode('000000',pV);
			} else {
				_this.pSelect.refreshByCode('000000',pV);
			}
		}
	};
	
	
	this.getValue = function(){
		var _this = this;
		var cValue = _this.cSelect.getValue();
		var pValue = _this.pSelect.getValue();
		return {
			category : _this.opt.category,
			p : pValue,
			c : cValue
		};
	}
	
	this.init(options, container);
}

var CBSLineRow = function(container, options){
	this.container = container;
	this.opt = $.extend({}, options);
	
	this.refresh = function(parentId, initId){
		var _this = this;
		_this.getData(parentId, _this.showData, initId);
	};
	
	this.refreshByCode = function(parentCode, initCode){
		var _this = this;
		_this.getDataByCode(parentCode, _this.showDataByCode, initCode);
	}
	
	this.showDataByCode = function(data, initCode){
		var _this = this;
		container.empty();
		if(options.showEmpty){
			container.append('<option data-id="" value="">请选择</option>');
		}
		$.each(data, function(i, j){
			if(initCode){
				if(j.regionCode == initCode){
					container.append('<option selected data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				} else {
					container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				}
			}else{
				container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
			}
		});
		container.multiselect("refresh");
		if(initCode){
			//console.log("init..."+initId);
		} else {
			//console.log("refresh");
			container.multiselect("widget").find("label.ui-corner-all:first").each(function(){
				this.click();
			});
		}
	};
	

	this.showData = function(data, initId){
		var _this = this;
		container.empty();
		if(options.showEmpty){
			container.append('<option data-id="" value="">请选择</option>');
		}
		$.each(data, function(i, j){
			if(initId){
				if(j.regionId == initId){
					container.append('<option selected data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				} else {
					container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
				}
			}else{
				container.append('<option data-id="'+ j.regionId +'" value="' + j.regionCode + '">' + j.regionName +'</option>');
			}
			
		});
		container.multiselect("refresh");
		if(initId){
			//console.log("init..."+initId);
		} else {
			//console.log("refresh");
			container.multiselect("widget").find("label.ui-multiselect-option:first").each(function(){
				this.click();
			});
		}
	};
	
	this.getValue = function(){
		return container.val();
	}
	
	
	this.getData = function(parentId, callback, initId){
		var url = '/api/region/findById';
		if(parentId){
			$.ajax({
		        url: global.server + url,
		        type: "get",
		        data: {"parentId":parentId},
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	var data = msg.data;
		            	callback(data, initId);
		            }
		        }
		    });
		} else {
			callback([], initId);
		}
	};
	
	this.getDataByCode = function(parentCode, callback, initCode){
		var url = '/api/region/findByCode';
		if(parentCode){
			$.ajax({
				url: global.server + url,
				type: "get",
				data: {"regionCode":parentCode},
				success: function (msg) {
					if (msg && msg.status && msg.status.statusCode == global.status.success) {
						var data = msg.data;
						callback(data, initCode);
					}
				}
			});
		} else {
			callback([], initCode);
		}
	};
	
};