<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model2_meterialInsert_checkContent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model2_metarilDetail.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ad_model2_updateDetail.css" type="text/css"/>
	<script type="text/javascript">
		function setTrBgColor(tableid, color1, color2) {
			var tab = document.getElementById(tableid);
			for (var i = 0; i < tab.rows.length; i++) {
				tab.rows[i].style.backgroundColor = (i % 2 == 0) ? color1 : color2;
			}
		}
		window.onload = function() {
			setTrBgColor("contentTable", "#f0f0f0", "white");
		}
		function go(number){
			document.search.currentPage_D.value = number;
			document.search.submit() ;	// 表单提交
		}
	</script>
</head>
<%
	int currentPage = 1 ;	// 为当前所在的页，默认在第1页
	int lineSize = 11 ;		// 每次显示的记录数
	long allRecorders = 0 ;	// 表示全部的记录数
	long pageSize = 1 ;		// 表示全部的页数（尾页）
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
	<!--弹出修改div-->
	<div id="MyDiv2" class="white_content">
		<iframe id="sup_update"  width="99%" height="97%"></iframe>
	</div>
	<!--弹出结束-->

	<!--弹出添加div-->
	<div id="MyDiv" class="white_content">
		<iframe id="sup_insert" width="99%" height="97%"></iframe>
	</div>
	<!--弹出结束-->
	<!--弹出层时背景层DIV-->
	<div id="fade" class="black_overlay">
	</div>

	<div>
		<span style="font-family: 黑体; font-size: 14px; white-space: nowrap;margin-right: 20px;">${address }</span>
		<span>
			<input name="addBtn" id="addBtn" onclick="ShowInsert('MyDiv','fade','','${reserveId}','${address}')"  style="border-radius: 2px; border: 0px currentColor; border-image: none; width: 120px; height: 17px;
			color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="button"
			value="添加物资信息"/>
		</span>

		<form id="search" name="search" action="SuppliesServlet" method="post">
			<table id="searchTable">
				<tr>
					<td>物资编号</td>
					<td>
						<input type="text" id="detail_number" name="detail_number" value="${detail_number }"/>
					</td>
					<td>物资名称</td>
					<td>
						<input type="text" id="detail_name" name="detail_name"/>
					</td>
					<td>
						生产日期
					</td>
					<td style="width:170px;">
						<input type="text" id="detail_start" name="detail_start" style="width:100px;" onclick="laydate()"
						readonly="readonly" value="${detail_start }"/>
						<img width="16" height="16" src="${pageContext.request.contextPath}/image/calendar.png"
						style="margin:0px 0 0 -25px;vertical-align: middle"/>
						至
						<input type="text" id="detail_end" name="detail_end" style="width:100px;" onclick="laydate()"
						readonly="readonly" value="${detail_end }"/>
						<img width="16" height="16" src="${pageContext.request.contextPath}/image/calendar.png"
						style="margin:0px 0 0 -25px;vertical-align: middle"/>
					</td>
					<td>是否失效</td>
					<td style="width: 60px;vertical-align: middle">
						<input type="radio" name="choose" id="choose1" value="是" ${choose=='是'?'choose':'' } 
						style="width:13px;height: 15px;line-height: 15px;vertical-align: middle;"/>是
						<input type="radio" name="choose" id="choose2" value="否" checked="checked"
						style="width:13px;height: 15px;line-height: 15px;vertical-align: middle;"/>否
					</td>
					<td style="width: 60px;">
					
					
					<button style="border-radius: 6px; border: 0px currentColor; border-image: none; width: 80px; height: 25px; color: white; font-size: 14px;
						background-color: rgb(18, 166, 134);" type="submit" value="查询">查询&nbsp;<img width="15"
						height="15"
						style="vertical-align: middle;"
						src="${pageContext.request.contextPath}/image/search.png"/>
					</button>
				</td>
				<td style="width: 60px;">
					<input name="clearBtn" id="clearBtn" style="border-radius: 6px; border: 0px currentColor; border-image: none; width: 80px; height: 25px;
					color: white; font-size: 14px; background-color: rgb(18, 166, 134);"
					onclick="clearSearchCondition()" type="button" value="清除条件"/>
				</td>
			</tr>
		</table>
			<input type="hidden" name = "action" id = "action" value="detail_list"/>	
			<input type="hidden" name = "currentPage_D" id="currentPage_D" value="${currentPage}"/>
			<input type="hidden" name="supAddress" id="supAddress" value="${address }"/>
			<input type="hidden" name="reserveId" id="reserveId" value="${reserveId }"/>
	</form>
</div>
<div id="content">
	<table id="contentTable">
		<tr>
			<td>
				物资编号
			</td>
			<td>
				物资名称
			</td>
			<td>
				价格
			</td>
			<td>
				生产日期
			</td>
			<td>
				保质期
			</td>
			<td>
				数量
			</td>
			<td style="width:50px;">
				是否失效
			</td>
			<td style="width:330px;">
				物资生产商
			</td>
			<td>
				操作
			</td>

		</tr>
				<!--最多可放14行-->
		<c:forEach items="${items_list }" var="sup">
		<tr id="tr_${sup.suppliesId }">
			<td>${sup.suppliesId }</td>
			<td>${sup.suppliesName }</td>
			<td>${sup.suppliesPrice }</td>
			<td>${sup.productDate }</td>
			<td>${sup.validDate }</td>
			<td>${sup.number }</td>
			<td>${sup.failSitution }</td>
			<td>${sup.producter }</td>
			<td>
				<a href="javascript:void(0)" id="detail_update" onclick="ShowUpdate('MyDiv2','fade','${sup.suppliesId}','${reserveId}','${address}','${currentPage}')" >修改</a>
				<a href="javascript:void(0)" id="detail_delete" onclick="isDeleteSupMes('Supplies','suppliesId','${sup.suppliesId }')">删除</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="9">
			<div class="yema">
				<input type="button" value="上一页" onclick="go(<%=currentPage-1%>)" <%=currentPage==1?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
				<input type="button" value="下一页" onclick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
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
</div>
</body>
</html>