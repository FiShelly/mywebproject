<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/new_model_check.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_model_regUpd.css"/>

</head>
<body onload="initRadioUpdate('${admin.grade}','${ad.province }','${ad.city }','${ad.country }')">
<form id="form_update" onsubmit="return checkContent('update')" method="post" action="${pageContext.request.contextPath }/AdminManageServlet">
    <table>
        <!--2015.11.17-->
        <tr>
            <td>
                账号
            </td>
            <td style="width:255px;">
                ${ad.loginId }
            </td>
        </tr>

        
        <tr>
            <td colspan="3"><strong>管理员级别</strong></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="radio" name="district" value="3"  id="province" style="width:13px;height:15px;vertical-align: bottom;"/>省级

                <input type="radio" name="district" value="2" id="city" style="width:13px;height:15px;vertical-align: bottom;"/>市级

                <input type="radio" name="district" value="1" id="county" style="width:13px;height:15px;vertical-align: bottom;"/>县级
            </td>
        </tr>
        <tr>
            <td colspan="3"><strong>管理员所在行政区</strong></td>
        </tr>
        
        
        <tr>
            <td colspan="3">
                <select id="cmbProvince" name="cmbProvince"></select>
                <select id="cmbCity" name="cmbCity"></select>
                <select id="cmbArea" name="cmbArea"></select>
            </td>
        </tr>
       <tr>
            <td>
                密码  
            </td>
            <td colspan="2"><input type="password" id="password1" name="password" style="width: 200px" onfocus="clearError('password1')"/></td>
            
        </tr>
        <tr>
            <td>
                重复密码   
            </td>
            <td colspan="2"><input type="password" id="password2" style="width: 200px" onfocus="clearError('password2')"/></td>
        </tr>
        <tr>
            <td>行政机构名称: 
            </td>
            <td colspan="2"><input type="text" id="farm_name" name="dept" value="${ad.detail }" onfocus="clearError('farm_name')" style="width: 200px"/> </td>
        </tr>
        <tr id="rowID" style="display:none">
            <td>
                是否为超级管理员
            </td>
            <td colspan="2">
                <input type="radio" ${ad.isSuperAdmin==true?"checked=true":"" } value="是" name="adminType" style="width:13px;height:15px;vertical-align: bottom;"/>是
                <input type="radio" ${ad.isSuperAdmin==false?"checked=true":"" } value="否" name="adminType" checked="checked" style="width:13px;height:15px;vertical-align: bottom;"/>否
            </td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_message" style="color: red;display: none">请选择详细的行政区!</label></td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_password" style="color: red;display: none">两次密码不同!</label></td>
        </tr>
    </table>
    	<input type="hidden" name = "action" id = "action" value="update"/>	
    	<input type="hidden" name = "number" id = "number" value="${ad.loginId }"/>	
    	<input type="hidden" name = "oldpw" id = "oldpw" value="${ad.password }"/>	
		<input type="hidden" name = "currentPage" id="currentPage" value="${parm.currentPage}"/>
</form>
</body>
</html>