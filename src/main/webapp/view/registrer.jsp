<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Registrer</title>
        <link href="static/css/style01.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap-reboot.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap.-reboot.min.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap-grid.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap.grid.min.css" rel="stylesheet" type="text/css">
         <link href="static/css/bootstrap.css" rel="stylesheet" type="text/css">
    </head>
        <body>

            <h1><span> Pay My Buddy</span></h1>
            <form:form modelAttribute="utilisateur" action="/registrer" method="post"
            					class="form">
            					<c:if test="${not empty error}">
            					    <div class="alert alert-danger">
            					        <c:out value="${error}"></c:out>
            					        </div>
            					    </c:if>
            					<fieldset>
                                <legend><h2>Vos Coordonnées</h2></legend>
            					<form:label path="prenom" class="form-label">Prenom</form:label><br>
            					<form:input path="Prenom" type="text" placeholder="Prenom"/><br>
            					<form:label path="nom" class="form-label">Nom</form:label><br>
            					<form:input path="Nom" type="text" placeholder="Nom"/><br>
            					<form:label path="email" class="form-label">email</form:label><br>
            					<form:input path="email" type="email" placeholder="abcdc@.****.abc"/><br>
            					<form:label path="tel" class="form-label">Tel</form:label><br>
            					<form:input path="tel" type="text" placeholder="0123456789"/><br>
            					<form:label path="mot_de_passe" class="form-label">Mot de passe</form:label><br>
            					<form:input path="mot_de_passe" type="password"
            						placeholder="Mot de passe" class="form-input" /><br><br>
            					<input type="submit" class="form-submit" value="Enregister"/>
            					<a href="/login">Annuler</a>


            					<p class="erreur">${ erreur }</p>
            					</fieldset>
            				</form:form>

        </body>
</html>