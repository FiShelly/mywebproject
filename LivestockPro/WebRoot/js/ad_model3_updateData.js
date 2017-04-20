$(function(){
	$( "#update_animalA" ).dialog({
		autoOpen: false,
		width: "500",
		height:"550",
		modal: true,
		buttons: [
		{
			type:"submit",
			text: "确定",
			click: function() {
				$result = $("#iframe_animalAUpdate")[0].contentWindow.checkContent('animalA');
				if($result){
					$(this).dialog("close");
               	 	$("#iframe_animalAUpdate")[0].contentWindow.document.getElementById("form_animalA").submit();
				}
			}
		},
		{
			text: "取消",
			click: function() {
				$( this ).dialog( "close" );

				// window.parent.location.reload();
			}
		}
		]
	});
	//animalB

	$( "#update_animalB" ).dialog({

		autoOpen: false,

		width: "500",
		height:"550",
		modal: true,
		buttons: [
		{
			type:"submit",
			text: "确定",
			click: function() {
				$result = $("#iframe_animalBUpdate")[0].contentWindow.checkContent('animalB');
				if($result){
					$( this ).dialog( "close" );
					$("#iframe_animalBUpdate")[0].contentWindow.document.getElementById("form_animalB").submit();
				}
			}
		},
		{
			text: "取消",
			click: function() {
				$( this ).dialog( "close" );
			}
		}
		]
	});
//productA

$( "#update_productA" ).dialog({

	autoOpen: false,

	width: "500",
	height:"560",
	modal: true,
	buttons: [
	{
		type:"submit",
		text: "确定",
		click: function() {
			$result = $("#iframe_productAUpdate")[0].contentWindow.checkContent('productA');
			if($result){
				$( this ).dialog( "close" );
				$("#iframe_productAUpdate")[0].contentWindow.document.getElementById("form_productA").submit();
			}
		}
	},
	{
		text: "取消",
		click: function() {
			$( this ).dialog( "close" );
		}
	}
	]
});
//productB
$( "#update_productB" ).dialog({

	autoOpen: false,

	width: "500",
	height:"550",
	modal: true,
	buttons: [
	{
		text: "确定",
		click: function() {
			$result = $("#iframe_productBUpdate")[0].contentWindow.checkContent('productB');
			if($result){
				$( this ).dialog( "close" );
				$("#iframe_productBUpdate")[0].contentWindow.document.getElementById("form_productB").submit();
			}
		}
	},
	{
		text: "取消",
		click: function() {
			$( this ).dialog( "close" );
		}
	}
	]
});
});
