package models

import play.api.libs.json.{JsPath, JsResult, JsSuccess, JsValue, Reads}
import play.api.libs.functional.syntax._

object StoreItem {

  case class StoreItem(id: String, title: String, content: String, views: Int, timestamp: Int)

  implicit val storeItemReads: Reads[StoreItem] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "title").read[String] and
      (JsPath \ "content").read[String] and
      (JsPath \ "views").read[Int] and
      (JsPath \ "timestamp").read[Int]
    )(StoreItem.apply _)
}