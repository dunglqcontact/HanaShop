<%-- 
    Document   : adminPage
    Created on : Jan 13, 2021, 11:29:21 PM
    Author     : Dung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/searchProduct.css">
        <link rel="stylesheet" href="css/searchBar.css">
    </head>
    <body>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName != 'Admin'}">
            <a href="loginPage.jsp">Login</a> 
        </c:if>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName eq 'Admin'}">
            <header>
                <div class="hold">
                    <div class="header">
                        <div class="container">
                            <div id="logo">
                                <img id="imgLogo" src="img/logo.png">
                            </div>
                            <ul class="nav">
                                <li><a href="adminPage.jsp">Admin Page</a></li>
                                <li><a href="MainController?btnAction=InsertProductParamTransform">Insert New Product</a> </li>
                                <li><a href="MainController?btnAction=Logout">Logout</a> </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>

            <section>
                <div class="search-box">
                    <c:if test="${sessionScope.CURRENT_USER != null}">
                        <p> 
                            Welcome: ${sessionScope.CURRENT_ROLE.roleName} ${sessionScope.CURRENT_USER.fullName}
                        </p>
                    </c:if>
                    <form action="MainController" method="POST">
                        <p>
                            <select name="cmbMethodSearch" class="inputComboBox">
                                <option value="Name">Search by name</option>
                                <option value="Money">Search by range of money</option>
                                <option value="Category">Search by category</option>
                            </select>
                        </p>
                        <p>
                            <select name="cmbProductStatus" class="inputComboBox">
                                <option value="1">Stocking</option>
                                <option value="0">Out of stock</option>
                            </select>
                        </p>
                        <input class="submit-button" type="submit" value="Confirm Search Method" name="btnAction"/>
                        <c:if test="${sessionScope.SEARCH_METHOD eq 'Name'}">
                            <input class="input-text" type="text" name="txtSearchProductName" value="${param.txtSearchProductName}" placeholder="Search Product By Name"/>
                        </c:if>

                        <c:if test="${sessionScope.SEARCH_METHOD eq 'Money'}">
                            <input class="input-text" type="text" name="txtSearchProductMinMoney" value="${param.txtSearchProductMinMoney}" placeholder="Input Min Money"/>
                            <input class="input-text" type="text" name="txtSearchProductMaxMoney" value="${param.txtSearchProductMaxMoney}" placeholder="Input Max Money"/>
                        </c:if>

                        <c:if test="${sessionScope.SEARCH_METHOD eq 'Category'}">
                            <select name="cmbCategory">
                                <option value="" disabled selected>Search by Category</option>
                                <c:forEach var="list" varStatus="counter" items="${sessionScope.CATEGORY_LIST}">
                                    <option value="${list.categoryID}">${list.categoryName}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${sessionScope.SEARCH_METHOD == null}">
                            <p>
                                Choose search method first
                            </p>
                        </c:if>
                        <input class="submit-button" type="submit" value="Search Product" name="btnAction"/>
                    </form>
                </div>
                <br/>
                <div class="section bg">
                    <div class="container" style="
                     margin-top: 340px;">
                        <c:if test="${sessionScope.SEARCH_RESULT != null}">
                            <c:if test="${not empty sessionScope.SEARCH_RESULT }">
                                <h1>Result</h1>
                                <c:forEach var="list" varStatus="counter" items="${sessionScope.SEARCH_RESULT}">
                                    <div class="col three bg nopad pointer">
                                        <div class="imgholder">
                                            <img  src="data:image/jpeg;base64,${list.imageData}">
                                        </div>

                                        <div id="featureProduct">
                                            <h1 class="feature">
                                                Product Name:${list.productName}
                                            </h1>
                                            <p>Description: <p>${list.description}</p>
                                            <p>Price: <p>${list.price}</p>
                                            <p>Create Date: <p>${list.date}</p>
                                            <p>Quantity: <p>${list.quantity}</p>
                                            <p>Status: <p>${list.status}</p>
                                            <c:url var="delete" value="MainController">
                                                <c:param name="btnAction" value="Delete Product"></c:param>
                                                <c:param name="txtProductID" value="${list.productID}"></c:param>
                                            </c:url>
                                            <p><a href="${delete}" onclick="return confirm('Do you still want to delete this product?')">Delete Product</a></p>

                                            <c:url var="update" value="MainController">
                                                <c:param name="btnAction" value="Update Product Page"></c:param>
                                                <c:param name="txtProductID" value="${list.productID}"></c:param>
                                            </c:url>
                                            <p><a href="${update}" onclick="return confirm('Do you still want to update this product?')">Update Product</a></p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </section>

        </c:if>
    </body>
</html>
