<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <jsp:include page = "include.jsp"/>
</head>
<body>
<div style="padding-left: 35%">
    <h1>ERROR</h1>
    <c:if test="${message != null}"><h1>${message}</h1></c:if>
    <img src="https://img.memecdn.com/sad-owl-is-sad_fb_234355.jpg" class="img-responsive" alt="Cinque Terre" width="304"
         height="236">
</div>

</body>
</html>
