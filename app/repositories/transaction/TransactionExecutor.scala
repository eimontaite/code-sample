package repositories.transaction

import scalikejdbc.{DB, DBSession}

import scala.concurrent.{ExecutionContext, Future}

trait TransactionExecutor {
  def asyncTx[A](f: DBSession => A)(implicit executionContext: ExecutionContext): Future[A]
}

trait ScalikeTransactionExecutor extends TransactionExecutor {
  override def asyncTx[A](f: DBSession => A)(implicit executionContext: ExecutionContext): Future[A] =
    Future(DB.localTx(f))
}
object ScalikeTransactionExecutor extends ScalikeTransactionExecutor

