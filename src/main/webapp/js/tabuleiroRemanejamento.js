jQuery(function($) {
	$(".tabLink").fancybox({ padding: 2});
	
	var playerHumanTurn = $("#playerHumanTurn").val();
	if (playerHumanTurn == "true") {
		$("#play").css('display', 'block');
	}
	
	$(".tabLink").click(function() {
		var armyQuantity = +$(this).text();
		var countryName = $(this).attr('class').split(" ")[2]; 
		var selectElement = '<select id="borderAlly">';
		var selectArmy = '<select id="armyNumber">';
		
		if (armyQuantity <= 1) {
			$("#redistributionDiv").html("");
			$("#redistributionDiv").html("<h2>Remanejar exército</h2><span>Você não tem exércitos suficientes neste território para remanejar.</span>");
			return;
		}
		
		//monta lista de aliados
		$("#borderAlly_" + countryName).find('option').each(function() {
			selectElement += '<option value="' + $(this).val() + '">' + $(this).val() + '</option>';
		});
		selectElement += '</select>';
		
		//monta lista de exercito remanejavel
		for (var i = armyQuantity - 1; i >= 1; i--) {
			selectArmy += '<option value="' + i + '">' + i + '</option>';
		}
		selectArmy += '</select>';
		
		$("#redistributionDiv").html("");
		$("#redistributionDiv").append('<h2>Remanejar exército</h2><span class="redistribuitionMessage">Mover exércitos de ' + countryName + ' para:</span><br/><br/>' + selectArmy + '<br/><br/>' + selectElement + '<div id="redistributeButton" class="' + 'button_' + countryName + '"><span>Remanejar</span></div>)');
	});
	
	$("#play").click(function() {
		$("#distributionForm").submit();
	}); 
	
	$("#okButton").click(function() {
		$.fancybox.close();
	}); 
	
	$("#redistributeButton").live('click', function() {	
		var countryFrom = $(".redistribuitionMessage").text().split(" ")[3];
		var countryFor = $("#borderAlly").val();

		var armyQuantity = +$("#armyNumber").val();
		var formerArmyCountryFrom = +$(".territoryArmy_" + countryFrom).val();
		var formerArmyCountryFor = +$(".territoryArmy_" + countryFor).val();
		
		$("." + countryFrom).text(formerArmyCountryFrom - armyQuantity);
		$("." + countryFor).text(formerArmyCountryFor + armyQuantity);
		$(".territoryArmy_" + countryFrom).val(formerArmyCountryFrom - armyQuantity);
		$(".territoryArmy_" + countryFor).val(formerArmyCountryFor + armyQuantity);
		
		$("." + countryFrom).text()
		$.fancybox.close();
	});
	
	setTimeout(function() {
		if ($("#playerHumanTurn").val() != "true") {
			$("#distributionForm").submit();
		}
		
	}, 2200);
	
});