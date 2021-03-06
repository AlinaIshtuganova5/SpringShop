<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible"
          content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description"
          content="">
    <meta name="author"
          content="">
    <link rel="icon"
          href="../../favicon.ico">

    <title>Login</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style>
        /*@import "bourbon";*/

        body {
            background: #eee !important;
        }

        .wrapper {
            margin-top: 80px;
            margin-bottom: 80px;
        }

        .form-signin {
            max-width: 380px;
            padding: 15px 35px 45px;
            margin: 0 auto;
            background-color: #fff;
            border: 1px solid rgba(0, 0, 0, 0.1);

        .form-signin-heading,
        .checkbox {
            margin-bottom: 30px;
        }

        .checkbox {
            font-weight: normal;
        }

        .form-control {
            position: relative;
            font-size: 16px;
            height: auto;
            padding: 10px;
        @include box-sizing(border-box);

        &
        :focus {
            z-index: 2;
        }

        }
        input[type="text"] {
            margin-bottom: -1px;
            border-bottom-left-radius: 0;
            border-bottom-right-radius: 0;
        }

        input[type="password"] {
            margin-bottom: 20px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
    <jsp:include page = "include.jsp"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <%--<jsp:include page="include.jsp"/>--%>
</head>

<body>
<div class="wrapper">
    <form class="form-signin"
          action="/login"
          method="post">

        <h2 class="form-signin-heading">Login</h2>
        <c:if test="${error != null}">
            <p>${error}</p>
        </c:if>
        <c:if test="${msg != null}">
            <p>${msg}</p>
        </c:if>
        <input type="text"
               class="form-control"
               name="email"
               placeholder="Email"
               required="true"
               autofocus="true"/>
        <br>
        <input type="password"
               class="form-control"
               name="password"
               placeholder="password"
               required="true"/>
        <br>
        <label>
            <input id="remember-me-submit"
                   type="checkbox"
                   name="remember-me"/>Remember me
        </label>
        <br>
        <br>
        <button class="btn btn-lg btn-primary btn-block"
                type="submit">Login
        </button>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <br>
        <a href="/signup">Sign up</a>
    </form>

</div>

</body>
</html>