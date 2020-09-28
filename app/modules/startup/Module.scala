package modules.startup

import com.google.inject.AbstractModule

class Module extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[StartUp]).asEagerSingleton()
  }
}
