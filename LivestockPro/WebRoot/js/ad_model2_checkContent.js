function checkContent(){
	
	var eles=document.getElementsByTagName("input");
	var select = document.getElementsByTagName("select");
	var blank_sum=0;
	var error_sum=0;
	for(var i=0;i<eles.length;i++)
	{
		if(eles[i].type=="text")
		{
			if(eles[i].value.replace(/\s/g,'')=="")
			{
				document.getElementById(eles[i].id).innerText="此处不能为空";
				document.getElementById(eles[i].id).style.color="red";
				blank_sum++;
			}else if(eles[i].value=="此处不能为空"){
				error_sum++;
			}
		}
	}
	if(select[0].selectedIndex==0||select[2].value=="市辖区"){
		error_sum ++;
		document.getElementById("warning_message").style.display="inline";
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