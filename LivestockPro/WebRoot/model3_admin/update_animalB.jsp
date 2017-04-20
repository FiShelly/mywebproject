<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_addDelRows.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_addDelTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/mouseOver.js"></script>
</head>
<body
	onload="addressInit('s_province', 's_city', 's_county','${ab.startProvince}','${ab.startCity}','${ab.startCountry }');
			  addressInit('cmbProvince', 'cmbCity', 'cmbArea','${ab.endProvince}','${ab.endCity}','${ab.endCountry }');">
	<div class="content">
		<div style="overflow-y:auto;height:100%;width: 100%;">
			
			<form id="form_animalB" name="form_animalB" method="post" action="${pageContext.request.contextPath }/AnimalBServlet?action=update" onsubmit="return checkContent('animalB');">
				<div class="tables">
					<table id="animalB">
						<tr>
							<td style="background-color:#f0f0f0;">
								编号
							</td>
							<td colspan="3" style="width:360px;">
								<input type="text" id="animalB_id" readonly="readonly" value="${ab.id}" name="animalB_id" onfocus="clearError('animalB_id')" onmouseover="over('animalB_id');" onmouseout="out('animalB_id');"/>
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0;">
								货主
							</td>
							<td>
								<input type="text" id="owner" value="${ab.shipperName}" name="owner" onfocus="clearError('owner')" onmouseover="over('owner');" onmouseout="out('owner');"/>
							</td>
							<td style="background-color:#f0f0f0;">
								联系电话
							</td>
							<td>
								<input type="text" id="phone_number" value="${ab.phoneNum}" name="phone_number" onfocus="clearError('phone_number')" onmouseover="over('phone_number');" onmouseout="out('phone_number');"/>
							</td>
						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								动物种类
							</td>
							<td>
								<input type="text" id="animal_type"  value="${ab.animalSpecies}" name="animal_type" onfocus="clearError('animal_type')" onmouseover="over('animal_type');" onmouseout="out('animal_type');"/>
							</td>
							<td style="background-color:#f0f0f0">
								数量及单位
							</td>
							<td>
								<input type="text" id="amount" name="amount" value="${ab.number}" onfocus="clearError('amount')" onmouseover="over('amount');" onmouseout="out('amount');"/>
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
								<input type="text" id="animalB_start_place" name="animalB_start_place" value="${ab.startDetail}" onfocus="clearError('animalB_start_place')" placeholder="(镇/街道/详细地址)"/>
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
							<input type="text" id="animalB_target_place" name="animalB_target_place" value="${ab.endDetail}"  onfocus="clearError('animalB_target_place')" placeholder="(镇/街道/详细地址)"/>
							</td>
						</tr>
						<tr>
							
							<td style="background-color:#f0f0f0">
								签发日期
							</td>
							<td>
								<input type="text" id="sign_date" name="sign_date" onfocus="clearError('sign_date')" value="${ab.date}" readonly="readonly" onclick="laydate()"/>
							</td>
							<td style="background-color:#f0f0f0">
								经手兽医
							</td>
							<td>
								<input type="text" id="doctor" name="doctor" onfocus="clearError('doctor')" value="${ab.vetName}" onmouseover="over('doctor');" onmouseout="out('doctor');"/>
							</td>

						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								用途
							</td>
							<td>
								<input type="text" id="purpose" name="purpose" value="${ab.use}" onfocus="clearError('purpose')" onmouseover="over('purpose');" onmouseout="out('purpose');"/>
							</td>
							<td style="background-color:#f0f0f0">
								合格证有效天数
							</td>
							<td>
								<input type="text" id="valid_date" name="valid_date" value="${ab.days}" onfocus="clearError('valid_date')" onmouseover="over('valid_date');" onmouseout="out('valid_date');"/>
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
					<c:forEach items="${ab.animalId }" var="abid" varStatus="status">
							<c:choose>
								<c:when test="${status.index%5 == 0 }">
									<tr>
										<td><input type="text" id="ID00${status.index}"
											value="${abid }" name="animalID"
											onmouseover="over('ID00${status.index}');"
											onmouseout="out('ID00${status.index}');"
											onfocus="clearError('ID00${status.index}')" /></td>
								</c:when>
								<c:when test="${status.index == 4 }">
									<td><input type="text" id="ID00${status.index}"
										name="animalID" onmouseover="over('ID00${status.index}');"
										value="${abid }" onmouseout="out('ID00${status.index}');"
										onfocus="clearError('ID00${status.index}')" /></td>
									<td style="text-align:left;border:0;"><input type="button"
										id="btn_addtr" name="btn_addtr"
										onclick="addRow('inner_table')"
										style="width:23px;height:17px;background:url(${pageContext.request.contextPath}/image/add.png) no-repeat;border:0;margin-left:10px;" />
									</td>
									</tr>
								</c:when>
								<c:when test="${status.index%5 != 0 }">
									<td><input type="text" id="ID00${status.index}"
										value="${abid }" name="animalID"
										onmouseover="over('ID00${status.index}');"
										onmouseout="out('ID00${status.index}');"
										onfocus="clearError('ID00${status.index}')" /></td>
									<c:if test="${(status.index+5)%5 == 4 }">
										<td style="text-align:left;border:0;"><input
											type="button" id="btn_addtr" name="btn_addtr"
											onclick="deleteRRow('inner_table',this)"
											style="width:23px;height:17px;background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;margin-left:10px;" />
										</td>
									</c:if>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${fn:length(ab.animalId) % 5 != 0 }">
							<c:forEach varStatus="status" begin="1"
								end="${5-fn:length(ab.animalId) % 5}">
								<td><input type="text"
									id="ID00${status.index+fn:length(ab.animalId)}" name="animalID"
									onmouseover="over('ID00${status.index+fn:length(ab.animalId)}');"
									onmouseout="out('ID00${status.index+fn:length(ab.animalId)}');"
									onfocus="clearError('ID001')" /></td>
							</c:forEach>
							<c:choose>
								<c:when test="${fn:length(ab.animalId)  < 5 }">
									<td style="text-align:left;border:0;"><input type="button"
										id="btn_addtr" name="btn_addtr"
										onclick="addRow('inner_table')"
										style="width:23px;height:17px;background:url(${pageContext.request.contextPath}/image/add.png) no-repeat;border:0;margin-left:10px;" />
									</td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left;border:0;"><input type="button"
										id="btn_addtr" name="btn_addtr"
										onclick="deleteRRow('inner_table',this)"
										style="width:23px;height:17px;background:url(image/delete.png) no-repeat;border:0;margin-left:10px;" />
									</td>
								</c:otherwise>
							</c:choose>
							</tr>
						</c:if>
				</table>
				</div>
				<input type="hidden" name="address" value="${admin.address }"/>
				<input type="hidden" name="currentPage" value="${currentPage }"/>
			</form>
	</div>
</div>
</body>
</html>