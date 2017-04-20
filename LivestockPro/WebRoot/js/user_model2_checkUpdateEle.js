// function checkEle(){
	
// }
function isEmpty(){
	var isEmpty=false;
	var eles=document.getElementsByTagName("input");
	for(var i=0;i<eles.length;i++){
		if(eles[i].type=="text"&&eles[i].value.replace(/\s/g,'')==""){
			isEmpty=true;
		}
	}
	if(isEmpty){
		return false;
	}else{
		return true;
	}
}