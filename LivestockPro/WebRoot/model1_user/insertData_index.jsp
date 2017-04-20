<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="30px,*" noresize="noresize" border=0>
	<frame src="${pageContext.request.contextPath}/model1_user/insertData_index_top.jsp" noresize="noresize" name="insertData_index_top" scrolling="no"/>
	<frameset cols="223px,*" noresize="noresize" border=0>
		<frame src="${pageContext.request.contextPath}/model1_user/insertData_index_left.jsp" noresize="noresize" name="insertData_index_left" scrolling="no"/>
		<frame src="${pageContext.request.contextPath}/model1_user/insertData_immuneProgram.jsp" noresize="noresize" name="index2_center" scrolling="no"/>
	</frameset>
</frameset>
</html>
