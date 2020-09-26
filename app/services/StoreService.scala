package services

import models.StoreItem.StoreItem
import repositories.StoreRepository
import repositories.transaction.TransactionExecutor

import scala.concurrent.{ExecutionContext, Future}

class StoreService(
  storeRepository: StoreRepository,
  transactionExecutor: TransactionExecutor
)(implicit ec: ExecutionContext) {
  def get(query: Option[String]): List[StoreItem] = {
    query match {
      case Some(queryString) => storeRepository.getWithFilter(PostgreSqlAdapterService.process(queryString))
      case None => storeRepository.get()
    }
  }

  def create(storeItem: StoreItem): Future[Unit] = {
    transactionExecutor.asyncTx { implicit session =>
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
  }
}
