<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include.jsp"/>
    <title>Ok</title>
</head>
<body>
<div style="padding-left: 35%">
    <c:if test="${message != null}"><h1>${message}</h1></c:if>
    <img src="https://i.t3hwin.com/2016/10/One-happy-owl.jpg" class="img-responsive" alt="Cinque Terre" width="304"
         height="236">
</div>

</body>
</html>
