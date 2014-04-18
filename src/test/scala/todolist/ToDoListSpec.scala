package todolist

import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatra.test.scalatest._
import todolist.models.{TaskData, Task}
import org.scalatest.BeforeAndAfter

class ToDoListSpec extends ScalatraSpec with BeforeAndAfter {

  addServlet(classOf[ToDoList], "/*")

  protected implicit val jsonFormats: Formats = DefaultFormats

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

  describe("POST /tasks on ToDoList") {
    it("should return status 200") {
      post("/tasks", testTaskJson, jsonContentType) {
        status should be(200)
      }
    }

    it("should save posted data") {
      clearTaskData()
      post("/tasks", testTaskJson, jsonContentType) {
        TaskData.all should contain (testTask)
      }
    }
  }

  describe("POST /tasks/update on ToDoList") {
    it("should return status 200") {
      post("/tasks/update", testDoneTaskJson, jsonContentType) {
        status should be(200)
      }
    }

    it("should save posted data") {
      setTestTaskData()
      post("/tasks/update", testDoneTaskJson, jsonContentType) {
        TaskData.all should contain (testDoneTask)
      }
    }

    it("should not append posted data") {
      setTestTaskData()
      post("/tasks/update", testDoneTaskJson, jsonContentType) {
        TaskData.all should not contain testTask
      }
    }

    it("should have no effect if task does not exist") {
      clearTaskData()
      post("/tasks/update", testDoneTaskJson, jsonContentType) {
        TaskData.all should not contain testDoneTask
      }
    }
  }

  val jsonContentType = Map("Content-Type" -> "application/json")
  val testTaskJson = """{ "text": "test", "done": false }"""
  val testTask = parse(testTaskJson).extract[Task]
  val testDoneTaskJson = """{ "text": "test", "done": true }"""
  val testDoneTask = parse(testDoneTaskJson).extract[Task]

  def setTestTaskData(): Unit = {
    TaskData.all = List(testTask)
  }

  def clearTaskData(): Unit = {
    TaskData.all = List()
  }
}
