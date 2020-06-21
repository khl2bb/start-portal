const $saveButton = document.getElementById('saveButton');

function log(str) {
    console.log(str);
}
function checkKey(e) {
    if (e.key === 's') {
        $saveButton.click();
    }
}
function save() {
    log(4);
    $saveButton.style.backgroundColor = black;
}

window.addEventListener('keypress', checkKey);
$saveButton.addEventListener('click', save);
