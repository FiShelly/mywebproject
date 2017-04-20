<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http:/LivestockSys/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_update_submit.js"></script>
<script type="text/javascript">
</script>
<style>
	table{
		border-collapse: collapse;
		table-layout: fixed;
		width: 532px;
		height: 41px;
		margin-top: 30px;
	}
	table tr td{
		border:1px solid #aaaaaa;
		text-align: center;
		font-family: 宋体;
		font-size: 12px;
		color:black;
		opacity: 0.85;

	}
	table tr td input{
		width: 99%;
		height: 98%;
		border:0;
	}
</style>
</head>
<body>
<div style="text-align:center;widht:552px;height:61px;">
	<form action="ImmuneProServlet" method="post" id="immuneContent" name="immuneContent" onsubmit="return isSubmit('immuneContent')">
		<table id="immuneContent">
			<tr>
							
				<td>
					日龄
				</td>
				<td>
					疫苗名称
				</td>
				<td>
					剂量
				</td>
				<td>
					免疫方法
				</td>
				<td>
					备注
				</td>
				
				</tr>
			    <tr>
					
					<td>
						<input type="text" id="immuneDate" name="immuneDate" value="${pro.dateAge}"  onfocus="clearError('immuneDate')"/>
					</td>
					<td>
						<input type="text" id="vaccineType" name="series" value="${pro.vaccine }" onfocus="clearError('vaccineType')"/>
					</td>
					<td>
						<input type="text" id="injectLevel" name="dose" value="${pro.dose }" onfocus="clearError('injectLevel')"/>
					</td>
					<td>
						<input type="text" id="injectPart" name="part" value="${pro.way }" onfocus="clearError('injectPart')"/>
					</td>
					<td>
						<input type="text" id="remark" name="remark" value="${pro.note} "/>
					</td>
						
					</tr>
					<tr>
						<td colspan=5 style="text-align:right;border:1px white solid;">
							<input type="submit"  value="保存"  id="save" name="save" style="width:46px;height:20px;border-radius:2px;color:white;background-color:#12A686;border:0;margin-top:10px;"/>
						</td>
					</tr>

		 </table>
		<input type="hidden" id="farmId" name="farmId" value="${pro.farm.farmId }"/>
		<input type="hidden" id="immuneSpecies" name="immuneSpecies" value="${pro.name }"/>
		<input type="hidden" id="sequeueNum" name="sequeueNum" value="${pro.sequeueNum }"/>
		<input type="hidden" id="id" name="id" value="${pro.id }"/>
		<input type="hidden" id="action" name="action" value="update"/>
	</form>
</div>
</body>
</html>