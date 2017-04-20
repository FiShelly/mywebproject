function showPic(Id){
	var ele=document.getElementsByTagName("img");
	for(var i=0;i<ele.length;i++){
		if(ele[i].id==Id){
			ele[i].style.display="inline";
			// alert(ele[i].id);
		}else{
			ele[i].style.display="none";
		}
	}
}