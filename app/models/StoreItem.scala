package models

import play.api.libs.json.{JsPath, JsResult, JsSuccess, JsValue, Json, Reads, Writes}
import play.api.libs.functional.syntax._

object StoreItem {

  case class StoreItem(id: String, title: String, content: String, views: Int, timestamp: Int)

  implicit val format = Json.format[StoreItem]
//  implicit val storeItemReads: Reads[StoreItem] = (
//    (JsPath \ "id").read[String] and
//      (JsPath \ "title").read[String] and
//      (JsPath \ "content").read[String] and
//      (JsPath \ "views").read[Int] and
//      (JsPath \ "timestamp").read[Int]
//    )(StoreItem.apply _)
//
//  implicit val storeItemWrites: Writes[StoreItem] = (
//    (JsPath \ "id").write[String] and
//      (JsPath \ "title").write[String] and
//      (JsPath \ "content").write[String] and
//      (JsPath \ "views").write[Int] and
//      (JsPath \ "timestamp").write[Int]
//    )(unlift(StoreItem.unapply))
}