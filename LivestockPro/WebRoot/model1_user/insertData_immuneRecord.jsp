<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_check2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_select.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_openNewWin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_insertData_addDelRow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_insertData_submit.js"></script>
<script type="text/javascript">
	function goInsert(){
		if(isSubmit("insertData_immuneRecord")){
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
			<form id="btns" name="btns" method="post" action="#" >
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_immuneRecord',12)" />
				<input type="button" value="保存" id="save" name="save" onclick="goInsert();"/>
				<input type="button" value="返回" id="return" name="return" onclick="if(window.confirm('是否放弃录入??')) return top.location.href='${pageContext.request.contextPath}/model1_user/model1_user_index.jsp'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		
		
	
		<div  class="content_table">
		<!--放入内容-->
  <form action="../ImmuneRecordServlet" method="post" id="form2" name="form2">
        <table id="insertData_immuneRecord" name="insertData_immuneRecord" >
          
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
			
				<td style="color:black;width:58px;padding-left:10px;">时间</td>
				
				<td style="color:black;width:60px;">圈舍号</td>
				<td style="color:black;width:63px;">存栏数</td>
				<td style="color:black;width:78px;">免疫数量</td>
				<td style="color:black;width:116px;">疫苗名称</td>
				<td style="color:black;width:130px;">疫苗生产厂</td>
				<td style="color:black;width:60px;">批号</td>
				<td style="color:black;width:100px;">使用年限</td>
				<td style="color:black;width:120px;">免疫方法</td>
				<td style="color:black;width:80px;">免疫剂量</td>
				<td style="color:black;width:80px;">免疫人员</td>
				<td style="color:black;width:50px;">备注</td>
				
			</tr>
			<tr>
				
				<td>
				<input type="text" id="date" name="date" onclick="laydate()" onfocus="clearError('date')"/>
				</td>
				<td>
				<input type="text" id="roomNumber" name="roomNumber" onfocus="clearError('roomNumber')"/>
				</td>
				<td>
				<input type="text" id="liveStockNumber" name="liveStockNumber" onfocus="clearError('liveStockNumber')"/>
				</td>
				<td>
				<input type="text" id="immuneNumber" name="immuneNumber" onfocus="clearError('immuneNumber')"/>
				</td>
				<td>
				<input type="text" id="immuneName" name="immuneName" onfocus="clearError('immuneName')"/>
				</td>
				<td>
				<input type="text" id="immuneProducer" name="immuneProducer" onfocus="clearError('immuneProducer')"/>
				</td>
				<td>
				<input type="text" id="batchNumber" name="batchNumber" onfocus="clearError('batchNumber')"/>
				</td>
				<td>
				<input type="text" id="validTime" name="validTime" onfocus="clearError('validTime')"/>
				</td>
				<td>
				<input type="text" id="immuneMethod" name="immuneMethod" onfocus="clearError('immuneMethod')"/>
				</td>
				<td>
				<input type="text" id="immuneLevel" name="immuneLevel" onfocus="clearError('immuneLevel')"/>
				</td>
				<td>
				<input type="text" id="immuneUser" name="immuneUser" onfocus="clearError('immuneUser')"/>
				</td>
				<td>
				<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
				</td>
				
				<td style="background-color:#F8FFF7">
					<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_immuneRecord',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
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