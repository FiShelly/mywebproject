<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_record_table.css"/>
<script language="javascript">
	if("${info}" != ""){
	 	alert("${info}");
	}
</script>
</head>
<body>
	<div class="content">
		<div class="content_banner">
			<font>${user.farm.farmName }</font>
		</div>
	<div class="alter_content">
		<div class="all_tables">
			<form action="#" method="post">
								<!--备案表-->
				<table id="table1">
					<caption id="the_title"><a name="1">养殖场、养殖小区备案表</a></caption>
					<tr>
						<td>名称</td>
						<td>${user.farm.farmName }</td>
						<td>养殖品种</td>
						<td>${user.farm.species }</td>
					</tr>
					<tr>
						<td>规模</td>
						<td colspan=3>${user.farm.farmSize }</td>
					</tr>
					<tr>
						<td>地址</td>
						<td colspan=3>${user.farm.location }</td>
					</tr>
					<tr>
						<td colspan=2>畜牧养殖场(小区)负责人</td>
						<td colspan=2>${user.farm.leader }</td>
					</tr>
					<tr>
						<td>邮政编码</td>
						<td>${user.farm.zipcode }</td>
						<td>联系电话</td>
						<td>${user.farm.phoneNum }</td>
					</tr>
					<tr>
						<td colspan=4 style="text-align:center;">畜牧养殖场(小区)有关情况简介</td>
							
					</tr>
					<tr>
						<td>动物防疫合格证编号</td>
						<td colspan=3>${user.farm.certificate }</td>
							
					</tr>
					<tr>
						<td>生产设施</td>
						<td colspan=3>${user.farm.productFac }</td>
					</tr>
					<tr>
						<td>生产设备</td>
						<td colspan=3>${user.farm.productEquip }</td>
					</tr>
					<tr>
						<td>环保设施</td>
						<td colspan=3 style="border-bottom-style:none;">${user.farm.envirEquip }</td>
					</tr>
					<tr>
						<td style="height:164px;">畜牧兽医技术人员数量和水平(专业技能)</td>
						<td colspan=3 style="border-left-style:none;border-right-style:none;border-top-style:none;border-bottom-style:none;">
						<div style="height:170px;width:533px;margin-left:-10px;overflow-y:auto;">
							<table id="inner_table">
								<tr>
									<td>
										技术人员方向
									</td>
									<td style="border-right-style:none;">
										技术人员数量
									</td>
								</tr>
						<c:forEach items="${user.farm.person }" var="per">
								<tr>
									<td>
									${per.professSkill }
									</td>
								<td style="border-right-style:none;">
									${per.personNum }
									</td>
								</tr>
						 </c:forEach>
							</table>

						</div>
						</td>
					</tr>				
				</table>
				<table id="table1">
					<caption id="the_title"><a name="1">养殖场、养殖小区平面图</a></caption>
					<tr>
						<td style="text-align:center;">
						
							<img src="${pageContext.request.contextPath}/uploadFinal/${user.farm.farmPhoto}"  width="100%" height="100%"/>

						</td>
					</tr>				
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>