<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="../css/style_imp_font.css" rel="stylesheet">
    <link href="../css/style_dean_insertcoe.css" rel="stylesheet">
    <title>课程系数调整</title>
    <style type="text/css">
    	.btn_copy_coe{
    		width: 150px;
    		float: right;
    	}
    	.btn_copy_coe:hover {
			background-color:#0079f5; 
		}
    </style>
</head>
<body>
<div class="tab_change_coe_type ">
    <div id="tab_baseCoe" class="tab_item_four tab_change_coe_type_checked">
        <div class="tab_change_coe_type_btn">
            <span>四类基本系数</span>
        </div>
    </div>
    <div id="tab_specCoe" class="tab_item_four">
        <div class="tab_change_coe_type_btn">
            <span>两类特殊系数</span>
        </div>
    </div>
    <c:if test="${fn:length(bcList)==0 }">
    <div id="tab_specCoe" class="tab_item_four btn_copy_coe">
        <div class="tab_change_coe_type_btn btn_copy_coe">
            <a href="coe_copyLastYearCoe.action">沿用去年课程系数</a> 
        </div>
    </div>
    </c:if>
</div>
<div id="base_coe_table"   class="base_coef_table public_coef_table">
   
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">基础系数
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addBase"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>班级数</span>
                <span>人数范围</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${bcList }" var="bc">
	             <form id="bcf_${bc.id }" > 
			           <div class="base_coef_table_row">
			                <span><input type="text"  value="${bc.classNum }"  name="bc.classNum"></span>
			                <span><input type="text" value="${bc.personIn }"  name="bc.personIn">-<input type="text" value="${bc.personOut }"  name="bc.personOut"></span>
			                <span><input type="text" value="${bc.coefficient }"  name="bc.coefficient"></span>
			                <span>
			                    <div class="same add" onclick="updateCoe('bcf_${bc.id }','updateBaseCoe');">
			                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
			                    </div>
			                    <div class="same delete" onclick="deleteCoe('${bc.id }','bcf_${bc.id }','deleteBaseCoe')">
			                        <a><i class="iconfont">&#xe632;</i></a>
			                    </div>
			                </span>
			            </div>
			            <input type="hidden" name="bc.yearMsg.years" value="${cyearmsg.years }">
			            <input type="hidden" name="bc.id" value="${bc.id }">
		          </form>
            </c:forEach>
        </div>
</div>

<div id="type_coe_table" class="type_coef_table public_coef_table">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">类别系数
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addType"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>类别名称</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${typeList }" var="type">
            <form id="tf_${type.id}">
				<div class="base_coef_table_row">
	                <span><input type="text" value="${type.typeName }" name="type.typeName"></span>
	                <span><input type="text" value="${type.coefficient }"  name="type.coefficient"></span>
	                <span>
	                    <div class="same add" onclick="updateCoe('tf_${type.id}','updateTypeCoe');">
	                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
	                    </div>
	                    <div class="same delete" onclick="deleteCoe('${type.id }','tf_${type.id}','deleteTypeCoe')">
	                        <a><i class="iconfont">&#xe632;</i></a>
	                    </div>
	                </span>
            	</div>
            	<input type="hidden" name="type.yearMsg.years" value="${cyearmsg.years }">
			    <input type="hidden" name="type.id" value="${type.id }">
			</form>
            </c:forEach>
        </div>
</div>

<div id="title_coe_table" class="type_coef_table public_coef_table">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title" >职称系数
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addTitle"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>职称名</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${titleList }" var="title">
            <form id="title_${title.id }">
            	<div class="base_coef_table_row">
	                <span><input type="text" value="${title.titleName }" name="title.titleName"></span>
	                <span><input type="text" value="${title.coefficient }"   name="title.coefficient"></span>
	                <span>
	                    <div class="same add" onclick="updateCoe('title_${title.id }','updateTitleCoe')">
	                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
	                    </div>
	                    <div class="same delete" onclick="deleteCoe('${title.id }','title_${title.id }','deleteTitleCoe')">
	                        <a><i class="iconfont">&#xe632;</i></a>
	                    </div>
	                </span>
            	</div>
            	<input type="hidden" name="title.yearMsg.years" value="${cyearmsg.years }">
			    <input type="hidden" name="title.id" value="${title.id }">
			 </form>
            </c:forEach>
        </div>
</div>

<div id="post_cpe_table" class="type_coef_table public_coef_table">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">职务补贴
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addPost"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>职务名</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${postList }" var="post">
                <form id="post_${post.id }" >
            	<div class="base_coef_table_row">
	                <span><input type="text"  value="${post.postName }" name="post.postName"></span>
	                <span><input type="text" value="${post.coefficient }"  name="post.coefficient"></span>
	                <span>
	                    <div class="same add" onclick="updateCoe('post_${post.id }','updatePostCoe')">
	                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
	                    </div>
	                    <div class="same delete" onclick="deleteCoe('${post.id }','post_${post.id }','deletePostCoe')">
	                        <a><i class="iconfont">&#xe632;</i></a>
	                    </div>
	                </span>
            	</div>
            	<input type="hidden" name="post.yearMsg.years" value="${cyearmsg.years }">
			    <input type="hidden" name="post.id" value="${post.id }">
			    </form>
            </c:forEach>
        </div>
</div>

<div id="classin_coe_table" style="display: none"  class="new_classin_table public_coef_table">
   
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">额内外课时系数
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addClassIn"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>额度</span>
                <span>额内系数</span>
                <span>额外系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${icList }" var="ic">
             <form id="ic_${ic.years}"> 
	            <div class="base_coef_table_row">
	                <span><input type="text" value="${ic.classInNum }" name="ic.classInNum"></span>
	                <span>
	                    <input type="text" value="${ic.coefficientIn }" name="ic.coefficientIn"></span>
	                <span><input type="text" value="${ic.coefficientOut }"   name="ic.coefficientOut"></span>
	                <span>
	                    <div class="same add"  onclick="updateCoe('ic_${ic.years }','updateClassInCoe')">
	                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
	                    </div>
	                    <div class="same delete" onclick="deleteCoe('${ic.years }','ic_${ic.years }','deleteClassInCoe')">
	                        <a><i class="iconfont">&#xe632;</i></a>
	                    </div>
	                </span>
	            </div>
	            <input type="hidden" name="ic.yearMsg.years" value="${cyearmsg.years }">
			    <input type="hidden" name="ic.years" value="${ic.years }">
	         </form>
            </c:forEach>
        </div>
</div>

<div id="spec_coe_table" style="display: none" class="type_coef_table public_coef_table">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">特殊系数
                    <div class="same add base_coef_title_add_btn" >
                        <a id="addSpec"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>项目名</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <c:forEach items="${specList }" var="spec">
            <form id="spec_${spec.id}">
	            <div class="base_coef_table_row">
	                <span><input type="text" value="${spec.specItemName }" name="spec.specItemName"></span>
	                <span><input type="text" value="${spec.coefficient }"  name="spec.coefficient"></span>
	                <span>
	                    <div class="same add" onclick="updateCoe('spec_${spec.id }','updateSpecCoe')">
	                        <a onclick=""><i class="iconfont">&#xe650;</i></a>
	                    </div> 
	                    <div class="same delete" onclick="deleteCoe('${spec.id }','spec_${spec.id }','deleteSpecCoe')">
	                        <a><i class="iconfont">&#xe632;</i></a>
	                    </div>
	                </span>
	            </div>
	            <input type="hidden" name="spec.yearMsg.years" value="${cyearmsg.years }">
			    <input type="hidden" name="spec.id" value="${spec.id }">
	        </form>
            </c:forEach>
        </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/public_view_control.js"></script>
<script src="../js/dean_ajax.js"></script>
</html>