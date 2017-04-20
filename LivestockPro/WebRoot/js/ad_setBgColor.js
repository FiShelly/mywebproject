function setBgColor(Id){
		var before=document.getElementsByTagName("a");
		for(var i=0;i<before.length;i++){
			if(before[i].id==Id){
				before[i].style.background="#232423";
			}else
			{
				before[i].style.background="#343535";
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