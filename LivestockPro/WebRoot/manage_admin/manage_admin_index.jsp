<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>管理员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manage_admin_style.css" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/new_model_funcs.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/user_model2_checkbox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/farm_delete_ajax.js" type="text/javascript"></script>
    <script type="text/javascript">
    function go(num,formId){
		document.getElementsByName("currentPage")[0].value=num;
		document.getElementsByName("currentPage")[1].value=num;
		document.getElementById(formId).submit() ;	// 表单提交
	}
    	function openNewWin(id,action){
    		if(action == 'updatePw'){
    			$( "#MyDiv3" ).dialog( "open" );
    			$("#frame_updatePassword").attr("src","manage_admin/manage_admin_updatepw.jsp?loginId="+id) ;
    		} else {
    			$( "#MyDiv2" ).dialog( "open" );
    			$("#frame_update").attr("src","AdminManageServlet?action=updatePre&currentPage=${currentPage }&loginId="+id) ;
    		}
    	}
    	 function goDeleteAll() {
    			var select = document.getElementsByName("check");
    			var count = 0;
    			for(var i = 0 ;i<select.length;i++) {
    				if(select[i].checked == true) {
    					count = count + 1;
    				}
    			}
    			if(window.confirm("确定删除这"+count+"项上管理员信息？")) {
    				document.form_content.action = "AdminManageServlet?action=deleteAll";
    				document.form_content.submit() ;
    			}
    		}
    	 function isLogout(){
    			if(window.confirm("确定退出登录？")){
    				window.open("${pageContext.request.contextPath}/LogoutServlet", "_top","fullscreen=yes");
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
    <!---注册-->
    <div id="MyDiv" style="display:none"  title="注册管理员的行政级别信息">
        <iframe id="frame_register" src="manage_admin/manage_admin_register.jsp?currentPage=${currentPage }" width="99%" height="98%" style="border:0"></iframe>
    </div>
    <!--修改-->
    <div id="MyDiv2" style="display:none"  title="修改管理员的行政级别信息">
        <iframe id="frame_update" width="99%" height="98%" style="border:0"></iframe>
    </div>

    <div id="MyDiv3" style="display:none"  title="修改管理员的密码">
        <iframe id="frame_updatePassword"  width="99%" height="98%" style="border:0"></iframe>
    </div>

    <div class="topMenu">
        <table class="tb_top">
            <tr>
                <td style="width: 60px;text-align: left">
                    <span>畜牧检疫防疫信息时空分析及管理系统</span>
                </td>
                <td style="width: 20px;"></td>
                <td style="width: 30px;">
                    <img src="${pageContext.request.contextPath}/image/cuttingLine.png"/>
                </td>
                <td style="width: 20px;background-color: #013334;">
                    <a href="manage_admin/manage_admin_index.jsp" target="_top" id="person_info">
                        <img src="${pageContext.request.contextPath}/image/message.png" style="width: 35px;height: 31px;padding-top: 5px;"/>
                        <p>人员信息</p></a>
                    </td>
                    <td style="width: 30px;">
                        <img src="${pageContext.request.contextPath}/image/cuttingLine.png"/>
                    </td>
                    <td  style="width: 60px;text-align: right;padding-right: 33px">
                       <p>${admin.loginId }[管理员]</p>
                       <p><a href="#" onclick="isLogout();" id="logout">退出</a></p>
                   </td>
               </tr>

           </table>
       </div>

       <div class="leftMenu">
        <ul class="ul_left">
            <li>
                <a id="select" onclick="setLiBgColor('select')" href="manage_admin/manage_admin_index.jsp" target="_top" style="background-color:#232423;">
                    <img width="38" height="26" src="${pageContext.request.contextPath}/image/eye.png"><br/>
                    查看
                </a> 
            </li>
            <li><a href="javascript:void(0)" onclick="setLiBgColor('register')" id="register">
                <img style="width: 35px; height: 31px;" src="${pageContext.request.contextPath}/image/LoginIcon.png" /><br/>
                注册
            </a> 
        </li>

    </ul>
</div>

<div class="content">
    <span style="font-family: 黑体; font-size: 14px; white-space: nowrap;">您的位置:&nbsp;&nbsp;工作人员信息管理</span>
    <div class="content_banner">
        <span>搜索</span>
    </div>

    <!--搜索框-->
    <div class="search">
        <form name="search" id="search"  style="height: 60px;" action="${pageContext.request.contextPath }/AdminManageServlet" method="post">
            <table style="width:100%">
                <tbody><tr><td style="width: 20px;">
                    账号:
                </td>
                <td style="width: 50px; text-align: left;">
                    <input name="number" value="${loginId }" id="number" type="text">
                </td>
                <td style="width: 20px;">
                    行政区:
                </td>
                <td style="width: 50px; text-align: left;">
                    <input name="district" value="${district }" id="district" type="text">
                </td>
                <td style="width: 35px;">
                    注册日期:
                </td>
                <td style="width: 120px; text-align: left;">
                    <input name="start_date" id="start_date" value="${start_date }" style="width: 100px;" onclick="laydate()" type="text" readonly="readonly">
                    <img width="16" height="16" style="margin: 0px 0px 0px -25px; vertical-align: middle;" src="${pageContext.request.contextPath}/image/calendar.png">
					至
					<input name="end_date" id="end_date" value="${end_date }" style="width: 100px;" onclick="laydate()" type="text" readonly="readonly">
                    <img width="16" height="16" style="margin: 0px 0px 0px -25px; vertical-align: middle;" src="${pageContext.request.contextPath}/image/calendar.png">

                </td>

                <td style="width: 30px;">
                    <button style="border-radius: 6px; border: 0px currentColor; border-image: none; width: 80px; height: 25px; color: white; font-size: 14px; background-color: rgb(18, 166, 134);"
                    type="submit" value="查询">查询&nbsp;<img width="15" height="15" style="vertical-align: middle;" src="${pageContext.request.contextPath}/image/search.png"></button></td>
                    <td style="width: 25px; text-align: left;"><input name="clearBtn" id="clearBtn" style="border-radius: 6px; border: 0px currentColor; border-image: none; width: 80px; height: 25px; color: white; font-size: 14px; background-color: rgb(18, 166, 134);" onclick="Clear()" type="button" value="清除条件" />
                    </td>
                </tr>
            </tbody></table>
        	<input type="hidden" name = "sAddress" id = "sAddress" value="${admin.address}"/>
			<input type="hidden" name = "action" id = "action" value="list"/>	
			<input type="hidden" name = "currentPage" id="currentPage" value="${currentPage}"/>
        </form>
    </div>
<c:choose>
<c:when test="${list!=null}">
<div style="width:100%;height:78%;overflow-y: auto;">
    <form id="form_content" name="form_content" action="${pageContext.request.contextPath }/AdminManageServlet" method="post">
        <table id="tb_content">
            <tr>
                <td style="width: 30px;">
                    <input name="checkAll" id="checkAll" style="margin-right: 10px; margin-left: 10px; float: left;" onclick="CheckAll()" type="checkbox">
                </td>
                <td>
                    账号
                </td>
                <td>
                    注册时间
                </td>
                <td>
                    行政区
                </td>
                <td>
                    级别
                </td>
                <td>
                    操作
                </td>
            </tr>
          <c:forEach items="${list }" var="ad">
          	<tr id="tr_${ad.loginId }" >
                <td><input name="check" id="check1" value="${ad.loginId }" style="margin-right: 10px; margin-left: 10px; float: left;" onclick="selectIt('4');" type="checkbox"></td>
                <td>${ad.loginId }</td>
                <td>${ad.registDate }</td>
                <td>${ad.address }</td>
                <td>${ad.grade==1?"县级":"" }${ad.grade==2?"市级":"" }${ad.grade==3?"省级":"" }</td>
                <td><a href="javascript:void(0)" id="update" onclick="openNewWin('${ad.loginId}','update');">修改</a>&nbsp;<a href="javascript:void(0)" onclick="isDeleteAdminMes('AdminManage','loginId','${ad.loginId}')">删除</a><br/>
                <a href="javascript:void(0)" id="update_password" onclick="openNewWin('${ad.loginId}','updatePw');">修改密码</a></td>
            </tr>
          </c:forEach>
            <tr>
                <td colspan="2" style="width: 20px; text-align: left;"><input name="btn_del" id="btn_del" style="border: 0px currentColor; border-image: none; width: 51px; height: 22px; color: white; font-family: 宋体; font-size: 12px; margin-left: 10px;display: none;  background-color: rgb(0, 168, 130);" onclick="goDeleteAll();" onload="del();" type="button" value="删除">
                </td>
                <td style="align: right;" colspan="4">
                    <div class="yema" >
						<input type="button" value="上一页" 
							onclick="go(<%=currentPage-1%>,'form_content')" <%=currentPage==1?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<input type="button" value="下一页" 
							onclick="go(<%=currentPage+1%>,'form_content')" <%=currentPage==pageSize?"DISABLED style='background-color:#83cdbe;border:0px;'":"style='background-color:#00A882;border:0px;'"%>/>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">共</font>
						<label><%=pageSize %></label><!--总页数为表格数据量-->
						<font style="font-family:宋体;font-size:12px;margin-left:2px;">页</font>
						<font style="font-family:宋体;font-size:12px;margin-right:2px;">第</font>
						<select name="page" onchange="go(this.value,'form_content')" >
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
        <input type="hidden" name = "sAddress" id = "sAddress" value="${admin.address}"/>
			<input type="hidden" name = "action" id = "action" value="list"/>	
			<input type="hidden" name = "currentPage"  value="${currentPage}"/>
    </form>
    </div>
    </c:when>
<c:otherwise>
	<script>
		window.location = "${pageContext.request.contextPath}/AdminManageServlet?action=list&sAddress=${admin.address}";
	</script>
</c:otherwise>
</c:choose>
</div>

</body>
</html>
