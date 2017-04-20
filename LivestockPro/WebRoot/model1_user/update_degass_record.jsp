<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		width: 40px;
		height: 20px;
		padding-bottom: 8px;
		padding-right: 10px;
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
<body onload="YYYYMMDDstart()">
<div style="text-align:center;width:100%;height:100%;">
	<form id="form1" name="form1" action="DisinfectionRecordServlet" method="post" onsubmit="return isSubmit('degass_record');">
	<table align=center id="degass_record">
		<tr>
			<td colspan=4 style="text-align:left">
			消毒记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				时间:
			</td>
			<td>
			<input type="text" id="disinfectionTime" name="disinfectionTime" value="${dis.disinfectionTime }" readonly="readonly" 
							onclick="laydate()" style="width:90px;height:20px;" onfocus="clearError('')"/>
			
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				消毒场所:
			</td>
			<td >
					<input type="text" id="place" name="place" value="${dis.place }" onfocus="clearError('place')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				消毒药名称:
			</td>
			<td >
				<input type="text" id="disinfectionName" name="disinfectionName"  style="width:150px;" value="${dis.disinfectionName }" onfocus="clearError('disinfectionName')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				用药剂量:
			</td>
			<td >
				<input type="text" id="disinfectionDose" name="disinfectionDose"  style="width:150px;" value="${dis.disinfectionDose }" onfocus="clearError('disinfectionDose')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				消毒方法:
			</td>
			<td >
				<input type="text" id="method" name="method" value="${dis.method }" onfocus="clearError('method')"  style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				操作人:
			</td>
			<td style="text-align:left;">
				<input type="text" id="sign" name="sign" value="${dis.sign }" onfocus="clearError('sign')"/>
			</td>
			<td></td><td></td>
		</tr>
		<tr>
			<td colspan=4>
				<input type="submit" onclick="openNewWin();" value="保存" id="save" name="save" style="margin-right:25px;width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
		</table>
		<input type="hidden" id = "id" name= "id" value="${dis.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
		</form>

</div>
</body>
</html>