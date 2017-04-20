<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--这是免疫程序的修改弹出窗口-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user_model1_update.css"/>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript">
function goUpdate(){
	document.form1.submit();
}
</script>
</head>
<body>
	<form action="DisposalHarmlessServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="handle_record1" name="handle_record1" class="content_table">
			<tr>
				<td style="color:black;width:114px;">日期</td>
				<td style="color:black;width:120px;">数量</td>
				<td style="color:black;width:102px;">处理(死亡原因)</td>
				<td style="color:black;width:112px;">畜禽标识码</td>
				<td style="color:black;width:118px;">处理方法</td>
				<td style="color:black;width:140px;">负责人(处理单位)</td>
				<td style="color:black;width:122px;">备注</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="handle_record2" name="handle_record2" class="content_table">
				<c:forEach items="${all}" var="disp">
					<tr >
						<td>
						<input type="text" id="disposalTime_${disp.key }"  readonly="readonly" name="disposalTime_${disp.key }" value="${disp.value.disposalTime }" onclick="laydate()"/>
						</td>
						<td>
						<input type="text" id="number_${disp.key }" name="number_${disp.key }" value="${disp.value.number }"/>
						</td>
						<td>
						<input type="text" id="disposalOrResult_${disp.key }" name="disposalOrResult_${disp.key }" value="${disp.value.disposalOrResult }"/>
						</td>
						<td>
						<input type="text" id="livestockId_${disp.key }" name="livestockId_${disp.key }" value="${disp.value.livestockId }"/>
						</td>
						<td>
						<input type="text" id="disposalMethod_${disp.key }" name="disposalMethod_${disp.key }" value="${disp.value.disposalMethod }"/>
						</td>
						<td>
						<input type="text" id="disposalStation_${disp.key }" name="disposalStation_${disp.key }" value="${disp.value.disposalStation }"/>
						</td>
						<td>
						<textarea rows="3" id = "note_${disp.key }" name = "note_${disp.key }">${disp.value.note }</textarea>
						<input type = "hidden" name = "id_${disp.key }" id="id_${disp.key }" value="${disp.value.id }"/>
						</td>										
					</tr>  
						
				</c:forEach>  
    	</table>
		</div>
		<input type="hidden" name="farmId" id="farmId" value="${user.farm.farmId }"/>
		<input type="hidden" name="action" id="action" value="updateAll"/>
		<input type="hidden" name="role" id="role" value="user"/>
		<input type="hidden" name="index" id="index" value="${index }"/>
		<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage }"/>
	</form>   
</body>
</html>