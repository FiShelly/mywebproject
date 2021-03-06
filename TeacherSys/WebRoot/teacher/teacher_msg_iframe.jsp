<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/style_imp_font.css">
    <link rel="stylesheet" href="../css/style_msg.css">
    <title></title>
</head>
<body>
<h3>
	消息列表 
	<div class="re_close_btn cancel" id="msg_list_close" >
    	<a ><i class="iconfont fontSize">&#xe625;</i></a>
	</div>
</h3>
<form>
    <table class="table_msg">
        <tr>
            <td class="msg_row_1">来自</td>
            <td class="msg_row_2">内容</td>
            <td class="msg_row_3">时间</td>
            <td class="msg_row_4">状态</td>
            <td class="msg_row_5">操作</td>
        </tr>
        <c:forEach items="${allMsg }" var="m">
        <tr id="tr_${m.id }">
            <td class="msg_row_1">${m.fromName }</td>
            <td class="msg_row_2">${m.content }</td>
            <td class="msg_row_3">${m.date }</td>
            <td class="msg_row_4" id="td_${m.id }">${m.isRead?"已读":"未读" }</td>
            <td class="msg_row_5">
            	<c:choose>
            		<c:when test="${m.isRead }">
            			<div class="same delete" onclick="deleteByAjax('${m.id}',false);">
                    		<a ><i class="iconfont">&#xe632;</i></a>
                		</div>
            		</c:when>
            		<c:otherwise>
            			<div id="a_${m.id }" class="same add" onclick="updateReadState('${m.id}','true')">
                    		<a ><i class="iconfont">&#xe684;</i></a>
                		</div>
                		<div class="same delete" onclick="deleteByAjax('${m.id}',false);">
                    		<a ><i class="iconfont">&#xe632;</i></a>
                		</div>
            		</c:otherwise>
            	</c:choose>
                
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td class="" colspan="5">
                <div class="page">
                    <input type="button" value="上一页" ${page.currentPage<=1?"disabled":""} onclick="go('${page.currentPage-1}');"/>
                    <input type="button" value="下一页" ${page.currentPage>=page.pageSize?"disabled":""} onclick="go('${page.currentPage+1}');"/>
                    <select>
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
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public_view_control.js"></script>
<script type="text/javascript" src="../js/msg_ajax.js"></script>    
</html>
