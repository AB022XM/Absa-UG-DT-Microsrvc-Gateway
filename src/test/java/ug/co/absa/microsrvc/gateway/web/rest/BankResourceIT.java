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
import ug.co.absa.microsrvc.gateway.domain.Bank;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.BankRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.BankSearchRepository;

/**
 * Integration tests for the {@link BankResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BankResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AMOL_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_AMOL_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BANK_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BANK_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BANK_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_18 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_18 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_20 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_20 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_23 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_23 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_FREE_FIELD_24 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_FREE_FIELD_24 = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/banks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/banks";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankSearchRepository bankSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Bank bank;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bank createEntity(EntityManager em) {
        Bank bank = new Bank()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .bankName(DEFAULT_BANK_NAME)
            .bankSwiftCode(DEFAULT_BANK_SWIFT_CODE)
            .bankBranchId(DEFAULT_BANK_BRANCH_ID)
            .bankPhoneNumber(DEFAULT_BANK_PHONE_NUMBER)
            .bankEmail(DEFAULT_BANK_EMAIL)
            .bankFreeField1(DEFAULT_BANK_FREE_FIELD_1)
            .bankFreeField3(DEFAULT_BANK_FREE_FIELD_3)
            .bankFreeField4(DEFAULT_BANK_FREE_FIELD_4)
            .bankFreeField5(DEFAULT_BANK_FREE_FIELD_5)
            .bankFreeField6(DEFAULT_BANK_FREE_FIELD_6)
            .bankFreeField7(DEFAULT_BANK_FREE_FIELD_7)
            .bankFreeField8(DEFAULT_BANK_FREE_FIELD_8)
            .bankFreeField9(DEFAULT_BANK_FREE_FIELD_9)
            .bankFreeField10(DEFAULT_BANK_FREE_FIELD_10)
            .bankFreeField11(DEFAULT_BANK_FREE_FIELD_11)
            .bankFreeField12(DEFAULT_BANK_FREE_FIELD_12)
            .bankFreeField13(DEFAULT_BANK_FREE_FIELD_13)
            .bankFreeField14(DEFAULT_BANK_FREE_FIELD_14)
            .bankFreeField15(DEFAULT_BANK_FREE_FIELD_15)
            .bankFreeField16(DEFAULT_BANK_FREE_FIELD_16)
            .bankFreeField17(DEFAULT_BANK_FREE_FIELD_17)
            .bankFreeField18(DEFAULT_BANK_FREE_FIELD_18)
            .bankFreeField19(DEFAULT_BANK_FREE_FIELD_19)
            .bankFreeField20(DEFAULT_BANK_FREE_FIELD_20)
            .bankFreeField21(DEFAULT_BANK_FREE_FIELD_21)
            .bankFreeField22(DEFAULT_BANK_FREE_FIELD_22)
            .bankFreeField23(DEFAULT_BANK_FREE_FIELD_23)
            .bankFreeField24(DEFAULT_BANK_FREE_FIELD_24)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .delflg(DEFAULT_DELFLG)
            .status(DEFAULT_STATUS);
        return bank;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bank createUpdatedEntity(EntityManager em) {
        Bank bank = new Bank()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .bankName(UPDATED_BANK_NAME)
            .bankSwiftCode(UPDATED_BANK_SWIFT_CODE)
            .bankBranchId(UPDATED_BANK_BRANCH_ID)
            .bankPhoneNumber(UPDATED_BANK_PHONE_NUMBER)
            .bankEmail(UPDATED_BANK_EMAIL)
            .bankFreeField1(UPDATED_BANK_FREE_FIELD_1)
            .bankFreeField3(UPDATED_BANK_FREE_FIELD_3)
            .bankFreeField4(UPDATED_BANK_FREE_FIELD_4)
            .bankFreeField5(UPDATED_BANK_FREE_FIELD_5)
            .bankFreeField6(UPDATED_BANK_FREE_FIELD_6)
            .bankFreeField7(UPDATED_BANK_FREE_FIELD_7)
            .bankFreeField8(UPDATED_BANK_FREE_FIELD_8)
            .bankFreeField9(UPDATED_BANK_FREE_FIELD_9)
            .bankFreeField10(UPDATED_BANK_FREE_FIELD_10)
            .bankFreeField11(UPDATED_BANK_FREE_FIELD_11)
            .bankFreeField12(UPDATED_BANK_FREE_FIELD_12)
            .bankFreeField13(UPDATED_BANK_FREE_FIELD_13)
            .bankFreeField14(UPDATED_BANK_FREE_FIELD_14)
            .bankFreeField15(UPDATED_BANK_FREE_FIELD_15)
            .bankFreeField16(UPDATED_BANK_FREE_FIELD_16)
            .bankFreeField17(UPDATED_BANK_FREE_FIELD_17)
            .bankFreeField18(UPDATED_BANK_FREE_FIELD_18)
            .bankFreeField19(UPDATED_BANK_FREE_FIELD_19)
            .bankFreeField20(UPDATED_BANK_FREE_FIELD_20)
            .bankFreeField21(UPDATED_BANK_FREE_FIELD_21)
            .bankFreeField22(UPDATED_BANK_FREE_FIELD_22)
            .bankFreeField23(UPDATED_BANK_FREE_FIELD_23)
            .bankFreeField24(UPDATED_BANK_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);
        return bank;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Bank.class).block();
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
        bankSearchRepository.deleteAll().block();
        assertThat(bankSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        bank = createEntity(em);
    }

    @Test
    void createBank() throws Exception {
        int databaseSizeBeforeCreate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // Create the Bank
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBank.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBank.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testBank.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testBank.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testBank.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBank.getBankSwiftCode()).isEqualTo(DEFAULT_BANK_SWIFT_CODE);
        assertThat(testBank.getBankBranchId()).isEqualTo(DEFAULT_BANK_BRANCH_ID);
        assertThat(testBank.getBankPhoneNumber()).isEqualTo(DEFAULT_BANK_PHONE_NUMBER);
        assertThat(testBank.getBankEmail()).isEqualTo(DEFAULT_BANK_EMAIL);
        assertThat(testBank.getBankFreeField1()).isEqualTo(DEFAULT_BANK_FREE_FIELD_1);
        assertThat(testBank.getBankFreeField3()).isEqualTo(DEFAULT_BANK_FREE_FIELD_3);
        assertThat(testBank.getBankFreeField4()).isEqualTo(DEFAULT_BANK_FREE_FIELD_4);
        assertThat(testBank.getBankFreeField5()).isEqualTo(DEFAULT_BANK_FREE_FIELD_5);
        assertThat(testBank.getBankFreeField6()).isEqualTo(DEFAULT_BANK_FREE_FIELD_6);
        assertThat(testBank.getBankFreeField7()).isEqualTo(DEFAULT_BANK_FREE_FIELD_7);
        assertThat(testBank.getBankFreeField8()).isEqualTo(DEFAULT_BANK_FREE_FIELD_8);
        assertThat(testBank.getBankFreeField9()).isEqualTo(DEFAULT_BANK_FREE_FIELD_9);
        assertThat(testBank.getBankFreeField10()).isEqualTo(DEFAULT_BANK_FREE_FIELD_10);
        assertThat(testBank.getBankFreeField11()).isEqualTo(DEFAULT_BANK_FREE_FIELD_11);
        assertThat(testBank.getBankFreeField12()).isEqualTo(DEFAULT_BANK_FREE_FIELD_12);
        assertThat(testBank.getBankFreeField13()).isEqualTo(DEFAULT_BANK_FREE_FIELD_13);
        assertThat(testBank.getBankFreeField14()).isEqualTo(DEFAULT_BANK_FREE_FIELD_14);
        assertThat(testBank.getBankFreeField15()).isEqualTo(DEFAULT_BANK_FREE_FIELD_15);
        assertThat(testBank.getBankFreeField16()).isEqualTo(DEFAULT_BANK_FREE_FIELD_16);
        assertThat(testBank.getBankFreeField17()).isEqualTo(DEFAULT_BANK_FREE_FIELD_17);
        assertThat(testBank.getBankFreeField18()).isEqualTo(DEFAULT_BANK_FREE_FIELD_18);
        assertThat(testBank.getBankFreeField19()).isEqualTo(DEFAULT_BANK_FREE_FIELD_19);
        assertThat(testBank.getBankFreeField20()).isEqualTo(DEFAULT_BANK_FREE_FIELD_20);
        assertThat(testBank.getBankFreeField21()).isEqualTo(DEFAULT_BANK_FREE_FIELD_21);
        assertThat(testBank.getBankFreeField22()).isEqualTo(DEFAULT_BANK_FREE_FIELD_22);
        assertThat(testBank.getBankFreeField23()).isEqualTo(DEFAULT_BANK_FREE_FIELD_23);
        assertThat(testBank.getBankFreeField24()).isEqualTo(DEFAULT_BANK_FREE_FIELD_24);
        assertThat(testBank.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBank.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBank.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBank.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBank.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBank.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createBankWithExistingId() throws Exception {
        // Create the Bank with an existing ID
        bank.setId(1L);

        int databaseSizeBeforeCreate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // set the field null
        bank.setBillerId(null);

        // Create the Bank, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // set the field null
        bank.setPaymentItemCode(null);

        // Create the Bank, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBankNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // set the field null
        bank.setBankName(null);

        // Create the Bank, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBankSwiftCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // set the field null
        bank.setBankSwiftCode(null);

        // Create the Bank, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        // set the field null
        bank.setCreatedAt(null);

        // Create the Bank, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllBanksAsStream() {
        // Initialize the database
        bankRepository.save(bank).block();

        List<Bank> bankList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Bank.class)
            .getResponseBody()
            .filter(bank::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(bankList).isNotNull();
        assertThat(bankList).hasSize(1);
        Bank testBank = bankList.get(0);
        assertThat(testBank.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBank.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBank.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testBank.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testBank.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testBank.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBank.getBankSwiftCode()).isEqualTo(DEFAULT_BANK_SWIFT_CODE);
        assertThat(testBank.getBankBranchId()).isEqualTo(DEFAULT_BANK_BRANCH_ID);
        assertThat(testBank.getBankPhoneNumber()).isEqualTo(DEFAULT_BANK_PHONE_NUMBER);
        assertThat(testBank.getBankEmail()).isEqualTo(DEFAULT_BANK_EMAIL);
        assertThat(testBank.getBankFreeField1()).isEqualTo(DEFAULT_BANK_FREE_FIELD_1);
        assertThat(testBank.getBankFreeField3()).isEqualTo(DEFAULT_BANK_FREE_FIELD_3);
        assertThat(testBank.getBankFreeField4()).isEqualTo(DEFAULT_BANK_FREE_FIELD_4);
        assertThat(testBank.getBankFreeField5()).isEqualTo(DEFAULT_BANK_FREE_FIELD_5);
        assertThat(testBank.getBankFreeField6()).isEqualTo(DEFAULT_BANK_FREE_FIELD_6);
        assertThat(testBank.getBankFreeField7()).isEqualTo(DEFAULT_BANK_FREE_FIELD_7);
        assertThat(testBank.getBankFreeField8()).isEqualTo(DEFAULT_BANK_FREE_FIELD_8);
        assertThat(testBank.getBankFreeField9()).isEqualTo(DEFAULT_BANK_FREE_FIELD_9);
        assertThat(testBank.getBankFreeField10()).isEqualTo(DEFAULT_BANK_FREE_FIELD_10);
        assertThat(testBank.getBankFreeField11()).isEqualTo(DEFAULT_BANK_FREE_FIELD_11);
        assertThat(testBank.getBankFreeField12()).isEqualTo(DEFAULT_BANK_FREE_FIELD_12);
        assertThat(testBank.getBankFreeField13()).isEqualTo(DEFAULT_BANK_FREE_FIELD_13);
        assertThat(testBank.getBankFreeField14()).isEqualTo(DEFAULT_BANK_FREE_FIELD_14);
        assertThat(testBank.getBankFreeField15()).isEqualTo(DEFAULT_BANK_FREE_FIELD_15);
        assertThat(testBank.getBankFreeField16()).isEqualTo(DEFAULT_BANK_FREE_FIELD_16);
        assertThat(testBank.getBankFreeField17()).isEqualTo(DEFAULT_BANK_FREE_FIELD_17);
        assertThat(testBank.getBankFreeField18()).isEqualTo(DEFAULT_BANK_FREE_FIELD_18);
        assertThat(testBank.getBankFreeField19()).isEqualTo(DEFAULT_BANK_FREE_FIELD_19);
        assertThat(testBank.getBankFreeField20()).isEqualTo(DEFAULT_BANK_FREE_FIELD_20);
        assertThat(testBank.getBankFreeField21()).isEqualTo(DEFAULT_BANK_FREE_FIELD_21);
        assertThat(testBank.getBankFreeField22()).isEqualTo(DEFAULT_BANK_FREE_FIELD_22);
        assertThat(testBank.getBankFreeField23()).isEqualTo(DEFAULT_BANK_FREE_FIELD_23);
        assertThat(testBank.getBankFreeField24()).isEqualTo(DEFAULT_BANK_FREE_FIELD_24);
        assertThat(testBank.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBank.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBank.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBank.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBank.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testBank.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void getAllBanks() {
        // Initialize the database
        bankRepository.save(bank).block();

        // Get all the bankList
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
            .value(hasItem(bank.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].bankName")
            .value(hasItem(DEFAULT_BANK_NAME))
            .jsonPath("$.[*].bankSwiftCode")
            .value(hasItem(DEFAULT_BANK_SWIFT_CODE))
            .jsonPath("$.[*].bankBranchId")
            .value(hasItem(DEFAULT_BANK_BRANCH_ID))
            .jsonPath("$.[*].bankPhoneNumber")
            .value(hasItem(DEFAULT_BANK_PHONE_NUMBER))
            .jsonPath("$.[*].bankEmail")
            .value(hasItem(DEFAULT_BANK_EMAIL))
            .jsonPath("$.[*].bankFreeField1")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_1))
            .jsonPath("$.[*].bankFreeField3")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_3))
            .jsonPath("$.[*].bankFreeField4")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_4))
            .jsonPath("$.[*].bankFreeField5")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_5))
            .jsonPath("$.[*].bankFreeField6")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_6))
            .jsonPath("$.[*].bankFreeField7")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_7))
            .jsonPath("$.[*].bankFreeField8")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_8))
            .jsonPath("$.[*].bankFreeField9")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_9))
            .jsonPath("$.[*].bankFreeField10")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_10))
            .jsonPath("$.[*].bankFreeField11")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_11))
            .jsonPath("$.[*].bankFreeField12")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_12))
            .jsonPath("$.[*].bankFreeField13")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_13))
            .jsonPath("$.[*].bankFreeField14")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_14))
            .jsonPath("$.[*].bankFreeField15")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_15))
            .jsonPath("$.[*].bankFreeField16")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_16))
            .jsonPath("$.[*].bankFreeField17")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_17))
            .jsonPath("$.[*].bankFreeField18")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_18))
            .jsonPath("$.[*].bankFreeField19")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_19))
            .jsonPath("$.[*].bankFreeField20")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_20))
            .jsonPath("$.[*].bankFreeField21")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_21))
            .jsonPath("$.[*].bankFreeField22")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_22))
            .jsonPath("$.[*].bankFreeField23")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_23))
            .jsonPath("$.[*].bankFreeField24")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_24))
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
    void getBank() {
        // Initialize the database
        bankRepository.save(bank).block();

        // Get the bank
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, bank.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(bank.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.paymentItemCode")
            .value(is(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.amolDTransactionId")
            .value(is(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.bankName")
            .value(is(DEFAULT_BANK_NAME))
            .jsonPath("$.bankSwiftCode")
            .value(is(DEFAULT_BANK_SWIFT_CODE))
            .jsonPath("$.bankBranchId")
            .value(is(DEFAULT_BANK_BRANCH_ID))
            .jsonPath("$.bankPhoneNumber")
            .value(is(DEFAULT_BANK_PHONE_NUMBER))
            .jsonPath("$.bankEmail")
            .value(is(DEFAULT_BANK_EMAIL))
            .jsonPath("$.bankFreeField1")
            .value(is(DEFAULT_BANK_FREE_FIELD_1))
            .jsonPath("$.bankFreeField3")
            .value(is(DEFAULT_BANK_FREE_FIELD_3))
            .jsonPath("$.bankFreeField4")
            .value(is(DEFAULT_BANK_FREE_FIELD_4))
            .jsonPath("$.bankFreeField5")
            .value(is(DEFAULT_BANK_FREE_FIELD_5))
            .jsonPath("$.bankFreeField6")
            .value(is(DEFAULT_BANK_FREE_FIELD_6))
            .jsonPath("$.bankFreeField7")
            .value(is(DEFAULT_BANK_FREE_FIELD_7))
            .jsonPath("$.bankFreeField8")
            .value(is(DEFAULT_BANK_FREE_FIELD_8))
            .jsonPath("$.bankFreeField9")
            .value(is(DEFAULT_BANK_FREE_FIELD_9))
            .jsonPath("$.bankFreeField10")
            .value(is(DEFAULT_BANK_FREE_FIELD_10))
            .jsonPath("$.bankFreeField11")
            .value(is(DEFAULT_BANK_FREE_FIELD_11))
            .jsonPath("$.bankFreeField12")
            .value(is(DEFAULT_BANK_FREE_FIELD_12))
            .jsonPath("$.bankFreeField13")
            .value(is(DEFAULT_BANK_FREE_FIELD_13))
            .jsonPath("$.bankFreeField14")
            .value(is(DEFAULT_BANK_FREE_FIELD_14))
            .jsonPath("$.bankFreeField15")
            .value(is(DEFAULT_BANK_FREE_FIELD_15))
            .jsonPath("$.bankFreeField16")
            .value(is(DEFAULT_BANK_FREE_FIELD_16))
            .jsonPath("$.bankFreeField17")
            .value(is(DEFAULT_BANK_FREE_FIELD_17))
            .jsonPath("$.bankFreeField18")
            .value(is(DEFAULT_BANK_FREE_FIELD_18))
            .jsonPath("$.bankFreeField19")
            .value(is(DEFAULT_BANK_FREE_FIELD_19))
            .jsonPath("$.bankFreeField20")
            .value(is(DEFAULT_BANK_FREE_FIELD_20))
            .jsonPath("$.bankFreeField21")
            .value(is(DEFAULT_BANK_FREE_FIELD_21))
            .jsonPath("$.bankFreeField22")
            .value(is(DEFAULT_BANK_FREE_FIELD_22))
            .jsonPath("$.bankFreeField23")
            .value(is(DEFAULT_BANK_FREE_FIELD_23))
            .jsonPath("$.bankFreeField24")
            .value(is(DEFAULT_BANK_FREE_FIELD_24))
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
    void getNonExistingBank() {
        // Get the bank
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBank() throws Exception {
        // Initialize the database
        bankRepository.save(bank).block();

        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        bankSearchRepository.save(bank).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());

        // Update the bank
        Bank updatedBank = bankRepository.findById(bank.getId()).block();
        updatedBank
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .bankName(UPDATED_BANK_NAME)
            .bankSwiftCode(UPDATED_BANK_SWIFT_CODE)
            .bankBranchId(UPDATED_BANK_BRANCH_ID)
            .bankPhoneNumber(UPDATED_BANK_PHONE_NUMBER)
            .bankEmail(UPDATED_BANK_EMAIL)
            .bankFreeField1(UPDATED_BANK_FREE_FIELD_1)
            .bankFreeField3(UPDATED_BANK_FREE_FIELD_3)
            .bankFreeField4(UPDATED_BANK_FREE_FIELD_4)
            .bankFreeField5(UPDATED_BANK_FREE_FIELD_5)
            .bankFreeField6(UPDATED_BANK_FREE_FIELD_6)
            .bankFreeField7(UPDATED_BANK_FREE_FIELD_7)
            .bankFreeField8(UPDATED_BANK_FREE_FIELD_8)
            .bankFreeField9(UPDATED_BANK_FREE_FIELD_9)
            .bankFreeField10(UPDATED_BANK_FREE_FIELD_10)
            .bankFreeField11(UPDATED_BANK_FREE_FIELD_11)
            .bankFreeField12(UPDATED_BANK_FREE_FIELD_12)
            .bankFreeField13(UPDATED_BANK_FREE_FIELD_13)
            .bankFreeField14(UPDATED_BANK_FREE_FIELD_14)
            .bankFreeField15(UPDATED_BANK_FREE_FIELD_15)
            .bankFreeField16(UPDATED_BANK_FREE_FIELD_16)
            .bankFreeField17(UPDATED_BANK_FREE_FIELD_17)
            .bankFreeField18(UPDATED_BANK_FREE_FIELD_18)
            .bankFreeField19(UPDATED_BANK_FREE_FIELD_19)
            .bankFreeField20(UPDATED_BANK_FREE_FIELD_20)
            .bankFreeField21(UPDATED_BANK_FREE_FIELD_21)
            .bankFreeField22(UPDATED_BANK_FREE_FIELD_22)
            .bankFreeField23(UPDATED_BANK_FREE_FIELD_23)
            .bankFreeField24(UPDATED_BANK_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBank.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBank))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBank.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBank.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testBank.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testBank.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testBank.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBank.getBankSwiftCode()).isEqualTo(UPDATED_BANK_SWIFT_CODE);
        assertThat(testBank.getBankBranchId()).isEqualTo(UPDATED_BANK_BRANCH_ID);
        assertThat(testBank.getBankPhoneNumber()).isEqualTo(UPDATED_BANK_PHONE_NUMBER);
        assertThat(testBank.getBankEmail()).isEqualTo(UPDATED_BANK_EMAIL);
        assertThat(testBank.getBankFreeField1()).isEqualTo(UPDATED_BANK_FREE_FIELD_1);
        assertThat(testBank.getBankFreeField3()).isEqualTo(UPDATED_BANK_FREE_FIELD_3);
        assertThat(testBank.getBankFreeField4()).isEqualTo(UPDATED_BANK_FREE_FIELD_4);
        assertThat(testBank.getBankFreeField5()).isEqualTo(UPDATED_BANK_FREE_FIELD_5);
        assertThat(testBank.getBankFreeField6()).isEqualTo(UPDATED_BANK_FREE_FIELD_6);
        assertThat(testBank.getBankFreeField7()).isEqualTo(UPDATED_BANK_FREE_FIELD_7);
        assertThat(testBank.getBankFreeField8()).isEqualTo(UPDATED_BANK_FREE_FIELD_8);
        assertThat(testBank.getBankFreeField9()).isEqualTo(UPDATED_BANK_FREE_FIELD_9);
        assertThat(testBank.getBankFreeField10()).isEqualTo(UPDATED_BANK_FREE_FIELD_10);
        assertThat(testBank.getBankFreeField11()).isEqualTo(UPDATED_BANK_FREE_FIELD_11);
        assertThat(testBank.getBankFreeField12()).isEqualTo(UPDATED_BANK_FREE_FIELD_12);
        assertThat(testBank.getBankFreeField13()).isEqualTo(UPDATED_BANK_FREE_FIELD_13);
        assertThat(testBank.getBankFreeField14()).isEqualTo(UPDATED_BANK_FREE_FIELD_14);
        assertThat(testBank.getBankFreeField15()).isEqualTo(UPDATED_BANK_FREE_FIELD_15);
        assertThat(testBank.getBankFreeField16()).isEqualTo(UPDATED_BANK_FREE_FIELD_16);
        assertThat(testBank.getBankFreeField17()).isEqualTo(UPDATED_BANK_FREE_FIELD_17);
        assertThat(testBank.getBankFreeField18()).isEqualTo(UPDATED_BANK_FREE_FIELD_18);
        assertThat(testBank.getBankFreeField19()).isEqualTo(UPDATED_BANK_FREE_FIELD_19);
        assertThat(testBank.getBankFreeField20()).isEqualTo(UPDATED_BANK_FREE_FIELD_20);
        assertThat(testBank.getBankFreeField21()).isEqualTo(UPDATED_BANK_FREE_FIELD_21);
        assertThat(testBank.getBankFreeField22()).isEqualTo(UPDATED_BANK_FREE_FIELD_22);
        assertThat(testBank.getBankFreeField23()).isEqualTo(UPDATED_BANK_FREE_FIELD_23);
        assertThat(testBank.getBankFreeField24()).isEqualTo(UPDATED_BANK_FREE_FIELD_24);
        assertThat(testBank.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBank.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBank.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBank.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBank.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBank.getStatus()).isEqualTo(UPDATED_STATUS);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Bank> bankSearchList = IterableUtils.toList(bankSearchRepository.findAll().collectList().block());
                Bank testBankSearch = bankSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testBankSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testBankSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testBankSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testBankSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testBankSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testBankSearch.getBankName()).isEqualTo(UPDATED_BANK_NAME);
                assertThat(testBankSearch.getBankSwiftCode()).isEqualTo(UPDATED_BANK_SWIFT_CODE);
                assertThat(testBankSearch.getBankBranchId()).isEqualTo(UPDATED_BANK_BRANCH_ID);
                assertThat(testBankSearch.getBankPhoneNumber()).isEqualTo(UPDATED_BANK_PHONE_NUMBER);
                assertThat(testBankSearch.getBankEmail()).isEqualTo(UPDATED_BANK_EMAIL);
                assertThat(testBankSearch.getBankFreeField1()).isEqualTo(UPDATED_BANK_FREE_FIELD_1);
                assertThat(testBankSearch.getBankFreeField3()).isEqualTo(UPDATED_BANK_FREE_FIELD_3);
                assertThat(testBankSearch.getBankFreeField4()).isEqualTo(UPDATED_BANK_FREE_FIELD_4);
                assertThat(testBankSearch.getBankFreeField5()).isEqualTo(UPDATED_BANK_FREE_FIELD_5);
                assertThat(testBankSearch.getBankFreeField6()).isEqualTo(UPDATED_BANK_FREE_FIELD_6);
                assertThat(testBankSearch.getBankFreeField7()).isEqualTo(UPDATED_BANK_FREE_FIELD_7);
                assertThat(testBankSearch.getBankFreeField8()).isEqualTo(UPDATED_BANK_FREE_FIELD_8);
                assertThat(testBankSearch.getBankFreeField9()).isEqualTo(UPDATED_BANK_FREE_FIELD_9);
                assertThat(testBankSearch.getBankFreeField10()).isEqualTo(UPDATED_BANK_FREE_FIELD_10);
                assertThat(testBankSearch.getBankFreeField11()).isEqualTo(UPDATED_BANK_FREE_FIELD_11);
                assertThat(testBankSearch.getBankFreeField12()).isEqualTo(UPDATED_BANK_FREE_FIELD_12);
                assertThat(testBankSearch.getBankFreeField13()).isEqualTo(UPDATED_BANK_FREE_FIELD_13);
                assertThat(testBankSearch.getBankFreeField14()).isEqualTo(UPDATED_BANK_FREE_FIELD_14);
                assertThat(testBankSearch.getBankFreeField15()).isEqualTo(UPDATED_BANK_FREE_FIELD_15);
                assertThat(testBankSearch.getBankFreeField16()).isEqualTo(UPDATED_BANK_FREE_FIELD_16);
                assertThat(testBankSearch.getBankFreeField17()).isEqualTo(UPDATED_BANK_FREE_FIELD_17);
                assertThat(testBankSearch.getBankFreeField18()).isEqualTo(UPDATED_BANK_FREE_FIELD_18);
                assertThat(testBankSearch.getBankFreeField19()).isEqualTo(UPDATED_BANK_FREE_FIELD_19);
                assertThat(testBankSearch.getBankFreeField20()).isEqualTo(UPDATED_BANK_FREE_FIELD_20);
                assertThat(testBankSearch.getBankFreeField21()).isEqualTo(UPDATED_BANK_FREE_FIELD_21);
                assertThat(testBankSearch.getBankFreeField22()).isEqualTo(UPDATED_BANK_FREE_FIELD_22);
                assertThat(testBankSearch.getBankFreeField23()).isEqualTo(UPDATED_BANK_FREE_FIELD_23);
                assertThat(testBankSearch.getBankFreeField24()).isEqualTo(UPDATED_BANK_FREE_FIELD_24);
                assertThat(testBankSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testBankSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testBankSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testBankSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
                assertThat(testBankSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testBankSearch.getStatus()).isEqualTo(UPDATED_STATUS);
            });
    }

    @Test
    void putNonExistingBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, bank.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateBankWithPatch() throws Exception {
        // Initialize the database
        bankRepository.save(bank).block();

        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();

        // Update the bank using partial update
        Bank partialUpdatedBank = new Bank();
        partialUpdatedBank.setId(bank.getId());

        partialUpdatedBank
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .bankSwiftCode(UPDATED_BANK_SWIFT_CODE)
            .bankPhoneNumber(UPDATED_BANK_PHONE_NUMBER)
            .bankEmail(UPDATED_BANK_EMAIL)
            .bankFreeField3(UPDATED_BANK_FREE_FIELD_3)
            .bankFreeField7(UPDATED_BANK_FREE_FIELD_7)
            .bankFreeField8(UPDATED_BANK_FREE_FIELD_8)
            .bankFreeField10(UPDATED_BANK_FREE_FIELD_10)
            .bankFreeField11(UPDATED_BANK_FREE_FIELD_11)
            .bankFreeField12(UPDATED_BANK_FREE_FIELD_12)
            .bankFreeField15(UPDATED_BANK_FREE_FIELD_15)
            .bankFreeField17(UPDATED_BANK_FREE_FIELD_17)
            .bankFreeField19(UPDATED_BANK_FREE_FIELD_19)
            .bankFreeField22(UPDATED_BANK_FREE_FIELD_22)
            .bankFreeField23(UPDATED_BANK_FREE_FIELD_23)
            .bankFreeField24(UPDATED_BANK_FREE_FIELD_24)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBank.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBank))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBank.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBank.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testBank.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testBank.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testBank.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBank.getBankSwiftCode()).isEqualTo(UPDATED_BANK_SWIFT_CODE);
        assertThat(testBank.getBankBranchId()).isEqualTo(DEFAULT_BANK_BRANCH_ID);
        assertThat(testBank.getBankPhoneNumber()).isEqualTo(UPDATED_BANK_PHONE_NUMBER);
        assertThat(testBank.getBankEmail()).isEqualTo(UPDATED_BANK_EMAIL);
        assertThat(testBank.getBankFreeField1()).isEqualTo(DEFAULT_BANK_FREE_FIELD_1);
        assertThat(testBank.getBankFreeField3()).isEqualTo(UPDATED_BANK_FREE_FIELD_3);
        assertThat(testBank.getBankFreeField4()).isEqualTo(DEFAULT_BANK_FREE_FIELD_4);
        assertThat(testBank.getBankFreeField5()).isEqualTo(DEFAULT_BANK_FREE_FIELD_5);
        assertThat(testBank.getBankFreeField6()).isEqualTo(DEFAULT_BANK_FREE_FIELD_6);
        assertThat(testBank.getBankFreeField7()).isEqualTo(UPDATED_BANK_FREE_FIELD_7);
        assertThat(testBank.getBankFreeField8()).isEqualTo(UPDATED_BANK_FREE_FIELD_8);
        assertThat(testBank.getBankFreeField9()).isEqualTo(DEFAULT_BANK_FREE_FIELD_9);
        assertThat(testBank.getBankFreeField10()).isEqualTo(UPDATED_BANK_FREE_FIELD_10);
        assertThat(testBank.getBankFreeField11()).isEqualTo(UPDATED_BANK_FREE_FIELD_11);
        assertThat(testBank.getBankFreeField12()).isEqualTo(UPDATED_BANK_FREE_FIELD_12);
        assertThat(testBank.getBankFreeField13()).isEqualTo(DEFAULT_BANK_FREE_FIELD_13);
        assertThat(testBank.getBankFreeField14()).isEqualTo(DEFAULT_BANK_FREE_FIELD_14);
        assertThat(testBank.getBankFreeField15()).isEqualTo(UPDATED_BANK_FREE_FIELD_15);
        assertThat(testBank.getBankFreeField16()).isEqualTo(DEFAULT_BANK_FREE_FIELD_16);
        assertThat(testBank.getBankFreeField17()).isEqualTo(UPDATED_BANK_FREE_FIELD_17);
        assertThat(testBank.getBankFreeField18()).isEqualTo(DEFAULT_BANK_FREE_FIELD_18);
        assertThat(testBank.getBankFreeField19()).isEqualTo(UPDATED_BANK_FREE_FIELD_19);
        assertThat(testBank.getBankFreeField20()).isEqualTo(DEFAULT_BANK_FREE_FIELD_20);
        assertThat(testBank.getBankFreeField21()).isEqualTo(DEFAULT_BANK_FREE_FIELD_21);
        assertThat(testBank.getBankFreeField22()).isEqualTo(UPDATED_BANK_FREE_FIELD_22);
        assertThat(testBank.getBankFreeField23()).isEqualTo(UPDATED_BANK_FREE_FIELD_23);
        assertThat(testBank.getBankFreeField24()).isEqualTo(UPDATED_BANK_FREE_FIELD_24);
        assertThat(testBank.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBank.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBank.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBank.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBank.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBank.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateBankWithPatch() throws Exception {
        // Initialize the database
        bankRepository.save(bank).block();

        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();

        // Update the bank using partial update
        Bank partialUpdatedBank = new Bank();
        partialUpdatedBank.setId(bank.getId());

        partialUpdatedBank
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .bankName(UPDATED_BANK_NAME)
            .bankSwiftCode(UPDATED_BANK_SWIFT_CODE)
            .bankBranchId(UPDATED_BANK_BRANCH_ID)
            .bankPhoneNumber(UPDATED_BANK_PHONE_NUMBER)
            .bankEmail(UPDATED_BANK_EMAIL)
            .bankFreeField1(UPDATED_BANK_FREE_FIELD_1)
            .bankFreeField3(UPDATED_BANK_FREE_FIELD_3)
            .bankFreeField4(UPDATED_BANK_FREE_FIELD_4)
            .bankFreeField5(UPDATED_BANK_FREE_FIELD_5)
            .bankFreeField6(UPDATED_BANK_FREE_FIELD_6)
            .bankFreeField7(UPDATED_BANK_FREE_FIELD_7)
            .bankFreeField8(UPDATED_BANK_FREE_FIELD_8)
            .bankFreeField9(UPDATED_BANK_FREE_FIELD_9)
            .bankFreeField10(UPDATED_BANK_FREE_FIELD_10)
            .bankFreeField11(UPDATED_BANK_FREE_FIELD_11)
            .bankFreeField12(UPDATED_BANK_FREE_FIELD_12)
            .bankFreeField13(UPDATED_BANK_FREE_FIELD_13)
            .bankFreeField14(UPDATED_BANK_FREE_FIELD_14)
            .bankFreeField15(UPDATED_BANK_FREE_FIELD_15)
            .bankFreeField16(UPDATED_BANK_FREE_FIELD_16)
            .bankFreeField17(UPDATED_BANK_FREE_FIELD_17)
            .bankFreeField18(UPDATED_BANK_FREE_FIELD_18)
            .bankFreeField19(UPDATED_BANK_FREE_FIELD_19)
            .bankFreeField20(UPDATED_BANK_FREE_FIELD_20)
            .bankFreeField21(UPDATED_BANK_FREE_FIELD_21)
            .bankFreeField22(UPDATED_BANK_FREE_FIELD_22)
            .bankFreeField23(UPDATED_BANK_FREE_FIELD_23)
            .bankFreeField24(UPDATED_BANK_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBank.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBank))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        Bank testBank = bankList.get(bankList.size() - 1);
        assertThat(testBank.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBank.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBank.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testBank.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testBank.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testBank.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBank.getBankSwiftCode()).isEqualTo(UPDATED_BANK_SWIFT_CODE);
        assertThat(testBank.getBankBranchId()).isEqualTo(UPDATED_BANK_BRANCH_ID);
        assertThat(testBank.getBankPhoneNumber()).isEqualTo(UPDATED_BANK_PHONE_NUMBER);
        assertThat(testBank.getBankEmail()).isEqualTo(UPDATED_BANK_EMAIL);
        assertThat(testBank.getBankFreeField1()).isEqualTo(UPDATED_BANK_FREE_FIELD_1);
        assertThat(testBank.getBankFreeField3()).isEqualTo(UPDATED_BANK_FREE_FIELD_3);
        assertThat(testBank.getBankFreeField4()).isEqualTo(UPDATED_BANK_FREE_FIELD_4);
        assertThat(testBank.getBankFreeField5()).isEqualTo(UPDATED_BANK_FREE_FIELD_5);
        assertThat(testBank.getBankFreeField6()).isEqualTo(UPDATED_BANK_FREE_FIELD_6);
        assertThat(testBank.getBankFreeField7()).isEqualTo(UPDATED_BANK_FREE_FIELD_7);
        assertThat(testBank.getBankFreeField8()).isEqualTo(UPDATED_BANK_FREE_FIELD_8);
        assertThat(testBank.getBankFreeField9()).isEqualTo(UPDATED_BANK_FREE_FIELD_9);
        assertThat(testBank.getBankFreeField10()).isEqualTo(UPDATED_BANK_FREE_FIELD_10);
        assertThat(testBank.getBankFreeField11()).isEqualTo(UPDATED_BANK_FREE_FIELD_11);
        assertThat(testBank.getBankFreeField12()).isEqualTo(UPDATED_BANK_FREE_FIELD_12);
        assertThat(testBank.getBankFreeField13()).isEqualTo(UPDATED_BANK_FREE_FIELD_13);
        assertThat(testBank.getBankFreeField14()).isEqualTo(UPDATED_BANK_FREE_FIELD_14);
        assertThat(testBank.getBankFreeField15()).isEqualTo(UPDATED_BANK_FREE_FIELD_15);
        assertThat(testBank.getBankFreeField16()).isEqualTo(UPDATED_BANK_FREE_FIELD_16);
        assertThat(testBank.getBankFreeField17()).isEqualTo(UPDATED_BANK_FREE_FIELD_17);
        assertThat(testBank.getBankFreeField18()).isEqualTo(UPDATED_BANK_FREE_FIELD_18);
        assertThat(testBank.getBankFreeField19()).isEqualTo(UPDATED_BANK_FREE_FIELD_19);
        assertThat(testBank.getBankFreeField20()).isEqualTo(UPDATED_BANK_FREE_FIELD_20);
        assertThat(testBank.getBankFreeField21()).isEqualTo(UPDATED_BANK_FREE_FIELD_21);
        assertThat(testBank.getBankFreeField22()).isEqualTo(UPDATED_BANK_FREE_FIELD_22);
        assertThat(testBank.getBankFreeField23()).isEqualTo(UPDATED_BANK_FREE_FIELD_23);
        assertThat(testBank.getBankFreeField24()).isEqualTo(UPDATED_BANK_FREE_FIELD_24);
        assertThat(testBank.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBank.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBank.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBank.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBank.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testBank.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, bank.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamBank() throws Exception {
        int databaseSizeBeforeUpdate = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        bank.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(bank))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Bank in the database
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteBank() {
        // Initialize the database
        bankRepository.save(bank).block();
        bankRepository.save(bank).block();
        bankSearchRepository.save(bank).block();

        int databaseSizeBeforeDelete = bankRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the bank
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, bank.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Bank> bankList = bankRepository.findAll().collectList().block();
        assertThat(bankList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(bankSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchBank() {
        // Initialize the database
        bank = bankRepository.save(bank).block();
        bankSearchRepository.save(bank).block();

        // Search the bank
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + bank.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(bank.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].bankName")
            .value(hasItem(DEFAULT_BANK_NAME))
            .jsonPath("$.[*].bankSwiftCode")
            .value(hasItem(DEFAULT_BANK_SWIFT_CODE))
            .jsonPath("$.[*].bankBranchId")
            .value(hasItem(DEFAULT_BANK_BRANCH_ID))
            .jsonPath("$.[*].bankPhoneNumber")
            .value(hasItem(DEFAULT_BANK_PHONE_NUMBER))
            .jsonPath("$.[*].bankEmail")
            .value(hasItem(DEFAULT_BANK_EMAIL))
            .jsonPath("$.[*].bankFreeField1")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_1))
            .jsonPath("$.[*].bankFreeField3")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_3))
            .jsonPath("$.[*].bankFreeField4")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_4))
            .jsonPath("$.[*].bankFreeField5")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_5))
            .jsonPath("$.[*].bankFreeField6")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_6))
            .jsonPath("$.[*].bankFreeField7")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_7))
            .jsonPath("$.[*].bankFreeField8")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_8))
            .jsonPath("$.[*].bankFreeField9")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_9))
            .jsonPath("$.[*].bankFreeField10")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_10))
            .jsonPath("$.[*].bankFreeField11")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_11))
            .jsonPath("$.[*].bankFreeField12")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_12))
            .jsonPath("$.[*].bankFreeField13")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_13))
            .jsonPath("$.[*].bankFreeField14")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_14))
            .jsonPath("$.[*].bankFreeField15")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_15))
            .jsonPath("$.[*].bankFreeField16")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_16))
            .jsonPath("$.[*].bankFreeField17")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_17))
            .jsonPath("$.[*].bankFreeField18")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_18))
            .jsonPath("$.[*].bankFreeField19")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_19))
            .jsonPath("$.[*].bankFreeField20")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_20))
            .jsonPath("$.[*].bankFreeField21")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_21))
            .jsonPath("$.[*].bankFreeField22")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_22))
            .jsonPath("$.[*].bankFreeField23")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_23))
            .jsonPath("$.[*].bankFreeField24")
            .value(hasItem(DEFAULT_BANK_FREE_FIELD_24))
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
