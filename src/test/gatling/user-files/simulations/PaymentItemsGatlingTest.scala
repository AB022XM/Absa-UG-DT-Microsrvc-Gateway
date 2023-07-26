import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PaymentItems entity.
 */
class PaymentItemsGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:9050"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the PaymentItems entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all paymentItems")
            .get("/api/payment-items")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new paymentItems")
            .post("/api/payment-items")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "recordId":"SAMPLE_TEXT"
                , "productCategoryId":"0"
                , "billerId":"0"
                , "paymentItemCode":"SAMPLE_TEXT"
                , "paymentItemId":"0"
                , "paymentItemName":"SAMPLE_TEXT"
                , "paymentItemDescription":"SAMPLE_TEXT"
                , "isActive":null
                , "hasFixedPrice":null
                , "hasVariablePrice":null
                , "hasDiscount":null
                , "hasTax":null
                , "amount":"0"
                , "chargeAmount":"0"
                , "hasChargeAmount":null
                , "taxAmount":"0"
                , "freeText":"SAMPLE_TEXT"
                , "freeText1":"SAMPLE_TEXT"
                , "freeText2":"SAMPLE_TEXT"
                , "freeText3":"SAMPLE_TEXT"
                , "freeText4":"SAMPLE_TEXT"
                , "freeText5":"SAMPLE_TEXT"
                , "freeText6":"SAMPLE_TEXT"
                , "freeText7":"SAMPLE_TEXT"
                , "freeText8":"SAMPLE_TEXT"
                , "freeText9":"SAMPLE_TEXT"
                , "freeText10":"SAMPLE_TEXT"
                , "freeText11":"SAMPLE_TEXT"
                , "freeText12":"SAMPLE_TEXT"
                , "freeText13":"SAMPLE_TEXT"
                , "freeText14":"SAMPLE_TEXT"
                , "freeText15":"SAMPLE_TEXT"
                , "freeText16":"SAMPLE_TEXT"
                , "freeText17":"SAMPLE_TEXT"
                , "freeText18":"SAMPLE_TEXT"
                , "freeText19":"SAMPLE_TEXT"
                , "delflg":null
                , "status":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "deletedAt":"2020-01-01T00:00:00.000Z"
                , "deletedBy":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_paymentItems_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created paymentItems")
                .get("${new_paymentItems_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created paymentItems")
            .delete("${new_paymentItems_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
