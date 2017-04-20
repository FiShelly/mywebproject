 var $lastAddId = "";
var $lastQddId = "";       
$(document).ready(function () {
            $(".mainMenu > li").click(function (event) {//  左侧菜单点击事件
                event.stopPropagation();
                hideAllIframe();
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
            $("#year_term").click(function(){
                $("#shadow").css("display", "block");
                $("#sal_man_dia").show(200);
            });
            $("#teacher_add").click(function () {
                hideAllIframe();
                if ($lastAddId != "teacher_add") {
                    $("#add_iframe").attr("src", "admin_teacher_insert_iframe.jsp?cyear="+$("#cy").val());
                }
                $("#add_iframe").show();
                $lastAddId = "teacher_add";
            });
            $("#dean_add").click(function () {
                hideAllIframe();
                if ($lastAddId != "dean_add") {
                    $("#add_iframe").attr("src", "admin_dean_insert_iframe.jsp?cyear="+$("#cy").val());
                }
                $("#add_iframe").show();
                $lastAddId = "dean_add";
            });
            $("#teacher_list").click(function () {
                hideAllIframe();
                if ($lastQddId != "teacher_list") {
                    $("#query_iframe").attr("src", "user_list.action?rolee=1&cyear="+$("#cy").val());
                }
                $("#query_iframe").show();
                $lastQddId = "teacher_list";
            });
            $("#dean_list").click(function () {
                hideAllIframe();
                if ($lastQddId != "dean_list") {
                    $("#query_iframe").attr("src", "user_list.action?rolee=2&cyear="+$("#cy").val());
                }
                $("#query_iframe").show();
                $lastQddId = "dean_list";
            });
        });
        function hideAllIframe(){
            $("#query_iframe").hide();
            $("#add_iframe").hide();
        }
        function logoutAccount(){
        	if(confirm("确定退出系统？")){
        		window.location = "loginout_logout.action";
        	}
        }