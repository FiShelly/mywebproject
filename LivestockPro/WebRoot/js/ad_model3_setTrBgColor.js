//设置表格背景色
function setTrBgColor(tableid,color1,color2){
    var tab=document.getElementById(tableid);
    for(var i=0;i<tab.rows.length;i++){
        tab.rows[i].style.backgroundColor=(i%2==0)?color1:color2;
    }
}
window.onload=function(){
	// var tables=document.getElementsByTagName("table");
	// for(var j=1;j<tables.length;j++){
	setTrBgColor("animalA","#f5f5f5","white");
	setTrBgColor("animalB","#f5f5f5","white");
	setTrBgColor("productA","#f5f5f5","white");
	setTrBgColor("productB","#f5f5f5","white");
	// }
}