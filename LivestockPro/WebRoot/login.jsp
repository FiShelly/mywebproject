<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>全新版登陆解密第一版</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/version3.css" />
<script src="${pageContext.request.contextPath }/js/version1.js" language="javascript" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/checkCode.js" language="javascript" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/placeholder.js" language="javascript" type="text/javascript"></script>
<script language="JavaScript" charset="utf-8">	
		
	function selectLogin() {
		var select = document.login.lg_option;
		var type = select[select.selectedIndex].text;
		if(type == "用户") {
			document.forms[0].action =  "LoginServlet?action=userLogin";
		} else if(type == "工作人员") {
			document.forms[0].action =  "LoginServlet?action=adminLogin";
		} else {
			document.forms[0].action =  "LoginServlet?action=sadminLogin";
		}
	}
	
	function init(){
		createCode();
		if("${flag}" == "0"){
			//账号或者密码错误
			document.getElementById("error").value="${info}";
			document.getElementById("error").style.color="#ef9783";
			document.getElementById("error").style.size="10px";
		} 

		if("${param.info}" == "re_login"){
			document.getElementById("error").value="您还未登录，请先登录！";
			document.getElementById("error").style.color="#ef9783";
			document.getElementById("error").style.size="10px";
		}
	}

</script>
<!--<script src="key.js" language="javascript" type="text/javascript"></script>-->
</head>
<body onload="init();initPlaceHolders();">
<!--banner位置-->
<div class="banner">
	<font>畜牧检疫防疫信息时空分析及管理系统</font>
</div>
<!--放入背景图片-->
<div>
	<!--放入阴影图片-->
    <div style="width:200px;height:200px;position:absolute;top:50%;left:50%;margin-left:-175px;margin-top:-98px;">
    	<img src="${pageContext.request.contextPath }/image/shadow_03.png" width="540" />
    </div>
    <div id="login">
    	<img src="${pageContext.request.contextPath }/image/background_03.jpg" class="img_bg" />
    </div>
    <div id="login">    
    	<img src="${pageContext.request.contextPath }/image/user3_03.png" class="img_head" />
    </div>
    <!--加入输入框-->
    <div id="login">

   		<form action="" method="post" name="login" onsubmit="return checkLogin();">
        <table>
          <tr style="width:288px;height:30px;" >
            <td colspan="2" align="center"><input type="text" placeholder="输入账号" maxlength="26" name="lg_id" id="lg_id"/><div style="margin-left:-20px;display:inline;padding-top:133px;"><img src="${pageContext.request.contextPath }/image/use1.png" width="15px" style=" vertical-align:middle;" /></div></td>
            <td></td>
          </tr>
          <tr style="width:288px;height:30px;">
            <td  colspan="2"><input type="password" maxlength="26" placeholder="输入密码" name="lg_password" id="lg_password"/><div style="margin-left:-20px;display:inline;"><img src="${pageContext.request.contextPath }/image/knock.png" width="17" style="vertical-align:middle" /></div></td>
            <td></td>
          </tr>
          <tr style="width:288px;height:30px;">
            <td align="left"><input type="text" maxlength="4" placeholder="输入验证码" name="lg_checkCode" id="lg_checkCode" id="lg_checkCode"/>
          <input type="button" id="checkCode" onclick="createCode()"/></td>
            <td align="right">
            <select  name="lg_option">
                <option selected="selected">用户</option>
              	<option>工作人员</option>
              	<option>管理员</option>
          </select></td>
          </tr>
          <tr style="width:288px;height:30px;">
            <td colspan="2"><input type="submit" id="submit_bt" value="登录"  onclick="selectLogin()" style="background-color:#12a686;display:block;font-size:12px;font-family:微软雅黑;color:white;"/></td>
            <td></td>
          </tr>
          <tr style="width:288px;height:30px;">
            <td align="left" valign="bottom" colspan="2">
            	<input type="text" value=" " disabled="disabled" id="error" style="height:20px;background-color:#ECECEC;"/></td>
          </tr>
        </table>
        </form>
   </div>
</div>
</body>
</html>
