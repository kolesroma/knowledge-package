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

    <link rel="stylesheet" href="<c:url value="/static/dhtmlx/suite/codebase/index.css?v=8.0.0"/>">
    <link rel="stylesheet" href="<c:url value="/static/dhtmlx/suite/codebase/buttons.css?v=8.0.0"/>">
    <script defer type="text/javascript" src="<c:url value="/static/dhtmlx/suite/codebase/grid-init-subpackages.js"/>"></script>
</head>
<body>
<header class="dhx_sample-header">
    <div class="dhx_sample-header__main">
        <nav class="dhx_sample-header__breadcrumbs">
            <ul class="dhx_sample-header-breadcrumbs">
                <li class="dhx_sample-header-breadcrumbs__item">
                    <a class="add-row">Add row</a>
                </li>
            </ul>
        </nav>
        <h1 class="dhx_sample-header__title">
            <div class="dhx_sample-header__content">
                Initialization with config.data
            </div>
        </h1>
    </div>
</header>
<section class="dhx_sample-container" style="height: 80%">
    <div style="height: 100%; width: 100%" id="grid"></div>
</section>
<script>
    const getJSON = async url => {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }
    getJSON("/k_pac_test_task_war_exploded/set/${containerId}/json")
        .then(data => {
            grid.data.parse(data);
        })
        .catch(error => {
            console.error(error);
        });
</script>
</body>
</html>

<script>
    const addButton = document.querySelector(".add-row");
    addButton.onclick = function () {
        // show form for creating
        alert(1);

    };

</script>