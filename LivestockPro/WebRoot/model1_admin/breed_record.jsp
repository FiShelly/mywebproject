<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/ad_leftMenu.css"/>
<script type="text/javascript" src="../js/ad_setBgColor.js"></script>
</head>
<body>
<!--左边的目录-->
<div class="left_menu">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/FarmMesServlet?action=list&address=${admin.address}" target="content" id="check" onclick="setBgColor('check')" style="background-color:#232423;">
			<img src="../image/eye.png" width="35px" height="26px"/><br/><font>查看</font></a>
			
		</li>
	</ul>
</div>
</body>
</html>