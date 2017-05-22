<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html lang="en">
<head>
    <title>Spring shop</title>
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

        .search-form .form-group {
            float: right !important;
            transition: all 0.35s, border-radius 0s;
            width: 32px;
            height: 32px;
            background-color: #fff;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
            border-radius: 25px;
            border: 1px solid #ccc;
        }

        .search-form .form-group input.form-control {
            padding-right: 20px;
            border: 0 none;
            background: transparent;
            box-shadow: none;
            display: block;
        }

        .search-form .form-group input.form-control::-webkit-input-placeholder {
            display: none;
        }

        .search-form .form-group input.form-control:-moz-placeholder {
            /* Firefox 18- */
            display: none;
        }

        .search-form .form-group input.form-control::-moz-placeholder {
            /* Firefox 19+ */
            display: none;
        }

        .search-form .form-group input.form-control:-ms-input-placeholder {
            display: none;
        }

        .search-form .form-group:hover,
        .search-form .form-group.hover {
            width: 100%;
            border-radius: 4px 25px 25px 4px;
        }

        .search-form .form-group span.form-control-feedback {
            position: absolute;
            top: -1px;
            right: -2px;
            z-index: 2;
            display: block;
            width: 34px;
            height: 34px;
            line-height: 34px;
            text-align: center;
            color: #3596e0;
            left: initial;
            font-size: 14px;
        }
    </style>
    <jsp:include page="include.jsp"/>
</head>
<body>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <br>

            <h4>Group by</h4>
            <form method="post" action="/item/group">
                <select  name="group_id">
                    <c:forEach items="${groups}" var="storage">
                        <option value="${storage.id}">${storage}</option>
                    </c:forEach>
                </select>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit">Group</button>
            </form>

            <br>
            <h4>Search</h4>
            <div class="row">
                <form action="/item/search" method="post" class="search-form">
                    <div class="form-group has-feedback">
                        <label for="search" class="sr-only">Search</label>
                        <input type="text" class="form-control" name="search" id="search" placeholder="search">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </form>

            </div>
            <br>
        </div>

        <div class="col-sm-9">

            <div class="content">
                <h2>Items</h2>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Cost</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item"
                               items="${items}">
                        <tr>
                            <td><a href="/items/${item.id}">${item.name}</a></td>
                            <td>${item.cost} </td>

                            <td>
                                <c:if test="${message != null && item.id == itemId}">
                                    <p style="color: red">${message}</p>
                                </c:if>
                                <c:choose>
                                    <c:when test="${item.totalAmount > 0}">
                                        <form action="/add/item/${item.id}" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <input type="hidden" name="view" value="/">
                                            <button type="submit" class="btn btn-primary">Add</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="color: red">no items</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>


        </div>
    </div>
</div>

<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
