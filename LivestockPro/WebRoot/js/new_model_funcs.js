var xmlHttp;
function createXMLHttp() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
}

function updatePassword(loginId,pw) {
	createXMLHttp();
	xmlHttp.open("POST","AdminManageServlet?action=updatePw&loginId="+loginId+"&pw="+pw);
	xmlHttp.onreadystatechange = updatePwCallback;
	xmlHttp.send();
}

function updatePwCallback() {
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true") {
				alert("密码修改成功！");
			} else {
				alert("密码修改失败！");
			}
		}
	}
}

function Clear(){
	var eles=document.getElementsByTagName("input");
	for(var i=0;i<eles.length;i++){
		if(eles[i].id=="number"||eles[i].id=="district"||eles[i].id=="start_date"){
			eles[i].value="";
		}else{
			continue;
		}
	}
}
function setTrBgColor(tableid,color1,color2){
	var tab=document.getElementById(tableid);
	for(var i=0;i<tab.rows.length;i++){
		tab.rows[i].style.backgroundColor=(i%2==0)?color1:color2;
	}
}
window.onload=function(){
	setTrBgColor("tb_content","#f5f5f5","white");

}
function setLiBgColor(Id){
	var before = document.getElementById("select");
	var af = document.getElementById("register");
	if(Id=="select"){

		before.style.background="#232423";
		before.style.opacity="1.0";

		af.style.background="#343535";
		af.style.opacity="0.8";

	}else if(Id=="register"){
		af.style.background="#232423";
		af.style.opacity="1.0";

		before.style.background="#343535";
		before.style.opacity="0.8";
	}
}
$(function(){
	//注册
	$("#MyDiv").dialog({
		autoOpen:false,
		width:"400",
		height:"500",
		modal:true,
		buttons: [
		{
			text: "确定",
			click: function () {
				var $doc = $("#frame_register")[0].contentWindow;
				if ($doc.checkContent("register")) {
					$doc.document.getElementById("cmbProvince").disabled=false;
					if($doc.document.getElementById("province").checked){
						$doc.document.getElementById("cmbCity").disabled=true;
						$doc.document.getElementById("cmbArea").disabled=true;
					}else if ($doc.document.getElementById("city").checked){
						$doc.document.getElementById("cmbCity").disabled=false;
						$doc.document.getElementById("cmbArea").disabled=true;
					}else {
						$doc.document.getElementById("cmbCity").disabled=false;
						$doc.document.getElementById("cmbArea").disabled=false;
					}
					$(this).dialog("close");//cmbProvince
					$doc.document.getElementById("form_register").submit();
				}
			}
		},
		{
			text: "取消",
			click: function () {
				clearEnContent("frame_register");
				$(this).dialog("close");
                }
            }
            ]
        });
	//修改
	$("#MyDiv2").dialog({
		autoOpen:false,
		width:"400",
		height:"470",
		modal:true,
		buttons: [
		{
			text: "确定",
			click: function () {
				var $doc = $("#frame_update")[0].contentWindow;
				if ($doc.checkContent("update")) {
					$doc.document.getElementById("cmbProvince").disabled=false;
					if($doc.document.getElementById("province").checked){
						$doc.document.getElementById("cmbCity").disabled=true;
						$doc.document.getElementById("cmbArea").disabled=true;
					}else if ($doc.document.getElementById("city").checked){
						$doc.document.getElementById("cmbCity").disabled=false;
						$doc.document.getElementById("cmbArea").disabled=true;
					}else {
						$doc.document.getElementById("cmbCity").disabled=false;
						$doc.document.getElementById("cmbArea").disabled=false;
					}
					$(this).dialog("close");
					$("#frame_update")[0].contentWindow.document.getElementById("form_update").submit();
				}
			}
		},
		{
			text: "取消",
			click: function () {
				clearEnContent("frame_update");
				$(this).dialog("close");
                }
            }
            ]
        });
	//修改密码
	$("#MyDiv3").dialog({
		autoOpen:false,
		width:"400",
		height:"430",
		modal:true,
		buttons: [
		{
			text: "确定",
			click: function () {
				$result = $("#frame_updatePassword")[0].contentWindow.checkContent("update_password");
				if ($result) {
					var loginId = $("#frame_updatePassword")[0].contentWindow.document.getElementById("loginId").value;
					var pw = $("#frame_updatePassword")[0].contentWindow.document.getElementById("password1").value;
					updatePassword(loginId, pw);
					$(this).dialog("close");
				}
			}
		},
		{
			text: "取消",
			click: function () {
				clearEnContent("frame_updatePassword");
				$(this).dialog("close");
                }
            }
            ]
        });
	$("#register").click(function (event){
		$("#MyDiv").dialog("open");
		event.preventDefault();
	});
	
});

function clearEnContent(parent_iframe) {

	switch(parent_iframe){
		case "frame_register":
			$("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_password1").style.display = "none";
			$("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_password").style.display = "none";
		break;
		case "frame_update":
			$("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_password").style.display = "none";
			
		break;
		case "frame_updatePassword":
			$("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_password").style.display = "none";
			$("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_password1").style.display = "none";
		break;
	}
	var contents = $("#" + parent_iframe)[0].contentWindow.document.getElementsByTagName("input");
	for (var i = 0; i < contents.length; i++) {
		contents[i].value = "";
	}
}

function CloseDiv(show_div, bg_div) {
    window.parent.document.getElementById(show_div).style.display = 'none';
    window.parent.document.getElementById(bg_div).style.display = 'none';
    var inputs = document.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].name == "et_province" || inputs[i].name == "et_city" || inputs[i].name == "et_county" || inputs[i].name == "farm_name") {
            inputs[i].value = "";
            inputs[i].style.color = "black";
        } else if (inputs[i].type == "radio") {
            inputs[i].checked = false;
        }
    }
    document.getElementById("warning_message").style.display = "none";
    document.getElementById("message").style.display = "none";
};
