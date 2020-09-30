package services

import javax.inject.Inject
import models.StoreItem.StoreItem
import repositories.StoreRepository
import scalikejdbc.DB

import scala.concurrent.{ExecutionContext, Future}

class StoreService @Inject()(storeRepository: StoreRepository)(implicit ec: ExecutionContext) {

  def get(query: Option[String]): List[StoreItem] = {
    query match {
      case Some(query) => storeRepository.getWithFilter(PostgreSqlAdapterService.process(query))
      case None => storeRepository.get()
    }
  }

  def create(storeItem: StoreItem): Future[Unit] = {
    DB.futureLocalTx { implicit session =>
      Future(storeRepository.updateOrInsert(
        StoreItem(
          id = storeItem.id,
          title = storeItem.title,
          content = storeItem.content,
          views = storeItem.views,
          timestamp = storeItem.timestamp
        )
      )
      )
    }
  }
}
