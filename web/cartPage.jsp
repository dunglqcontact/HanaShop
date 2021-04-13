<%-- 
   Document   : cartPage
   Created on : Jan 19, 2021, 9:00:21 PM
   Author     : Dung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/viewProduct.css">
    </head>
    <body>
        <c:if test="${sessionScope.CURRENT_USER == null}">
            <a href="loginPage.jsp">Login</a> 
        </c:if>
        <c:if test="${sessionScope.CURRENT_USER != null}">
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
                <div class="sectionHolder">
                    <c:set var="orderDetail" value="${sessionScope.ORDER_DETAIL}" />
                    <c:if test="${orderDetail != null}">
                        <c:if test="${not empty sessionScope.PRODUCT_LIST}">
                            <c:forEach var="list" varStatus="current" items="${sessionScope.PRODUCT_LIST}">
                                <div class="item-content three bg nopad pointer">
                                    <form action="MainController" method="POST">
                                        <p>Product ID: ${list.productID}</p>
                                        <input type="hidden" name="txtProductID" value="${list.productID}"/>

                                        <p>Product Name: ${list.productName}</p>
                                        <input type="hidden" name="txtProductName" value="${list.productName}"/>

                                        <p>Unit Price: ${list.price}</p>
                                        <input type="hidden" name="txtPrice" value="${list.price}"/>
                                        <p>
                                            Quantity: <input type="text" name="txtQuantity" value="${list.quantity}"/>
                                        </p>
                                        <p>Total: ${list.totalPrice}</p>
                                        <input type="hidden" name="txtTotalPrice" value="${list.totalPrice}"/>

                                        <input type="hidden" name="txtTotal" value="${orderDetail.total}"/>

                                        <input class="button" type="submit" name="btnAction" value="Remove" onclick="return confirm('Do you still want to delete this product?')">
                                        <input class="button" type="submit" name="btnAction" value="Update">
                                    </form>
                                </div>
                            </c:forEach>


                        </c:if>
                    </c:if>
                </c:if>
            </div>
            
        </section>
            <div class="total-price">
                <p>Total Price: ${orderDetail.total}</p>
                <form action="MainController" method="POST">
                    <input type="hidden" name="txtTotal" value="${orderDetail.total}"/>
                    <input type="submit" value="Order" name="btnAction" onclick="return confirm('Do you want to order?')"/>
                </form>
            </div>
    </body>
</html>
