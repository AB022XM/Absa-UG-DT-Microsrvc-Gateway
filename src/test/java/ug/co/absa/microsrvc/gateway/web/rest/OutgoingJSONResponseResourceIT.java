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
import ug.co.absa.microsrvc.gateway.domain.OutgoingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.OutgoingJSONResponseRepository;
import ug.co.absa.microsrvc.gateway.repository.search.OutgoingJSONResponseSearchRepository;

/**
 * Integration tests for the {@link OutgoingJSONResponseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class OutgoingJSONResponseResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_TO_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_TO_ENDPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_FROM_ENDPOINT = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/outgoing-json-responses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/outgoing-json-responses";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OutgoingJSONResponseRepository outgoingJSONResponseRepository;

    @Autowired
    private OutgoingJSONResponseSearchRepository outgoingJSONResponseSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private OutgoingJSONResponse outgoingJSONResponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutgoingJSONResponse createEntity(EntityManager em) {
        OutgoingJSONResponse outgoingJSONResponse = new OutgoingJSONResponse()
            .recordId(DEFAULT_RECORD_ID)
            .responseId(DEFAULT_RESPONSE_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .responseJson(DEFAULT_RESPONSE_JSON)
            .responseType(DEFAULT_RESPONSE_TYPE)
            .responseDescription(DEFAULT_RESPONSE_DESCRIPTION)
            .toEndpoint(DEFAULT_TO_ENDPOINT)
            .fromEndpoint(DEFAULT_FROM_ENDPOINT)
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
        return outgoingJSONResponse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutgoingJSONResponse createUpdatedEntity(EntityManager em) {
        OutgoingJSONResponse outgoingJSONResponse = new OutgoingJSONResponse()
            .recordId(UPDATED_RECORD_ID)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
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
        return outgoingJSONResponse;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(OutgoingJSONResponse.class).block();
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
        outgoingJSONResponseSearchRepository.deleteAll().block();
        assertThat(outgoingJSONResponseSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        outgoingJSONResponse = createEntity(em);
    }

    @Test
    void createOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeCreate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        // Create the OutgoingJSONResponse
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        OutgoingJSONResponse testOutgoingJSONResponse = outgoingJSONResponseList.get(outgoingJSONResponseList.size() - 1);
        assertThat(testOutgoingJSONResponse.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testOutgoingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testOutgoingJSONResponse.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testOutgoingJSONResponse.getResponseJson()).isEqualTo(DEFAULT_RESPONSE_JSON);
        assertThat(testOutgoingJSONResponse.getResponseType()).isEqualTo(DEFAULT_RESPONSE_TYPE);
        assertThat(testOutgoingJSONResponse.getResponseDescription()).isEqualTo(DEFAULT_RESPONSE_DESCRIPTION);
        assertThat(testOutgoingJSONResponse.getToEndpoint()).isEqualTo(DEFAULT_TO_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getFromEndpoint()).isEqualTo(DEFAULT_FROM_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testOutgoingJSONResponse.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testOutgoingJSONResponse.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testOutgoingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testOutgoingJSONResponse.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testOutgoingJSONResponse.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testOutgoingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testOutgoingJSONResponse.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testOutgoingJSONResponse.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testOutgoingJSONResponse.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testOutgoingJSONResponse.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testOutgoingJSONResponse.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testOutgoingJSONResponse.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testOutgoingJSONResponse.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testOutgoingJSONResponse.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testOutgoingJSONResponse.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testOutgoingJSONResponse.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testOutgoingJSONResponse.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testOutgoingJSONResponse.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testOutgoingJSONResponse.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testOutgoingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testOutgoingJSONResponse.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testOutgoingJSONResponse.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testOutgoingJSONResponse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testOutgoingJSONResponse.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOutgoingJSONResponse.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testOutgoingJSONResponse.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createOutgoingJSONResponseWithExistingId() throws Exception {
        // Create the OutgoingJSONResponse with an existing ID
        outgoingJSONResponse.setId(1L);

        int databaseSizeBeforeCreate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkResponseTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        // set the field null
        outgoingJSONResponse.setResponseType(null);

        // Create the OutgoingJSONResponse, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        // set the field null
        outgoingJSONResponse.setTimestamp(null);

        // Create the OutgoingJSONResponse, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllOutgoingJSONResponsesAsStream() {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        List<OutgoingJSONResponse> outgoingJSONResponseList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(OutgoingJSONResponse.class)
            .getResponseBody()
            .filter(outgoingJSONResponse::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(outgoingJSONResponseList).isNotNull();
        assertThat(outgoingJSONResponseList).hasSize(1);
        OutgoingJSONResponse testOutgoingJSONResponse = outgoingJSONResponseList.get(0);
        assertThat(testOutgoingJSONResponse.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testOutgoingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testOutgoingJSONResponse.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testOutgoingJSONResponse.getResponseJson()).isEqualTo(DEFAULT_RESPONSE_JSON);
        assertThat(testOutgoingJSONResponse.getResponseType()).isEqualTo(DEFAULT_RESPONSE_TYPE);
        assertThat(testOutgoingJSONResponse.getResponseDescription()).isEqualTo(DEFAULT_RESPONSE_DESCRIPTION);
        assertThat(testOutgoingJSONResponse.getToEndpoint()).isEqualTo(DEFAULT_TO_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getFromEndpoint()).isEqualTo(DEFAULT_FROM_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getResponseStatus()).isEqualTo(DEFAULT_RESPONSE_STATUS);
        assertThat(testOutgoingJSONResponse.getAdditionalInfo()).isEqualTo(DEFAULT_ADDITIONAL_INFO);
        assertThat(testOutgoingJSONResponse.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testOutgoingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testOutgoingJSONResponse.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testOutgoingJSONResponse.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testOutgoingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testOutgoingJSONResponse.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testOutgoingJSONResponse.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testOutgoingJSONResponse.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testOutgoingJSONResponse.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testOutgoingJSONResponse.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testOutgoingJSONResponse.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testOutgoingJSONResponse.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testOutgoingJSONResponse.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testOutgoingJSONResponse.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testOutgoingJSONResponse.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testOutgoingJSONResponse.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testOutgoingJSONResponse.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testOutgoingJSONResponse.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testOutgoingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testOutgoingJSONResponse.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testOutgoingJSONResponse.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testOutgoingJSONResponse.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testOutgoingJSONResponse.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOutgoingJSONResponse.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testOutgoingJSONResponse.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllOutgoingJSONResponses() {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        // Get all the outgoingJSONResponseList
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
            .value(hasItem(outgoingJSONResponse.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
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
            .jsonPath("$.[*].toEndpoint")
            .value(hasItem(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.[*].fromEndpoint")
            .value(hasItem(DEFAULT_FROM_ENDPOINT))
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
    void getOutgoingJSONResponse() {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        // Get the outgoingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, outgoingJSONResponse.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(outgoingJSONResponse.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
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
            .jsonPath("$.toEndpoint")
            .value(is(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.fromEndpoint")
            .value(is(DEFAULT_FROM_ENDPOINT))
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
    void getNonExistingOutgoingJSONResponse() {
        // Get the outgoingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingOutgoingJSONResponse() throws Exception {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        outgoingJSONResponseSearchRepository.save(outgoingJSONResponse).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());

        // Update the outgoingJSONResponse
        OutgoingJSONResponse updatedOutgoingJSONResponse = outgoingJSONResponseRepository.findById(outgoingJSONResponse.getId()).block();
        updatedOutgoingJSONResponse
            .recordId(UPDATED_RECORD_ID)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
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
            .uri(ENTITY_API_URL_ID, updatedOutgoingJSONResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedOutgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        OutgoingJSONResponse testOutgoingJSONResponse = outgoingJSONResponseList.get(outgoingJSONResponseList.size() - 1);
        assertThat(testOutgoingJSONResponse.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testOutgoingJSONResponse.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
        assertThat(testOutgoingJSONResponse.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testOutgoingJSONResponse.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
        assertThat(testOutgoingJSONResponse.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
        assertThat(testOutgoingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testOutgoingJSONResponse.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testOutgoingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testOutgoingJSONResponse.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testOutgoingJSONResponse.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
        assertThat(testOutgoingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testOutgoingJSONResponse.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testOutgoingJSONResponse.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testOutgoingJSONResponse.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testOutgoingJSONResponse.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testOutgoingJSONResponse.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testOutgoingJSONResponse.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testOutgoingJSONResponse.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testOutgoingJSONResponse.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testOutgoingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testOutgoingJSONResponse.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testOutgoingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testOutgoingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testOutgoingJSONResponse.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testOutgoingJSONResponse.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testOutgoingJSONResponse.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testOutgoingJSONResponse.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testOutgoingJSONResponse.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testOutgoingJSONResponse.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testOutgoingJSONResponse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOutgoingJSONResponse.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOutgoingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testOutgoingJSONResponse.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<OutgoingJSONResponse> outgoingJSONResponseSearchList = IterableUtils.toList(
                    outgoingJSONResponseSearchRepository.findAll().collectList().block()
                );
                OutgoingJSONResponse testOutgoingJSONResponseSearch = outgoingJSONResponseSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testOutgoingJSONResponseSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testOutgoingJSONResponseSearch.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
                assertThat(testOutgoingJSONResponseSearch.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
                assertThat(testOutgoingJSONResponseSearch.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
                assertThat(testOutgoingJSONResponseSearch.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
                assertThat(testOutgoingJSONResponseSearch.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
                assertThat(testOutgoingJSONResponseSearch.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
                assertThat(testOutgoingJSONResponseSearch.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
                assertThat(testOutgoingJSONResponseSearch.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
                assertThat(testOutgoingJSONResponseSearch.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
                assertThat(testOutgoingJSONResponseSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testOutgoingJSONResponseSearch.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
                assertThat(testOutgoingJSONResponseSearch.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
                assertThat(testOutgoingJSONResponseSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testOutgoingJSONResponseSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testOutgoingJSONResponseSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testOutgoingJSONResponseSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testOutgoingJSONResponseSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testOutgoingJSONResponseSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testOutgoingJSONResponseSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testOutgoingJSONResponseSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testOutgoingJSONResponseSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testOutgoingJSONResponseSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testOutgoingJSONResponseSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testOutgoingJSONResponseSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testOutgoingJSONResponseSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testOutgoingJSONResponseSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testOutgoingJSONResponseSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testOutgoingJSONResponseSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testOutgoingJSONResponseSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testOutgoingJSONResponseSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testOutgoingJSONResponseSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testOutgoingJSONResponseSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testOutgoingJSONResponseSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testOutgoingJSONResponseSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testOutgoingJSONResponseSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testOutgoingJSONResponseSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, outgoingJSONResponse.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateOutgoingJSONResponseWithPatch() throws Exception {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();

        // Update the outgoingJSONResponse using partial update
        OutgoingJSONResponse partialUpdatedOutgoingJSONResponse = new OutgoingJSONResponse();
        partialUpdatedOutgoingJSONResponse.setId(outgoingJSONResponse.getId());

        partialUpdatedOutgoingJSONResponse
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
            .responseStatus(UPDATED_RESPONSE_STATUS)
            .additionalInfo(UPDATED_ADDITIONAL_INFO)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedOutgoingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedOutgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        OutgoingJSONResponse testOutgoingJSONResponse = outgoingJSONResponseList.get(outgoingJSONResponseList.size() - 1);
        assertThat(testOutgoingJSONResponse.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testOutgoingJSONResponse.getResponseId()).isEqualTo(DEFAULT_RESPONSE_ID);
        assertThat(testOutgoingJSONResponse.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testOutgoingJSONResponse.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
        assertThat(testOutgoingJSONResponse.getResponseType()).isEqualTo(DEFAULT_RESPONSE_TYPE);
        assertThat(testOutgoingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testOutgoingJSONResponse.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testOutgoingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testOutgoingJSONResponse.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testOutgoingJSONResponse.getExceptionMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        assertThat(testOutgoingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testOutgoingJSONResponse.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testOutgoingJSONResponse.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testOutgoingJSONResponse.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testOutgoingJSONResponse.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testOutgoingJSONResponse.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testOutgoingJSONResponse.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testOutgoingJSONResponse.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testOutgoingJSONResponse.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testOutgoingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testOutgoingJSONResponse.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testOutgoingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testOutgoingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testOutgoingJSONResponse.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testOutgoingJSONResponse.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testOutgoingJSONResponse.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testOutgoingJSONResponse.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testOutgoingJSONResponse.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testOutgoingJSONResponse.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testOutgoingJSONResponse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOutgoingJSONResponse.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOutgoingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testOutgoingJSONResponse.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateOutgoingJSONResponseWithPatch() throws Exception {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();

        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();

        // Update the outgoingJSONResponse using partial update
        OutgoingJSONResponse partialUpdatedOutgoingJSONResponse = new OutgoingJSONResponse();
        partialUpdatedOutgoingJSONResponse.setId(outgoingJSONResponse.getId());

        partialUpdatedOutgoingJSONResponse
            .recordId(UPDATED_RECORD_ID)
            .responseId(UPDATED_RESPONSE_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .responseJson(UPDATED_RESPONSE_JSON)
            .responseType(UPDATED_RESPONSE_TYPE)
            .responseDescription(UPDATED_RESPONSE_DESCRIPTION)
            .toEndpoint(UPDATED_TO_ENDPOINT)
            .fromEndpoint(UPDATED_FROM_ENDPOINT)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedOutgoingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedOutgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        OutgoingJSONResponse testOutgoingJSONResponse = outgoingJSONResponseList.get(outgoingJSONResponseList.size() - 1);
        assertThat(testOutgoingJSONResponse.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testOutgoingJSONResponse.getResponseId()).isEqualTo(UPDATED_RESPONSE_ID);
        assertThat(testOutgoingJSONResponse.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testOutgoingJSONResponse.getResponseJson()).isEqualTo(UPDATED_RESPONSE_JSON);
        assertThat(testOutgoingJSONResponse.getResponseType()).isEqualTo(UPDATED_RESPONSE_TYPE);
        assertThat(testOutgoingJSONResponse.getResponseDescription()).isEqualTo(UPDATED_RESPONSE_DESCRIPTION);
        assertThat(testOutgoingJSONResponse.getToEndpoint()).isEqualTo(UPDATED_TO_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getFromEndpoint()).isEqualTo(UPDATED_FROM_ENDPOINT);
        assertThat(testOutgoingJSONResponse.getResponseStatus()).isEqualTo(UPDATED_RESPONSE_STATUS);
        assertThat(testOutgoingJSONResponse.getAdditionalInfo()).isEqualTo(UPDATED_ADDITIONAL_INFO);
        assertThat(testOutgoingJSONResponse.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testOutgoingJSONResponse.getExceptionMessage()).isEqualTo(UPDATED_EXCEPTION_MESSAGE);
        assertThat(testOutgoingJSONResponse.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testOutgoingJSONResponse.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testOutgoingJSONResponse.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testOutgoingJSONResponse.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testOutgoingJSONResponse.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testOutgoingJSONResponse.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testOutgoingJSONResponse.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testOutgoingJSONResponse.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testOutgoingJSONResponse.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testOutgoingJSONResponse.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testOutgoingJSONResponse.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testOutgoingJSONResponse.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testOutgoingJSONResponse.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testOutgoingJSONResponse.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testOutgoingJSONResponse.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testOutgoingJSONResponse.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testOutgoingJSONResponse.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testOutgoingJSONResponse.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testOutgoingJSONResponse.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testOutgoingJSONResponse.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testOutgoingJSONResponse.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOutgoingJSONResponse.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOutgoingJSONResponse.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testOutgoingJSONResponse.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, outgoingJSONResponse.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamOutgoingJSONResponse() throws Exception {
        int databaseSizeBeforeUpdate = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        outgoingJSONResponse.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(outgoingJSONResponse))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the OutgoingJSONResponse in the database
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteOutgoingJSONResponse() {
        // Initialize the database
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();
        outgoingJSONResponseRepository.save(outgoingJSONResponse).block();
        outgoingJSONResponseSearchRepository.save(outgoingJSONResponse).block();

        int databaseSizeBeforeDelete = outgoingJSONResponseRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the outgoingJSONResponse
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, outgoingJSONResponse.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<OutgoingJSONResponse> outgoingJSONResponseList = outgoingJSONResponseRepository.findAll().collectList().block();
        assertThat(outgoingJSONResponseList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(outgoingJSONResponseSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchOutgoingJSONResponse() {
        // Initialize the database
        outgoingJSONResponse = outgoingJSONResponseRepository.save(outgoingJSONResponse).block();
        outgoingJSONResponseSearchRepository.save(outgoingJSONResponse).block();

        // Search the outgoingJSONResponse
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + outgoingJSONResponse.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(outgoingJSONResponse.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
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
            .jsonPath("$.[*].toEndpoint")
            .value(hasItem(DEFAULT_TO_ENDPOINT))
            .jsonPath("$.[*].fromEndpoint")
            .value(hasItem(DEFAULT_FROM_ENDPOINT))
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
