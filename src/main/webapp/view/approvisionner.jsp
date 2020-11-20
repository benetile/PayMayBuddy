<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Approvisionner</title>
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
            				<h1><span>Pay My Buddy</span></h1><br>
            				<c:if test="${!empty sessionScope.prenom}">
            				<h2>Welcome to Home Page ${sessionScope.prenom}</h2>
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
            		</br>

                <div class="container" id="homediv">
                    <div class ="jumbotron text-center">
                    <h2> Faire une nouvelle transaction </h2>
                    <h3> Ajouter les informations</h3>
                    <form modelAttribute="transaction" class="from-horizontal"  method="POST" action="/approvisionner">
                    <c:if test="${!empty user}">

                        <fieldset>
                        <label name="email_user" name="email_user">Email</label><br>
                        <input name="email_user" type="email" value=${user.email}><br>
                        <label name="description" name="description">Description</label><br>
                        <input name="description" type="text" /><br>
                        <label name="montant" type="text">Amount</label><br>
                        <input name="montant" type="text" ><br>
                        <label name="user_id" type="text">id Beneficiaire</label><br>
                        <input name="user_id" type="text" value=${user.id_user}><br>
                        </br></br>

                        <input type="submit" class="form-submit" value="Valider">
                        <a href="/transaction">Annuler</a>

                        <p class="erreur">${ erreur }</p>
                        </fieldset>

                        </c:if>
                        </form>
                           <c:choose>
                            <c:when test="${mode=='APPROVISIONNER_COMPTE'}">
                               <div class="alert alert-success">
                                 <h2>Votre transaction a été éffectué avec succès</h2>
                                    </div>
                               <a href="/transaction">Retour</a>
                            </c:when>
                            <c:when test="${mode=='ECHEC_TRANSACTION'}">
                            <div class="alert alert-danger">
                              <h2>Votre transaction a échouée</h2>
                            </div>
                             <a href="/transaction">Retour</a
                            </c:when>
                            </c:choose>
                           </div>
                    </div>
                    </div>
                    <script src="static/js/bootstrap.min.js"></script>
                    <script src="static/js/bootstrap.js"></script>
        </body>
</html>