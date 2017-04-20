<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_check2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_openNewWin.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_insertData_addDelRow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_insertData_submit.js"></script>
<script type="text/javascript">
	function goInsert(){
		if(isSubmit("insertData_medicineRecord")){
			document.form2.submit();
		}
	}
	if("${info}" != ""){
		alert("${info}");
	}
</script>
</head>
<body>
	<div class="content">
		<div class="content_banner">
			<font>${user.farm.farmName }</font>
		</div>
		
		<div class="button">
			<form id="btns" name="btns" method="post" action="#" >
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_medicineRecord',8)" />
				<input type="button" value="保存" id="save" name="save" onclick="goInsert();"/>
				<input type="button" value="返回" id="return" name="return" onclick="if(window.confirm('是否放弃录入??')) return top.location.href='${pageContext.request.contextPath}/model1_user/model1_user_index.jsp'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		
		
	
		<div  class="content_table">
		<!--放入内容-->
		<form action="${pageContext.request.contextPath}/FoodDrugUseRecordServlet" method="post" id="form2" name="form2">
        <table id="insertData_medicineRecord" name="insertData_medicineRecord" >

			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
			
				<td style="color:black;width:134px;padding-left:10px;">开始时间</td>
				
				<td style="color:black;width:137px;">停止时间</td>
				<td style="color:black;width:137px;">加工时间</td>
				<td style="color:black;width:98px;">批号</td>
				<td style="color:black;width:108px;">产品名称</td>
				<td style="color:black;width:115px;">生产商</td>
				<td style="color:black;width:79px;">用量</td>
				<td style="color:black;width:197px;">备注</td>
				<!-- <td style="color:black;width:40px;">
				</td> -->
			</tr>
			<tr>
				
				<td>
				<input type="text" id="startDate" name="startDate" onclick="laydate()" onfocus="clearError('startDate')"/>
				</td>
				<td>
				<input type="text" id="endDate" name="endDate" onclick="laydate()" onfocus="clearError('endDate')"/>
				</td>
				<td>
				<input type="text" id="processDate" name="processDate" onfocus="clearError('processDate')"/>
				</td>
				<td>
				<input type="text" id="batchNumber" name="batchNumber"  onfocus="clearError('batchNumber')"/>
				</td>
				<td>
				<input type="text" id="productName" name="productName" onfocus="clearError('productName')"/>
				</td>
				<td>
				<input type="text" id="producer" name="producer" onfocus="clearError('producer')"/>
				</td>
				<td>
				<input type="text" id="useLevel" name="useLevel" onfocus="clearError('useLevel')"/>
				</td>
				<td>
				<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
				</td>
				<td style="background-color:#F8FFF7">
					<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_medicineRecord',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
				</td>
			</tr>                     
    </table>
    	<input type = "hidden" id="action" name = "action" value="insert"/>
    	<input type = "hidden" id="farmId" name = "farmId" value="${user.farm.farmId }"/>
    </form>
	</div>
		
</div>
</body>
</html>