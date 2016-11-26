<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<c:set var="menu" value="${pf:getMenu(currentUser.functionIds) }"/>

<header id="header">
	<div class="wrap">
		<div class="logo"><a href="javascript:void(0);"></a></div>
		<div class="head-menu">
			<ul>
				<c:forEach var="category" items="${menu }">
					<c:set var="firstFun" value="${pf:getFirstFun(category.subMenu) }"/>
					<c:set var="current" value=""/>
					<c:if test="${floder eq category.description}">
						<c:set var="current" value="active"/>
						<c:set scope="request" var="subMenu" value="${category.subMenu }"/>
					</c:if>
					<li><a class="${current }" href="${ pageContext.request.contextPath}${firstFun.menuPath }">${category.menuName }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="icon"></div>
	</div>
</header>