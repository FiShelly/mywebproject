var xmlHttp ;
var temp = "00";
var flag = "true";

function isDeleteModel3Mes(status,pkid,apid) {
	if(window.confirm("确定删除该信息？")) {
		deleteMes(status,pkid,apid);
	}
}

function createXMLHttp() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
}

function deleteMes(status,pkid,id) {
	createXMLHttp();
	temp = id;
	xmlHttp.open("POST",status+"Servlet?action=delete&"+pkid +"="+farmIdd);
	xmlHttp.onreadystatechange = deleteFarmCallback;
	xmlHttp.send();
}

function deleteFarmCallback() {
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true" ) {
				if(flag=="true"){
					alert("该养殖场信息删除成功！");
				} else {
					alert("该条信息删除成功！");
				}	
				var tr = document.getElementById("tr_"+temp);
				var tab = tr.parentNode;
				tab.removeChild(tr);
				if(tab.rows.length == 1) {
					parent.leftMenu.location.reload
				}					
			} else {
				if(flag=="true"){
					alert("该养殖场信息删除失败！");
				} else {
					alert("该条信息删除失败！");
				}
			}
			
		}
	}
}