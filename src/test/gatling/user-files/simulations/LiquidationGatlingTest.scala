import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Liquidation entity.
 */
class LiquidationGatlingTest extends Simulation {

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

    val scn = scenario("Test the Liquidation entity")
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
            exec(http("Get all liquidations")
            .get("/api/liquidations")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new liquidation")
            .post("/api/liquidations")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "recordId":"SAMPLE_TEXT"
                , "serviceLevel":"N"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "partnerCode":"SAMPLE_TEXT"
                , "amount":"SAMPLE_TEXT"
                , "currency":"SAMPLE_TEXT"
                , "receiverBankcode":"SAMPLE_TEXT"
                , "receiverAccountNumber":"SAMPLE_TEXT"
                , "beneficiaryName":"SAMPLE_TEXT"
                , "instructionId":"SAMPLE_TEXT"
                , "senderToReceiverInfo":"SAMPLE_TEXT"
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
                , "freeText20":"SAMPLE_TEXT"
                , "freeText21":"SAMPLE_TEXT"
                , "freeText22":"SAMPLE_TEXT"
                , "freeText23":"SAMPLE_TEXT"
                , "freeText24":"SAMPLE_TEXT"
                , "freeText25":"SAMPLE_TEXT"
                , "freeText26":"SAMPLE_TEXT"
                , "freeText27":"SAMPLE_TEXT"
                , "freeText28":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_liquidation_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created liquidation")
                .get("${new_liquidation_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created liquidation")
            .delete("${new_liquidation_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
