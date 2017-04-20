<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>模块五admin</title>
</head>
<frameset rows="60px,*" noresize="noresize" border=0>
	<frame src="${pageContext.request.contextPath}/model5_admin/topMenu.jsp" noresize="noresize"
		name="topMenu" scrolling="no" />
	<frameset cols="60px,*" noresize="noresize" border=0>
		<frame src="${pageContext.request.contextPath}/model5_admin/leftMenu.jsp" name="leftMenu"
			scrolling="no" />
		<frame src="${pageContext.request.contextPath}/model5_admin/model5_index_content.jsp" name="content"
			scrolling="no" />
	</frameset>
</frameset>
</html>
