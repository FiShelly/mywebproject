<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="30px,*" noresize="noresize" border=0>
	<frame src="${pageContext.request.contextPath}/model1_admin/farm_detail_top.jsp" noresize="noresize" name="index2_top" scrolling="no"/>
	<frameset cols="223px,*" noresize="noresize" border=0>
		<frame src="${pageContext.request.contextPath}/model1_admin/farm_detail_left.jsp?farmId=${farmId}&farmName=${farmName}" noresize="noresize" name="index2_left" scrolling="no"/>
		<frame src="FarmMesServlet?action=detail&farmId=${farmId}" noresize="noresize" name="index2_center" scrolling="no"/>
	</frameset>
</frameset>
</html>