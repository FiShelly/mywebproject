<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员操作版本二</title>
<frameset rows="60px,*" noresize="noresize" border=0>
	<frame src="${pageContext.request.contextPath}/model2_admin/material_topMenu.jsp" noresize="noresize" name="topMenu" scrolling="no"  />
	<frameset cols="60px,*" noresize="noresize" border=0>
		<frame src="${pageContext.request.contextPath}/model2_admin/material_leftMenu.jsp" name="leftMenu" scrolling="no" />
		<frame src="${pageContext.request.contextPath}/model2_admin/material_content.jsp" name="content" scrolling="no"/>
	</frameset>
</frameset>
</head>
</html>
