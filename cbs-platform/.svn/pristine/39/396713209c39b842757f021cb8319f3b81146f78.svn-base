<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8"/>
		<meta name="renderer" content="webkit"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta content="always" name="referrer"/>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>处理订单</title>
		<script type="text/javascript">
			var global_authorization = '${currentUser.staffId}:${currentUser.token}';
		</script>
		<%@ include file="/common/resource.jsp"%>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/list.css?v=${sessionId}"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pagination.css?v=${sessionId}"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/pagination.js?v=${sessionId}"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/CBSTable.js?v=${sessionId}"></script>
	</head>
	<body>
		<tiles:insertAttribute name="header" flush="true"/>
		<div class="wrap">
			<table class="wrap-table">
				<tbody>
					<tr>
						<td class="orange">
							<div class="list-left">	
								<tiles:insertAttribute name="menu" flush="true"/>
							</div>
						</td>
						<td>
							<div class="list-right">
								<tiles:insertAttribute ignore="true" name="body" flush="true"/>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<tiles:insertAttribute name="footer" flush="true"/>
	</body>
</html>

