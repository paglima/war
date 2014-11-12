jQuery(function($) {
	$(".tabLink").fancybox({ padding: 2});
	
	var playerHumanTurn = $("#playerHumanTurn").val();
	var turn = +$("#turn").text();
	
	if (playerHumanTurn == "true" || turn == 1) {
		$("#play").css('display', 'block');
	}
	
	$(".tabLink").click(function() {
		$("#noArmyMessage").hide();
		$("#armyNumber").empty();
		$("#armyNumber").removeClass();
		
		var className = $(this).attr('class').split(" ")[2];
		$("#armyNumber").addClass(className + "_link");
		
		var armyQuantity = +$("#armyLeft").text().replace("Exercitos Sobrando: ", "");
		var territoryActualArmy = +$(this).text();
		var formerArmyQuantity = +$('#formerArmyQuantity').text();
		
		if (armyQuantity <= 0) {
			$("#noArmyMessage").show();
		} 
		
		if (territoryActualArmy > armyQuantity && armyQuantity == 0) {
			for (var i = territoryActualArmy; i >= formerArmyQuantity; i--) {
				$("#armyNumber").append(new Option(i, i));		
			}
			return;
		} 
		
		for (var i = (armyQuantity + territoryActualArmy); i >= formerArmyQuantity; i--) {
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
	
	setTimeout(function() {
		if ($("#playerHumanTurn").val() != "true" && turn != 1) {
			$("#distributionForm").submit();
		}
		
	}, 2200);
	
});