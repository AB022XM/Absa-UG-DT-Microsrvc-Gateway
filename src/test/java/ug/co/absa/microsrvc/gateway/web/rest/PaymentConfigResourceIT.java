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
import ug.co.absa.microsrvc.gateway.domain.PaymentConfig;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.PaymentConfigRepository;
import ug.co.absa.microsrvc.gateway.repository.search.PaymentConfigSearchRepository;

/**
 * Integration tests for the {@link PaymentConfigResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PaymentConfigResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STATUS = "BBBBBBBBBB";

    private static final ModeOfPayment DEFAULT_PAYMENT_METHOD = ModeOfPayment.CASH;
    private static final ModeOfPayment UPDATED_PAYMENT_METHOD = ModeOfPayment.RTGS;

    private static final Double DEFAULT_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PAYMENT_AMOUNT = 2D;

    private static final String DEFAULT_ADDITIONAL_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_CONFIG = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/payment-configs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/payment-configs";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PaymentConfigRepository paymentConfigRepository;

    @Autowired
    private PaymentConfigSearchRepository paymentConfigSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PaymentConfig paymentConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentConfig createEntity(EntityManager em) {
        PaymentConfig paymentConfig = new PaymentConfig()
            .recordId(DEFAULT_RECORD_ID)
            .paymentId(DEFAULT_PAYMENT_ID)
            .paymentName(DEFAULT_PAYMENT_NAME)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .paymentDescription(DEFAULT_PAYMENT_DESCRIPTION)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .paymentMethod(DEFAULT_PAYMENT_METHOD)
            .paymentAmount(DEFAULT_PAYMENT_AMOUNT)
            .additionalConfig(DEFAULT_ADDITIONAL_CONFIG)
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
            .freeField20(DEFAULT_FREE_FIELD_20)
            .timestamp(DEFAULT_TIMESTAMP)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return paymentConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentConfig createUpdatedEntity(EntityManager em) {
        PaymentConfig paymentConfig = new PaymentConfig()
            .recordId(UPDATED_RECORD_ID)
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentName(UPDATED_PAYMENT_NAME)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .additionalConfig(UPDATED_ADDITIONAL_CONFIG)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return paymentConfig;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PaymentConfig.class).block();
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
        paymentConfigSearchRepository.deleteAll().block();
        assertThat(paymentConfigSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        paymentConfig = createEntity(em);
    }

    @Test
    void createPaymentConfig() throws Exception {
        int databaseSizeBeforeCreate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        // Create the PaymentConfig
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        PaymentConfig testPaymentConfig = paymentConfigList.get(paymentConfigList.size() - 1);
        assertThat(testPaymentConfig.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testPaymentConfig.getPaymentId()).isEqualTo(DEFAULT_PAYMENT_ID);
        assertThat(testPaymentConfig.getPaymentName()).isEqualTo(DEFAULT_PAYMENT_NAME);
        assertThat(testPaymentConfig.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testPaymentConfig.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testPaymentConfig.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testPaymentConfig.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testPaymentConfig.getPaymentAmount()).isEqualTo(DEFAULT_PAYMENT_AMOUNT);
        assertThat(testPaymentConfig.getAdditionalConfig()).isEqualTo(DEFAULT_ADDITIONAL_CONFIG);
        assertThat(testPaymentConfig.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testPaymentConfig.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testPaymentConfig.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testPaymentConfig.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testPaymentConfig.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testPaymentConfig.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testPaymentConfig.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testPaymentConfig.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testPaymentConfig.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testPaymentConfig.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testPaymentConfig.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testPaymentConfig.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testPaymentConfig.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testPaymentConfig.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testPaymentConfig.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testPaymentConfig.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testPaymentConfig.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testPaymentConfig.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testPaymentConfig.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testPaymentConfig.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testPaymentConfig.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPaymentConfig.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaymentConfig.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createPaymentConfigWithExistingId() throws Exception {
        // Create the PaymentConfig with an existing ID
        paymentConfig.setId(1L);

        int databaseSizeBeforeCreate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        // set the field null
        paymentConfig.setPaymentName(null);

        // Create the PaymentConfig, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        // set the field null
        paymentConfig.setPaymentType(null);

        // Create the PaymentConfig, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        // set the field null
        paymentConfig.setTimestamp(null);

        // Create the PaymentConfig, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllPaymentConfigsAsStream() {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        List<PaymentConfig> paymentConfigList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(PaymentConfig.class)
            .getResponseBody()
            .filter(paymentConfig::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(paymentConfigList).isNotNull();
        assertThat(paymentConfigList).hasSize(1);
        PaymentConfig testPaymentConfig = paymentConfigList.get(0);
        assertThat(testPaymentConfig.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testPaymentConfig.getPaymentId()).isEqualTo(DEFAULT_PAYMENT_ID);
        assertThat(testPaymentConfig.getPaymentName()).isEqualTo(DEFAULT_PAYMENT_NAME);
        assertThat(testPaymentConfig.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testPaymentConfig.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testPaymentConfig.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testPaymentConfig.getPaymentMethod()).isEqualTo(DEFAULT_PAYMENT_METHOD);
        assertThat(testPaymentConfig.getPaymentAmount()).isEqualTo(DEFAULT_PAYMENT_AMOUNT);
        assertThat(testPaymentConfig.getAdditionalConfig()).isEqualTo(DEFAULT_ADDITIONAL_CONFIG);
        assertThat(testPaymentConfig.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testPaymentConfig.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testPaymentConfig.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testPaymentConfig.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testPaymentConfig.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testPaymentConfig.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testPaymentConfig.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testPaymentConfig.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testPaymentConfig.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testPaymentConfig.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testPaymentConfig.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testPaymentConfig.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testPaymentConfig.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testPaymentConfig.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testPaymentConfig.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testPaymentConfig.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testPaymentConfig.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testPaymentConfig.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testPaymentConfig.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testPaymentConfig.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testPaymentConfig.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPaymentConfig.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaymentConfig.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllPaymentConfigs() {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        // Get all the paymentConfigList
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
            .value(hasItem(paymentConfig.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].paymentId")
            .value(hasItem(DEFAULT_PAYMENT_ID))
            .jsonPath("$.[*].paymentName")
            .value(hasItem(DEFAULT_PAYMENT_NAME))
            .jsonPath("$.[*].paymentType")
            .value(hasItem(DEFAULT_PAYMENT_TYPE))
            .jsonPath("$.[*].paymentDescription")
            .value(hasItem(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.[*].paymentStatus")
            .value(hasItem(DEFAULT_PAYMENT_STATUS))
            .jsonPath("$.[*].paymentMethod")
            .value(hasItem(DEFAULT_PAYMENT_METHOD.toString()))
            .jsonPath("$.[*].paymentAmount")
            .value(hasItem(DEFAULT_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].additionalConfig")
            .value(hasItem(DEFAULT_ADDITIONAL_CONFIG.toString()))
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
    void getPaymentConfig() {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        // Get the paymentConfig
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, paymentConfig.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(paymentConfig.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.paymentId")
            .value(is(DEFAULT_PAYMENT_ID))
            .jsonPath("$.paymentName")
            .value(is(DEFAULT_PAYMENT_NAME))
            .jsonPath("$.paymentType")
            .value(is(DEFAULT_PAYMENT_TYPE))
            .jsonPath("$.paymentDescription")
            .value(is(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.paymentStatus")
            .value(is(DEFAULT_PAYMENT_STATUS))
            .jsonPath("$.paymentMethod")
            .value(is(DEFAULT_PAYMENT_METHOD.toString()))
            .jsonPath("$.paymentAmount")
            .value(is(DEFAULT_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.additionalConfig")
            .value(is(DEFAULT_ADDITIONAL_CONFIG.toString()))
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
    void getNonExistingPaymentConfig() {
        // Get the paymentConfig
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPaymentConfig() throws Exception {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        paymentConfigSearchRepository.save(paymentConfig).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());

        // Update the paymentConfig
        PaymentConfig updatedPaymentConfig = paymentConfigRepository.findById(paymentConfig.getId()).block();
        updatedPaymentConfig
            .recordId(UPDATED_RECORD_ID)
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentName(UPDATED_PAYMENT_NAME)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .additionalConfig(UPDATED_ADDITIONAL_CONFIG)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedPaymentConfig.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedPaymentConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        PaymentConfig testPaymentConfig = paymentConfigList.get(paymentConfigList.size() - 1);
        assertThat(testPaymentConfig.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testPaymentConfig.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testPaymentConfig.getPaymentName()).isEqualTo(UPDATED_PAYMENT_NAME);
        assertThat(testPaymentConfig.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testPaymentConfig.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testPaymentConfig.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testPaymentConfig.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testPaymentConfig.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPaymentConfig.getAdditionalConfig()).isEqualTo(UPDATED_ADDITIONAL_CONFIG);
        assertThat(testPaymentConfig.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPaymentConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPaymentConfig.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testPaymentConfig.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testPaymentConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testPaymentConfig.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testPaymentConfig.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testPaymentConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testPaymentConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testPaymentConfig.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testPaymentConfig.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testPaymentConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testPaymentConfig.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testPaymentConfig.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testPaymentConfig.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testPaymentConfig.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testPaymentConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testPaymentConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testPaymentConfig.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testPaymentConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testPaymentConfig.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPaymentConfig.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaymentConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<PaymentConfig> paymentConfigSearchList = IterableUtils.toList(
                    paymentConfigSearchRepository.findAll().collectList().block()
                );
                PaymentConfig testPaymentConfigSearch = paymentConfigSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testPaymentConfigSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testPaymentConfigSearch.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
                assertThat(testPaymentConfigSearch.getPaymentName()).isEqualTo(UPDATED_PAYMENT_NAME);
                assertThat(testPaymentConfigSearch.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
                assertThat(testPaymentConfigSearch.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
                assertThat(testPaymentConfigSearch.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
                assertThat(testPaymentConfigSearch.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
                assertThat(testPaymentConfigSearch.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
                assertThat(testPaymentConfigSearch.getAdditionalConfig()).isEqualTo(UPDATED_ADDITIONAL_CONFIG);
                assertThat(testPaymentConfigSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testPaymentConfigSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testPaymentConfigSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testPaymentConfigSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testPaymentConfigSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testPaymentConfigSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testPaymentConfigSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testPaymentConfigSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testPaymentConfigSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testPaymentConfigSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testPaymentConfigSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testPaymentConfigSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testPaymentConfigSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testPaymentConfigSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testPaymentConfigSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testPaymentConfigSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testPaymentConfigSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testPaymentConfigSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testPaymentConfigSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testPaymentConfigSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testPaymentConfigSearch.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
                assertThat(testPaymentConfigSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testPaymentConfigSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testPaymentConfigSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testPaymentConfigSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testPaymentConfigSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, paymentConfig.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdatePaymentConfigWithPatch() throws Exception {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();

        // Update the paymentConfig using partial update
        PaymentConfig partialUpdatedPaymentConfig = new PaymentConfig();
        partialUpdatedPaymentConfig.setId(paymentConfig.getId());

        partialUpdatedPaymentConfig
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentName(UPDATED_PAYMENT_NAME)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPaymentConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPaymentConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        PaymentConfig testPaymentConfig = paymentConfigList.get(paymentConfigList.size() - 1);
        assertThat(testPaymentConfig.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testPaymentConfig.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testPaymentConfig.getPaymentName()).isEqualTo(UPDATED_PAYMENT_NAME);
        assertThat(testPaymentConfig.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testPaymentConfig.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testPaymentConfig.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testPaymentConfig.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testPaymentConfig.getPaymentAmount()).isEqualTo(DEFAULT_PAYMENT_AMOUNT);
        assertThat(testPaymentConfig.getAdditionalConfig()).isEqualTo(DEFAULT_ADDITIONAL_CONFIG);
        assertThat(testPaymentConfig.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPaymentConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPaymentConfig.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testPaymentConfig.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testPaymentConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testPaymentConfig.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testPaymentConfig.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testPaymentConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testPaymentConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testPaymentConfig.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testPaymentConfig.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testPaymentConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testPaymentConfig.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testPaymentConfig.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testPaymentConfig.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testPaymentConfig.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testPaymentConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testPaymentConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testPaymentConfig.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testPaymentConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testPaymentConfig.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPaymentConfig.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaymentConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdatePaymentConfigWithPatch() throws Exception {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();

        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();

        // Update the paymentConfig using partial update
        PaymentConfig partialUpdatedPaymentConfig = new PaymentConfig();
        partialUpdatedPaymentConfig.setId(paymentConfig.getId());

        partialUpdatedPaymentConfig
            .recordId(UPDATED_RECORD_ID)
            .paymentId(UPDATED_PAYMENT_ID)
            .paymentName(UPDATED_PAYMENT_NAME)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .paymentMethod(UPDATED_PAYMENT_METHOD)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .additionalConfig(UPDATED_ADDITIONAL_CONFIG)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPaymentConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPaymentConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        PaymentConfig testPaymentConfig = paymentConfigList.get(paymentConfigList.size() - 1);
        assertThat(testPaymentConfig.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testPaymentConfig.getPaymentId()).isEqualTo(UPDATED_PAYMENT_ID);
        assertThat(testPaymentConfig.getPaymentName()).isEqualTo(UPDATED_PAYMENT_NAME);
        assertThat(testPaymentConfig.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testPaymentConfig.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testPaymentConfig.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testPaymentConfig.getPaymentMethod()).isEqualTo(UPDATED_PAYMENT_METHOD);
        assertThat(testPaymentConfig.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPaymentConfig.getAdditionalConfig()).isEqualTo(UPDATED_ADDITIONAL_CONFIG);
        assertThat(testPaymentConfig.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testPaymentConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testPaymentConfig.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testPaymentConfig.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testPaymentConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testPaymentConfig.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testPaymentConfig.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testPaymentConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testPaymentConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testPaymentConfig.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testPaymentConfig.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testPaymentConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testPaymentConfig.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testPaymentConfig.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testPaymentConfig.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testPaymentConfig.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testPaymentConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testPaymentConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testPaymentConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testPaymentConfig.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testPaymentConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testPaymentConfig.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPaymentConfig.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaymentConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, paymentConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamPaymentConfig() throws Exception {
        int databaseSizeBeforeUpdate = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        paymentConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentConfig))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PaymentConfig in the database
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deletePaymentConfig() {
        // Initialize the database
        paymentConfigRepository.save(paymentConfig).block();
        paymentConfigRepository.save(paymentConfig).block();
        paymentConfigSearchRepository.save(paymentConfig).block();

        int databaseSizeBeforeDelete = paymentConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the paymentConfig
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, paymentConfig.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PaymentConfig> paymentConfigList = paymentConfigRepository.findAll().collectList().block();
        assertThat(paymentConfigList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchPaymentConfig() {
        // Initialize the database
        paymentConfig = paymentConfigRepository.save(paymentConfig).block();
        paymentConfigSearchRepository.save(paymentConfig).block();

        // Search the paymentConfig
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + paymentConfig.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(paymentConfig.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].paymentId")
            .value(hasItem(DEFAULT_PAYMENT_ID))
            .jsonPath("$.[*].paymentName")
            .value(hasItem(DEFAULT_PAYMENT_NAME))
            .jsonPath("$.[*].paymentType")
            .value(hasItem(DEFAULT_PAYMENT_TYPE))
            .jsonPath("$.[*].paymentDescription")
            .value(hasItem(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.[*].paymentStatus")
            .value(hasItem(DEFAULT_PAYMENT_STATUS))
            .jsonPath("$.[*].paymentMethod")
            .value(hasItem(DEFAULT_PAYMENT_METHOD.toString()))
            .jsonPath("$.[*].paymentAmount")
            .value(hasItem(DEFAULT_PAYMENT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].additionalConfig")
            .value(hasItem(DEFAULT_ADDITIONAL_CONFIG.toString()))
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
