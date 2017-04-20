<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_insertData_content.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_changeLiBgColor.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_addDelTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mouseOver.js"></script>
</head>
<body onload="addressInit('s_province', 's_city', 's_county');addressInit('cmbProvince', 'cmbCity', 'cmbArea');">
<div class="content">
	<div style="overflow-y:auto;height:100%;">
		<div class="tables">
			<form id="form_productA" name="form_productA" method="post" action="${pageContext.request.contextPath }/ProductAServlet?action=insert" onsubmit="return checkContent('productA');">
				<table id="productA">
					<tr>
						<td style="background-color:#f0f0f0;">
							编号
						</td>
						<td colspan="3" style="width:360px;">
							<input type="text" id="productA_id" name="productA_id" onfocus="clearError('productA_id')" onmouseover="over('productA_id');" onmouseout="out('productA_id');"/>
						</td>
					</tr>

					<tr>
						<td style="background-color:#f0f0f0;">
							货主
						</td>
						<td style="width:120px;">
							<input type="text" id="owner" name="owner" onfocus="clearError('owner')" onmouseover="over('owner');" onmouseout="out('owner');"/>
						</td>
						<td style="background-color:#f0f0f0">
							联系电话
						</td>
						<td style="width:120px;">
							<input type="text" id="phone_number" name="phone_number" onfocus="clearError('phone_number')" onmouseover="over('phone_number');" onmouseout="out('phone_number');"/>
						</td>
					</tr>

					<tr>
						<td style="background-color:#f0f0f0;">
							产品名称
						</td>
						<td style="width:120px;">
							<input type="text" id="product_name" name="product_name" onfocus="clearError('product_name')" onmouseover="over('product_name');" onmouseout="out('product_name');"/>
						</td>
						<td style="background-color:#f0f0f0">
							数量及单位
						</td>
						<td style="width:120px;">
							<input type="text" id="amount" name="amount" onfocus="clearError('amount')" onmouseover="over('amount');" onmouseout="out('amount');"/>
						</td>
					</tr>

					<tr>
						<td rowspan="2" style="background-color:#f0f0f0">
							生产单位地址
						</td>
						<td colspan="3" style="text-align:left;">
							<select id="s_province" name="s_province"></select>
							<select id="s_city" name="s_city" ></select>
							<select id="s_county" name="s_county"></select>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="text" id="produce_place" name="produce_place" onfocus="clearError('produce_place')"  placeholder="(镇/街道/详细地址)"/>
						</td>
					</tr>
					
					<tr>
						<td rowspan="2" style="background-color:#f0f0f0">
							目的地
						</td>
						<td colspan=3 style="text-align:left;">
							<select id="cmbProvince" name="cmbProvince"></select>  
							<select id="cmbCity" name="cmbCity"></select>  
							<select id="cmbArea" name="cmbArea"></select> 
						</td>
					</tr>
					<tr>
						<td colspan="3">
							 <input type="text" id="productA_target_place" name="productA_target_place"  onfocus="clearError('productA_target_place')" placeholder="(镇/街道/详细地址)"/>
						</td>
					</tr>
					

					<tr>
						
						<td style="background-color:#f0f0f0">
							承运人
						</td>
						<td style="width:120px;">
							<input type="text" id="transport_man" name="transport_man" onfocus="clearError('transport_man')" onmouseover="over('transport_man');" onmouseout="out('transport_man');"/>
						</td>
						<td style="background-color:#f0f0f0">
							联系电话
						</td>
						<td style="width:120px;">
							<input type="text" id="transport_phone" name="transport_phone" onfocus="clearError('transport_phone')" onmouseover="over('transport_phone');" onmouseout="out('transport_phone');"/>
						</td>
						
					</tr>
					
					<tr>
						<td style="background-color:#f0f0f0">
							签发日期
						</td>
						<td style="width:120px;">
							<input type="text" id="sign_date" name="sign_date" onfocus="clearError('sign_date')" readonly="readonly" onclick="laydate()"/>
						</td>
						<td style="background-color:#f0f0f0">
							合格证有效天数
						</td>
						<td>
							<input type="text" id="valid_date" name="valid_date" onfocus="clearError('valid_date')" onmouseover="over('valid_date');" onmouseout="out('valid_date');"/>
						</td>
						
					</tr>

					<tr>
						<td style="background-color:#f0f0f0">
							运载方式
						</td>
						<td style="width:120px;">
							<select id="transport" name="transport">
							<option value="铁路" id="railway" name="railway" selected="selected">铁路</option>
							<option value="水路" id="waterway" name="waterway">水路</option>
							<option value="航空" id="air" name="air">航空</option>
							<option value="公路" id="highway" name="highway">公路</option>
							</select>
						</td>
						<td style="background-color:#f0f0f0">
							经手兽医
						</td>
						<td style="width:120px;">
							<input type="text" id="doctor" name="doctor" onfocus="clearError('doctor')" onmouseover="over('doctor');" onmouseout="out('doctor');"/>
						</td>
					</tr>
					
					<tr>
						<td style="background-color:#f0f0f0;">
							运载工具牌号
						</td>
						<td style="width:120px;">
							<input type="text" id="mark" name="mark" onfocus="clearError('mark')" onmouseover="over('mark');" onmouseout="out('mark');"/>
						</td>
						<td style="background-color:#f0f0f0;">
							运载工具消毒处理方式
						</td>
						<td style="width:120px;">
							<input type="text" id="degass" name="degass" onfocus="clearError('degass')" onmouseover="over('degass');" onmouseout="out('degass');"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2" style="background-color:#f0f0f0">
							备注
						</td>
						<td rowspan="2" colspan="3" style="border-top-style: none;">
							<textarea cols="40" rows="2" id="remark" name="remark" onfocus="clearError('remark')" style="border: 0;" onmouseover="over('remark');" onmouseout="out('remark');"></textarea>

						</td>
					</tr>
					
				</table>
				</form>
			</div>

		</div>
	</div>
</body>
</html>