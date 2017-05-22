<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html lang="en">
<head>
    <title>${user.fio}</title>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>

        .row.content {
            height: 1500px
        }

        /* Set gray background color and 100% height */
        .sidenav {
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
    <jsp:include page="include.jsp"/>
</head>
<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <br>

            <h4>Email: ${user.email}</h4>
            <h4>FIO: ${user.fio}</h4>
            <h4>Addres: ${user.address}</h4>
            <br>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <form method="post" action="/admin/users/enable/${user.id}">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-link">
                        <c:choose>
                            <c:when test="${user.enabled}">
                                Set disabled
                            </c:when>
                            <c:otherwise>
                                Set enabled
                            </c:otherwise>
                        </c:choose>
                    </button>
                </form>
                <form method="post" action="/admin/users/delete/${user.id}">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-link">Delete user</button>
                </form>
                <a href="/admin/users" methods="get"><h4>Users</h4></a>
            </c:if>
        </div>

        <div class="col-sm-9">

            <div class="content">
                <h1>Orders</h1>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>status</th>
                        <th>items</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order"
                               items="${user.orders}">
                        <tr>
                            <td>${order.statusEnum.name()}</td>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="${order.itemOrders}">
                                        <li>${item.item.name}, amount: ${item.amount}
                                            <c:if test="${order.statusEnum.name().equals('Setting')}">
                                                <c:choose>
                                                    <c:when test="${item.item.totalAmount > 0}">
                                                        <form action="/item/${item.item.id}" method="post">
                                                            <input type="hidden" name="${_csrf.parameterName}"
                                                                   value="${_csrf.token}"/>
                                                            <input type="hidden" name="view" value="/user">
                                                            <button type="submit" class="btn btn-link">Add</button>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p>no more items</p>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:if test="${message != null && item.item.id == itemId}">
                                                    <p style="color: red">${message}</p>
                                                </c:if>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <c:if test="${order.statusEnum.name().equals('Setting')}">
                                <td>
                                    <form method="get" action="/buy">
                                        <button type="submit" class="btn btn-link">Buy</button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>


        </div>
    </div>
</div>
</body>
</html>
