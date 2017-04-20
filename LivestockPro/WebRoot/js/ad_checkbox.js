//checkbox
//用于显示删除按钮
function CheckAll(){
	var del=document.getElementById("delete");
	 for(var  i=0;i<document.form2.elements.length;i++){  
       var e=document.form2.elements[i];  
       if  (e.name != "checkAll"){
       		 e.checked  =  document.form2.checkAll.checked;
       		 if(e.checked){
       		 	del.style.display="inline";
       		 }else{
       		 	del.style.display="none";
       		 } 
       }        
	}      
       
}
function selectAnimals(){
       for(var  i=0;i<document.selectAnimal.elements.length;i++){  
              var e=document.selectAnimal.elements[i];
              var all=document.getElementById("selectAll");  
              if  (e.name != "selectAll"){
                      e.checked  =  document.selectAnimal.selectAll.checked;
                      if(all.checked){
                          for(var j=1;j<document.selectAnimal.elements.length;j++){
                            document.getElementById("selectTable").rows[j].style.backgroundColor="#f5f5f5";
                          }
                        }else{
                          for(var j=1;j<document.selectAnimal.elements.length;j++){
                           document.getElementById("selectTable").rows[j].style.backgroundColor="white";
                          }
                        }

              }     
       }      
}
function animalSelect(){
       var allSelect=document.selectAnimal.elements.length;
       var len=document.selectAnimal.elements.length;
       // alert(len);
       for(var  i=0;i<document.selectAnimal.elements.length;i++){  
              var e=document.selectAnimal.elements[i];  
              var selectAll=document.selectAnimal.elements[0];
              if  (e.name != "selectAll"){
                     if(!e.checked){
                            allSelect--;
                            selectAll.checked=false;
                            document.getElementById("selectTable").rows[i].style.backgroundColor="white";
                      }else{
                        document.getElementById("selectTable").rows[i].style.backgroundColor="#f5f5f5";
                      }
              }
              
       }
       if(allSelect==len){
              selectAll.checked=true;
               var eles=document.getElementsByTagName("a");
                      if(e.checked){
                          for(var j=1;j<eles.length;j++){
                            document.getElementById("selectTable").rows[j].style.backgroundColor="#f5f5f5";
                          }
                      }else{
                        for(var j=1;j<eles.length;j++){
                           document.getElementById("selectTable").rows[j].style.backgroundColor="white";
                          }
                      } 
       }
}
function selectTable(){
       var delBtn=document.getElementById("delSelect");
       var batchBtn=document.getElementById("batchUpdate");
       for(var  i=0;i<document.immuneContent.elements.length;i++){  
              var e=document.immuneContent.elements[i];  
              if  (e.name != "selectAll"){
                      e.checked  =  document.immuneContent.selectAll.checked;
                      if(e.checked){
                            delBtn.style.backgroundColor="#12a686";
                            delBtn.disabled=false;
                            batchBtn.style.backgroundColor="#12a686";
                            batchBtn.disabled=false;
                      }else{
                            delBtn.style.backgroundColor="#83CDBE";
                            delBtn.disabled=true;
                            batchBtn.style.backgroundColor="#83CDBE";
                            batchBtn.disabled=true;
                      }       
              }        
       }      
}
function tableSelect(){
       var delBtn=document.getElementById("delSelect");
        var batchBtn=document.getElementById("batchUpdate");
       var allSelect=document.immuneContent.elements.length;
       var len=document.immuneContent.elements.length;
       // alert(len);
       for(var  i=0;i<document.immuneContent.elements.length;i++){  
              var e=document.immuneContent.elements[i];  
              var selectAll=document.immuneContent.elements[0];
              if  (e.name != "selectAll"){
                     if(!e.checked){
                            allSelect--;
                            selectAll.checked=false;
                            delBtn.style.backgroundColor="#83CDBE";
                            delBtn.disabled=true;
                            batchBtn.style.backgroundColor="#83CDBE";
                            batchBtn.disabled=true;
                      }else{
                             delBtn.style.backgroundColor="#12a686";
                            delBtn.disabled=false;
                            batchBtn.style.backgroundColor="#12a686";
                            batchBtn.disabled=false;
                      }
              }
              
       }
       if(allSelect==len){
              selectAll.checked=true;
       }
}