<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ad_model3_detailStyle.css"/>
</head>
<body>
    <table id="animalA">
        <tr>
            <td style="background-color:#f0f0f0;">
                编号
            </td>
            <td colspan="3" style="width:360px;">
            	${aa.id }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0;">
                货主
            </td>
            <td>
               ${aa.shipperName }
            </td>
            <td style="background-color:#f0f0f0">
                联系电话
            </td>
            <td>
                ${aa.phoneNum }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                动物种类
            </td>
            <td>
            	${aa.animalSpecies }
            </td>
            <td style="background-color:#f0f0f0">
                数量及单位
            </td>
            <td>
            	${aa.number }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                启运地点
            </td>
            <td colspan="3">
                ${aa.startAddress }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                到达地点
            </td>
            <td colspan="3">
                ${aa.destination }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                用途
            </td>
            <td colspan="3">
            	${aa.use }
            </td>
            
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                承运人
            </td>
            <td>
            	${aa.carrier }
            </td>
            <td style="background-color:#f0f0f0">
                联系电话
            </td>
            <td>
            	${aa.carrierPhone }
            </td>
        </tr>

        <tr>
            <td style="background-color:#f0f0f0">
                运载方式
            </td>
            <td>
                ${aa.transportWay }
            </td>
            <td style="background-color:#f0f0f0">
                运输工具牌号
            </td>
            <td>
               ${aa.transportId}
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0;">
                运载工具消毒情况
            </td>
            <td>
                装运前经&nbsp;${aa.disinfection }&nbsp;消毒
            </td>
             <td style="background-color:#f0f0f0">
                经手兽医
            </td>
            <td>
            	${aa.vetName }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0">
                合格证有效天数
            </td>
            <td>
            	${aa.days }
            </td>
           
            <td style="background-color:#f0f0f0">
                签发日期
            </td>
            <td>
            	${aa.date }
            </td>
        </tr>
        <tr>
            <td style="background-color:#f0f0f0;height: 40px">
                备注
            </td>
            <td colspan="3">
            	${aa.note }
            </td>
        </tr>
        
    </table>
    <div class="animalID">
        <span>耳标号</span>
        <table>
			
			<c:forEach items="${aa.animalId }" var="aaid" varStatus="status">
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