jQuery(function($) {
	$('#tabuleiro').click(function(e) { //Default mouse Position 
        console.log(e.pageX + ' , ' + e.pageY);
        e.preventDefault();
    });
	
	$(".tabLink").fancybox({ padding: 2});
	
	$(".tabLink").click(function() {
		$("#armyNumber").empty();
		$("#armyNumber").removeClass();
		
		var className = $(this).attr('class').replace("tabLink ", "");
		$("#armyNumber").addClass(className);
		
		var armyQuantity = +$("#armyLeft").text().replace("Exercitos Sobrando: ", "");
		
		if (armyQuantity <= 0) {
			$("#redistributeButton").hide();
			$("#armyNumber").hide();
			$("#noArmyMessage").show();
			return;
		} 
		
		$("#redistributeButton").show();
		$("#armyNumber").show();
		$("#noArmyMessage").hide();
		
		for (var i = armyQuantity; i >= 1; i--) {
			$("#armyNumber").append(new Option(i, i));		
		}
		
	});
	
	$('#info').width($('#wrapperBoard').width() - $('#tabuleiro').width());
	$('body').css('background-color', '#000');
	
	$("#play").click(function() {
		$("#distributionForm").submit();
	});
	
	$("#redistributeButton").click(function() {
		var classTextName = $('#armyNumber').attr('class');
		var countryName = $('#armyNumber').attr('class').replace("_link", "");
		var classInputName = "territoryArmy_" + countryName;
		
		var armyChangeNumber = +$("#armyNumber").val();
		
		$('.' + classInputName).val(armyChangeNumber);
		$('.' + classTextName).html(armyChangeNumber);
		$('#territoryArmy').val(armyChangeNumber);
		
		var newTotalArmyLeft = +$('#armyLeft').text().replace("Exercitos Sobrando: ", "") - armyChangeNumber;
		$('#armyLeft').html("Exercitos Sobrando: " + newTotalArmyLeft);
		
		$.fancybox.close();
	});
	
});