<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>



<div class="sidebar-menu">
	<h2>${currentCategory.menuName }</h2>
	<ul class="sub-menu">
		<c:set var="subMenu" value="${currentCategory.subMenu }" scope="request" />
		<c:import url="menu_item.jsp" />
		<!-- <li><a href="businessSourceNew.html" class="current">处理订单</a></li>
		<li>
			<a href="javascript:void(0);" class="down-icon">调度</a>
			<ul class="two-menu">
				<li><a href="">配载</a></li>
				<li><a href="">调配运力</a></li>
			</ul>
		</li>
		<li><a href="">发车</a></li>
		<li>
			<a href="javascript:void(0);" class="down-icon">在途管理</a>
			<ul class="two-menu">
				<li><a href="">在途跟踪</a></li>
				<li><a href="">查看地图</a></li>
			</ul>
		</li>
		<li><a href="">到达</a></li>
		<li><a href="">总览</a></li> -->
	</ul>
</div>
<script type="text/javascript">
$(function(){
	$('.down-icon').click(function(){
		$(this).toggleClass("fold");
		$(this).next('.two-menu').toggle('fast');
	});
});
</script>
