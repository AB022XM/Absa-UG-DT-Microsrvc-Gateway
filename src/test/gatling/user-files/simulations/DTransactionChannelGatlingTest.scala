import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the DTransactionChannel entity.
 */
class DTransactionChannelGatlingTest extends Simulation {

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

    val scn = scenario("Test the DTransactionChannel entity")
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
            exec(http("Get all dTransactionChannels")
            .get("/api/d-transaction-channels")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new dTransactionChannel")
            .post("/api/d-transaction-channels")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "billerId":"SAMPLE_TEXT"
                , "paymentItemCode":"SAMPLE_TEXT"
                , "dtDTransactionId":"SAMPLE_TEXT"
                , "transactionReferenceNumber":"SAMPLE_TEXT"
                , "externalTranid":"SAMPLE_TEXT"
                , "channelCode":"SAMPLE_TEXT"
                , "channelName":"SAMPLE_TEXT"
                , "channelDescription":"SAMPLE_TEXT"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "chanelFreeText":"SAMPLE_TEXT"
                , "chanelFreeText1":"SAMPLE_TEXT"
                , "chanelFreeText2":"SAMPLE_TEXT"
                , "chanelFreeText3":"SAMPLE_TEXT"
                , "chanelFreeText4":"SAMPLE_TEXT"
                , "chanelFreeText5":"SAMPLE_TEXT"
                , "chanelFreeText6":"SAMPLE_TEXT"
                , "chanelFreeText7":"SAMPLE_TEXT"
                , "chanelFreeText8":"SAMPLE_TEXT"
                , "chanelFreeText9":"SAMPLE_TEXT"
                , "chanelFreeText10":"SAMPLE_TEXT"
                , "chanelFreeText11":"SAMPLE_TEXT"
                , "chanelFreeText12":"SAMPLE_TEXT"
                , "chanelFreeText13":"SAMPLE_TEXT"
                , "chanelFreeText14":"SAMPLE_TEXT"
                , "chanelFreeText15":"SAMPLE_TEXT"
                , "chanelFreeText16":"SAMPLE_TEXT"
                , "chanelFreeText17":"SAMPLE_TEXT"
                , "chanelFreeText18":"SAMPLE_TEXT"
                , "chanelFreeText19":"SAMPLE_TEXT"
                , "chanelFreeText20":"SAMPLE_TEXT"
                , "chanelFreeText21":"SAMPLE_TEXT"
                , "chanelFreeText22":"SAMPLE_TEXT"
                , "chanelFreeText23":"SAMPLE_TEXT"
                , "chanelFreeText24":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                , "delflg":null
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_dTransactionChannel_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created dTransactionChannel")
                .get("${new_dTransactionChannel_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created dTransactionChannel")
            .delete("${new_dTransactionChannel_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
