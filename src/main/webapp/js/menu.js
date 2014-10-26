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
	
	$('#colors').removeClass().addClass(jQuery(this).find("option:selected").attr("class")); 
		
	$('#colors').change(function(){
		$(this).removeClass().addClass(jQuery(this).find("option:selected").attr("class")); 
	});
	
 });