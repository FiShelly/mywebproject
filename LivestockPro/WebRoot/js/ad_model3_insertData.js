$(document).ready(function () {
    $("#dialog_animalA").dialog({
        autoOpen: false,
        width: "500",
        height: "550",
        modal: true,
        buttons: [
            {
            	type:"submit",
                text: "确定",
                click: function () {
                    $result = $("#iframe_animalA")[0].contentWindow.checkContent('animalA');
                    if ($result) {
                    	 $(this).dialog("close");
                    	 $("#iframe_animalA")[0].contentWindow.document.getElementById("form_animalA").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_animalA");
                    $(this).dialog("close");
                }
            }
        ]
    });
    //animalB

    $("#dialog_animalB").dialog({

        autoOpen: false,

        width: "500",
        height: "550",
        modal: true,
        buttons: [
            {
            	type:"submit",
                text: "确定",
                click: function () {
                    $result = $("#iframe_animalB")[0].contentWindow.checkContent('animalB');
                    if ($result) {
                    	 $(this).dialog("close");
                    	 $("#iframe_animalB")[0].contentWindow.document.getElementById("form_animalB").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_animalB");
                    $(this).dialog("close");
                    // window.parent.location.reload();
                }
            }
        ]
    });
//productA

    $("#dialog_productA").dialog({

        autoOpen: false,

        width: "500",
        height: "560",
        modal: true,
        buttons: [
            {
                text: "确定",
                click: function () {
                    $result = $("#iframe_productA")[0].contentWindow.checkContent('productA');
                    if ($result) {
                        $(this).dialog("close");
                        $("#iframe_productA")[0].contentWindow.document.getElementById("form_productA").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_productA");
                    $(this).dialog("close");
                }
            }
        ]
    });
//productB
    $("#dialog_productB").dialog({

        autoOpen: false,

        width: "500",
        height: "550",
        modal: true,
        buttons: [
            {
                text: "确定",
                click: function () {
                    $result = $("#iframe_productB")[0].contentWindow.checkContent('productB');
                    if ($result) {
                        $(this).dialog("close");
                        $("#iframe_productB")[0].contentWindow.document.getElementById("form_productB").submit();
                    }
                }
            },
            {
                text: "取消",
                click: function () {
                    clearEnContent("iframe_productB");
                    $(this).dialog("close");
                }
            }
        ]
    });

// Link to open the dialog
    $("#animalA_insert").click(function (event) {
        $("#dialog_animalA").dialog("open");
        event.preventDefault();
    });
    $("#animalB_insert").click(function (event) {
        $("#dialog_animalB").dialog("open");
        event.preventDefault();
    });
    $("#productA_insert").click(function (event) {
        $("#dialog_productA").dialog("open");
        event.preventDefault();
    });
    $("#productB_insert").click(function (event) {
        $("#dialog_productB").dialog("open");
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
    }
});