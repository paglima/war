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
		<title>Tabuleiro</title>
	</head>
	<body>
		<div id="wrapperBoard">
			<div id="tabuleiro">
				<form:form id="distributionForm" action="distribuiExercito" autocomplete="off" modelAttribute="territorioForm" method="POST">
					<c:forEach items="${usuarios}" var="usuario">
					
						<c:forEach items="${usuario.territorios}" var="territorio" varStatus="status">
							<div class="circleButton ${territorio.nomeTerritorio} cor${usuario.cor}">
								<c:choose>
									<c:when test="${usuario.jogadorHumano == true}">
										<a class="tabLink ${territorio.nomeTerritorio}_link" href="#redistributionDiv">${territorio.quantidadeExercito}</a>
									</c:when>
									<c:otherwise>
										<a class="${territorio.nomeTerritorio}_link" href="#redistributionDiv">${territorio.quantidadeExercito}</a>
									</c:otherwise>
								</c:choose>
								
							</div>
							
							<form:hidden path="territorios[${status.index}].idTerritorio" value="${territorio.idTerritorio}" />
							<form:hidden path="territorios[${status.index}].nomeTerritorio" value="${territorio.nomeTerritorio}" />
							<form:hidden class="territoryArmy_${territorio.nomeTerritorio}" path="territorios[${status.index}].quantidadeExercito" value="${territorio.quantidadeExercito}" />
						</c:forEach>
					
					</c:forEach>
				</form:form>
			</div>
			
			<div id="info">
				Jogadores
				<br/>
				<br/>
				<c:forEach items="${usuarios}" var="usuario">
					<span>${usuario.nomeUsuario}</span>				
					<br/>
					<c:if test="${usuario.jogadorHumano == true}">
						<span>Objetivo: ${usuario.objetivo.descricao}</span> <br/>
						<span id="armyLeft">Exercitos Sobrando: ${usuario.exercitoSobrando}</span> <br/>
					</c:if>
					<br/>
				</c:forEach>
				
				<h2 id="play">JOGAR</h2>
			</div>
		</div>
		
		<div style="display:none;">
			<div id="redistributionDiv" style="width:280px;height:200px;overflow:auto;">
				<h2>Remanejar exército</h2>
				<span id="noArmyMessage" style="display:none;">Você não tem mais exércitos disponíveis para distribuir.</span>
				<select id="armyNumber">
				</select>
				<br/>
				<div id="redistributeButton">
					<span>Remanejar</span>
				</div>
			</div>
		</div>
	</body>
</html>