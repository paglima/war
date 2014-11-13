<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Jogo Solo</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <script src="../js/jQuery/jquery-1.8.0.min.js" type="text/javascript"></script>
     	<script src="../js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
	    <script src="../js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
	   	<link rel="stylesheet" type="text/css" href="../js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
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
		        		<input type="text" maxlength="20" name="nome" autocomplete="off"/>
					</div>
					
					<div class="otherData" > 
						<label for="colors">Selecione uma cor para o seu exército</label> 
						<select id="colors" name="nomeCor">
							<c:forEach items="${cores}" var="cor">
								<c:choose>
									<c:when test="${cor.nomeCor eq 'Vermelho'}">
										<option class="${cor.nomeCor}" value="${cor.nomeCor}" selected>${cor.nomeCor}</option>
									</c:when>
									<c:otherwise>
										<option class="${cor.nomeCor}" value="${cor.nomeCor}">${cor.nomeCor}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach> 
						</select>
					</div>
				
					<div class="otherData" > 
						<label for="numberOfPlayers">Entre com o número de inimigos</label> 
						<select name="quantidadeInimigos">
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>
				
		        	<div id="play">
		        		<span id="playButton">Jogar</span>
					</div>
					
		        </form>
				
			</div>
		</div>
		
		<div style="display:none;">
			<div id="gameStartedLightBox" style="width:330px;height:240px;overflow:auto;">
				<h2>Atenção</h2>
				<span>Você já começou uma partida. Deseja começar uma nova?</span>
				<br/>
				<div id="okButton">
					<span>Ok</span>
				</div>
				<div id="cancelButton">
					<span>Cancelar</span>
				</div>
			</div>
		</div>
		
		<a style="display:none;" id="newGame" href="#gameStartedLightBox">confirm</a>
		<input type="hidden" id="gameStarted" value="${jogoComecado}">
    </body>	
</html>