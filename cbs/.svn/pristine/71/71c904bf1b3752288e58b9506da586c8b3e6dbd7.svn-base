var CBSPop = function(options){
	this.opt = $.extend({}, options);
	
	this.el=$('<div class="mo_del">\
			<div class="model_remark">\
			<textarea maxlength="400" name="" id="action_reason"></textarea>\
			<a href="javascript:void(0);">确定</a>\
			<i></i>\
		</div>\
	</div>');
	
	
	this.init = function(options){
		var _this = this;
		_this.el.find('textarea').attr('placeholder',_this.opt.text)
		$('body').append(_this.el);
		_this.el.find('.model_remark i').click(function(){
			_this.destory();
		});
		_this.el.find('.model_remark a').click(function(){
			_this.opt.callback.call(_this, _this.opt.data);
			_this.destory();
		});
		_this.show();
	};
	
	this.show = function(){
		var _this = this;
		_this.el.find('.mo_del').show();
		_this.el.show();
	}
	
	this.destory = function(){
		var _this = this;
		_this.el.remove();
	}
	
	this.init(options);
}