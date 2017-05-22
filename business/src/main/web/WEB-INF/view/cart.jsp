<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card</title>
</head>
<jsp:include page="include.jsp"/>
<body>
<div class="content" style="padding-left: 20px">
    <h1>Items</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Cost</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="itemOrder"
                   items="${cart.itemOrders}">
            <tr>
                <td>${itemOrder.item.name}</td>
                <td>${itemOrder.item.cost}</td>
                <td>${itemOrder.amount}</td>
                <td>
                    <c:choose>
                        <c:when test="${itemOrder.item.totalAmount > 0}">
                            <form action="/add/item/${itemOrder.item.id}" method="post">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                                <input type="hidden" name="view" value="/cart">
                                <button type="submit" class="btn btn-link">Add</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <p style="color: red">no more items</p>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${message != null && itemOrder.item.id == itemId}">
                       <td><p style="color: red">${message}</p></td>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${cart.itemOrders.size() > 0}">
        <form style="float: right; padding-right: 200px" method="get" action="/buy">
            <button type="submit" class="btn btn-primary btn-lg"> Buy</button>
        </form>
    </c:if>
</div>
</body>
</html>
