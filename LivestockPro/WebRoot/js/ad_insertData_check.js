function JTrim(s)
{
    return s.replace(/(^\s*)|(\s*$)/g, "");
}

function checkContent(){
	
	var eles=document.getElementsByTagName("input");
	var blank_sum=0;
	var error_sum=0;
	for(var i=0;i<eles.length;i++)
	{
		if(eles[i].type=="text")
		{
			if(eles[i].value.replace(/\s/g,'')=='')
			{
				document.getElementById(eles[i].id).innerText="此处不能为空";
				document.getElementById(eles[i].id).style.color="red";
				blank_sum++;
			}else if(eles[i].value=="此处不能为空"){
				error_sum++;
			}
		}
	}
	if(blank_sum==0&&error_sum==0){
		alert("添加成功");
		return true;
	}else{
		return false;
	}
}

function checkRight() {

	var blank_sum=0;
	var error_sum=0;
	var eles=document.getElementsByTagName("input");
	for(var j=0;j<eles.length;j++){
		if(eles[j].type=="text"){
			if(eles[j].name=="suppliesId"||eles[j].name=="suppliesName"||eles[j].name=="suppliesPrice"||
			   eles[j].name=="number"||eles[j].name=="productDate"||eles[j].name=="validDate"||
			   eles[j].name=="failSitution"||eles[j].name=="managementstation"||eles[j].name=="producter"||
			   eles[j].name=="head"||eles[j].name=="position"||eles[j].name=="phoneNum"||eles[j].name=="address") {
				
				if(eles[j].value.replace(/\s/g,'')==''){//输入不能为空格
					eles[j].value="此处不可为空";
					eles[j].style.color="red";
						//alert(eles[j].id);
					blank_sum++;	
				}else if(eles[j].value=="此处不可为空"){
					error_sum++;
				}
				
			}
		}
	}
	if(blank_sum==0&&error_sum==0){
		alert("添加成功");
		return true;
	}else{
		return false;
	}
}

//用于清除错误信息
function clearError(target){
	if(target.value=="此处不可为空")
		{
			target.value="";
			target.style.color="black";
		}
	// if(ele.value=="此处不可为空"){
	// 	ele.value="";
	// 	ele.style.color="black";
	// }
}

 