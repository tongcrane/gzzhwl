/**
 * 
 */
var CBSTable = function(options){
	this.opt = $.extend({}, options);
	
	this.pageContainer = this.opt.page;
	this.contentContainer = this.opt.content;
	this.pageIndex = this.opt.pageNumber;
	this.pageSize = this.opt.pageSize;
	this.hasNext = false;
	this.hasPrev = false;
	this.pageCount = 0;
	this.total = 0;
	this.param = $.extend({}, this.opt.queryParams);
	
	var _this = this;
	
	this.load = function(param){
		var _this = this;
		this.pageIndex = this.opt.pageNumber;
		this.pageSize = this.opt.pageSize;
		this.param = $.extend({}, this.opt.queryParams, param);
		_this.getData(_this.renderRow);
	};
	
	this.reload = function(){
		var _this = this;
		_this.getData(_this.renderRow);
	};
	
	this.renderRow = function(data){
		var _this = this;
		_this.contentContainer.empty();
		if(typeof _this.opt.rowRender === "function"){
			$.each(data,function(i, j){
				var el = _this.opt.rowRender(i, j);
				_this.contentContainer.append(el);
			});
		}
	}
	
	this.init = function(options){
		var _this = this;
		_this.reload();
		_this.render();
	};
	
	this.render = function(){
		var _this = this;
		_this.pageContainer.empty();
		var el = $('<span>第<i class="page-num">1</i>页，共<i class="page-total">10</i>页</span> \
					<div class="han-page"> \
						<button class="handle-pre"></button> \
						<button class="handle-next"></button> \
					</div> \
					<select class="page-select"> \
						<option value="" selected>第1页</option> \
					</select>');
		
		_this.pageContainer.append(el);
		
		var pageSelect = _this.pageContainer.find('.page-select');
		pageSelect.empty();
		if(_this.pageCount>0){
			for(var i = 1; i<=_this.pageCount; i++){
				var op = $('<option></option>').text('第'+i+'页').val(i);
				if((_this.pageIndex+1)==i){
					op.attr("selected", true);
				}
				pageSelect.append(op);
			}
		} else {
			var op = $('<option></option>').text('第1页').val(1);
			pageSelect.append(op);
		}
		pageSelect.multiselect({
			header : false,
			multiple : false,
			selectedList : 1,
			minWidth : 100
		});	
		
		pageSelect.change(function(){
			_this.pageIndex = this.value-1;
			_this.reload();
		});
		
		
		_this.pageContainer.find('.page-num').html(_this.pageIndex+1);
		if(_this.pageCount>0){
			_this.pageContainer.find('.page-total').html(_this.pageCount);
		} else {
			_this.pageContainer.find('.page-total').html(1);
		}
		
		_this.pageContainer.find('.handle-next').click(function(){
			if(_this.hasNext){
				_this.pageIndex++;
				_this.reload();
			}
		});
		_this.pageContainer.find('.handle-pre').click(function(){
			if(_this.hasPrev){
				_this.pageIndex--;
				_this.reload();
			}
		});
	};
	
	this.refreshPage = function(data){
		var _this = this;
		_this.pageIndex = data.current;
    	_this.pageSize = data.pageSize;
    	_this.hasNext = data.hasNext;
    	_this.hasPrev = data.hasPrev;
    	_this.pageCount = data.pageCount;
    	_this.total = data.total;
    	_this.render();
	}
	
	this.getData = function(callback){
		var _this = this;
		var url = _this.opt.url;
		var param = _this.param;
		param.pageIndex = _this.pageIndex;
		param.pageSize = _this.pageSize;
		var onBeforeLoad = true;
		
		if(typeof _this.opt.onBeforeLoad === "function"){
			onBeforeLoad = _this.opt.onBeforeLoad(param);
		}
		var method = _this.opt.method;
		if(!method){
			method = "post";
		}
		if(onBeforeLoad){
			$.ajax({
		        url: global.server + url,
		        type: method,
		        data: param,
		        success: function (msg) {
		            if (msg && msg.status && msg.status.statusCode == global.status.success) {
		            	var data = msg.data;
		            	_this.refreshPage(data);
		            	callback.call(_this,data.rows);
		            }
		        }
		    });
		}
	};
	
	
	this.init(this.opt);
};