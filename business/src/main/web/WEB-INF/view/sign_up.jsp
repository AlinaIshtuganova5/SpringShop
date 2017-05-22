<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8"
         language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible"
          content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Sign up</title>
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
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <jsp:include page="include.jsp"/>
</head>

<body>
<div class="wrapper">
    <form:form class="form-signin"
               method="post"
               action="/signup"
               modelAttribute="user">
        <h2 class="form-signin-heading">Sign up</h2>

        <spring:bind path="fio">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input required="required"
                            type="text"
                            class="form-control"
                            path="fio"
                            placeholder="FIO"
                            autofocus="true"/>
                <form:errors path="fio"/>
            </div>
        </spring:bind>

        <spring:bind path="address">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input required="required"
                            type="text"
                            class="form-control"
                            path="address"
                            placeholder="Address"
                            autofocus="true"/>
                <form:errors path="address"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input required="required"
                            type="email"
                            class="form-control"
                            path="email"
                            placeholder="Email"
                            autofocus="true"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password"
                            required="required"
                            class="form-control"
                            path="password"
                            placeholder="Password"/>
                <form:errors path="password"/>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password"
                            required="required"
                            class="form-control"
                            path="confirmPassword"
                            placeholder="Confirm password"/>
                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block"
                type="submit">Sign up
        </button>
        <br>
        <a href="/login">Login</a>
    </form:form>


</div>

</body>
</html>