/*
* @Author: Administrator
* @Date:   2016-06-06 16:20:35
* @Last Modified by:   Administrator
* @Last Modified time: 2016-07-22 14:23:13
*/
$(function () {
	if (!global.isLogin()) {
		$("#login .reg_hint").hide();
		$('#login').on('click', '#LoginBtn', function(event) {
			var mobile = $("#login #phone");
			var pass = $("#login #pass");
		    var remember = $("#login #remember");
			var msg = "";
			if ($.trim(mobile.val())=="") {
				//msg = "手机号不能为空！";
				msg="请输入手机号";
				mobile.focus().css('border-color', '#e14831').next().show().html(msg);
				return false;
			}else if (!/^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test($.trim(mobile.val()))) {
				msg = "手机号格式不正确！";
				mobile.focus().css('border-color', '#e14831').next().show().html(msg);
				return false;
			}else{
				mobile.css('border-color', '').next().hide();
			} 
			if ($.trim(pass.val())=="") {
				msg = "请输入密码";
				pass.focus().css('border-color', '#e14831').next().show().html(msg);
				return false;
			// }else if (!/^\w{6,20}$/.test($.trim(pass.val()))) {
			// 	msg = "密码格式不正确！";
			// 	pass.focus().css('border-color', '#e14831').next().show().html(msg);
			// 	return false;
			}else{
				pass.css('border-color', '').next().hide();
			}

			global.doLogin(mobile.val(),pass.val(),function(status) {
				var statusCode=status.statusCode;
				if(statusCode!==global.status.success) {
					mobile.focus().css('border-color', '#e14831').next().show().html("账号或密码错误，请重新输入。");
				} 
			});
		});
	} else {
		location.href=global.getContextPath() + "/index.htm";
	}
})