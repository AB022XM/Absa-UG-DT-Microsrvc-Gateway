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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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
import ug.co.absa.microsrvc.gateway.domain.CustomAuditHistory;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditHistoryRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.CustomAuditHistorySearchRepository;

/**
 * Integration tests for the {@link CustomAuditHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CustomAuditHistoryResourceIT {

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

    private static final String DEFAULT_CHANGE_REASON = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE_REASON = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/custom-audit-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/custom-audit-histories";

    @Autowired
    private CustomAuditHistoryRepository customAuditHistoryRepository;

    @Autowired
    private CustomAuditHistorySearchRepository customAuditHistorySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CustomAuditHistory customAuditHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomAuditHistory createEntity(EntityManager em) {
        CustomAuditHistory customAuditHistory = new CustomAuditHistory()
            .id(UUID.randomUUID())
            .recordId(DEFAULT_RECORD_ID)
            .actionId(DEFAULT_ACTION_ID)
            .timestamp(DEFAULT_TIMESTAMP)
            .oldValue(DEFAULT_OLD_VALUE)
            .newValue(DEFAULT_NEW_VALUE)
            .changeReason(DEFAULT_CHANGE_REASON)
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
        return customAuditHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomAuditHistory createUpdatedEntity(EntityManager em) {
        CustomAuditHistory customAuditHistory = new CustomAuditHistory()
            .id(UUID.randomUUID())
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeReason(UPDATED_CHANGE_REASON)
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
        return customAuditHistory;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CustomAuditHistory.class).block();
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
        customAuditHistorySearchRepository.deleteAll().block();
        assertThat(customAuditHistorySearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        customAuditHistory = createEntity(em);
    }

    @Test
    void createCustomAuditHistory() throws Exception {
        int databaseSizeBeforeCreate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(null);
        // Create the CustomAuditHistory
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        CustomAuditHistory testCustomAuditHistory = customAuditHistoryList.get(customAuditHistoryList.size() - 1);
        assertThat(testCustomAuditHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testCustomAuditHistory.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testCustomAuditHistory.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCustomAuditHistory.getOldValue()).isEqualTo(DEFAULT_OLD_VALUE);
        assertThat(testCustomAuditHistory.getNewValue()).isEqualTo(DEFAULT_NEW_VALUE);
        assertThat(testCustomAuditHistory.getChangeReason()).isEqualTo(DEFAULT_CHANGE_REASON);
        assertThat(testCustomAuditHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomAuditHistory.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAuditHistory.getDescription2()).isEqualTo(DEFAULT_DESCRIPTION_2);
        assertThat(testCustomAuditHistory.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAuditHistory.getDescription4()).isEqualTo(DEFAULT_DESCRIPTION_4);
        assertThat(testCustomAuditHistory.getDescription5()).isEqualTo(DEFAULT_DESCRIPTION_5);
        assertThat(testCustomAuditHistory.getDescription6()).isEqualTo(DEFAULT_DESCRIPTION_6);
        assertThat(testCustomAuditHistory.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAuditHistory.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAuditHistory.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAuditHistory.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testCustomAuditHistory.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAuditHistory.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAuditHistory.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAuditHistory.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testCustomAuditHistory.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testCustomAuditHistory.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testCustomAuditHistory.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testCustomAuditHistory.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAuditHistory.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAuditHistory.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testCustomAuditHistory.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testCustomAuditHistory.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testCustomAuditHistory.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAuditHistory.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testCustomAuditHistory.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAuditHistory.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAuditHistory.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testCustomAuditHistory.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testCustomAuditHistory.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testCustomAuditHistory.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testCustomAuditHistory.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testCustomAuditHistory.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAuditHistory.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testCustomAuditHistory.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testCustomAuditHistory.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testCustomAuditHistory.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAuditHistory.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testCustomAuditHistory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomAuditHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomAuditHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomAuditHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createCustomAuditHistoryWithExistingId() throws Exception {
        // Create the CustomAuditHistory with an existing ID
        customAuditHistoryRepository.save(customAuditHistory).block();

        int databaseSizeBeforeCreate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkActionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        // set the field null
        customAuditHistory.setActionId(null);

        // Create the CustomAuditHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        // set the field null
        customAuditHistory.setCreatedAt(null);

        // Create the CustomAuditHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllCustomAuditHistoriesAsStream() {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        List<CustomAuditHistory> customAuditHistoryList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(CustomAuditHistory.class)
            .getResponseBody()
            .filter(customAuditHistory::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(customAuditHistoryList).isNotNull();
        assertThat(customAuditHistoryList).hasSize(1);
        CustomAuditHistory testCustomAuditHistory = customAuditHistoryList.get(0);
        assertThat(testCustomAuditHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testCustomAuditHistory.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testCustomAuditHistory.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCustomAuditHistory.getOldValue()).isEqualTo(DEFAULT_OLD_VALUE);
        assertThat(testCustomAuditHistory.getNewValue()).isEqualTo(DEFAULT_NEW_VALUE);
        assertThat(testCustomAuditHistory.getChangeReason()).isEqualTo(DEFAULT_CHANGE_REASON);
        assertThat(testCustomAuditHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomAuditHistory.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAuditHistory.getDescription2()).isEqualTo(DEFAULT_DESCRIPTION_2);
        assertThat(testCustomAuditHistory.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAuditHistory.getDescription4()).isEqualTo(DEFAULT_DESCRIPTION_4);
        assertThat(testCustomAuditHistory.getDescription5()).isEqualTo(DEFAULT_DESCRIPTION_5);
        assertThat(testCustomAuditHistory.getDescription6()).isEqualTo(DEFAULT_DESCRIPTION_6);
        assertThat(testCustomAuditHistory.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAuditHistory.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAuditHistory.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAuditHistory.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testCustomAuditHistory.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAuditHistory.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAuditHistory.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAuditHistory.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testCustomAuditHistory.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testCustomAuditHistory.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testCustomAuditHistory.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testCustomAuditHistory.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAuditHistory.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAuditHistory.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testCustomAuditHistory.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testCustomAuditHistory.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testCustomAuditHistory.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAuditHistory.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testCustomAuditHistory.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAuditHistory.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAuditHistory.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testCustomAuditHistory.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testCustomAuditHistory.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testCustomAuditHistory.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testCustomAuditHistory.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testCustomAuditHistory.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAuditHistory.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testCustomAuditHistory.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testCustomAuditHistory.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testCustomAuditHistory.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAuditHistory.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testCustomAuditHistory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomAuditHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomAuditHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomAuditHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllCustomAuditHistories() {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        // Get all the customAuditHistoryList
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
            .value(hasItem(customAuditHistory.getId().toString()))
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
            .jsonPath("$.[*].changeReason")
            .value(hasItem(DEFAULT_CHANGE_REASON))
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
    void getCustomAuditHistory() {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        // Get the customAuditHistory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, customAuditHistory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(customAuditHistory.getId().toString()))
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
            .jsonPath("$.changeReason")
            .value(is(DEFAULT_CHANGE_REASON))
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
    void getNonExistingCustomAuditHistory() {
        // Get the customAuditHistory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCustomAuditHistory() throws Exception {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        customAuditHistorySearchRepository.save(customAuditHistory).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());

        // Update the customAuditHistory
        CustomAuditHistory updatedCustomAuditHistory = customAuditHistoryRepository.findById(customAuditHistory.getId()).block();
        updatedCustomAuditHistory
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeReason(UPDATED_CHANGE_REASON)
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
            .uri(ENTITY_API_URL_ID, updatedCustomAuditHistory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCustomAuditHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        CustomAuditHistory testCustomAuditHistory = customAuditHistoryList.get(customAuditHistoryList.size() - 1);
        assertThat(testCustomAuditHistory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testCustomAuditHistory.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testCustomAuditHistory.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCustomAuditHistory.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAuditHistory.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAuditHistory.getChangeReason()).isEqualTo(UPDATED_CHANGE_REASON);
        assertThat(testCustomAuditHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomAuditHistory.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
        assertThat(testCustomAuditHistory.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
        assertThat(testCustomAuditHistory.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
        assertThat(testCustomAuditHistory.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAuditHistory.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
        assertThat(testCustomAuditHistory.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
        assertThat(testCustomAuditHistory.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
        assertThat(testCustomAuditHistory.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
        assertThat(testCustomAuditHistory.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
        assertThat(testCustomAuditHistory.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testCustomAuditHistory.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testCustomAuditHistory.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testCustomAuditHistory.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testCustomAuditHistory.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testCustomAuditHistory.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testCustomAuditHistory.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testCustomAuditHistory.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testCustomAuditHistory.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testCustomAuditHistory.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testCustomAuditHistory.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testCustomAuditHistory.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAuditHistory.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAuditHistory.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testCustomAuditHistory.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testCustomAuditHistory.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testCustomAuditHistory.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testCustomAuditHistory.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAuditHistory.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testCustomAuditHistory.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testCustomAuditHistory.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testCustomAuditHistory.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testCustomAuditHistory.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testCustomAuditHistory.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testCustomAuditHistory.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testCustomAuditHistory.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAuditHistory.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testCustomAuditHistory.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAuditHistory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomAuditHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAuditHistory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomAuditHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<CustomAuditHistory> customAuditHistorySearchList = IterableUtils.toList(
                    customAuditHistorySearchRepository.findAll().collectList().block()
                );
                CustomAuditHistory testCustomAuditHistorySearch = customAuditHistorySearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testCustomAuditHistorySearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testCustomAuditHistorySearch.getActionId()).isEqualTo(UPDATED_ACTION_ID);
                assertThat(testCustomAuditHistorySearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testCustomAuditHistorySearch.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
                assertThat(testCustomAuditHistorySearch.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
                assertThat(testCustomAuditHistorySearch.getChangeReason()).isEqualTo(UPDATED_CHANGE_REASON);
                assertThat(testCustomAuditHistorySearch.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
                assertThat(testCustomAuditHistorySearch.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
                assertThat(testCustomAuditHistorySearch.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
                assertThat(testCustomAuditHistorySearch.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
                assertThat(testCustomAuditHistorySearch.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
                assertThat(testCustomAuditHistorySearch.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
                assertThat(testCustomAuditHistorySearch.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
                assertThat(testCustomAuditHistorySearch.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
                assertThat(testCustomAuditHistorySearch.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
                assertThat(testCustomAuditHistorySearch.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
                assertThat(testCustomAuditHistorySearch.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
                assertThat(testCustomAuditHistorySearch.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
                assertThat(testCustomAuditHistorySearch.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
                assertThat(testCustomAuditHistorySearch.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
                assertThat(testCustomAuditHistorySearch.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
                assertThat(testCustomAuditHistorySearch.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
                assertThat(testCustomAuditHistorySearch.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
                assertThat(testCustomAuditHistorySearch.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
                assertThat(testCustomAuditHistorySearch.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
                assertThat(testCustomAuditHistorySearch.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
                assertThat(testCustomAuditHistorySearch.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
                assertThat(testCustomAuditHistorySearch.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
                assertThat(testCustomAuditHistorySearch.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
                assertThat(testCustomAuditHistorySearch.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
                assertThat(testCustomAuditHistorySearch.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
                assertThat(testCustomAuditHistorySearch.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
                assertThat(testCustomAuditHistorySearch.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
                assertThat(testCustomAuditHistorySearch.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
                assertThat(testCustomAuditHistorySearch.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
                assertThat(testCustomAuditHistorySearch.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
                assertThat(testCustomAuditHistorySearch.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
                assertThat(testCustomAuditHistorySearch.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
                assertThat(testCustomAuditHistorySearch.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
                assertThat(testCustomAuditHistorySearch.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
                assertThat(testCustomAuditHistorySearch.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
                assertThat(testCustomAuditHistorySearch.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
                assertThat(testCustomAuditHistorySearch.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
                assertThat(testCustomAuditHistorySearch.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
                assertThat(testCustomAuditHistorySearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testCustomAuditHistorySearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testCustomAuditHistorySearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testCustomAuditHistorySearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, customAuditHistory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateCustomAuditHistoryWithPatch() throws Exception {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();

        // Update the customAuditHistory using partial update
        CustomAuditHistory partialUpdatedCustomAuditHistory = new CustomAuditHistory();
        partialUpdatedCustomAuditHistory.setId(customAuditHistory.getId());

        partialUpdatedCustomAuditHistory
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeReason(UPDATED_CHANGE_REASON)
            .description4(UPDATED_DESCRIPTION_4)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText6(UPDATED_FREE_TEXT_6)
            .freeText8(UPDATED_FREE_TEXT_8)
            .freeText11(UPDATED_FREE_TEXT_11)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText13(UPDATED_FREE_TEXT_13)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText19(UPDATED_FREE_TEXT_19)
            .freeText25(UPDATED_FREE_TEXT_25)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomAuditHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomAuditHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        CustomAuditHistory testCustomAuditHistory = customAuditHistoryList.get(customAuditHistoryList.size() - 1);
        assertThat(testCustomAuditHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testCustomAuditHistory.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testCustomAuditHistory.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCustomAuditHistory.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAuditHistory.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAuditHistory.getChangeReason()).isEqualTo(UPDATED_CHANGE_REASON);
        assertThat(testCustomAuditHistory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomAuditHistory.getDescription1()).isEqualTo(DEFAULT_DESCRIPTION_1);
        assertThat(testCustomAuditHistory.getDescription2()).isEqualTo(DEFAULT_DESCRIPTION_2);
        assertThat(testCustomAuditHistory.getDescription3()).isEqualTo(DEFAULT_DESCRIPTION_3);
        assertThat(testCustomAuditHistory.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAuditHistory.getDescription5()).isEqualTo(DEFAULT_DESCRIPTION_5);
        assertThat(testCustomAuditHistory.getDescription6()).isEqualTo(DEFAULT_DESCRIPTION_6);
        assertThat(testCustomAuditHistory.getDescription7()).isEqualTo(DEFAULT_DESCRIPTION_7);
        assertThat(testCustomAuditHistory.getDescription8()).isEqualTo(DEFAULT_DESCRIPTION_8);
        assertThat(testCustomAuditHistory.getDescription9()).isEqualTo(DEFAULT_DESCRIPTION_9);
        assertThat(testCustomAuditHistory.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testCustomAuditHistory.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testCustomAuditHistory.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testCustomAuditHistory.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testCustomAuditHistory.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testCustomAuditHistory.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testCustomAuditHistory.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testCustomAuditHistory.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testCustomAuditHistory.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testCustomAuditHistory.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testCustomAuditHistory.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testCustomAuditHistory.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAuditHistory.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAuditHistory.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testCustomAuditHistory.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testCustomAuditHistory.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testCustomAuditHistory.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testCustomAuditHistory.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAuditHistory.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testCustomAuditHistory.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testCustomAuditHistory.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testCustomAuditHistory.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testCustomAuditHistory.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testCustomAuditHistory.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testCustomAuditHistory.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testCustomAuditHistory.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAuditHistory.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testCustomAuditHistory.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAuditHistory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomAuditHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAuditHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomAuditHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateCustomAuditHistoryWithPatch() throws Exception {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();

        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();

        // Update the customAuditHistory using partial update
        CustomAuditHistory partialUpdatedCustomAuditHistory = new CustomAuditHistory();
        partialUpdatedCustomAuditHistory.setId(customAuditHistory.getId());

        partialUpdatedCustomAuditHistory
            .recordId(UPDATED_RECORD_ID)
            .actionId(UPDATED_ACTION_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .oldValue(UPDATED_OLD_VALUE)
            .newValue(UPDATED_NEW_VALUE)
            .changeReason(UPDATED_CHANGE_REASON)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomAuditHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomAuditHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        CustomAuditHistory testCustomAuditHistory = customAuditHistoryList.get(customAuditHistoryList.size() - 1);
        assertThat(testCustomAuditHistory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testCustomAuditHistory.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testCustomAuditHistory.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCustomAuditHistory.getOldValue()).isEqualTo(UPDATED_OLD_VALUE);
        assertThat(testCustomAuditHistory.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testCustomAuditHistory.getChangeReason()).isEqualTo(UPDATED_CHANGE_REASON);
        assertThat(testCustomAuditHistory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomAuditHistory.getDescription1()).isEqualTo(UPDATED_DESCRIPTION_1);
        assertThat(testCustomAuditHistory.getDescription2()).isEqualTo(UPDATED_DESCRIPTION_2);
        assertThat(testCustomAuditHistory.getDescription3()).isEqualTo(UPDATED_DESCRIPTION_3);
        assertThat(testCustomAuditHistory.getDescription4()).isEqualTo(UPDATED_DESCRIPTION_4);
        assertThat(testCustomAuditHistory.getDescription5()).isEqualTo(UPDATED_DESCRIPTION_5);
        assertThat(testCustomAuditHistory.getDescription6()).isEqualTo(UPDATED_DESCRIPTION_6);
        assertThat(testCustomAuditHistory.getDescription7()).isEqualTo(UPDATED_DESCRIPTION_7);
        assertThat(testCustomAuditHistory.getDescription8()).isEqualTo(UPDATED_DESCRIPTION_8);
        assertThat(testCustomAuditHistory.getDescription9()).isEqualTo(UPDATED_DESCRIPTION_9);
        assertThat(testCustomAuditHistory.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testCustomAuditHistory.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testCustomAuditHistory.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testCustomAuditHistory.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testCustomAuditHistory.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testCustomAuditHistory.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testCustomAuditHistory.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testCustomAuditHistory.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testCustomAuditHistory.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testCustomAuditHistory.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testCustomAuditHistory.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testCustomAuditHistory.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testCustomAuditHistory.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testCustomAuditHistory.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testCustomAuditHistory.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testCustomAuditHistory.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testCustomAuditHistory.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testCustomAuditHistory.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testCustomAuditHistory.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testCustomAuditHistory.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testCustomAuditHistory.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testCustomAuditHistory.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testCustomAuditHistory.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testCustomAuditHistory.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testCustomAuditHistory.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testCustomAuditHistory.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testCustomAuditHistory.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testCustomAuditHistory.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testCustomAuditHistory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomAuditHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomAuditHistory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomAuditHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, customAuditHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamCustomAuditHistory() throws Exception {
        int databaseSizeBeforeUpdate = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        customAuditHistory.setId(UUID.randomUUID());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customAuditHistory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CustomAuditHistory in the database
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteCustomAuditHistory() {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistoryRepository.save(customAuditHistory).block();
        customAuditHistoryRepository.save(customAuditHistory).block();
        customAuditHistorySearchRepository.save(customAuditHistory).block();

        int databaseSizeBeforeDelete = customAuditHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the customAuditHistory
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, customAuditHistory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CustomAuditHistory> customAuditHistoryList = customAuditHistoryRepository.findAll().collectList().block();
        assertThat(customAuditHistoryList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customAuditHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchCustomAuditHistory() {
        // Initialize the database
        customAuditHistory.setId(UUID.randomUUID());
        customAuditHistory = customAuditHistoryRepository.save(customAuditHistory).block();
        customAuditHistorySearchRepository.save(customAuditHistory).block();

        // Search the customAuditHistory
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + customAuditHistory.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(customAuditHistory.getId().toString()))
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
            .jsonPath("$.[*].changeReason")
            .value(hasItem(DEFAULT_CHANGE_REASON))
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
