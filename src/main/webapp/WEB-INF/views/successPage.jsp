<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>User Data</title>
</head>
<body>
<h1>User Data</h1>

<c:choose>
    <c:when test="${not empty userTablesData}">
        <c:forEach var="table" items="${userTablesData}">
            <h2>${table.key}</h2>
            <table border="1">
                <thead>
                    <tr>
                        <c:forEach var="column" items="${table.value[0].keySet()}">
                            <th>${column}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${table.value}">
                        <tr>
                            <c:forEach var="value" items="${row.values()}">
                                <td>${value}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>No data found for the current user.</p>
    </c:otherwise>
</c:choose>

</body>
</html>