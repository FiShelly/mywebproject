<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_insertData_content.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_changeLiBgColor.js"></script>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_submit.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model4_inputCheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area2.js"></script>
	<style type="text/css">
		td{
			height: 29px;
		}
	</style>
</head>
<body onload="addressInit('cmbProvince', 'cmbCity', 'cmbArea');">
	<div class="content">
		<div style="overflow-y:auto;height:100%;width: 100%;">
			<div class="tables">
				<form method="" action="" onsubmit="return checkContent();">
					<table>
						<tr>
							<td style="background-color:#f0f0f0;">
								养殖场名称
							</td>
							<td colspan=3 style="width:360px;">
								${epi.farm.farmName}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0;">
								联系人
							</td>
							<td>
								${epi.farm.leader}
							</td>
							<td style="background-color:#f0f0f0;">
								联系人电话
							</td>
							<td>
								${epi.farm.phoneNum}
							</td>
						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								发病动物种类
							</td>
							<td>
								${epi.sickSpecies}
							</td>
							<td style="background-color:#f0f0f0">
								饲养种类
							</td>
							<td>
								${epi.sickSpecies}
							</td>
						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								养殖场地点
							</td>
							<td colspan=3 style="text-align:left;">
								${epi.farm.location}
							</td>
							
						</tr>
						
						<tr>
							
							<td style="background-color:#f0f0f0">
								饲养规模
							</td>
							<td colspan="3">
								${epi.feedScale}
							</td>
							

						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								饲养方式
							</td>
							<td colspan="3">
								${epi.feedSitua}
							</td>
						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								临床症状
							</td>
							<td colspan="3">
								${epi.clinicalSysp}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								死亡情况
							</td>
							<td colspan="3">
								${epi.deaths}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								是否有人员感染
							</td>
							<td colspan="3">
								${epi.isPeoInfect=="true"?"是":"否"}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								封锁情况
							</td>
							<td colspan="3">
								${epi.blockadeSitua}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								免疫情况
							</td>
							<td colspan="3">
								${epi.immunitySitua}
							</td>
							
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								治疗情况
							</td>
							<td colspan="3">
								${epi.treatmentSitua}
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								审核处理情况
							</td>
							<td colspan="3">
								${epi.status==0?"未审核，未处理":""}${epi.status==1?"已审核，未处理":""}
								${epi.status==2?"已审核，未处理":""}${epi.status==3?"已审核，已处理":""}
							</td>
						
						</tr>
					</table>
				</form>
				
			</div>
		</div>

	</div>
</body>
</html>