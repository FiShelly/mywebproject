<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_check2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_select.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_clearCondition.js"></script>
<script language="javascript">
	function go(num){
		document.getElementById("currentPage").value = num ;
		document.form3.submit() ;	// 表单提交
	}
</script>
<%
	int currentPage = 1 ;	// 为当前所在的页，默认在第1页
	int lineSize = 11 ;		// 每次显示的记录数
	long allRecorders = 0 ;	// 表示全部的记录数
	long pageSize = 1 ;		// 表示全部的页数（尾页）
	String url = (String)request.getAttribute("URL") ;
%>
<%
	try{
		currentPage = (Integer)(request.getAttribute("currentPage")) ;
	} catch(Exception e) {}
	try{
		lineSize = (Integer)(request.getAttribute("lineSize")) ;
	} catch(Exception e) {}
	try{
		allRecorders = (Integer)(request.getAttribute("allRecorders")) ;
	} catch(Exception e) {}
%>
<%
	System.out.println(allRecorders);
	pageSize = (allRecorders + lineSize -1) / lineSize ;
	if(pageSize == 0){
		pageSize = 1 ;
	}
%>
</head>
<body>
	<div class="content">
		<div class="content_banner">
			<font>${param.farmName}</font>
		</div>
		
		<div class="button">
			<form id="btns" name="btns" method="post" action="#">
				<input type="button" value="返回" id="return" name="return" 
					onclick="top.location.href='${pageContext.request.contextPath}/model1_admin/model1_admin_index.jsp'" target="_top" />
			</form>
		</div>
		<!--查询条件-->
		<div class="search">
		<form action="${URL }" method="post" name="search" id="search" >
			<div style="margin-left:10px;float:left;margin-top:15px;float:left;">
				查询内容:<input type="text" id="content_search" name="content_search"  value="${content_search }" 
						 style="width:120px;height:20px;border:1px solid #89D6C4;"/>
			</div>
			<div style="float:left;margin-top:15px;margin-left:10px;">
				日期:<input type="text" id="date1" name="date1" value="${date1 }" readonly="readonly" 
					style="width:90px;height:20px;border:1px solid #89D6C4;" onclick="laydate()"/>
			</div>

			<div style="float:left;margin-left:-17px;margin-top:18px;">
				<img src="${pageContext.request.contextPath}/image/calendar.png" height="16px" width="16px" />
			</div>
			<div style="float:left;margin-top:15px;margin-left:3px;">
				至<input type="text" id="date2" name="date2"  value="${date2 }" readonly="readonly" 
					onclick="laydate()" style="width:90px;height:20px;border:1px solid #89D6C4;" />
			</div>
			<div style="float:left;margin-left:-17px;margin-top:18px;">
				<img src="${pageContext.request.contextPath}/image/calendar.png" height="16px" width="16px" />
			</div>

			<div id="specialDiv">
				<input type="submit" value="查询" id="searchBtn" name="clearBtn" style="width:80px;height:25px;border-radius:6px;
					background-color:#12a686;color:white;border:0px;font-size:14px;"/>
				<img src="${pageContext.request.contextPath}/image/search.png" width="15px" height="15px" 
					style="margin-left:-25px;margin-right:20px;margin-top:-3px;"/>
				<input type="button" value="清除条件" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn"  
					style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;"/>
			</div>
			<input type="hidden" name = "action" id = "action" value="list"/>
			<input type="hidden" name = "farmId" id = "farmId" value="${param.farmId}" />
		</form>
	</div>


	<div style="width:100%;height:79%;overflow-y:auto;">
				<!--放入内容-->
                    <table id="table" class="content_table">
                       <form action="#" method="post" id="form2" name="form2">
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>	
				<td style="color:black;width:78px;padding-left:10px;">日期</td>
				<td style="color:black;width:90px;">消毒场所</td>
				<td style="color:black;width:100px;">消毒药名称</td>
				<td style="color:black;width:70px;">用药剂量</td>
				<td style="color:black;width:99px;">消毒方法</td>
				<td style="color:black;width:60px;">操作人</td>
			</tr>
		<c:forEach items="${all }" var="dis">
			<tr>	
				<td style="padding-left:10px;">${dis.disinfectionTime }</td>
				<td>${dis.place }</td>
				<td>${dis.disinfectionName }</td>
				<td>${dis.disinfectionDose }</td>
				<td>${dis.method }</td>
				<td>${dis.sign }</td>
			</tr>	
		</c:forEach>	
			</form>
			<form action="${URL }" method="post" name="form3">
			<tr>			
				<td colspan="6" style="align:right">
					<div class="yema">
						<input type="button" value="上一页" 
							onclick="go(<%=currentPage-1%>)" <%=currentPage==1?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<input type="button" value="下一页" 
							onclick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
						<label><%=pageSize %></label><!--总页数为表格数据量-->
						<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
						<select name="page" onchange="go(this.value)">
							<%
								for(int x=1;x<=pageSize;x++){
							%>
								<option value="<%=x%>" <%=x==currentPage?"SELECTED":""%>><%=x%></option>
							<%
								}
							%>
						</select>
						<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
					</div>
				</td>
			</tr>
			<input type="hidden" name = "currentPage" id="currentPage" value="1"/>
			<input type="hidden" name = "action" id = "action" value="list"/>		
			<input type="hidden" name = "content_search" id = "content_search" value="${content_search }" />
			<input type="hidden" name = "date1" id = "date1" value="${date1 }" />
			<input type="hidden" name = "date2" id = "date2" value="${date2 }" />
			<input type="hidden" name = "farmId" id = "farmId" value="${param.farmId }" />
		</form>
                            
                    </table>
				</div>
			</div>
	

</body>
</html>