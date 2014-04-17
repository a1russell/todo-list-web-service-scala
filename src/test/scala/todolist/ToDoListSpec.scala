package todolist

import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatra.test.scalatest._
import todolist.models.{TaskData, Task}
import org.scalatest.BeforeAndAfter

class ToDoListSpec extends ScalatraSpec with BeforeAndAfter {
  addServlet(classOf[ToDoList], "/*")

  val originalTaskData: List[Task] = TaskData.all

  after {
    TaskData.all = originalTaskData
  }

  describe("GET /tasks on ToDoList") {
    it("should return status 200") {
      get("/tasks") {
        status should be (200)
      }
    }

    it("should return a list") {
      setTestTaskData()
      get("/tasks") {
        val json = parse(body)
        json.getClass should be (classOf[JArray])
      }
    }

    it("should return task with value for text") {
      setTestTaskData()
      get("/tasks") {
        val json = parse(body)
        json \ "text" should be (JString("test"))
      }
    }

    it("should return task with value for done") {
      setTestTaskData()
      get("/tasks") {
        val json = parse(body)
        json \ "done" should be (JBool(value = false))
      }
    }
  }

  def setTestTaskData(): Unit = {
    TaskData.all = List(Task("test", done = false))
  }
}
