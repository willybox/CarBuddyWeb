<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Page d'accueil</title>
	<link type="text/css" rel="stylesheet" href="form.css" />
	</head>
	<body>
		<div id="menu">
		  <form method="post" action="Connection">
	           <fieldset>
	               <legend>Connexion</legend>
	               <p>Vous pouvez vous connecter ou vous inscrire.</p>
	
	               <label for="username">Nom d'utilisateur <span class="requis">*</span></label>
	               <input type="text" id="username" name="username" value="<c:out value="${param.username}"/>" size="20" maxlength="20" />
	               <br />
	               <c:if test="${not empty errorsMap['USERNAME']}">
	                <span class="erreur">${errorsMap['USERNAME']}</span>
	                <br />
	               </c:if>
	
	               <label for="password">Mot de passe <span class="requis">*</span></label>
	               <input type="password" id="password" name="password" value="<c:out value=""/>" size="20" maxlength="20" />
	               <br />
	               <c:if test="${not empty errorsMap['PASSWORD']}">
	                <span class="erreur">${errorsMap['PASSWORD']}</span>
	                <br />
	               </c:if>
	               
	               <input type="submit" value="<c:out value="Se connecter"/>" class="sansLabel" />
	               <br />
	               
		    	<p><a href="<c:url value="/Inscription"/>">S'incrire</a></p>
	           </fieldset>
	       </form>
		</div>
	</body>
</html>