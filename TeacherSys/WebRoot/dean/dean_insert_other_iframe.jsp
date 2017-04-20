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
<div class="add_other_dia newAddBcWh">
    <form method="post" onsubmit="return addOtherCoe(this);">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title" id="table_title">基础系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <div class="base_coef_table_title alerm_text" id="alerm_text">注意：所有内容均要填写。
                </div>
            </div>
            <div class="base_coef_table_row">
                <span id="itemName">班级数</span>
                <span>系数</span>
                <span>操作</span>
            </div>
            <div class="base_coef_table_row">
                <span><input type="text" class="flag" required="required"></span>
                <span><input type="number" step="0.01" name="coefficient" required="required"></span>
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
        <input type="hidden" id="action" value="" />
    </form>
</div>
</body>
 <script src="../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../js/public_view_control.js"></script>
    <script src="../js/dean_ajax.js"></script>
</html>