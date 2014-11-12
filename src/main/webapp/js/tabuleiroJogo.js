jQuery(function($) {
	$(".atack").fancybox({ padding: 2});
	
	$("#play").click(function() {
		$("#matchForm").submit();
	}); 
	
	$(".atack").click(function() {
		var countryName = $(this).attr('class').split(" ")[2]; 
		var selectElement = '<select id="atackingTerritory">';
		
		$("#playersAtackers_" + countryName).find('option').each(function() {
			selectElement += '<option value="' + $(this).val() + '">' + $(this).val() + '</option>';
		});
		
		selectElement += '</select>';
		
		$("#atackDiv").html("");
		$("#atackDiv").append('<h2>Atacar</h2><span>Atacar por:</span><br/>' + selectElement + '<div id="atackButton" class="atacked_' + countryName + '"><span>Atacar</span></div>)');
	});
	
	$("#atacked").click(function() {
		var text = "";
		
		$("#movementDiv p").each(function() {
			text = $(this).text();
		});
		
		$("#atackedInfo").text(text);
	});
	
	$("#atackButton").live('click',function() {
		var atackingCountryName = $("#atackingTerritory option:selected").text();
		var atackedCountryName = $(this).attr('class').replace("atacked_", "");
			
		var atackingArmyQuantity = +$("." + atackingCountryName).text();
		var defendingArmyQuantity = +$("." + atackedCountryName).text();
		
		var atackingDicesNumber = processAtackingDices(atackingArmyQuantity);
		var defendingDicesNumber = processDefendingDices(defendingArmyQuantity);
		
		if (atackingDicesNumber == 3 & defendingDicesNumber == 3) {
			atackWithSameDicesNumber(atackingCountryName, atackedCountryName, defendingDicesNumber);
			
		} else if (defendingDicesNumber <= 2 && atackingDicesNumber == 3) {
			atackWithMoreAtackingDices(atackingCountryName, atackedCountryName, atackingDicesNumber, defendingDicesNumber);
			
		} else {
			defendingDicesNumber = atackingDicesNumber;
			atackWithSameDicesNumber(atackingCountryName, atackedCountryName, defendingDicesNumber);
		}
	});
	
	function atackWithMoreAtackingDices(atackingCountryName, atackedCountryName, atackingDicesNumber, defendingDicesNumber) {
		var atackingMove = generateBestScores(atackingDicesNumber, defendingDicesNumber);
		
		for (var i = 0; i < defendingDicesNumber; i++) {
			var defenseDice = Math.floor(Math.random() * (6 - 1 + 1)) + 1;
			
			verifyResult(atackingCountryName, atackedCountryName, atackingMove[i], defenseDice);
		}
	}
	
	function generateBestScores(atackingDicesNumber, defendingDicesNumber) {
		var allDices = [];
		var dicesProcessed = [];
		
		for (var i = 0; i < atackingDicesNumber; i++) {
			allDices.push(Math.floor(Math.random() * (6 - 1 + 1)) + 1);
		}
		
		for (var i = 0; i < defendingDicesNumber; i++) {
			dicesProcessed.push(getBestScore(allDices));
		}
		
		return dicesProcessed;
	}
	
	function getBestScore(allDices) {
		var indexOfGreater = 0;
		
		for (var i = 0; i < allDices.length; i++) {
			if (allDices[i] > allDices[indexOfGreater]) {
				indexOfGreater = i;
			}
		}
		
		var bestScore = allDices[indexOfGreater];
		allDices.splice(indexOfGreater, 1);
		
		return bestScore;
	}
	
	function atackWithSameDicesNumber(atackingCountryName, atackedCountryName, defendingDicesNumber) {
		for (var i = 0; i < defendingDicesNumber; i++) {
			var atackingDice = Math.floor(Math.random() * (6 - 1 + 1)) + 1;
			var defendingDice = Math.floor(Math.random() * (6 - 1 + 1)) + 1;
			
			verifyResult(atackingCountryName, atackedCountryName, atackingDice, defendingDice); 
		}
	}
	
	function verifyResult(atackingCountryName, atackedCountryName, atackingDice, defendingDice) {
		var defendingArmyQuantity = +$("." + atackedCountryName).text();
		var atackingArmyQuantity = +$("." + atackingCountryName).text();
		
		if (atackingDice > defendingDice) {
			$("." + atackedCountryName).text(defendingArmyQuantity - 1);
			
			if ((defendingArmyQuantity - 1) == 0) {
				$("." + atackedCountryName).text(1);
				$("." + atackingCountryName).text(atackingArmyQuantity - 1);
				
				processConquest(atackedCountryName,  atackingCountryName);
			}
			
		} else {
			$("." + atackingCountryName).text(atackingArmyQuantity - 1);
		}		
	}
	
	function processConquest(atackedCountryName, atackingCountryName) {
		$('.conquested_' + atackedCountryName).val("true");
		
		//aumenta o número de territorios do jogador que ganhou territorio
		var winnerColor = $('.' + atackingCountryName).attr('class').split(" ")[2].replace("cor", "");
		var totalTerritoriesOfAtackingPlayer = +$('.userBox_' + winnerColor).find('.territoriesTotal').text().replace("Total de territórios: ", "");
		$('.userBox_' + winnerColor).find('.territoriesTotal').html("Total de territórios: " + (totalTerritoriesOfAtackingPlayer + 1));
		
		//diminui o número de territorios do jogador que perdeu territorio
		var loserColor = $('.' + atackedCountryName).attr('class').split(" ")[3].replace("cor", "");
		var totalTerritoriesOfAtackedPlayer = +$('.userBox_' + loserColor).find('.territoriesTotal').text().replace("Total de territórios: ", "");
		console.log('.userBox_' + loserColor);
		$('.userBox_' + loserColor).find('.territoriesTotal').html("Total de territórios: " + (totalTerritoriesOfAtackedPlayer - 1));
		
		//seta a cor do usuario que ganhou
		$('.' + atackedCountryName).removeClass("cor" + loserColor).off('click');
		$('.' + atackedCountryName).addClass("cor" + winnerColor);
		
		//remove a classe que o torna atacavel
		$('.' + atackedCountryName).removeClass("atack");
	}
	
	function processAtackingDices(atackingArmyQuantity) {
		if (atackingArmyQuantity >= 3) {
			return 3;
		}
		
		if (atackingArmyQuantity == 2) {
			return 1;
		}
		
		return 2;
	}
	
	function processDefendingDices(defendingArmyQuantity) {
		if (defendingArmyQuantity > 3) {
			return 3;
		}
		
		return defendingArmyQuantity;
	}
	
	setTimeout(function() {
		if ($("#playerAtacked").val() == "true") {
			$("#atacked").fancybox({ padding: 2, 
									 'hideOnOverlayClick':false,
									 'hideOnContentClick':false,
									 'showCloseButton': false});
			$("#atacked").trigger('click');
		
		} else if ($("#playerHumanTurn").val() != "true") {
			$("#matchForm").submit();
		}
		
	}, 2200);
	
});