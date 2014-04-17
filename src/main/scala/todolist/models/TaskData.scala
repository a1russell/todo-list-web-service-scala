package todolist.models

object TaskData {
  var all = List(
    Task("Buy some cookies.", done = true),
    Task("Eat some cookies.", done = false),
    Task("Tell everyone how much I really like cookies.", done = false)
  )
}
