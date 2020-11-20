<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Transaction</title>
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
                    <h2> Effectuer une nouvelle transaction </h2>
                    <form class="from-horizontal" modelAttribute="email" method="POST" action="/transaction">
                       <label type="email" name="email">Email </label>
                        <input type="email" name="email" />
                        <input type="submit" class="btn btn-primary" value="Verifier"/>
                      </form>

                    </div>
                    </div>
                    <div class="container" id="homediv">
                    <div class ="jumbotron text-center">
                    <h2>My Transactions</h2>
                      <table class="table table-striped-bordered">
                      <thead>
                        <tr>
                        <th>Email</th>
                        <th>Description</th>
                        <th>Date transaction</th>
                        <th>Amount</th>
                        </tr>
                        </thead>
                         <tbody>
                         <c:forEach var="transaction" items="${transactions}">
                               <tr>
                               <td>${transaction.email_user}</td>
                               <td>${transaction.description}</td>
                               <td>${transaction.date}</td>
                               <td>${transaction.montant}</td>
                             </tr>
                            </c:forEach>
                         </tbody>
                      </table>

                        <ul class="pagination">
                          <li><a href="#">Prev</a></li>
                          <li><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">5</a></li>
                          <li><a href="#">Next</a></li>
                        </ul>
                        <br><br>
                    </div>
                </div>
                    <script src="static/js/bootstrap.min.js"></script>
                    <script src="static/js/bootstrap.js"></script>
        </body>
</html>