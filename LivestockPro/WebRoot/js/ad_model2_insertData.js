$(function () {
    $("#dialog_insert").dialog({
        autoOpen: false,
        width: "500",
        height: "450",
        modal: true,
        buttons: [
            {
                text: "确定",
                click: function () {
                    $result = $("#iframe_insert")[0].contentWindow.checkContent();
                    if ($result) {
                        $(this).dialog("close");
                        $("#iframe_insert")[0].contentWindow.document.getElementById("action").value="insert";
                        $("#iframe_insert")[0].contentWindow.document.getElementById("supplies_op").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_insert");
                    $(this).dialog("close");
                }
            }
        ]
    });
    $("#dialog_update").dialog({
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
                        $("#iframe_update")[0].contentWindow.document.getElementById("action").value="update";
                        $("#iframe_update")[0].contentWindow.document.getElementById("supplies_op").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_update");
                    $(this).dialog("close");
                }
            }
        ]
    });
    $("#dialog_detail").dialog({
        autoOpen: false,
        width: "1000",
        height: "540",
        modal: true,
       
    });



// Link to open the dialog
    $("#material_insert").click(function (event) {
        $("#dialog_insert").dialog("open");
        event.preventDefault();
    });



    function clearEnContent(parent_iframe) {
        var contents = $("#"+parent_iframe)[0].contentWindow.document.getElementsByTagName("input");
        var areaContent = $("#"+parent_iframe)[0].contentWindow.document.getElementsByTagName("textarea");
        for(var i=0;i<contents.length;i++){
            contents[i].value="";
        }
        for(var i=0;i<areaContent.length;i++){
            areaContent[i].value="";
        }
         $("#"+parent_iframe)[0].contentWindow.document.getElementById("warning_message").style.display="none";
    }

})