<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>普通模块四</title>
</head>
<frameset rows="60px,*" noresize="noresize" border=0>
        <frame src="${pageContext.request.contextPath}/model4_user/model4_index_topMenu.jsp" noresize="noresize" name="topMenu" scrolling="no"/>
        <frameset cols="60px,*" noresize="noresize" border=0>
            <frame src="${pageContext.request.contextPath}/model4_user/model4_index_leftMenu.jsp" name="leftMenu" scrolling="no"/>
            <frame src="${pageContext.request.contextPath}/model4_user/model4_index_content.jsp" name="content" scrolling="no"/>
        </frameset>
    </frameset>
</html>
