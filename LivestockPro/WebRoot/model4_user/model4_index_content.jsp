<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!--这是放置右边内容-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model3_check.css"/>
    <script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model2_checkbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_setTrBgColor.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_clearCondition.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/farm_delete_ajax.js"></script>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/user_model4_insertContent.js"></script>
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
			$("#iframe_detail").attr("src","EpidemicRecordServlet?action=detail&id="+id) ;
		} else {
			$( "#update_div" ).dialog( "open" );
			$("#iframe_update").attr("src","EpidemicRecordServlet?action=updatePre&currentPage=${currentPage}&id="+id) ;
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
    <div id="submit_div" style="display:none"  title="疫情信息上报">
        <iframe id="iframe_submit" src="model4_user/epidemic_insert.jsp" width="99%" height="98%"></iframe>
    </div>
    

    <!--弹出的更改窗口-->
    <div id="update_div" style="display:none"  title="疫情信息更改">
        <iframe id="iframe_update" width="99%" height="98%"></iframe>
    </div> 


    <!--新增-->
    <!--弹出详情窗口-->
    <div id="detail_div" style="display:none"  title="疫情详细信息">
        <iframe id="iframe_detail"  width="99%" height="99%"></iframe>
    </div> 
    

    <div class="content">
        <div class="address">
            <font style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;疫情信息上报管理</font>
        </div>

        <div class="content_banner">
            <font>查看</font>
        </div>

        <div class="search">
            <form action="EpidemicRecordServlet" method="post" name="search" id="search" style="height:60px;">
                <table id="aa" name="aa"> 
                    <tr>
                        <td style="width:30px;">
                            爆发日期:
                        </td>
                        <td style="width: 90px; text-align: left;">
                            <input name="start_date" id="start_date" value="${start_date }" style="width: 100px;" onclick="laydate()" type="text" readonly="readonly"/>
                            <img width="16" height="16" style="margin: 0px 0px 0px -25px; vertical-align: middle;" src="${pageContext.request.contextPath}/image/calendar.png"/>
                            至
                            <input name="end_date" id="end_date" value="${end_date }" style="width: 100px;" onclick="laydate()" type="text" readonly="readonly"/>
                            <img width="16" height="16" style="margin: 0px 0px 0px -25px; vertical-align: middle;" src="${pageContext.request.contextPath}/image/calendar.png"/>
                        </td>
                        <td style="width:40px;">
                            发病动物种类:
                        </td>
                        <td style="text-align: left;width: 50px;">
                            <input type="text" id="animal_type" name="animal_type" value="${animal_type }"  style="width:90%;"/>
                        </td>
                        <td style="width:40px;">
                            审核及处理情况:
                        </td>
                        <td style="text-align:left;width:120px;">

                            <input type="radio" name="status" value="0" ${status=='0'?'checked':''} id="choice1" style="width:13px;height:15px;vertical-align: bottom;" />未审核&nbsp;
                            <input type="radio" name="status" value="1" ${status=='1'?'checked':''}  id="choice2" style="width:13px;height:15px;vertical-align: bottom;" />已审核
                            <input type="radio" name="status" value="2" id="choice1"  ${status=='2'?'checked':''}  style="width:13px;height:15px;vertical-align: bottom;" />未处理&nbsp;
                            <input type="radio" name="status" value="3" id="choice2"  ${status=='3'?'checked':''}  style="width:13px;height:15px;vertical-align: bottom;"/>已处理
                        </td>
                     <td style="width:35px;">
                        <button type="submit" value="查询" style="width:80px;height:25px;border-radius:6px;background-color:#12a686;color:white;border:0px;font-size:14px;">查询&nbsp;<img src="${pageContext.request.contextPath}/image/search.png" style="vertical-align: middle;" width="15px" height="15px"/></button></td>
                        <td style="width:30px;text-align: left"><input type="button" value="清除条件" id="clearBtn" onclick="clearSearchCondition()" name="clearBtn"  style="width:80px;height:25px;border-radius:6px;color:white;border:0px;font-size:14px;background-color:#12a686;"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name = "farmId" id = "farmId" value="${user.farm.farmId}"/>
				<input type="hidden" name = "action" id = "action" value="list"/>	
				<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>
            </form>
        </div>

        <!--2015.5.11新增div设置滚动条-->
        <div style="width:100%;height:79%;overflow-y:auto;">
            <!--放入内容-->
            <form action="" method="post" id="form_content" name="form_content">

                <table class="content_table" id="animalA">
                    <!--该表格中除了每一行的第一列是左对齐，其他的都是居中对齐-->
                    <tr>
                        <td style="width:20px;"><input type="checkbox" name="checkAll" style="margin-right: 10px; margin-left: 10px;" id="checkAll" onclick="CheckAll()" /></td>
                        <td style="color:black;width:54px;">爆发日期</td>
                        <td style="color:black;width:60px;">是否有人感染</td>
                        
                        <td style="color:black;width:200px;text-align:left;">死亡情况</td>
                        <td style="color:black;width:144px;text-align:left;">饲养种类</td>
                        <td style="color:black;width:104px;text-align:left;">饲养规模</td>
                        <td style="color:black;width:60px;text-align:left;">发病动物种类</td>
                        <td style="width: 40px; text-align: center;">审核情况</td>
                        <td style="width: 40px; text-align: center;">处理情况</td>
                        
                        <td style="color:black;width:64px;">操作</td>
                    </tr>
                    <c:forEach items="${list}" var="epi">
                    <tr id="tr_${epi.id }">
                        <td style="width:20px;"><input type="checkbox" value="${epi.id }" onclick="selectIt(5);" id="check" name="check" style="margin-left:10px;margin-right:10px;"/></td>
                        <td>${epi.date }</td>
                        <td>${epi.isPeoInfect==true?'是':'否' }</td>
                        <td style="text-align:left;">${epi.deaths }</td>
                        <td style="text-align:left;">${epi.feedSpecies }</td>
                        <td style="text-align:left;">${epi.feedScale }</td>
                        <td style="text-align:left;">${epi.sickSpecies }</td>
                        <td style="text-align: center">${epi.status==0?'未审核':'已审核' }</td>
                        <td style="text-align: center">${epi.status!=3?'未处理':'已处理' }</td>
                        
                        <td><a href="javascript:void(0)" id="epidemic_detail" onclick="openNewWin('${epi.id}','detail');">详情</a>&nbsp;
                        <a href="#" onclick="isDeleteMes('EpidemicRecord','id','${epi.id}');">删除</a><br/>
                        	<a href="javascript:void(0)" id="epidemic_update" onclick="openNewWin('${epi.id}','update');">修改</a></td>
                    </tr>
                    </c:forEach>

                    <tr>
                        <td style="text-align:left;width:20px;">
                            <input type="button" id="btn_del" name="btn_del" value="删除" onclick="goDeleteAll();" style="background-color:#00A882;border:0px;font-family:宋体;font-size:12px;margin-left:10px;width:51px;height:22px;color:white;display:none;"/>
                        </td>
                        
                        <td colspan="9" style="align:right">
                            <div class="yema">
                             <input type="button" value="上报" id="epidemic_submit" name="epidemic_submit" style="background-color:#00A882;border:0px;" />
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
                <input type="hidden" name="status" value="${status }"/>
                <input type="hidden" name="farmId" value="${user.farm.farmId }"/>
            </form>

        </div>
    </div>
</c:when>
<c:otherwise>
	<script language="javascript">
		window.location = "${pageContext.request.contextPath}/EpidemicRecordServlet?action=list&farmId=${user.farm.farmId}&status=0";
	</script>
</c:otherwise>
</c:choose>
</body>
</html>
