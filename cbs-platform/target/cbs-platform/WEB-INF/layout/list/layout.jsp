<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>处理订单</title>
		<%@ include file="/common/resource.jsp"%>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/list.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css"/>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-2.1.1.min.js"></script>
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui.min.js"></script> -->
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/datepicker.min.js"></script> -->
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.multiselect.js"></script> -->
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.multiselect.filter.js"></script> -->
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.autocomplete.js"></script> -->
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/moment.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/store.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.md5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/global_config.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/pagination.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/CBSTable.js"></script>
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/CBSConfirm.js"></script> -->
		<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/CBSAutocomplete2.js"></script> -->
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<div class="wrap">
			<table class="wrap-table">
				<tbody>
					<tr>
						<td class="orange">
							<div class="list-left">	
								<tiles:insertAttribute name="menu" />
							</div>
						</td>
						<td>
							<div class="list-right">	
								<tiles:insertAttribute name="body" />
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<tiles:insertAttribute name="footer" />
	</body>
</html>

