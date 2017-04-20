<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model4_dispatchContent.js"></script>
	<style type="text/css">
		.content{
			width: 100%;
			/*height: 100px;*/
			border:1px solid #A9D7CD;
			margin-top: 10px;
		}
		table{
			width: 100%;
			border-collapse: collapse;
			table-layout: fixed;
		}
		td{
			border:1px solid #AAAAAA;
			/*width: 100px;*/
			height:20px;
			font-family: "宋体";

			font-size: 14px;
		}
		input{
			width: 99%;
			height: 98%;
			border:0;
		}
		span{
			font-family: "黑体"; 
			font-size: 15px; 
			white-space: nowrap;
			color:white;
			margin-left:200px;
		}
		
	</style>
</head>
<body onload="addressInit('cmbProvince2', 'cmbCity2', 'cmbArea2');">
	<div style="background: url('${pageContext.request.contextPath}/image/ui-bg_gloss-wave_35_f6a828_500x100.png') no-repeat;width:100%;height:40px;border-radius: 4px;vertical-align: middle;line-height: 40px;"> 
		<span>物资调度信息</span>
	</div>
	<div class="content">
		<span style="color:black;font-size: 14px;">物资初始位置信息</span>
		<table>
			<tr>
				<td>物资编号</td>
				<td>${item.suppliesId }</td>
				<td>物资名称</td>
				<td>${item.suppliesName }</td>
			</tr>
			<tr>
				<td>是否失效</td>
				<td id="valid">${item.failSitution }</td>
				<td>物资数量</td>
				<td id="meterial_number">${item.number }</td>
			</tr>
			<tr>
				<td>所在位置</td>
				<td colspan="3">
					${item.supplies.address }
				</td>
			</tr>
		</table>
	</div>
	<form action="${pageContext.request.contextPath}/SuppliesServlet" method="post" onsubmit="return checkContent();">
		<div class="content">
			<span style="color:black;font-size: 14px;">物资目标位置信息</span>

			<table>
				<tr>
					<td>调度日期</td>
					<td><input type="text" onclick="laydate()" readonly="readonly" name="date" id="date_end" onfocus="clearError('date_end')"/></td>
					<td>调度数量</td>
					<td><input type="text" id="amount" name="amount" onfocus="clearError('amount')"/></td>
				</tr>
				<tr>
					<td rowspan="2">目标位置</td>

					<td colspan="3">
						<select id="cmbProvince2" name="cmbProvince2"></select>  
						<select id="cmbCity2" name="cmbCity2"></select>  
						<select id="cmbArea2" name="cmbArea2"></select></td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="text" id="address" name="address" onfocus="clearError('address')" placeholder="(镇/街道/详细地址)"/>
						</td>
					</tr>
				</table>
			</div>
			<div style="height:25px;">
				<span id="warning_message" style="color: red;margin-left: 70px;display:none;">请选择详细的地址!!</span>
				<span id="warning_message2" style="color: red;margin:0 0 0 70px;display:none;">物资已失效或者调度数量超过存储数量!!</span>
				<span id="warning_message3" style="color: red;margin:0 0 0 70px;display:none;">调度时间必须晚于今日!!</span>
			</div>
			<div id="btn_div" style="float: right;margin-top: 50px;margin-right: 20px">
				<input name="btn_ok" id="btn_ok" style="border-radius: 2px; border: 0px currentColor; border-image: none; width: 70px; height: 40px;color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="submit" value="确定"/>
				<input name="btn_cancel" id="btn_cancel" style="border-radius: 2px; border: 0px currentColor; border-image: none; width: 70px; height: 40px;color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="button" value="取消"onclick="CloseDiv('MyDiv2','fade')"/>
			</div>
			<input type="hidden" name="id" value="${item.supplies.reserveId }"/>
			<input type="hidden" name="supId" value="${item.suppliesId }"/>
			<input type="hidden" name="count" value="${item.number }"/>
			<input type="hidden" name="action" value="insert_disSup"/>
		</form>
	</body>
	</html>
