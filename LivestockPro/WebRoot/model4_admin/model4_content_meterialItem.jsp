<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model4_meterialInfo.css">
	<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model4_dispatchContent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_model2_updateDetail.css" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model4_search.js"></script>
	<script type="text/javascript">
	function openInsertWin(id){
		 document.getElementById('dispatch').src="${pageContext.request.contextPath }/SuppliesServlet?action=insertPre_disSup&supId="+id;
		 ShowDiv('MyDiv2','fade');
	}
	</script>
</head>
<body>
	<!--弹出的窗口-->
	<div id="MyDiv2" class="white_content2">
		<iframe  id="dispatch"  width="99%" height="97%"></iframe>
	</div>
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay">
	</div>
	<div class="content">
		
		<table>
			
			<thead><tr>
				<th width="15%">
					<span>物资编号</span>
					
					
				</th>
				<th width="17%">
					<span>物资名称</span>
					
				</th >
				<th width="13%">
					<span>生产日期</span>
					
				</th>
				<th class="numeric" width="10%">
					保质期
				</th>
				<th class="numeric" width="12%">
					数量
				</th>
				<th width="23%">
					<span>物资生产商</span>
				</th>
				<th width="7%">
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sup.supItems }" var="item">
			<tr>
				<td>${item.suppliesId }</td>
				<td>${item.suppliesName }</td>
				<td>${item.productDate }</td>
				<td>${item.validDate }</td>
				<td>${item.number }</td>
				<td>${item.producter }</td>
				<td><a href="javascript:void(0)" id="dispatch_info" onclick="openInsertWin('${item.suppliesId }')">调度</a></td>
			</tr>
			</c:forEach>
		
		</tbody>
	</table>
</div>
</body>
</html>