function checkLogin(){
	var lg_number=document.getElementById("lg_number");
	var number=lg_number.value;
	var reg = /^\d*$/;
	if(lg_number.value==""){
		document.getElementById("number_error").innerHTML="账号不能为空";
		document.getElementById("number_error").style.color="red";
		document.getElementById("number_error").style.border=0;
		document.getElementById("number_error").style.display="inline";
	}else{
		document.getElementById("number_error").innerHTML="right";
		document.getElementById("number_error").style.border=0;
		document.getElementById("number_error").style.color="green";
		document.getElementById("number_error").style.display="inline";
	}
	/*else if(lg_number.value!=""){
		if(!re"".test(number)){
			document.getElementById("number_error").innerHTML="账号必须为数字";
			document.getElementById("number_error").style.color="red";
			document.getElementById("number_error").style.border=0;
			document.getElementById("number_error").style.display="inline";
		}else{
			document.getElementById("number_error").innerHTML="right";
			document.getElementById("number_error").style.border=0;
			document.getElementById("number_error").style.color="green";
			document.getElementById("number_error").style.display="inline";
		}
	}*/
}

function checkPassword(){
	var lg_password1=document.getElementById("lg_password1");
	var lg_password2=document.getElementById("lg_password2");

		if(lg_password1.value!=lg_password2.value){
			document.getElementById("password").innerHTML="两次密码不一致";
			document.getElementById("password").style.color="red";
			document.getElementById("password").style.border=0;
			document.getElementById("password").style.display="inline";
		}else{
			document.getElementById("password").innerHTML="right";
			document.getElementById("password").style.border=0;
			document.getElementById("password").style.color="green";
			document.getElementById("password").style.display="inline";
		}
	
}
function isEmpty(id){
	var ele=document.getElementById(id);
	switch(id){
		case "lg_name":
			if(ele.value==""){
				document.getElementById("name_error").innerHTML="名称不能为空";
				document.getElementById("name_error").style.color="red";
				document.getElementById("name_error").style.border=0;
				document.getElementById("name_error").style.display="inline";
			}else{
				document.getElementById("name_error").innerHTML="right";
				document.getElementById("name_error").style.border=0;
				document.getElementById("name_error").style.color="green";
				document.getElementById("name_error").style.display="inline";
			}
			break;
		case "animalId":
			if(ele.value==""){
				document.getElementById("animalId_error").innerHTML="标识码不能为空";
				document.getElementById("animalId_error").style.color="red";
				document.getElementById("animalId_error").style.border=0;
				document.getElementById("animalId_error").style.display="inline";
			}else{
				document.getElementById("animalId_error").innerHTML="right";
				document.getElementById("animalId_error").style.border=0;
				document.getElementById("animalId_error").style.color="green";
				document.getElementById("animalId_error").style.display="inline";
			}
			break;
		case "scale":
			if(ele.value==""){
				document.getElementById("scale_error").innerHTML="规模不能为空";
				document.getElementById("scale_error").style.color="red";
				document.getElementById("scale_error").style.border=0;
				document.getElementById("scale_error").style.display="inline";
			}else{
				document.getElementById("scale_error").innerHTML="right";
				document.getElementById("scale_error").style.border=0;
				document.getElementById("scale_error").style.color="green";
				document.getElementById("scale_error").style.display="inline";
			}
			break;
		case "address":
			if(ele.value==""){
				document.getElementById("address_error").innerHTML="地址不能为空";
				document.getElementById("address_error").style.color="red";
				document.getElementById("address_error").style.border=0;
				document.getElementById("address_error").style.display="inline";
			}else{
				document.getElementById("address_error").innerHTML="right";
				document.getElementById("address_error").style.border=0;
				document.getElementById("address_error").style.color="green";
				document.getElementById("address_error").style.display="inline";
			}
			break;
		case "principle":
			if(ele.value==""){
				document.getElementById("breed_error1").innerHTML="养殖场负责人不能为空";
				document.getElementById("breed_error1").style.color="red";
				document.getElementById("breed_error1").style.border=0;
				document.getElementById("breed_error1").style.display="inline";
			}else{
				document.getElementById("breed_error1").innerHTML="";
				document.getElementById("breed_error1").style.border=0;
				document.getElementById("breed_error1").style.color="green";
				document.getElementById("breed_error1").style.display="hidden";
			}
			break;
		case "variety":
			if(ele.value==""){
				document.getElementById("breed_error2").innerHTML="养殖品种不能为空";
				document.getElementById("breed_error2").style.color="red";
				document.getElementById("breed_error2").style.border=0;
				document.getElementById("breed_error2").style.display="inline";
			}else{
				document.getElementById("breed_error2").innerHTML="right";
				document.getElementById("breed_error2").style.border=0;
				document.getElementById("breed_error2").style.color="green";
				document.getElementById("breed_error2").style.display="inline";
			}
			break;
		case "postalcode":
			var reg = /^\d*$/;
			if(ele.value==""){
				document.getElementById("person_error1").innerHTML="邮政编码不能为空";
				document.getElementById("person_error1").style.color="red";
				document.getElementById("person_error1").style.border=0;
				document.getElementById("person_error1").style.display="inline";
			}else if(ele.value!=""){
				if(!reg.test(ele.value)){
					document.getElementById("person_error1").innerHTML="邮政编码必须为数字";
					document.getElementById("person_error1").style.color="red";
					document.getElementById("person_error1").style.border=0;
					document.getElementById("person_error1").style.display="inline";
				}else{
					document.getElementById("person_error1").innerHTML="right";
					document.getElementById("person_error1").style.border=0;
					document.getElementById("person_error1").style.color="green";
					document.getElementById("person_error1").style.display="inline";
				}
			}
			break;
		case "phone_number":
			var reg = /^\d*$/;
			if(ele.value==""){
				document.getElementById("person_error2").innerHTML="联系电话不能为空";
				document.getElementById("person_error2").style.color="red";
				document.getElementById("person_error2").style.border=0;
				document.getElementById("person_error2").style.display="inline";
			}else if(ele.value!=""){
				if(!reg.test(ele.value)){
					document.getElementById("person_error2").innerHTML="联系电话必须为数字";
					document.getElementById("person_error2").style.color="red";
					document.getElementById("person_error2").style.border=0;
					document.getElementById("person_error2").style.display="inline";
				}else{
					document.getElementById("person_error2").innerHTML="right";
					document.getElementById("person_error2").style.border=0;
					document.getElementById("person_error2").style.color="green";
					document.getElementById("person_error2").style.display="inline";
				}
			}
			break;
		case "certification":
			if(ele.value==""){
				document.getElementById("certification_error").innerHTML="此项不能为空";
				document.getElementById("certification_error").style.color="red";
				document.getElementById("certification_error").style.border=0;
				document.getElementById("certification_error").style.display="inline";
			}else{
				document.getElementById("certification_error").innerHTML="right";
				document.getElementById("certification_error").style.border=0;
				document.getElementById("certification_error").style.color="green";
				document.getElementById("certification_error").style.display="inline";
			}
			break;
		case "facility_product":
			if(ele.value==""){
				document.getElementById("facility_product_error").innerHTML="此项不能为空";
				document.getElementById("facility_product_error").style.color="red";
				document.getElementById("facility_product_error").style.border=0;
				document.getElementById("facility_product_error").style.display="inline";
			}else{
				document.getElementById("facility_product_error").innerHTML="right";
				document.getElementById("facility_product_error").style.border=0;
				document.getElementById("facility_product_error").style.color="green";
				document.getElementById("facility_product_error").style.display="inline";
			}
			break;
		case "equipment":
			if(ele.value==""){
				document.getElementById("equipment_error").innerHTML="此项不能为空";
				document.getElementById("equipment_error").style.color="red";
				document.getElementById("equipment_error").style.border=0;
				document.getElementById("equipment_error").style.display="inline";
			}else{
				document.getElementById("equipment_error").innerHTML="right";
				document.getElementById("equipment_error").style.border=0;
				document.getElementById("equipment_error").style.color="green";
				document.getElementById("equipment_error").style.display="inline";
			}
			break;
		case "envior_facility":
			if(ele.value==""){
				document.getElementById("envior_facility_error").innerHTML="此项不能为空";
				document.getElementById("envior_facility_error").style.color="red";
				document.getElementById("envior_facility_error").style.border=0;
				document.getElementById("envior_facility_error").style.display="inline";
			}else{
				document.getElementById("envior_facility_error").innerHTML="right";
				document.getElementById("envior_facility_error").style.border=0;
				document.getElementById("envior_facility_error").style.color="green";
				document.getElementById("envior_facility_error").style.display="inline";
			}
			break;
	}
}