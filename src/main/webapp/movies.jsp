
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Movies Table</title>
    <style type="text/css">
        .pageButton{
            background-color: #d8d3ff;
        }
        .currButton{
            margin-top: inherit;
            background-color: #4f5468;
        }
        .backButton {
            margin-top: 5%;
        }
    </style>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Director</th>
    </tr>

    <c:forEach items="${requestScope.movies}" var="currentMovie">
        <tr>
            <td><c:out value="${currentMovie.id}"/></td>
            <td><c:out value="${currentMovie.title}"/></td>
            <td><c:out value="${currentMovie.genre}"/></td>
            <td><c:out value="${currentMovie.year}"/></td>
            <td><c:out value="${currentMovie.director}"/></td>
        </tr>
    </c:forEach>
</table>


<table>

<tr align="center">
        <c:forEach begin="1" end="${requestScope.numOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <td align="center"> <input class="currButton" type="submit" name="page" value="${i}"></td>
                </c:when>
                <c:otherwise>
                    <td align="center">
                        <form method="get" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="${requestScope.command}">
                            <input class="pageButton" type="submit" name="page" value="${i}">
                        </form>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<div class="backButton">
<form action="${pageContext.request.contextPath}/index.jsp">
    <input type="submit" value="Go back"/>
</form>
</div>
</body>
</html>
