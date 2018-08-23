<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil de ${requestScope.user.firstname} ${requestScope.user.name}</title>
</head>
<body>
	<p>Nom: ${requestScope.user.name}</p>
	<p>Prenom: ${requestScope.user.firstname}</p>
	<p>Age: ${requestScope.age}</p>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>