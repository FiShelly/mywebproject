<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style_admin_insert.css" />
<link rel="stylesheet" href="../css/style_imp_font.css" />
<title></title>

</head>
<body onload="getAllAjaxData('${cyearmsg.years}')">
	<h1>教师账号创建</h1>
	<span id="error_text" class="error_text">填写的信息有误，请确认后再次提交。</span>
	<table id="copy_table">
		<tr style="display: none">
			<td><input type="text" name="loginId" required pattern="\w{4,}"
				oninput="setCustomValidity('');"
				oninvalid="setCustomValidity('至少输入4位字符')" value=""
				onblur="checkLoginId(this);"></td>
			<td><input type="text" required="required" name="userName"
				oninput="setCustomValidity('');"
				oninvalid="setCustomValidity('姓名不能为空')"></td>
			<td><input type="text" readonly onclick="laydate({ start: '1970-01-01'})"
				name="birthDate"></td>
			<td><select name="title.id">
			</select></td>
			<td><select name="post.id">
			</select></td>
			<td><select name="state">
					<option value="false">停用</option>
					<option value="true">激活</option>
			</select></td>
			<td>
				<div class="same add">
					<a onclick="addRow();"><i class="iconfont">&#xe64d;</i></a>
				</div>
				<div class="same delete">
					<a onclick="removeRow(this);"><i class="iconfont">&#xe625;</i></a>
				</div> <input type="hidden" name="role" value="1"> <input
				type="hidden" name="pw" value="123456">
			</td>
		</tr>
	</table>
	<form id="add_user_form" onsubmit="return false;">
		<table class="table_msg fixTabWidth">
			<tr>
				<td>账号</td>
				<td>姓名</td>
				<td>出生日期</td>
				<td>职称</td>
				<td>职务</td>
				<td>状态</td>
				<td>操作</td>
			</tr>
			<tr>
				<td><input type="text" name="loginId" value=""
					onblur="checkLoginId(this);" required pattern="\w{4,}"
					oninput="setCustomValidity('');"
					oninvalid="setCustomValidity('至少输入4位字符')"></td>
				<td><input type="text" required="required" name="userName"
					oninput="setCustomValidity('');"
					oninvalid="setCustomValidity('姓名不能为空')"></td>
				<td><input type="text" readonly onclick="laydate({ start: '1970-01-01'})"
					name="birthDate"></td>
				<td><select name="title.id">
				</select></td>
				<td><select name="post.id">

				</select></td>
				<td><select name="state">
						<option value="false">停用</option>
						<option value="true">激活</option>
				</select></td>
				<td>
					<div class="same add">
						<a onclick="addRow();"><i class="iconfont">&#xe64d;</i></a>
					</div>
					<div class="same delete">
						<a onclick="removeRow(this);"><i class="iconfont">&#xe625;</i></a>
					</div> <input type="hidden" name="role" value="1"> <input
					type="hidden" name="pw" value="123456">
				</td>
			</tr>
			<tr>
				<td colspan="7"><input class="btn_group add"
					onclick="subData();" type="submit" value="提交" /> <input
					class="btn_group delete" type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
<script src="../js/laydate/laydate.js"></script>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/public_view_control.js"></script>
<script src="../js/admin_ajax.js"></script>
</html>