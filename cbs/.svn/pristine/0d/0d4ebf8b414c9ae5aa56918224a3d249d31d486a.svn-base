/**
 * 费用调整弹窗
 */
var CBSCostAdjust = function(options){
	this.opt = $.extend({}, options);
	
	this.el=$('<div class="mo_del">\
			<div class="model_content adjust">\
			<h4>费用调整</h4>\
			<div class="layout">\
				<label class="labe_l short" for="">费用类型</label>\
				<input type="radio" name="way" value="00" checked="checked">扣款\
				<input type="radio" name="way" value="01">补偿\
			</div>\
			<div class="layout fl">\
				<label class="labe_l relati_ve money" for="">异常费用</label>\
				<input class="inpu_t pad_right" type="text" data-column="itemPrice" onkeyup="if(isNaN(value))execCommand(\'undo\')">\
			</div>\
			<a href="javascript:void(0);" class="verify_elec_receipt">提交</a>\
			<i class="clo_se"></i>\
		</div>\
		</div>');
	
	
	this.init = function(options){
		var _this = this;
		$('body').append(_this.el);
		_this.el.find('input[data-column="itemPrice"]').on('blur',function(){
			var v=$(this).val();
			if(v) {
				$(this).val(Math.abs(Number(v)).toFixed(2));
			}
		});
		_this.el.find('.clo_se').click(function(){
			_this.destory();
		});
		_this.el.find('a').click(function(){
//			_this.opt.callback.call(_this, _this.opt.data);
			
			var data = {};
			data.feedBackId=_this.opt.data.feedbackId;
			data.itemPrice=_this.el.find('input[data-column="itemPrice"]').val();
			data.priceSymbol=_this.el.find("input[name='way']:checked").val();
			var valid=_this.validFeedBack(data);
			if(valid){
			    $.ajax({
			        url: global.server + '/admin/feedback/updateFeedbackCharges',
			        type: "POST",
			        data: data,
			        success: function (msg) {
			            if (msg && msg.status && msg.status.statusCode == global.status.success) {
			            	alert("提交成功");
			            	_this.destory();
			            	window.location.reload();
			            } else {
			            	alert(msg.status.errorMsg);
			            }
			        }
			    });
			}
		});
		
		_this.show();
	};
	
	this.validFeedBack=function(data) {
		var valid=true;
		if(data.itemPrice>=100000) {
			valid=false;
			alert('产生费用最多为5位整数2位小数');
			return false;
		}
		return valid;
	}
	
	this.show = function(){
		var _this = this;
		_this.el.find('.adjust').show();
		_this.el.show();
	}
	
	this.destory = function(){
		var _this = this;
		_this.el.remove();
	}
	
	this.init(options);
}