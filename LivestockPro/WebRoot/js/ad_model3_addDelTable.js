var temp = 0; //全局变量，以便修改id
var seId0; //全局变量，修改后的id值
var seId1;
var seId2;
function cloneTable(table_id){
	tbl=document.getElementById(table_id);
	if(table_id=="animalA"||table_id=="animalB"){
		var tb2=document.getElementById("inner_table");
		var t1=document.createElement("table");
		var t2=document.createElement("table");
		t1.align=tbl.align;
		t1.border=tbl.border;
	    t1.innerHTML=tbl.innerHTML;

	    t2.align=tb2.align;
		t2.border=tb2.border;
	    t2.innerHTML=tb2.innerHTML;
	    if(table_id=="animalA"){
	    	document.form_animalA.appendChild(t1);
	    	document.form_animalA.appendChild(t2);
	    }else{
	    	document.form_animalB.appendChild(t1);
	    	document.form_animalB.appendChild(t2);
	    }
	}else{
		var t3=document.createElement("table");
		t3.align=tbl.align;
		t3.border=tbl.border;
	    t3.innerHTML=tbl.innerHTML;
		var ads = t3.getElementsByTagName('select');
		seId0 = ads[0].id+temp;
		seId1 = ads[1].id+temp;
		seId2 = ads[2].id+temp;  
		t3.getElementsByTagName('select')[0].options.length = 0; //清空复制后的下拉表框
		t3.getElementsByTagName('select')[1].options.length = 0;
		t3.getElementsByTagName('select')[2].options.length = 0;
		t3.getElementsByTagName('select')[0].id=seId0;//改变Id
		t3.getElementsByTagName('select')[1].id=seId1;
		t3.getElementsByTagName('select')[2].id=seId2;
		temp++;
	    if(table_id=="productA"){
			
	    	document.form_productA.appendChild(t3);
	    }
	    else if(table_id=="productB"){
			
			document.form_productB.appendChild(t3);
			
			
	    }
		
	}
	
}
function delTable(table_id){
	var form = document.getElementById(table_id);  
	var tagElements = form.getElementsByTagName('input'); 
	var length = tagElements.length;
	var elements = new Array();   
	for ( var i=0; i<length; i++ ) {
		var e = tagElements[i];
		if ( (e.type=='hidden') ) {
			  elements.push(tagElements[i]); 
		}
	}
	if(elements.length != 1) {
		var aa = elements[elements.length-1];
		var target_table=aa.parentNode.parentNode.parentNode.parentNode;
		if(table_id=="form_productA"){
			form_productA.removeChild(target_table);
		}
		else if(table_id=="form_productB"){
			form_productB.removeChild(target_table);
		}
		
	}
	
}