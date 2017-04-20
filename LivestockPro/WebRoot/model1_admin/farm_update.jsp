<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_register_content.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_getFullPath.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_register_checkElement_update.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_addRow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_submit_update.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/preView.js"></script>
<script type="text/javascript">
function goRegist() {
	if(isValid()) {
		document.form2.submit();
	}
}
</script>
<title>修改页面</title>
</head>
<body>
<div class="content">
	<div class="address">
		<font style="font-size:14px;font-family:黑体;white-space:nowrap;">
		您的位置:&nbsp;&nbsp;<font style="color:gray;font-family:黑体;font-size:14px;white-space:nowrap;">修改</font></font>
	</div>
	<div class="content_banner">
		<font>修改</font>
	</div>
	<div class="button">
		<form method="post" action="#" id="form1" name="form1" onsubmit="return isValid()">
			<input type="button" value="保存" id="save" name="save" onclick="goRegist()"/>
			<input type="button" value="返回" id="return" name="return" onclick="javascript:history.back()" />
		</form>
	</div>
	<!--放置内容的表格-->
	<div class="alter_content">
		<form method="post" action="FarmMesServlet" id="form2" name="form2" enctype="multipart/form-data">	
			<div id="content_div">
					<!--备案表-->
					<table class="login">
						<tr>
							<td>账号</td>
							<td>
							<input type="text" id="lg_number" name="loginId" onblur="checkLogin()" readonly="readonly" value="${user.loginId }"/>
							</td>
							<td style="border:0;" id="number_error"></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="password" id="lg_password1" name = "password1" onblur="checkPassword()" value=""/></td>
						</tr>
						<tr>
							<td>重复密码</td>
							<td><input type="password" id="lg_password2" name = "password2" onblur="checkPassword()" value=""/></td>
							<td style="border:0;" id="password"></td>
						</tr>
					</table>
					<table id="table1">
						<div id="the_title">养殖场、养殖小区备案表</div></br>
						<tr>
							<td>畜禽标识码</td>
							<td colspan=3>
							<input type="text" id="animalId" onblur="isEmpty('animalId')" name = "farmId" readonly="readonly"  value="${farm.farmId }" />
							</td>
							<td style="border:0;" id="animalId_error"></td>
						</tr>
						<tr>
							<td>名称</td>
							<td colspan=3>
							<input type="text" id="lg_name" onblur="isEmpty('lg_name')" name="farmName" value="${farm.farmName }"/>
							</td>
							<td style="border:0;" id="name_error"></td>
						</tr>
						<tr>
							<td>规模</td>
							<td colspan=3><input type="text" id="scale" onblur="isEmpty('scale')" name="farmsize" value="${farm.farmSize }"/></td>
							<td style="border:0;" id="scale_error"></td>
						</tr>
						<tr>
							<td>地址</td>
							<td colspan=3>
								<input type="text" id="address" onblur="isEmpty('address')"  name="location" value="${farm.location }"/>
							</td>
							<td style="border:0;" id="address_error"></td>
						</tr>
						<tr>
							<td>畜牧养殖场(小区)负责人</td>
							<td><input type="text" id="principle" onblur="isEmpty('principle')" name="leader" value="${farm.leader }"/></td>
							<td>养殖品种</td>
							<td><input type="text" id="variety" onblur="isEmpty('variety')" name="species" value="${farm.species }"/></td>
							<td style="border:0;" id="breed_error1"></td>
							<td style="border:0;" id="breed_error2"></td>
						</tr>
						<tr>
							<td>邮政编码</td>
							<td><input type="text" id="postalcode" onblur="isEmpty('postalcode')" name="zipcode" value="${farm.zipcode }"/></td>
							<td>联系电话</td>
							<td><input type="text" id="phone_number" onblur="isEmpty('phone_number')" name="phoneNum" value="${farm.phoneNum }"/></td>
							<td style="border:0;" id="person_error1"></td>
							<td style="border:0;" id="person_error2"></td>
						</tr>
						<tr>
							<td colspan=4 style="text-align:center;">畜牧养殖场(小区)有关情况简介</td>
							
						</tr>
						<tr>
							<td>动物防疫合格证编号</td>
							<td colspan=3><input type="text" id="certification" readonly="readonly"  onblur="isEmpty('certification')" name="certificate" value="${farm.certificate }"/></td>
							<td style="border:0;" id="certification_error"></td>
							
						</tr>
						<tr>
							<td>生产设施</td>
							<td colspan=3><input type="text" id="facility_product" onblur="isEmpty('facility_product')" name="productFac" value="${farm.productFac }"/></td>
							<td style="border:0;" id="facility_product_error"></td>
						</tr>
						<tr>
							<td>生产设备</td>
							<td colspan=3><input type="text" id="equipment" onblur="isEmpty('equipment')" name="productEquip" value="${farm.productEquip }"/></td>
							<td style="border:0;" id="equipment_error"></td>
						</tr>
						<tr>
							<td>环保设施</td>
							<td colspan=3><input type="text" id="envior_facility" onblur="isEmpty('envior_facility')" name="envirEquip" value="${farm.envirEquip }"/></td>
							<td style="border:0;" id="envior_facility_error"></td>
						</tr>
						<tr>
							<td style="height:165px;" >畜牧兽医技术人员数量和水平</br>(专业技能)</td>
							<td colspan=3 style="border-left-style:none;border-top-style:none;">
								<div style="height:168px;width:600px;margin-left:-11px;overflow-y:auto;border-right-style:none;margin-top:-1px;">
								<table id="inner_table" border=1>
									<tr>
										<td>
											技术人员方向
										</td>
										<td>
											技术人员数量
										</td>
										<td style="width:40px;height:17px;">
										<input type="button" id="btn_addtr" name="btn_addtr" onclick="addRow('inner_table')"   style="width:23px;height:17px;background:url(${pageContext.request.contextPath}/image/add.png) no-repeat;border:0;margin-left:-3px;"/>
										</td>
										
									</tr>
							<c:forEach items="${farm.person }" var="per">
									<tr>
										<td>
										<input type="text" id="major" name="major" style="width:100%;border-right-style:none;" value="${per.professSkill }"/>
										</td>
										<td>
										<input type="text" id="amount" name="amount" style="width:100%;border-right-style:none;" value="${per.personNum }"/>
										
										</td>
										<td style="width:40px;">
										<input type="button" id="delete" name="delete" onclick="deleteRow(this)" style="width:20px;background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border-right-style:none;margin-left:-5px;"/>
										</td>
									</tr>
							</c:forEach>
								</table>
								</div>
						
							</td>
						</tr>
						
					</table>
			</div>


			
            <div class="the_border">
            </div>
            
            <div style="width:100%;height:500px;background-color:white;padding-top:10px;padding-left:10px;">
            	<font style="font-family:宋体;font-size:12px;color:#484949;opacity:0.9;">畜禽养殖场平面图</font><br/>
           		 <div style="width:420px;height:420px;border:1px #89d6c4 solid;margin-top:10px;margin-bottom:10px;">
               		 <img id="img" width="100%" height="100%" src="${pageContext.request.contextPath}/uploadFinal/${user.farm.farmPhoto}"/>
            	</div>
            	<input name="file" type="file" id="file" onchange="document.getElementById('img').src=getFullPath(this);"/>
           		 <!--点击该上传按钮即将图片上传-->
           		 <input type="button" value="上传" style="background-color:#00A782;width:120px;margin-left:78px;border:0px;height:20px;font-family:微软雅黑;color:white;font-size:12px;"/>
            </div>
            <div class="the_border">
            </div>
            <input type="hidden" name = "registDate" id = "registDate" value="${farm.registDate}"/>
			<input type="hidden" name = "address" id = "address" value="${admin.address}"/>
			<input type="hidden" name = "action" id = "action" value="update"/>	
			<input type="hidden" name = "oldPhoto" id = "oldPhoto" value="${farm.farmPhoto }"/>	
			<input type="hidden" name = "oldpw" id = "oldpw" value="${user.password }"/>	
			<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>
	</form>
	</div>
	
</div>
</body>
</html>