import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the DTransaction entity.
 */
class DTransactionGatlingTest extends Simulation {

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

    val scn = scenario("Test the DTransaction entity")
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
            exec(http("Get all dTransactions")
            .get("/api/d-transactions")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new dTransaction")
            .post("/api/d-transactions")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "billerId":"SAMPLE_TEXT"
                , "paymentItemCode":"SAMPLE_TEXT"
                , "dtDTransactionId":"SAMPLE_TEXT"
                , "amolDTransactionId":"SAMPLE_TEXT"
                , "transactionReferenceNumber":"SAMPLE_TEXT"
                , "externalTranid":"SAMPLE_TEXT"
                , "token":"SAMPLE_TEXT"
                , "transferId":"SAMPLE_TEXT"
                , "productCode":"SAMPLE_TEXT"
                , "paymentChannelCode":"ATM"
                , "accountNumber":"SAMPLE_TEXT"
                , "amount":"0"
                , "debitAmount":"0"
                , "creditAmount":"0"
                , "settlementAmount":"0"
                , "settlementDate":"2020-01-01T00:00:00.000Z"
                , "transactionDate":"2020-01-01T00:00:00.000Z"
                , "isRetried":null
                , "lastRetryDate":"2020-01-01T00:00:00.000Z"
                , "firstRetryDate":"2020-01-01T00:00:00.000Z"
                , "retryResposeCode":"SUCCESS"
                , "retryResponseMessage":"TRANSACTIONSUCCESS"
                , "retryCount":"0"
                , "isRetriableFlg":null
                , "doNotRetryDTransaction":null
                , "status":"PENDING"
                , "statusCode":"SAMPLE_TEXT"
                , "statusDetails":"SAMPLE_TEXT"
                , "retries":"0"
                , "timestamp":"2020-01-01T00:00:00.000Z"
                , "postedBy":"SAMPLE_TEXT"
                , "postedDate":"SAMPLE_TEXT"
                , "internalErrorCode":"SAMPLE_TEXT"
                , "externalErrorCode":"SAMPLE_TEXT"
                , "isCrossCurrency":null
                , "isCrossBank":null
                , "currency":"SAMPLE_TEXT"
                , "creditAccount":"SAMPLE_TEXT"
                , "debitAccount":"SAMPLE_TEXT"
                , "phoneNumber":"SAMPLE_TEXT"
                , "customerNumber":"SAMPLE_TEXT"
                , "tranStatus":"PENDING"
                , "tranStatusDetails":"SAMPLE_TEXT"
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
                , "tranFreeField13":"0"
                , "tranFreeField14":null
                , "tranFreeField15":null
                , "tranFreeField16":"2020-01-01T00:00:00.000Z"
                , "tranFreeField17":null
                , "tranFreeField18":null
                , "tranFreeField19":null
                , "tranFreeField20":"2020-01-01T00:00:00.000Z"
                , "tranFreeField21":"2020-01-01T00:00:00.000Z"
                , "tranFreeField22":null
                , "tranFreeField23":"2020-01-01T00:00:00.000Z"
                , "tranFreeField24":"SAMPLE_TEXT"
                , "tranFreeField25":"SAMPLE_TEXT"
                , "tranFreeField26":"SAMPLE_TEXT"
                , "tranFreeField27":"SAMPLE_TEXT"
                , "tranFreeField28":"SAMPLE_TEXT"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_dTransaction_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created dTransaction")
                .get("${new_dTransaction_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created dTransaction")
            .delete("${new_dTransaction_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
