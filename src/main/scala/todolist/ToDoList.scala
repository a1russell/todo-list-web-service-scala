package todolist

import todolist.models._

class ToDoList extends ToDoListStack {

  get("/") {
    Message("Hello, world!")
  }
}
