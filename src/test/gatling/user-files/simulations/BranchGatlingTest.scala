import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Branch entity.
 */
class BranchGatlingTest extends Simulation {

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

    val scn = scenario("Test the Branch entity")
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
            exec(http("Get all branches")
            .get("/api/branches")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new branch")
            .post("/api/branches")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "recordId":"SAMPLE_TEXT"
                , "addressId":"SAMPLE_TEXT"
                , "bankId":"SAMPLE_TEXT"
                , "branchId":"SAMPLE_TEXT"
                , "branchName":"SAMPLE_TEXT"
                , "branchSwiftCode":"SAMPLE_TEXT"
                , "branchPhoneNumber":"SAMPLE_TEXT"
                , "branchEmail":"SAMPLE_TEXT"
                , "branchFreeField1":"SAMPLE_TEXT"
                , "branchFreeField3":"SAMPLE_TEXT"
                , "branchFreeField4":"SAMPLE_TEXT"
                , "branchFreeField5":"SAMPLE_TEXT"
                , "branchFreeField6":"SAMPLE_TEXT"
                , "branchFreeField7":"SAMPLE_TEXT"
                , "branchFreeField8":"SAMPLE_TEXT"
                , "branchFreeField9":"SAMPLE_TEXT"
                , "branchFreeField10":"SAMPLE_TEXT"
                , "branchFreeField11":"SAMPLE_TEXT"
                , "branchFreeField12":"SAMPLE_TEXT"
                , "branchFreeField13":"SAMPLE_TEXT"
                , "branchFreeField14":"SAMPLE_TEXT"
                , "branchFreeField15":"SAMPLE_TEXT"
                , "branchFreeField16":"SAMPLE_TEXT"
                , "branchFreeField17":"SAMPLE_TEXT"
                , "branchFreeField18":"SAMPLE_TEXT"
                , "branchFreeField19":"SAMPLE_TEXT"
                , "branchFreeField20":"SAMPLE_TEXT"
                , "branchFreeField21":"SAMPLE_TEXT"
                , "branchFreeField22":"SAMPLE_TEXT"
                , "branchFreeField23":"SAMPLE_TEXT"
                , "branchFreeField24":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "status":"ACTIVE"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_branch_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created branch")
                .get("${new_branch_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created branch")
            .delete("${new_branch_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
