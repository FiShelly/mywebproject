function getFullPath(obj) { 
	if(obj) { 
	//ie 
		if (window.navigator.userAgent.indexOf("MSIE")>=1) { 
			obj.select(); 
			return document.selection.createRange().text; 
		} 
		//firefox 
		else if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
			if(obj.files) { 
				return obj.files.item(0).getAsDataURL(); 
			} 
			return obj.value; 
		} 
			return obj.value; 
		} 
} 