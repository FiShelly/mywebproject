/*改变li 的背景色*/
function changeLiBgColor(target_id){
	var eles=document.getElementsByTagName("li");
	var spans=document.getElementsByTagName("span");
	var checks=document.getElementsByTagName("input");
	var btn_animalA=document.getElementById("btn_animalA");
	var btn_animalB=document.getElementById("btn_animalB");
	var btn_productA=document.getElementById("btn_productA");
	var btn_productB=document.getElementById("btn_productB");
	var product=document.getElementById("product");
	
	for(var i=0;i<eles.length;i++){
		if(eles[i].id==target_id){
			eles[i].style.backgroundColor="white";
			spans[i].style.color="#00A782";
		}else{
			eles[i].style.backgroundColor="#00A782";
			spans[i].style.color="white";
		}
	}
	if(target_id=="animal_A"||target_id=="animal_B"){
		product.innerText="动物名称";
	}else{
		product.innerText="产品名称";
	}
}
/*动态显示哪个表格*/
function showTable(target_id){

	var tables=document.getElementsByTagName("table");
	for(var j=0;j<tables.length;j++){
		if(tables[j].id==target_id){
			tables[j].setAttribute("style","display:true");
		}else if(tables[j].id=="UL"||tables[j].id=="aa"){
			continue;
		}else{
			tables[j].setAttribute("style","display:none");
		}
	}
}
