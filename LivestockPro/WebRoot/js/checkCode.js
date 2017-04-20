//生成验证码的js方法
var code ; //在全局 定义验证码
function createCode(){ 
	code = new Array();
	var codeLength = 4;//验证码的长度
	var checkCode = document.getElementById("checkCode");
	checkCode.value = "";

	var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

	for(var i=0;i<codeLength;i++) {
	   var charIndex = Math.floor(Math.random()*36);
	   code +=selectChar[charIndex];
	}
	if(code.length != codeLength){
	   createCode();
	}
	checkCode.value = code;
}

function validate() {
	var inputCode = document.getElementById("lg_checkCode").value.toUpperCase();

	if(inputCode != code ){
		document.getElementById("error").value="验证码输入错误";
		document.getElementById("error").style.color="#ef9783";
		document.getElementById("error").style.size="12px";
	    createCode();
   		return false;
	}else return true;
}