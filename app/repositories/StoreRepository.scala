package repositories

import models.StoreItem.StoreItem
import scalikejdbc._

class StoreRepository {

  def get(query: Option[String])(implicit session: DBSession = ReadOnlyAutoSession): List[StoreItem] = {
    query match {
      case Some(query) =>
        // https://github.com/scalikejdbc/scalikejdbc/issues/320
        // would rather parameterize this, because this is a huge vulnerability
        val filter = SQLSyntax.createUnsafely("WHERE ".concat(query))
        sql"""
         SELECT * FROM store_items $filter
       """
          .map(rsToStoreItem)
          .list()
          .apply()

      case None =>
        sql"""
         SELECT * FROM store_items
       """
          .map(rsToStoreItem)
          .list()
          .apply()
    }
  }

  def updateOrInsert(storeItem: StoreItem)(implicit session: DBSession): Unit = {
    sql"""
         INSERT INTO store_items
         VALUES (
         ${storeItem.id},
         ${storeItem.title},
         ${storeItem.content},
         ${storeItem.views},
         ${storeItem.timestamp}
         ) ON conflict (id) do UPDATE SET
         id = ${storeItem.id},
         title = ${storeItem.title},
         content = ${storeItem.content},
         views = ${storeItem.views},
         timestamp = ${storeItem.timestamp}
       """
      .execute()
      .apply()
  }

  private def rsToStoreItem(rs: WrappedResultSet): StoreItem = {
    StoreItem(
      id = rs.string("id"),
      title = rs.string("title"),
      content = rs.string("content"),
      views = rs.int("views"),
      timestamp = rs.int("timestamp")
    )
  }
}

object StoreRepository extends StoreRepository
