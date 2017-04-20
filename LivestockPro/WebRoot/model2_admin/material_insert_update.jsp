<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model3_area.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ad_model2_checkContent.js"></script>

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            width: 100%;
            height: 100%;
        }

        #table {
            border-collapse: collapse;
            width: 99%;
            height: 99%;

            text-align: center;
            table-layout: fixed;

        }

        #table tr td {
            border: 1px solid #aaaaaa;
            text-align: center;
            height: 25px;
            width: 100px;
            font-family: 宋体;
            font-size: 12px;
            color: black;
            opacity: 0.85;
        }

        #table tr td input {
            width: 99%;
            height: 100%;
            /*border:0;*/
        }

        input:hover {
            border: 1px solid #A9D7CD;
        }
    </style>
</head>
<body onload="addressInit('cmbProvince', 'cmbCity', 'cmbArea','${sup.province }','${sup.city }','${sup.country }');">
<div style="text-align:center;width:100%;height:100%;">
													  
    <form id="supplies_op" name="supplies_op" action="${pageContext.request.contextPath}/SuppliesServlet" method="post" onsubmit="return checkContent();">
        <table align="center" id="table">
            <tr>
                <td style="background-color:#f0f0f0;width:92px;">
                    储备点编号
                </td>
                <td>
                    <input type="text" id="reserve_number" name="reserve_number" ${sup.reserveId != null ?'readonly':''  } value="${sup.reserveId }" onfocus="clearError('reserve_number')"/>
                </td>
                 <td style="background-color:#f0f0f0;width:92px;">
                    储备点名称
                </td>
                <td>
                    <input type="text" id="supppliesName" name="supppliesName" value="${sup.name }" onfocus="clearError('supppliesName')"/>
                </td>
                
            </tr>
            <tr>
            <td style="background-color:#f0f0f0;width:92px;">
                    负责人
                </td>
                <td>
                    <input type="text" id="person" value="${sup.head }" name="person" onfocus="clearError('person')"/>
                </td>
            
                <td style="background-color:#f0f0f0;width:92px;">
                    联系电话
                </td>
                <td>
                    <input type="text" id="phone_number" name="phone_number" value="${sup.phoneNum }" onfocus="clearError('phone_number')"/>
                </td>
              
              
            </tr>
            <tr>
                <td style="background-color:#f0f0f0;width:92px;">
                    注册时间
                </td>
                <td>
                    <input type="text" id="date" name="date" onclick="laydate()" value="${sup.registDate }" readonly="readonly"
                           onfocus="clearError('date')"/>
                </td>

                <td style="background-color:#f0f0f0;width:92px;">
                    职位
                </td>
                <td>
                    <input type="text" id="job" name="job" value="${sup.position }" onfocus="clearError('job')"/>
                </td>

            </tr>


            <tr>
                <td rowspan="2" style="background-color:#f0f0f0;width:92px;">
                    储备点
                </td>
                <td colspan="3" style="text-align: left">
                    <select id="cmbProvince" name="cmbProvince"></select>
                    <select id="cmbCity" name="cmbCity"></select>
                    <select id="cmbArea" name="cmbArea"></select>
                </td>

            </tr>
            <tr>
                <td colspan="3">
                    <input type="text" id="factor" value="${sup.detail }" name="factor" onfocus="clearError('factor')" placeholder="(镇/街道/详细地址)"/>
                </td>
            </tr>
 			<tr>
                
                <td style="background-color:#f0f0f0;width:92px;">
                    管理单位
                </td>
                <td colspan="3" style="text-align: left">
                    <input type="text" id="manager_unit" name="manager_unit" value="${sup.managementstation }"  onfocus="clearError('manager_unit')"/>
                </td>
                
            </tr>

        </table>
        <br/><br/>

        <div id="warning_message" name="warning_message" style="display: none;color:red">请选择地区下拉详细选择项</div>
        <input type="hidden" name="action" id="action" value=""/>
		<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }"/>
    </form>

</div>
</body>
</html>