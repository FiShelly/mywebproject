/*为用户模块四的弹出窗口*/
$(function () {
    $("#submit_div").dialog({
        autoOpen: false,
        width: "500",
        height: "450",
        modal: true,
        buttons: [
            {
                text: "确定",
                click: function () {
                     $result = $("#iframe_submit")[0].contentWindow.checkContent();
                     if ($result) {
                        $(this).dialog("close");
                        $("#iframe_submit")[0].contentWindow.document.getElementById('epidemicForm').submit();
                     }
                }
            },
            {
                text: "取消",
                click: function () {
                     clearEnContent("iframe_submit");
                    $(this).dialog("close");

                    // window.parent.location.reload();
                }
            }
        ]
    });
    $("#update_div").dialog({
        autoOpen: false,
        width: "500",
        height: "450",
        modal: true,
        buttons: [
            {
                text: "确定",
                click: function () {
                     $result = $("#iframe_update")[0].contentWindow.checkContent();
                     if ($result) {
                    	 $(this).dialog("close");
                    	$("#iframe_update")[0].contentWindow.document.getElementById('epiform').submit();
                        
                     }
                }
            },
            {
                text: "取消",
                click: function () {
                     clearEnContent("iframe_update");
                    $(this).dialog("close");

                    // window.parent.location.reload();
                }
            }
        ]
    });
    $("#detail_div").dialog({
        autoOpen: false,
        width: "500",
        height: "390",
        modal: true,
        
    });

// Link to open the dialog
    $("#epidemic_submit").click(function (event) {
        $("#submit_div").dialog("open");
        event.preventDefault();
    });


    function clearEnContent(parent_iframe) {
        var contents = $("#"+parent_iframe)[0].contentWindow.document.getElementsByTagName("input");
        var areaContent = $("#"+parent_iframe)[0].contentWindow.document.getElementsByTagName("textarea");
        var selects = $("#"+parent_iframe)[0].contentWindow.document.getElementsByTagName("select");
        
        for(var i=0;i<contents.length;i++){
            contents[i].value="";
        }
        for(var i=0;i<areaContent.length;i++){
            areaContent[i].value="";
        }
    }
})