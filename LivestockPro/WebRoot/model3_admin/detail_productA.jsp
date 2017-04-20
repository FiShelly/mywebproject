<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/ad_model3_detailStyle.css"/>

</head>
<body>
<div style="text-align:center;">
    <table id="productA">
        <tr>
            <td style="background-color:#f0f0f0;">
                编号
            </td>
            <td colspan="3" style="width:340px;">
            	${pa.id }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;">
                货主
            </td>
            <td style="width:120px;">
                ${pa.shipperName }
            </td>
            <td style="background-color:#f0f0f0">
                联系电话
            </td>
            <td style="width:120px;">
               ${pa.phoneNum }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;">
                产品名称
            </td>
            <td style="width:120px;">
                ${pa.productName }
            </td>
            <td style="background-color:#f0f0f0">
                数量及单位
            </td>
            <td style="width:120px;">
                ${pa.number }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                生产单位地址
            </td>
            <td colspan="3">
               ${pa.addressName }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                目的地
            </td>
            <td colspan="3">
                ${pa.destination }
            </td>
        </tr>

        <tr>

            <td style="background-color:#f0f0f0">
                承运人
            </td>
            <td style="width:120px;">
            	${pa.carrier }
            </td>
            <td style="background-color:#f0f0f0">
                联系电话
            </td>
            <td style="width:120px;">
            	${pa.carrierPhone }
            </td>

        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                运载方式
            </td>
            <td style="width:120px;">
             	${pa.transportWay }
            </td>
            <td style="background-color:#f0f0f0">
                经手兽医
            </td>
            <td style="width:120px;">
                ${pa.vetName }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;">
                运载工具牌号
            </td>
            <td style="width:120px;">
				${pa.transportId }
            </td>
            <td style="background-color:#f0f0f0;">
                消毒处理方式
            </td>
            <td style="width:120px;">
				${pa.disinfection }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                合格证有效天数
            </td>
            <td>
            	${pa.days }
            </td>
            <td style="background-color:#f0f0f0">
                签发日期
            </td>
            <td style="width:120px;">
            	${pa.date}
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;height: 40px">
                备注
            </td>
            <td colspan="3">
            	${pa.note }
            </td>
        </tr>
    </table>
</div>
</body>
</html>