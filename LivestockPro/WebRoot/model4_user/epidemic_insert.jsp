<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_insertData_content.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_changeLiBgColor.js"></script>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model4_inputCheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area2.js"></script>
	<style type="text/css">
		input{
			width: 99%;
		}
		td{
			width: 150px;
			height: 30px;
		}
	</style>
</head>
<body >
	<div class="content">
		<div style="overflow-y:auto;height:100%;width: 100%;">
			<div class="tables">												  
				<form id="epidemicForm" name="epidemicForm" method="post" action="${pageContext.request.contextPath }/EpidemicRecordServlet" onsubmit="return checkContent();">
					<table>

						<tr>
							<td style="background-color:#f0f0f0">
								发病动物种类
							</td>
							<td>
								<input type="text" id="animal_type" name="animal_type" onfocus="clearError('animal_type')" />
							</td>
							<td style="background-color:#f0f0f0">
								饲养种类
							</td>
							<td>
								<input type="text" id="feed_type" name="feed_type" onfocus="clearError('feed_type')" />
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								爆发日期
							</td>
							<td>
								<input type="text" id="date" name="date" onfocus="clearError('date')" onclick="laydate()" readonly="readonly" />
							</td>
							<td style="background-color:#f0f0f0">
								是否有人员感染
							</td>
							<td>
								<input type="radio" name="infect" style="width:13px;height:15px;vertical-align: bottom" />是
								<input type="radio" name="infect" style="width:13px;height:15px;vertical-align: bottom" checked="checked" />否
							</td>
							
						</tr>
						<tr>
							
							<td style="background-color:#f0f0f0">
								饲养规模
							</td>
							<td colspan="3">
								<input type="text" id="feed_size" name="feed_size" onfocus="clearError('feed_size')" />
							</td>
						
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								饲养方式
							</td>
							<td colspan="3">
								<input type="text" id="feed_method" name="feed_method" onfocus="clearError('feed_method')" />
							</td>
						</tr>

						<tr>
							<td style="background-color:#f0f0f0">
								临床症状
							</td>
							<td colspan="3">
								<input type="text" id="symptom" name="symptom" onfocus="clearError('symptom')" />
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								死亡情况
							</td>
							<td colspan="3">
								<input type="text" id="death" name="death" onfocus="clearError('death')" />
							</td>
						</tr>
						
						<tr>
							<td style="background-color:#f0f0f0">
								封锁情况
							</td>
							<td colspan="3">
								<input type="text" id="close" name="close" onfocus="clearError('close')" />
							</td>
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								免疫情况
							</td>
							<td colspan="3">
								<input type="text" id="immune_situation" name="immune_situation" onfocus="clearError('immune_situation')" />
							</td>
							
						</tr>
						<tr>
							<td style="background-color:#f0f0f0">
								治疗情况
							</td>
							<td colspan="3">
								<input type="text" id="treat" name="treat" onfocus="clearError('treat')" />
							</td>
						</tr>

					</table>
					<input type="hidden" name="action" value="insert"/>
					<input type="hidden" name="farmId" value="${user.farm.farmId }"/>	
					<input type="hidden" name="status" value="0" />
				</form>
			</div>
		</div>

	</div>
</body>
</html>
