<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_update_submit.js"></script>

<script type="text/javascript">
function openNewWin(id,theWidth,theHeight){
	document.form1.submit();
	 
}
</script>
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
<body onload="YYYYMMDDstart()">
<div style="text-align:center;width:100%;height:100%;margin-top:10px;">

	<form id="form1" name="form1" action="ProductRecordServlet" method="post" onsubmit="return isSubmit('product_record');">
		<table align=center>
		<tr>
			<td colspan=4 style="text-align:left">
			生产记录
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				圈舍号:
			</td>
			<td>
				<input type="text" id="roomNum" name="roomNum" value="${pro.roomNum }" onfocus="clearError('roomNum')"/>
			</td>
			
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				时间:
			</td>
			<td>
				<input type="text" id="recordDate" name="recordDate" value="${pro.recordDate }" onfocus="clearError('recordDate')" readonly="readonly" 
					onclick="laydate()" style="width:90px;height:20px;"/>
			</td>
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				生产数量:
			</td>
			<td>
				<input type="text" id="birthNum" name="birthNum" value="${pro.birthNum }" onfocus="clearError('birthNum')"/>
			</td>
			
			
		</tr>
		<tr>
			<td style="font-size:12px;">
				调入数量:
			</td>
			<td>
				<input type="text" id="putNum" name="putNum" value="${pro.putNum }" onfocus="clearError('putNum')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				调出数量:
			</td>
			<td>
				<input type="text" id="inNum" name="inNum" value="${pro.inNum }" onfocus="clearError('inNum')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				死淘数量:
			</td>
			<td>
				<input type="text" id="deadNum" name="deadNum" value="${pro.deadNum }" onfocus="clearError('deadNum')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				存栏数:
			</td>
			<td>
				<input type="text" id="remainNum" name="remainNum" value="${pro.remainNum }" onfocus="clearError('remainNum')"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:12px;">
				备注:
			</td>
			<td>
				<textarea rows="3" id="note" name="note">${pro.note }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan=4>
				<input type="submit"  value="保存" id="save" name="save" style="width:48px;font-size: 12px;font-family:宋体;color:white;background-color:#12A686;"/>
			</td>
		</tr>
		
	</table>
	<input type="hidden" id = "id" name= "id" value="${pro.id }"/>
		<input type="hidden" id = "action" name= "action" value="update"/>
		<input type="hidden" id = "farmId" name= "farmId" value="${user.farm.farmId }"/>
			</form>
</div>
</body>
</html>