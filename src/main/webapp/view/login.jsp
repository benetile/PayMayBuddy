<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Login</title>
        <link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap-reboot.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap.-reboot.min.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap-grid.css" rel="stylesheet" type="text/css">
        <link href="static/css/bootstrap.grid.min.css" rel="stylesheet" type="text/css">
        <link href="static/css/style01.css" rel="stylesheet" type="text/css">

    </head>
        <body>
            <div class="container" id="homediv">
                <div class ="jumbotron text-center">
                    <h1><span> Pay My Buddy</span></h1>

                        <form:form modelAttribute="utilisateur" action="/login" method="post"
                                        class="form">
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                     <c:out value="${error}"></c:out>
                                </div>
                            </c:if>
                            <form:label path="email" class="form-label">Email</form:label><br>
                            <input type="email" name="email" placeholder="Email" value="${utilisateur.email}"/>
                            <br>
                            <form:label path="mot_de_passe" class="form-label">Mot de passe</form:label><br>
                              <input type="password" name="mot_de_passe" value="${utilisateur.mot_de_passe}" placeholder="Mot de passe" class="form-input" /><br><br>
                           <input type="submit" class="form-submit" value="Login">
                            <p class="erreur">${ erreur }</p>
                        </form:form>
                <p><a href="registrer">Create Compte</a></p>
        </div>
        </div>
        </body>
</html>