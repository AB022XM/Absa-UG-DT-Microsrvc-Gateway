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
import ug.co.absa.microsrvc.gateway.domain.BlockedAccounts;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.BlockedAccountsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.BlockedAccountsSearchRepository;

/**
 * Integration tests for the {@link BlockedAccountsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BlockedAccountsResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_ID = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_BLOCK_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_BLOCK_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BLOCK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_1 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_4 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_4 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_TEMP = false;
    private static final Boolean UPDATED_IS_TEMP = true;

    private static final String DEFAULT_BLOCK_FREE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_1 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_2 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_3 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_4 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_5 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_FREE_TEXT_6 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_FREE_TEXT_6 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_5 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_6 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_7 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_7 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_8 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_8 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_9 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_9 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_10 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_10 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_11 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_11 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_12 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_12 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_13 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_13 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_14 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_14 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_15 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_15 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOCK_REASON_CODE_16 = "AAAAAAAAAA";
    private static final String UPDATED_BLOCK_REASON_CODE_16 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELFLG = false;
    private static final Boolean UPDATED_DELFLG = true;

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.ACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.INACTIVE;

    private static final String ENTITY_API_URL = "/api/blocked-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/blocked-accounts";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BlockedAccountsRepository blockedAccountsRepository;

    @Autowired
    private BlockedAccountsSearchRepository blockedAccountsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private BlockedAccounts blockedAccounts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlockedAccounts createEntity(EntityManager em) {
        BlockedAccounts blockedAccounts = new BlockedAccounts()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerAccountNumber(DEFAULT_CUSTOMER_ACCOUNT_NUMBER)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .blockId(DEFAULT_BLOCK_ID)
            .blockCode(DEFAULT_BLOCK_CODE)
            .blockDate(DEFAULT_BLOCK_DATE)
            .blockType(DEFAULT_BLOCK_TYPE)
            .blockStatus(DEFAULT_BLOCK_STATUS)
            .blockReason(DEFAULT_BLOCK_REASON)
            .blockReasonCode1(DEFAULT_BLOCK_REASON_CODE_1)
            .blockReasonCode2(DEFAULT_BLOCK_REASON_CODE_2)
            .blockReason1(DEFAULT_BLOCK_REASON_1)
            .blockReasonCode3(DEFAULT_BLOCK_REASON_CODE_3)
            .blockReasonCode4(DEFAULT_BLOCK_REASON_CODE_4)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .isTemp(DEFAULT_IS_TEMP)
            .blockFreeText(DEFAULT_BLOCK_FREE_TEXT)
            .blockFreeText1(DEFAULT_BLOCK_FREE_TEXT_1)
            .blockFreeText2(DEFAULT_BLOCK_FREE_TEXT_2)
            .blockFreeText3(DEFAULT_BLOCK_FREE_TEXT_3)
            .blockFreeText4(DEFAULT_BLOCK_FREE_TEXT_4)
            .blockFreeText5(DEFAULT_BLOCK_FREE_TEXT_5)
            .blockFreeText6(DEFAULT_BLOCK_FREE_TEXT_6)
            .blockReasonCode5(DEFAULT_BLOCK_REASON_CODE_5)
            .blockReasonCode6(DEFAULT_BLOCK_REASON_CODE_6)
            .blockReasonCode7(DEFAULT_BLOCK_REASON_CODE_7)
            .blockReasonCode8(DEFAULT_BLOCK_REASON_CODE_8)
            .blockReasonCode9(DEFAULT_BLOCK_REASON_CODE_9)
            .blockReasonCode10(DEFAULT_BLOCK_REASON_CODE_10)
            .blockReasonCode11(DEFAULT_BLOCK_REASON_CODE_11)
            .blockReasonCode12(DEFAULT_BLOCK_REASON_CODE_12)
            .blockReasonCode13(DEFAULT_BLOCK_REASON_CODE_13)
            .blockReasonCode14(DEFAULT_BLOCK_REASON_CODE_14)
            .blockReasonCode15(DEFAULT_BLOCK_REASON_CODE_15)
            .blockReasonCode16(DEFAULT_BLOCK_REASON_CODE_16)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .delflg(DEFAULT_DELFLG)
            .status(DEFAULT_STATUS);
        return blockedAccounts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlockedAccounts createUpdatedEntity(EntityManager em) {
        BlockedAccounts blockedAccounts = new BlockedAccounts()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerAccountNumber(UPDATED_CUSTOMER_ACCOUNT_NUMBER)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .blockId(UPDATED_BLOCK_ID)
            .blockCode(UPDATED_BLOCK_CODE)
            .blockDate(UPDATED_BLOCK_DATE)
            .blockType(UPDATED_BLOCK_TYPE)
            .blockStatus(UPDATED_BLOCK_STATUS)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockReasonCode1(UPDATED_BLOCK_REASON_CODE_1)
            .blockReasonCode2(UPDATED_BLOCK_REASON_CODE_2)
            .blockReason1(UPDATED_BLOCK_REASON_1)
            .blockReasonCode3(UPDATED_BLOCK_REASON_CODE_3)
            .blockReasonCode4(UPDATED_BLOCK_REASON_CODE_4)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .isTemp(UPDATED_IS_TEMP)
            .blockFreeText(UPDATED_BLOCK_FREE_TEXT)
            .blockFreeText1(UPDATED_BLOCK_FREE_TEXT_1)
            .blockFreeText2(UPDATED_BLOCK_FREE_TEXT_2)
            .blockFreeText3(UPDATED_BLOCK_FREE_TEXT_3)
            .blockFreeText4(UPDATED_BLOCK_FREE_TEXT_4)
            .blockFreeText5(UPDATED_BLOCK_FREE_TEXT_5)
            .blockFreeText6(UPDATED_BLOCK_FREE_TEXT_6)
            .blockReasonCode5(UPDATED_BLOCK_REASON_CODE_5)
            .blockReasonCode6(UPDATED_BLOCK_REASON_CODE_6)
            .blockReasonCode7(UPDATED_BLOCK_REASON_CODE_7)
            .blockReasonCode8(UPDATED_BLOCK_REASON_CODE_8)
            .blockReasonCode9(UPDATED_BLOCK_REASON_CODE_9)
            .blockReasonCode10(UPDATED_BLOCK_REASON_CODE_10)
            .blockReasonCode11(UPDATED_BLOCK_REASON_CODE_11)
            .blockReasonCode12(UPDATED_BLOCK_REASON_CODE_12)
            .blockReasonCode13(UPDATED_BLOCK_REASON_CODE_13)
            .blockReasonCode14(UPDATED_BLOCK_REASON_CODE_14)
            .blockReasonCode15(UPDATED_BLOCK_REASON_CODE_15)
            .blockReasonCode16(UPDATED_BLOCK_REASON_CODE_16)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);
        return blockedAccounts;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(BlockedAccounts.class).block();
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
        blockedAccountsSearchRepository.deleteAll().block();
        assertThat(blockedAccountsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        blockedAccounts = createEntity(em);
    }

    @Test
    void createBlockedAccounts() throws Exception {
        int databaseSizeBeforeCreate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // Create the BlockedAccounts
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        BlockedAccounts testBlockedAccounts = blockedAccountsList.get(blockedAccountsList.size() - 1);
        assertThat(testBlockedAccounts.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBlockedAccounts.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBlockedAccounts.getCustomerAccountNumber()).isEqualTo(DEFAULT_CUSTOMER_ACCOUNT_NUMBER);
        assertThat(testBlockedAccounts.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testBlockedAccounts.getBlockId()).isEqualTo(DEFAULT_BLOCK_ID);
        assertThat(testBlockedAccounts.getBlockCode()).isEqualTo(DEFAULT_BLOCK_CODE);
        assertThat(testBlockedAccounts.getBlockDate()).isEqualTo(DEFAULT_BLOCK_DATE);
        assertThat(testBlockedAccounts.getBlockType()).isEqualTo(DEFAULT_BLOCK_TYPE);
        assertThat(testBlockedAccounts.getBlockStatus()).isEqualTo(DEFAULT_BLOCK_STATUS);
        assertThat(testBlockedAccounts.getBlockReason()).isEqualTo(DEFAULT_BLOCK_REASON);
        assertThat(testBlockedAccounts.getBlockReasonCode1()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_1);
        assertThat(testBlockedAccounts.getBlockReasonCode2()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_2);
        assertThat(testBlockedAccounts.getBlockReason1()).isEqualTo(DEFAULT_BLOCK_REASON_1);
        assertThat(testBlockedAccounts.getBlockReasonCode3()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_3);
        assertThat(testBlockedAccounts.getBlockReasonCode4()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_4);
        assertThat(testBlockedAccounts.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBlockedAccounts.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBlockedAccounts.getIsTemp()).isEqualTo(DEFAULT_IS_TEMP);
        assertThat(testBlockedAccounts.getBlockFreeText()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT);
        assertThat(testBlockedAccounts.getBlockFreeText1()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_1);
        assertThat(testBlockedAccounts.getBlockFreeText2()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_2);
        assertThat(testBlockedAccounts.getBlockFreeText3()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_3);
        assertThat(testBlockedAccounts.getBlockFreeText4()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_4);
        assertThat(testBlockedAccounts.getBlockFreeText5()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_5);
        assertThat(testBlockedAccounts.getBlockFreeText6()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_6);
        assertThat(testBlockedAccounts.getBlockReasonCode5()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_5);
        assertThat(testBlockedAccounts.getBlockReasonCode6()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_6);
        assertThat(testBlockedAccounts.getBlockReasonCode7()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_7);
        assertThat(testBlockedAccounts.getBlockReasonCode8()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_8);
        assertThat(testBlockedAccounts.getBlockReasonCode9()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_9);
        assertThat(testBlockedAccounts.getBlockReasonCode10()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_10);
        assertThat(testBlockedAccounts.getBlockReasonCode11()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_11);
        assertThat(testBlockedAccounts.getBlockReasonCode12()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_12);
        assertThat(testBlockedAccounts.getBlockReasonCode13()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_13);
        assertThat(testBlockedAccounts.getBlockReasonCode14()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_14);
        assertThat(testBlockedAccounts.getBlockReasonCode15()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_15);
        assertThat(testBlockedAccounts.getBlockReasonCode16()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_16);
        assertThat(testBlockedAccounts.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBlockedAccounts.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBlockedAccounts.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBlockedAccounts.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBlockedAccounts.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBlockedAccounts.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createBlockedAccountsWithExistingId() throws Exception {
        // Create the BlockedAccounts with an existing ID
        blockedAccounts.setId(1L);

        int databaseSizeBeforeCreate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setCustomerId(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setCustomerAccountNumber(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockId(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockCode(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockDate(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockType(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockStatus(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReasonIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReason(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReasonCode1IsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReasonCode1(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReasonCode2IsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReasonCode2(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReason1IsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReason1(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReasonCode3IsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReasonCode3(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBlockReasonCode4IsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setBlockReasonCode4(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setStartDate(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setEndDate(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        // set the field null
        blockedAccounts.setCreatedAt(null);

        // Create the BlockedAccounts, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllBlockedAccountsAsStream() {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        List<BlockedAccounts> blockedAccountsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(BlockedAccounts.class)
            .getResponseBody()
            .filter(blockedAccounts::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(blockedAccountsList).isNotNull();
        assertThat(blockedAccountsList).hasSize(1);
        BlockedAccounts testBlockedAccounts = blockedAccountsList.get(0);
        assertThat(testBlockedAccounts.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBlockedAccounts.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBlockedAccounts.getCustomerAccountNumber()).isEqualTo(DEFAULT_CUSTOMER_ACCOUNT_NUMBER);
        assertThat(testBlockedAccounts.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testBlockedAccounts.getBlockId()).isEqualTo(DEFAULT_BLOCK_ID);
        assertThat(testBlockedAccounts.getBlockCode()).isEqualTo(DEFAULT_BLOCK_CODE);
        assertThat(testBlockedAccounts.getBlockDate()).isEqualTo(DEFAULT_BLOCK_DATE);
        assertThat(testBlockedAccounts.getBlockType()).isEqualTo(DEFAULT_BLOCK_TYPE);
        assertThat(testBlockedAccounts.getBlockStatus()).isEqualTo(DEFAULT_BLOCK_STATUS);
        assertThat(testBlockedAccounts.getBlockReason()).isEqualTo(DEFAULT_BLOCK_REASON);
        assertThat(testBlockedAccounts.getBlockReasonCode1()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_1);
        assertThat(testBlockedAccounts.getBlockReasonCode2()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_2);
        assertThat(testBlockedAccounts.getBlockReason1()).isEqualTo(DEFAULT_BLOCK_REASON_1);
        assertThat(testBlockedAccounts.getBlockReasonCode3()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_3);
        assertThat(testBlockedAccounts.getBlockReasonCode4()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_4);
        assertThat(testBlockedAccounts.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBlockedAccounts.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBlockedAccounts.getIsTemp()).isEqualTo(DEFAULT_IS_TEMP);
        assertThat(testBlockedAccounts.getBlockFreeText()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT);
        assertThat(testBlockedAccounts.getBlockFreeText1()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_1);
        assertThat(testBlockedAccounts.getBlockFreeText2()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_2);
        assertThat(testBlockedAccounts.getBlockFreeText3()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_3);
        assertThat(testBlockedAccounts.getBlockFreeText4()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_4);
        assertThat(testBlockedAccounts.getBlockFreeText5()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_5);
        assertThat(testBlockedAccounts.getBlockFreeText6()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_6);
        assertThat(testBlockedAccounts.getBlockReasonCode5()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_5);
        assertThat(testBlockedAccounts.getBlockReasonCode6()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_6);
        assertThat(testBlockedAccounts.getBlockReasonCode7()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_7);
        assertThat(testBlockedAccounts.getBlockReasonCode8()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_8);
        assertThat(testBlockedAccounts.getBlockReasonCode9()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_9);
        assertThat(testBlockedAccounts.getBlockReasonCode10()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_10);
        assertThat(testBlockedAccounts.getBlockReasonCode11()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_11);
        assertThat(testBlockedAccounts.getBlockReasonCode12()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_12);
        assertThat(testBlockedAccounts.getBlockReasonCode13()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_13);
        assertThat(testBlockedAccounts.getBlockReasonCode14()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_14);
        assertThat(testBlockedAccounts.getBlockReasonCode15()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_15);
        assertThat(testBlockedAccounts.getBlockReasonCode16()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_16);
        assertThat(testBlockedAccounts.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBlockedAccounts.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBlockedAccounts.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBlockedAccounts.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBlockedAccounts.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBlockedAccounts.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void getAllBlockedAccounts() {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        // Get all the blockedAccountsList
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
            .value(hasItem(blockedAccounts.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.[*].customerAccountNumber")
            .value(hasItem(DEFAULT_CUSTOMER_ACCOUNT_NUMBER))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].blockId")
            .value(hasItem(DEFAULT_BLOCK_ID))
            .jsonPath("$.[*].blockCode")
            .value(hasItem(DEFAULT_BLOCK_CODE))
            .jsonPath("$.[*].blockDate")
            .value(hasItem(sameInstant(DEFAULT_BLOCK_DATE)))
            .jsonPath("$.[*].blockType")
            .value(hasItem(DEFAULT_BLOCK_TYPE))
            .jsonPath("$.[*].blockStatus")
            .value(hasItem(DEFAULT_BLOCK_STATUS))
            .jsonPath("$.[*].blockReason")
            .value(hasItem(DEFAULT_BLOCK_REASON))
            .jsonPath("$.[*].blockReasonCode1")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_1))
            .jsonPath("$.[*].blockReasonCode2")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_2))
            .jsonPath("$.[*].blockReason1")
            .value(hasItem(DEFAULT_BLOCK_REASON_1))
            .jsonPath("$.[*].blockReasonCode3")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_3))
            .jsonPath("$.[*].blockReasonCode4")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_4))
            .jsonPath("$.[*].startDate")
            .value(hasItem(sameInstant(DEFAULT_START_DATE)))
            .jsonPath("$.[*].endDate")
            .value(hasItem(sameInstant(DEFAULT_END_DATE)))
            .jsonPath("$.[*].isTemp")
            .value(hasItem(DEFAULT_IS_TEMP.booleanValue()))
            .jsonPath("$.[*].blockFreeText")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT))
            .jsonPath("$.[*].blockFreeText1")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_1))
            .jsonPath("$.[*].blockFreeText2")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_2))
            .jsonPath("$.[*].blockFreeText3")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_3))
            .jsonPath("$.[*].blockFreeText4")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_4))
            .jsonPath("$.[*].blockFreeText5")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_5))
            .jsonPath("$.[*].blockFreeText6")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_6))
            .jsonPath("$.[*].blockReasonCode5")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_5))
            .jsonPath("$.[*].blockReasonCode6")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_6))
            .jsonPath("$.[*].blockReasonCode7")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_7))
            .jsonPath("$.[*].blockReasonCode8")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_8))
            .jsonPath("$.[*].blockReasonCode9")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_9))
            .jsonPath("$.[*].blockReasonCode10")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_10))
            .jsonPath("$.[*].blockReasonCode11")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_11))
            .jsonPath("$.[*].blockReasonCode12")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_12))
            .jsonPath("$.[*].blockReasonCode13")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_13))
            .jsonPath("$.[*].blockReasonCode14")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_14))
            .jsonPath("$.[*].blockReasonCode15")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_15))
            .jsonPath("$.[*].blockReasonCode16")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_16))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()));
    }

    @Test
    void getBlockedAccounts() {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        // Get the blockedAccounts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, blockedAccounts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(blockedAccounts.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.customerId")
            .value(is(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.customerAccountNumber")
            .value(is(DEFAULT_CUSTOMER_ACCOUNT_NUMBER))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.blockId")
            .value(is(DEFAULT_BLOCK_ID))
            .jsonPath("$.blockCode")
            .value(is(DEFAULT_BLOCK_CODE))
            .jsonPath("$.blockDate")
            .value(is(sameInstant(DEFAULT_BLOCK_DATE)))
            .jsonPath("$.blockType")
            .value(is(DEFAULT_BLOCK_TYPE))
            .jsonPath("$.blockStatus")
            .value(is(DEFAULT_BLOCK_STATUS))
            .jsonPath("$.blockReason")
            .value(is(DEFAULT_BLOCK_REASON))
            .jsonPath("$.blockReasonCode1")
            .value(is(DEFAULT_BLOCK_REASON_CODE_1))
            .jsonPath("$.blockReasonCode2")
            .value(is(DEFAULT_BLOCK_REASON_CODE_2))
            .jsonPath("$.blockReason1")
            .value(is(DEFAULT_BLOCK_REASON_1))
            .jsonPath("$.blockReasonCode3")
            .value(is(DEFAULT_BLOCK_REASON_CODE_3))
            .jsonPath("$.blockReasonCode4")
            .value(is(DEFAULT_BLOCK_REASON_CODE_4))
            .jsonPath("$.startDate")
            .value(is(sameInstant(DEFAULT_START_DATE)))
            .jsonPath("$.endDate")
            .value(is(sameInstant(DEFAULT_END_DATE)))
            .jsonPath("$.isTemp")
            .value(is(DEFAULT_IS_TEMP.booleanValue()))
            .jsonPath("$.blockFreeText")
            .value(is(DEFAULT_BLOCK_FREE_TEXT))
            .jsonPath("$.blockFreeText1")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_1))
            .jsonPath("$.blockFreeText2")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_2))
            .jsonPath("$.blockFreeText3")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_3))
            .jsonPath("$.blockFreeText4")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_4))
            .jsonPath("$.blockFreeText5")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_5))
            .jsonPath("$.blockFreeText6")
            .value(is(DEFAULT_BLOCK_FREE_TEXT_6))
            .jsonPath("$.blockReasonCode5")
            .value(is(DEFAULT_BLOCK_REASON_CODE_5))
            .jsonPath("$.blockReasonCode6")
            .value(is(DEFAULT_BLOCK_REASON_CODE_6))
            .jsonPath("$.blockReasonCode7")
            .value(is(DEFAULT_BLOCK_REASON_CODE_7))
            .jsonPath("$.blockReasonCode8")
            .value(is(DEFAULT_BLOCK_REASON_CODE_8))
            .jsonPath("$.blockReasonCode9")
            .value(is(DEFAULT_BLOCK_REASON_CODE_9))
            .jsonPath("$.blockReasonCode10")
            .value(is(DEFAULT_BLOCK_REASON_CODE_10))
            .jsonPath("$.blockReasonCode11")
            .value(is(DEFAULT_BLOCK_REASON_CODE_11))
            .jsonPath("$.blockReasonCode12")
            .value(is(DEFAULT_BLOCK_REASON_CODE_12))
            .jsonPath("$.blockReasonCode13")
            .value(is(DEFAULT_BLOCK_REASON_CODE_13))
            .jsonPath("$.blockReasonCode14")
            .value(is(DEFAULT_BLOCK_REASON_CODE_14))
            .jsonPath("$.blockReasonCode15")
            .value(is(DEFAULT_BLOCK_REASON_CODE_15))
            .jsonPath("$.blockReasonCode16")
            .value(is(DEFAULT_BLOCK_REASON_CODE_16))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY))
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()));
    }

    @Test
    void getNonExistingBlockedAccounts() {
        // Get the blockedAccounts
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBlockedAccounts() throws Exception {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        blockedAccountsSearchRepository.save(blockedAccounts).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());

        // Update the blockedAccounts
        BlockedAccounts updatedBlockedAccounts = blockedAccountsRepository.findById(blockedAccounts.getId()).block();
        updatedBlockedAccounts
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerAccountNumber(UPDATED_CUSTOMER_ACCOUNT_NUMBER)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .blockId(UPDATED_BLOCK_ID)
            .blockCode(UPDATED_BLOCK_CODE)
            .blockDate(UPDATED_BLOCK_DATE)
            .blockType(UPDATED_BLOCK_TYPE)
            .blockStatus(UPDATED_BLOCK_STATUS)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockReasonCode1(UPDATED_BLOCK_REASON_CODE_1)
            .blockReasonCode2(UPDATED_BLOCK_REASON_CODE_2)
            .blockReason1(UPDATED_BLOCK_REASON_1)
            .blockReasonCode3(UPDATED_BLOCK_REASON_CODE_3)
            .blockReasonCode4(UPDATED_BLOCK_REASON_CODE_4)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .isTemp(UPDATED_IS_TEMP)
            .blockFreeText(UPDATED_BLOCK_FREE_TEXT)
            .blockFreeText1(UPDATED_BLOCK_FREE_TEXT_1)
            .blockFreeText2(UPDATED_BLOCK_FREE_TEXT_2)
            .blockFreeText3(UPDATED_BLOCK_FREE_TEXT_3)
            .blockFreeText4(UPDATED_BLOCK_FREE_TEXT_4)
            .blockFreeText5(UPDATED_BLOCK_FREE_TEXT_5)
            .blockFreeText6(UPDATED_BLOCK_FREE_TEXT_6)
            .blockReasonCode5(UPDATED_BLOCK_REASON_CODE_5)
            .blockReasonCode6(UPDATED_BLOCK_REASON_CODE_6)
            .blockReasonCode7(UPDATED_BLOCK_REASON_CODE_7)
            .blockReasonCode8(UPDATED_BLOCK_REASON_CODE_8)
            .blockReasonCode9(UPDATED_BLOCK_REASON_CODE_9)
            .blockReasonCode10(UPDATED_BLOCK_REASON_CODE_10)
            .blockReasonCode11(UPDATED_BLOCK_REASON_CODE_11)
            .blockReasonCode12(UPDATED_BLOCK_REASON_CODE_12)
            .blockReasonCode13(UPDATED_BLOCK_REASON_CODE_13)
            .blockReasonCode14(UPDATED_BLOCK_REASON_CODE_14)
            .blockReasonCode15(UPDATED_BLOCK_REASON_CODE_15)
            .blockReasonCode16(UPDATED_BLOCK_REASON_CODE_16)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBlockedAccounts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBlockedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        BlockedAccounts testBlockedAccounts = blockedAccountsList.get(blockedAccountsList.size() - 1);
        assertThat(testBlockedAccounts.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBlockedAccounts.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBlockedAccounts.getCustomerAccountNumber()).isEqualTo(UPDATED_CUSTOMER_ACCOUNT_NUMBER);
        assertThat(testBlockedAccounts.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testBlockedAccounts.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
        assertThat(testBlockedAccounts.getBlockCode()).isEqualTo(UPDATED_BLOCK_CODE);
        assertThat(testBlockedAccounts.getBlockDate()).isEqualTo(UPDATED_BLOCK_DATE);
        assertThat(testBlockedAccounts.getBlockType()).isEqualTo(UPDATED_BLOCK_TYPE);
        assertThat(testBlockedAccounts.getBlockStatus()).isEqualTo(UPDATED_BLOCK_STATUS);
        assertThat(testBlockedAccounts.getBlockReason()).isEqualTo(UPDATED_BLOCK_REASON);
        assertThat(testBlockedAccounts.getBlockReasonCode1()).isEqualTo(UPDATED_BLOCK_REASON_CODE_1);
        assertThat(testBlockedAccounts.getBlockReasonCode2()).isEqualTo(UPDATED_BLOCK_REASON_CODE_2);
        assertThat(testBlockedAccounts.getBlockReason1()).isEqualTo(UPDATED_BLOCK_REASON_1);
        assertThat(testBlockedAccounts.getBlockReasonCode3()).isEqualTo(UPDATED_BLOCK_REASON_CODE_3);
        assertThat(testBlockedAccounts.getBlockReasonCode4()).isEqualTo(UPDATED_BLOCK_REASON_CODE_4);
        assertThat(testBlockedAccounts.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBlockedAccounts.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBlockedAccounts.getIsTemp()).isEqualTo(UPDATED_IS_TEMP);
        assertThat(testBlockedAccounts.getBlockFreeText()).isEqualTo(UPDATED_BLOCK_FREE_TEXT);
        assertThat(testBlockedAccounts.getBlockFreeText1()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_1);
        assertThat(testBlockedAccounts.getBlockFreeText2()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_2);
        assertThat(testBlockedAccounts.getBlockFreeText3()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_3);
        assertThat(testBlockedAccounts.getBlockFreeText4()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_4);
        assertThat(testBlockedAccounts.getBlockFreeText5()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_5);
        assertThat(testBlockedAccounts.getBlockFreeText6()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_6);
        assertThat(testBlockedAccounts.getBlockReasonCode5()).isEqualTo(UPDATED_BLOCK_REASON_CODE_5);
        assertThat(testBlockedAccounts.getBlockReasonCode6()).isEqualTo(UPDATED_BLOCK_REASON_CODE_6);
        assertThat(testBlockedAccounts.getBlockReasonCode7()).isEqualTo(UPDATED_BLOCK_REASON_CODE_7);
        assertThat(testBlockedAccounts.getBlockReasonCode8()).isEqualTo(UPDATED_BLOCK_REASON_CODE_8);
        assertThat(testBlockedAccounts.getBlockReasonCode9()).isEqualTo(UPDATED_BLOCK_REASON_CODE_9);
        assertThat(testBlockedAccounts.getBlockReasonCode10()).isEqualTo(UPDATED_BLOCK_REASON_CODE_10);
        assertThat(testBlockedAccounts.getBlockReasonCode11()).isEqualTo(UPDATED_BLOCK_REASON_CODE_11);
        assertThat(testBlockedAccounts.getBlockReasonCode12()).isEqualTo(UPDATED_BLOCK_REASON_CODE_12);
        assertThat(testBlockedAccounts.getBlockReasonCode13()).isEqualTo(UPDATED_BLOCK_REASON_CODE_13);
        assertThat(testBlockedAccounts.getBlockReasonCode14()).isEqualTo(UPDATED_BLOCK_REASON_CODE_14);
        assertThat(testBlockedAccounts.getBlockReasonCode15()).isEqualTo(UPDATED_BLOCK_REASON_CODE_15);
        assertThat(testBlockedAccounts.getBlockReasonCode16()).isEqualTo(UPDATED_BLOCK_REASON_CODE_16);
        assertThat(testBlockedAccounts.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBlockedAccounts.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBlockedAccounts.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBlockedAccounts.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBlockedAccounts.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBlockedAccounts.getStatus()).isEqualTo(UPDATED_STATUS);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<BlockedAccounts> blockedAccountsSearchList = IterableUtils.toList(
                    blockedAccountsSearchRepository.findAll().collectList().block()
                );
                BlockedAccounts testBlockedAccountsSearch = blockedAccountsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testBlockedAccountsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testBlockedAccountsSearch.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
                assertThat(testBlockedAccountsSearch.getCustomerAccountNumber()).isEqualTo(UPDATED_CUSTOMER_ACCOUNT_NUMBER);
                assertThat(testBlockedAccountsSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testBlockedAccountsSearch.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
                assertThat(testBlockedAccountsSearch.getBlockCode()).isEqualTo(UPDATED_BLOCK_CODE);
                assertThat(testBlockedAccountsSearch.getBlockDate()).isEqualTo(UPDATED_BLOCK_DATE);
                assertThat(testBlockedAccountsSearch.getBlockType()).isEqualTo(UPDATED_BLOCK_TYPE);
                assertThat(testBlockedAccountsSearch.getBlockStatus()).isEqualTo(UPDATED_BLOCK_STATUS);
                assertThat(testBlockedAccountsSearch.getBlockReason()).isEqualTo(UPDATED_BLOCK_REASON);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode1()).isEqualTo(UPDATED_BLOCK_REASON_CODE_1);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode2()).isEqualTo(UPDATED_BLOCK_REASON_CODE_2);
                assertThat(testBlockedAccountsSearch.getBlockReason1()).isEqualTo(UPDATED_BLOCK_REASON_1);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode3()).isEqualTo(UPDATED_BLOCK_REASON_CODE_3);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode4()).isEqualTo(UPDATED_BLOCK_REASON_CODE_4);
                assertThat(testBlockedAccountsSearch.getStartDate()).isEqualTo(UPDATED_START_DATE);
                assertThat(testBlockedAccountsSearch.getEndDate()).isEqualTo(UPDATED_END_DATE);
                assertThat(testBlockedAccountsSearch.getIsTemp()).isEqualTo(UPDATED_IS_TEMP);
                assertThat(testBlockedAccountsSearch.getBlockFreeText()).isEqualTo(UPDATED_BLOCK_FREE_TEXT);
                assertThat(testBlockedAccountsSearch.getBlockFreeText1()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_1);
                assertThat(testBlockedAccountsSearch.getBlockFreeText2()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_2);
                assertThat(testBlockedAccountsSearch.getBlockFreeText3()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_3);
                assertThat(testBlockedAccountsSearch.getBlockFreeText4()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_4);
                assertThat(testBlockedAccountsSearch.getBlockFreeText5()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_5);
                assertThat(testBlockedAccountsSearch.getBlockFreeText6()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_6);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode5()).isEqualTo(UPDATED_BLOCK_REASON_CODE_5);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode6()).isEqualTo(UPDATED_BLOCK_REASON_CODE_6);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode7()).isEqualTo(UPDATED_BLOCK_REASON_CODE_7);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode8()).isEqualTo(UPDATED_BLOCK_REASON_CODE_8);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode9()).isEqualTo(UPDATED_BLOCK_REASON_CODE_9);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode10()).isEqualTo(UPDATED_BLOCK_REASON_CODE_10);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode11()).isEqualTo(UPDATED_BLOCK_REASON_CODE_11);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode12()).isEqualTo(UPDATED_BLOCK_REASON_CODE_12);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode13()).isEqualTo(UPDATED_BLOCK_REASON_CODE_13);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode14()).isEqualTo(UPDATED_BLOCK_REASON_CODE_14);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode15()).isEqualTo(UPDATED_BLOCK_REASON_CODE_15);
                assertThat(testBlockedAccountsSearch.getBlockReasonCode16()).isEqualTo(UPDATED_BLOCK_REASON_CODE_16);
                assertThat(testBlockedAccountsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testBlockedAccountsSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testBlockedAccountsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testBlockedAccountsSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
                assertThat(testBlockedAccountsSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testBlockedAccountsSearch.getStatus()).isEqualTo(UPDATED_STATUS);
            });
    }

    @Test
    void putNonExistingBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, blockedAccounts.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateBlockedAccountsWithPatch() throws Exception {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();

        // Update the blockedAccounts using partial update
        BlockedAccounts partialUpdatedBlockedAccounts = new BlockedAccounts();
        partialUpdatedBlockedAccounts.setId(blockedAccounts.getId());

        partialUpdatedBlockedAccounts
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .blockId(UPDATED_BLOCK_ID)
            .blockCode(UPDATED_BLOCK_CODE)
            .blockDate(UPDATED_BLOCK_DATE)
            .blockType(UPDATED_BLOCK_TYPE)
            .blockStatus(UPDATED_BLOCK_STATUS)
            .isTemp(UPDATED_IS_TEMP)
            .blockFreeText(UPDATED_BLOCK_FREE_TEXT)
            .blockFreeText3(UPDATED_BLOCK_FREE_TEXT_3)
            .blockFreeText5(UPDATED_BLOCK_FREE_TEXT_5)
            .blockFreeText6(UPDATED_BLOCK_FREE_TEXT_6)
            .blockReasonCode5(UPDATED_BLOCK_REASON_CODE_5)
            .blockReasonCode6(UPDATED_BLOCK_REASON_CODE_6)
            .blockReasonCode7(UPDATED_BLOCK_REASON_CODE_7)
            .blockReasonCode8(UPDATED_BLOCK_REASON_CODE_8)
            .blockReasonCode9(UPDATED_BLOCK_REASON_CODE_9)
            .blockReasonCode13(UPDATED_BLOCK_REASON_CODE_13)
            .blockReasonCode15(UPDATED_BLOCK_REASON_CODE_15)
            .updatedBy(UPDATED_UPDATED_BY)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBlockedAccounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBlockedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        BlockedAccounts testBlockedAccounts = blockedAccountsList.get(blockedAccountsList.size() - 1);
        assertThat(testBlockedAccounts.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBlockedAccounts.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testBlockedAccounts.getCustomerAccountNumber()).isEqualTo(DEFAULT_CUSTOMER_ACCOUNT_NUMBER);
        assertThat(testBlockedAccounts.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testBlockedAccounts.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
        assertThat(testBlockedAccounts.getBlockCode()).isEqualTo(UPDATED_BLOCK_CODE);
        assertThat(testBlockedAccounts.getBlockDate()).isEqualTo(UPDATED_BLOCK_DATE);
        assertThat(testBlockedAccounts.getBlockType()).isEqualTo(UPDATED_BLOCK_TYPE);
        assertThat(testBlockedAccounts.getBlockStatus()).isEqualTo(UPDATED_BLOCK_STATUS);
        assertThat(testBlockedAccounts.getBlockReason()).isEqualTo(DEFAULT_BLOCK_REASON);
        assertThat(testBlockedAccounts.getBlockReasonCode1()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_1);
        assertThat(testBlockedAccounts.getBlockReasonCode2()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_2);
        assertThat(testBlockedAccounts.getBlockReason1()).isEqualTo(DEFAULT_BLOCK_REASON_1);
        assertThat(testBlockedAccounts.getBlockReasonCode3()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_3);
        assertThat(testBlockedAccounts.getBlockReasonCode4()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_4);
        assertThat(testBlockedAccounts.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBlockedAccounts.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBlockedAccounts.getIsTemp()).isEqualTo(UPDATED_IS_TEMP);
        assertThat(testBlockedAccounts.getBlockFreeText()).isEqualTo(UPDATED_BLOCK_FREE_TEXT);
        assertThat(testBlockedAccounts.getBlockFreeText1()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_1);
        assertThat(testBlockedAccounts.getBlockFreeText2()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_2);
        assertThat(testBlockedAccounts.getBlockFreeText3()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_3);
        assertThat(testBlockedAccounts.getBlockFreeText4()).isEqualTo(DEFAULT_BLOCK_FREE_TEXT_4);
        assertThat(testBlockedAccounts.getBlockFreeText5()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_5);
        assertThat(testBlockedAccounts.getBlockFreeText6()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_6);
        assertThat(testBlockedAccounts.getBlockReasonCode5()).isEqualTo(UPDATED_BLOCK_REASON_CODE_5);
        assertThat(testBlockedAccounts.getBlockReasonCode6()).isEqualTo(UPDATED_BLOCK_REASON_CODE_6);
        assertThat(testBlockedAccounts.getBlockReasonCode7()).isEqualTo(UPDATED_BLOCK_REASON_CODE_7);
        assertThat(testBlockedAccounts.getBlockReasonCode8()).isEqualTo(UPDATED_BLOCK_REASON_CODE_8);
        assertThat(testBlockedAccounts.getBlockReasonCode9()).isEqualTo(UPDATED_BLOCK_REASON_CODE_9);
        assertThat(testBlockedAccounts.getBlockReasonCode10()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_10);
        assertThat(testBlockedAccounts.getBlockReasonCode11()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_11);
        assertThat(testBlockedAccounts.getBlockReasonCode12()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_12);
        assertThat(testBlockedAccounts.getBlockReasonCode13()).isEqualTo(UPDATED_BLOCK_REASON_CODE_13);
        assertThat(testBlockedAccounts.getBlockReasonCode14()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_14);
        assertThat(testBlockedAccounts.getBlockReasonCode15()).isEqualTo(UPDATED_BLOCK_REASON_CODE_15);
        assertThat(testBlockedAccounts.getBlockReasonCode16()).isEqualTo(DEFAULT_BLOCK_REASON_CODE_16);
        assertThat(testBlockedAccounts.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBlockedAccounts.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBlockedAccounts.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBlockedAccounts.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBlockedAccounts.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBlockedAccounts.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateBlockedAccountsWithPatch() throws Exception {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();

        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();

        // Update the blockedAccounts using partial update
        BlockedAccounts partialUpdatedBlockedAccounts = new BlockedAccounts();
        partialUpdatedBlockedAccounts.setId(blockedAccounts.getId());

        partialUpdatedBlockedAccounts
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerAccountNumber(UPDATED_CUSTOMER_ACCOUNT_NUMBER)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .blockId(UPDATED_BLOCK_ID)
            .blockCode(UPDATED_BLOCK_CODE)
            .blockDate(UPDATED_BLOCK_DATE)
            .blockType(UPDATED_BLOCK_TYPE)
            .blockStatus(UPDATED_BLOCK_STATUS)
            .blockReason(UPDATED_BLOCK_REASON)
            .blockReasonCode1(UPDATED_BLOCK_REASON_CODE_1)
            .blockReasonCode2(UPDATED_BLOCK_REASON_CODE_2)
            .blockReason1(UPDATED_BLOCK_REASON_1)
            .blockReasonCode3(UPDATED_BLOCK_REASON_CODE_3)
            .blockReasonCode4(UPDATED_BLOCK_REASON_CODE_4)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .isTemp(UPDATED_IS_TEMP)
            .blockFreeText(UPDATED_BLOCK_FREE_TEXT)
            .blockFreeText1(UPDATED_BLOCK_FREE_TEXT_1)
            .blockFreeText2(UPDATED_BLOCK_FREE_TEXT_2)
            .blockFreeText3(UPDATED_BLOCK_FREE_TEXT_3)
            .blockFreeText4(UPDATED_BLOCK_FREE_TEXT_4)
            .blockFreeText5(UPDATED_BLOCK_FREE_TEXT_5)
            .blockFreeText6(UPDATED_BLOCK_FREE_TEXT_6)
            .blockReasonCode5(UPDATED_BLOCK_REASON_CODE_5)
            .blockReasonCode6(UPDATED_BLOCK_REASON_CODE_6)
            .blockReasonCode7(UPDATED_BLOCK_REASON_CODE_7)
            .blockReasonCode8(UPDATED_BLOCK_REASON_CODE_8)
            .blockReasonCode9(UPDATED_BLOCK_REASON_CODE_9)
            .blockReasonCode10(UPDATED_BLOCK_REASON_CODE_10)
            .blockReasonCode11(UPDATED_BLOCK_REASON_CODE_11)
            .blockReasonCode12(UPDATED_BLOCK_REASON_CODE_12)
            .blockReasonCode13(UPDATED_BLOCK_REASON_CODE_13)
            .blockReasonCode14(UPDATED_BLOCK_REASON_CODE_14)
            .blockReasonCode15(UPDATED_BLOCK_REASON_CODE_15)
            .blockReasonCode16(UPDATED_BLOCK_REASON_CODE_16)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBlockedAccounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBlockedAccounts))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        BlockedAccounts testBlockedAccounts = blockedAccountsList.get(blockedAccountsList.size() - 1);
        assertThat(testBlockedAccounts.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBlockedAccounts.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testBlockedAccounts.getCustomerAccountNumber()).isEqualTo(UPDATED_CUSTOMER_ACCOUNT_NUMBER);
        assertThat(testBlockedAccounts.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testBlockedAccounts.getBlockId()).isEqualTo(UPDATED_BLOCK_ID);
        assertThat(testBlockedAccounts.getBlockCode()).isEqualTo(UPDATED_BLOCK_CODE);
        assertThat(testBlockedAccounts.getBlockDate()).isEqualTo(UPDATED_BLOCK_DATE);
        assertThat(testBlockedAccounts.getBlockType()).isEqualTo(UPDATED_BLOCK_TYPE);
        assertThat(testBlockedAccounts.getBlockStatus()).isEqualTo(UPDATED_BLOCK_STATUS);
        assertThat(testBlockedAccounts.getBlockReason()).isEqualTo(UPDATED_BLOCK_REASON);
        assertThat(testBlockedAccounts.getBlockReasonCode1()).isEqualTo(UPDATED_BLOCK_REASON_CODE_1);
        assertThat(testBlockedAccounts.getBlockReasonCode2()).isEqualTo(UPDATED_BLOCK_REASON_CODE_2);
        assertThat(testBlockedAccounts.getBlockReason1()).isEqualTo(UPDATED_BLOCK_REASON_1);
        assertThat(testBlockedAccounts.getBlockReasonCode3()).isEqualTo(UPDATED_BLOCK_REASON_CODE_3);
        assertThat(testBlockedAccounts.getBlockReasonCode4()).isEqualTo(UPDATED_BLOCK_REASON_CODE_4);
        assertThat(testBlockedAccounts.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBlockedAccounts.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBlockedAccounts.getIsTemp()).isEqualTo(UPDATED_IS_TEMP);
        assertThat(testBlockedAccounts.getBlockFreeText()).isEqualTo(UPDATED_BLOCK_FREE_TEXT);
        assertThat(testBlockedAccounts.getBlockFreeText1()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_1);
        assertThat(testBlockedAccounts.getBlockFreeText2()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_2);
        assertThat(testBlockedAccounts.getBlockFreeText3()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_3);
        assertThat(testBlockedAccounts.getBlockFreeText4()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_4);
        assertThat(testBlockedAccounts.getBlockFreeText5()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_5);
        assertThat(testBlockedAccounts.getBlockFreeText6()).isEqualTo(UPDATED_BLOCK_FREE_TEXT_6);
        assertThat(testBlockedAccounts.getBlockReasonCode5()).isEqualTo(UPDATED_BLOCK_REASON_CODE_5);
        assertThat(testBlockedAccounts.getBlockReasonCode6()).isEqualTo(UPDATED_BLOCK_REASON_CODE_6);
        assertThat(testBlockedAccounts.getBlockReasonCode7()).isEqualTo(UPDATED_BLOCK_REASON_CODE_7);
        assertThat(testBlockedAccounts.getBlockReasonCode8()).isEqualTo(UPDATED_BLOCK_REASON_CODE_8);
        assertThat(testBlockedAccounts.getBlockReasonCode9()).isEqualTo(UPDATED_BLOCK_REASON_CODE_9);
        assertThat(testBlockedAccounts.getBlockReasonCode10()).isEqualTo(UPDATED_BLOCK_REASON_CODE_10);
        assertThat(testBlockedAccounts.getBlockReasonCode11()).isEqualTo(UPDATED_BLOCK_REASON_CODE_11);
        assertThat(testBlockedAccounts.getBlockReasonCode12()).isEqualTo(UPDATED_BLOCK_REASON_CODE_12);
        assertThat(testBlockedAccounts.getBlockReasonCode13()).isEqualTo(UPDATED_BLOCK_REASON_CODE_13);
        assertThat(testBlockedAccounts.getBlockReasonCode14()).isEqualTo(UPDATED_BLOCK_REASON_CODE_14);
        assertThat(testBlockedAccounts.getBlockReasonCode15()).isEqualTo(UPDATED_BLOCK_REASON_CODE_15);
        assertThat(testBlockedAccounts.getBlockReasonCode16()).isEqualTo(UPDATED_BLOCK_REASON_CODE_16);
        assertThat(testBlockedAccounts.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBlockedAccounts.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBlockedAccounts.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBlockedAccounts.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBlockedAccounts.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBlockedAccounts.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, blockedAccounts.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamBlockedAccounts() throws Exception {
        int databaseSizeBeforeUpdate = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        blockedAccounts.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(blockedAccounts))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BlockedAccounts in the database
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteBlockedAccounts() {
        // Initialize the database
        blockedAccountsRepository.save(blockedAccounts).block();
        blockedAccountsRepository.save(blockedAccounts).block();
        blockedAccountsSearchRepository.save(blockedAccounts).block();

        int databaseSizeBeforeDelete = blockedAccountsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the blockedAccounts
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, blockedAccounts.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<BlockedAccounts> blockedAccountsList = blockedAccountsRepository.findAll().collectList().block();
        assertThat(blockedAccountsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(blockedAccountsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchBlockedAccounts() {
        // Initialize the database
        blockedAccounts = blockedAccountsRepository.save(blockedAccounts).block();
        blockedAccountsSearchRepository.save(blockedAccounts).block();

        // Search the blockedAccounts
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + blockedAccounts.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(blockedAccounts.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.[*].customerAccountNumber")
            .value(hasItem(DEFAULT_CUSTOMER_ACCOUNT_NUMBER))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].blockId")
            .value(hasItem(DEFAULT_BLOCK_ID))
            .jsonPath("$.[*].blockCode")
            .value(hasItem(DEFAULT_BLOCK_CODE))
            .jsonPath("$.[*].blockDate")
            .value(hasItem(sameInstant(DEFAULT_BLOCK_DATE)))
            .jsonPath("$.[*].blockType")
            .value(hasItem(DEFAULT_BLOCK_TYPE))
            .jsonPath("$.[*].blockStatus")
            .value(hasItem(DEFAULT_BLOCK_STATUS))
            .jsonPath("$.[*].blockReason")
            .value(hasItem(DEFAULT_BLOCK_REASON))
            .jsonPath("$.[*].blockReasonCode1")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_1))
            .jsonPath("$.[*].blockReasonCode2")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_2))
            .jsonPath("$.[*].blockReason1")
            .value(hasItem(DEFAULT_BLOCK_REASON_1))
            .jsonPath("$.[*].blockReasonCode3")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_3))
            .jsonPath("$.[*].blockReasonCode4")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_4))
            .jsonPath("$.[*].startDate")
            .value(hasItem(sameInstant(DEFAULT_START_DATE)))
            .jsonPath("$.[*].endDate")
            .value(hasItem(sameInstant(DEFAULT_END_DATE)))
            .jsonPath("$.[*].isTemp")
            .value(hasItem(DEFAULT_IS_TEMP.booleanValue()))
            .jsonPath("$.[*].blockFreeText")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT))
            .jsonPath("$.[*].blockFreeText1")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_1))
            .jsonPath("$.[*].blockFreeText2")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_2))
            .jsonPath("$.[*].blockFreeText3")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_3))
            .jsonPath("$.[*].blockFreeText4")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_4))
            .jsonPath("$.[*].blockFreeText5")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_5))
            .jsonPath("$.[*].blockFreeText6")
            .value(hasItem(DEFAULT_BLOCK_FREE_TEXT_6))
            .jsonPath("$.[*].blockReasonCode5")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_5))
            .jsonPath("$.[*].blockReasonCode6")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_6))
            .jsonPath("$.[*].blockReasonCode7")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_7))
            .jsonPath("$.[*].blockReasonCode8")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_8))
            .jsonPath("$.[*].blockReasonCode9")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_9))
            .jsonPath("$.[*].blockReasonCode10")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_10))
            .jsonPath("$.[*].blockReasonCode11")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_11))
            .jsonPath("$.[*].blockReasonCode12")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_12))
            .jsonPath("$.[*].blockReasonCode13")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_13))
            .jsonPath("$.[*].blockReasonCode14")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_14))
            .jsonPath("$.[*].blockReasonCode15")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_15))
            .jsonPath("$.[*].blockReasonCode16")
            .value(hasItem(DEFAULT_BLOCK_REASON_CODE_16))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()));
    }
}
