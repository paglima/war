<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Cadastro Jogadores</title>
       <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <script src="../js/jQuery/jquery-1.11.1.min.js" type="text/javascript"></script>
    	<script src="../js/cadastroUsuario.js" type="text/javascript"></script>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    
	<h1>Cadastro Jogadores </h1>
		<div id="inputNumeroJogadores" > 
			<label for="numeroJogadores">Entre com o número de jogadores</label> 
			<select id="numeroJogadores">
			  <option value="" selected>0</option>
			  <option value="1">1</option>
			  <option value="2">2</option>
			  <option value="3">3</option>
			  <option value="4">4</option>
			  <option value="5">5</option>
			  <option value="6">6</option>
			</select>
		
		</div>
		<div id="divFormCadastroUsuario" style="display:none">
	        <form:form action="cadastraUsuario" method="post" modelAttribute="usuarioForm" name="cadastraUsuarioForm">
	        </form:form>
		</div>
    </body>
    		
</html>