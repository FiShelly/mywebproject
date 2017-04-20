<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/style_dean_review.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
</head>
<body>
<div class="review_table fixReWidth">
    <div class="tab_space_add_title">
        <h1>教师工作量审核与录入</h1>
    </div>
    <div class=" rowRe">
        <div class="review_row_1">
            <span>姓名</span>
        </div>
        <div class="review_row_2">
            <span>职称</span>
        </div>
        <div class="review_row_3">
            <span>职务</span>
        </div>
        <div class="review_row_4">
            <span>提交数目</span>
        </div>
        <div class="review_row_5">
            <span>最后提交日期</span>
        </div>
        <div class="review_row_6">
            <span>审核</span>
        </div>
        <div class="review_row_7">
            <span>工作量录入</span>
        </div>
    </div>
    <c:forEach items="${allPr }" var="pr">
	     <div class=" rowRe">
	        <div class="review_row_1">
	            <span>${pr.user.userName }</span>
	        </div>
	        <div class="review_row_2">
	            <span>${pr.user.title.titleName }</span>
	        </div>
	        <div class="review_row_3">
	            <span>${pr.user.post.postName }</span>
	        </div>
	        <div class="review_row_4">
	            <span>${pr.subCount }</span>
	        </div>
	        <div class="review_row_5">
	            <span>${pr.subDate }</span>
	        </div>
	        <div class="review_row_6">
	            <div class="add ajbtn" onclick="handleClick_o('work_listReview.action?itemName=${pr.loginId}');">
	                <a id="review_work"><i  class="iconfont">&#xe684;</i></a>
	            </div>
	        </div>
	        <div class="review_row_7" onclick="openInsertWorkDia('${pr.user.userName}','${pr.user.loginId }','${pr.user.title.coefficient }','${pr.user.post.coefficient }')">
	            <div class="add ajbtn">
	                <a id="add_work"><i class="iconfont">&#xe6a0;</i></a>
	            </div>
	        </div>
	    </div>
    </c:forEach>
    <div class="page_row">
        <div class="pageDisplay_re">
            <span class="pagefontsize">第</span>
                <span><select class="pageSelect_re pagefontsize"  onchange="go(this.value);">
                   	<c:forEach var="p" begin="1" end="${page.pageSize }">
						<option value="${p }" ${p==page.currentPage?"SELECTED":"" } >${p}</option>
					</c:forEach>
                </select></span>
            <span class="pagefontsize"> 页</span>
        </div>
        <div class="page_btn_group_re" onclick="go('${page.currentPage+1}');">
            <span> <a>下一页</a></span>
        </div>
        <div class="page_btn_group_re" onclick="go('${page.currentPage-1}');">
            <span><a>上一页</a></span>
        </div>
    </div>
</div>
</body>
 <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../js/dean_index.js"></script>
    <script>
        function go(num){
        	var ps = "${page.pageSize }";
        	if(num <= 0 ){
        		return;
        	} else if(num > ps) {
        		return;
        	}
        	window.location.href="work_listPreReview.action?page.pageSize=${page.pageSize }&page.currentPage="+num;
        }
    </script>
</html>