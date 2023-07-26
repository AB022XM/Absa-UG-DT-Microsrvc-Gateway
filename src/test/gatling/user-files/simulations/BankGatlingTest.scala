import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Bank entity.
 */
class BankGatlingTest extends Simulation {

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

    val scn = scenario("Test the Bank entity")
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
            exec(http("Get all banks")
            .get("/api/banks")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new bank")
            .post("/api/banks")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "billerId":"SAMPLE_TEXT"
                , "paymentItemCode":"SAMPLE_TEXT"
                , "dtDTransactionId":"SAMPLE_TEXT"
                , "amolDTransactionId":"SAMPLE_TEXT"
                , "bankName":"SAMPLE_TEXT"
                , "bankSwiftCode":"SAMPLE_TEXT"
                , "bankBranchId":"SAMPLE_TEXT"
                , "bankPhoneNumber":"SAMPLE_TEXT"
                , "bankEmail":"SAMPLE_TEXT"
                , "bankFreeField1":"SAMPLE_TEXT"
                , "bankFreeField3":"SAMPLE_TEXT"
                , "bankFreeField4":"SAMPLE_TEXT"
                , "bankFreeField5":"SAMPLE_TEXT"
                , "bankFreeField6":"SAMPLE_TEXT"
                , "bankFreeField7":"SAMPLE_TEXT"
                , "bankFreeField8":"SAMPLE_TEXT"
                , "bankFreeField9":"SAMPLE_TEXT"
                , "bankFreeField10":"SAMPLE_TEXT"
                , "bankFreeField11":"SAMPLE_TEXT"
                , "bankFreeField12":"SAMPLE_TEXT"
                , "bankFreeField13":"SAMPLE_TEXT"
                , "bankFreeField14":"SAMPLE_TEXT"
                , "bankFreeField15":"SAMPLE_TEXT"
                , "bankFreeField16":"SAMPLE_TEXT"
                , "bankFreeField17":"SAMPLE_TEXT"
                , "bankFreeField18":"SAMPLE_TEXT"
                , "bankFreeField19":"SAMPLE_TEXT"
                , "bankFreeField20":"SAMPLE_TEXT"
                , "bankFreeField21":"SAMPLE_TEXT"
                , "bankFreeField22":"SAMPLE_TEXT"
                , "bankFreeField23":"SAMPLE_TEXT"
                , "bankFreeField24":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                , "delflg":null
                , "status":"ACTIVE"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_bank_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created bank")
                .get("${new_bank_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created bank")
            .delete("${new_bank_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
