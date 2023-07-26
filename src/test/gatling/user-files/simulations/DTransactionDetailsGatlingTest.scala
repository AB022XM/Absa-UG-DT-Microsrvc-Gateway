import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the DTransactionDetails entity.
 */
class DTransactionDetailsGatlingTest extends Simulation {

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

    val scn = scenario("Test the DTransactionDetails entity")
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
            exec(http("Get all dTransactionDetails")
            .get("/api/d-transaction-details")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new dTransactionDetails")
            .post("/api/d-transaction-details")
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
                , "productCode":"SAMPLE_TEXT"
                , "paymentChannelCode":"ATM"
                , "debitAccountNumber":"SAMPLE_TEXT"
                , "creditAccountNumber":"SAMPLE_TEXT"
                , "debitAmount":"0"
                , "creditAmount":"0"
                , "transactionAmount":"0"
                , "debitDate":"2020-01-01T00:00:00.000Z"
                , "creditDate":"2020-01-01T00:00:00.000Z"
                , "status":"PENDING"
                , "settlementDate":"2020-01-01T00:00:00.000Z"
                , "debitCurrency":"SAMPLE_TEXT"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "phoneNumber":"SAMPLE_TEXT"
                , "email":"SAMPLE_TEXT"
                , "payerName":"SAMPLE_TEXT"
                , "payerAddress":"SAMPLE_TEXT"
                , "payerEmail":"SAMPLE_TEXT"
                , "payerPhoneNumber":"SAMPLE_TEXT"
                , "payerNarration":"SAMPLE_TEXT"
                , "payerBranchId":"SAMPLE_TEXT"
                , "beneficiaryName":"SAMPLE_TEXT"
                , "beneficiaryAccount":"SAMPLE_TEXT"
                , "beneficiaryBranchId":"SAMPLE_TEXT"
                , "beneficiaryPhoneNumber":"SAMPLE_TEXT"
                , "tranStatus":"PENDING"
                , "narration1":"SAMPLE_TEXT"
                , "narration2":"SAMPLE_TEXT"
                , "narration3":"SAMPLE_TEXT"
                , "narration4":"SAMPLE_TEXT"
                , "narration5":"SAMPLE_TEXT"
                , "narration6":"SAMPLE_TEXT"
                , "narration7":"SAMPLE_TEXT"
                , "narration8":"SAMPLE_TEXT"
                , "narration9":"SAMPLE_TEXT"
                , "narration10":"SAMPLE_TEXT"
                , "narration11":"SAMPLE_TEXT"
                , "narration12":"SAMPLE_TEXT"
                , "modeOfPayment":"CASH"
                , "retries":"0"
                , "posted":null
                , "apiId":"SAMPLE_TEXT"
                , "apiVersion":"SAMPLE_TEXT"
                , "postingApi":"SAMPLE_TEXT"
                , "isPosted":null
                , "postedBy":"SAMPLE_TEXT"
                , "postedDate":"SAMPLE_TEXT"
                , "tranFreeField1":"SAMPLE_TEXT"
                , "tranFreeField3":"SAMPLE_TEXT"
                , "tranFreeField4":"SAMPLE_TEXT"
                , "tranFreeField5":"SAMPLE_TEXT"
                , "tranFreeField6":"SAMPLE_TEXT"
                , "tranFreeField7":"SAMPLE_TEXT"
                , "tranFreeField8":"SAMPLE_TEXT"
                , "tranFreeField9":"SAMPLE_TEXT"
                , "tranFreeField10":"SAMPLE_TEXT"
                , "tranFreeField11":"SAMPLE_TEXT"
                , "tranFreeField12":null
                , "tranFreeField13":null
                , "tranFreeField14":null
                , "tranFreeField15":null
                , "tranFreeField16":"SAMPLE_TEXT"
                , "tranFreeField17":"SAMPLE_TEXT"
                , "tranFreeField18":"SAMPLE_TEXT"
                , "tranFreeField19":"SAMPLE_TEXT"
                , "tranFreeField20":"SAMPLE_TEXT"
                , "tranFreeField21":"SAMPLE_TEXT"
                , "tranFreeField22":"SAMPLE_TEXT"
                , "tranFreeField23":"SAMPLE_TEXT"
                , "tranFreeField24":"SAMPLE_TEXT"
                , "tranFreeField25":"SAMPLE_TEXT"
                , "tranFreeField26":"SAMPLE_TEXT"
                , "tranFreeField27":"SAMPLE_TEXT"
                , "tranFreeField28":"SAMPLE_TEXT"
                , "internalErrorCode":"SAMPLE_TEXT"
                , "externalErrorCode":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_dTransactionDetails_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created dTransactionDetails")
                .get("${new_dTransactionDetails_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created dTransactionDetails")
            .delete("${new_dTransactionDetails_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
