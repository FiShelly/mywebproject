function clearSearchCondition(){
	var eles=document.getElementsByTagName("input");
	var clearBtn=document.getElementById("clearBtn");
	// alert(eles.length);
	for(var i=0;i<eles.length;i++){
		if(eles[i].type=="text"){
			eles[i].value="";
		}else if(eles[i].type=="checkbox"){

			if(eles[i].id=="highway"||eles[i].id=="waterway"||eles[i].id=="railway"||eles[i].id=="aviation"){
				eles[i].checked=false;
			}else{
				continue;
			}
			
		}
	}
	document.getElementById("resultTd").style.display="none";
}