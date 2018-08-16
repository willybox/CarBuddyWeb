<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil de ${driver.firstname} ${driver.name}</title>
</head>
<body>
	<p>Nom: ${driver.name}</p>
	<p>Prenom: ${driver.firstname}</p>
	<p>Age: ${requestScope.age}</p>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>