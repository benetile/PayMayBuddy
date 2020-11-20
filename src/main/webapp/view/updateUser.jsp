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
                               <c:when test="${mode=='VALIDER_INFOS'}">
                                  <div class="alert alert-success">
                                    <h2>Votre modification a éte éffectuée avec succès</h2>
                                       </div>
                                  <a href="/transaction">Retour</a>
                               </c:when>
                               <c:when test="${mode=='ECHEC_MODIFICATION'}">
                               <div class="alert alert-danger">
                                 <h2>Votre modification a échouée </h2>
                               </div>
                                <a href="/transaction">Retour</a
                               </c:when>
                            </c:choose>
                          </div>
                      </div>
                     </body>
             </html>
