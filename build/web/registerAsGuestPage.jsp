<%-- 
    Document   : registerAsGuestPage
    Created on : Jan 7, 2021, 7:28:17 PM
    Author     : Dung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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

        <section>
            <div id="login">
                <div class="imgPic">
                    <img id="imgLogin" src="img/loginPic.png">
                </div>
                <div class="inputForm">
                    <h1>REGISTER</h1>
                    <c:set var="error" value="${requestScope.REGISTER_ERROR}" />
                    <form action="MainController" method="POST" enctype="multipart/form-data">
                        <p>
                            <input type="text" name="txtUserID" placeholder="User ID" class="inputText"/>
                        </p>
                        <p>
                            ${error.userIDError}
                        </p>
                        <p>
                            <input type="text" name="txtFullName" placeholder="Full Name" class="inputText"/>
                        </p>
                        <p>
                            ${error.fullNameError}
                        </p>
                        <p>
                            <input type="text" name="txtAddress" placeholder="Address" class="inputText"/>
                        </p>
                        <p>
                            ${error.addressError}
                        </p>
                        <!--<input type="Date" name="txtDateOfBirth" placeholder="Date of birth"/><br>-->
                        <p>
                            <input type="text" placeholder="Date of birth" onfocus="(this.type = 'date')" class="inputText"/>
                        </p>
                        <p>
                            <input type="password" name="txtPassword" placeholder="Password" class="inputText"/>
                        </p>
                        <p>
                            ${error.passwordError}
                        </p>
                        <p>
                            <input type="password" name="txtPasswordConfirm" placeholder="Password Confirm: " class="inputText"/>
                        </p>
                        <p>
                            ${error.passwordConfirmError}
                        </p>
                        <input type="submit" value="Register" name="btnAction" class="inputButton"/>
                        <input type="reset" value="Reset" class="inputButton"/>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
