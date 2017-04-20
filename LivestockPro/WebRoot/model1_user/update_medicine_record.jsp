<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_calendar_select.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_update_submit.js"></script>
<style>
	table{
		border-collapse: collapse;
		width:100%;
		height:100%;
		text-align:right;
		/*margin-top:10px;*/
		table-layout:fixed;
		font-size: 14px;
		font-family: 宋体;
	}
	table tr td{
		/*border:1px solid black;*/
		width: 100px;
		height: 20px;
		padding-right: 10px;
		padding-bottom: 8px;
	}
	table tr td input{
		width: 100px;
		height: 20px;
		border-radius: 2px;
		border:1px solid #12A686;
	}
	textarea{
		border-radius: 2px;
		border:1px solid #12A686;
	}
</style>
</head>
<body onload="YYYYMMDDstart()">
<div style="text-align:center;width:100%;height:100%;">

<form id="form1" name="form1" action="FoodDrugUseRecordServlet" method="post" onsubmit="return isSubmit('medicine_record');">
	<table align=center>
	
		<tr>
			<td colspan=3 style="text-align:left">
			饲料、饲料添加剂和兽药使用记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				开始使用时间:
			</td>
			<td colspan=2 style="text-align:left;">
				<input type="text" id="startTime" name="startTime" value="${fdur.startTime }" onfocus="clearError('startTime')" readonly="readonly" 
					onclick="laydate()" style="width:90px;height:20px;"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				停止时间:
			</td>
			<td   style="text-align:left;">
				<input type="text" id="stopTime" name="stopTime" value="${fdur.stopTime }" readonly="readonly"  onfocus="clearError('stopTime')"
					onclick="laydate()" style="width:90px;height:20px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				产品名称:
			</td>
			<td >
				<input type="text" id="productName" name="productName" style="width:200px;" value="${fdur.productName }" onfocus="clearError('productName')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				生产产家:
			</td>
			<td colspan=1>
				<input type="text" id="manufacturer" name="manufacturer" style="width:150px;" value="${fdur.manufacturer }" onfocus="clearError('manufacturer')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				批号:
			</td>
			<td colspan=1>
				<input type="text" id="batchNum" name="batchNum" style="width:150px;" value="${fdur.batchNum }" onfocus="clearError('batchNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				加工日期:
			</td>
			<td colspan=1>
				<input type="text" id="processDate" name="processDate" style="width:150px;" value="${fdur.processDate }" onfocus="clearError('processDate')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				用量:
			</td>
			<td colspan=1>
				<input type="text" id="dosage" name="dosage" style="width:150px;" value="${fdur.dosage }" onfocus="clearError('dosage')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				备注:
			</td>
			<td>
				<textarea rows="3" id="note" name="note">${fdur.note }</textarea>
			</td>
			
		</tr>
		<tr>
			<td colspan=3>
				<input type="submit"  value="保存" id="save" name="save" style="width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>

	</table>
		<input type="hidden" id = "id" name= "id" value="${fdur.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
	</form>
</div>
</body>
</html>
