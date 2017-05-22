<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html lang="en">
<head>
    <title>${item.name}</title>
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

            <h3>${item.name}</h3>
            <c:if test="${message != null && item.id == itemId}">
                <p style="color: red">${message}</p>
            </c:if>
            <c:choose>
                <c:when test="${item.totalAmount > 0}">
                    <form action="/add/item/${item.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="view" value="/items/">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p style="color: red">no items</p>
                </c:otherwise>
            </c:choose>
            <br>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <br>
                <br>
                <h4>Add items into storage</h4>
                <form action="/admin/edit/item/${item.id}" method="post">
                    <select name="storage_id">
                        <c:forEach items="${storages}" var="storage">
                            <option value="${storage.id}">${storage.address} ${storage.city}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <br>
                    <input name="amount" type="number" placeholder="amount">
                    <br>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <br>
                    <button type="submit">Add into storage</button>
                </form>
            </c:if>
        </div>

        <div class="col-sm-9">

            <div class="content">
                <h1>Info</h1>
                <h4>Name: ${item.name}</h4>
                <h4>Cost: ${item.cost}</h4>
                <h4>Total amount: ${item.totalAmount}</h4>
                <h4>Description: ${item.description} </h4>
                <h4>Group: ${item.group} </h4>
                <br>
                <h4>Storages: </h4>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>City</th>
                        <th>Address</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="itemStorages"
                               items="${item.itemStorages}">
                        <tr>
                            <td>${itemStorages.storage.city}</td>
                            <td>${itemStorages.storage.address}</td>
                            <td>${itemStorages.amount}</td>
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
