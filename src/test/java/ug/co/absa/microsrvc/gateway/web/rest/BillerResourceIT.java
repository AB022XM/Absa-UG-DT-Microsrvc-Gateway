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
import ug.co.absa.microsrvc.gateway.domain.Biller;
import ug.co.absa.microsrvc.gateway.repository.BillerRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.BillerSearchRepository;

/**
 * Integration tests for the {@link BillerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BillerResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLER_CATEGORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_CATEGORY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_1 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_2 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_3 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_4 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_5 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_6 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_NARATIVE_7 = "AAAAAAAAAA";
    private static final String UPDATED_NARATIVE_7 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String DEFAULT_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_7 = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/billers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/billers";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private BillerSearchRepository billerSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Biller biller;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biller createEntity(EntityManager em) {
        Biller biller = new Biller()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .billerCode(DEFAULT_BILLER_CODE)
            .billerName(DEFAULT_BILLER_NAME)
            .billerCategoryId(DEFAULT_BILLER_CATEGORY_ID)
            .addressId(DEFAULT_ADDRESS_ID)
            .narative(DEFAULT_NARATIVE)
            .narative1(DEFAULT_NARATIVE_1)
            .narative2(DEFAULT_NARATIVE_2)
            .narative3(DEFAULT_NARATIVE_3)
            .narative4(DEFAULT_NARATIVE_4)
            .narative5(DEFAULT_NARATIVE_5)
            .narative6(DEFAULT_NARATIVE_6)
            .narative7(DEFAULT_NARATIVE_7)
            .timestamp(DEFAULT_TIMESTAMP)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10)
            .freeField11(DEFAULT_FREE_FIELD_11)
            .freeField12(DEFAULT_FREE_FIELD_12)
            .freeField13(DEFAULT_FREE_FIELD_13)
            .delflg(DEFAULT_DELFLG)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return biller;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biller createUpdatedEntity(EntityManager em) {
        Biller biller = new Biller()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .billerCode(UPDATED_BILLER_CODE)
            .billerName(UPDATED_BILLER_NAME)
            .billerCategoryId(UPDATED_BILLER_CATEGORY_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .narative(UPDATED_NARATIVE)
            .narative1(UPDATED_NARATIVE_1)
            .narative2(UPDATED_NARATIVE_2)
            .narative3(UPDATED_NARATIVE_3)
            .narative4(UPDATED_NARATIVE_4)
            .narative5(UPDATED_NARATIVE_5)
            .narative6(UPDATED_NARATIVE_6)
            .narative7(UPDATED_NARATIVE_7)
            .timestamp(UPDATED_TIMESTAMP)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return biller;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Biller.class).block();
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
        billerSearchRepository.deleteAll().block();
        assertThat(billerSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        biller = createEntity(em);
    }

    @Test
    void createBiller() throws Exception {
        int databaseSizeBeforeCreate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        // Create the Biller
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBiller.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBiller.getBillerCode()).isEqualTo(DEFAULT_BILLER_CODE);
        assertThat(testBiller.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
        assertThat(testBiller.getBillerCategoryId()).isEqualTo(DEFAULT_BILLER_CATEGORY_ID);
        assertThat(testBiller.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testBiller.getNarative()).isEqualTo(DEFAULT_NARATIVE);
        assertThat(testBiller.getNarative1()).isEqualTo(DEFAULT_NARATIVE_1);
        assertThat(testBiller.getNarative2()).isEqualTo(DEFAULT_NARATIVE_2);
        assertThat(testBiller.getNarative3()).isEqualTo(DEFAULT_NARATIVE_3);
        assertThat(testBiller.getNarative4()).isEqualTo(DEFAULT_NARATIVE_4);
        assertThat(testBiller.getNarative5()).isEqualTo(DEFAULT_NARATIVE_5);
        assertThat(testBiller.getNarative6()).isEqualTo(DEFAULT_NARATIVE_6);
        assertThat(testBiller.getNarative7()).isEqualTo(DEFAULT_NARATIVE_7);
        assertThat(testBiller.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBiller.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testBiller.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testBiller.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testBiller.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testBiller.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testBiller.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testBiller.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testBiller.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testBiller.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testBiller.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testBiller.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testBiller.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testBiller.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testBiller.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBiller.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBiller.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiller.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBiller.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createBillerWithExistingId() throws Exception {
        // Create the Biller with an existing ID
        biller.setId(1L);

        int databaseSizeBeforeCreate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        // set the field null
        biller.setBillerId(null);

        // Create the Biller, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        // set the field null
        biller.setBillerCode(null);

        // Create the Biller, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        // set the field null
        biller.setCreatedAt(null);

        // Create the Biller, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllBillersAsStream() {
        // Initialize the database
        billerRepository.save(biller).block();

        List<Biller> billerList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Biller.class)
            .getResponseBody()
            .filter(biller::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(billerList).isNotNull();
        assertThat(billerList).hasSize(1);
        Biller testBiller = billerList.get(0);
        assertThat(testBiller.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBiller.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBiller.getBillerCode()).isEqualTo(DEFAULT_BILLER_CODE);
        assertThat(testBiller.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
        assertThat(testBiller.getBillerCategoryId()).isEqualTo(DEFAULT_BILLER_CATEGORY_ID);
        assertThat(testBiller.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testBiller.getNarative()).isEqualTo(DEFAULT_NARATIVE);
        assertThat(testBiller.getNarative1()).isEqualTo(DEFAULT_NARATIVE_1);
        assertThat(testBiller.getNarative2()).isEqualTo(DEFAULT_NARATIVE_2);
        assertThat(testBiller.getNarative3()).isEqualTo(DEFAULT_NARATIVE_3);
        assertThat(testBiller.getNarative4()).isEqualTo(DEFAULT_NARATIVE_4);
        assertThat(testBiller.getNarative5()).isEqualTo(DEFAULT_NARATIVE_5);
        assertThat(testBiller.getNarative6()).isEqualTo(DEFAULT_NARATIVE_6);
        assertThat(testBiller.getNarative7()).isEqualTo(DEFAULT_NARATIVE_7);
        assertThat(testBiller.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBiller.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testBiller.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testBiller.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testBiller.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testBiller.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testBiller.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testBiller.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testBiller.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testBiller.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testBiller.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testBiller.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testBiller.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testBiller.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testBiller.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBiller.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBiller.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiller.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBiller.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllBillers() {
        // Initialize the database
        billerRepository.save(biller).block();

        // Get all the billerList
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
            .value(hasItem(biller.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].billerCode")
            .value(hasItem(DEFAULT_BILLER_CODE))
            .jsonPath("$.[*].billerName")
            .value(hasItem(DEFAULT_BILLER_NAME))
            .jsonPath("$.[*].billerCategoryId")
            .value(hasItem(DEFAULT_BILLER_CATEGORY_ID))
            .jsonPath("$.[*].addressId")
            .value(hasItem(DEFAULT_ADDRESS_ID))
            .jsonPath("$.[*].narative")
            .value(hasItem(DEFAULT_NARATIVE))
            .jsonPath("$.[*].narative1")
            .value(hasItem(DEFAULT_NARATIVE_1))
            .jsonPath("$.[*].narative2")
            .value(hasItem(DEFAULT_NARATIVE_2))
            .jsonPath("$.[*].narative3")
            .value(hasItem(DEFAULT_NARATIVE_3))
            .jsonPath("$.[*].narative4")
            .value(hasItem(DEFAULT_NARATIVE_4))
            .jsonPath("$.[*].narative5")
            .value(hasItem(DEFAULT_NARATIVE_5))
            .jsonPath("$.[*].narative6")
            .value(hasItem(DEFAULT_NARATIVE_6))
            .jsonPath("$.[*].narative7")
            .value(hasItem(DEFAULT_NARATIVE_7))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
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
            .jsonPath("$.[*].freeField7")
            .value(hasItem(DEFAULT_FREE_FIELD_7))
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
    void getBiller() {
        // Initialize the database
        billerRepository.save(biller).block();

        // Get the biller
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, biller.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(biller.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.billerCode")
            .value(is(DEFAULT_BILLER_CODE))
            .jsonPath("$.billerName")
            .value(is(DEFAULT_BILLER_NAME))
            .jsonPath("$.billerCategoryId")
            .value(is(DEFAULT_BILLER_CATEGORY_ID))
            .jsonPath("$.addressId")
            .value(is(DEFAULT_ADDRESS_ID))
            .jsonPath("$.narative")
            .value(is(DEFAULT_NARATIVE))
            .jsonPath("$.narative1")
            .value(is(DEFAULT_NARATIVE_1))
            .jsonPath("$.narative2")
            .value(is(DEFAULT_NARATIVE_2))
            .jsonPath("$.narative3")
            .value(is(DEFAULT_NARATIVE_3))
            .jsonPath("$.narative4")
            .value(is(DEFAULT_NARATIVE_4))
            .jsonPath("$.narative5")
            .value(is(DEFAULT_NARATIVE_5))
            .jsonPath("$.narative6")
            .value(is(DEFAULT_NARATIVE_6))
            .jsonPath("$.narative7")
            .value(is(DEFAULT_NARATIVE_7))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
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
            .jsonPath("$.freeField7")
            .value(is(DEFAULT_FREE_FIELD_7))
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
    void getNonExistingBiller() {
        // Get the biller
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBiller() throws Exception {
        // Initialize the database
        billerRepository.save(biller).block();

        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        billerSearchRepository.save(biller).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());

        // Update the biller
        Biller updatedBiller = billerRepository.findById(biller.getId()).block();
        updatedBiller
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .billerCode(UPDATED_BILLER_CODE)
            .billerName(UPDATED_BILLER_NAME)
            .billerCategoryId(UPDATED_BILLER_CATEGORY_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .narative(UPDATED_NARATIVE)
            .narative1(UPDATED_NARATIVE_1)
            .narative2(UPDATED_NARATIVE_2)
            .narative3(UPDATED_NARATIVE_3)
            .narative4(UPDATED_NARATIVE_4)
            .narative5(UPDATED_NARATIVE_5)
            .narative6(UPDATED_NARATIVE_6)
            .narative7(UPDATED_NARATIVE_7)
            .timestamp(UPDATED_TIMESTAMP)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBiller.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBiller))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBiller.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBiller.getBillerCode()).isEqualTo(UPDATED_BILLER_CODE);
        assertThat(testBiller.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
        assertThat(testBiller.getBillerCategoryId()).isEqualTo(UPDATED_BILLER_CATEGORY_ID);
        assertThat(testBiller.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testBiller.getNarative()).isEqualTo(UPDATED_NARATIVE);
        assertThat(testBiller.getNarative1()).isEqualTo(UPDATED_NARATIVE_1);
        assertThat(testBiller.getNarative2()).isEqualTo(UPDATED_NARATIVE_2);
        assertThat(testBiller.getNarative3()).isEqualTo(UPDATED_NARATIVE_3);
        assertThat(testBiller.getNarative4()).isEqualTo(UPDATED_NARATIVE_4);
        assertThat(testBiller.getNarative5()).isEqualTo(UPDATED_NARATIVE_5);
        assertThat(testBiller.getNarative6()).isEqualTo(UPDATED_NARATIVE_6);
        assertThat(testBiller.getNarative7()).isEqualTo(UPDATED_NARATIVE_7);
        assertThat(testBiller.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBiller.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testBiller.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testBiller.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testBiller.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testBiller.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testBiller.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testBiller.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testBiller.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testBiller.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testBiller.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testBiller.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testBiller.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testBiller.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testBiller.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBiller.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBiller.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiller.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBiller.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Biller> billerSearchList = IterableUtils.toList(billerSearchRepository.findAll().collectList().block());
                Biller testBillerSearch = billerSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testBillerSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testBillerSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testBillerSearch.getBillerCode()).isEqualTo(UPDATED_BILLER_CODE);
                assertThat(testBillerSearch.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
                assertThat(testBillerSearch.getBillerCategoryId()).isEqualTo(UPDATED_BILLER_CATEGORY_ID);
                assertThat(testBillerSearch.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
                assertThat(testBillerSearch.getNarative()).isEqualTo(UPDATED_NARATIVE);
                assertThat(testBillerSearch.getNarative1()).isEqualTo(UPDATED_NARATIVE_1);
                assertThat(testBillerSearch.getNarative2()).isEqualTo(UPDATED_NARATIVE_2);
                assertThat(testBillerSearch.getNarative3()).isEqualTo(UPDATED_NARATIVE_3);
                assertThat(testBillerSearch.getNarative4()).isEqualTo(UPDATED_NARATIVE_4);
                assertThat(testBillerSearch.getNarative5()).isEqualTo(UPDATED_NARATIVE_5);
                assertThat(testBillerSearch.getNarative6()).isEqualTo(UPDATED_NARATIVE_6);
                assertThat(testBillerSearch.getNarative7()).isEqualTo(UPDATED_NARATIVE_7);
                assertThat(testBillerSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testBillerSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testBillerSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testBillerSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testBillerSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testBillerSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testBillerSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testBillerSearch.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
                assertThat(testBillerSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testBillerSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testBillerSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testBillerSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testBillerSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testBillerSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testBillerSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testBillerSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testBillerSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testBillerSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testBillerSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, biller.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateBillerWithPatch() throws Exception {
        // Initialize the database
        billerRepository.save(biller).block();

        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();

        // Update the biller using partial update
        Biller partialUpdatedBiller = new Biller();
        partialUpdatedBiller.setId(biller.getId());

        partialUpdatedBiller
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .billerName(UPDATED_BILLER_NAME)
            .narative(UPDATED_NARATIVE)
            .narative2(UPDATED_NARATIVE_2)
            .narative4(UPDATED_NARATIVE_4)
            .narative7(UPDATED_NARATIVE_7)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBiller.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBiller))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBiller.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBiller.getBillerCode()).isEqualTo(DEFAULT_BILLER_CODE);
        assertThat(testBiller.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
        assertThat(testBiller.getBillerCategoryId()).isEqualTo(DEFAULT_BILLER_CATEGORY_ID);
        assertThat(testBiller.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testBiller.getNarative()).isEqualTo(UPDATED_NARATIVE);
        assertThat(testBiller.getNarative1()).isEqualTo(DEFAULT_NARATIVE_1);
        assertThat(testBiller.getNarative2()).isEqualTo(UPDATED_NARATIVE_2);
        assertThat(testBiller.getNarative3()).isEqualTo(DEFAULT_NARATIVE_3);
        assertThat(testBiller.getNarative4()).isEqualTo(UPDATED_NARATIVE_4);
        assertThat(testBiller.getNarative5()).isEqualTo(DEFAULT_NARATIVE_5);
        assertThat(testBiller.getNarative6()).isEqualTo(DEFAULT_NARATIVE_6);
        assertThat(testBiller.getNarative7()).isEqualTo(UPDATED_NARATIVE_7);
        assertThat(testBiller.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBiller.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testBiller.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testBiller.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testBiller.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testBiller.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testBiller.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testBiller.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testBiller.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testBiller.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testBiller.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testBiller.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testBiller.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testBiller.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testBiller.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBiller.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBiller.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBiller.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBiller.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateBillerWithPatch() throws Exception {
        // Initialize the database
        billerRepository.save(biller).block();

        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();

        // Update the biller using partial update
        Biller partialUpdatedBiller = new Biller();
        partialUpdatedBiller.setId(biller.getId());

        partialUpdatedBiller
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .billerCode(UPDATED_BILLER_CODE)
            .billerName(UPDATED_BILLER_NAME)
            .billerCategoryId(UPDATED_BILLER_CATEGORY_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .narative(UPDATED_NARATIVE)
            .narative1(UPDATED_NARATIVE_1)
            .narative2(UPDATED_NARATIVE_2)
            .narative3(UPDATED_NARATIVE_3)
            .narative4(UPDATED_NARATIVE_4)
            .narative5(UPDATED_NARATIVE_5)
            .narative6(UPDATED_NARATIVE_6)
            .narative7(UPDATED_NARATIVE_7)
            .timestamp(UPDATED_TIMESTAMP)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBiller.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBiller))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBiller.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBiller.getBillerCode()).isEqualTo(UPDATED_BILLER_CODE);
        assertThat(testBiller.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
        assertThat(testBiller.getBillerCategoryId()).isEqualTo(UPDATED_BILLER_CATEGORY_ID);
        assertThat(testBiller.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testBiller.getNarative()).isEqualTo(UPDATED_NARATIVE);
        assertThat(testBiller.getNarative1()).isEqualTo(UPDATED_NARATIVE_1);
        assertThat(testBiller.getNarative2()).isEqualTo(UPDATED_NARATIVE_2);
        assertThat(testBiller.getNarative3()).isEqualTo(UPDATED_NARATIVE_3);
        assertThat(testBiller.getNarative4()).isEqualTo(UPDATED_NARATIVE_4);
        assertThat(testBiller.getNarative5()).isEqualTo(UPDATED_NARATIVE_5);
        assertThat(testBiller.getNarative6()).isEqualTo(UPDATED_NARATIVE_6);
        assertThat(testBiller.getNarative7()).isEqualTo(UPDATED_NARATIVE_7);
        assertThat(testBiller.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBiller.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testBiller.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testBiller.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testBiller.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testBiller.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testBiller.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testBiller.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testBiller.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testBiller.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testBiller.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testBiller.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testBiller.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testBiller.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testBiller.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBiller.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBiller.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBiller.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBiller.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, biller.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        biller.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(biller))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteBiller() {
        // Initialize the database
        billerRepository.save(biller).block();
        billerRepository.save(biller).block();
        billerSearchRepository.save(biller).block();

        int databaseSizeBeforeDelete = billerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the biller
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, biller.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Biller> billerList = billerRepository.findAll().collectList().block();
        assertThat(billerList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(billerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchBiller() {
        // Initialize the database
        biller = billerRepository.save(biller).block();
        billerSearchRepository.save(biller).block();

        // Search the biller
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + biller.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(biller.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].billerCode")
            .value(hasItem(DEFAULT_BILLER_CODE))
            .jsonPath("$.[*].billerName")
            .value(hasItem(DEFAULT_BILLER_NAME))
            .jsonPath("$.[*].billerCategoryId")
            .value(hasItem(DEFAULT_BILLER_CATEGORY_ID))
            .jsonPath("$.[*].addressId")
            .value(hasItem(DEFAULT_ADDRESS_ID))
            .jsonPath("$.[*].narative")
            .value(hasItem(DEFAULT_NARATIVE))
            .jsonPath("$.[*].narative1")
            .value(hasItem(DEFAULT_NARATIVE_1))
            .jsonPath("$.[*].narative2")
            .value(hasItem(DEFAULT_NARATIVE_2))
            .jsonPath("$.[*].narative3")
            .value(hasItem(DEFAULT_NARATIVE_3))
            .jsonPath("$.[*].narative4")
            .value(hasItem(DEFAULT_NARATIVE_4))
            .jsonPath("$.[*].narative5")
            .value(hasItem(DEFAULT_NARATIVE_5))
            .jsonPath("$.[*].narative6")
            .value(hasItem(DEFAULT_NARATIVE_6))
            .jsonPath("$.[*].narative7")
            .value(hasItem(DEFAULT_NARATIVE_7))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
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
            .jsonPath("$.[*].freeField7")
            .value(hasItem(DEFAULT_FREE_FIELD_7))
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
