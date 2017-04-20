var v = 1;
function addRow(Id) {
	var aa = Id;
	var x = document.getElementById(Id);
	// alert(x.id);
	var length = x.rows.length;
	var cols = x.rows[length - 1].cells.length - 1;
	var tr = x.insertRow(length);
	for (var i = 0; i < cols; i++) {
		var td = tr.insertCell(i);
		td.innerHTML = "<input type='text' id='"
				+ x.rows[x.rows.length - 2].cells[i]
						.getElementsByTagName("input")[0].id
				+ v.toString()
				+ "' name='"
				+ x.rows[x.rows.length - 2].cells[i]
						.getElementsByTagName("input")[0].name
				+ "' onfocus='clearError(\""
				+ x.rows[x.rows.length - 2].cells[i]
						.getElementsByTagName("input")[0].id
				+ v.toString()
				+ "\") onmouseover='over(\""
				+ x.rows[x.rows.length - 2].cells[i]
						.getElementsByTagName("input")[0].id
				+ v.toString()
				+ "\")' onmouseout='out(\""
				+ x.rows[x.rows.length - 2].cells[i]
						.getElementsByTagName("input")[0].id + v.toString()
				+ "\")'/>";
	}
	var a = tr.insertCell(cols);
	a.setAttribute("style", "border:0;");
	a.innerHTML = "<input type='button' id='delete' name='delete' "
			+ "onclick=\"deleteRRow('" + aa
			+ "',this)\" style='background:url(image/"
			+ "delete.png) no-repeat;border:0;margin-left:10px;'/>";
}

function deleteRRow(target_table, r) {

	var i = r.parentNode.parentNode.rowIndex;
	var number = document.getElementById(target_table).rows.length;

	if (number != 1) {
		document.getElementById(target_table).deleteRow(i);

	} else {
	}

}
