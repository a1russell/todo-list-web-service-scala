package todolist

import todolist.models._

class ToDoList extends ToDoListStack {

  get("/tasks") {
    TaskData.all
  }

  post("/tasks") {
    TaskData.all = parsedBody.extract[Task] :: TaskData.all
  }
}
