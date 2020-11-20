<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Profil</title>
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
                                <h2>&nbsp Welcome ${sessionScope.prenom}</h2>
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
                          <c:when test="${mode=='PROFIL'}">
                         <c:if test="${ !empty utilisateur }" >
                             <fieldset>
                               <legend><h2> Les Coordonnées de l utilisateur </h2></legend>
                               <table class="table table-bordered">
                                <thead>
                                  <tr>
                                  <th>Id Utilisateur</th><th>${utilisateur.id_user}</th></tr>
                                  <tr><th>Prénom</th><th>${utilisateur.prenom}</th></tr>
                                  <tr><th>Nom</th><th>${utilisateur.nom}</th></tr>
                                  <th>Email </th><th>${utilisateur.email}</th></tr>
                                  <th>Tel</th><th>${utilisateur.tel}</th></tr>
                                 </tr>
                                  </thead>
                                  </table>
                                  </fieldset>
                                  </c:if>
                                  <a href="/upadteUser">Modifier les infos </a> &nbsp &nbsp
                                  <a href="/deleteUser?id_user=${utilisateur.id_user}">Supprimer Compte</a>
                             </c:when>
                             <c:when test="${mode=='SUPPRIMER_USER'}">
                             <div class="alert alert-success">
                                 <h2>Votre compte a été supprimé avec succès</h2>
                              </div>
                               <a href="/login">Retour</a>
                             </c:when>
                             <c:when test="${mode=='UPDATE_USER'}">

                             <form class="from-horizontal"  method="POST" action="/profil">
                                    <c:if test="${ !empty update }" >
                                   <fieldset>
                               <legend><h2> Les Coordonnées à modifier </h2></legend>
                               <table class="table table-bordered">
                                <thead>

                                  <tr><tr><th>Prénom</th><th><input type="text" name="prenom" value="${update.prenom}"/></th></tr>
                                  <tr><th>Nom</th><th><input type="text" name="nom" value="${update.nom}"</th></tr>
                                  <tr><th>Tel</th><th><input type="text" name="tel" value="${update.tel}"</th></tr>
                                  <tr><th>Id Utilisateur</th><th><input type="password" name="mot_de_passe" value="${update.mot_de_passe}"</th></tr>

                                   </thead>
                                  </table>
                                  </fieldset>

                                 <input type="submit" class="btn btn-primary" value="Valider"/>
                                <a href="/profil">Retour</a>
                                </c:if>
                                 </form>
                              </c:when>
                           </c:choose>
                         </div>
                     </div>
        </body>
</html>