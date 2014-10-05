$(document).ready(function() {
	$('#numeroJogadores').change(function(){
		var nJogadores = $(this).val();
		$("form[name='cadastraUsuarioForm']").children().remove();
		if(nJogadores === "") 
			return false;
		for ( var i = 0; i < nJogadores; i++) {
			$('<span />', {html:'Jogador'+ (i+1)} ).appendTo("form[name='cadastraUsuarioForm']");
			$('<input />', {type: 'text', name: 'usuarios['+i+'].nomeUsuario'}).appendTo("form[name='cadastraUsuarioForm']");
		}
		
		$('<input />', {type: 'submit'}).appendTo("form[name='cadastraUsuarioForm']");
		$('#divFormCadastroUsuario').show();
	});
 });
