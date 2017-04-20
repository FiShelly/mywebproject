

function createXMLHttp() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
}

function checkId(loginId) {
	createXMLHttp();
	xmlHttp.open("POST","../AdminManageServlet?action=checkId&loginId="+loginId);
	xmlHttp.onreadystatechange = checkUserIdCallback;
	xmlHttp.send();
}

function checkUserIdCallback() {
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true") {
				document.getElementById("number").value="此账号已被注册";
				document.getElementById("number").style.color="red";
			} 
		}
	}
}

function checkContent(ID) {
    switch (ID) {
        case "register":
        var blank_sum = 0;
            var error_sum = 0;//是否有错误
            var checkedSum = 0;
            var same = 0;
            var eles = document.getElementsByTagName("input");
            var selects = document.getElementsByTagName("select");
            var passord1 = document.getElementById("password1");
            var password2 = document.getElementById("password2");

            var area = document.getElementById("cmbArea");
            if(area.disabled==false&&area.style.display=="inline"&&area.value=="市辖区"){
                checkedSum++;
                document.getElementById("warning_message").style.display="inline";
                
            }
            if (passord1.value != password2.value) {
                document.getElementById("warning_password").style.display = "inline";
                same++;
            }
            for (var i = 0; i < eles.length; i++) {
                if (eles[i].type == "text") {
                    if (eles[i].value.replace(/\s/g, '') == "") {
                        document.getElementById(eles[i].id).value = "此处不能为空";
                        document.getElementById(eles[i].id).style.color = "red";
                        blank_sum++;
                    } else if (eles[i].value == "此处不能为空") {
                        error_sum++;
                    }
                }else if (eles[i].type == "password") {
                    if (eles[i].value.replace(/\s/g, '') == "") {
                        document.getElementById("warning_password1").style.display = "inline";
                        blank_sum++;
                    }
                }
            }
            if (blank_sum == 0 && error_sum == 0 &&same==0&&checkedSum==0) {
                return true;
            }
            // if (checkedSum == 0) {11.22
            //     document.getElementById("message").style.display = "inline";
            //     return false;
            // }
            // if (selects[0].value == "省份" || selects[2].value == "市辖区") {
            //     document.getElementById("warning_message").style.display = "inline";
            //     return false;
            // }
            break;
            case "update":
            var blank_sum = 0;
            var error_sum = 0;//是否有错误
            var checkedSum = 0;
            var same = 0;
            var eles = document.getElementsByTagName("input");
            var selects = document.getElementsByTagName("select");
            var passord1 = document.getElementById("password1");
            var password2 = document.getElementById("password2");

            var area = document.getElementById("cmbArea");
            if(area.disabled==false&&area.style.display=="inline"&&area.value=="市辖区"){
                checkedSum++;
                document.getElementById("warning_message").style.display="inline";
                
            }
            if (passord1.value != password2.value) {
                document.getElementById("warning_password").style.display = "inline";
                same++;
            }
            for (var i = 0; i < eles.length; i++) {
                if (eles[i].type == "text") {
                    if (eles[i].value.replace(/\s/g, '') == "") {
                        document.getElementById(eles[i].id).value = "此处不能为空";
                        document.getElementById(eles[i].id).style.color = "red";
                        blank_sum++;
                    } else if (eles[i].value == "此处不能为空") {
                        error_sum++;
                    }
                }
            }

            if (blank_sum == 0 && error_sum == 0 && same == 0&&checkedSum==0) {
                return true;
            }
            
            break;
            case "update_password":
            var blank_sum = 0;
            var error_sum = 0;//是否有错误

            var same = 0;
            var eles = document.getElementsByTagName("input");
            var passord1 = document.getElementById("password1");
            var password2 = document.getElementById("password2");

            for (var i = 0; i < eles.length; i++) {
                if (eles[i].type == "text" || eles[i].type == "password") {
                    if (eles[i].value.replace(/\s/g, '') == "") {
                        document.getElementById("warning_password1").style.display = "inline";
                        blank_sum++;
                    } else if (eles[i].value == "此处不能为空") {
                        error_sum++;
                    } else if (passord1.value != password2.value) {
                        document.getElementById("warning_password").style.display = "inline";
                        same++;
                    }
                }
            }

            if (blank_sum == 0 && error_sum == 0 && same == 0) {
                return true;
            }
            break;
        }

    }
//用于清除错误信息
function clearError(target_id) {
    var ele = document.getElementById(target_id);
    if (ele.value == "此处不能为空" || ele.value == '此账号已被注册') {
        ele.value = "";
        ele.style.color = "black";
    }
}

function initRadio(a,x,y,z){
    window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
    $("select").eq(0).attr("disabled",false);
    $("select").eq(1).attr("disabled",false);
    $("select").eq(2).attr("disabled",false);
    if(a=='3'){
        $("select").eq(0).attr("disabled","disabled");
        $("input:radio").eq(0).attr("checked","checked");
        document.getElementById("cmbCity").style.display="none";
        document.getElementById("cmbArea").style.display="none";
        $("input:radio").eq(0).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,'0','0');
            document.getElementById("rowID").style.display="none";
        });
        $("input:radio").eq(1).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            $("select").eq(1).attr("disabled",false);
            document.getElementById("cmbArea").style.display="none";
            document.getElementById("rowID").style.display="";
        });
        $("input:radio").eq(2).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            $("select").eq(1).attr("disabled",false);
            document.getElementById("rowID").style.display="";
        });
        
    }else if(a=='2'){
        $("input:radio").eq(1).attr("checked","checked");
        $("select").eq(0).attr("disabled","disabled");
        $("select").eq(1).attr("disabled","disabled");
        document.getElementById("cmbArea").style.display="none";
        $("input:radio").eq(0).attr("disabled","disabled");
        $("input:radio").eq(1).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,'0');
            document.getElementById("rowID").style.display="none";
        });
        $("input:radio").eq(2).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            document.getElementById("rowID").style.display="";
        });
    }else if(a=='1'){
        $("input:radio").eq(2).attr("checked","checked");
        $("select").eq(0).attr("disabled","disabled");
        $("select").eq(1).attr("disabled","disabled");
        $("select").eq(2).attr("disabled","disabled");
        $("input:radio").eq(0).attr("disabled","disabled");
        $("input:radio").eq(1).attr("disabled","disabled");
        document.getElementById("rowID").style.display="none";
    }
    
};
function initRadioUpdate(a,x,y,z){
    window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
    if(a=='3'){
        //省级
        if(y==""){
            $("input:radio").eq(0).attr("checked","checked");
            $("select").eq(0).attr("disabled","disabled");
            document.getElementById("cmbCity").style.display="none";
            document.getElementById("cmbArea").style.display="none";
        }else{
            if(z==""){
                //市级
                $("input:radio").eq(1).attr("checked","checked");
                $("select").eq(0).attr("disabled","disabled");
                $("select").eq(1).attr("disabled",false);
                document.getElementById("cmbCity").style.display="inline";
                document.getElementById("cmbArea").style.display="none";
                document.getElementById("rowID").style.display="";

            }else{
                $("input:radio").eq(2).attr("checked","checked");
                $("select").eq(0).attr("disabled","disabled");
                $("select").eq(1).attr("disabled",false);
                $("select").eq(2).attr("disabled",false);
                document.getElementById("cmbCity").style.display="inline";
                document.getElementById("cmbArea").style.display="inline";
                document.getElementById("rowID").style.display="";
            }
        }
        
        
        $("input:radio").eq(0).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            document.getElementById("cmbCity").style.display="none";
            document.getElementById("cmbArea").style.display="none";
            document.getElementById("rowID").style.display="none";
        });
        $("input:radio").eq(1).bind("click",function(){
            document.getElementById("cmbCity").style.display="inline";
            document.getElementById("cmbArea").style.display="none";
            $("select").eq(2).attr("disabled",false);
            $("select").eq(0).attr("disabled","disabled");
            $("select").eq(1).attr("disabled",false);
            document.getElementById("rowID").style.display="";
        });
        $("input:radio").eq(2).bind("click",function(){
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            document.getElementById("cmbCity").style.display="inline";
            document.getElementById("cmbArea").style.display="inline";
            $("select").eq(0).attr("disabled","disabled");
            $("select").eq(1).attr("disabled",false);
            $("select").eq(2).attr("disabled",false);
            document.getElementById("rowID").style.display="";
        });
        
    }else if(a=='2'){
        if(z==""){
            $("input:radio").eq(1).attr("checked","checked");
            document.getElementById("cmbArea").style.display="none";
        }else{
           $("input:radio").eq(2).attr("checked","checked");
           document.getElementById("cmbArea").style.display="inline";

       }

       $("input:radio").eq(0).attr("disabled","disabled");
        // $("input:radio").eq(1).attr("disabled","disabled");
        $("select").eq(0).attr("disabled","disabled");
        $("select").eq(1).attr("disabled","disabled");
        
        // $("select").eq(1).attr("disabled","disabled");
        $("input:radio").eq(1).bind("click",function(){
            document.getElementById("cmbArea").style.display="none";
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            $("select").eq(0).attr("disabled","disabled");
            $("select").eq(1).attr("disabled","disabled");
            document.getElementById("rowID").style.display="none";
        });
        $("input:radio").eq(2).bind("click",function(){
            document.getElementById("cmbArea").style.display="inline";
            window.onload=addressInit('cmbProvince', 'cmbCity', 'cmbArea',x,y,z);
            $("select").eq(0).attr("disabled","disabled");
            $("select").eq(1).attr("disabled","disabled");
            document.getElementById("rowID").style.display="";
        });
    }else if(a=='1'){
        $("input:radio").eq(2).attr("checked","checked");
        $("input:radio").eq(0).attr("disabled","disabled");
        $("input:radio").eq(1).attr("disabled","disabled");
        $("input:radio").eq(2).attr("disabled","disabled");
        $("input:radio").eq(2).attr("checked","checked");
        $("select").eq(0).attr("disabled","disabled");
        $("select").eq(1).attr("disabled","disabled");
        $("select").eq(2).attr("disabled","disabled");
    }
}
