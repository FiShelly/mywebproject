<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_leftMenu.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_setBgColor.js"></script>
</head>
<body>
	<div class="left_menu">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/model5_admin/model5_index_content.jsp" target="content" id="file" onclick="setBgColor('file')" style="background-color:#232423;">
			<img src="${pageContext.request.contextPath}/image/file.png" width="38px" height="26px"/><br/><span>信息统计</span></a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/model5_admin/warning_index.jsp" target="content" id="waring" onclick="setBgColor('waring')">
			<img src="${pageContext.request.contextPath}/image/waring2.png" width="38px" height="26px" style="opacity:0.8"/><br/><span>预警</span></a>
		</li>
	</ul>
</div>
</body>
</html>
</html>
