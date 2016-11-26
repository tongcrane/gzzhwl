<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<form:form autocomplete="off" modelAttribute="user" method="post" action="${ pageContext.request.contextPath}/login" id="j_loginform">
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
			<a tabindex="3" class="login-btn" title="点击登录" href="javascript:;" id="login_button">登录</a>
		</div>
	</div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js?v=${sessionId}"></script>