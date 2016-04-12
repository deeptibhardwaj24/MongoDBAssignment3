
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._



/**
  * Created by knoldus on 11/4/16.
  */
@RunWith(classOf[JUnitRunner])
class EmployeeControllerSpec extends Specification {
  "rendering add form" in new WithApplication() {
    val res = route(FakeRequest(GET, "/")).get
    status(res) must equalTo(200)
    contentType(res) must beSome.which(_ == "text/html")
  }

  "rendering login form" in new WithApplication() {
    val res = route(FakeRequest(GET, "/login")).get
    status(res) must equalTo(200)
    contentType(res) must beSome.which(_ == "text/html")
  }

  "valid login form" in new WithApplication() {
    val res = route(FakeRequest(POST, "/validate").withFormUrlEncodedBody("name" -> "deepti", "password" -> "deepti123").withSession("id"->"1")).get
    status(res) must equalTo(200)
    contentAsString(res) must contain("Employee successfully logged in!! :)")
  }

  "missing password" in new WithApplication() {
    val res = route(FakeRequest(POST, "/validate").withFormUrlEncodedBody("name" -> "deepti", "password" -> "").withFlash("error" -> "incorrect name or paswsword").withSession("id"->"1")).get
    println(res)
    status(res) must equalTo(400)

  }
  "missing name" in new WithApplication() {
    val res = route(FakeRequest(POST, "/validate").withFormUrlEncodedBody("name" -> "", "password" -> "deepti123").withFlash("error" -> "incorrect name or paswsword").withSession("id"->"1")).get
    status(res) must equalTo(400)

  }
  "bad login form " in new WithApplication() {
    val res = route(FakeRequest(POST, "/validate").withFormUrlEncodedBody("name" -> "", "password" -> "").withFlash("error" -> "incorrect name or paswsword").withSession("id"->"1")).get
    status(res) must equalTo(400)
  }

    "valid register user" in new WithApplication() {
      val res = route(FakeRequest(POST, "/register").withFormUrlEncodedBody("name" -> "amit", "password" -> "amit123", "address" -> "delhi", "dob" -> "24/06/1992", "joiningDate" -> "21/1/2009", "designation" -> "trainee").withFlash("error" -> "incorrect name or paswsword")).get
      status(res) must equalTo(200)
      contentAsString(res) must contain("Employee successfully added!!!")

    }

    "bad register form" in new WithApplication() {
      val res = route(FakeRequest(POST, "/register").withFormUrlEncodedBody("name" -> "", "password" -> "", "address" -> "", "dob" -> "24/06/1992", "joiningDate" -> "21/1/2009", "designation" -> "trainee").withFlash("error" -> "invalid form!!")).get
      status(res) must equalTo(303)

    }
  }












