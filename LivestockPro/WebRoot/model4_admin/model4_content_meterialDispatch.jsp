<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_clearCondition.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
	<script src="${pageContext.request.contextPath}/js/user_model2_checkbox.js" type="text/javascript"></script>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		table{
			width: 100%;
			height: 25px;
			margin: 0;
			border-collapse: collapse;
			table-layout: fixed;
		}
		table tr td{
			width: 70px;
			height: 100%;
			font-family: "宋体";
			font-size: 14px;
			color:black;
		}
		#search{
			margin: 2px 0 2px 0;
		}
		#search input{
			width: 90%;
			height: 80%;
			border: 1px solid #00A782;
		}
		.content{
			width: 99%;
			height: 98%;
			margin-left: 1px;
			overflow-y: auto;
			border: 1px solid #00A782;
		}
		.content tr td{
			height: 30px;
			text-align: center;
			word-wrap:break-word;
			padding:1px
		}
		#content tr td input{
			height: 15px;
		}
		#content tr td a{
			font-family: "宋体";
			color: black;
			font-size: 12px;
		}
		a:link{
			text-decoration: none;
			font-family: "黑体";

		}
		a:visited{
			text-decoration: none;
			font-family: "黑体";
		}
		a:hover{
			text-decoration: underline;
			font-family: "黑体";
		}
		.yema{
			width: 270px;
			text-align: right;
			float: right;
			margin-right: 10px;
			word-spacing: 2px;
		}
		
	</style>
	<script type="text/javascript">
	$(function(){
		$("#choose1").click(function(){
			$result = window.confirm("物资确定已到达？");
			if($result==true){
				$(this).css("display","none");
				$("#choose2").css("display","inline");
			}
		});
	});
	function setTrBgColor(tableid, color1, color2) {
			var tab = document.getElementById(tableid);
			for (var i = 0; i < tab.rows.length; i++) {
				tab.rows[i].style.backgroundColor = (i % 2 == 0) ? color1 : color2;
			}
		}
		window.onload = function() {
			setTrBgColor("content", "#f0f0f0", "white");
		};
	function go(num){
	    document.getElementById("currentPage").value = num;
	    document.getElementById("searchForm").submit() ;	// 表单提交
	}
	</script>
</head>
<%
	int currentPage = 1; // 为当前所在的页，默认在第1页
	int lineSize = 11; // 每次显示的记录数
	long allRecorders = 0; // 表示全部的记录数
	long pageSize = 1; // 表示全部的页数（尾页）
%>
<%
	try {
		currentPage = (Integer) (request.getAttribute("currentPage"));
	} catch (Exception e) {
	}
	try {
		lineSize = (Integer) (request.getAttribute("lineSize"));
	} catch (Exception e) {
	}
	try {
		allRecorders = (Integer) (request.getAttribute("allRecorders"));
	} catch (Exception e) {
	}
%>
<%
	pageSize = (allRecorders + lineSize - 1) / lineSize;
	if (pageSize == 0) {
		pageSize = 1;
	}
%>
<body>
<c:choose>
	<c:when test="${list!=null }">
	<form id="searchForm" action="${pageContext.request.contextPath }/SuppliesServlet" method="post">
		<table id="search">
			<tr>
				<td style="width:50px;">
					物资编号:
				</td>
				<td>
					<input type="text" value="${supId }" name="supId" id="meterial_number" />
				</td>
				<td style="width:50px;">
					物资名称:
				</td>
				<td>
					<input type="text" value="${supName }" name="supName" id="meterial_name" />
				</td>
				<td style="width:50px;">
					调度日期:
				</td>
				<td>
					<input type="text" id="date" onclick="laydate()" value="${date }" readonly="readonly" />
					<img width="16" height="16" style="margin: 0px 0px 0px -25px; vertical-align: middle;" src="${pageContext.request.contextPath}/image/calendar.png">
				</td>
				

				<td style="width:40px;">
					<button style="border-radius: 4px; border: 0px currentColor; border-image: none;width:90%;  height: 25px; color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="submit" value="查询">查询&nbsp;<img width="15" height="15" style="vertical-align: middle;" src="${pageContext.request.contextPath}/image/search.png"></button>
				</td>
				<td style="width:40px;">
					<input type="button" value="清空" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn" style="width:90%;height:25px;border-radius:4px;color:white;border:0px;font-size:14px;background-color:#12a686;">
				</td>
			</tr>
		</table>
		<input type="hidden" name = "address" id = "address" value="${admin.address}"/>
		<input type="hidden" name = "action" id = "action" value="supItemDis_list"/>	
		<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>
	</form>
	<div class="content">
		<form id="form_content" action="" method="post" name="form_content">
			<table id="content">
				<tr>
					<td style="width:10px">
						<input type="checkbox" id="checkAll" name="checkAll" onclick="CheckAll()" />
					</td>
					<td style="width:30px">物资编号</td>
					<td style="width:30px">物资名称</td>
					<td>储备点位置</td>
					<td style="width:30px">调度日期</td>
					<td style="width:30px">调度数量</td>
					
					<td>目标位置</td>
					<td style="width:25px">操作</td>
				</tr>
				<!--1366分辨率最多14条记录-->
				<c:forEach items="${list }" var="dis">
					<tr id="tr_${dis.id }">
						<td style="width:15px">
							<input type="checkbox" id="check" name="check" value="${dis.id }" onclick="selectIt(4)" />
						</td>
						<td>${dis.item.suppliesId }</td>
						<td>${dis.item.suppliesName }</td>
						<td>${dis.item.supplies.address }</td>
						<td>${dis.date }</td>
						<td>${dis.number }</td>
						
						<td>${dis.targerAddress }</td>
						<td>
							<a href="javascript:void(0)" onclick="isDeleteSupItemDisMes('Supplies','id','${dis.id }')">删除</a><br/>
							<c:choose>
								<c:when test="${dis.isArrive==false }">
									<a href="javascript:void(0)" id="choose1_${dis.id }" onclick="changeIsArrive('choose1_${dis.id }','choose2_${dis.id }','${dis.id }','${admin.address }')">是否到达</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" id="choose2_${dis.id }" style="display: display;color:gray">已到达</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0)" id="choose2_${dis.id }" style="display: none;color:gray">已到达</a>
						</td>
					</tr>
				</c:forEach>
				

					<tr>
						<td colspan="2" style="text-align: left">
							<input onclick="goDeleteAll();" style="display: none;border-radius: 2px; border: 0px currentColor; border-image: none;width:70px;  height: 25px; margin-left: 5px; color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="button" value="删除所选"  id="btn_del" />
						</td>
						<td colspan="6">
							<div class="yema">
								<input type="button" value="上一页"
											onclick="go(<%=currentPage - 1%>)"
											<%=currentPage == 1
							? "DISABLED style='background-color:#83cdbe;width:51px;height:22px;border:0px;'"
							: "style='background-color:#00A882;width:51px;height:22px;border:0px;'"%> />
										<input type="button" value="下一页"
											onclick="go(<%=currentPage + 1%>)"
											<%=currentPage == pageSize
							? "DISABLED style='background-color:#83cdbe;width:51px;height:22px;border:0px;'"
							: "style='background-color:#00A882;width:51px;height:22px;border:0px;'"%> />
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
										<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
										<select name="page"
											onchange="go(this.value)">
											<%
												for (int x = 1; x <= pageSize; x++) {
											%>
											<option value="<%=x%>"
												<%=x == currentPage ? "SELECTED" : ""%>><%=x%></option>
											<%
												}
											%>
										</select> <font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
									</div>
                        </td>
					</tr>
				</table>
			</form>
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			window.location = "${pageContext.request.contextPath }/SuppliesServlet?action=supItemDis_list&address=${admin.address}";
		</script>
	</c:otherwise>
</c:choose>
	</body>
	<script type="text/javascript">
	function goDeleteAll() {
		var select = document.getElementsByName("check");
		var count = 0;
		for(var i = 0 ;i<select.length;i++) {
			if(select[i].checked == true) {
				count = count + 1;
			};
		}
		if(window.confirm("确定删除这"+count+"项物资调度信息？")) {
			document.form_content.action = "SuppliesServlet?action=supItemDis_deleteAll";
			document.form_content.submit() ;
		};
	};
	if("${info}" != ""){
		alert("${info}");
	}
	</script>
	</html>
