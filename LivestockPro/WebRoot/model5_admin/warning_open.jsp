<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
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
            margin:20px 0 0 0;
            border-collapse: collapse;
        }

        tr td {
            border: 1px solid #aaaaaa;
            text-align: left;
            height: 25px;
            width: 100px;
            font-family: "宋体";
            font-size: 12px;
            color: black;
            table-layout: fixed;
        }
		input {
			width: 70px
		}
        input:hover {
            border: 1px solid #A4DECE;
        }
    </style>
</head>
<body>
<div style="height: 80%;overflow-y: auto;">
    <form id="warning_message" action="#" method="post">
        <table>
            <tr>
                <td colspan="3">
                    预测发生疫情的概率为:<input type="text" value="<fmt:formatNumber value="${rate}"  pattern="#00.00%"/>" readonly="readonly" id="probability"/>
                </td>

            </tr>
            <tr>
                <td colspan="3">预警分析与建议:</td>
            </tr>
            <c:forEach items="${advices }" var="ad">
             <tr>
                <td colspan="3" style="text-align: left;font-size: 13px">
                	<span>${ad }</span>
                </td>
            </tr>
            </c:forEach>
           
        </table>
    </form>
</div>
</body>
</html>
