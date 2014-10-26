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
				
					<div id="numberOfPlayers" > 
						<label for="numeroJogadores">Entre com o número de jogadores</label> 
						<select id="numeroJogadores" name="quantidadeInimigos">
						  <option value="1" selected>1</option>
						  <option value="2">2</option>
						  <option value="3">3</option>
						  <option value="4">4</option>
						  <option value="5">5</option>
						  <option value="6">6</option>
						</select>
					</div>
				
	        		<span>Digite o seu nome:</span>
	        		<input type="text" name="nome"/>
	        		
		        	<div id="play">
		        		<button id="playButton" type="submit">Jogar</button>
					</div>
					
		        </form>
				
			</div>
		</div>
    </body>	
</html>