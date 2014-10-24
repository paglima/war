<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Objetivo</title>
       <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <script src="../js/jQuery/jquery-1.11.1.min.js" type="text/javascript"></script>
    	<script src="../js/script.js" type="text/javascript"></script>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    
	<h1>Cadastro Objetivos </h1>
        <form:form action="cadastro" method="post" commandName="objetivo">
            <table>
                <tr>	
                    <td>Id: <form:input path="idObjetivo"/>
                    </td>
                </tr>
                <tr>	
                    <td>Descricão: <form:input path="descricao"/>
                    </td>
                </tr>
               </table>
            <input type="submit"/>
        </form:form>
    </body>
    		
</html>