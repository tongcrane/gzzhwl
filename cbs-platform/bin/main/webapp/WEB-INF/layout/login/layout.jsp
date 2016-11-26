<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<title>Welcome</title>
	</head>
<body>
	<tiles:insertAttribute name="header" />
	<br/>
	<tiles:insertAttribute name="body" />
	<br/>
	<tiles:insertAttribute name="footer" />
</body>
</html>