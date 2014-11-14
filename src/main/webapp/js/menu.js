jQuery(function($) {
	$('#singleplayer').click(function() {
		location.href="solo";
	});
	
	$('#multiplayer').click(function() {
		location.href="../files/manualWar.pdf";
	});
	
	$('#play').click(function() {
		var gameStarted = $("#gameStarted").val();
		console.log(gameStarted);
		
		if (gameStarted != "true") {
			$('#playSolo').submit();
			return;
		}
		
		$("#newGame").fancybox({ padding: 2});
		$("#newGame").trigger('click');
	});
	
	$('#cancelButton').click(function() {
		$.fancybox.close();
	});
	
	$('#okButton').click(function() {
		$('#playSolo').submit();
	});
	
	
	$('#colors').removeClass().addClass($('#colors').find("option:selected").attr("class")); 
		
	$('#colors').change(function() {
		$(this).removeClass().addClass($(this).find("option:selected").attr("class")); 
	});
	
 });