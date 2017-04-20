<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_immuneProgram_centerContent.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model1_openNewWin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_material_open.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_clearCondition.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
<script type="text/javascript">
function changeTrBgColor(r){
	// alert("ok");
	var i=r.parentNode.parentNode.rowIndex;
	for(var j=0;j<document.getElementById("selectTable").rows.length;j++){
		if(j==i)
		{
			document.getElementById("selectTable").rows[j].style.backgroundColor="#f5f5f5";
			document.selectAnimal.elements[j].checked="checked";
		}else{
			document.getElementById("selectTable").rows[j].style.backgroundColor="white";
			document.selectAnimal.elements[j].checked=false;
		}
	}
	
}
</script>
</head>
<body>
	<div class="content">
		 <div class="all_tables">
				<form id="immuneContent" name="immuneContent">
					<table id="immuneContent">
					<!--2015.6.1增加行初始颜色为f5f5f5f5-->
						<tr style="background-color:#f5f5f5;">
							<td style="width:30px;">
								日龄
							</td>
							<td style="width:70px;">
								疫苗名称
							</td>
							<td>
								剂量
							</td>
							<td>
								免疫方式
							</td>
							<td style="width:70px;">
								备注
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach items="${all }" var="pro">	
			        	<tr id="tr_${pro.sequeueNum }">
							<td style="width:20px;">
								${pro.dateAge }
							</td>
							<td style="width:50px;">
								${pro.vaccine }
							</td>
							<td>
								${pro.dose }
							</td>
							<td>
								${pro.way }
							</td>
							<td style="width:50px;">
								${pro.note}
							</td>
							<td>
								<a href="javascript:void(0)" onclick="openAddContent('${pro.id}')">修改</a>&nbsp;
								<a href="#" onclick="isDeleteMes('ImmunePro','id','${pro.id}')">删除</a>
								
							</td>
						</tr>
						</c:forEach>

						

			        </table>
			    </form>
		</div>	
	</div>
</body>
</html>