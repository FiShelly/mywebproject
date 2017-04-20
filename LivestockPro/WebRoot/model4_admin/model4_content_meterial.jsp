<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ad_model4_style.css">

<script type="text/javascript">
	var item = {};
	if ("${list}" != "") {
		function toggleItem() {

		}
		function addItem(parentItem, address, id) {
			var newItem = $('<div class="tree"></div>').hide();
			var a = 0;
			newItem.append($("<span id = 'span_"+id+"'></span>").click(
					function() {
						$(this).parent().children("div").toggle();
						$(this).toggleClass("collapse");
						if (a == 0) {$("#supItemIframe_" + id).attr("src","SuppliesServlet?action=allSup_list&id="+ id);
							a++;
						}
						return false;
					}));
			item = {
				label : address,
				content : $("<div><iframe id='supItemIframe_"
						+ id
						+ "' width='100%' height='400px;' style='border:1px solid #12A686;'></iframe></div>")
			};
			newItem.append($("<label></label>").html(item.label));
			newItem.append($('<div class="content"></div>')
					.append(item.content).hide());
			parentItem.append(newItem);
			return newItem;
		}
		function addLevels(parent, levels, address, id) {
			var element = addItem(parent, address, id);
			if (levels > 0) {
				addLevels(element, levels - 1);
			}
			return element;
		}

		function init(id) {
			var root = addLevels($("#" + id), 0).show();
		}

		$(document).ready(
				function() {
					<c:forEach items="${list }" var="val" >
					var root_${sta.index} = addLevels(
							$("#treeContainer_${val[0]}"), 0,
							'${val[1]}${val[2]}', '${val[0]}').show();
					</c:forEach>
				});
		if("${param.open}" != "" ){
			var id = "${param.supId}";
			$("#span_"+id).show();
		}

	} else {
		window.location = "${pageContext.request.contextPath}/SuppliesServlet?action=allSupName_list&address=${admin.address}";
	}
</script>
</head>

<body>

	<c:forEach items="${list }" var="val">
		<div id="treeContainer_${val[0]}" class="treeContainer"></div>
	</c:forEach>
</body>
</html>
