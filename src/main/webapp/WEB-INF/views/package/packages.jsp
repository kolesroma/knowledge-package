<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ua">
<head>
    <title>Packages</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script type="text/javascript" src="<c:url value="/static/dhtmlx/suite/codebase/suite.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/static/dhtmlx/suite/codebase/suite.css"/>">
    <link rel="stylesheet" href="//cdn.materialdesignicons.com/5.4.55/css/materialdesignicons.min.css"/>

    <link rel="stylesheet" href="<c:url value="/static/dhtmlx/suite/codebase/index.css?v=8.0.0"/>">
    <link rel="stylesheet" href="<c:url value="/static/dhtmlx/suite/codebase/buttons.css?v=8.0.0"/>">
    <script defer type="text/javascript" src="<c:url value="/static/dhtmlx/suite/codebase/grid-init-packages.js"/>"></script>
</head>
<body>
<div id="layout" style="height: 100%;"></div>
<script>
    const getJSON = async url => {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }
    getJSON("/k_pac_test_task_war_exploded/kpacs/json")
        .then(data => {
            grid.data.parse(data);
        })
        .catch(error => {
            console.error(error);
        });
</script>
</body>
</html>