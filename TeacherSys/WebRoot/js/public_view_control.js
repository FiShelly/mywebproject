/**
 * Created by FiShelly on 2016/3/28.
 */
var $lastUrl = "";
$(document).ready(function () {
	 $("#export_tc").click(function(){
		 console.log(1);
		 window.open("download.action?result="+$(".radio_btn:checked").val(),"_blank") ;
	 });
	
	 $("#query_coe input").attr("disabled","disabled");
	
	 $("#msg_list_close").click(function(){
         $("#msg_dia",window.parent.document).slideUp(200);
         $("#shadow",window.parent.document).hide();
     });
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };

    $("#close_dia").click(function(){
        $("#shadow",window.parent.document).css("display","none");
        $("#add_base_coe",window.parent.document).slideUp(200);
    });
    var parm = $.getUrlParam("state");

    if(parm == "type"){
        $("#table_title").html("类别系数");
        $("#alerm_text").html("如毕业设计，课程设计等属于类别系数");
        $("#itemName").html("类别名称");
        $("input.flag").attr("name","typeName");
        $("#action").val("addTypeCoe");
    } else if(parm == "title"){
        $("#table_title").html("职称系数");
        $("#alerm_text").html("请填写全部职称以及课程系数");
        $("#itemName").html("职称名");
        $("input.flag").attr("name","titleName");
        $("#action").val("addTitleCoe");
    }else if(parm == "post"){
        $("#table_title").html("职务补贴");
        $("#alerm_text").html("请填写全部职务以及补贴信息");
        $("#itemName").html("职务名");
        $("input.flag").attr("name","postName");
        $("#action").val("addPostCoe");
    }else if(parm == "experple"){
        $("#table_title").html("实验室人员基本课时");
        $("#alerm_text").html("请填写各项实验室人员基本课时");
        $("#itemName").html("课时项目");
        $("input.flag").attr("name","baseClassName");
        $("#action").val("addExpCoe");
    }else if(parm == "spec"){
        $("#table_title").html("特殊系数");
        $("#alerm_text").html("请填写特殊项目课程系数，如双语/新开课程");
        $("#itemName").html("项目名称");
        $("input.flag").attr("name","specItemName");
        $("#action").val("addSpecCoe");
    }

    $("#tab_baseCoe").click(function(){
        $("#tab_baseCoe").css("background-color","#0079f5");
        $("#tab_specCoe").css("background-color","#FFFFFF");
        $("#base_coe_table").css("display","block");
        $("#type_coe_table").css("display","block");
        $("#title_coe_table").css("display","block");
        $("#post_cpe_table").css("display","block");
        $("#static_coe_table").css("display","none");
        $("#experple_coe_table").css("display","none");
        $("#classin_coe_table").css("display","none");
        $("#spec_coe_table").css("display","none");
    });
    $("#tab_specCoe").click(function(){
        $("#tab_baseCoe").css("background-color","#FFFFFF");
        $("#tab_specCoe").css("background-color","#0079f5");
        $("#static_coe_table").css("display","block");
        $("#experple_coe_table").css("display","block");
        $("#classin_coe_table").css("display","block");
        $("#spec_coe_table").css("display","block");
        $("#base_coe_table").css("display","none");
        $("#type_coe_table").css("display","none");
        $("#title_coe_table").css("display","none");
        $("#post_cpe_table").css("display","none");
    });

    $("#addBase").click(function(){
        handleClick("dean_insert_bc_iframe.jsp");
    });

    $("#addClassIn").click(function(){
        handleClick("dean_insert_limit_iframe.jsp");
    });

    $("#addType").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=type");
    });

    function handleClick(url){
        $("#shadow",window.parent.document).css("display","block");
        $("#add_base_coe",window.parent.document).show(200,function(){
            if($lastUrl != url){
                $("#add_coe_iframe",window.parent.document).attr("src",url);
                $lastUrl= url;
            }
            $("#add_coe_iframe",window.parent.document).css("display","block");
        });
    }

    $("#addTitle").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=title");
    });

    $("#addPost").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=post");
    });

    $("#addStatic").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=static");
    });

    $("#addExPerple").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=experple");
    });

    $("#addSpec").click(function(){
        handleClick("dean_insert_other_iframe.jsp?state=spec");
    });


    //左侧菜单栏消失
    $("#controlSlide").click(function () {
        $("#leftMenu").toggle(200, "linear", function () {
        });
    });
    //显示设置列表
    $("#item_setting").click(function (event) {
        $("#user_msg").slideUp(200);
        $("#user_remind").slideUp(200);
        if ($("#user_setting").css("display") == "none") {
            $("#user_setting").slideDown(200);
        } else {
            $("#user_setting").slideUp(200);
        }
        event.stopPropagation();//阻止事件冒泡
    });

    $("body").click(function () {
        $("#user_setting").slideUp(200);//点击外部消失
        $("#user_msg").slideUp(200);
        $("#user_remind").slideUp(200);
    });
    //个人信息对话框显示/隐藏
    $("#personal_msg").click(function (event) {
        $("#shadow").show();
        $("#msg_dialog").show(200);
        event.stopPropagation();
    });
    //对话框关闭按钮事件
    $("#msg_dia_close").click(function (event) {
        $("#msg_dialog").slideUp(200);
        $("#shadow").hide();
        event.stopPropagation();
    });
    //修改密码的错误提示
    $("#submit").click(function () {
        if ($("#repassword").val() == "") {
            $("#repassword").get(0).setCustomValidity("请重复输入密码");
            return;
        }
        if ($("#password").val() != $("#repassword").val()) {
            $("#repassword").get(0).setCustomValidity("两次密码输入不一致");
            return;
        }
        event.stopPropagation();
    });
    //修改密码按钮点击事件（设置按钮）
    $("#update_pw_btn").click(function (event) {
        $("#shadow").show();
        $("#update_pw_dialog").show(200);
        event.stopPropagation();
    });
    //修改密码对话框取消按钮事件
    $("#cancel").click(function (event) {
        $("#update_pw_dialog").slideUp(200);
        $("#shadow").hide();
        event.stopPropagation();
    });
    $("#unit_dia_close").click(function (event) {
        $("#sal_man_dia").slideUp(200);
        $("#shadow").hide();
        event.stopPropagation();
    });
    $("#sc_dia_close").click(function (event) {
        $("#success_dia").slideUp(200);
        $("#shadow").hide();
        event.stopPropagation();
    });
    $("#failed_dia_close").click(function (event) {
        $("#failed_dia").slideUp(200);
        event.stopPropagation();
    });
    $("#close").click(function(event){
        $("#review_detail_dia",window.parent.document).slideUp(200);
        $("#insert_work_dia",window.parent.document).slideUp(200);
        $("#shadow",window.parent.document).hide();
        event.stopPropagation();
    });
    $("#close_copy_dia").click(function(event){
        $("#copy_dia",window.parent.document).slideUp(200);
        $("#shadow",window.parent.document).hide();
        event.stopPropagation();
    });
});


//增加行
function removeRow(dom) {
    if ($(".delete").length > 2) {
        $(dom).parent().parent().parent().parent().remove();
    }
}
//删除行
function addRow() {
    if ($(".add").length < 10) {
        $(".row:eq(0)").clone().insertAfter(".row:last");//插入到第二个表格的第一行后面
        $(".row:last").css("display", "block");
        $(".row:last").attr('id','div_'+new Date().getTime());
    } else {
        alert("抱歉，一次性只能添加9条工作量");
    }
}
function copyRowContent(dom){
    $("#copy_dia").slideDown(200,function(){
    	var divid = $(dom).parent().parent().parent().attr('id');
    	if($lastUrl != divid){
            $("#copy_iframe").attr("src","work_allList.action?divid="+divid);
            $lastUrl = divid;
       }
    });
    $("#shadow").show();
}

function addDeanRow() {
    if ($("div[name=add_base_con_btn]").length < 5){
        $(".base_coef_table_row:eq(3)").clone().insertAfter(".base_coef_table_row:eq(-2)");//插入到第二个表格的第一行后面
        $(".base_coef_table_row:eq(-2) input[type='text']").val("");
        $(".base_coef_table_row:eq(-2) input[type='number']").val("");
    } else {
        alert("抱歉，一次性只能添加5条课程系数记录");
    }
}
function removeDeanRow(dom) {
    if ($(".delete").length > 1) {
        $(dom).parent().parent().parent().remove();
    }
}
function diaSuc(text){
	$("#shadow", window.parent.document).show();
	$("#loading_dia", window.parent.document).hide(200);
	$("#success_dia", window.parent.document).show(200);
	$("#success_dia",window.parent.document).css({"background-image":"url(../images/gou.jpg)",
        "background-color":"#fff","background-repeat":"no-repeat","background-position":"6px 4px"});
	$("#success_dia span", window.parent.document).css("color","#000000");
	if(text!=null || text != ''){
		$("#success_dia span", window.parent.document).text(text);
	}
}
function diaFail(text){
	$("#shadow", window.parent.document).show();
	$("#loading_dia", window.parent.document).hide(200);
	$("#success_dia", window.parent.document).show(200);
	$("#success_dia",window.parent.document).css({"background-image":"url(../images/cha.png)",
        "background-color":"#fff","background-repeat":"no-repeat","background-position":"6px 4px"});
	$("#success_dia span", window.parent.document).css("color","#FF0000");
	if(text!=null || text != ''){
		$("#success_dia span", window.parent.document).text(text);
	}
}
function diaFailSecond(text){
	$("#shadow", window.parent.document).show();
	$("#loading_dia", window.parent.document).hide(200);
	$("#failed_dia", window.parent.document).show(200);
	$("#failed_dia",window.parent.document).css({"background-image":"url(../images/cha.png)",
        "background-color":"#fff","background-repeat":"no-repeat","background-position":"6px 4px"});
	$("#failed_dia span", window.parent.document).css("color","#FF0000");
	if(text!=null || text != ''){
		$("#failed_dia span", window.parent.document).text(text);
	}
}
function diaSucSecond(text){
	$("#shadow", window.parent.document).show();
	$("#loading_dia", window.parent.document).hide(200);
	$("#failed_dia", window.parent.document).show(200);
	$("#failed_dia",window.parent.document).css({"background-image":"url(../images/gou.jpg)",
        "background-color":"#fff","background-repeat":"no-repeat","background-position":"6px 4px"});
	$("#failed_dia span", window.parent.document).css("color","#000000");
	if(text!=null || text != ''){
		$("#failed_dia span", window.parent.document).text(text);
	}
}
function diaOpen(text){
	$("#shadow", window.parent.document).show();
	if(text != '' || text!=null){
		$("#loading_dia", window.parent.document).text(text);
	}
	$("#loading_dia", window.parent.document).show(200);
}
function changeYear(){
	 $("#op_iframe_query",window.parent.document).attr("src", "coe_queryAllCoe.action?msg.years="+$("#msgyears").val());
}
function logoutAccount(){
	if(confirm("确定退出系统？")){
		window.location = "loginout_logout.action";
	}
}
function subQuery(){
	$("#form1").submit();
}
function clearData(){
	$("#itemName").val('');
	$("#teahcerName").val('');
	$("#allterm").prop("checked","checked");
}
function logoutAccount(){
	if(confirm("确定退出系统？")){
		window.location = "loginout_logout.action";
	}
}