<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="hidden"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <script src="../js/jQuery/jquery-1.8.0.min.js" type="text/javascript"></script>
		<script src="../js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
	    <script src="../js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
	   	<link rel="stylesheet" type="text/css" href="../js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	   	<link rel="stylesheet" type="text/css" href="../css/style.css"> 
	   	<script src="../js/tabuleiro.js" type="text/javascript"></script>
	   	<script src="../js/tabuleiroJogo.js" type="text/javascript"></script>
		<title>Tabuleiro</title>
	</head>
	<body>

		<div id="wrapperBoard">
			<div id="tabuleiro">
				<form:form id="matchForm" action="remanejamento" autocomplete="off" modelAttribute="territorioForm" method="POST">
					<c:set var="index" value="0" scope="page"/>	
					
					<c:forEach items="${usuarios}" var="usuario">
					
						<c:forEach items="${usuario.territorios}" var="territorio">
							<c:choose>
								<c:when test="${territorio.jogadorHumanoPodeAtacar == true}">
									<a class="circleButton atack ${territorio.nomeTerritorio} cor${usuario.cor}" href="#atackDiv">${territorio.quantidadeExercito}</a>
									
									<select id="playersAtackers_${territorio.nomeTerritorio}" name="playerAtacker" style="display:none">
										<c:forEach items="${territorio.vizinhosJogadorHumanoQuePodemAtacar}" var="vizinhoJogadorHumano">
											<option value="${vizinhoJogadorHumano.nomeTerritorio}">${vizinhoJogadorHumano.nomeTerritorio}</option>
										</c:forEach> 
									</select>
								
								</c:when>
								<c:otherwise>
									<a class="circleButton ${territorio.nomeTerritorio} cor${usuario.cor}" href="#atackDiv">${territorio.quantidadeExercito}</a>
								</c:otherwise>
							</c:choose>
							
							<form:hidden class="conquested_${territorio.nomeTerritorio}" path="territorios[${index}].corDoConquistador" value="" />
							<form:hidden path="territorios[${index}].idTerritorio" value="${territorio.idTerritorio}" />
							<form:hidden path="territorios[${index}].nomeTerritorio" value="${territorio.nomeTerritorio}" />
							<form:hidden class="territoryArmy_${territorio.nomeTerritorio}" path="territorios[${index}].quantidadeExercito" value="${territorio.quantidadeExercito}" />
							
							<c:set var="index" value="${index + 1}" scope="page"/>
						</c:forEach>
					</c:forEach>
					
					<input type="hidden" name="turno" value="${turno}" />
				</form:form>
				
				<a style="display:none;" id="atacked" href="#atackedDiv"></a>
			</div>
			
			<div id="info">
				<h2>Atacar</h2>
				<div id="back">Desistir</div>
				<c:if test="${erro != null}">
					<span class="errorMessage">${erro}</span> <br/><br/>
				</c:if>
				
				Jogadores (Rodada: <span id="turn">${usuarios[0].jogo.turno}</span>)
				<br/>
				<br/>
				<c:forEach items="${usuarios}" var="usuario">
					<div class="userBox_${usuario.cor}" id="userBox">
						<c:choose>
							<c:when test="${usuario.turnoDaJogada == true}">
								<div class="circleButtonTag playerTurnCircle cor${usuario.cor}"></div>
								<span class="playerTurnText">${usuario.nomeUsuario}</span>	(Jogando)			
								<br/>
								<span class="territoriesTotal" style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
								<c:if test="${usuario.jogadorHumano == true}">
									<input type="hidden" id="playerHumanTurn" value="true"/>							
									<span style="font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> <br/>
								</c:if>	
							</c:when>
							<c:when test="${usuario.aindaNoJogo == false}">
								<div class="circleButtonTag cor${usuario.cor} notPlayingDiv"></div>
								<span class="notPlayingText">${usuario.nomeUsuario}</span>	(Eliminado)			
								<br/>
								<span style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
								<c:if test="${usuario.jogadorHumano == true}">
									<span style="margin-bottom: 10px;font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> 
								</c:if>	
							</c:when>
							<c:otherwise>
								<div class="circleButtonTag cor${usuario.cor}"></div>
								<span>${usuario.nomeUsuario}</span>				
								<br/>
								<span class="territoriesTotal" style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
								<c:if test="${usuario.jogadorHumano == true}">
									<span style="font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> <br/>
								</c:if>
							</c:otherwise>
						</c:choose>
						
					</div>
					
					<br/>
				</c:forEach>
									
				<c:if test="${turnoJogadorHumano != true}">
					<c:if test="${fn:length(jogada.sumarioJogada) > 0}">
						<div id="movementDiv"> 
							<c:forEach items="${jogada.sumarioJogada}" var="sumario">
								<c:if test="${fn:contains(sumario, 'Você está sendo atacado.')}">
									<input type="hidden" id="playerAtacked" value="true"/>							
								</c:if>
								
								<p>${sumario}</p> 
							</c:forEach>
						</div>
					</c:if>
				</c:if>					
								
				<c:choose>
					<c:when test="${turnoJogadorHumano != true}">
						<div id="play" style="display:none; margin: 50px auto 80px auto;">
			        		<button id="playButton" type="submit">Continuar</button>
						</div>
					</c:when>
					<c:otherwise>
						<div id="play" style="margin: 20px auto;">
			        		<button id="playButton" type="submit">Continuar</button>
						</div>
					</c:otherwise>
				</c:choose>				
			</div>
		</div>
		
		<div style="display:none;">
			<div id="atackDiv" style="width:630px;height:580px;overflow:auto;">
			</div>
		</div>
		
		<div style="display:none;">
			<div id="atackedDiv" style="width:630px;height:580px;overflow:auto;">
				<h2>Você está sendo atacado!</h2>
				<span id="atackedInfo"></span>
				<div id="defenseButton"> 
					<span>Defender</span>
				</div>
			</div>
		</div>
		
		<c:if test="${usuarioVencedor != null}">
			<input type="hidden" id="endOfGameFlag" value="true"/>							
			<a style="display:none;" id="endGameLink" href="#endOfGame">confirm</a>
			
			<div style="display:none;">
				<div id="endOfGame" style="width:380px;height:380px;overflow:auto;">
					<h2>Fim de jogo</h2>
					<p>O jogador ${usuarioVencedor.nomeUsuario} venceu a partida!</p>
					<p>Objetivo: ${usuarioVencedor.objetivo.descricao}</p>
					
					<div id="backEndGame">
						<span>Voltar ao menu</span>
					</div>
				</div>
			</div>
		</c:if>
	</body>
</html>