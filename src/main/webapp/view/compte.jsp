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
            				<h1><span>Pay My Buddy</span></h1><br>
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
                         <c:choose>
                         <c:when test="${mode=='INFO_COMPTE'}" >
                         <c:if test="${ !empty compteUser }" >
                            <fieldset>
                            <legend><h2> Les Coordonnées de votre Compte </h2></legend>
                            <table class="table table-striped-bordered">

                              <thead>
                                <tr>
                                <th>Id Compte</th><th>${compteUser.id_compte}</th></tr>
                                <th>Iban</th><th>${compteUser.iban}</th></tr>
                                <tr><th>Sold</th><th>${compteUser.sold}</th></tr>
                                <tr><th>Id Utilisateur</th><th>${compteUser.user_id}</th>

                                </tr>

                                </thead>

                                </table>
                           </fieldset>
                           <br></br></br>
                           <h2>Approvisionner votre compte</h2>
                           <form modelAttribute="compte" class="from-horizontal" method="POST" action="/compte">
                               <label>Numero de la carte </label>
                               <input type="" name="""/><br>
                               <label>Montant</label>
                               <input type="text" name="sold" value=""/><br>
                               <input type="submit" class="btn btn-primary" value="Valider"/>
                             </form>
                             <br><br>
                             <a href="/updateInfo-compte">Update Compte</a>
                             <a href="/supprimer-compte?id_compte=${compteUser.id_compte}">Delete Compte</a>
                             <a href="/virement">Fare Virement</a>

                        </c:if>
                        </c:when>
                        <c:when test="${mode=='CREATED_COMPTE'}">

                           <fieldset>
                           <legend><h2> Veuillez Créer un compte </h2></legend>
                                <a href="/creerCompte">Creer un Compte</a>
                           </fieldset>
                       </c:when>
                         <c:when test="${mode=='APPROVISIONNER'}">
                         <div class="alert alert-success">
                             <h2>Votre compte a été crédité avec succès</h2>
                          </div>
                           <a href="/compte">Retour</a>
                         </c:when>
                         <c:when test="${mode=='SUPPRIMER_COMPTE'}">
                          <div class="alert alert-success">
                              <h2>Votre compte a été supprimé avec succès</h2>
                           </div>
                            <a href="/compte">Retour</a>
                          </c:when>

                        </c:choose>
                        <br><br>

                       </div>
                     </div>

        </body>
</html>