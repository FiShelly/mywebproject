function selectIt(useless_sum){
	/*useless_sum是调用页面中不是单选框的数量*/
	var del=document.getElementById("btn_del");
	var allSelect=document.form_content.elements.length;
	var noSelect=0;
	var len=document.form_content.elements.length;
	var checkAll=document.form_content.elements[0];
			// alert(len);
	for(var  i=0;i<len;i++){  
		var e=document.form_content.elements[i];
		if(e.name != "checkAll"&&e.type=="checkbox"){
			if(e.checked){
				del.style.display="inline";
		       		 	// break;
		    }else if(!e.checked){
		       	noSelect++;
		       	allSelect--;
		       	checkAll.checked=false;
		    }
		}

	}
	if(noSelect==len-useless_sum-1){
		del.style.display="none";
	}
	if(allSelect==len){
		checkAll.checked=true;
	}
}


//checkbox
//用于显示删除按钮
function CheckAll(){
	
	var del=document.getElementById("btn_del");
	for(var  i=0;i<document.form_content.elements.length;i++){  
		var e=document.form_content.elements[i];  
		if  (e.name != "checkAll"){
			e.checked  =  document.form_content.checkAll.checked;
			if(e.checked){
				del.style.display="inline";

			}else{
				del.style.display="none";
			} 
		}        
	}   


}