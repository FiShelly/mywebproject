<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="../images/favicon.ico" type="image/x-icon" />
<title>服务器错误</title>
<style type="text/css">
h2 {
	text-align: center;
}

a:LINK {
	color: black;
	text-decoration: none
}

a:VISITED {
	color: black;
}
</style>

</head>
<body >
	<h1>:( 服务器错误，请与管理员联系。</h1>
	<h1>
		:( <span id="redTime">3</span>秒后跳转到登录页面，如果没有跳转请点击下方的跳转链接.
	</h1>
	<h2>
		<a href="/TeacherWorkSys">【跳转】</a>
	</h2>
</body>
<script type="text/javascript">
	var time = 2;
	var intervalid;
	intervalid = setInterval("fun()", 1000);
	function fun() {
		if (time == 0) {
			window.location.href = "/TeacherWorkSys";
			clearInterval(intervalid);
		}
		document.getElementById("redTime").innerHTML = time;
		time--;
	}
</script>
</html>
