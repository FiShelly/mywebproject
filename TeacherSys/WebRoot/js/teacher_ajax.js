	var type;
	var bc;
	var spec;
	var classin;

	function computeExpWork(dom,flag){
		var $domComNum;
		var $domStuNum;
		if(flag){
			$domComNum = $(dom);
			$domStuNum = $(dom).parent().siblings(".row_7").children("input");
		} else {
			$domComNum = $(dom).parent().siblings(".row_4").children("input");
			$domStuNum = $(dom);
		}
		var comNumValue = $domComNum.val()==""?1:$domComNum.val();
		var stuNumValue = $domStuNum.val()==""?1:$domStuNum.val();
		var coe = $(dom).parent().siblings(".row_5").children("input").val(); //课程系数
		var res = (comNumValue * stuNumValue)/30*coe;
		$(dom).parent().siblings(".row_10").children("input").val(res.toFixed(2));
	}
	
	function deleteWork(dom,id,flag){
		if(window.confirm("确定删除账号此工作量记录信息？")){
	    	$.ajax({
				type : "post",
				url : "work_delete.action?work.id="+id,
				data : {},
				dataType : "json",
				success : function(data) {
					if(data == "true"){
						alert("删除用户成功！");
						getDataCountByAjax();
						if(flag){
							$(dom).closest("form").parent().parent().remove();
						} else{
							$(dom).closest("form").parent().remove();
						}
						
					} else {
						alert("删除用户失败！");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(console.log(XMLHttpRequest.status));
					console.log(XMLHttpRequest.status);
				}
			});
		}
	}
	function getDataCountByAjax(){
		$.ajax({
			type : "post",
			url : "dc_getDataCountByAjax.action",
			data : {},
			dataType : "json",
			success : function(data) {
				var json = jQuery.parseJSON(data);
				$("#rangeWork").val(json.inWork);
				$("#moreWork").val(json.inOut);
				$("#aallWork").val(json.allWork);
				$("#money").val(json.allSal);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(console.log(XMLHttpRequest.status));
				console.log(XMLHttpRequest.status);
			}
		});	
	}
	
	function updateWork(dom,flag,fid){
		if(window.confirm("确定修改此工作量记录信息？")){
			var data;
			if(flag){
				data = $("#"+fid).serialize();
			} else {
				data = $(dom).parent().parent().serialize();
			}
	    	$.ajax({
				type : "post",
				url : "work_update.action",
				data : data,
				dataType : "json",
				success : function(data) {
					if(data == "true"){
						alert("修改用户成功！");
						getDataCountByAjax();
						if(flag){
							$("#"+fid).remove();
						}
					} else {
						alert("修改用户失败！");
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log(XMLHttpRequest.status);
					console.log(console.log(XMLHttpRequest.status));
					console.log(XMLHttpRequest.status);
				}
			});	
	   	}
	}

	function subWorkData(){
		var $form = $("form[name='work_form']");
		var isNext = checkForm();
		if(isNext){
			var dataParam = "";
			$.each($form,function(idx,obj){
				if(idx != 0){
					dataParam = dataParam + $(this).serialize() + "&";
				}
			});
			dataParam = dataParam.substring(0, dataParam.length-1);
			$("#shadow", window.parent.document).show();
			$("#loading_dia", window.parent.document).show(200);
			$.ajax({
				type : "post",
				url : "/TeacherWorkSys/work_addWork.action",
				data : dataParam,
				dataType : "json",
				success : function(data) {
					if (data == 0) {
						$("#shadow", window.parent.document).show();
						$("#loading_dia", window.parent.document).hide(200);
						$("#success_dia", window.parent.document).show(200);
						$("#success_dia span", window.parent.document).css("color","#000000");
						deleteRowByNum($(".fixWidth").length - 2);
						addRow();
					} else {
						$("#shadow", window.parent.document).show();
						$("#loading_dia", window.parent.document).hide(200);
						$("#success_dia", window.parent.document).show(200);
						$("#success_dia",window.parent.document).css({"background-image":"url(../images/cha.png)",
			                "background-color":"#fff","background-repeat":"no-repeat","background-position":"6px 4px"});
						$("#success_dia span", window.parent.document).css("color","#FF0000");
						$("#success_dia span", window.parent.document).text("数据添加失败。");
						
						deleteRowByNum(data);
					}
					$("#error_text").hide();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		} else {
			$(".error_text").css("display","inline-block");
		}
		
	}

	function deleteRowByNum(num) {
		for (var i = 0; i < num; i++) {
			$(".fixWidth:eq(1)").remove();
		}
	}
	function checkForm(){
		var $form = $("form[name='work_form']");
		var isRight = true;
		$.each($form,function(idx,obj){
			if(idx != 0){
				var itemName = $(this).children(".row_3").children("input[name='itemName']");
				var classNum = $(this).children(".row_6").children("input[name='classNum']");
				var stuNum = $(this).children(".row_7").children("input[name='stuNum']");
				var comNum = $(this).children(".row_4").children("input[name='comNum']");
				if(itemName.val() == "" || comNum.val() <= 0|| comNum.val()  == "" ){
					isRight = false;
					return false;
				}
				if((classNum.val()=="" || classNum.val() <= 0) && classNum.attr("disabled")!="disabled"){
					isRight = false;
					console.log(classNum.val()=="");
					console.log(classNum.val()<=0);
					console.log(classNum.attr("disabled")!="disabled");
					return false;
				}
				if((stuNum.val()=="" || stuNum.val() <= 0 )&& stuNum.attr("disabled")!="disabled"){
					isRight = false;
					return false;
				}
			}
		});
		return isRight;
	}
	
	function getIcByAjax(year){
		$.ajax({
			type : "post",
			url : "/TeacherWorkSys/ic_getIcByAjax.action?msg.years="+year,
			dataType : "json",
			success : function(data) {
				classin = jQuery.parseJSON(data);
				//$.each(json,function(idx,obj){
					//classin = obj;
				//});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	function getSpecCoeByAjax(year){
		$.ajax({
			type : "post",
			url : "/TeacherWorkSys/spec_getSpecCoeByAjax.action?msg.years="+year,
			dataType : "json",
			success : function(data) {
				spec = jQuery.parseJSON(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	function getBcByAjax(year){
		
		$.ajax({
			type : "post",
			url : "/TeacherWorkSys/bc_getBcByAjax.action?msg.years="+year,
			dataType : "json",
			success : function(data) {
				bc = jQuery.parseJSON(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	function getTypeByAjax(year){
		$.ajax({
			type : "post",
			url : "/TeacherWorkSys/type_getTypeByAjax.action?msg.years="+year,
			dataType : "json",
			success : function(data) {
				type = jQuery.parseJSON(data);
				$.each(type, function(idx, obj) {
						$("select[name='type.id']").append(
								"<option  value='"+obj.id+"'>" + obj.typeName
										+ "</option>");
				});

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				console.log(console.log(XMLHttpRequest.status));
				console.log(XMLHttpRequest.status);
			}
		});
	}
	function computeAllWork(title,post,money){
		var all = 0;
		$.each($("input[name='allWork']"),function(){
			all= all + $(this).val()*1.0;
		});
		all = all*title*1.0+post*1.0;
		if(all < classin.classInNum){
			$("#rangeWork").val(all*1.0);
		} else {
			$("#rangeWork").val(classin.classInNum);
			var temp = (all-classin.classInNum)*classin.coefficientOut;
			$("#moreWork").val(temp);
			all = classin.classInNum + temp;
		}
		$("#aallWork").val(all);
		$("#money").val(all*(money*1.0));
	}
	function compleAllCoe(dom){
		var comValue = $(dom).val();
		var coe = $(dom).parent().siblings(".row_5").children("input").val(); //课程系数
		var $dom = $(dom).parent().siblings(".row_10").children("input");		//工作量
		var cwork = comValue * coe;
		$dom.val(cwork );
		
	}
	function compleBaseCoe(dom,flag){
		var $domClass;
		var $domStuNum;
		if(flag){
			$domClass = $(dom);
			$domStuNum = $(dom).parent().siblings(".row_7").children("input");
		} else {
			$domClass = $(dom).parent().siblings(".row_6").children("input");
			$domStuNum = $(dom);
		}
		var classNum = $domClass.val();
		var stuNum = $domStuNum.val();
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
		var $dom = $(dom).parent().siblings(".row_5").children("input");
		if(tempO >= tempT){
			$dom.val(tempO);
		} else {
			$dom.val( tempT);
		}
		$(dom).parent().siblings(".row_10").children("input").val(($dom.val()*$(dom).parent().siblings(".row_4").children("input").val()).toFixed(2));
	}
	function compleSpecCoeClass(dom,flag){
		if( $(dom).parent().siblings(".row_10").children("input").val() != 0){
			var $dom = $(dom).parent().siblings(".row_10").children("input");
			$.each(spec,function(idx,obj){
				if(obj.specItemName == "新开课程"){
					if(flag){
						$dom.val($dom.val() * obj.coefficient);
					} else {
						$dom.val($dom.val() / (obj.coefficient*1.0));
					}
				}
			});
		} else {
			$(dom).next().next().prop("checked","checked");
			alert("请先填写课程的完成数目，班级数和人数，以便系统正确计算。");
		}
		
	}
	function compleSpecCoeLg(dom,flag){
		if( $dom = $(dom).parent().siblings(".row_5").children("input").val() != 1){
			var $dom = $(dom).parent().siblings(".row_5").children("input");
			$.each(spec,function(idx,obj){
				if(obj.specItemName == "双语教学"){
					if(flag){
						$dom.val($dom.val() * obj.coefficient);
					} else {
						$dom.val($dom.val() / (obj.coefficient*1.0));
					}
				}
			});
			var a = $dom.val();
			var b = $(dom).parent().siblings(".row_4").children("input").val();
			$(dom).parent().siblings(".row_10").children("input").val((a*b).toFixed(2));
		} else {
			alert("请先填写课程的完成数目，班级数和人数，以便系统正确计算。");
			$(dom).attr("checked",false);
			$(dom).next().next().prop("checked",true);
		}
	}
	function iterator(json,key){
		var res = null ;
		$.each(json,function(idx,obj){
			if(obj.id == key){
				res = obj;
				return true ;
			}
		});
		return res;
	}
	//设置项目名
	function selectItem(dom) {
		var text = $(dom).find("option:selected").text();
	    if (text == '理论教学' || text == '课程设计') {
	        $(dom).parent().next().children("input").val("");
	    } else {
	        $(dom).parent().next().children("input").val(text);
	    }
	    $(dom).parent().siblings(".row_5").children("input").val(1);
	    var obj = iterator(type,$(dom).val());
	    if(obj != null){
	    	$(dom).parent().siblings(".row_5").children("input").val(obj.coefficient);
	    }
	    if(text == '辅助专任教师上课' || text == '自行上课'){
	    	$(dom).parent().siblings(".row_9").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_8").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_6").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_7").children("input").val("");
	        $(dom).parent().siblings(".row_6").children("input").val("");
	        $(dom).parent().siblings(".row_4").children("input").val("");
	        $(dom).parent().siblings(".row_7").children("input").removeAttr("disabled");
	    	$(dom).parent().siblings(".row_7").children("input").val("").attr('onblur','computeExpWork(this,false)');
	    	$(dom).parent().siblings(".row_4").children("input").val("").attr('onblur','computeExpWork(this,true)');
	    } else if (text != '理论教学') {
	    	$(dom).parent().siblings(".row_9").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_8").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_7").children("input").attr("disabled", "disabled");
	        $(dom).parent().siblings(".row_6").children("input").attr("disabled", "disabled");
	        
	        $(dom).parent().siblings(".row_7").children("input").val("");
	        $(dom).parent().siblings(".row_6").children("input").val("");
	        $(dom).parent().siblings(".row_4").children("input").val("");
	        $(dom).parent().siblings(".row_7").children("input").val("").attr('onblur','compleBaseCoe(this,false)');
	    } else {
	        $(dom).parent().siblings(".row_9").children("input").removeAttr("disabled");
	        $(dom).parent().siblings(".row_8").children("input").removeAttr("disabled");
	        $(dom).parent().siblings(".row_7").children("input").removeAttr("disabled");
	        $(dom).parent().siblings(".row_6").children("input").removeAttr("disabled");
	        $(dom).parent().siblings(".row_7").children("input").val("").attr('onblur','compleBaseCoe(this,false)');
	    }
	}
	
