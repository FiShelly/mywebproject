<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统登录页</title>
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="css/style_login.css">
</head>
<body onload="setTipText('${flag}','${info }');">
<div class="bgblue"></div>
<div class="bggray"></div>
<div class="login_frame">
    <div class="login_frame_head">
        <h1>教师工作量管理系统</h1>
        <a class="icon_title">
            <img src="images/ic_launcher.png">
        </a>
    </div>
    <div class="login_frame_content">
        <form id="login_form" method="post" action="">
            <div class="errortext">
                <label id="error_text">错误提示</label>
            </div>
            <div class="input_type">
                <label for="loginId"><img src="images/use1.png"></label>
                <input class="form_style" autofocus="autofocus" name="loginId" id="loginId" type="text" required oninput="setCustomValidity('');"
                       oninvalid="setCustomValidity('请输入账号')" placeholder="请输入账号" value="${model.loginId }">

            </div>
            <div class="input_type">
                <label for="password"><img src="images/knock.png"></label>
                <input class="form_style" name="pw" id="password" type="password" required oninput="setCustomValidity('');"
                       oninvalid="setCustomValidity('请输入密码')" placeholder="请输入密码">
            </div>
            <div class="input_type">
                <select  class="form_style" id="role" name="role">
                    <option value="0">
                        请选择登录角色
                    </option>
                    <option value="1" ${model.role==1?"selected":"" }>
                        教师
                    </option>
                    <option value="2" ${model.role==2?"selected":"" }>
                        教务员
                    </option>
                    <option value="3" ${model.role==3?"selected":"" }>
                        管理员
                    </option>
                </select>
            </div>
            <div class="input_type">
                <div class="checkboxDiv">
                    <input id="rember_pw" class="checkboxStyle" type="checkbox"/>
                    <label for="rember_pw" class="checklabel">记住密码</label>
                </div>
                <div class="forgetpw">
                    <a href="#" class="checklabel linkforget" id="forgetPw">忘记密码</a>
                </div>
            </div>
            <div class="imgbtn_style">
                <input type="submit"  value="登录" class="sub_btn" id="sub_btn">
            </div>
        </form>
    </div>
</div>
</body>
 <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="js/login.js"></script>
</html>