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
                            <c:when test="${mode=='BENEFICIARE'}" >
                            <c:if test="${ !empty beneficiaire }" >
                               <fieldset>
                               <legend><h2> Ajouter un nouveau Beneficiare </h2></legend>

                              <br></br></br>
                              <h2>Effectué un virement</h2>
                              <form modelAttribute="compte" class="from-horizontal" method="POST" action="/virement">
                                  <label>Montant</label>
                                  <input type="text" name="sold" value=""/><br>
                                  <input type="submit" class="btn btn-primary" value="Valider"/>
                                </form>
                                <br><br>
                                <a href="compte">Retour</a>

                           </c:if>
                           </c:when>
                           <c:when test="${mode=='SUCCES'}">
                              <div class="alert alert-success">
                                <h2>Votre virement a été éffectué avec succès</h2>
                                   </div>
                              <a href="/compte">Retour</a>
                           </c:when>
                           <c:when test="${mode=='ECHEC_OPERATION'}">
                           <div class="alert alert-danger">
                             <h2>Votre virement a échouée</h2>
                           </div>
                            <a href="/compte">Retour</a
                           </c:when>
                           </c:choose>
                    <br><br>

                   </div>
                 </div>

    </body>
</html>