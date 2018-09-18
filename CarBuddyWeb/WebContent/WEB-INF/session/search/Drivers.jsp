<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des conducteurs</title>
</head>
<body>
	<c:forEach items="${requestScope.drivers}" var="driver">
		<div>
			<p>Nom: ${driver.name}</p>
			<p>Prenom: ${driver.firstname}</p>
			<p>Travel date: ${driver.driverProfile.travelDate}</p>
			<c:if test="${!empty driver.driverProfile.preferedStart}">
				<p>Destination: ${driver.driverProfile.preferedStart.street} ${driver.driverProfile.preferedStart.postal}
				${driver.driverProfile.preferedStart.city} ${driver.driverProfile.preferedStart.country}</p>
			</c:if>
			<c:if test="${!empty driver.driverProfile.destination}">
				<p>Destination: ${driver.driverProfile.destination.street} ${driver.driverProfile.destination.postal}
				${driver.driverProfile.destination.city} ${driver.driverProfile.destination.country}</p>
			</c:if>
		</div>
	</c:forEach>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>