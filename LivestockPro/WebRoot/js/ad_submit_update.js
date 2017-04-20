function isValid(){
	// alert("ok");
	for(var i=0;i<document.form2.elements.length;i++){
		var ele=document.form2.elements[i];
		var mark;
		if((ele.type=="text")&&ele.id!="major"&&ele.id!="amount")
		{
			if(ele.value==""){
				document.getElementById(ele.id).focus();
				// alert("输入必填选项");

				return false;
			}else{
				continue;
			}
		}
	}
	
	if(document.form2.lg_password1.value != document.form2.lg_password2.value) {
		document.form2.lg_password1.focus();
		return false;
	} else {
		return true;
	}
	
}