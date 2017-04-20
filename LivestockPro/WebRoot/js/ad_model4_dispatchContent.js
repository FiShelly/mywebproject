function checkContent(){
    var reg1 = /^\d*$/;//检验是否为纯数字
    var blank_sum=0;
    var error_sum=0;//是否有错误
    var eles = document.getElementsByTagName("input");
    var selects = document.getElementsByTagName("select");
    
    if(selects[0].selectedIndex==0||selects[2].value=="市辖区"){
        $("#warning_message").css("display","inline");
        error_sum++;
    }

    if(document.getElementById("valid").innerHTML=="是"
        ||document.getElementById("meterial_number").innerHTML<=0){
        $("#warning_message2").css("display","inline");
    error_sum++;
}
for(var i=0;i<eles.length;i++){
    if(eles[i].type=="text"){
        if(eles[i].value.replace(/\s/g, '') == ""){
            document.getElementById(eles[i].id).value = "此处不能为空";
            document.getElementById(eles[i].id).style.color = "red";
            blank_sum++;

        }else if(eles[i].id=="date_end"){
        //输入的时间
            var date = document.getElementById(eles[i].id).value;
            var day = date.substr(8,9);
            var month = date.substring(5,7);
            var year = date.substr(0,4);
            //系统时间
            var myDate = new Date();
            // alert(myDate.getDate());
            if((year<myDate.getFullYear())||(year>=myDate.getFullYear()&&month<myDate.getMonth()+1)||
                (year>=myDate.getFullYear()&&month>=myDate.getMonth()+1&&day<=myDate.getDate())){
                $("#warning_message3").css("display","inline");
                error_sum++;
            }
        }else if(eles[i].id=="amount"){
            var number = parseInt(document.getElementById(eles[i].id).value);
            if(number>document.getElementById("meterial_number").innerHTML||number<=0){
                $("#warning_message2").css("display","inline");
                error_sum++;
            }
            if(!reg1.test(eles[i].value)){
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
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
    window.parent.document.getElementById(show_div).style.display='none';
    window.parent.document.getElementById(bg_div).style.display='none';
    var inputs = document.getElementsByTagName("input");
    for(var i = 0;i < inputs.length;i++){
        if(inputs[i].type!="button"&&inputs[i].type!="submit"){
            inputs[i].value="";
            inputs[i].style.color="black";
        }
        
    }
    $("#warning_message").css("display","none");
    $("#warning_message2").css("display","none");
    $("#warning_message3").css("display","none");
};