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
	
 });