<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--这是免疫程序的修改弹出窗口-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_update.css"/>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript">
function goUpdate(){
	document.form1.submit();
}
</script>
</head>
<body>
	<form action="MedicalRecordServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="treat_record1" name="treat_record1" class="content_table">
			
			<tr>
				<td style="color:black;width:87px;">时间</td>
				<td style="color:black;width:94px;">存栏数</td>
				<td style="color:black;width:99px;">圈舍号</td>
				<td style="color:black;width:84px;">日龄</td>
				<td style="color:black;width:92px;">发病数</td>
				<td style="color:black;width:88px;">病因</td>
				<td style="color:black;width:89px;">诊疗人员</td>
				<td style="color:black;width:99px;">用药名称</td>
				<td style="color:black;width:94px;">用药方法</td>
				<td style="color:black;width:90px;">诊疗结果</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="treat_record2" name="treat_record2" class="content_table">
				<c:forEach items="${all }" var="med">
					<tr>
						<td>
						<input type="text" id="medicalTime_${med.key }" name="medicalTime_${med.key }" onclick="laydate();" value="${med.value.medicalTime }" />
						</td>
						<td>
						<input type="text" id="remain_${med.key }" name="remain_${med.key }" value="${med.value.remain }"/>
						</td>
						<td>
						<input type="text" id="roomNum_${med.key }" name="roomNum_${med.key }" value="${med.value.roomNum }"/>
						</td>
						<td>
						<input type="text" id="dateAge_${med.key }" name="dateAge_${med.key }" value="${med.value.dateAge }"/>
						</td>
						<td>
						<input type="text" id="sickNum_${med.key }" name="sickNum_${med.key }" value="${med.value.sickNum }"/>
						</td>
						<td>
						<input type="text" id="sickReason_${med.key }" name="sickReason_${med.key }" value="${med.value.sickReason }" />
						</td>
						<td>
						<input type="text" id="medicalPeo_${med.key }" name="medicalPeo_${med.key }" value="${med.value.medicalPeo }"/>
						</td>
						<td>
						<input type="text" id="drugName_${med.key }" name="drugName_${med.key }" value="${med.value.drugName }"/>
						</td>
						<td>
						<input type="text" id="method_${med.key }" name="method_${med.key }" value="${med.value.method }"/>
						</td>
						<td>
						<input type="text" id="medicalResult_${med.key }" name="medicalResult_${med.key }" value="${med.value.medicalResult }"/>
						<input type = "hidden" name = "id_${med.key }" id="id_${med.key }" value="${med.value.id }"/>
						</td>							
					</tr>  
					</c:forEach>
    	</table>
		</div>
		<input type="hidden" name="farmId" id="farmId" value="${user.farm.farmId }"/>
		<input type="hidden" name="action" id="action" value="updateAll"/>
		<input type="hidden" name="role" id="role" value="user"/>
		<input type="hidden" name="index" id="index" value="${index }"/>
		<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage }"/>
	</form>   
</body>
</html>