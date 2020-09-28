package modules.startup

import javax.inject.Inject
import play.api.inject.ApplicationLifecycle
import scalikejdbc.ConnectionPool
import scalikejdbc.config.DBs

import scala.concurrent.Future

class StartUp @Inject()(
  lifecycle: ApplicationLifecycle
) {
  def onStart(): Unit = {
    DBs.setupAll()
  }

  def onStop(): Unit = {
    ConnectionPool.closeAll()
  }

  lifecycle.addStopHook(() => Future.successful(onStop()))
  onStart()
}
