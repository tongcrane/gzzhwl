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
	</head>
<body>
	<tiles:insertAttribute name="header" />
	<div class="wrap">
		<table class="wrap-table">
			<tbody>
				<tr>
					<td>
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

