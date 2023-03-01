<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ua">
<head>
    <title>Packages</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Created at</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${packages}" var="pac">
            <tr>
                <td><c:out value="${pac.id}"/></td>
                <td><c:out value="${pac.title}"/></td>
                <td><c:out value="${pac.description}"/></td>
                <td><c:out value="${pac.createdAt}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/kpacs/${pac.id}/delete" method="post">
                        <input type="submit" value="X">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>