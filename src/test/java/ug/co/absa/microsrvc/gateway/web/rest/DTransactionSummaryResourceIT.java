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
import ug.co.absa.microsrvc.gateway.domain.DTransactionSummary;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.DTransactionSummaryRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionSummarySearchRepository;

/**
 * Integration tests for the {@link DTransactionSummaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DTransactionSummaryResourceIT {

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

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_AMOUNT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TRANSACTION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRANSACTION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TranStatus DEFAULT_TRANSACTION_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_TRANSACTION_STATUS = TranStatus.SUCCESS;

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

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final ErrorCodes DEFAULT_ERROR_CODE = ErrorCodes.SUCCESS;
    private static final ErrorCodes UPDATED_ERROR_CODE = ErrorCodes.PENDING;

    private static final ErrorMessage DEFAULT_ERROR_MESSAGE = ErrorMessage.TRANSACTIONSUCCESS;
    private static final ErrorMessage UPDATED_ERROR_MESSAGE = ErrorMessage.TRANSACTIONFAILED;

    private static final String ENTITY_API_URL = "/api/d-transaction-summaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/d-transaction-summaries";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DTransactionSummaryRepository dTransactionSummaryRepository;

    @Autowired
    private DTransactionSummarySearchRepository dTransactionSummarySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DTransactionSummary dTransactionSummary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionSummary createEntity(EntityManager em) {
        DTransactionSummary dTransactionSummary = new DTransactionSummary()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .recordId(DEFAULT_RECORD_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .transactionStatus(DEFAULT_TRANSACTION_STATUS)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField6ContentType(DEFAULT_FREE_FIELD_6_CONTENT_TYPE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .errorCode(DEFAULT_ERROR_CODE)
            .errorMessage(DEFAULT_ERROR_MESSAGE);
        return dTransactionSummary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionSummary createUpdatedEntity(EntityManager em) {
        DTransactionSummary dTransactionSummary = new DTransactionSummary()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE);
        return dTransactionSummary;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DTransactionSummary.class).block();
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
        dTransactionSummarySearchRepository.deleteAll().block();
        assertThat(dTransactionSummarySearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        dTransactionSummary = createEntity(em);
    }

    @Test
    void createDTransactionSummary() throws Exception {
        int databaseSizeBeforeCreate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // Create the DTransactionSummary
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DTransactionSummary testDTransactionSummary = dTransactionSummaryList.get(dTransactionSummaryList.size() - 1);
        assertThat(testDTransactionSummary.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionSummary.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionSummary.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionSummary.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionSummary.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDTransactionSummary.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testDTransactionSummary.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionSummary.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testDTransactionSummary.getTransactionStatus()).isEqualTo(DEFAULT_TRANSACTION_STATUS);
        assertThat(testDTransactionSummary.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDTransactionSummary.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDTransactionSummary.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDTransactionSummary.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDTransactionSummary.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDTransactionSummary.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDTransactionSummary.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testDTransactionSummary.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionSummary.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionSummary.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionSummary.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDTransactionSummary.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testDTransactionSummary.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
    }

    @Test
    void createDTransactionSummaryWithExistingId() throws Exception {
        // Create the DTransactionSummary with an existing ID
        dTransactionSummary.setId(1L);

        int databaseSizeBeforeCreate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionSummary.setBillerId(null);

        // Create the DTransactionSummary, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionSummary.setPaymentItemCode(null);

        // Create the DTransactionSummary, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionSummary.setTransactionType(null);

        // Create the DTransactionSummary, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTransactionAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionSummary.setTransactionAmount(null);

        // Create the DTransactionSummary, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionSummary.setCreatedAt(null);

        // Create the DTransactionSummary, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDTransactionSummariesAsStream() {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        List<DTransactionSummary> dTransactionSummaryList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DTransactionSummary.class)
            .getResponseBody()
            .filter(dTransactionSummary::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(dTransactionSummaryList).isNotNull();
        assertThat(dTransactionSummaryList).hasSize(1);
        DTransactionSummary testDTransactionSummary = dTransactionSummaryList.get(0);
        assertThat(testDTransactionSummary.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionSummary.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionSummary.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionSummary.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionSummary.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDTransactionSummary.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testDTransactionSummary.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionSummary.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testDTransactionSummary.getTransactionStatus()).isEqualTo(DEFAULT_TRANSACTION_STATUS);
        assertThat(testDTransactionSummary.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDTransactionSummary.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDTransactionSummary.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDTransactionSummary.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDTransactionSummary.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDTransactionSummary.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDTransactionSummary.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testDTransactionSummary.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionSummary.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionSummary.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionSummary.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDTransactionSummary.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testDTransactionSummary.getErrorMessage()).isEqualTo(DEFAULT_ERROR_MESSAGE);
    }

    @Test
    void getAllDTransactionSummaries() {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        // Get all the dTransactionSummaryList
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
            .value(hasItem(dTransactionSummary.getId().intValue()))
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
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].transactionType")
            .value(hasItem(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].transactionStatus")
            .value(hasItem(DEFAULT_TRANSACTION_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.[*].freeField6ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()));
    }

    @Test
    void getDTransactionSummary() {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        // Get the dTransactionSummary
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dTransactionSummary.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dTransactionSummary.getId().intValue()))
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
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.transactionType")
            .value(is(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.transactionAmount")
            .value(is(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.transactionDate")
            .value(is(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.transactionStatus")
            .value(is(DEFAULT_TRANSACTION_STATUS.toString()))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.freeField2")
            .value(is(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.freeField3")
            .value(is(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.freeField4")
            .value(is(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.freeField5")
            .value(is(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.freeField6ContentType")
            .value(is(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.freeField6")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY))
            .jsonPath("$.errorCode")
            .value(is(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.errorMessage")
            .value(is(DEFAULT_ERROR_MESSAGE.toString()));
    }

    @Test
    void getNonExistingDTransactionSummary() {
        // Get the dTransactionSummary
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDTransactionSummary() throws Exception {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        dTransactionSummarySearchRepository.save(dTransactionSummary).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());

        // Update the dTransactionSummary
        DTransactionSummary updatedDTransactionSummary = dTransactionSummaryRepository.findById(dTransactionSummary.getId()).block();
        updatedDTransactionSummary
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDTransactionSummary.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDTransactionSummary))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionSummary testDTransactionSummary = dTransactionSummaryList.get(dTransactionSummaryList.size() - 1);
        assertThat(testDTransactionSummary.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionSummary.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionSummary.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionSummary.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionSummary.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDTransactionSummary.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testDTransactionSummary.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionSummary.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransactionSummary.getTransactionStatus()).isEqualTo(UPDATED_TRANSACTION_STATUS);
        assertThat(testDTransactionSummary.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDTransactionSummary.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDTransactionSummary.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDTransactionSummary.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDTransactionSummary.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDTransactionSummary.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDTransactionSummary.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testDTransactionSummary.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionSummary.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionSummary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionSummary.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionSummary.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testDTransactionSummary.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DTransactionSummary> dTransactionSummarySearchList = IterableUtils.toList(
                    dTransactionSummarySearchRepository.findAll().collectList().block()
                );
                DTransactionSummary testDTransactionSummarySearch = dTransactionSummarySearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDTransactionSummarySearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDTransactionSummarySearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testDTransactionSummarySearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testDTransactionSummarySearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testDTransactionSummarySearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testDTransactionSummarySearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testDTransactionSummarySearch.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
                assertThat(testDTransactionSummarySearch.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
                assertThat(testDTransactionSummarySearch.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
                assertThat(testDTransactionSummarySearch.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
                assertThat(testDTransactionSummarySearch.getTransactionStatus()).isEqualTo(UPDATED_TRANSACTION_STATUS);
                assertThat(testDTransactionSummarySearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testDTransactionSummarySearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testDTransactionSummarySearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testDTransactionSummarySearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testDTransactionSummarySearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testDTransactionSummarySearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testDTransactionSummarySearch.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
                assertThat(testDTransactionSummarySearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDTransactionSummarySearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDTransactionSummarySearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDTransactionSummarySearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
                assertThat(testDTransactionSummarySearch.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
                assertThat(testDTransactionSummarySearch.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
            });
    }

    @Test
    void putNonExistingDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dTransactionSummary.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDTransactionSummaryWithPatch() throws Exception {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();

        // Update the dTransactionSummary using partial update
        DTransactionSummary partialUpdatedDTransactionSummary = new DTransactionSummary();
        partialUpdatedDTransactionSummary.setId(dTransactionSummary.getId());

        partialUpdatedDTransactionSummary
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .recordId(UPDATED_RECORD_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .errorMessage(UPDATED_ERROR_MESSAGE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionSummary.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionSummary))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionSummary testDTransactionSummary = dTransactionSummaryList.get(dTransactionSummaryList.size() - 1);
        assertThat(testDTransactionSummary.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionSummary.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionSummary.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionSummary.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionSummary.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDTransactionSummary.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testDTransactionSummary.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionSummary.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransactionSummary.getTransactionStatus()).isEqualTo(DEFAULT_TRANSACTION_STATUS);
        assertThat(testDTransactionSummary.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDTransactionSummary.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDTransactionSummary.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDTransactionSummary.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDTransactionSummary.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDTransactionSummary.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDTransactionSummary.getFreeField6ContentType()).isEqualTo(DEFAULT_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testDTransactionSummary.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionSummary.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionSummary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionSummary.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionSummary.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testDTransactionSummary.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
    }

    @Test
    void fullUpdateDTransactionSummaryWithPatch() throws Exception {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();

        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();

        // Update the dTransactionSummary using partial update
        DTransactionSummary partialUpdatedDTransactionSummary = new DTransactionSummary();
        partialUpdatedDTransactionSummary.setId(dTransactionSummary.getId());

        partialUpdatedDTransactionSummary
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .recordId(UPDATED_RECORD_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .transactionStatus(UPDATED_TRANSACTION_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField6ContentType(UPDATED_FREE_FIELD_6_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .errorCode(UPDATED_ERROR_CODE)
            .errorMessage(UPDATED_ERROR_MESSAGE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionSummary.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionSummary))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionSummary testDTransactionSummary = dTransactionSummaryList.get(dTransactionSummaryList.size() - 1);
        assertThat(testDTransactionSummary.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionSummary.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionSummary.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionSummary.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionSummary.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDTransactionSummary.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testDTransactionSummary.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testDTransactionSummary.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionSummary.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransactionSummary.getTransactionStatus()).isEqualTo(UPDATED_TRANSACTION_STATUS);
        assertThat(testDTransactionSummary.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDTransactionSummary.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDTransactionSummary.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDTransactionSummary.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDTransactionSummary.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDTransactionSummary.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDTransactionSummary.getFreeField6ContentType()).isEqualTo(UPDATED_FREE_FIELD_6_CONTENT_TYPE);
        assertThat(testDTransactionSummary.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionSummary.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionSummary.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionSummary.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionSummary.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testDTransactionSummary.getErrorMessage()).isEqualTo(UPDATED_ERROR_MESSAGE);
    }

    @Test
    void patchNonExistingDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dTransactionSummary.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDTransactionSummary() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        dTransactionSummary.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionSummary))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionSummary in the database
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDTransactionSummary() {
        // Initialize the database
        dTransactionSummaryRepository.save(dTransactionSummary).block();
        dTransactionSummaryRepository.save(dTransactionSummary).block();
        dTransactionSummarySearchRepository.save(dTransactionSummary).block();

        int databaseSizeBeforeDelete = dTransactionSummaryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the dTransactionSummary
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dTransactionSummary.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DTransactionSummary> dTransactionSummaryList = dTransactionSummaryRepository.findAll().collectList().block();
        assertThat(dTransactionSummaryList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSummarySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDTransactionSummary() {
        // Initialize the database
        dTransactionSummary = dTransactionSummaryRepository.save(dTransactionSummary).block();
        dTransactionSummarySearchRepository.save(dTransactionSummary).block();

        // Search the dTransactionSummary
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + dTransactionSummary.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dTransactionSummary.getId().intValue()))
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
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].transactionType")
            .value(hasItem(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].transactionStatus")
            .value(hasItem(DEFAULT_TRANSACTION_STATUS.toString()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5.toString()))
            .jsonPath("$.[*].freeField6ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_6_CONTENT_TYPE))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_6)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE.toString()))
            .jsonPath("$.[*].errorMessage")
            .value(hasItem(DEFAULT_ERROR_MESSAGE.toString()));
    }
}
