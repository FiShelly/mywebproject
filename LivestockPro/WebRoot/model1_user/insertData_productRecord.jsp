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
		if(isSubmit("insertData_productRecord")){
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
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_productRecord',8)" />
				<input type="button" value="保存" id="save" name="save" onclick="goInsert();"/>
				<input type="button" value="返回" id="return" name="return" onclick="if(window.confirm('是否放弃录入??')) return top.location.href='${pageContext.request.contextPath}/model1_user/model1_user_index.jsp'" target="_top" />
			</form>
		</div>
		<div  class="content_table">
		<!--放入内容-->
		<form action="../ProductRecordServlet" method="post" id="form2" name="form2">
        <table id="insertData_productRecord" >

			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
			
				<td style="color:black;width:92px;padding-left:10px;">圈舍号</td>				
				<td style="color:black;width:107px;">时间</td>
				<td style="color:black;width:122px;">出生数</td>				
				<td style="color:black;width:100px;">调入数</td>
				<td style="color:black;width:100px;">调出数</td>
				<td style="color:black;width:100px;">死淘数</td>
				<td style="color:black;width:142px;">存栏数</td>
				<td style="color:black;width:205px;">备注</td>
				
			</tr>
			<tr>
				
				<td>
				<input type="text" id="roomNumber" name="roomNumber" onfocus="clearError('roomNumber')"/>
				</td>
				<td>
				<input type="text" id="date" name="date" readonly="readonly" onclick="laydate()" onfocus="clearError('date')"/>
				</td>
				<td>
				<input type="text" onfocus="clearError('birthNumber')" id="birthNumber" name="birthNumber"/>
				</td>
				
				<td>
				<input type="text" onfocus="clearError('enterNumber')" id="enterNumber" name="enterNumber"/>
				</td>

				<td>
				<input type="text" onfocus="clearError('exportNumber')" id="exportNumber" name="exportNumber"/>
				</td>
				<td>
				<input type="text" onfocus="clearError('deathNumber')" id="deathNumber" name="deathNumber"/>
				</td>
				<td>
				<input type="text" id="livestockNumber" name="livestockNumber" onfocus="clearError('livestockNumber')"/>
				</td>
				<td>
				<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
				</td>
				<td style="background-color:#F8FFF7">
					<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_productRecord',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
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