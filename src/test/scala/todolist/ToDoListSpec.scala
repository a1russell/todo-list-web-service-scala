package todolist

import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatra.test.scalatest._
import todolist.models.{TaskData, Task}
import org.scalatest.BeforeAndAfter

class ToDoListSpec extends ScalatraSpec with BeforeAndAfter {
  var toDoList: ToDoList = _

  after {
    removeServlets()
  }

  describe("GET /tasks on ToDoList") {
    it("should return status 200") {
      addToDoListServlet()
      get("/tasks") {
        status should be (200)
      }
    }

    it("should return a list") {
      addTodoListServletWithTask()
      get("/tasks") {
        val json = parse(body)
        json.getClass should be (classOf[JArray])
      }
    }

    it("should return task with value for text") {
      addTodoListServletWithTask()
      get("/tasks") {
        val json = parse(body)
        json \ "text" should be (JString("test"))
      }
    }

    it("should return task with value for done") {
      addTodoListServletWithTask()
      get("/tasks") {
        val json = parse(body)
        json \ "done" should be (JBool(value = false))
      }
    }
  }

  def addTodoListServletWithTask(): Unit = {
    val tasks = List(Task("test", done = false))
    addToDoListServlet(tasks)
  }

  def addToDoListServlet(tasks: List[Task] = TaskData.all): Unit = {
    implicit val _tasks: List[Task] = tasks
    toDoList = new ToDoList
    addServlet(toDoList, "/*")
  }

  def removeServlets() = {
    servletContextHandler.getServletHandler.setServletMappings(Array())
  }
}
