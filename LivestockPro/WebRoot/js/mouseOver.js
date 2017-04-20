function over(target_id){
	var tar = document.getElementById(target_id);
	var eles=document.getElementsByTagName("input");
	if(target_id=="animalA_degass"){
		tar.setAttribute("style","border:1px #A3E7D4 solid;width:50%;height:98%;");
	}
	// else if(target_id=="animalA_start_place"||target_id=="animalA_target_place"){
	// 	tar.setAttribute("style","border:1px #A3E7D4 solid;width:200px;");
	// }
	else if(tar.name=="animalID"){
		for(var i=0;i<eles.length;i++){
			if(eles[i].type=="text"&&eles[i].name=="animalID"){
				tar.setAttribute("style","border:1px #A3E7D4 solid;");
			}
		}
	}else{
		tar.setAttribute("style","border:1px #A3E7D4 solid;");
	}
}
function out(target_id){
	var tar = document.getElementById(target_id);
	if(target_id=="animalA_degass"){
		tar.setAttribute("style","border:1px black solid;border-left-style:none;border-right-style:"+
			"none;border-top-style:none;width:50%;height:98%;");
	}
	
	else{
		tar.setAttribute("style","border:0;");
	}
	
}