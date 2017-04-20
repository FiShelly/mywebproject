//判断是否为空
function isEmpty(checkId){
	var ele=document.getElementById(checkId);
	if(ele.value==""){
		return true;
	}else{
		return false;
	}
}
//判断是否为正数
function isValid(checkId){
	var ele=document.getElementById(checkId);
	if(parseInt(ele.value)>=0){
		return true;
	}else{
		return false;
	} 
}
//提交之后进行的判断
function isSubmit(table_id){
	var eles=document.getElementsByTagName("input");
	// var row_number=document.getElementById(table_id).rows.length-1;
	// alert(row_number);
	var blank_sum=0;
	var valid_sum=0;
	switch(table_id){
		case "immuneContent":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="immuneDate"||eles[j].name=="series"||eles[j].name=="dose"||eles[j].name=="part"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).innerText="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							continue;
						}
					
					}
				}
			}
			if(blank_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		
		case "product_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="roomNum"||eles[j].name=="recordDate"||eles[j].name=="remainNum"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							continue;
						}
					}else if(eles[j].name=="birthNum"||eles[j].name=="putNum"||eles[j].name=="inNum"||eles[j].name=="deadNum"){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		
		case "medicine_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="startTime"||eles[j].id=="productName"||eles[j].id=="manufacturer"||eles[j].id=="processDate"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].id=="dosage"){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		case "degass_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="disinfectionTime"||eles[j].id=="place"||eles[j].id=="method"||eles[j].id=="user"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}
				}
			}
			if(blank_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		case "immune_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="immuneTime"||eles[j].id=="roomNum"||eles[j].id=="vaccineName"||eles[j].id=="vaccineProducers"||eles[j].id=="immuneMethod"
						||eles[j].id=="immuneDosage"||eles[j].id=="immunePeople"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].id=="remainNum"||eles[j].id=="immuneNum" ){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		case "treat_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="medicalTime"||eles[j].id=="sickReason"||eles[j].id=="medicalPeo"||eles[j].id=="drugName"||eles[j].id=="medicalResult"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].id=="livestockId"||eles[j].id=="dateAge"||eles[j].id=="sickNum"){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		case "prevent_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="samplingTime"||eles[j].id=="monitoringName"||eles[j].id=="monitoringStation"||eles[j].id=="monitoringResult"||eles[j].id=="disposalConditions"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].id=="samplingNum"){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
		case "handle_record":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].id=="disposalOrResult"||eles[j].id=="disposalMethod"||eles[j].id=="disposalStation"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].id=="livestockId"||eles[j].id=="number"){
						if(!isEmpty(eles[j].id)){
							if(isValid(eles[j].id)){
								continue;
							}else{
								document.getElementById(eles[j].id).value="此处必须为正数";
								document.getElementById(eles[j].id).style.color="red";
								valid_sum++;
							}
						}
					}
				}
			}
			if(blank_sum==0&&valid_sum==0){
				// alert("保存成功");
				return true;
			}
			return false;
			break;
	}
}
//用于清除错误信息
function clearError(target_id){
	var ele=document.getElementById(target_id);
	if(ele.value=="此处不可为空"||ele.value=="此处必须为正数"){
		ele.value="";
		ele.style.color="black";
	}
}