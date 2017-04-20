function openNewWin(target_table,id,theWidth,theHeight){
	var left = ( screen.availWidth -  theWidth)/2;  
    var top  = ( screen.availHeight - theHeight)/2; 
	window.open(target_table+"?action=updatePre&id="+id,"修改","height="+theHeight+"px,width="+theWidth+"px,top="+top+",left="+left+",resizable=false");
}