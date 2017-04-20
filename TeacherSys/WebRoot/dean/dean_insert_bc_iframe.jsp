<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="../css/style_dean_insertbc.css" rel="stylesheet">
    <link href="../css/style_imp_font.css" rel="stylesheet">
    <title></title>
</head>
<body class="iframeDisplayWh">
<div class="add_base_dia newAddBcWh">
    <form  method="post" onsubmit="return addBaseCoe(this);">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">基础系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <div class="base_coef_table_title alerm_text" id="error_text">注意：所有内容均要填写,且为数字。 
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>班级数</span>
                <span>人数范围</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <div class="base_coef_table_row">
                <span><input type="number" min="0" required="required" name="classNum"></span>
                <span><input type="number" min="0" required="required" name="personIn">-<input type="number" required="required" min="0" name="personOut"></span>
                <span><input type="number" min="0"  required="required" step="0.01" name="coefficient"></span>
                <span>
                    <div class="same add" name="add_base_con_btn">
                        <a onclick="addDeanRow();"><i class="iconfont">&#xe64d;</i></a>
                    </div>
                    <div class="same delete" name="cancel">
                        <a onclick="removeDeanRow(this);"><i class="iconfont">&#xe625;</i></a>
                    </div>
					<input type="hidden" name="yearMsg.years" value="${cyearmsg.years }"/>
                </span>
            </div>
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">
                    <input class="btn_add_base_con_same" style="background-color:#0079f5" type="submit" value="提交"/>
                    <input class="btn_add_base_con_same" style="background-color:#FF3366" type="reset" value="重置"/>
                    <input id="close_dia" class="btn_add_base_con_same" style="background-color:red" type="button" value="关闭"/>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="../js/public_view_control.js"></script>
<script src="../js/dean_ajax.js"></script>
</html>