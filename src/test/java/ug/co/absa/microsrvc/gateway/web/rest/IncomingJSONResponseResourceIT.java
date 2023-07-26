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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONResponseRepository;
import ug.co.absa.microsrvc.gateway.repository.search.IncomingJSONResponseSearchRepository;

/**
 * Integration tests for the {@link IncomingJSONResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class IncomingJSONResponseResourceIT {

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

    private static final String DEFAULT_RESPONSE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_JSON = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_JSON = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_INFO = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_INFO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EXCEPTION_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_EXCEPTION_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD = "BBBBBBBBBB";

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

    private static final String DEFAULT_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_15 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_15 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_15_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_15_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_18 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_18 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_18_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_18_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/incoming-json-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/incoming-json-responses";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IncomingJSONResponseRepository incomingJSONResponseRepository;

    @Autowired
    private IncomingJSONResponseSearchRepository incomingJSONResponseSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private IncomingJSONResponse incomingJSONResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomingJSONResponse createEntity(EntityManager em) {
        IncomingJSONResponse incomingJSONResponse = new IncomingJSONResponse()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .responseId(DEFAULT_RESPONSE_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .responseJson(DEFAULT_RESPONSE_JSON)
            .responseType(DEFAULT_RESPONSE_TYPE)
            .responseDescription(DEFAULT_RESPONSE_DESCRIPTION)
            .responseStatus(DEFAULT_RESPONSE_STATUS)
            .additionalInfo(DEFAULT_ADDITIONAL_INFO)
            .timestamp(DEFAULT_TIMESTAMP)
            .exceptionMessage(DEFAULT_EXCEPTION_MESSAGE)
            .freeField(DEFAULT_FREE_FIELD)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10)
            .freeField11(DEFAULT_FREE_FIELD_11)
            .freeField12(DEFAULT_FREE_FIELD_12)
            .freeField13(DEFAULT_FREE_FIELD_13)
            .freeField14(DEFAULT_FREE_FIELD_14)
            .freeField15(DEFAULT_FREE_FIELD_15)
            .freeField15ContentType(DEFAULT_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(DEFAULT_FREE_FIELD_16)
            .freeField17(DEFAULT_FREE_FIELD_17)
            .freeField18(DEFAULT_FREE_FIELD_18)
            .freeField18ContentType(DEFAULT_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(DEFAULT_FREE_FIELD_19)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return incomingJSONResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncomingJSONResponse createUpdatedEntity(EntityManager em) {
        IncomingJSONResponse incomingJSONResponse = new IncomingJSONResponse()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .timestamp(UPDATED_TIMESTAMP)
            .exceptionMessage(UPDATED_EXCEPTION_MESSAGE)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return incomingJSONResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(IncomingJSONResponse.class).block();
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
        incomingJSONResponseSearchRepository.deleteAll().block();
        assertThat(incomingJSONResponseSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        incomingJSONResponse = createEntity(em);
    }

    @Test
    void createIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeCreate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        // Create the IncomingJSONResponse
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        IncomingJSONResponse testIncomingJSONResponse = incomingJSONResponseList.get(incomingJSONResponseList.size() - 1);
        assertThat(testIncomingJSONResponse.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testIncomingJSONResponse.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONResponse.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testIncomingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testIncomingJSONResponse.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getResponseJson()).isEqualTo(DEFAULT_RESPONSE_JSON);
        assertThat(testIncomingJSONResponse.getResponseType()).isEqualTo(DEFAULT_RESPONSE_TYPE);
        assertThat(testIncomingJSONResponse.getResponseDescription()).isEqualTo(DEFAULT_RESPONSE_DESCRIPTION);
        assertThat(testIncomingJSONResponse.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testIncomingJSONResponse.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testIncomingJSONResponse.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIncomingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testIncomingJSONResponse.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testIncomingJSONResponse.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testIncomingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testIncomingJSONResponse.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testIncomingJSONResponse.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONResponse.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testIncomingJSONResponse.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testIncomingJSONResponse.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testIncomingJSONResponse.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testIncomingJSONResponse.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testIncomingJSONResponse.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testIncomingJSONResponse.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testIncomingJSONResponse.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testIncomingJSONResponse.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONResponse.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testIncomingJSONResponse.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testIncomingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONResponse.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONResponse.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testIncomingJSONResponse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONResponse.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncomingJSONResponse.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncomingJSONResponse.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createIncomingJSONResponseWithExistingId() throws Exception {
        // Create the IncomingJSONResponse with an existing ID
        incomingJSONResponse.setId(1L);

        int databaseSizeBeforeCreate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkResponseTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        // set the field null
        incomingJSONResponse.setResponseType(null);

        // Create the IncomingJSONResponse, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        // set the field null
        incomingJSONResponse.setTimestamp(null);

        // Create the IncomingJSONResponse, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllIncomingJSONResponsesAsStream() {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        List<IncomingJSONResponse> incomingJSONResponseList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(IncomingJSONResponse.class)
            .getResponseBody()
            .filter(incomingJSONResponse::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(incomingJSONResponseList).isNotNull();
        assertThat(incomingJSONResponseList).hasSize(1);
        IncomingJSONResponse testIncomingJSONResponse = incomingJSONResponseList.get(0);
        assertThat(testIncomingJSONResponse.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testIncomingJSONResponse.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONResponse.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testIncomingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testIncomingJSONResponse.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getResponseJson()).isEqualTo(DEFAULT_RESPONSE_JSON);
        assertThat(testIncomingJSONResponse.getResponseType()).isEqualTo(DEFAULT_RESPONSE_TYPE);
        assertThat(testIncomingJSONResponse.getResponseDescription()).isEqualTo(DEFAULT_RESPONSE_DESCRIPTION);
        assertThat(testIncomingJSONResponse.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testIncomingJSONResponse.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testIncomingJSONResponse.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIncomingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testIncomingJSONResponse.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testIncomingJSONResponse.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testIncomingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testIncomingJSONResponse.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testIncomingJSONResponse.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONResponse.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testIncomingJSONResponse.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testIncomingJSONResponse.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testIncomingJSONResponse.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testIncomingJSONResponse.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testIncomingJSONResponse.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testIncomingJSONResponse.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testIncomingJSONResponse.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testIncomingJSONResponse.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONResponse.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testIncomingJSONResponse.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testIncomingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONResponse.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONResponse.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testIncomingJSONResponse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONResponse.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncomingJSONResponse.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncomingJSONResponse.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllIncomingJSONResponses() {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        // Get all the incomingJSONResponseList
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
            .value(hasItem(incomingJSONResponse.getId().intValue()))
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
            .jsonPath("$.[*].responseId")
            .value(hasItem(DEFAULT_RESPONSE_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].responseJson")
            .value(hasItem(DEFAULT_RESPONSE_JSON.toString()))
            .jsonPath("$.[*].responseType")
            .value(hasItem(DEFAULT_RESPONSE_TYPE))
            .jsonPath("$.[*].responseDescription")
            .value(hasItem(DEFAULT_RESPONSE_DESCRIPTION))
            .jsonPath("$.[*].responseStatus")
            .value(hasItem(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.[*].additionalInfo")
            .value(hasItem(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].exceptionMessage")
            .value(hasItem(DEFAULT_EXCEPTION_MESSAGE.toString()))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.[*].freeField18ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
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
    void getIncomingJSONResponse() {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        // Get the incomingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, incomingJSONResponse.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(incomingJSONResponse.getId().intValue()))
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
            .jsonPath("$.responseId")
            .value(is(DEFAULT_RESPONSE_ID))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.responseJson")
            .value(is(DEFAULT_RESPONSE_JSON.toString()))
            .jsonPath("$.responseType")
            .value(is(DEFAULT_RESPONSE_TYPE))
            .jsonPath("$.responseDescription")
            .value(is(DEFAULT_RESPONSE_DESCRIPTION))
            .jsonPath("$.responseStatus")
            .value(is(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.additionalInfo")
            .value(is(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.exceptionMessage")
            .value(is(DEFAULT_EXCEPTION_MESSAGE.toString()))
            .jsonPath("$.freeField")
            .value(is(DEFAULT_FREE_FIELD))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.freeField2")
            .value(is(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.freeField3")
            .value(is(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.freeField4")
            .value(is(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.freeField5")
            .value(is(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.freeField6")
            .value(is(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.freeField8")
            .value(is(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.freeField9")
            .value(is(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.freeField10")
            .value(is(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.freeField11")
            .value(is(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.freeField12")
            .value(is(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.freeField13")
            .value(is(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.freeField14")
            .value(is(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.freeField15ContentType")
            .value(is(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.freeField15")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.freeField16")
            .value(is(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.freeField17")
            .value(is(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.freeField18ContentType")
            .value(is(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.freeField18")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.freeField19")
            .value(is(DEFAULT_FREE_FIELD_19))
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
    void getNonExistingIncomingJSONResponse() {
        // Get the incomingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingIncomingJSONResponse() throws Exception {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        incomingJSONResponseSearchRepository.save(incomingJSONResponse).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());

        // Update the incomingJSONResponse
        IncomingJSONResponse updatedIncomingJSONResponse = incomingJSONResponseRepository.findById(incomingJSONResponse.getId()).block();
        updatedIncomingJSONResponse
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .timestamp(UPDATED_TIMESTAMP)
            .exceptionMessage(UPDATED_EXCEPTION_MESSAGE)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedIncomingJSONResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedIncomingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONResponse testIncomingJSONResponse = incomingJSONResponseList.get(incomingJSONResponseList.size() - 1);
        assertThat(testIncomingJSONResponse.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONResponse.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONResponse.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONResponse.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
        assertThat(testIncomingJSONResponse.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
        assertThat(testIncomingJSONResponse.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
        assertThat(testIncomingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testIncomingJSONResponse.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testIncomingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONResponse.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONResponse.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
        assertThat(testIncomingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testIncomingJSONResponse.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testIncomingJSONResponse.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testIncomingJSONResponse.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testIncomingJSONResponse.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testIncomingJSONResponse.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONResponse.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testIncomingJSONResponse.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONResponse.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testIncomingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testIncomingJSONResponse.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testIncomingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONResponse.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testIncomingJSONResponse.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONResponse.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONResponse.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testIncomingJSONResponse.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testIncomingJSONResponse.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONResponse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncomingJSONResponse.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncomingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncomingJSONResponse.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<IncomingJSONResponse> incomingJSONResponseSearchList = IterableUtils.toList(
                    incomingJSONResponseSearchRepository.findAll().collectList().block()
                );
                IncomingJSONResponse testIncomingJSONResponseSearch = incomingJSONResponseSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testIncomingJSONResponseSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testIncomingJSONResponseSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testIncomingJSONResponseSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testIncomingJSONResponseSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testIncomingJSONResponseSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testIncomingJSONResponseSearch.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
                assertThat(testIncomingJSONResponseSearch.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
                assertThat(testIncomingJSONResponseSearch.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
                assertThat(testIncomingJSONResponseSearch.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
                assertThat(testIncomingJSONResponseSearch.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
                assertThat(testIncomingJSONResponseSearch.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
                assertThat(testIncomingJSONResponseSearch.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
                assertThat(testIncomingJSONResponseSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testIncomingJSONResponseSearch.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
                assertThat(testIncomingJSONResponseSearch.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
                assertThat(testIncomingJSONResponseSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testIncomingJSONResponseSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testIncomingJSONResponseSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testIncomingJSONResponseSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testIncomingJSONResponseSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testIncomingJSONResponseSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testIncomingJSONResponseSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testIncomingJSONResponseSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testIncomingJSONResponseSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testIncomingJSONResponseSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testIncomingJSONResponseSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testIncomingJSONResponseSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testIncomingJSONResponseSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testIncomingJSONResponseSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testIncomingJSONResponseSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testIncomingJSONResponseSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testIncomingJSONResponseSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testIncomingJSONResponseSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testIncomingJSONResponseSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testIncomingJSONResponseSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testIncomingJSONResponseSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testIncomingJSONResponseSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testIncomingJSONResponseSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testIncomingJSONResponseSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, incomingJSONResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateIncomingJSONResponseWithPatch() throws Exception {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();

        // Update the incomingJSONResponse using partial update
        IncomingJSONResponse partialUpdatedIncomingJSONResponse = new IncomingJSONResponse();
        partialUpdatedIncomingJSONResponse.setId(incomingJSONResponse.getId());

        partialUpdatedIncomingJSONResponse
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .timestamp(UPDATED_TIMESTAMP)
            .freeField(UPDATED_FREE_FIELD)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField19(UPDATED_FREE_FIELD_19)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIncomingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIncomingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONResponse testIncomingJSONResponse = incomingJSONResponseList.get(incomingJSONResponseList.size() - 1);
        assertThat(testIncomingJSONResponse.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONResponse.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONResponse.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testIncomingJSONResponse.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getResponseJson()).isEqualTo(DEFAULT_RESPONSE_JSON);
        assertThat(testIncomingJSONResponse.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
        assertThat(testIncomingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testIncomingJSONResponse.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testIncomingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONResponse.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testIncomingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testIncomingJSONResponse.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testIncomingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testIncomingJSONResponse.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testIncomingJSONResponse.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testIncomingJSONResponse.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONResponse.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testIncomingJSONResponse.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONResponse.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testIncomingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testIncomingJSONResponse.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testIncomingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONResponse.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testIncomingJSONResponse.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONResponse.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testIncomingJSONResponse.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testIncomingJSONResponse.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONResponse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncomingJSONResponse.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncomingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncomingJSONResponse.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateIncomingJSONResponseWithPatch() throws Exception {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();

        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();

        // Update the incomingJSONResponse using partial update
        IncomingJSONResponse partialUpdatedIncomingJSONResponse = new IncomingJSONResponse();
        partialUpdatedIncomingJSONResponse.setId(incomingJSONResponse.getId());

        partialUpdatedIncomingJSONResponse
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .timestamp(UPDATED_TIMESTAMP)
            .exceptionMessage(UPDATED_EXCEPTION_MESSAGE)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIncomingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIncomingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        IncomingJSONResponse testIncomingJSONResponse = incomingJSONResponseList.get(incomingJSONResponseList.size() - 1);
        assertThat(testIncomingJSONResponse.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testIncomingJSONResponse.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testIncomingJSONResponse.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testIncomingJSONResponse.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
        assertThat(testIncomingJSONResponse.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testIncomingJSONResponse.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
        assertThat(testIncomingJSONResponse.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
        assertThat(testIncomingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testIncomingJSONResponse.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testIncomingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testIncomingJSONResponse.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIncomingJSONResponse.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
        assertThat(testIncomingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testIncomingJSONResponse.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testIncomingJSONResponse.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testIncomingJSONResponse.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testIncomingJSONResponse.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testIncomingJSONResponse.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testIncomingJSONResponse.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testIncomingJSONResponse.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testIncomingJSONResponse.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testIncomingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testIncomingJSONResponse.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testIncomingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testIncomingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testIncomingJSONResponse.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testIncomingJSONResponse.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testIncomingJSONResponse.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testIncomingJSONResponse.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testIncomingJSONResponse.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testIncomingJSONResponse.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testIncomingJSONResponse.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testIncomingJSONResponse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncomingJSONResponse.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncomingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncomingJSONResponse.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, incomingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamIncomingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        incomingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(incomingJSONResponse))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IncomingJSONResponse in the database
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteIncomingJSONResponse() {
        // Initialize the database
        incomingJSONResponseRepository.save(incomingJSONResponse).block();
        incomingJSONResponseRepository.save(incomingJSONResponse).block();
        incomingJSONResponseSearchRepository.save(incomingJSONResponse).block();

        int databaseSizeBeforeDelete = incomingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the incomingJSONResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, incomingJSONResponse.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<IncomingJSONResponse> incomingJSONResponseList = incomingJSONResponseRepository.findAll().collectList().block();
        assertThat(incomingJSONResponseList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(incomingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchIncomingJSONResponse() {
        // Initialize the database
        incomingJSONResponse = incomingJSONResponseRepository.save(incomingJSONResponse).block();
        incomingJSONResponseSearchRepository.save(incomingJSONResponse).block();

        // Search the incomingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + incomingJSONResponse.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(incomingJSONResponse.getId().intValue()))
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
            .jsonPath("$.[*].responseId")
            .value(hasItem(DEFAULT_RESPONSE_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].responseJson")
            .value(hasItem(DEFAULT_RESPONSE_JSON.toString()))
            .jsonPath("$.[*].responseType")
            .value(hasItem(DEFAULT_RESPONSE_TYPE))
            .jsonPath("$.[*].responseDescription")
            .value(hasItem(DEFAULT_RESPONSE_DESCRIPTION))
            .jsonPath("$.[*].responseStatus")
            .value(hasItem(DEFAULT_RESPONSE_STATUS))
            .jsonPath("$.[*].additionalInfo")
            .value(hasItem(DEFAULT_ADDITIONAL_INFO.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].exceptionMessage")
            .value(hasItem(DEFAULT_EXCEPTION_MESSAGE.toString()))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.[*].freeField18ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
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
