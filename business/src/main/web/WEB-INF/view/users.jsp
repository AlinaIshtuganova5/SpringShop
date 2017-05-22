<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible"
          content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <meta name="description"
          content="">
    <meta name="author"
          content="">

    <title>Users</title>

    <jsp:include page="include.jsp"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <h2>Users</h2>
    <div class="list-group">
        <c:forEach items="${users}" var="user">
            <a href="/admin/users/${user.id}" class="list-group-item"> ${user.email}</a>
        </c:forEach>
    </div>
</div>

<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
