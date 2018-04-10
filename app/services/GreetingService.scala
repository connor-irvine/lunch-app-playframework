package services

import com.google.inject.ImplementedBy

class RealGreetingService extends GreetingService {
  def greeting: String = "Bonjour!"
}

@ImplementedBy(classOf[RealGreetingService])
trait GreetingService {
  def greeting: String

}
