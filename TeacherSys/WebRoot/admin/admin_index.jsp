<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统管理员端</title>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="../images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../css/style_imp_font.css">
    <link rel="stylesheet" href="../css/style_public.css">
    <link rel="stylesheet" href="../css/style_dean_index.css">
    <link rel="stylesheet" href="../css/style_admin_index.css"/>
   
</head>
<body>
<div id="main">
    <div id="leftMenu">
        <div class="headIcon">
            <a href="http://www.zhku.edu.cn" target="_blank"><img src="../images/ic_launcher.png"/></a>
            <label>教师工作量管理系统</label>
        </div>
        <div class="menuList">
            <ul class="mainMenu">
                <li>
                    <span class="iconLiLeft"><i class="iconfont">&#xe650;</i></span>
                    <span>账号创建</span>
                    <span class="iconLiRight"><i class="iconfont">&#xe64c;</i></span>
                    <ul class="childMenu" style="display: none">
                        <li id="teacher_add"><span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>教师账号创建</span>
                        </li>
                        <li id="teacher_list">
                            <span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>教师账号列表</span>
                        </li>
                        <li id="dean_add">
                            <span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>教务员账号创建</span>
                        </li>
                        <li id="dean_list">
                            <span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>教务员账号列表</span>
                        </li>
                    </ul>
                </li>
                <li id="year_term">
                    <span class="iconLiLeft"><i class="iconfont">&#xe661;</i></span>
                    <span>年度与学期信息管理</span>
                </li>
               
            </ul>
        </div>
    </div>
    <div id="content">
        <div id="content_head">
            <div class="head_item_left"><a class="menuIcon" id="controlSlide"><i class="iconfont">&#xe64f;</i></a></div>

            <div class="head_item_right">
                <div class="headText">欢迎您！<strong>${admin.loginId}</strong><br><a href="#" onclick="logoutAccount();">【退出】</a></div>
            </div>
            <div class="head_item_setting"><a class="menuIcon"><i id="item_setting" class="iconfont">&#xe624;</i></a>
            </div>
        </div>
        <div id="user_setting">
            <div class="user_arrow"></div>
            <div class="user_setting_item">
                <div class="user_item">
                    <!--<a href="#" id="personal_msg">个人信息</a>-->
                    <a href="#" id="update_pw_btn">修改密码</a>
                </div>
            </div>
        </div>

        <div id="content_main">
            <iframe id="add_iframe" src="">

            </iframe>
            <iframe id="query_iframe" src="" >

            </iframe>
        </div>
    </div>
</div>
<div id="update_pw_dialog" class="pop_dialog newWh">
    <div class="space"></div>
    <h2>修改密码</h2>

    <div class="tab_space_updatepw">
        <form id="update_pw">
            <label for="password">输入密码：</label><input id="password" type="password" required value=""
                                                      oninput="setCustomValidity('');"
                                                      oninvalid="setCustomValidity('请输入密码')"/><br><br>
            <label for="repassword">重复密码：</label><input id="repassword" type="password" required value=""
                                                        oninput="setCustomValidity('');"/><br><br>
            <input id="button" onclick="updatePw('${admin.loginId}');" class="btn_style submit" type="submit" value="修改"/>
            <input id="cancel" class="btn_style cancel" type="button" value="取消"/>
        </form>
    </div>
</div>
<div id="sal_man_dia" class="pop_sal_dialog">
    <div class="sal_man_dia_content">
        <h2 class="sal_man_dia_title">添加年度学期信息</h2>
        <span id="error_text" class="error_text">你输入的年份已存在，请确认后重新输入。</span>
        <form>
            <div style="margin-top: 10px">
                <label for="unitSal">年度: </label><input  type="number" id="yearmsg" name="msg.year">
                <input  type="button" id="sub_year" onclick="checkYearExist();" value="提交" class="btn_style submit"/>
            </div>
        </form>
        <hr>
        <h2 class="sal_man_dia_title">年度信息列表</h2>

        <div id="history_unit_sal">
            <div class="history_unit_sal_row">
                <span>年度</span>
                <span>创建时间</span>
                <span>当前年度</span>	
            </div>
            <c:forEach items="${allYears }" var="y">
            <div class="history_unit_sal_row">
                <span>${y.years }</span>
                <span>${y.date }</span>
                <span>
                	<c:choose>
                		<c:when test="${y.isCurrent }">
                			是
                		</c:when>
                		<c:otherwise>
                			<a href="#" onclick="changeCurYear('${y.years }' );"  class="history_link">否</a>
                		</c:otherwise>
                	</c:choose>
                </span>
            </div>
            </c:forEach>
            <a href="#" id="unit_dia_close" class="cancel">关闭</a>
        </div>
    </div>
</div>
<input type="hidden" id="cy" value="${cyearmsg.years}"/>
<div id="shadow"></div>
<div id="loading_dia" class="loading_dialog_content loading_icon" >
    数据正在添加，请稍等...
</div>
<div id="success_dia" class="loading_dialog_content gou_icon">
    <span>数据添加成功.</span>&nbsp;<a id="sc_dia_close">[关闭]</a>
</div>
<div id="failed_dia" class="loading_dialog_content cha_icon">
    <span>数据添加成功.</span>&nbsp;<a id="failed_dia_close">[关闭]</a>
</div>
</body>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/public_view_control.js"></script>
    <script src="../js/admin_index.js"></script>
    <script src="../js/admin_ajax.js"></script>
    <script type="text/javascript">
    	
    </script>
</html>