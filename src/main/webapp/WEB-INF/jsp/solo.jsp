<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Jogo Solo</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <script src="../js/jQuery/jquery-1.8.0.min.js" type="text/javascript"></script>
    	<script src="../js/menu.js" type="text/javascript"></script>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    <body>
		<div id="wrapper">
			<div id="options"> 
				<span id="sub-title">Jogo Solo</span>
				
				<form id="playSolo" action="jogarSolo" method="post">
					
					<div class="firstData">
						<span>Digite o seu nome:</span>
		        		<input type="text" name="nome"/>
					</div>
					
					<div class="otherData" > 
						<label for="colors">Selecione uma cor para o seu exército</label> 
						<select name="codigoCor">
							<c:forEach items="${cores}" var="cor">
								<c:choose>
									<c:when test="${cor.nomeCor eq 'Vermelho'}">
										<option style="color:${cor.codigoCor}" value="cor.codigoCor" selected>${cor.nomeCor}</option>
									</c:when>
									<c:otherwise>
										<option style="color:${cor.codigoCor}" value="cor.codigoCor">${cor.nomeCor}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach> 
						</select>
					</div>
				
					<div class="otherData" > 
						<label for="numberOfPlayers">Entre com o número de jogadores</label> 
						<select name="quantidadeInimigos">
							<option value="1" selected>1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select>
					</div>
				
		        	<div id="play">
		        		<button id="playButton" type="submit">Jogar</button>
					</div>
					
		        </form>
				
			</div>
		</div>
    </body>	
</html>