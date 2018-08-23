<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editer mon profil</title>
</head>
<body>
  <form method="post" action="<c:url value="/session/EditProfile"/>" enctype="multipart/form-data">
          <fieldset>
              <legend>Editer</legend>
              <p>Vous pouvez vous editer via ce formulaire.</p>
              
              <label for="avatar">Avatar <span class="requis">*</span></label>
              <input type="file" id="avatar" name="avatar" />
              <c:if test="${not empty sessionScope.userSession.avatar}">
              	<label>Avatar actuel: ${sessionScope.userSession.avatar}</label>
              	<c:set var="image"><c:out value="${ sessionScope.userSession.avatar }"></c:out></c:set>
				<a href="<c:url value="${ sessionScope.userSession.avatar }" />">Voir</a>
              </c:if>
              <br />
              <c:if test="${not empty errorsMap['AVATAR']}">
               <span class="erreur">${errorsMap['AVATAR']}</span>
               <br />
              </c:if>
              <c:if test="${not empty errorsMap['CONFIGURATION']}">
               <span class="erreur">${errorsMap['CONFIGURATION']}</span>
               <br />
              </c:if>
              
              <label for="name">Nom <span class="requis">*</span></label>
              <input type="text" id="name" name="name" value="<c:out value="${sessionScope.userSession.name}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['NAME']}">
               <span class="erreur">${errorsMap['NAME']}</span>
               <br />
              </c:if>
              <label for="firstname">Prenom <span class="requis">*</span></label>
              <input type="text" id="firstname" name="firstname" value="<c:out value="${sessionScope.userSession.firstname}"/>" size="20" maxlength="20" />
              <br />
              
              <label for="choixNouveauClient">Sexe <span class="requis">*</span></label>
              <input type="radio" id="gender" name="gender" value="male" checked /> Homme
              <input type="radio" id="gender" name="gender" value="female" /> Femme
              <br />

              <c:if test="${not empty errorsMap['FIRSTNAME']}">
               <span class="erreur">${errorsMap['FIRSTNAME']}</span>
               <br />
              </c:if>
              
              <label for="birthday">Date de naissance <span class="requis">*</span></label>
              <input type="date" id="birthday" name="birthday" value="<c:out value="${sessionScope.userSession.birthday}"/>" />
              <br />
              <c:if test="${not empty errorsMap['BIRTHDAY']}">
               <span class="erreur">${errorsMap['BIRTHDAY']}</span>
               <br />
              </c:if>
              
              <label for="phone">Numero de telephone <span class="requis">*</span></label>
              <input type="text" id="phone" name="phone" value="<c:out value="${sessionScope.userSession.phone}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['PHONE']}">
               <span class="erreur">${errorsMap['PHONE']}</span>
               <br />
              </c:if>
              <br />
              
              <label for="email">Adresse email <span class="requis">*</span></label>
              <input type="text" id="email" name="email" value="<c:out value="${sessionScope.userSession.email}"/>" size="20" maxlength="60" />
              <br />
              <c:if test="${not empty errorsMap['EMAIL']}">
               <span class="erreur">${errorsMap['EMAIL']}</span>
               <br />
              </c:if>

              <label for="username">Nom d'utilisateur <span class="requis">*</span></label>
              <input type="text" id="username" name="username" value="<c:out value="${sessionScope.userSession.username}"/>" size="20" maxlength="20" />
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
              <input type="text" id="country" name="country" value="<c:out value="${sessionScope.userSession.address.country}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['COUNTRY']}">
               <span class="erreur">${errorsMap['COUNTRY']}</span>
               <br />
              </c:if>
              
              <label for="city">Ville <span class="requis">*</span></label>
              <input type="text" id="city" name="city" value="<c:out value="${sessionScope.userSession.address.city}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['CITY']}">
               <span class="erreur">${errorsMap['CITY']}</span>
               <br />
              </c:if>
              
              <label for="postal">Code postal <span class="requis">*</span></label>
              <input type="text" id="postal" name="postal" value="<c:out value="${sessionScope.userSession.address.postal}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['POSTAL']}">
               <span class="erreur">${errorsMap['POSTAL']}</span>
               <br />
              </c:if>
              
              <label for="street">Rue <span class="requis">*</span></label>
              <input type="text" id="street" name="street" value="<c:out value="${sessionScope.userSession.address.street}"/>" size="20" maxlength="20" />
              <br />
              <c:if test="${not empty errorsMap['STREET']}">
               <span class="erreur">${errorsMap['STREET']}</span>
               <br />
              </c:if>
              
              <input type="submit" value="<c:out value="Editer"/>" class="sansLabel" />
              <br />
              <c:if test="${empty errorsMap}">
               <span class="success">Edition validated</span>
               <br />
              </c:if>
              
          </fieldset>
      </form>
</body>
<c:import url="../footer.jsp">
  <c:param name="design" value="bleu"/>
</c:import>
</html>