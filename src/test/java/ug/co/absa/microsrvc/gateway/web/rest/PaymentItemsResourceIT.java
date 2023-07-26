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
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.PaymentItemsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.PaymentItemsSearchRepository;

/**
 * Integration tests for the {@link PaymentItemsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PaymentItemsResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRODUCT_CATEGORY_ID = 1;
    private static final Integer UPDATED_PRODUCT_CATEGORY_ID = 2;

    private static final Integer DEFAULT_BILLER_ID = 1;
    private static final Integer UPDATED_BILLER_ID = 2;

    private static final String DEFAULT_PAYMENT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYMENT_ITEM_ID = 1;
    private static final Integer UPDATED_PAYMENT_ITEM_ID = 2;

    private static final String DEFAULT_PAYMENT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_HAS_FIXED_PRICE = false;
    private static final Boolean UPDATED_HAS_FIXED_PRICE = true;

    private static final Boolean DEFAULT_HAS_VARIABLE_PRICE = false;
    private static final Boolean UPDATED_HAS_VARIABLE_PRICE = true;

    private static final Boolean DEFAULT_HAS_DISCOUNT = false;
    private static final Boolean UPDATED_HAS_DISCOUNT = true;

    private static final Boolean DEFAULT_HAS_TAX = false;
    private static final Boolean UPDATED_HAS_TAX = true;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Double DEFAULT_CHARGE_AMOUNT = 1D;
    private static final Double UPDATED_CHARGE_AMOUNT = 2D;

    private static final Boolean DEFAULT_HAS_CHARGE_AMOUNT = false;
    private static final Boolean UPDATED_HAS_CHARGE_AMOUNT = true;

    private static final Double DEFAULT_TAX_AMOUNT = 1D;
    private static final Double UPDATED_TAX_AMOUNT = 2D;

    private static final String DEFAULT_FREE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FREE_TEXT = "BBBBBBBBBB";

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

    private static final Boolean DEFAULT_DELFLG = false;
    private static final Boolean UPDATED_DELFLG = true;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DELETED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DELETED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DELETED_BY = "AAAAAAAAAA";
    private static final String UPDATED_DELETED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/payment-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/payment-items";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PaymentItemsRepository paymentItemsRepository;

    @Autowired
    private PaymentItemsSearchRepository paymentItemsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PaymentItems paymentItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentItems createEntity(EntityManager em) {
        PaymentItems paymentItems = new PaymentItems()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .productCategoryId(DEFAULT_PRODUCT_CATEGORY_ID)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .paymentItemId(DEFAULT_PAYMENT_ITEM_ID)
            .paymentItemName(DEFAULT_PAYMENT_ITEM_NAME)
            .paymentItemDescription(DEFAULT_PAYMENT_ITEM_DESCRIPTION)
            .isActive(DEFAULT_IS_ACTIVE)
            .hasFixedPrice(DEFAULT_HAS_FIXED_PRICE)
            .hasVariablePrice(DEFAULT_HAS_VARIABLE_PRICE)
            .hasDiscount(DEFAULT_HAS_DISCOUNT)
            .hasTax(DEFAULT_HAS_TAX)
            .amount(DEFAULT_AMOUNT)
            .chargeAmount(DEFAULT_CHARGE_AMOUNT)
            .hasChargeAmount(DEFAULT_HAS_CHARGE_AMOUNT)
            .taxAmount(DEFAULT_TAX_AMOUNT)
            .freeText(DEFAULT_FREE_TEXT)
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
            .delflg(DEFAULT_DELFLG)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .deletedAt(DEFAULT_DELETED_AT)
            .deletedBy(DEFAULT_DELETED_BY);
        return paymentItems;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentItems createUpdatedEntity(EntityManager em) {
        PaymentItems paymentItems = new PaymentItems()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryId(UPDATED_PRODUCT_CATEGORY_ID)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .paymentItemId(UPDATED_PAYMENT_ITEM_ID)
            .paymentItemName(UPDATED_PAYMENT_ITEM_NAME)
            .paymentItemDescription(UPDATED_PAYMENT_ITEM_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .hasFixedPrice(UPDATED_HAS_FIXED_PRICE)
            .hasVariablePrice(UPDATED_HAS_VARIABLE_PRICE)
            .hasDiscount(UPDATED_HAS_DISCOUNT)
            .hasTax(UPDATED_HAS_TAX)
            .amount(UPDATED_AMOUNT)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .hasChargeAmount(UPDATED_HAS_CHARGE_AMOUNT)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .freeText(UPDATED_FREE_TEXT)
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
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT)
            .deletedBy(UPDATED_DELETED_BY);
        return paymentItems;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PaymentItems.class).block();
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
        paymentItemsSearchRepository.deleteAll().block();
        assertThat(paymentItemsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        paymentItems = createEntity(em);
    }

    @Test
    void createPaymentItems() throws Exception {
        int databaseSizeBeforeCreate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        // Create the PaymentItems
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        PaymentItems testPaymentItems = paymentItemsList.get(paymentItemsList.size() - 1);
        assertThat(testPaymentItems.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testPaymentItems.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testPaymentItems.getProductCategoryId()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_ID);
        assertThat(testPaymentItems.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testPaymentItems.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testPaymentItems.getPaymentItemId()).isEqualTo(DEFAULT_PAYMENT_ITEM_ID);
        assertThat(testPaymentItems.getPaymentItemName()).isEqualTo(DEFAULT_PAYMENT_ITEM_NAME);
        assertThat(testPaymentItems.getPaymentItemDescription()).isEqualTo(DEFAULT_PAYMENT_ITEM_DESCRIPTION);
        assertThat(testPaymentItems.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testPaymentItems.getHasFixedPrice()).isEqualTo(DEFAULT_HAS_FIXED_PRICE);
        assertThat(testPaymentItems.getHasVariablePrice()).isEqualTo(DEFAULT_HAS_VARIABLE_PRICE);
        assertThat(testPaymentItems.getHasDiscount()).isEqualTo(DEFAULT_HAS_DISCOUNT);
        assertThat(testPaymentItems.getHasTax()).isEqualTo(DEFAULT_HAS_TAX);
        assertThat(testPaymentItems.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentItems.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getHasChargeAmount()).isEqualTo(DEFAULT_HAS_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testPaymentItems.getFreeText()).isEqualTo(DEFAULT_FREE_TEXT);
        assertThat(testPaymentItems.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testPaymentItems.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testPaymentItems.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testPaymentItems.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testPaymentItems.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testPaymentItems.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testPaymentItems.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testPaymentItems.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testPaymentItems.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testPaymentItems.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testPaymentItems.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testPaymentItems.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testPaymentItems.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testPaymentItems.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testPaymentItems.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testPaymentItems.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testPaymentItems.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testPaymentItems.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testPaymentItems.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testPaymentItems.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testPaymentItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPaymentItems.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentItems.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaymentItems.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
        assertThat(testPaymentItems.getDeletedBy()).isEqualTo(DEFAULT_DELETED_BY);
    }

    @Test
    void createPaymentItemsWithExistingId() throws Exception {
        // Create the PaymentItems with an existing ID
        paymentItems.setId(1L);

        int databaseSizeBeforeCreate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllPaymentItemsAsStream() {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        List<PaymentItems> paymentItemsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(PaymentItems.class)
            .getResponseBody()
            .filter(paymentItems::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(paymentItemsList).isNotNull();
        assertThat(paymentItemsList).hasSize(1);
        PaymentItems testPaymentItems = paymentItemsList.get(0);
        assertThat(testPaymentItems.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testPaymentItems.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testPaymentItems.getProductCategoryId()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_ID);
        assertThat(testPaymentItems.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testPaymentItems.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testPaymentItems.getPaymentItemId()).isEqualTo(DEFAULT_PAYMENT_ITEM_ID);
        assertThat(testPaymentItems.getPaymentItemName()).isEqualTo(DEFAULT_PAYMENT_ITEM_NAME);
        assertThat(testPaymentItems.getPaymentItemDescription()).isEqualTo(DEFAULT_PAYMENT_ITEM_DESCRIPTION);
        assertThat(testPaymentItems.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testPaymentItems.getHasFixedPrice()).isEqualTo(DEFAULT_HAS_FIXED_PRICE);
        assertThat(testPaymentItems.getHasVariablePrice()).isEqualTo(DEFAULT_HAS_VARIABLE_PRICE);
        assertThat(testPaymentItems.getHasDiscount()).isEqualTo(DEFAULT_HAS_DISCOUNT);
        assertThat(testPaymentItems.getHasTax()).isEqualTo(DEFAULT_HAS_TAX);
        assertThat(testPaymentItems.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentItems.getChargeAmount()).isEqualTo(DEFAULT_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getHasChargeAmount()).isEqualTo(DEFAULT_HAS_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testPaymentItems.getFreeText()).isEqualTo(DEFAULT_FREE_TEXT);
        assertThat(testPaymentItems.getFreeText1()).isEqualTo(DEFAULT_FREE_TEXT_1);
        assertThat(testPaymentItems.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testPaymentItems.getFreeText3()).isEqualTo(DEFAULT_FREE_TEXT_3);
        assertThat(testPaymentItems.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testPaymentItems.getFreeText5()).isEqualTo(DEFAULT_FREE_TEXT_5);
        assertThat(testPaymentItems.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testPaymentItems.getFreeText7()).isEqualTo(DEFAULT_FREE_TEXT_7);
        assertThat(testPaymentItems.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testPaymentItems.getFreeText9()).isEqualTo(DEFAULT_FREE_TEXT_9);
        assertThat(testPaymentItems.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testPaymentItems.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testPaymentItems.getFreeText12()).isEqualTo(DEFAULT_FREE_TEXT_12);
        assertThat(testPaymentItems.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testPaymentItems.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testPaymentItems.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testPaymentItems.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testPaymentItems.getFreeText17()).isEqualTo(DEFAULT_FREE_TEXT_17);
        assertThat(testPaymentItems.getFreeText18()).isEqualTo(DEFAULT_FREE_TEXT_18);
        assertThat(testPaymentItems.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testPaymentItems.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testPaymentItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPaymentItems.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentItems.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testPaymentItems.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
        assertThat(testPaymentItems.getDeletedBy()).isEqualTo(DEFAULT_DELETED_BY);
    }

    @Test
    void getAllPaymentItems() {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        // Get all the paymentItemsList
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
            .value(hasItem(paymentItems.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].productCategoryId")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_ID))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].paymentItemId")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_ID))
            .jsonPath("$.[*].paymentItemName")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_NAME))
            .jsonPath("$.[*].paymentItemDescription")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_DESCRIPTION))
            .jsonPath("$.[*].isActive")
            .value(hasItem(DEFAULT_IS_ACTIVE.booleanValue()))
            .jsonPath("$.[*].hasFixedPrice")
            .value(hasItem(DEFAULT_HAS_FIXED_PRICE.booleanValue()))
            .jsonPath("$.[*].hasVariablePrice")
            .value(hasItem(DEFAULT_HAS_VARIABLE_PRICE.booleanValue()))
            .jsonPath("$.[*].hasDiscount")
            .value(hasItem(DEFAULT_HAS_DISCOUNT.booleanValue()))
            .jsonPath("$.[*].hasTax")
            .value(hasItem(DEFAULT_HAS_TAX.booleanValue()))
            .jsonPath("$.[*].amount")
            .value(hasItem(DEFAULT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].chargeAmount")
            .value(hasItem(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].hasChargeAmount")
            .value(hasItem(DEFAULT_HAS_CHARGE_AMOUNT.booleanValue()))
            .jsonPath("$.[*].taxAmount")
            .value(hasItem(DEFAULT_TAX_AMOUNT.doubleValue()))
            .jsonPath("$.[*].freeText")
            .value(hasItem(DEFAULT_FREE_TEXT))
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
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].deletedAt")
            .value(hasItem(sameInstant(DEFAULT_DELETED_AT)))
            .jsonPath("$.[*].deletedBy")
            .value(hasItem(DEFAULT_DELETED_BY));
    }

    @Test
    void getPaymentItems() {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        // Get the paymentItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, paymentItems.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(paymentItems.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.productCategoryId")
            .value(is(DEFAULT_PRODUCT_CATEGORY_ID))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.paymentItemCode")
            .value(is(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.paymentItemId")
            .value(is(DEFAULT_PAYMENT_ITEM_ID))
            .jsonPath("$.paymentItemName")
            .value(is(DEFAULT_PAYMENT_ITEM_NAME))
            .jsonPath("$.paymentItemDescription")
            .value(is(DEFAULT_PAYMENT_ITEM_DESCRIPTION))
            .jsonPath("$.isActive")
            .value(is(DEFAULT_IS_ACTIVE.booleanValue()))
            .jsonPath("$.hasFixedPrice")
            .value(is(DEFAULT_HAS_FIXED_PRICE.booleanValue()))
            .jsonPath("$.hasVariablePrice")
            .value(is(DEFAULT_HAS_VARIABLE_PRICE.booleanValue()))
            .jsonPath("$.hasDiscount")
            .value(is(DEFAULT_HAS_DISCOUNT.booleanValue()))
            .jsonPath("$.hasTax")
            .value(is(DEFAULT_HAS_TAX.booleanValue()))
            .jsonPath("$.amount")
            .value(is(DEFAULT_AMOUNT.doubleValue()))
            .jsonPath("$.chargeAmount")
            .value(is(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.hasChargeAmount")
            .value(is(DEFAULT_HAS_CHARGE_AMOUNT.booleanValue()))
            .jsonPath("$.taxAmount")
            .value(is(DEFAULT_TAX_AMOUNT.doubleValue()))
            .jsonPath("$.freeText")
            .value(is(DEFAULT_FREE_TEXT))
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
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.deletedAt")
            .value(is(sameInstant(DEFAULT_DELETED_AT)))
            .jsonPath("$.deletedBy")
            .value(is(DEFAULT_DELETED_BY));
    }

    @Test
    void getNonExistingPaymentItems() {
        // Get the paymentItems
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPaymentItems() throws Exception {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        paymentItemsSearchRepository.save(paymentItems).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());

        // Update the paymentItems
        PaymentItems updatedPaymentItems = paymentItemsRepository.findById(paymentItems.getId()).block();
        updatedPaymentItems
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryId(UPDATED_PRODUCT_CATEGORY_ID)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .paymentItemId(UPDATED_PAYMENT_ITEM_ID)
            .paymentItemName(UPDATED_PAYMENT_ITEM_NAME)
            .paymentItemDescription(UPDATED_PAYMENT_ITEM_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .hasFixedPrice(UPDATED_HAS_FIXED_PRICE)
            .hasVariablePrice(UPDATED_HAS_VARIABLE_PRICE)
            .hasDiscount(UPDATED_HAS_DISCOUNT)
            .hasTax(UPDATED_HAS_TAX)
            .amount(UPDATED_AMOUNT)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .hasChargeAmount(UPDATED_HAS_CHARGE_AMOUNT)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .freeText(UPDATED_FREE_TEXT)
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
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT)
            .deletedBy(UPDATED_DELETED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedPaymentItems.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedPaymentItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        PaymentItems testPaymentItems = paymentItemsList.get(paymentItemsList.size() - 1);
        assertThat(testPaymentItems.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testPaymentItems.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testPaymentItems.getProductCategoryId()).isEqualTo(UPDATED_PRODUCT_CATEGORY_ID);
        assertThat(testPaymentItems.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testPaymentItems.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testPaymentItems.getPaymentItemId()).isEqualTo(UPDATED_PAYMENT_ITEM_ID);
        assertThat(testPaymentItems.getPaymentItemName()).isEqualTo(UPDATED_PAYMENT_ITEM_NAME);
        assertThat(testPaymentItems.getPaymentItemDescription()).isEqualTo(UPDATED_PAYMENT_ITEM_DESCRIPTION);
        assertThat(testPaymentItems.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPaymentItems.getHasFixedPrice()).isEqualTo(UPDATED_HAS_FIXED_PRICE);
        assertThat(testPaymentItems.getHasVariablePrice()).isEqualTo(UPDATED_HAS_VARIABLE_PRICE);
        assertThat(testPaymentItems.getHasDiscount()).isEqualTo(UPDATED_HAS_DISCOUNT);
        assertThat(testPaymentItems.getHasTax()).isEqualTo(UPDATED_HAS_TAX);
        assertThat(testPaymentItems.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentItems.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getHasChargeAmount()).isEqualTo(UPDATED_HAS_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testPaymentItems.getFreeText()).isEqualTo(UPDATED_FREE_TEXT);
        assertThat(testPaymentItems.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testPaymentItems.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testPaymentItems.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testPaymentItems.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testPaymentItems.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testPaymentItems.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testPaymentItems.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testPaymentItems.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testPaymentItems.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testPaymentItems.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testPaymentItems.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testPaymentItems.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testPaymentItems.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testPaymentItems.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testPaymentItems.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testPaymentItems.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testPaymentItems.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testPaymentItems.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testPaymentItems.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testPaymentItems.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testPaymentItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPaymentItems.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentItems.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaymentItems.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
        assertThat(testPaymentItems.getDeletedBy()).isEqualTo(UPDATED_DELETED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<PaymentItems> paymentItemsSearchList = IterableUtils.toList(
                    paymentItemsSearchRepository.findAll().collectList().block()
                );
                PaymentItems testPaymentItemsSearch = paymentItemsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testPaymentItemsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testPaymentItemsSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testPaymentItemsSearch.getProductCategoryId()).isEqualTo(UPDATED_PRODUCT_CATEGORY_ID);
                assertThat(testPaymentItemsSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testPaymentItemsSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testPaymentItemsSearch.getPaymentItemId()).isEqualTo(UPDATED_PAYMENT_ITEM_ID);
                assertThat(testPaymentItemsSearch.getPaymentItemName()).isEqualTo(UPDATED_PAYMENT_ITEM_NAME);
                assertThat(testPaymentItemsSearch.getPaymentItemDescription()).isEqualTo(UPDATED_PAYMENT_ITEM_DESCRIPTION);
                assertThat(testPaymentItemsSearch.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
                assertThat(testPaymentItemsSearch.getHasFixedPrice()).isEqualTo(UPDATED_HAS_FIXED_PRICE);
                assertThat(testPaymentItemsSearch.getHasVariablePrice()).isEqualTo(UPDATED_HAS_VARIABLE_PRICE);
                assertThat(testPaymentItemsSearch.getHasDiscount()).isEqualTo(UPDATED_HAS_DISCOUNT);
                assertThat(testPaymentItemsSearch.getHasTax()).isEqualTo(UPDATED_HAS_TAX);
                assertThat(testPaymentItemsSearch.getAmount()).isEqualTo(UPDATED_AMOUNT);
                assertThat(testPaymentItemsSearch.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
                assertThat(testPaymentItemsSearch.getHasChargeAmount()).isEqualTo(UPDATED_HAS_CHARGE_AMOUNT);
                assertThat(testPaymentItemsSearch.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
                assertThat(testPaymentItemsSearch.getFreeText()).isEqualTo(UPDATED_FREE_TEXT);
                assertThat(testPaymentItemsSearch.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
                assertThat(testPaymentItemsSearch.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
                assertThat(testPaymentItemsSearch.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
                assertThat(testPaymentItemsSearch.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
                assertThat(testPaymentItemsSearch.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
                assertThat(testPaymentItemsSearch.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
                assertThat(testPaymentItemsSearch.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
                assertThat(testPaymentItemsSearch.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
                assertThat(testPaymentItemsSearch.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
                assertThat(testPaymentItemsSearch.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
                assertThat(testPaymentItemsSearch.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
                assertThat(testPaymentItemsSearch.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
                assertThat(testPaymentItemsSearch.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
                assertThat(testPaymentItemsSearch.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
                assertThat(testPaymentItemsSearch.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
                assertThat(testPaymentItemsSearch.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
                assertThat(testPaymentItemsSearch.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
                assertThat(testPaymentItemsSearch.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
                assertThat(testPaymentItemsSearch.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
                assertThat(testPaymentItemsSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testPaymentItemsSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testPaymentItemsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testPaymentItemsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testPaymentItemsSearch.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
                assertThat(testPaymentItemsSearch.getDeletedBy()).isEqualTo(UPDATED_DELETED_BY);
            });
    }

    @Test
    void putNonExistingPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, paymentItems.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdatePaymentItemsWithPatch() throws Exception {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();

        // Update the paymentItems using partial update
        PaymentItems partialUpdatedPaymentItems = new PaymentItems();
        partialUpdatedPaymentItems.setId(paymentItems.getId());

        partialUpdatedPaymentItems
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .paymentItemId(UPDATED_PAYMENT_ITEM_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .hasDiscount(UPDATED_HAS_DISCOUNT)
            .hasTax(UPDATED_HAS_TAX)
            .amount(UPDATED_AMOUNT)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .hasChargeAmount(UPDATED_HAS_CHARGE_AMOUNT)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .freeText1(UPDATED_FREE_TEXT_1)
            .freeText3(UPDATED_FREE_TEXT_3)
            .freeText5(UPDATED_FREE_TEXT_5)
            .freeText7(UPDATED_FREE_TEXT_7)
            .freeText9(UPDATED_FREE_TEXT_9)
            .freeText12(UPDATED_FREE_TEXT_12)
            .freeText17(UPDATED_FREE_TEXT_17)
            .freeText18(UPDATED_FREE_TEXT_18)
            .updatedAt(UPDATED_UPDATED_AT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPaymentItems.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPaymentItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        PaymentItems testPaymentItems = paymentItemsList.get(paymentItemsList.size() - 1);
        assertThat(testPaymentItems.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testPaymentItems.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testPaymentItems.getProductCategoryId()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_ID);
        assertThat(testPaymentItems.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testPaymentItems.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testPaymentItems.getPaymentItemId()).isEqualTo(UPDATED_PAYMENT_ITEM_ID);
        assertThat(testPaymentItems.getPaymentItemName()).isEqualTo(DEFAULT_PAYMENT_ITEM_NAME);
        assertThat(testPaymentItems.getPaymentItemDescription()).isEqualTo(DEFAULT_PAYMENT_ITEM_DESCRIPTION);
        assertThat(testPaymentItems.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPaymentItems.getHasFixedPrice()).isEqualTo(DEFAULT_HAS_FIXED_PRICE);
        assertThat(testPaymentItems.getHasVariablePrice()).isEqualTo(DEFAULT_HAS_VARIABLE_PRICE);
        assertThat(testPaymentItems.getHasDiscount()).isEqualTo(UPDATED_HAS_DISCOUNT);
        assertThat(testPaymentItems.getHasTax()).isEqualTo(UPDATED_HAS_TAX);
        assertThat(testPaymentItems.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentItems.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getHasChargeAmount()).isEqualTo(UPDATED_HAS_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testPaymentItems.getFreeText()).isEqualTo(DEFAULT_FREE_TEXT);
        assertThat(testPaymentItems.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testPaymentItems.getFreeText2()).isEqualTo(DEFAULT_FREE_TEXT_2);
        assertThat(testPaymentItems.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testPaymentItems.getFreeText4()).isEqualTo(DEFAULT_FREE_TEXT_4);
        assertThat(testPaymentItems.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testPaymentItems.getFreeText6()).isEqualTo(DEFAULT_FREE_TEXT_6);
        assertThat(testPaymentItems.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testPaymentItems.getFreeText8()).isEqualTo(DEFAULT_FREE_TEXT_8);
        assertThat(testPaymentItems.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testPaymentItems.getFreeText10()).isEqualTo(DEFAULT_FREE_TEXT_10);
        assertThat(testPaymentItems.getFreeText11()).isEqualTo(DEFAULT_FREE_TEXT_11);
        assertThat(testPaymentItems.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testPaymentItems.getFreeText13()).isEqualTo(DEFAULT_FREE_TEXT_13);
        assertThat(testPaymentItems.getFreeText14()).isEqualTo(DEFAULT_FREE_TEXT_14);
        assertThat(testPaymentItems.getFreeText15()).isEqualTo(DEFAULT_FREE_TEXT_15);
        assertThat(testPaymentItems.getFreeText16()).isEqualTo(DEFAULT_FREE_TEXT_16);
        assertThat(testPaymentItems.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testPaymentItems.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testPaymentItems.getFreeText19()).isEqualTo(DEFAULT_FREE_TEXT_19);
        assertThat(testPaymentItems.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testPaymentItems.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPaymentItems.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentItems.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaymentItems.getDeletedAt()).isEqualTo(DEFAULT_DELETED_AT);
        assertThat(testPaymentItems.getDeletedBy()).isEqualTo(DEFAULT_DELETED_BY);
    }

    @Test
    void fullUpdatePaymentItemsWithPatch() throws Exception {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();

        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();

        // Update the paymentItems using partial update
        PaymentItems partialUpdatedPaymentItems = new PaymentItems();
        partialUpdatedPaymentItems.setId(paymentItems.getId());

        partialUpdatedPaymentItems
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryId(UPDATED_PRODUCT_CATEGORY_ID)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .paymentItemId(UPDATED_PAYMENT_ITEM_ID)
            .paymentItemName(UPDATED_PAYMENT_ITEM_NAME)
            .paymentItemDescription(UPDATED_PAYMENT_ITEM_DESCRIPTION)
            .isActive(UPDATED_IS_ACTIVE)
            .hasFixedPrice(UPDATED_HAS_FIXED_PRICE)
            .hasVariablePrice(UPDATED_HAS_VARIABLE_PRICE)
            .hasDiscount(UPDATED_HAS_DISCOUNT)
            .hasTax(UPDATED_HAS_TAX)
            .amount(UPDATED_AMOUNT)
            .chargeAmount(UPDATED_CHARGE_AMOUNT)
            .hasChargeAmount(UPDATED_HAS_CHARGE_AMOUNT)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .freeText(UPDATED_FREE_TEXT)
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
            .delflg(UPDATED_DELFLG)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .deletedAt(UPDATED_DELETED_AT)
            .deletedBy(UPDATED_DELETED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPaymentItems.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPaymentItems))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        PaymentItems testPaymentItems = paymentItemsList.get(paymentItemsList.size() - 1);
        assertThat(testPaymentItems.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testPaymentItems.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testPaymentItems.getProductCategoryId()).isEqualTo(UPDATED_PRODUCT_CATEGORY_ID);
        assertThat(testPaymentItems.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testPaymentItems.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testPaymentItems.getPaymentItemId()).isEqualTo(UPDATED_PAYMENT_ITEM_ID);
        assertThat(testPaymentItems.getPaymentItemName()).isEqualTo(UPDATED_PAYMENT_ITEM_NAME);
        assertThat(testPaymentItems.getPaymentItemDescription()).isEqualTo(UPDATED_PAYMENT_ITEM_DESCRIPTION);
        assertThat(testPaymentItems.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testPaymentItems.getHasFixedPrice()).isEqualTo(UPDATED_HAS_FIXED_PRICE);
        assertThat(testPaymentItems.getHasVariablePrice()).isEqualTo(UPDATED_HAS_VARIABLE_PRICE);
        assertThat(testPaymentItems.getHasDiscount()).isEqualTo(UPDATED_HAS_DISCOUNT);
        assertThat(testPaymentItems.getHasTax()).isEqualTo(UPDATED_HAS_TAX);
        assertThat(testPaymentItems.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentItems.getChargeAmount()).isEqualTo(UPDATED_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getHasChargeAmount()).isEqualTo(UPDATED_HAS_CHARGE_AMOUNT);
        assertThat(testPaymentItems.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testPaymentItems.getFreeText()).isEqualTo(UPDATED_FREE_TEXT);
        assertThat(testPaymentItems.getFreeText1()).isEqualTo(UPDATED_FREE_TEXT_1);
        assertThat(testPaymentItems.getFreeText2()).isEqualTo(UPDATED_FREE_TEXT_2);
        assertThat(testPaymentItems.getFreeText3()).isEqualTo(UPDATED_FREE_TEXT_3);
        assertThat(testPaymentItems.getFreeText4()).isEqualTo(UPDATED_FREE_TEXT_4);
        assertThat(testPaymentItems.getFreeText5()).isEqualTo(UPDATED_FREE_TEXT_5);
        assertThat(testPaymentItems.getFreeText6()).isEqualTo(UPDATED_FREE_TEXT_6);
        assertThat(testPaymentItems.getFreeText7()).isEqualTo(UPDATED_FREE_TEXT_7);
        assertThat(testPaymentItems.getFreeText8()).isEqualTo(UPDATED_FREE_TEXT_8);
        assertThat(testPaymentItems.getFreeText9()).isEqualTo(UPDATED_FREE_TEXT_9);
        assertThat(testPaymentItems.getFreeText10()).isEqualTo(UPDATED_FREE_TEXT_10);
        assertThat(testPaymentItems.getFreeText11()).isEqualTo(UPDATED_FREE_TEXT_11);
        assertThat(testPaymentItems.getFreeText12()).isEqualTo(UPDATED_FREE_TEXT_12);
        assertThat(testPaymentItems.getFreeText13()).isEqualTo(UPDATED_FREE_TEXT_13);
        assertThat(testPaymentItems.getFreeText14()).isEqualTo(UPDATED_FREE_TEXT_14);
        assertThat(testPaymentItems.getFreeText15()).isEqualTo(UPDATED_FREE_TEXT_15);
        assertThat(testPaymentItems.getFreeText16()).isEqualTo(UPDATED_FREE_TEXT_16);
        assertThat(testPaymentItems.getFreeText17()).isEqualTo(UPDATED_FREE_TEXT_17);
        assertThat(testPaymentItems.getFreeText18()).isEqualTo(UPDATED_FREE_TEXT_18);
        assertThat(testPaymentItems.getFreeText19()).isEqualTo(UPDATED_FREE_TEXT_19);
        assertThat(testPaymentItems.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testPaymentItems.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPaymentItems.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentItems.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testPaymentItems.getDeletedAt()).isEqualTo(UPDATED_DELETED_AT);
        assertThat(testPaymentItems.getDeletedBy()).isEqualTo(UPDATED_DELETED_BY);
    }

    @Test
    void patchNonExistingPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, paymentItems.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamPaymentItems() throws Exception {
        int databaseSizeBeforeUpdate = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        paymentItems.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(paymentItems))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PaymentItems in the database
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deletePaymentItems() {
        // Initialize the database
        paymentItemsRepository.save(paymentItems).block();
        paymentItemsRepository.save(paymentItems).block();
        paymentItemsSearchRepository.save(paymentItems).block();

        int databaseSizeBeforeDelete = paymentItemsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the paymentItems
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, paymentItems.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PaymentItems> paymentItemsList = paymentItemsRepository.findAll().collectList().block();
        assertThat(paymentItemsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(paymentItemsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchPaymentItems() {
        // Initialize the database
        paymentItems = paymentItemsRepository.save(paymentItems).block();
        paymentItemsSearchRepository.save(paymentItems).block();

        // Search the paymentItems
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + paymentItems.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(paymentItems.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].productCategoryId")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_ID))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].paymentItemId")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_ID))
            .jsonPath("$.[*].paymentItemName")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_NAME))
            .jsonPath("$.[*].paymentItemDescription")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_DESCRIPTION))
            .jsonPath("$.[*].isActive")
            .value(hasItem(DEFAULT_IS_ACTIVE.booleanValue()))
            .jsonPath("$.[*].hasFixedPrice")
            .value(hasItem(DEFAULT_HAS_FIXED_PRICE.booleanValue()))
            .jsonPath("$.[*].hasVariablePrice")
            .value(hasItem(DEFAULT_HAS_VARIABLE_PRICE.booleanValue()))
            .jsonPath("$.[*].hasDiscount")
            .value(hasItem(DEFAULT_HAS_DISCOUNT.booleanValue()))
            .jsonPath("$.[*].hasTax")
            .value(hasItem(DEFAULT_HAS_TAX.booleanValue()))
            .jsonPath("$.[*].amount")
            .value(hasItem(DEFAULT_AMOUNT.doubleValue()))
            .jsonPath("$.[*].chargeAmount")
            .value(hasItem(DEFAULT_CHARGE_AMOUNT.doubleValue()))
            .jsonPath("$.[*].hasChargeAmount")
            .value(hasItem(DEFAULT_HAS_CHARGE_AMOUNT.booleanValue()))
            .jsonPath("$.[*].taxAmount")
            .value(hasItem(DEFAULT_TAX_AMOUNT.doubleValue()))
            .jsonPath("$.[*].freeText")
            .value(hasItem(DEFAULT_FREE_TEXT))
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
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].deletedAt")
            .value(hasItem(sameInstant(DEFAULT_DELETED_AT)))
            .jsonPath("$.[*].deletedBy")
            .value(hasItem(DEFAULT_DELETED_BY));
    }
}
