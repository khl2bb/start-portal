const $saveButton = document.getElementById('saveButton');

function log(str) {
    console.log(str);
}

$(window).bind('keydown', function (event) {
    if (event.ctrlKey || event.metaKey) {
        switch (String.fromCharCode(event.which).toLowerCase()) {
            case 's':
                event.preventDefault();
                log('save');
                $saveButton.click();
                break;
        }
    }
});

log("hi");