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
		if(isSubmit("insertData_preventRecord")){
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
				<input type="button" value="增加" id="addRow" name="addRow" onclick="add('insertData_preventRecord',8)" />
				<input type="button" value="保存" id="save" name="save" onclick="goInsert();"/>
				<input type="button" value="返回" id="return" name="return" onclick="if(window.confirm('是否放弃录入??')) return top.location.href='${pageContext.request.contextPath}/model1_user/model1_user_index.jsp'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		
		
	
		<div  class="content_table">
		<!--放入内容-->
	 <form action="../EpidemicMonitoringServlet" method="post" id="form2" name="form2">
        <table id="insertData_preventRecord" name="insertData_preventRecord" >
   
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
			
				<td style="color:black;width:68px;padding-left:10px;">采样日期</td>
				<td style="color:black;width:72px;">圈舍号</td>		
				<td style="color:black;width:88px;">采样数量</td>
				<td style="color:black;width:117px;">监测项目</td>
				<td style="color:black;width:158px;">监测单位</td>
				<td style="color:black;width:138px;">监测结果</td>
				<td style="color:black;width:131px;">处理情况</td>
				<td style="color:black;width:122px;">备注</td>
				
			</tr>
			<tr>
				
				<td>
				<input type="text" id="samplingDate" name="samplingDate" onclick="laydate()" onfocus="clearError('samplingDate')"/>
				</td>
				<td>
				<input type="text" id="roomNumber" name="roomNumber" onfocus="clearError('roomNumber')"/>
				</td>
				
				<td>
				<input type="text" id="sampleNumber" name="sampleNumber" onfocus="clearError('sampleNumber')"/>
				</td>
				<td>
				<input type="text" id="detectPro" name="detectPro" onfocus="clearError('detectPro')"/>
				</td>
				<td>
				<input type="text" id="detectUnit" name="detectUnit" onfocus="clearError('detectUnit')"/>
				</td>
				<td>
				<input type="text" id="detectResult" name="detectResult" onfocus="clearError('detectResult')"/>
				</td>
				<td>
				<input type="text" id="processCon" name="processCon" onfocus="clearError('processCon')"/>
				</td>
				<td>
				<textarea rows="3" cols="30" id="remark" name="remark"></textarea>
				</td>
				<td style="background-color:#F8FFF7">
					<input type="button" id="deleteRow" name="deleteRow" onclick="deleteRRow('insertData_preventRecord',this)" style="background:url(${pageContext.request.contextPath}/image/delete.png) no-repeat;border:0;"/>
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