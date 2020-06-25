const $saveButton = document.getElementById('saveButton');

function log(str) {
  console.log(str);
}

// function checkKey(e) {
//   if (e.key === 's') {
//     $saveButton.click();
//   }
// }
// function save() {
//   log(4);
//   $saveButton.style.backgroundColor = black;
// }

$(window).bind('keydown', function (event) {
  if (event.ctrlKey || event.metaKey) {
    switch (String.fromCharCode(event.which).toLowerCase()) {
      case 's':
        event.preventDefault();
        $saveButton.click();
        break;
    }
  }
});

// window.addEventListener('keypress', checkKey);
// $saveButton.addEventListener('click', save);
