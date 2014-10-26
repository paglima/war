jQuery(function($) {
	$('#singleplayer').click(function(){
		location.href="solo";
	});
	
	$('#multiplayer').click(function(){
		location.href="multi-jogador";
	});
	
	$('#play').click(function(){
		$('#playSolo').submit();
	});
	
	$('#colors').css('color', $('#colors option:selected').css('color'));
	
	$('#colors').change(function(){
		$('#colors').css('color', $('#colors option:selected').css('color'));
	});
	
 });