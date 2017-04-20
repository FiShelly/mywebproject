<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教务员数据统计与报表</title>
    <link href="../css/style_dean_tablecount.css" rel="stylesheet">
    <link href="../css/style_imp_font.css" rel="stylesheet">
</head>
<body>
<div class="tab_space_add">
    <div class="tab_space_add_title">
    	<h1>数据统计与报表</h1>
    	<div class="page_btn_group_exp" id="export_tc">
        	<span><a>导出报表</a></span>
   		 </div>
    </div>
    <form class="query_box_form" id="tform" action="work_dcList.action" method="post">
        <div class="query_content">
        	<span>学期：</span>
        	<input class="radio_btn" onchange="go(1);" name="termChioce" id="allterm" type="radio" ${termChioce==3?"checked":"" }  value="3"/><label for="allterm">全部</label>
        	<input class="radio_btn" onchange="go(1);" name="termChioce" id="lastterm" type="radio" ${termChioce==2?"checked":"" } value="2"/><label for="lastterm">整年</label>
        	<input class="radio_btn" onchange="go(1);" name="termChioce" id="lastterm" type="radio" ${termChioce==0?"checked":"" } value="0"/><label for="lastterm">上</label>
        	<input class="radio_btn" onchange="go(1);" name="termChioce" id="nextterm" type="radio" ${termChioce==1?"checked":"" } value="1"/><label for="nextterm">下</label>　
        </div>
        <input type="hidden" name="queryParam" value="${queryParam}" />
        <input type="hidden" name="cyear.years" value="${cyear.years}" />
        <input type="hidden" id="currentPage" name="page.currentPage" value="${page.currentPage }"/>
        <input type="hidden" name="page.pageSize" value="${page.pageSize }"/>
    </form>
    <table class="table_count">
        <tr>
            <td class="td_1">学期</td>
            <td class="td_0">教师名称</td>
            <td class="td_2">职称</td>
            <td class="td_2">职务</td>
            <td class="td_0">职称系数</td>
            <td class="td_0">职务补贴</td>
            <td class="td_2">额内工作量</td>
            <td class="td_2">额外工作量</td>
            <td class="td_2">总工作量</td>
            <td class="td_2">工作量金额</td>
        </tr>
        <c:forEach items="${allDc }" var="dc">
        <tr>
            <td class="td_1">${dc.term==0?"上":"" }${dc.term==1?"下":"" }${dc.term==2?"整年":"" }</td>
            <td class="td_0">${dc.user.userName}</td>
            <td class="td_2">${dc.user.title.titleName}</td>
            <td class="td_2">${dc.user.post.postName}</td>
            <td class="td_0">${dc.user.title.coefficient}</td>
            <td class="td_0">${dc.user.post.coefficient}</td>
            <td class="td_2">${dc.inWork}</td>
            <td class="td_2">${dc.inOut}</td>
            <td class="td_2">${dc.allWork}</td>
            <td class="td_2">${dc.allSal}</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="10">
                <div class="pageDisplay_re">
                    <span class="pagefontsize">第</span>
                    <span>
                    <select class="pageSelect_re pagefontsize"  onchange="go(this.value);">
                       <c:forEach var="p" begin="1" end="${page.pageSize }">
							<option value="${p }" ${p==page.currentPage?"SELECTED":"" } >${p}</option>
						</c:forEach>
                    </select>
                    </span>
                    <span class="pagefontsize"> 页</span>
                </div>
 				<div class="page_btn_group_re" onclick="go('${page.currentPage+1}');">
           		 	<span> <a>下一页</a></span>
        		</div>
        		<div class="page_btn_group_re" onclick="go('${page.currentPage-1}');">
            		<span><a>上一页</a></span>
        		</div>
            </td>
        </tr>
    </table>
</div>
</body>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../js/public_view_control.js"></script>
    <script type="text/javascript">
	    function go(num){
	    	var ps = "${page.pageSize }";
	    	if(num <= 0 ){
	    		return;
	    	} else if(num > ps) {
	    		return;
	    	}
	    	$("#currentPage").val(num);
	    	$("#tform").submit();
	    }
	    
    </script>
</html>