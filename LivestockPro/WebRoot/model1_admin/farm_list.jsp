<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--这是放置右边内容-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_check.css"/>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_setTrBgColor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_clearCondition.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
<script language="javascript">
	function go(num){
		document.getElementById("currentPage").value = num ;
		document.form3.submit() ;	// 表单提交
	}
	function goDeleteAll() {
		var select = document.getElementsByName("check");
		var count = 0;
		for(var i = 0 ;i<select.length;i++) {
			if(select[i].checked == true) {
				count = count + 1;
			}
		}
		if(window.confirm("确定删除这"+count+"项养殖场信息？")) {
			document.form2.submit() ;
		}
	}
	
</script>
</head>

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

<c:choose>
<c:when test="${all!=null}">
<body>
<div class="content">
	<div class="address">
		<font style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;养殖档案管理</a></font>&nbsp;>&nbsp;
		<font style="color:gray;font-family:黑体;font-size:14px;white-space: nowrap;">查看</font>
	</div>
	
	<div class="content_banner">
		<font>查看</font>
	</div>
    	<div class="search">
		<form action="${URL}" method="post" name="search" id="search" style="height:60px;" >
			<div style="margin-left:10px;">
				名称:<input type="text" id="name" name="name" value="${name }"/>
			</div>

			<div>
				日期:<input type="text" id="date1" name="date1" readonly="readonly" onclick="laydate()" value="${date1 }"/>
			</div>

			<div style="margin-left:-40px;margin-top:3px;">
				<img src="${pageContext.request.contextPath }/image/calendar.png" height="16px" width="16px" />
			</div>
			<div style="margin-left:-17px;">
				至<input type="text" id="date2" name="date2" onclick="laydate()" readonly="readonly" style="margin-left:3px;" value="${date2 }" />
			</div>
			<div style="margin-left:-40px;margin-top:3px;">
				<img src="${pageContext.request.contextPath }/image/calendar.png" height="16px" width="16px"/>
			</div>
			<div>
				地址:<input type="text" id="area" name="area" value="${area }"/>
			</div>
			<input type="hidden" name = "address" id = "address" value="${admin.address}" />
				
			<div id="specialDiv">
				<button type="submit" value="查询" style="width:80px;height:25px;
					border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;" >
				查询&nbsp;
				<img src="${pageContext.request.contextPath}/image/search.png" width="15px" height="15px"/></button>
				<input type="button" value="清除条件" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn" 
				 style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;"/>
			</div>
			<input type="hidden" name = "action" id = "action" value="list"/>
		</form>
	</div>
	
	<div style="width:100%;height:75%;overflow-y:auto;">
	<!--放入内容-->
	<table id="table" class="content_table">
		<form action="${URL}" method="post" id="form2" name="form2">
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
				<td style="width:20px;">
					<input type="checkbox" name="checkAll" id="checkAll" style="float:left;margin-left:10px;margin-right:10px;" onclick="CheckAll()" />
					<input type="hidden" name = "action" id = "action" value="deleteAll"/>
				</td>
				<td style="color:black;">名称</td>
				
				<td style="color:black;">品种</td>
				<td style="color:black;">注册时间</td>
				<td style="color:black;">地址</td>
				<td style="color:black;">操作</td>
			</tr>
			<c:forEach items="${all}" var="farm">
			<tr id="tr_${farm.farmId}">
				<td style="text-align:left;width:20px;">
					<input type="checkbox" onclick="selectIt();" id="check" name="check" style="float:left;margin-left:10px;margin-right:10px;" value="${farm.farmId}"/>
				</td>
				<td>
					${farm.farmName}
				</td>
				<td>
					${farm.species}
				</td>
				<td>${farm.registDate}</td>
				<td>${farm.location }</td>
				<td><a href="${pageContext.request.contextPath}/FarmMesServlet?action=detailPre&farmId=${farm.farmId}" target="content">详情</a>&nbsp;
					<a href="#" onclick="isDelete('FarmMes','farmId','${farm.farmId}');">删除</a></br>
					<a href="${pageContext.request.contextPath}/FarmMesServlet?action=updatePre&farmId=${farm.farmId}&currentPage=${currentPage}" target="content">修改</a>								
				</td>	
			</tr>		
			</c:forEach>			
				<input type="hidden" name="address" id="address" value="${admin.address}"  />
				<!--***其中通过需要添加权限-->
			</form>
			<form action="${URL}" method="post" name="form3">
			<tr>
				<td style="text-align:left;width:20px;">
					<input type="button" id="delete" name="delete" value="删除" onload="del();" style="background-color:#00A882;
						border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;" 
						onclick="goDeleteAll();"/>
				</td>
				<td colspan="5" style="align:right">
					<div class="yema">
						<input type="button" value="上一页" 
							onclick="go(<%=currentPage-1%>)" <%=currentPage==1?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<input type="button" value="下一页" 
							onclick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
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
			<input type="hidden" name = "address" id = "address" value="${admin.address}"/>
			<input type="hidden" name = "action" id = "action" value="list"/>
			<input type="hidden" name = "name" id = "name" value="${name }" />
			<input type="hidden" name = "date1" id = "date1" value="${date1 }" />
			<input type="hidden" name = "date2" id = "date2" value="${date2 }" />
			<input type="hidden" name = "area" id = "area" value="${area }" />
		</form>
	</table>
	</div>
</div>
</body>
</c:when>
<c:otherwise>
	<script language="javascript">
		window.location = "${pageContext.request.contextPath}/FarmMesServlet?action=list&address=${admin.address}";
	</script>
</c:otherwise>
</c:choose>
</html>
