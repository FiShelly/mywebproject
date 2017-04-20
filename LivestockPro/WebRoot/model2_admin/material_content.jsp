<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
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
<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model2_insertData.js"></script>

<script language="javascript">
	
	function go(num){
		document.search.currentPage.value = num;
		document.search.submit() ;	// 表单提交
	}
	
	function goDeleteAll() {
		var select = document.getElementsByName("check");
		var count = 0;
		for(var i = 0 ;i<select.length;i++) {
			if(select[i].checked == true) {
				count = count + 1;
			}
		}
		if(window.confirm("确定删除这"+count+"项物资信息？")) {
			document.form2.action = "SuppliesServlet?action=deleteAll&address=${admin.address}";
			document.form2.submit() ;
		}
	}
	if("${info}" != ""){
	 	alert("${info}");
	}
	function openNewWin(id,action,supAddress){
		if(action == 'detail'){
			$( "#dialog_detail" ).dialog( "open" );
			$("#iframe_detail").attr("src","SuppliesServlet?action=detail_list&choose=否&reserveId="+id+"&supAddress="+supAddress) ;
		} else {
			$( "#dialog_update" ).dialog( "open" );
			$("#iframe_update").attr("src","SuppliesServlet?action=updatePre&currentPage=${currentPage}&reserveId="+id) ;
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
	pageSize = (allRecorders + lineSize -1) / lineSize ;
	if(pageSize == 0){
		pageSize = 1 ;
	}
%>
<body>
<c:choose>
<c:when test="${all!=null}">
<!--弹出的新建窗口-->
	<div id="dialog_insert" style="display:none"  title="疫情物资储备点添加">
		<iframe id="iframe_insert" src="model2_admin/material_insert_update.jsp" width="99%" height="99%"></iframe>
	</div>

	<div id="dialog_update" style="display:none"  title="疫情物资储备点信息更改">
		<iframe id="iframe_update"  width="99%" height="99%"></iframe>
	</div>

	<div id="dialog_detail" style="display:none"  title="疫情物资信息详情">
		<iframe id="iframe_detail" width="99%" height="99%"></iframe>
	</div>
<div class="content">
	<div class="address">
		<font style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;重大疫情物资储备管理</font>
	</div>
	
	<div class="content_banner">
		<font>查看</font>
	</div>
	
	<div class="search">
		<form action="${URL}" method="post" name="search" id="search" style="height:60px;">
			<div style="margin-left:10px;">
				储备点:<input type="text" id="name" name="name" value="${supAddress }"/>
			</div>
			<div style="margin-left:10px;">
				负责人:<input type="text" id="person" name="person" value="${name }"/>
			</div>
			<div>
				注册时间&nbsp;<input type="text" id="date1" name="date1" readonly="readonly" onclick="laydate()" value="${date1 }"/>
			</div>

			<div style="margin-left:-40px;margin-top:3px;">
				<img src="${pageContext.request.contextPath}/image/calendar.png" height="16px" width="16px" />
			</div>
			<div style="margin-left:-17px;">
				至<input type="text" id="date2" name="date2"  value="${date2 }" readonly="readonly" onclick="laydate()" style="margin-left:3px;" />
			</div>
			<div style="margin-left:-40px;margin-top:3px;">
				<img src="${pageContext.request.contextPath}/image/calendar.png" height="16px" width="16px"/>
			</div>
			
			<div id="specialDiv">
				<input type="submit" value="查询" id="searchBtn" name="clearBtn" style="width:80px;height:25px;border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;"/>
				<img src="${pageContext.request.contextPath}/image/search.png" width="15px" height="15px" style="margin-left:-25px;margin-right:20px;margin-top:-3px;"/>
				<!-- <button type="button" value="查询" style="width:80px;height:25px;border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;" >查询&nbsp;<img src="${pageContext.request.contextPath}/image/search.png" width="15px" height="15px"/></button> -->
				<input type="button" value="清除条件" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn"  style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;"/>
			</div>
			<input type="hidden" name = "address" id = "address" value="${admin.address}"/>
			<input type="hidden" name = "action" id = "action" value="list"/>	
			<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>

		</form>
	</div>
	<!--2015.5.11新增div设置滚动条-->
	<div style="width:100%;height:77%;overflow-y:auto;">
	<!--放入内容-->
	<form action="${URL }" method="post" id="form2" name="form2">
		
		<table id="table" class="content_table" >
			<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
			<tr>
				<td style="width:3%;"><input type="checkbox" name="checkAll" id="checkAll" style="float:left;margin-left:10px;margin-right:10px;" onclick="CheckAll()" /></td>
				<td style="color:black;width:6%;">储备点编号</td>
				<td style="color:black;width:6%;">储备点名称</td>
				<td style="color:black;width:7%;">负责人</td>
				<td style="color:black;width:10%;">联系电话</td>
				<td style="color:black;width:15%;text-align:left;">储备点地址</td>
				<td style="color:black;width:15%">管理单位</td>
				<td style="color:black;width:7%;">注册时间</td>
				<td style="color:black;width:7%''">职位</td>
				<td style="color:black;">操作</td>
			</tr>
			
			<c:forEach items="${all }" var="sup">
			<tr id="tr_${sup.reserveId }">
				<td style="text-align:left;width:3%;"><input type="checkbox" value="${sup.reserveId }" onclick="selectIt();" id="check" name="check" style="float:left;margin-left:10px;margin-right:10px;"/></td>
				<td>${sup.reserveId }</td>
				<td>${sup.name }</td>
				<td>${sup.head }</td>
				<td>${sup.phoneNum }</td>
				<td style="text-align:left;">${sup.address }</td>
				<td>${sup.managementstation }</td>
				<td>${sup.registDate }</td>
				<td>${sup.position }</td>
				<td><a href="#" id="material_detail" onclick="openNewWin('${sup.reserveId }','detail','${sup.address }')">物资详情</a><br/>
					<a href="#" onclick="isDeleteMes('Supplies','reserveId','${sup.reserveId }')">删除</a>&nbsp;
					<a href="javascript:void(0)" id="material_update" onclick="openNewWin('${sup.reserveId }','update')">修改</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td style="text-align:left;width:20px;">
					<input type="button" id="delete" name="delete" value="删除"  onclick="goDeleteAll()"
					 style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;
					 height:22px;color:white;display:none;"/>
				</td>
				<td colspan="9" style="align:right;">
					<div class="yema" >
						<input type="button" value="添加" id="material_insert" name="material_insert" style="background-color:#00A882;border:0px;" />
						<input type="button" value="上一页" 
							onclick="go(<%=currentPage-1%>)" <%=currentPage==1?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<input type="button" value="下一页" 
							onclick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
						<label><%=pageSize %></label><!--总页数为表格数据量-->
						<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
						<select name="page" onchange="go(this.value)" >
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
		</table>
		</form>
	</div>
</div>
</c:when>
<c:otherwise>
	<script language="javascript">
		window.location = "${pageContext.request.contextPath}/SuppliesServlet?action=list&address=${admin.address}";
	</script>
</c:otherwise>
</c:choose>
</body>
</html>