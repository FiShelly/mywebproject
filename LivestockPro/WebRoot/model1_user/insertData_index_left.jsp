<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_index2_left.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_showPic.js"></script>
</head>
<body>
	<!--定位-->
			<div class="theUL">
				<table class="menu">
					<tr>
						<td style="color:white;">&nbsp;&nbsp;&nbsp;表格导航</td>
					</tr>
					
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_immuneProgram.jsp" target="index2_center" onclick="showPic('pic2')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic2" style="display:inline;"/>&nbsp;&nbsp;免疫程序</a></td>
					</tr>
					
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_productRecord.jsp" target="index2_center" onclick="showPic('pic9')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic9" style="display:none;"/>&nbsp;&nbsp;生产记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_medicineRecord.jsp" target="index2_center" onclick="showPic('pic3')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic3" style="display:none;"/>&nbsp;&nbsp;饲料、饲料添加剂和兽药使用记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_degassRecord.jsp" target="index2_center" onclick="showPic('pic4')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic4" style="display:none;"/>&nbsp;&nbsp;消毒记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_immuneRecord.jsp" target="index2_center" onclick="showPic('pic5')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic5" style="display:none;"/>&nbsp;&nbsp;免疫记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_treatRecord.jsp" target="index2_center" onclick="showPic('pic6')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic6" style="display:none;"/>&nbsp;&nbsp;诊疗记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_preventRecord.jsp" target="index2_center" onclick="showPic('pic7')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic7" style="display:none;"/>&nbsp;&nbsp;防疫监测记录</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/model1_user/insertData_handleRecord.jsp" target="index2_center" onclick="showPic('pic8')" style="display:block;"><img src="${pageContext.request.contextPath}/image/triangle.png" height="30px" id="pic8" style="display:none;"/>&nbsp;&nbsp;病死畜禽无害化处理记录</a></td>
					</tr>
				</table>
				
			</div>
</body>
</html>
