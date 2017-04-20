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
	<form action="FoodDrugUseRecordServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="medicine_record1" name="medicine_record1" class="content_table">
			<tr>
				<td style="color:black;width:94px;">开始时间</td>
				
				<td style="color:black;width:97px;">停止时间</td>
				<td style="color:black;width:97px;">加工时间</td>
				<td style="color:black;width:98px;">批号</td>
				<td style="color:black;width:98px;">产品名称</td>
				<td style="color:black;width:95px;">生产商</td>
				<td style="color:black;width:79px;">用量</td>
				<td style="color:black;width:127px;">备注</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="medicine_record2" name="medicine_record2" class="content_table">
	            
				<c:forEach items="${all }" var="fdur">		
				<tr>
					<td>
						<input type="text" id="startTime_${fdur.key }"  readonly="readonly" name="startTime_${fdur.key }" onclick="laydate()" value="${fdur.value.startTime }"/>
					</td>	
					<td>
						<input type="text" id="stopTime_${fdur.key }"  readonly="readonly" name="stopTime_${fdur.key }" onclick="laydate()" value="${fdur.value.stopTime }"/>
					</td>
					<td>
					<input type="text" id="processDate_${fdur.key }" name="processDate_${fdur.key }"  value="${fdur.value.processDate }"/>
					</td>
					<td>
					<input type="text" id="batchNum_${fdur.key }" name="batchNum_${fdur.key }" value="${fdur.value.batchNum }"/>
					</td>
					<td>
					<input type="text" id="productName_${fdur.key }" name="productName_${fdur.key }"  value="${fdur.value.productName }"/>
					</td>
					<td>
					<input type="text" id="manufacturer_${fdur.key }" name="manufacturer_${fdur.key }" value="${fdur.value.manufacturer }"/>
					</td>
					<td>
					<input type="text" id="dosage_${fdur.key }" name="dosage_${fdur.key }" value="${fdur.value.dosage }"/>
					</td>
					<td>
					<textarea rows="3" id="note_${fdur.key }" name="note_${fdur.key }"  >${fdur.value.note }</textarea>
					<input type = "hidden" name = "id_${fdur.key }" id="id_${fdur.key }" value="${fdur.value.id }"/>
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