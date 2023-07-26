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
import ug.co.absa.microsrvc.gateway.domain.DefaultSettings;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.DefaultSettingsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DefaultSettingsSearchRepository;

/**
 * Integration tests for the {@link DefaultSettingsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DefaultSettingsResourceIT {

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

    private static final String DEFAULT_THIRD_PARTY_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_THIRD_PARTY_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_SETTING_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_SETTING_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_JSON_SETTINGS = "AAAAAAAAAA";
    private static final String UPDATED_JSON_SETTINGS = "BBBBBBBBBB";

    private static final String DEFAULT_APP_CONFIG = "AAAAAAAAAA";
    private static final String UPDATED_APP_CONFIG = "BBBBBBBBBB";

    private static final String DEFAULT_APP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_APP_NAME = "BBBBBBBBBB";

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

    private static final String DEFAULT_UPDATEDBY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATEDBY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/default-settings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/default-settings";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DefaultSettingsRepository defaultSettingsRepository;

    @Autowired
    private DefaultSettingsSearchRepository defaultSettingsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DefaultSettings defaultSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultSettings createEntity(EntityManager em) {
        DefaultSettings defaultSettings = new DefaultSettings()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .thirdPartyDTransactionId(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID)
            .defaultSettingCode(DEFAULT_DEFAULT_SETTING_CODE)
            .jsonSettings(DEFAULT_JSON_SETTINGS)
            .appConfig(DEFAULT_APP_CONFIG)
            .appName(DEFAULT_APP_NAME)
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
            .timestamp(DEFAULT_TIMESTAMP)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedby(DEFAULT_UPDATEDBY);
        return defaultSettings;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultSettings createUpdatedEntity(EntityManager em) {
        DefaultSettings defaultSettings = new DefaultSettings()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .thirdPartyDTransactionId(UPDATED_THIRD_PARTY_D_TRANSACTION_ID)
            .defaultSettingCode(UPDATED_DEFAULT_SETTING_CODE)
            .jsonSettings(UPDATED_JSON_SETTINGS)
            .appConfig(UPDATED_APP_CONFIG)
            .appName(UPDATED_APP_NAME)
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
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);
        return defaultSettings;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DefaultSettings.class).block();
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
        defaultSettingsSearchRepository.deleteAll().block();
        assertThat(defaultSettingsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        defaultSettings = createEntity(em);
    }

    @Test
    void createDefaultSettings() throws Exception {
        int databaseSizeBeforeCreate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        // Create the DefaultSettings
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DefaultSettings testDefaultSettings = defaultSettingsList.get(defaultSettingsList.size() - 1);
        assertThat(testDefaultSettings.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDefaultSettings.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDefaultSettings.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDefaultSettings.getThirdPartyDTransactionId()).isEqualTo(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getDefaultSettingCode()).isEqualTo(DEFAULT_DEFAULT_SETTING_CODE);
        assertThat(testDefaultSettings.getJsonSettings()).isEqualTo(DEFAULT_JSON_SETTINGS);
        assertThat(testDefaultSettings.getAppConfig()).isEqualTo(DEFAULT_APP_CONFIG);
        assertThat(testDefaultSettings.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testDefaultSettings.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testDefaultSettings.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDefaultSettings.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDefaultSettings.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDefaultSettings.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDefaultSettings.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDefaultSettings.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDefaultSettings.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDefaultSettings.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDefaultSettings.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDefaultSettings.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testDefaultSettings.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testDefaultSettings.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testDefaultSettings.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testDefaultSettings.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testDefaultSettings.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testDefaultSettings.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDefaultSettings.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testDefaultSettings.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDefaultSettings.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDefaultSettings.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDefaultSettings.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDefaultSettings.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDefaultSettings.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDefaultSettings.getUpdatedby()).isEqualTo(DEFAULT_UPDATEDBY);
    }

    @Test
    void createDefaultSettingsWithExistingId() throws Exception {
        // Create the DefaultSettings with an existing ID
        defaultSettings.setId(1L);

        int databaseSizeBeforeCreate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDefaultSettingsAsStream() {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        List<DefaultSettings> defaultSettingsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DefaultSettings.class)
            .getResponseBody()
            .filter(defaultSettings::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(defaultSettingsList).isNotNull();
        assertThat(defaultSettingsList).hasSize(1);
        DefaultSettings testDefaultSettings = defaultSettingsList.get(0);
        assertThat(testDefaultSettings.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDefaultSettings.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDefaultSettings.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDefaultSettings.getThirdPartyDTransactionId()).isEqualTo(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getDefaultSettingCode()).isEqualTo(DEFAULT_DEFAULT_SETTING_CODE);
        assertThat(testDefaultSettings.getJsonSettings()).isEqualTo(DEFAULT_JSON_SETTINGS);
        assertThat(testDefaultSettings.getAppConfig()).isEqualTo(DEFAULT_APP_CONFIG);
        assertThat(testDefaultSettings.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testDefaultSettings.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testDefaultSettings.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDefaultSettings.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDefaultSettings.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDefaultSettings.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDefaultSettings.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDefaultSettings.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDefaultSettings.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDefaultSettings.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDefaultSettings.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDefaultSettings.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testDefaultSettings.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testDefaultSettings.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testDefaultSettings.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testDefaultSettings.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testDefaultSettings.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testDefaultSettings.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDefaultSettings.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testDefaultSettings.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDefaultSettings.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDefaultSettings.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDefaultSettings.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDefaultSettings.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDefaultSettings.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDefaultSettings.getUpdatedby()).isEqualTo(DEFAULT_UPDATEDBY);
    }

    @Test
    void getAllDefaultSettings() {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        // Get all the defaultSettingsList
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
            .value(hasItem(defaultSettings.getId().intValue()))
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
            .jsonPath("$.[*].thirdPartyDTransactionId")
            .value(hasItem(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID))
            .jsonPath("$.[*].defaultSettingCode")
            .value(hasItem(DEFAULT_DEFAULT_SETTING_CODE))
            .jsonPath("$.[*].jsonSettings")
            .value(hasItem(DEFAULT_JSON_SETTINGS.toString()))
            .jsonPath("$.[*].appConfig")
            .value(hasItem(DEFAULT_APP_CONFIG.toString()))
            .jsonPath("$.[*].appName")
            .value(hasItem(DEFAULT_APP_NAME))
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
            .jsonPath("$.[*].updatedby")
            .value(hasItem(DEFAULT_UPDATEDBY));
    }

    @Test
    void getDefaultSettings() {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        // Get the defaultSettings
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, defaultSettings.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(defaultSettings.getId().intValue()))
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
            .jsonPath("$.thirdPartyDTransactionId")
            .value(is(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID))
            .jsonPath("$.defaultSettingCode")
            .value(is(DEFAULT_DEFAULT_SETTING_CODE))
            .jsonPath("$.jsonSettings")
            .value(is(DEFAULT_JSON_SETTINGS.toString()))
            .jsonPath("$.appConfig")
            .value(is(DEFAULT_APP_CONFIG.toString()))
            .jsonPath("$.appName")
            .value(is(DEFAULT_APP_NAME))
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
            .jsonPath("$.updatedby")
            .value(is(DEFAULT_UPDATEDBY));
    }

    @Test
    void getNonExistingDefaultSettings() {
        // Get the defaultSettings
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDefaultSettings() throws Exception {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        defaultSettingsSearchRepository.save(defaultSettings).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());

        // Update the defaultSettings
        DefaultSettings updatedDefaultSettings = defaultSettingsRepository.findById(defaultSettings.getId()).block();
        updatedDefaultSettings
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .thirdPartyDTransactionId(UPDATED_THIRD_PARTY_D_TRANSACTION_ID)
            .defaultSettingCode(UPDATED_DEFAULT_SETTING_CODE)
            .jsonSettings(UPDATED_JSON_SETTINGS)
            .appConfig(UPDATED_APP_CONFIG)
            .appName(UPDATED_APP_NAME)
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
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDefaultSettings.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDefaultSettings))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        DefaultSettings testDefaultSettings = defaultSettingsList.get(defaultSettingsList.size() - 1);
        assertThat(testDefaultSettings.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDefaultSettings.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDefaultSettings.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDefaultSettings.getThirdPartyDTransactionId()).isEqualTo(UPDATED_THIRD_PARTY_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getDefaultSettingCode()).isEqualTo(UPDATED_DEFAULT_SETTING_CODE);
        assertThat(testDefaultSettings.getJsonSettings()).isEqualTo(UPDATED_JSON_SETTINGS);
        assertThat(testDefaultSettings.getAppConfig()).isEqualTo(UPDATED_APP_CONFIG);
        assertThat(testDefaultSettings.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testDefaultSettings.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testDefaultSettings.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDefaultSettings.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDefaultSettings.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDefaultSettings.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDefaultSettings.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDefaultSettings.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDefaultSettings.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDefaultSettings.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDefaultSettings.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testDefaultSettings.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testDefaultSettings.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDefaultSettings.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testDefaultSettings.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testDefaultSettings.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testDefaultSettings.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testDefaultSettings.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testDefaultSettings.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testDefaultSettings.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testDefaultSettings.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDefaultSettings.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDefaultSettings.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDefaultSettings.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDefaultSettings.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDefaultSettings.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DefaultSettings> defaultSettingsSearchList = IterableUtils.toList(
                    defaultSettingsSearchRepository.findAll().collectList().block()
                );
                DefaultSettings testDefaultSettingsSearch = defaultSettingsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDefaultSettingsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDefaultSettingsSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testDefaultSettingsSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testDefaultSettingsSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testDefaultSettingsSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testDefaultSettingsSearch.getThirdPartyDTransactionId()).isEqualTo(UPDATED_THIRD_PARTY_D_TRANSACTION_ID);
                assertThat(testDefaultSettingsSearch.getDefaultSettingCode()).isEqualTo(UPDATED_DEFAULT_SETTING_CODE);
                assertThat(testDefaultSettingsSearch.getJsonSettings()).isEqualTo(UPDATED_JSON_SETTINGS);
                assertThat(testDefaultSettingsSearch.getAppConfig()).isEqualTo(UPDATED_APP_CONFIG);
                assertThat(testDefaultSettingsSearch.getAppName()).isEqualTo(UPDATED_APP_NAME);
                assertThat(testDefaultSettingsSearch.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
                assertThat(testDefaultSettingsSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testDefaultSettingsSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testDefaultSettingsSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testDefaultSettingsSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testDefaultSettingsSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testDefaultSettingsSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testDefaultSettingsSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testDefaultSettingsSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testDefaultSettingsSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testDefaultSettingsSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testDefaultSettingsSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testDefaultSettingsSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testDefaultSettingsSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testDefaultSettingsSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testDefaultSettingsSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testDefaultSettingsSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testDefaultSettingsSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testDefaultSettingsSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testDefaultSettingsSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testDefaultSettingsSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testDefaultSettingsSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDefaultSettingsSearch.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
                assertThat(testDefaultSettingsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDefaultSettingsSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDefaultSettingsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDefaultSettingsSearch.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
            });
    }

    @Test
    void putNonExistingDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, defaultSettings.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDefaultSettingsWithPatch() throws Exception {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();

        // Update the defaultSettings using partial update
        DefaultSettings partialUpdatedDefaultSettings = new DefaultSettings();
        partialUpdatedDefaultSettings.setId(defaultSettings.getId());

        partialUpdatedDefaultSettings
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .token(UPDATED_TOKEN)
            .defaultSettingCode(UPDATED_DEFAULT_SETTING_CODE)
            .appConfig(UPDATED_APP_CONFIG)
            .freeField(UPDATED_FREE_FIELD)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .timestamp(UPDATED_TIMESTAMP)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDefaultSettings.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDefaultSettings))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        DefaultSettings testDefaultSettings = defaultSettingsList.get(defaultSettingsList.size() - 1);
        assertThat(testDefaultSettings.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDefaultSettings.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDefaultSettings.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDefaultSettings.getThirdPartyDTransactionId()).isEqualTo(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getDefaultSettingCode()).isEqualTo(UPDATED_DEFAULT_SETTING_CODE);
        assertThat(testDefaultSettings.getJsonSettings()).isEqualTo(DEFAULT_JSON_SETTINGS);
        assertThat(testDefaultSettings.getAppConfig()).isEqualTo(UPDATED_APP_CONFIG);
        assertThat(testDefaultSettings.getAppName()).isEqualTo(DEFAULT_APP_NAME);
        assertThat(testDefaultSettings.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testDefaultSettings.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDefaultSettings.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDefaultSettings.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDefaultSettings.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDefaultSettings.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDefaultSettings.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDefaultSettings.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDefaultSettings.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDefaultSettings.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDefaultSettings.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testDefaultSettings.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDefaultSettings.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testDefaultSettings.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testDefaultSettings.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testDefaultSettings.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testDefaultSettings.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDefaultSettings.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testDefaultSettings.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDefaultSettings.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDefaultSettings.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDefaultSettings.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDefaultSettings.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDefaultSettings.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDefaultSettings.getUpdatedby()).isEqualTo(DEFAULT_UPDATEDBY);
    }

    @Test
    void fullUpdateDefaultSettingsWithPatch() throws Exception {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();

        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();

        // Update the defaultSettings using partial update
        DefaultSettings partialUpdatedDefaultSettings = new DefaultSettings();
        partialUpdatedDefaultSettings.setId(defaultSettings.getId());

        partialUpdatedDefaultSettings
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .thirdPartyDTransactionId(UPDATED_THIRD_PARTY_D_TRANSACTION_ID)
            .defaultSettingCode(UPDATED_DEFAULT_SETTING_CODE)
            .jsonSettings(UPDATED_JSON_SETTINGS)
            .appConfig(UPDATED_APP_CONFIG)
            .appName(UPDATED_APP_NAME)
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
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedby(UPDATED_UPDATEDBY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDefaultSettings.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDefaultSettings))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        DefaultSettings testDefaultSettings = defaultSettingsList.get(defaultSettingsList.size() - 1);
        assertThat(testDefaultSettings.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDefaultSettings.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDefaultSettings.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDefaultSettings.getThirdPartyDTransactionId()).isEqualTo(UPDATED_THIRD_PARTY_D_TRANSACTION_ID);
        assertThat(testDefaultSettings.getDefaultSettingCode()).isEqualTo(UPDATED_DEFAULT_SETTING_CODE);
        assertThat(testDefaultSettings.getJsonSettings()).isEqualTo(UPDATED_JSON_SETTINGS);
        assertThat(testDefaultSettings.getAppConfig()).isEqualTo(UPDATED_APP_CONFIG);
        assertThat(testDefaultSettings.getAppName()).isEqualTo(UPDATED_APP_NAME);
        assertThat(testDefaultSettings.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testDefaultSettings.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDefaultSettings.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDefaultSettings.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDefaultSettings.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDefaultSettings.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDefaultSettings.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDefaultSettings.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDefaultSettings.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDefaultSettings.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testDefaultSettings.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testDefaultSettings.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDefaultSettings.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testDefaultSettings.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testDefaultSettings.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testDefaultSettings.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testDefaultSettings.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testDefaultSettings.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testDefaultSettings.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDefaultSettings.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testDefaultSettings.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDefaultSettings.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDefaultSettings.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDefaultSettings.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDefaultSettings.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDefaultSettings.getUpdatedby()).isEqualTo(UPDATED_UPDATEDBY);
    }

    @Test
    void patchNonExistingDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, defaultSettings.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDefaultSettings() throws Exception {
        int databaseSizeBeforeUpdate = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        defaultSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(defaultSettings))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DefaultSettings in the database
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDefaultSettings() {
        // Initialize the database
        defaultSettingsRepository.save(defaultSettings).block();
        defaultSettingsRepository.save(defaultSettings).block();
        defaultSettingsSearchRepository.save(defaultSettings).block();

        int databaseSizeBeforeDelete = defaultSettingsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the defaultSettings
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, defaultSettings.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DefaultSettings> defaultSettingsList = defaultSettingsRepository.findAll().collectList().block();
        assertThat(defaultSettingsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(defaultSettingsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDefaultSettings() {
        // Initialize the database
        defaultSettings = defaultSettingsRepository.save(defaultSettings).block();
        defaultSettingsSearchRepository.save(defaultSettings).block();

        // Search the defaultSettings
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + defaultSettings.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(defaultSettings.getId().intValue()))
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
            .jsonPath("$.[*].thirdPartyDTransactionId")
            .value(hasItem(DEFAULT_THIRD_PARTY_D_TRANSACTION_ID))
            .jsonPath("$.[*].defaultSettingCode")
            .value(hasItem(DEFAULT_DEFAULT_SETTING_CODE))
            .jsonPath("$.[*].jsonSettings")
            .value(hasItem(DEFAULT_JSON_SETTINGS.toString()))
            .jsonPath("$.[*].appConfig")
            .value(hasItem(DEFAULT_APP_CONFIG.toString()))
            .jsonPath("$.[*].appName")
            .value(hasItem(DEFAULT_APP_NAME))
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
            .jsonPath("$.[*].updatedby")
            .value(hasItem(DEFAULT_UPDATEDBY));
    }
}
