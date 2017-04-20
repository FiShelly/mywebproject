//checkbox
function CheckAll(){
  	var del=document.getElementById("delete");
    
    var batch=document.getElementById("batch");

	  for(var  i=0;i<document.form2.elements.length;i++){  
       var e=document.form2.elements[i];  
       // alert(form2.parentNode.id);
       if (e.name != "checkAll"){
       		 e.checked  =  document.form2.checkAll.checked;
       		 if(e.checked){
         		   	
                del.setAttribute("style","background-color:#00A882;border:0px;width: 51px;height: 22px;border-radius:2px;color:white;font-family: 宋体;font-size: 12px;");
                batch.setAttribute("style","background-color:#00A882;border:0px;width: 71px;height: 22px;border-radius:2px;color:white;font-family: 宋体;font-size: 12px;");
       		      batch.disabled=false;
                del.disabled=false;
               
           }
            else{
       		     	
                del.setAttribute("style","background-color:#83cdbe;border:0px;width: 51px;height: 22px;border-radius:2px;color:white;font-family: 宋体;font-size: 12px;");
                batch.setAttribute("style","background-color:#83cdbe;border:0px;width: 71px;height: 22px;border-radius:2px;color:white;font-family: 宋体;font-size: 12px;");

       		 } 
       }        
	}      
       
}