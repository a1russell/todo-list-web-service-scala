package todolist

import org.scalatra.test.scalatest._

// For more on ScalaTest, see http://www.scalatest.org/quick_start
class ToDoListSpec extends ScalatraSpec {
  addServlet(classOf[ToDoList], "/*")

  describe("GET / on ToDoList") {
    it("should return status 200") {
      get("/") {
        status should be (200)
      }
    }
  }
}
