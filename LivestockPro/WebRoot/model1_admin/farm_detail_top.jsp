<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/ad_index2_top.css"/>
</head>
<body>
	<div class="address">
			<font style="font-size:14px;font-family:黑体;white-space:nowrap;">
			您的位置:&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/FarmMesServlet?action=list&address=${admin.address}" target="content">
			<font style="color:black;">养殖档案管理
			</font></a></font>&nbsp;>&nbsp;<font style="color:gray;font-family:黑体;font-size:14px;white-space:nowrap;">查看详情</font>
	</div>
</body>
</html>
