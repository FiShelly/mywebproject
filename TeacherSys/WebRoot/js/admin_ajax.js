function changeCurYear(year){
	diaOpen("设置中，请稍等...");
	$.ajax({
		type:"post",
		url:"admin_updateCurYear.action",
		data:{
			"msg.years":year,
		},
		dataType:"json",
		success:function(data){
			if(data == "true"){
				diaSucSecond("设置成功。");
				var idx1 = -10,idx2 = -10;
				$("div.history_unit_sal_row span").each(function(idx){
					var text = $.trim($(this).text());
					if(text == "是"){
						idx1 = idx; 
					}
					if(text == year){
						idx2 = idx;
					}
				});
				var val = "";
				$("div.history_unit_sal_row span").each(function(idx){
					if(idx1 - 2 == idx ) {
						val = $(this).text();
					}
					if(idx1 == idx){
						var html = "<a onclick='changeCurYear("+val+");'  class='history_link'>否</a>";
						$(this).html(html);
					}
					if(idx2+2 == idx){
						$(this).text("是");
					}
				});
				diaOpen("重置用户列表，请稍等...");
				updateUserCoe(year);
			} else {
				diaFailSecond("设置失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("设置失败");
		},
	});
}

function updateUserCoe(year){
	$.ajax({
		type : "post",
		url : "coe_updateUser.action",
		data : {"msg.years":year},
		dataType : "json",
		success : function(data) {
			console.log(data);
			if(data == "true"){
				diaSuc("操作成功！");
			} else {
				diaFail("操作失败！");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFail("操作失败！");
		}
	});
}

function deleteUser(dom,loginId){
	if(window.confirm("确定删除账号为 "+loginId+" 的信息？")){
		var index = $(dom).closest("tr").index()-1;
    	$.ajax({
			type : "post",
			url : "user_delete.action?user.loginId="+loginId,
			data : {},
			dataType : "json",
			success : function(data) {
				if(data == "true"){
					diaSuc("删除用户成功！");
					$(dom).closest("tr").remove();
				} else {
					diaFail("删除用户失败！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				diaFail("删除用户失败！");
				console.log(XMLHttpRequest.status);
				console.log(console.log(XMLHttpRequest.status));
				console.log(XMLHttpRequest.status);
			}
		});
	}
	
}
function updateUser(dom,loginId,role){
	if(window.confirm("确定修改账号为 "+loginId+" 的信息？")){
	var index = $(dom).closest("tr").index()-1;
	$.ajax({
		type : "post",
		url : "user_update.action",
		data : {
			"user.loginId": $("input[name='loginId']:eq("+index+")").val(),
			"user.userName":$("input[name='userName']:eq("+index+")").val(),
			"user.birthDate":$("input[name='birthDate']:eq("+index+")").val(),
			"user.title.id":$("select[name='title.id']:eq("+index+")").val(),
			"user.post.id":$("select[name='post.id']:eq("+index+")").val(),
			"user.state":$("select[name='state']:eq("+index+")").val(),
			"user.pw":$("input[name='pw']:eq("+index+")").val(),
			"user.role":role,
		},
		dataType : "json",
		success : function(data) {
			if(data == "true"){
				diaSuc("修改用户成功！");
			} else {
				diaFail("修改用户失败！");
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFail("修改用户失败！");
			console.log(XMLHttpRequest.status);
			console.log(console.log(XMLHttpRequest.status));
			console.log(XMLHttpRequest.status);
		}
	});	
	}
}
function resetPw(id){
	if(window.confirm("确定重置账号为 "+id+" 的登录密码？")){
		$.ajax({
	   		type:"post",
	   		url:"user_updatePw.action",
	   		data:{
	   			"user.loginId":id,
	   			"user.pw":123456,
	   		},
	   		dataType:"json",
	   		success:function(data){
				if(data == "true"){
					diaSuc("密码重置成功。");
				} else {
					diaFail("密码重置修改失败");
				}
	   		},
	   		error : function(XMLHttpRequest, textStatus, errorThrown) {
	   			diaFail("修改失败");
	   		},
	    });
	}
}

function updatePw(id){
	var pw = $("#password").val();
	var repw = $("#repassword").val();
	if(pw == repw){
		$.ajax({
    		type:"post",
    		url:"admin_updatePw.action",
    		data:{
    			"loginId":id,
    			"pw":pw,
    			"role":3,
    		},
    		dataType:"json",
    		success:function(data){
    			if(data == "true"){
    				diaSuc("密码修改成功。");
        			$("#update_pw_dialog").slideUp(200);
        			$("#shadow").hide();
    			} else {
    				diaFail("修改失败");
    			}
    			
    		},
    		error : function(XMLHttpRequest, textStatus, errorThrown) {
    			diaFail("修改失败");
			},
    	});
	} else {
		alert("两次密码输入不一致，请确定后重新输入。");
	}
	
}
function checkYearExist() {
		var val = $("input#yearmsg").val();
		var s="";
		var theDate=new Date();
		s += theDate.getFullYear()+"-";                         // 获取年份。
		s += (theDate.getMonth() + 1) + "-";            // 获取月份。
		s += theDate.getDate(); 
		diaOpen("正在创建，请稍等...");
		$.ajax({
			type : "post",
			url : "admin_checkYearExist.action?msg.years="+val,
			data : {},
			dataType : "json",
			success : function(data) {
				if (data == "true") {
					$("#error_text").show();
				} else {
					$("#error_text").hide();
						$.ajax({
		    				type : "post",
		    				url : "admin_addYear.action?msg.years="+val,
		    				data : {},
		    				dataType : "json",
		    				success : function(data) {
		    					if(data == "true"){
		    						$("div.history_unit_sal_row:eq(0)").clone().insertAfter("div.history_unit_sal_row:eq(0)");
		    						$("div.history_unit_sal_row:eq(1) span:eq(0)").text(val);
		    						$("div.history_unit_sal_row:eq(1) span:eq(1)").text(s);
		    						var html = "<a onclick='changeCurYear("+val+");'  class='history_link'>否</a>";
		    						$("div.history_unit_sal_row:eq(1) span:eq(2)").html(html);
		    						diaOpen("创建成功，正在更新教师列表，请稍等...");
		    						updateCoeAndUser(val);
		    						
		    					} else {
		    						$("#error_text").text("添加失败。");
		    					}
		    				},
		    				error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert("error");
		    				}
						});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});

}

function updateCoeAndUser(year){
    	$.ajax({
			type : "post",
			url : "coe_copyLastYearCoe.action",
			data : {"msg.years":year},
			dataType : "json",
			success : function(data) {
				console.log(data);
				if(data == "true"){
					diaSuc("操作成功！");
				} else {
					diaFail("操作失败，请让教务员更新系数！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				diaFail("操作失败，请让教务员更新系数！");
			}
		});
}


var isNoError;
//增加行
function removeRow(dom) {
	if ($(".delete").length > 2) {
		$(dom).parent().parent().parent().remove();
	}
}
//删除行
function addRow() {
	if ($(".add").length < 10) {
		$("#copy_table tr:eq(0)").clone().insertAfter(
				".table_msg:eq(0) tr:eq(-2)");//插入到第二个表格的第一行后面
		$(".table_msg:eq(0) tr:eq(-2)").css("display", "table-row");
	} else {
		alert("抱歉，一次性只能添加9条工作量");
	}
}
function getAllAjaxData(years) {
	getAjax("title_getTitleByAjax.action?msg.years="+years,
			"title.id");
	getAjax("post_getTitleByAjax.action?msg.years="+years,
			"post.id");
}
function getAjax(url, selectName) {
	$.ajax({
		type : "post",
		url : url,
		dataType : "json",
		success : function(data) {
			var json = jQuery.parseJSON(data);
			$.each(json, function(idx, obj) {
				if (selectName == "title.id") {
					$("select[name='" + selectName + "']").append(
							"<option  value='"+obj.id+"'>" + obj.titleName
									+ "</option>");
				} else {
					$("select[name='" + selectName + "']").append(
							"<option  value='"+obj.id+"'>" + obj.postName
									+ "</option>");
				}

			});

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}
function subData() {
	if (isNoError && !hasRepeat()) {
		diaOpen();
		$.ajax({
			type : "post",
			url : "user_addUser.action",
			data : $("#add_user_form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data == 0) {
					diaSuc("数据添加成功");
					deleteRowByNum($(".table_msg:eq(0) tr").length - 2);
					addRow();
				} else {
					diaFail("数据添加失败");
					deleteRowByNum(data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	} else {
		$("#error_text").css("display", "inline");
	}
}
function deleteRowByNum(num) {
	for (var i = 0; i < num; i++) {
		$(".table_msg:eq(0) tr:eq(1)").remove();
	}
}
function hasRepeat() {
	var arr = [];
	$("input[name='loginId']").each(function() {
		if ($(this).val() != "") {
			arr.push($(this).val());
		}
	});
	arr.sort(arr);
	for (var i = 1; i < arr.length; i++) {
		if (arr[i] == arr[i - 1]) {
			return true;
		}
	}
	return false;
}
function checkLoginId(dom) {
	var $dom = $(dom);
	if ($dom.val().length >= 4) {
		$.ajax({
			type : "post",
			url : "user_checkLoginId.action",
			data : {
				result : $dom.val()
			},
			dataType : "json",
			success : function(data) {
				if (data == "true") {
					isNoError = false;
					$dom.val("此账号已被注册。");
					$dom.css("color", "#FF0000");
					$dom.on("focus", function() {
						$dom.val("");
						$dom.css("color", "#000000");
						$dom.unbind("focus");
					});
				} else {
					isNoError = true;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	} else {
		isNoError = false;
	}

}