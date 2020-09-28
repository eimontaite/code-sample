package controllers

import javax.inject._
import models.StoreItem.StoreItem
import play.api.mvc._
import play.api.libs.json.Json
import scalikejdbc.config.DBs
import services.StoreService

@Singleton
class StoreController @Inject()(val controllerComponents: ControllerComponents, storeService: StoreService) extends BaseController {

  DBs.setupAll()

  def getStoreItem(): Action[AnyContent] = Action { request =>
    val query = request.queryString.map { case (_, v) =>
      v.mkString
    }.headOption

    val json = {
      query match {
        case Some(queryString) => Ok(Json.toJson(storeService.getWithFilter(queryString)))
        case None => Ok(Json.toJson(storeService.get()))
      }
    }
    json
  }

  def saveStoreItem: Action[AnyContent] = Action { request =>
    val json = request.body.asJson.get
    val storeItem = json.as[StoreItem]
    storeService.create(storeItem)
    Ok
  }

  DBs.closeAll()
}
