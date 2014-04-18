package todolist

import todolist.models._

class ToDoList extends ToDoListStack {

  get("/tasks") {
    TaskData.all
  }

  post("/tasks") {
    TaskData.all = parsedBody.extract[Task] :: TaskData.all
  }

  post("/tasks/update") {
    val updatedTask = parsedBody.extract[Task]
    TaskData.all = TaskData.all.map { task =>
      if (updatedTask.text == task.text) {
        updatedTask
      } else {
        task
      }
    }
  }
}
