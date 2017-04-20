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
		text-align:left;
		/*margin-top:10px;*/
		table-layout:fixed;
		font-size: 14px;
		font-family: 宋体;
	}
	table tr td{
		/*border:1px solid black;*/
		width: 300px;
		height: 20px;
		padding-right: 10px;
		padding-bottom: 8px;
	}
	table tr td input{
		width: 80px;
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
<body>
<div style="text-align:center;width:100%;height:100%;">
<form id="form1" name="form1" action="MedicalRecordServlet" method="post" onsubmit="return isSubmit('treat_record');">
	<table align=center>
		<tr>
			<td colspan=3 style="text-align:left">
			诊疗记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				时间:
			</td>
			<td>
				<input type="text" id="medicalTime" name="medicalTime" value="${med.medicalTime }" onfocus="clearError('medicalTime')" readonly="readonly" 
					onclick="laydate()" />
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				存栏数
			</td>
			<td>
				<input type="text" id="remain" name="remain" value="${med.remain }" onfocus="clearError('livestockId')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				圈舍号:
			</td>
			<td>
				<input type="text" id="roomNum" name="roomNum" value="${med.roomNum }" onfocus="clearError('roomNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				日龄:
			</td>
			<td>
				<input type="text" id="dateAge" name="dateAge" value="${med.dateAge }" onfocus="clearError('dateAge')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				发病数:
			</td>
			<td>
				<input type="text" id="sickNum" name="sickNum" value="${med.sickNum }"  onfocus="clearError('sickNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				病因:
			</td>
			<td colspan=3>
				<input type="text" id="sickReason" name="sickReason" value="${med.sickReason }" onfocus="clearError('sickReason')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				诊疗人员:
			</td>
			<td>
				<input type="text" id="medicalPeo" name="medicalPeo" value="${med.medicalPeo }" onfocus="clearError('medicalPeo')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				用药名称:
			</td>
			<td>
				<input type="text" id="drugName" name="drugName" value="${med.drugName }" onfocus="clearError('drugName')" style="width:99px;"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				用药方法:
			</td>
			<td>
				<input type="text" id="method" name="method" value="${med.method }" onfocus="clearError('method')" />
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				诊疗结果:
			</td>
			<td>
				<input type="text" id="medicalResult" name="medicalResult" value="${med.medicalResult }" onfocus="clearError('medicalResult')"/>
			</td>
		</tr>
		
		<tr>
			<td colspan=4 style="text-align:right;">
				<input type="submit"  value="保存" id="save" name="save" style="margin-right:35px;width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
		

	</table>
		<input type="hidden" id = "id" name= "id" value="${med.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
</form>
</div>
</body>
</html>
