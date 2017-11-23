

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Error!</title>
</head>
<body>
    <p style="color: crimson"><c:out value="${requestScope.errorMessage}"/></p>
    <form action="${pageContext.request.contextPath}/index.jsp">
        <input type="submit" value="Go back"/>
    </form>
</body>
</html>
