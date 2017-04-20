var $lastId = "";
var tloginId = "";
function openInsertWorkDia(userName,loginId,tcoe,pcoe){
	 $("#shadow",window.parent.document).css("display","block");
     $("#insert_work_dia",window.parent.document).slideDown(200,function(){
         $("#insert_work_iframe",window.parent.document).css("display","block");
         if(tloginId != loginId){
        	 $("#insert_work_iframe",window.parent.document).attr("src","dean_insert_work_iframe.jsp?tloginId="+loginId+"&tname="+userName+"&tcoe="+tcoe+"&pcoe="+pcoe);
        	 tloginId = loginId;
         }
     });
}
function handleClick_o(url){
    $("#shadow",window.parent.document).css("display","block");
    $("#review_detail_dia",window.parent.document).slideDown(200,function(){
        $("#review_iframe",window.parent.document).attr("src",url);
        $("#review_iframe",window.parent.document).css("display","block");
    });
}
function initIframNone() {
    $("#op_iframe_review").css("display", "none");
    $("#op_iframe_query").css("display", "none");
    $("#op_iframe_add").css("display", "none");
    $("#op_iframe_historyquery").css("display", "none");
    $("#op_iframe_adjustment").css("display", "none");
    $("#op_iframe_tablecount").css("display", "none");
}
$(document).ready(function () {
            $(".mainMenu > li").click(function (event) {//  左侧菜单点击事件
                event.stopPropagation();
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
                    //   没有下拉才菜单需要重新绑定
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
                initIframNone();
                if ($(this).attr("id") == "class_sal_man") {
                    $("#shadow").css("display", "block");
                    $("#sal_man_dia").show(200);
                } else if ($(this).attr("id") == "word_review_add") {
                    if ($("#op_iframe_review").attr("src") == "") {
                        $("#op_iframe_review").attr("src", "work_listPreReview.action");
                    }
                    $("#op_iframe_review").css("display", "block");
                }
            }).mouseover(function () { //鼠标移入事件
                $(this).css("background-color", "#0055ff");
            }).mouseout(function () {   //鼠标移出事件
                $(this).css("background-color", "#00aaff");
            });

            $(".childMenu li").click(function (event) {
                event.stopPropagation();//阻止时间冒泡
                //判断显示哪个iframe
                if ($(this).attr('id') == "coe_adjustmen") {
                    initIframNone();
                    if ($("#op_iframe_adjustment").attr("src") == "") {
                        $("#op_iframe_adjustment").attr("src", "coe_akfCoe.action");
                    }
                    $("#op_iframe_adjustment").css("display", "block");
                } else if ($(this).attr('id') == "coe_query") {
                    initIframNone();
                    if ($("#op_iframe_query").attr("src") == "") {
                        $("#op_iframe_query").attr("src", "coe_queryAllCoe.action?id=-50");
                    }
                    $("#op_iframe_query").css("display", "block");
                }
//                else {
//                    var $id = $(this).attr('id');
//                    initIframNone();
//                    if($id != $lastId){
//                        $("#op_iframe_query").attr("src","dean_query_work_iframe.jsp");
//                        $lastId = $id;
//                    }
//                    $("#op_iframe_query").css("display","block");
//                }
                //恢复全部字体颜色
                $(".childMenu  li").each(function () {
                    $(this).css("color", "#000000");
                    $(this).bind("mouseout", function () {
                        $(this).css("color", "#000000");
                    });
                });
                //设置选中字体颜色
                $(this).unbind("mouseout");
                $(this).css("color", "#ffc0cb");
                if ($(this).attr('id') == "class_sal_man") {

                }
            }).mouseout(function () {
                $(this).css("color", "#000000");
            }).mouseover(function () {
                $(this).css("color", "#ffc0cb");
            });

            $("#item_msg").click(function(){
                $("#shadow").show();
                $("#msg_dia").slideDown(200,function(){
                    if( $lastId != "#remind_more"){
                        $("#msg_iframe").attr("src","msg_listFrom.action");
                    }
                    $lastId = "#msg_more";
                    $("#msg_iframe").show();
                });
            });
        });
        function historyquery(year,dom){
            var $id = $(dom).attr('id');
            initIframNone();
            if($id != $lastId){
                $("#op_iframe_query").attr("src","work_pssList.action?cyear.years="+year);
                $lastId = $id;
            }
            $("#op_iframe_query").css("display","block");
        }

        function tablecount(year,dom){
            var $id = $(dom).attr('id');
            initIframNone();
            if($id != $lastId){
                $("#op_iframe_tablecount").attr("src","work_dcList.action?queryParam=true&termChioce=3&cyear.years="+year);
                $lastId = $id;
            }
            $("#op_iframe_tablecount").css("display","block");
        }
