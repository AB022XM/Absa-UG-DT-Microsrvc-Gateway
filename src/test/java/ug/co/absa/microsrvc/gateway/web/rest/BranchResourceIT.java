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
import ug.co.absa.microsrvc.gateway.domain.Branch;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.BranchRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.BranchSearchRepository;

/**
 * Integration tests for the {@link BranchResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BranchResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ID = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_18 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_18 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_20 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_20 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_23 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_23 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_FREE_FIELD_24 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_FREE_FIELD_24 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final RecordStatus DEFAULT_STATUS = RecordStatus.ACTIVE;
    private static final RecordStatus UPDATED_STATUS = RecordStatus.INACTIVE;

    private static final String ENTITY_API_URL = "/api/branches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/branches";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchSearchRepository branchSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Branch branch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branch createEntity(EntityManager em) {
        Branch branch = new Branch()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .addressId(DEFAULT_ADDRESS_ID)
            .bankId(DEFAULT_BANK_ID)
            .branchId(DEFAULT_BRANCH_ID)
            .branchName(DEFAULT_BRANCH_NAME)
            .branchSwiftCode(DEFAULT_BRANCH_SWIFT_CODE)
            .branchPhoneNumber(DEFAULT_BRANCH_PHONE_NUMBER)
            .branchEmail(DEFAULT_BRANCH_EMAIL)
            .branchFreeField1(DEFAULT_BRANCH_FREE_FIELD_1)
            .branchFreeField3(DEFAULT_BRANCH_FREE_FIELD_3)
            .branchFreeField4(DEFAULT_BRANCH_FREE_FIELD_4)
            .branchFreeField5(DEFAULT_BRANCH_FREE_FIELD_5)
            .branchFreeField6(DEFAULT_BRANCH_FREE_FIELD_6)
            .branchFreeField7(DEFAULT_BRANCH_FREE_FIELD_7)
            .branchFreeField8(DEFAULT_BRANCH_FREE_FIELD_8)
            .branchFreeField9(DEFAULT_BRANCH_FREE_FIELD_9)
            .branchFreeField10(DEFAULT_BRANCH_FREE_FIELD_10)
            .branchFreeField11(DEFAULT_BRANCH_FREE_FIELD_11)
            .branchFreeField12(DEFAULT_BRANCH_FREE_FIELD_12)
            .branchFreeField13(DEFAULT_BRANCH_FREE_FIELD_13)
            .branchFreeField14(DEFAULT_BRANCH_FREE_FIELD_14)
            .branchFreeField15(DEFAULT_BRANCH_FREE_FIELD_15)
            .branchFreeField16(DEFAULT_BRANCH_FREE_FIELD_16)
            .branchFreeField17(DEFAULT_BRANCH_FREE_FIELD_17)
            .branchFreeField18(DEFAULT_BRANCH_FREE_FIELD_18)
            .branchFreeField19(DEFAULT_BRANCH_FREE_FIELD_19)
            .branchFreeField20(DEFAULT_BRANCH_FREE_FIELD_20)
            .branchFreeField21(DEFAULT_BRANCH_FREE_FIELD_21)
            .branchFreeField22(DEFAULT_BRANCH_FREE_FIELD_22)
            .branchFreeField23(DEFAULT_BRANCH_FREE_FIELD_23)
            .branchFreeField24(DEFAULT_BRANCH_FREE_FIELD_24)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .timestamp(DEFAULT_TIMESTAMP)
            .status(DEFAULT_STATUS);
        return branch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Branch createUpdatedEntity(EntityManager em) {
        Branch branch = new Branch()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .bankId(UPDATED_BANK_ID)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .branchSwiftCode(UPDATED_BRANCH_SWIFT_CODE)
            .branchPhoneNumber(UPDATED_BRANCH_PHONE_NUMBER)
            .branchEmail(UPDATED_BRANCH_EMAIL)
            .branchFreeField1(UPDATED_BRANCH_FREE_FIELD_1)
            .branchFreeField3(UPDATED_BRANCH_FREE_FIELD_3)
            .branchFreeField4(UPDATED_BRANCH_FREE_FIELD_4)
            .branchFreeField5(UPDATED_BRANCH_FREE_FIELD_5)
            .branchFreeField6(UPDATED_BRANCH_FREE_FIELD_6)
            .branchFreeField7(UPDATED_BRANCH_FREE_FIELD_7)
            .branchFreeField8(UPDATED_BRANCH_FREE_FIELD_8)
            .branchFreeField9(UPDATED_BRANCH_FREE_FIELD_9)
            .branchFreeField10(UPDATED_BRANCH_FREE_FIELD_10)
            .branchFreeField11(UPDATED_BRANCH_FREE_FIELD_11)
            .branchFreeField12(UPDATED_BRANCH_FREE_FIELD_12)
            .branchFreeField13(UPDATED_BRANCH_FREE_FIELD_13)
            .branchFreeField14(UPDATED_BRANCH_FREE_FIELD_14)
            .branchFreeField15(UPDATED_BRANCH_FREE_FIELD_15)
            .branchFreeField16(UPDATED_BRANCH_FREE_FIELD_16)
            .branchFreeField17(UPDATED_BRANCH_FREE_FIELD_17)
            .branchFreeField18(UPDATED_BRANCH_FREE_FIELD_18)
            .branchFreeField19(UPDATED_BRANCH_FREE_FIELD_19)
            .branchFreeField20(UPDATED_BRANCH_FREE_FIELD_20)
            .branchFreeField21(UPDATED_BRANCH_FREE_FIELD_21)
            .branchFreeField22(UPDATED_BRANCH_FREE_FIELD_22)
            .branchFreeField23(UPDATED_BRANCH_FREE_FIELD_23)
            .branchFreeField24(UPDATED_BRANCH_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .timestamp(UPDATED_TIMESTAMP)
            .status(UPDATED_STATUS);
        return branch;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Branch.class).block();
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
        branchSearchRepository.deleteAll().block();
        assertThat(branchSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        branch = createEntity(em);
    }

    @Test
    void createBranch() throws Exception {
        int databaseSizeBeforeCreate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        // Create the Branch
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBranch.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testBranch.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testBranch.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testBranch.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranch.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranch.getBranchSwiftCode()).isEqualTo(DEFAULT_BRANCH_SWIFT_CODE);
        assertThat(testBranch.getBranchPhoneNumber()).isEqualTo(DEFAULT_BRANCH_PHONE_NUMBER);
        assertThat(testBranch.getBranchEmail()).isEqualTo(DEFAULT_BRANCH_EMAIL);
        assertThat(testBranch.getBranchFreeField1()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_1);
        assertThat(testBranch.getBranchFreeField3()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_3);
        assertThat(testBranch.getBranchFreeField4()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_4);
        assertThat(testBranch.getBranchFreeField5()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_5);
        assertThat(testBranch.getBranchFreeField6()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_6);
        assertThat(testBranch.getBranchFreeField7()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_7);
        assertThat(testBranch.getBranchFreeField8()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_8);
        assertThat(testBranch.getBranchFreeField9()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_9);
        assertThat(testBranch.getBranchFreeField10()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_10);
        assertThat(testBranch.getBranchFreeField11()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_11);
        assertThat(testBranch.getBranchFreeField12()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_12);
        assertThat(testBranch.getBranchFreeField13()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_13);
        assertThat(testBranch.getBranchFreeField14()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_14);
        assertThat(testBranch.getBranchFreeField15()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_15);
        assertThat(testBranch.getBranchFreeField16()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_16);
        assertThat(testBranch.getBranchFreeField17()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_17);
        assertThat(testBranch.getBranchFreeField18()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_18);
        assertThat(testBranch.getBranchFreeField19()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_19);
        assertThat(testBranch.getBranchFreeField20()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_20);
        assertThat(testBranch.getBranchFreeField21()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_21);
        assertThat(testBranch.getBranchFreeField22()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_22);
        assertThat(testBranch.getBranchFreeField23()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_23);
        assertThat(testBranch.getBranchFreeField24()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_24);
        assertThat(testBranch.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBranch.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBranch.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBranch.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBranch.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createBranchWithExistingId() throws Exception {
        // Create the Branch with an existing ID
        branch.setId(1L);

        int databaseSizeBeforeCreate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBranchNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        // set the field null
        branch.setBranchName(null);

        // Create the Branch, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBranchSwiftCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        // set the field null
        branch.setBranchSwiftCode(null);

        // Create the Branch, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        // set the field null
        branch.setCreatedAt(null);

        // Create the Branch, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllBranchesAsStream() {
        // Initialize the database
        branchRepository.save(branch).block();

        List<Branch> branchList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Branch.class)
            .getResponseBody()
            .filter(branch::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(branchList).isNotNull();
        assertThat(branchList).hasSize(1);
        Branch testBranch = branchList.get(0);
        assertThat(testBranch.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBranch.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testBranch.getAddressId()).isEqualTo(DEFAULT_ADDRESS_ID);
        assertThat(testBranch.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testBranch.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testBranch.getBranchName()).isEqualTo(DEFAULT_BRANCH_NAME);
        assertThat(testBranch.getBranchSwiftCode()).isEqualTo(DEFAULT_BRANCH_SWIFT_CODE);
        assertThat(testBranch.getBranchPhoneNumber()).isEqualTo(DEFAULT_BRANCH_PHONE_NUMBER);
        assertThat(testBranch.getBranchEmail()).isEqualTo(DEFAULT_BRANCH_EMAIL);
        assertThat(testBranch.getBranchFreeField1()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_1);
        assertThat(testBranch.getBranchFreeField3()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_3);
        assertThat(testBranch.getBranchFreeField4()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_4);
        assertThat(testBranch.getBranchFreeField5()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_5);
        assertThat(testBranch.getBranchFreeField6()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_6);
        assertThat(testBranch.getBranchFreeField7()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_7);
        assertThat(testBranch.getBranchFreeField8()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_8);
        assertThat(testBranch.getBranchFreeField9()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_9);
        assertThat(testBranch.getBranchFreeField10()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_10);
        assertThat(testBranch.getBranchFreeField11()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_11);
        assertThat(testBranch.getBranchFreeField12()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_12);
        assertThat(testBranch.getBranchFreeField13()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_13);
        assertThat(testBranch.getBranchFreeField14()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_14);
        assertThat(testBranch.getBranchFreeField15()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_15);
        assertThat(testBranch.getBranchFreeField16()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_16);
        assertThat(testBranch.getBranchFreeField17()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_17);
        assertThat(testBranch.getBranchFreeField18()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_18);
        assertThat(testBranch.getBranchFreeField19()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_19);
        assertThat(testBranch.getBranchFreeField20()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_20);
        assertThat(testBranch.getBranchFreeField21()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_21);
        assertThat(testBranch.getBranchFreeField22()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_22);
        assertThat(testBranch.getBranchFreeField23()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_23);
        assertThat(testBranch.getBranchFreeField24()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_24);
        assertThat(testBranch.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testBranch.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBranch.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBranch.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBranch.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void getAllBranches() {
        // Initialize the database
        branchRepository.save(branch).block();

        // Get all the branchList
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
            .value(hasItem(branch.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].addressId")
            .value(hasItem(DEFAULT_ADDRESS_ID))
            .jsonPath("$.[*].bankId")
            .value(hasItem(DEFAULT_BANK_ID))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].branchSwiftCode")
            .value(hasItem(DEFAULT_BRANCH_SWIFT_CODE))
            .jsonPath("$.[*].branchPhoneNumber")
            .value(hasItem(DEFAULT_BRANCH_PHONE_NUMBER))
            .jsonPath("$.[*].branchEmail")
            .value(hasItem(DEFAULT_BRANCH_EMAIL))
            .jsonPath("$.[*].branchFreeField1")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_1))
            .jsonPath("$.[*].branchFreeField3")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_3))
            .jsonPath("$.[*].branchFreeField4")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_4))
            .jsonPath("$.[*].branchFreeField5")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_5))
            .jsonPath("$.[*].branchFreeField6")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_6))
            .jsonPath("$.[*].branchFreeField7")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_7))
            .jsonPath("$.[*].branchFreeField8")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_8))
            .jsonPath("$.[*].branchFreeField9")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_9))
            .jsonPath("$.[*].branchFreeField10")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_10))
            .jsonPath("$.[*].branchFreeField11")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_11))
            .jsonPath("$.[*].branchFreeField12")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_12))
            .jsonPath("$.[*].branchFreeField13")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_13))
            .jsonPath("$.[*].branchFreeField14")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_14))
            .jsonPath("$.[*].branchFreeField15")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_15))
            .jsonPath("$.[*].branchFreeField16")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_16))
            .jsonPath("$.[*].branchFreeField17")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_17))
            .jsonPath("$.[*].branchFreeField18")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_18))
            .jsonPath("$.[*].branchFreeField19")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_19))
            .jsonPath("$.[*].branchFreeField20")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_20))
            .jsonPath("$.[*].branchFreeField21")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_21))
            .jsonPath("$.[*].branchFreeField22")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_22))
            .jsonPath("$.[*].branchFreeField23")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_23))
            .jsonPath("$.[*].branchFreeField24")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_24))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()));
    }

    @Test
    void getBranch() {
        // Initialize the database
        branchRepository.save(branch).block();

        // Get the branch
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, branch.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(branch.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.addressId")
            .value(is(DEFAULT_ADDRESS_ID))
            .jsonPath("$.bankId")
            .value(is(DEFAULT_BANK_ID))
            .jsonPath("$.branchId")
            .value(is(DEFAULT_BRANCH_ID))
            .jsonPath("$.branchName")
            .value(is(DEFAULT_BRANCH_NAME))
            .jsonPath("$.branchSwiftCode")
            .value(is(DEFAULT_BRANCH_SWIFT_CODE))
            .jsonPath("$.branchPhoneNumber")
            .value(is(DEFAULT_BRANCH_PHONE_NUMBER))
            .jsonPath("$.branchEmail")
            .value(is(DEFAULT_BRANCH_EMAIL))
            .jsonPath("$.branchFreeField1")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_1))
            .jsonPath("$.branchFreeField3")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_3))
            .jsonPath("$.branchFreeField4")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_4))
            .jsonPath("$.branchFreeField5")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_5))
            .jsonPath("$.branchFreeField6")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_6))
            .jsonPath("$.branchFreeField7")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_7))
            .jsonPath("$.branchFreeField8")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_8))
            .jsonPath("$.branchFreeField9")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_9))
            .jsonPath("$.branchFreeField10")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_10))
            .jsonPath("$.branchFreeField11")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_11))
            .jsonPath("$.branchFreeField12")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_12))
            .jsonPath("$.branchFreeField13")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_13))
            .jsonPath("$.branchFreeField14")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_14))
            .jsonPath("$.branchFreeField15")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_15))
            .jsonPath("$.branchFreeField16")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_16))
            .jsonPath("$.branchFreeField17")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_17))
            .jsonPath("$.branchFreeField18")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_18))
            .jsonPath("$.branchFreeField19")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_19))
            .jsonPath("$.branchFreeField20")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_20))
            .jsonPath("$.branchFreeField21")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_21))
            .jsonPath("$.branchFreeField22")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_22))
            .jsonPath("$.branchFreeField23")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_23))
            .jsonPath("$.branchFreeField24")
            .value(is(DEFAULT_BRANCH_FREE_FIELD_24))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()));
    }

    @Test
    void getNonExistingBranch() {
        // Get the branch
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBranch() throws Exception {
        // Initialize the database
        branchRepository.save(branch).block();

        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        branchSearchRepository.save(branch).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());

        // Update the branch
        Branch updatedBranch = branchRepository.findById(branch.getId()).block();
        updatedBranch
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .bankId(UPDATED_BANK_ID)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .branchSwiftCode(UPDATED_BRANCH_SWIFT_CODE)
            .branchPhoneNumber(UPDATED_BRANCH_PHONE_NUMBER)
            .branchEmail(UPDATED_BRANCH_EMAIL)
            .branchFreeField1(UPDATED_BRANCH_FREE_FIELD_1)
            .branchFreeField3(UPDATED_BRANCH_FREE_FIELD_3)
            .branchFreeField4(UPDATED_BRANCH_FREE_FIELD_4)
            .branchFreeField5(UPDATED_BRANCH_FREE_FIELD_5)
            .branchFreeField6(UPDATED_BRANCH_FREE_FIELD_6)
            .branchFreeField7(UPDATED_BRANCH_FREE_FIELD_7)
            .branchFreeField8(UPDATED_BRANCH_FREE_FIELD_8)
            .branchFreeField9(UPDATED_BRANCH_FREE_FIELD_9)
            .branchFreeField10(UPDATED_BRANCH_FREE_FIELD_10)
            .branchFreeField11(UPDATED_BRANCH_FREE_FIELD_11)
            .branchFreeField12(UPDATED_BRANCH_FREE_FIELD_12)
            .branchFreeField13(UPDATED_BRANCH_FREE_FIELD_13)
            .branchFreeField14(UPDATED_BRANCH_FREE_FIELD_14)
            .branchFreeField15(UPDATED_BRANCH_FREE_FIELD_15)
            .branchFreeField16(UPDATED_BRANCH_FREE_FIELD_16)
            .branchFreeField17(UPDATED_BRANCH_FREE_FIELD_17)
            .branchFreeField18(UPDATED_BRANCH_FREE_FIELD_18)
            .branchFreeField19(UPDATED_BRANCH_FREE_FIELD_19)
            .branchFreeField20(UPDATED_BRANCH_FREE_FIELD_20)
            .branchFreeField21(UPDATED_BRANCH_FREE_FIELD_21)
            .branchFreeField22(UPDATED_BRANCH_FREE_FIELD_22)
            .branchFreeField23(UPDATED_BRANCH_FREE_FIELD_23)
            .branchFreeField24(UPDATED_BRANCH_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .timestamp(UPDATED_TIMESTAMP)
            .status(UPDATED_STATUS);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedBranch.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedBranch))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBranch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testBranch.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testBranch.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testBranch.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranch.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranch.getBranchSwiftCode()).isEqualTo(UPDATED_BRANCH_SWIFT_CODE);
        assertThat(testBranch.getBranchPhoneNumber()).isEqualTo(UPDATED_BRANCH_PHONE_NUMBER);
        assertThat(testBranch.getBranchEmail()).isEqualTo(UPDATED_BRANCH_EMAIL);
        assertThat(testBranch.getBranchFreeField1()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_1);
        assertThat(testBranch.getBranchFreeField3()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_3);
        assertThat(testBranch.getBranchFreeField4()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_4);
        assertThat(testBranch.getBranchFreeField5()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_5);
        assertThat(testBranch.getBranchFreeField6()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_6);
        assertThat(testBranch.getBranchFreeField7()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_7);
        assertThat(testBranch.getBranchFreeField8()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_8);
        assertThat(testBranch.getBranchFreeField9()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_9);
        assertThat(testBranch.getBranchFreeField10()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_10);
        assertThat(testBranch.getBranchFreeField11()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_11);
        assertThat(testBranch.getBranchFreeField12()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_12);
        assertThat(testBranch.getBranchFreeField13()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_13);
        assertThat(testBranch.getBranchFreeField14()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_14);
        assertThat(testBranch.getBranchFreeField15()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_15);
        assertThat(testBranch.getBranchFreeField16()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_16);
        assertThat(testBranch.getBranchFreeField17()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_17);
        assertThat(testBranch.getBranchFreeField18()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_18);
        assertThat(testBranch.getBranchFreeField19()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_19);
        assertThat(testBranch.getBranchFreeField20()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_20);
        assertThat(testBranch.getBranchFreeField21()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_21);
        assertThat(testBranch.getBranchFreeField22()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_22);
        assertThat(testBranch.getBranchFreeField23()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_23);
        assertThat(testBranch.getBranchFreeField24()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_24);
        assertThat(testBranch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBranch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBranch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBranch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBranch.getStatus()).isEqualTo(UPDATED_STATUS);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Branch> branchSearchList = IterableUtils.toList(branchSearchRepository.findAll().collectList().block());
                Branch testBranchSearch = branchSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testBranchSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testBranchSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testBranchSearch.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
                assertThat(testBranchSearch.getBankId()).isEqualTo(UPDATED_BANK_ID);
                assertThat(testBranchSearch.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
                assertThat(testBranchSearch.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
                assertThat(testBranchSearch.getBranchSwiftCode()).isEqualTo(UPDATED_BRANCH_SWIFT_CODE);
                assertThat(testBranchSearch.getBranchPhoneNumber()).isEqualTo(UPDATED_BRANCH_PHONE_NUMBER);
                assertThat(testBranchSearch.getBranchEmail()).isEqualTo(UPDATED_BRANCH_EMAIL);
                assertThat(testBranchSearch.getBranchFreeField1()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_1);
                assertThat(testBranchSearch.getBranchFreeField3()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_3);
                assertThat(testBranchSearch.getBranchFreeField4()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_4);
                assertThat(testBranchSearch.getBranchFreeField5()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_5);
                assertThat(testBranchSearch.getBranchFreeField6()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_6);
                assertThat(testBranchSearch.getBranchFreeField7()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_7);
                assertThat(testBranchSearch.getBranchFreeField8()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_8);
                assertThat(testBranchSearch.getBranchFreeField9()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_9);
                assertThat(testBranchSearch.getBranchFreeField10()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_10);
                assertThat(testBranchSearch.getBranchFreeField11()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_11);
                assertThat(testBranchSearch.getBranchFreeField12()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_12);
                assertThat(testBranchSearch.getBranchFreeField13()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_13);
                assertThat(testBranchSearch.getBranchFreeField14()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_14);
                assertThat(testBranchSearch.getBranchFreeField15()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_15);
                assertThat(testBranchSearch.getBranchFreeField16()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_16);
                assertThat(testBranchSearch.getBranchFreeField17()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_17);
                assertThat(testBranchSearch.getBranchFreeField18()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_18);
                assertThat(testBranchSearch.getBranchFreeField19()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_19);
                assertThat(testBranchSearch.getBranchFreeField20()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_20);
                assertThat(testBranchSearch.getBranchFreeField21()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_21);
                assertThat(testBranchSearch.getBranchFreeField22()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_22);
                assertThat(testBranchSearch.getBranchFreeField23()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_23);
                assertThat(testBranchSearch.getBranchFreeField24()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_24);
                assertThat(testBranchSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testBranchSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testBranchSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testBranchSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
                assertThat(testBranchSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testBranchSearch.getStatus()).isEqualTo(UPDATED_STATUS);
            });
    }

    @Test
    void putNonExistingBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, branch.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateBranchWithPatch() throws Exception {
        // Initialize the database
        branchRepository.save(branch).block();

        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();

        // Update the branch using partial update
        Branch partialUpdatedBranch = new Branch();
        partialUpdatedBranch.setId(branch.getId());

        partialUpdatedBranch
            .recordId(UPDATED_RECORD_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .branchSwiftCode(UPDATED_BRANCH_SWIFT_CODE)
            .branchFreeField3(UPDATED_BRANCH_FREE_FIELD_3)
            .branchFreeField6(UPDATED_BRANCH_FREE_FIELD_6)
            .branchFreeField8(UPDATED_BRANCH_FREE_FIELD_8)
            .branchFreeField9(UPDATED_BRANCH_FREE_FIELD_9)
            .branchFreeField11(UPDATED_BRANCH_FREE_FIELD_11)
            .branchFreeField13(UPDATED_BRANCH_FREE_FIELD_13)
            .branchFreeField16(UPDATED_BRANCH_FREE_FIELD_16)
            .branchFreeField17(UPDATED_BRANCH_FREE_FIELD_17)
            .branchFreeField18(UPDATED_BRANCH_FREE_FIELD_18)
            .branchFreeField19(UPDATED_BRANCH_FREE_FIELD_19)
            .branchFreeField21(UPDATED_BRANCH_FREE_FIELD_21)
            .branchFreeField24(UPDATED_BRANCH_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBranch.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBranch))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testBranch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testBranch.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testBranch.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testBranch.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranch.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranch.getBranchSwiftCode()).isEqualTo(UPDATED_BRANCH_SWIFT_CODE);
        assertThat(testBranch.getBranchPhoneNumber()).isEqualTo(DEFAULT_BRANCH_PHONE_NUMBER);
        assertThat(testBranch.getBranchEmail()).isEqualTo(DEFAULT_BRANCH_EMAIL);
        assertThat(testBranch.getBranchFreeField1()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_1);
        assertThat(testBranch.getBranchFreeField3()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_3);
        assertThat(testBranch.getBranchFreeField4()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_4);
        assertThat(testBranch.getBranchFreeField5()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_5);
        assertThat(testBranch.getBranchFreeField6()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_6);
        assertThat(testBranch.getBranchFreeField7()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_7);
        assertThat(testBranch.getBranchFreeField8()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_8);
        assertThat(testBranch.getBranchFreeField9()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_9);
        assertThat(testBranch.getBranchFreeField10()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_10);
        assertThat(testBranch.getBranchFreeField11()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_11);
        assertThat(testBranch.getBranchFreeField12()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_12);
        assertThat(testBranch.getBranchFreeField13()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_13);
        assertThat(testBranch.getBranchFreeField14()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_14);
        assertThat(testBranch.getBranchFreeField15()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_15);
        assertThat(testBranch.getBranchFreeField16()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_16);
        assertThat(testBranch.getBranchFreeField17()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_17);
        assertThat(testBranch.getBranchFreeField18()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_18);
        assertThat(testBranch.getBranchFreeField19()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_19);
        assertThat(testBranch.getBranchFreeField20()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_20);
        assertThat(testBranch.getBranchFreeField21()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_21);
        assertThat(testBranch.getBranchFreeField22()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_22);
        assertThat(testBranch.getBranchFreeField23()).isEqualTo(DEFAULT_BRANCH_FREE_FIELD_23);
        assertThat(testBranch.getBranchFreeField24()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_24);
        assertThat(testBranch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBranch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBranch.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testBranch.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testBranch.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateBranchWithPatch() throws Exception {
        // Initialize the database
        branchRepository.save(branch).block();

        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();

        // Update the branch using partial update
        Branch partialUpdatedBranch = new Branch();
        partialUpdatedBranch.setId(branch.getId());

        partialUpdatedBranch
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .addressId(UPDATED_ADDRESS_ID)
            .bankId(UPDATED_BANK_ID)
            .branchId(UPDATED_BRANCH_ID)
            .branchName(UPDATED_BRANCH_NAME)
            .branchSwiftCode(UPDATED_BRANCH_SWIFT_CODE)
            .branchPhoneNumber(UPDATED_BRANCH_PHONE_NUMBER)
            .branchEmail(UPDATED_BRANCH_EMAIL)
            .branchFreeField1(UPDATED_BRANCH_FREE_FIELD_1)
            .branchFreeField3(UPDATED_BRANCH_FREE_FIELD_3)
            .branchFreeField4(UPDATED_BRANCH_FREE_FIELD_4)
            .branchFreeField5(UPDATED_BRANCH_FREE_FIELD_5)
            .branchFreeField6(UPDATED_BRANCH_FREE_FIELD_6)
            .branchFreeField7(UPDATED_BRANCH_FREE_FIELD_7)
            .branchFreeField8(UPDATED_BRANCH_FREE_FIELD_8)
            .branchFreeField9(UPDATED_BRANCH_FREE_FIELD_9)
            .branchFreeField10(UPDATED_BRANCH_FREE_FIELD_10)
            .branchFreeField11(UPDATED_BRANCH_FREE_FIELD_11)
            .branchFreeField12(UPDATED_BRANCH_FREE_FIELD_12)
            .branchFreeField13(UPDATED_BRANCH_FREE_FIELD_13)
            .branchFreeField14(UPDATED_BRANCH_FREE_FIELD_14)
            .branchFreeField15(UPDATED_BRANCH_FREE_FIELD_15)
            .branchFreeField16(UPDATED_BRANCH_FREE_FIELD_16)
            .branchFreeField17(UPDATED_BRANCH_FREE_FIELD_17)
            .branchFreeField18(UPDATED_BRANCH_FREE_FIELD_18)
            .branchFreeField19(UPDATED_BRANCH_FREE_FIELD_19)
            .branchFreeField20(UPDATED_BRANCH_FREE_FIELD_20)
            .branchFreeField21(UPDATED_BRANCH_FREE_FIELD_21)
            .branchFreeField22(UPDATED_BRANCH_FREE_FIELD_22)
            .branchFreeField23(UPDATED_BRANCH_FREE_FIELD_23)
            .branchFreeField24(UPDATED_BRANCH_FREE_FIELD_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .timestamp(UPDATED_TIMESTAMP)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBranch.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBranch))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        Branch testBranch = branchList.get(branchList.size() - 1);
        assertThat(testBranch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testBranch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testBranch.getAddressId()).isEqualTo(UPDATED_ADDRESS_ID);
        assertThat(testBranch.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testBranch.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testBranch.getBranchName()).isEqualTo(UPDATED_BRANCH_NAME);
        assertThat(testBranch.getBranchSwiftCode()).isEqualTo(UPDATED_BRANCH_SWIFT_CODE);
        assertThat(testBranch.getBranchPhoneNumber()).isEqualTo(UPDATED_BRANCH_PHONE_NUMBER);
        assertThat(testBranch.getBranchEmail()).isEqualTo(UPDATED_BRANCH_EMAIL);
        assertThat(testBranch.getBranchFreeField1()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_1);
        assertThat(testBranch.getBranchFreeField3()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_3);
        assertThat(testBranch.getBranchFreeField4()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_4);
        assertThat(testBranch.getBranchFreeField5()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_5);
        assertThat(testBranch.getBranchFreeField6()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_6);
        assertThat(testBranch.getBranchFreeField7()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_7);
        assertThat(testBranch.getBranchFreeField8()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_8);
        assertThat(testBranch.getBranchFreeField9()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_9);
        assertThat(testBranch.getBranchFreeField10()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_10);
        assertThat(testBranch.getBranchFreeField11()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_11);
        assertThat(testBranch.getBranchFreeField12()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_12);
        assertThat(testBranch.getBranchFreeField13()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_13);
        assertThat(testBranch.getBranchFreeField14()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_14);
        assertThat(testBranch.getBranchFreeField15()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_15);
        assertThat(testBranch.getBranchFreeField16()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_16);
        assertThat(testBranch.getBranchFreeField17()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_17);
        assertThat(testBranch.getBranchFreeField18()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_18);
        assertThat(testBranch.getBranchFreeField19()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_19);
        assertThat(testBranch.getBranchFreeField20()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_20);
        assertThat(testBranch.getBranchFreeField21()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_21);
        assertThat(testBranch.getBranchFreeField22()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_22);
        assertThat(testBranch.getBranchFreeField23()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_23);
        assertThat(testBranch.getBranchFreeField24()).isEqualTo(UPDATED_BRANCH_FREE_FIELD_24);
        assertThat(testBranch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testBranch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBranch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testBranch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testBranch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testBranch.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, branch.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamBranch() throws Exception {
        int databaseSizeBeforeUpdate = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        branch.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(branch))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Branch in the database
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteBranch() {
        // Initialize the database
        branchRepository.save(branch).block();
        branchRepository.save(branch).block();
        branchSearchRepository.save(branch).block();

        int databaseSizeBeforeDelete = branchRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the branch
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, branch.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Branch> branchList = branchRepository.findAll().collectList().block();
        assertThat(branchList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(branchSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchBranch() {
        // Initialize the database
        branch = branchRepository.save(branch).block();
        branchSearchRepository.save(branch).block();

        // Search the branch
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + branch.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(branch.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].addressId")
            .value(hasItem(DEFAULT_ADDRESS_ID))
            .jsonPath("$.[*].bankId")
            .value(hasItem(DEFAULT_BANK_ID))
            .jsonPath("$.[*].branchId")
            .value(hasItem(DEFAULT_BRANCH_ID))
            .jsonPath("$.[*].branchName")
            .value(hasItem(DEFAULT_BRANCH_NAME))
            .jsonPath("$.[*].branchSwiftCode")
            .value(hasItem(DEFAULT_BRANCH_SWIFT_CODE))
            .jsonPath("$.[*].branchPhoneNumber")
            .value(hasItem(DEFAULT_BRANCH_PHONE_NUMBER))
            .jsonPath("$.[*].branchEmail")
            .value(hasItem(DEFAULT_BRANCH_EMAIL))
            .jsonPath("$.[*].branchFreeField1")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_1))
            .jsonPath("$.[*].branchFreeField3")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_3))
            .jsonPath("$.[*].branchFreeField4")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_4))
            .jsonPath("$.[*].branchFreeField5")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_5))
            .jsonPath("$.[*].branchFreeField6")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_6))
            .jsonPath("$.[*].branchFreeField7")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_7))
            .jsonPath("$.[*].branchFreeField8")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_8))
            .jsonPath("$.[*].branchFreeField9")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_9))
            .jsonPath("$.[*].branchFreeField10")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_10))
            .jsonPath("$.[*].branchFreeField11")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_11))
            .jsonPath("$.[*].branchFreeField12")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_12))
            .jsonPath("$.[*].branchFreeField13")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_13))
            .jsonPath("$.[*].branchFreeField14")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_14))
            .jsonPath("$.[*].branchFreeField15")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_15))
            .jsonPath("$.[*].branchFreeField16")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_16))
            .jsonPath("$.[*].branchFreeField17")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_17))
            .jsonPath("$.[*].branchFreeField18")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_18))
            .jsonPath("$.[*].branchFreeField19")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_19))
            .jsonPath("$.[*].branchFreeField20")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_20))
            .jsonPath("$.[*].branchFreeField21")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_21))
            .jsonPath("$.[*].branchFreeField22")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_22))
            .jsonPath("$.[*].branchFreeField23")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_23))
            .jsonPath("$.[*].branchFreeField24")
            .value(hasItem(DEFAULT_BRANCH_FREE_FIELD_24))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()));
    }
}
