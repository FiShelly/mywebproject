/**
 * Created by FiShelly on 2016/5/14.
 */

$(document).ready(function(){
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    var ec = $.getUrlParam("errorCode");
    if(ec == 1){
        $("#error_text").css("visibility","visible");
        $("#etc").text("账号密码错误，请重新输入。");
        return false;
    } else if(ec == 2){
        $("#error_text").css("visibility","visible");
        $("#etc").text("你还未登录，请登录后再操作。");
    }
});