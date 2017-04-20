<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_immuneProgram_centerContent.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_checkbox.js"></script>

</head>
<body>
	<div class="content">
		<div class="all_tables"><!--2015.5.23加上滚动条-->
			<div style="width:100%;height:100%;overflow-y:auto;">
				<!--放入内容-->
				<form id="immuneContent" name="immuneContent">
					<table id="immuneContent">
					<!--2015.6.1增加行初始颜色为f5f5f5f5-->
						<tr style="background-color:#f5f5f5;">
							<td style="width:20px;">
								日龄
							</td>
							<td style="width:50px;">
								疫苗名称
							</td>
							<td>
								剂量
							</td>
							<td>
								免疫方式
							</td>
							<td style="width:50px;">
								备注
							</td>
							
						</tr>
			        	<c:forEach items="${all }" var="pro">	
			        	<tr>
							
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
							
						</tr>

						</c:forEach>
						
						
			        </table>
			    </form>
		</div>	
	</div>
	</div>
</body>
</html>