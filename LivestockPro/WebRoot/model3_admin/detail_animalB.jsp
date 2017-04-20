<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/ad_model3_detailStyle.css"/>
</head>
<body>
    <table id="animalB">
        <tr>
            <td style="background-color:#f0f0f0;">
                编号
            </td>
            <td colspan="3" style="width:360px;">
            	${ab.id }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0;">
                货主
            </td>
            <td style="width:120px;">
            	${ab.shipperName }
            </td>
            <td style="background-color:#f0f0f0;">
                联系电话
            </td>
            <td style="width:120px;">
            	${ab.phoneNum }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                动物种类
            </td>
            <td style="width:120px;">
            	${ab.animalSpecies }
            </td>
            <td style="background-color:#f0f0f0">
                数量及单位
            </td>
            <td style="width:120px;">
            	${ab.number }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                启运地点
            </td>
            <td colspan="3">
               ${ab.startAddress }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                到达地点
            </td>
            <td colspan="3">
               ${ab.destination }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                用途
            </td>
            <td style="width:120px;">
            	${ab.use }
            </td>
            <td style="background-color:#f0f0f0">
                经手兽医
            </td>
            <td style="width:120px;">
            	${ab.vetName }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                合格证有效天数
            </td>
            <td style="width:120px;">
           	 	${ab.days }
            </td>

            <td style="background-color:#f0f0f0">
                签发日期
            </td>
            <td style="width:120px;">
            	${ab.date }
            </td>
        </tr>

    </table>
    <div class="animalID">
        <span>耳标号</span>
        <table>
           <c:forEach items="${ab.animalId }" var="aaid" varStatus="status">
				<c:choose>
					<c:when test="${status.index % 4 == 0 }">
						<tr>
						<td>
                			${aaid }
                		</td>
					</c:when>
					<c:when test="${status.index % 4 == 3 }">
						<td>
							${aaid }
						</td>
						</tr>
					</c:when>
					<c:otherwise>
						<td>
                			${aaid }
                		</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
           
        </table>
    </div>

</body>
</html>