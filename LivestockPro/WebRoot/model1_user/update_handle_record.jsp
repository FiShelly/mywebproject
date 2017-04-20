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
		width: 330px;
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
<body>
<div style="text-align:left;width:100%;height:100%;">
	<form id="form1" name="form1" action="DisposalHarmlessServlet" method="post" onsubmit="return isSubmit('handle_record');">
	<table align=center>
		<tr>
			<td colspan=3 style="text-align:left">
			病死畜禽无害化处理记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				日期:
			</td>
			<td>
			<div style="float:left;padding :0;auto">
				<input type="text" id="disposalTime" name="disposalTime" value="${disp.disposalTime }" readonly="readonly" 
					onclick="laydate()" style="width:90px;height:20px;" onfocus="clearError('')"/>
			</div>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;" >
				数量:
			</td>
			<td>
				<input type="text" id="number" name="number" value="${disp.number }" style="width:150px;" onfocus="clearError('number')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				处理或死亡原因:
			</td>
			<td colspan=2>
				<input type="text" id="disposalOrResult" name="disposalOrResult" value="${disp.disposalOrResult }" style="width:150px;" onfocus="clearError('disposalOrResult')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				畜禽标识编码:
			</td>
			<td>
				<input type="text" id="livestockId" name="livestockId" value="${disp.livestockId }" style="width:150px;" onfocus="clearError('livestockId')"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				处理方法:
			</td>
			<td colspan=2>
				<input type="text" id="disposalMethod" name="disposalMethod" value="${disp.disposalMethod }" onfocus="clearError('disposalMethod')" style="width:150px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;text-align:right;margin-right:10px;">
				处理单位(负责人):
			</td>
			<td colspan=3>
				<input type="text" id="disposalStation" name="disposalStation" value="${disp.disposalStation }" onfocus="clearError('disposalStation')" style="width:150px;"/>
			</td>
		</tr>
		
		<tr>
			<td style="font-size:12px;text-align:right;vertical-align:top;margin-right:10px;">
				备注:
			</td>
			<td colspan=3 style="vertical-align:top;">
				<textarea rows="12" cols="23" id= "note" name = "note">${disp.note }</textarea>
				<input type="submit" value="保存" id="save" name="save" style="float:right;margin-right:6px;margin-top:10px;width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
			</table>
			<input type="hidden" id = "id" name= "id" value="${disp.id }"/>
			<input type="hidden" id = "action" name= "action" value="update"/>
			<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
		</form>

</div>
</body>
</html>