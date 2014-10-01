<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Descricao</title>
	</head>
	<body>
		<c:forEach items="${objetivos}" var="objetivo">
		   <P>${objetivo.descricao}</p>
		</c:forEach>		
	</body>
</html>