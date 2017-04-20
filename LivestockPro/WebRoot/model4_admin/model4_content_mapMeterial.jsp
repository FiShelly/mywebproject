<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ad_model4_style.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=DM7uQ0naOR58uhGC2jkmcotd"></script>
  <script type="text/javascript">
    $(function(){
      $("#choice_A").click(function(){
        $(this).css("background-color","white");
        document.getElementById("span1").style.color="#00A782";

        $("#choice_B").css("background-color","#00A782");
        document.getElementById("span2").style.color="white";
        $("#content1").css("display","block");
        $("#content2").css("display","none");
      });
       $("#choice_B").click(function(){
        $(this).css("background-color","white");
        document.getElementById("span2").style.color="#00A782";
        $("#disIframe").attr("src", "model4_admin/model4_content_meterialDispatch.jsp");
        $("#choice_A").css("background-color","#00A782");
        document.getElementById("span1").style.color="white";
        $("#content2").css("display","block");
        $("#content1").css("display","none");
      });
    });
  </script>
</head>
<body>
<c:choose>
<c:when test="${flag }">
  <div id="result" style="width: 50%;position: relative">
    <div style="margin:5px 0 5px 20px;">
      <span style="font-family: 黑体; font-size: 14px; white-space: nowrap;">您的位置:&nbsp;物资储备与动态信息查询</span>
    </div> 

    <table id="myUL" name="myUL">
      <tr>
        <ul>
          <td>
            <li style="background-color:white;" id="choice_A" name="choice_A">
              <a href="javascript:void(0)"><span style="color:#00A782;" id="span1">物资调度</span></a>
            </li>
          </td>
          <td>
            <li style="background-color:#00A782;" id="choice_B" name="choice_B">
              <a href="javascript:void(0);"><span style="color:white;" id="span2">动态查询</span></a>
            </li>
          </td>

        </ul>
      </tr>
    </table>
    <div id="conntents" style="width: 95%;height: 99%;margin-left: 20px;overflow-y: auto;position: relative">
     <div id="content1">
        <iframe src="model4_admin/model4_content_meterial.jsp?open=${open }&supId=${id}" width="100%" height="90%" style="border:0"></iframe>
      </div>
      <div id="content2" style="display: none;">
        <iframe src="#" id="disIframe" width="100%" height="90%" style="border:0;"></iframe>
      </div> 
    </div>
  </div>
  <div id="l-map" style="width: 50%;position: relative"></div>
  <script type="text/javascript">
    // 百度地图API功能
	var map = new BMap.Map("l-map");            // 创建Map实例
	map.centerAndZoom("${admin.address}", 12);
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
	var myGeo = new BMap.Geocoder();
	initSup();
	initDis();
	function initDis() {
		<c:forEach var="obj" items="${disList}" >
			myGeo.getPoint("${obj.targerAddress}", function(point){
				if (point) {
      				var myIcon = new BMap.Icon("${pageContext.request.contextPath}/image/pla.png", new BMap.Size(20,30));
					var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat),{icon:myIcon});  // 创建标注，为要查询的地方对应的经纬度
   					map.addOverlay(marker);
					var title = "物资编号编号：" + "${obj.item.suppliesId}" + "</br>" +
      							"物资名称：" + "${obj.item.suppliesName}" + "</br>";
      				var content =  title +
    								"调度数量：" + "${obj.date}" + "</br>" +
    								"调度日期：" + "${obj.number}" + "</br>" +
    				 				"储备点地址地址：" + "${obj.item.supplies.address}" + "</br>" +
    								"调度地址："  + "${obj.targerAddress}" +  "</br>" ; 
       				var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
       				marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
				}
			}, "");
		
		</c:forEach>
	}
	
	function initSup() {
		<c:forEach var="obj" items="${supList}" >
			myGeo.getPoint("${obj.address}", function(point){
				if (point) {
					var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat));  // 创建标注，为要查询的地方对应的经纬度
      					map.addOverlay(marker);
      					var title = "储备点编号：" + "${obj.reserveId}" + "</br>" +
      								"储备点名称：" + "${obj.name}" + "</br>";
      					var content =  title +
      				 				"储备点地址地址：" + "${obj.address}" + "</br>" +
      								"管理单位："  + "${obj.managementstation}" +  "</br>" + 
      								"负责人及联系方式：" + "${obj.head}，${obj.position},${obj.phoneNum}"  + "</br>" +
      								"注册日期：" + "${obj.registDate}" + "</br>";
       				var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
       				marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
				}
			}, "");
		
		</c:forEach>
	}
		
  </script>
 </c:when>
 <c:otherwise>
 <script type="text/javascript">
 	window.location = "${pageContext.request.contextPath}/GisMapServlet?action=admin_sup_Map&address=${admin.address}";
 </script>
 </c:otherwise>
 </c:choose>
</body>
</html>


