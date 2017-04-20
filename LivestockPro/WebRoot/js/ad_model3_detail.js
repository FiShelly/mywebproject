//弹出详情页面
function openDetail(target){
	switch(target){
		case "animalA":
			var left = ( screen.availWidth -  750)/2;  
		    var top  = ( screen.availHeight - 385)/2; 
			window.open("../model3_admin_html/animalA_detail.html","动物A表详情","height=385px,width=750px,top="+top+",left="+left+",resizable=no,z-look=yes,alwaysRaised=yes,titlebar=no");
			break;
		case "animalB":
			var left = ( screen.availWidth -  620)/2;  
		    var top  = ( screen.availHeight - 305)/2; 
			window.open("../model3_admin_html/animalB_detail.html","动物B表详情","height=305px,width=620px,top="+top+",left="+left+",resizable=false");
			break;
		case "productA":
			var left = ( screen.availWidth -  460)/2;  
		    var top  = ( screen.availHeight - 240)/2; 
			window.open("../model3_admin_html/productA_detail.html","产品A表详情","height=240px,width=460px,top="+top+",left="+left+",resizable=false");
			break;
		case "productB":
			var left = ( screen.availWidth -  460)/2;  
		    var top  = ( screen.availHeight - 220)/2; 
			window.open("../model3_admin_html/productB_detail.html","产品A表详情","height=220px,width=460px,top="+top+",left="+left+",resizable=false");
			break;
	}
}
