<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="../css/style_dean_insertcoe.css" rel="stylesheet">
    <link href="../css/style_dean_querycoe.css" rel="stylesheet">
    <link href="../css/style_imp_font.css" rel="stylesheet">
    <title>课程系数调整</title>
</head>
<body id="query_coe">
<div class="tab_change_coe_type ">

    <div id="tab_baseCoe" class="tab_item_four tab_change_coe_type_checked">
        <div class="tab_change_coe_type_btn">
            <span>四类基本系数</span>
        </div>
    </div>
    <div id="tab_specCoe" class="tab_item_four">
        <div class="tab_change_coe_type_btn">
            <span>四类特殊系数</span>
        </div>
    </div>
    <div id="tab_select" class="tab_item_four ">
        <div class="tab_change_coe_type_btn">
            <form>
                <label>年度：</label>
                <select name="msg.years" onchange="changeYear();" id="msgyears">
                	<c:forEach items="${yearList }" var="y">
                		<option ${y.years==msg.years?'selected':'' } value="${y.years }">${y.years }</option>
                	</c:forEach>
                </select>
            </form>
        </div>
    </div>
</div>
<div id="base_coe_table"   class="query_base_coef_table public_coef_table cancelMargin">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">基础系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>班级数</span>
                <span>人数范围</span>
                <span>系数</span>
            </div>
			<c:forEach items="${bcList }" var="bc">
				<div class="base_coef_table_row">
	                <span><input type="text"  value="${bc.classNum }"  name="bc.classNum"></span>
             		<span><input type="text" value="${bc.personIn }"  name="bc.personIn">-<input type="text" value="${bc.personOut }"  name="bc.personOut"></span>
             		<span><input type="text" value="${bc.coefficient }"  name="bc.coefficient"></span>
	            </div>
			</c:forEach>
        </div>
    </form>
</div>

<div id="type_coe_table" class="query_type_coef_table public_coef_table">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">类别系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>类别名称</span>
                <span>系数</span>
            </div>
            <c:forEach items="${typeList }" var="type">
	            <div class="base_coef_table_row">
	                 <span><input type="text" value="${type.typeName }" name="type.typeName"></span>
	                <span><input type="text" value="${type.coefficient }"  name="type.coefficient"></span>
	            </div>
            </c:forEach>
        </div>
    </form>
</div>

<div id="title_coe_table" class="query_type_coef_table public_coef_table">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title" >职称系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>职称名</span>
                <span>系数</span>
            </div>
            <c:forEach items="${titleList }" var="title">
            <div class="base_coef_table_row">
                <span><input type="text" value="${title.titleName }" name="title.titleName"></span>
	            <span><input type="text" value="${title.coefficient }"   name="title.coefficient"></span>
            </div>
            </c:forEach>
        </div>
    </form>
</div>

<div id="post_cpe_table" class="query_type_coef_table public_coef_table">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">职务补贴
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>职务名</span>
                <span>系数</span>
            </div>
            <c:forEach items="${postList }" var="post">
             	<div class="base_coef_table_row">
             		<span><input type="text"  value="${post.postName }" name="post.postName"></span>
	                <span><input type="text" value="${post.coefficient }"  name="post.coefficient"></span>
            	</div>
            </c:forEach>
        </div>
    </form>
</div>

<div id="classin_coe_table" style="display: none"  class="query_base_coef_table public_coef_table">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">额内外课时系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>额度</span>
                <span>额内系数</span>
                <span>额外系数</span>
            </div>
            <c:forEach items="${icList }" var="ic">
	            <div class="base_coef_table_row">
	                <span><input type="text" value="${ic.classInNum }" name="ic.classInNum"></span>
	                <span>
	                    <input type="text" value="${ic.coefficientIn }" name="ic.coefficientIn"></span>
	                <span><input type="text" value="${ic.coefficientOut }"   name="ic.coefficientOut"></span>
	            </div>
            </c:forEach>
        </div>
    </form>
</div>

<div id="spec_coe_table" style="display: none" class="query_type_coef_table public_coef_table">
    <form>
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">特殊系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>项目名</span>
                <span>系数</span>
            </div>
            <c:forEach items="${specList }" var="spec">
	            <div class="base_coef_table_row">
	                <span><input type="text" value="${spec.specItemName }" name="spec.specItemName"></span>
	                <span><input type="text" value="${spec.coefficient }"  name="spec.coefficient"></span>
	            </div>
            </c:forEach>
        </div>
    </form>
</div>
</body>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/public_view_control.js"></script>
</html>