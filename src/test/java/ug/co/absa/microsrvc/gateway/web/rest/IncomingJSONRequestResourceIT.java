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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONRequestRepository;
import ug.co.absa.microsrvc.gateway.repository.search.IncomingJSONRequestSearchRepository;

/**
 * Integration tests for the {@link IncomingJSONRequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class IncomingJSONRequestResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AMOL_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_AMOL_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_FROM_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_TO_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_TO_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_JSON = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_INFO = "BBBBBBBBBB";

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

    private static final byte[] DEFAULT_FREE_FIELD_6 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_6 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_6_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_6_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FREE_FIELD_7 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_7 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_7_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_7_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_FREE_FIELD_8 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_8 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_8_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_8_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FREE_FIELD_10 = false;
    private static final Boolean UPDATED_FREE_FIELD_10 = true;

    private static final Boolean DEFAULT_FREE_FIELD_11 = false;
    private static final Boolean UPDATED_FREE_FIELD_11 = true;

    private static final Integer DEFAULT_FREE_FIELD_12 = 1;
    private static final Integer UPDATED_FREE_FIELD_12 = 2;

    private static final String DEFAULT_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_18 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_18 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_20 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_20 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/incoming-json-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/incoming-json-requests";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IncomingJSONRequestRepository incomingJSONRequestRepository;

    @Autowired
    private IncomingJSONRequestSearchRepository incomingJSONRequestSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private IncomingJSONRequest incomingJSONRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomingJSONRequest createEntity(EntityManager em) {
        IncomingJSONRequest incomingJSONRequest = new IncomingJSONRequest()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .fromEndpoint(DEFAULT_FROM_ENDPOINT)
            .toEndpoint(DEFAULT_TO_ENDPOINT)
            .requestJson(DEFAULT_REQUEST_JSON)
            .requestType(DEFAULT_REQUEST_TYPE)
            .requestDescription(DEFAULT_REQUEST_DESCRIPTION)
            .requestStatus(DEFAULT_REQUEST_STATUS)
            .additionalInfo(DEFAULT_ADDITIONAL_INFO)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField6ContentType(DEFAULT_FREE_FIELD_6_CONTENT_TYPE)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField7ContentType(DEFAULT_FREE_FIELD_7_CONTENT_TYPE)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField8ContentType(DEFAULT_FREE_FIELD_8_CONTENT_TYPE)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10)
            .freeField11(DEFAULT_FREE_FIELD_11)
            .freeField12(DEFAULT_FREE_FIELD_12)
            .freeField13(DEFAULT_FREE_FIELD_13)
            .freeField14(DEFAULT_FREE_FIELD_14)
            .freeField15(DEFAULT_FREE_FIELD_15)
            .freeField16(DEFAULT_FREE_FIELD_16)
            .freeField17(DEFAULT_FREE_FIELD_17)
            .freeField18(DEFAULT_FREE_FIELD_18)
            .freeField19(DEFAULT_FREE_FIELD_19)
            .freeField20(DEFAULT_FREE_FIELD_20)
            .timestamp(DEFAULT_TIMESTAMP)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return incomingJSONRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomingJSONRequest createUpdatedEntity(EntityManager em) {
        IncomingJSONRequest incomingJSONRequest = new IncomingJSONRequest()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transactionId(UPDATED_TRANSACTION_ID)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .requestJson(UPDATED_REQUEST_JSON)
            .requestType(UPDATED_REQUEST_TYPE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField7ContentType(UPDATED_FREE_FIELD_7_CONTENT_TYPE)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField8ContentType(UPDATED_FREE_FIELD_8_CONTENT_TYPE)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField19(UPDATED_FREE_FIELD_19)
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return incomingJSONRequest;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(IncomingJSONRequest.class).block();
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
        incomingJSONRequestSearchRepository.deleteAll().block();
        assertThat(incomingJSONRequestSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        incomingJSONRequest = createEntity(em);
    }

    @Test
    void createIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeCreate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        // Create the IncomingJSONRequest
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        IncomingJSONRequest testIncomingJSONRequest = incomingJSONRequestList.get(incomingJSONRequestList.size() - 1);
        assertThat(testIncomingJSONRequest.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testIncomingJSONRequest.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONRequest.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testIncomingJSONRequest.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getFromEndpoint()).isEqualTo(DEFAULT_FROM_ENDPOINT);
        assertThat(testIncomingJSONRequest.getToEndpoint()).isEqualTo(DEFAULT_TO_ENDPOINT);
        assertThat(testIncomingJSONRequest.getRequestJson()).isEqualTo(DEFAULT_REQUEST_JSON);
        assertThat(testIncomingJSONRequest.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testIncomingJSONRequest.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testIncomingJSONRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testIncomingJSONRequest.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testIncomingJSONRequest.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testIncomingJSONRequest.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testIncomingJSONRequest.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testIncomingJSONRequest.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONRequest.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testIncomingJSONRequest.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testIncomingJSONRequest.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testIncomingJSONRequest.getFreeField7ContentType()).isEqualTo(DEFAULT_FREE_FIELD_7_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testIncomingJSONRequest.getFreeField8ContentType()).isEqualTo(DEFAULT_FREE_FIELD_8_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testIncomingJSONRequest.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testIncomingJSONRequest.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testIncomingJSONRequest.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testIncomingJSONRequest.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testIncomingJSONRequest.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONRequest.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testIncomingJSONRequest.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testIncomingJSONRequest.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONRequest.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONRequest.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testIncomingJSONRequest.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testIncomingJSONRequest.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIncomingJSONRequest.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONRequest.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncomingJSONRequest.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncomingJSONRequest.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createIncomingJSONRequestWithExistingId() throws Exception {
        // Create the IncomingJSONRequest with an existing ID
        incomingJSONRequest.setId(1L);

        int databaseSizeBeforeCreate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkRequestTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        // set the field null
        incomingJSONRequest.setRequestType(null);

        // Create the IncomingJSONRequest, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        // set the field null
        incomingJSONRequest.setTimestamp(null);

        // Create the IncomingJSONRequest, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllIncomingJSONRequestsAsStream() {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        List<IncomingJSONRequest> incomingJSONRequestList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(IncomingJSONRequest.class)
            .getResponseBody()
            .filter(incomingJSONRequest::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(incomingJSONRequestList).isNotNull();
        assertThat(incomingJSONRequestList).hasSize(1);
        IncomingJSONRequest testIncomingJSONRequest = incomingJSONRequestList.get(0);
        assertThat(testIncomingJSONRequest.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testIncomingJSONRequest.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONRequest.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testIncomingJSONRequest.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getFromEndpoint()).isEqualTo(DEFAULT_FROM_ENDPOINT);
        assertThat(testIncomingJSONRequest.getToEndpoint()).isEqualTo(DEFAULT_TO_ENDPOINT);
        assertThat(testIncomingJSONRequest.getRequestJson()).isEqualTo(DEFAULT_REQUEST_JSON);
        assertThat(testIncomingJSONRequest.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testIncomingJSONRequest.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testIncomingJSONRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testIncomingJSONRequest.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testIncomingJSONRequest.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testIncomingJSONRequest.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testIncomingJSONRequest.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testIncomingJSONRequest.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONRequest.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testIncomingJSONRequest.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testIncomingJSONRequest.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testIncomingJSONRequest.getFreeField7ContentType()).isEqualTo(DEFAULT_FREE_FIELD_7_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testIncomingJSONRequest.getFreeField8ContentType()).isEqualTo(DEFAULT_FREE_FIELD_8_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testIncomingJSONRequest.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testIncomingJSONRequest.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testIncomingJSONRequest.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testIncomingJSONRequest.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testIncomingJSONRequest.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONRequest.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testIncomingJSONRequest.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testIncomingJSONRequest.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONRequest.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONRequest.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testIncomingJSONRequest.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testIncomingJSONRequest.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIncomingJSONRequest.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONRequest.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncomingJSONRequest.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncomingJSONRequest.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllIncomingJSONRequests() {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        // Get all the incomingJSONRequestList
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
            .value(hasItem(incomingJSONRequest.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].fromEndpoint")
            .value(hasItem(DEFAULT_FROM_ENDPOINT))
            .jsonPath("$.[*].toEndpoint")
            .value(hasItem(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.[*].requestJson")
            .value(hasItem(DEFAULT_REQUEST_JSON.toString()))
            .jsonPath("$.[*].requestType")
            .value(hasItem(DEFAULT_REQUEST_TYPE))
            .jsonPath("$.[*].requestDescription")
            .value(hasItem(DEFAULT_REQUEST_DESCRIPTION))
            .jsonPath("$.[*].requestStatus")
            .value(hasItem(DEFAULT_REQUEST_STATUS))
            .jsonPath("$.[*].additionalInfo")
            .value(hasItem(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3.toString()))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4.toString()))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.[*].freeField6ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.[*].freeField7ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_7_CONTENT_TYPE))
            .jsonPath("$.[*].freeField7")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_7)))
            .jsonPath("$.[*].freeField8ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_8_CONTENT_TYPE))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_8)))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue()))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11.booleanValue()))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13.toString()))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(DEFAULT_FREE_FIELD_15))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(DEFAULT_FREE_FIELD_18))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
            .jsonPath("$.[*].freeField20")
            .value(hasItem(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
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
    void getIncomingJSONRequest() {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        // Get the incomingJSONRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, incomingJSONRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(incomingJSONRequest.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.amolDTransactionId")
            .value(is(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.token")
            .value(is(DEFAULT_TOKEN))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.fromEndpoint")
            .value(is(DEFAULT_FROM_ENDPOINT))
            .jsonPath("$.toEndpoint")
            .value(is(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.requestJson")
            .value(is(DEFAULT_REQUEST_JSON.toString()))
            .jsonPath("$.requestType")
            .value(is(DEFAULT_REQUEST_TYPE))
            .jsonPath("$.requestDescription")
            .value(is(DEFAULT_REQUEST_DESCRIPTION))
            .jsonPath("$.requestStatus")
            .value(is(DEFAULT_REQUEST_STATUS))
            .jsonPath("$.additionalInfo")
            .value(is(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.freeField2")
            .value(is(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.freeField3")
            .value(is(DEFAULT_FREE_FIELD_3.toString()))
            .jsonPath("$.freeField4")
            .value(is(DEFAULT_FREE_FIELD_4.toString()))
            .jsonPath("$.freeField5")
            .value(is(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.freeField6ContentType")
            .value(is(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.freeField6")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.freeField7ContentType")
            .value(is(DEFAULT_FREE_FIELD_7_CONTENT_TYPE))
            .jsonPath("$.freeField7")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_7)))
            .jsonPath("$.freeField8ContentType")
            .value(is(DEFAULT_FREE_FIELD_8_CONTENT_TYPE))
            .jsonPath("$.freeField8")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_8)))
            .jsonPath("$.freeField9")
            .value(is(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.freeField10")
            .value(is(DEFAULT_FREE_FIELD_10.booleanValue()))
            .jsonPath("$.freeField11")
            .value(is(DEFAULT_FREE_FIELD_11.booleanValue()))
            .jsonPath("$.freeField12")
            .value(is(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.freeField13")
            .value(is(DEFAULT_FREE_FIELD_13.toString()))
            .jsonPath("$.freeField14")
            .value(is(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.freeField15")
            .value(is(DEFAULT_FREE_FIELD_15))
            .jsonPath("$.freeField16")
            .value(is(DEFAULT_FREE_FIELD_16))
            .jsonPath("$.freeField17")
            .value(is(DEFAULT_FREE_FIELD_17))
            .jsonPath("$.freeField18")
            .value(is(DEFAULT_FREE_FIELD_18))
            .jsonPath("$.freeField19")
            .value(is(DEFAULT_FREE_FIELD_19))
            .jsonPath("$.freeField20")
            .value(is(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
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
    void getNonExistingIncomingJSONRequest() {
        // Get the incomingJSONRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingIncomingJSONRequest() throws Exception {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        incomingJSONRequestSearchRepository.save(incomingJSONRequest).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());

        // Update the incomingJSONRequest
        IncomingJSONRequest updatedIncomingJSONRequest = incomingJSONRequestRepository.findById(incomingJSONRequest.getId()).block();
        updatedIncomingJSONRequest
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transactionId(UPDATED_TRANSACTION_ID)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .requestJson(UPDATED_REQUEST_JSON)
            .requestType(UPDATED_REQUEST_TYPE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField7ContentType(UPDATED_FREE_FIELD_7_CONTENT_TYPE)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField8ContentType(UPDATED_FREE_FIELD_8_CONTENT_TYPE)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField19(UPDATED_FREE_FIELD_19)
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedIncomingJSONRequest.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedIncomingJSONRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONRequest testIncomingJSONRequest = incomingJSONRequestList.get(incomingJSONRequestList.size() - 1);
        assertThat(testIncomingJSONRequest.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONRequest.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONRequest.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONRequest.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
        assertThat(testIncomingJSONRequest.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
        assertThat(testIncomingJSONRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testIncomingJSONRequest.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testIncomingJSONRequest.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testIncomingJSONRequest.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
        assertThat(testIncomingJSONRequest.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONRequest.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testIncomingJSONRequest.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testIncomingJSONRequest.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testIncomingJSONRequest.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testIncomingJSONRequest.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONRequest.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testIncomingJSONRequest.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testIncomingJSONRequest.getFreeField7ContentType()).isEqualTo(UPDATED_FREE_FIELD_7_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONRequest.getFreeField8ContentType()).isEqualTo(UPDATED_FREE_FIELD_8_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testIncomingJSONRequest.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testIncomingJSONRequest.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testIncomingJSONRequest.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONRequest.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONRequest.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testIncomingJSONRequest.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONRequest.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONRequest.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testIncomingJSONRequest.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testIncomingJSONRequest.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONRequest.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testIncomingJSONRequest.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONRequest.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncomingJSONRequest.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncomingJSONRequest.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncomingJSONRequest.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<IncomingJSONRequest> incomingJSONRequestSearchList = IterableUtils.toList(
                    incomingJSONRequestSearchRepository.findAll().collectList().block()
                );
                IncomingJSONRequest testIncomingJSONRequestSearch = incomingJSONRequestSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testIncomingJSONRequestSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testIncomingJSONRequestSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testIncomingJSONRequestSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testIncomingJSONRequestSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testIncomingJSONRequestSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testIncomingJSONRequestSearch.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
                assertThat(testIncomingJSONRequestSearch.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
                assertThat(testIncomingJSONRequestSearch.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
                assertThat(testIncomingJSONRequestSearch.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
                assertThat(testIncomingJSONRequestSearch.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
                assertThat(testIncomingJSONRequestSearch.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
                assertThat(testIncomingJSONRequestSearch.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
                assertThat(testIncomingJSONRequestSearch.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
                assertThat(testIncomingJSONRequestSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testIncomingJSONRequestSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testIncomingJSONRequestSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testIncomingJSONRequestSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testIncomingJSONRequestSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testIncomingJSONRequestSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testIncomingJSONRequestSearch.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
                assertThat(testIncomingJSONRequestSearch.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
                assertThat(testIncomingJSONRequestSearch.getFreeField7ContentType()).isEqualTo(UPDATED_FREE_FIELD_7_CONTENT_TYPE);
                assertThat(testIncomingJSONRequestSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testIncomingJSONRequestSearch.getFreeField8ContentType()).isEqualTo(UPDATED_FREE_FIELD_8_CONTENT_TYPE);
                assertThat(testIncomingJSONRequestSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testIncomingJSONRequestSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testIncomingJSONRequestSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testIncomingJSONRequestSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testIncomingJSONRequestSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testIncomingJSONRequestSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testIncomingJSONRequestSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testIncomingJSONRequestSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testIncomingJSONRequestSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testIncomingJSONRequestSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testIncomingJSONRequestSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testIncomingJSONRequestSearch.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
                assertThat(testIncomingJSONRequestSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testIncomingJSONRequestSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testIncomingJSONRequestSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testIncomingJSONRequestSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testIncomingJSONRequestSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, incomingJSONRequest.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateIncomingJSONRequestWithPatch() throws Exception {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();

        // Update the incomingJSONRequest using partial update
        IncomingJSONRequest partialUpdatedIncomingJSONRequest = new IncomingJSONRequest();
        partialUpdatedIncomingJSONRequest.setId(incomingJSONRequest.getId());

        partialUpdatedIncomingJSONRequest
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .token(UPDATED_TOKEN)
            .requestJson(UPDATED_REQUEST_JSON)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField8ContentType(UPDATED_FREE_FIELD_8_CONTENT_TYPE)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField19(UPDATED_FREE_FIELD_19)
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdBy(UPDATED_CREATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIncomingJSONRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIncomingJSONRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONRequest testIncomingJSONRequest = incomingJSONRequestList.get(incomingJSONRequestList.size() - 1);
        assertThat(testIncomingJSONRequest.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONRequest.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONRequest.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONRequest.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getFromEndpoint()).isEqualTo(DEFAULT_FROM_ENDPOINT);
        assertThat(testIncomingJSONRequest.getToEndpoint()).isEqualTo(DEFAULT_TO_ENDPOINT);
        assertThat(testIncomingJSONRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testIncomingJSONRequest.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testIncomingJSONRequest.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testIncomingJSONRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testIncomingJSONRequest.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONRequest.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testIncomingJSONRequest.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testIncomingJSONRequest.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testIncomingJSONRequest.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONRequest.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONRequest.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testIncomingJSONRequest.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testIncomingJSONRequest.getFreeField7ContentType()).isEqualTo(DEFAULT_FREE_FIELD_7_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONRequest.getFreeField8ContentType()).isEqualTo(UPDATED_FREE_FIELD_8_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testIncomingJSONRequest.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testIncomingJSONRequest.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testIncomingJSONRequest.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONRequest.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONRequest.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONRequest.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONRequest.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONRequest.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONRequest.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONRequest.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONRequest.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testIncomingJSONRequest.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONRequest.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONRequest.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncomingJSONRequest.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncomingJSONRequest.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateIncomingJSONRequestWithPatch() throws Exception {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();

        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();

        // Update the incomingJSONRequest using partial update
        IncomingJSONRequest partialUpdatedIncomingJSONRequest = new IncomingJSONRequest();
        partialUpdatedIncomingJSONRequest.setId(incomingJSONRequest.getId());

        partialUpdatedIncomingJSONRequest
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transactionId(UPDATED_TRANSACTION_ID)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .requestJson(UPDATED_REQUEST_JSON)
            .requestType(UPDATED_REQUEST_TYPE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField7ContentType(UPDATED_FREE_FIELD_7_CONTENT_TYPE)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField8ContentType(UPDATED_FREE_FIELD_8_CONTENT_TYPE)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField19(UPDATED_FREE_FIELD_19)
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIncomingJSONRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIncomingJSONRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONRequest testIncomingJSONRequest = incomingJSONRequestList.get(incomingJSONRequestList.size() - 1);
        assertThat(testIncomingJSONRequest.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONRequest.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONRequest.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONRequest.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testIncomingJSONRequest.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
        assertThat(testIncomingJSONRequest.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
        assertThat(testIncomingJSONRequest.getRequestJson()).isEqualTo(UPDATED_REQUEST_JSON);
        assertThat(testIncomingJSONRequest.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testIncomingJSONRequest.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testIncomingJSONRequest.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
        assertThat(testIncomingJSONRequest.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONRequest.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testIncomingJSONRequest.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testIncomingJSONRequest.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testIncomingJSONRequest.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testIncomingJSONRequest.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONRequest.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testIncomingJSONRequest.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testIncomingJSONRequest.getFreeField7ContentType()).isEqualTo(UPDATED_FREE_FIELD_7_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONRequest.getFreeField8ContentType()).isEqualTo(UPDATED_FREE_FIELD_8_CONTENT_TYPE);
        assertThat(testIncomingJSONRequest.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testIncomingJSONRequest.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testIncomingJSONRequest.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testIncomingJSONRequest.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONRequest.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONRequest.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testIncomingJSONRequest.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONRequest.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONRequest.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testIncomingJSONRequest.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testIncomingJSONRequest.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONRequest.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testIncomingJSONRequest.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONRequest.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncomingJSONRequest.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncomingJSONRequest.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncomingJSONRequest.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, incomingJSONRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamIncomingJSONRequest() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        incomingJSONRequest.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONRequest))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IncomingJSONRequest in the database
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteIncomingJSONRequest() {
        // Initialize the database
        incomingJSONRequestRepository.save(incomingJSONRequest).block();
        incomingJSONRequestRepository.save(incomingJSONRequest).block();
        incomingJSONRequestSearchRepository.save(incomingJSONRequest).block();

        int databaseSizeBeforeDelete = incomingJSONRequestRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the incomingJSONRequest
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, incomingJSONRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<IncomingJSONRequest> incomingJSONRequestList = incomingJSONRequestRepository.findAll().collectList().block();
        assertThat(incomingJSONRequestList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONRequestSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchIncomingJSONRequest() {
        // Initialize the database
        incomingJSONRequest = incomingJSONRequestRepository.save(incomingJSONRequest).block();
        incomingJSONRequestSearchRepository.save(incomingJSONRequest).block();

        // Search the incomingJSONRequest
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + incomingJSONRequest.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(incomingJSONRequest.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].fromEndpoint")
            .value(hasItem(DEFAULT_FROM_ENDPOINT))
            .jsonPath("$.[*].toEndpoint")
            .value(hasItem(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.[*].requestJson")
            .value(hasItem(DEFAULT_REQUEST_JSON.toString()))
            .jsonPath("$.[*].requestType")
            .value(hasItem(DEFAULT_REQUEST_TYPE))
            .jsonPath("$.[*].requestDescription")
            .value(hasItem(DEFAULT_REQUEST_DESCRIPTION))
            .jsonPath("$.[*].requestStatus")
            .value(hasItem(DEFAULT_REQUEST_STATUS))
            .jsonPath("$.[*].additionalInfo")
            .value(hasItem(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3.toString()))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4.toString()))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.[*].freeField6ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.[*].freeField7ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_7_CONTENT_TYPE))
            .jsonPath("$.[*].freeField7")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_7)))
            .jsonPath("$.[*].freeField8ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_8_CONTENT_TYPE))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_8)))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10.booleanValue()))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11.booleanValue()))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13.toString()))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(DEFAULT_FREE_FIELD_15))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(DEFAULT_FREE_FIELD_18))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
            .jsonPath("$.[*].freeField20")
            .value(hasItem(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
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
