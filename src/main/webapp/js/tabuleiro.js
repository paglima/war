jQuery(function($) {
	$('#tabuleiro').click(function(e) { //Default mouse Position 
        e.preventDefault();
    });
	
	$('#info').width($('#wrapperBoard').width() - $('#tabuleiro').width() - 50);
	$('body').css('background-color', '#000');
	
	$(".tabLink").fancybox({ padding: 2});
	
	$(".tabLink").click(function() {
		$("#noArmyMessage").hide();
		$("#armyNumber").empty();
		$("#armyNumber").removeClass();
		
		var className = $(this).attr('class').split(" ")[2];
		$("#armyNumber").addClass(className + "_link");
		
		var armyQuantity = +$("#armyLeft").text().replace("Exercitos Sobrando: ", "");
		var territoryActualArmy = +$(this).text();
		
		if (armyQuantity <= 0) {
			$("#noArmyMessage").show();
		} 
		
		if (territoryActualArmy > armyQuantity && armyQuantity == 0) {
			for (var i = territoryActualArmy; i >= 1; i--) {
				$("#armyNumber").append(new Option(i, i));		
			}
			return;
		} 
		
		for (var i = (armyQuantity + territoryActualArmy); i >= 1; i--) {
			$("#armyNumber").append(new Option(i, i));		
		}	
	});
	
	$("#play").click(function() {
		var actualArmyLeft = +$('#armyLeft').text().replace("Exercitos Sobrando: ", "");
		
		if (actualArmyLeft != 0) {
			$("#keepDistribuition").fancybox({ padding: 2});
			$("#keepDistribuition").trigger('click');
			return;
		}
		
		$("#distributionForm").submit();
	}); 
	
	$("#okButton").click(function() {
		$.fancybox.close();
	}); 
	
	$("#redistributeButton").click(function() {
		var classTextName = $('#armyNumber').attr('class').replace("_link", "");
		var classInputName = "territoryArmy_" + classTextName;
		
		var armyChangeNumber = +$("#armyNumber").val();
		var actualTerrytoryArmy = +$('.' + classInputName).val();
		var actualArmyLeft = +$('#armyLeft').text().replace("Exercitos Sobrando: ", "");
		
		$('.' + classInputName).val(armyChangeNumber);
		$('.' + classTextName).html(armyChangeNumber);
		
		if (armyChangeNumber < actualTerrytoryArmy) {
			var newTotalArmyLeft = actualArmyLeft + (actualTerrytoryArmy - armyChangeNumber);
			$('#armyLeft').html("Exercitos Sobrando: " + newTotalArmyLeft);
			
			$.fancybox.close();
			return;
		} 
		
		if ((armyChangeNumber > actualTerrytoryArmy) && ((armyChangeNumber - actualTerrytoryArmy) >= actualArmyLeft) && (actualArmyLeft > 0)) {
			var newTotalArmyLeft = actualArmyLeft - (armyChangeNumber - actualTerrytoryArmy);
			$('#armyLeft').html("Exercitos Sobrando: " + newTotalArmyLeft);
			
			$.fancybox.close();
			return;
		}
		
		if (actualArmyLeft == 0 && armyChangeNumber == actualTerrytoryArmy) {
			$.fancybox.close();
			return;
		}
		
		var newTotalArmyLeft = actualArmyLeft - armyChangeNumber;
		$('#armyLeft').html("Exercitos Sobrando: " + (newTotalArmyLeft + 1));
		
		$.fancybox.close();
	});
	
});