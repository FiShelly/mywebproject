<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/ad_topMenu.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ad_setBgColor.js"></script>
<script type="text/javascript">
	function isLogout(){
		if(window.confirm("确定退出登录？")){
			window.open("${pageContext.request.contextPath}/LogoutServlet", "_top","fullscreen=yes");
		}
	}
</script>
</head>
<div class="banner">
	<!--放置顶部目录栏-->
	<div class="top_menu">
		<table>
			<tr>
				<td style="width:200px;padding-left:13px;text-align:left;"><p style="font-size:18px;font-family:黑体;color:white;white-space:nowrap;margin-top:-25px;">畜牧检疫防疫信息时空分析及管理系统</p></div></td>
				<td><img src="${pageContext.request.contextPath }/image/cuttingLine.png" style="width:10px;height:35px;padding-bottom:22px;" /></td>
				<td><a href="${pageContext.request.contextPath }/model1_user/model1_user_index.jsp" target="_top" id="check" onclick="setTopMenuBgColor('check')"><img src="${pageContext.request.contextPath }/image/message.png" style="width:29px;height:31px;"/><p>养殖档案管理</p></a></td>
				
				<td><a href="${pageContext.request.contextPath }/model3_user/model3_user_index.jsp" target="_top" id="info" style="background-color:#003637;" onclick="setTopMenuBgColor('info')"><img src="${pageContext.request.contextPath }/image/needle.png" style="width:33px;height:30px;"/><p>疫情信息管理</p></a></td>
				<td><a href="${pageContext.request.contextPath}/model4_user/model4_user_index.jsp"  target="_top" id="gis" onclick="setTopMenuBgColor('gis')"><img src="${pageContext.request.contextPath }/image/watchMap.png" style="width:37px;height:31px;"/><p>GIS平台</p></a></td>
				
				
				
				<td><img src="${pageContext.request.contextPath }/image/cuttingLine.png" style="width:10px;height:35px;padding-bottom:22px;" /></td>
				<td style="padding-right:33px;text-align:right;vertical-align:top;padding-top:15px;">
					<p>${user.farm.leader }[普通用户]</p>
					<a href="#"   style="background-color:#12a686;font-family: 宋体;font-size: 12px;color: white;white-space: nowrap;
							margin-top:5px;border:0px;text-decoration:underline;" onclick="isLogout();">
						退出
					</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</html>
