<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page d'accueil</title>
</head>
<body>
	<a href="<c:url value="/Deconnection"/>">Log out</a>
	<c:out value="${ dummyHello }"></c:out>
</body>
</html>