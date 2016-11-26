$(function(){
	var ticketId=global.QueryString.ticketId;
	var _password=$('#reg_pass');
	var password=$('#re_pass');
	
	$('#LoginBtn').click(function(){
		$.ajax({
            type: "post",
            url: global.server + '/admin/staff/validTicket',
            data: {ticketId:ticketId},
            async: true,
            success: function(msg) {
            	if (msg && msg.status && msg.status.statusCode == global.status.success) {
            		var validPass=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/;
            		if($.trim(_password.val())=="") {
            			_password.focus().css('border-color', '#e14831').next().show().html('密码不能为空');
            			return false;
            		} else {
            			_password.css('border-color', '').next().hide();
            		}
            		if(!validPass.test(_password.val())) {
            			_password.focus().css('border-color', '#e14831').next().show().html('密码为6-10位英文、数字组合');
            			return false;
            		} else {
            			_password.css('border-color', '').next().hide();
            		}
            		if($.trim(password.val())=='') {
            			password.focus().css('border-color', '#e14831').next().show().html('请再次输入密码');
            			return false;
            		}
            		if(_password.val()!=password.val()) {
            			password.focus().css('border-color', '#e14831').next().show().html('两次密码不一致');
            			return false;
            		}
            		
            		$.ajax({
			            type: "post",
			            url: global.server + '/admin/staff/resetPasswordByTicket',
			            data: {ticketId:ticketId,password:$.md5_32(password.val())},
			            async: true,
			            success: function(msg) {
			            	if (msg && msg.status && msg.status.statusCode == global.status.success) {
			    				alert('修改成功');
			    				location.href="login.html";
			                } else if(msg.status.statusCode != global.status.success){
			                	//console.log('链接失效');
			                }
			            }
			        });
                } else if(msg.status.statusCode != global.status.success){
                	console.log('链接已失效');
                }
            }
        });
	})
	
});