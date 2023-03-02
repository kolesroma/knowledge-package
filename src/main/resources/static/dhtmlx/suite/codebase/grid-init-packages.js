const grid = new dhx.Grid("grid", {
    columns: [
        {width: 100, id: "id", header: [{text: "Id"}]},
        {width: 350, id: "title", header: [{text: "Title"}]},
        {width: 348, id: "description", header: [{text: "Description"}]},
        {width: 300, id: "createdAt", header: [{text: "Created at"}]},
        {
            width: 120, id: "delete", header: [{text: "Actions", align: "center"}],
            htmlEnable: true, align: "center",
            template: function () {
                return "<span class='action-buttons'><a class='remove-button'>Delete</a></span>"
            }
        }
    ],
    eventHandlers: {
        onclick: {
            "remove-button": function (e, data) {
                const url = '/k_pac_test_task_war_exploded/kpacs/' + data.row.id + '/delete';
                let form = document.createElement('form');
                form.setAttribute('action', url);
                form.setAttribute('method', 'post');
                document.body.appendChild(form);
                form.submit();
            }
        }
    }
});