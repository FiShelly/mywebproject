//设置表格背景色
function setTrBgColor(tableid,color1,color2){
    var tab=document.getElementsByTagName(tableid)[0];
    for(var i=0;i<tab.rows.length;i++){
        tab.rows[i].style.backgroundColor=(i%2==0)?color1:color2;
    }
}
window.onload=function(){
    setTrBgColor("table","#f5f5f5","white");
}