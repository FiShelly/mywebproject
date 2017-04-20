<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教师端工作量录入</title>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="../css/style_teacher_op_public.css"/>
    <link rel="stylesheet" href="../css/style_teacher_update.css">
    <link rel="stylesheet" href="../css/style_imp_font.css">
        <script type="text/javascript" src="../js/teacher_ajax.js"></script>
    <script type="text/javascript" src="../js/public_view_control.js"></script>
    <style>

    </style>
</head>
<body onload="getTypeByAjax('${cyearmsg.years}');">

<div class="tab_space_add">
    <div class="tab_space_add_title"><h1>工作量修改</h1></div>

    <div class="workRowChange">
        <div class="result fixWidth workRowTextA">
         	<label>总工作量：</label><input type="text" name="aallWork" id="aallWork" value="${dc.allWork }" required min="0" readonly/>
        	<label>额内工作量：</label><input type="text" name="rangWork" id="rangeWork" value="${dc.inWork }"  required min="0" readonly/>
        	<label>额外工作量：</label><input type="text" name="moreWork" id="moreWork" value="${dc.inOut }"  required min="0" readonly/>
        	<label>金额：</label><input type="text" name="moeny" value="${dc.allSal }"  id="money"   required min="0" readonly/>
        	<label>单位工作量金额：</label><input type="text" name="moreWork" id="unit_sal" value="${cyearmsg.sal }" required min="0" readonly/>
        </div>
    </div>

    <div class="table_form">

        <table class="table_add_content_title">
            <tr>
                <td class="row_1">年度</td>
                <td class="row_2">类别</td>
                <td class="row_3">项目</td>
                <td class="row_4">完成天数(人数)</td>
                <td class="row_5">课程系数</td>
                <td class="row_6">班级数</td>
                <td class="row_7">人数</td>
                <td class="row_8">新开课程</td>
                <td class="row_9">双语教学</td>
                <td class="row_10">工作量</td>
                <td class="row_11">操作</td>
            </tr>
        </table>
		<c:forEach items="${allWork }" var="w">
		 <div class="row fixWidth" >
            <form name="work_form">
                    <div class="row_1">
                        <input class="radio_btn" name="work.term.id" value="${cyearmsg.lastTerm.id }" ${w.term.id==cyearmsg.lastTerm.id?"checked":"" }  type="radio"/><label>上</label>
                        <input class="radio_btn" name="work.term.id" value="${cyearmsg.nextTerm.id }" ${w.term.id==cyearmsg.nextTerm.id?"checked":"" }  type="radio"/><label >下　</label>
                    </div>
                    <div class="row_2">
                       <select id="work_type"  name="work.type.id" onchange="selectItem(this);">
                  		 	<c:forEach items="${allType }" var="t">
                    			<option value="${t.id }" ${t.id==w.type.id?'selected':'' }>${t.typeName }</option>
                   			</c:forEach>
                		</select>
                    </div>
                    <div class="row_3">
                        <input type="text" name="work.itemName" placeholder="请输入课程名/项目名" value="${w.itemName }"/>
                    </div>
                    <div class="row_4">
                        <input type="number" name="work.comNum" required min="0" value="${w.comNum }" placeholder="完成天数" onblur="compleAllCoe(this);"/>
                    </div>
                    <div class="row_5">
                        <input name="work.coefficient" type="text" value="${w.coefficient }" readonly="readonly" >
                    </div>
                    <div class="row_6">
                    	<input type="number" name="work.classNum" ${w.classNum==0?"disabled":"" } value="${w.classNum }" required min="0" placeholder="班数" onblur="compleBaseCoe(this,true);"/>
                    </div>
                    <div class="row_7">
                    	  <input type="number" name="work.stuNum" ${w.classNum==0?"disabled":"" }  value="${w.stuNum }" required min="0" placeholder="人数" onblur="compleBaseCoe(this,false)"/>
                    </div>
                    <div class="row_8"> 
                        <input class="radio_btn" name="work.isNewClass" ${w.classNum==0?"disabled":"" }  ${w.isNewClass==true?"checked":"" } value="true" type="radio" onchange="compleSpecCoeClass(this,true);"/><label
                            >是</label>
                        <input class="radio_btn" name="work.isNewClass" ${w.classNum==0?"disabled":"" }  ${w.isNewClass==false?"checked":"" } value="false" checked type="radio" onchange="compleSpecCoeClass(this,false);"/><label
                           >否</label>
                    </div>
                    <div class="row_9">
                        <input class="radio_btn" name="work.isTwoLauage" ${w.classNum==0?"disabled":"" } ${w.isTwoLauage==true?"checked":"" } value="true" type="radio" onchange="compleSpecCoeLg(this,true);"/><label
                            >是</label>
                        <input class="radio_btn" name="work.isTwoLauage" ${w.classNum==0?"disabled":"" } ${w.isTwoLauage==false?"checked":"" } value="false" checked type="radio" onchange="compleSpecCoeLg(this,false)"/><label
                            >否</label>
                    </div>
                    <div class="row_10">
                        <input type="text" name="work.allWork" class="allwork" required placeholder="工作量" value="${w.allWork }" />
                    </div>
                <div class="row_11">
                 <input type="hidden" name="work.user.loginId" value="${user.loginId }"/>
                 <c:if test="${w.duser.loginId!=null }">
                 	 <input type="hidden" name="work.duser.loginId" value="${w.duser.loginId }"/>
                 	 <input type="hidden" name="work.fbContent" value="${w.fbContent }"/>
                 	 <input type="hidden" name="work.supplement" value="${w.supplement }"/>
                 </c:if>
                  <input type="hidden" name="work.id" value="${w.id }"/>
                    <div class="same add" onclick="updateWork(this);">
                        <a ><i class="iconfont">&#xe684;</i></a>
                    </div>
                     <div class="same delete" onclick="deleteWork(this,'${w.id}');">
                            <a ><i class="iconfont">&#xe632;</i></a>
                        </div>
                </div>
            </form>
        </div>
		
		</c:forEach>
       
        <div class="result fixWidth page_group">
             <div class="page">
                    <input type="button" onclick="go('${page.currentPage-1}')" value="上一页" ${page.currentPage==1||page.currentPage>page.pageSize?"DISABLED":""}>
                    <input type="button" onclick="go('${page.currentPage+1}')" value="下一页" ${page.currentPage>=page.pageSize?"DISABLED":""}>
                    
                    <select name="page_select" onchange="go(this.value)" >
							<c:forEach var="p" begin="1" end="${page.pageSize }">
								<option value="${p }" ${p==page.currentPage?"SELECTED":"" } >${p}</option>
							</c:forEach>
					</select>
                </div>
        </div>
</div></div>
</body>
<script type="text/javascript">
function go(num){
	window.location.href="work_updatePre.action?page.currentPage="+num;
}
</script>
</html>