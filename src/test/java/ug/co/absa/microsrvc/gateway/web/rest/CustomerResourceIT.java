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
import ug.co.absa.microsrvc.gateway.domain.Customer;
import ug.co.absa.microsrvc.gateway.repository.CustomerRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.CustomerSearchRepository;

/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CustomerResourceIT {

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

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/customers";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSearchRepository customerSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Customer customer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
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
        return customer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
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
        return customer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Customer.class).block();
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
        customerSearchRepository.deleteAll().block();
        assertThat(customerSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        customer = createEntity(em);
    }

    @Test
    void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // Create the Customer
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCustomer.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testCustomer.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testCustomer.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testCustomer.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testCustomer.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCustomer.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testCustomer.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testCustomer.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testCustomer.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testCustomer.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testCustomer.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testCustomer.getExternalTXNid()).isEqualTo(DEFAULT_EXTERNAL_TX_NID);
        assertThat(testCustomer.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testCustomer.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCustomer.getCustomerProduct()).isEqualTo(DEFAULT_CUSTOMER_PRODUCT);
        assertThat(testCustomer.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testCustomer.getAccountCategory()).isEqualTo(DEFAULT_ACCOUNT_CATEGORY);
        assertThat(testCustomer.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testCustomer.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCustomer.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testCustomer.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testCustomer.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testCustomer.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testCustomer.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testCustomer.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testCustomer.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testCustomer.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testCustomer.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testCustomer.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testCustomer.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testCustomer.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testCustomer.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testCustomer.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testCustomer.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testCustomer.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testCustomer.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testCustomer.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testCustomer.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testCustomer.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testCustomer.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testCustomer.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testCustomer.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testCustomer.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testCustomer.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testCustomer.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testCustomer.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testCustomer.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testCustomer.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testCustomer.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testCustomer.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testCustomer.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testCustomer.getTranFreeField30()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_30);
        assertThat(testCustomer.getTranFreeField31()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_31);
        assertThat(testCustomer.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testCustomer.getTranFreeField33()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_33);
        assertThat(testCustomer.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomer.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomer.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createCustomerWithExistingId() throws Exception {
        // Create the Customer with an existing ID
        customer.setId(1L);

        int databaseSizeBeforeCreate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setBillerId(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setPaymentItemCode(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setAccountName(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReturnCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setReturnCode(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkReturnMessageIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setReturnMessage(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkExternalTXNidIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setExternalTXNid(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTransactionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setTransactionDate(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setCustomerId(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerProductIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setCustomerProduct(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCustomerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setCustomerType(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setAccountCategory(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setAccountType(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setAccountNumber(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setPhoneNumber(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        // set the field null
        customer.setChannel(null);

        // Create the Customer, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllCustomersAsStream() {
        // Initialize the database
        customerRepository.save(customer).block();

        List<Customer> customerList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Customer.class)
            .getResponseBody()
            .filter(customer::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(customerList).isNotNull();
        assertThat(customerList).hasSize(1);
        Customer testCustomer = customerList.get(0);
        assertThat(testCustomer.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testCustomer.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testCustomer.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testCustomer.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testCustomer.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testCustomer.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCustomer.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testCustomer.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testCustomer.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testCustomer.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testCustomer.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testCustomer.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testCustomer.getExternalTXNid()).isEqualTo(DEFAULT_EXTERNAL_TX_NID);
        assertThat(testCustomer.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testCustomer.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCustomer.getCustomerProduct()).isEqualTo(DEFAULT_CUSTOMER_PRODUCT);
        assertThat(testCustomer.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testCustomer.getAccountCategory()).isEqualTo(DEFAULT_ACCOUNT_CATEGORY);
        assertThat(testCustomer.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testCustomer.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCustomer.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testCustomer.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testCustomer.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testCustomer.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testCustomer.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testCustomer.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testCustomer.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testCustomer.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testCustomer.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testCustomer.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testCustomer.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testCustomer.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testCustomer.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testCustomer.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testCustomer.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testCustomer.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testCustomer.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testCustomer.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testCustomer.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testCustomer.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testCustomer.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testCustomer.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testCustomer.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testCustomer.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testCustomer.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testCustomer.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testCustomer.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testCustomer.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testCustomer.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testCustomer.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testCustomer.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testCustomer.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testCustomer.getTranFreeField30()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_30);
        assertThat(testCustomer.getTranFreeField31()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_31);
        assertThat(testCustomer.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testCustomer.getTranFreeField33()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_33);
        assertThat(testCustomer.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testCustomer.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testCustomer.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllCustomers() {
        // Initialize the database
        customerRepository.save(customer).block();

        // Get all the customerList
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
            .value(hasItem(customer.getId().intValue()))
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
    void getCustomer() {
        // Initialize the database
        customerRepository.save(customer).block();

        // Get the customer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, customer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(customer.getId().intValue()))
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
    void getNonExistingCustomer() {
        // Get the customer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCustomer() throws Exception {
        // Initialize the database
        customerRepository.save(customer).block();

        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        customerSearchRepository.save(customer).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).block();
        updatedCustomer
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
            .uri(ENTITY_API_URL_ID, updatedCustomer.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCustomer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomer.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testCustomer.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testCustomer.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testCustomer.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testCustomer.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCustomer.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testCustomer.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testCustomer.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testCustomer.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testCustomer.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testCustomer.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testCustomer.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
        assertThat(testCustomer.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testCustomer.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testCustomer.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testCustomer.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testCustomer.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
        assertThat(testCustomer.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testCustomer.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testCustomer.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCustomer.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testCustomer.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testCustomer.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testCustomer.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testCustomer.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testCustomer.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testCustomer.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testCustomer.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testCustomer.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testCustomer.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testCustomer.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testCustomer.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testCustomer.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testCustomer.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testCustomer.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testCustomer.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testCustomer.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testCustomer.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testCustomer.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testCustomer.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testCustomer.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testCustomer.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testCustomer.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testCustomer.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testCustomer.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testCustomer.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testCustomer.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testCustomer.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testCustomer.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testCustomer.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testCustomer.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
        assertThat(testCustomer.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
        assertThat(testCustomer.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
        assertThat(testCustomer.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
        assertThat(testCustomer.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
        assertThat(testCustomer.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomer.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Customer> customerSearchList = IterableUtils.toList(customerSearchRepository.findAll().collectList().block());
                Customer testCustomerSearch = customerSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testCustomerSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testCustomerSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testCustomerSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testCustomerSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testCustomerSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testCustomerSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testCustomerSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testCustomerSearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testCustomerSearch.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
                assertThat(testCustomerSearch.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
                assertThat(testCustomerSearch.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
                assertThat(testCustomerSearch.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
                assertThat(testCustomerSearch.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
                assertThat(testCustomerSearch.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
                assertThat(testCustomerSearch.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
                assertThat(testCustomerSearch.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
                assertThat(testCustomerSearch.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
                assertThat(testCustomerSearch.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
                assertThat(testCustomerSearch.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
                assertThat(testCustomerSearch.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
                assertThat(testCustomerSearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testCustomerSearch.getChannel()).isEqualTo(UPDATED_CHANNEL);
                assertThat(testCustomerSearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testCustomerSearch.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
                assertThat(testCustomerSearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testCustomerSearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testCustomerSearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testCustomerSearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testCustomerSearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testCustomerSearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testCustomerSearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testCustomerSearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testCustomerSearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testCustomerSearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testCustomerSearch.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
                assertThat(testCustomerSearch.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
                assertThat(testCustomerSearch.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
                assertThat(testCustomerSearch.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
                assertThat(testCustomerSearch.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
                assertThat(testCustomerSearch.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
                assertThat(testCustomerSearch.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
                assertThat(testCustomerSearch.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
                assertThat(testCustomerSearch.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
                assertThat(testCustomerSearch.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
                assertThat(testCustomerSearch.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
                assertThat(testCustomerSearch.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
                assertThat(testCustomerSearch.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
                assertThat(testCustomerSearch.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
                assertThat(testCustomerSearch.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
                assertThat(testCustomerSearch.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
                assertThat(testCustomerSearch.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
                assertThat(testCustomerSearch.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
                assertThat(testCustomerSearch.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
                assertThat(testCustomerSearch.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
                assertThat(testCustomerSearch.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
                assertThat(testCustomerSearch.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
                assertThat(testCustomerSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testCustomerSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testCustomerSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testCustomerSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, customer.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customerRepository.save(customer).block();

        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transferId(UPDATED_TRANSFER_ID)
            .requestId(UPDATED_REQUEST_ID)
            .externalTXNid(UPDATED_EXTERNAL_TX_NID)
            .customerProduct(UPDATED_CUSTOMER_PRODUCT)
            .accountCategory(UPDATED_ACCOUNT_CATEGORY)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField33(UPDATED_TRAN_FREE_FIELD_33)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomer.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testCustomer.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testCustomer.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testCustomer.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testCustomer.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCustomer.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testCustomer.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testCustomer.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testCustomer.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testCustomer.getReturnCode()).isEqualTo(DEFAULT_RETURN_CODE);
        assertThat(testCustomer.getReturnMessage()).isEqualTo(DEFAULT_RETURN_MESSAGE);
        assertThat(testCustomer.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
        assertThat(testCustomer.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testCustomer.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCustomer.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testCustomer.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testCustomer.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
        assertThat(testCustomer.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testCustomer.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testCustomer.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCustomer.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testCustomer.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testCustomer.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testCustomer.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testCustomer.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testCustomer.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testCustomer.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testCustomer.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testCustomer.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testCustomer.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testCustomer.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testCustomer.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testCustomer.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testCustomer.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testCustomer.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testCustomer.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testCustomer.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testCustomer.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testCustomer.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testCustomer.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testCustomer.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testCustomer.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testCustomer.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testCustomer.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testCustomer.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testCustomer.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testCustomer.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testCustomer.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testCustomer.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testCustomer.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testCustomer.getTranFreeField29()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_29);
        assertThat(testCustomer.getTranFreeField30()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_30);
        assertThat(testCustomer.getTranFreeField31()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_31);
        assertThat(testCustomer.getTranFreeField32()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_32);
        assertThat(testCustomer.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
        assertThat(testCustomer.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomer.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customerRepository.save(customer).block();

        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
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
            .uri(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testCustomer.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testCustomer.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testCustomer.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testCustomer.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testCustomer.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testCustomer.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testCustomer.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testCustomer.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testCustomer.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testCustomer.getReturnCode()).isEqualTo(UPDATED_RETURN_CODE);
        assertThat(testCustomer.getReturnMessage()).isEqualTo(UPDATED_RETURN_MESSAGE);
        assertThat(testCustomer.getExternalTXNid()).isEqualTo(UPDATED_EXTERNAL_TX_NID);
        assertThat(testCustomer.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testCustomer.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testCustomer.getCustomerProduct()).isEqualTo(UPDATED_CUSTOMER_PRODUCT);
        assertThat(testCustomer.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testCustomer.getAccountCategory()).isEqualTo(UPDATED_ACCOUNT_CATEGORY);
        assertThat(testCustomer.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testCustomer.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testCustomer.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testCustomer.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testCustomer.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testCustomer.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testCustomer.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testCustomer.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testCustomer.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testCustomer.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testCustomer.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testCustomer.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testCustomer.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testCustomer.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testCustomer.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testCustomer.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testCustomer.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testCustomer.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testCustomer.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testCustomer.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testCustomer.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testCustomer.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testCustomer.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testCustomer.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testCustomer.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testCustomer.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testCustomer.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testCustomer.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testCustomer.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testCustomer.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testCustomer.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testCustomer.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testCustomer.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testCustomer.getTranFreeField29()).isEqualTo(UPDATED_TRAN_FREE_FIELD_29);
        assertThat(testCustomer.getTranFreeField30()).isEqualTo(UPDATED_TRAN_FREE_FIELD_30);
        assertThat(testCustomer.getTranFreeField31()).isEqualTo(UPDATED_TRAN_FREE_FIELD_31);
        assertThat(testCustomer.getTranFreeField32()).isEqualTo(UPDATED_TRAN_FREE_FIELD_32);
        assertThat(testCustomer.getTranFreeField33()).isEqualTo(UPDATED_TRAN_FREE_FIELD_33);
        assertThat(testCustomer.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testCustomer.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testCustomer.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, customer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        customer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(customer))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteCustomer() {
        // Initialize the database
        customerRepository.save(customer).block();
        customerRepository.save(customer).block();
        customerSearchRepository.save(customer).block();

        int databaseSizeBeforeDelete = customerRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the customer
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, customer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll().collectList().block();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(customerSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchCustomer() {
        // Initialize the database
        customer = customerRepository.save(customer).block();
        customerSearchRepository.save(customer).block();

        // Search the customer
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + customer.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(customer.getId().intValue()))
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
