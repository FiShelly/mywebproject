<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教师端工作量录入</title>
    <link rel="stylesheet" href="../css/style_dean_insertwork.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
</head>
<body onload="getTypeByAjax('${cyearmsg.years}');getBcByAjax('${cyearmsg.years}');getSpecCoeByAjax('${cyearmsg.years}');getIcByAjax('${cyearmsg.years}');">
<div class="tab_space_add" >
    <div class="tab_space_add_title"><h1>${param.tname }老师的工作量录入</h1>
		<span class = "error_text" >填入的信息有误，请确认后重新提交（如：项目名不能为空，完成数目，班级数，人数须大于0）</span>
        <div class="re_close_btn cancel" name="cancel">
            <a id="close"><i class="iconfont fontSize">&#xe625;</i></a>
        </div>
    </div>
    <div class="table_form">
        <table class="table_add_content_title">
            <tr>
                <td class="di_row_1">学期</td>
                <td class="di_row_2">类别</td>
                <td class="di_row_3">项目</td>
                <td class="di_row_4">完成数目</td>
                <td class="di_row_5">课程系数</td>
                <td class="di_row_6">班级数</td>
                <td class="di_row_7">人数</td>
                <td class="di_row_8">新开课程</td>
                <td class="di_row_9">双语教学</td>
                <td class="di_row_10">工作量</td>
                <td class="di_row_11">操作</td>
            </tr>
        </table>

        <div class="row fixWidth" style="display: none" >
            <form name='work_form'>
                <div class="di_row_1">
                    <input class="radio_btn" name="term" value="${cyearmsg.lastTerm.id }" checked type="radio"/><label>上</label>
                    <input class="radio_btn" name="term" value="${cyearmsg.nextTerm.id }" type="radio"/><label>下　</label>
                </div>
                <div class="di_row_2">
                    <select name="type.id" onchange="selectItem(this);">
                        </select>
                </div>
                <div class="di_row_3">
                    <input type="text" name="itemName" placeholder="请输入课程名/项目名"/>
                </div>
                <div class="di_row_4">
                    <input type="text" name="comNum" required min="0" placeholder="完成天数" onblur="compleAllCoe(this);"/>
                </div>
                <div class="di_row_5">
                    <input name="coefficient" type="text" value="1" readonly="readonly">
                </div>
                <div class="di_row_6">
                     <input type="text" name="classNum" required min="0" placeholder="班级数" onblur="compleBaseCoe(this,true);"/>
                </div>
                <div class="di_row_7">
                	<input type="text" name="stuNum" required min="0" placeholder="人数" onblur="compleBaseCoe(this,false)"/>
                </div>
                <div class="di_row_8">
                    <input class="radio_btn" name="isNewClass" type="radio" value="true" onchange="compleSpecCoeClass(this,true);"/><label
                        >是</label>
                    <input class="radio_btn" name="isNewClass" checked type="radio" value="false" onchange="compleSpecCoeClass(this,false);"/><label
                        >否</label>
                </div>
                <div class="di_row_9">
                    <input class="radio_btn" name="isTwoLauage" type="radio" value="true" onchange="compleSpecCoeLg(this,true);"/><label
                        >是</label> 
                    <input class="radio_btn" name="isTwoLauage" checked value="false" type="radio" onchange="compleSpecCoeLg(this,false)"/><label
                        >否</label>
                </div>
                <div class="di_row_10">
                    <input type="text" name="allWork" readonly="readonly" required placeholder="工作量"/>
                </div>
                <div class="di_row_11">
                	 <input type="hidden" name="user.loginId" value="${param.tloginId }"/>
                	 <input type="hidden" name="queryParam" value="${param.tloginId }"/>
                    <div class="same copy" onclick="copyRowContent(this);">
                        <a ><i class="iconfont">&#xe63a;</i></a>
                    </div>
                    <div class="same add">
                        <a onclick="addRow();"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                    <div class="same delete">
                        <a onclick="removeRow(this);"><i class="iconfont">&#xe625;</i></a>
                    </div>
                </div>
            </form>
        </div>
        <div class="row fixWidth" id="div_1">
            <form name='work_form'>
                <div class="di_row_1">
                    <input class="radio_btn" name="term" value="${cyearmsg.lastTerm.id }" checked type="radio"/><label>上</label>
                    <input class="radio_btn" name="term" value="${cyearmsg.nextTerm.id }" type="radio"/><label>下　</label>
                </div>
                <div class="di_row_2">
                    <select name="type.id" onchange="selectItem(this);">
                    </select>
                </div>
                <div class="di_row_3">
                    <input type="text" name="itemName" placeholder="请输入课程名/项目名"/>
                </div>
                <div class="di_row_4">
                    <input type="text" name="comNum" required min="0" placeholder="完成天数" onblur="compleAllCoe(this);"/>
                </div>
                <div class="di_row_5">
                    <input name="coefficient" type="text" value="1" readonly="readonly">
                </div>
                <div class="di_row_6">
               	 	<input type="text" name="classNum" required min="0" placeholder="班级数"  onblur="compleBaseCoe(this,true);"/>
                </div>
                <div class="di_row_7">
                    <input type="text" name="stuNum" required min="0" placeholder="人数" onblur="compleBaseCoe(this,false)"/>
                </div>
                <div class="di_row_8">
                    <input class="radio_btn" name="isNewClass" type="radio" value="true" onchange="compleSpecCoeClass(this,true);"/><label
                        >是</label>
                    <input class="radio_btn" name="isNewClass" checked type="radio" value="false" onchange="compleSpecCoeClass(this,false);"/><label
                        >否</label>
                </div>
                <div class="di_row_9">
                    <input class="radio_btn" name="isTwoLauage" type="radio" value="true" onchange="compleSpecCoeLg(this,true);"/><label
                        >是</label>
                    <input class="radio_btn" name="isTwoLauage" checked type="radio" value="false" onchange="compleSpecCoeLg(this,false)"/><label
                        >否</label>
                </div>
                <div class="di_row_10">
                    <input type="text" name="allWork" required placeholder="工作量" readonly="readonly"/>
                </div>
                <div class="di_row_11">
               	 	<input type="hidden" name="user.loginId" value="${param.tloginId }"/>
               	 	<input type="hidden" name="queryParam" value="${param.tloginId }"/>
                    <div class="same copy" onclick="copyRowContent(this);">
                        <a ><i class="iconfont">&#xe63a;</i></a>
                    </div>
                    <div class="same add">
                        <a onclick="addRow();"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                    <div class="same delete">
                        <a onclick="removeRow(this);"><i class="iconfont">&#xe625;</i></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="result fixWidth">
        <label>总工作量：</label><input type="text" name="aallWork" id="aallWork" value="0" required min="0" readonly/>
        <label>额内工作量：</label><input type="text" name="rangWork" id="rangeWork" value="0"  required min="0" readonly/>
        <label>额外工作量：</label><input type="text" name="moreWork" id="moreWork" value="0"  required min="0" readonly/>
        <label>金额：</label><input type="text" name="moeny" value="0"  id="money"  required min="0" readonly/>
        <label>单位工作量金额：</label><input type="text" name="moreWork" id="unit_sal" value="${cyearmsg.sal }" required min="0" readonly/>
        <input type="button" value="合计" onclick="computeAllWork('${param.tcoe}','${param.pcoe}','${cyearmsg.sal}');"/>
    </div>
    <div class="btn_group" onclick="subWorkData();">
        <a>提交</a>
    </div>
</div>
<div id="copy_dia" class="copy_dia">
    <iframe id="copy_iframe" src=""></iframe>
</div>
<div id="shadow"></div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../js/public_view_control.js"></script>
    <script type="text/javascript" src="../js/dean_insertwork_ajax.js"></script>
</html>