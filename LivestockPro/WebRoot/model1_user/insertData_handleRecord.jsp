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
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_insertData_addDelRow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_insertData_submit.js"></script>
<script type="text/javascript">
	function goInsert(){
		if(isSubmit("insertData_handleRecord")){
			document.form2.submit();
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
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_handleRecord',7)" />
				<input type="button" value="保存" id="save" name="save" onclick="goInsert();"/>
				<input type="button" value="返回" id="return" name="return" onclick="if(window.confirm('是否放弃录入??')) return top.location.href='${pageContext.request.contextPath}/model1_user/model1_user_index.jsp'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		
		
	
		<div  class="content_table">
		<!--放入内容-->
	 <form action="../DisposalHarmlessServlet" method="post" id="form2" name="form2">
        <table id="insertData_handleRecord" name="insertData_handleRecord" >
   
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
			
				<td style="color:black;width:74px;padding-left:10px;">日期</td>
				<td style="color:black;width:60px;">数量</td>
				<td style="color:black;width:172px;">处理(死亡原因)</td>
				<td style="color:black;width:132px;">畜禽标识码</td>
				<td style="color:black;width:118px;">处理方法</td>
				<td style="color:black;width:213px;">负责人(处理单位)</td>
				
				<td style="color:black;width:202px;">备注</td>
				
			</tr>
			<tr>
				
				<td>
				<input type="text" id="date" name="date" onclick="laydate()" onfocus="clearError('date')"/>
				</td>
				<td>
				<input type="text" id="amount" name="amount" onfocus="clearError('amount')"/>
				</td>
				<td>
				<input type="text" id="deathReason" name="deathReason" onfocus="clearError('deathReason')"/>
				</td>
				<td>
				<input type="text" id="animalId" name="animalId" onfocus="clearError('animalId')"/>
				</td>
				<td>
				<input type="text" id="processMethod" name="processMethod" onfocus="clearError('processMethod')"/>
				</td>
				<td>
				<input type="text" id="principle" name="principle" onfocus="clearError('principle')"/>
				</td>
				
				<td>
				<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
				</td>
				<td style="background-color:#F8FFF7">
					<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_handleRecord',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
				</td>
			</tr>		                 
    </table>
    	<input type = "hidden" id="action" name = "action" value="insert"/>
    	<input type = "hidden" id="farmId" name = "farmId" value="${user.farm.farmId }"/>
	</form>
	</div>
		
</div>
</body>
</html>