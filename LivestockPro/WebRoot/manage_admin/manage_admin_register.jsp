<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/new_model_area.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/new_model_check.js">
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_model_regUpd.css"/>

</head>
<body onload="addressInit('cmbProvince','cmbCity','cmbArea');initRadio('${admin.grade}','${admin.province }','${admin.city }','${admin.country }')">
<!--
    initRadio参数解释，第一个参数表示管理员的级别为省级，3为省级，2为市级，1为县级
-->
<form id="form_register" method="post" action="${pageContext.request.contextPath }/AdminManageServlet" onsubmit="return checkContent('register')">
    <table>
        <tr>
            <td>
                账号   
            </td>
            <td colspan="2"><input type="text" id="number" name="number" style="width: 170px" onblur="checkId(this.value);" onfocus="clearError('number')"/></td>
            
        </tr>
        <tr>
            <td>
                密码   
            </td>
            <td colspan="2"><input type="password" id="password1" name="password" style="width: 170px" onfocus="clearError('password1')"/></td>
        </tr>
        <tr>
            <td>
                重复密码   
            </td>
            <td colspan="2"><input type="password" id="password2" style="width: 170px" onfocus="clearError('password2')"/></td>
        </tr>
        <tr>
            <td colspan="3"><strong>管理员级别</strong></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="radio" name="district" value="3" ${admin.grade==3?"checked=true":"" } id="province" style="width:13px;height:15px;vertical-align: bottom;"/>省级

                <input type="radio" name="district" value="2" ${admin.grade==2?"checked=true":"" } id="city" style="width:13px;height:15px;vertical-align: bottom;"/>市级

                <input type="radio" name="district" value="1" ${admin.grade==1?"checked=true":"" } id="county" style="width:13px;height:15px;vertical-align: bottom;"/>县级
            </td>
        </tr>
        <tr>
            <td colspan="3"><strong>管理员所在行政区</strong></td>
        </tr>
        
        
        <tr>
            <td colspan="3">
                <select id="cmbProvince"  name="cmbProvince" disabled="disabled"></select>
                <select id="cmbCity" name="cmbCity" disabled="disabled"></select>
                <select id="cmbArea" name="cmbArea" disabled="disabled"></select>
            </td>
        </tr>
       
        <tr>
            <td>行政机构名称: 
            </td>
            <td colspan="2"><input type="text" id="farm_name" name="dept" onfocus="clearError('farm_name')" style="width: 170px"/> </td>
        </tr>
        <tr id="rowID" style="display:none">
            <td>
                是否为超级管理员
            </td>
            <td colspan="2">
                <input type="radio" name="adminType" value="是" style="width:13px;height:15px;vertical-align: bottom;"/>是
                <input type="radio" name="adminType" value="否" checked style="width:13px;height:15px;vertical-align: bottom;"/>否
            </td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_password1" style="color: red;display: none">密码不能为空!</label></td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_password" style="color: red;display: none">两次密码不同!</label></td>
        </tr>
        <tr>
            <td colspan="3" style="border: 0"><label id="warning_message" style="color: red;display: none">请选择详细的行政区!</label></td>
        </tr>
    </table>
    	<input type="hidden" name = "action" id = "action" value="insert"/>	
		<input type="hidden" name = "currentPage" id="currentPage" value="${parm.currentPage}"/>
</form>
</body>
</html>