<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }
    </style>
</head>
<body>


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button"
                    class="navbar-toggle"
                    data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="/">Spring Shop</a>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/admin/item">Add new item</a>
                    </li>
                    <li><a href="/admin/users" methods="get">Users</a></li>
                </ul>

            </c:if>
        </div>
        <div class="collapse navbar-collapse"
             id="myNavbar">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name == null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        <li><a href="/signup">Sign up</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/cart">
                                <span class="glyphicon glyphicon-shopping-cart"></span> </a>
                        </li>
                        <li>
                            <a href="/user">
                                <span class="glyphicon glyphicon-user"></span>
                                    ${pageContext.request.userPrincipal.name}
                            </a></li>

                        <li>
                            <form method="post" action="/logout">
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-link">
                                    <span class="glyphicon glyphicon-log-out">Logout</span>
                                </button>

                            </form>
                        </li>

                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

</body>
</html>
