<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_immuneProgram_centerContent.css"/>
<script type="text/javascript">
function changeTrBgColor(r,specName,farmId){
	var i=r.parentNode.parentNode.rowIndex;
	for(var j=0;j<document.getElementById("selectTable").rows.length;j++){
		if(j==i)
		{
			document.getElementById("selectTable").rows[j].style.backgroundColor="#f5f5f5";
			document.selectAnimal.elements[j].checked=false;
		}else{
		
			document.getElementById("selectTable").rows[j].style.backgroundColor="white";

			document.selectAnimal.elements[j].checked=true;
			window.open("${pageContext.request.contextPath}/ImmuneProServlet?action=list&species="+specName+"&farmId="+farmId,"centerContent");
			
		}
	}
	
}
function animalSelect(r,specName,farmId){
	var i=r.parentNode.parentNode.rowIndex;
	for(var j=0;j<document.getElementById("selectTable").rows.length;j++){
		if(j==i)
		{
			document.getElementById("selectTable").rows[j].style.backgroundColor="#f5f5f5";
			// document.selectAnimal.elements[j].checked=false;
			window.open("${pageContext.request.contextPath}/ImmuneProServlet?action=list&species="+specName+"&farmId="+farmId,"centerContent");
			
		}else{
			document.getElementById("selectTable").rows[j].style.backgroundColor="white";
			// document.selectAnimal.elements[j].checked=true;
		}
	}
}

</script>
</head>
<% 
	int index = 0;
%>
<body>
	<div class="content">	
			<form id="selectAnimal" name="selectAnimal" method="post" action="#">
			<div class="content_leftMenu">
				<table id="selectTable">
					<tr>
						<td colspan=2>免疫物种</td>
					</tr>
					<c:forEach items="${species }" var="spec">
					<tr>
						<td style="width:5px;"><input type="radio" id="selectPig_<%=index++ %>" name="select"
							<%=index==1?" checked='checked'":"" %>  onclick="animalSelect(this,'${spec}','${farmId}')" value="${spec}"/>
						</td>
						<td>
						<a href="javascript:void(0);" onclick="changeTrBgColor(this,'${spec}','${farmId}')" style="display:block">${spec }</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</form>	
	</div>
	<script type="text/javascript">
		var eles=document.getElementsByTagName("input");		
		for(var i=0;i<eles.length;i++) {

			if(eles[i].type == "radio" && eles[i].checked == true) {
				window.open("${pageContext.request.contextPath}/ImmuneProServlet?action=list&species="+eles[i].value+"&farmId=${farmId}","centerContent");
			}
		}
	</script>
</body>
</html>
