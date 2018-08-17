<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/include/css/form.css"/>" />
	</head>
	<body>
		  <form method="post" action="<c:url value="/Inscription"/>">
            <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
                
                <label for="name">Nom <span class="requis">*</span></label>
                <input type="text" id="name" name="name" value="<c:out value="${param.name}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['NAME']}">
	                <span class="erreur">${errorsMap['NAME']}</span>
	                <br />
                </c:if>
                <label for="firstname">Prenom <span class="requis">*</span></label>
                <input type="text" id="firstname" name="firstname" value="<c:out value="${param.firstname}"/>" size="20" maxlength="20" />
                <br />
                
                <label for="choixNouveauClient">Sexe <span class="requis">*</span></label>
                <input type="radio" id="sex" name="sex" value="male" checked /> Homme
                <input type="radio" id="sex" name="sex" value="female" /> Femme
                <br />

                <c:if test="${not empty errorsMap['FIRSTNAME']}">
	                <span class="erreur">${errorsMap['FIRSTNAME']}</span>
	                <br />
                </c:if>
                
                <label for="birthday">Date de naissance <span class="requis">*</span></label>
                <input type="date" id="birthday" name="birthday" value="<c:out value="${param.birthday}"/>" />
                <br />
                <c:if test="${not empty errorsMap['BIRTHDAY']}">
	                <span class="erreur">${errorsMap['BIRTHDAY']}</span>
	                <br />
                </c:if>
                
                <label for="phone">Numero de telephone <span class="requis">*</span></label>
                <input type="text" id="phone" name="phone" value="<c:out value="${param.phone}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['PHONE']}">
	                <span class="erreur">${errorsMap['PHONE']}</span>
	                <br />
                </c:if>
                <br />
                
                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="<c:out value="${param.email}"/>" size="20" maxlength="60" />
                <br />
                <c:if test="${not empty errorsMap['EMAIL']}">
	                <span class="erreur">${errorsMap['EMAIL']}</span>
	                <br />
                </c:if>

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

                <label for="confirmPW">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmPW" name="confirmPW" value="<c:out value=""/>" size="20" maxlength="20" />
                <br />
                <br />
                
                <label for="country">Pays <span class="requis">*</span></label>
                <input type="text" id="country" name="country" value="<c:out value="${param.country}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['COUNTRY']}">
	                <span class="erreur">${errorsMap['COUNTRY']}</span>
	                <br />
                </c:if>
                
                <label for="city">Ville <span class="requis">*</span></label>
                <input type="text" id="city" name="city" value="<c:out value="${param.city}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['CITY']}">
	                <span class="erreur">${errorsMap['CITY']}</span>
	                <br />
                </c:if>
                
                <label for="postal">Code postal <span class="requis">*</span></label>
                <input type="text" id="postal" name="postal" value="<c:out value="${param.postal}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['POSTAL']}">
	                <span class="erreur">${errorsMap['POSTAL']}</span>
	                <br />
                </c:if>
                
                <label for="street">Rue <span class="requis">*</span></label>
                <input type="text" id="street" name="street" value="<c:out value="${param.street}"/>" size="20" maxlength="20" />
                <br />
                <c:if test="${not empty errorsMap['STREET']}">
	                <span class="erreur">${errorsMap['STREET']}</span>
	                <br />
                </c:if>
                
                <input type="submit" value="<c:out value="Inscription"/>" class="sansLabel" />
                <br />

            </fieldset>
        </form>
	</body>
</html>