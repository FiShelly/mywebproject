/**
 * Created by FiShelly on 2016/3/27.
 */
/**
 * Created by FiShelly on 2016/3/27.
 */
var $lastId = "";
var isUpdate = false;
$(document).ready(function () {
    $(".mainMenu > li").click(function (event) {//  左侧菜单点击事件
        event.stopPropagation();
//
        if ($(this).attr("id") == "mainMenu_add") {//  遍历mainMenu的li结点
            $("#op_iframe_add").css("display", "block");
            isOpen = false;
        } else {
            $("#op_iframe_add").css("display", "none");
        }
        $("#op_iframe_update").css("display","none");
        $("#op_iframe_feedback").css("display","none");
        $("#op_iframe_query").css("display","none");
        $(".mainMenu > li").each(function () {
            $(this).css("background-color", "#00aaff");

            $(this).bind("mouseout", function () {//  如果没有绑定，则绑定回去
                $(this).css("background-color", "#00aaff");
            });

            if ($(this).children("ul").length > 0) {//   判断是否是有子节点
                //  上拉动画（返回初始状态）
                // 上拉动画绑定mouseout事件回去
                if ($(this).children("ul").css("display") != "none") {
                    $(this).children("ul").slideUp(200);
                    $(this).animate({height: 40}, 200);
                    $(this).bind("mouseout");
                }
            }
        });
        //   下拉动画
        if ($(this).children("ul").length > 0) {
            if ($(this).children("ul").css("display") == "none") {
                $(this).children("ul").slideDown(200);
                var $num = $(this).children("ul").children("li").length;
                $(this).animate({height: $num * 35 + 40}, 200);
                //  防止颜色被改变，解绑mouseout事件
                $(this).unbind("mouseout");
            }
        } else {
            //   第一条没有下拉才菜单需要重新绑定
            $(this).unbind("mouseout");
        }
        // 恢复字体颜色
        $(".childMenu  li").each(function () {
            $(this).css("color", "#000000");
            $(this).bind("mouseout", function () {
                $(this).css("color", "#000000");
            });
        });
        $(this).css("background-color", "#0055ff");
    }).mouseover(function () { //鼠标移入事件
        $(this).css("background-color", "#0055ff");
    }).mouseout(function () {   //鼠标移出事件
        $(this).css("background-color", "#00aaff");
    });

    $(".childMenu li").click(function (event) {
        event.stopPropagation();//阻止时间冒泡
        //判断显示哪个iframe
        if ($(this).attr('id') == "childMenu_update") {
            $("#op_iframe_query").css("display","none");
            $("#op_iframe_feedback").css("display","none");
            if($("#op_iframe_update").attr("src") == ""){
                $("#op_iframe_update").attr("src","work_updatePre.action");
            }
            $("#op_iframe_update").css("display","block");
        } else if($(this).attr('id') == "childMenu_feedback"){
            $("#op_iframe_update").css("display","none");
            $("#op_iframe_query").css("display","none");
            if($("#op_iframe_feedback").attr("src") == ""){
                $("#op_iframe_feedback").attr("src","work_listFeedBack.action");
            }
            $("#op_iframe_feedback").css("display","block");
        } else if($(this).attr('id') == "childMenu_apply"){
            //do something
        } else {
            var $id = $(this).attr('id');
            $("#op_iframe_update").css("display","none");
            $("#op_iframe_feedback").css("display","none");
            if($id != $lastId){
                $("#op_iframe_query").attr("src","work_pssList.action?cyear.years="+ $id);
                $lastId = $id;
            }
            $("#op_iframe_query").css("display","block");
        }
        $("#op_iframe_add").css("display", "none");
        //恢复全部字体颜色
        $(".childMenu  li").each(function () {
            $(this).css("color", "#000000");
            $(this).bind("mouseout",function(){
                $(this).css("color", "#000000");
            });
        });
        //设置选中字体颜色
        $(this).unbind("mouseout");
        $(this).css("color", "#ffc0cb");
    }).mouseout(function () {
        $(this).css("color", "#000000");
    }).mouseover(function () {
        $(this).css("color", "#ffc0cb");
    });
    $("#uad_cancel").click(function (event) {
        $("#shadow").hide();
        $("#update_apply_dia").hide(200);
    });
    $("#item_msg").click(function(){
        $("#shadow").show();
        $("#msg_dia").slideDown(200,function(){
            if($("#msg_iframe").attr("src") == ""){
                $("#msg_iframe").attr("src","msg_listTo.action");
            }
            $("#msg_iframe").show();
        });
    });
});

//设置修改申请对话框弹出事件
function findRepeatAndOpenDia(){
	if(isUpdate){
		 $("#shadow").show();
		 $("#update_apply_dia").show(200);
	} else {
		$.ajax({
			type : "post",
			url : "msg_findRepeat.action",
			data :{},
			dataType : "json",
			success : function(data) {
				if(data == "true"){
					isUpdate = true;
					$("#shadow").show();
					$("#update_apply_dia").show(200);
				} else {
					diaFail("你已经提交过修改申请，请勿重复提交！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				diaFail("请求失败！");
				console.log(XMLHttpRequest.status);
				console.log(console.log(XMLHttpRequest.status));
				console.log(XMLHttpRequest.status);
			}
		});	
	}
}
//提交修改申请
function subUpdateApply(){
	$.ajax({
		type : "post",
		url : "msg_addMsg.action",
		data :$("#update_apply").serialize(),
		dataType : "json",
		success : function(data) {
			if(data == "true"){
				isUpdate = false;
				diaSuc("修改申请提交成功！");
			} else {
				diaFail("修改申请提交失败，请稍后重试。");
			}
			$("#update_apply_dia").slideUp(200);
			$("#update_reason").val("");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFail("请求失败！");
			$("#update_apply_dia").slideUp(200);
			console.log(XMLHttpRequest.status);
			console.log(console.log(XMLHttpRequest.status));
			console.log(XMLHttpRequest.status);
		}
	});	
}
function updatePwByAjax(){
	if($lastId == "true"){
		
	}
	$.ajax({
		type : "post",
		url : "user_updatePw.action",
		data :$("#update_pw").serialize(),
		dataType : "json",
		success : function(data) {
			if(data == "true"){
				alert("修改用户密码成功！");
				$("#update_pw_dialog").slideUp(200);
			    $("#shadow").hide();
				$("#password").val("");
				$("#repassword").val("");
			} else {
				alert("修改用户密码失败！");
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status);
			console.log(console.log(XMLHttpRequest.status));
			console.log(XMLHttpRequest.status);
		}
	});	
	return false;
}