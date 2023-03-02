<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ua">
<head>
    <!-- meta block -->
    <title>Initialization with config.data - DHTMLX Grid</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">

    <link rel="shortcut icon" href="static/dhtmlx/samples/common/favicon/favicon.ico" type="image/x-icon"/>
    <link rel="icon" href="static/dhtmlx/samples/common/favicon/icon-16.png" sizes="16x16"/>
    <link rel="icon" href="static/dhtmlx/samples/common/favicon/icon-32.png" sizes="32x32"/>
    <link rel="icon" href="static/dhtmlx/samples/common/favicon/icon-48.png" sizes="48x48"/>
    <link rel="icon" href="static/dhtmlx/samples/common/favicon/icon-96.png" sizes="96x96"/>
    <link rel="icon" href="static/dhtmlx/samples/common/favicon/icon-144.png" sizes="144x144"/>
    <!-- end meta block -->
    <script type="text/javascript" src="static/dhtmlx/codebase/grid.js?v=8.0.0"></script>
    <link rel="stylesheet" href="static/dhtmlx/codebase/grid.css?v=8.0.0">

    <link rel="stylesheet" href="static/dhtmlx/samples/common/index.css?v=8.0.0">
    <!-- custom sample head -->
    <script src="static/dhtmlx/samples/common/data.js?v=8.0.0"></script>
    <style>
        body {
            margin: 0;
        }

        .action-buttons {
            display: flex;
            justify-content: space-evenly;
            width: 100%;
        }

        .remove-button {
            cursor: pointer;
            color: red;
        }

        .add-row {
            cursor: pointer;
            color: blue;
        }
    </style>
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
    const grid = new dhx.Grid("grid", {
        columns: [
            {width: 100, id: "id", header: [{text: "Id"}]},
            {width: 350, id: "title", header: [{text: "Title"}]},
            {width: 348, id: "description", header: [{text: "Description"}]},
            {width: 300, id: "createdAt", header: [{text: "Created at"}]},
            {
                id: "delete", width: 120, header: [{text: "Actions", align: "center"}],
                htmlEnable: true, align: "center",
                template: function () {
                    return "<span class='action-buttons'><a class='remove-button'>Delete</a></span>"
                }
            }
        ],
        eventHandlers: {
            onclick: {
                "remove-button": function (e, data) {
                    const url = 'kpacs/' + data.row.id + '/delete';
                    let form = document.createElement('form');
                    form.setAttribute('action', url);
                    form.setAttribute('method', 'post');
                    document.body.appendChild(form);
                    form.submit();
                }
            }
        }
    });

    let array = [];
    let item;
    <c:if test="${packages.size() != 0}">
    <c:forEach begin="0" end="${packages.size() - 1}" varStatus="loop">
    item = {
        id: "${packages.get(loop.index).id}",
        title: "${packages.get(loop.index).title}",
        description: "${packages.get(loop.index).description}",
        createdAt: "${packages.get(loop.index).createdAt}",
        delete: "",
    };
    array.push(item);
    </c:forEach>
    grid.data.parse(array);
    </c:if>

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