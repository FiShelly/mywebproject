/*model3_ad的检验输入函数*/
function checkContent(target_id){
	
	var eles=document.getElementsByTagName("input");
	var reg = /^\d*$/;/*验证纯数字*/
	var blank_sum=0;
	var error_sum=0;
	var number_error=0;
	switch(target_id){
		case "animalB":
			for(var i=0;i<eles.length;i++)
			{
				if(eles[i].type=="text")
				{
					if(eles[i].name=='animalB_id'||eles[i].name=='owner'||eles[i].name=='phone_number'||eles[i].name=='animal_type'
						||eles[i].name=='amount'||eles[i].name=='animalB_start_place'||eles[i].name=='animalB_target_place'
						||eles[i].name=='purpose'||eles[i].name=='doctor'||eles[i].name=='valid_date'||eles[i].name=='sign_date'
						)
					{
						if(eles[i].value.replace(/\s/g,'')==""){
							eles[i].value="此处不能为空";
							eles[i].style.color="red";
							blank_sum++;
						}else{
							if(eles[i].value=="此处不能为空"){
							error_sum++;
							}else if(eles[i].value=="此处应为数字"){
								number_error++;
							}else if(!reg.test(eles[i].value)&&(eles[i].name=="phone_number"||eles[i].name=="animal_amount"||eles[i].name=="valid_date"
								||eles[i].name=="carrier_phone")){
								number_error++;
								eles[i].value="此处应为数字";
								eles[i].style.color="red";
							}
						}
					}
				}
			}
			if(blank_sum==0&&error_sum==0&&number_error==0){
				return true;
			}else{
				return false;
			}
			break;
		case "animalA":
			for(var i=0;i<eles.length;i++)
			{
				if(eles[i].type=="text")
				{
					if(eles[i].name=='animalA_id'||eles[i].name=='owner'||eles[i].name=='phone_number'
						||eles[i].name=='animal_type'||eles[i].name=='animal_amount'||eles[i].name=='animalA_start_place'
						||eles[i].name=='animalA_target_place'||eles[i].name=='purpose'||eles[i].name=='carrier'
						||eles[i].name=='carrier_phone'||eles[i].name=='sign'||eles[i].name=='animalA_degass'
						||eles[i].name=='valid_date'||eles[i].name=='doctor'||eles[i].name=='issue_date')
					{
						if(eles[i].value.replace(/\s/g,'')==""){
							eles[i].value="此处不能为空";
							eles[i].style.color="red";
							blank_sum++;
						}else{
							if(eles[i].value=="此处不能为空"){
								error_sum++;
							}else if(eles[i].value=="此处应为数字"){
								number_error++;
							}else if(!reg.test(eles[i].value)&&(eles[i].name=="phone_number"||eles[i].name=="amount"
								||eles[i].name=="valid_date" || eles[i].name=="carrier_phone")){
								number_error++;
								eles[i].value="此处应为数字";
								eles[i].style.color="red";
							}
						}
					}
					
				}
			}
			if(blank_sum==0&&error_sum==0&&number_error==0){
				return true;
			}else{
				return false;
			}
			break;
		case "productA":
			for(var i=0;i<eles.length;i++)
			{
				if(eles[i].type=="text")
				{
					if(eles[i].name=="phone_number"||eles[i].name=="amount"||eles[i].name=="valid_date"||eles[i].name=="carrier_phone"
						||eles[i].name=='productA_id'||eles[i].name=='owner'||eles[i].name=='product_name'||eles[i].name=='produce_place'
						||eles[i].name=='produce_unit'||eles[i].name=='productA_target_place'||eles[i].name=='sign_date'||eles[i].name=='transport_man'
						||eles[i].name=='transport_phone'||eles[i].name=='transport'||eles[i].name=='doctor'||eles[i].name=='mark'||eles[i].name=='degass')
					{
						if(eles[i].value.replace(/\s/g,'')==""){
							eles[i].value="此处不能为空";
							eles[i].style.color="red";
							blank_sum++;
						}else{
							if(eles[i].value=="此处不能为空"){
								error_sum++;
							}
							else if(!reg.test(eles[i].value)&&(eles[i].name=="valid_date"||eles[i].name=="phone_number")){
								number_error++;
								eles[i].value="此处应为数字";
								eles[i].style.color="red";
							}else if(eles[i].value=="此处应为数字"){
								number_error++;
							}
						} 
						
					}
					
				}
			}
			if(blank_sum==0&&error_sum==0&&number_error==0){
				return true;
			}else{
				return false;
			}
			break;
		case "productB":
			for(var i=0;i<eles.length;i++)
			{
				if(eles[i].type=="text")
				{
					if(eles[i].name=="phone_number"||eles[i].name=="amount"||eles[i].name=="valid_date"||eles[i].name=="carrier_phone"
						||eles[i].name=='productB_id'||eles[i].name=='owner'||eles[i].name=='product_name'||eles[i].name=='produce_place1'||eles[i].name=='produce_place2'
						||eles[i].name=='produce_unit'||eles[i].name=='productB_target_place'||eles[i].name=='code'||eles[i].name=='sign_date'
						||eles[i].name=='doctor'){
						if(eles[i].value.replace(/\s/g,'')==""){
							eles[i].value="此处不能为空";
							eles[i].style.color="red";
							blank_sum++;
						}else{
							if(eles[i].value=="此处不能为空"){
								error_sum++;
							}
							else if(!reg.test(eles[i].value)&&(eles[i].name=="valid_date")){
								number_error++;
								eles[i].value="此处应为数字";
								eles[i].style.color="red";
							}else if(eles[i].value=="此处应为数字"){
								number_error++;
							}
						} 
						
					}
					
				}
			}
				
			if(blank_sum==0&&error_sum==0&&number_error==0){
				return true;
			}else{
				return false;
			}
			break;
		}
}
//用于清除错误信息
function clearError(target_id){
	var ele=document.getElementById(target_id);
	if(ele.value=="此处不能为空"||ele.value=="此处应为数字"){
		ele.value="";
		ele.style.color="black";
	}
}