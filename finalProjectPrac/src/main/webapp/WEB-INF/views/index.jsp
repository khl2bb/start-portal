<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8" />
    <title>Todos</title>
</head>
<body>
<div class="container">
    <header>
        <h1>Todo List index.jsp</h1>

        <form class="new-task" id="input-form">
            <input
                    type="text"
                    name="text"
                    placeholder="할일을 입력하세요"
                    id="input"
            />
        </form>
    </header>
    <div id="result"></div>
    <!-- <ul>
  <li>
    <button class="delete">×</button>
    <input type="checkbox" class="toggle-checked">
    <span class="text">JS공부하기</span>
  </li>
</ul> -->
</div>
<script src="index.js"></script>
<script src="todo-model.js"></script>
<script src="html-render.js"></script>
<script src="form-input.js"></script>
<script src="data-manager.js"></script>

</body>
</html>
