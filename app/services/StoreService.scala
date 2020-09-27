package services

import javax.inject.Inject
import models.StoreItem.StoreItem
import repositories.StoreRepository
import scalikejdbc.DB
import scalikejdbc.config.DBs

import scala.concurrent.{ExecutionContext, Future}

class StoreService @Inject()(storeRepository: StoreRepository)(implicit ec: ExecutionContext) {

  def get(): List[StoreItem] = {
    DBs.setupAll()

    storeRepository.get()
  }

  def getWithFilter(query: String): List[StoreItem] = {
    DBs.setupAll()

    storeRepository.getWithFilter(PostgreSqlAdapterService.process(query))
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
