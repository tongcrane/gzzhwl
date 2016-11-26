/**
 * 
 */
var CBSAutocomplete2 = function(options){
	
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
		var url = _this.opt.url;
		var type = _this.opt.method;
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
		
		el.devbridgeAutocomplete({
	        lookup: data,
	        appendTo: _this.opt.appendTo,
	        preserveInput : true,
	        forceFixPosition: true,
//	        showNoSuggestionNotice: true,
//	        noSuggestionNotice: '抱歉，没有匹配到相应的值。',
//	        lookupFilter: function(suggestion, originalQuery, queryLowerCase) {
//	            var re = new RegExp('\\b' + $.Autocomplete.utils.escapeRegExChars(queryLowerCase), 'gi');
//	            return re.test(suggestion.value);
//	        },
	        onInvalidateSelection: function () {
	        	_this.value = null;
	        },
	        onBlur : function(){
	        	if(_this.current != _this.value){
	        		_this.current = _this.value;
	        		_this.onChange(null);
//	        		_this.clear();
	        	}
	        },
	        onSelect: function(suggestion) {
	        	var row = suggestion.row;
	        	_this.value = row[valueField];
	        	if(_this.current != _this.value){
	        		_this.current = _this.value;
	        		_this.onChange(row);
	        	}
	        }
	    });
		
		if(_this.opt.initValue != null){
			_this.setValue(_this.opt.initValue);
			_this.opt.initValue = null;
		}
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
		_this.getData();
	}
	
	this.disable = function(){
		var _this = this;
		var el = _this.opt.target;
		el.devbridgeAutocomplete('disable');
		el.prop('readonly', true);
	}
	
	this.enable = function(){
		var _this = this;
		var el = _this.opt.target;
		el.devbridgeAutocomplete('enable');
		el.prop('readonly', false);
	}
	
	this.clear = function(){
		var _this = this;
		_this.value = null;
		_this.opt.target.val('');
		_this.onChange(null);
	}
	
	this.setValue = function(value){
		var _this = this;
		if(!_this.loding){
			_this.value = value;
			var valueField = _this.opt.valueField;
			var textField = _this.opt.textField;
			if(_this.data != null){
				$.each(_this.data, function(i, j){
					if(j[valueField] == value){
						var row = {
							value : _this.opt.rowFilter(j),
							data : j[valueField],
							row : j
						}
						_this.opt.target.devbridgeAutocomplete('setValue', row);
						_this.onChange(j);
						return false;
					}
				});
			}
		} else {
			_this.opt.initValue = value;
		}
	}

	
	this.init();
}

