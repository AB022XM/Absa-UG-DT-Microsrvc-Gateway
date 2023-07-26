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
import ug.co.absa.microsrvc.gateway.domain.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.CurrencyRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.CurrencySearchRepository;

/**
 * Integration tests for the {@link CurrencyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CurrencyResourceIT {

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

    private static final String DEFAULT_CURRENCY_UNIQUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_UNIQUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_NAME = "BBBBBBBBBB";

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

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final RecordStatus DEFAULT_RECORD_STATUS = RecordStatus.ACTIVE;
    private static final RecordStatus UPDATED_RECORD_STATUS = RecordStatus.INACTIVE;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/currencies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/currencies";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencySearchRepository currencySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Currency currency;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Currency createEntity(EntityManager em) {
        Currency currency = new Currency()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .currencyUniqueId(DEFAULT_CURRENCY_UNIQUE_ID)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .currencyName(DEFAULT_CURRENCY_NAME)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .timestamp(DEFAULT_TIMESTAMP)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return currency;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Currency createUpdatedEntity(EntityManager em) {
        Currency currency = new Currency()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .currencyUniqueId(UPDATED_CURRENCY_UNIQUE_ID)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .currencyName(UPDATED_CURRENCY_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return currency;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Currency.class).block();
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
        currencySearchRepository.deleteAll().block();
        assertThat(currencySearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        currency = createEntity(em);
    }

    @Test
    void createCurrency() throws Exception {
        int databaseSizeBeforeCreate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        // Create the Currency
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCurrency.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testCurrency.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testCurrency.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCurrency.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testCurrency.getCurrencyUniqueId()).isEqualTo(DEFAULT_CURRENCY_UNIQUE_ID);
        assertThat(testCurrency.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(DEFAULT_CURRENCY_NAME);
        assertThat(testCurrency.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testCurrency.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testCurrency.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testCurrency.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testCurrency.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testCurrency.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testCurrency.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCurrency.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testCurrency.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCurrency.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCurrency.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCurrency.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createCurrencyWithExistingId() throws Exception {
        // Create the Currency with an existing ID
        currency.setId(1L);

        int databaseSizeBeforeCreate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        // set the field null
        currency.setTimestamp(null);

        // Create the Currency, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        // set the field null
        currency.setCreatedAt(null);

        // Create the Currency, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllCurrenciesAsStream() {
        // Initialize the database
        currencyRepository.save(currency).block();

        List<Currency> currencyList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Currency.class)
            .getResponseBody()
            .filter(currency::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(currencyList).isNotNull();
        assertThat(currencyList).hasSize(1);
        Currency testCurrency = currencyList.get(0);
        assertThat(testCurrency.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCurrency.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testCurrency.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testCurrency.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCurrency.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testCurrency.getCurrencyUniqueId()).isEqualTo(DEFAULT_CURRENCY_UNIQUE_ID);
        assertThat(testCurrency.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(DEFAULT_CURRENCY_NAME);
        assertThat(testCurrency.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testCurrency.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testCurrency.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testCurrency.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testCurrency.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testCurrency.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testCurrency.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCurrency.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testCurrency.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCurrency.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCurrency.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCurrency.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllCurrencies() {
        // Initialize the database
        currencyRepository.save(currency).block();

        // Get all the currencyList
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
            .value(hasItem(currency.getId().intValue()))
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
            .jsonPath("$.[*].currencyUniqueId")
            .value(hasItem(DEFAULT_CURRENCY_UNIQUE_ID))
            .jsonPath("$.[*].currencyCode")
            .value(hasItem(DEFAULT_CURRENCY_CODE))
            .jsonPath("$.[*].currencyName")
            .value(hasItem(DEFAULT_CURRENCY_NAME))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
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
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()))
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
    void getCurrency() {
        // Initialize the database
        currencyRepository.save(currency).block();

        // Get the currency
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, currency.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(currency.getId().intValue()))
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
            .jsonPath("$.currencyUniqueId")
            .value(is(DEFAULT_CURRENCY_UNIQUE_ID))
            .jsonPath("$.currencyCode")
            .value(is(DEFAULT_CURRENCY_CODE))
            .jsonPath("$.currencyName")
            .value(is(DEFAULT_CURRENCY_NAME))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1.toString()))
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
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS.toString()))
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
    void getNonExistingCurrency() {
        // Get the currency
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCurrency() throws Exception {
        // Initialize the database
        currencyRepository.save(currency).block();

        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        currencySearchRepository.save(currency).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());

        // Update the currency
        Currency updatedCurrency = currencyRepository.findById(currency.getId()).block();
        updatedCurrency
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .currencyUniqueId(UPDATED_CURRENCY_UNIQUE_ID)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .currencyName(UPDATED_CURRENCY_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCurrency.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCurrency))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCurrency.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testCurrency.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testCurrency.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCurrency.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testCurrency.getCurrencyUniqueId()).isEqualTo(UPDATED_CURRENCY_UNIQUE_ID);
        assertThat(testCurrency.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(UPDATED_CURRENCY_NAME);
        assertThat(testCurrency.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testCurrency.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testCurrency.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testCurrency.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testCurrency.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testCurrency.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testCurrency.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCurrency.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testCurrency.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCurrency.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCurrency.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCurrency.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Currency> currencySearchList = IterableUtils.toList(currencySearchRepository.findAll().collectList().block());
                Currency testCurrencySearch = currencySearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testCurrencySearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testCurrencySearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testCurrencySearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testCurrencySearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testCurrencySearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testCurrencySearch.getCurrencyUniqueId()).isEqualTo(UPDATED_CURRENCY_UNIQUE_ID);
                assertThat(testCurrencySearch.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
                assertThat(testCurrencySearch.getCurrencyName()).isEqualTo(UPDATED_CURRENCY_NAME);
                assertThat(testCurrencySearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testCurrencySearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testCurrencySearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testCurrencySearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testCurrencySearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testCurrencySearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testCurrencySearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testCurrencySearch.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
                assertThat(testCurrencySearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testCurrencySearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testCurrencySearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testCurrencySearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, currency.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateCurrencyWithPatch() throws Exception {
        // Initialize the database
        currencyRepository.save(currency).block();

        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();

        // Update the currency using partial update
        Currency partialUpdatedCurrency = new Currency();
        partialUpdatedCurrency.setId(currency.getId());

        partialUpdatedCurrency
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .currencyName(UPDATED_CURRENCY_NAME)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField6(UPDATED_FREE_FIELD_6)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCurrency.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCurrency))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCurrency.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testCurrency.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testCurrency.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCurrency.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testCurrency.getCurrencyUniqueId()).isEqualTo(DEFAULT_CURRENCY_UNIQUE_ID);
        assertThat(testCurrency.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(UPDATED_CURRENCY_NAME);
        assertThat(testCurrency.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testCurrency.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testCurrency.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testCurrency.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testCurrency.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testCurrency.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testCurrency.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCurrency.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testCurrency.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCurrency.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCurrency.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCurrency.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateCurrencyWithPatch() throws Exception {
        // Initialize the database
        currencyRepository.save(currency).block();

        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();

        // Update the currency using partial update
        Currency partialUpdatedCurrency = new Currency();
        partialUpdatedCurrency.setId(currency.getId());

        partialUpdatedCurrency
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .currencyUniqueId(UPDATED_CURRENCY_UNIQUE_ID)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .currencyName(UPDATED_CURRENCY_NAME)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCurrency.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCurrency))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        Currency testCurrency = currencyList.get(currencyList.size() - 1);
        assertThat(testCurrency.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCurrency.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testCurrency.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testCurrency.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCurrency.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testCurrency.getCurrencyUniqueId()).isEqualTo(UPDATED_CURRENCY_UNIQUE_ID);
        assertThat(testCurrency.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testCurrency.getCurrencyName()).isEqualTo(UPDATED_CURRENCY_NAME);
        assertThat(testCurrency.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testCurrency.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testCurrency.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testCurrency.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testCurrency.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testCurrency.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testCurrency.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCurrency.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testCurrency.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCurrency.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCurrency.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCurrency.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, currency.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamCurrency() throws Exception {
        int databaseSizeBeforeUpdate = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        currency.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(currency))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Currency in the database
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteCurrency() {
        // Initialize the database
        currencyRepository.save(currency).block();
        currencyRepository.save(currency).block();
        currencySearchRepository.save(currency).block();

        int databaseSizeBeforeDelete = currencyRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the currency
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, currency.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Currency> currencyList = currencyRepository.findAll().collectList().block();
        assertThat(currencyList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(currencySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchCurrency() {
        // Initialize the database
        currency = currencyRepository.save(currency).block();
        currencySearchRepository.save(currency).block();

        // Search the currency
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + currency.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(currency.getId().intValue()))
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
            .jsonPath("$.[*].currencyUniqueId")
            .value(hasItem(DEFAULT_CURRENCY_UNIQUE_ID))
            .jsonPath("$.[*].currencyCode")
            .value(hasItem(DEFAULT_CURRENCY_CODE))
            .jsonPath("$.[*].currencyName")
            .value(hasItem(DEFAULT_CURRENCY_NAME))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.toString()))
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
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()))
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
