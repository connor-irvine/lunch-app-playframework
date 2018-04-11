package guice

import com.google.inject.AbstractModule
import java.util.Calendar


class Module extends AbstractModule{
  override def configure() = {
    bind(classOf[Calendar]).toInstance(Calendar.getInstance())

  }

}
