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
import ug.co.absa.microsrvc.gateway.domain.GenericConfigs;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.GenericConfigsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.GenericConfigsSearchRepository;

/**
 * Integration tests for the {@link GenericConfigsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class GenericConfigsResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGS_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGS_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_CONFIGS = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_CONFIGS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FREE_FIELD = false;
    private static final Boolean UPDATED_FREE_FIELD = true;

    private static final Boolean DEFAULT_FREE_FIELD_1 = false;
    private static final Boolean UPDATED_FREE_FIELD_1 = true;

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

    private static final String DEFAULT_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_23 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_23 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_24 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_24 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_25 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_25 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_25_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_25_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_26 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_26 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_27 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_27 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_28 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_28 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_28_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_28_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_29 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_29 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_30 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_30 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_31 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_31 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_32 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_32 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_33 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_33 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_34 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_34 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final RecordStatus DEFAULT_RECORD_STATUS = RecordStatus.ACTIVE;
    private static final RecordStatus UPDATED_RECORD_STATUS = RecordStatus.INACTIVE;

    private static final String ENTITY_API_URL = "/api/generic-configs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/generic-configs";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GenericConfigsRepository genericConfigsRepository;

    @Autowired
    private GenericConfigsSearchRepository genericConfigsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private GenericConfigs genericConfigs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GenericConfigs createEntity(EntityManager em) {
        GenericConfigs genericConfigs = new GenericConfigs()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .configId(DEFAULT_CONFIG_ID)
            .configName(DEFAULT_CONFIG_NAME)
            .configsDetails(DEFAULT_CONFIGS_DETAILS)
            .additionalConfigs(DEFAULT_ADDITIONAL_CONFIGS)
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
            .freeField20(DEFAULT_FREE_FIELD_20)
            .freeField21(DEFAULT_FREE_FIELD_21)
            .freeField22(DEFAULT_FREE_FIELD_22)
            .freeField23(DEFAULT_FREE_FIELD_23)
            .freeField24(DEFAULT_FREE_FIELD_24)
            .freeField25(DEFAULT_FREE_FIELD_25)
            .freeField25ContentType(DEFAULT_FREE_FIELD_25_CONTENT_TYPE)
            .freeField26(DEFAULT_FREE_FIELD_26)
            .freeField27(DEFAULT_FREE_FIELD_27)
            .freeField28(DEFAULT_FREE_FIELD_28)
            .freeField28ContentType(DEFAULT_FREE_FIELD_28_CONTENT_TYPE)
            .freeField29(DEFAULT_FREE_FIELD_29)
            .freeField30(DEFAULT_FREE_FIELD_30)
            .freeField31(DEFAULT_FREE_FIELD_31)
            .freeField32(DEFAULT_FREE_FIELD_32)
            .freeField33(DEFAULT_FREE_FIELD_33)
            .freeField34(DEFAULT_FREE_FIELD_34)
            .timestamp(DEFAULT_TIMESTAMP)
            .recordStatus(DEFAULT_RECORD_STATUS);
        return genericConfigs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GenericConfigs createUpdatedEntity(EntityManager em) {
        GenericConfigs genericConfigs = new GenericConfigs()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configsDetails(UPDATED_CONFIGS_DETAILS)
            .additionalConfigs(UPDATED_ADDITIONAL_CONFIGS)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .freeField21(UPDATED_FREE_FIELD_21)
            .freeField22(UPDATED_FREE_FIELD_22)
            .freeField23(UPDATED_FREE_FIELD_23)
            .freeField24(UPDATED_FREE_FIELD_24)
            .freeField25(UPDATED_FREE_FIELD_25)
            .freeField25ContentType(UPDATED_FREE_FIELD_25_CONTENT_TYPE)
            .freeField26(UPDATED_FREE_FIELD_26)
            .freeField27(UPDATED_FREE_FIELD_27)
            .freeField28(UPDATED_FREE_FIELD_28)
            .freeField28ContentType(UPDATED_FREE_FIELD_28_CONTENT_TYPE)
            .freeField29(UPDATED_FREE_FIELD_29)
            .freeField30(UPDATED_FREE_FIELD_30)
            .freeField31(UPDATED_FREE_FIELD_31)
            .freeField32(UPDATED_FREE_FIELD_32)
            .freeField33(UPDATED_FREE_FIELD_33)
            .freeField34(UPDATED_FREE_FIELD_34)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS);
        return genericConfigs;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(GenericConfigs.class).block();
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
        genericConfigsSearchRepository.deleteAll().block();
        assertThat(genericConfigsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        genericConfigs = createEntity(em);
    }

    @Test
    void createGenericConfigs() throws Exception {
        int databaseSizeBeforeCreate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        // Create the GenericConfigs
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        GenericConfigs testGenericConfigs = genericConfigsList.get(genericConfigsList.size() - 1);
        assertThat(testGenericConfigs.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testGenericConfigs.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testGenericConfigs.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testGenericConfigs.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testGenericConfigs.getConfigsDetails()).isEqualTo(DEFAULT_CONFIGS_DETAILS);
        assertThat(testGenericConfigs.getAdditionalConfigs()).isEqualTo(DEFAULT_ADDITIONAL_CONFIGS);
        assertThat(testGenericConfigs.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testGenericConfigs.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testGenericConfigs.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testGenericConfigs.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testGenericConfigs.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testGenericConfigs.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testGenericConfigs.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testGenericConfigs.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testGenericConfigs.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testGenericConfigs.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testGenericConfigs.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testGenericConfigs.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testGenericConfigs.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testGenericConfigs.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testGenericConfigs.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testGenericConfigs.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testGenericConfigs.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testGenericConfigs.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testGenericConfigs.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testGenericConfigs.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testGenericConfigs.getFreeField21()).isEqualTo(DEFAULT_FREE_FIELD_21);
        assertThat(testGenericConfigs.getFreeField22()).isEqualTo(DEFAULT_FREE_FIELD_22);
        assertThat(testGenericConfigs.getFreeField23()).isEqualTo(DEFAULT_FREE_FIELD_23);
        assertThat(testGenericConfigs.getFreeField24()).isEqualTo(DEFAULT_FREE_FIELD_24);
        assertThat(testGenericConfigs.getFreeField25()).isEqualTo(DEFAULT_FREE_FIELD_25);
        assertThat(testGenericConfigs.getFreeField25ContentType()).isEqualTo(DEFAULT_FREE_FIELD_25_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField26()).isEqualTo(DEFAULT_FREE_FIELD_26);
        assertThat(testGenericConfigs.getFreeField27()).isEqualTo(DEFAULT_FREE_FIELD_27);
        assertThat(testGenericConfigs.getFreeField28()).isEqualTo(DEFAULT_FREE_FIELD_28);
        assertThat(testGenericConfigs.getFreeField28ContentType()).isEqualTo(DEFAULT_FREE_FIELD_28_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField29()).isEqualTo(DEFAULT_FREE_FIELD_29);
        assertThat(testGenericConfigs.getFreeField30()).isEqualTo(DEFAULT_FREE_FIELD_30);
        assertThat(testGenericConfigs.getFreeField31()).isEqualTo(DEFAULT_FREE_FIELD_31);
        assertThat(testGenericConfigs.getFreeField32()).isEqualTo(DEFAULT_FREE_FIELD_32);
        assertThat(testGenericConfigs.getFreeField33()).isEqualTo(DEFAULT_FREE_FIELD_33);
        assertThat(testGenericConfigs.getFreeField34()).isEqualTo(DEFAULT_FREE_FIELD_34);
        assertThat(testGenericConfigs.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testGenericConfigs.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
    }

    @Test
    void createGenericConfigsWithExistingId() throws Exception {
        // Create the GenericConfigs with an existing ID
        genericConfigs.setId(1L);

        int databaseSizeBeforeCreate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        // set the field null
        genericConfigs.setTimestamp(null);

        // Create the GenericConfigs, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllGenericConfigsAsStream() {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        List<GenericConfigs> genericConfigsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(GenericConfigs.class)
            .getResponseBody()
            .filter(genericConfigs::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(genericConfigsList).isNotNull();
        assertThat(genericConfigsList).hasSize(1);
        GenericConfigs testGenericConfigs = genericConfigsList.get(0);
        assertThat(testGenericConfigs.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testGenericConfigs.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testGenericConfigs.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testGenericConfigs.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testGenericConfigs.getConfigsDetails()).isEqualTo(DEFAULT_CONFIGS_DETAILS);
        assertThat(testGenericConfigs.getAdditionalConfigs()).isEqualTo(DEFAULT_ADDITIONAL_CONFIGS);
        assertThat(testGenericConfigs.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testGenericConfigs.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testGenericConfigs.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testGenericConfigs.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testGenericConfigs.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testGenericConfigs.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testGenericConfigs.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testGenericConfigs.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testGenericConfigs.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testGenericConfigs.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testGenericConfigs.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testGenericConfigs.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testGenericConfigs.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testGenericConfigs.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testGenericConfigs.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testGenericConfigs.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testGenericConfigs.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testGenericConfigs.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testGenericConfigs.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testGenericConfigs.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testGenericConfigs.getFreeField21()).isEqualTo(DEFAULT_FREE_FIELD_21);
        assertThat(testGenericConfigs.getFreeField22()).isEqualTo(DEFAULT_FREE_FIELD_22);
        assertThat(testGenericConfigs.getFreeField23()).isEqualTo(DEFAULT_FREE_FIELD_23);
        assertThat(testGenericConfigs.getFreeField24()).isEqualTo(DEFAULT_FREE_FIELD_24);
        assertThat(testGenericConfigs.getFreeField25()).isEqualTo(DEFAULT_FREE_FIELD_25);
        assertThat(testGenericConfigs.getFreeField25ContentType()).isEqualTo(DEFAULT_FREE_FIELD_25_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField26()).isEqualTo(DEFAULT_FREE_FIELD_26);
        assertThat(testGenericConfigs.getFreeField27()).isEqualTo(DEFAULT_FREE_FIELD_27);
        assertThat(testGenericConfigs.getFreeField28()).isEqualTo(DEFAULT_FREE_FIELD_28);
        assertThat(testGenericConfigs.getFreeField28ContentType()).isEqualTo(DEFAULT_FREE_FIELD_28_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField29()).isEqualTo(DEFAULT_FREE_FIELD_29);
        assertThat(testGenericConfigs.getFreeField30()).isEqualTo(DEFAULT_FREE_FIELD_30);
        assertThat(testGenericConfigs.getFreeField31()).isEqualTo(DEFAULT_FREE_FIELD_31);
        assertThat(testGenericConfigs.getFreeField32()).isEqualTo(DEFAULT_FREE_FIELD_32);
        assertThat(testGenericConfigs.getFreeField33()).isEqualTo(DEFAULT_FREE_FIELD_33);
        assertThat(testGenericConfigs.getFreeField34()).isEqualTo(DEFAULT_FREE_FIELD_34);
        assertThat(testGenericConfigs.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testGenericConfigs.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
    }

    @Test
    void getAllGenericConfigs() {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        // Get all the genericConfigsList
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
            .value(hasItem(genericConfigs.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].configName")
            .value(hasItem(DEFAULT_CONFIG_NAME))
            .jsonPath("$.[*].configsDetails")
            .value(hasItem(DEFAULT_CONFIGS_DETAILS))
            .jsonPath("$.[*].additionalConfigs")
            .value(hasItem(DEFAULT_ADDITIONAL_CONFIGS))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD.booleanValue()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.booleanValue()))
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
            .jsonPath("$.[*].freeField20")
            .value(hasItem(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.[*].freeField21")
            .value(hasItem(DEFAULT_FREE_FIELD_21))
            .jsonPath("$.[*].freeField22")
            .value(hasItem(DEFAULT_FREE_FIELD_22))
            .jsonPath("$.[*].freeField23")
            .value(hasItem(DEFAULT_FREE_FIELD_23))
            .jsonPath("$.[*].freeField24")
            .value(hasItem(DEFAULT_FREE_FIELD_24))
            .jsonPath("$.[*].freeField25ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_25_CONTENT_TYPE))
            .jsonPath("$.[*].freeField25")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_25)))
            .jsonPath("$.[*].freeField26")
            .value(hasItem(DEFAULT_FREE_FIELD_26.toString()))
            .jsonPath("$.[*].freeField27")
            .value(hasItem(DEFAULT_FREE_FIELD_27.toString()))
            .jsonPath("$.[*].freeField28ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_28_CONTENT_TYPE))
            .jsonPath("$.[*].freeField28")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_28)))
            .jsonPath("$.[*].freeField29")
            .value(hasItem(DEFAULT_FREE_FIELD_29))
            .jsonPath("$.[*].freeField30")
            .value(hasItem(DEFAULT_FREE_FIELD_30))
            .jsonPath("$.[*].freeField31")
            .value(hasItem(DEFAULT_FREE_FIELD_31))
            .jsonPath("$.[*].freeField32")
            .value(hasItem(DEFAULT_FREE_FIELD_32))
            .jsonPath("$.[*].freeField33")
            .value(hasItem(DEFAULT_FREE_FIELD_33))
            .jsonPath("$.[*].freeField34")
            .value(hasItem(DEFAULT_FREE_FIELD_34))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()));
    }

    @Test
    void getGenericConfigs() {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        // Get the genericConfigs
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, genericConfigs.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(genericConfigs.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.configId")
            .value(is(DEFAULT_CONFIG_ID))
            .jsonPath("$.configName")
            .value(is(DEFAULT_CONFIG_NAME))
            .jsonPath("$.configsDetails")
            .value(is(DEFAULT_CONFIGS_DETAILS))
            .jsonPath("$.additionalConfigs")
            .value(is(DEFAULT_ADDITIONAL_CONFIGS))
            .jsonPath("$.freeField")
            .value(is(DEFAULT_FREE_FIELD.booleanValue()))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1.booleanValue()))
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
            .jsonPath("$.freeField20")
            .value(is(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.freeField21")
            .value(is(DEFAULT_FREE_FIELD_21))
            .jsonPath("$.freeField22")
            .value(is(DEFAULT_FREE_FIELD_22))
            .jsonPath("$.freeField23")
            .value(is(DEFAULT_FREE_FIELD_23))
            .jsonPath("$.freeField24")
            .value(is(DEFAULT_FREE_FIELD_24))
            .jsonPath("$.freeField25ContentType")
            .value(is(DEFAULT_FREE_FIELD_25_CONTENT_TYPE))
            .jsonPath("$.freeField25")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_25)))
            .jsonPath("$.freeField26")
            .value(is(DEFAULT_FREE_FIELD_26.toString()))
            .jsonPath("$.freeField27")
            .value(is(DEFAULT_FREE_FIELD_27.toString()))
            .jsonPath("$.freeField28ContentType")
            .value(is(DEFAULT_FREE_FIELD_28_CONTENT_TYPE))
            .jsonPath("$.freeField28")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_28)))
            .jsonPath("$.freeField29")
            .value(is(DEFAULT_FREE_FIELD_29))
            .jsonPath("$.freeField30")
            .value(is(DEFAULT_FREE_FIELD_30))
            .jsonPath("$.freeField31")
            .value(is(DEFAULT_FREE_FIELD_31))
            .jsonPath("$.freeField32")
            .value(is(DEFAULT_FREE_FIELD_32))
            .jsonPath("$.freeField33")
            .value(is(DEFAULT_FREE_FIELD_33))
            .jsonPath("$.freeField34")
            .value(is(DEFAULT_FREE_FIELD_34))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS.toString()));
    }

    @Test
    void getNonExistingGenericConfigs() {
        // Get the genericConfigs
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingGenericConfigs() throws Exception {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        genericConfigsSearchRepository.save(genericConfigs).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());

        // Update the genericConfigs
        GenericConfigs updatedGenericConfigs = genericConfigsRepository.findById(genericConfigs.getId()).block();
        updatedGenericConfigs
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configsDetails(UPDATED_CONFIGS_DETAILS)
            .additionalConfigs(UPDATED_ADDITIONAL_CONFIGS)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .freeField21(UPDATED_FREE_FIELD_21)
            .freeField22(UPDATED_FREE_FIELD_22)
            .freeField23(UPDATED_FREE_FIELD_23)
            .freeField24(UPDATED_FREE_FIELD_24)
            .freeField25(UPDATED_FREE_FIELD_25)
            .freeField25ContentType(UPDATED_FREE_FIELD_25_CONTENT_TYPE)
            .freeField26(UPDATED_FREE_FIELD_26)
            .freeField27(UPDATED_FREE_FIELD_27)
            .freeField28(UPDATED_FREE_FIELD_28)
            .freeField28ContentType(UPDATED_FREE_FIELD_28_CONTENT_TYPE)
            .freeField29(UPDATED_FREE_FIELD_29)
            .freeField30(UPDATED_FREE_FIELD_30)
            .freeField31(UPDATED_FREE_FIELD_31)
            .freeField32(UPDATED_FREE_FIELD_32)
            .freeField33(UPDATED_FREE_FIELD_33)
            .freeField34(UPDATED_FREE_FIELD_34)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedGenericConfigs.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedGenericConfigs))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        GenericConfigs testGenericConfigs = genericConfigsList.get(genericConfigsList.size() - 1);
        assertThat(testGenericConfigs.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testGenericConfigs.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testGenericConfigs.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testGenericConfigs.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
        assertThat(testGenericConfigs.getConfigsDetails()).isEqualTo(UPDATED_CONFIGS_DETAILS);
        assertThat(testGenericConfigs.getAdditionalConfigs()).isEqualTo(UPDATED_ADDITIONAL_CONFIGS);
        assertThat(testGenericConfigs.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testGenericConfigs.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testGenericConfigs.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testGenericConfigs.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testGenericConfigs.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testGenericConfigs.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testGenericConfigs.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testGenericConfigs.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testGenericConfigs.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testGenericConfigs.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testGenericConfigs.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testGenericConfigs.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testGenericConfigs.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testGenericConfigs.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testGenericConfigs.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testGenericConfigs.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testGenericConfigs.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testGenericConfigs.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testGenericConfigs.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testGenericConfigs.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testGenericConfigs.getFreeField21()).isEqualTo(UPDATED_FREE_FIELD_21);
        assertThat(testGenericConfigs.getFreeField22()).isEqualTo(UPDATED_FREE_FIELD_22);
        assertThat(testGenericConfigs.getFreeField23()).isEqualTo(UPDATED_FREE_FIELD_23);
        assertThat(testGenericConfigs.getFreeField24()).isEqualTo(UPDATED_FREE_FIELD_24);
        assertThat(testGenericConfigs.getFreeField25()).isEqualTo(UPDATED_FREE_FIELD_25);
        assertThat(testGenericConfigs.getFreeField25ContentType()).isEqualTo(UPDATED_FREE_FIELD_25_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField26()).isEqualTo(UPDATED_FREE_FIELD_26);
        assertThat(testGenericConfigs.getFreeField27()).isEqualTo(UPDATED_FREE_FIELD_27);
        assertThat(testGenericConfigs.getFreeField28()).isEqualTo(UPDATED_FREE_FIELD_28);
        assertThat(testGenericConfigs.getFreeField28ContentType()).isEqualTo(UPDATED_FREE_FIELD_28_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField29()).isEqualTo(UPDATED_FREE_FIELD_29);
        assertThat(testGenericConfigs.getFreeField30()).isEqualTo(UPDATED_FREE_FIELD_30);
        assertThat(testGenericConfigs.getFreeField31()).isEqualTo(UPDATED_FREE_FIELD_31);
        assertThat(testGenericConfigs.getFreeField32()).isEqualTo(UPDATED_FREE_FIELD_32);
        assertThat(testGenericConfigs.getFreeField33()).isEqualTo(UPDATED_FREE_FIELD_33);
        assertThat(testGenericConfigs.getFreeField34()).isEqualTo(UPDATED_FREE_FIELD_34);
        assertThat(testGenericConfigs.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testGenericConfigs.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<GenericConfigs> genericConfigsSearchList = IterableUtils.toList(
                    genericConfigsSearchRepository.findAll().collectList().block()
                );
                GenericConfigs testGenericConfigsSearch = genericConfigsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testGenericConfigsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testGenericConfigsSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testGenericConfigsSearch.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
                assertThat(testGenericConfigsSearch.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
                assertThat(testGenericConfigsSearch.getConfigsDetails()).isEqualTo(UPDATED_CONFIGS_DETAILS);
                assertThat(testGenericConfigsSearch.getAdditionalConfigs()).isEqualTo(UPDATED_ADDITIONAL_CONFIGS);
                assertThat(testGenericConfigsSearch.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
                assertThat(testGenericConfigsSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testGenericConfigsSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testGenericConfigsSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testGenericConfigsSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testGenericConfigsSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testGenericConfigsSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testGenericConfigsSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testGenericConfigsSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testGenericConfigsSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testGenericConfigsSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testGenericConfigsSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testGenericConfigsSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testGenericConfigsSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testGenericConfigsSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testGenericConfigsSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testGenericConfigsSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testGenericConfigsSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testGenericConfigsSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testGenericConfigsSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testGenericConfigsSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testGenericConfigsSearch.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
                assertThat(testGenericConfigsSearch.getFreeField21()).isEqualTo(UPDATED_FREE_FIELD_21);
                assertThat(testGenericConfigsSearch.getFreeField22()).isEqualTo(UPDATED_FREE_FIELD_22);
                assertThat(testGenericConfigsSearch.getFreeField23()).isEqualTo(UPDATED_FREE_FIELD_23);
                assertThat(testGenericConfigsSearch.getFreeField24()).isEqualTo(UPDATED_FREE_FIELD_24);
                assertThat(testGenericConfigsSearch.getFreeField25()).isEqualTo(UPDATED_FREE_FIELD_25);
                assertThat(testGenericConfigsSearch.getFreeField25ContentType()).isEqualTo(UPDATED_FREE_FIELD_25_CONTENT_TYPE);
                assertThat(testGenericConfigsSearch.getFreeField26()).isEqualTo(UPDATED_FREE_FIELD_26);
                assertThat(testGenericConfigsSearch.getFreeField27()).isEqualTo(UPDATED_FREE_FIELD_27);
                assertThat(testGenericConfigsSearch.getFreeField28()).isEqualTo(UPDATED_FREE_FIELD_28);
                assertThat(testGenericConfigsSearch.getFreeField28ContentType()).isEqualTo(UPDATED_FREE_FIELD_28_CONTENT_TYPE);
                assertThat(testGenericConfigsSearch.getFreeField29()).isEqualTo(UPDATED_FREE_FIELD_29);
                assertThat(testGenericConfigsSearch.getFreeField30()).isEqualTo(UPDATED_FREE_FIELD_30);
                assertThat(testGenericConfigsSearch.getFreeField31()).isEqualTo(UPDATED_FREE_FIELD_31);
                assertThat(testGenericConfigsSearch.getFreeField32()).isEqualTo(UPDATED_FREE_FIELD_32);
                assertThat(testGenericConfigsSearch.getFreeField33()).isEqualTo(UPDATED_FREE_FIELD_33);
                assertThat(testGenericConfigsSearch.getFreeField34()).isEqualTo(UPDATED_FREE_FIELD_34);
                assertThat(testGenericConfigsSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testGenericConfigsSearch.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
            });
    }

    @Test
    void putNonExistingGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, genericConfigs.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateGenericConfigsWithPatch() throws Exception {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();

        // Update the genericConfigs using partial update
        GenericConfigs partialUpdatedGenericConfigs = new GenericConfigs();
        partialUpdatedGenericConfigs.setId(genericConfigs.getId());

        partialUpdatedGenericConfigs
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .additionalConfigs(UPDATED_ADDITIONAL_CONFIGS)
            .freeField(UPDATED_FREE_FIELD)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
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
            .freeField21(UPDATED_FREE_FIELD_21)
            .freeField23(UPDATED_FREE_FIELD_23)
            .freeField25(UPDATED_FREE_FIELD_25)
            .freeField25ContentType(UPDATED_FREE_FIELD_25_CONTENT_TYPE)
            .freeField28(UPDATED_FREE_FIELD_28)
            .freeField28ContentType(UPDATED_FREE_FIELD_28_CONTENT_TYPE)
            .freeField30(UPDATED_FREE_FIELD_30)
            .freeField31(UPDATED_FREE_FIELD_31)
            .freeField33(UPDATED_FREE_FIELD_33)
            .freeField34(UPDATED_FREE_FIELD_34)
            .timestamp(UPDATED_TIMESTAMP);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedGenericConfigs.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedGenericConfigs))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        GenericConfigs testGenericConfigs = genericConfigsList.get(genericConfigsList.size() - 1);
        assertThat(testGenericConfigs.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testGenericConfigs.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testGenericConfigs.getConfigId()).isEqualTo(DEFAULT_CONFIG_ID);
        assertThat(testGenericConfigs.getConfigName()).isEqualTo(DEFAULT_CONFIG_NAME);
        assertThat(testGenericConfigs.getConfigsDetails()).isEqualTo(DEFAULT_CONFIGS_DETAILS);
        assertThat(testGenericConfigs.getAdditionalConfigs()).isEqualTo(UPDATED_ADDITIONAL_CONFIGS);
        assertThat(testGenericConfigs.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testGenericConfigs.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testGenericConfigs.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testGenericConfigs.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testGenericConfigs.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testGenericConfigs.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testGenericConfigs.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testGenericConfigs.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testGenericConfigs.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testGenericConfigs.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testGenericConfigs.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testGenericConfigs.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testGenericConfigs.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testGenericConfigs.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testGenericConfigs.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testGenericConfigs.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testGenericConfigs.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testGenericConfigs.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testGenericConfigs.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testGenericConfigs.getFreeField20()).isEqualTo(DEFAULT_FREE_FIELD_20);
        assertThat(testGenericConfigs.getFreeField21()).isEqualTo(UPDATED_FREE_FIELD_21);
        assertThat(testGenericConfigs.getFreeField22()).isEqualTo(DEFAULT_FREE_FIELD_22);
        assertThat(testGenericConfigs.getFreeField23()).isEqualTo(UPDATED_FREE_FIELD_23);
        assertThat(testGenericConfigs.getFreeField24()).isEqualTo(DEFAULT_FREE_FIELD_24);
        assertThat(testGenericConfigs.getFreeField25()).isEqualTo(UPDATED_FREE_FIELD_25);
        assertThat(testGenericConfigs.getFreeField25ContentType()).isEqualTo(UPDATED_FREE_FIELD_25_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField26()).isEqualTo(DEFAULT_FREE_FIELD_26);
        assertThat(testGenericConfigs.getFreeField27()).isEqualTo(DEFAULT_FREE_FIELD_27);
        assertThat(testGenericConfigs.getFreeField28()).isEqualTo(UPDATED_FREE_FIELD_28);
        assertThat(testGenericConfigs.getFreeField28ContentType()).isEqualTo(UPDATED_FREE_FIELD_28_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField29()).isEqualTo(DEFAULT_FREE_FIELD_29);
        assertThat(testGenericConfigs.getFreeField30()).isEqualTo(UPDATED_FREE_FIELD_30);
        assertThat(testGenericConfigs.getFreeField31()).isEqualTo(UPDATED_FREE_FIELD_31);
        assertThat(testGenericConfigs.getFreeField32()).isEqualTo(DEFAULT_FREE_FIELD_32);
        assertThat(testGenericConfigs.getFreeField33()).isEqualTo(UPDATED_FREE_FIELD_33);
        assertThat(testGenericConfigs.getFreeField34()).isEqualTo(UPDATED_FREE_FIELD_34);
        assertThat(testGenericConfigs.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testGenericConfigs.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
    }

    @Test
    void fullUpdateGenericConfigsWithPatch() throws Exception {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();

        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();

        // Update the genericConfigs using partial update
        GenericConfigs partialUpdatedGenericConfigs = new GenericConfigs();
        partialUpdatedGenericConfigs.setId(genericConfigs.getId());

        partialUpdatedGenericConfigs
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .configId(UPDATED_CONFIG_ID)
            .configName(UPDATED_CONFIG_NAME)
            .configsDetails(UPDATED_CONFIGS_DETAILS)
            .additionalConfigs(UPDATED_ADDITIONAL_CONFIGS)
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
            .freeField20(UPDATED_FREE_FIELD_20)
            .freeField21(UPDATED_FREE_FIELD_21)
            .freeField22(UPDATED_FREE_FIELD_22)
            .freeField23(UPDATED_FREE_FIELD_23)
            .freeField24(UPDATED_FREE_FIELD_24)
            .freeField25(UPDATED_FREE_FIELD_25)
            .freeField25ContentType(UPDATED_FREE_FIELD_25_CONTENT_TYPE)
            .freeField26(UPDATED_FREE_FIELD_26)
            .freeField27(UPDATED_FREE_FIELD_27)
            .freeField28(UPDATED_FREE_FIELD_28)
            .freeField28ContentType(UPDATED_FREE_FIELD_28_CONTENT_TYPE)
            .freeField29(UPDATED_FREE_FIELD_29)
            .freeField30(UPDATED_FREE_FIELD_30)
            .freeField31(UPDATED_FREE_FIELD_31)
            .freeField32(UPDATED_FREE_FIELD_32)
            .freeField33(UPDATED_FREE_FIELD_33)
            .freeField34(UPDATED_FREE_FIELD_34)
            .timestamp(UPDATED_TIMESTAMP)
            .recordStatus(UPDATED_RECORD_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedGenericConfigs.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedGenericConfigs))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        GenericConfigs testGenericConfigs = genericConfigsList.get(genericConfigsList.size() - 1);
        assertThat(testGenericConfigs.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testGenericConfigs.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testGenericConfigs.getConfigId()).isEqualTo(UPDATED_CONFIG_ID);
        assertThat(testGenericConfigs.getConfigName()).isEqualTo(UPDATED_CONFIG_NAME);
        assertThat(testGenericConfigs.getConfigsDetails()).isEqualTo(UPDATED_CONFIGS_DETAILS);
        assertThat(testGenericConfigs.getAdditionalConfigs()).isEqualTo(UPDATED_ADDITIONAL_CONFIGS);
        assertThat(testGenericConfigs.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testGenericConfigs.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testGenericConfigs.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testGenericConfigs.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testGenericConfigs.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testGenericConfigs.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testGenericConfigs.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testGenericConfigs.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testGenericConfigs.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testGenericConfigs.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testGenericConfigs.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testGenericConfigs.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testGenericConfigs.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testGenericConfigs.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testGenericConfigs.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testGenericConfigs.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testGenericConfigs.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testGenericConfigs.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testGenericConfigs.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testGenericConfigs.getFreeField20()).isEqualTo(UPDATED_FREE_FIELD_20);
        assertThat(testGenericConfigs.getFreeField21()).isEqualTo(UPDATED_FREE_FIELD_21);
        assertThat(testGenericConfigs.getFreeField22()).isEqualTo(UPDATED_FREE_FIELD_22);
        assertThat(testGenericConfigs.getFreeField23()).isEqualTo(UPDATED_FREE_FIELD_23);
        assertThat(testGenericConfigs.getFreeField24()).isEqualTo(UPDATED_FREE_FIELD_24);
        assertThat(testGenericConfigs.getFreeField25()).isEqualTo(UPDATED_FREE_FIELD_25);
        assertThat(testGenericConfigs.getFreeField25ContentType()).isEqualTo(UPDATED_FREE_FIELD_25_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField26()).isEqualTo(UPDATED_FREE_FIELD_26);
        assertThat(testGenericConfigs.getFreeField27()).isEqualTo(UPDATED_FREE_FIELD_27);
        assertThat(testGenericConfigs.getFreeField28()).isEqualTo(UPDATED_FREE_FIELD_28);
        assertThat(testGenericConfigs.getFreeField28ContentType()).isEqualTo(UPDATED_FREE_FIELD_28_CONTENT_TYPE);
        assertThat(testGenericConfigs.getFreeField29()).isEqualTo(UPDATED_FREE_FIELD_29);
        assertThat(testGenericConfigs.getFreeField30()).isEqualTo(UPDATED_FREE_FIELD_30);
        assertThat(testGenericConfigs.getFreeField31()).isEqualTo(UPDATED_FREE_FIELD_31);
        assertThat(testGenericConfigs.getFreeField32()).isEqualTo(UPDATED_FREE_FIELD_32);
        assertThat(testGenericConfigs.getFreeField33()).isEqualTo(UPDATED_FREE_FIELD_33);
        assertThat(testGenericConfigs.getFreeField34()).isEqualTo(UPDATED_FREE_FIELD_34);
        assertThat(testGenericConfigs.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testGenericConfigs.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
    }

    @Test
    void patchNonExistingGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, genericConfigs.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamGenericConfigs() throws Exception {
        int databaseSizeBeforeUpdate = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        genericConfigs.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(genericConfigs))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the GenericConfigs in the database
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteGenericConfigs() {
        // Initialize the database
        genericConfigsRepository.save(genericConfigs).block();
        genericConfigsRepository.save(genericConfigs).block();
        genericConfigsSearchRepository.save(genericConfigs).block();

        int databaseSizeBeforeDelete = genericConfigsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the genericConfigs
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, genericConfigs.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<GenericConfigs> genericConfigsList = genericConfigsRepository.findAll().collectList().block();
        assertThat(genericConfigsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(genericConfigsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchGenericConfigs() {
        // Initialize the database
        genericConfigs = genericConfigsRepository.save(genericConfigs).block();
        genericConfigsSearchRepository.save(genericConfigs).block();

        // Search the genericConfigs
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + genericConfigs.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(genericConfigs.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].configId")
            .value(hasItem(DEFAULT_CONFIG_ID))
            .jsonPath("$.[*].configName")
            .value(hasItem(DEFAULT_CONFIG_NAME))
            .jsonPath("$.[*].configsDetails")
            .value(hasItem(DEFAULT_CONFIGS_DETAILS))
            .jsonPath("$.[*].additionalConfigs")
            .value(hasItem(DEFAULT_ADDITIONAL_CONFIGS))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD.booleanValue()))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1.booleanValue()))
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
            .jsonPath("$.[*].freeField20")
            .value(hasItem(DEFAULT_FREE_FIELD_20))
            .jsonPath("$.[*].freeField21")
            .value(hasItem(DEFAULT_FREE_FIELD_21))
            .jsonPath("$.[*].freeField22")
            .value(hasItem(DEFAULT_FREE_FIELD_22))
            .jsonPath("$.[*].freeField23")
            .value(hasItem(DEFAULT_FREE_FIELD_23))
            .jsonPath("$.[*].freeField24")
            .value(hasItem(DEFAULT_FREE_FIELD_24))
            .jsonPath("$.[*].freeField25ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_25_CONTENT_TYPE))
            .jsonPath("$.[*].freeField25")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_25)))
            .jsonPath("$.[*].freeField26")
            .value(hasItem(DEFAULT_FREE_FIELD_26.toString()))
            .jsonPath("$.[*].freeField27")
            .value(hasItem(DEFAULT_FREE_FIELD_27.toString()))
            .jsonPath("$.[*].freeField28ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_28_CONTENT_TYPE))
            .jsonPath("$.[*].freeField28")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_28)))
            .jsonPath("$.[*].freeField29")
            .value(hasItem(DEFAULT_FREE_FIELD_29))
            .jsonPath("$.[*].freeField30")
            .value(hasItem(DEFAULT_FREE_FIELD_30))
            .jsonPath("$.[*].freeField31")
            .value(hasItem(DEFAULT_FREE_FIELD_31))
            .jsonPath("$.[*].freeField32")
            .value(hasItem(DEFAULT_FREE_FIELD_32))
            .jsonPath("$.[*].freeField33")
            .value(hasItem(DEFAULT_FREE_FIELD_33))
            .jsonPath("$.[*].freeField34")
            .value(hasItem(DEFAULT_FREE_FIELD_34))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()));
    }
}
