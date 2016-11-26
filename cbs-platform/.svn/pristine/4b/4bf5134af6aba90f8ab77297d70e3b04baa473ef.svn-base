(function($){
	"use strict";
	var LoginForm = function(ele, options){
		this.container = ele;
		
		this.doLogin = function(){
			var _this = this;
			var unv = _this.userName.val() != "";
			var pwv = _this.passWord.val() != "";
			if(!unv){
				_this.userName.focus();
				return false;
			}
			if(!pwv){
				_this.passWord.focus();
				return false;
			}
			_this.container.submit();//表单提交
		}

		this.bind = function(options){
			var _this = this;
			
			_this.userName = _this.container.find(options.userName);
			_this.passWord = _this.container.find(options.passWord);
			
			_this.userName.focus().keydown(function(event) {
				if (event.which == 13) {//改回车事件
					_this.passWord.focus();//用户名回车，密码获得焦点
				}
			}).blur(function(){
				this.value = global.trim(this.value);
			});
			
			_this.passWord.keydown(function(event) {
				if (event.which == 13) {//改回车事件
					_this.doLogin();//验证码回车，提交表单
				}
			});
			
			_this.container.find(options.submit).click(function(){
				_this.doLogin();
			})
		}
		
		this.bind(options);
	}
	
	$.fn.loginForm = function (options) {
		options = $.extend({},$.fn.loginForm.defaults, options);
		return this.each(function() {
			var me = $(this),
				instance = me.data("_loginForm");
			
			if (!instance) {
				instance = new LoginForm(me, options);
				me.data("_loginForm", instance);
			}
			if ($.type(options) === 'string') return instance[options]();
		});
    };
    
    $.fn.loginForm.defaults = {
    	"userName" : "#j_username",
    	"passWord" : "#j_password",
    	"submit" : "#login_button"
	}
    
})(jQuery)

$(function(){
	$("#j_loginform").loginForm();
});