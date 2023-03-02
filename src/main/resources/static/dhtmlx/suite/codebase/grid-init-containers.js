const grid = new dhx.Grid("grid", {
    columns: [
        {width: 300, id: "id", header: [{text: "Id"}]},
        {width: 600, id: "title", header: [{text: "Title"}]},
        {
            width: 318, id: "delete", header: [{text: "Actions", align: "center"}],
            htmlEnable: true, align: "center",
            template: function () {
                return "<span class='action-buttons'><a class='remove-button'>Delete</a></span>"
            }
        }
    ],
    eventHandlers: {
        onclick: {
            "remove-button": function (e, data) {
                const url = '/k_pac_test_task_war_exploded/sets/' + data.row.id + '/delete';
                let form = document.createElement('form');
                form.setAttribute('action', url);
                form.setAttribute('method', 'post');
                document.body.appendChild(form);
                form.submit();
            }
        }
    }
});

grid.events.on("cellDblClick", function (row, column, e) {
    location.href = '/k_pac_test_task_war_exploded/set/' + row.id;
});