<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教师端工作量录入</title>
    <link rel="stylesheet" href="../css/style_dean_copywork.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
</head>
<body>
<div class="tab_space_add">
    <div class="tab_space_add_title">
    	<h1>工作量复制</h1>
        <div class="re_close_btn cancel" name="cancel">
            <a id="close_copy_dia"><i class="iconfont fontSize">&#xe625;</i></a>
        </div>
    </div>
    <div class="table_form">
        <table class="table_copy">
            <thead>
                <tr>
                    <th class="copy_row_1">学期</th>
                    <th class="copy_row_2">类别
                        <span class="search_input">
                            <input class="searchType" type="text" onblur="setBorderColor(this,'#CCC');"
                                   onfocus="setBorderColor(this,'#3385ff')" />
                            <i class="fontSize iconfont">&#xe633;</i>
                        </span>
                    </th>
                    <th class="copy_row_3">项目
                        <span class="search_input">
                            <input class="searchType" type="text" onblur="setBorderColor(this,'#CCC');"
                                   onfocus="setBorderColor(this,'#3385ff')"/>
                            <i class="fontSize iconfont">&#xe633;</i>
                        </span>
                    </th>
                    <th class="copy_row_4">完成数目</th>
                    <th class="copy_row_5">课程系数</th>
                    <th class="copy_row_6">人数</th>
                    <th class="copy_row_7">班级数</th>
                    <th class="copy_row_8">新开课程</th>
                    <th class="copy_row_9">双语教学</th>
                    <th class="copy_row_10">工作量</th>
                    <th class="copy_row_11">操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${allWork }" var="w">
            <tr id="tr_${w.id }">
                <td class="copy_row_1">${w.term.isLastTerm==true?"上":"下" }</td>
	            <td class="copy_row_2">${w.type.typeName }</td>
	            <td class="copy_row_3">${w.itemName }</td>
	            <td class="copy_row_4">${w.comNum }</td>
	            <td class="copy_row_5">${w.coefficient }</td>
	            <td class="copy_row_6">${w.classNum }</td>
	            <td class="copy_row_7">${w.stuNum }</td>
	            <td class="copy_row_8">${w.isNewClass==true?"是":"否" }</td>
	            <td class="copy_row_9">${w.isTwoLauage==true?"是":"否" }</td>
	            <td class="copy_row_10">${w.allWork}</td>
                <td class="copy_row_11">
                	<div class="same add" onclick="selectData('tr_${w.id}')">
                    	<a ><i class="iconfont">&#xe684;</i></a>
                	</div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/public_view_control.js"></script>
<script type="text/javascript" src="../js/dean_copy.js"></script>
</html>
