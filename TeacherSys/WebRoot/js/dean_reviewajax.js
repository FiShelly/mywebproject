var ptext = "";
var atext = "";
var yearterm = [{
	"termId":"",
	"isLastTerm":true
},{
	"termId":"",
	"isLastTerm":false
}];
var reviewFlag = false;

function init(year,ltid,ntid){
	yearterm[0].termId = ltid;
	yearterm[1].termId = ntid;
	getTypeByAjax(year);
	getBcByAjax(year);
	getSpecCoeByAjax(year);
	getIcByAjax(year);
}

function computeWork(id){
	var comNum = $("#td4_"+id).text();
	var coe = $("#td5_"+id).text();
	$("#td10_"+id).text((comNum*coe).toFixed(2));
	
}

function onPassAndUpdate(wid,trid,tuid,state,loginId,userName,supplement,fbContent){
	var msg = $("#tu_"+wid).val();
	var typeId = 0;
	var termId = 0;
	$.each(type, function(idx, obj) {
		if(obj.typeName == $("#td2_"+wid).text()){
			typeId = obj.id;
			return;
		}
	});
	$.each(yearterm, function(idx, obj) {
		var temp = $("#td1_"+wid).text()=='上'?true:false;
		if(obj.isLastTerm == temp){
			termId = obj.termId;
			return;
		}
	});
	var itemName = $("#td3_"+wid).text();
	var comNum = $("#td4_"+wid).text();
	var coefficient = $("#td5_"+wid).text();
	var classNum = $("#td6_"+wid).text();
	var stuNum = $("#td7_"+wid).text();
	var isNewClass = $("#td8_"+wid).text()=='是'?true:false;
	var isTwoLauage = $("#td9_"+wid).text()=='是'?"true":"false";
	var allWork = $("#td10_"+wid).text();
	diaOpen("操作中...");
	$.ajax({
		type : "post",
		url : "work_updateUc.action",
		data : {
			"work.id":wid,
			"work.type.id":typeId,
			"work.itemName":itemName,
			"work.term.id":termId,
			"work.comNum":comNum,
			"work.classNum":classNum,
			"work.stuNum":stuNum,
			"work.isNewClass":isNewClass,
			"work.isTwoLauage":isTwoLauage,
			"work.coefficient":coefficient,
			"work.fbContent":fbContent,
			"work.allWork":allWork,
			"work.state":state,
			"work.supplement":supplement,
			"work.user.loginId":loginId,
			"work.user.userName":userName,
			"queryParamTn":msg,
			"queryParam":loginId,
			},
		dataType : "json",
		success : function(data) {
			if (data == "true") {
				$("#"+trid).remove();
				diaSucSecond("操作成功。");
			} else {
				diaFailSecond("操作失败。");
			}
			reviewFlag = false;
			$("#shadow").hide();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("操作失败。");
		}
	});
}

function compleReviewSpecCoeLg(id,flag){
	$.each(spec,function(idx,obj){
		if(obj.specItemName == "双语教学"){
			if(flag=='是'){
				var res = $("#td5_"+id).text() * obj.coefficient;
				$("#td5_"+id).text(res.toFixed(2));
			} else {
				var res = $("#td5_"+id).text() / obj.coefficient;
				$("#td5_"+id).text(res.toFixed(2));
			}
		}
	});
	computeWork(id);
}

function compleReviewSpecCoeClass(id,flag){
	$.each(spec,function(idx,obj){
		if(obj.specItemName == "新开课程"){
			if(flag=='是'){
				var res = $("#td10_"+id).text() * obj.coefficient;
				$("#td10_"+id).text(res);
			} else {
				var res = $("#td10_"+id).text() / obj.coefficient;
				$("#td10_"+id).text(res);
			}
		}
	});
}

function compleReviewBaseCoe(id){
	var stuNum = $("#td7_"+id).text();
	var classNum = $("#td6_"+id).text();
	var tempO = 1;
	var tempT = 1;
	$.each(bc,function(idx,obj){
		if(classNum == obj.classNum && obj.personIn==0 && obj.personOut == 0){
			tempO = obj.coefficient;
		}
		if(classNum == obj.classNum && stuNum >= obj.personIn && stuNum <= obj.personOut){
			tempT = obj.coefficient;
		}
	});
	if(tempO >= tempT){
		$("#td5_"+id).text(tempO);
	} else {
		$("#td5_"+id).text(tempT);
	}
	computeWork(id);
}

function insertInput(e){
    reviewFlag = false;
    ptext = $(e.target).text();
    var html = "<input type='text' id='temp' class='temp' onblur='removeInput(event);'/>";
    $(e.target).html(html);
    $("#temp").val(ptext).focus();
    $(e.target).removeAttr("onclick");
    e.stopPropagation();
}
function removeInput(e){
    atext = $(e.target).val();
    $(e.target).parent().attr("onclick","insertInput(event);");
    var wid = $(e.target).parent().attr('id');
    $(e.target).parent().html(atext);
    if(ptext != atext){
        reviewFlag = true;
        if(wid.indexOf("td4")>=0){
        	computeWork(wid.split("_")[1]);
        } else if(wid.indexOf("td6")||wid.indexOf("td7")){
        	compleReviewBaseCoe(wid.split("_")[1]);
        }    
    }
}
function insertSelect(f,e){
    reviewFlag = false;
    ptext = $(e.target).text();
    var html = "";
    if(f){
        html = "<select id='temp' class='tempS' onblur='removeSelect("+f+",event);'><option >上</option><option>下</option></select>";
    } else {
        html = "<select id='temp' class='tempS' onblur='removeSelect("+f+",event);'><option >否</option><option>是</option></select>";
    }
    $(e.target).html(html);
    $("#temp").val(ptext).focus();
    $(e.target).removeAttr("onclick");
    e.stopPropagation();
}
function removeSelect(f,e){
    atext = $(e.target).val();
    $(e.target).parent().attr("onclick","insertSelect("+f+",event);");
    var wid = $(e.target).parent().attr('id');
    $(e.target).parent().html(atext);
    if(ptext != atext){
        reviewFlag = true;
        if(wid.indexOf("td9")>=0){
        	compleReviewSpecCoeClass(wid.split("_")[1],atext);
        } else if(wid.indexOf("td8")>=0){
        	compleReviewSpecCoeLg(wid.split("_")[1],atext);
        }
    }
}
$(document).ready(function(){
    $("div.unPass").each(function(){
        $(this).click(function(){
            $("#shadow").css("display","block");
            $(this).parent().next().slideDown(200);
        });
    });
    $("div.checkUp").each(function(){
        $(this).click(function(){
            $("#shadow").css("display","block");
            $(this).parent().next().next().slideDown(200);
        });
    });
    $("a.cancel").each(function(){
        $(this).click(function(){
            $("#shadow").css("display","none");
            $(this).parent().parent().slideUp(200);
        });
    });
});
function onPassWork(wid,trid,state,loginId){
	if(reviewFlag){
		$("#shadow").css("display","block");
        $("#uc_"+wid).slideDown(200);
	} else {
		if(window.confirm("确认通过此条工作量记录？")){
			onReview(wid, trid, state, '',loginId);
		}
	}
	
}
function onUnPassWork(wid,trid,taid,state){
	var fbC = $("#"+taid).val();
	onReview(wid, trid, state, fbC);
}
function onReview(wid,trid,state,fbContent,loginId){
		diaOpen("操作中...");
		$.ajax({
   		type : "post",
   		url : "work_review.action",
   		data : {"work.id":wid,"work.fbContent":fbContent,"work.state":state,"queryParam":loginId},
   		dataType : "json",
   		success : function(data) {
   			if (data == "true") {
   				$("#"+trid).remove();
   				diaSucSecond("操作成功。");
   			} else {
   				diaFailSecond("操作失败。");
   			}
   			if(state==1){
					$("#shadow").hide();
				}
   		},
   		error : function(XMLHttpRequest, textStatus, errorThrown) {
   			diaFailSecond("操作失败。");
   		}
   	});
}
