/**
 * 
 */
var CBSSelect = function(options){
	
	var defaultOpt = {
		target : null,
		url:'',
		param: {},
		rowFilter : function(row){
			return row[this.textField];
		},
		loadFilter : function(data){
			return data;
		},
		onBeforeLoad : function(param){
			return true;
		},
		minWidth : null,
		onChange : $.noop,
		initValue : null,
		method : 'post',
	    valueField :  'id',
	    textField : 'text'
	};
	this.loding = true;
	
	this.data = null;
	this.value = null;
	this.current = null;
	
	this.opt = $.extend({}, defaultOpt, options);
	
	this.getData = function(param){
		var _this = this;
		var param = $.extend({}, _this.opt.param, param);
		if(_this.opt.onBeforeLoad.call(_this, param)){
			var type = _this.opt.method;
			var url = _this.opt.url;
			_this.loding = true;
			$.ajax({
	            url: global.server + url,
	            type: type,
	            data: param,
	            success: function (msg) {
	                if (msg && msg.status && msg.status.statusCode == global.status.success) {
	                	var data = _this.opt.loadFilter.call(_this, msg.data);
	                	_this.data = data;
	                	_this.loding = false;
	                	_this.fillData();
	                } else {
	                	_this.data = null;
	                	_this.loding = false;
	                }
	            }
	        });
		} else {
			_this.loading = false;
		}
	}
	
	this.fillData = function(){
		var _this = this;
		var valueField = _this.opt.valueField;
	    var textField = _this.opt.textField;
	    
		var data = $.map(_this.data, function(value, key) {
			return {
				value : _this.opt.rowFilter(value),
				data : value[valueField],
				row : value
			};
		});
		
		var el = _this.opt.target;
		
		el.empty();
		var none = $('<option value="">请选择</option>').data('row',null);
		el.append(none);
        
        $.each(data, function(i, j){
        	var row = $('<option value="' + j.data + '">' + j.value +'</option>');
        	row.data('row', j.row);
        	el.append(row);
        });
        
        _this.refresh();
        
		if(_this.opt.initValue != null){
			_this.setValue(_this.opt.initValue);
			_this.opt.initValue = null;
		}
	}
	
	this.refresh = function(){
		var _this = this;
		var el = _this.opt.target;
		el.multiselect("refresh");
	}
	
	this.onChange = function(row){
		var _this = this;
		_this.opt.onChange.call(_this, row);
	}
	
	this.getValue = function(){
		var _this = this;
		return _this.value;
	}
	
	this.init = function(){
		var _this = this;
		if(_this.opt.initValue){
			_this.current = _this.value;
		}
		var valueField = _this.opt.valueField;
	    var textField = _this.opt.textField;
	    var minWidth = _this.opt.minWidth;
	    
		var el = _this.opt.target;
		el.multiselect({
         	header : false,
 			multiple : false,
 			selectedList : 1,
 			minWidth : minWidth,
 			noneSelectedText: '请选择',
 			click : function(event, ui) {
 				var row = ui.row;
 				if(row != null){
 					_this.value = row[valueField];
 				} else {
 					_this.value = null;
 				}
 				if(_this.current != _this.value){
	        		_this.current = _this.value;
	        		_this.onChange(row);
	        	}
 			}
         });
		_this.getData({});
	}
	
	this.disable = function(){
		var _this = this;
		var el = _this.opt.target;
		el.multiselect('disable');
	}
	
	this.enable = function(){
		var _this = this;
		var el = _this.opt.target;
		el.multiselect('enable');
	}
	
	this.clear = function(){
		var _this = this;
		var el = _this.opt.target;
		el.empty();
		_this.data = null;
		_this.value = null;
		_this.refresh();
		_this.onChange(null);
	}
	
	this.setValue = function(value){
		var _this = this;
		if(!_this.loding){
			_this.value = value;
			var el = _this.opt.target;
			el.multiselect('setValue', value)
		} else {
			_this.opt.initValue = value;
		}
	}
	
	this.init();
}

