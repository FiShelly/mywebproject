<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_update_submit.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>

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
		width: 50px;
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

	<form id="form1" name="form1" action="EpidemicMonitoringServlet" method="post" onsubmit="return isSubmit('prevent_record')">
		<table align=center>
		<tr>
			<td colspan=4 style="text-align:left">
			防疫监测记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				采样时间:
			</td>
			<td>
				<input type="text" id="samplingTime" name="samplingTime" value="${epi.samplingTime }" onfocus="clearError('samplingTime')" readonly="readonly" 
					onclick="laydate()" style="width:90px;height:20px;"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				圈舍号:
			</td>
			<td>
				<input type="text" id="roomNum" name="roomNum" value="${epi.roomNum }" onfocus="clearError('roomNum')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				采样数量:
			</td>
			<td>
				<input type="text" id="samplingNum" name="samplingNum" value="${epi.samplingNum }" onfocus="clearError('samplingNum')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				监测项目:
			</td>
			<td>
				<input type="text" id="monitoringName" name="monitoringName" value="${epi.monitoringName }" onfocus="clearError('monitoringName')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				监测单位:
			</td>
			<td colspan=3>
				<input type="text" id="monitoringStation" name="monitoringStation" value="${epi.monitoringStation }" onfocus="clearError('monitoringStation')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				监测结果:
			</td>
			<td colspan=3>
				<input type="text" id="monitoringResult" name="monitoringResult" value="${epi.monitoringResult }" onfocus="clearError('monitoringResult')" style="width:150px;"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				处理情况:
			</td>
			<td>
				<input type="text" id="disposalConditions" name="disposalConditions" value="${epi.disposalConditions }" onfocus="clearError('disposalConditions')" style="width:150px;"/>
			</td>
		</tr>
		
		
		<tr>
			<td style="font-size:12px;text-align:right;vertical-align:top;margin-right:10px;">
				备注:
			</td>
			<td colspan=3 style="vertical-align:top;">
				<textarea rows="12" cols="23" id="note" name="note">${epi.note }</textarea>
				<input type="submit"   value="保存" id="save" name="save" style="float:right;margin-right:6px;margin-top:10px;width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
			</table>
		<input type="hidden" id = "id" name= "id" value="${epi.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>

		</form>

</div>
</body>
</html>
