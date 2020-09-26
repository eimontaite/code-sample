package controllers

import javax.inject._
import models.StoreItem.StoreItem
import play.api.mvc._
import play.api.libs.json.Json
import services.StoreService

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, storeService: StoreService) extends BaseController {

  def saveStoreItem: Action[AnyContent] = Action { request =>
    val json = request.body.asJson.get
    val storeItem = json.as[StoreItem]
    storeService.create(storeItem)
    Ok
  }
}
