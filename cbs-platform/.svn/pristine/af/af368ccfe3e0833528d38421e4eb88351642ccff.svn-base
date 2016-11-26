<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:forEach var="menu" items="${subMenu }">
	<c:if test="${menu.isLeaf eq '1'}">
		<c:set var="current" value=""/>
		<c:if test="${page eq menu.description}">
			<c:set var="current" value="current"/>
		</c:if>
		<li><a href="${ pageContext.request.contextPath}${menu.menuPath }" class="${current }">${menu.menuName }</a></li>
	</c:if>
	<c:if test="${menu.isLeaf eq '0'}">
		<li>
			<a href="javascript:void(0);" class="down-icon">${menu.menuName }</a>
			<ul class="two-menu">
				<c:set var="subMenu" value="${menu.subMenu}" scope="request" />
				<c:import url="menu_item.jsp" />
			</ul>
		</li>
	</c:if>
</c:forEach>