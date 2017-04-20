<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教务员端工作量查询</title>
    <link href="../css/style_dean_querywork.css" rel="stylesheet">
    <link href="../css/style_imp_font.css" rel="stylesheet">
</head>
<body>
<div class="tab_space_add">
    <div class="tab_space_add_title"><h1>工作量查询</h1></div>

    <div class="query_box_nowork fixWidth">
        <div class="result fixWidth query_box_title"><span class="query_box_title_content">条件查询</span></div>
        <div class="fixWidth query_box_formdiv">
            <form class="query_box_form "  action="work_pssList.action" method="post" id="form1">
                <span>学期：</span>
                <input class="radio_btn" name="termChioce" onchange="subQuery();" id="allterm" type="radio" ${termChioce==0?"checked":"" } value="0"/><label for="allterm">全部</label>
                <input class="radio_btn" name="termChioce" onchange="subQuery();" id="lastterm" type="radio" ${termChioce==1?"checked":"" } value="1"/><label
                    for="lastterm">上</label>
                <input class="radio_btn" name="termChioce" onchange="subQuery();" id="nextterm" type="radio" ${termChioce==2?"checked":"" } value="2"/><label for="nextterm">下</label>　
                <label for="workNum">教师姓名：</label>
                <input type="text" class="form_input" id="teacherName" name="queryParamTn" value="${queryParamTn }" placeholder="请输入查教师名称"/>
                <label for="className">项目名/课程名：</label>
                <input type="text" class="form_input" id="itemName" name="queryParam" value="${queryParam }" placeholder="请输入课程名/项目名"/>

                <div class="btn_group" onclick="subQuery();">
                    <a>查询</a><i class="fontSize iconfont">&#xe633;</i>
                </div>
                <div class="btn_group" onclick="clearData();">
                    <a>清除条件</a>
                </div>
                <input type="hidden" name="cyear.years" value="${cyear.years}"/>
            </form>
        </div>

    </div>

    <table class="table_form query_work_table">
        <tr class="row fixWidth floatStyle">
            <td class="req_row_0_0">
                <span>姓名</span>
            </td>
            <td class="req_row_0_0">
            	<span>审核人</span>
            </td>
            <td class="req_row_1_1">
                <span>学期</span>
            </td>
            <td class="req_row_2_2">
                <span>类别</span>
            </td>
            <td class="req_row_3_3">
                <span>项目</span>
            </td>
            <td class="req_row_4_4">
                <span> 完成数目</span>
            </td>
            <td class="req_row_5_5">
                <span>课程系数</span>
            </td>
            <td class="req_row_7_7">
                <span>班级数</span>
            </td>
            <td class="req_row_6_6">
                <span>人数</span>
            </td>
            <td class="req_row_8_8">
                <span>新开课程</span>
            </td>
            <td class="req_row_9_9">
                <span>双语教学</span>
            </td>
            <td class="row_10">
                <span>工作量</span>
            </td>
        </tr>
		<c:forEach items="${allWork}" var="w">
		<tr class="row fixWidth floatStyle">
            <td class="req_row_0_0">
                <span>${w.user.userName }</span>
            </td>
            <td class="req_row_0_0">
                <span>${w.duser.userName }</span>
            </td>
            <td class="req_row_1_1">
                <span>${w.term.isLastTerm==true?"上":"下" }</span>
            </td>
            <td class="req_row_2_2">
                <span>${w.type.typeName }</span>
            </td>
            <td class="req_row_3_3">
                <span>${w.itemName }</span>
            </td>
            <td class="req_row_4_4">
                <span>${w.comNum }</span>
            </td>
            <td class="req_row_5_5">
                <span>${w.coefficient }</span>
            </td>
            <td class="req_row_7_7">
                <span>${w.classNum }</span>
            </td>
            <td class="req_row_6_6">
                <span>${w.stuNum }</span>
            </td>
            <td class="req_row_8_8">
                <span>${w.isNewClass==true?"是":"否" }</span>
            </td>
            <td class="req_row_9_9">
                <span>${w.isTwoLauage==true?"是":"否" }</span>
            </td>
            <td class="row_10">
                <span>${w.allWork}</span>
            </td>
        </tr>
		</c:forEach>
        <tr class="result fixWidth ">
            <td colspan="12">
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
	window.location.href="work_pssList.action?cyear.years=${cyear.years}&page.pageSize=${page.pageSize }&page.currentPage="+num;
}
</script>
</html>