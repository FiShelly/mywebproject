<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>地图显示</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 

  </head>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=7OclS25lRgCs6rsslowGNh5m"></script>

  <body onload="init();">
      <div style="width:730px;margin:auto;">   
        <div id="container" 
            style="position: absolute;
                margin-top:30px; 
                width: 730px; 
                height: 450px; 
                top: 50; 
                border: 1px solid gray;
                overflow:hidden;">
        </div>
    </div>
  </body>

  <script type="text/javascript">
	if("${list}" == ""){
		window.location = "${pageContext.request.contextPath}/GisMapServlet?action=userMap&province=${user.farm.province}&city=${user.farm.city}";
	} else {
		var map = new BMap.Map("container");
		map.centerAndZoom("${user.farm.location}", 12);
		map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
		var myGeo = new BMap.Geocoder();
	}
  	
		function init() {
			<c:forEach var="obj" items="${list}"> 
					myGeo.getPoint("${obj.farm.location}", function(point){
						if (point) {
							var myIcon = new BMap.Icon("${pageContext.request.contextPath}/image/pla.png", new BMap.Size(20,30));
							var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat),{icon:myIcon});  // 创建标注，为要查询的地方对应的经纬度
	       					map.addOverlay(marker);
	       					var title = "疫情爆发地址：" + "${obj.farm.location}" + "</br>";
	       					var content =  title + 
	       								"死亡情况："  + "${obj.deaths}" +  "</br>" + 
	       								"发病动物种类：" + "${obj.sickSpecies}"  + "</br>";
	        				var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>" + "<input type='button' value='疫情详情' onclick=''>");
	        				marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
						}
					}, "");
				
			</c:forEach>
			//<c:forEach var="obj" items="${listPlag}"> 
			//		myGeo.getPoint("${obj.address}", function(point){
			//			if (point) {
			//				var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat));  // 创建标注，为要查询的地方对应的经纬度
	       	//				map.addOverlay(marker);
	       	//				var title = "疫情爆发点：" + "${obj.address}" + "</br>";
	       	//				var content =  title + "疫情名称：" + "${obj.plagueName}" + "</br>" +
	       	//									   "爆发日期：" + "${obj.date}" + "</br>"; 
	        //				var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>" + "<input type='button' value='详细信息' onclick=''>");
	        //				marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
			//		}
			//	}, "${admin.address}");
				
			//</c:forEach> 
		}
</script>
</html>
