function isValid(){
	// alert("ok");
	var selects = document.getElementsByTagName("select");
	for(var i=0;i<document.form2.elements.length;i++){
		var ele=document.form2.elements[i];
		var mark;
		if((ele.type=="text"||ele.type=="password")&&ele.id!="major"&&ele.id!="amount")
		{
			if(ele.value.replace(/\s/g,'')==''){
				document.getElementById(ele.id).focus();
				// alert("输入必填选项");

				return false;
			}else{
				continue;
			}
		}
	}
	if(document.form2.lg_password1.value!=document.form2.lg_password2.value){
		return false;
	}else if(selects[0].selectedIndex==0||selects[2].value=="市辖区"){
		document.getElementById("warning_error").innerHTML="请选择详细的地址";
		document.getElementById("warning_error").style.color="red";
		return false;
	} else{
		return true;
	}
}