package ug.co.absa.microsrvc.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static ug.co.absa.microsrvc.gateway.web.rest.TestUtil.sameInstant;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.IntegrationTest;
import ug.co.absa.microsrvc.gateway.domain.ResponseInfo;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.ResponseInfoRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ResponseInfoSearchRepository;

/**
 * Integration tests for the {@link ResponseInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ResponseInfoResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_TRANID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TRANID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_BODY = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_BODY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_REQUEST_REF = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_REF = "BBBBBBBBBB";

    private static final TranStatus DEFAULT_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_2_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ErrorCodes DEFAULT_ERROR_CODE = ErrorCodes.SUCCESS;
    private static final ErrorCodes UPDATED_ERROR_CODE = ErrorCodes.PENDING;

    private static final ErrorMessage DEFAULT_ERROR_MESSAGE = ErrorMessage.TRANSACTIONSUCCESS;
    private static final ErrorMessage UPDATED_ERROR_MESSAGE = ErrorMessage.TRANSACTIONFAILED;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/response-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/response-infos";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResponseInfoRepository responseInfoRepository;

    @Autowired
    private ResponseInfoSearchRepository responseInfoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ResponseInfo responseInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResponseInfo createEntity(EntityManager em) {
        ResponseInfo responseInfo = new ResponseInfo()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(DEFAULT_EXTERNAL_TRANID)
            .responseCode(DEFAULT_RESPONSE_CODE)
            .responseMessage(DEFAULT_RESPONSE_MESSAGE)
            .responseBody(DEFAULT_RESPONSE_BODY)
            .timestamp(DEFAULT_TIMESTAMP)
            .requestRef(DEFAULT_REQUEST_REF)
            .status(DEFAULT_STATUS)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField2ContentType(DEFAULT_FREE_FIELD_2_CONTENT_TYPE)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .time(DEFAULT_TIME)
            .errorCode(DEFAULT_ERROR_CODE)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return responseInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResponseInfo createUpdatedEntity(EntityManager em) {
        ResponseInfo responseInfo = new ResponseInfo()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField2ContentType(UPDATED_FREE_FIELD_2_CONTENT_TYPE)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .time(UPDATED_TIME)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return responseInfo;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ResponseInfo.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @AfterEach
    public void cleanupElasticSearchRepository() {
        responseInfoSearchRepository.deleteAll().block();
        assertThat(responseInfoSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        responseInfo = createEntity(em);
    }

    @Test
    void createResponseInfo() throws Exception {
        int databaseSizeBeforeCreate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        // Create the ResponseInfo
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        ResponseInfo testResponseInfo = responseInfoList.get(responseInfoList.size() - 1);
        assertThat(testResponseInfo.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testResponseInfo.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testResponseInfo.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testResponseInfo.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testResponseInfo.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testResponseInfo.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testResponseInfo.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testResponseInfo.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testResponseInfo.getResponseBody()).isEqualTo(DEFAULT_RESPONSE_BODY);
        assertThat(testResponseInfo.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testResponseInfo.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testResponseInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResponseInfo.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testResponseInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testResponseInfo.getFreeField2ContentType()).isEqualTo(DEFAULT_FREE_FIELD_2_CONTENT_TYPE);
        assertThat(testResponseInfo.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testResponseInfo.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testResponseInfo.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testResponseInfo.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testResponseInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testResponseInfo.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testResponseInfo.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testResponseInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testResponseInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testResponseInfo.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testResponseInfo.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createResponseInfoWithExistingId() throws Exception {
        // Create the ResponseInfo with an existing ID
        responseInfo.setId(1L);

        int databaseSizeBeforeCreate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        // set the field null
        responseInfo.setBillerId(null);

        // Create the ResponseInfo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        // set the field null
        responseInfo.setPaymentItemCode(null);

        // Create the ResponseInfo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllResponseInfosAsStream() {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        List<ResponseInfo> responseInfoList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ResponseInfo.class)
            .getResponseBody()
            .filter(responseInfo::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(responseInfoList).isNotNull();
        assertThat(responseInfoList).hasSize(1);
        ResponseInfo testResponseInfo = responseInfoList.get(0);
        assertThat(testResponseInfo.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testResponseInfo.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testResponseInfo.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testResponseInfo.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testResponseInfo.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testResponseInfo.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testResponseInfo.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testResponseInfo.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testResponseInfo.getResponseBody()).isEqualTo(DEFAULT_RESPONSE_BODY);
        assertThat(testResponseInfo.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testResponseInfo.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testResponseInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testResponseInfo.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testResponseInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testResponseInfo.getFreeField2ContentType()).isEqualTo(DEFAULT_FREE_FIELD_2_CONTENT_TYPE);
        assertThat(testResponseInfo.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testResponseInfo.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testResponseInfo.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testResponseInfo.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testResponseInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testResponseInfo.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testResponseInfo.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testResponseInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testResponseInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testResponseInfo.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testResponseInfo.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllResponseInfos() {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        // Get all the responseInfoList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(responseInfo.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].responseCode")
            .value(hasItem(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.[*].responseMessage")
            .value(hasItem(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.[*].responseBody")
            .value(hasItem(DEFAULT_RESPONSE_BODY))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].requestRef")
            .value(hasItem(DEFAULT_REQUEST_REF))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.[*].freeField2ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_2_CONTENT_TYPE))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_2)))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].time")
            .value(hasItem(sameInstant(DEFAULT_TIME)))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }

    @Test
    void getResponseInfo() {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        // Get the responseInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, responseInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(responseInfo.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.paymentItemCode")
            .value(is(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.externalTranid")
            .value(is(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.responseCode")
            .value(is(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.responseMessage")
            .value(is(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.responseBody")
            .value(is(DEFAULT_RESPONSE_BODY))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.requestRef")
            .value(is(DEFAULT_REQUEST_REF))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.freeField2ContentType")
            .value(is(DEFAULT_FREE_FIELD_2_CONTENT_TYPE))
            .jsonPath("$.freeField2")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_2)))
            .jsonPath("$.freeField3")
            .value(is(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.freeField4")
            .value(is(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.freeField5")
            .value(is(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.freeField6")
            .value(is(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.time")
            .value(is(sameInstant(DEFAULT_TIME)))
            .jsonPath("$.errorCode")
            .value(is(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.errorMessage")
            .value(is(DEFAULT_ERROR_MESSAGE.toString()))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY));
    }

    @Test
    void getNonExistingResponseInfo() {
        // Get the responseInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingResponseInfo() throws Exception {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        responseInfoSearchRepository.save(responseInfo).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());

        // Update the responseInfo
        ResponseInfo updatedResponseInfo = responseInfoRepository.findById(responseInfo.getId()).block();
        updatedResponseInfo
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField2ContentType(UPDATED_FREE_FIELD_2_CONTENT_TYPE)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .time(UPDATED_TIME)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedResponseInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedResponseInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        ResponseInfo testResponseInfo = responseInfoList.get(responseInfoList.size() - 1);
        assertThat(testResponseInfo.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testResponseInfo.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testResponseInfo.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testResponseInfo.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testResponseInfo.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testResponseInfo.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testResponseInfo.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testResponseInfo.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testResponseInfo.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
        assertThat(testResponseInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testResponseInfo.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testResponseInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResponseInfo.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testResponseInfo.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testResponseInfo.getFreeField2ContentType()).isEqualTo(UPDATED_FREE_FIELD_2_CONTENT_TYPE);
        assertThat(testResponseInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testResponseInfo.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testResponseInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testResponseInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testResponseInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testResponseInfo.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testResponseInfo.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testResponseInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testResponseInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResponseInfo.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testResponseInfo.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<ResponseInfo> responseInfoSearchList = IterableUtils.toList(
                    responseInfoSearchRepository.findAll().collectList().block()
                );
                ResponseInfo testResponseInfoSearch = responseInfoSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testResponseInfoSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testResponseInfoSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testResponseInfoSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testResponseInfoSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testResponseInfoSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testResponseInfoSearch.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
                assertThat(testResponseInfoSearch.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
                assertThat(testResponseInfoSearch.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
                assertThat(testResponseInfoSearch.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
                assertThat(testResponseInfoSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testResponseInfoSearch.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
                assertThat(testResponseInfoSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testResponseInfoSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testResponseInfoSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testResponseInfoSearch.getFreeField2ContentType()).isEqualTo(UPDATED_FREE_FIELD_2_CONTENT_TYPE);
                assertThat(testResponseInfoSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testResponseInfoSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testResponseInfoSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testResponseInfoSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testResponseInfoSearch.getTime()).isEqualTo(UPDATED_TIME);
                assertThat(testResponseInfoSearch.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
                assertThat(testResponseInfoSearch.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
                assertThat(testResponseInfoSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testResponseInfoSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testResponseInfoSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testResponseInfoSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, responseInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateResponseInfoWithPatch() throws Exception {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();

        // Update the responseInfo using partial update
        ResponseInfo partialUpdatedResponseInfo = new ResponseInfo();
        partialUpdatedResponseInfo.setId(responseInfo.getId());

        partialUpdatedResponseInfo
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedResponseInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedResponseInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        ResponseInfo testResponseInfo = responseInfoList.get(responseInfoList.size() - 1);
        assertThat(testResponseInfo.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testResponseInfo.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testResponseInfo.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testResponseInfo.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testResponseInfo.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testResponseInfo.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testResponseInfo.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testResponseInfo.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testResponseInfo.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
        assertThat(testResponseInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testResponseInfo.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testResponseInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResponseInfo.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testResponseInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testResponseInfo.getFreeField2ContentType()).isEqualTo(DEFAULT_FREE_FIELD_2_CONTENT_TYPE);
        assertThat(testResponseInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testResponseInfo.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testResponseInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testResponseInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testResponseInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testResponseInfo.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testResponseInfo.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testResponseInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testResponseInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testResponseInfo.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testResponseInfo.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateResponseInfoWithPatch() throws Exception {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();

        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();

        // Update the responseInfo using partial update
        ResponseInfo partialUpdatedResponseInfo = new ResponseInfo();
        partialUpdatedResponseInfo.setId(responseInfo.getId());

        partialUpdatedResponseInfo
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField2ContentType(UPDATED_FREE_FIELD_2_CONTENT_TYPE)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .time(UPDATED_TIME)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedResponseInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedResponseInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        ResponseInfo testResponseInfo = responseInfoList.get(responseInfoList.size() - 1);
        assertThat(testResponseInfo.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testResponseInfo.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testResponseInfo.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testResponseInfo.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testResponseInfo.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testResponseInfo.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testResponseInfo.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testResponseInfo.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testResponseInfo.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
        assertThat(testResponseInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testResponseInfo.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testResponseInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testResponseInfo.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testResponseInfo.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testResponseInfo.getFreeField2ContentType()).isEqualTo(UPDATED_FREE_FIELD_2_CONTENT_TYPE);
        assertThat(testResponseInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testResponseInfo.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testResponseInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testResponseInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testResponseInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testResponseInfo.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testResponseInfo.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testResponseInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testResponseInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testResponseInfo.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testResponseInfo.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, responseInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamResponseInfo() throws Exception {
        int databaseSizeBeforeUpdate = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        responseInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(responseInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ResponseInfo in the database
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteResponseInfo() {
        // Initialize the database
        responseInfoRepository.save(responseInfo).block();
        responseInfoRepository.save(responseInfo).block();
        responseInfoSearchRepository.save(responseInfo).block();

        int databaseSizeBeforeDelete = responseInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the responseInfo
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, responseInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ResponseInfo> responseInfoList = responseInfoRepository.findAll().collectList().block();
        assertThat(responseInfoList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(responseInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchResponseInfo() {
        // Initialize the database
        responseInfo = responseInfoRepository.save(responseInfo).block();
        responseInfoSearchRepository.save(responseInfo).block();

        // Search the responseInfo
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + responseInfo.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(responseInfo.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].responseCode")
            .value(hasItem(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.[*].responseMessage")
            .value(hasItem(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.[*].responseBody")
            .value(hasItem(DEFAULT_RESPONSE_BODY))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].requestRef")
            .value(hasItem(DEFAULT_REQUEST_REF))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.[*].freeField2ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_2_CONTENT_TYPE))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_2)))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].time")
            .value(hasItem(sameInstant(DEFAULT_TIME)))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }
}
