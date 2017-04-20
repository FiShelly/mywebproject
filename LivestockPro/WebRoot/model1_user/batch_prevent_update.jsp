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
	<form action="EpidemicMonitoringServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="prevent_record1" name="prevent_record1" class="content_table">
			<tr>
				<td style="color:black;width:108px;">采样日期</td>
				<td style="color:black;width:112px;">圈舍号</td>
				<td style="color:black;width:108px;">采样</td>
				<td style="color:black;width:97px;">监测项目</td>
				<td style="color:black;width:108px;">监测单位</td>
				<td style="color:black;width:108px;">监测结果</td>
				<td style="color:black;width:101px;">处理情况</td>
				<td style="color:black;width:122px;">备注</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="prevent_record2" name="prevent_record2" class="content_table">
	        	<c:forEach items="${all}" var="epi">
					<tr>
						<td>
						<input type="text" id="samplingTime_${epi.key }"  readonly="readonly" name="samplingTime_${epi.key }" onclick="laydate()" value="${epi.value.samplingTime }"/>
						</td>
						<td>
						<input type="text" id="roomNum_${epi.key }" name="roomNum_${epi.key }" value="${epi.value.roomNum }" />
						</td>
						<td>
						<input type="text" id="samplingNum_${epi.key }" name="samplingNum_${epi.key }"  value="${epi.value.samplingNum }" />
						</td>
						<td>
						<input type="text" id="monitoringName_${epi.key }" name="monitoringName_${epi.key }" value="${epi.value.monitoringName }" />
						</td>
						<td>
						<input type="text" id="monitoringStation_${epi.key }" name="monitoringStation_${epi.key }" value="${epi.value.monitoringStation }"/>
						</td>
						<td>
						<input type="text" id="monitoringResult_${epi.key }" name="monitoringResult_${epi.key }"  value="${epi.value.monitoringResult }" />
						</td>
						<td>
						<input type="text" id="disposalConditions_${epi.key }" name="disposalConditions_${epi.key }"  value="${epi.value.disposalConditions }"/>
						</td>
						<td>
						<textarea rows="3"id="note_${epi.key }" name="note_${epi.key }">${epi.value.note }</textarea>
						<input type = "hidden" name = "id_${epi.key }" id="id_${epi.key }" value="${epi.value.id }"/>
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
