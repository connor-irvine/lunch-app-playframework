package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

class WelcomeController @Inject()(greeter: GreeterService) extends Controller {

  def welcome() = Action {
    val greeting = greeter.greeting
    Ok(views.html.welcome(greeting))
  }
}