var CBSPcdSelect = function(options, container){
	this.opt = $.extend({}, {showEmpty:true}, options);
	
	
	this.init = function(opt){
		var _this = this;
		var _ptarget = container.find(".pcd-select-p");
		var _ctarget = container.find(".pcd-select-c");
		var _dtarget = container.find(".pcd-select-d");
		
		this.pSelect = new CBSPcsSelectRow(_ptarget, _this.opt);
		this.cSelect = new CBSPcsSelectRow(_ctarget, _this.opt);
		this.dSelect = new CBSPcsSelectRow(_dtarget, _this.opt);
		

		_ptarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			noneSelectedText: '请选择',
			minWidth : _this.opt.width,
			click: function(event, ui){
				_this.cSelect.refresh(ui.id);
		    }
		});
		_ctarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			noneSelectedText: '请选择',
			minWidth : _this.opt.width,
			click : function(event, ui) {
				_this.dSelect.refresh(ui.id);
			}
		});
		
		_dtarget.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			noneSelectedText: '请选择',
			minWidth : _this.opt.width
		});
		
		
		
		var initCode = _this.opt.initCode;
		
		_this.showDefault(initCode);
	};
	
	this.showDefault = function(code){
		var _this = this;
		if(code){
			$.ajax({
		        url: global.server + '/api/region/findRootByCode',
		        type: "get",
		        data: {"regionCode":code},
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	var data = msg.data;
		            	var existsD = false;
		            	var existsC = false;
		            	$.each(data, function(i, j){
		            		if(j.regionLevel == 3){
		            			_this.dSelect.refresh(j.parentRegionId, j.regionId);
		            			existsD = true;
		            		} else if(j.regionLevel == 2){
		            			_this.cSelect.refresh(j.parentRegionId, j.regionId);
		            			existsC = true;
		            		} else if (j.regionLevel == 1){
		            			_this.pSelect.refresh(j.parentRegionId, j.regionId);
		            		}
		            	});
		            	$.each(data, function(i, j){
		            		if(j.regionLevel == 2 && !existsD){
		            			_this.dSelect.refresh(j.regionId);
		            		} else if (j.regionLevel == 1 && !existsC){
		            			_this.cSelect.refresh(j.regionId);
		            		}
		            	});
		            	
		            }
		        }
		    });
		} else {
			_this.pSelect.refresh(1);
		}
	};
	
	this.getValue = function(){
		var _this = this;
		var category = _this.opt.category;
		var _value = _this.dSelect.getValue();
		if(_value==""){
			_value = _this.cSelect.getValue();
		}
		if(_value==""){
			_value = _this.pSelect.getValue();
		}
		return {category:category,code:_value};
	};
	
	this.getText = function(){
		var _this = this;
		var _text = [];
		var _dt = _this.dSelect.getText();
		var _ct = _this.cSelect.getText();
		var _pt = _this.pSelect.getText();
		_text.push(_pt);
		_text.push(_ct);
		_text.push(_dt);
		return _text;
	}
	
	
	this.init(options, container);
};

var CBSPcsSelectRow = function(container, options){
	this.container = container;
	this.opt = $.extend({}, options);
	
	this.refresh = function(parentId, initId){
		var _this = this;
		_this.getData(parentId, _this.showData, initId);
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
//			console.log("refresh");
			container.multiselect("widget").find("label.ui-multiselect-option:first").each(function(){
				this.click();
			});
		}
	};
	
	this.getValue = function(){
		return container.val();
	}
	
	this.getText = function(){
		var val = container.val();
		if(val != ''){
			return container.multiselect("getButton").text();
		} else {
			return '';
		}
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
	
};