<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <script src="../js/jQuery/jquery-1.8.0.min.js" type="text/javascript"></script>
		<script src="../js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
	    <script src="../js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
	   	<link rel="stylesheet" type="text/css" href="../js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	   	<!-- <link rel="stylesheet" type="text/css" href="../css/style.css"> -->
	   	<script src="../js/tabuleiro.js" type="text/javascript"></script>
		<title>Tabuleiro</title>
	</head>
	<body style="background-color: black; font-family: Verdana;">
		<div id="wrapper-tabuleiro">
			<div id="tabuleiro" style="background-image: url(../img/war.jpg); height: 910px; width: 1280px;">
				<a id="tabLink" href="#tableDiv" style="color: #fff; text-decoration: none; position:absolute; left: 320px; top: 613px;">Brasil</a>
			</div>
			
			<div id="divTeste" style="display: none;">
				<div id="tableDiv">
					<div id="atackDiv" style="width:280px;height:200px;overflow:auto;">
						<h2>Ataque</h2>
						<table>
							  <tr>
							    <th>Países</th>
							    <th>Soldados atacar</th>
							  </tr>
							  <tr>
							    <td>
							    	<select>
									  <option value="Colombia-Venezuela">Colombia/Venezuela</option>
									  <option value="Peru-Bolivia-Chile">Peru/Bolivia/Chile</option>
									  <option value="Argélia-Nigeria">Argélia/Nigéria</option>
									</select>
							    </td>
							    <td>
							    	<select>
									  <option value="3">3</option>
									  <option value="2">2</option>
									  <option value="1">1</option>
									</select>
							    </td>
							  </tr>
						</table>
						<input id="ataqueButton" type="button" value="atacar" style="position: relative; top: 20px; left: 120px"/>
					</div>
					
					<div id="remanejamentDiv" style="width:280px;height:200px;overflow:auto;">
						<h2>Remanejar</h2>
						<table>
							  <tr>
							  	<th>Território</th>
							    <th>Soldados</th>
							  </tr>
							  <tr>
							    <td>
							    	<select>
										<option value="Argentina">Argentina Uruguai</option>
									</select>
							    </td>
							    <td>
							    	<select>
									  <option value="3">8</option>
									  <option value="2">7</option>
									  <option value="1">6</option>
									  <option value="1">5</option>
									  <option value="1">4</option>
									  <option value="1">3</option>
									  <option value="1">2</option>
									  <option value="1">1</option>
									</select>
							    </td>
							  </tr>
						</table>
						<input id="ataqueButton" type="button" value="remanejar" style="position: relative; top: 20px; left: 120px"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>