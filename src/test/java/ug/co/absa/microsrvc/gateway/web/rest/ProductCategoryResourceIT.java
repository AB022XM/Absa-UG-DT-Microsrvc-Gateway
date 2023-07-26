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
import ug.co.absa.microsrvc.gateway.domain.ProductCategory;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.ProductCategoryRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ProductCategorySearchRepository;

/**
 * Integration tests for the {@link ProductCategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ProductCategoryResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CATEGORY_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CATEGORY_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELFLG = false;
    private static final Boolean UPDATED_DELFLG = true;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/product-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/product-categories";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategorySearchRepository productCategorySearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ProductCategory productCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductCategory createEntity(EntityManager em) {
        ProductCategory productCategory = new ProductCategory()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .recordId(DEFAULT_RECORD_ID)
            .productCategoryCode(DEFAULT_PRODUCT_CATEGORY_CODE)
            .productCategoryName(DEFAULT_PRODUCT_CATEGORY_NAME)
            .productCategoryDescription(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION)
            .productCategoryImage(DEFAULT_PRODUCT_CATEGORY_IMAGE)
            .productFreeField1(DEFAULT_PRODUCT_FREE_FIELD_1)
            .productFreeField2(DEFAULT_PRODUCT_FREE_FIELD_2)
            .productFreeField3(DEFAULT_PRODUCT_FREE_FIELD_3)
            .productFreeField4(DEFAULT_PRODUCT_FREE_FIELD_4)
            .productFreeField5(DEFAULT_PRODUCT_FREE_FIELD_5)
            .productFreeField6(DEFAULT_PRODUCT_FREE_FIELD_6)
            .productFreeField7(DEFAULT_PRODUCT_FREE_FIELD_7)
            .productFreeField8(DEFAULT_PRODUCT_FREE_FIELD_8)
            .productFreeField9(DEFAULT_PRODUCT_FREE_FIELD_9)
            .productFreeField10(DEFAULT_PRODUCT_FREE_FIELD_10)
            .productFreeField11(DEFAULT_PRODUCT_FREE_FIELD_11)
            .delflg(DEFAULT_DELFLG)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return productCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductCategory createUpdatedEntity(EntityManager em) {
        ProductCategory productCategory = new ProductCategory()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryCode(UPDATED_PRODUCT_CATEGORY_CODE)
            .productCategoryName(UPDATED_PRODUCT_CATEGORY_NAME)
            .productCategoryDescription(UPDATED_PRODUCT_CATEGORY_DESCRIPTION)
            .productCategoryImage(UPDATED_PRODUCT_CATEGORY_IMAGE)
            .productFreeField1(UPDATED_PRODUCT_FREE_FIELD_1)
            .productFreeField2(UPDATED_PRODUCT_FREE_FIELD_2)
            .productFreeField3(UPDATED_PRODUCT_FREE_FIELD_3)
            .productFreeField4(UPDATED_PRODUCT_FREE_FIELD_4)
            .productFreeField5(UPDATED_PRODUCT_FREE_FIELD_5)
            .productFreeField6(UPDATED_PRODUCT_FREE_FIELD_6)
            .productFreeField7(UPDATED_PRODUCT_FREE_FIELD_7)
            .productFreeField8(UPDATED_PRODUCT_FREE_FIELD_8)
            .productFreeField9(UPDATED_PRODUCT_FREE_FIELD_9)
            .productFreeField10(UPDATED_PRODUCT_FREE_FIELD_10)
            .productFreeField11(UPDATED_PRODUCT_FREE_FIELD_11)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return productCategory;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ProductCategory.class).block();
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
        productCategorySearchRepository.deleteAll().block();
        assertThat(productCategorySearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        productCategory = createEntity(em);
    }

    @Test
    void createProductCategory() throws Exception {
        int databaseSizeBeforeCreate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        // Create the ProductCategory
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        ProductCategory testProductCategory = productCategoryList.get(productCategoryList.size() - 1);
        assertThat(testProductCategory.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testProductCategory.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testProductCategory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testProductCategory.getProductCategoryCode()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_CODE);
        assertThat(testProductCategory.getProductCategoryName()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_NAME);
        assertThat(testProductCategory.getProductCategoryDescription()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testProductCategory.getProductCategoryImage()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_IMAGE);
        assertThat(testProductCategory.getProductFreeField1()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_1);
        assertThat(testProductCategory.getProductFreeField2()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_2);
        assertThat(testProductCategory.getProductFreeField3()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_3);
        assertThat(testProductCategory.getProductFreeField4()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_4);
        assertThat(testProductCategory.getProductFreeField5()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_5);
        assertThat(testProductCategory.getProductFreeField6()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_6);
        assertThat(testProductCategory.getProductFreeField7()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_7);
        assertThat(testProductCategory.getProductFreeField8()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_8);
        assertThat(testProductCategory.getProductFreeField9()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_9);
        assertThat(testProductCategory.getProductFreeField10()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_10);
        assertThat(testProductCategory.getProductFreeField11()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_11);
        assertThat(testProductCategory.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testProductCategory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testProductCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductCategory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testProductCategory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createProductCategoryWithExistingId() throws Exception {
        // Create the ProductCategory with an existing ID
        productCategory.setId(1L);

        int databaseSizeBeforeCreate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkProductCategoryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        // set the field null
        productCategory.setProductCategoryCode(null);

        // Create the ProductCategory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkProductCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        // set the field null
        productCategory.setProductCategoryName(null);

        // Create the ProductCategory, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllProductCategoriesAsStream() {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        List<ProductCategory> productCategoryList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(ProductCategory.class)
            .getResponseBody()
            .filter(productCategory::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(productCategoryList).isNotNull();
        assertThat(productCategoryList).hasSize(1);
        ProductCategory testProductCategory = productCategoryList.get(0);
        assertThat(testProductCategory.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testProductCategory.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testProductCategory.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testProductCategory.getProductCategoryCode()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_CODE);
        assertThat(testProductCategory.getProductCategoryName()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_NAME);
        assertThat(testProductCategory.getProductCategoryDescription()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testProductCategory.getProductCategoryImage()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_IMAGE);
        assertThat(testProductCategory.getProductFreeField1()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_1);
        assertThat(testProductCategory.getProductFreeField2()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_2);
        assertThat(testProductCategory.getProductFreeField3()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_3);
        assertThat(testProductCategory.getProductFreeField4()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_4);
        assertThat(testProductCategory.getProductFreeField5()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_5);
        assertThat(testProductCategory.getProductFreeField6()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_6);
        assertThat(testProductCategory.getProductFreeField7()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_7);
        assertThat(testProductCategory.getProductFreeField8()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_8);
        assertThat(testProductCategory.getProductFreeField9()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_9);
        assertThat(testProductCategory.getProductFreeField10()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_10);
        assertThat(testProductCategory.getProductFreeField11()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_11);
        assertThat(testProductCategory.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testProductCategory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testProductCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductCategory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testProductCategory.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllProductCategories() {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        // Get all the productCategoryList
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
            .value(hasItem(productCategory.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].productCategoryCode")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_CODE))
            .jsonPath("$.[*].productCategoryName")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_NAME))
            .jsonPath("$.[*].productCategoryDescription")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION))
            .jsonPath("$.[*].productCategoryImage")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_IMAGE))
            .jsonPath("$.[*].productFreeField1")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_1))
            .jsonPath("$.[*].productFreeField2")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_2.toString()))
            .jsonPath("$.[*].productFreeField3")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_3))
            .jsonPath("$.[*].productFreeField4")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_4))
            .jsonPath("$.[*].productFreeField5")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_5))
            .jsonPath("$.[*].productFreeField6")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_6))
            .jsonPath("$.[*].productFreeField7")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_7))
            .jsonPath("$.[*].productFreeField8")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_8))
            .jsonPath("$.[*].productFreeField9")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_9))
            .jsonPath("$.[*].productFreeField10")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_10))
            .jsonPath("$.[*].productFreeField11")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_11))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
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
    void getProductCategory() {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        // Get the productCategory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, productCategory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(productCategory.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.productCategoryCode")
            .value(is(DEFAULT_PRODUCT_CATEGORY_CODE))
            .jsonPath("$.productCategoryName")
            .value(is(DEFAULT_PRODUCT_CATEGORY_NAME))
            .jsonPath("$.productCategoryDescription")
            .value(is(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION))
            .jsonPath("$.productCategoryImage")
            .value(is(DEFAULT_PRODUCT_CATEGORY_IMAGE))
            .jsonPath("$.productFreeField1")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_1))
            .jsonPath("$.productFreeField2")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_2.toString()))
            .jsonPath("$.productFreeField3")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_3))
            .jsonPath("$.productFreeField4")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_4))
            .jsonPath("$.productFreeField5")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_5))
            .jsonPath("$.productFreeField6")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_6))
            .jsonPath("$.productFreeField7")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_7))
            .jsonPath("$.productFreeField8")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_8))
            .jsonPath("$.productFreeField9")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_9))
            .jsonPath("$.productFreeField10")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_10))
            .jsonPath("$.productFreeField11")
            .value(is(DEFAULT_PRODUCT_FREE_FIELD_11))
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()))
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
    void getNonExistingProductCategory() {
        // Get the productCategory
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingProductCategory() throws Exception {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        productCategorySearchRepository.save(productCategory).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());

        // Update the productCategory
        ProductCategory updatedProductCategory = productCategoryRepository.findById(productCategory.getId()).block();
        updatedProductCategory
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryCode(UPDATED_PRODUCT_CATEGORY_CODE)
            .productCategoryName(UPDATED_PRODUCT_CATEGORY_NAME)
            .productCategoryDescription(UPDATED_PRODUCT_CATEGORY_DESCRIPTION)
            .productCategoryImage(UPDATED_PRODUCT_CATEGORY_IMAGE)
            .productFreeField1(UPDATED_PRODUCT_FREE_FIELD_1)
            .productFreeField2(UPDATED_PRODUCT_FREE_FIELD_2)
            .productFreeField3(UPDATED_PRODUCT_FREE_FIELD_3)
            .productFreeField4(UPDATED_PRODUCT_FREE_FIELD_4)
            .productFreeField5(UPDATED_PRODUCT_FREE_FIELD_5)
            .productFreeField6(UPDATED_PRODUCT_FREE_FIELD_6)
            .productFreeField7(UPDATED_PRODUCT_FREE_FIELD_7)
            .productFreeField8(UPDATED_PRODUCT_FREE_FIELD_8)
            .productFreeField9(UPDATED_PRODUCT_FREE_FIELD_9)
            .productFreeField10(UPDATED_PRODUCT_FREE_FIELD_10)
            .productFreeField11(UPDATED_PRODUCT_FREE_FIELD_11)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedProductCategory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedProductCategory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        ProductCategory testProductCategory = productCategoryList.get(productCategoryList.size() - 1);
        assertThat(testProductCategory.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testProductCategory.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testProductCategory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testProductCategory.getProductCategoryCode()).isEqualTo(UPDATED_PRODUCT_CATEGORY_CODE);
        assertThat(testProductCategory.getProductCategoryName()).isEqualTo(UPDATED_PRODUCT_CATEGORY_NAME);
        assertThat(testProductCategory.getProductCategoryDescription()).isEqualTo(UPDATED_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testProductCategory.getProductCategoryImage()).isEqualTo(UPDATED_PRODUCT_CATEGORY_IMAGE);
        assertThat(testProductCategory.getProductFreeField1()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_1);
        assertThat(testProductCategory.getProductFreeField2()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_2);
        assertThat(testProductCategory.getProductFreeField3()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_3);
        assertThat(testProductCategory.getProductFreeField4()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_4);
        assertThat(testProductCategory.getProductFreeField5()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_5);
        assertThat(testProductCategory.getProductFreeField6()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_6);
        assertThat(testProductCategory.getProductFreeField7()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_7);
        assertThat(testProductCategory.getProductFreeField8()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_8);
        assertThat(testProductCategory.getProductFreeField9()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_9);
        assertThat(testProductCategory.getProductFreeField10()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_10);
        assertThat(testProductCategory.getProductFreeField11()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_11);
        assertThat(testProductCategory.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testProductCategory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testProductCategory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductCategory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testProductCategory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<ProductCategory> productCategorySearchList = IterableUtils.toList(
                    productCategorySearchRepository.findAll().collectList().block()
                );
                ProductCategory testProductCategorySearch = productCategorySearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testProductCategorySearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testProductCategorySearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testProductCategorySearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testProductCategorySearch.getProductCategoryCode()).isEqualTo(UPDATED_PRODUCT_CATEGORY_CODE);
                assertThat(testProductCategorySearch.getProductCategoryName()).isEqualTo(UPDATED_PRODUCT_CATEGORY_NAME);
                assertThat(testProductCategorySearch.getProductCategoryDescription()).isEqualTo(UPDATED_PRODUCT_CATEGORY_DESCRIPTION);
                assertThat(testProductCategorySearch.getProductCategoryImage()).isEqualTo(UPDATED_PRODUCT_CATEGORY_IMAGE);
                assertThat(testProductCategorySearch.getProductFreeField1()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_1);
                assertThat(testProductCategorySearch.getProductFreeField2()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_2);
                assertThat(testProductCategorySearch.getProductFreeField3()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_3);
                assertThat(testProductCategorySearch.getProductFreeField4()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_4);
                assertThat(testProductCategorySearch.getProductFreeField5()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_5);
                assertThat(testProductCategorySearch.getProductFreeField6()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_6);
                assertThat(testProductCategorySearch.getProductFreeField7()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_7);
                assertThat(testProductCategorySearch.getProductFreeField8()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_8);
                assertThat(testProductCategorySearch.getProductFreeField9()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_9);
                assertThat(testProductCategorySearch.getProductFreeField10()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_10);
                assertThat(testProductCategorySearch.getProductFreeField11()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_11);
                assertThat(testProductCategorySearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testProductCategorySearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testProductCategorySearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testProductCategorySearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testProductCategorySearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, productCategory.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateProductCategoryWithPatch() throws Exception {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();

        // Update the productCategory using partial update
        ProductCategory partialUpdatedProductCategory = new ProductCategory();
        partialUpdatedProductCategory.setId(productCategory.getId());

        partialUpdatedProductCategory
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryName(UPDATED_PRODUCT_CATEGORY_NAME)
            .productFreeField2(UPDATED_PRODUCT_FREE_FIELD_2)
            .productFreeField3(UPDATED_PRODUCT_FREE_FIELD_3)
            .productFreeField4(UPDATED_PRODUCT_FREE_FIELD_4)
            .productFreeField6(UPDATED_PRODUCT_FREE_FIELD_6)
            .productFreeField7(UPDATED_PRODUCT_FREE_FIELD_7)
            .productFreeField8(UPDATED_PRODUCT_FREE_FIELD_8)
            .productFreeField9(UPDATED_PRODUCT_FREE_FIELD_9)
            .productFreeField10(UPDATED_PRODUCT_FREE_FIELD_10)
            .productFreeField11(UPDATED_PRODUCT_FREE_FIELD_11)
            .delflg(UPDATED_DELFLG)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedProductCategory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedProductCategory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        ProductCategory testProductCategory = productCategoryList.get(productCategoryList.size() - 1);
        assertThat(testProductCategory.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testProductCategory.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testProductCategory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testProductCategory.getProductCategoryCode()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_CODE);
        assertThat(testProductCategory.getProductCategoryName()).isEqualTo(UPDATED_PRODUCT_CATEGORY_NAME);
        assertThat(testProductCategory.getProductCategoryDescription()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testProductCategory.getProductCategoryImage()).isEqualTo(DEFAULT_PRODUCT_CATEGORY_IMAGE);
        assertThat(testProductCategory.getProductFreeField1()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_1);
        assertThat(testProductCategory.getProductFreeField2()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_2);
        assertThat(testProductCategory.getProductFreeField3()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_3);
        assertThat(testProductCategory.getProductFreeField4()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_4);
        assertThat(testProductCategory.getProductFreeField5()).isEqualTo(DEFAULT_PRODUCT_FREE_FIELD_5);
        assertThat(testProductCategory.getProductFreeField6()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_6);
        assertThat(testProductCategory.getProductFreeField7()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_7);
        assertThat(testProductCategory.getProductFreeField8()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_8);
        assertThat(testProductCategory.getProductFreeField9()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_9);
        assertThat(testProductCategory.getProductFreeField10()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_10);
        assertThat(testProductCategory.getProductFreeField11()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_11);
        assertThat(testProductCategory.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testProductCategory.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testProductCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProductCategory.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testProductCategory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateProductCategoryWithPatch() throws Exception {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();

        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();

        // Update the productCategory using partial update
        ProductCategory partialUpdatedProductCategory = new ProductCategory();
        partialUpdatedProductCategory.setId(productCategory.getId());

        partialUpdatedProductCategory
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .recordId(UPDATED_RECORD_ID)
            .productCategoryCode(UPDATED_PRODUCT_CATEGORY_CODE)
            .productCategoryName(UPDATED_PRODUCT_CATEGORY_NAME)
            .productCategoryDescription(UPDATED_PRODUCT_CATEGORY_DESCRIPTION)
            .productCategoryImage(UPDATED_PRODUCT_CATEGORY_IMAGE)
            .productFreeField1(UPDATED_PRODUCT_FREE_FIELD_1)
            .productFreeField2(UPDATED_PRODUCT_FREE_FIELD_2)
            .productFreeField3(UPDATED_PRODUCT_FREE_FIELD_3)
            .productFreeField4(UPDATED_PRODUCT_FREE_FIELD_4)
            .productFreeField5(UPDATED_PRODUCT_FREE_FIELD_5)
            .productFreeField6(UPDATED_PRODUCT_FREE_FIELD_6)
            .productFreeField7(UPDATED_PRODUCT_FREE_FIELD_7)
            .productFreeField8(UPDATED_PRODUCT_FREE_FIELD_8)
            .productFreeField9(UPDATED_PRODUCT_FREE_FIELD_9)
            .productFreeField10(UPDATED_PRODUCT_FREE_FIELD_10)
            .productFreeField11(UPDATED_PRODUCT_FREE_FIELD_11)
            .delflg(UPDATED_DELFLG)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedProductCategory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedProductCategory))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        ProductCategory testProductCategory = productCategoryList.get(productCategoryList.size() - 1);
        assertThat(testProductCategory.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testProductCategory.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testProductCategory.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testProductCategory.getProductCategoryCode()).isEqualTo(UPDATED_PRODUCT_CATEGORY_CODE);
        assertThat(testProductCategory.getProductCategoryName()).isEqualTo(UPDATED_PRODUCT_CATEGORY_NAME);
        assertThat(testProductCategory.getProductCategoryDescription()).isEqualTo(UPDATED_PRODUCT_CATEGORY_DESCRIPTION);
        assertThat(testProductCategory.getProductCategoryImage()).isEqualTo(UPDATED_PRODUCT_CATEGORY_IMAGE);
        assertThat(testProductCategory.getProductFreeField1()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_1);
        assertThat(testProductCategory.getProductFreeField2()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_2);
        assertThat(testProductCategory.getProductFreeField3()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_3);
        assertThat(testProductCategory.getProductFreeField4()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_4);
        assertThat(testProductCategory.getProductFreeField5()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_5);
        assertThat(testProductCategory.getProductFreeField6()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_6);
        assertThat(testProductCategory.getProductFreeField7()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_7);
        assertThat(testProductCategory.getProductFreeField8()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_8);
        assertThat(testProductCategory.getProductFreeField9()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_9);
        assertThat(testProductCategory.getProductFreeField10()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_10);
        assertThat(testProductCategory.getProductFreeField11()).isEqualTo(UPDATED_PRODUCT_FREE_FIELD_11);
        assertThat(testProductCategory.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testProductCategory.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testProductCategory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProductCategory.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testProductCategory.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, productCategory.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamProductCategory() throws Exception {
        int databaseSizeBeforeUpdate = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        productCategory.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(productCategory))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ProductCategory in the database
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteProductCategory() {
        // Initialize the database
        productCategoryRepository.save(productCategory).block();
        productCategoryRepository.save(productCategory).block();
        productCategorySearchRepository.save(productCategory).block();

        int databaseSizeBeforeDelete = productCategoryRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the productCategory
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, productCategory.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll().collectList().block();
        assertThat(productCategoryList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(productCategorySearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchProductCategory() {
        // Initialize the database
        productCategory = productCategoryRepository.save(productCategory).block();
        productCategorySearchRepository.save(productCategory).block();

        // Search the productCategory
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + productCategory.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(productCategory.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].productCategoryCode")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_CODE))
            .jsonPath("$.[*].productCategoryName")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_NAME))
            .jsonPath("$.[*].productCategoryDescription")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_DESCRIPTION))
            .jsonPath("$.[*].productCategoryImage")
            .value(hasItem(DEFAULT_PRODUCT_CATEGORY_IMAGE))
            .jsonPath("$.[*].productFreeField1")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_1))
            .jsonPath("$.[*].productFreeField2")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_2.toString()))
            .jsonPath("$.[*].productFreeField3")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_3))
            .jsonPath("$.[*].productFreeField4")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_4))
            .jsonPath("$.[*].productFreeField5")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_5))
            .jsonPath("$.[*].productFreeField6")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_6))
            .jsonPath("$.[*].productFreeField7")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_7))
            .jsonPath("$.[*].productFreeField8")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_8))
            .jsonPath("$.[*].productFreeField9")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_9))
            .jsonPath("$.[*].productFreeField10")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_10))
            .jsonPath("$.[*].productFreeField11")
            .value(hasItem(DEFAULT_PRODUCT_FREE_FIELD_11))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
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
