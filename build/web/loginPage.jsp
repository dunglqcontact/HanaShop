<%-- 
    Document   : loginPage
    Created on : Jan 5, 2021, 6:20:11 PM
    Author     : Dung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/inputForm.css">
    </head>
    <body>
        <header>
            <div class="hold">
                <div class="header">
                    <div class="container">
                        <div id="logo">
                            <img id="imgLogo" src="img/logo.png">
                        </div>
                        <ul class="nav">
                            <li><a href="homePage.jsp">Home</a></li>
                            <li><a href="registerAsGuestPage.jsp">Register</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <c:set var="error" value="${requestScope.LOGIN_ERROR}" />
        <section>
            <div id="login">
                <div class="imgPic">
                    <img id="imgLogin" src="img/loginPic.png">
                </div>
                <div class="inputForm">
                    <h1>LOGIN</h1>
                    <form action="MainController" method="POST">

                        <input type="text" name="txtUserID" placeholder="User ID" class="inputText"/><br>
                        <p>${error.userIDError}</p>
                        <input type="password" name="txtPassword" placeholder="Password" class="inputText"/><br>
                        <p>${error.passwordError}</p>
                        <p>${error.loginFailed}</p>
                        <input type="submit" value="Login" name="btnAction" class="inputButton"/>
                        <input type="reset" value="Reset" class="inputButton"/>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
