package todolist

import todolist.models._

class ToDoList extends ToDoListStack {

  get("/tasks") {
    TaskData.all
  }
}
