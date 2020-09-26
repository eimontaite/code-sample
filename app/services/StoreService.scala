package services

import javax.inject.Inject
import models.StoreItem.StoreItem
import repositories.StoreRepository
import scalikejdbc.DB
import scalikejdbc.config.DBs

import scala.concurrent.{ExecutionContext, Future}

class StoreService @Inject()(storeRepository: StoreRepository)(implicit ec: ExecutionContext) {

  def get(query: Option[String]): List[StoreItem] = {
    query match {
      case Some(queryString) => storeRepository.getWithFilter(PostgreSqlAdapterService.process(queryString))
      case None => storeRepository.get()
    }
  }

  def create(storeItem: StoreItem): Future[Unit] = {
    DBs.setupAll()

    DB localTx { implicit session =>
      storeRepository.updateOrInsert(
        StoreItem(
          id = storeItem.id,
          title = storeItem.title,
          content = storeItem.content,
          views = storeItem.views,
          timestamp = storeItem.timestamp
        )
      )
    }

    Future(DBs.closeAll())
  }
}
