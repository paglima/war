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
	   	<script src="../js/tabuleiroRedistribuicao.js" type="text/javascript"></script>
		<title>Tabuleiro</title>
	</head>
	<body>
		<div id="wrapperBoard">
			<div id="tabuleiro">
				<form:form id="distributionForm" action="partida" autocomplete="off" modelAttribute="territorioForm" method="POST">
					<c:set var="index" value="0" scope="page"/>	

					<c:forEach items="${usuarios}" var="usuario">
					
						<c:forEach items="${usuario.territorios}" var="territorio">
								<c:choose>
									<c:when test="${usuario.jogadorHumano == true}">
										<a class="circleButton tabLink ${territorio.nomeTerritorio} cor${usuario.cor}" href="#redistributionDiv">${territorio.quantidadeExercito}</a>
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
				
				<input id="firstDistribuition" type="hidden" name="firstDistribuition" value="${usuarios[0].jogo.distrubuicaoInicial}" />
			</div>
			
			<div id="info">
				<h2>Distribuir exércitos</h2>
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
									<span id="armyLeft" style="font-size: 0.8em;">Exercitos Sobrando: ${usuario.exercitoSobrando}</span> <br/>
									<span style="font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> <br/>
									<a id="cards" href="#cardsDiv">Cartas</a>
								</c:if>	
							</c:when>
							<c:otherwise>
								<div class="circleButtonTag cor${usuario.cor}"></div>
								<span>${usuario.nomeUsuario}</span>				
								<br/>
								<span style="font-size: 0.8em;">Total de territórios: ${usuario.totalDeTerritorios}</span> <br/>
								<c:if test="${usuario.jogadorHumano == true}">
									<span id="armyLeft" style="font-size: 0.8em;">Exercitos Sobrando: ${usuario.exercitoSobrando}</span> <br/>
									<span style="font-size: 0.8em;font-weight: bold;font-style: italic;">Objetivo: ${usuario.objetivo.descricao}</span> <br/>
								</c:if>
							</c:otherwise>
						</c:choose>
						
					</div>
					<br/>
				</c:forEach>
				
	        	<div id="play" style="display:none;	margin: 50px auto 80px auto;">
	        		<button id="playButton" type="submit">Continuar</button>
				</div>
				
				<a style="display:none;" id="keepDistribuition" href="#redistributionNotCompleted">confirm</a>
			</div>
		</div>
		
		<div style="display:none;">
			<div id="redistributionDiv" style="width:330px;height:280px;overflow:auto;">
				<h2>Distribuir exército</h2>
				<span id="noArmyMessage" style="display:none;">Você não tem mais exércitos disponíveis para distribuir. <br/><br/></span>
				<select id="armyNumber">
				</select>
				<br/>
				<br/>
				<div id="redistributeButton">
					<span>Distribuir</span>
				</div>
			</div>
		</div>
		
		<div style="display:none;">
			<div id="redistributionNotCompleted" style="width:280px;height:230px;overflow:auto;">
				<h2>Distribuir exército</h2>
				<span>Você ainda tem exércitos para distribuir.</span>
				<br/>
				<br/>
				<div id="okButton">
					<span>Ok</span>
				</div>
			</div>
		</div>
		
		<div style="display:none;">
			<div id="cardsDiv" style="width:280px;height:230px;overflow:auto;">
				<h2>Cartas</h2>
				<br/>
				
				<c:choose>
					<c:when test="${fn:length(usuarios[0].jogo.usuarioHumano.cartas) > 0}">
						<c:forEach items="${usuarios[0].jogo.usuarioHumano.cartas}" var="carta">
							
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span>Você ainda não tem nenhuma carta.</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</body>
</html>