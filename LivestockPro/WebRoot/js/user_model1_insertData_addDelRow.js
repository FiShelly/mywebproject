var v=1;
 function add(target_table,cols){
 	 
 	// alert(target_table);
 	var aa = target_table;
 	var x=document.getElementById(target_table);
 	// var curr_id=x.rows[x.rows.length-1].cells[0].getElementsByTagName("input")[0].id;
 	// alert(curr_id);
	var length = x.rows.length;

 	var tr=x.insertRow(length);
 	switch(target_table){

 		case 'insertData_immuneProgram':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i!==cols-1){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";

		 		}else if(i==cols-1){
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark'></textarea>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_productRecord':
 			// alert(x.cols[0].id);
 			for(var i=0;i<cols;i++)
 			{
		 		var a=tr.insertCell(i);
		 		if(i==1){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 			// alert(x.rows[x.rows.length-1].cells[i].getElementsByTagName("input")[0].id+v.toString());
		 		}
		 		else if(i==cols-1){
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark'></textarea>";
		 		}else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
			 v++;
 			break;
 		case 'insertData_medicineRecord':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i==0||i==1){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}else if(i==cols-1){
		 			
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark'></textarea>";
		 		}else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_degassRecord':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i==0){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 		else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_immuneRecord':
 			for(var i=0;i<cols;i++)
 			{
		 		var a=tr.insertCell(i);
		 		if(i==0){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}else if(i==cols-1){
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark'></textarea>";
		 		}
		 		else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_treatRecord':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i==0){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 		else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
					
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_preventRecord':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i==0){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}else if(i==cols-1){
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark' style="+
		 			"'width:99%;height:95%;'></textarea>";
		 		}
		 		else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 		case 'insertData_handleRecord':
 			for(var i=0;i<cols;i++){
		 		var a=tr.insertCell(i);
		 		if(i==0){
		 			a.innerHTML="<input type='text' id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"'"+
		 			"onclick='laydate()' readonly='readonly' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 		else if(i==cols-1){
		 			
		 			a.innerHTML="<textarea rows='3' cols='30' id='remark' name='remark' style="+
		 			"'width:99%;height:95%;'></textarea>";
		 		}else{
		 			a.innerHTML="<input type='text'"+" id='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+
		 			"' name='"+x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].name+"' onfocus='clearError(\""+
		 				x.rows[x.rows.length-2].cells[i].getElementsByTagName("input")[0].id+v.toString()+"\")'/>";
		 		}
		 	}
		 	var a=tr.insertCell(cols);
 			a.innerHTML="<input type='button' id='delete' name='delete' "+
 			"onclick=\"deleteRRow('"+aa+"',this)\" style='background:url(../image/"+
 				"delete.png) no-repeat;border:0;'/>";
 			break;
 	}
 }

function deleteRRow(target_table,r){

	var i=r.parentNode.parentNode.rowIndex;
  	var number=document.getElementById(target_table).rows.length;
  	
  	if(number!=2){
  		document.getElementById(target_table).deleteRow(i);
  		
 	 }else{}
	  	
}