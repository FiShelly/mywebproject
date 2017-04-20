function selectData(trid){
	var divid = $.getUrlParam("divid");
	console.log(divid);
	var $pdivdom = $("#"+divid,window.parent.document);
	var term = $("#"+trid).children("td:eq(0)").text();
	var typeName = $("#"+trid).children("td:eq(1)").text();
	var itemName = $("#"+trid).children("td:eq(2)").text();
	var comNum = $("#"+trid).children("td:eq(3)").text();
	var coefficient = $("#"+trid).children("td:eq(4)").text();
	var classNum = $("#"+trid).children("td:eq(5)").text();
	var stuNum = $("#"+trid).children("td:eq(6)").text();
	var isNewClass = $("#"+trid).children("td:eq(7)").text();
	var isTwoLauage = $("#"+trid).children("td:eq(8)").text();
	var allWork = $("#"+trid).children("td:eq(9)").text();
	if(term=="上"){
		$pdivdom.find("input[name=term]:eq(0)").prop("checked","checked");
	} else {
		$pdivdom.find("input[name=term]:eq(1)").prop("checked","checked");
	}
	$pdivdom.find("select[name='type.id']").find("option").each(function(i){
		if($(this).text() == typeName){
			$(this).attr("selected","selected");
		}
	});
	$pdivdom.find("input[name=itemName]").val(itemName);
	$pdivdom.find("input[name=comNum]").val(comNum);
	$pdivdom.find("input[name=coefficient]").val(coefficient);
	$pdivdom.find("input[name=classNum]").val(classNum);
	$pdivdom.find("input[name=stuNum]").val(stuNum);
	$pdivdom.find("input[name=allWork]").val(allWork);
	if(isNewClass=="是"){
		$pdivdom.find("input[name=isNewClass]:eq(0)").prop("checked","checked");
	} else {
		$pdivdom.find("input[name=isNewClass]:eq(1)").prop("checked","checked");
	}
	if(isTwoLauage=="是"){
		$pdivdom.find("input[name=isTwoLauage]:eq(0)").prop("checked","checked");
	} else {
		$pdivdom.find("input[name=isTwoLauage]:eq(1)").prop("checked","checked");
	}
	$("#copy_dia",window.parent.document).slideUp(200);
}
function filterColumn(input, column) {
    $("tbody tr").show().each(function () {
        var filterVal = input.val();
        var rowVal = this.cells[column].innerHTML;
        if (rowVal.indexOf(filterVal) < 0) {
            $(this).hide();
        }
    });
}
$(document).ready(function(){
    $(".searchType").each(function (i) {
        $(this).keyup(function () {
            filterColumn($(this), i+1);
        });
    });
});
function setBorderColor(dom,color){
    $(dom).parent().css("border-color",color);
}