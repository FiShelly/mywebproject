$(function(){
    $( "#detail_div" ).dialog({
        autoOpen: false,
        width: "500",
        height:"480",
        modal: true,
        
    });
    $("#update_div").dialog({
        autoOpen:false,
        width:"500",
        height:"450",
        modal:true,
        buttons: [
            {
                text: "确定",
                click: function () {
                    $result = $("#iframe_update")[0].contentWindow.checkContent();
                    if ($result) {
                    	$("#iframe_update")[0].contentWindow.document.getElementById('epiform').submit();
                    	$(this).dialog("close");
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
    $("#insert_div").dialog({
        autoOpen:false,
        width:"500",
        height:"450",
        modal:true,
        buttons: [
            {
                text: "确定",
                click: function () {
                    $result = $("#iframe_insert")[0].contentWindow.checkContent();
                    if ($result) {
                        $(this).dialog("close");
                        $("#iframe_insert")[0].contentWindow.document.getElementById('epidemicForm').submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_insert");
                    $(this).dialog("close");
                    // window.parent.location.reload();
                }
            }
        ]
    });


    $("#dispose_insert").click(function(event){
        $("#insert_div").dialog("open");
        event.preventDefault();
    });

});

function clearEnContent(parent_iframe) {
    var contents = $("#" + parent_iframe)[0].contentWindow.document.getElementsByTagName("input");
    for (var i = 0; i < contents.length; i++) {
        contents[i].value = "";
    }
    
    $("#" + parent_iframe)[0].contentWindow.document.getElementById("warning_message").style.display = "none";
}