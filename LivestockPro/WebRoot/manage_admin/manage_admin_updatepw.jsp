<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/new_model_check.js">
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_model_regUpd.css"/>

</head>
<body>
<form id="form_updatePassword" onsubmit="return checkContent('update_password')">
    <table>
        <tr>
            <td colspan="3"><strong>管理员信息</strong></td>
        </tr>        
        <tr>
        	<td>
                账号  
            </td>
            <td colspan="2">${param.loginId }</td>
            
        </tr>
       <tr>
            <td>
                密码  
            </td>
            <td colspan="2"><input type="password" id="password1" style="width: 200px" onfocus="clearError('password1')"/></td>
            
        </tr>
        <tr>
            <td>
                重复密码   
            </td>
            <td colspan="2"><input type="password" id="password2" style="width: 200px" onfocus="clearError('password2')"/></td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_password1" style="color: red;display: none">密码不能为空!!</label></td>
        </tr>
         <tr>
            <td colspan="3" style="border: 0"><label id="warning_password" style="color: red;display: none">两次密码不同!!</label></td>
        </tr>
    </table>
    <input type="hidden" value="${param.loginId }" id="loginId"/>
</form>
</body>
</html>