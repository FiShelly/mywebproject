function addSal(fid,id){
	diaOpen();
        	$.ajax({
        		type : "post",
        		url : "coe_addSal.action",
        		data :$("#"+fid).serialize(),
        		dataType : "json",
        		success : function(data) {
        			if(data == "true"){
        				var us = $("#unitSal").val();
        				$("#"+id).text(us+".0");
        				diaSucSecond("修改单位课酬成功.！");
        			} else {
        				diaFailSecond("修改单位课酬失败！");
        			}
        		},
        		error : function(XMLHttpRequest, textStatus, errorThrown) {
        			diaFailSecond("修改单位课酬失败！");
        		}
        	});	
        	return false;
}
function addClassInCoe(dom){
	var isRight = true;
	$("input[type=number]").each(function(){
		if(isNaN($(this).val()) || $(this).val()<0 || $(this).val()== "" || $(this).val() == null){
			$("#alerm_text").text("系数必须为数字且大于0.");
			isRight = false;
			return;
		}
	});
	if(!isRight){
		return false;
	}
	diaOpen();
	$.ajax({
		type : "post",
		url : "coe_updateClassInCoe.action",
		data : $(dom).serialize(),
		dataType : "json",
		success : function(data) {
			if (data == "true") {
				$("#add_base_coe",window.parent.document).slideUp(200);
				$("#op_iframe_adjustment",window.parent.document).attr("src","coe_akfCoe.action");
				diaSuc("数据添加成功");
			} else {
				$("#alerm_text").text("添加失败，数据格式有误，请确认后重新添加。");
				diaFailSecond("数据添加失败。");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("数据添加失败。");
		}
	});
	return false;	
}

function addOtherCoe(dom){
	var isRight = true;
	$("input[type=number]").each(function(){
		if(isNaN($(this).val()) || $(this).val()<0 || $(this).val()== "" || $(this).val() == null){
			$("#alerm_text").text("系数必须为数字且大于0.");
			isRight = false;
			return;
		}
	});
	$("input[type=text]").each(function(){
		if( $(this).val()== "" || $(this).val() == null){
			$("#alerm_text").text("输入的内容不能为空.");
			isRight = false;
			return;
		}
	});
	if(!isRight){
		return false;
	}
	diaOpen();
	$.ajax({
		type : "post",
		url : "coe_"+$("#action").val()+".action",
		data : $(dom).serialize(),
		dataType : "json",
		success : function(data) {
			if (data == 0) {
				$("#add_base_coe",window.parent.document).slideUp(200);
				$("#op_iframe_adjustment",window.parent.document).attr("src","coe_akfCoe.action");
				diaSuc("数据添加成功");
			} else {
				$("#alerm_text").text("添加失败，数据格式有误，请确认后重新添加。");
				diaFailSecond("数据添加失败。");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("数据添加失败。");
		}
	});
	return false;	
}

function addBaseCoe(dom){
	var isRight = true;
	$("input[type=number]", document.forms[0]).each(function(){
		if(isNaN($(this).val()) || $(this).val()<0){
			$("#error_text").text("输入的内容必须为数字，且不能为空.");
			isRight = false;
			return;
		}
	});
	if(!isRight){
		return false;
	}
	diaOpen();
	$.ajax({
		type : "post",
		url : "coe_addBaseCoe.action",
		data : $(dom).serialize(),
		dataType : "json",
		success : function(data) {
			if (data == 0) {
				$("#add_base_coe",window.parent.document).slideUp(200);
				$("#op_iframe_adjustment",window.parent.document).attr("src","coe_akfCoe.action");
				diaSuc("数据添加成功");
			} else {
				$("#error_text").text("添加失败，数据格式有误，请确认后重新添加。");
				diaFailSecond("数据添加失败。");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("数据添加失败。");
		}
	});
	return false;
}

function updatePwByAjax(){
	diaOpen();
	$.ajax({
		type : "post",
		url : "user_updatePw.action",
		data :$("#update_pw").serialize(),
		dataType : "json",
		success : function(data) {
			if(data == "true"){
				diaSuc("修改用户密码成功！");
				$("#update_pw_dialog").slideUp(200);
				$("#password").val("");
				$("#repassword").val("");
			} else {
				diaFailSecond("修改用户密码失败！");
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			diaFailSecond("修改用户密码失败！");
		}
	});	
	return false;
}
function updateCoe(fid,action){
	if(window.confirm("确认修改此系数记录？")){
		diaOpen("正在修改");
		$.ajax({
			type : "post",
			url : "coe_"+action+".action",
			data :$("#"+fid).serialize(),
			dataType : "json",
			success : function(data) {
				if(data == "true"){
					diaSuc("修改成功！");
				} else {
					diaFail("数据有误,修改失败！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				diaFail("数据有误,修改失败！");
			}
		});	
	}
}
function deleteCoe(id,fid,action){
	console.log(fid);
	if(window.confirm("确认删除此系数记录？")){
		diaOpen("正在删除...");
		$.ajax({
			type : "post",
			url : "coe_"+action+".action",
			data :{id:id},
			dataType : "json",
			success : function(data) {
				if(data == "true"){
					$("#"+fid).remove();
					diaSuc("删除成功！");
				} else {
					diaFail("数据有误，删除失败！");
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				diaFail("数据有误，删除失败！");
			}
		});	
	}
}
//教务员增加工作量

