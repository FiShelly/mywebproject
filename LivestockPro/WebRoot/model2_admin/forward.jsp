<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'forward.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
if("${info}" != ""){
	if(confirm("${info}")) {
		refresh();
	} else {
		refresh();
	}
} 
function refresh() {
	window.parent.location = "${pageContext.request.contextPath}/SuppliesServlet?action=list&address=${admin.address }&currentPage=${currentPage}";//刷新 
}
</script>
  </head>
  <body>
  </body>
</html>
