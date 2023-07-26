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
import ug.co.absa.microsrvc.gateway.domain.DTransactionHistory;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.DTransactionHistoryRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionHistorySearchRepository;

/**
 * Integration tests for the {@link DTransactionHistoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DTransactionHistoryResourceIT {

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final Channel DEFAULT_PAYMENT_CHANNEL_CODE = Channel.ATM;
    private static final Channel UPDATED_PAYMENT_CHANNEL_CODE = Channel.CDM;

    private static final String DEFAULT_DEBIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEBIT_AMOUNT = 1;
    private static final Integer UPDATED_DEBIT_AMOUNT = 2;

    private static final Integer DEFAULT_CREDIT_AMOUNT = 1;
    private static final Integer UPDATED_CREDIT_AMOUNT = 2;

    private static final Integer DEFAULT_TRANSACTION_AMOUNT = 1;
    private static final Integer UPDATED_TRANSACTION_AMOUNT = 2;

    private static final ZonedDateTime DEFAULT_DEBIT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEBIT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREDIT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREDIT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TranStatus DEFAULT_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_STATUS = TranStatus.SUCCESS;

    private static final ZonedDateTime DEFAULT_SETTLEMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETTLEMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Currency DEFAULT_DEBIT_CURRENCY = Currency.UGX;
    private static final Currency UPDATED_DEBIT_CURRENCY = Currency.USD;

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_PHONE_NUMBER = "BBBBBBBBBB";

    private static final TranStatus DEFAULT_TRAN_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_TRAN_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_NARRATION_1 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_2 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_3 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_3 = "BBBBBBBBBB";

    private static final ModeOfPayment DEFAULT_MODE_OF_PAYMENT = ModeOfPayment.CASH;
    private static final ModeOfPayment UPDATED_MODE_OF_PAYMENT = ModeOfPayment.RTGS;

    private static final Integer DEFAULT_RETRIES = 1;
    private static final Integer UPDATED_RETRIES = 2;

    private static final Boolean DEFAULT_POSTED = false;
    private static final Boolean UPDATED_POSTED = true;

    private static final String DEFAULT_API_ID = "AAAAAAAAAA";
    private static final String UPDATED_API_ID = "BBBBBBBBBB";

    private static final String DEFAULT_API_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_API_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_POSTING_API = "AAAAAAAAAA";
    private static final String UPDATED_POSTING_API = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_POSTED = false;
    private static final Boolean UPDATED_IS_POSTED = true;

    private static final String DEFAULT_POSTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_POSTED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_POSTED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-transaction-histories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/d-transaction-histories";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DTransactionHistoryRepository dTransactionHistoryRepository;

    @Autowired
    private DTransactionHistorySearchRepository dTransactionHistorySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DTransactionHistory dTransactionHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionHistory createEntity(EntityManager em) {
        DTransactionHistory dTransactionHistory = new DTransactionHistory()
            .recordId(DEFAULT_RECORD_ID)
            .transferId(DEFAULT_TRANSFER_ID)
            .productCode(DEFAULT_PRODUCT_CODE)
            .paymentChannelCode(DEFAULT_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(DEFAULT_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(DEFAULT_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(DEFAULT_DEBIT_AMOUNT)
            .creditAmount(DEFAULT_CREDIT_AMOUNT)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .debitDate(DEFAULT_DEBIT_DATE)
            .creditDate(DEFAULT_CREDIT_DATE)
            .status(DEFAULT_STATUS)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .debitCurrency(DEFAULT_DEBIT_CURRENCY)
            .timestamp(DEFAULT_TIMESTAMP)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .payerName(DEFAULT_PAYER_NAME)
            .payerAddress(DEFAULT_PAYER_ADDRESS)
            .payerEmail(DEFAULT_PAYER_EMAIL)
            .payerPhoneNumber(DEFAULT_PAYER_PHONE_NUMBER)
            .payerNarration(DEFAULT_PAYER_NARRATION)
            .payerBranchId(DEFAULT_PAYER_BRANCH_ID)
            .beneficiaryName(DEFAULT_BENEFICIARY_NAME)
            .beneficiaryAccount(DEFAULT_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(DEFAULT_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(DEFAULT_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(DEFAULT_TRAN_STATUS)
            .narration1(DEFAULT_NARRATION_1)
            .narration2(DEFAULT_NARRATION_2)
            .narration3(DEFAULT_NARRATION_3)
            .modeOfPayment(DEFAULT_MODE_OF_PAYMENT)
            .retries(DEFAULT_RETRIES)
            .posted(DEFAULT_POSTED)
            .apiId(DEFAULT_API_ID)
            .apiVersion(DEFAULT_API_VERSION)
            .postingApi(DEFAULT_POSTING_API)
            .isPosted(DEFAULT_IS_POSTED)
            .postedBy(DEFAULT_POSTED_BY)
            .postedDate(DEFAULT_POSTED_DATE)
            .internalErrorCode(DEFAULT_INTERNAL_ERROR_CODE)
            .externalErrorCode(DEFAULT_EXTERNAL_ERROR_CODE)
            .tranFreeField1(DEFAULT_TRAN_FREE_FIELD_1)
            .tranFreeField3(DEFAULT_TRAN_FREE_FIELD_3)
            .tranFreeField4(DEFAULT_TRAN_FREE_FIELD_4)
            .tranFreeField5(DEFAULT_TRAN_FREE_FIELD_5)
            .tranFreeField6(DEFAULT_TRAN_FREE_FIELD_6)
            .tranFreeField7(DEFAULT_TRAN_FREE_FIELD_7)
            .tranFreeField8(DEFAULT_TRAN_FREE_FIELD_8)
            .tranFreeField9(DEFAULT_TRAN_FREE_FIELD_9)
            .tranFreeField10(DEFAULT_TRAN_FREE_FIELD_10)
            .tranFreeField11(DEFAULT_TRAN_FREE_FIELD_11)
            .tranFreeField12(DEFAULT_TRAN_FREE_FIELD_12)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return dTransactionHistory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionHistory createUpdatedEntity(EntityManager em) {
        DTransactionHistory dTransactionHistory = new DTransactionHistory()
            .recordId(UPDATED_RECORD_ID)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return dTransactionHistory;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DTransactionHistory.class).block();
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
        dTransactionHistorySearchRepository.deleteAll().block();
        assertThat(dTransactionHistorySearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        dTransactionHistory = createEntity(em);
    }

    @Test
    void createDTransactionHistory() throws Exception {
        int databaseSizeBeforeCreate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // Create the DTransactionHistory
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DTransactionHistory testDTransactionHistory = dTransactionHistoryList.get(dTransactionHistoryList.size() - 1);
        assertThat(testDTransactionHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDTransactionHistory.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransactionHistory.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransactionHistory.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionHistory.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getDebitAmount()).isEqualTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransactionHistory.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionHistory.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionHistory.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testDTransactionHistory.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testDTransactionHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionHistory.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransactionHistory.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testDTransactionHistory.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionHistory.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDTransactionHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testDTransactionHistory.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionHistory.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testDTransactionHistory.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testDTransactionHistory.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testDTransactionHistory.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionHistory.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransactionHistory.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testDTransactionHistory.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testDTransactionHistory.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testDTransactionHistory.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionHistory.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransactionHistory.getPosted()).isEqualTo(DEFAULT_POSTED);
        assertThat(testDTransactionHistory.getApiId()).isEqualTo(DEFAULT_API_ID);
        assertThat(testDTransactionHistory.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testDTransactionHistory.getPostingApi()).isEqualTo(DEFAULT_POSTING_API);
        assertThat(testDTransactionHistory.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionHistory.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionHistory.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransactionHistory.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionHistory.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionHistory.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionHistory.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionHistory.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionHistory.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionHistory.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionHistory.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionHistory.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionHistory.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionHistory.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionHistory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createDTransactionHistoryWithExistingId() throws Exception {
        // Create the DTransactionHistory with an existing ID
        dTransactionHistory.setId(1L);

        int databaseSizeBeforeCreate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setProductCode(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentChannelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setPaymentChannelCode(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setDebitAccountNumber(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreditAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setCreditAccountNumber(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setDebitDate(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setCreditDate(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setTimestamp(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setPhoneNumber(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionHistory.setEmail(null);

        // Create the DTransactionHistory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDTransactionHistoriesAsStream() {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        List<DTransactionHistory> dTransactionHistoryList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DTransactionHistory.class)
            .getResponseBody()
            .filter(dTransactionHistory::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(dTransactionHistoryList).isNotNull();
        assertThat(dTransactionHistoryList).hasSize(1);
        DTransactionHistory testDTransactionHistory = dTransactionHistoryList.get(0);
        assertThat(testDTransactionHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDTransactionHistory.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransactionHistory.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransactionHistory.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionHistory.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getDebitAmount()).isEqualTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransactionHistory.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionHistory.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionHistory.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testDTransactionHistory.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testDTransactionHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionHistory.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransactionHistory.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testDTransactionHistory.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionHistory.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDTransactionHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testDTransactionHistory.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionHistory.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testDTransactionHistory.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testDTransactionHistory.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testDTransactionHistory.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionHistory.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransactionHistory.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testDTransactionHistory.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testDTransactionHistory.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testDTransactionHistory.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionHistory.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransactionHistory.getPosted()).isEqualTo(DEFAULT_POSTED);
        assertThat(testDTransactionHistory.getApiId()).isEqualTo(DEFAULT_API_ID);
        assertThat(testDTransactionHistory.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testDTransactionHistory.getPostingApi()).isEqualTo(DEFAULT_POSTING_API);
        assertThat(testDTransactionHistory.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionHistory.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionHistory.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransactionHistory.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionHistory.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionHistory.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionHistory.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionHistory.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionHistory.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionHistory.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionHistory.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionHistory.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionHistory.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionHistory.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionHistory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllDTransactionHistories() {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        // Get all the dTransactionHistoryList
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
            .value(hasItem(dTransactionHistory.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].debitAccountNumber")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].creditAccountNumber")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(DEFAULT_DEBIT_AMOUNT))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(DEFAULT_CREDIT_AMOUNT))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.[*].debitDate")
            .value(hasItem(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.[*].creditDate")
            .value(hasItem(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].debitCurrency")
            .value(hasItem(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payerAddress")
            .value(hasItem(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.[*].payerEmail")
            .value(hasItem(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.[*].payerPhoneNumber")
            .value(hasItem(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.[*].payerNarration")
            .value(hasItem(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.[*].payerBranchId")
            .value(hasItem(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].beneficiaryAccount")
            .value(hasItem(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.[*].beneficiaryBranchId")
            .value(hasItem(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryPhoneNumber")
            .value(hasItem(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].narration1")
            .value(hasItem(DEFAULT_NARRATION_1))
            .jsonPath("$.[*].narration2")
            .value(hasItem(DEFAULT_NARRATION_2))
            .jsonPath("$.[*].narration3")
            .value(hasItem(DEFAULT_NARRATION_3))
            .jsonPath("$.[*].modeOfPayment")
            .value(hasItem(DEFAULT_MODE_OF_PAYMENT.toString()))
            .jsonPath("$.[*].retries")
            .value(hasItem(DEFAULT_RETRIES))
            .jsonPath("$.[*].posted")
            .value(hasItem(DEFAULT_POSTED.booleanValue()))
            .jsonPath("$.[*].apiId")
            .value(hasItem(DEFAULT_API_ID))
            .jsonPath("$.[*].apiVersion")
            .value(hasItem(DEFAULT_API_VERSION))
            .jsonPath("$.[*].postingApi")
            .value(hasItem(DEFAULT_POSTING_API))
            .jsonPath("$.[*].isPosted")
            .value(hasItem(DEFAULT_IS_POSTED.booleanValue()))
            .jsonPath("$.[*].postedBy")
            .value(hasItem(DEFAULT_POSTED_BY))
            .jsonPath("$.[*].postedDate")
            .value(hasItem(DEFAULT_POSTED_DATE))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField3")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.[*].tranFreeField4")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.[*].tranFreeField5")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.[*].tranFreeField6")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.[*].tranFreeField7")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.[*].tranFreeField8")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.[*].tranFreeField9")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.[*].tranFreeField10")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.[*].tranFreeField11")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12))
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
    void getDTransactionHistory() {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        // Get the dTransactionHistory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dTransactionHistory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dTransactionHistory.getId().intValue()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.transferId")
            .value(is(DEFAULT_TRANSFER_ID))
            .jsonPath("$.productCode")
            .value(is(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.paymentChannelCode")
            .value(is(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.debitAccountNumber")
            .value(is(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.creditAccountNumber")
            .value(is(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.debitAmount")
            .value(is(DEFAULT_DEBIT_AMOUNT))
            .jsonPath("$.creditAmount")
            .value(is(DEFAULT_CREDIT_AMOUNT))
            .jsonPath("$.transactionAmount")
            .value(is(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.debitDate")
            .value(is(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.creditDate")
            .value(is(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.settlementDate")
            .value(is(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.debitCurrency")
            .value(is(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.phoneNumber")
            .value(is(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.payerAddress")
            .value(is(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.payerEmail")
            .value(is(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.payerPhoneNumber")
            .value(is(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.payerNarration")
            .value(is(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.payerBranchId")
            .value(is(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.beneficiaryName")
            .value(is(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.beneficiaryAccount")
            .value(is(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.beneficiaryBranchId")
            .value(is(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.beneficiaryPhoneNumber")
            .value(is(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.tranStatus")
            .value(is(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.narration1")
            .value(is(DEFAULT_NARRATION_1))
            .jsonPath("$.narration2")
            .value(is(DEFAULT_NARRATION_2))
            .jsonPath("$.narration3")
            .value(is(DEFAULT_NARRATION_3))
            .jsonPath("$.modeOfPayment")
            .value(is(DEFAULT_MODE_OF_PAYMENT.toString()))
            .jsonPath("$.retries")
            .value(is(DEFAULT_RETRIES))
            .jsonPath("$.posted")
            .value(is(DEFAULT_POSTED.booleanValue()))
            .jsonPath("$.apiId")
            .value(is(DEFAULT_API_ID))
            .jsonPath("$.apiVersion")
            .value(is(DEFAULT_API_VERSION))
            .jsonPath("$.postingApi")
            .value(is(DEFAULT_POSTING_API))
            .jsonPath("$.isPosted")
            .value(is(DEFAULT_IS_POSTED.booleanValue()))
            .jsonPath("$.postedBy")
            .value(is(DEFAULT_POSTED_BY))
            .jsonPath("$.postedDate")
            .value(is(DEFAULT_POSTED_DATE))
            .jsonPath("$.internalErrorCode")
            .value(is(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.externalErrorCode")
            .value(is(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.tranFreeField1")
            .value(is(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.tranFreeField3")
            .value(is(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.tranFreeField4")
            .value(is(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.tranFreeField5")
            .value(is(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.tranFreeField6")
            .value(is(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.tranFreeField7")
            .value(is(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.tranFreeField8")
            .value(is(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.tranFreeField9")
            .value(is(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.tranFreeField10")
            .value(is(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.tranFreeField11")
            .value(is(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.tranFreeField12")
            .value(is(DEFAULT_TRAN_FREE_FIELD_12))
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
    void getNonExistingDTransactionHistory() {
        // Get the dTransactionHistory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDTransactionHistory() throws Exception {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        dTransactionHistorySearchRepository.save(dTransactionHistory).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());

        // Update the dTransactionHistory
        DTransactionHistory updatedDTransactionHistory = dTransactionHistoryRepository.findById(dTransactionHistory.getId()).block();
        updatedDTransactionHistory
            .recordId(UPDATED_RECORD_ID)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDTransactionHistory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDTransactionHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionHistory testDTransactionHistory = dTransactionHistoryList.get(dTransactionHistoryList.size() - 1);
        assertThat(testDTransactionHistory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDTransactionHistory.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransactionHistory.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransactionHistory.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionHistory.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransactionHistory.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransactionHistory.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionHistory.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testDTransactionHistory.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testDTransactionHistory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransactionHistory.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransactionHistory.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testDTransactionHistory.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionHistory.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testDTransactionHistory.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testDTransactionHistory.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testDTransactionHistory.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testDTransactionHistory.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionHistory.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionHistory.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransactionHistory.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testDTransactionHistory.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testDTransactionHistory.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionHistory.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
        assertThat(testDTransactionHistory.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransactionHistory.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionHistory.getApiId()).isEqualTo(UPDATED_API_ID);
        assertThat(testDTransactionHistory.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testDTransactionHistory.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionHistory.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
        assertThat(testDTransactionHistory.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransactionHistory.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransactionHistory.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionHistory.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionHistory.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionHistory.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionHistory.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionHistory.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionHistory.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionHistory.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionHistory.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionHistory.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionHistory.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionHistory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionHistory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DTransactionHistory> dTransactionHistorySearchList = IterableUtils.toList(
                    dTransactionHistorySearchRepository.findAll().collectList().block()
                );
                DTransactionHistory testDTransactionHistorySearch = dTransactionHistorySearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDTransactionHistorySearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testDTransactionHistorySearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testDTransactionHistorySearch.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
                assertThat(testDTransactionHistorySearch.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
                assertThat(testDTransactionHistorySearch.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
                assertThat(testDTransactionHistorySearch.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
                assertThat(testDTransactionHistorySearch.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
                assertThat(testDTransactionHistorySearch.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
                assertThat(testDTransactionHistorySearch.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
                assertThat(testDTransactionHistorySearch.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
                assertThat(testDTransactionHistorySearch.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
                assertThat(testDTransactionHistorySearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testDTransactionHistorySearch.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
                assertThat(testDTransactionHistorySearch.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
                assertThat(testDTransactionHistorySearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDTransactionHistorySearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testDTransactionHistorySearch.getEmail()).isEqualTo(UPDATED_EMAIL);
                assertThat(testDTransactionHistorySearch.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
                assertThat(testDTransactionHistorySearch.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
                assertThat(testDTransactionHistorySearch.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
                assertThat(testDTransactionHistorySearch.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
                assertThat(testDTransactionHistorySearch.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
                assertThat(testDTransactionHistorySearch.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
                assertThat(testDTransactionHistorySearch.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
                assertThat(testDTransactionHistorySearch.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
                assertThat(testDTransactionHistorySearch.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
                assertThat(testDTransactionHistorySearch.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
                assertThat(testDTransactionHistorySearch.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
                assertThat(testDTransactionHistorySearch.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
                assertThat(testDTransactionHistorySearch.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
                assertThat(testDTransactionHistorySearch.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
                assertThat(testDTransactionHistorySearch.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
                assertThat(testDTransactionHistorySearch.getRetries()).isEqualTo(UPDATED_RETRIES);
                assertThat(testDTransactionHistorySearch.getPosted()).isEqualTo(UPDATED_POSTED);
                assertThat(testDTransactionHistorySearch.getApiId()).isEqualTo(UPDATED_API_ID);
                assertThat(testDTransactionHistorySearch.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
                assertThat(testDTransactionHistorySearch.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
                assertThat(testDTransactionHistorySearch.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
                assertThat(testDTransactionHistorySearch.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
                assertThat(testDTransactionHistorySearch.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
                assertThat(testDTransactionHistorySearch.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
                assertThat(testDTransactionHistorySearch.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
                assertThat(testDTransactionHistorySearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testDTransactionHistorySearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testDTransactionHistorySearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testDTransactionHistorySearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testDTransactionHistorySearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testDTransactionHistorySearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testDTransactionHistorySearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testDTransactionHistorySearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testDTransactionHistorySearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testDTransactionHistorySearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testDTransactionHistorySearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testDTransactionHistorySearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDTransactionHistorySearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDTransactionHistorySearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDTransactionHistorySearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dTransactionHistory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDTransactionHistoryWithPatch() throws Exception {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();

        // Update the dTransactionHistory using partial update
        DTransactionHistory partialUpdatedDTransactionHistory = new DTransactionHistory();
        partialUpdatedDTransactionHistory.setId(dTransactionHistory.getId());

        partialUpdatedDTransactionHistory
            .transferId(UPDATED_TRANSFER_ID)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .postedDate(UPDATED_POSTED_DATE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionHistory testDTransactionHistory = dTransactionHistoryList.get(dTransactionHistoryList.size() - 1);
        assertThat(testDTransactionHistory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDTransactionHistory.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransactionHistory.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransactionHistory.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionHistory.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransactionHistory.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionHistory.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionHistory.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testDTransactionHistory.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testDTransactionHistory.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionHistory.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransactionHistory.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testDTransactionHistory.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionHistory.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionHistory.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testDTransactionHistory.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionHistory.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testDTransactionHistory.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testDTransactionHistory.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionHistory.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionHistory.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransactionHistory.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testDTransactionHistory.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testDTransactionHistory.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionHistory.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionHistory.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransactionHistory.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionHistory.getApiId()).isEqualTo(DEFAULT_API_ID);
        assertThat(testDTransactionHistory.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testDTransactionHistory.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionHistory.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionHistory.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionHistory.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransactionHistory.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionHistory.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionHistory.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionHistory.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionHistory.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionHistory.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionHistory.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionHistory.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionHistory.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionHistory.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionHistory.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionHistory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionHistory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionHistory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void fullUpdateDTransactionHistoryWithPatch() throws Exception {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();

        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();

        // Update the dTransactionHistory using partial update
        DTransactionHistory partialUpdatedDTransactionHistory = new DTransactionHistory();
        partialUpdatedDTransactionHistory.setId(dTransactionHistory.getId());

        partialUpdatedDTransactionHistory
            .recordId(UPDATED_RECORD_ID)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionHistory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        DTransactionHistory testDTransactionHistory = dTransactionHistoryList.get(dTransactionHistoryList.size() - 1);
        assertThat(testDTransactionHistory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDTransactionHistory.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransactionHistory.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransactionHistory.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionHistory.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionHistory.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransactionHistory.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransactionHistory.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionHistory.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testDTransactionHistory.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testDTransactionHistory.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransactionHistory.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransactionHistory.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testDTransactionHistory.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionHistory.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionHistory.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testDTransactionHistory.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testDTransactionHistory.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testDTransactionHistory.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testDTransactionHistory.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionHistory.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionHistory.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionHistory.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionHistory.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransactionHistory.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testDTransactionHistory.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testDTransactionHistory.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionHistory.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
        assertThat(testDTransactionHistory.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransactionHistory.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionHistory.getApiId()).isEqualTo(UPDATED_API_ID);
        assertThat(testDTransactionHistory.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testDTransactionHistory.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionHistory.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
        assertThat(testDTransactionHistory.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransactionHistory.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransactionHistory.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransactionHistory.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionHistory.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionHistory.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionHistory.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionHistory.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionHistory.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionHistory.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionHistory.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionHistory.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionHistory.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionHistory.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionHistory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionHistory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionHistory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dTransactionHistory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        dTransactionHistory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionHistory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionHistory in the database
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDTransactionHistory() {
        // Initialize the database
        dTransactionHistoryRepository.save(dTransactionHistory).block();
        dTransactionHistoryRepository.save(dTransactionHistory).block();
        dTransactionHistorySearchRepository.save(dTransactionHistory).block();

        int databaseSizeBeforeDelete = dTransactionHistoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the dTransactionHistory
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dTransactionHistory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DTransactionHistory> dTransactionHistoryList = dTransactionHistoryRepository.findAll().collectList().block();
        assertThat(dTransactionHistoryList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionHistorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDTransactionHistory() {
        // Initialize the database
        dTransactionHistory = dTransactionHistoryRepository.save(dTransactionHistory).block();
        dTransactionHistorySearchRepository.save(dTransactionHistory).block();

        // Search the dTransactionHistory
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + dTransactionHistory.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dTransactionHistory.getId().intValue()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].debitAccountNumber")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].creditAccountNumber")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(DEFAULT_DEBIT_AMOUNT))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(DEFAULT_CREDIT_AMOUNT))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(DEFAULT_TRANSACTION_AMOUNT))
            .jsonPath("$.[*].debitDate")
            .value(hasItem(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.[*].creditDate")
            .value(hasItem(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].debitCurrency")
            .value(hasItem(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payerAddress")
            .value(hasItem(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.[*].payerEmail")
            .value(hasItem(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.[*].payerPhoneNumber")
            .value(hasItem(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.[*].payerNarration")
            .value(hasItem(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.[*].payerBranchId")
            .value(hasItem(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].beneficiaryAccount")
            .value(hasItem(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.[*].beneficiaryBranchId")
            .value(hasItem(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryPhoneNumber")
            .value(hasItem(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].narration1")
            .value(hasItem(DEFAULT_NARRATION_1))
            .jsonPath("$.[*].narration2")
            .value(hasItem(DEFAULT_NARRATION_2))
            .jsonPath("$.[*].narration3")
            .value(hasItem(DEFAULT_NARRATION_3))
            .jsonPath("$.[*].modeOfPayment")
            .value(hasItem(DEFAULT_MODE_OF_PAYMENT.toString()))
            .jsonPath("$.[*].retries")
            .value(hasItem(DEFAULT_RETRIES))
            .jsonPath("$.[*].posted")
            .value(hasItem(DEFAULT_POSTED.booleanValue()))
            .jsonPath("$.[*].apiId")
            .value(hasItem(DEFAULT_API_ID))
            .jsonPath("$.[*].apiVersion")
            .value(hasItem(DEFAULT_API_VERSION))
            .jsonPath("$.[*].postingApi")
            .value(hasItem(DEFAULT_POSTING_API))
            .jsonPath("$.[*].isPosted")
            .value(hasItem(DEFAULT_IS_POSTED.booleanValue()))
            .jsonPath("$.[*].postedBy")
            .value(hasItem(DEFAULT_POSTED_BY))
            .jsonPath("$.[*].postedDate")
            .value(hasItem(DEFAULT_POSTED_DATE))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField3")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.[*].tranFreeField4")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.[*].tranFreeField5")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.[*].tranFreeField6")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.[*].tranFreeField7")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.[*].tranFreeField8")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.[*].tranFreeField9")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.[*].tranFreeField10")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.[*].tranFreeField11")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12))
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
