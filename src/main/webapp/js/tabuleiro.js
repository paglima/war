jQuery(function($) {
	$('#tabuleiro').click(function(e) { //Default mouse Position 
        console.log(e.pageX + ' , ' + e.pageY);
        e.preventDefault();
    });
	
	$("#tabLink").fancybox();
	
});