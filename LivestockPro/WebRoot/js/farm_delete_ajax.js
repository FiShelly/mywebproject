var xmlHttp ;
var temp = "00";
var flag = "true";
var check = "true";
var mesid = "";
var manid = "";
function isDelete(status,pkid,farmId) {
	if(window.confirm("确定删除该养殖场信息？")) {
		deleteMes(status,pkid,farmId);
	}
}

function isDeleteMes(status,pkid,farmId) {
	if(window.confirm("确定删除此条信息？")) {
		flag = "false";
		deleteMes(status,pkid,farmId);
	}
}

function isDeleteAdminMes(status,pkid,farmId) {
	if(window.confirm("确定删除此条管理员信息？")) {
		flag = "false";
		deleteMes(status,pkid,farmId);
	}
}

function isDeleteSupMes(status,pkid,farmId) {
	if(window.confirm("确定删除此条物资信息？")) {
		flag = "false";
		deleteMesSup(status,pkid,farmId);
	}
}

function isDeleteSupItemDisMes(status,pkid,farmId) {
	if(window.confirm("确定删除此条物资调度信息？")) {
		flag = "false";
		deleteMesSupItemDis(status,pkid,farmId);
	}
}

function deleteMesSupItemDis(status,pkid,farmIdd) {
	createXMLHttp();
	temp = farmIdd;
	xmlHttp.open("POST",status+"Servlet?action=supItemDis_delete&"+pkid +"="+farmIdd);
	xmlHttp.onreadystatechange = deleteFarmCallback;
	xmlHttp.send();
}

function changeIsArrive(divId1,divId2,id,address){
	mesid = divId1;
	manid = divId2;
	if(confirm("物资是否已到达目标地址？")){
		changeArriveStatus(id,address);
	}
}

function changeArriveStatus(id,address){
	createXMLHttp();
	temp = id;
	xmlHttp.open("POST","SuppliesServlet?action=supItemDis_change&id="+id+"&address="+address);
	xmlHttp.onreadystatechange = casCallback;
	xmlHttp.send();
}

function casCallback(){
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true" ) {
				document.getElementById(mesid).style.display="none";
	            document.getElementById(manid).style.display="inline";
			} else {
				alert("无法修改物资调度信息到达状态！");
			}
		} 
	}
}

function isChangeStatus(id,tempval,dismesid,dismanid){
	check = tempval;
	mesid = dismesid;
	manid = dismanid;
	if(check == 'true'){
		if(confirm("确认此条疫情信息是否属实？")){
			auditingMsg(id);
		}
	} else {
		if(confirm("确认处理该疫情信息？")){
			auditingMsg(id);
		}
	}
}

function auditingMsg(id){
	createXMLHttp();
	temp = id;
	xmlHttp.open("POST","EpidemicRecordServlet?action=changeStatus&id="+id);
	xmlHttp.onreadystatechange = auditingMsgCallback;
	xmlHttp.send();
}

function auditingMsgCallback(){
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true" ) {
				if(check=="true") {
					alert("疫情信息审核成功！");
					var dispose_message = document.getElementById(mesid);
		            dispose_message.href="javascript:void(0)";
		            dispose_message.style.display="none";
		            document.getElementById(manid).style.display="inline";
		            document.getElementById("ta_"+temp).innerHTML = "已审核";
				} else {
					alert("疫情信息处理成功！");
					var btn_handle = document.getElementById(mesid);
	                var dispose_update = document.getElementById(manid);
	                btn_handle.innerText = "已处理";
	                btn_handle.href="javascript:void(0)";
	                btn_handle.onclick="return false";
	                btn_handle.style.color="gray";
	                dispose_update.style.display="none";
	                document.getElementById("th_"+temp).innerHTML = "已审核";
				}
				} else {
					if(check=="true") {
						alert("疫情信息审核失败！");
					} else{
						alert("疫情信息处理失败！");
					}
				}
			} 
		}
	}

function deleteMesSup(status,pkid,farmIdd) {
	createXMLHttp();
	temp = farmIdd;
	xmlHttp.open("POST",status+"Servlet?action=delete_supItem&"+pkid +"="+farmIdd);
	xmlHttp.onreadystatechange = deleteFarmCallback;
	xmlHttp.send();
}

function createXMLHttp() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
}

function deleteMes(status,pkid,farmIdd) {
	createXMLHttp();
	temp = farmIdd;
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