<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_insertData_content.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_changeLiBgColor.js"></script>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_submit.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_addDelRows.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_addDelTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mouseOver.js"></script>
</head>
<body onload="addressInit('s_province', 's_city', 's_county');addressInit('cmbProvince', 'cmbCity', 'cmbArea');">
	<div class="content">
		
		<div style="overflow-y:auto;height:100%;">
		
			<form id="form_animalA" name="form_animalA" method="post" action="${pageContext.request.contextPath }/AnimalAServlet?action=insert" onsubmit="checkContent('animalA')">
			<div class="tables">
				<table id="animalA">
				<tr>
					<td style="background-color:#f0f0f0;">
						编号
					</td>
					<td colspan="3" style="width:300px;">
						<input type="text" id="animalA_id" name="animalA_id" onfocus="clearError('animalA_id')" onmouseover="over('animalA_id');" onmouseout="out('animalA_id');"/>
					</td>
					
				</tr>
				
				<tr>
					<td style="background-color:#f0f0f0;">
						货主
					</td>
					<td>
						<input type="text" id="owner" name="owner" onfocus="clearError('owner')" onmouseover="over('owner');" onmouseout="out('owner');"/>
					</td>
					<td style="background-color:#f0f0f0">
						联系电话
					</td>
					<td>
						<input type="text" id="phone_number" name="phone_number" onfocus="clearError('phone_number')" onmouseover="over('phone_number');" onmouseout="out('phone_number');" />
					</td>
					
					
				</tr>
				<tr>
					<td style="background-color:#f0f0f0">
						动物种类
					</td>
					<td>
						<input type="text" id="animal_type" name="animal_type" onfocus="clearError('animal_type')" onmouseover="over('animal_type');" onmouseout="out('animal_type');"/>
					</td>
					<td style="background-color:#f0f0f0">
						数量及单位
					</td>
					<td>
						<input type="text" id="animal_amount" name="animal_amount" onfocus="clearError('animal_amount')" onmouseover="over('animal_amount');" onmouseout="out('animal_amount');"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2" style="background-color:#f0f0f0">
						启运地点
					</td>
					<td colspan="3" style="text-align:left;">
						<select id="s_province" name="s_province"></select>
						<select id="s_city" name="s_city" ></select>
						<select id="s_county" name="s_county"></select>
						
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align:left;">
						<input type="text" id="animalA_start_place" name="animalA_start_place" onfocus="clearError('animalA_start_place')" placeholder="(镇/街道/详细地址)"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2" style="background-color:#f0f0f0">
						到达地点
					</td>
					<td colspan="3" style="text-align:left;">
						<select id="cmbProvince" name="cmbProvince"></select>  
						<select id="cmbCity" name="cmbCity"></select>  
						<select id="cmbArea" name="cmbArea"></select>  

					</td>
					
				</tr>
				<tr>
					<td colspan="3" style="text-align:left;">
						<input type="text" id="animalA_target_place" name="animalA_target_place"  onfocus="clearError('animalA_target_place')" placeholder="(镇/街道/详细地址)"/>
					</td>
				</tr>
				<tr>
					<td style="background-color:#f0f0f0">
						签发日期
					</td>
					<td>
						<input type="text" id="issue_date" name="issue_date" onfocus="clearError('issue_date')" readonly="readonly" onclick="laydate()"/>
					</td>
					<td style="background-color:#f0f0f0">
						用途
					</td>
					<td>
						<input type="text" id="purpose" name="purpose" onfocus="clearError('purpose')" onmouseover="over('purpose');" onmouseout="out('purpose');"/>
					</td>
				</tr>
				
				<tr>
					<td style="background-color:#f0f0f0">
						承运人
					</td>
					<td>
						<input type="text" id="carrier" name="carrier" onfocus="clearError('carrier')" onmouseover="over('carrier');" onmouseout="out('carrier');"/>
					</td>
					
					<td style="background-color:#f0f0f0">
						联系电话
					</td>
					<td>
						<input type="text" id="carrier_phone" name="carrier_phone" onfocus="clearError('carrier_phone')" onmouseover="over('carrier_phone');" onmouseout="out('carrier_phone');"/>
					</td>
					
				</tr>

				<tr>
					<td style="background-color:#f0f0f0">
						运载方式
					</td>
					<td colspan="3" style="width:230px;">
						<input type="radio" name="radiobutton" value="公路" checked="checked" style="width:10px;height:10px;"/>&nbsp;公路&nbsp;
						<input type="radio" name="radiobutton" value="铁路"  style="width:10px;height:10px;"/>&nbsp;铁路&nbsp;
						<input type="radio" name="radiobutton" value="水路"  style="width:10px;height:10px;"/>&nbsp;水路&nbsp;
						<input type="radio" name="radiobutton" value="航空"  style="width:10px;height:10px;"/>&nbsp;航空
					</td>
					
				</tr>
				<tr>
					<td style="background-color:#f0f0f0">
						运输工具牌号
					</td>
					<td colspan="3">
						<input type="text" id="sign" name="sign" onfocus="clearError('sign')" onmouseover="over('sign');" onmouseout="out('sign');"/>
					</td>
				</tr>
				<tr>
					<td style="background-color:#f0f0f0;width=120px;">
						运载工具消毒情况
					</td>
					<td colspan="3">
						装运前经<input type="text" id="animalA_degass" name="animalA_degass" onfocus="clearError('animalA_degass')" style="width:50%;height:98%;border-bottom:1px solid black;" onmouseover="over('animalA_degass');" onmouseout="out('animalA_degass');"/>消毒
					</td>
				</tr>
				<tr>
					<td style="background-color:#f0f0f0">
						合格证有效天数
					</td>
					<td>
						<input type="text" id="valid_date" name="valid_date" onfocus="clearError('valid_date')" onmouseover="over('valid_date');" onmouseout="out('valid_date');"/>
					</td>
					<td style="background-color:#f0f0f0">
						经手兽医
					</td>
					<td>
						<input type="text" id="doctor" name="doctor" onfocus="clearError('doctor')" onmouseover="over('doctor');" onmouseout="out('doctor');"/>
					</td>
					
				</tr>
				<tr>
					<td rowspan="2" style="background-color:#f0f0f0">
						备注
					</td>
					<td rowspan="2" colspan="3">
						<textarea cols="38" rows="2" id="remark" name="remark" onfocus="clearError('remark')"></textarea>
						
					</td>
				</tr>
			</table>
		</div>
		<div class="animalID">
			
			
			<table id="inner_table">
					<tr rowspan="2">
						<td colspan="6" style="width:58px;height:30px;text-align: left;border:0;">
							<font style="color:white;font-family: 宋体;font-size: 12px;margin: 0 10px;">耳标号</font>
						</td>
					</tr>
					<tr><td style="border:0;height:10px;"></td></tr>
					<tr>
						<td>
							<input type="text" id="ID001" name="animalID" onmouseover="over('ID001');" onmouseout="out('ID001');" onfocus="clearError('ID001')"/>
						</td>
						<td>
							<input type="text" id="ID002" name="animalID" onmouseover="over('ID002');" onmouseout="out('ID002');" onfocus="clearError('ID002')"/>
						</td>
						<td>
							<input type="text" id="ID003" name="animalID" onmouseover="over('ID003');" onmouseout="out('ID003');" onfocus="clearError('ID003')"/>
						</td>
						<td>
							<input type="text" id="ID004" name="animalID" onmouseover="over('ID004');" onmouseout="out('ID004');" onfocus="clearError('ID004')"/>
						</td>
						<td>
							<input type="text" id="ID005" name="animalID" onmouseover="over('ID005');" onmouseout="out('ID005');" onfocus="clearError('ID005')"/>
						</td>
						
						<td style="text-align:left;border:0;">
							<input type="button" id="btn_addtr" name="btn_addtr" onclick="addRow('inner_table')" style="width:23px;height:17px;background:url(${pageContext.request.contextPath}/image/add.png) no-repeat;border:0;margin-left:10px;"/>
							<input type="hidden" id = "flag" name = "flag" value=""/>
						</td>
					</tr>
				</table>
				</div>
				</form>
		
		</div>
	</div>

</body>
</html>
