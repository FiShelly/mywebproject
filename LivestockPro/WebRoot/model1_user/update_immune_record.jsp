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
		height: 30px;
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
	<form id="form1" name="form1" action="ImmuneRecordServlet" method="post" onsubmit="return isSubmit('immune_record');">
	<table align=center>

		<tr>
			<td colspan=4 style="text-align:left">
			免疫记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				时间:
			</td>
			<td colspan=3 style="text-align:left;">
				<input type="text" id="immuneTime" name="immuneTime" value="${immune.immuneTime }" readonly="readonly" 
							onclick="laydate()" style="width:90px;height:20px;" onfocus="clearError('immuneTime')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;" >
				圈舍号:
			</td>
			<td>
				<input type="text" id="roomNum" name="roomNum" value="${immune.roomNum }" onfocus="clearError('roomNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				存栏数量:
			</td>
			<td>
				<input type="text" id="remainNum" name="remainNum" value="${immune.remainNum }" onfocus="clearError('remainNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				免疫数量:
			</td>
			<td>
				<input type="text" id="immuneNum" name="immuneNum"  value="${immune.immuneNum }" onfocus="clearError('immuneNum')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				疫苗名称:
			</td>
			<td colspan=3>
				<input type="text" id="vaccineName" name="vaccineName" style="width:150px;" value="${immune.vaccineName }" onfocus="clearError('vaccineName')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				疫苗生产商:
			</td>
			<td colspan=3>
				<input type="text" id="vaccineProducers" name="vaccineProducers" style="width:150px;" value="${immune.vaccineProducers }" onfocus="clearError('vaccineProducers')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				批号:
			</td>
			<td>
				<input type="text" id="batchNum" name="batchNum"  value="${immune.batchNum }" onfocus="clearError('batchNum')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				有效期:
			</td>
			<td  style="text-align:left;">
				<input type="text" id="vaccineValidTime" name="vaccineValidTime" style="width:150px;" value="${immune.vaccineValidTime }" onfocus="clearError('vaccineValidTime')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				免疫方法:
			</td>
			<td>
				<input type="text" id="immuneMethod" name="immuneMethod" style="width:150px;" value="${immune.immuneMethod }" onfocus="clearError('immuneMethod')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				免疫剂量:
			</td>
			<td>
				<input type="text"  id="immuneDosage" name="immuneDosage" style="width:150px;" value="${immune.immuneDosage }" onfocus="clearError('immuneDosage')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				免疫人员:
			</td>
			<td>
				<input type="text" id="immunePeople" name="immunePeople" style="width:150px;" value="${immune.immunePeople }" onfocus="clearError('immunePeople')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;vertical-align:top;margin-right:10px;">
				备注:
			</td>
			<td>
				<textarea rows="12" cols="25" name="note" id="note">${immune.note }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan=4 style="text-align:right;">
				<input type="submit"  value="保存" id="save" name="save" style="margin-right:35px;width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
	</table>
	<input type="hidden" id = "id" name= "id" value="${immune.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
			</form>
</div>
</body>
</html>
