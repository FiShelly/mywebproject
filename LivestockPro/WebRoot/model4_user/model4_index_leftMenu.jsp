<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_leftMenu.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_setBgColor.js"></script>
</head>
<body>
<!--左边的目录-->
<div class="left_menu">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/model4_user/model4_index_content.jsp" target="content" id="check" onclick="setBgColor('check')" style="background-color:#232423;">
			<img src="${pageContext.request.contextPath}/image/reserves.png" width="38px" height="26px"/><br/><font>疫情信息上报管理</font></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/model4_user/model4_map_outbreak.jsp" target="content" id="check2" onclick="setBgColor('check2')" >
			<img src="${pageContext.request.contextPath}/image/outbreak.png" width="38px" height="26px"/><br/><font>疫情爆发信息显示</font></a>
		</li>
		
	</ul>
</div>
</body>
</html>