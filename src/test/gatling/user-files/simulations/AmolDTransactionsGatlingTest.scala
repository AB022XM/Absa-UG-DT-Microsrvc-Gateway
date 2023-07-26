import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the AmolDTransactions entity.
 */
class AmolDTransactionsGatlingTest extends Simulation {

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

    val scn = scenario("Test the AmolDTransactions entity")
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
            exec(http("Get all amolDTransactions")
            .get("/api/amol-d-transactions")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new amolDTransactions")
            .post("/api/amol-d-transactions")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "absaTranRef":null
                , "billerId":"SAMPLE_TEXT"
                , "dtDTransactionId":"SAMPLE_TEXT"
                , "amolDTransactionId":"SAMPLE_TEXT"
                , "transactionReferenceNumber":"SAMPLE_TEXT"
                , "token":"SAMPLE_TEXT"
                , "transferId":"SAMPLE_TEXT"
                , "externalTxnId":"SAMPLE_TEXT"
                , "customerReference":"SAMPLE_TEXT"
                , "debitAccountNumber":"SAMPLE_TEXT"
                , "creditAccountNumber":"SAMPLE_TEXT"
                , "debitAmount":"0"
                , "creditAmount":"0"
                , "transactionAmount":"0"
                , "debitDate":"2020-01-01T00:00:00.000Z"
                , "creditDate":"2020-01-01T00:00:00.000Z"
                , "status":"PENDING"
                , "settlementDate":"2020-01-01T00:00:00.000Z"
                , "debitCurrency":"UGX"
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
                , "taxAmount":"0"
                , "taxGLAccount":"SAMPLE_TEXT"
                , "taxNarration":"SAMPLE_TEXT"
                , "internalErrorCode":"SAMPLE_TEXT"
                , "externalErrorCode":"SAMPLE_TEXT"
                , "payeeBranchId":"SAMPLE_TEXT"
                , "payeeProductInstanceReference":"SAMPLE_TEXT"
                , "payeeProductCode":"SAMPLE_TEXT"
                , "payeeProductName":"SAMPLE_TEXT"
                , "payeeProductDescription":"SAMPLE_TEXT"
                , "payeeProductUnitOfMeasure":"SAMPLE_TEXT"
                , "payeeProductQuantity":"SAMPLE_TEXT"
                , "subCategoryCode":"SAMPLE_TEXT"
                , "payerBankCode":"SAMPLE_TEXT"
                , "payerBankName":"SAMPLE_TEXT"
                , "payerBankAddress":"SAMPLE_TEXT"
                , "payerBankCity":"SAMPLE_TEXT"
                , "payerBankState":"SAMPLE_TEXT"
                , "payerBankCountry":"SAMPLE_TEXT"
                , "payerBankPostalCode":"SAMPLE_TEXT"
                , "checkerId":"SAMPLE_TEXT"
                , "remittanceInformation":"SAMPLE_TEXT"
                , "paymentChannelCode":"ATM"
                , "feeAmount":"0"
                , "feeGLAccount":"SAMPLE_TEXT"
                , "feeNarration":"SAMPLE_TEXT"
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
                , "responseCode":"SAMPLE_TEXT"
                , "responseMessage":"SAMPLE_TEXT"
                , "responseDetails":null
                , "transferReferenceId":"SAMPLE_TEXT"
                , "transferStatus":"SAMPLE_TEXT"
                , "responseDateTime":"2020-01-01T00:00:00.000Z"
                , "createdAt":"2020-01-01T00:00:00.000Z"
                , "createdBy":"SAMPLE_TEXT"
                , "updatedAt":"2020-01-01T00:00:00.000Z"
                , "updatedBy":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_amolDTransactions_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created amolDTransactions")
                .get("${new_amolDTransactions_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created amolDTransactions")
            .delete("${new_amolDTransactions_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
