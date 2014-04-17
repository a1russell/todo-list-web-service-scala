package todolist

import todolist.models._

class ToDoList(implicit val tasks: List[Task]) extends ToDoListStack {

  get("/tasks") {
    tasks
  }
}
