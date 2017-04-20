<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教师端工作量查询</title>
    <link rel="stylesheet" href="../css/style_dean_reviewdietail.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
</head>
<body onload="init('${cyearmsg.years}','${cyearmsg.lastTerm.id }','${cyearmsg.nextTerm.id }');">
<div class="tab_space_add">
    <div class="tab_space_add_title"><h1>${tuser.userName }老师的工作量审核</h1>
        <div class="re_close_btn delete" name="cancel">
            <a id="close"><i class="iconfont fontSize">&#xe625;</i></a>
        </div>
    </div>
</div>
    <table class="table_msg">
        <thead>
        	<tr>
        		<td class="re_de_row_1">学期</td>
	            <td class="re_de_row_2">类别</td>
	            <td class="re_de_row_3">项目名称</td>
	            <td class="re_de_row_4">完成数目</td>
	            <td class="re_de_row_5">课程系数</td>
	            <td class="re_de_row_7">班级数</td>
	            <td class="re_de_row_6">人数</td>
	            <td class="re_de_row_8">新开课程</td>
	            <td class="re_de_row_9">双语教学</td>
	            <td class="re_de_row_10">工作量</td>
	            <td class="re_de_row_11">操作</td>
        	</tr>
        </thead>
        <c:forEach items="${allWork }" var="w">
        <tr id="tr_${w.id }">
            <td id="td1_${w.id }" onclick="insertSelect(true,event)">${w.term.isLastTerm==true?"上":"下" }</td>
            <td id="td2_${w.id }" >${w.type.typeName }</td>
            <td id="td3_${w.id }" onclick="insertInput(event);">${w.itemName }</td>
            <td id="td4_${w.id }" onclick="insertInput(event);">${w.comNum }</td>
            <td id="td5_${w.id }" >${w.coefficient }</td>
            <td id="td6_${w.id }" onclick="insertInput(event);">${w.classNum }</td>
            <td id="td7_${w.id }" onclick="insertInput(event);">${w.stuNum }</td>
            <td id="td8_${w.id }" ${w.type.typeName=='理论教学'?"onclick='insertSelect(false,event)'":""} ${w.type.typeName!='理论教学'?"class='noCursor'":""}>${w.isNewClass==true?"是":"否" }</td>
            <td id="td9_${w.id }" ${w.type.typeName=='理论教学'?"onclick='insertSelect(false,event)'":""} ${w.type.typeName!='理论教学'?"class='noCursor'":""}>${w.isTwoLauage==true?"是":"否" }</td>
            <td id="td10_${w.id }">${w.allWork }</td>
            <td>
            	<div>
	            	<div id="a_${m.id }" class="same add" onclick="onPassWork('${w.id}','tr_${w.id }','2','${w.user.loginId }');">
	                 	<a ><i class="iconfont">&#xe684;</i></a>
	             	</div>
	             	<div class="same delete unPass">
	                 	<a ><i class="iconfont">&#xe685;</i></a>
	             	</div>
	               	<c:if test="${w.supplement!=''&&w.supplement!=null }">
	               		<div class="same add checkUp">
	                 		<a ><i class="iconfont">&#xe670;</i></a>
	             		</div>
	               	</c:if>
	            </div>
                <span class="pop_feedback">
                    <h3 class="feedback_title">反馈内容</h3>
                    <span>　　类别：${w.type.typeName }</span>
                    <span>　项目名：${w.itemName }</span>
                    <span>修改反馈：</span>
                    <textarea autofocus class="feedbackText" id="ta_${w.id }"></textarea><br>
                    <span class="feedback_btn_group"><a class="btn_add_base_con_same add" onclick="onUnPassWork('${w.id}','tr_${w.id }','ta_${w.id }','1');">确认</a>&nbsp;<a class="btn_add_base_con_same add cancel">取消</a></span>
                </span>
                <span class="pop_feedback" id="uc_${w.id }">
                    <h3 class="feedback_title">填写修改内容</h3>
                    <span>　　类别：${w.type.typeName }</span>
                    <span>　项目名：${w.itemName }</span>
                    <span>请填写修改内容(如果没修改则不填)：</span>
                    <textarea autofocus class="feedbackText"  id="tu_${w.id }"></textarea><br>
                    <span class="feedback_btn_group"><a class="btn_add_base_con_same add"  onclick="onPassAndUpdate('${w.id}','tr_${w.id }','tu_${w.id }','2','${w.user.loginId }','${w.user.userName }','${w.supplement }','${w.fbContent }');">确认</a>&nbsp;<a class="btn_add_base_con_same add cancel">取消</a></span>
                </span>
                <span class="pop_feedback">
                    <h3 class="feedback_title">补充说明内容</h3>
                    <span>　　类别：${w.type.typeName }</span>
                    <span>　项目名：${w.itemName }</span>
                    <span>补充说明：</span>
                    <textarea autofocus class="feedbackText" readonly="readonly" >${w.supplement }</textarea><br>
                    <span class="feedback_btn_group"><a class="btn_add_base_con_same add cancel">确认</a>&nbsp;<a class="btn_add_base_con_same add cancel">取消</a></span>
                </span>
            </td>
        	</tr>
        </c:forEach>
        
        <tfoot>
           <tr>
           	 <td colspan="11" class="noCursor">
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
                    <div class="page_btn_group_re" onclick="go('${page.currentPage+1}');">
                        <span><a>上一页</a></span>
                    </div>
                </div>
            </td>
           </tr>
        </tfoot>
    </table>
<div id="shadow"></div>
</body>
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../js/public_view_control.js"></script>
    <script type="text/javascript" src="../js/dean_ajax.js"></script>
    <script type="text/javascript" src="../js/dean_insertwork_ajax.js"></script>
    <script type="text/javascript" src="../js/dean_reviewajax.js"></script>
    <script>
        function go(num){
        	var ps = "${page.pageSize }";
        	if(num <= 0 ){
        		return;
        	} else if(num > ps) {
        		return;
        	}
        	window.location.href="work_listReview.action?queryName=${tuser.loginId}";
        }
    </script>
</html>