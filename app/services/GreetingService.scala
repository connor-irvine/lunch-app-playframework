package services

import com.google.inject.ImplementedBy
import java.util.Calendar

import javax.inject.Inject

class RealGreetingService @Inject()(calendar: Calendar) extends GreetingService {
  def greeting: String = {
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    if (currentHour < 12)
      "Good Morning!"
    else
      "Good Afternoon!"
  }
}

@ImplementedBy(classOf[RealGreetingService])
trait GreetingService {
  def greeting: String

}
