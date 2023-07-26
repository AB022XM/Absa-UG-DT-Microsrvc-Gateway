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
import ug.co.absa.microsrvc.gateway.domain.Apps;
import ug.co.absa.microsrvc.gateway.repository.AppsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.AppsSearchRepository;

/**
 * Integration tests for the {@link AppsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AppsResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APP_CURRENT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_APP_CURRENT_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_APP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_APP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APP_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_APP_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_APP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_APP_STATUS = "BBBBBBBBBB";

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

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATEDBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/apps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/apps";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppsRepository appsRepository;

    @Autowired
    private AppsSearchRepository appsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Apps apps;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apps createEntity(EntityManager em) {
        Apps apps = new Apps()
            .recordId(DEFAULT_RECORD_ID)
            .configId(DEFAULT_CONFIG_ID)
            .appId(DEFAULT_APP_ID)
            .appCurrentVersion(DEFAULT_APP_CURRENT_VERSION)
            .appName(DEFAULT_APP_NAME)
            .appDescription(DEFAULT_APP_DESCRIPTION)
            .appStatus(DEFAULT_APP_STATUS)
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
            .timestamp(DEFAULT_TIMESTAMP)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedby(DEFAULT_UPDATEDBY);
        return apps;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apps createUpdatedEntity(EntityManager em) {
        Apps apps = new Apps()
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .appId(UPDATED_APP_ID)
            .appCurrentVersion(UPDATED_APP_CURRENT_VERSION)
            .appName(UPDATED_APP_NAME)
            .appDescription(UPDATED_APP_DESCRIPTION)
            .appStatus(UPDATED_APP_STATUS)
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
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);
        return apps;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Apps.class).block();
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
        appsSearchRepository.deleteAll().block();
        assertThat(appsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        apps = createEntity(em);
    }

    @Test
    void createApps() throws Exception {
        int databaseSizeBeforeCreate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        // Create the Apps
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Apps testApps = appsList.get(appsList.size() - 1);
        assertThat(testApps.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testApps.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testApps.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testApps.getAppCurrentVersion()).isEqualTo(DEFAULT_APP_CURRENT_VERSION);
        assertThat(testApps.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testApps.getAppDescription()).isEqualTo(DEFAULT_APP_DESCRIPTION);
        assertThat(testApps.getAppStatus()).isEqualTo(DEFAULT_APP_STATUS);
        assertThat(testApps.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testApps.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testApps.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testApps.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testApps.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testApps.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testApps.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testApps.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testApps.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testApps.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testApps.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testApps.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testApps.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testApps.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testApps.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testApps.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testApps.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testApps.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testApps.getUpdatedby()).isEqualTo(DEFAULT_UPDATEDBY);
    }

    @Test
    void createAppsWithExistingId() throws Exception {
        // Create the Apps with an existing ID
        apps.setId(1L);

        int databaseSizeBeforeCreate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAppIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        // set the field null
        apps.setAppId(null);

        // Create the Apps, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAppCurrentVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        // set the field null
        apps.setAppCurrentVersion(null);

        // Create the Apps, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAppNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        // set the field null
        apps.setAppName(null);

        // Create the Apps, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllAppsAsStream() {
        // Initialize the database
        appsRepository.save(apps).block();

        List<Apps> appsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Apps.class)
            .getResponseBody()
            .filter(apps::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(appsList).isNotNull();
        assertThat(appsList).hasSize(1);
        Apps testApps = appsList.get(0);
        assertThat(testApps.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testApps.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testApps.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testApps.getAppCurrentVersion()).isEqualTo(DEFAULT_APP_CURRENT_VERSION);
        assertThat(testApps.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testApps.getAppDescription()).isEqualTo(DEFAULT_APP_DESCRIPTION);
        assertThat(testApps.getAppStatus()).isEqualTo(DEFAULT_APP_STATUS);
        assertThat(testApps.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testApps.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testApps.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testApps.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testApps.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testApps.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testApps.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testApps.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testApps.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testApps.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testApps.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testApps.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testApps.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testApps.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testApps.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testApps.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testApps.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testApps.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testApps.getUpdatedby()).isEqualTo(DEFAULT_UPDATEDBY);
    }

    @Test
    void getAllApps() {
        // Initialize the database
        appsRepository.save(apps).block();

        // Get all the appsList
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
            .value(hasItem(apps.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].appId")
            .value(hasItem(DEFAULT_APP_ID))
            .jsonPath("$.[*].appCurrentVersion")
            .value(hasItem(DEFAULT_APP_CURRENT_VERSION))
            .jsonPath("$.[*].appName")
            .value(hasItem(DEFAULT_APP_NAME))
            .jsonPath("$.[*].appDescription")
            .value(hasItem(DEFAULT_APP_DESCRIPTION))
            .jsonPath("$.[*].appStatus")
            .value(hasItem(DEFAULT_APP_STATUS))
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
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedby")
            .value(hasItem(DEFAULT_UPDATEDBY));
    }

    @Test
    void getApps() {
        // Initialize the database
        appsRepository.save(apps).block();

        // Get the apps
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, apps.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(apps.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.configId")
            .value(is(DEFAULT_CONFIG_ID))
            .jsonPath("$.appId")
            .value(is(DEFAULT_APP_ID))
            .jsonPath("$.appCurrentVersion")
            .value(is(DEFAULT_APP_CURRENT_VERSION))
            .jsonPath("$.appName")
            .value(is(DEFAULT_APP_NAME))
            .jsonPath("$.appDescription")
            .value(is(DEFAULT_APP_DESCRIPTION))
            .jsonPath("$.appStatus")
            .value(is(DEFAULT_APP_STATUS))
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
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedby")
            .value(is(DEFAULT_UPDATEDBY));
    }

    @Test
    void getNonExistingApps() {
        // Get the apps
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingApps() throws Exception {
        // Initialize the database
        appsRepository.save(apps).block();

        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        appsSearchRepository.save(apps).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());

        // Update the apps
        Apps updatedApps = appsRepository.findById(apps.getId()).block();
        updatedApps
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .appId(UPDATED_APP_ID)
            .appCurrentVersion(UPDATED_APP_CURRENT_VERSION)
            .appName(UPDATED_APP_NAME)
            .appDescription(UPDATED_APP_DESCRIPTION)
            .appStatus(UPDATED_APP_STATUS)
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
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedApps.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedApps))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        Apps testApps = appsList.get(appsList.size() - 1);
        assertThat(testApps.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testApps.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testApps.getAppCurrentVersion()).isEqualTo(UPDATED_APP_CURRENT_VERSION);
        assertThat(testApps.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testApps.getAppDescription()).isEqualTo(UPDATED_APP_DESCRIPTION);
        assertThat(testApps.getAppStatus()).isEqualTo(UPDATED_APP_STATUS);
        assertThat(testApps.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testApps.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testApps.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testApps.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testApps.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testApps.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testApps.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testApps.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testApps.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testApps.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testApps.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testApps.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testApps.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testApps.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testApps.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testApps.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testApps.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testApps.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testApps.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Apps> appsSearchList = IterableUtils.toList(appsSearchRepository.findAll().collectList().block());
                Apps testAppsSearch = appsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testAppsSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testAppsSearch.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
                assertThat(testAppsSearch.getAppId()).isEqualTo(UPDATED_APP_ID);
                assertThat(testAppsSearch.getAppCurrentVersion()).isEqualTo(UPDATED_APP_CURRENT_VERSION);
                assertThat(testAppsSearch.getAppName()).isEqualTo(UPDATED_APP_NAME);
                assertThat(testAppsSearch.getAppDescription()).isEqualTo(UPDATED_APP_DESCRIPTION);
                assertThat(testAppsSearch.getAppStatus()).isEqualTo(UPDATED_APP_STATUS);
                assertThat(testAppsSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testAppsSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testAppsSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testAppsSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testAppsSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testAppsSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testAppsSearch.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
                assertThat(testAppsSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testAppsSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testAppsSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testAppsSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testAppsSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testAppsSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testAppsSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testAppsSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testAppsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testAppsSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testAppsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testAppsSearch.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
            });
    }

    @Test
    void putNonExistingApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, apps.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateAppsWithPatch() throws Exception {
        // Initialize the database
        appsRepository.save(apps).block();

        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();

        // Update the apps using partial update
        Apps partialUpdatedApps = new Apps();
        partialUpdatedApps.setId(apps.getId());

        partialUpdatedApps
            .appId(UPDATED_APP_ID)
            .appName(UPDATED_APP_NAME)
            .appDescription(UPDATED_APP_DESCRIPTION)
            .appStatus(UPDATED_APP_STATUS)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedApps.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedApps))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        Apps testApps = appsList.get(appsList.size() - 1);
        assertThat(testApps.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testApps.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testApps.getAppCurrentVersion()).isEqualTo(DEFAULT_APP_CURRENT_VERSION);
        assertThat(testApps.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testApps.getAppDescription()).isEqualTo(UPDATED_APP_DESCRIPTION);
        assertThat(testApps.getAppStatus()).isEqualTo(UPDATED_APP_STATUS);
        assertThat(testApps.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testApps.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testApps.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testApps.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testApps.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testApps.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testApps.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testApps.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testApps.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testApps.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testApps.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testApps.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testApps.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testApps.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testApps.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testApps.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testApps.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testApps.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testApps.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
    }

    @Test
    void fullUpdateAppsWithPatch() throws Exception {
        // Initialize the database
        appsRepository.save(apps).block();

        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();

        // Update the apps using partial update
        Apps partialUpdatedApps = new Apps();
        partialUpdatedApps.setId(apps.getId());

        partialUpdatedApps
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .appId(UPDATED_APP_ID)
            .appCurrentVersion(UPDATED_APP_CURRENT_VERSION)
            .appName(UPDATED_APP_NAME)
            .appDescription(UPDATED_APP_DESCRIPTION)
            .appStatus(UPDATED_APP_STATUS)
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
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedApps.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedApps))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        Apps testApps = appsList.get(appsList.size() - 1);
        assertThat(testApps.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testApps.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testApps.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testApps.getAppCurrentVersion()).isEqualTo(UPDATED_APP_CURRENT_VERSION);
        assertThat(testApps.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testApps.getAppDescription()).isEqualTo(UPDATED_APP_DESCRIPTION);
        assertThat(testApps.getAppStatus()).isEqualTo(UPDATED_APP_STATUS);
        assertThat(testApps.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testApps.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testApps.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testApps.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testApps.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testApps.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testApps.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testApps.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testApps.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testApps.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testApps.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testApps.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testApps.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testApps.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testApps.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testApps.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testApps.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testApps.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testApps.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
    }

    @Test
    void patchNonExistingApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, apps.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamApps() throws Exception {
        int databaseSizeBeforeUpdate = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        apps.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(apps))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Apps in the database
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteApps() {
        // Initialize the database
        appsRepository.save(apps).block();
        appsRepository.save(apps).block();
        appsSearchRepository.save(apps).block();

        int databaseSizeBeforeDelete = appsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the apps
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, apps.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Apps> appsList = appsRepository.findAll().collectList().block();
        assertThat(appsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchApps() {
        // Initialize the database
        apps = appsRepository.save(apps).block();
        appsSearchRepository.save(apps).block();

        // Search the apps
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + apps.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(apps.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].appId")
            .value(hasItem(DEFAULT_APP_ID))
            .jsonPath("$.[*].appCurrentVersion")
            .value(hasItem(DEFAULT_APP_CURRENT_VERSION))
            .jsonPath("$.[*].appName")
            .value(hasItem(DEFAULT_APP_NAME))
            .jsonPath("$.[*].appDescription")
            .value(hasItem(DEFAULT_APP_DESCRIPTION))
            .jsonPath("$.[*].appStatus")
            .value(hasItem(DEFAULT_APP_STATUS))
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
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedby")
            .value(hasItem(DEFAULT_UPDATEDBY));
    }
}
