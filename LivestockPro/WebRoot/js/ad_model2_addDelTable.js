function cloneTable(){
	var tbl=document.getElementById("contentTable");
	t=document.createElement("table");
	t.align=tbl.align;
	t.border=tbl.border;
    t.innerHTML=tbl.innerHTML;
	document.form1.appendChild(t);

}
function delTable(){
	var form = document.getElementById('form1');  
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
		form.removeChild(target_table);
	}
	
}