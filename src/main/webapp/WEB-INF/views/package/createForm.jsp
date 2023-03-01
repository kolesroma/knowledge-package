<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create package</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/kpacs/" method="post" modelAttribute="packageForm">
    <label>Title<form:input type="text" path="title"/></label>
    <label>Description<form:input type="text" path="description"/></label>
    <label>Created at<form:input type="text" path="createdAt"/></label>
    <input type="submit" value="create">
</form:form>
</body>
</html>