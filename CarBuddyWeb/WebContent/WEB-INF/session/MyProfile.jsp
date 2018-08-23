<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil de ${sessionScope.userSession.firstname} ${sessionScope.userSession.name}</title>
</head>
<body>
	<p>Nom: ${sessionScope.userSession.name}</p>
	<p>Prenom: ${sessionScope.userSession.firstname}</p>
	<p>Age: ${requestScope.age}</p>
	<a href="<c:url value="/session/EditProfile"/>">Editer mon profil</a>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>