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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.IntegrationTest;
import ug.co.absa.microsrvc.gateway.domain.Liquidation;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.LiquidationRepository;
import ug.co.absa.microsrvc.gateway.repository.search.LiquidationSearchRepository;

/**
 * Integration tests for the {@link LiquidationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class LiquidationResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final ServiceLevel DEFAULT_SERVICE_LEVEL = ServiceLevel.N;
    private static final ServiceLevel UPDATED_SERVICE_LEVEL = ServiceLevel.Y;

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PARTNER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARTNER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_BANKCODE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_BANKCODE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_TO_RECEIVER_INFO = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_TO_RECEIVER_INFO = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/liquidations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/liquidations";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LiquidationRepository liquidationRepository;

    @Autowired
    private LiquidationSearchRepository liquidationSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Liquidation liquidation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Liquidation createEntity(EntityManager em) {
        Liquidation liquidation = new Liquidation()
            .recordId(DEFAULT_RECORD_ID)
            .serviceLevel(DEFAULT_SERVICE_LEVEL)
            .timestamp(DEFAULT_TIMESTAMP)
            .partnerCode(DEFAULT_PARTNER_CODE)
            .amount(DEFAULT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .receiverBankcode(DEFAULT_RECEIVER_BANKCODE)
            .receiverAccountNumber(DEFAULT_RECEIVER_ACCOUNT_NUMBER)
            .beneficiaryName(DEFAULT_BENEFICIARY_NAME)
            .instructionId(DEFAULT_INSTRUCTION_ID)
            .senderToReceiverInfo(DEFAULT_SENDER_TO_RECEIVER_INFO)
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
        return liquidation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Liquidation createUpdatedEntity(EntityManager em) {
        Liquidation liquidation = new Liquidation()
            .recordId(UPDATED_RECORD_ID)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .timestamp(UPDATED_TIMESTAMP)
            .partnerCode(UPDATED_PARTNER_CODE)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .receiverBankcode(UPDATED_RECEIVER_BANKCODE)
            .receiverAccountNumber(UPDATED_RECEIVER_ACCOUNT_NUMBER)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .instructionId(UPDATED_INSTRUCTION_ID)
            .senderToReceiverInfo(UPDATED_SENDER_TO_RECEIVER_INFO)
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
        return liquidation;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Liquidation.class).block();
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
        liquidationSearchRepository.deleteAll().block();
        assertThat(liquidationSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        liquidation = createEntity(em);
    }

    @Test
    void createLiquidation() throws Exception {
        int databaseSizeBeforeCreate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // Create the Liquidation
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Liquidation testLiquidation = liquidationList.get(liquidationList.size() - 1);
        assertThat(testLiquidation.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testLiquidation.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testLiquidation.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testLiquidation.getPartnerCode()).isEqualTo(DEFAULT_PARTNER_CODE);
        assertThat(testLiquidation.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testLiquidation.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testLiquidation.getReceiverBankcode()).isEqualTo(DEFAULT_RECEIVER_BANKCODE);
        assertThat(testLiquidation.getReceiverAccountNumber()).isEqualTo(DEFAULT_RECEIVER_ACCOUNT_NUMBER);
        assertThat(testLiquidation.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testLiquidation.getInstructionId()).isEqualTo(DEFAULT_INSTRUCTION_ID);
        assertThat(testLiquidation.getSenderToReceiverInfo()).isEqualTo(DEFAULT_SENDER_TO_RECEIVER_INFO);
        assertThat(testLiquidation.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testLiquidation.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testLiquidation.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testLiquidation.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testLiquidation.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testLiquidation.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testLiquidation.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testLiquidation.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testLiquidation.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testLiquidation.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testLiquidation.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testLiquidation.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testLiquidation.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testLiquidation.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testLiquidation.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testLiquidation.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testLiquidation.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testLiquidation.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testLiquidation.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testLiquidation.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testLiquidation.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testLiquidation.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testLiquidation.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testLiquidation.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testLiquidation.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testLiquidation.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testLiquidation.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testLiquidation.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testLiquidation.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLiquidation.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testLiquidation.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testLiquidation.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createLiquidationWithExistingId() throws Exception {
        // Create the Liquidation with an existing ID
        liquidation.setId(1L);

        int databaseSizeBeforeCreate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkServiceLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setServiceLevel(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setTimestamp(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPartnerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setPartnerCode(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setAmount(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setCurrency(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReceiverBankcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setReceiverBankcode(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReceiverAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setReceiverAccountNumber(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBeneficiaryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setBeneficiaryName(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkInstructionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setInstructionId(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkSenderToReceiverInfoIsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setSenderToReceiverInfo(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText1IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText1(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText2IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText2(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText3IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText3(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText4IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText4(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText5IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText5(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText6IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText6(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText7IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText7(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText8IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText8(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText9IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText9(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText10IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText10(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText11IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText11(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText12IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText12(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText13IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText13(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText14IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText14(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText15IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText15(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText16IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText16(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText17IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText17(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText18IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText18(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText19IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText19(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText20IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText20(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText21IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText21(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText22IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText22(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText23IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText23(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText24IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText24(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText25IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText25(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText26IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText26(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText27IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText27(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkFreeText28IsRequired() throws Exception {
        int databaseSizeBeforeTest = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        // set the field null
        liquidation.setFreeText28(null);

        // Create the Liquidation, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllLiquidationsAsStream() {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        List<Liquidation> liquidationList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Liquidation.class)
            .getResponseBody()
            .filter(liquidation::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(liquidationList).isNotNull();
        assertThat(liquidationList).hasSize(1);
        Liquidation testLiquidation = liquidationList.get(0);
        assertThat(testLiquidation.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testLiquidation.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testLiquidation.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testLiquidation.getPartnerCode()).isEqualTo(DEFAULT_PARTNER_CODE);
        assertThat(testLiquidation.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testLiquidation.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testLiquidation.getReceiverBankcode()).isEqualTo(DEFAULT_RECEIVER_BANKCODE);
        assertThat(testLiquidation.getReceiverAccountNumber()).isEqualTo(DEFAULT_RECEIVER_ACCOUNT_NUMBER);
        assertThat(testLiquidation.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testLiquidation.getInstructionId()).isEqualTo(DEFAULT_INSTRUCTION_ID);
        assertThat(testLiquidation.getSenderToReceiverInfo()).isEqualTo(DEFAULT_SENDER_TO_RECEIVER_INFO);
        assertThat(testLiquidation.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testLiquidation.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testLiquidation.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testLiquidation.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testLiquidation.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testLiquidation.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testLiquidation.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testLiquidation.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testLiquidation.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testLiquidation.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testLiquidation.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testLiquidation.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testLiquidation.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testLiquidation.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testLiquidation.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testLiquidation.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testLiquidation.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testLiquidation.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testLiquidation.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testLiquidation.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testLiquidation.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testLiquidation.getFreeText22()).isEqualTo(DEFAULT_FREE_TEXT_22);
        assertThat(testLiquidation.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testLiquidation.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testLiquidation.getFreeText25()).isEqualTo(DEFAULT_FREE_TEXT_25);
        assertThat(testLiquidation.getFreeText26()).isEqualTo(DEFAULT_FREE_TEXT_26);
        assertThat(testLiquidation.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testLiquidation.getFreeText28()).isEqualTo(DEFAULT_FREE_TEXT_28);
        assertThat(testLiquidation.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testLiquidation.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testLiquidation.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testLiquidation.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllLiquidations() {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        // Get all the liquidationList
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
            .value(hasItem(liquidation.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].serviceLevel")
            .value(hasItem(DEFAULT_SERVICE_LEVEL.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].partnerCode")
            .value(hasItem(DEFAULT_PARTNER_CODE))
            .jsonPath("$.[*].amount")
            .value(hasItem(DEFAULT_AMOUNT))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].receiverBankcode")
            .value(hasItem(DEFAULT_RECEIVER_BANKCODE))
            .jsonPath("$.[*].receiverAccountNumber")
            .value(hasItem(DEFAULT_RECEIVER_ACCOUNT_NUMBER))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].instructionId")
            .value(hasItem(DEFAULT_INSTRUCTION_ID))
            .jsonPath("$.[*].senderToReceiverInfo")
            .value(hasItem(DEFAULT_SENDER_TO_RECEIVER_INFO))
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
    void getLiquidation() {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        // Get the liquidation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, liquidation.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(liquidation.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.serviceLevel")
            .value(is(DEFAULT_SERVICE_LEVEL.toString()))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.partnerCode")
            .value(is(DEFAULT_PARTNER_CODE))
            .jsonPath("$.amount")
            .value(is(DEFAULT_AMOUNT))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.receiverBankcode")
            .value(is(DEFAULT_RECEIVER_BANKCODE))
            .jsonPath("$.receiverAccountNumber")
            .value(is(DEFAULT_RECEIVER_ACCOUNT_NUMBER))
            .jsonPath("$.beneficiaryName")
            .value(is(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.instructionId")
            .value(is(DEFAULT_INSTRUCTION_ID))
            .jsonPath("$.senderToReceiverInfo")
            .value(is(DEFAULT_SENDER_TO_RECEIVER_INFO))
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
    void getNonExistingLiquidation() {
        // Get the liquidation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingLiquidation() throws Exception {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        liquidationSearchRepository.save(liquidation).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());

        // Update the liquidation
        Liquidation updatedLiquidation = liquidationRepository.findById(liquidation.getId()).block();
        updatedLiquidation
            .recordId(UPDATED_RECORD_ID)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .timestamp(UPDATED_TIMESTAMP)
            .partnerCode(UPDATED_PARTNER_CODE)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .receiverBankcode(UPDATED_RECEIVER_BANKCODE)
            .receiverAccountNumber(UPDATED_RECEIVER_ACCOUNT_NUMBER)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .instructionId(UPDATED_INSTRUCTION_ID)
            .senderToReceiverInfo(UPDATED_SENDER_TO_RECEIVER_INFO)
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
            .uri(ENTITY_API_URL_ID, updatedLiquidation.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedLiquidation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        Liquidation testLiquidation = liquidationList.get(liquidationList.size() - 1);
        assertThat(testLiquidation.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testLiquidation.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testLiquidation.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testLiquidation.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testLiquidation.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testLiquidation.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testLiquidation.getReceiverBankcode()).isEqualTo(UPDATED_RECEIVER_BANKCODE);
        assertThat(testLiquidation.getReceiverAccountNumber()).isEqualTo(UPDATED_RECEIVER_ACCOUNT_NUMBER);
        assertThat(testLiquidation.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testLiquidation.getInstructionId()).isEqualTo(UPDATED_INSTRUCTION_ID);
        assertThat(testLiquidation.getSenderToReceiverInfo()).isEqualTo(UPDATED_SENDER_TO_RECEIVER_INFO);
        assertThat(testLiquidation.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testLiquidation.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testLiquidation.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testLiquidation.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testLiquidation.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testLiquidation.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testLiquidation.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testLiquidation.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testLiquidation.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testLiquidation.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testLiquidation.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testLiquidation.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testLiquidation.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testLiquidation.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testLiquidation.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testLiquidation.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testLiquidation.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testLiquidation.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testLiquidation.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testLiquidation.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testLiquidation.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testLiquidation.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testLiquidation.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testLiquidation.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testLiquidation.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testLiquidation.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testLiquidation.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testLiquidation.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testLiquidation.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLiquidation.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testLiquidation.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testLiquidation.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Liquidation> liquidationSearchList = IterableUtils.toList(liquidationSearchRepository.findAll().collectList().block());
                Liquidation testLiquidationSearch = liquidationSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testLiquidationSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testLiquidationSearch.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
                assertThat(testLiquidationSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testLiquidationSearch.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
                assertThat(testLiquidationSearch.getAmount()).isEqualTo(UPDATED_AMOUNT);
                assertThat(testLiquidationSearch.getCurrency()).isEqualTo(UPDATED_CURRENCY);
                assertThat(testLiquidationSearch.getReceiverBankcode()).isEqualTo(UPDATED_RECEIVER_BANKCODE);
                assertThat(testLiquidationSearch.getReceiverAccountNumber()).isEqualTo(UPDATED_RECEIVER_ACCOUNT_NUMBER);
                assertThat(testLiquidationSearch.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
                assertThat(testLiquidationSearch.getInstructionId()).isEqualTo(UPDATED_INSTRUCTION_ID);
                assertThat(testLiquidationSearch.getSenderToReceiverInfo()).isEqualTo(UPDATED_SENDER_TO_RECEIVER_INFO);
                assertThat(testLiquidationSearch.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
                assertThat(testLiquidationSearch.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
                assertThat(testLiquidationSearch.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
                assertThat(testLiquidationSearch.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
                assertThat(testLiquidationSearch.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
                assertThat(testLiquidationSearch.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
                assertThat(testLiquidationSearch.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
                assertThat(testLiquidationSearch.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
                assertThat(testLiquidationSearch.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
                assertThat(testLiquidationSearch.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
                assertThat(testLiquidationSearch.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
                assertThat(testLiquidationSearch.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
                assertThat(testLiquidationSearch.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
                assertThat(testLiquidationSearch.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
                assertThat(testLiquidationSearch.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
                assertThat(testLiquidationSearch.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
                assertThat(testLiquidationSearch.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
                assertThat(testLiquidationSearch.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
                assertThat(testLiquidationSearch.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
                assertThat(testLiquidationSearch.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
                assertThat(testLiquidationSearch.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
                assertThat(testLiquidationSearch.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
                assertThat(testLiquidationSearch.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
                assertThat(testLiquidationSearch.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
                assertThat(testLiquidationSearch.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
                assertThat(testLiquidationSearch.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
                assertThat(testLiquidationSearch.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
                assertThat(testLiquidationSearch.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
                assertThat(testLiquidationSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testLiquidationSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testLiquidationSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testLiquidationSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, liquidation.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateLiquidationWithPatch() throws Exception {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();

        // Update the liquidation using partial update
        Liquidation partialUpdatedLiquidation = new Liquidation();
        partialUpdatedLiquidation.setId(liquidation.getId());

        partialUpdatedLiquidation
            .recordId(UPDATED_RECORD_ID)
            .receiverBankcode(UPDATED_RECEIVER_BANKCODE)
            .receiverAccountNumber(UPDATED_RECEIVER_ACCOUNT_NUMBER)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .instructionId(UPDATED_INSTRUCTION_ID)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText4(UPDATED_FREE_TEXT_4)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText6(UPDATED_FREE_TEXT_6)
            .freeText8(UPDATED_FREE_TEXT_8)
            .freeText11(UPDATED_FREE_TEXT_11)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText18(UPDATED_FREE_TEXT_18)
            .freeText22(UPDATED_FREE_TEXT_22)
            .freeText25(UPDATED_FREE_TEXT_25)
            .freeText26(UPDATED_FREE_TEXT_26)
            .freeText28(UPDATED_FREE_TEXT_28)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLiquidation.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLiquidation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        Liquidation testLiquidation = liquidationList.get(liquidationList.size() - 1);
        assertThat(testLiquidation.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testLiquidation.getServiceLevel()).isEqualTo(DEFAULT_SERVICE_LEVEL);
        assertThat(testLiquidation.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testLiquidation.getPartnerCode()).isEqualTo(DEFAULT_PARTNER_CODE);
        assertThat(testLiquidation.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testLiquidation.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testLiquidation.getReceiverBankcode()).isEqualTo(UPDATED_RECEIVER_BANKCODE);
        assertThat(testLiquidation.getReceiverAccountNumber()).isEqualTo(UPDATED_RECEIVER_ACCOUNT_NUMBER);
        assertThat(testLiquidation.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testLiquidation.getInstructionId()).isEqualTo(UPDATED_INSTRUCTION_ID);
        assertThat(testLiquidation.getSenderToReceiverInfo()).isEqualTo(DEFAULT_SENDER_TO_RECEIVER_INFO);
        assertThat(testLiquidation.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testLiquidation.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testLiquidation.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testLiquidation.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testLiquidation.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testLiquidation.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testLiquidation.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testLiquidation.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testLiquidation.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testLiquidation.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testLiquidation.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testLiquidation.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testLiquidation.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testLiquidation.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testLiquidation.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testLiquidation.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testLiquidation.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testLiquidation.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testLiquidation.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testLiquidation.getFreeText20()).isEqualTo(DEFAULT_FREE_TEXT_20);
        assertThat(testLiquidation.getFreeText21()).isEqualTo(DEFAULT_FREE_TEXT_21);
        assertThat(testLiquidation.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testLiquidation.getFreeText23()).isEqualTo(DEFAULT_FREE_TEXT_23);
        assertThat(testLiquidation.getFreeText24()).isEqualTo(DEFAULT_FREE_TEXT_24);
        assertThat(testLiquidation.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testLiquidation.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testLiquidation.getFreeText27()).isEqualTo(DEFAULT_FREE_TEXT_27);
        assertThat(testLiquidation.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testLiquidation.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLiquidation.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testLiquidation.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testLiquidation.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateLiquidationWithPatch() throws Exception {
        // Initialize the database
        liquidationRepository.save(liquidation).block();

        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();

        // Update the liquidation using partial update
        Liquidation partialUpdatedLiquidation = new Liquidation();
        partialUpdatedLiquidation.setId(liquidation.getId());

        partialUpdatedLiquidation
            .recordId(UPDATED_RECORD_ID)
            .serviceLevel(UPDATED_SERVICE_LEVEL)
            .timestamp(UPDATED_TIMESTAMP)
            .partnerCode(UPDATED_PARTNER_CODE)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .receiverBankcode(UPDATED_RECEIVER_BANKCODE)
            .receiverAccountNumber(UPDATED_RECEIVER_ACCOUNT_NUMBER)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .instructionId(UPDATED_INSTRUCTION_ID)
            .senderToReceiverInfo(UPDATED_SENDER_TO_RECEIVER_INFO)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedLiquidation.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedLiquidation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        Liquidation testLiquidation = liquidationList.get(liquidationList.size() - 1);
        assertThat(testLiquidation.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testLiquidation.getServiceLevel()).isEqualTo(UPDATED_SERVICE_LEVEL);
        assertThat(testLiquidation.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testLiquidation.getPartnerCode()).isEqualTo(UPDATED_PARTNER_CODE);
        assertThat(testLiquidation.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testLiquidation.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testLiquidation.getReceiverBankcode()).isEqualTo(UPDATED_RECEIVER_BANKCODE);
        assertThat(testLiquidation.getReceiverAccountNumber()).isEqualTo(UPDATED_RECEIVER_ACCOUNT_NUMBER);
        assertThat(testLiquidation.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testLiquidation.getInstructionId()).isEqualTo(UPDATED_INSTRUCTION_ID);
        assertThat(testLiquidation.getSenderToReceiverInfo()).isEqualTo(UPDATED_SENDER_TO_RECEIVER_INFO);
        assertThat(testLiquidation.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testLiquidation.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testLiquidation.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testLiquidation.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testLiquidation.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testLiquidation.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testLiquidation.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testLiquidation.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testLiquidation.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testLiquidation.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testLiquidation.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testLiquidation.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testLiquidation.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testLiquidation.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testLiquidation.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testLiquidation.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testLiquidation.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testLiquidation.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testLiquidation.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testLiquidation.getFreeText20()).isEqualTo(UPDATED_FREE_TEXT_20);
        assertThat(testLiquidation.getFreeText21()).isEqualTo(UPDATED_FREE_TEXT_21);
        assertThat(testLiquidation.getFreeText22()).isEqualTo(UPDATED_FREE_TEXT_22);
        assertThat(testLiquidation.getFreeText23()).isEqualTo(UPDATED_FREE_TEXT_23);
        assertThat(testLiquidation.getFreeText24()).isEqualTo(UPDATED_FREE_TEXT_24);
        assertThat(testLiquidation.getFreeText25()).isEqualTo(UPDATED_FREE_TEXT_25);
        assertThat(testLiquidation.getFreeText26()).isEqualTo(UPDATED_FREE_TEXT_26);
        assertThat(testLiquidation.getFreeText27()).isEqualTo(UPDATED_FREE_TEXT_27);
        assertThat(testLiquidation.getFreeText28()).isEqualTo(UPDATED_FREE_TEXT_28);
        assertThat(testLiquidation.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testLiquidation.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testLiquidation.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testLiquidation.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, liquidation.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamLiquidation() throws Exception {
        int databaseSizeBeforeUpdate = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        liquidation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(liquidation))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Liquidation in the database
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteLiquidation() {
        // Initialize the database
        liquidationRepository.save(liquidation).block();
        liquidationRepository.save(liquidation).block();
        liquidationSearchRepository.save(liquidation).block();

        int databaseSizeBeforeDelete = liquidationRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the liquidation
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, liquidation.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Liquidation> liquidationList = liquidationRepository.findAll().collectList().block();
        assertThat(liquidationList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(liquidationSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchLiquidation() {
        // Initialize the database
        liquidation = liquidationRepository.save(liquidation).block();
        liquidationSearchRepository.save(liquidation).block();

        // Search the liquidation
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + liquidation.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(liquidation.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].serviceLevel")
            .value(hasItem(DEFAULT_SERVICE_LEVEL.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].partnerCode")
            .value(hasItem(DEFAULT_PARTNER_CODE))
            .jsonPath("$.[*].amount")
            .value(hasItem(DEFAULT_AMOUNT))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].receiverBankcode")
            .value(hasItem(DEFAULT_RECEIVER_BANKCODE))
            .jsonPath("$.[*].receiverAccountNumber")
            .value(hasItem(DEFAULT_RECEIVER_ACCOUNT_NUMBER))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].instructionId")
            .value(hasItem(DEFAULT_INSTRUCTION_ID))
            .jsonPath("$.[*].senderToReceiverInfo")
            .value(hasItem(DEFAULT_SENDER_TO_RECEIVER_INFO))
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
