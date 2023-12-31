import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Customer entity.
 */
class CustomerGatlingTest extends Simulation {

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

    val scn = scenario("Test the Customer entity")
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
            exec(http("Get all customers")
            .get("/api/customers")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new customer")
            .post("/api/customers")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "billerId":"SAMPLE_TEXT"
                , "paymentItemCode":"SAMPLE_TEXT"
                , "dtDTransactionId":"SAMPLE_TEXT"
                , "amolDTransactionId":"SAMPLE_TEXT"
                , "transactionReferenceNumber":"SAMPLE_TEXT"
                , "token":"SAMPLE_TEXT"
                , "transferId":"SAMPLE_TEXT"
                , "requestId":"0"
                , "accountName":"SAMPLE_TEXT"
                , "returnCode":"SAMPLE_TEXT"
                , "returnMessage":"SAMPLE_TEXT"
                , "externalTXNid":"SAMPLE_TEXT"
                , "transactionDate":"2020-01-01T00:00:00.000Z"
                , "customerId":"SAMPLE_TEXT"
                , "customerProduct":"SAMPLE_TEXT"
                , "customerType":"SAMPLE_TEXT"
                , "accountCategory":"SAMPLE_TEXT"
                , "accountType":"SAMPLE_TEXT"
                , "accountNumber":"SAMPLE_TEXT"
                , "phoneNumber":"SAMPLE_TEXT"
                , "channel":"SAMPLE_TEXT"
                , "tranFreeField1":"SAMPLE_TEXT"
                , "tranFreeField2":"SAMPLE_TEXT"
                , "tranFreeField3":"SAMPLE_TEXT"
                , "tranFreeField4":"SAMPLE_TEXT"
                , "tranFreeField5":"SAMPLE_TEXT"
                , "tranFreeField6":"SAMPLE_TEXT"
                , "tranFreeField7":"SAMPLE_TEXT"
                , "tranFreeField8":"SAMPLE_TEXT"
                , "tranFreeField9":"SAMPLE_TEXT"
                , "tranFreeField10":"SAMPLE_TEXT"
                , "tranFreeField11":"SAMPLE_TEXT"
                , "tranFreeField12":"SAMPLE_TEXT"
                , "tranFreeField13":"SAMPLE_TEXT"
                , "tranFreeField14":"SAMPLE_TEXT"
                , "tranFreeField15":"SAMPLE_TEXT"
                , "tranFreeField16":"SAMPLE_TEXT"
                , "tranFreeField17":"SAMPLE_TEXT"
                , "tranFreeField18":"0"
                , "tranFreeField19":"0"
                , "tranFreeField20":null
                , "tranFreeField21":"SAMPLE_TEXT"
                , "tranFreeField22":null
                , "tranFreeField23":null
                , "tranFreeField24":"2020-01-01T00:00:00.000Z"
                , "tranFreeField25":"SAMPLE_TEXT"
                , "tranFreeField26":"SAMPLE_TEXT"
                , "tranFreeField27":"SAMPLE_TEXT"
                , "tranFreeField28":"SAMPLE_TEXT"
                , "tranFreeField29":"SAMPLE_TEXT"
                , "tranFreeField30":"SAMPLE_TEXT"
                , "tranFreeField31":"SAMPLE_TEXT"
                , "tranFreeField32":"SAMPLE_TEXT"
                , "tranFreeField33":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_customer_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created customer")
                .get("${new_customer_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created customer")
            .delete("${new_customer_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
