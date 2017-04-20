/**
 * Created by FiShelly on 2016/6/13.
 */
$(document).ready(function(){
    //更多按钮点击事件绑定
    $("#menuMore").bind("click",longMenu);
    //收起按钮点击事件绑定
    $("#menuShort").bind("click",shortMenu);

    //header菜单拉伸，显示完全
    function longMenu(){
        $(".hd-bottom").animate({height:"4.1rem"},200);
        $("#menuMore span").text("搞笑");
        $("#menuMore div").hide();
        $("#menuMore").unbind("click").bind("click",clickMenu);
    }
    //header菜单收缩，显示部分
    function shortMenu(){
        $(".hd-bottom").animate({height:"1.42rem"},200);
        $("#menuMore span").text("更多");
        $("#menuMore div").show();
        $("#menuMore").unbind("click").bind("click",longMenu);
    }
    //header导航点击事件
    function clickMenu(){
        $(".click").removeClass("click");
        $(this).find("span").addClass("click");
        $("article").children().remove();
        queryAllNews(1,9,$(this).find("span").text());
    }
    //注册事件
    $(".flag").click(clickMenu);

    queryAllNews(1,9,"推荐");
    //查询加载所有新闻。
    function queryAllNews(cp,ls,type){
        $.ajax({
            url:"/news/queryAll",
            data:{"cp":cp,"ls":ls,"type":type,"_csrf": $("#csrfToken").val()},
            type:'POST',
            dataType:"json",
            success:function (data) {
                console.log(data);
                //构建表格html语句
                var html = "";
                $.each(data.news,function (idx,value) {
                    var temp = "";
                    var pubDate = new Date(Date.parse(value.date.replace(/-/g, "/")));
                    var curDate = new Date();
                    var disDate = (curDate-pubDate)/(1000);
                    if(disDate<60){
                        disDate = "刚刚";
                    } else if(disDate < 3600){
                        disDate = parseInt((disDate / 60))+"分钟前";
                    } else if(disDate < 86400){
                        disDate = parseInt((disDate / 3600))+"小时前";
                    } else {
                        disDate = parseInt((disDate / 86400))+"天前";
                    }
                    if(value.imgdir != "" && value.imgdir != null){
                        temp = "<section><div class='news-item'><div class='news-img'>"+
                            "<img src='"+value.imgdir+"'></div><div class='news-content'>"+
                            "<p class='news-title'>"+value.title+"</p>"+
                            "<p class='news-title news-sum'>"+value.summary+"</p>"+
                            "<span class='news-bm news-time'>"+disDate+"</span>"+
                            "<span class='news-bm news-state'>"+value.state+"</span>"+
                            "</div></div></section>"
                    } else {
                        temp = " <section><div class='news-item'><div class='news-content'>"+
                            "<p class='news-titleb'>"+value.title+"</p>"+
                            "<p class='news-titleb news-sumb'>"+value.summary+"</p>"+
                            "<span class='news-bm news-timeb'>"+disDate+"</span>"+
                            "<span class='news-bm news-state'>"+value.state+"</span></div></div><section>"
                    }
                    $("article").append(temp);
                });
                if(data.news.length != 0){
                    $(".btn-refresh").attr("data-page",cp);
                }
            },
            error:function(data){

            }
        });
    }
    //加载更多按钮点击事件。
    $(".btn-refresh").click(function(){
        var page = $(this).attr("data-page")*1+1;
        var type = $(".click").text();
        queryAllNews(page,9,type);
    });
});