package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}
import services.GreetingService
import java.util.Calendar


class WelcomeControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "WelcomeController GET" should {
    "return a successful response" in {
      val controller = new WelcomeController(FakeMorningGreeter)
      val result = controller.welcome.apply(FakeRequest())
      status(result) mustBe OK
    }
  }
  "respond  to the /welcome url" in {
    val request = FakeRequest(GET, "/welcome").withHeaders("Host" -> "localhost")
    val home = route(app, request).get
    status(home) mustBe OK
  }

  "return some html" in {
    val controller = new WelcomeController(FakeMorningGreeter)
    val result = controller.welcome.apply(FakeRequest())
    contentType(result) mustBe Some("text/html")
  }

  "say good morning and have a title" in {
    val controller = new WelcomeController(FakeMorningGreeter)
    val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
    contentAsString(result) must include ("<h1>Good Morning!</h1>")
    contentAsString(result) must include ("<title>Welcome!</title>")
  }

  "say good afternoon when it's the afternoon and have a title" in {
    val controller = new WelcomeController(FakeAfternoonGreeter)
    val result = controller.welcome().apply(FakeRequest(GET, "/foo"))
    contentAsString(result) must not include ("<h1>Good Morning!</h1>")
    contentAsString(result) must include ("<h1>Good Afternoon!</h1>")
    contentAsString(result) must include ("<title>Welcome!</title>")

  }
}
object FakeMorningGreeter extends GreetingService {
  def greeting: String = {
    val currentHour = FakeMorningCalender.get(Calendar.HOUR_OF_DAY)
    if (currentHour < 12)
      "Good Morning!"
    else
      "Good Afternoon!"
  }
}
object FakeAfternoonGreeter extends GreetingService {
  def greeting: String = {
    val currentHour = FakeAfternoonCalender.get(Calendar.HOUR_OF_DAY)
    if (currentHour < 12)
      "Good Morning!"
    else
      "Good Afternoon!"
  }
}

object FakeAfternoonCalender extends Calendar {

  override def get(field: Int): Int = 13

  override def computeFields(): Unit = ???

  override def getMinimum(field: Int): Int = ???

  override def add(field: Int, amount: Int): Unit = ???

  override def getLeastMaximum(field: Int): Int = ???

  override def getGreatestMinimum(field: Int): Int = ???

  override def roll(field: Int, up: Boolean): Unit = ???

  override def getMaximum(field: Int): Int = ???

  override def computeTime(): Unit = ???
}

object FakeMorningCalender extends Calendar {

  override def get(field: Int): Int = 11

  override def computeFields(): Unit = ???

  override def getMinimum(field: Int): Int = ???

  override def add(field: Int, amount: Int): Unit = ???

  override def getLeastMaximum(field: Int): Int = ???

  override def getGreatestMinimum(field: Int): Int = ???

  override def roll(field: Int, up: Boolean): Unit = ???

  override def getMaximum(field: Int): Int = ???

  override def computeTime(): Unit = ???
}