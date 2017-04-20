function selectIt(){
	var del=document.getElementById("delete");
	var batch=document.getElementById("batch");

	var allSelect=document.form2.elements.length;
	var noSelect=0;
	var len=document.form2.elements.length;
	// alert(len);
	for(var  i=0;i<document.form2.elements.length;i++){  
       var e=document.form2.elements[i];  
       var checkAll=document.form2.elements[0];
       if  (e.name != "checkAll"){
       		 if(e.checked&&del.style.display!="inline"){
       		 	// del.style.display="inline";
       		 	del.setAttribute("style","border-radius:2px;background-color:#00A882;border:0px;width: 51px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
       		 
       		 	batch.setAttribute("style","border-radius:2px;background-color:#00A882;border:0px;width: 71px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
       		 	batch.disabled=false;
       		 	del.disabled=false;

       		 }else if(!e.checked){
       		 	noSelect++;
       		 	allSelect--;
       		 	checkAll.checked=false;
       		 }
       }
              
	}

	if(noSelect==len-1){
		//一个都没有选择的情况
		// del.style.display="inline";
		
		del.setAttribute("style","border-radius:2px;background-color:#83cdbe;border:0px;width: 51px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
		batch.setAttribute("style","border-radius:2px;background-color:#83cdbe;border:0px;width: 71px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
		batch.disabled=true;
		del.disabled=true;
      
	}
	if(allSelect==len){
		//全都选中的情况
		checkAll.checked=true;
		// del.style.display="inline";
       	del.setAttribute("style","border-radius:2px;background-color:#00A882;border:0px;width: 51px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
       
       	batch.setAttribute("style","border-radius:2px;background-color:#00A882;border:0px;width: 71px;height: 22px;color:white;font-family: 宋体;font-size: 12px;");
		batch.disabled=false;
		del.disabled=false;
      
	}

}