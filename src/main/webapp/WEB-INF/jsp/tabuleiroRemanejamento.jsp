<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="hidden"%>	
	
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
	   	<script src="../js/tabuleiroRemanejamento.js" type="text/javascript"></script>
		<title>Tabuleiro</title>
	</head>
	<body>
		<div id="wrapperBoard">
			<div id="tabuleiro">
				<form:form id="distributionForm" action="preDistribuicao" autocomplete="off" modelAttribute="territorioForm" method="POST">
					<c:set var="index" value="0" scope="page"/>	

					<c:forEach items="${usuarios}" var="usuario">
					
						<c:forEach items="${usuario.territorios}" var="territorio">
								<c:choose>
									<c:when test="${usuario.jogadorHumano == true && usuario.turnoDaJogada == true}">
										<a class="circleButton tabLink ${territorio.nomeTerritorio} cor${usuario.cor}" href="#redistributionDiv">${territorio.quantidadeExercito}</a>
									
										<select id="borderAlly_${territorio.nomeTerritorio}" name="borderAlly" style="display:none">
											<c:forEach items="${territorio.vizinhosJogadorHumano}" var="vizinhoJogadorHumano">
												<option value="${vizinhoJogadorHumano.nomeTerritorio}">${vizinhoJogadorHumano.nomeTerritorio}</option>
											</c:forEach> 
										</select>
										
									</c:when>
									<c:otherwise>
										<a class="circleButton ${territorio.nomeTerritorio} cor${usuario.cor}" href="#redistributionDiv">${territorio.quantidadeExercito}</a>
									</c:otherwise>
								</c:choose>
							
							<form:hidden path="territorios[${index}].idTerritorio" value="${territorio.idTerritorio}" />
							<form:hidden class="territoryArmy_${territorio.nomeTerritorio}" path="territorios[${index}].quantidadeExercito" value="${territorio.quantidadeExercito}" />
							<span style="display:none" id="formerArmyQuantity">${usuario.territorios[index].quantidadeExercito}</span>
						
							<c:set var="index" value="${index + 1}" scope="page"/>
						</c:forEach>
					</c:forEach>
					
					<input type="hidden" name="turno" value="${turno}" />
					
				</form:form>
			</div>
			
			<div id="info">
				<h2>Remanejar exércitos</h2>
				<div id="back">Desistir</div>
				Jogadores (Rodada: <span id="turn">${usuarios[0].jogo.turno}</span>)
				<br/>
				<br/>
				<c:forEach items="${usuarios}" var="usuario">
					<div id="userBox">
						<c:choose>
							<c:when test="${usuario.turnoDaJogada == true}">
								<div class="circleButtonTag playerTurnCircle cor${usuario.cor}"></div>
								<span class="playerTurnText">${usuario.nomeUsuario}</span>	(Jogando)			
								<br/>
								<span style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
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
								<span style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
								<c:if test="${usuario.jogadorHumano == true}">
									<span style="font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> <br/>
								</c:if>
							</c:otherwise>
						</c:choose>
						
					</div>
					<br/>
				</c:forEach>
				
	        	<div id="play" style="display:none; margin: 20px auto;">
	        		<button id="playButton" type="submit">Passar turno</button>
				</div>
				
			</div>
		</div>
		
		<div style="display:none;">
			<div id="redistributionDiv" style="width:330px;height:320px;overflow:auto;">
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