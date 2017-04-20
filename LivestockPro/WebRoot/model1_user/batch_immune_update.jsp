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
	<form action="ImmuneRecordServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="immune_record1" name="immune_record1" class="content_table">
			<tr>
				<td style="color:black;width:98px;">时间</td>
				<td style="color:black;width:90px;">圈舍号</td>
				<td style="color:black;width:103px;">存栏数</td>
				<td style="color:black;width:98px;">免疫数量</td>
				<td style="color:black;width:86px;">疫苗名称</td>
				<td style="color:black;width:90px;">疫苗生产厂</td>
				<td style="color:black;width:80px;">批号</td>
				<td style="color:black;width:100px;">有效期</td>
				<td style="color:black;width:100px;">免疫方法</td>
				<td style="color:black;width:102px;">免疫剂量</td>
				<td style="color:black;width:110px;">免疫人员</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="immune_record2" name="immune_record2" class="content_table">
				<c:forEach items="${all }" var="immune">
					<tr>
						<td>
						<input type="text" id="immuneTime_${immune.key }" readonly="readonly"  name="immuneTime_${immune.key }" onclick="laydate()"  value="${immune.value.immuneTime }"/>
						</td>
						<td>
						<input type="text" id="roomNum_${immune.key }" name="roomNum_${immune.key }" value="${immune.value.roomNum }"/>
						</td>
						<td>
						<input type="text" id="remainNum_${immune.key }" name="remainNum_${immune.key }" value="${immune.value.remainNum }"/>
						</td>
						<td>
						<input type="text" id="immuneNum_${immune.key }" name="immuneNum_${immune.key }" value="${immune.value.immuneNum }"/>
						</td>
						<td>
						<input type="text" id="vaccineName_${immune.key }" name="vaccineName_${immune.key }" value="${immune.value.vaccineName }"/>
						</td>
						<td>
						<input type="text" id="vaccineProducers_${immune.key }" name="vaccineProducers_${immune.key }"  value="${immune.value.vaccineProducers }"/>
						</td>
						<td>
						<input type="text" id="batchNum_${immune.key }" name="batchNum_${immune.key }" value="${immune.value.batchNum }"/>
						</td>
						<td>
						<input type="text" id="vaccineValidTime_${immune.key }" name="vaccineValidTime_${immune.key }" value="${immune.value.vaccineValidTime }"/>
						</td>
						<td>
						<input type="text" id="immuneMethod_${immune.key }" name="immuneMethod_${immune.key }" value="${immune.value.immuneMethod }"/>
						</td>
						<td>
						<input type="text" id="immuneDosage_${immune.key }" name="immuneDosage_${immune.key }" value="${immune.value.immuneDosage }"/>
						</td>
						<td>
						<input type="text" id="immunePeople_${immune.key }" name="immunePeople_${immune.key }" value="${immune.value.immunePeople }"/>
						</td>
						<td>
							<textarea rows="3"id="note_${immune.key }" name="note_${immune.key }">${immune.value.note }</textarea>
							<input type = "hidden" name = "id_${immune.key }" id="id_${immune.key }" value="${immune.value.id }"/>
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
