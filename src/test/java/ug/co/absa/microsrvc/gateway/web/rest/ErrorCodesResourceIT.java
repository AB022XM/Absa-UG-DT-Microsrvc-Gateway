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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.IntegrationTest;
import ug.co.absa.microsrvc.gateway.domain.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.ErrorCodesRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ErrorCodesSearchRepository;

/**
 * Integration tests for the {@link ErrorCodesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ErrorCodesResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_TRANID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TRANID = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_CODE = "BBBBBBBBBB";

    private static final ErrorMessage DEFAULT_ERROR_MESSAGE = ErrorMessage.TRANSACTIONSUCCESS;
    private static final ErrorMessage UPDATED_ERROR_MESSAGE = ErrorMessage.TRANSACTIONFAILED;

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

    private static final String DEFAULT_CUSTOMER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FIELD_8 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/error-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/error-codes";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ErrorCodesRepository errorCodesRepository;

    @Autowired
    private ErrorCodesSearchRepository errorCodesSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ErrorCodes errorCodes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorCodes createEntity(EntityManager em) {
        ErrorCodes errorCodes = new ErrorCodes()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(DEFAULT_EXTERNAL_TRANID)
            .errorCode(DEFAULT_ERROR_CODE)
            .errorMessage(DEFAULT_ERROR_MESSAGE)
            .responseCode(DEFAULT_RESPONSE_CODE)
            .responseMessage(DEFAULT_RESPONSE_MESSAGE)
            .responseBody(DEFAULT_RESPONSE_BODY)
            .timestamp(DEFAULT_TIMESTAMP)
            .requestRef(DEFAULT_REQUEST_REF)
            .status(DEFAULT_STATUS)
            .customerField1(DEFAULT_CUSTOMER_FIELD_1)
            .customerField2(DEFAULT_CUSTOMER_FIELD_2)
            .customerField3(DEFAULT_CUSTOMER_FIELD_3)
            .customerField4(DEFAULT_CUSTOMER_FIELD_4)
            .customerField5(DEFAULT_CUSTOMER_FIELD_5)
            .customerField6(DEFAULT_CUSTOMER_FIELD_6)
            .customerField7(DEFAULT_CUSTOMER_FIELD_7)
            .customerField8(DEFAULT_CUSTOMER_FIELD_8)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return errorCodes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorCodes createUpdatedEntity(EntityManager em) {
        ErrorCodes errorCodes = new ErrorCodes()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .customerField1(UPDATED_CUSTOMER_FIELD_1)
            .customerField2(UPDATED_CUSTOMER_FIELD_2)
            .customerField3(UPDATED_CUSTOMER_FIELD_3)
            .customerField4(UPDATED_CUSTOMER_FIELD_4)
            .customerField5(UPDATED_CUSTOMER_FIELD_5)
            .customerField6(UPDATED_CUSTOMER_FIELD_6)
            .customerField7(UPDATED_CUSTOMER_FIELD_7)
            .customerField8(UPDATED_CUSTOMER_FIELD_8)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return errorCodes;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ErrorCodes.class).block();
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
        errorCodesSearchRepository.deleteAll().block();
        assertThat(errorCodesSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        errorCodes = createEntity(em);
    }

    @Test
    void createErrorCodes() throws Exception {
        int databaseSizeBeforeCreate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        // Create the ErrorCodes
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        ErrorCodes testErrorCodes = errorCodesList.get(errorCodesList.size() - 1);
        assertThat(testErrorCodes.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testErrorCodes.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testErrorCodes.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testErrorCodes.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testErrorCodes.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testErrorCodes.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testErrorCodes.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testErrorCodes.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testErrorCodes.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testErrorCodes.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testErrorCodes.getResponseBody()).isEqualTo(DEFAULT_RESPONSE_BODY);
        assertThat(testErrorCodes.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testErrorCodes.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testErrorCodes.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testErrorCodes.getCustomerField1()).isEqualTo(DEFAULT_CUSTOMER_FIELD_1);
        assertThat(testErrorCodes.getCustomerField2()).isEqualTo(DEFAULT_CUSTOMER_FIELD_2);
        assertThat(testErrorCodes.getCustomerField3()).isEqualTo(DEFAULT_CUSTOMER_FIELD_3);
        assertThat(testErrorCodes.getCustomerField4()).isEqualTo(DEFAULT_CUSTOMER_FIELD_4);
        assertThat(testErrorCodes.getCustomerField5()).isEqualTo(DEFAULT_CUSTOMER_FIELD_5);
        assertThat(testErrorCodes.getCustomerField6()).isEqualTo(DEFAULT_CUSTOMER_FIELD_6);
        assertThat(testErrorCodes.getCustomerField7()).isEqualTo(DEFAULT_CUSTOMER_FIELD_7);
        assertThat(testErrorCodes.getCustomerField8()).isEqualTo(DEFAULT_CUSTOMER_FIELD_8);
        assertThat(testErrorCodes.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testErrorCodes.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testErrorCodes.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testErrorCodes.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createErrorCodesWithExistingId() throws Exception {
        // Create the ErrorCodes with an existing ID
        errorCodes.setId(1L);

        int databaseSizeBeforeCreate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        // set the field null
        errorCodes.setPaymentItemCode(null);

        // Create the ErrorCodes, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkErrorCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        // set the field null
        errorCodes.setErrorCode(null);

        // Create the ErrorCodes, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        // set the field null
        errorCodes.setCreatedAt(null);

        // Create the ErrorCodes, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllErrorCodesAsStream() {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        List<ErrorCodes> errorCodesList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ErrorCodes.class)
            .getResponseBody()
            .filter(errorCodes::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(errorCodesList).isNotNull();
        assertThat(errorCodesList).hasSize(1);
        ErrorCodes testErrorCodes = errorCodesList.get(0);
        assertThat(testErrorCodes.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testErrorCodes.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testErrorCodes.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testErrorCodes.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testErrorCodes.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testErrorCodes.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testErrorCodes.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testErrorCodes.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testErrorCodes.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testErrorCodes.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testErrorCodes.getResponseBody()).isEqualTo(DEFAULT_RESPONSE_BODY);
        assertThat(testErrorCodes.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testErrorCodes.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testErrorCodes.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testErrorCodes.getCustomerField1()).isEqualTo(DEFAULT_CUSTOMER_FIELD_1);
        assertThat(testErrorCodes.getCustomerField2()).isEqualTo(DEFAULT_CUSTOMER_FIELD_2);
        assertThat(testErrorCodes.getCustomerField3()).isEqualTo(DEFAULT_CUSTOMER_FIELD_3);
        assertThat(testErrorCodes.getCustomerField4()).isEqualTo(DEFAULT_CUSTOMER_FIELD_4);
        assertThat(testErrorCodes.getCustomerField5()).isEqualTo(DEFAULT_CUSTOMER_FIELD_5);
        assertThat(testErrorCodes.getCustomerField6()).isEqualTo(DEFAULT_CUSTOMER_FIELD_6);
        assertThat(testErrorCodes.getCustomerField7()).isEqualTo(DEFAULT_CUSTOMER_FIELD_7);
        assertThat(testErrorCodes.getCustomerField8()).isEqualTo(DEFAULT_CUSTOMER_FIELD_8);
        assertThat(testErrorCodes.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testErrorCodes.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testErrorCodes.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testErrorCodes.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllErrorCodes() {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        // Get all the errorCodesList
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
            .value(hasItem(errorCodes.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()))
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
            .jsonPath("$.[*].customerField1")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_1))
            .jsonPath("$.[*].customerField2")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_2))
            .jsonPath("$.[*].customerField3")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_3))
            .jsonPath("$.[*].customerField4")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_4))
            .jsonPath("$.[*].customerField5")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_5))
            .jsonPath("$.[*].customerField6")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_6))
            .jsonPath("$.[*].customerField7")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_7))
            .jsonPath("$.[*].customerField8")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_8))
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
    void getErrorCodes() {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        // Get the errorCodes
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, errorCodes.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(errorCodes.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.paymentItemCode")
            .value(is(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.externalTranid")
            .value(is(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.errorCode")
            .value(is(DEFAULT_ERROR_CODE))
            .jsonPath("$.errorMessage")
            .value(is(DEFAULT_ERROR_MESSAGE.toString()))
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
            .jsonPath("$.customerField1")
            .value(is(DEFAULT_CUSTOMER_FIELD_1))
            .jsonPath("$.customerField2")
            .value(is(DEFAULT_CUSTOMER_FIELD_2))
            .jsonPath("$.customerField3")
            .value(is(DEFAULT_CUSTOMER_FIELD_3))
            .jsonPath("$.customerField4")
            .value(is(DEFAULT_CUSTOMER_FIELD_4))
            .jsonPath("$.customerField5")
            .value(is(DEFAULT_CUSTOMER_FIELD_5))
            .jsonPath("$.customerField6")
            .value(is(DEFAULT_CUSTOMER_FIELD_6))
            .jsonPath("$.customerField7")
            .value(is(DEFAULT_CUSTOMER_FIELD_7))
            .jsonPath("$.customerField8")
            .value(is(DEFAULT_CUSTOMER_FIELD_8))
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
    void getNonExistingErrorCodes() {
        // Get the errorCodes
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingErrorCodes() throws Exception {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        errorCodesSearchRepository.save(errorCodes).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());

        // Update the errorCodes
        ErrorCodes updatedErrorCodes = errorCodesRepository.findById(errorCodes.getId()).block();
        updatedErrorCodes
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .customerField1(UPDATED_CUSTOMER_FIELD_1)
            .customerField2(UPDATED_CUSTOMER_FIELD_2)
            .customerField3(UPDATED_CUSTOMER_FIELD_3)
            .customerField4(UPDATED_CUSTOMER_FIELD_4)
            .customerField5(UPDATED_CUSTOMER_FIELD_5)
            .customerField6(UPDATED_CUSTOMER_FIELD_6)
            .customerField7(UPDATED_CUSTOMER_FIELD_7)
            .customerField8(UPDATED_CUSTOMER_FIELD_8)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedErrorCodes.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedErrorCodes))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        ErrorCodes testErrorCodes = errorCodesList.get(errorCodesList.size() - 1);
        assertThat(testErrorCodes.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testErrorCodes.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testErrorCodes.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testErrorCodes.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testErrorCodes.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testErrorCodes.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testErrorCodes.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testErrorCodes.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testErrorCodes.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testErrorCodes.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testErrorCodes.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
        assertThat(testErrorCodes.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testErrorCodes.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testErrorCodes.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testErrorCodes.getCustomerField1()).isEqualTo(UPDATED_CUSTOMER_FIELD_1);
        assertThat(testErrorCodes.getCustomerField2()).isEqualTo(UPDATED_CUSTOMER_FIELD_2);
        assertThat(testErrorCodes.getCustomerField3()).isEqualTo(UPDATED_CUSTOMER_FIELD_3);
        assertThat(testErrorCodes.getCustomerField4()).isEqualTo(UPDATED_CUSTOMER_FIELD_4);
        assertThat(testErrorCodes.getCustomerField5()).isEqualTo(UPDATED_CUSTOMER_FIELD_5);
        assertThat(testErrorCodes.getCustomerField6()).isEqualTo(UPDATED_CUSTOMER_FIELD_6);
        assertThat(testErrorCodes.getCustomerField7()).isEqualTo(UPDATED_CUSTOMER_FIELD_7);
        assertThat(testErrorCodes.getCustomerField8()).isEqualTo(UPDATED_CUSTOMER_FIELD_8);
        assertThat(testErrorCodes.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testErrorCodes.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testErrorCodes.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testErrorCodes.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<ErrorCodes> errorCodesSearchList = IterableUtils.toList(errorCodesSearchRepository.findAll().collectList().block());
                ErrorCodes testErrorCodesSearch = errorCodesSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testErrorCodesSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testErrorCodesSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testErrorCodesSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testErrorCodesSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testErrorCodesSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testErrorCodesSearch.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
                assertThat(testErrorCodesSearch.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
                assertThat(testErrorCodesSearch.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
                assertThat(testErrorCodesSearch.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
                assertThat(testErrorCodesSearch.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
                assertThat(testErrorCodesSearch.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
                assertThat(testErrorCodesSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testErrorCodesSearch.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
                assertThat(testErrorCodesSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testErrorCodesSearch.getCustomerField1()).isEqualTo(UPDATED_CUSTOMER_FIELD_1);
                assertThat(testErrorCodesSearch.getCustomerField2()).isEqualTo(UPDATED_CUSTOMER_FIELD_2);
                assertThat(testErrorCodesSearch.getCustomerField3()).isEqualTo(UPDATED_CUSTOMER_FIELD_3);
                assertThat(testErrorCodesSearch.getCustomerField4()).isEqualTo(UPDATED_CUSTOMER_FIELD_4);
                assertThat(testErrorCodesSearch.getCustomerField5()).isEqualTo(UPDATED_CUSTOMER_FIELD_5);
                assertThat(testErrorCodesSearch.getCustomerField6()).isEqualTo(UPDATED_CUSTOMER_FIELD_6);
                assertThat(testErrorCodesSearch.getCustomerField7()).isEqualTo(UPDATED_CUSTOMER_FIELD_7);
                assertThat(testErrorCodesSearch.getCustomerField8()).isEqualTo(UPDATED_CUSTOMER_FIELD_8);
                assertThat(testErrorCodesSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testErrorCodesSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testErrorCodesSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testErrorCodesSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, errorCodes.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateErrorCodesWithPatch() throws Exception {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();

        // Update the errorCodes using partial update
        ErrorCodes partialUpdatedErrorCodes = new ErrorCodes();
        partialUpdatedErrorCodes.setId(errorCodes.getId());

        partialUpdatedErrorCodes
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .errorCode(UPDATED_ERROR_CODE)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .status(UPDATED_STATUS)
            .customerField1(UPDATED_CUSTOMER_FIELD_1)
            .customerField2(UPDATED_CUSTOMER_FIELD_2)
            .customerField3(UPDATED_CUSTOMER_FIELD_3)
            .customerField5(UPDATED_CUSTOMER_FIELD_5)
            .customerField7(UPDATED_CUSTOMER_FIELD_7)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedErrorCodes.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedErrorCodes))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        ErrorCodes testErrorCodes = errorCodesList.get(errorCodesList.size() - 1);
        assertThat(testErrorCodes.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testErrorCodes.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testErrorCodes.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testErrorCodes.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testErrorCodes.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testErrorCodes.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testErrorCodes.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testErrorCodes.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
        assertThat(testErrorCodes.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testErrorCodes.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testErrorCodes.getResponseBody()).isEqualTo(DEFAULT_RESPONSE_BODY);
        assertThat(testErrorCodes.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testErrorCodes.getRequestRef()).isEqualTo(DEFAULT_REQUEST_REF);
        assertThat(testErrorCodes.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testErrorCodes.getCustomerField1()).isEqualTo(UPDATED_CUSTOMER_FIELD_1);
        assertThat(testErrorCodes.getCustomerField2()).isEqualTo(UPDATED_CUSTOMER_FIELD_2);
        assertThat(testErrorCodes.getCustomerField3()).isEqualTo(UPDATED_CUSTOMER_FIELD_3);
        assertThat(testErrorCodes.getCustomerField4()).isEqualTo(DEFAULT_CUSTOMER_FIELD_4);
        assertThat(testErrorCodes.getCustomerField5()).isEqualTo(UPDATED_CUSTOMER_FIELD_5);
        assertThat(testErrorCodes.getCustomerField6()).isEqualTo(DEFAULT_CUSTOMER_FIELD_6);
        assertThat(testErrorCodes.getCustomerField7()).isEqualTo(UPDATED_CUSTOMER_FIELD_7);
        assertThat(testErrorCodes.getCustomerField8()).isEqualTo(DEFAULT_CUSTOMER_FIELD_8);
        assertThat(testErrorCodes.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testErrorCodes.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testErrorCodes.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testErrorCodes.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateErrorCodesWithPatch() throws Exception {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();

        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();

        // Update the errorCodes using partial update
        ErrorCodes partialUpdatedErrorCodes = new ErrorCodes();
        partialUpdatedErrorCodes.setId(errorCodes.getId());

        partialUpdatedErrorCodes
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseBody(UPDATED_RESPONSE_BODY)
            .timestamp(UPDATED_TIMESTAMP)
            .requestRef(UPDATED_REQUEST_REF)
            .status(UPDATED_STATUS)
            .customerField1(UPDATED_CUSTOMER_FIELD_1)
            .customerField2(UPDATED_CUSTOMER_FIELD_2)
            .customerField3(UPDATED_CUSTOMER_FIELD_3)
            .customerField4(UPDATED_CUSTOMER_FIELD_4)
            .customerField5(UPDATED_CUSTOMER_FIELD_5)
            .customerField6(UPDATED_CUSTOMER_FIELD_6)
            .customerField7(UPDATED_CUSTOMER_FIELD_7)
            .customerField8(UPDATED_CUSTOMER_FIELD_8)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedErrorCodes.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedErrorCodes))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        ErrorCodes testErrorCodes = errorCodesList.get(errorCodesList.size() - 1);
        assertThat(testErrorCodes.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testErrorCodes.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testErrorCodes.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testErrorCodes.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testErrorCodes.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testErrorCodes.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testErrorCodes.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testErrorCodes.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        assertThat(testErrorCodes.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testErrorCodes.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testErrorCodes.getResponseBody()).isEqualTo(UPDATED_RESPONSE_BODY);
        assertThat(testErrorCodes.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testErrorCodes.getRequestRef()).isEqualTo(UPDATED_REQUEST_REF);
        assertThat(testErrorCodes.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testErrorCodes.getCustomerField1()).isEqualTo(UPDATED_CUSTOMER_FIELD_1);
        assertThat(testErrorCodes.getCustomerField2()).isEqualTo(UPDATED_CUSTOMER_FIELD_2);
        assertThat(testErrorCodes.getCustomerField3()).isEqualTo(UPDATED_CUSTOMER_FIELD_3);
        assertThat(testErrorCodes.getCustomerField4()).isEqualTo(UPDATED_CUSTOMER_FIELD_4);
        assertThat(testErrorCodes.getCustomerField5()).isEqualTo(UPDATED_CUSTOMER_FIELD_5);
        assertThat(testErrorCodes.getCustomerField6()).isEqualTo(UPDATED_CUSTOMER_FIELD_6);
        assertThat(testErrorCodes.getCustomerField7()).isEqualTo(UPDATED_CUSTOMER_FIELD_7);
        assertThat(testErrorCodes.getCustomerField8()).isEqualTo(UPDATED_CUSTOMER_FIELD_8);
        assertThat(testErrorCodes.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testErrorCodes.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testErrorCodes.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testErrorCodes.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, errorCodes.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamErrorCodes() throws Exception {
        int databaseSizeBeforeUpdate = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        errorCodes.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(errorCodes))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ErrorCodes in the database
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteErrorCodes() {
        // Initialize the database
        errorCodesRepository.save(errorCodes).block();
        errorCodesRepository.save(errorCodes).block();
        errorCodesSearchRepository.save(errorCodes).block();

        int databaseSizeBeforeDelete = errorCodesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the errorCodes
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, errorCodes.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ErrorCodes> errorCodesList = errorCodesRepository.findAll().collectList().block();
        assertThat(errorCodesList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(errorCodesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchErrorCodes() {
        // Initialize the database
        errorCodes = errorCodesRepository.save(errorCodes).block();
        errorCodesSearchRepository.save(errorCodes).block();

        // Search the errorCodes
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + errorCodes.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(errorCodes.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()))
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
            .jsonPath("$.[*].customerField1")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_1))
            .jsonPath("$.[*].customerField2")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_2))
            .jsonPath("$.[*].customerField3")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_3))
            .jsonPath("$.[*].customerField4")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_4))
            .jsonPath("$.[*].customerField5")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_5))
            .jsonPath("$.[*].customerField6")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_6))
            .jsonPath("$.[*].customerField7")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_7))
            .jsonPath("$.[*].customerField8")
            .value(hasItem(DEFAULT_CUSTOMER_FIELD_8))
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
