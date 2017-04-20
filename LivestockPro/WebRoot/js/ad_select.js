function selectIt(){
	var del=document.getElementById("delete");
	var allSelect=document.form2.elements.length;
	var noSelect=0;
	var len=document.form2.elements.length;
	// alert(len);
	for(var  i=0;i<document.form2.elements.length;i++){  
       var e=document.form2.elements[i];  
       var checkAll=document.form2.elements[0];
       if  (e.name != "checkAll"){
       		 if(e.checked&&del.style.display!="inline"){
       		 	del.style.display="inline";
       		 	// break;
       		 }else if(!e.checked){
       		 	noSelect++;
       		 	allSelect--;
       		 	checkAll.checked=false;
       		 }
       }
              
	}

	if(noSelect==len-1){
		del.style.display="none";
	}
	if(allSelect==len){
		checkAll.checked=true;
	}

}