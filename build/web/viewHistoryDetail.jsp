<%-- 
    Document   : viewHistoryDetail
    Created on : Jan 20, 2021, 6:12:21 PM
    Author     : Dung
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/viewProduct.css">
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
                            <li><a href="homePage.jsp">Home Page</a></li>
                            <li><a href="orderHistoryPage.jsp">History Page</a> </li>
                            <li><a href="MainController?btnAction=Logout">Logout</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <section>
            <c:if test="${sessionScope.CURRENT_ROLE.roleName != 'Customer'}">
                <a href="loginPage.jsp">Login</a> 
            </c:if>
            <div class="sectionHolder">
                <c:if test="${sessionScope.CURRENT_ROLE.roleName eq 'Customer'}">
                    <c:forEach var="list" varStatus="current" items="${sessionScope.ORDER_DETAIL_HISTORY}">
                        <div class="item-content three bg nopad pointer">
                            <p>Product Name: ${list.productName}</p>

                            <p>Quantity: ${list.quantity}</p>

                            <p>Price Total: ${list.total}</p>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
    </section>
</body>
</html>
