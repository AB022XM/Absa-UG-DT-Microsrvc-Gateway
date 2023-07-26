package ug.co.absa.microsrvc.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static ug.co.absa.microsrvc.gateway.web.rest.TestUtil.sameInstant;
import static ug.co.absa.microsrvc.gateway.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
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
import ug.co.absa.microsrvc.gateway.domain.AccountDetails;
import ug.co.absa.microsrvc.gateway.repository.AccountDetailsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.AccountDetailsSearchRepository;

/**
 * Integration tests for the {@link AccountDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AccountDetailsResourceIT {

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

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_REQUEST_ID = 1;
    private static final Integer UPDATED_REQUEST_ID = 2;

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_TX_NID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TX_NID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TRANSACTION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRANSACTION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_PRODUCT = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_2 = "BBBBBBBBBB";

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

    private static final String DEFAULT_TRAN_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TRAN_FREE_FIELD_18 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRAN_FREE_FIELD_18 = new BigDecimal(2);

    private static final Integer DEFAULT_TRAN_FREE_FIELD_19 = 1;
    private static final Integer UPDATED_TRAN_FREE_FIELD_19 = 2;

    private static final Boolean DEFAULT_TRAN_FREE_FIELD_20 = false;
    private static final Boolean UPDATED_TRAN_FREE_FIELD_20 = true;

    private static final String DEFAULT_TRAN_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TRAN_FREE_FIELD_23 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TRAN_FREE_FIELD_23 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_TRAN_FREE_FIELD_24 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRAN_FREE_FIELD_24 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TRAN_FREE_FIELD_25 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_25 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_26 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_26 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_27 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_27 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_28 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_28 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_29 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_29 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_30 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_30 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_31 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_31 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_32 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_32 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_33 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_33 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/account-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/account-details";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private AccountDetailsSearchRepository accountDetailsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AccountDetails accountDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountDetails createEntity(EntityManager em) {
        AccountDetails accountDetails = new AccountDetails()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .transferId(DEFAULT_TRANSFER_ID)
            .requestId(DEFAULT_REQUEST_ID)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .returnCode(DEFAULT_RETURN_CODE)
            .returnMessage(DEFAULT_RETURN_MESSAGE)
            .externalTXNid(DEFAULT_EXTERNAL_TX_NID)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerProduct(DEFAULT_CUSTOMER_PRODUCT)
            .customerType(DEFAULT_CUSTOMER_TYPE)
            .accountCategory(DEFAULT_ACCOUNT_CATEGORY)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .channel(DEFAULT_CHANNEL)
            .tranFreeField1(DEFAULT_TRAN_FREE_FIELD_1)
            .tranFreeField2(DEFAULT_TRAN_FREE_FIELD_2)
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
            .tranFreeField13(DEFAULT_TRAN_FREE_FIELD_13)
            .tranFreeField14(DEFAULT_TRAN_FREE_FIELD_14)
            .tranFreeField15(DEFAULT_TRAN_FREE_FIELD_15)
            .tranFreeField16(DEFAULT_TRAN_FREE_FIELD_16)
            .tranFreeField17(DEFAULT_TRAN_FREE_FIELD_17)
            .tranFreeField18(DEFAULT_TRAN_FREE_FIELD_18)
            .tranFreeField19(DEFAULT_TRAN_FREE_FIELD_19)
            .tranFreeField20(DEFAULT_TRAN_FREE_FIELD_20)
            .tranFreeField21(DEFAULT_TRAN_FREE_FIELD_21)
            .tranFreeField22(DEFAULT_TRAN_FREE_FIELD_22)
            .tranFreeField23(DEFAULT_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(DEFAULT_TRAN_FREE_FIELD_24)
            .tranFreeField25(DEFAULT_TRAN_FREE_FIELD_25)
            .tranFreeField26(DEFAULT_TRAN_FREE_FIELD_26)
            .tranFreeField27(DEFAULT_TRAN_FREE_FIELD_27)
            .tranFreeField28(DEFAULT_TRAN_FREE_FIELD_28)
            .tranFreeField29(DEFAULT_TRAN_FREE_FIELD_29)
            .tranFreeField30(DEFAULT_TRAN_FREE_FIELD_30)
            .tranFreeField31(DEFAULT_TRAN_FREE_FIELD_31)
            .tranFreeField32(DEFAULT_TRAN_FREE_FIELD_32)
            .tranFreeField33(DEFAULT_TRAN_FREE_FIELD_33)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return accountDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountDetails createUpdatedEntity(EntityManager em) {
        AccountDetails accountDetails = new AccountDetails()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .requestId(UPDATED_REQUEST_ID)
            .accountName(UPDATED_ACCOUNT_NAME)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .externalTXNid(UPDATED_EXTERNAL_TX_NID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerProduct(UPDATED_CUSTOMER_PRODUCT)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .accountCategory(UPDATED_ACCOUNT_CATEGORY)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .channel(UPDATED_CHANNEL)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
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
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .tranFreeField29(UPDATED_TRAN_FREE_FIELD_29)
            .tranFreeField30(UPDATED_TRAN_FREE_FIELD_30)
            .tranFreeField31(UPDATED_TRAN_FREE_FIELD_31)
            .tranFreeField32(UPDATED_TRAN_FREE_FIELD_32)
            .tranFreeField33(UPDATED_TRAN_FREE_FIELD_33)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return accountDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AccountDetails.class).block();
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
        accountDetailsSearchRepository.deleteAll().block();
        assertThat(accountDetailsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        accountDetails = createEntity(em);
    }

    @Test
    void createAccountDetails() throws Exception {
        int databaseSizeBeforeCreate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // Create the AccountDetails
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        AccountDetails testAccountDetails = accountDetailsList.get(accountDetailsList.size() - 1);
        assertThat(testAccountDetails.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAccountDetails.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAccountDetails.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testAccountDetails.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAccountDetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAccountDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testAccountDetails.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testAccountDetails.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testAccountDetails.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testAccountDetails.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testAccountDetails.getExternalTXNid()).isEqualTo(DEFAULT_EXTERNAL_TX_NID);
        assertThat(testAccountDetails.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testAccountDetails.getCustomerProduct()).isEqualTo(DEFAULT_CUSTOMER_PRODUCT);
        assertThat(testAccountDetails.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testAccountDetails.getAccountCategory()).isEqualTo(DEFAULT_ACCOUNT_CATEGORY);
        assertThat(testAccountDetails.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccountDetails.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testAccountDetails.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAccountDetails.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testAccountDetails.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testAccountDetails.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testAccountDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAccountDetails.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testAccountDetails.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testAccountDetails.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAccountDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAccountDetails.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testAccountDetails.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testAccountDetails.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testAccountDetails.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testAccountDetails.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testAccountDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testAccountDetails.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testAccountDetails.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testAccountDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAccountDetails.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testAccountDetails.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testAccountDetails.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testAccountDetails.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testAccountDetails.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testAccountDetails.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testAccountDetails.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testAccountDetails.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAccountDetails.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testAccountDetails.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testAccountDetails.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testAccountDetails.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testAccountDetails.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testAccountDetails.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testAccountDetails.getTranFreeField30()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_30);
        assertThat(testAccountDetails.getTranFreeField31()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_31);
        assertThat(testAccountDetails.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testAccountDetails.getTranFreeField33()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_33);
        assertThat(testAccountDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAccountDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAccountDetails.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAccountDetails.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createAccountDetailsWithExistingId() throws Exception {
        // Create the AccountDetails with an existing ID
        accountDetails.setId(1L);

        int databaseSizeBeforeCreate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setBillerId(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setPaymentItemCode(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setAccountName(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReturnCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setReturnCode(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReturnMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setReturnMessage(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkExternalTXNidIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setExternalTXNid(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTransactionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setTransactionDate(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setCustomerId(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerProductIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setCustomerProduct(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setCustomerType(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setAccountCategory(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setAccountType(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setAccountNumber(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setPhoneNumber(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        accountDetails.setChannel(null);

        // Create the AccountDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllAccountDetailsAsStream() {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        List<AccountDetails> accountDetailsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AccountDetails.class)
            .getResponseBody()
            .filter(accountDetails::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(accountDetailsList).isNotNull();
        assertThat(accountDetailsList).hasSize(1);
        AccountDetails testAccountDetails = accountDetailsList.get(0);
        assertThat(testAccountDetails.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAccountDetails.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAccountDetails.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testAccountDetails.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAccountDetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAccountDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testAccountDetails.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testAccountDetails.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testAccountDetails.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testAccountDetails.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testAccountDetails.getExternalTXNid()).isEqualTo(DEFAULT_EXTERNAL_TX_NID);
        assertThat(testAccountDetails.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testAccountDetails.getCustomerProduct()).isEqualTo(DEFAULT_CUSTOMER_PRODUCT);
        assertThat(testAccountDetails.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testAccountDetails.getAccountCategory()).isEqualTo(DEFAULT_ACCOUNT_CATEGORY);
        assertThat(testAccountDetails.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccountDetails.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testAccountDetails.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAccountDetails.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testAccountDetails.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testAccountDetails.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testAccountDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAccountDetails.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testAccountDetails.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testAccountDetails.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAccountDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAccountDetails.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testAccountDetails.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testAccountDetails.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testAccountDetails.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testAccountDetails.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testAccountDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testAccountDetails.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testAccountDetails.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testAccountDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAccountDetails.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testAccountDetails.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testAccountDetails.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testAccountDetails.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testAccountDetails.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testAccountDetails.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testAccountDetails.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testAccountDetails.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAccountDetails.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testAccountDetails.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testAccountDetails.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testAccountDetails.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testAccountDetails.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testAccountDetails.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testAccountDetails.getTranFreeField30()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_30);
        assertThat(testAccountDetails.getTranFreeField31()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_31);
        assertThat(testAccountDetails.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testAccountDetails.getTranFreeField33()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_33);
        assertThat(testAccountDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAccountDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAccountDetails.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAccountDetails.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllAccountDetails() {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        // Get all the accountDetailsList
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
            .value(hasItem(accountDetails.getId().intValue()))
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
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].requestId")
            .value(hasItem(DEFAULT_REQUEST_ID))
            .jsonPath("$.[*].accountName")
            .value(hasItem(DEFAULT_ACCOUNT_NAME))
            .jsonPath("$.[*].returnCode")
            .value(hasItem(DEFAULT_RETURN_CODE))
            .jsonPath("$.[*].returnMessage")
            .value(hasItem(DEFAULT_RETURN_MESSAGE))
            .jsonPath("$.[*].externalTXNid")
            .value(hasItem(DEFAULT_EXTERNAL_TX_NID))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.[*].customerProduct")
            .value(hasItem(DEFAULT_CUSTOMER_PRODUCT))
            .jsonPath("$.[*].customerType")
            .value(hasItem(DEFAULT_CUSTOMER_TYPE))
            .jsonPath("$.[*].accountCategory")
            .value(hasItem(DEFAULT_ACCOUNT_CATEGORY))
            .jsonPath("$.[*].accountType")
            .value(hasItem(DEFAULT_ACCOUNT_TYPE))
            .jsonPath("$.[*].accountNumber")
            .value(hasItem(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].channel")
            .value(hasItem(DEFAULT_CHANNEL))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField2")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_2))
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
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.[*].tranFreeField23ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.[*].tranFreeField25")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.[*].tranFreeField26")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.[*].tranFreeField27")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.[*].tranFreeField28")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_28))
            .jsonPath("$.[*].tranFreeField29")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_29))
            .jsonPath("$.[*].tranFreeField30")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_30))
            .jsonPath("$.[*].tranFreeField31")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_31))
            .jsonPath("$.[*].tranFreeField32")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_32))
            .jsonPath("$.[*].tranFreeField33")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_33))
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
    void getAccountDetails() {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        // Get the accountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, accountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(accountDetails.getId().intValue()))
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
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.token")
            .value(is(DEFAULT_TOKEN))
            .jsonPath("$.transferId")
            .value(is(DEFAULT_TRANSFER_ID))
            .jsonPath("$.requestId")
            .value(is(DEFAULT_REQUEST_ID))
            .jsonPath("$.accountName")
            .value(is(DEFAULT_ACCOUNT_NAME))
            .jsonPath("$.returnCode")
            .value(is(DEFAULT_RETURN_CODE))
            .jsonPath("$.returnMessage")
            .value(is(DEFAULT_RETURN_MESSAGE))
            .jsonPath("$.externalTXNid")
            .value(is(DEFAULT_EXTERNAL_TX_NID))
            .jsonPath("$.transactionDate")
            .value(is(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.customerId")
            .value(is(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.customerProduct")
            .value(is(DEFAULT_CUSTOMER_PRODUCT))
            .jsonPath("$.customerType")
            .value(is(DEFAULT_CUSTOMER_TYPE))
            .jsonPath("$.accountCategory")
            .value(is(DEFAULT_ACCOUNT_CATEGORY))
            .jsonPath("$.accountType")
            .value(is(DEFAULT_ACCOUNT_TYPE))
            .jsonPath("$.accountNumber")
            .value(is(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.phoneNumber")
            .value(is(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.channel")
            .value(is(DEFAULT_CHANNEL))
            .jsonPath("$.tranFreeField1")
            .value(is(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.tranFreeField2")
            .value(is(DEFAULT_TRAN_FREE_FIELD_2))
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
            .jsonPath("$.tranFreeField13")
            .value(is(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.tranFreeField14")
            .value(is(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.tranFreeField15")
            .value(is(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.tranFreeField16")
            .value(is(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.tranFreeField17")
            .value(is(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.tranFreeField18")
            .value(is(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.tranFreeField19")
            .value(is(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.tranFreeField20")
            .value(is(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.tranFreeField21")
            .value(is(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.tranFreeField22")
            .value(is(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.tranFreeField23ContentType")
            .value(is(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.tranFreeField23")
            .value(is(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.tranFreeField24")
            .value(is(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.tranFreeField25")
            .value(is(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.tranFreeField26")
            .value(is(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.tranFreeField27")
            .value(is(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.tranFreeField28")
            .value(is(DEFAULT_TRAN_FREE_FIELD_28))
            .jsonPath("$.tranFreeField29")
            .value(is(DEFAULT_TRAN_FREE_FIELD_29))
            .jsonPath("$.tranFreeField30")
            .value(is(DEFAULT_TRAN_FREE_FIELD_30))
            .jsonPath("$.tranFreeField31")
            .value(is(DEFAULT_TRAN_FREE_FIELD_31))
            .jsonPath("$.tranFreeField32")
            .value(is(DEFAULT_TRAN_FREE_FIELD_32))
            .jsonPath("$.tranFreeField33")
            .value(is(DEFAULT_TRAN_FREE_FIELD_33))
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
    void getNonExistingAccountDetails() {
        // Get the accountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAccountDetails() throws Exception {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        accountDetailsSearchRepository.save(accountDetails).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());

        // Update the accountDetails
        AccountDetails updatedAccountDetails = accountDetailsRepository.findById(accountDetails.getId()).block();
        updatedAccountDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .requestId(UPDATED_REQUEST_ID)
            .accountName(UPDATED_ACCOUNT_NAME)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .externalTXNid(UPDATED_EXTERNAL_TX_NID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerProduct(UPDATED_CUSTOMER_PRODUCT)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .accountCategory(UPDATED_ACCOUNT_CATEGORY)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .channel(UPDATED_CHANNEL)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
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
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .tranFreeField29(UPDATED_TRAN_FREE_FIELD_29)
            .tranFreeField30(UPDATED_TRAN_FREE_FIELD_30)
            .tranFreeField31(UPDATED_TRAN_FREE_FIELD_31)
            .tranFreeField32(UPDATED_TRAN_FREE_FIELD_32)
            .tranFreeField33(UPDATED_TRAN_FREE_FIELD_33)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAccountDetails.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        AccountDetails testAccountDetails = accountDetailsList.get(accountDetailsList.size() - 1);
        assertThat(testAccountDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAccountDetails.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testAccountDetails.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testAccountDetails.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAccountDetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAccountDetails.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testAccountDetails.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testAccountDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountDetails.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testAccountDetails.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testAccountDetails.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
        assertThat(testAccountDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testAccountDetails.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testAccountDetails.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testAccountDetails.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
        assertThat(testAccountDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testAccountDetails.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testAccountDetails.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAccountDetails.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testAccountDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAccountDetails.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testAccountDetails.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testAccountDetails.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testAccountDetails.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAccountDetails.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testAccountDetails.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testAccountDetails.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testAccountDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testAccountDetails.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testAccountDetails.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAccountDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testAccountDetails.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testAccountDetails.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testAccountDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAccountDetails.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testAccountDetails.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testAccountDetails.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testAccountDetails.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testAccountDetails.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAccountDetails.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testAccountDetails.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testAccountDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testAccountDetails.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAccountDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAccountDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testAccountDetails.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testAccountDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testAccountDetails.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testAccountDetails.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
        assertThat(testAccountDetails.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
        assertThat(testAccountDetails.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
        assertThat(testAccountDetails.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
        assertThat(testAccountDetails.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
        assertThat(testAccountDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAccountDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAccountDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAccountDetails.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<AccountDetails> accountDetailsSearchList = IterableUtils.toList(
                    accountDetailsSearchRepository.findAll().collectList().block()
                );
                AccountDetails testAccountDetailsSearch = accountDetailsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testAccountDetailsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testAccountDetailsSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testAccountDetailsSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testAccountDetailsSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testAccountDetailsSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testAccountDetailsSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testAccountDetailsSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testAccountDetailsSearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testAccountDetailsSearch.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
                assertThat(testAccountDetailsSearch.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
                assertThat(testAccountDetailsSearch.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
                assertThat(testAccountDetailsSearch.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
                assertThat(testAccountDetailsSearch.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
                assertThat(testAccountDetailsSearch.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
                assertThat(testAccountDetailsSearch.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
                assertThat(testAccountDetailsSearch.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
                assertThat(testAccountDetailsSearch.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
                assertThat(testAccountDetailsSearch.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
                assertThat(testAccountDetailsSearch.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
                assertThat(testAccountDetailsSearch.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
                assertThat(testAccountDetailsSearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testAccountDetailsSearch.getChannel()).isEqualTo(UPDATED_CHANNEL);
                assertThat(testAccountDetailsSearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testAccountDetailsSearch.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
                assertThat(testAccountDetailsSearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testAccountDetailsSearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testAccountDetailsSearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testAccountDetailsSearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testAccountDetailsSearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testAccountDetailsSearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testAccountDetailsSearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testAccountDetailsSearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testAccountDetailsSearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testAccountDetailsSearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testAccountDetailsSearch.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
                assertThat(testAccountDetailsSearch.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
                assertThat(testAccountDetailsSearch.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
                assertThat(testAccountDetailsSearch.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
                assertThat(testAccountDetailsSearch.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
                assertThat(testAccountDetailsSearch.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
                assertThat(testAccountDetailsSearch.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
                assertThat(testAccountDetailsSearch.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
                assertThat(testAccountDetailsSearch.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
                assertThat(testAccountDetailsSearch.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
                assertThat(testAccountDetailsSearch.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
                assertThat(testAccountDetailsSearch.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
                assertThat(testAccountDetailsSearch.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
                assertThat(testAccountDetailsSearch.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
                assertThat(testAccountDetailsSearch.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
                assertThat(testAccountDetailsSearch.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
                assertThat(testAccountDetailsSearch.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
                assertThat(testAccountDetailsSearch.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
                assertThat(testAccountDetailsSearch.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
                assertThat(testAccountDetailsSearch.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
                assertThat(testAccountDetailsSearch.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
                assertThat(testAccountDetailsSearch.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
                assertThat(testAccountDetailsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testAccountDetailsSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testAccountDetailsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testAccountDetailsSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, accountDetails.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();

        // Update the accountDetails using partial update
        AccountDetails partialUpdatedAccountDetails = new AccountDetails();
        partialUpdatedAccountDetails.setId(accountDetails.getId());

        partialUpdatedAccountDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .customerProduct(UPDATED_CUSTOMER_PRODUCT)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField30(UPDATED_TRAN_FREE_FIELD_30)
            .tranFreeField31(UPDATED_TRAN_FREE_FIELD_31)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        AccountDetails testAccountDetails = accountDetailsList.get(accountDetailsList.size() - 1);
        assertThat(testAccountDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAccountDetails.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAccountDetails.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testAccountDetails.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAccountDetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAccountDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testAccountDetails.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testAccountDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountDetails.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testAccountDetails.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testAccountDetails.getExternalTXNid()).isEqualTo(DEFAULT_EXTERNAL_TX_NID);
        assertThat(testAccountDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testAccountDetails.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testAccountDetails.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testAccountDetails.getAccountCategory()).isEqualTo(DEFAULT_ACCOUNT_CATEGORY);
        assertThat(testAccountDetails.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccountDetails.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testAccountDetails.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAccountDetails.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testAccountDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAccountDetails.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testAccountDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAccountDetails.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testAccountDetails.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAccountDetails.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAccountDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAccountDetails.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testAccountDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testAccountDetails.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testAccountDetails.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAccountDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testAccountDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testAccountDetails.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testAccountDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAccountDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAccountDetails.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testAccountDetails.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testAccountDetails.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testAccountDetails.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAccountDetails.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testAccountDetails.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testAccountDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testAccountDetails.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAccountDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAccountDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testAccountDetails.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testAccountDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testAccountDetails.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testAccountDetails.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testAccountDetails.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
        assertThat(testAccountDetails.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
        assertThat(testAccountDetails.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testAccountDetails.getTranFreeField33()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_33);
        assertThat(testAccountDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAccountDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAccountDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAccountDetails.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();

        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();

        // Update the accountDetails using partial update
        AccountDetails partialUpdatedAccountDetails = new AccountDetails();
        partialUpdatedAccountDetails.setId(accountDetails.getId());

        partialUpdatedAccountDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .requestId(UPDATED_REQUEST_ID)
            .accountName(UPDATED_ACCOUNT_NAME)
            .returnCode(UPDATED_RETURN_CODE)
            .returnMessage(UPDATED_RETURN_MESSAGE)
            .externalTXNid(UPDATED_EXTERNAL_TX_NID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerProduct(UPDATED_CUSTOMER_PRODUCT)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .accountCategory(UPDATED_ACCOUNT_CATEGORY)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .channel(UPDATED_CHANNEL)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
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
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .tranFreeField29(UPDATED_TRAN_FREE_FIELD_29)
            .tranFreeField30(UPDATED_TRAN_FREE_FIELD_30)
            .tranFreeField31(UPDATED_TRAN_FREE_FIELD_31)
            .tranFreeField32(UPDATED_TRAN_FREE_FIELD_32)
            .tranFreeField33(UPDATED_TRAN_FREE_FIELD_33)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        AccountDetails testAccountDetails = accountDetailsList.get(accountDetailsList.size() - 1);
        assertThat(testAccountDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAccountDetails.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testAccountDetails.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testAccountDetails.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testAccountDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAccountDetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAccountDetails.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testAccountDetails.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testAccountDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountDetails.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testAccountDetails.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testAccountDetails.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
        assertThat(testAccountDetails.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testAccountDetails.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testAccountDetails.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testAccountDetails.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
        assertThat(testAccountDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testAccountDetails.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testAccountDetails.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAccountDetails.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testAccountDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAccountDetails.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testAccountDetails.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testAccountDetails.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testAccountDetails.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAccountDetails.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testAccountDetails.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testAccountDetails.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testAccountDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testAccountDetails.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testAccountDetails.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAccountDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testAccountDetails.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testAccountDetails.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testAccountDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAccountDetails.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testAccountDetails.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testAccountDetails.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testAccountDetails.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testAccountDetails.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAccountDetails.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testAccountDetails.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testAccountDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testAccountDetails.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAccountDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAccountDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testAccountDetails.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testAccountDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testAccountDetails.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testAccountDetails.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
        assertThat(testAccountDetails.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
        assertThat(testAccountDetails.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
        assertThat(testAccountDetails.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
        assertThat(testAccountDetails.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
        assertThat(testAccountDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAccountDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAccountDetails.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAccountDetails.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, accountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        accountDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(accountDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AccountDetails in the database
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteAccountDetails() {
        // Initialize the database
        accountDetailsRepository.save(accountDetails).block();
        accountDetailsRepository.save(accountDetails).block();
        accountDetailsSearchRepository.save(accountDetails).block();

        int databaseSizeBeforeDelete = accountDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the accountDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, accountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AccountDetails> accountDetailsList = accountDetailsRepository.findAll().collectList().block();
        assertThat(accountDetailsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(accountDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchAccountDetails() {
        // Initialize the database
        accountDetails = accountDetailsRepository.save(accountDetails).block();
        accountDetailsSearchRepository.save(accountDetails).block();

        // Search the accountDetails
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + accountDetails.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(accountDetails.getId().intValue()))
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
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].requestId")
            .value(hasItem(DEFAULT_REQUEST_ID))
            .jsonPath("$.[*].accountName")
            .value(hasItem(DEFAULT_ACCOUNT_NAME))
            .jsonPath("$.[*].returnCode")
            .value(hasItem(DEFAULT_RETURN_CODE))
            .jsonPath("$.[*].returnMessage")
            .value(hasItem(DEFAULT_RETURN_MESSAGE))
            .jsonPath("$.[*].externalTXNid")
            .value(hasItem(DEFAULT_EXTERNAL_TX_NID))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID))
            .jsonPath("$.[*].customerProduct")
            .value(hasItem(DEFAULT_CUSTOMER_PRODUCT))
            .jsonPath("$.[*].customerType")
            .value(hasItem(DEFAULT_CUSTOMER_TYPE))
            .jsonPath("$.[*].accountCategory")
            .value(hasItem(DEFAULT_ACCOUNT_CATEGORY))
            .jsonPath("$.[*].accountType")
            .value(hasItem(DEFAULT_ACCOUNT_TYPE))
            .jsonPath("$.[*].accountNumber")
            .value(hasItem(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].channel")
            .value(hasItem(DEFAULT_CHANNEL))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField2")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_2))
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
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.[*].tranFreeField23ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.[*].tranFreeField25")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.[*].tranFreeField26")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.[*].tranFreeField27")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.[*].tranFreeField28")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_28))
            .jsonPath("$.[*].tranFreeField29")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_29))
            .jsonPath("$.[*].tranFreeField30")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_30))
            .jsonPath("$.[*].tranFreeField31")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_31))
            .jsonPath("$.[*].tranFreeField32")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_32))
            .jsonPath("$.[*].tranFreeField33")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_33))
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
