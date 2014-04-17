package todolist

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

trait ToDoListStack extends ScalatraServlet with NativeJsonSupport with CorsSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }
}
