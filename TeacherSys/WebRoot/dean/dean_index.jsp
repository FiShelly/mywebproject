<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师工作量管理系统教务员端</title>
	<link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="../images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../css/style_public.css"/>
    <link rel="stylesheet" href="../css/style_imp_font.css"/>
    <link rel="stylesheet" href="../css/style_dean_index.css"/>
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
                <li id="mainMenu_add">
                    <span class="iconLiLeft"><i class="iconfont">&#xe695;</i></span>
                    <span>课程系数管理</span>
                    <span class="iconLiRight"><i class="iconfont">&#xe64c;</i></span>
                    <ul class="childMenu" style="display: none">
                        <li id="coe_adjustmen"><span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>课程系数调整</span>
                        </li>
                        <li id="coe_query">
                            <span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            <span>课程系数查询</span>
                        </li>

                    </ul>
                </li>
                <li id="class_sal_man">
                    <span class="iconLiLeft"><i class="iconfont">&#xe65f;</i></span>
                    <span>单位课酬管理</span>
                </li>
                <li id="word_review_add">
                    <span class="iconLiLeft"><i class="iconfont">&#xe63b;</i></span>
                    <span>工作量审核与录入</span>
                </li>
                <li>
                    <span class="iconLiLeft"><i class="iconfont">&#xe661;</i></span>
                    <span>历史工作量查询</span>
                    <span class="iconLiRight"><i class="iconfont">&#xe64c;</i></span>
                    <ul class="childMenu" style="display: none">
                    	<c:forEach  items="${allYears }" var="y">
	                    	 <li onclick="historyquery('${y.years }',this);" id="q_${y.years }"><span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
	                            <span>${y.years }</span>
	                        </li>
                    	</c:forEach>
                    </ul>
                </li>
                <li id="data_count_table">
                    <span class="iconLiLeft"><i class="iconfont">&#xe643;</i></span>
                    <span>数据统计与报表</span>
                    <span class="iconLiRight"><i class="iconfont">&#xe64c;</i></span>
                    <ul class="childMenu" style="display: none">
                    	<c:forEach  items="${allYears }" var="y">
	                        <li onclick="tablecount('${y.years }',this);" id="t_${y.years }"><span class="iconLiLeft"><i class="iconfont">&#xe64d;</i></span>
                            	<span>${y.years }</span>
                        	</li>
                    	</c:forEach>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div id="content">
        <div id="content_head">
            <div class="head_item_left"><a class="menuIcon" id="controlSlide"><i  class="iconfont">&#xe64f;</i></a></div>
            <div class="head_item_left"><a href="#" class="menuIcon"><i id="item_msg" class="iconfont">&#xe628;</i></a>
            	<c:if test="${msgCount!=0 }">
            		<span class="messageStyle" style="background-color: indianred">${msgCount }</span>
            	</c:if>
            </div>
            <div class="head_item_right">
                <div class="headText">欢迎您！<strong>${user.userName }</strong><br><a href="#"  onclick="logoutAccount();">【退出】</a></div>
            </div>
            <div class="head_item_setting"><a class="menuIcon"><i id="item_setting" class="iconfont">&#xe624;</i></a>
            </div>
            <div id="user_setting">
                <div class="user_arrow"></div>
                <div class="user_setting_item">
                    <div class="user_item">
                        <a href="#" id="personal_msg">个人信息</a>
                        <a href="#" id="update_pw_btn">修改密码</a>
                    </div>
                </div>
            </div>
        </div>
        <div id="content_main">
            <iframe id="op_iframe_adjustment" src="">
            </iframe>
            <iframe id="op_iframe_review" src="">
            </iframe>
            <iframe id="op_iframe_query" src="" >
            </iframe>
            <iframe id="op_iframe_historyquery" src="">
            </iframe>
            <iframe id="op_iframe_tablecount" src="" >
            </iframe>
        </div>
    </div>
</div>
<div id="msg_dialog" class="pop_dialog">
    <div class="space"></div>
    <div class="tab_space">
        <table>
            <tr>
                <td class="tab_title" colspan="2">教务员个人信息</td>
            </tr>
            <tr>
                <td>账号</td>
                <td>${user.loginId }</td>
            </tr>
            <tr>
                <td>姓名</td>
                <td>${user.userName }</td>
            </tr>
            <tr>
                <td>出生日期</td>
                <td>${user.birthDate }</td>
            </tr>
            <tr>
                <td>职称</td>
                <td>${user.title.titleName }</td>
            </tr>
            <tr>
                <td>职务</td>
                <td>${user.post.postName }</td>
            </tr>
            <tr>
                <td>状态</td>
                <td>${user.state==true?"激活":"未激活" }</td>
            </tr>
        </table>
    </div>
    <a href="#" id="msg_dia_close">关闭</a>
</div>
<div id="update_pw_dialog" class="pop_dialog newWh">
    <div class="space"></div>
    <h2>修改密码</h2>

    <div class="tab_space_updatepw">
       <form id="update_pw" onsubmit="return updatePwByAjax();">
            <label for="password">输入密码：</label><input id="password" name="user.pw" type="password" required value="" oninput="setCustomValidity('');"
                                       oninvalid="setCustomValidity('请输入密码')"/><br><br>
            <label for="repassword">重复密码：</label><input id="repassword" type="password" required value=""
                                       oninput="setCustomValidity('');"/><br><br>
            <input type="hidden" name="user.loginId" value="${user.loginId }"/>
            <input id="submit" class="btn_style"  type="submit" value="修改"/> 
            <input id="cancel" class="btn_style" type="button" value="取消"/>
        </form>
    </div>
</div>
<div id="add_base_coe" class="pop_dialog">
    <iframe id="add_coe_iframe" src="">
    </iframe>
</div>

<div id="msg_dia" class="pop_msg_dialog" >
    <iframe id="msg_iframe" src="" ></iframe>
</div>
<div id="sal_man_dia" class="pop_sal_dialog">
    <div class="sal_man_dia_content">
        <h2 class="sal_man_dia_title">添加单位课酬</h2>
        <span id="error_text"></span>
        <form id="addSalForm">
            <div style="margin-top: 10px">
                <label for="unitSal">单位课酬: </label><input type="number" id="unitSal" name="msg.sal">
                <input  type="button" value="提交" class="btn_style submit" onclick="addSal('addSalForm','y_${cyearmsg.years }');"/>
                <input type="hidden" value="${cyearmsg.years }" name="msg.years"/>
            </div>
        </form>
        <hr>
        <h2 class="sal_man_dia_title">往年单位课酬</h2>

        <div id="history_unit_sal">
            <div class="history_unit_sal_row">
                <span>
                    年度
                </span>
                <span>
                    单位课酬
                </span>
            </div>
            <c:forEach items="${allYears }" var="y">
            	 <div class="history_unit_sal_row">
            		<span>
                    	${y.years }
	                </span>
	                <span id="y_${y.years }">
	                   	${y.sal }
	                </span>
	             </div>
           	</c:forEach>
            <a href="#" id="unit_dia_close" class="cancel">关闭</a>
        </div>

    </div>
</div>

<div id="review_detail_dia" class=" changepop_dialog">
    <iframe id="review_iframe" src=""></iframe>
</div>

<div id="insert_work_dia" class="changepop_dialog ">
    <iframe id="insert_work_iframe" src=""></iframe>
</div>

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
 <script src="../js/dean_index.js"></script>
 <script src="../js/dean_ajax.js"></script>
</html>