function checkContent(){
    var reg1 = /^\d*$/;//检验是否为纯数字
    var blank_sum=0;
    var error_sum=0;//是否有错误
    var eles = document.getElementsByTagName("input");
    for(var i=0;i<eles.length;i++){
        if(eles[i].type=="text"){
            if(eles[i].value.replace(/\s/g, '') == ""){
                document.getElementById(eles[i].id).value = "此处不能为空";
                document.getElementById(eles[i].id).style.color = "red";
                blank_sum++;
                
            }else if (eles[i].name == "quality" || eles[i].name == "amount" || eles[i].name == "price") {
                if (!reg1.test(eles[i].value)) {
                    document.getElementById(eles[i].id).value = "此处应为数字";
                    document.getElementById(eles[i].id).style.color = "red";
                    error_sum++;
                }
            }
            else if (eles[i].value == "此处不能为空") {
                error_sum++;
            }
        }
    }
    
    if(blank_sum==0&&error_sum==0){
        return true;
    }else{
        return false;
    }
}
//用于清除错误信息
function clearError(target_id){
    var ele=document.getElementById(target_id);
    if(ele.value=="此处不能为空"||ele.value=="此处应为数字"){
        ele.value="";
        ele.style.color="black";
    }
}

//弹出隐藏层
function ShowDiv(show_div,bg_div){
    document.getElementById(show_div).style.display='block';
    document.getElementById(bg_div).style.display='block' ;
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    $("#"+bg_div).height($(document).height());
};

//弹出隐藏层
function ShowUpdate(show_div,bg_div,suppliesId,reserveId,address,cp){
	ShowDiv(show_div,bg_div);
	document.getElementById("sup_update").contentWindow.location = 
		"SuppliesServlet?action=updatePre_supItem&suppliesId="+suppliesId+"&reserveId="+reserveId+"&supAddress="+address+"&currentPage_D="+cp;
};

//弹出隐藏层
function ShowInsert(show_div,bg_div,reserveId,address,cp){
	ShowDiv(show_div,bg_div);
	document.getElementById("sup_insert").contentWindow.location = 
		"SuppliesServlet?action=insertPre_supItem&reserveId="+reserveId+"&supAddress=+"+address;
};

//关闭弹出层
function CloseDiv(show_div,bg_div)
{
    window.parent.document.getElementById(show_div).style.display='none';
    window.parent.document.getElementById(bg_div).style.display='none';
    var inputs = document.getElementsByTagName("input");
    for(var i = 0;i < inputs.length;i++){
        if(inputs[i].name=="quality"||inputs[i].name=="date"||inputs[i].name=="meterial_number"||
            inputs[i].name=="meterial_name"||inputs[i].name=="price"||inputs[i].name=="producer"||
            inputs[i].name=="amount"){
            inputs[i].value="";
            inputs[i].style.color="black";
        }
    }
};


