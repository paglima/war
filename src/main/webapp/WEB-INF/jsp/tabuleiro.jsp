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
	   	<link rel="stylesheet" type="text/css" href="../css/style.css">
	   	<script src="../js/tabuleiro.js" type="text/javascript"></script>
		<title>Tabuleiro</title>
	</head>
	<body>
		<div id="tabuleiro" style="background-image: url(../img/war.jpg); height: 910px; width: 1280px;">
			<a id="tabLink" href="#tableDiv" style="position:absolute; left: 320px; top: 613px;">Brasil</a>
		</div>
		
		<div id="divTeste" style="display: none;">
			<div id="tableDiv" style="width:200px;height:300px;overflow:auto;">
				<h2>Ataque</h2>
				<table>
					  <tr>
					    <th style="">Países</th>
					    <th>Nº Soldados</th>
					  </tr>
					  <tr>
					    <td>
					    	<select>
							  <option value="Venezuela">Venezuela</option>
							  <option value="Colombia">Colombia</option>
							  <option value="Peru">Peru</option>
							  <option value="Uruguai">Uruguai</option>
							  <option value="Argentina">Argentina</option>
							  <option value="Chile">Chile</option>
							  <option value="Bolivia">Bolivia</option>
							</select>
					    </td>
					    <td>
					    	<select>
							  <option value="1">1</option>
							  <option value="2">2</option>
							  <option value="3">3</option>
							</select>
					    </td>
					  </tr>
				</table>
				<input type="button" value="atacar" style="top: 10px;"/>
			</div>
		</div>
	</body>
</html>