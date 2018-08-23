<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil de ${users[1].firstname} ${users[1].name}</title>
</head>
<body>
	<p>Nom: ${users[1].name}</p>
	<p>Prenom: ${users[1].firstname}</p>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>