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
import ug.co.absa.microsrvc.gateway.domain.CustomAudit;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.CustomAuditSearchRepository;

/**
 * Integration tests for the {@link CustomAuditResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CustomAuditResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final ServiceLevel DEFAULT_ACTION_ID = ServiceLevel.N;
    private static final ServiceLevel UPDATED_ACTION_ID = ServiceLevel.Y;

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_OLD_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_OLD_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_NEW_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_NEW_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGE_RESAON = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE_RESAON = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_1 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_2 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_3 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_3 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_4 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_4 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_5 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_5 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_6 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_6 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_7 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_7 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_8 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_8 = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION_9 = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION_9 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_5 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_5 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_6 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_6 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_7 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_7 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_8 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_8 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_9 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_9 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_10 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_10 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_11 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_11 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_12 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_12 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_13 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_13 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_14 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_14 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_15 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_15 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_16 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_16 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_17 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_17 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_18 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_18 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_19 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_19 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_20 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_20 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_21 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_21 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_22 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_22 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_23 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_23 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_24 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_24 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_25 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_25 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_26 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_26 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_27 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_27 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_TEXT_28 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT_28 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/custom-audits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/custom-audits";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CustomAuditRepository customAuditRepository;

    @Autowired
    private CustomAuditSearchRepository customAuditSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CustomAudit customAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomAudit createEntity(EntityManager em) {
        CustomAudit customAudit = new CustomAudit()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .actionId(DEFAULT_ACTION_ID)
            .timestamp(DEFAULT_TIMESTAMP)
            .oldValue(DEFAULT_OLD_VALUE)
            .newValue(DEFAULT_NEW_VALUE)
            .changeResaon(DEFAULT_CHANGE_RESAON)
            .description(DEFAULT_DESCRIPTION)
            .description1(DEFAULT_DESCRIPTION_1)
            .description2(DEFAULT_DESCRIPTION_2)
            .description3(DEFAULT_DESCRIPTION_3)
            .description4(DEFAULT_DESCRIPTION_4)
            .description5(DEFAULT_DESCRIPTION_5)
            .description6(DEFAULT_DESCRIPTION_6)
            .description7(DEFAULT_DESCRIPTION_7)
            .description8(DEFAULT_DESCRIPTION_8)
            .description9(DEFAULT_DESCRIPTION_9)
            .freeText1(DEFAULT_FREE_TEXT_1)
            .freeText2(DEFAULT_FREE_TEXT_2)
            .freeText3(DEFAULT_FREE_TEXT_3)
            .freeText4(DEFAULT_FREE_TEXT_4)
            .freeText5(DEFAULT_FREE_TEXT_5)
            .freeText6(DEFAULT_FREE_TEXT_6)
            .freeText7(DEFAULT_FREE_TEXT_7)
            .freeText8(DEFAULT_FREE_TEXT_8)
            .freeText9(DEFAULT_FREE_TEXT_9)
            .freeText10(DEFAULT_FREE_TEXT_10)
            .freeText11(DEFAULT_FREE_TEXT_11)
            .freeText12(DEFAULT_FREE_TEXT_12)
            .freeText13(DEFAULT_FREE_TEXT_13)
            .freeText14(DEFAULT_FREE_TEXT_14)
            .freeText15(DEFAULT_FREE_TEXT_15)
            .freeText16(DEFAULT_FREE_TEXT_16)
            .freeText17(DEFAULT_FREE_TEXT_17)
            .freeText18(DEFAULT_FREE_TEXT_18)
            .freeText19(DEFAULT_FREE_TEXT_19)
            .freeText20(DEFAULT_FREE_TEXT_20)
            .freeText21(DEFAULT_FREE_TEXT_21)
            .freeText22(DEFAULT_FREE_TEXT_22)
            .freeText23(DEFAULT_FREE_TEXT_23)
            .freeText24(DEFAULT_FREE_TEXT_24)
            .freeText25(DEFAULT_FREE_TEXT_25)
            .freeText26(DEFAULT_FREE_TEXT_26)
            .freeText27(DEFAULT_FREE_TEXT_27)
            .freeText28(DEFAULT_FREE_TEXT_28)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return customAudit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomAudit createUpdatedEntity(EntityManager em) {
        CustomAudit customAudit = new CustomAudit()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeResaon(UPDATED_CHANGE_RESAON)
            .description(UPDATED_DESCRIPTION)
            .description1(UPDATED_DESCRIPTION_1)
            .description2(UPDATED_DESCRIPTION_2)
            .description3(UPDATED_DESCRIPTION_3)
            .description4(UPDATED_DESCRIPTION_4)
            .description5(UPDATED_DESCRIPTION_5)
            .description6(UPDATED_DESCRIPTION_6)
            .description7(UPDATED_DESCRIPTION_7)
            .description8(UPDATED_DESCRIPTION_8)
            .description9(UPDATED_DESCRIPTION_9)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText2(UPDATED_FREE_TEXT_2)
            .freeText3(UPDATED_FREE_TEXT_3)
            .freeText4(UPDATED_FREE_TEXT_4)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText6(UPDATED_FREE_TEXT_6)
            .freeText7(UPDATED_FREE_TEXT_7)
            .freeText8(UPDATED_FREE_TEXT_8)
            .freeText9(UPDATED_FREE_TEXT_9)
            .freeText10(UPDATED_FREE_TEXT_10)
            .freeText11(UPDATED_FREE_TEXT_11)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText13(UPDATED_FREE_TEXT_13)
            .freeText14(UPDATED_FREE_TEXT_14)
            .freeText15(UPDATED_FREE_TEXT_15)
            .freeText16(UPDATED_FREE_TEXT_16)
            .freeText17(UPDATED_FREE_TEXT_17)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText19(UPDATED_FREE_TEXT_19)
            .freeText20(UPDATED_FREE_TEXT_20)
            .freeText21(UPDATED_FREE_TEXT_21)
            .freeText22(UPDATED_FREE_TEXT_22)
            .freeText23(UPDATED_FREE_TEXT_23)
            .freeText24(UPDATED_FREE_TEXT_24)
            .freeText25(UPDATED_FREE_TEXT_25)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText27(UPDATED_FREE_TEXT_27)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return customAudit;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CustomAudit.class).block();
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
        customAuditSearchRepository.deleteAll().block();
        assertThat(customAuditSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        customAudit = createEntity(em);
    }

    @Test
    void createCustomAudit() throws Exception {
        int databaseSizeBeforeCreate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        // Create the CustomAudit
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        CustomAudit testCustomAudit = customAuditList.get(customAuditList.size() - 1);
        assertThat(testCustomAudit.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCustomAudit.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testCustomAudit.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testCustomAudit.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCustomAudit.getOldValue()).isEqualTo(DEFAULT_OLD_VALUE);
        assertThat(testCustomAudit.getNewValue()).isEqualTo(DEFAULT_NEW_VALUE);
        assertThat(testCustomAudit.getChangeResaon()).isEqualTo(DEFAULT_CHANGE_RESAON);
        assertThat(testCustomAudit.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomAudit.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAudit.getDescription2()).isEqualTo(DEFAULT_DESCRIPTION_2);
        assertThat(testCustomAudit.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAudit.getDescription4()).isEqualTo(DEFAULT_DESCRIPTION_4);
        assertThat(testCustomAudit.getDescription5()).isEqualTo(DEFAULT_DESCRIPTION_5);
        assertThat(testCustomAudit.getDescription6()).isEqualTo(DEFAULT_DESCRIPTION_6);
        assertThat(testCustomAudit.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAudit.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAudit.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAudit.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testCustomAudit.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAudit.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAudit.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAudit.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testCustomAudit.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testCustomAudit.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testCustomAudit.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testCustomAudit.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAudit.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAudit.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testCustomAudit.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testCustomAudit.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testCustomAudit.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAudit.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testCustomAudit.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAudit.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAudit.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testCustomAudit.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testCustomAudit.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testCustomAudit.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testCustomAudit.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testCustomAudit.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAudit.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testCustomAudit.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testCustomAudit.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testCustomAudit.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAudit.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testCustomAudit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomAudit.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomAudit.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomAudit.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createCustomAuditWithExistingId() throws Exception {
        // Create the CustomAudit with an existing ID
        customAudit.setId(1L);

        int databaseSizeBeforeCreate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkActionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        // set the field null
        customAudit.setActionId(null);

        // Create the CustomAudit, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        // set the field null
        customAudit.setCreatedAt(null);

        // Create the CustomAudit, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllCustomAuditsAsStream() {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        List<CustomAudit> customAuditList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CustomAudit.class)
            .getResponseBody()
            .filter(customAudit::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(customAuditList).isNotNull();
        assertThat(customAuditList).hasSize(1);
        CustomAudit testCustomAudit = customAuditList.get(0);
        assertThat(testCustomAudit.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCustomAudit.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testCustomAudit.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testCustomAudit.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCustomAudit.getOldValue()).isEqualTo(DEFAULT_OLD_VALUE);
        assertThat(testCustomAudit.getNewValue()).isEqualTo(DEFAULT_NEW_VALUE);
        assertThat(testCustomAudit.getChangeResaon()).isEqualTo(DEFAULT_CHANGE_RESAON);
        assertThat(testCustomAudit.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomAudit.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAudit.getDescription2()).isEqualTo(DEFAULT_DESCRIPTION_2);
        assertThat(testCustomAudit.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAudit.getDescription4()).isEqualTo(DEFAULT_DESCRIPTION_4);
        assertThat(testCustomAudit.getDescription5()).isEqualTo(DEFAULT_DESCRIPTION_5);
        assertThat(testCustomAudit.getDescription6()).isEqualTo(DEFAULT_DESCRIPTION_6);
        assertThat(testCustomAudit.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAudit.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAudit.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAudit.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testCustomAudit.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAudit.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAudit.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAudit.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testCustomAudit.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testCustomAudit.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testCustomAudit.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testCustomAudit.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAudit.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAudit.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testCustomAudit.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testCustomAudit.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testCustomAudit.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAudit.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testCustomAudit.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAudit.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAudit.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testCustomAudit.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testCustomAudit.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testCustomAudit.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testCustomAudit.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testCustomAudit.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAudit.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testCustomAudit.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testCustomAudit.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testCustomAudit.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAudit.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testCustomAudit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomAudit.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomAudit.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomAudit.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllCustomAudits() {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        // Get all the customAuditList
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
            .value(hasItem(customAudit.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].actionId")
            .value(hasItem(DEFAULT_ACTION_ID.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].oldValue")
            .value(hasItem(DEFAULT_OLD_VALUE))
            .jsonPath("$.[*].newValue")
            .value(hasItem(DEFAULT_NEW_VALUE))
            .jsonPath("$.[*].changeResaon")
            .value(hasItem(DEFAULT_CHANGE_RESAON))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].description1")
            .value(hasItem(DEFAULT_DESCRIPTION_1))
            .jsonPath("$.[*].description2")
            .value(hasItem(DEFAULT_DESCRIPTION_2))
            .jsonPath("$.[*].description3")
            .value(hasItem(DEFAULT_DESCRIPTION_3))
            .jsonPath("$.[*].description4")
            .value(hasItem(DEFAULT_DESCRIPTION_4))
            .jsonPath("$.[*].description5")
            .value(hasItem(DEFAULT_DESCRIPTION_5))
            .jsonPath("$.[*].description6")
            .value(hasItem(DEFAULT_DESCRIPTION_6))
            .jsonPath("$.[*].description7")
            .value(hasItem(DEFAULT_DESCRIPTION_7))
            .jsonPath("$.[*].description8")
            .value(hasItem(DEFAULT_DESCRIPTION_8))
            .jsonPath("$.[*].description9")
            .value(hasItem(DEFAULT_DESCRIPTION_9))
            .jsonPath("$.[*].freeText1")
            .value(hasItem(DEFAULT_FREE_TEXT_1))
            .jsonPath("$.[*].freeText2")
            .value(hasItem(DEFAULT_FREE_TEXT_2))
            .jsonPath("$.[*].freeText3")
            .value(hasItem(DEFAULT_FREE_TEXT_3))
            .jsonPath("$.[*].freeText4")
            .value(hasItem(DEFAULT_FREE_TEXT_4))
            .jsonPath("$.[*].freeText5")
            .value(hasItem(DEFAULT_FREE_TEXT_5))
            .jsonPath("$.[*].freeText6")
            .value(hasItem(DEFAULT_FREE_TEXT_6))
            .jsonPath("$.[*].freeText7")
            .value(hasItem(DEFAULT_FREE_TEXT_7))
            .jsonPath("$.[*].freeText8")
            .value(hasItem(DEFAULT_FREE_TEXT_8))
            .jsonPath("$.[*].freeText9")
            .value(hasItem(DEFAULT_FREE_TEXT_9))
            .jsonPath("$.[*].freeText10")
            .value(hasItem(DEFAULT_FREE_TEXT_10))
            .jsonPath("$.[*].freeText11")
            .value(hasItem(DEFAULT_FREE_TEXT_11))
            .jsonPath("$.[*].freeText12")
            .value(hasItem(DEFAULT_FREE_TEXT_12))
            .jsonPath("$.[*].freeText13")
            .value(hasItem(DEFAULT_FREE_TEXT_13))
            .jsonPath("$.[*].freeText14")
            .value(hasItem(DEFAULT_FREE_TEXT_14))
            .jsonPath("$.[*].freeText15")
            .value(hasItem(DEFAULT_FREE_TEXT_15))
            .jsonPath("$.[*].freeText16")
            .value(hasItem(DEFAULT_FREE_TEXT_16))
            .jsonPath("$.[*].freeText17")
            .value(hasItem(DEFAULT_FREE_TEXT_17))
            .jsonPath("$.[*].freeText18")
            .value(hasItem(DEFAULT_FREE_TEXT_18))
            .jsonPath("$.[*].freeText19")
            .value(hasItem(DEFAULT_FREE_TEXT_19))
            .jsonPath("$.[*].freeText20")
            .value(hasItem(DEFAULT_FREE_TEXT_20))
            .jsonPath("$.[*].freeText21")
            .value(hasItem(DEFAULT_FREE_TEXT_21))
            .jsonPath("$.[*].freeText22")
            .value(hasItem(DEFAULT_FREE_TEXT_22))
            .jsonPath("$.[*].freeText23")
            .value(hasItem(DEFAULT_FREE_TEXT_23))
            .jsonPath("$.[*].freeText24")
            .value(hasItem(DEFAULT_FREE_TEXT_24))
            .jsonPath("$.[*].freeText25")
            .value(hasItem(DEFAULT_FREE_TEXT_25))
            .jsonPath("$.[*].freeText26")
            .value(hasItem(DEFAULT_FREE_TEXT_26))
            .jsonPath("$.[*].freeText27")
            .value(hasItem(DEFAULT_FREE_TEXT_27))
            .jsonPath("$.[*].freeText28")
            .value(hasItem(DEFAULT_FREE_TEXT_28))
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
    void getCustomAudit() {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        // Get the customAudit
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, customAudit.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(customAudit.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.actionId")
            .value(is(DEFAULT_ACTION_ID.toString()))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.oldValue")
            .value(is(DEFAULT_OLD_VALUE))
            .jsonPath("$.newValue")
            .value(is(DEFAULT_NEW_VALUE))
            .jsonPath("$.changeResaon")
            .value(is(DEFAULT_CHANGE_RESAON))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.description1")
            .value(is(DEFAULT_DESCRIPTION_1))
            .jsonPath("$.description2")
            .value(is(DEFAULT_DESCRIPTION_2))
            .jsonPath("$.description3")
            .value(is(DEFAULT_DESCRIPTION_3))
            .jsonPath("$.description4")
            .value(is(DEFAULT_DESCRIPTION_4))
            .jsonPath("$.description5")
            .value(is(DEFAULT_DESCRIPTION_5))
            .jsonPath("$.description6")
            .value(is(DEFAULT_DESCRIPTION_6))
            .jsonPath("$.description7")
            .value(is(DEFAULT_DESCRIPTION_7))
            .jsonPath("$.description8")
            .value(is(DEFAULT_DESCRIPTION_8))
            .jsonPath("$.description9")
            .value(is(DEFAULT_DESCRIPTION_9))
            .jsonPath("$.freeText1")
            .value(is(DEFAULT_FREE_TEXT_1))
            .jsonPath("$.freeText2")
            .value(is(DEFAULT_FREE_TEXT_2))
            .jsonPath("$.freeText3")
            .value(is(DEFAULT_FREE_TEXT_3))
            .jsonPath("$.freeText4")
            .value(is(DEFAULT_FREE_TEXT_4))
            .jsonPath("$.freeText5")
            .value(is(DEFAULT_FREE_TEXT_5))
            .jsonPath("$.freeText6")
            .value(is(DEFAULT_FREE_TEXT_6))
            .jsonPath("$.freeText7")
            .value(is(DEFAULT_FREE_TEXT_7))
            .jsonPath("$.freeText8")
            .value(is(DEFAULT_FREE_TEXT_8))
            .jsonPath("$.freeText9")
            .value(is(DEFAULT_FREE_TEXT_9))
            .jsonPath("$.freeText10")
            .value(is(DEFAULT_FREE_TEXT_10))
            .jsonPath("$.freeText11")
            .value(is(DEFAULT_FREE_TEXT_11))
            .jsonPath("$.freeText12")
            .value(is(DEFAULT_FREE_TEXT_12))
            .jsonPath("$.freeText13")
            .value(is(DEFAULT_FREE_TEXT_13))
            .jsonPath("$.freeText14")
            .value(is(DEFAULT_FREE_TEXT_14))
            .jsonPath("$.freeText15")
            .value(is(DEFAULT_FREE_TEXT_15))
            .jsonPath("$.freeText16")
            .value(is(DEFAULT_FREE_TEXT_16))
            .jsonPath("$.freeText17")
            .value(is(DEFAULT_FREE_TEXT_17))
            .jsonPath("$.freeText18")
            .value(is(DEFAULT_FREE_TEXT_18))
            .jsonPath("$.freeText19")
            .value(is(DEFAULT_FREE_TEXT_19))
            .jsonPath("$.freeText20")
            .value(is(DEFAULT_FREE_TEXT_20))
            .jsonPath("$.freeText21")
            .value(is(DEFAULT_FREE_TEXT_21))
            .jsonPath("$.freeText22")
            .value(is(DEFAULT_FREE_TEXT_22))
            .jsonPath("$.freeText23")
            .value(is(DEFAULT_FREE_TEXT_23))
            .jsonPath("$.freeText24")
            .value(is(DEFAULT_FREE_TEXT_24))
            .jsonPath("$.freeText25")
            .value(is(DEFAULT_FREE_TEXT_25))
            .jsonPath("$.freeText26")
            .value(is(DEFAULT_FREE_TEXT_26))
            .jsonPath("$.freeText27")
            .value(is(DEFAULT_FREE_TEXT_27))
            .jsonPath("$.freeText28")
            .value(is(DEFAULT_FREE_TEXT_28))
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
    void getNonExistingCustomAudit() {
        // Get the customAudit
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCustomAudit() throws Exception {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        customAuditSearchRepository.save(customAudit).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());

        // Update the customAudit
        CustomAudit updatedCustomAudit = customAuditRepository.findById(customAudit.getId()).block();
        updatedCustomAudit
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeResaon(UPDATED_CHANGE_RESAON)
            .description(UPDATED_DESCRIPTION)
            .description1(UPDATED_DESCRIPTION_1)
            .description2(UPDATED_DESCRIPTION_2)
            .description3(UPDATED_DESCRIPTION_3)
            .description4(UPDATED_DESCRIPTION_4)
            .description5(UPDATED_DESCRIPTION_5)
            .description6(UPDATED_DESCRIPTION_6)
            .description7(UPDATED_DESCRIPTION_7)
            .description8(UPDATED_DESCRIPTION_8)
            .description9(UPDATED_DESCRIPTION_9)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText2(UPDATED_FREE_TEXT_2)
            .freeText3(UPDATED_FREE_TEXT_3)
            .freeText4(UPDATED_FREE_TEXT_4)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText6(UPDATED_FREE_TEXT_6)
            .freeText7(UPDATED_FREE_TEXT_7)
            .freeText8(UPDATED_FREE_TEXT_8)
            .freeText9(UPDATED_FREE_TEXT_9)
            .freeText10(UPDATED_FREE_TEXT_10)
            .freeText11(UPDATED_FREE_TEXT_11)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText13(UPDATED_FREE_TEXT_13)
            .freeText14(UPDATED_FREE_TEXT_14)
            .freeText15(UPDATED_FREE_TEXT_15)
            .freeText16(UPDATED_FREE_TEXT_16)
            .freeText17(UPDATED_FREE_TEXT_17)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText19(UPDATED_FREE_TEXT_19)
            .freeText20(UPDATED_FREE_TEXT_20)
            .freeText21(UPDATED_FREE_TEXT_21)
            .freeText22(UPDATED_FREE_TEXT_22)
            .freeText23(UPDATED_FREE_TEXT_23)
            .freeText24(UPDATED_FREE_TEXT_24)
            .freeText25(UPDATED_FREE_TEXT_25)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText27(UPDATED_FREE_TEXT_27)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCustomAudit.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCustomAudit))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        CustomAudit testCustomAudit = customAuditList.get(customAuditList.size() - 1);
        assertThat(testCustomAudit.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomAudit.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testCustomAudit.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testCustomAudit.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCustomAudit.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAudit.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAudit.getChangeResaon()).isEqualTo(UPDATED_CHANGE_RESAON);
        assertThat(testCustomAudit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomAudit.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
        assertThat(testCustomAudit.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
        assertThat(testCustomAudit.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
        assertThat(testCustomAudit.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAudit.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
        assertThat(testCustomAudit.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
        assertThat(testCustomAudit.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
        assertThat(testCustomAudit.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
        assertThat(testCustomAudit.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
        assertThat(testCustomAudit.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testCustomAudit.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testCustomAudit.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testCustomAudit.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testCustomAudit.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testCustomAudit.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testCustomAudit.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testCustomAudit.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testCustomAudit.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testCustomAudit.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testCustomAudit.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testCustomAudit.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAudit.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAudit.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testCustomAudit.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testCustomAudit.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testCustomAudit.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testCustomAudit.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAudit.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testCustomAudit.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testCustomAudit.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testCustomAudit.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testCustomAudit.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testCustomAudit.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testCustomAudit.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testCustomAudit.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAudit.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testCustomAudit.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAudit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomAudit.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAudit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomAudit.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<CustomAudit> customAuditSearchList = IterableUtils.toList(customAuditSearchRepository.findAll().collectList().block());
                CustomAudit testCustomAuditSearch = customAuditSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testCustomAuditSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testCustomAuditSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testCustomAuditSearch.getActionId()).isEqualTo(UPDATED_ACTION_ID);
                assertThat(testCustomAuditSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testCustomAuditSearch.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
                assertThat(testCustomAuditSearch.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
                assertThat(testCustomAuditSearch.getChangeResaon()).isEqualTo(UPDATED_CHANGE_RESAON);
                assertThat(testCustomAuditSearch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
                assertThat(testCustomAuditSearch.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
                assertThat(testCustomAuditSearch.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
                assertThat(testCustomAuditSearch.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
                assertThat(testCustomAuditSearch.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
                assertThat(testCustomAuditSearch.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
                assertThat(testCustomAuditSearch.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
                assertThat(testCustomAuditSearch.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
                assertThat(testCustomAuditSearch.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
                assertThat(testCustomAuditSearch.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
                assertThat(testCustomAuditSearch.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
                assertThat(testCustomAuditSearch.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
                assertThat(testCustomAuditSearch.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
                assertThat(testCustomAuditSearch.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
                assertThat(testCustomAuditSearch.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
                assertThat(testCustomAuditSearch.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
                assertThat(testCustomAuditSearch.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
                assertThat(testCustomAuditSearch.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
                assertThat(testCustomAuditSearch.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
                assertThat(testCustomAuditSearch.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
                assertThat(testCustomAuditSearch.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
                assertThat(testCustomAuditSearch.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
                assertThat(testCustomAuditSearch.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
                assertThat(testCustomAuditSearch.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
                assertThat(testCustomAuditSearch.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
                assertThat(testCustomAuditSearch.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
                assertThat(testCustomAuditSearch.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
                assertThat(testCustomAuditSearch.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
                assertThat(testCustomAuditSearch.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
                assertThat(testCustomAuditSearch.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
                assertThat(testCustomAuditSearch.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
                assertThat(testCustomAuditSearch.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
                assertThat(testCustomAuditSearch.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
                assertThat(testCustomAuditSearch.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
                assertThat(testCustomAuditSearch.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
                assertThat(testCustomAuditSearch.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
                assertThat(testCustomAuditSearch.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
                assertThat(testCustomAuditSearch.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
                assertThat(testCustomAuditSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testCustomAuditSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testCustomAuditSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testCustomAuditSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, customAudit.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateCustomAuditWithPatch() throws Exception {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();

        // Update the customAudit using partial update
        CustomAudit partialUpdatedCustomAudit = new CustomAudit();
        partialUpdatedCustomAudit.setId(customAudit.getId());

        partialUpdatedCustomAudit
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .description(UPDATED_DESCRIPTION)
            .description2(UPDATED_DESCRIPTION_2)
            .description4(UPDATED_DESCRIPTION_4)
            .description5(UPDATED_DESCRIPTION_5)
            .description6(UPDATED_DESCRIPTION_6)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText7(UPDATED_FREE_TEXT_7)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText13(UPDATED_FREE_TEXT_13)
            .freeText15(UPDATED_FREE_TEXT_15)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText20(UPDATED_FREE_TEXT_20)
            .freeText21(UPDATED_FREE_TEXT_21)
            .freeText22(UPDATED_FREE_TEXT_22)
            .freeText24(UPDATED_FREE_TEXT_24)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomAudit.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomAudit))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        CustomAudit testCustomAudit = customAuditList.get(customAuditList.size() - 1);
        assertThat(testCustomAudit.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomAudit.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testCustomAudit.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testCustomAudit.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCustomAudit.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAudit.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAudit.getChangeResaon()).isEqualTo(DEFAULT_CHANGE_RESAON);
        assertThat(testCustomAudit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomAudit.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAudit.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
        assertThat(testCustomAudit.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAudit.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAudit.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
        assertThat(testCustomAudit.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
        assertThat(testCustomAudit.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAudit.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAudit.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAudit.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testCustomAudit.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAudit.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAudit.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAudit.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testCustomAudit.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testCustomAudit.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testCustomAudit.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testCustomAudit.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAudit.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAudit.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testCustomAudit.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAudit.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAudit.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAudit.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testCustomAudit.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAudit.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAudit.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAudit.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testCustomAudit.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testCustomAudit.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testCustomAudit.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testCustomAudit.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAudit.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testCustomAudit.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testCustomAudit.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAudit.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAudit.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAudit.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomAudit.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAudit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomAudit.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateCustomAuditWithPatch() throws Exception {
        // Initialize the database
        customAuditRepository.save(customAudit).block();

        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();

        // Update the customAudit using partial update
        CustomAudit partialUpdatedCustomAudit = new CustomAudit();
        partialUpdatedCustomAudit.setId(customAudit.getId());

        partialUpdatedCustomAudit
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeResaon(UPDATED_CHANGE_RESAON)
            .description(UPDATED_DESCRIPTION)
            .description1(UPDATED_DESCRIPTION_1)
            .description2(UPDATED_DESCRIPTION_2)
            .description3(UPDATED_DESCRIPTION_3)
            .description4(UPDATED_DESCRIPTION_4)
            .description5(UPDATED_DESCRIPTION_5)
            .description6(UPDATED_DESCRIPTION_6)
            .description7(UPDATED_DESCRIPTION_7)
            .description8(UPDATED_DESCRIPTION_8)
            .description9(UPDATED_DESCRIPTION_9)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText2(UPDATED_FREE_TEXT_2)
            .freeText3(UPDATED_FREE_TEXT_3)
            .freeText4(UPDATED_FREE_TEXT_4)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText6(UPDATED_FREE_TEXT_6)
            .freeText7(UPDATED_FREE_TEXT_7)
            .freeText8(UPDATED_FREE_TEXT_8)
            .freeText9(UPDATED_FREE_TEXT_9)
            .freeText10(UPDATED_FREE_TEXT_10)
            .freeText11(UPDATED_FREE_TEXT_11)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText13(UPDATED_FREE_TEXT_13)
            .freeText14(UPDATED_FREE_TEXT_14)
            .freeText15(UPDATED_FREE_TEXT_15)
            .freeText16(UPDATED_FREE_TEXT_16)
            .freeText17(UPDATED_FREE_TEXT_17)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText19(UPDATED_FREE_TEXT_19)
            .freeText20(UPDATED_FREE_TEXT_20)
            .freeText21(UPDATED_FREE_TEXT_21)
            .freeText22(UPDATED_FREE_TEXT_22)
            .freeText23(UPDATED_FREE_TEXT_23)
            .freeText24(UPDATED_FREE_TEXT_24)
            .freeText25(UPDATED_FREE_TEXT_25)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText27(UPDATED_FREE_TEXT_27)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomAudit.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomAudit))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        CustomAudit testCustomAudit = customAuditList.get(customAuditList.size() - 1);
        assertThat(testCustomAudit.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomAudit.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testCustomAudit.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testCustomAudit.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCustomAudit.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAudit.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAudit.getChangeResaon()).isEqualTo(UPDATED_CHANGE_RESAON);
        assertThat(testCustomAudit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomAudit.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
        assertThat(testCustomAudit.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
        assertThat(testCustomAudit.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
        assertThat(testCustomAudit.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAudit.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
        assertThat(testCustomAudit.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
        assertThat(testCustomAudit.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
        assertThat(testCustomAudit.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
        assertThat(testCustomAudit.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
        assertThat(testCustomAudit.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testCustomAudit.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testCustomAudit.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testCustomAudit.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testCustomAudit.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testCustomAudit.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testCustomAudit.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testCustomAudit.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testCustomAudit.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testCustomAudit.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testCustomAudit.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testCustomAudit.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAudit.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAudit.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testCustomAudit.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testCustomAudit.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testCustomAudit.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testCustomAudit.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAudit.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testCustomAudit.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testCustomAudit.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testCustomAudit.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testCustomAudit.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testCustomAudit.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testCustomAudit.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testCustomAudit.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAudit.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testCustomAudit.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAudit.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomAudit.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAudit.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomAudit.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, customAudit.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamCustomAudit() throws Exception {
        int databaseSizeBeforeUpdate = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        customAudit.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAudit))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CustomAudit in the database
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteCustomAudit() {
        // Initialize the database
        customAuditRepository.save(customAudit).block();
        customAuditRepository.save(customAudit).block();
        customAuditSearchRepository.save(customAudit).block();

        int databaseSizeBeforeDelete = customAuditRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the customAudit
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, customAudit.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CustomAudit> customAuditList = customAuditRepository.findAll().collectList().block();
        assertThat(customAuditList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchCustomAudit() {
        // Initialize the database
        customAudit = customAuditRepository.save(customAudit).block();
        customAuditSearchRepository.save(customAudit).block();

        // Search the customAudit
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + customAudit.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(customAudit.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].actionId")
            .value(hasItem(DEFAULT_ACTION_ID.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].oldValue")
            .value(hasItem(DEFAULT_OLD_VALUE))
            .jsonPath("$.[*].newValue")
            .value(hasItem(DEFAULT_NEW_VALUE))
            .jsonPath("$.[*].changeResaon")
            .value(hasItem(DEFAULT_CHANGE_RESAON))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].description1")
            .value(hasItem(DEFAULT_DESCRIPTION_1))
            .jsonPath("$.[*].description2")
            .value(hasItem(DEFAULT_DESCRIPTION_2))
            .jsonPath("$.[*].description3")
            .value(hasItem(DEFAULT_DESCRIPTION_3))
            .jsonPath("$.[*].description4")
            .value(hasItem(DEFAULT_DESCRIPTION_4))
            .jsonPath("$.[*].description5")
            .value(hasItem(DEFAULT_DESCRIPTION_5))
            .jsonPath("$.[*].description6")
            .value(hasItem(DEFAULT_DESCRIPTION_6))
            .jsonPath("$.[*].description7")
            .value(hasItem(DEFAULT_DESCRIPTION_7))
            .jsonPath("$.[*].description8")
            .value(hasItem(DEFAULT_DESCRIPTION_8))
            .jsonPath("$.[*].description9")
            .value(hasItem(DEFAULT_DESCRIPTION_9))
            .jsonPath("$.[*].freeText1")
            .value(hasItem(DEFAULT_FREE_TEXT_1))
            .jsonPath("$.[*].freeText2")
            .value(hasItem(DEFAULT_FREE_TEXT_2))
            .jsonPath("$.[*].freeText3")
            .value(hasItem(DEFAULT_FREE_TEXT_3))
            .jsonPath("$.[*].freeText4")
            .value(hasItem(DEFAULT_FREE_TEXT_4))
            .jsonPath("$.[*].freeText5")
            .value(hasItem(DEFAULT_FREE_TEXT_5))
            .jsonPath("$.[*].freeText6")
            .value(hasItem(DEFAULT_FREE_TEXT_6))
            .jsonPath("$.[*].freeText7")
            .value(hasItem(DEFAULT_FREE_TEXT_7))
            .jsonPath("$.[*].freeText8")
            .value(hasItem(DEFAULT_FREE_TEXT_8))
            .jsonPath("$.[*].freeText9")
            .value(hasItem(DEFAULT_FREE_TEXT_9))
            .jsonPath("$.[*].freeText10")
            .value(hasItem(DEFAULT_FREE_TEXT_10))
            .jsonPath("$.[*].freeText11")
            .value(hasItem(DEFAULT_FREE_TEXT_11))
            .jsonPath("$.[*].freeText12")
            .value(hasItem(DEFAULT_FREE_TEXT_12))
            .jsonPath("$.[*].freeText13")
            .value(hasItem(DEFAULT_FREE_TEXT_13))
            .jsonPath("$.[*].freeText14")
            .value(hasItem(DEFAULT_FREE_TEXT_14))
            .jsonPath("$.[*].freeText15")
            .value(hasItem(DEFAULT_FREE_TEXT_15))
            .jsonPath("$.[*].freeText16")
            .value(hasItem(DEFAULT_FREE_TEXT_16))
            .jsonPath("$.[*].freeText17")
            .value(hasItem(DEFAULT_FREE_TEXT_17))
            .jsonPath("$.[*].freeText18")
            .value(hasItem(DEFAULT_FREE_TEXT_18))
            .jsonPath("$.[*].freeText19")
            .value(hasItem(DEFAULT_FREE_TEXT_19))
            .jsonPath("$.[*].freeText20")
            .value(hasItem(DEFAULT_FREE_TEXT_20))
            .jsonPath("$.[*].freeText21")
            .value(hasItem(DEFAULT_FREE_TEXT_21))
            .jsonPath("$.[*].freeText22")
            .value(hasItem(DEFAULT_FREE_TEXT_22))
            .jsonPath("$.[*].freeText23")
            .value(hasItem(DEFAULT_FREE_TEXT_23))
            .jsonPath("$.[*].freeText24")
            .value(hasItem(DEFAULT_FREE_TEXT_24))
            .jsonPath("$.[*].freeText25")
            .value(hasItem(DEFAULT_FREE_TEXT_25))
            .jsonPath("$.[*].freeText26")
            .value(hasItem(DEFAULT_FREE_TEXT_26))
            .jsonPath("$.[*].freeText27")
            .value(hasItem(DEFAULT_FREE_TEXT_27))
            .jsonPath("$.[*].freeText28")
            .value(hasItem(DEFAULT_FREE_TEXT_28))
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
