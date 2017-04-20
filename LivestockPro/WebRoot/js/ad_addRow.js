 function addRow(Id ){
 	var tbl=document.getElementById(Id).insertRow(1);

	  var td1=tbl.insertCell(0);
	  var td2=tbl.insertCell(1);
	  var td3=tbl.insertCell(2);
	  td1.innerHTML="<input type='text' id='major' name='major' style='width:100%;border:0;'/>";
	  td2.innerHTML="<input type='text' id='amount' name='amount' style='width:100%;border:0;'/>";
	  td3.innerHTML="<input type='button' id='delete' name='delete' onclick='deleteRow(this)' style='width:20px;background:url(../image/delete.png) no-repeat;color:white;margin-left:-5px'/>";
 	
 	
 }

 function deleteRow(r)
  {
  var i=r.parentNode.parentNode.rowIndex;
  var number=document.getElementById("inner_table").rows.length;
  if(number!=2){
  	document.getElementById('inner_table').deleteRow(i);
  }else{}
  }