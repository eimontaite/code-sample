package models

object StoreItem {

  case class StoreItem(id: String, title: String, content: String, views: Int, timestamp: Int)

}