function updateReadState(id,state){
	console.log(state);
	$.ajax({
   		type : "post",
   		url : "msg_updateReadState.action",
   		data :{
   			"msg.fromId":id,
   			"result":state,
   			"msg.id":id,
   		},
   		dataType : "json",
   		success : function(data) {
   			if(data == "true"){
   				$("#td_"+id).text("已读");
   				$("#a_"+id).hide();
   			} else {
   				diaFailSecond("操作失败，请稍后重试！");
   			}
   		},
   		error : function(XMLHttpRequest, textStatus, errorThrown) {
   			diaFailSecond("请求失败！");
   			console.log(XMLHttpRequest.status);
   			console.log(console.log(XMLHttpRequest.status));
   			console.log(XMLHttpRequest.status);
   		}
   	});	
}
function deleteByAjax(id,flag){
	var text = "";
	if(flag){
		text = "修改申请";
	} else {
		text = "消息记录";
	}
	if(window.confirm("确定删除该"+text+"?")){
	$.ajax({
   		type : "post",
   		url : "msg_delete.action",
   		data :{
   			"msg.id":id,
   		},
   		dataType : "json",
   		success : function(data) {
   			if(data == "true"){
   				diaSucSecond("消息删除成功"+text+"！");
				$("#tr_"+id).remove();
   			} else {
   				diaFailSecond("消息删除失败"+text+"！");
   			}

   		},
   		error : function(XMLHttpRequest, textStatus, errorThrown) {
   			diaFailSecond("请求失败！");
       			console.log(XMLHttpRequest.status);
       			console.log(console.log(XMLHttpRequest.status));
       			console.log(XMLHttpRequest.status);
       		}
       	});
	}
}	
function passUpdateApply(state,id,toId,toName,name,flag){
   	var text = "";
   	if(flag){
   		text = "确定同意"+name+"老师的修改申请？";
   	} else {
   		text = "确定拒绝"+name+"老师的修改申请？";
   	}
   	if(window.confirm(text)){
   		$.ajax({
       		type : "post",
       		url : "msg_updatePassState.action",
       		data :{
       			"msg.passState":state,
       			"msg.id":id,
       			"msg.toId":toId,
       			"msg.toName":toName,
       		},
       		dataType : "json",
       		success : function(data) {
       			if(data == "true"){
       				if(flag){
       					diaSucSecond("该修改申请已被同意！");
       					$("#td_"+id).text("同意");
       				} else {
       					diaSucSecond("该修改申请已被拒绝！");
       					$("#tr_"+id).text("拒绝");
       				}
       				$("#d_"+id).show();
   					$("#div_"+id).hide();
       			} else {
       				diaFailSecond("修改申请审核失败，请重试！");
       			}

       		},
       		error : function(XMLHttpRequest, textStatus, errorThrown) {
       			diaFailSecond("请求失败！");
       			console.log(XMLHttpRequest.status);
       			console.log(console.log(XMLHttpRequest.status));
       			console.log(XMLHttpRequest.status);
       		}
       	});	
   	}
	}