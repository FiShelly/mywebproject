<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_immuneProgram_check2.css"/>
</head>
<body>
	<div class="content">
		<div class="content_banner">
			<font>${user.farm.farmName}</font>
		</div>
		
		<div class="button">
			<form id="btns" name="btns" method="post" action="#">
				<input type="button" value="返回" id="return" name="return" onclick="top.location.href='${pageContext.request.contextPath}/model1_admin_html/index.html'" target="_top" />
			</form>
		</div>
	
	<div>
</body>
</html>
