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
    <table id="productB">
        <tr>
            <td style="background-color:#f0f0f0;">
                编号
            </td>
            <td colspan="3" style="width:340px;">
            	${pb.id }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;">
                货主
            </td>
            <td style="width:120px;">
				${pb.shipperName }
            </td>
            <td style="background-color:#f0f0f0">
                产品名称
            </td>
            <td style="width:120px;">
                ${pb.productName }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                数量及单位
            </td>
            <td style="width:120px;">
                ${pb.number }
            </td>
            <td style="background-color:#f0f0f0;">
                产地
            </td>
            <td style="width:120px;">
               ${pb.producter }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                生产单位地址
            </td>
            <td colspan="3">
				${pb.addressName }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                目的地
            </td>
            <td colspan="3">
				${pb.destination }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                检疫标识号
            </td>
            <td colspan="3">
           		 ${pb.quarantinemarks }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                合格证有效天数
            </td>
            <td>
            	${pb.days }
            </td>
            <td style="background-color:#f0f0f0">
                签发日期
            </td>
            <td>
            	${pb.date }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0;">
                经手兽医
            </td>
            <td colspan="3">
				${pb.vetName }
            </td>

        </tr>

        <tr>
            <td style="background-color:#f0f0f0;height: 40px">
                备注
            </td>
            <td colspan="3">
            	${pb.note }
            </td>
        </tr>

    </table>
</div>
</body>
</html>