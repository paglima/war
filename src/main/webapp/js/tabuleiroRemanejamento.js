jQuery(function($) {
	$('#info').width($('#wrapperBoard').width() - $('#tabuleiro').width() - 50);
	$('body').css('background-color', '#1e2d4d');
	
	$(".tabLink").fancybox({ padding: 2});
	
	$("#back").click(function() {
		if (confirm("Você perderá a partida, deseja mesmo desistir do jogo?")) {
			location.href="../menu/inicio";
		}
	});
	
	var playerHumanTurn = $("#playerHumanTurn").val();
	if (playerHumanTurn == "true") {
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
		if ($("#playerHumanTurn").val() != "true") {
			$("#distributionForm").submit();
		}
		
	}, 2200);
	
});