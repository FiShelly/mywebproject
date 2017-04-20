<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model2_meterialInsert_checkContent.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_model2_updateDetail.css" type="text/css"/>
</head>
<body>
	<div style="text-align: center; cursor: default;margin-top: 10px">
		<div style="background: url('${pageContext.request.contextPath}/image/ui-bg_gloss-wave_35_f6a828_500x100.png') no-repeat;width:100%;height:40px;border-radius: 4px;vertical-align: middle;line-height: 40px;"> 
			<span style="font-family: 黑体; font-size: 18px; white-space: nowrap;color:white;">添加物资详细信息</span>
		</div>

		<form id="insert_meterial" name="insert_meterial" action="${pageContext.request.contextPath}/SuppliesServlet" method="post" onsubmit="return checkContent();">
			<table align="center" id="table">

				<tr>
					<td colspan="2">
						物资编号
					</td>
					<td colspan="2">
						<input type="text" id="meterial_number" name="meterial_number"
						onfocus="clearError('meterial_number')"/>
					</td>
				</tr>
				<tr>
					<td>
						物资生产商
					</td>
					<td colspan="3">
						<input type="text" id="producer" name="producer" onfocus="clearError('producer')"/>
					</td>
				</tr>
				<tr>
					<td>
						物资名称
					</td>
					<td>
						<input type="text" id="meterial_name" name="meterial_name" onfocus="clearError('meterial_name')"/>
					</td>
					
					<td>
						价格
					</td>
					<td>
						<input type="text" id="price" name="price" onfocus="clearError('price')"/>
					</td>
					
				</tr>

				<tr>
					<td>
						生产日期
					</td>
					<td>
						<input type="text" id="date" name="date" onclick="laydate()" readonly="readonly"
						onfocus="clearError('date')"/>
					</td>
					<td>
						保质期
					</td>
					<td style="text-align: left">
						<input type="text" id="quality" name="quality" onfocus="clearError('quality')" style="width: 70%"/>个月
					</td>
				</tr>

				<tr>
					<td>
						数量
					</td>
					<td>
						<input type="text" id="amount" name="amount" onfocus="clearError('amount')"/>
					</td>
					<td>
						是否失效
					</td>
					<td>
						<input type="radio" id="choice1" name="valid" value="是" style="width: 13px;height:15px;vertical-align: middle"/>是
						<input type="radio" id="choice2" name="valid" value="否" style="width: 13px;height:15px;vertical-align: middle" checked="checked"/>否
					</td>
				</tr>

			</table>

			<div id="btn_div" style="float:right;margin-top:50px;margin-right: 20px">
				<input name="btn_ok" id="btn_ok" style="border-radius: 2px; border: 0px currentColor; border-image: none; width: 70px; height: 40px;
				color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="submit" value="确定"/>
				<input name="btn_cancel" id="btn_cancel" style="border-radius: 2px; border: 0px currentColor; border-image: none; width: 70px; height: 40px;
				color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="button" value="取消"
				onclick="CloseDiv('MyDiv','fade')"/>
			</div>
			<input type="hidden" name = "action" id = "action" value="insert_supItem"/>	
			<input type="hidden" name="supAddress" id="supAddress" value="${supAddress }"/>
			<input type="hidden" name="reserveId" id="reserveId" value="${reserveId }"/>
		</form>
	</div>
</body>
</html>