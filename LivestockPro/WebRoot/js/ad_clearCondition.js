function clearSearchCondition(){
	var eles=document.getElementsByTagName("input");
	var clearBtn=document.getElementById("clearBtn");
	// alert(eles.length);
	for(var i=0;i<eles.length;i++){
		if(eles[i].type=="text"){
			eles[i].value="";
		}
	}
	// clearBtn.style.backgroundColor="#83CDBE";
	// clearBtn.disabled=true;
}