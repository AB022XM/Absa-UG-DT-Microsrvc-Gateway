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
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;
import ug.co.absa.microsrvc.gateway.repository.BillerAccountRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.BillerAccountSearchRepository;

/**
 * Integration tests for the {@link BillerAccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BillerAccountResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_ACC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ACC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_ACCOUNT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ACCOUNT_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BILLER_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELFLG = false;
    private static final Boolean UPDATED_DELFLG = true;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/biller-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/biller-accounts";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BillerAccountRepository billerAccountRepository;

    @Autowired
    private BillerAccountSearchRepository billerAccountSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private BillerAccount billerAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerAccount createEntity(EntityManager em) {
        BillerAccount billerAccount = new BillerAccount()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .billerId(DEFAULT_BILLER_ID)
            .billerName(DEFAULT_BILLER_NAME)
            .billerAccNumber(DEFAULT_BILLER_ACC_NUMBER)
            .billerAccountDescription(DEFAULT_BILLER_ACCOUNT_DESCRIPTION)
            .timestamp(DEFAULT_TIMESTAMP)
            .billerFreeField1(DEFAULT_BILLER_FREE_FIELD_1)
            .billerFreeField2(DEFAULT_BILLER_FREE_FIELD_2)
            .billerFreeField3(DEFAULT_BILLER_FREE_FIELD_3)
            .billerFreeField4(DEFAULT_BILLER_FREE_FIELD_4)
            .billerFreeField5(DEFAULT_BILLER_FREE_FIELD_5)
            .billerFreeField6(DEFAULT_BILLER_FREE_FIELD_6)
            .billerFreeField7(DEFAULT_BILLER_FREE_FIELD_7)
            .billerFreeField8(DEFAULT_BILLER_FREE_FIELD_8)
            .billerFreeField9(DEFAULT_BILLER_FREE_FIELD_9)
            .billerFreeField10(DEFAULT_BILLER_FREE_FIELD_10)
            .billerFreeField11(DEFAULT_BILLER_FREE_FIELD_11)
            .billerFreeField12(DEFAULT_BILLER_FREE_FIELD_12)
            .billerFreeField13(DEFAULT_BILLER_FREE_FIELD_13)
            .delflg(DEFAULT_DELFLG)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return billerAccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerAccount createUpdatedEntity(EntityManager em) {
        BillerAccount billerAccount = new BillerAccount()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .billerId(UPDATED_BILLER_ID)
            .billerName(UPDATED_BILLER_NAME)
            .billerAccNumber(UPDATED_BILLER_ACC_NUMBER)
            .billerAccountDescription(UPDATED_BILLER_ACCOUNT_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .billerFreeField1(UPDATED_BILLER_FREE_FIELD_1)
            .billerFreeField2(UPDATED_BILLER_FREE_FIELD_2)
            .billerFreeField3(UPDATED_BILLER_FREE_FIELD_3)
            .billerFreeField4(UPDATED_BILLER_FREE_FIELD_4)
            .billerFreeField5(UPDATED_BILLER_FREE_FIELD_5)
            .billerFreeField6(UPDATED_BILLER_FREE_FIELD_6)
            .billerFreeField7(UPDATED_BILLER_FREE_FIELD_7)
            .billerFreeField8(UPDATED_BILLER_FREE_FIELD_8)
            .billerFreeField9(UPDATED_BILLER_FREE_FIELD_9)
            .billerFreeField10(UPDATED_BILLER_FREE_FIELD_10)
            .billerFreeField11(UPDATED_BILLER_FREE_FIELD_11)
            .billerFreeField12(UPDATED_BILLER_FREE_FIELD_12)
            .billerFreeField13(UPDATED_BILLER_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return billerAccount;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(BillerAccount.class).block();
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
        billerAccountSearchRepository.deleteAll().block();
        assertThat(billerAccountSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        billerAccount = createEntity(em);
    }

    @Test
    void createBillerAccount() throws Exception {
        int databaseSizeBeforeCreate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // Create the BillerAccount
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        BillerAccount testBillerAccount = billerAccountList.get(billerAccountList.size() - 1);
        assertThat(testBillerAccount.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBillerAccount.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testBillerAccount.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBillerAccount.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
        assertThat(testBillerAccount.getBillerAccNumber()).isEqualTo(DEFAULT_BILLER_ACC_NUMBER);
        assertThat(testBillerAccount.getBillerAccountDescription()).isEqualTo(DEFAULT_BILLER_ACCOUNT_DESCRIPTION);
        assertThat(testBillerAccount.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBillerAccount.getBillerFreeField1()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_1);
        assertThat(testBillerAccount.getBillerFreeField2()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_2);
        assertThat(testBillerAccount.getBillerFreeField3()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_3);
        assertThat(testBillerAccount.getBillerFreeField4()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_4);
        assertThat(testBillerAccount.getBillerFreeField5()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_5);
        assertThat(testBillerAccount.getBillerFreeField6()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_6);
        assertThat(testBillerAccount.getBillerFreeField7()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_7);
        assertThat(testBillerAccount.getBillerFreeField8()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_8);
        assertThat(testBillerAccount.getBillerFreeField9()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_9);
        assertThat(testBillerAccount.getBillerFreeField10()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_10);
        assertThat(testBillerAccount.getBillerFreeField11()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_11);
        assertThat(testBillerAccount.getBillerFreeField12()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_12);
        assertThat(testBillerAccount.getBillerFreeField13()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_13);
        assertThat(testBillerAccount.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBillerAccount.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBillerAccount.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBillerAccount.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBillerAccount.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createBillerAccountWithExistingId() throws Exception {
        // Create the BillerAccount with an existing ID
        billerAccount.setId(1L);

        int databaseSizeBeforeCreate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkRecordIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // set the field null
        billerAccount.setRecordId(null);

        // Create the BillerAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // set the field null
        billerAccount.setBillerId(null);

        // Create the BillerAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // set the field null
        billerAccount.setBillerName(null);

        // Create the BillerAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // set the field null
        billerAccount.setBillerAccNumber(null);

        // Create the BillerAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        // set the field null
        billerAccount.setCreatedAt(null);

        // Create the BillerAccount, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllBillerAccountsAsStream() {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        List<BillerAccount> billerAccountList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(BillerAccount.class)
            .getResponseBody()
            .filter(billerAccount::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(billerAccountList).isNotNull();
        assertThat(billerAccountList).hasSize(1);
        BillerAccount testBillerAccount = billerAccountList.get(0);
        assertThat(testBillerAccount.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBillerAccount.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testBillerAccount.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBillerAccount.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
        assertThat(testBillerAccount.getBillerAccNumber()).isEqualTo(DEFAULT_BILLER_ACC_NUMBER);
        assertThat(testBillerAccount.getBillerAccountDescription()).isEqualTo(DEFAULT_BILLER_ACCOUNT_DESCRIPTION);
        assertThat(testBillerAccount.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBillerAccount.getBillerFreeField1()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_1);
        assertThat(testBillerAccount.getBillerFreeField2()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_2);
        assertThat(testBillerAccount.getBillerFreeField3()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_3);
        assertThat(testBillerAccount.getBillerFreeField4()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_4);
        assertThat(testBillerAccount.getBillerFreeField5()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_5);
        assertThat(testBillerAccount.getBillerFreeField6()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_6);
        assertThat(testBillerAccount.getBillerFreeField7()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_7);
        assertThat(testBillerAccount.getBillerFreeField8()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_8);
        assertThat(testBillerAccount.getBillerFreeField9()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_9);
        assertThat(testBillerAccount.getBillerFreeField10()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_10);
        assertThat(testBillerAccount.getBillerFreeField11()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_11);
        assertThat(testBillerAccount.getBillerFreeField12()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_12);
        assertThat(testBillerAccount.getBillerFreeField13()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_13);
        assertThat(testBillerAccount.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBillerAccount.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBillerAccount.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBillerAccount.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBillerAccount.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllBillerAccounts() {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        // Get all the billerAccountList
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
            .value(hasItem(billerAccount.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].billerName")
            .value(hasItem(DEFAULT_BILLER_NAME))
            .jsonPath("$.[*].billerAccNumber")
            .value(hasItem(DEFAULT_BILLER_ACC_NUMBER))
            .jsonPath("$.[*].billerAccountDescription")
            .value(hasItem(DEFAULT_BILLER_ACCOUNT_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].billerFreeField1")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_1))
            .jsonPath("$.[*].billerFreeField2")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_2))
            .jsonPath("$.[*].billerFreeField3")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_3))
            .jsonPath("$.[*].billerFreeField4")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_4))
            .jsonPath("$.[*].billerFreeField5")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_5))
            .jsonPath("$.[*].billerFreeField6")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_6))
            .jsonPath("$.[*].billerFreeField7")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_7))
            .jsonPath("$.[*].billerFreeField8")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_8))
            .jsonPath("$.[*].billerFreeField9")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_9))
            .jsonPath("$.[*].billerFreeField10")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_10))
            .jsonPath("$.[*].billerFreeField11")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_11))
            .jsonPath("$.[*].billerFreeField12")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_12))
            .jsonPath("$.[*].billerFreeField13")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_13))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
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
    void getBillerAccount() {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        // Get the billerAccount
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, billerAccount.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(billerAccount.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.billerName")
            .value(is(DEFAULT_BILLER_NAME))
            .jsonPath("$.billerAccNumber")
            .value(is(DEFAULT_BILLER_ACC_NUMBER))
            .jsonPath("$.billerAccountDescription")
            .value(is(DEFAULT_BILLER_ACCOUNT_DESCRIPTION))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.billerFreeField1")
            .value(is(DEFAULT_BILLER_FREE_FIELD_1))
            .jsonPath("$.billerFreeField2")
            .value(is(DEFAULT_BILLER_FREE_FIELD_2))
            .jsonPath("$.billerFreeField3")
            .value(is(DEFAULT_BILLER_FREE_FIELD_3))
            .jsonPath("$.billerFreeField4")
            .value(is(DEFAULT_BILLER_FREE_FIELD_4))
            .jsonPath("$.billerFreeField5")
            .value(is(DEFAULT_BILLER_FREE_FIELD_5))
            .jsonPath("$.billerFreeField6")
            .value(is(DEFAULT_BILLER_FREE_FIELD_6))
            .jsonPath("$.billerFreeField7")
            .value(is(DEFAULT_BILLER_FREE_FIELD_7))
            .jsonPath("$.billerFreeField8")
            .value(is(DEFAULT_BILLER_FREE_FIELD_8))
            .jsonPath("$.billerFreeField9")
            .value(is(DEFAULT_BILLER_FREE_FIELD_9))
            .jsonPath("$.billerFreeField10")
            .value(is(DEFAULT_BILLER_FREE_FIELD_10))
            .jsonPath("$.billerFreeField11")
            .value(is(DEFAULT_BILLER_FREE_FIELD_11))
            .jsonPath("$.billerFreeField12")
            .value(is(DEFAULT_BILLER_FREE_FIELD_12))
            .jsonPath("$.billerFreeField13")
            .value(is(DEFAULT_BILLER_FREE_FIELD_13))
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()))
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
    void getNonExistingBillerAccount() {
        // Get the billerAccount
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBillerAccount() throws Exception {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        billerAccountSearchRepository.save(billerAccount).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());

        // Update the billerAccount
        BillerAccount updatedBillerAccount = billerAccountRepository.findById(billerAccount.getId()).block();
        updatedBillerAccount
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .billerId(UPDATED_BILLER_ID)
            .billerName(UPDATED_BILLER_NAME)
            .billerAccNumber(UPDATED_BILLER_ACC_NUMBER)
            .billerAccountDescription(UPDATED_BILLER_ACCOUNT_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .billerFreeField1(UPDATED_BILLER_FREE_FIELD_1)
            .billerFreeField2(UPDATED_BILLER_FREE_FIELD_2)
            .billerFreeField3(UPDATED_BILLER_FREE_FIELD_3)
            .billerFreeField4(UPDATED_BILLER_FREE_FIELD_4)
            .billerFreeField5(UPDATED_BILLER_FREE_FIELD_5)
            .billerFreeField6(UPDATED_BILLER_FREE_FIELD_6)
            .billerFreeField7(UPDATED_BILLER_FREE_FIELD_7)
            .billerFreeField8(UPDATED_BILLER_FREE_FIELD_8)
            .billerFreeField9(UPDATED_BILLER_FREE_FIELD_9)
            .billerFreeField10(UPDATED_BILLER_FREE_FIELD_10)
            .billerFreeField11(UPDATED_BILLER_FREE_FIELD_11)
            .billerFreeField12(UPDATED_BILLER_FREE_FIELD_12)
            .billerFreeField13(UPDATED_BILLER_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBillerAccount.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBillerAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        BillerAccount testBillerAccount = billerAccountList.get(billerAccountList.size() - 1);
        assertThat(testBillerAccount.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBillerAccount.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testBillerAccount.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBillerAccount.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
        assertThat(testBillerAccount.getBillerAccNumber()).isEqualTo(UPDATED_BILLER_ACC_NUMBER);
        assertThat(testBillerAccount.getBillerAccountDescription()).isEqualTo(UPDATED_BILLER_ACCOUNT_DESCRIPTION);
        assertThat(testBillerAccount.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBillerAccount.getBillerFreeField1()).isEqualTo(UPDATED_BILLER_FREE_FIELD_1);
        assertThat(testBillerAccount.getBillerFreeField2()).isEqualTo(UPDATED_BILLER_FREE_FIELD_2);
        assertThat(testBillerAccount.getBillerFreeField3()).isEqualTo(UPDATED_BILLER_FREE_FIELD_3);
        assertThat(testBillerAccount.getBillerFreeField4()).isEqualTo(UPDATED_BILLER_FREE_FIELD_4);
        assertThat(testBillerAccount.getBillerFreeField5()).isEqualTo(UPDATED_BILLER_FREE_FIELD_5);
        assertThat(testBillerAccount.getBillerFreeField6()).isEqualTo(UPDATED_BILLER_FREE_FIELD_6);
        assertThat(testBillerAccount.getBillerFreeField7()).isEqualTo(UPDATED_BILLER_FREE_FIELD_7);
        assertThat(testBillerAccount.getBillerFreeField8()).isEqualTo(UPDATED_BILLER_FREE_FIELD_8);
        assertThat(testBillerAccount.getBillerFreeField9()).isEqualTo(UPDATED_BILLER_FREE_FIELD_9);
        assertThat(testBillerAccount.getBillerFreeField10()).isEqualTo(UPDATED_BILLER_FREE_FIELD_10);
        assertThat(testBillerAccount.getBillerFreeField11()).isEqualTo(UPDATED_BILLER_FREE_FIELD_11);
        assertThat(testBillerAccount.getBillerFreeField12()).isEqualTo(UPDATED_BILLER_FREE_FIELD_12);
        assertThat(testBillerAccount.getBillerFreeField13()).isEqualTo(UPDATED_BILLER_FREE_FIELD_13);
        assertThat(testBillerAccount.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBillerAccount.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBillerAccount.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBillerAccount.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBillerAccount.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<BillerAccount> billerAccountSearchList = IterableUtils.toList(
                    billerAccountSearchRepository.findAll().collectList().block()
                );
                BillerAccount testBillerAccountSearch = billerAccountSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testBillerAccountSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testBillerAccountSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testBillerAccountSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testBillerAccountSearch.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
                assertThat(testBillerAccountSearch.getBillerAccNumber()).isEqualTo(UPDATED_BILLER_ACC_NUMBER);
                assertThat(testBillerAccountSearch.getBillerAccountDescription()).isEqualTo(UPDATED_BILLER_ACCOUNT_DESCRIPTION);
                assertThat(testBillerAccountSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testBillerAccountSearch.getBillerFreeField1()).isEqualTo(UPDATED_BILLER_FREE_FIELD_1);
                assertThat(testBillerAccountSearch.getBillerFreeField2()).isEqualTo(UPDATED_BILLER_FREE_FIELD_2);
                assertThat(testBillerAccountSearch.getBillerFreeField3()).isEqualTo(UPDATED_BILLER_FREE_FIELD_3);
                assertThat(testBillerAccountSearch.getBillerFreeField4()).isEqualTo(UPDATED_BILLER_FREE_FIELD_4);
                assertThat(testBillerAccountSearch.getBillerFreeField5()).isEqualTo(UPDATED_BILLER_FREE_FIELD_5);
                assertThat(testBillerAccountSearch.getBillerFreeField6()).isEqualTo(UPDATED_BILLER_FREE_FIELD_6);
                assertThat(testBillerAccountSearch.getBillerFreeField7()).isEqualTo(UPDATED_BILLER_FREE_FIELD_7);
                assertThat(testBillerAccountSearch.getBillerFreeField8()).isEqualTo(UPDATED_BILLER_FREE_FIELD_8);
                assertThat(testBillerAccountSearch.getBillerFreeField9()).isEqualTo(UPDATED_BILLER_FREE_FIELD_9);
                assertThat(testBillerAccountSearch.getBillerFreeField10()).isEqualTo(UPDATED_BILLER_FREE_FIELD_10);
                assertThat(testBillerAccountSearch.getBillerFreeField11()).isEqualTo(UPDATED_BILLER_FREE_FIELD_11);
                assertThat(testBillerAccountSearch.getBillerFreeField12()).isEqualTo(UPDATED_BILLER_FREE_FIELD_12);
                assertThat(testBillerAccountSearch.getBillerFreeField13()).isEqualTo(UPDATED_BILLER_FREE_FIELD_13);
                assertThat(testBillerAccountSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testBillerAccountSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testBillerAccountSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testBillerAccountSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testBillerAccountSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, billerAccount.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateBillerAccountWithPatch() throws Exception {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();

        // Update the billerAccount using partial update
        BillerAccount partialUpdatedBillerAccount = new BillerAccount();
        partialUpdatedBillerAccount.setId(billerAccount.getId());

        partialUpdatedBillerAccount
            .billerId(UPDATED_BILLER_ID)
            .billerAccNumber(UPDATED_BILLER_ACC_NUMBER)
            .timestamp(UPDATED_TIMESTAMP)
            .billerFreeField7(UPDATED_BILLER_FREE_FIELD_7)
            .billerFreeField9(UPDATED_BILLER_FREE_FIELD_9)
            .billerFreeField10(UPDATED_BILLER_FREE_FIELD_10)
            .billerFreeField13(UPDATED_BILLER_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBillerAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBillerAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        BillerAccount testBillerAccount = billerAccountList.get(billerAccountList.size() - 1);
        assertThat(testBillerAccount.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBillerAccount.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testBillerAccount.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBillerAccount.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
        assertThat(testBillerAccount.getBillerAccNumber()).isEqualTo(UPDATED_BILLER_ACC_NUMBER);
        assertThat(testBillerAccount.getBillerAccountDescription()).isEqualTo(DEFAULT_BILLER_ACCOUNT_DESCRIPTION);
        assertThat(testBillerAccount.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBillerAccount.getBillerFreeField1()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_1);
        assertThat(testBillerAccount.getBillerFreeField2()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_2);
        assertThat(testBillerAccount.getBillerFreeField3()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_3);
        assertThat(testBillerAccount.getBillerFreeField4()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_4);
        assertThat(testBillerAccount.getBillerFreeField5()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_5);
        assertThat(testBillerAccount.getBillerFreeField6()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_6);
        assertThat(testBillerAccount.getBillerFreeField7()).isEqualTo(UPDATED_BILLER_FREE_FIELD_7);
        assertThat(testBillerAccount.getBillerFreeField8()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_8);
        assertThat(testBillerAccount.getBillerFreeField9()).isEqualTo(UPDATED_BILLER_FREE_FIELD_9);
        assertThat(testBillerAccount.getBillerFreeField10()).isEqualTo(UPDATED_BILLER_FREE_FIELD_10);
        assertThat(testBillerAccount.getBillerFreeField11()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_11);
        assertThat(testBillerAccount.getBillerFreeField12()).isEqualTo(DEFAULT_BILLER_FREE_FIELD_12);
        assertThat(testBillerAccount.getBillerFreeField13()).isEqualTo(UPDATED_BILLER_FREE_FIELD_13);
        assertThat(testBillerAccount.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBillerAccount.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBillerAccount.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBillerAccount.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBillerAccount.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateBillerAccountWithPatch() throws Exception {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();

        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();

        // Update the billerAccount using partial update
        BillerAccount partialUpdatedBillerAccount = new BillerAccount();
        partialUpdatedBillerAccount.setId(billerAccount.getId());

        partialUpdatedBillerAccount
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .billerId(UPDATED_BILLER_ID)
            .billerName(UPDATED_BILLER_NAME)
            .billerAccNumber(UPDATED_BILLER_ACC_NUMBER)
            .billerAccountDescription(UPDATED_BILLER_ACCOUNT_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .billerFreeField1(UPDATED_BILLER_FREE_FIELD_1)
            .billerFreeField2(UPDATED_BILLER_FREE_FIELD_2)
            .billerFreeField3(UPDATED_BILLER_FREE_FIELD_3)
            .billerFreeField4(UPDATED_BILLER_FREE_FIELD_4)
            .billerFreeField5(UPDATED_BILLER_FREE_FIELD_5)
            .billerFreeField6(UPDATED_BILLER_FREE_FIELD_6)
            .billerFreeField7(UPDATED_BILLER_FREE_FIELD_7)
            .billerFreeField8(UPDATED_BILLER_FREE_FIELD_8)
            .billerFreeField9(UPDATED_BILLER_FREE_FIELD_9)
            .billerFreeField10(UPDATED_BILLER_FREE_FIELD_10)
            .billerFreeField11(UPDATED_BILLER_FREE_FIELD_11)
            .billerFreeField12(UPDATED_BILLER_FREE_FIELD_12)
            .billerFreeField13(UPDATED_BILLER_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBillerAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBillerAccount))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        BillerAccount testBillerAccount = billerAccountList.get(billerAccountList.size() - 1);
        assertThat(testBillerAccount.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBillerAccount.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testBillerAccount.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBillerAccount.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
        assertThat(testBillerAccount.getBillerAccNumber()).isEqualTo(UPDATED_BILLER_ACC_NUMBER);
        assertThat(testBillerAccount.getBillerAccountDescription()).isEqualTo(UPDATED_BILLER_ACCOUNT_DESCRIPTION);
        assertThat(testBillerAccount.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBillerAccount.getBillerFreeField1()).isEqualTo(UPDATED_BILLER_FREE_FIELD_1);
        assertThat(testBillerAccount.getBillerFreeField2()).isEqualTo(UPDATED_BILLER_FREE_FIELD_2);
        assertThat(testBillerAccount.getBillerFreeField3()).isEqualTo(UPDATED_BILLER_FREE_FIELD_3);
        assertThat(testBillerAccount.getBillerFreeField4()).isEqualTo(UPDATED_BILLER_FREE_FIELD_4);
        assertThat(testBillerAccount.getBillerFreeField5()).isEqualTo(UPDATED_BILLER_FREE_FIELD_5);
        assertThat(testBillerAccount.getBillerFreeField6()).isEqualTo(UPDATED_BILLER_FREE_FIELD_6);
        assertThat(testBillerAccount.getBillerFreeField7()).isEqualTo(UPDATED_BILLER_FREE_FIELD_7);
        assertThat(testBillerAccount.getBillerFreeField8()).isEqualTo(UPDATED_BILLER_FREE_FIELD_8);
        assertThat(testBillerAccount.getBillerFreeField9()).isEqualTo(UPDATED_BILLER_FREE_FIELD_9);
        assertThat(testBillerAccount.getBillerFreeField10()).isEqualTo(UPDATED_BILLER_FREE_FIELD_10);
        assertThat(testBillerAccount.getBillerFreeField11()).isEqualTo(UPDATED_BILLER_FREE_FIELD_11);
        assertThat(testBillerAccount.getBillerFreeField12()).isEqualTo(UPDATED_BILLER_FREE_FIELD_12);
        assertThat(testBillerAccount.getBillerFreeField13()).isEqualTo(UPDATED_BILLER_FREE_FIELD_13);
        assertThat(testBillerAccount.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBillerAccount.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBillerAccount.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBillerAccount.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBillerAccount.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, billerAccount.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamBillerAccount() throws Exception {
        int databaseSizeBeforeUpdate = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        billerAccount.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(billerAccount))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BillerAccount in the database
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteBillerAccount() {
        // Initialize the database
        billerAccountRepository.save(billerAccount).block();
        billerAccountRepository.save(billerAccount).block();
        billerAccountSearchRepository.save(billerAccount).block();

        int databaseSizeBeforeDelete = billerAccountRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the billerAccount
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, billerAccount.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<BillerAccount> billerAccountList = billerAccountRepository.findAll().collectList().block();
        assertThat(billerAccountList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerAccountSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchBillerAccount() {
        // Initialize the database
        billerAccount = billerAccountRepository.save(billerAccount).block();
        billerAccountSearchRepository.save(billerAccount).block();

        // Search the billerAccount
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + billerAccount.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(billerAccount.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].billerName")
            .value(hasItem(DEFAULT_BILLER_NAME))
            .jsonPath("$.[*].billerAccNumber")
            .value(hasItem(DEFAULT_BILLER_ACC_NUMBER))
            .jsonPath("$.[*].billerAccountDescription")
            .value(hasItem(DEFAULT_BILLER_ACCOUNT_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].billerFreeField1")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_1))
            .jsonPath("$.[*].billerFreeField2")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_2))
            .jsonPath("$.[*].billerFreeField3")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_3))
            .jsonPath("$.[*].billerFreeField4")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_4))
            .jsonPath("$.[*].billerFreeField5")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_5))
            .jsonPath("$.[*].billerFreeField6")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_6))
            .jsonPath("$.[*].billerFreeField7")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_7))
            .jsonPath("$.[*].billerFreeField8")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_8))
            .jsonPath("$.[*].billerFreeField9")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_9))
            .jsonPath("$.[*].billerFreeField10")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_10))
            .jsonPath("$.[*].billerFreeField11")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_11))
            .jsonPath("$.[*].billerFreeField12")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_12))
            .jsonPath("$.[*].billerFreeField13")
            .value(hasItem(DEFAULT_BILLER_FREE_FIELD_13))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
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
