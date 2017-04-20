<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="../css/style_dean_insertbc.css" rel="stylesheet">
    <link href="../css/style_imp_font.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div id="classin_coe_table"  class="add_limit_dia public_coef_table">
    <form  method="post" onsubmit="return addClassInCoe(this);">
        <div >
            <div class="base_coef_table_row">
                <div class="base_coef_table_title">额内外课时系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <div class="base_coef_table_title alerm_text" id="alerm_text">请填写完整的额内外系数
                </div>
            </div>
            <div class="base_coef_table_row">
                <span>额度</span>
                <span>额内系数</span>
                <span>额外系数</span>
            </div>
            <div class="base_coef_table_row">
                <span><input type="number"  name="ic.classInNum"></span>
                <span>
                    <input type="number" step="0.01" name="ic.coefficientIn"></span>
                <span><input type="number" step="0.01"  name="ic.coefficientOut"></span>
               <input type="hidden" name="ic.yearMsg.years" value="${cyearmsg.years }"/>
               <input type="hidden" name="ic.years" value="${cyearmsg.years }"/>
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