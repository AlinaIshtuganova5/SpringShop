<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="step" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
         language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible"
          content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Add item</title>
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
               action="/admin/item"
               modelAttribute="item">
        <h2 class="form-signin-heading">Add new item</h2>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input required="required"
                            type="text"
                            class="form-control"
                            path="name"
                            placeholder="Name"
                            autofocus="true"/>
                <form:errors path="name"/>
            </div>
        </spring:bind>

        <spring:bind path="Description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input
                            type="textfield"
                            class="form-control"
                            path="description"
                            placeholder="Description"
                            autofocus="false"/>
                <form:errors path="description"/>
            </div>
        </spring:bind>
        <br>
        <spring:bind path="Cost">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input required="required"
                            type="number"
                            step='0.01'
                            min="0.01"
                            class="form-control"
                            path="cost"
                            placeholder="Cost"
                            autofocus="false"/>
                <form:errors path="cost"/>
            </div>
        </spring:bind>

        <form:select name="group_id" path="group">
            <c:forEach items="${group}" var="group">
                <option value="${group}">${group}</option>
            </c:forEach>
        </form:select>
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <br>
        <button class="btn btn-lg btn-primary btn-block"
                type="submit">Add
        </button>
        <br>
    </form:form>


</div>

</body>
</html>