function setBgColor(Id){
		var before=document.getElementsByTagName("a");
		for(var i=0;i<before.length;i++){
			if(before[i].id==Id){
				before[i].style.background="#232423";
				before[i].style.opacity="1.0";
			}else
			{
				before[i].style.background="#343535";
				before[i].style.opacity="0.8";
			}
		}
}
function setTopMenuBgColor(Id){
	var before=document.getElementsByTagName("a");
		for(var i=0;i<before.length;i++){
			if(before[i].id==Id){
				before[i].style.background="#003637";
			}else
			{
				before[i].style.background="#12A686";
			}
		}
}