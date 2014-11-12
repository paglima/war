jQuery(function($) {
	window.location.hash="no-back-button";
	window.location.hash="Again-No-back-button";
	window.onhashchange= function() {
		window.location.hash="no-back-button";
	};
	
	function disableF5(e) { 
		if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) {
			e.preventDefault(); 
		}
	};

	$(document).ready(function(){
	     $(document).on("keydown", disableF5);
	});
	
	$('#info').width($('#wrapperBoard').width() - $('#tabuleiro').width() - 50);
	$('body').css('background-color', '#1e2d4d');
	
	$("#back").click(function() {
		if (confirm("Você perderá a partida, deseja mesmo desistir do jogo?")) {
			location.href="../menu/inicio";
		}
	});
	
});