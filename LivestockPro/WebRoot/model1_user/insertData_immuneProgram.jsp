<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_check2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_openNewWin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_insertData_addDelRow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_insertData_submit.js"></script>
<script language="javascript">
	function goInsert() {
		if(isSubmit("insertData_immuneProgram")) {
			document.form2.submit() ;
		}		
	}
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
		
		<div class="button">
			<form id="btns" name="btns" method="post" action="#"  >
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_immuneProgram',5)" />
				<input type="button" value="删除" id="delete" name="delete"/>
				<input type="button" value="保存" id="save" name="save" onclick="goInsert()"/>
				<input type="button" value="返回" id="return" name="return" onclick="top.location.href='${pageContext.request.contextPath}/model1_user_html/index.html'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		
		
	
		<div  class="content_table">
		<form action="../ImmuneProServlet" method="post" id="form2" name="form2" target="_top">
			<!--放入内容-->
			<table>
				<tr>
					<td style="font-size:12px;background-color:white;padding-left:10px;">
						免疫物种
					</td>
					<td style="background-color:white;">
						<input type="text" id="immuneSpecies" name="immuneSpecies" style="border:1px solid #12A686;margin-left:-491px;
							width:100px;height:22px;border-radius:2px;" onblur="checkImmuneProSpec(this.value,'${user.farm.farmId}')" onfocus="clearError('immuneSpecies')"/>
						<span id = "immuneSpecies_error"></span>	
					</td>
					 
				</tr>
			</table>
	        <table id="insertData_immuneProgram" name="insertData_immuneProgram">

				<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
					
					<tr>
					
						<td style="color:black;width:167px;padding-left:10px;">日龄</td>					
						<td style="width:200px;">疫苗种类</td>
						<td style="color:black;width:200px;">注射剂量</td>
						<td style="color:black;width:200px;">免疫方式</td>
						<td style="color:black;width:250px;">备注</td>
						
					</tr>
					<tr>
						
						<td>
						<input type="text" id="immuneDate" name="immuneDate" onfocus="clearError('immuneDate')"/>
						</td>
						<td>
						<input type="text" id="series" name="series" onfocus="clearError('series')"/>
						</td>
						<td>
						<input type="text" id="dose" name="dose" onfocus="clearError('dose')"/>
						</td>
						<td>
						<input type="text" id="part" name="part" onfocus="clearError('part')"/>
						</td>
						<td>
						<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
						</td>
						<td style="background-color:#F8FFF7">
							<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_immuneProgram',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
						</td>
					</tr>
                     
	    	</table>
	    	<input type="hidden" name="action" id = "action" value="insert"/>
	    	<input type="hidden" name="farmId" id = "farmId" value="${user.farm.farmId }"/>
	    </form>
		<div>
		
	</div>
	</div>
	</div>
</body>
</html>
