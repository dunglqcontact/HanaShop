<%-- 
    Document   : updateProductPage
    Created on : Jan 19, 2021, 3:29:19 PM
    Author     : Dung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/inputForm.css">
    </head>
    <body>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName != 'Admin'}">
            <a href="loginPage.jsp">Login</a> 
        </c:if>
        <header>
            <div class="hold">
                <div class="header">
                    <div class="container">
                        <div id="logo">
                            <img id="imgLogo" src="img/logo.png">
                        </div>
                        <ul class="nav">
                            <li><a href="adminPage.jsp">Admin Page</a></li>
                            <li><a href="MainController?btnAction=Logout">Logout</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName eq 'Admin'}">
            <c:set var="error" value="${requestScope.UPDATE_PRODUCT_ERROR}" />
            <section>
                <div id="login">
                    <div class="imgPic">
                        <img id="imgLogin" src="img/loginPic.png">
                    </div>
                    <div class="inputForm">
                        <h1>UPDATE PRODUCT</h1>
                        <form action="MainController" method="POST" enctype="multipart/form-data">
                            <input type="text" name="txtProductID" value="Book ID: ${param.txtProductID}" readonly class="inputText"/><br>
                            <input type="text" name="txtProductName" placeholder="Product Name" class="inputText"/><br>
                            <p>${error.productNameError}</p>
                            <input type="text" name="txtDescription" placeholder="Description" class="inputText"/><br>
                            <p>${error.descriptionError}</p>
                            <input type="text" name="txtPrice" placeholder="Price" class="inputText"/><br>
                            <p>${error.priceError}</p>
                            <select name="cmbCategory" required class="inputComboBox">
                                <option value="" disabled selected>Category</option>
                                <c:forEach var="list" varStatus="counter" items="${sessionScope.CATEGORY_LIST}">
                                    <option value="${list.categoryID}">${list.categoryName}</option>
                                </c:forEach>
                            </select><br>
                            <input type="number" name="txtQuantity" placeholder="Quantity" class="inputText"/><br>
                            <p>${error.quantityError}</p>
                            <input type="file" name="file" placeholder="File" class="inputText"/><br>
                            <p>${error.imageDataError}</p>
                            <fieldset>
                                <p class="statusForm">Status</p>
                                Stocking: <input type="radio" value="true" name="txtStatus" checked="checked"/>
                                Out of stock: <input type="radio" value="false" name="txtStatus"/>
                                <p>${error.statusError}</p>
                            </fieldset>
                            <input type="submit" value="Update Product" name="btnAction"/>
                        </form>
                    </div>
                </div>
            </section>
        </c:if>
    </body>
</html>
