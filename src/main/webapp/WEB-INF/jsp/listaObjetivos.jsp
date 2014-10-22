<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	 <head>
        <title>Lista Objetivos</title>
      	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js" type="text/javascript"></script>
    	<script src="../js/listaObjetivos.js" type="text/javascript"></script>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
	<body>
		<c:forEach items="${jogadores}" var="jogador">
			<div>
				<span>${jogador.nomeUsuario} </span> 
				<span style="display: none" class="objetivo"> ${jogador.objetivo.descricao} </span>
				<input style="display: inline-block;" type="button" class="objetivoButton" value="Mostrar"/>
			</div>
		</c:forEach>

		<form:form id="tabuleiro" method="post" action="../jogo/">
			<input type="submit"/>
		</form:form>		
	</body>
</html>