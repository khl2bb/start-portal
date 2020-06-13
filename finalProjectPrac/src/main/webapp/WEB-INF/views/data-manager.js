function saveData(data) {
  // eslint-disable-next-line no-console
  console.log('todos:', data);
  localStorage.setItem('data', JSON.stringify(data));
}

function loadData() {
  const dataString = localStorage.getItem('data') || '[]';
  return JSON.parse(dataString);
}

export { saveData, loadData };
