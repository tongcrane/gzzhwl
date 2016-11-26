<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>登录</title>
        <%@ include file="/common/resource.jsp"%>
    </head>
    <body>
   		<form:form modelAttribute="user" method="post" action="${ pageContext.request.contextPath}/login" id="j_loginform">
			<form:errors path="*" element="div" cssClass="login-err-panel"/>
			<div class="login-form login_on_un">
				<div class="login-un">
					<span class="icon-wrapper"><i class="icon_login un"></i></span>
					<form:input type="text" path="userName" id="j_username" placeholder="用户名" tabindex="1"/>
				</div>
				<div class="login-pwd">
					<span class="icon-wrapper"><i class="icon_login pwd"></i></span>
					<form:input type="password" path="passWord" id="j_password" tabindex="2" placeholder="密码" />
				</div>
				<div class="login-btn-panel" style="border:0px;">
					<a tabindex="4" class="login-btn" title="点击登录" href="javascript:;" id="login_button">登录</a>
				</div>
			</div>
		</form:form>
    </body>
    
    <script type="text/javascript">
		$(function(){
			new LoginForm();
		});
		
		var LoginForm = function(){
			this.container = $("#j_loginform");
			
			//去除空格（左右包括全角半角）
			var trim = function (text) {
				var strTrim = text.replace(/(^\s*)|(\s*$)/g, "");
				strTrim = strTrim.replace(/^[\s　\t]+|[\s　\t]+$/, "");
				var strf = strTrim;
				strf = strf.replace(/(^\s*)|(\s*$)/g, "");
				strf = strf.replace(/^[\s　\t]+|[\s　\t]+$/, "");
				return strf;
			}
			
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
			
			this.bind = function(){
				var _this = this;
				_this.userName = _this.container.find("#j_username");
				_this.passWord = _this.container.find("#j_password");
				
				_this.userName.focus().keydown(function(event) {
					if (event.which == 13) {//改回车事件
						_this.passWord.focus();//用户名回车，密码获得焦点
					}
				}).blur(function(){
					this.value = trim(this.value);
				});
				
				_this.passWord.keydown(function(event) {
					if (event.which == 13) {//改回车事件
						_this.captcha.focus();//密码回车，验证码获得焦点
					}
				});
				
				_this.container.find("#login_button").click(function(){
					_this.doLogin();
				})
			}
			
			this.bind();
		}
	</script>
</html>
