<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>管理员模块四</title>
</head>
<frameset rows="60px,*" noresize="noresize" border=0>
        <frame src="${pageContext.request.contextPath}/model4_admin/model4_index_topMenu.jsp" noresize="noresize" name="topMenu" scrolling="no"/>
        <frameset cols="60px,*" noresize="noresize" border=0>
            <frame src="${pageContext.request.contextPath}/model4_admin/model4_index_leftMenu.jsp" name="leftMenu" scrolling="no"/>
            <frame src="${pageContext.request.contextPath}/model4_admin/model4_content_epidemic.jsp" name="content" scrolling="no"/>
        </frameset>
    </frameset>
</html>