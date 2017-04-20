/**
 * Created by FiShelly on 2016/5/15.
 */
var $loginId = "";
var $tempId = "";
$(document).ready(function(){
    //菜单隐藏
    $("#controlSlide").click(function(){
        var text = $("#leftMenu").css("display");
        if(text == "block"){
            $("#content").removeClass(" col-sm-9 col-md-9 col-lg-10 ").
                addClass(" col-sm-12 col-md-12 col-lg-12 ").show(300);
        } else {
            $("#content").removeClass("col-xs-12 col-sm-12 col-md-12 col-lg-12 content").
                addClass("col-xs-12 col-sm-9 col-md-9 col-lg-10 content");
        }
        $("#leftMenu").toggle(200);
    });
    //获取url的后代参数
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    //模态框居中显示
    function centerModals(){
        $('.modal').each(function(i){
            var $clone = $(this).clone().css('display', 'block').appendTo('body');    var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
            top = top > 0 ? top : 0;
            $clone.remove();
            $(this).find('.modal-content').css("margin-top", top);
        });
    }
    $('.modal').on('show.bs.modal', centerModals);
    $(window).on('resize', centerModals);

    //注册用户操作。
    $("#registUser").click(function () {
        //验证账号密码不为空
        if($("#userName").val().trim()!="" && $("#pwd").val().trim()!=""){
            //ajax添加用户。
            $.ajax({
                url:"/users/insert?_csrf="+$("#csrfToken").val(),
                data:$("#user_form").serialize(),
                type:'POST',
                dataType:"json",
                success:function (data) {
                    operatorSuc(data,"添加成功！","添加失败！");
                },
                error:function(data){
                    operatorFail("添加失败！");
                }
            });
        } else {
            operatorFail("请填写账号密码！");
        }

    });
    //检测账号是否被注册
    $("#userName").blur(function(){
        //利用ajax来异步检测用户名是否存在
        $.ajax({
            url:"/users/queryOne",
            data:{"loginId":$("#userName").val(),"_csrf": $("#csrfToken").val()},
            type:'POST',
            dataType:"json",
            success:function (data) {
                if(data==2){
                    operatorFail("此账号已被注册！");
                    $("#userName").val("").focus();
                }
            },
            error:function(data){

            }
        });
    });
    //添加新闻操作
    $("#add_news").click(function(){
        //标题和摘要不能为空
        if($("#new-title").val().trim() != "" && $("#new-sum").val().trim() != ""){
            //可用formData和ajax来异步操作。
            var formData = new FormData($("#news_form")[0]);
            formData.append("CustomField", "This is some extra data");

            $.ajax({
                url:"/news/insert?_csrf="+$("#csrfToken").val(),
                data:formData,
                type:'POST',
                dataType:"json",
                processData: false,
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success:function (data) {
                    queryAllNews(1,9,1);
                    operatorSuc(data,"添加成功！","添加失败！");
                },
                error:function(data){
                    operatorFail("添加失败！");
                }
            });
        } else {
            operatorFail("请填写新闻标题以及摘要！");
        }
    });
    //操作失败显示提示。
    function operatorFail(text){
        $("#fbcontent").text(text);
        $("#fb_header").addClass("alert-danger");
        $("#opfeedback").modal("show");
    }
    //操作成功显示提示
    function operatorSuc(data,stext,ftext){
        if(data==-1){
            window.location.href = "login.html?errorCode=2"
        } else if(data == 1){
            $("#fbcontent").text(stext);
            $("#fb_header").addClass("alert-success");
            $("#opfeedback").modal("show");
            clearInput();
        } else {
            $("#fbcontent").text(ftext);
            $("#fb_header").addClass("alert-danger");
            $("#opfeedback").modal("show");
        }
    }
    //清空输入框
    function clearInput(){
        $("#news_form input").val("");
        $("#news_form textarea").val("");
        $("#user_form input").val("");
    }
    //初次查询
    queryAllNews(1,9,1);
    //分页查询新闻。
    function queryAllNews(cp,ls,pp,flag){
        $.ajax({
            url:"/news/queryAll",
            data:{"cp":cp,"ls":ls,"_csrf": $("#csrfToken").val()},
            type:'POST',
            dataType:"json",
            success:function (data) {
                if(data==-1){
                    window.location.href = "index?errorCode=2"
                } else {
                    //构建表格html语句
                    var html = "";
                    $.each(data.news,function (idx,value) {
                        var temp = "";
                        //如果为1则表示操作成功，将对应行变色
                        //如果为2则表示失败，
                        //如果为3则表示未做操作。
                        if(flag == 1 && value.id == $tempId){
                            temp = "<tr class='success' id='tr_"+value.id+"'>";
                        } else if(flag == 2 && value.id == $tempId){
                            temp = "<tr class='danger' id='tr_"+value.id+"'>";
                        } else {
                            temp = "<tr id='tr_"+value.id+"'>";
                        }
                         temp +=
                            "<td>"+value.title+"</td>" +
                            "<td>"+value.summary+"</td>" +
                            "<td>"+value.date+"</td>" +
                            "<td>"+value.type+"</td>" +
                            "<td>"+value.state+"</td>" +
                            "<td><span data-id='"+value.id+"' class='op-btnu glyphicon glyphicon-pencil' aria-hidden='true'></span>"+
                            "<span data-id='"+value.id+"' class='op-btnd glyphicon glyphicon-trash' aria-hidden='true'></span></td>" +
                            "</tr>";
                        html += temp;
                    });
                    $("#news_tbody").html(html);
                    //分页栏创建
                    if(pp == 1){
                        html="<li class='disabled'><a href='#' aria-label='Previous' ><span aria-hidden='true'>«</span></a></li>";
                    } else {
                        html="<li><a href='#' class='page-previous' aria-label='Previous'><span aria-hidden='true'>«</span></a></li>";
                    }
                    // 构建分页栏html语句
                    for(var i = 1;i<=data.allPage;i++){
                        if(i == pp){
                            html+="<li class='active'><a href='#' class='page'>"+i+"</a></li>"
                        } else {
                            html+="<li ><a href='#' class='page'>"+i+"</a></li>"
                        }
                    }
                    if(pp == data.allPage){
                        html+="<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>»</span></a></li>"
                    } else {
                        html+="<li><a class='page-next' href='#' aria-label='Next'><span aria-hidden='true'>»</span></a></li>"
                    }
                    $(".pagination").html(html);
                    // 为分页按钮添加事件
                    $(".page").click(function(){
                        queryAllNews($(this).text(),9,$(this).text());
                    });
                    $(".page-next").click(function(){
                        var p = $("li.active a").text()*1+1;
                        queryAllNews(p,9,p);
                    });
                    $(".page-previous").click(function(){
                        var p = $("li.active a").text()*1-1;
                        queryAllNews(p,9,p);
                    });
                    //为修改按钮添加事件
                    $(".op-btnu").click(function(){
                        var id = $(this).attr("data-id");
                        $.ajax({
                            url:"/news/queryOne",
                            data:{"id":id,"_csrf": $("#csrfToken").val()},
                            type:'POST',
                            dataType:"json",
                            success:function (data) {
                                if(data==-1){
                                    window.location.href = "index?errorCode=2"
                                } else {

                                    $("#unew-title").val(data.title);
                                    $("#unew-sum").val(data.summary);
                                    $("#udimg").prop("src",data.imgdir);
                                    $("#unew-type").val(data.type);
                                    $("#unew-state").val(data.state);
                                    $("#rphoto").val(data.imgdir);
                                    $("#nid").val(data.id);
                                    $tempId = data.id;
                                    $("#update-news-modal").modal("show");
                                }
                            },
                            error:function(data){

                            }
                        });
                    });
                    //为删除按钮添加事件
                    $(".op-btnd").click(function(){
                        if(window.confirm("确定删除此条新闻？")){
                            var id = $(this).attr("data-id");
                            $.ajax({
                                url:"/news/delete",
                                data:{"id":id,"_csrf": $("#csrfToken").val()},
                                type:'POST',
                                dataType:"json",
                                success:function (data) {
                                    operatorSuc(data,"删除成功！","删除失败！");
                                    $("#tr_"+id).remove();
                                },
                                error:function(data){
                                    operatorFail("删除失败！");
                                }
                            });
                        }
                    });
                }
            },
            error:function(data){

            }
        });
    }
    //上传预览。
    $("input[type=file]").change(function(){
        var tempId = $(this).attr("id");
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.onload = function (e) {
            if(tempId == "upload-file"){
                $("#adimg").prop("src",this.result);
            } else {
                $("#udimg").prop("src",this.result);
            }
        }
    });
    //修改新闻操作
    $("#update_news").click(function(){
        if($("#unew-title").val().trim() != "" && $("#unew-sum").val().trim() != ""){
            var formData = new FormData($("#unews_form")[0]);
            formData.append("CustomField", "This is some extra data");
            $.ajax({
                url:"/news/insert?_csrf="+$("#csrfToken").val(),
                data:formData,
                type:'POST',
                dataType:"json",
                processData: false,
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success:function (data) {
                    operatorSuc(data,"修改成功！","修改失败！");
                    var p = $("li.active a").text()*1;
                    if(data == 1){
                        queryAllNews(p,9,p,1);
                        $("#update-news-modal").modal("hide");
                    } else {
                        queryAllNews(p,9,p,2);
                    }
                },
                error:function(data){
                    var p = $("li.active a").text()*1;
                    operatorFail("修改失败！");
                    queryAllNews(p,9,p,2);
                }
            });
        } else {
            operatorFail("请填写新闻标题以及摘要！");
        }
    });

});