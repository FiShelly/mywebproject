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
	<form action="ProductRecordServlet" method="post" id="form1" name="form1">
		<div class="banner">
			<div class="button">
				<input type="button" value="保存" id="save" name="save" onclick="goUpdate()"/>
				<input type="button" value="取消" id="cancel" name="cancel"/>
			</div>
		</div>
		<!--表头固定位置-->
		<table id="product_record1" name="product_record1" class="content_table">
			<tr>
				<td style="color:black;width:92px;">圈舍号</td>
				<td style="color:black;width:107px;">时间</td>
				<td style="color:black;width:102px;">出生数</td>
				<td style="color:black;width:102px;">死淘数</td>
				<td style="color:black;width:122px;">调出数</td>
				<td style="color:black;width:122px;">调入数</td>
				<td style="color:black;width:122px;">存栏数</td>
				<td style="color:black;width:135px;">备注</td>
			</tr>
		</table>
		<div class="updateContent">
			<!--放入内容-->

	        <table id="product_record2" name="product_record2" class="content_table">
	            
				<c:forEach items="${all }" var="pro">	
					<tr>
						<td>
							<input type="text" id="roomNum_${pro.key }" name="roomNum_${pro.key }" value="${pro.value.roomNum }"/>
						</td>
						<td>
						<input type="text" id="recordDate_${pro.key }"   readonly="readonly" name="recordDate_${pro.key }" onclick="laydate()"  value="${pro.value.recordDate }"/>
						</td>
						<td>
						<input type="text" id="birthNum_${pro.key }" name="birthNum_${pro.key }" value="${pro.value.birthNum }"/>
						</td>
						<td>
						<input type="text" id="deadNum_${pro.key }" name="deadNum_${pro.key }" value="${pro.value.deadNum }"/>
						</td>
						<td>
						<input type="text" id="putNum_${pro.key }" name="putNum_${pro.key }" value="${pro.value.putNum }"/>
						</td>
						<td>
						<input type="text" id="inNum_${pro.key }" name="inNum_${pro.key }" value="${pro.value.inNum }"/>
						</td>
						<td>
						<input type="text" id="remainNum_${pro.key }" name="remainNum_${pro.key }" value="${pro.value.remainNum }"/>
						</td>
						<td>
						<textarea rows="3" id="note_${pro.key }" name="note_${pro.key }">${pro.value.note }</textarea>
						<input type = "hidden" name = "id_${pro.key }" id="id_${pro.key }" value="${pro.value.id }"/>
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