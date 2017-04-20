<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--这是放置右边内容-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--这是放置右边内容-->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_check.css"/>
	<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"></link>
	<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_insertData.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_checkbox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_setTrBgColor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_detail.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_update.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_changeLiBgColor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_insertData.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_updateData.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_details.js"></script><!--新增-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
<!--新增-->
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
		<c:when test="${flag!=null}">
			<!--弹出的新建窗口-->
			<div id="dialog_animalA" style="display:none" title="动物A信息添加">
				<iframe id="iframe_animalA" src="model3_admin/insert_animalA.jsp"
					width="99%" height="99%"></iframe>
			</div>
			<div id="dialog_animalB" style="display:none" title="动物B信息添加">
				<iframe id="iframe_animalB" src="model3_admin/insert_animalB.jsp"
					width="99%" height="99%"></iframe>
			</div>
			<div id="dialog_productA" style="display:none" title="产品A信息添加">
				<iframe id="iframe_productA" src="model3_admin/insert_productA.jsp"
					width="99%" height="99%"></iframe>
			</div>
			<div id="dialog_productB" style="display:none" title="产品B信息添加">
				<iframe id="iframe_productB" src="model3_admin/insert_productB.jsp"
					width="99%" height="99%"></iframe>
			</div>

			<!--弹出的更改窗口-->
			<div id="update_animalA" style="display:none" title="动物A信息更改">
				<iframe id="iframe_animalAUpdate" width="99%" height="99%"></iframe>
			</div>
			<div id="update_animalB" style="display:none" title="动物B信息更改">
				<iframe id="iframe_animalBUpdate"width="99%" height="99%"></iframe>
			</div>
			<div id="update_productA" style="display:none" title="产品A信息更改">
				<iframe id="iframe_productAUpdate" width="99%" height="99%"></iframe>
			</div>
			<div id="update_productB" style="display:none" title="产品B信息更改">
				<iframe id="iframe_productBUpdate" width="99%" height="99%"></iframe>
			</div>

			<!--新增-->
			<!--弹出详情窗口-->
			<div id="detail_animalA" style="display:none" title="动物A详细信息">
				<iframe id="iframe_animalADetail" width="99%" height="99%"></iframe>
			</div>
			<div id="detail_animalB" style="display:none" title="动物B详细信息">
				<iframe id="iframe_animalBDetail" width="99%" height="99%"></iframe>
			</div>
			<div id="detail_productA" style="display:none" title="产品A详细信息">
				<iframe id="iframe_productADetail"width="99%" height="99%"></iframe>
			</div>
			<div id="detail_productB" style="display:none" title="产品B详细信息">
				<iframe id="iframe_productBDetail"width="99%" height="99%"></iframe>
			</div>

			<div class="content">
				<div class="address">
					<font style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;检疫信息管理</a></font>
				</div>

				<div class="content_banner">
					<font>查看</font>
				</div>

				<div class="search">
					<form action="AnimalAServlet"  method="post"name="search" id="search" style="height:60px;">
						<table id="aa" name="aa">
							<tr>
								<td style="width:20px;">编号:</td>
								<td style="text-align: left;width: 50px;"><input
									type="text" id="number" name="number" value="${number }" /></td>
								<td style="width:20px;">货主:</td>
								<td style="text-align: left;width: 50px;"><input
									type="text" id="owner" name="owner" value="${owner }" /></td>
								<td style="width:35px;">签发日期:</td>
								<td style="text-align: left;width: 120px;"><input
									type="text" id="start_date" name="start_date"
									value="${start_date }" style="width:100px;" onclick="laydate()"
									readonly="readonly" /> <img width="16" height="16"
									src="${pageContext.request.contextPath}/image/calendar.png"
									style="margin:0px 0 0 -25px;vertical-align: middle" /> 至 <input
									type="text" id="end_date" name="end_date" value="${end_date}"
									style="width:100px;" onclick="laydate()" readonly="readonly" />
									<img width="16" height="16"
									src="${pageContext.request.contextPath}/image/calendar.png"
									style="margin:0px 0 0 -25px;vertical-align: middle" /></td>

								<td style="width:30px;"><label id="product" name="product">动物名称</label>
								</td>
								<td style="text-align: left;width: 50px;"><input
									type="text" id="animal_name" name="animal_name"
									value="${animal_name }" /></td>
								<td style="width:130px;">运载方式: <input type="checkbox"
									id="highway" name="way" value="公路"
									${fn:contains(way,"公路")?'checked':'' }
									style="width:13px;height:13px;vertical-align: top;" />公路 <input
									type="checkbox" id="waterway" name="way" value="水路"
									${fn:contains(way,"水路")?'checked':'' }
									style="width:13px;height:13px;vertical-align: top;" />水路 <input
									type="checkbox" id="railway" name="way" value="铁路"
									${fn:contains(way,"铁路")?'checked':'' }
									style="width:13px;height:13px;vertical-align: top;" />铁路 <input
									type="checkbox" id="aviation" name="way" value="航空"
									${fn:contains(way,"航空")?'checked':'' }
									style="width:13px;height:13px;vertical-align: top;" />航空

								</td>

								<td style="width:60px;">
									<button type="submit" value="查询"
										style="width:80px;height:25px;border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;">
										查询&nbsp;<img
											src="${pageContext.request.contextPath}/image/search.png"
											style="vertical-align: middle;" width="15px" height="15px" />
									</button>
								</td>
								<td style="width:60px;text-align: left"><input
									type="button" value="清除条件" id="clearBtn"
									onclick="clearSearchCondition();" name="clearBtn"
									style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;" />
								</td>
							</tr>
						</table>
						<input type="hidden" name="action" id="action" value="list" /> <input
							type="hidden" name="address" id="address"
							value="${admin.address }" /> <input type="hidden"
							name="currentPage" id="currentPage" value="1" />
					</form>
				</div>
				<!--四个选项-->
				<table id="UL" name="UL">
					<tr>
						<ul id="myUL" name="myUL">
							<td>
								<li style="background-color:white;" id="animal_A"
								name="animal_A"><a href="${pageContext.request.contextPath }/AnimalAServlet?action=list&address=${admin.address }">
								<span style="color:#00A782;">动物A</span></a></li>
							</td>
							<td>
								<li style="background-color:#00A782;" id="animal_B"
								name="animal_B"><a href="${pageContext.request.contextPath }/AnimalBServlet?action=list&address=${admin.address }">
								<span>动物B</span></a>
							</li>
							</td>
							<td>
								<li style="background-color:#00A782;" id="product_A"
								name="product_A"><a href="${pageContext.request.contextPath }/ProductAServlet?action=list&address=${admin.address }">
								<span>产品A</span></a>
							</li>
							</td>
							<td>
								<li style="background-color:#00A782;" id="product_B"
								name="product_B"><a href="${pageContext.request.contextPath }/ProductBServlet?action=list&address=${admin.address }">
								<span>产品B</span></a>
							</li>
							</td>

						</ul>
					</tr>
				</table>

				<!--2015.5.11新增div设置滚动条-->
				<div style="width:100%;height:70%;overflow-y:auto;">
					<!--放入内容-->
					<form action="AnimalAServlet" method="post" id="form_animalA"
						name="form_animalA">
						<table class="content_table" id="animalA" style="display:true;">
							<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
							<tr style="postision：relative;z-index:1">
								<td style="width:20px;"><input type="checkbox"
									name="checkAll" id="checkAll"
									style="margin-left:10px;margin-right:10px;"
									onclick="CheckAll('animalA')" /></td>
								<td style="color:black;width:64px;">编号</td>
								<td style="color:black;width:46px;text-align:left;">货主</td>
								<td style="color:black;width:84px;">货主电话</td>
								<td style="color:black;width:54px;">签发日期</td>
								<td style="color:black;width:76px;text-align:left;">动物种类</td>
								<td style="color:black;width:184px;text-align:left;">起运地点</td>
								<td style="color:black;width:184px;text-align:left;">到达地点</td>
								<td style="color:black;width:60px;text-align:left;">运载方式</td>
								<td style="color:black;width:64px;">操作</td>
							</tr>
							<c:forEach items="${list }" var="aa">
								<tr id="tr_${aa.id }">
									<td style="text-align:left;width:20px;"><input
										type="checkbox" onclick="selectIt('animalA');" id="check" value="${aa.id }"
										name="check" style="margin-left:10px;margin-right:10px;" /></td>
									<td>${aa.id }</td>
									<td style="text-align:left;">${aa.shipperName }</td>
									<td>${aa.phoneNum }</td>
									<td>${aa.date }</td>
									<td style="text-align:left;">${aa.animalSpecies }</td>
									<td style="text-align:left;">${aa.startAddress }</td>
									<td style="text-align:left;">${aa.destination }</td>
									<td style="text-align:left;">${aa.transportWay }</td>
									<td><a href="javascript:void(0)" id="animalA_detail"
										onclick="gotoDetail('detail_animalA','iframe_animalADetail','${aa.id }','AnimalAServlet');">详情</a>&nbsp;<a
										href="#" onclick="isDeleteMes('AnimalA','id','${aa.id}')">删除</a></br> <a
										href="javascript:void(0)"
										onclick="gotoUpdate('update_animalA','iframe_animalAUpdate','${aa.id }','AnimalAServlet');"
										id="animalA_update">修改</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td style="text-align:left;width:20px;"><input
									type="button" id="btn_animalA" name="btn_animalA" value="删除"
									onload="del();" onclick="gotoDeleteAll('AnimalAServlet','form_animalA')"
									style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;" />
								</td>
								<td colspan="9" style="align:right">
									<div class="yema">
										<input type="button" value="添加" id="animalA_insert"
											name="animalA_insert"
											style="background-color:#00A882;border:0px;" /> <input
											type="button" value="上一页"
											onclick="go(<%=currentPage - 1%>,'search','AnimalAServlet')"
											<%=currentPage == 1
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<input type="button" value="下一页"
											onclick="go(<%=currentPage + 1%>,'search','AnimalAServlet')"
											<%=currentPage == pageSize
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
										<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
										<select name="page" onchange="go(this.value,'search','AnimalAServlet')">
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

					<form action="#" method="post" id="form_animalB"
						name="form_animalB">
						<!-- AnimalB -->
						<table class="content_table" id="animalB" style="display:none;">
							<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
							<tr>
								<td style="width:20px;"><input type="checkbox"
									name="checkAll" id="checkAll"
									style="float:left;margin-left:10px;margin-right:10px;"
									onclick="CheckAll('animalB')" /></td>
								<td style="color:black;width:64px;">编号</td>
								<td style="color:black;width:46px;text-align:left;">货主</td>
								<td style="color:black;width:84px;">货主电话</td>
								<td style="color:black;width:54px;">签发日期</td>
								<td style="color:black;width:76px;text-align:left;">动物种类</td>
								<td style="color:black;width:184px;text-align:left;">起运地点</td>
								<td style="color:black;width:184px;text-align:left;">到达地点</td>
								<td style="color:black;width:60px;text-align:left;">用途</td>
								<td style="color:black;width:64px;">操作</td>
							</tr>
							<c:forEach items="${listB }" var="ab">
								<tr id="tr_${ab.id }">
									<td style="text-align:left;width:20px;"><input
										type="checkbox" onclick="selectIt('animalA');" id="check" value="${ab.id }"
										name="check" style="margin-left:10px;margin-right:10px;" /></td>
									<td>${ab.id }</td>
									<td style="text-align:left;">${ab.shipperName }</td>
									<td>${ab.phoneNum }</td>
									<td>${ab.date }</td>
									<td style="text-align:left;">${ab.animalSpecies }</td>
									<td style="text-align:left;">${ab.startAddress }</td>
									<td style="text-align:left;">${ab.destination }</td>
									<td style="text-align:left;">${ab.use }</td>
									<td><a href="javascript:void(0)" id="animalA_detail"
										onclick="gotoDetail('detail_animalB','iframe_animalBDetail','${ab.id }','AnimalBServlet');">详情</a>&nbsp;<a
										href="#" onclick="isDeleteMes('AnimalB','id','${ab.id}')">删除</a></br> <a
										href="javascript:void(0)"
										onclick="gotoUpdate('update_animalB','iframe_animalBUpdate','${ab.id }','AnimalBServlet');"
										id="animalA_update">修改</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td style="text-align:left;width:20px;"><input
									type="button" id="btn_animalA" name="btn_animalA" value="删除"
									onload="del();" onclick="gotoDeleteAll('AnimalBServlet','form_animalB')"
									style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;" />
								</td>
								<td colspan="9" style="align:right">
									<div class="yema">
										<input type="button" value="添加" id="animalB_insert"
											name="animalB_insert"
											style="background-color:#00A882;border:0px;" /> <input
											type="button" value="上一页"
											onclick="go(<%=currentPage - 1%>,'search','AnimalBServlet')"
											<%=currentPage == 1
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<input type="button" value="下一页"
											onclick="go(<%=currentPage + 1%>,'search','AnimalBServlet')"
											<%=currentPage == pageSize
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
										<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
										<select name="page" onchange="go(this.value,'search','AnimalBServlet')">
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

					<form action="#" method="post" id="form_productA"
						name="form_productA">
						<!--productA-->
						<table class="content_table" id="productA" style="display:none;">
							<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
							<tr>
								<td style="width:20px;"><input type="checkbox"
									name="checkAll" id="checkAll"
									style="float:left;margin-left:10px;margin-right:10px;"
									onclick="CheckAll('productA')" /></td>
								<td style="color:black;width:64px;">编号</td>
								<td style="color:black;width:46px;text-align:left;">货主</td>
								<td style="color:black;width:84px;">货主电话</td>
								<td style="color:black;width:54px;">签发日期</td>
								<td style="color:black;width:76px;text-align:left;">产品名称</td>
								<td style="color:black;width:184px;text-align:left;">生产单位地址</td>
								<td style="color:black;width:184px;text-align:left;">目的地</td>
								<td style="color:black;width:60px;text-align:left;">运载方式</td>
								<td style="color:black;width:64px;">操作</td>
							</tr>
							<c:forEach items="${listC }" var="pa">
								<tr id="tr_${pa.id }">
									<td style="text-align:left;width:20px;"><input
										type="checkbox" onclick="selectIt('animalA');" id="check" value="${pa.id }"
										name="check" style="margin-left:10px;margin-right:10px;" /></td>
									<td>${pa.id }</td>
									<td style="text-align:left;">${pa.shipperName }</td>
									<td>${pa.phoneNum }</td>
									<td>${pa.date }</td>
									<td style="text-align:left;">${pa.productName }</td>
									<td style="text-align:left;">${pa.addressName }</td>
									<td style="text-align:left;">${pa.destination }</td>
									<td style="text-align:left;">${pa.transportWay }</td>
									<td><a href="javascript:void(0)" id="animalA_detail"
										onclick="gotoDetail('detail_productA','iframe_productADetail','${pa.id }','ProductAServlet');">详情</a>&nbsp;<a
										href="#" onclick="isDeleteMes('ProductA','id','${pa.id}')">删除</a></br> <a
										href="javascript:void(0)"
										onclick="gotoUpdate('update_productA','iframe_productAUpdate','${pa.id }','ProductAServlet');"
										id="animalA_update">修改</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td style="text-align:left;width:20px;"><input
									type="button" id="btn_animalA" name="btn_animalA" value="删除"
									onload="del();" onclick="gotoDeleteAll('ProductAServlet','form_productA')"
									style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;" />
								</td>
								<td colspan="9" style="align:right">
									<div class="yema">
										<input type="button" value="添加" id="productA_insert"
											name="animalA_insert"
											style="background-color:#00A882;border:0px;" /> <input
											type="button" value="上一页"
											onclick="go(<%=currentPage - 1%>,'search','ProductAServlet')"
											<%=currentPage == 1
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<input type="button" value="下一页"
											onclick="go(<%=currentPage + 1%>,'search','ProductAServlet')"
											<%=currentPage == pageSize
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
										<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
										<select name="page" onchange="go(this.value,'search','ProductAServlet')">
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

					<form action="#" method="post" id="form_productB" name="form_productB">
						<!--productB-->
						<table class="content_table" id="productB" style="display:none;">
							<!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
							<tr>
								<td style="width:20px;"><input type="checkbox"
									name="checkAll" id="checkAll"
									style="float:left;margin-left:10px;margin-right:10px;"
									onclick="CheckAll('productB')" /></td>
								<td style="color:black;width:64px;">编号</td>
								<td style="color:black;width:46px;text-align:left;">货主</td>
								<td style="color:black;width:84px;">产品名称</td>
								<td style="color:black;width:54px;">签发日期</td>
								<td style="color:black;width:76px;text-align:left;">检疫标识号</td>
								<td style="color:black;width:184px;text-align:left;">生产单位地址</td>
								<td style="color:black;width:184px;text-align:left;">目的地</td>
								<td style="color:black;width:60px;text-align:left;">数量及单位</td>
								<td style="color:black;width:64px;">操作</td>
							</tr>
							<c:forEach items="${listD }" var="pb">
								<tr id="tr_${pb.id }">
									<td style="text-align:left;width:20px;"><input
										type="checkbox" onclick="selectIt('animalA');" id="check" value="${pb.id }"
										name="check" style="margin-left:10px;margin-right:10px;" /></td>
									<td>${pb.id }</td>
									<td style="text-align:left;">${pb.shipperName }</td>
									<td>${pb.productName }</td>
									<td>${pb.date }</td>
									<td style="text-align:left;">${pb.quarantinemarks }</td>
									<td style="text-align:left;">${pb.addressName }</td>
									<td style="text-align:left;">${pb.destination }</td>
									<td style="text-align:left;">${pb.number }</td>
									<td><a href="javascript:void(0)" id="animalA_detail"
										onclick="gotoDetail('detail_productB','iframe_productBDetail','${pb.id }','ProductBServlet');">详情</a>&nbsp;<a
										href="#" onclick="isDeleteMes('ProductB','id','${pb.id}')">删除</a></br> <a
										href="javascript:void(0)"
										onclick="gotoUpdate('update_productB','iframe_productBUpdate','${pb.id }','ProductBServlet');"
										id="animalA_update">修改</a></td>
								</tr>
							</c:forEach>
							<tr>
								<td style="text-align:left;width:20px;"><input
									type="button" id="btn_animalA" name="btn_animalA" value="删除" onclick="gotoDeleteAll('ProductBServlet','form_productB')"
									style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;" />
								</td>
								<td colspan="9" style="align:right">
									<div class="yema">
										<input type="button" value="添加" id="productB_insert"
											name="animalA_insert"
											style="background-color:#00A882;border:0px;" /> <input
											type="button" value="上一页"
											onclick="go(<%=currentPage - 1%>,'search','ProductBServlet')"
											<%=currentPage == 1
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<input type="button" value="下一页"
											onclick="go(<%=currentPage + 1%>,'search','ProductBServlet')"
											<%=currentPage == pageSize
							? "DISABLED style='background-color:#83cdbe;border:0px;'"
							: "style='background-color:#00A882;border:0px;'"%> />
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
										<label><%=pageSize%></label>
										<!--总页数为表格数据量-->
										<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
										<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
										<select name="page" onchange="go(this.value,'search','ProductBServlet')">
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
							<!-- </form> -->
						</table>
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
	<script language="javascript">
		window.location = "${pageContext.request.contextPath}/AnimalAServlet?action=list&address=${admin.address }";
	</script>
		</c:otherwise>
	</c:choose>
	<script language="javascript">
	function gotoDetail(tragetId,iframeId,paraId,sub_form) {
			$( "#"+tragetId ).dialog( "open" );
			$("#"+iframeId).attr("src",sub_form+"?action=detail&id="+paraId) ;
	}
	function gotoUpdate(tragetId,iframeId,paraId,sub_form) {
		$( "#"+tragetId ).dialog( "open" );
		$("#"+iframeId).attr("src",sub_form+"?action=updatePre&id="+paraId+"&currentPage=${currentPage}") ;
	}
	function go(num,formId,sub_form){
		document.getElementById(formId).currentPage.value = num;
		document.getElementById(formId).action = sub_form;
		document.getElementById(formId).submit() ;	// 表单提交
	}
	function gotoDeleteAll(sub_form,formId) {
		if(window.confirm("确定批量删除相关信息？")){
			document.getElementById(formId).action=sub_form+"?action=deleteAll";
			document.getElementById(formId).submit();
		}
	}
	if("${flag}" == "1"){
		changeLiBgColor('animal_A');
		showTable('animalA');
		document.getElementById("search").action = "AnimalAServlet";
	} else if ("${flag}" == "2") {
		changeLiBgColor('animal_B');
		showTable('animalB');
		document.getElementById("search").action = "AnimalBServlet";
	} else if ("${flag}" == "3") {
		changeLiBgColor('product_A');
		showTable('productA');
		document.getElementById("search").action = "ProductAServlet";
	} else if ("${flag}" == "4") {
		changeLiBgColor('product_B');
		showTable('productB');
		document.getElementById("search").action = "ProductBServlet";
	} 
</script>
</body>

</html>