<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des buddies</title>
</head>
<body>
	<c:forEach items="${requestScope.buddies}" var="buddy">
		<div>
			<p>Nom: ${buddy.name}</p>
			<p>Prenom: ${buddy.firstname}</p>
			<p>Travel date: ${buddy.buddyProfile.travelDate}</p>
			<c:if test="${!empty buddy.buddyProfile.start}">
				<p>Destination: ${buddy.buddyProfile.start.street} ${buddy.buddyProfile.start.postal}
				${buddy.buddyProfile.start.city} ${buddy.buddyProfile.start.country}</p>
			</c:if>
			<c:if test="${!empty buddy.buddyProfile.destination}">
				<p>Destination: ${buddy.buddyProfile.destination.street} ${buddy.buddyProfile.destination.postal}
				${buddy.buddyProfile.destination.city} ${buddy.buddyProfile.destination.country}</p>
			</c:if>
		</div>
	</c:forEach>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>