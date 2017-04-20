<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教师端工作量查询</title>
    <link rel="stylesheet" href="../css/style_teacher_op_public.css"/>
    <link rel="stylesheet" href="../css/style_teacher_query.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
</head>
<body>
<div class="tab_space_add">
    <div class="tab_space_add_title"><h1>工作量查询</h1></div>
    <div class="query_box fixWidth">
        <div class="result fixWidth query_box_title"><span class="query_box_title_content">条件查询</span></div>
        <div class="fixWidth query_box_formdiv">
            <form class="query_box_form "  action="work_pssList.action" method="post" id="form1">
                <span>学期：</span>
                <input class="radio_btn" name="termChioce" id="allterm" type="radio" value="0" ${termChioce==0?"checked":"" }/><label for="allterm">全部</label>
                <input class="radio_btn" name="termChioce" id="lastterm" type="radio" value="1" ${termChioce==1?"checked":"" }/><label
                    for="lastterm">上</label>
                <input class="radio_btn" name="termChioce" id="nextterm" type="radio" value="2" ${termChioce==2?"checked":"" }/><label for="nextterm">下</label>　
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
        <div class="result fixWidth workRowTextA">
            <div class="workMsg">
                <label>总工作量：</label><input type="number" name="allWork" value="${dc.allWork }" readonly/>
                <label>额内工作量：</label><input type="number" name="rangWork"   value="${dc.inWork }" readonly/>
                <label>额外工作量：</label><input type="number" name="moreWork"  value="${dc.inOut }"  readonly/>
                <label>金额：</label><input type="number" name="moeny"  value="${dc.allSal }" />
                <label>单位工作量金额：</label><input type="number" name="moreWork"  value="${cyearmsg.sal }" readonly/>
            </div>
            <div class="pageControl">
            </div>
        </div>
    </div>
	
    <table class="table_form query_table">
        <tr>
            <td class="row_1_1">学期</td>
            <td class="row_2_2">类别</td>
            <td class="row_3_3">项目</td>
            <td class="row_4">完成数目</td>
            <td class="row_5_5">课程系数</td>
            <td class="row_6_6">班级数</td>
            <td class="row_7_7">人数</td>
            <td class="row_8_8">新开课程</td>
            <td class="row_9_9">双语教学</td>
            <td class="row_10">工作量</td>
        </tr>
        <c:forEach items="${allWork }" var="w">
        <tr >
            <td class="row_1_1">${w.term.isLastTerm==true?"上":"下" }</td>
            <td class="row_2_2">${w.type.typeName }</td>
            <td class="row_3_3">${w.itemName }</td>
            <td class="row_4">${w.comNum }</td>
            <td class="row_5_5">${w.coefficient }</td>
            <td class="row_6_6">${w.classNum }</td>
            <td class="row_7_7">${w.stuNum }</td>
            <td class="row_8_8">${w.isNewClass==true?"是":"否" }</td>
            <td class="row_9_9">${w.isTwoLauage==true?"是":"否" }</td>
            <td class="row_10">${w.allWork}</td>
        </tr>
        </c:forEach>
        <tr>
           <td colspan="10">
               <div class="result fixWidth ">
                   <div class="pageDisplay_re">
                       <span class="pagefontsize">第</span>
                        <span><select class="pageSelect_re pagefontsize" onchange="go(this.value);">
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
                       <span><a >上一页</a></span>
                   </div>
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