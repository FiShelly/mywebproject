<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model4_style.css">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BHnPLEnVh294pWWNtp850fKt"></script>
    <title>本地搜索的结果面板</title>
</head>
<body >
<c:choose>
	<c:when test="${list!=null }">
    <div id="result">
        <div style="margin:5px 0 5px 20px;"><font style="font-family: 黑体; font-size: 14px; white-space: nowrap;">疫情爆发地理信息</font></div>
	<form action="${pageContext.request.contextPath}/GisMapServlet" method="post">      
        <input type="text" id="key" name="key" />
        <button type="submit" style="border-radius: 6px; border: 0px currentColor; border-image: none; width: 70px; height: 25px; color: white; font-size: 14px; background-color: rgb(18, 166, 134);" type="button" value="查询">查询&nbsp;<img width="15" height="15" style="vertical-align: middle;" src="${pageContext.request.contextPath}/image/search.png"></button>
     	<input type="hidden" name="action" value="epi_map"/>
     	<input type="hidden" name="province" value="${farm.province}"/>
     	<input type="hidden" name="city" value="${farm.city}"/>
     	<input type="hidden" name="role" value="user"/>
     </form>    
   <div style="align:center;height: 87%;overflow-y: auto;overflow-x:auto" >
            <c:forEach items="${list }" var="epi">
             <table onclick="toDisMap('${epi.id}');">
                <tr>
                    <td width="30%">
                    	爆发地点
                    </td>
                    <td>
                    	${epi.farm.location }
                    </td>
                </tr>
                <tr>
                    <td>
                    	饲养种类
                    </td>
                    <td>
                    	${epi.feedSpecies }
                    </td>
                </tr>
                <tr>
                    <td>
                    	发病种类
                    </td>
                    <td>
                    	${epi.sickSpecies }
                    </td>
                </tr>
                <tr>
                    <td>
                    	上报人
                    </td>
                    <td>
                    	${epi.farm.phoneNum }
                    </td>
                </tr>
                 <tr>
                    <td>
                    	联系方式
                    </td>
                    <td>
                    	${epi.farm.leader }
                    </td>
                </tr>
                <tr>
                    <td>
                    	爆发日期
                    </td>
                    <td>
                    	${epi.date  }
                    </td>
                </tr>
               </table>
            </c:forEach>
           
        </div>
    </div>
<div id="l-map">

</div> 
</c:when>
	<c:otherwise>
		<script type="text/javascript">
			window.location = "${pageContext.request.contextPath}/GisMapServlet?action=epi_map&province=${user.farm.province}&city=${user.farm.city}&role=user";
		</script>
	</c:otherwise>
</c:choose>
</body>
<script type="text/javascript">
var map = new BMap.Map("l-map");            // 创建Map实例
map.centerAndZoom("${user.farm.location}", 12);
map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
var myGeo = new BMap.Geocoder();
var epi = {};
    function initMap() {
    	 // 百度地图API功能
  	 	map.centerAndZoom(epi.farm.location, 12);
    	myGeo.getPoint(epi.farm.location, function(point){
    		if (point) {
				var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat));  // 创建标注，为要查询的地方对应的经纬度
  					map.addOverlay(marker);
					var flag = epi.isPeoInfect==false?"否":"是";
  					var title = "爆发地点：" + epi.farm.location + "</br>" +
  								"饲养种类：" + epi.feedSpecies + "</br>"+
  								"发病动物种类：" + epi.sickSpecies + "</br>"+
  								"饲养规模：" + epi.feedScale + "</br>"+
  								"饲养情况：" + epi.feedSitua + "</br>"+
  								"临床症状：" + epi.clinicalSysp + "</br>"+
  								"死亡情况：" + epi.deaths + "</br>"+
  								"是否有人感染：" + flag + "</br>"+
  								"封锁情况：" + epi.immunitySitua + "</br>"+
  								"免疫情况：" + epi.treatmentSitua + "</br>"+
  								"治疗情况：" + epi.immunitySitua + "</br>"+
  								"爆发日期：" + epi.date + "</br>";
  					
   				var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + title + "</p>");
   				marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
			};
		}, "");
    	
    }
	function createXMLHttp() {
    	if(window.XMLHttpRequest) {
    		xmlHttp = new XMLHttpRequest();
    	} else {
    		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    	} 
    }
    
    function toDisMap(id) {
    	createXMLHttp();
    	xmlHttp.open("POST","EpidemicRecordServlet?action=detail_ajax&id="+id);
    	xmlHttp.onreadystatechange = getEpiCallback;
    	xmlHttp.send();
    }
    
    function getEpiCallback() {
    	if(xmlHttp.readyState == 4) {
    		if(xmlHttp.status == 200) {
    			var text = xmlHttp.responseText;
    			epi = eval("("+text+")");
    			initMap();
    		}
    	}
    }
    function useMapDisplay(epi){
    	
    }
</script>
</html>
