function createXMLHttp() {
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
}

function checkImmuneProSpec(species,farmId) {
	createXMLHttp();
	xmlHttp.open("POST","../ImmuneProServlet?action=checkId&species="+species+"&farmId="+farmId);
	xmlHttp.onreadystatechange = checkImmuneProCallback;
	xmlHttp.send();
}

var flag = true;

function checkImmuneProCallback() {
	if(xmlHttp.readyState == 4) {
		if(xmlHttp.status == 200) {
			var text = xmlHttp.responseText;
			if(text == "true") {
				document.getElementById("immuneSpecies_error").innerHTML="此类型动物的免疫程序表已经存在，请勿重复建立";
				document.getElementById("immuneSpecies_error").style.color="red";
				document.getElementById("immuneSpecies_error").style.border=0;
				document.getElementById("immuneSpecies_error").style.display="inline";	
				flag = false;
			}  else {
				document.getElementById("immuneSpecies_error").innerHTML="";
				document.getElementById("immuneSpecies_error").style.color="black";
				document.getElementById("immuneSpecies_error").style.border=0;
				document.getElementById("immuneSpecies_error").style.display="inline";	
				flag = true;
			}
		}
	}
}

//判断是否为空
function isEmpty(checkId){
	var ele=document.getElementById(checkId);
	if(ele.value.replace(/\s/g,'')==''){
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
	var blank_sum=0;
	var valid_sum=0;
	switch(table_id){
		case "insertData_productRecord":
		
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="roomNumber"||eles[j].name=="date"||eles[j].name=="livestockNumber"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="birthNumber"||eles[j].name=="exportNumber"||eles[j].name=="enterNumber"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_immuneProgram":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="immuneDate"||eles[j].name=="series"||eles[j].name=="dose"||eles[j].name=="part"||eles[j].name=="immuneSpecies")
					{
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
				
				return true;
			}
			return false;
			break;
			
		case "insertData_medicineRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="startDate"||eles[j].name=="productName"||eles[j].name=="producer"||eles[j].name=="processDate"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="useLevel"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_degassRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="date"||eles[j].name=="degassPlace"||eles[j].name=="batchNumber"||eles[j].name=="degassMethod"||eles[j].name=="user"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_immuneRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="date"||eles[j].name=="roomNumber"||eles[j].name=="immuneName"||eles[j].name=="immuneProducer"||eles[j].name=="immuneMethod"
						||eles[j].name=="immuneLevel"||eles[j].name=="immuneUser"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="liveStockNumber"||eles[j].name=="immuneNumber"||eles[j].name=="validTime"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_treatRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="date"||eles[j].name=="reason"||eles[j].name=="treatName"||eles[j].name=="medicineName"||eles[j].name=="result"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="animalId"||eles[j].name=="dayAge"||eles[j].name=="attackNumber"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_preventRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="samplingDate"||eles[j].name=="detectPro"||eles[j].name=="detectUnit"||eles[j].name=="detectResult"||eles[j].name=="processCon"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="sample"){
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
				
				return true;
			}
			return false;
			break;
		case "insertData_handleRecord":
			for(var j=0;j<eles.length;j++)
			{
				if(eles[j].type=="text")
				{
					if(eles[j].name=="amount"||eles[j].name=="deathReason"||eles[j].name=="processMethod"||eles[j].name=="principle"){
						if(!isEmpty(eles[j].id)&&eles[j].value!="此处不可为空"){
							continue;
						}else{
							document.getElementById(eles[j].id).value="此处不可为空";
							document.getElementById(eles[j].id).style.color="red";
							//alert(eles[j].id);
							blank_sum++;
							
						}
					}else if(eles[j].name=="animalId"){
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