function openDetail(){
	//弹出详情页面
	var left = ( screen.availWidth -  600)/2;  
    var top  = ( screen.availHeight - 150)/2; 
	window.open("../model2_admin_html/material_detail.html","详情","height=150px,width=600px,top="+top+",left="+left+",resizable=false");
}
function openAddContent(id){
	var left = ( screen.availWidth -  552)/2;  
    var top  = ( screen.availHeight - 61)/2; 
    window.open("ImmuneProServlet?action=updatePre&id="+id,"修改","height=80px,width=552px,top="+top+",left="+left+",resizable=false");
}