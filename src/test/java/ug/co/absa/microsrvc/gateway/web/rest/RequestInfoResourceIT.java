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
import ug.co.absa.microsrvc.gateway.domain.RequestInfo;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.RequestInfoRepository;
import ug.co.absa.microsrvc.gateway.repository.search.RequestInfoSearchRepository;

/**
 * Integration tests for the {@link RequestInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class RequestInfoResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DATA = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_LIST = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_LIST = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_REQUEST_REF = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_REF = "BBBBBBBBBB";

    private static final TranStatus DEFAULT_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/request-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/request-infos";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestInfoRepository requestInfoRepository;

    @Autowired
    private RequestInfoSearchRepository requestInfoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private RequestInfo requestInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestInfo createEntity(EntityManager em) {
        RequestInfo requestInfo = new RequestInfo()
            .recordId(DEFAULT_RECORD_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .requestData(DEFAULT_REQUEST_DATA)
            .paramList(DEFAULT_PARAM_LIST)
            .timestamp(DEFAULT_TIMESTAMP)
            .requestRef(DEFAULT_REQUEST_REF)
            .status(DEFAULT_STATUS)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
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
        return requestInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestInfo createUpdatedEntity(EntityManager em) {
        RequestInfo requestInfo = new RequestInfo()
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .requestData(UPDATED_REQUEST_DATA)
            .paramList(UPDATED_PARAM_LIST)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
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
        return requestInfo;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(RequestInfo.class).block();
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
        requestInfoSearchRepository.deleteAll().block();
        assertThat(requestInfoSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        requestInfo = createEntity(em);
    }

    @Test
    void createRequestInfo() throws Exception {
        int databaseSizeBeforeCreate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        // Create the RequestInfo
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        RequestInfo testRequestInfo = requestInfoList.get(requestInfoList.size() - 1);
        assertThat(testRequestInfo.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testRequestInfo.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testRequestInfo.getRequestData()).isEqualTo(DEFAULT_REQUEST_DATA);
        assertThat(testRequestInfo.getParamList()).isEqualTo(DEFAULT_PARAM_LIST);
        assertThat(testRequestInfo.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testRequestInfo.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testRequestInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRequestInfo.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testRequestInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testRequestInfo.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testRequestInfo.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testRequestInfo.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testRequestInfo.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testRequestInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testRequestInfo.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testRequestInfo.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testRequestInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRequestInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRequestInfo.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRequestInfo.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createRequestInfoWithExistingId() throws Exception {
        // Create the RequestInfo with an existing ID
        requestInfo.setId(1L);

        int databaseSizeBeforeCreate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        // set the field null
        requestInfo.setCreatedAt(null);

        // Create the RequestInfo, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllRequestInfosAsStream() {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        List<RequestInfo> requestInfoList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(RequestInfo.class)
            .getResponseBody()
            .filter(requestInfo::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(requestInfoList).isNotNull();
        assertThat(requestInfoList).hasSize(1);
        RequestInfo testRequestInfo = requestInfoList.get(0);
        assertThat(testRequestInfo.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testRequestInfo.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testRequestInfo.getRequestData()).isEqualTo(DEFAULT_REQUEST_DATA);
        assertThat(testRequestInfo.getParamList()).isEqualTo(DEFAULT_PARAM_LIST);
        assertThat(testRequestInfo.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testRequestInfo.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testRequestInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRequestInfo.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testRequestInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testRequestInfo.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testRequestInfo.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testRequestInfo.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testRequestInfo.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testRequestInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testRequestInfo.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testRequestInfo.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testRequestInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRequestInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRequestInfo.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRequestInfo.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllRequestInfos() {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        // Get all the requestInfoList
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
            .value(hasItem(requestInfo.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].requestData")
            .value(hasItem(DEFAULT_REQUEST_DATA))
            .jsonPath("$.[*].paramList")
            .value(hasItem(DEFAULT_PARAM_LIST))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].requestRef")
            .value(hasItem(DEFAULT_REQUEST_REF))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2.toString()))
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
    void getRequestInfo() {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        // Get the requestInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, requestInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(requestInfo.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.requestData")
            .value(is(DEFAULT_REQUEST_DATA))
            .jsonPath("$.paramList")
            .value(is(DEFAULT_PARAM_LIST))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.requestRef")
            .value(is(DEFAULT_REQUEST_REF))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.freeField2")
            .value(is(DEFAULT_FREE_FIELD_2.toString()))
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
    void getNonExistingRequestInfo() {
        // Get the requestInfo
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingRequestInfo() throws Exception {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        requestInfoSearchRepository.save(requestInfo).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());

        // Update the requestInfo
        RequestInfo updatedRequestInfo = requestInfoRepository.findById(requestInfo.getId()).block();
        updatedRequestInfo
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .requestData(UPDATED_REQUEST_DATA)
            .paramList(UPDATED_PARAM_LIST)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
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
            .uri(ENTITY_API_URL_ID, updatedRequestInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedRequestInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        RequestInfo testRequestInfo = requestInfoList.get(requestInfoList.size() - 1);
        assertThat(testRequestInfo.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testRequestInfo.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testRequestInfo.getRequestData()).isEqualTo(UPDATED_REQUEST_DATA);
        assertThat(testRequestInfo.getParamList()).isEqualTo(UPDATED_PARAM_LIST);
        assertThat(testRequestInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testRequestInfo.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testRequestInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequestInfo.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testRequestInfo.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testRequestInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testRequestInfo.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testRequestInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testRequestInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testRequestInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testRequestInfo.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testRequestInfo.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testRequestInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequestInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRequestInfo.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRequestInfo.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<RequestInfo> requestInfoSearchList = IterableUtils.toList(requestInfoSearchRepository.findAll().collectList().block());
                RequestInfo testRequestInfoSearch = requestInfoSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testRequestInfoSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testRequestInfoSearch.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
                assertThat(testRequestInfoSearch.getRequestData()).isEqualTo(UPDATED_REQUEST_DATA);
                assertThat(testRequestInfoSearch.getParamList()).isEqualTo(UPDATED_PARAM_LIST);
                assertThat(testRequestInfoSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testRequestInfoSearch.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
                assertThat(testRequestInfoSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testRequestInfoSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testRequestInfoSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testRequestInfoSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testRequestInfoSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testRequestInfoSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testRequestInfoSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testRequestInfoSearch.getTime()).isEqualTo(UPDATED_TIME);
                assertThat(testRequestInfoSearch.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
                assertThat(testRequestInfoSearch.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
                assertThat(testRequestInfoSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testRequestInfoSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testRequestInfoSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testRequestInfoSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, requestInfo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateRequestInfoWithPatch() throws Exception {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();

        // Update the requestInfo using partial update
        RequestInfo partialUpdatedRequestInfo = new RequestInfo();
        partialUpdatedRequestInfo.setId(requestInfo.getId());

        partialUpdatedRequestInfo
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .errorCode(UPDATED_ERROR_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRequestInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        RequestInfo testRequestInfo = requestInfoList.get(requestInfoList.size() - 1);
        assertThat(testRequestInfo.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testRequestInfo.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testRequestInfo.getRequestData()).isEqualTo(DEFAULT_REQUEST_DATA);
        assertThat(testRequestInfo.getParamList()).isEqualTo(DEFAULT_PARAM_LIST);
        assertThat(testRequestInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testRequestInfo.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testRequestInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRequestInfo.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testRequestInfo.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testRequestInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testRequestInfo.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testRequestInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testRequestInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testRequestInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testRequestInfo.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testRequestInfo.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testRequestInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRequestInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRequestInfo.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRequestInfo.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateRequestInfoWithPatch() throws Exception {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();

        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();

        // Update the requestInfo using partial update
        RequestInfo partialUpdatedRequestInfo = new RequestInfo();
        partialUpdatedRequestInfo.setId(requestInfo.getId());

        partialUpdatedRequestInfo
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .requestData(UPDATED_REQUEST_DATA)
            .paramList(UPDATED_PARAM_LIST)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedRequestInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestInfo))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        RequestInfo testRequestInfo = requestInfoList.get(requestInfoList.size() - 1);
        assertThat(testRequestInfo.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testRequestInfo.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testRequestInfo.getRequestData()).isEqualTo(UPDATED_REQUEST_DATA);
        assertThat(testRequestInfo.getParamList()).isEqualTo(UPDATED_PARAM_LIST);
        assertThat(testRequestInfo.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testRequestInfo.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testRequestInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequestInfo.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testRequestInfo.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testRequestInfo.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testRequestInfo.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testRequestInfo.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testRequestInfo.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testRequestInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testRequestInfo.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testRequestInfo.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testRequestInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRequestInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRequestInfo.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRequestInfo.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, requestInfo.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamRequestInfo() throws Exception {
        int databaseSizeBeforeUpdate = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        requestInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestInfo))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RequestInfo in the database
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteRequestInfo() {
        // Initialize the database
        requestInfoRepository.save(requestInfo).block();
        requestInfoRepository.save(requestInfo).block();
        requestInfoSearchRepository.save(requestInfo).block();

        int databaseSizeBeforeDelete = requestInfoRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the requestInfo
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, requestInfo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<RequestInfo> requestInfoList = requestInfoRepository.findAll().collectList().block();
        assertThat(requestInfoList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(requestInfoSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchRequestInfo() {
        // Initialize the database
        requestInfo = requestInfoRepository.save(requestInfo).block();
        requestInfoSearchRepository.save(requestInfo).block();

        // Search the requestInfo
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + requestInfo.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(requestInfo.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].requestData")
            .value(hasItem(DEFAULT_REQUEST_DATA))
            .jsonPath("$.[*].paramList")
            .value(hasItem(DEFAULT_PARAM_LIST))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].requestRef")
            .value(hasItem(DEFAULT_REQUEST_REF))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2.toString()))
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
