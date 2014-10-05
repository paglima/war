$(document).ready(function() {
	$("#duplica").click(function(){
		$(".fotosbox1").append($("input[name=fotos]:eq(0)").clone().val(""));
	});
	
	$("#ajax").click(function(){
		$.ajax({
			type: "GET",
			url: "/crud-catalogo/veiculos/ajax",
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			data: {teste:"fodase"},
			async: false,
			success: function( expectedMargin ) {
				
				alert(expectedMargin).text();
				
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert("PEEEEEEEEEIN");
				return false;
			}
		});
	});
 });
