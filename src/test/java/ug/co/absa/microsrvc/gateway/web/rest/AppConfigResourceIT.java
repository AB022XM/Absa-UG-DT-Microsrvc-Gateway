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
import ug.co.absa.microsrvc.gateway.domain.AppConfig;
import ug.co.absa.microsrvc.gateway.repository.AppConfigRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.AppConfigSearchRepository;

/**
 * Integration tests for the {@link AppConfigResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AppConfigResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_STATUS = "BBBBBBBBBB";

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

    private static final byte[] DEFAULT_FREE_FIELD_14 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_14 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_14_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_14_CONTENT_TYPE = "image/png";

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

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/app-configs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/app-configs";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Autowired
    private AppConfigSearchRepository appConfigSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AppConfig appConfig;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfig createEntity(EntityManager em) {
        AppConfig appConfig = new AppConfig()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .configId(DEFAULT_CONFIG_ID)
            .configName(DEFAULT_CONFIG_NAME)
            .configValue(DEFAULT_CONFIG_VALUE)
            .configType(DEFAULT_CONFIG_TYPE)
            .configDescription(DEFAULT_CONFIG_DESCRIPTION)
            .configStatus(DEFAULT_CONFIG_STATUS)
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
            .freeField14(DEFAULT_FREE_FIELD_14)
            .freeField14ContentType(DEFAULT_FREE_FIELD_14_CONTENT_TYPE)
            .freeField15(DEFAULT_FREE_FIELD_15)
            .freeField15ContentType(DEFAULT_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(DEFAULT_FREE_FIELD_16)
            .freeField17(DEFAULT_FREE_FIELD_17)
            .freeField18(DEFAULT_FREE_FIELD_18)
            .freeField18ContentType(DEFAULT_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(DEFAULT_FREE_FIELD_19)
            .delflg(DEFAULT_DELFLG)
            .timestamp(DEFAULT_TIMESTAMP)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return appConfig;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppConfig createUpdatedEntity(EntityManager em) {
        AppConfig appConfig = new AppConfig()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configValue(UPDATED_CONFIG_VALUE)
            .configType(UPDATED_CONFIG_TYPE)
            .configDescription(UPDATED_CONFIG_DESCRIPTION)
            .configStatus(UPDATED_CONFIG_STATUS)
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
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField14ContentType(UPDATED_FREE_FIELD_14_CONTENT_TYPE)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .delflg(UPDATED_DELFLG)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return appConfig;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AppConfig.class).block();
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
        appConfigSearchRepository.deleteAll().block();
        assertThat(appConfigSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        appConfig = createEntity(em);
    }

    @Test
    void createAppConfig() throws Exception {
        int databaseSizeBeforeCreate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        // Create the AppConfig
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAppConfig.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testAppConfig.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testAppConfig.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testAppConfig.getConfigValue()).isEqualTo(DEFAULT_CONFIG_VALUE);
        assertThat(testAppConfig.getConfigType()).isEqualTo(DEFAULT_CONFIG_TYPE);
        assertThat(testAppConfig.getConfigDescription()).isEqualTo(DEFAULT_CONFIG_DESCRIPTION);
        assertThat(testAppConfig.getConfigStatus()).isEqualTo(DEFAULT_CONFIG_STATUS);
        assertThat(testAppConfig.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testAppConfig.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testAppConfig.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testAppConfig.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testAppConfig.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testAppConfig.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testAppConfig.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testAppConfig.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testAppConfig.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testAppConfig.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testAppConfig.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testAppConfig.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testAppConfig.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testAppConfig.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testAppConfig.getFreeField14ContentType()).isEqualTo(DEFAULT_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testAppConfig.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testAppConfig.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testAppConfig.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testAppConfig.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testAppConfig.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testAppConfig.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testAppConfig.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAppConfig.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAppConfig.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createAppConfigWithExistingId() throws Exception {
        // Create the AppConfig with an existing ID
        appConfig.setId(1L);

        int databaseSizeBeforeCreate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkConfigNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        // set the field null
        appConfig.setConfigName(null);

        // Create the AppConfig, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkConfigTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        // set the field null
        appConfig.setConfigType(null);

        // Create the AppConfig, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllAppConfigsAsStream() {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        List<AppConfig> appConfigList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AppConfig.class)
            .getResponseBody()
            .filter(appConfig::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(appConfigList).isNotNull();
        assertThat(appConfigList).hasSize(1);
        AppConfig testAppConfig = appConfigList.get(0);
        assertThat(testAppConfig.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAppConfig.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testAppConfig.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testAppConfig.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testAppConfig.getConfigValue()).isEqualTo(DEFAULT_CONFIG_VALUE);
        assertThat(testAppConfig.getConfigType()).isEqualTo(DEFAULT_CONFIG_TYPE);
        assertThat(testAppConfig.getConfigDescription()).isEqualTo(DEFAULT_CONFIG_DESCRIPTION);
        assertThat(testAppConfig.getConfigStatus()).isEqualTo(DEFAULT_CONFIG_STATUS);
        assertThat(testAppConfig.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testAppConfig.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testAppConfig.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testAppConfig.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testAppConfig.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testAppConfig.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testAppConfig.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testAppConfig.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testAppConfig.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testAppConfig.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testAppConfig.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testAppConfig.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testAppConfig.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testAppConfig.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testAppConfig.getFreeField14ContentType()).isEqualTo(DEFAULT_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testAppConfig.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testAppConfig.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testAppConfig.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testAppConfig.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testAppConfig.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testAppConfig.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testAppConfig.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAppConfig.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAppConfig.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllAppConfigs() {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        // Get all the appConfigList
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
            .value(hasItem(appConfig.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].configName")
            .value(hasItem(DEFAULT_CONFIG_NAME))
            .jsonPath("$.[*].configValue")
            .value(hasItem(DEFAULT_CONFIG_VALUE.toString()))
            .jsonPath("$.[*].configType")
            .value(hasItem(DEFAULT_CONFIG_TYPE))
            .jsonPath("$.[*].configDescription")
            .value(hasItem(DEFAULT_CONFIG_DESCRIPTION))
            .jsonPath("$.[*].configStatus")
            .value(hasItem(DEFAULT_CONFIG_STATUS))
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
            .jsonPath("$.[*].freeField14ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_14)))
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
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }

    @Test
    void getAppConfig() {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        // Get the appConfig
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, appConfig.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(appConfig.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.configId")
            .value(is(DEFAULT_CONFIG_ID))
            .jsonPath("$.configName")
            .value(is(DEFAULT_CONFIG_NAME))
            .jsonPath("$.configValue")
            .value(is(DEFAULT_CONFIG_VALUE.toString()))
            .jsonPath("$.configType")
            .value(is(DEFAULT_CONFIG_TYPE))
            .jsonPath("$.configDescription")
            .value(is(DEFAULT_CONFIG_DESCRIPTION))
            .jsonPath("$.configStatus")
            .value(is(DEFAULT_CONFIG_STATUS))
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
            .jsonPath("$.freeField14ContentType")
            .value(is(DEFAULT_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.freeField14")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_14)))
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
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY));
    }

    @Test
    void getNonExistingAppConfig() {
        // Get the appConfig
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAppConfig() throws Exception {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        appConfigSearchRepository.save(appConfig).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());

        // Update the appConfig
        AppConfig updatedAppConfig = appConfigRepository.findById(appConfig.getId()).block();
        updatedAppConfig
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configValue(UPDATED_CONFIG_VALUE)
            .configType(UPDATED_CONFIG_TYPE)
            .configDescription(UPDATED_CONFIG_DESCRIPTION)
            .configStatus(UPDATED_CONFIG_STATUS)
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
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField14ContentType(UPDATED_FREE_FIELD_14_CONTENT_TYPE)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .delflg(UPDATED_DELFLG)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAppConfig.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedAppConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAppConfig.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testAppConfig.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testAppConfig.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
        assertThat(testAppConfig.getConfigValue()).isEqualTo(UPDATED_CONFIG_VALUE);
        assertThat(testAppConfig.getConfigType()).isEqualTo(UPDATED_CONFIG_TYPE);
        assertThat(testAppConfig.getConfigDescription()).isEqualTo(UPDATED_CONFIG_DESCRIPTION);
        assertThat(testAppConfig.getConfigStatus()).isEqualTo(UPDATED_CONFIG_STATUS);
        assertThat(testAppConfig.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testAppConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testAppConfig.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testAppConfig.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testAppConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testAppConfig.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testAppConfig.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testAppConfig.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testAppConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testAppConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testAppConfig.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testAppConfig.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testAppConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testAppConfig.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testAppConfig.getFreeField14ContentType()).isEqualTo(UPDATED_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testAppConfig.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testAppConfig.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testAppConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testAppConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testAppConfig.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testAppConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAppConfig.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAppConfig.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAppConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<AppConfig> appConfigSearchList = IterableUtils.toList(appConfigSearchRepository.findAll().collectList().block());
                AppConfig testAppConfigSearch = appConfigSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testAppConfigSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testAppConfigSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testAppConfigSearch.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
                assertThat(testAppConfigSearch.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
                assertThat(testAppConfigSearch.getConfigValue()).isEqualTo(UPDATED_CONFIG_VALUE);
                assertThat(testAppConfigSearch.getConfigType()).isEqualTo(UPDATED_CONFIG_TYPE);
                assertThat(testAppConfigSearch.getConfigDescription()).isEqualTo(UPDATED_CONFIG_DESCRIPTION);
                assertThat(testAppConfigSearch.getConfigStatus()).isEqualTo(UPDATED_CONFIG_STATUS);
                assertThat(testAppConfigSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testAppConfigSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testAppConfigSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testAppConfigSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testAppConfigSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testAppConfigSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testAppConfigSearch.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
                assertThat(testAppConfigSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testAppConfigSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testAppConfigSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testAppConfigSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testAppConfigSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testAppConfigSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testAppConfigSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testAppConfigSearch.getFreeField14ContentType()).isEqualTo(UPDATED_FREE_FIELD_14_CONTENT_TYPE);
                assertThat(testAppConfigSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testAppConfigSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testAppConfigSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testAppConfigSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testAppConfigSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testAppConfigSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testAppConfigSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testAppConfigSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testAppConfigSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testAppConfigSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testAppConfigSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testAppConfigSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testAppConfigSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, appConfig.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateAppConfigWithPatch() throws Exception {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();

        // Update the appConfig using partial update
        AppConfig partialUpdatedAppConfig = new AppConfig();
        partialUpdatedAppConfig.setId(appConfig.getId());

        partialUpdatedAppConfig
            .recordId(UPDATED_RECORD_ID)
            .configType(UPDATED_CONFIG_TYPE)
            .configStatus(UPDATED_CONFIG_STATUS)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField14ContentType(UPDATED_FREE_FIELD_14_CONTENT_TYPE)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .delflg(UPDATED_DELFLG)
            .timestamp(UPDATED_TIMESTAMP)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAppConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAppConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAppConfig.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testAppConfig.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testAppConfig.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testAppConfig.getConfigValue()).isEqualTo(DEFAULT_CONFIG_VALUE);
        assertThat(testAppConfig.getConfigType()).isEqualTo(UPDATED_CONFIG_TYPE);
        assertThat(testAppConfig.getConfigDescription()).isEqualTo(DEFAULT_CONFIG_DESCRIPTION);
        assertThat(testAppConfig.getConfigStatus()).isEqualTo(UPDATED_CONFIG_STATUS);
        assertThat(testAppConfig.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testAppConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testAppConfig.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testAppConfig.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testAppConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testAppConfig.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testAppConfig.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testAppConfig.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testAppConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testAppConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testAppConfig.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testAppConfig.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testAppConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testAppConfig.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testAppConfig.getFreeField14ContentType()).isEqualTo(UPDATED_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testAppConfig.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testAppConfig.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testAppConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testAppConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testAppConfig.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testAppConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAppConfig.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAppConfig.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAppConfig.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAppConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateAppConfigWithPatch() throws Exception {
        // Initialize the database
        appConfigRepository.save(appConfig).block();

        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();

        // Update the appConfig using partial update
        AppConfig partialUpdatedAppConfig = new AppConfig();
        partialUpdatedAppConfig.setId(appConfig.getId());

        partialUpdatedAppConfig
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configValue(UPDATED_CONFIG_VALUE)
            .configType(UPDATED_CONFIG_TYPE)
            .configDescription(UPDATED_CONFIG_DESCRIPTION)
            .configStatus(UPDATED_CONFIG_STATUS)
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
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField14ContentType(UPDATED_FREE_FIELD_14_CONTENT_TYPE)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .delflg(UPDATED_DELFLG)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAppConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAppConfig))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        AppConfig testAppConfig = appConfigList.get(appConfigList.size() - 1);
        assertThat(testAppConfig.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAppConfig.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testAppConfig.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testAppConfig.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
        assertThat(testAppConfig.getConfigValue()).isEqualTo(UPDATED_CONFIG_VALUE);
        assertThat(testAppConfig.getConfigType()).isEqualTo(UPDATED_CONFIG_TYPE);
        assertThat(testAppConfig.getConfigDescription()).isEqualTo(UPDATED_CONFIG_DESCRIPTION);
        assertThat(testAppConfig.getConfigStatus()).isEqualTo(UPDATED_CONFIG_STATUS);
        assertThat(testAppConfig.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testAppConfig.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testAppConfig.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testAppConfig.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testAppConfig.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testAppConfig.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testAppConfig.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testAppConfig.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testAppConfig.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testAppConfig.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testAppConfig.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testAppConfig.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testAppConfig.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testAppConfig.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testAppConfig.getFreeField14ContentType()).isEqualTo(UPDATED_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testAppConfig.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testAppConfig.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testAppConfig.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testAppConfig.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testAppConfig.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testAppConfig.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testAppConfig.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAppConfig.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAppConfig.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAppConfig.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAppConfig.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, appConfig.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamAppConfig() throws Exception {
        int databaseSizeBeforeUpdate = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        appConfig.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(appConfig))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AppConfig in the database
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteAppConfig() {
        // Initialize the database
        appConfigRepository.save(appConfig).block();
        appConfigRepository.save(appConfig).block();
        appConfigSearchRepository.save(appConfig).block();

        int databaseSizeBeforeDelete = appConfigRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the appConfig
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, appConfig.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AppConfig> appConfigList = appConfigRepository.findAll().collectList().block();
        assertThat(appConfigList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(appConfigSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchAppConfig() {
        // Initialize the database
        appConfig = appConfigRepository.save(appConfig).block();
        appConfigSearchRepository.save(appConfig).block();

        // Search the appConfig
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + appConfig.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(appConfig.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].configName")
            .value(hasItem(DEFAULT_CONFIG_NAME))
            .jsonPath("$.[*].configValue")
            .value(hasItem(DEFAULT_CONFIG_VALUE.toString()))
            .jsonPath("$.[*].configType")
            .value(hasItem(DEFAULT_CONFIG_TYPE))
            .jsonPath("$.[*].configDescription")
            .value(hasItem(DEFAULT_CONFIG_DESCRIPTION))
            .jsonPath("$.[*].configStatus")
            .value(hasItem(DEFAULT_CONFIG_STATUS))
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
            .jsonPath("$.[*].freeField14ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_14)))
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
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }
}
