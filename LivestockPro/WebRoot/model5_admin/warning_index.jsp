<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_check.css"/>
    <script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_setTrBgColor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_clearCondition.js"></script>
    <script type="text/javascript">
        $(function() {
            //注册
            $("#div_warning").dialog({
                autoOpen: false,
                width: "620",
                height: "500",
                modal: true,
                buttons: [
                    {
                        text: "关闭",
                        click: function () {
                            $(this).dialog("close");
                        }
                    }
                ]
            });
        });
        function openDialog(farmId,address){
			$("#ifram_warning").attr("src","CountServlet?type=wcount&time=0&farmId="+farmId+"&local="+address) ;
			$("#div_warning").dialog("open");
			event.preventDefault();
		}
		function go(num){
			document.getElementById("currentPage").value = num ;
			document.getElementById("search").submit() ;	// 表单提交
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
</head>
<c:choose>
<c:when test="${all!=null}">
<body>
<!--弹出窗口-->
<div id="div_warning" style="display:none"  title="预警信息">
    <iframe  id="ifram_warning" style="border:0" width="99%" height="98%"></iframe>
</div>
<div class="content">
    <div class="address">
        <span style="font-size:14px;font-family:黑体;white-space:nowrap;">您的位置:&nbsp;&nbsp;预警信息</a></span>&nbsp;>&nbsp;<span style="color:gray;font-family:黑体;font-size:14px;white-space: nowrap;">查看</span>
    </div>


   	<div class="content_banner">
		<span>查看</span>
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
			<input type="hidden" name="address" id="address" value="${admin.address}"  />
			<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"  />
			<input type="hidden" name="type" id="type" value="wcount"  />
		</form>
	</div>
	<div style="width:100%;height:75%;overflow-y:auto;">
	<table id="table" class="content_table">
			<tr>
				<td>畜禽标识码</td>
				<td>名称</td>
				<td>品种</td>
				<td>注册时间</td>
				<td>地址</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${all}" var="farm">
			<tr id="tr_${farm.farmId}">
				<td >
					${farm.farmId}
				</td>
				<td>
					${farm.farmName}
				</td>
				<td>
					${farm.species}
				</td>
				<td>${farm.registDate}</td>
				<td>${farm.location }</td>
				<td>
                    <a href="javascript:void(0);" onclick="openDialog('${farm.farmId}','${farm.location}');" id="waring">预警</a></td></td>
  	</tr>		
			</c:forEach>	

			<tr>
				<td colspan="6" style="align:right">
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
	</table>
	</div>
</div>
</body>
</c:when>
<c:otherwise>
	<script>
		window.location = "${pageContext.request.contextPath}/FarmMesServlet?action=list&address=${admin.address}&type=wcount";
	</script>
</c:otherwise>
</c:choose>
</html>