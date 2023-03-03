const toolbarData = [
    {
        id: "add",
        type: "button",
        circle: true,
        value: "Add a new row",
        size: "small",
        icon: "mdi mdi-plus",
        full: true
    },
    {
        type: "spacer"
    }
];

const editFormConfig = {
    padding: 0,
    rows: [
        {
            type: "input",
            name: "title",
            label: "Title"
        },
        {
            type: "input",
            name: "description",
            label: "Description"
        },
        {
            align: "end",
            cols: [
                {
                    id: "apply-button",
                    type: "button",
                    text: "Apply",
                    icon: "mdi mdi-check",
                    circle: true,
                }
            ]
        }

    ]
}

const grid = new dhx.Grid(null, {
    css: "dhx_demo-grid",
    autoWidth: true,
    columns: [
        {id: "id", header: [{text: "Id"}]},
        {id: "title", header: [{text: "Title"}]},
        {id: "description", header: [{text: "Description"}]},
        {id: "createdAt", header: [{text: "Created at"}]},
        {
            id: "delete", header: [{text: "Actions", align: "center"}],
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

// Layout initialization
const layout = new dhx.Layout("layout", {
    cols: [
        {
            rows: [
                {
                    id: "toolbar",
                    height: "content"
                },
                {
                    type: "space",
                    rows: [
                        {
                            id: "grid"
                        }
                    ]
                }
            ]
        }
    ]
});

// Toolbar initialization
const toolbar = new dhx.Toolbar(null, {
    css: "toolbar_template_a"
});
// loading structure into Toolbar
toolbar.data.parse(toolbarData);
// assign the handler to the Click event of the button with the id="add"
// pressing the Add button will add a new item to the grid and open the form for editing this item
toolbar.events.on("click", function (id) {
    if (id === "add") {
        openEditor();
    }
});

// initializing Widnow for the editing form
const editWindow = new dhx.Window({
    width: 440,
    height: 350,
    modal: true
});

// initializing Form for the editing form
const editForm = new dhx.Form(null, editFormConfig);
// assign a handler to the Click event of the button with the id="apply-button"
// pressing the Apply button will get all data of the form, update data of the edited item, and close the editing form
editForm.getItem("apply-button").events.on("click", function () {
    const newData = editForm.getValue();
    fetch('/k_pac_test_task_war_exploded/kpacs', {
        method: "POST",
        body: JSON.stringify(
            newData
        ),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
        .then((response) => response.json())
        .then((json) => {
            location.href = '/k_pac_test_task_war_exploded/kpacs?successCreateId=' + json.id;
        })
        .catch((error) => console.error(error));

    closeEditor();
});

// initializing the function that opens the editing form for a Grid item returned by its id
// and fills the form fields with the data of the item
function openEditor() {
    editWindow.show();
}

// initializing the function that closes the editing form and clears it
function closeEditor() {
    editForm.clear();
    editWindow.hide();
}

// attaching widgets to Layout cells
layout.getCell("toolbar").attach(toolbar);
layout.getCell("grid").attach(grid);

// attaching Form to Window
editWindow.attach(editForm);