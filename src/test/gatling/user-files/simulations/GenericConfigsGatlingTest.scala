import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the GenericConfigs entity.
 */
class GenericConfigsGatlingTest extends Simulation {

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

    val scn = scenario("Test the GenericConfigs entity")
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
            exec(http("Get all genericConfigs")
            .get("/api/generic-configs")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new genericConfigs")
            .post("/api/generic-configs")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "recordId":"SAMPLE_TEXT"
                , "configId":"SAMPLE_TEXT"
                , "configName":"SAMPLE_TEXT"
                , "configsDetails":"SAMPLE_TEXT"
                , "additionalConfigs":"SAMPLE_TEXT"
                , "freeField":null
                , "freeField1":null
                , "freeField2":"SAMPLE_TEXT"
                , "freeField3":"SAMPLE_TEXT"
                , "freeField4":"SAMPLE_TEXT"
                , "freeField5":"SAMPLE_TEXT"
                , "freeField6":"SAMPLE_TEXT"
                , "freeField8":"SAMPLE_TEXT"
                , "freeField9":"SAMPLE_TEXT"
                , "freeField10":"SAMPLE_TEXT"
                , "freeField11":"SAMPLE_TEXT"
                , "freeField12":"SAMPLE_TEXT"
                , "freeField13":"SAMPLE_TEXT"
                , "freeField14":"SAMPLE_TEXT"
                , "freeField15":null
                , "freeField16":null
                , "freeField17":null
                , "freeField18":null
                , "freeField19":"SAMPLE_TEXT"
                , "freeField20":"SAMPLE_TEXT"
                , "freeField21":"SAMPLE_TEXT"
                , "freeField22":"SAMPLE_TEXT"
                , "freeField23":"SAMPLE_TEXT"
                , "freeField24":"SAMPLE_TEXT"
                , "freeField25":null
                , "freeField26":null
                , "freeField27":null
                , "freeField28":null
                , "freeField29":"SAMPLE_TEXT"
                , "freeField30":"SAMPLE_TEXT"
                , "freeField31":"SAMPLE_TEXT"
                , "freeField32":"SAMPLE_TEXT"
                , "freeField33":"SAMPLE_TEXT"
                , "freeField34":"SAMPLE_TEXT"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "recordStatus":"ACTIVE"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_genericConfigs_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created genericConfigs")
                .get("${new_genericConfigs_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created genericConfigs")
            .delete("${new_genericConfigs_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
