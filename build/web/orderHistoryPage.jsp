<%-- 
    Document   : orderHistoryPage
    Created on : Jan 20, 2021, 5:48:16 PM
    Author     : Dung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/searchProduct.css">
        <link rel="stylesheet" href="css/viewProduct.css">
    </head>
    <body>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName != 'Customer'}">
            <a href="loginPage.jsp">Login</a> 
        </c:if>
        <c:if test="${sessionScope.CURRENT_ROLE.roleName eq 'Customer'}">

            <header>
                <div class="hold">
                    <div class="header">
                        <div class="container">
                            <div id="logo">
                                <img id="imgLogo" src="img/logo.png">
                            </div>
                            <ul class="nav">
                                <li><a href="homePage.jsp">Home</a></li>
                                <li><a href="MainController?btnAction=Logout">Logout</a> </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>
            <section>
                <div class="search-box">
                    <form action="MainController" method="POST">
                        <p>
                            <select name="cmbSearch" required class="inputComboBox">
                                <option value="Name">Search by name</option>
                                <option value="Date">Search by Date</option>
                            </select>
                        </p>
                        <p>
                            <input class="submit-button" type="submit" value="Confirm Search Order Method" name="btnAction"/>
                        </p>
                        <c:if test="${sessionScope.SEARCH_HISTORY_METHOD eq 'Name'}">
                            <p>
                                <input class="input-text" type="text" name="txtSearchProductName" 
                                       value="${param.txtSearchProductName}" placeholder="Search Product By Name"/>
                            </p>
                        </c:if>
                        <c:if test="${sessionScope.SEARCH_HISTORY_METHOD eq 'Date'}">
                            <p>
                                <select name="cmbDate" required class="inputComboBox">
                                    <option value="" disabled selected>Search Product By Date</option>
                                    <c:forEach var="list" varStatus="counter" items="${sessionScope.DATE_LIST}">
                                        <option value="${list}">${list}</option>
                                    </c:forEach>
                                </select>
                            </p>
                        </c:if>
                        <c:if test="${sessionScope.SEARCH_HISTORY_METHOD == null}">
                            <p>
                                Choose search method first
                            </p>
                        </c:if>
                        <input class="submit-button" type="submit" value="Search History" name="btnAction">
                    </form>
                </div>
                <div class="sectionHolder" style="
                     position: absolute;
                     margin-top: 200px;
                     ">
                    <c:if test="${sessionScope.SEARCH_HISTORY_METHOD != null}">
                        <c:forEach var="list" varStatus="current" items="${sessionScope.ORDER_HISTORY}">
                            <div class="item-content three bg nopad pointer">
                                <p>Order Date:  ${list.orderDate}</p>

                                <p>Price:${list.price}</p>

                                <c:url var="historyDetail" value="MainController">
                                    <c:param name="btnAction" value="ViewHistoryDetail"></c:param>
                                    <c:param name="txtOrderID" value="${list.orderID}"></c:param>
                                </c:url>
                                <p>
                                    <a href="${historyDetail}">View detail</a>
                                </p>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
        </section>

    </body>
</html>
