jQuery(function($) {
	$('#tabuleiro').click(function(e) { //Default mouse Position 
        e.preventDefault();
    });
	
	$(".tabLink").fancybox({ padding: 2});
	
	$(".tabLink").click(function() {
	});
	
	$('#info').width($('#wrapperBoard').width() - $('#tabuleiro').width() - 50);
	$('body').css('background-color', '#1e2d4d');
	
});