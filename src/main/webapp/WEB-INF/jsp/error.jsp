

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Error!</title>
</head>
<body>
    <c:out value="${requestScope.errorMessage}"/>
    <form action="${pageContext.request.contextPath}/index.jsp">
        <input type="submit" value="Go back"/>
    </form>
</body>
</html>
