import todolist._
import org.scalatra._
import javax.servlet.ServletContext
import todolist.models.{TaskData, Task}

class ScalatraBootstrap extends LifeCycle {

  implicit val tasks: List[Task] = TaskData.all

  override def init(context: ServletContext) {
    context.mount(new ToDoList, "/*")
  }
}
