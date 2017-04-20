function selectIt(target_id){
	switch(target_id){
		case "animalA":
			var del=document.getElementById("btn_animalA");
			var datch = document.getElementById("batch_auditing");
			var allSelect=document.form_animalA.elements.length;
			var noSelect=0;
			var len=document.form_animalA.elements.length;
			// alert(len);
			for(var  i=0;i<document.form_animalA.elements.length-6;i++){  
		       var e=document.form_animalA.elements[i];
		       var checkAll=document.form_animalA.elements[0];
		       if  (e.name != "checkAll"){
		       		 if(e.checked&&del.style.display!="inline"){
		       		 	del.style.display="inline";
		       		 	datch.style.display="inline";
		       		 	// break;
		       		 }else if(!e.checked){
		       		 	noSelect++;
		       		 	allSelect--;
		       		 	checkAll.checked=false;
		       		 }
		       }
		              
			}

			if(noSelect==len-7){
				del.style.display="none";
				datch.style.display="none";
			}
			if(allSelect==len){
				checkAll.checked=true;
			}
		break;
		case "animalB":
			var del=document.getElementById("btn_animalB");
			var allSelect=document.form_animalB.elements.length;
			var noSelect=0;
			var len=document.form_animalB.elements.length;
			// alert(len);
			for(var  i=0;i<document.form_animalB.elements.length-5;i++){  
		       var e=document.form_animalB.elements[i];
		       var checkAll=document.form_animalB.elements[0];
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

			if(noSelect==len-6){
				del.style.display="none";
			}
			if(allSelect==len){
				checkAll.checked=true;
			}
		break;
		case "productA":
			var del=document.getElementById("btn_productA");
			var allSelect=document.form_productA.elements.length;
			var noSelect=0;
			var len=document.form_productA.elements.length;
			// alert(len);
			for(var  i=0;i<document.form_productA.elements.length-5;i++){  
		       var e=document.form_productA.elements[i];
		       var checkAll=document.form_productA.elements[0];
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

			if(noSelect==len-6){
				del.style.display="none";
			}
			if(allSelect==len){
				checkAll.checked=true;
			}
		break;
		case "productB":
			var del=document.getElementById("btn_productB");
			var allSelect=document.form_productB.elements.length;
			var noSelect=0;
			var len=document.form_productB.elements.length;
			// alert(len);
			for(var  i=0;i<document.form_productB.elements.length-5;i++){  
		       var e=document.form_productB.elements[i];
		       var checkAll=document.form_productB.elements[0];
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

			if(noSelect==len-6){
				del.style.display="none";
			}
			if(allSelect==len){
				checkAll.checked=true;
			}
		break;
	}
	

}

//checkbox
//用于显示删除按钮
function CheckAll(target_id){
	switch(target_id){
		case "animalA":
			var del=document.getElementById("btn_animalA");
			var datch = document.getElementById("batch_auditing");
			 for(var  i=0;i<document.form_animalA.elements.length;i++){  
		       var e=document.form_animalA.elements[i];  
		       if  (e.name != "checkAll"){
		       		 e.checked  =  document.form_animalA.checkAll.checked;
		       		 if(e.checked){
		       		 	del.style.display="inline";
		       		 	datch.style.display="inline";

		       		 }else{
		       		 	del.style.display="none";
		       		 	datch.style.display="none";
		       		 } 
		       }        
			}   
		break;
		case "animalB":
			var del=document.getElementById("btn_animalB");
			 for(var  i=0;i<document.form_animalB.elements.length;i++){  
		       var e=document.form_animalB.elements[i];  
		       if  (e.name != "checkAll"){
		       		 e.checked  =  document.form_animalB.checkAll.checked;
		       		 if(e.checked){
		       		 	del.style.display="inline";
		       		 }else{
		       		 	del.style.display="none";
		       		 } 
		       }        
			}   
		break;
		case "productA":
			var del=document.getElementById("btn_productA");
			 for(var  i=0;i<document.form_productA.elements.length;i++){  
		       var e=document.form_productA.elements[i];  
		       if  (e.name != "checkAll"){
		       		 e.checked  =  document.form_productA.checkAll.checked;
		       		 if(e.checked){
		       		 	del.style.display="inline";
		       		 }else{
		       		 	del.style.display="none";
		       		 } 
		       }        
			}   
		break;
		case "productB":
			var del=document.getElementById("btn_productB");
			 for(var  i=0;i<document.form_productB.elements.length;i++){  
		       var e=document.form_productB.elements[i];  
		       if  (e.name != "checkAll"){
		       		 e.checked  =  document.form_productB.checkAll.checked;
		       		 if(e.checked){
		       		 	del.style.display="inline";
		       		 }else{
		       		 	del.style.display="none";
		       		 } 
		       }        
			}   
		break;
	}
	   
       
}