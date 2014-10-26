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
	
	$('#colors').removeClass().addClass($('#colors').find("option:selected").attr("class")); 
		
	$('#colors').change(function(){
		$(this).removeClass().addClass($(this).find("option:selected").attr("class")); 
	});
	
 });