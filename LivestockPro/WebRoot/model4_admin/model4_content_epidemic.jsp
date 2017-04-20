<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!--这是放置右边内容-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_check.css"/>
    <script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_insertData.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model4_checkbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_setTrBgColor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model4_updateData.js"></script>
    
    <script type="text/javascript">
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
    		if(window.confirm("确定删除这"+count+"项上报的疫情信息？")) {
    			document.form_content.action = "EpidemicRecordServlet?action=deleteAll";
    			document.form_content.submit() ;
    		}
    	}
    	if("${info}" != ""){
    	 	alert("${info}");
    	}
    	function openNewWin(id,action){
    		if(action == 'detail'){
    			$( "#detail_div" ).dialog( "open" );
    			$("#iframe_detail").attr("src","EpidemicRecordServlet?action=detail&role=admin&id="+id) ;
    		} else {
    			$( "#update_div" ).dialog( "open" );
    			$("#iframe_update").attr("src","EpidemicRecordServlet?action=updatePre&role=admin&currentPage=${currentPage}&id="+id) ;
    		}
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
<c:when test="${list!=null}">
    <!--弹出的新建窗口-->
    <div id="insert_div" style="display:none"  title="疫情信息添加">
        <iframe id="iframe_insert" src="model4_admin/model4_epidemic_insert.jsp" width="99%" height="99%"></iframe>
    </div>

    <!--弹出的更改窗口-->
    <div id="update_div" style="display:none"  title="疫情信息更改">
        <iframe id="iframe_update"  width="99%" height="99%"></iframe>
    </div>

    <!--新增-->
    <!--弹出详情窗口-->
    <div id="detail_div" style="display:none"  title="疫情详细信息">
        <iframe id="iframe_detail"  width="99%" height="99%"></iframe>
    </div>

    <div class="content">
        <div class="address">
            <font style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;疫情状态变更</font>
        </div>

        <div class="content_banner">
            <font>查看</font>
        </div>

        <div class="search">
            <form action="EpidemicRecordServlet" method="post" name="search" id="search" style="height:60px;">
                <table id="aa" name="aa"> 
                    <tr>
                    <td style="width:40px;">
                        爆发地点:
                    </td>
                    <td style="text-align: left;width: 50px;">
                        <input type="text" id="epidemic_address" value="${epidemic_address }" name="epidemic_address"/>
                    </td>
                    <td style="width:40px;">
                        爆发日期:
                    </td>
                    <td style="text-align: left;width: 120px;" >
                        <input type="text" id="start_date"  value="${start_date }" name="start_date" style="width:100px;" onclick="laydate()" readonly="readonly" />
                        <img width="16" height="16" src="${pageContext.request.contextPath}/image/calendar.png" style="margin:0px 0 0 -25px;vertical-align: middle"/>
                        至
                        <input type="text" id="end_date"  value="${end_date }" name="end_date" style="width:100px;" onclick="laydate()" readonly="readonly"/>
                        <img width="16" height="16" src="${pageContext.request.contextPath}/image/calendar.png" style="margin:0px 0 0 -25px;vertical-align: middle"/>
                    </td>

                    <td style="width:180px;">
                        审核及处理情况:
                        <input type="radio" name="status" value="0"  ${status=='0'?'checked':''} style="width:13px;height:15px;vertical-align: bottom;"/>未审核
                        <input type="radio" name="status" value="1" ${status=='1'?'checked':''} style="width:13px;height:15px;vertical-align: bottom;"  />已审核
                        <input type="radio" name="status" value="2" ${status=='2'?'checked':''} style="width:13px;height:15px;vertical-align: bottom;"/>未处理
                        <input type="radio" name="status" value="3" ${status=='3'?'checked':''} style="width:13px;height:15px;vertical-align: bottom;"  />已处理

                    </td>

                    <td style="width:50px;">
                        <button type="submit" value="查询" style="width:80px;height:25px;border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;">查询&nbsp;<img src="${pageContext.request.contextPath}/image/search.png" style="vertical-align: middle;" width="15px" height="15px"/></button></td>
                        <td style="width:50px;text-align: left"><input type="button" value="清除条件" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn"  style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name = "address" id = "address" value="${admin.address}"/>
				<input type="hidden" name = "action" id = "action" value="admin_list"/>	
				<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>
            </form>
        </div>

        <!--2015.5.11新增div设置滚动条-->
        <div style="width:100%;height:79%;overflow-y:auto;">
            <!--放入内容-->
            <form action="#" method="post" id="form_animalA" name="form_animalA">

                <table class="content_table" id="animalA">
                    <!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
                    <tr>
                        <td style="width:20px;"><input type="checkbox" name="checkAll" id="checkAll" style="margin-left:10px;margin-right:10px;" onclick="CheckAll('animalA')" /></td>
                        <td style="color:black;width:54px;">畜禽标识码</td>
                        <td style="color:black;width:54px;">养殖场名称</td>
                        <td style="color:black;width:46px;text-align:center;">联系人</td>
                        <td style="color:black;width:44px;">联系人电话</td>
                        <td style="color:black;width:74px;">养殖种类</td>
                        <td style="color:black;width:74px;">发病动物种类</td>
                        <td style="color:black;width:54px;text-align:center;">爆发日期</td>
                        <td style="color:black;width:175px;text-align:left;">爆发地点</td>
                        <td style="color:black;width:30px;text-align:center;">审核情况</td>
                        <td style="color:black;width:30px;text-align:center;">处理情况</td>
                        <td style="color:black;width:64px;">操作</td>
                    </tr>
                    <c:forEach items="${list }" var="epi">
                     <tr id="tr_${epi.id }">
                        <td style="width:20px;"><input type="checkbox" onclick="selectIt('animalA');" id="check" name="check" style="margin-left:10px;margin-right:10px;"/></td>
                       	<td>${epi.farm.farmId }</td>
                        <td>${epi.farm.farmName }</td>
                        <td style="text-align:center;">${epi.farm.leader }</td>
                        <td>${epi.farm.phoneNum }</td>
                        <td>${epi.feedSpecies }</td>
                        <td>${epi.sickSpecies }</td>
                        <td style="text-align:center;">${epi.date }</td>
                        <td  style="text-align:left;">${epi.farm.location }</td>
                        <td style="text-align: center"  id="ta_${epi.id }">${epi.status==0?'未审核':'已审核' }</td>
                        <td style="text-align: center"  id="th_${epi.id }">${epi.status!=3?'未处理':'已处理' }</td>
                        <td>
                            <a href="javascript:void(0)" id="dispose_detail" onclick="openNewWin('${epi.id}','detail');">详情</a>&nbsp;
                            <a href="#" onclick="isDeleteMes('EpidemicRecord','id','${epi.id}');">删除</a><br/>
                            &nbsp;<a href="javascript:void(0)" id="dispose_update_${epi.id }" ${epi.status==3?"style='display: none;'":"" } onclick="openNewWin('${epi.id}','update');">修改</a>&nbsp;
                            <c:choose>
                            	<c:when test="${epi.status==0 }">
                            		<a href="javascript:void(0)" id="dispose_message_${epi.id }" onclick="isChangeStatus('${epi.id}','true','dispose_message_${epi.id }','btn_handle_${epi.id }');">审核</a>&nbsp;
                            	</c:when>
                            	<c:when test="${epi.status==3 }">
                            		&nbsp;&nbsp;<a href="javascript:void(0)" id="dispose_message" style="color: gray">已处理</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="javascript:void(0)" onclick="isChangeStatus('${epi.id}','false','btn_handle_${epi.id }','dispose_update_${epi.id }');" id="btn_handle_${epi.id }" >未处理</a>
                            	</c:otherwise>
                            </c:choose>
                            
                            <a href="javascript:void(0)" onclick="isChangeStatus('${epi.id}','false','btn_handle_${epi.id }','dispose_update_${epi.id }');" id="btn_handle_${epi.id }" style="display: none;">未处理</a>
                        </td>
                    </tr>
                    </c:forEach>
                   

                    <tr>
                        <td style="text-align:left;width:20px;">
                            <input type="button" id="btn_animalA" name="btn_animalA" value="删除"   style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;"/>
                        </td>
                        <td style="text-align:left;width:20px;">
                            <input type="button" id="batch_auditing" name="batch_auditing" value="批量审核" onclick="window.confirm('确定批量审核??')" style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:40px;width:71px;height:22px;color:white;display: none;"/>
                        </td>
                        <td colspan="10" style="align:right">
                            <div class="yema">
                            <input type="button" value="添加" id="dispose_insert" name="animalA_insert" style="background-color:#00A882;border:0px;" />
                                <input type="button" value="上一页"
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
										<select name="page"
											onchange="go(this.value,'search','ProductAServlet')">
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
    </div>
</c:when>
<c:otherwise>
	<script language="javascript">
		window.location = "${pageContext.request.contextPath}/EpidemicRecordServlet?action=admin_list&address=${admin.address}&status=0";
	</script>
</c:otherwise>
</c:choose>
</body>
</html>