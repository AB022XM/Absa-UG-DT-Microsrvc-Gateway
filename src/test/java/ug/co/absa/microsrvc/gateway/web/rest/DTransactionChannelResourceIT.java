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
import ug.co.absa.microsrvc.gateway.domain.DTransactionChannel;
import ug.co.absa.microsrvc.gateway.repository.DTransactionChannelRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionChannelSearchRepository;

/**
 * Integration tests for the {@link DTransactionChannelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DTransactionChannelResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_TRANID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TRANID = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CHANEL_FREE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_1 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_2 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_3 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_4 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_4 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_5 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_5 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_6 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_6 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_7 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_7 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_8 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_8 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_9 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_9 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_10 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_10 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_11 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_11 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_12 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_12 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_13 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_13 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_14 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_14 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_15 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_15 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_16 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_16 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_17 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_17 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_18 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_18 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_19 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_19 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_20 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_20 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_21 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_21 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_22 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_22 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_23 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_23 = "BBBBBBBBBB";

    private static final String DEFAULT_CHANEL_FREE_TEXT_24 = "AAAAAAAAAA";
    private static final String UPDATED_CHANEL_FREE_TEXT_24 = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/d-transaction-channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/d-transaction-channels";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DTransactionChannelRepository dTransactionChannelRepository;

    @Autowired
    private DTransactionChannelSearchRepository dTransactionChannelSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DTransactionChannel dTransactionChannel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionChannel createEntity(EntityManager em) {
        DTransactionChannel dTransactionChannel = new DTransactionChannel()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(DEFAULT_EXTERNAL_TRANID)
            .channelCode(DEFAULT_CHANNEL_CODE)
            .channelName(DEFAULT_CHANNEL_NAME)
            .channelDescription(DEFAULT_CHANNEL_DESCRIPTION)
            .timestamp(DEFAULT_TIMESTAMP)
            .chanelFreeText(DEFAULT_CHANEL_FREE_TEXT)
            .chanelFreeText1(DEFAULT_CHANEL_FREE_TEXT_1)
            .chanelFreeText2(DEFAULT_CHANEL_FREE_TEXT_2)
            .chanelFreeText3(DEFAULT_CHANEL_FREE_TEXT_3)
            .chanelFreeText4(DEFAULT_CHANEL_FREE_TEXT_4)
            .chanelFreeText5(DEFAULT_CHANEL_FREE_TEXT_5)
            .chanelFreeText6(DEFAULT_CHANEL_FREE_TEXT_6)
            .chanelFreeText7(DEFAULT_CHANEL_FREE_TEXT_7)
            .chanelFreeText8(DEFAULT_CHANEL_FREE_TEXT_8)
            .chanelFreeText9(DEFAULT_CHANEL_FREE_TEXT_9)
            .chanelFreeText10(DEFAULT_CHANEL_FREE_TEXT_10)
            .chanelFreeText11(DEFAULT_CHANEL_FREE_TEXT_11)
            .chanelFreeText12(DEFAULT_CHANEL_FREE_TEXT_12)
            .chanelFreeText13(DEFAULT_CHANEL_FREE_TEXT_13)
            .chanelFreeText14(DEFAULT_CHANEL_FREE_TEXT_14)
            .chanelFreeText15(DEFAULT_CHANEL_FREE_TEXT_15)
            .chanelFreeText16(DEFAULT_CHANEL_FREE_TEXT_16)
            .chanelFreeText17(DEFAULT_CHANEL_FREE_TEXT_17)
            .chanelFreeText18(DEFAULT_CHANEL_FREE_TEXT_18)
            .chanelFreeText19(DEFAULT_CHANEL_FREE_TEXT_19)
            .chanelFreeText20(DEFAULT_CHANEL_FREE_TEXT_20)
            .chanelFreeText21(DEFAULT_CHANEL_FREE_TEXT_21)
            .chanelFreeText22(DEFAULT_CHANEL_FREE_TEXT_22)
            .chanelFreeText23(DEFAULT_CHANEL_FREE_TEXT_23)
            .chanelFreeText24(DEFAULT_CHANEL_FREE_TEXT_24)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY)
            .delflg(DEFAULT_DELFLG);
        return dTransactionChannel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionChannel createUpdatedEntity(EntityManager em) {
        DTransactionChannel dTransactionChannel = new DTransactionChannel()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .channelCode(UPDATED_CHANNEL_CODE)
            .channelName(UPDATED_CHANNEL_NAME)
            .channelDescription(UPDATED_CHANNEL_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .chanelFreeText(UPDATED_CHANEL_FREE_TEXT)
            .chanelFreeText1(UPDATED_CHANEL_FREE_TEXT_1)
            .chanelFreeText2(UPDATED_CHANEL_FREE_TEXT_2)
            .chanelFreeText3(UPDATED_CHANEL_FREE_TEXT_3)
            .chanelFreeText4(UPDATED_CHANEL_FREE_TEXT_4)
            .chanelFreeText5(UPDATED_CHANEL_FREE_TEXT_5)
            .chanelFreeText6(UPDATED_CHANEL_FREE_TEXT_6)
            .chanelFreeText7(UPDATED_CHANEL_FREE_TEXT_7)
            .chanelFreeText8(UPDATED_CHANEL_FREE_TEXT_8)
            .chanelFreeText9(UPDATED_CHANEL_FREE_TEXT_9)
            .chanelFreeText10(UPDATED_CHANEL_FREE_TEXT_10)
            .chanelFreeText11(UPDATED_CHANEL_FREE_TEXT_11)
            .chanelFreeText12(UPDATED_CHANEL_FREE_TEXT_12)
            .chanelFreeText13(UPDATED_CHANEL_FREE_TEXT_13)
            .chanelFreeText14(UPDATED_CHANEL_FREE_TEXT_14)
            .chanelFreeText15(UPDATED_CHANEL_FREE_TEXT_15)
            .chanelFreeText16(UPDATED_CHANEL_FREE_TEXT_16)
            .chanelFreeText17(UPDATED_CHANEL_FREE_TEXT_17)
            .chanelFreeText18(UPDATED_CHANEL_FREE_TEXT_18)
            .chanelFreeText19(UPDATED_CHANEL_FREE_TEXT_19)
            .chanelFreeText20(UPDATED_CHANEL_FREE_TEXT_20)
            .chanelFreeText21(UPDATED_CHANEL_FREE_TEXT_21)
            .chanelFreeText22(UPDATED_CHANEL_FREE_TEXT_22)
            .chanelFreeText23(UPDATED_CHANEL_FREE_TEXT_23)
            .chanelFreeText24(UPDATED_CHANEL_FREE_TEXT_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG);
        return dTransactionChannel;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DTransactionChannel.class).block();
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
        dTransactionChannelSearchRepository.deleteAll().block();
        assertThat(dTransactionChannelSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        dTransactionChannel = createEntity(em);
    }

    @Test
    void createDTransactionChannel() throws Exception {
        int databaseSizeBeforeCreate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        // Create the DTransactionChannel
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DTransactionChannel testDTransactionChannel = dTransactionChannelList.get(dTransactionChannelList.size() - 1);
        assertThat(testDTransactionChannel.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionChannel.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionChannel.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionChannel.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionChannel.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionChannel.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransactionChannel.getChannelCode()).isEqualTo(DEFAULT_CHANNEL_CODE);
        assertThat(testDTransactionChannel.getChannelName()).isEqualTo(DEFAULT_CHANNEL_NAME);
        assertThat(testDTransactionChannel.getChannelDescription()).isEqualTo(DEFAULT_CHANNEL_DESCRIPTION);
        assertThat(testDTransactionChannel.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionChannel.getChanelFreeText()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT);
        assertThat(testDTransactionChannel.getChanelFreeText1()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_1);
        assertThat(testDTransactionChannel.getChanelFreeText2()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_2);
        assertThat(testDTransactionChannel.getChanelFreeText3()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_3);
        assertThat(testDTransactionChannel.getChanelFreeText4()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_4);
        assertThat(testDTransactionChannel.getChanelFreeText5()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_5);
        assertThat(testDTransactionChannel.getChanelFreeText6()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_6);
        assertThat(testDTransactionChannel.getChanelFreeText7()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_7);
        assertThat(testDTransactionChannel.getChanelFreeText8()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_8);
        assertThat(testDTransactionChannel.getChanelFreeText9()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_9);
        assertThat(testDTransactionChannel.getChanelFreeText10()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_10);
        assertThat(testDTransactionChannel.getChanelFreeText11()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_11);
        assertThat(testDTransactionChannel.getChanelFreeText12()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_12);
        assertThat(testDTransactionChannel.getChanelFreeText13()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_13);
        assertThat(testDTransactionChannel.getChanelFreeText14()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_14);
        assertThat(testDTransactionChannel.getChanelFreeText15()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_15);
        assertThat(testDTransactionChannel.getChanelFreeText16()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_16);
        assertThat(testDTransactionChannel.getChanelFreeText17()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_17);
        assertThat(testDTransactionChannel.getChanelFreeText18()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_18);
        assertThat(testDTransactionChannel.getChanelFreeText19()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_19);
        assertThat(testDTransactionChannel.getChanelFreeText20()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_20);
        assertThat(testDTransactionChannel.getChanelFreeText21()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_21);
        assertThat(testDTransactionChannel.getChanelFreeText22()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_22);
        assertThat(testDTransactionChannel.getChanelFreeText23()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_23);
        assertThat(testDTransactionChannel.getChanelFreeText24()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_24);
        assertThat(testDTransactionChannel.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionChannel.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionChannel.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionChannel.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDTransactionChannel.getDelflg()).isEqualTo(DEFAULT_DELFLG);
    }

    @Test
    void createDTransactionChannelWithExistingId() throws Exception {
        // Create the DTransactionChannel with an existing ID
        dTransactionChannel.setId(1L);

        int databaseSizeBeforeCreate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionChannel.setBillerId(null);

        // Create the DTransactionChannel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionChannel.setPaymentItemCode(null);

        // Create the DTransactionChannel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkChannelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionChannel.setChannelCode(null);

        // Create the DTransactionChannel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkChannelNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionChannel.setChannelName(null);

        // Create the DTransactionChannel, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDTransactionChannelsAsStream() {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        List<DTransactionChannel> dTransactionChannelList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DTransactionChannel.class)
            .getResponseBody()
            .filter(dTransactionChannel::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(dTransactionChannelList).isNotNull();
        assertThat(dTransactionChannelList).hasSize(1);
        DTransactionChannel testDTransactionChannel = dTransactionChannelList.get(0);
        assertThat(testDTransactionChannel.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionChannel.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionChannel.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionChannel.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionChannel.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionChannel.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransactionChannel.getChannelCode()).isEqualTo(DEFAULT_CHANNEL_CODE);
        assertThat(testDTransactionChannel.getChannelName()).isEqualTo(DEFAULT_CHANNEL_NAME);
        assertThat(testDTransactionChannel.getChannelDescription()).isEqualTo(DEFAULT_CHANNEL_DESCRIPTION);
        assertThat(testDTransactionChannel.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionChannel.getChanelFreeText()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT);
        assertThat(testDTransactionChannel.getChanelFreeText1()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_1);
        assertThat(testDTransactionChannel.getChanelFreeText2()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_2);
        assertThat(testDTransactionChannel.getChanelFreeText3()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_3);
        assertThat(testDTransactionChannel.getChanelFreeText4()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_4);
        assertThat(testDTransactionChannel.getChanelFreeText5()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_5);
        assertThat(testDTransactionChannel.getChanelFreeText6()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_6);
        assertThat(testDTransactionChannel.getChanelFreeText7()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_7);
        assertThat(testDTransactionChannel.getChanelFreeText8()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_8);
        assertThat(testDTransactionChannel.getChanelFreeText9()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_9);
        assertThat(testDTransactionChannel.getChanelFreeText10()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_10);
        assertThat(testDTransactionChannel.getChanelFreeText11()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_11);
        assertThat(testDTransactionChannel.getChanelFreeText12()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_12);
        assertThat(testDTransactionChannel.getChanelFreeText13()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_13);
        assertThat(testDTransactionChannel.getChanelFreeText14()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_14);
        assertThat(testDTransactionChannel.getChanelFreeText15()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_15);
        assertThat(testDTransactionChannel.getChanelFreeText16()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_16);
        assertThat(testDTransactionChannel.getChanelFreeText17()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_17);
        assertThat(testDTransactionChannel.getChanelFreeText18()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_18);
        assertThat(testDTransactionChannel.getChanelFreeText19()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_19);
        assertThat(testDTransactionChannel.getChanelFreeText20()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_20);
        assertThat(testDTransactionChannel.getChanelFreeText21()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_21);
        assertThat(testDTransactionChannel.getChanelFreeText22()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_22);
        assertThat(testDTransactionChannel.getChanelFreeText23()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_23);
        assertThat(testDTransactionChannel.getChanelFreeText24()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_24);
        assertThat(testDTransactionChannel.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransactionChannel.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransactionChannel.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransactionChannel.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDTransactionChannel.getDelflg()).isEqualTo(DEFAULT_DELFLG);
    }

    @Test
    void getAllDTransactionChannels() {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        // Get all the dTransactionChannelList
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
            .value(hasItem(dTransactionChannel.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].channelCode")
            .value(hasItem(DEFAULT_CHANNEL_CODE))
            .jsonPath("$.[*].channelName")
            .value(hasItem(DEFAULT_CHANNEL_NAME))
            .jsonPath("$.[*].channelDescription")
            .value(hasItem(DEFAULT_CHANNEL_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].chanelFreeText")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT))
            .jsonPath("$.[*].chanelFreeText1")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_1))
            .jsonPath("$.[*].chanelFreeText2")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_2))
            .jsonPath("$.[*].chanelFreeText3")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_3))
            .jsonPath("$.[*].chanelFreeText4")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_4))
            .jsonPath("$.[*].chanelFreeText5")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_5))
            .jsonPath("$.[*].chanelFreeText6")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_6))
            .jsonPath("$.[*].chanelFreeText7")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_7))
            .jsonPath("$.[*].chanelFreeText8")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_8))
            .jsonPath("$.[*].chanelFreeText9")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_9))
            .jsonPath("$.[*].chanelFreeText10")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_10))
            .jsonPath("$.[*].chanelFreeText11")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_11))
            .jsonPath("$.[*].chanelFreeText12")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_12))
            .jsonPath("$.[*].chanelFreeText13")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_13))
            .jsonPath("$.[*].chanelFreeText14")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_14))
            .jsonPath("$.[*].chanelFreeText15")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_15))
            .jsonPath("$.[*].chanelFreeText16")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_16))
            .jsonPath("$.[*].chanelFreeText17")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_17))
            .jsonPath("$.[*].chanelFreeText18")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_18))
            .jsonPath("$.[*].chanelFreeText19")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_19))
            .jsonPath("$.[*].chanelFreeText20")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_20))
            .jsonPath("$.[*].chanelFreeText21")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_21))
            .jsonPath("$.[*].chanelFreeText22")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_22))
            .jsonPath("$.[*].chanelFreeText23")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_23))
            .jsonPath("$.[*].chanelFreeText24")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_24))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()));
    }

    @Test
    void getDTransactionChannel() {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        // Get the dTransactionChannel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dTransactionChannel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dTransactionChannel.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.paymentItemCode")
            .value(is(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.externalTranid")
            .value(is(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.channelCode")
            .value(is(DEFAULT_CHANNEL_CODE))
            .jsonPath("$.channelName")
            .value(is(DEFAULT_CHANNEL_NAME))
            .jsonPath("$.channelDescription")
            .value(is(DEFAULT_CHANNEL_DESCRIPTION))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.chanelFreeText")
            .value(is(DEFAULT_CHANEL_FREE_TEXT))
            .jsonPath("$.chanelFreeText1")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_1))
            .jsonPath("$.chanelFreeText2")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_2))
            .jsonPath("$.chanelFreeText3")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_3))
            .jsonPath("$.chanelFreeText4")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_4))
            .jsonPath("$.chanelFreeText5")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_5))
            .jsonPath("$.chanelFreeText6")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_6))
            .jsonPath("$.chanelFreeText7")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_7))
            .jsonPath("$.chanelFreeText8")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_8))
            .jsonPath("$.chanelFreeText9")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_9))
            .jsonPath("$.chanelFreeText10")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_10))
            .jsonPath("$.chanelFreeText11")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_11))
            .jsonPath("$.chanelFreeText12")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_12))
            .jsonPath("$.chanelFreeText13")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_13))
            .jsonPath("$.chanelFreeText14")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_14))
            .jsonPath("$.chanelFreeText15")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_15))
            .jsonPath("$.chanelFreeText16")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_16))
            .jsonPath("$.chanelFreeText17")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_17))
            .jsonPath("$.chanelFreeText18")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_18))
            .jsonPath("$.chanelFreeText19")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_19))
            .jsonPath("$.chanelFreeText20")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_20))
            .jsonPath("$.chanelFreeText21")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_21))
            .jsonPath("$.chanelFreeText22")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_22))
            .jsonPath("$.chanelFreeText23")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_23))
            .jsonPath("$.chanelFreeText24")
            .value(is(DEFAULT_CHANEL_FREE_TEXT_24))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY))
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()));
    }

    @Test
    void getNonExistingDTransactionChannel() {
        // Get the dTransactionChannel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDTransactionChannel() throws Exception {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        dTransactionChannelSearchRepository.save(dTransactionChannel).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());

        // Update the dTransactionChannel
        DTransactionChannel updatedDTransactionChannel = dTransactionChannelRepository.findById(dTransactionChannel.getId()).block();
        updatedDTransactionChannel
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .channelCode(UPDATED_CHANNEL_CODE)
            .channelName(UPDATED_CHANNEL_NAME)
            .channelDescription(UPDATED_CHANNEL_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .chanelFreeText(UPDATED_CHANEL_FREE_TEXT)
            .chanelFreeText1(UPDATED_CHANEL_FREE_TEXT_1)
            .chanelFreeText2(UPDATED_CHANEL_FREE_TEXT_2)
            .chanelFreeText3(UPDATED_CHANEL_FREE_TEXT_3)
            .chanelFreeText4(UPDATED_CHANEL_FREE_TEXT_4)
            .chanelFreeText5(UPDATED_CHANEL_FREE_TEXT_5)
            .chanelFreeText6(UPDATED_CHANEL_FREE_TEXT_6)
            .chanelFreeText7(UPDATED_CHANEL_FREE_TEXT_7)
            .chanelFreeText8(UPDATED_CHANEL_FREE_TEXT_8)
            .chanelFreeText9(UPDATED_CHANEL_FREE_TEXT_9)
            .chanelFreeText10(UPDATED_CHANEL_FREE_TEXT_10)
            .chanelFreeText11(UPDATED_CHANEL_FREE_TEXT_11)
            .chanelFreeText12(UPDATED_CHANEL_FREE_TEXT_12)
            .chanelFreeText13(UPDATED_CHANEL_FREE_TEXT_13)
            .chanelFreeText14(UPDATED_CHANEL_FREE_TEXT_14)
            .chanelFreeText15(UPDATED_CHANEL_FREE_TEXT_15)
            .chanelFreeText16(UPDATED_CHANEL_FREE_TEXT_16)
            .chanelFreeText17(UPDATED_CHANEL_FREE_TEXT_17)
            .chanelFreeText18(UPDATED_CHANEL_FREE_TEXT_18)
            .chanelFreeText19(UPDATED_CHANEL_FREE_TEXT_19)
            .chanelFreeText20(UPDATED_CHANEL_FREE_TEXT_20)
            .chanelFreeText21(UPDATED_CHANEL_FREE_TEXT_21)
            .chanelFreeText22(UPDATED_CHANEL_FREE_TEXT_22)
            .chanelFreeText23(UPDATED_CHANEL_FREE_TEXT_23)
            .chanelFreeText24(UPDATED_CHANEL_FREE_TEXT_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDTransactionChannel.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDTransactionChannel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        DTransactionChannel testDTransactionChannel = dTransactionChannelList.get(dTransactionChannelList.size() - 1);
        assertThat(testDTransactionChannel.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionChannel.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionChannel.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionChannel.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionChannel.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionChannel.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testDTransactionChannel.getChannelCode()).isEqualTo(UPDATED_CHANNEL_CODE);
        assertThat(testDTransactionChannel.getChannelName()).isEqualTo(UPDATED_CHANNEL_NAME);
        assertThat(testDTransactionChannel.getChannelDescription()).isEqualTo(UPDATED_CHANNEL_DESCRIPTION);
        assertThat(testDTransactionChannel.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionChannel.getChanelFreeText()).isEqualTo(UPDATED_CHANEL_FREE_TEXT);
        assertThat(testDTransactionChannel.getChanelFreeText1()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_1);
        assertThat(testDTransactionChannel.getChanelFreeText2()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_2);
        assertThat(testDTransactionChannel.getChanelFreeText3()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_3);
        assertThat(testDTransactionChannel.getChanelFreeText4()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_4);
        assertThat(testDTransactionChannel.getChanelFreeText5()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_5);
        assertThat(testDTransactionChannel.getChanelFreeText6()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_6);
        assertThat(testDTransactionChannel.getChanelFreeText7()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_7);
        assertThat(testDTransactionChannel.getChanelFreeText8()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_8);
        assertThat(testDTransactionChannel.getChanelFreeText9()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_9);
        assertThat(testDTransactionChannel.getChanelFreeText10()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_10);
        assertThat(testDTransactionChannel.getChanelFreeText11()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_11);
        assertThat(testDTransactionChannel.getChanelFreeText12()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_12);
        assertThat(testDTransactionChannel.getChanelFreeText13()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_13);
        assertThat(testDTransactionChannel.getChanelFreeText14()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_14);
        assertThat(testDTransactionChannel.getChanelFreeText15()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_15);
        assertThat(testDTransactionChannel.getChanelFreeText16()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_16);
        assertThat(testDTransactionChannel.getChanelFreeText17()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_17);
        assertThat(testDTransactionChannel.getChanelFreeText18()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_18);
        assertThat(testDTransactionChannel.getChanelFreeText19()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_19);
        assertThat(testDTransactionChannel.getChanelFreeText20()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_20);
        assertThat(testDTransactionChannel.getChanelFreeText21()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_21);
        assertThat(testDTransactionChannel.getChanelFreeText22()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_22);
        assertThat(testDTransactionChannel.getChanelFreeText23()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_23);
        assertThat(testDTransactionChannel.getChanelFreeText24()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_24);
        assertThat(testDTransactionChannel.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionChannel.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionChannel.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionChannel.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionChannel.getDelflg()).isEqualTo(UPDATED_DELFLG);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DTransactionChannel> dTransactionChannelSearchList = IterableUtils.toList(
                    dTransactionChannelSearchRepository.findAll().collectList().block()
                );
                DTransactionChannel testDTransactionChannelSearch = dTransactionChannelSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDTransactionChannelSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDTransactionChannelSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testDTransactionChannelSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testDTransactionChannelSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testDTransactionChannelSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testDTransactionChannelSearch.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
                assertThat(testDTransactionChannelSearch.getChannelCode()).isEqualTo(UPDATED_CHANNEL_CODE);
                assertThat(testDTransactionChannelSearch.getChannelName()).isEqualTo(UPDATED_CHANNEL_NAME);
                assertThat(testDTransactionChannelSearch.getChannelDescription()).isEqualTo(UPDATED_CHANNEL_DESCRIPTION);
                assertThat(testDTransactionChannelSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDTransactionChannelSearch.getChanelFreeText()).isEqualTo(UPDATED_CHANEL_FREE_TEXT);
                assertThat(testDTransactionChannelSearch.getChanelFreeText1()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_1);
                assertThat(testDTransactionChannelSearch.getChanelFreeText2()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_2);
                assertThat(testDTransactionChannelSearch.getChanelFreeText3()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_3);
                assertThat(testDTransactionChannelSearch.getChanelFreeText4()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_4);
                assertThat(testDTransactionChannelSearch.getChanelFreeText5()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_5);
                assertThat(testDTransactionChannelSearch.getChanelFreeText6()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_6);
                assertThat(testDTransactionChannelSearch.getChanelFreeText7()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_7);
                assertThat(testDTransactionChannelSearch.getChanelFreeText8()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_8);
                assertThat(testDTransactionChannelSearch.getChanelFreeText9()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_9);
                assertThat(testDTransactionChannelSearch.getChanelFreeText10()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_10);
                assertThat(testDTransactionChannelSearch.getChanelFreeText11()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_11);
                assertThat(testDTransactionChannelSearch.getChanelFreeText12()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_12);
                assertThat(testDTransactionChannelSearch.getChanelFreeText13()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_13);
                assertThat(testDTransactionChannelSearch.getChanelFreeText14()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_14);
                assertThat(testDTransactionChannelSearch.getChanelFreeText15()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_15);
                assertThat(testDTransactionChannelSearch.getChanelFreeText16()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_16);
                assertThat(testDTransactionChannelSearch.getChanelFreeText17()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_17);
                assertThat(testDTransactionChannelSearch.getChanelFreeText18()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_18);
                assertThat(testDTransactionChannelSearch.getChanelFreeText19()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_19);
                assertThat(testDTransactionChannelSearch.getChanelFreeText20()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_20);
                assertThat(testDTransactionChannelSearch.getChanelFreeText21()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_21);
                assertThat(testDTransactionChannelSearch.getChanelFreeText22()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_22);
                assertThat(testDTransactionChannelSearch.getChanelFreeText23()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_23);
                assertThat(testDTransactionChannelSearch.getChanelFreeText24()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_24);
                assertThat(testDTransactionChannelSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDTransactionChannelSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDTransactionChannelSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDTransactionChannelSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
                assertThat(testDTransactionChannelSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
            });
    }

    @Test
    void putNonExistingDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dTransactionChannel.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDTransactionChannelWithPatch() throws Exception {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();

        // Update the dTransactionChannel using partial update
        DTransactionChannel partialUpdatedDTransactionChannel = new DTransactionChannel();
        partialUpdatedDTransactionChannel.setId(dTransactionChannel.getId());

        partialUpdatedDTransactionChannel
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .channelDescription(UPDATED_CHANNEL_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .chanelFreeText1(UPDATED_CHANEL_FREE_TEXT_1)
            .chanelFreeText2(UPDATED_CHANEL_FREE_TEXT_2)
            .chanelFreeText5(UPDATED_CHANEL_FREE_TEXT_5)
            .chanelFreeText8(UPDATED_CHANEL_FREE_TEXT_8)
            .chanelFreeText10(UPDATED_CHANEL_FREE_TEXT_10)
            .chanelFreeText11(UPDATED_CHANEL_FREE_TEXT_11)
            .chanelFreeText13(UPDATED_CHANEL_FREE_TEXT_13)
            .chanelFreeText14(UPDATED_CHANEL_FREE_TEXT_14)
            .chanelFreeText15(UPDATED_CHANEL_FREE_TEXT_15)
            .chanelFreeText16(UPDATED_CHANEL_FREE_TEXT_16)
            .chanelFreeText18(UPDATED_CHANEL_FREE_TEXT_18)
            .chanelFreeText19(UPDATED_CHANEL_FREE_TEXT_19)
            .chanelFreeText20(UPDATED_CHANEL_FREE_TEXT_20)
            .chanelFreeText21(UPDATED_CHANEL_FREE_TEXT_21)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionChannel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionChannel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        DTransactionChannel testDTransactionChannel = dTransactionChannelList.get(dTransactionChannelList.size() - 1);
        assertThat(testDTransactionChannel.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionChannel.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionChannel.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionChannel.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionChannel.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionChannel.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransactionChannel.getChannelCode()).isEqualTo(DEFAULT_CHANNEL_CODE);
        assertThat(testDTransactionChannel.getChannelName()).isEqualTo(DEFAULT_CHANNEL_NAME);
        assertThat(testDTransactionChannel.getChannelDescription()).isEqualTo(UPDATED_CHANNEL_DESCRIPTION);
        assertThat(testDTransactionChannel.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionChannel.getChanelFreeText()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT);
        assertThat(testDTransactionChannel.getChanelFreeText1()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_1);
        assertThat(testDTransactionChannel.getChanelFreeText2()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_2);
        assertThat(testDTransactionChannel.getChanelFreeText3()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_3);
        assertThat(testDTransactionChannel.getChanelFreeText4()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_4);
        assertThat(testDTransactionChannel.getChanelFreeText5()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_5);
        assertThat(testDTransactionChannel.getChanelFreeText6()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_6);
        assertThat(testDTransactionChannel.getChanelFreeText7()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_7);
        assertThat(testDTransactionChannel.getChanelFreeText8()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_8);
        assertThat(testDTransactionChannel.getChanelFreeText9()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_9);
        assertThat(testDTransactionChannel.getChanelFreeText10()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_10);
        assertThat(testDTransactionChannel.getChanelFreeText11()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_11);
        assertThat(testDTransactionChannel.getChanelFreeText12()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_12);
        assertThat(testDTransactionChannel.getChanelFreeText13()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_13);
        assertThat(testDTransactionChannel.getChanelFreeText14()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_14);
        assertThat(testDTransactionChannel.getChanelFreeText15()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_15);
        assertThat(testDTransactionChannel.getChanelFreeText16()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_16);
        assertThat(testDTransactionChannel.getChanelFreeText17()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_17);
        assertThat(testDTransactionChannel.getChanelFreeText18()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_18);
        assertThat(testDTransactionChannel.getChanelFreeText19()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_19);
        assertThat(testDTransactionChannel.getChanelFreeText20()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_20);
        assertThat(testDTransactionChannel.getChanelFreeText21()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_21);
        assertThat(testDTransactionChannel.getChanelFreeText22()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_22);
        assertThat(testDTransactionChannel.getChanelFreeText23()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_23);
        assertThat(testDTransactionChannel.getChanelFreeText24()).isEqualTo(DEFAULT_CHANEL_FREE_TEXT_24);
        assertThat(testDTransactionChannel.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionChannel.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionChannel.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionChannel.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionChannel.getDelflg()).isEqualTo(DEFAULT_DELFLG);
    }

    @Test
    void fullUpdateDTransactionChannelWithPatch() throws Exception {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();

        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();

        // Update the dTransactionChannel using partial update
        DTransactionChannel partialUpdatedDTransactionChannel = new DTransactionChannel();
        partialUpdatedDTransactionChannel.setId(dTransactionChannel.getId());

        partialUpdatedDTransactionChannel
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .channelCode(UPDATED_CHANNEL_CODE)
            .channelName(UPDATED_CHANNEL_NAME)
            .channelDescription(UPDATED_CHANNEL_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .chanelFreeText(UPDATED_CHANEL_FREE_TEXT)
            .chanelFreeText1(UPDATED_CHANEL_FREE_TEXT_1)
            .chanelFreeText2(UPDATED_CHANEL_FREE_TEXT_2)
            .chanelFreeText3(UPDATED_CHANEL_FREE_TEXT_3)
            .chanelFreeText4(UPDATED_CHANEL_FREE_TEXT_4)
            .chanelFreeText5(UPDATED_CHANEL_FREE_TEXT_5)
            .chanelFreeText6(UPDATED_CHANEL_FREE_TEXT_6)
            .chanelFreeText7(UPDATED_CHANEL_FREE_TEXT_7)
            .chanelFreeText8(UPDATED_CHANEL_FREE_TEXT_8)
            .chanelFreeText9(UPDATED_CHANEL_FREE_TEXT_9)
            .chanelFreeText10(UPDATED_CHANEL_FREE_TEXT_10)
            .chanelFreeText11(UPDATED_CHANEL_FREE_TEXT_11)
            .chanelFreeText12(UPDATED_CHANEL_FREE_TEXT_12)
            .chanelFreeText13(UPDATED_CHANEL_FREE_TEXT_13)
            .chanelFreeText14(UPDATED_CHANEL_FREE_TEXT_14)
            .chanelFreeText15(UPDATED_CHANEL_FREE_TEXT_15)
            .chanelFreeText16(UPDATED_CHANEL_FREE_TEXT_16)
            .chanelFreeText17(UPDATED_CHANEL_FREE_TEXT_17)
            .chanelFreeText18(UPDATED_CHANEL_FREE_TEXT_18)
            .chanelFreeText19(UPDATED_CHANEL_FREE_TEXT_19)
            .chanelFreeText20(UPDATED_CHANEL_FREE_TEXT_20)
            .chanelFreeText21(UPDATED_CHANEL_FREE_TEXT_21)
            .chanelFreeText22(UPDATED_CHANEL_FREE_TEXT_22)
            .chanelFreeText23(UPDATED_CHANEL_FREE_TEXT_23)
            .chanelFreeText24(UPDATED_CHANEL_FREE_TEXT_24)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY)
            .delflg(UPDATED_DELFLG);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionChannel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionChannel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        DTransactionChannel testDTransactionChannel = dTransactionChannelList.get(dTransactionChannelList.size() - 1);
        assertThat(testDTransactionChannel.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionChannel.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionChannel.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionChannel.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionChannel.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionChannel.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testDTransactionChannel.getChannelCode()).isEqualTo(UPDATED_CHANNEL_CODE);
        assertThat(testDTransactionChannel.getChannelName()).isEqualTo(UPDATED_CHANNEL_NAME);
        assertThat(testDTransactionChannel.getChannelDescription()).isEqualTo(UPDATED_CHANNEL_DESCRIPTION);
        assertThat(testDTransactionChannel.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionChannel.getChanelFreeText()).isEqualTo(UPDATED_CHANEL_FREE_TEXT);
        assertThat(testDTransactionChannel.getChanelFreeText1()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_1);
        assertThat(testDTransactionChannel.getChanelFreeText2()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_2);
        assertThat(testDTransactionChannel.getChanelFreeText3()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_3);
        assertThat(testDTransactionChannel.getChanelFreeText4()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_4);
        assertThat(testDTransactionChannel.getChanelFreeText5()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_5);
        assertThat(testDTransactionChannel.getChanelFreeText6()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_6);
        assertThat(testDTransactionChannel.getChanelFreeText7()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_7);
        assertThat(testDTransactionChannel.getChanelFreeText8()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_8);
        assertThat(testDTransactionChannel.getChanelFreeText9()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_9);
        assertThat(testDTransactionChannel.getChanelFreeText10()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_10);
        assertThat(testDTransactionChannel.getChanelFreeText11()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_11);
        assertThat(testDTransactionChannel.getChanelFreeText12()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_12);
        assertThat(testDTransactionChannel.getChanelFreeText13()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_13);
        assertThat(testDTransactionChannel.getChanelFreeText14()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_14);
        assertThat(testDTransactionChannel.getChanelFreeText15()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_15);
        assertThat(testDTransactionChannel.getChanelFreeText16()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_16);
        assertThat(testDTransactionChannel.getChanelFreeText17()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_17);
        assertThat(testDTransactionChannel.getChanelFreeText18()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_18);
        assertThat(testDTransactionChannel.getChanelFreeText19()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_19);
        assertThat(testDTransactionChannel.getChanelFreeText20()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_20);
        assertThat(testDTransactionChannel.getChanelFreeText21()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_21);
        assertThat(testDTransactionChannel.getChanelFreeText22()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_22);
        assertThat(testDTransactionChannel.getChanelFreeText23()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_23);
        assertThat(testDTransactionChannel.getChanelFreeText24()).isEqualTo(UPDATED_CHANEL_FREE_TEXT_24);
        assertThat(testDTransactionChannel.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransactionChannel.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransactionChannel.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransactionChannel.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDTransactionChannel.getDelflg()).isEqualTo(UPDATED_DELFLG);
    }

    @Test
    void patchNonExistingDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dTransactionChannel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDTransactionChannel() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        dTransactionChannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionChannel))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionChannel in the database
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDTransactionChannel() {
        // Initialize the database
        dTransactionChannelRepository.save(dTransactionChannel).block();
        dTransactionChannelRepository.save(dTransactionChannel).block();
        dTransactionChannelSearchRepository.save(dTransactionChannel).block();

        int databaseSizeBeforeDelete = dTransactionChannelRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the dTransactionChannel
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dTransactionChannel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DTransactionChannel> dTransactionChannelList = dTransactionChannelRepository.findAll().collectList().block();
        assertThat(dTransactionChannelList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionChannelSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDTransactionChannel() {
        // Initialize the database
        dTransactionChannel = dTransactionChannelRepository.save(dTransactionChannel).block();
        dTransactionChannelSearchRepository.save(dTransactionChannel).block();

        // Search the dTransactionChannel
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + dTransactionChannel.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dTransactionChannel.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].paymentItemCode")
            .value(hasItem(DEFAULT_PAYMENT_ITEM_CODE))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].channelCode")
            .value(hasItem(DEFAULT_CHANNEL_CODE))
            .jsonPath("$.[*].channelName")
            .value(hasItem(DEFAULT_CHANNEL_NAME))
            .jsonPath("$.[*].channelDescription")
            .value(hasItem(DEFAULT_CHANNEL_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].chanelFreeText")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT))
            .jsonPath("$.[*].chanelFreeText1")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_1))
            .jsonPath("$.[*].chanelFreeText2")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_2))
            .jsonPath("$.[*].chanelFreeText3")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_3))
            .jsonPath("$.[*].chanelFreeText4")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_4))
            .jsonPath("$.[*].chanelFreeText5")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_5))
            .jsonPath("$.[*].chanelFreeText6")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_6))
            .jsonPath("$.[*].chanelFreeText7")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_7))
            .jsonPath("$.[*].chanelFreeText8")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_8))
            .jsonPath("$.[*].chanelFreeText9")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_9))
            .jsonPath("$.[*].chanelFreeText10")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_10))
            .jsonPath("$.[*].chanelFreeText11")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_11))
            .jsonPath("$.[*].chanelFreeText12")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_12))
            .jsonPath("$.[*].chanelFreeText13")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_13))
            .jsonPath("$.[*].chanelFreeText14")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_14))
            .jsonPath("$.[*].chanelFreeText15")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_15))
            .jsonPath("$.[*].chanelFreeText16")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_16))
            .jsonPath("$.[*].chanelFreeText17")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_17))
            .jsonPath("$.[*].chanelFreeText18")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_18))
            .jsonPath("$.[*].chanelFreeText19")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_19))
            .jsonPath("$.[*].chanelFreeText20")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_20))
            .jsonPath("$.[*].chanelFreeText21")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_21))
            .jsonPath("$.[*].chanelFreeText22")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_22))
            .jsonPath("$.[*].chanelFreeText23")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_23))
            .jsonPath("$.[*].chanelFreeText24")
            .value(hasItem(DEFAULT_CHANEL_FREE_TEXT_24))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()));
    }
}
