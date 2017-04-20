<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style_admin_query.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
    <title></title>
</head>
<body>
<h1>教师账号列表</h1>
<form>
    <table class="table_msg fixTabWidth">
        <tr>
            <td>账号</td>
            <td>姓名</td>
            <td>出生日期</td>
            <td>职称</td>
            <td>职务</td>
            <td>状态</td>
            <td>操作</td>
        </tr>
<c:forEach items="${all }" var="u">
 <tr>
            <td><input type="text" name="loginId" readonly="readonly" value="${u.loginId }"></td>
            <td><input type="text" name="userName" value="${u.userName }"></td>
            <td><input type="text" readonly onclick="laydate({start:'${u.birthDate }'})" name="birthDate" value="${u.birthDate }"></td>
            <td>
                <select name="title.id">
                   <c:forEach items="${allTitle }" var="t">
                    	<option value="${t.id }" ${t.id==u.title.id?'selected':'' }>${t.titleName }</option>
                   </c:forEach>
                </select>
            </td>
            <td>
                <select name="post.id">
                   <c:forEach items="${allPost}" var="p">
                    	<option value="${p.id }"  ${p.id==u.post.id?'selected':'' }>${p.postName }</option>
                   </c:forEach>
                </select>
            </td>
            <td>
                <select name="state">
                    <option value="false" >停用</option>
                    <option value="true" ${u.state?"selected":"" }>激活</option>
                </select>
            </td>
            <td>
                <div class="same add" onclick="updateUser(this,'${u.loginId }',${u.role });">
                    <a onclick=""><i class="iconfont">&#xe684;</i></a>
                </div>
                <div class="same delete" onclick="resetPw('${u.loginId }')">
                    <a onclick=""><i class="iconfont">&#xe694;</i></a>
                </div>
                <div class="same delete" onclick="deleteUser(this,'${u.loginId}');">
                    <a onclick=""><i class="iconfont">&#xe632;</i></a>
                </div>
                <input type="hidden" name="pw" value="${u.pw }">
            </td>
        </tr>
</c:forEach>
        <tr>
            <td colspan="7">
                <div class="page">
                    <input type="button" onclick="go('${page.currentPage-1}')" value="上一页" ${page.currentPage==1||page.currentPage>page.pageSize?"DISABLED":""}>
                    <input type="button" onclick="go('${page.currentPage+1}')" value="下一页" ${page.currentPage>=page.pageSize?"DISABLED":""}>
                    
                    <select name="page_select" onchange="go(this.value)" >
							<c:forEach var="p" begin="1" end="${page.pageSize }">
								<option value="${p }" ${p==page.currentPage?"SELECTED":"" } >${p}</option>
							</c:forEach>
					</select>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
    <script src="../js/laydate/laydate.js"></script>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/public_view_control.js"></script>
    <script src="../js/admin_ajax.js"></script>
  	<script>
        function go(num){
        	window.location.href="user_list.action?rolee=2&cyear=${cyear}&page.currentPage="+num;
    	}
    </script>
    </html>