// JavaScript Document
//验证输入
function isEmpty(s)
{
	var whitespace = "\t\n\r";
	var i;
	if(s == null || s.length == 0||s=="输入账号"||s=="输入密码"||s=="输入验证码") return true;
	for(i = 0;i < s.length;i++)
	{
		var c = s.charAt(i);
		if(whitespace.indexOf(c) == -1) return false;
	}
	return true;
}
//在输入框中显示提示信息
function checkLogin(){
	var id=document.login.lg_id.value;
	var password=document.login.lg_password.value;
	var checkCode=document.login.lg_checkCode.value;
	if(isEmpty(id)){
		document.getElementById("error").value="登录账号不能为空";
		document.getElementById("error").style.color="#ef9783";
		document.getElementById("error").style.size="10px";
		document.getElementById("lg_id").focus();
		return false;
	}
	if(isEmpty(password)){
		document.getElementById("error").value="登录密码不能为空";
		document.getElementById("error").style.color="#ef9783";
		document.getElementById("error").style.size="10px";
		document.getElementById("lg_password").focus();
		return false;
	}
	if(isEmpty(checkCode)){
		document.getElementById("error").value="验证码不能为空";
		document.getElementById("error").style.color="#ef9783";
		document.getElementById("error").style.size="10px";
		document.getElementById("lg_checkCode").focus();
		return false;
	}
	if(!validate()) {
		return false;
	} 
	//
}

