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
	<form action="DisinfectionRecordServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="degass_record1" name="degass_record1" class="content_table">
			<tr>
				<td style="color:black;width:124px;">日期</td>
				<td style="color:black;width:127px;">消毒场所</td>
				<td style="color:black;width:118px;">消毒药名称</td>
				<td style="color:black;width:116px;">用药剂量</td>
				<td style="color:black;width:128px;">消毒方法</td>
				<td style="color:black;width:130px;">操作人</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="degass_record2" name="degass_record2" class="content_table">
	        	<c:forEach items="${all }" var="dis">
					<tr>
						<td>
						<input type="text" id="disinfectionTime_${dis.key }" readonly="readonly" name="disinfectionTime_${dis.key }" onclick="laydate()" value="${dis.value.disinfectionTime }"/>
						</td>
						<td>
						<input type="text" id="place_${dis.key }" name="place_${dis.key }" value="${dis.value.place }"/>
						</td>
						<td>
						<input type="text" id="disinfectionName_${dis.key }" name="disinfectionName_${dis.key }" value="${dis.value.disinfectionName }"/>
						</td>
						<td>
						<input type="text" id="disinfectionDose_${dis.key }" name="disinfectionDose_${dis.key }" value="${dis.value.disinfectionDose }"/>
						</td>
						<td>
						<input type="text" id="method_${dis.key }" name="method_${dis.key }" value="${dis.value.method }"/>
						</td>
						<td>
						<input type="text" id="sign_${dis.key }" name="sign_${dis.key }" value="${dis.value.sign }"/>
						<input type = "hidden" name = "id_${dis.key }" id="id_${dis.key }" value="${dis.value.id }"/>
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
