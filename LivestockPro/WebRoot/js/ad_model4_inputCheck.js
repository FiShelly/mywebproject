function checkContent(){
	var error_sum=0;
	var blank_sum=0;
	var inputs = document.getElementsByTagName("input");
	var warning_message=document.getElementById("warning_message");
	var selects = document.getElementsByTagName("select");
	if(selects[0].selectedIndex==0||selects[2].value=="市辖区"){
		warning_message.style.display="inline";
		error_sum++;
	}
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].type=="text"){
			if(inputs[i].value.replace(/\s/g,'')==""){
				inputs[i].value="此处不能为空";
				inputs[i].style.color="red";
				error_sum++;
			}else if(inputs[i].value=="此处不能为空"){
				blank_sum++;
			}
		}
		
	}
	if(blank_sum==0&&error_sum==0){
		return true;
	}else{
		return false;
	}
}
//用于清除错误信息
function clearError(target_id){
	var ele=document.getElementById(target_id);
	if(ele.value=="此处不能为空"){
		ele.value="";
		ele.style.color="black";
	}
}
