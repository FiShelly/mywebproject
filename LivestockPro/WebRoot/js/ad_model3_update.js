function openUpdate(target){
	//弹出修改页面
	switch(target){
		case "animalA":
			var left = ( screen.availWidth -  700)/2;  
		    var top  = ( screen.availHeight - 280)/2; 
			window.open("../model3_admin_html/animalA_update.html","动物A表修改","height=280px,width=700px,top="+top+",left="+left+",resizable=false");
			break;
		case "animalB":
			var left = ( screen.availWidth -  600)/2;  
		    var top  = ( screen.availHeight - 210)/2; 
			window.open("../model3_admin_html/animalB_update.html","动物B表修改","height=210px,width=600px,top="+top+",left="+left+",resizable=false");
			break;
		case "productA":
			var left = ( screen.availWidth -  460)/2;  
		    var top  = ( screen.availHeight - 310)/2; 
			window.open("../model3_admin_html/productA_update.html","产品A修改","height=310px,width=460px,top="+top+",left="+left+",resizable=false");
			break;
		case "productB":
			var left = ( screen.availWidth -  460)/2;  
		    var top  = ( screen.availHeight - 280)/2; 
			window.open("../model3_admin_html/productB_update.html","产品A表修改","height=280px,width=460px,top="+top+",left="+left+",resizable=false");
			break;
	}
	
}