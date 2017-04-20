 $(document).ready(function(){
        $("#sub_btn").click(function(){
            var $v = $("#role").val();
            if($v == 0){
                $("#error_text").css("display","inline");
                $("#error_text").text("请选择登录角色。");
                return false;
            } else if($v == 3){
                $("#login_form").attr("action","admin_login.action");
                if($("#rember_pw").prop("checked")){
                	savePw();
                }
            } else {
                $("#login_form").attr("action","loginout_login.action");
                if($("#rember_pw").prop("checked")){
                	savePw();
                }
            }
        });
        $("#forgetPw").click(function(){
        	alert("请与管理员联系，让管理员帮助您重置密码。");
        });
        $("#loginId").blur(function(){
        	var loginId = $("#loginId").val();
        	var val = localStorage.getItem(loginId);
        	if(val != null){
        		$("#password").val(val);
        	}
        });
        var lastLoginId = localStorage.getItem("lastLogin");
        if(lastLoginId!=null){
        	var val = localStorage.getItem(lastLoginId);
        	$("#loginId").val(lastLoginId);
        	$("#password").val(val);
        }
        $("#password").change(function(){
        	console.log($(this).val());
        });
});
function setTipText(flag,info){
	if(flag == "false"){
		$("#error_text").css("display","inline");
        $("#error_text").text(info);
	} 
}
function savePw(){
	 var loginId = $("#loginId").val();
     var pw = $("#password").val();
     localStorage.setItem(loginId,pw);
     localStorage.setItem("lastLogin",loginId);
}