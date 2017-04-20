<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<frameset rows="30px,*" noresize="noresize" border=0>
	<frame src="${pageContext.request.contextPath}/model1_admin/immune_program_topBanner.jsp?farmName=${param.farmName}" scrolling="no" name="topBanner"/>
	<frameset cols="106px,*" noresize="noresize" border=0>
		<frame src="${pageContext.request.contextPath}/ImmuneProServlet?action=species&farmId=${param.farmId}" scrolling="no" name="leftMenu"/>
		<frame src="${pageContext.request.contextPath}/model1_admin/farm_immune_program.jsp" scrolling="no" name="centerContent"/>
	</frameset>
</frameset>

</html>
