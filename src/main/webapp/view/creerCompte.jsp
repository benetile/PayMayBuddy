<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Compte</title>
                <link href="static/css/bootstrap.css" rel="stylesheet" type="text/css">
                                <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
                                <link href="static/css/bootstrap-reboot.css" rel="stylesheet" type="text/css">
                                <link href="static/css/bootstrap.-reboot.min.css" rel="stylesheet" type="text/css">
                                <link href="static/css/bootstrap-grid.css" rel="stylesheet" type="text/css">
                                <link href="static/css/bootstrap.grid.min.css" rel="stylesheet" type="text/css">
                                <link href="static/css/style1.css" rel="stylesheet" type="text/css">
    </head>
        <body>

            		<header>
            				<p><h1>Pay My Buddy</h1></p>
            				<c:if test="${!empty sessionScope.prenom}">
                                <h2>Welcome ${sessionScope.prenom}</h2>
                            </c:if>
            		</header>
            		<nav>
            			<ul>
            				<li><a href="index">Home</a></li>
            				<li><a href="compte">Compte</a></li>
            				<li><a href="transaction">Transfer</a></li>
            				<li><a href="profil">Profil</a></li>
            				<li><a href="contact">Contact</a></li>
                            <li><a href="logout">Logout</a></li>
            			</ul>
            		</nav>

                    <div class="container" id="homediv">
                         <div class ="jumbotron text-center">
                             <fieldset>
                               <legend><h2> Veuillez Créer un compte </h2></legend>
                               <form modelAttribute="newCompte" class="from-horizontal" method="POST" action="/creerCompte">
                                    <label name="iban">IBan</label></br>
                                    <input name="iban" type="text"><br>
                                    <input type="submit" class="form-submit" value="Valider la Création">
                                    <input type="submit" class="form-submit" value="Annuler">

                               <p class="erreur">${ erreur }</p>
                              </fieldset>
                              </form>
                              <c:choose>
                            <c:when test="${mode=='CREATION'}">
                            <div class="alert alert-success">
                                <h2>Votre compte a été crée avec succès</h2>
                             </div>
                              <a href="/compte">Retour</a>
                            </c:when>
                            </c:choose>
                         </div>
                     </div>
        </body>
</html>