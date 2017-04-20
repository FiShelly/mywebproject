<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
    $(function(){
        $("#year").bind("click",function(){
        	$("#months").attr("disabled",true);//月份下拉
            $("#foform").submit() ;
        });
        $("#month").bind("click",function(){
			$("#months").attr("disabled",false);
        	$("#foform").submit();
        });
    });
    function init(register_date,search_date,name,flag){
        var date1 = register_date.split("-");//获取传入注册年份
        var date2 = search_date.split("-");
        var now = new Date();//获取当前年份
        for(var i=date1[0];i<=now.getFullYear();i++){
            $("select").eq(0).prepend("<option>"+i+"</option>");
        }
		if(flag == 1){
            $("#select_month").hide();
            $("#register_month").hide();//标题显示月份
		} else {
	        $("#select_month").show();
	        $("#register_month").show();
		}

        $("#farm_name").html(name);
        $("#register_year").html(date2[0]);
        $("#register_month").html(date2[1]+"月");

        var months = $("#months").options;
        $("#months").bind("change",function(){
            var temp = $(this).val();
            $("#register_month").html(temp);
            $("#foform").submit();
        });
        var years = $("#years").options;
        $("#years").bind("change",function(){
            $("#register_year").html($(this).val());
            $("#foform").submit();
        });

        var dy =date2[0];
        var dd = date2[1];
        $("#years").val(dy);
        $("#months").val(dd);
    }
    </script>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            width: 100%;
            height: 100%;
        }

        table {
            width: 100%;
            height: auto;
            border-collapse: collapse;
        }

        tr td {
            border: 1px solid #aaaaaa;
            text-align: center;
            height: 25px;
            width: 100px;
            font-family: "宋体";
            font-size: 12px;
            color: black;
            table-layout: fixed;
        }
    </style>
</head>
<body onload="init('${name[1]}','${date}','${name[0]}','${flag }')">
<div style="height: 80%;overflow-y: auto;">
<form id="foform" method="post" action="${pageContext.request.contextPath}/CountServlet">
    <table>
        <tr>
            <td colspan="4">
                <input type="radio" id="year" value="1" ${flag==1?"checked":"" } name="time"  style="width: 13px; height: 15px; vertical-align: bottom;"/>年份
                <input type="radio" id="month" value="0" ${flag==0?"checked":"" } name="time" style="width: 13px; height: 15px; vertical-align: bottom;" />月份
            </td>
        </tr>
        <tr>
            <td colspan="4">
            <span id="select_year">
            年份:
            <select id="years" name="years">

            </select>
            </span>
            <span id="select_month">
            月份:
            <select id="months"  name="months">
                <option value="01">01月</option>
                <option value="02">02月</option>
                <option value="03">03月</option>
                <option value="04">04月</option>
                <option value="05">05月</option>
                <option value="06">06月</option>
                <option value="07">07月</option>
                <option value="08">08月</option>
                <option value="09">09月</option>
                <option value="10">10月</option>
                <option value="11">11月</option>
                <option value="12">12月</option>
            </select>
            </span>
            </td>

        </tr>
        <tr>
            <td colspan="4">
                <span><label id="farm_name"></label><label id="register_year"></label>年<label id="register_month"></label>信息统计表</span>
            </td>
        </tr>
		<tr>
            <td>类别</td>
            <td>统计值</td>
			 <td>类别</td>
            <td>统计值</td>
        </tr>
        <tr>
            <td colspan="2">养殖档案统计表</td>
            <td colspan="2">检疫信息统计表</td>
        </tr>
        <tr>
            <td>养殖场死淘率</td>
            <td><fmt:formatNumber value="${msg.ddr}" pattern="#00.00%"/></td>
            <td>动物A平均运输检疫次数</td>
            <td><fmt:formatNumber value="${msg.avgAaCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
           <td>养殖场畜牧免疫率</td>
            <td><fmt:formatNumber value="${msg.immRate}" pattern="#00.00%"/></td>
            <td>动物B平均运输检疫次数</td>
            <td><fmt:formatNumber value="${msg.avgAbCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
            <td>养殖场畜牧发病率</td>
            <td><fmt:formatNumber value="${msg.avgSickCount}" pattern="#00.00%"/></td>
            <td>产品A平均运输检疫次数</td>
            <td><fmt:formatNumber value="${msg.avgPaCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
            <td>养殖场死亡率</td>
            <td><fmt:formatNumber value="${msg.daethr}" pattern="#00.00%"/></td>
            <td>产品B平均运输检疫次数</td>
            <td><fmt:formatNumber value="${msg.avgPbCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
            <td>养殖场平均消毒次数</td>
            <td><fmt:formatNumber value="${msg.avgDisfCount}" pattern="#0.00#"/></td>
            <td colspan="2">疫情爆发统计表</td>
        </tr>
        <tr>
            <td>养殖场平均防疫检测次数</td>
            <td><fmt:formatNumber value="${msg.avgPreCount}" pattern="#0.00#"/></td>
            <td>养殖场上报疫情次数</td>
            <td><fmt:formatNumber value="${msg.avgerCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
            <td colspan="2">疫情物资统计表</td>
            <td>疫情被审核次数</td>
            <td><fmt:formatNumber value="${msg.avgRerCount}" pattern="#0.00#"/></td>
        </tr>
        <tr>
            <td>养殖场平均接收物资次数</td>
            <td><fmt:formatNumber value="${msg.avgSupCount}" pattern="#0.00#"/></td>
            <td>疫情审核率</td>
            <td><fmt:formatNumber value="${msg.avgRerRate}" pattern="#0.00#"/></td>
        </tr>
    </table>
    <input type="hidden" name="type" value="count"/>
    <input type="hidden" name="farmId" value="${farmId }"/>
    <input type="hidden" name="local" value="${local }"/>
    </form>
</div>
</body>
</html>