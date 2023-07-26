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
import ug.co.absa.microsrvc.gateway.domain.Devices;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.DevicesRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DevicesSearchRepository;

/**
 * Integration tests for the {@link DevicesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DevicesResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_RECORD_ID = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DEVICE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_DELFLG = false;
    private static final Boolean UPDATED_DELFLG = true;

    private static final RecordStatus DEFAULT_RECORD_STATUS = RecordStatus.ACTIVE;
    private static final RecordStatus UPDATED_RECORD_STATUS = RecordStatus.INACTIVE;

    private static final String DEFAULT_FREE_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_15 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_15 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_15_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_15_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FREE_FIELD_18 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FREE_FIELD_18 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FREE_FIELD_18_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FREE_FIELD_18_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/devices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/devices";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DevicesRepository devicesRepository;

    @Autowired
    private DevicesSearchRepository devicesSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Devices devices;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Devices createEntity(EntityManager em) {
        Devices devices = new Devices()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .recordId(DEFAULT_RECORD_ID)
            .deviceId(DEFAULT_DEVICE_ID)
            .deviceName(DEFAULT_DEVICE_NAME)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .deviceDescription(DEFAULT_DEVICE_DESCRIPTION)
            .timestamp(DEFAULT_TIMESTAMP)
            .delflg(DEFAULT_DELFLG)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .freeField(DEFAULT_FREE_FIELD)
            .freeField1(DEFAULT_FREE_FIELD_1)
            .freeField2(DEFAULT_FREE_FIELD_2)
            .freeField3(DEFAULT_FREE_FIELD_3)
            .freeField4(DEFAULT_FREE_FIELD_4)
            .freeField5(DEFAULT_FREE_FIELD_5)
            .freeField6(DEFAULT_FREE_FIELD_6)
            .freeField7(DEFAULT_FREE_FIELD_7)
            .freeField8(DEFAULT_FREE_FIELD_8)
            .freeField9(DEFAULT_FREE_FIELD_9)
            .freeField10(DEFAULT_FREE_FIELD_10)
            .freeField11(DEFAULT_FREE_FIELD_11)
            .freeField12(DEFAULT_FREE_FIELD_12)
            .freeField13(DEFAULT_FREE_FIELD_13)
            .freeField14(DEFAULT_FREE_FIELD_14)
            .freeField15(DEFAULT_FREE_FIELD_15)
            .freeField15ContentType(DEFAULT_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(DEFAULT_FREE_FIELD_16)
            .freeField17(DEFAULT_FREE_FIELD_17)
            .freeField18(DEFAULT_FREE_FIELD_18)
            .freeField18ContentType(DEFAULT_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(DEFAULT_FREE_FIELD_19)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return devices;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Devices createUpdatedEntity(EntityManager em) {
        Devices devices = new Devices()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .deviceId(UPDATED_DEVICE_ID)
            .deviceName(UPDATED_DEVICE_NAME)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceDescription(UPDATED_DEVICE_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .delflg(UPDATED_DELFLG)
            .recordStatus(UPDATED_RECORD_STATUS)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return devices;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Devices.class).block();
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
        devicesSearchRepository.deleteAll().block();
        assertThat(devicesSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        devices = createEntity(em);
    }

    @Test
    void createDevices() throws Exception {
        int databaseSizeBeforeCreate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        // Create the Devices
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Devices testDevices = devicesList.get(devicesList.size() - 1);
        assertThat(testDevices.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDevices.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDevices.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testDevices.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
        assertThat(testDevices.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testDevices.getDeviceDescription()).isEqualTo(DEFAULT_DEVICE_DESCRIPTION);
        assertThat(testDevices.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDevices.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testDevices.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDevices.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testDevices.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDevices.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDevices.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDevices.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDevices.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDevices.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDevices.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testDevices.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDevices.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDevices.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDevices.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testDevices.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testDevices.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testDevices.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testDevices.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testDevices.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDevices.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testDevices.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDevices.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testDevices.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDevices.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDevices.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDevices.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDevices.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDevices.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createDevicesWithExistingId() throws Exception {
        // Create the Devices with an existing ID
        devices.setId(1L);

        int databaseSizeBeforeCreate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDeviceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        // set the field null
        devices.setDeviceName(null);

        // Create the Devices, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDeviceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        // set the field null
        devices.setDeviceType(null);

        // Create the Devices, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        // set the field null
        devices.setTimestamp(null);

        // Create the Devices, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDevicesAsStream() {
        // Initialize the database
        devicesRepository.save(devices).block();

        List<Devices> devicesList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(Devices.class)
            .getResponseBody()
            .filter(devices::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(devicesList).isNotNull();
        assertThat(devicesList).hasSize(1);
        Devices testDevices = devicesList.get(0);
        assertThat(testDevices.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDevices.getRecordId()).isEqualTo(DEFAULT_RECORD_ID);
        assertThat(testDevices.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testDevices.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
        assertThat(testDevices.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testDevices.getDeviceDescription()).isEqualTo(DEFAULT_DEVICE_DESCRIPTION);
        assertThat(testDevices.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDevices.getDelflg()).isEqualTo(DEFAULT_DELFLG);
        assertThat(testDevices.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDevices.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testDevices.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDevices.getFreeField2()).isEqualTo(DEFAULT_FREE_FIELD_2);
        assertThat(testDevices.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDevices.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDevices.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDevices.getFreeField6()).isEqualTo(DEFAULT_FREE_FIELD_6);
        assertThat(testDevices.getFreeField7()).isEqualTo(DEFAULT_FREE_FIELD_7);
        assertThat(testDevices.getFreeField8()).isEqualTo(DEFAULT_FREE_FIELD_8);
        assertThat(testDevices.getFreeField9()).isEqualTo(DEFAULT_FREE_FIELD_9);
        assertThat(testDevices.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDevices.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testDevices.getFreeField12()).isEqualTo(DEFAULT_FREE_FIELD_12);
        assertThat(testDevices.getFreeField13()).isEqualTo(DEFAULT_FREE_FIELD_13);
        assertThat(testDevices.getFreeField14()).isEqualTo(DEFAULT_FREE_FIELD_14);
        assertThat(testDevices.getFreeField15()).isEqualTo(DEFAULT_FREE_FIELD_15);
        assertThat(testDevices.getFreeField15ContentType()).isEqualTo(DEFAULT_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDevices.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testDevices.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDevices.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testDevices.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDevices.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDevices.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDevices.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDevices.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDevices.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllDevices() {
        // Initialize the database
        devicesRepository.save(devices).block();

        // Get all the devicesList
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
            .value(hasItem(devices.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].deviceId")
            .value(hasItem(DEFAULT_DEVICE_ID))
            .jsonPath("$.[*].deviceName")
            .value(hasItem(DEFAULT_DEVICE_NAME))
            .jsonPath("$.[*].deviceType")
            .value(hasItem(DEFAULT_DEVICE_TYPE))
            .jsonPath("$.[*].deviceDescription")
            .value(hasItem(DEFAULT_DEVICE_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].freeField7")
            .value(hasItem(DEFAULT_FREE_FIELD_7))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.[*].freeField18ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
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
    void getDevices() {
        // Initialize the database
        devicesRepository.save(devices).block();

        // Get the devices
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, devices.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(devices.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.recordId")
            .value(is(DEFAULT_RECORD_ID))
            .jsonPath("$.deviceId")
            .value(is(DEFAULT_DEVICE_ID))
            .jsonPath("$.deviceName")
            .value(is(DEFAULT_DEVICE_NAME))
            .jsonPath("$.deviceType")
            .value(is(DEFAULT_DEVICE_TYPE))
            .jsonPath("$.deviceDescription")
            .value(is(DEFAULT_DEVICE_DESCRIPTION))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.delflg")
            .value(is(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS.toString()))
            .jsonPath("$.freeField")
            .value(is(DEFAULT_FREE_FIELD))
            .jsonPath("$.freeField1")
            .value(is(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.freeField2")
            .value(is(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.freeField3")
            .value(is(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.freeField4")
            .value(is(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.freeField5")
            .value(is(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.freeField6")
            .value(is(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.freeField7")
            .value(is(DEFAULT_FREE_FIELD_7))
            .jsonPath("$.freeField8")
            .value(is(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.freeField9")
            .value(is(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.freeField10")
            .value(is(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.freeField11")
            .value(is(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.freeField12")
            .value(is(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.freeField13")
            .value(is(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.freeField14")
            .value(is(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.freeField15ContentType")
            .value(is(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.freeField15")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.freeField16")
            .value(is(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.freeField17")
            .value(is(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.freeField18ContentType")
            .value(is(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.freeField18")
            .value(is(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.freeField19")
            .value(is(DEFAULT_FREE_FIELD_19))
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
    void getNonExistingDevices() {
        // Get the devices
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDevices() throws Exception {
        // Initialize the database
        devicesRepository.save(devices).block();

        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        devicesSearchRepository.save(devices).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());

        // Update the devices
        Devices updatedDevices = devicesRepository.findById(devices.getId()).block();
        updatedDevices
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .deviceId(UPDATED_DEVICE_ID)
            .deviceName(UPDATED_DEVICE_NAME)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceDescription(UPDATED_DEVICE_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .delflg(UPDATED_DELFLG)
            .recordStatus(UPDATED_RECORD_STATUS)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDevices.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDevices))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        Devices testDevices = devicesList.get(devicesList.size() - 1);
        assertThat(testDevices.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDevices.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDevices.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testDevices.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
        assertThat(testDevices.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testDevices.getDeviceDescription()).isEqualTo(UPDATED_DEVICE_DESCRIPTION);
        assertThat(testDevices.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDevices.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testDevices.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDevices.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testDevices.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDevices.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDevices.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDevices.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDevices.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDevices.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDevices.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testDevices.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDevices.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDevices.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testDevices.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testDevices.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDevices.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testDevices.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testDevices.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testDevices.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDevices.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testDevices.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testDevices.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testDevices.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDevices.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testDevices.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDevices.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDevices.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDevices.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Devices> devicesSearchList = IterableUtils.toList(devicesSearchRepository.findAll().collectList().block());
                Devices testDevicesSearch = devicesSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDevicesSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDevicesSearch.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
                assertThat(testDevicesSearch.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
                assertThat(testDevicesSearch.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
                assertThat(testDevicesSearch.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
                assertThat(testDevicesSearch.getDeviceDescription()).isEqualTo(UPDATED_DEVICE_DESCRIPTION);
                assertThat(testDevicesSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDevicesSearch.getDelflg()).isEqualTo(UPDATED_DELFLG);
                assertThat(testDevicesSearch.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
                assertThat(testDevicesSearch.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
                assertThat(testDevicesSearch.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
                assertThat(testDevicesSearch.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
                assertThat(testDevicesSearch.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
                assertThat(testDevicesSearch.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
                assertThat(testDevicesSearch.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
                assertThat(testDevicesSearch.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
                assertThat(testDevicesSearch.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
                assertThat(testDevicesSearch.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
                assertThat(testDevicesSearch.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
                assertThat(testDevicesSearch.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
                assertThat(testDevicesSearch.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
                assertThat(testDevicesSearch.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
                assertThat(testDevicesSearch.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
                assertThat(testDevicesSearch.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
                assertThat(testDevicesSearch.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
                assertThat(testDevicesSearch.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testDevicesSearch.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
                assertThat(testDevicesSearch.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
                assertThat(testDevicesSearch.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
                assertThat(testDevicesSearch.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
                assertThat(testDevicesSearch.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
                assertThat(testDevicesSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDevicesSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDevicesSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDevicesSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, devices.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDevicesWithPatch() throws Exception {
        // Initialize the database
        devicesRepository.save(devices).block();

        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();

        // Update the devices using partial update
        Devices partialUpdatedDevices = new Devices();
        partialUpdatedDevices.setId(devices.getId());

        partialUpdatedDevices
            .recordId(UPDATED_RECORD_ID)
            .deviceType(UPDATED_DEVICE_TYPE)
            .timestamp(UPDATED_TIMESTAMP)
            .delflg(UPDATED_DELFLG)
            .recordStatus(UPDATED_RECORD_STATUS)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDevices.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDevices))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        Devices testDevices = devicesList.get(devicesList.size() - 1);
        assertThat(testDevices.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDevices.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDevices.getDeviceId()).isEqualTo(DEFAULT_DEVICE_ID);
        assertThat(testDevices.getDeviceName()).isEqualTo(DEFAULT_DEVICE_NAME);
        assertThat(testDevices.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testDevices.getDeviceDescription()).isEqualTo(DEFAULT_DEVICE_DESCRIPTION);
        assertThat(testDevices.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDevices.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testDevices.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDevices.getFreeField()).isEqualTo(DEFAULT_FREE_FIELD);
        assertThat(testDevices.getFreeField1()).isEqualTo(DEFAULT_FREE_FIELD_1);
        assertThat(testDevices.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDevices.getFreeField3()).isEqualTo(DEFAULT_FREE_FIELD_3);
        assertThat(testDevices.getFreeField4()).isEqualTo(DEFAULT_FREE_FIELD_4);
        assertThat(testDevices.getFreeField5()).isEqualTo(DEFAULT_FREE_FIELD_5);
        assertThat(testDevices.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDevices.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testDevices.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDevices.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDevices.getFreeField10()).isEqualTo(DEFAULT_FREE_FIELD_10);
        assertThat(testDevices.getFreeField11()).isEqualTo(DEFAULT_FREE_FIELD_11);
        assertThat(testDevices.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDevices.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testDevices.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testDevices.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testDevices.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDevices.getFreeField16()).isEqualTo(DEFAULT_FREE_FIELD_16);
        assertThat(testDevices.getFreeField17()).isEqualTo(DEFAULT_FREE_FIELD_17);
        assertThat(testDevices.getFreeField18()).isEqualTo(DEFAULT_FREE_FIELD_18);
        assertThat(testDevices.getFreeField18ContentType()).isEqualTo(DEFAULT_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDevices.getFreeField19()).isEqualTo(DEFAULT_FREE_FIELD_19);
        assertThat(testDevices.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDevices.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDevices.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDevices.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateDevicesWithPatch() throws Exception {
        // Initialize the database
        devicesRepository.save(devices).block();

        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();

        // Update the devices using partial update
        Devices partialUpdatedDevices = new Devices();
        partialUpdatedDevices.setId(devices.getId());

        partialUpdatedDevices
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .recordId(UPDATED_RECORD_ID)
            .deviceId(UPDATED_DEVICE_ID)
            .deviceName(UPDATED_DEVICE_NAME)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceDescription(UPDATED_DEVICE_DESCRIPTION)
            .timestamp(UPDATED_TIMESTAMP)
            .delflg(UPDATED_DELFLG)
            .recordStatus(UPDATED_RECORD_STATUS)
            .freeField(UPDATED_FREE_FIELD)
            .freeField1(UPDATED_FREE_FIELD_1)
            .freeField2(UPDATED_FREE_FIELD_2)
            .freeField3(UPDATED_FREE_FIELD_3)
            .freeField4(UPDATED_FREE_FIELD_4)
            .freeField5(UPDATED_FREE_FIELD_5)
            .freeField6(UPDATED_FREE_FIELD_6)
            .freeField7(UPDATED_FREE_FIELD_7)
            .freeField8(UPDATED_FREE_FIELD_8)
            .freeField9(UPDATED_FREE_FIELD_9)
            .freeField10(UPDATED_FREE_FIELD_10)
            .freeField11(UPDATED_FREE_FIELD_11)
            .freeField12(UPDATED_FREE_FIELD_12)
            .freeField13(UPDATED_FREE_FIELD_13)
            .freeField14(UPDATED_FREE_FIELD_14)
            .freeField15(UPDATED_FREE_FIELD_15)
            .freeField15ContentType(UPDATED_FREE_FIELD_15_CONTENT_TYPE)
            .freeField16(UPDATED_FREE_FIELD_16)
            .freeField17(UPDATED_FREE_FIELD_17)
            .freeField18(UPDATED_FREE_FIELD_18)
            .freeField18ContentType(UPDATED_FREE_FIELD_18_CONTENT_TYPE)
            .freeField19(UPDATED_FREE_FIELD_19)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDevices.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDevices))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        Devices testDevices = devicesList.get(devicesList.size() - 1);
        assertThat(testDevices.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDevices.getRecordId()).isEqualTo(UPDATED_RECORD_ID);
        assertThat(testDevices.getDeviceId()).isEqualTo(UPDATED_DEVICE_ID);
        assertThat(testDevices.getDeviceName()).isEqualTo(UPDATED_DEVICE_NAME);
        assertThat(testDevices.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testDevices.getDeviceDescription()).isEqualTo(UPDATED_DEVICE_DESCRIPTION);
        assertThat(testDevices.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDevices.getDelflg()).isEqualTo(UPDATED_DELFLG);
        assertThat(testDevices.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDevices.getFreeField()).isEqualTo(UPDATED_FREE_FIELD);
        assertThat(testDevices.getFreeField1()).isEqualTo(UPDATED_FREE_FIELD_1);
        assertThat(testDevices.getFreeField2()).isEqualTo(UPDATED_FREE_FIELD_2);
        assertThat(testDevices.getFreeField3()).isEqualTo(UPDATED_FREE_FIELD_3);
        assertThat(testDevices.getFreeField4()).isEqualTo(UPDATED_FREE_FIELD_4);
        assertThat(testDevices.getFreeField5()).isEqualTo(UPDATED_FREE_FIELD_5);
        assertThat(testDevices.getFreeField6()).isEqualTo(UPDATED_FREE_FIELD_6);
        assertThat(testDevices.getFreeField7()).isEqualTo(UPDATED_FREE_FIELD_7);
        assertThat(testDevices.getFreeField8()).isEqualTo(UPDATED_FREE_FIELD_8);
        assertThat(testDevices.getFreeField9()).isEqualTo(UPDATED_FREE_FIELD_9);
        assertThat(testDevices.getFreeField10()).isEqualTo(UPDATED_FREE_FIELD_10);
        assertThat(testDevices.getFreeField11()).isEqualTo(UPDATED_FREE_FIELD_11);
        assertThat(testDevices.getFreeField12()).isEqualTo(UPDATED_FREE_FIELD_12);
        assertThat(testDevices.getFreeField13()).isEqualTo(UPDATED_FREE_FIELD_13);
        assertThat(testDevices.getFreeField14()).isEqualTo(UPDATED_FREE_FIELD_14);
        assertThat(testDevices.getFreeField15()).isEqualTo(UPDATED_FREE_FIELD_15);
        assertThat(testDevices.getFreeField15ContentType()).isEqualTo(UPDATED_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDevices.getFreeField16()).isEqualTo(UPDATED_FREE_FIELD_16);
        assertThat(testDevices.getFreeField17()).isEqualTo(UPDATED_FREE_FIELD_17);
        assertThat(testDevices.getFreeField18()).isEqualTo(UPDATED_FREE_FIELD_18);
        assertThat(testDevices.getFreeField18ContentType()).isEqualTo(UPDATED_FREE_FIELD_18_CONTENT_TYPE);
        assertThat(testDevices.getFreeField19()).isEqualTo(UPDATED_FREE_FIELD_19);
        assertThat(testDevices.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDevices.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDevices.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDevices.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, devices.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDevices() throws Exception {
        int databaseSizeBeforeUpdate = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        devices.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(devices))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Devices in the database
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDevices() {
        // Initialize the database
        devicesRepository.save(devices).block();
        devicesRepository.save(devices).block();
        devicesSearchRepository.save(devices).block();

        int databaseSizeBeforeDelete = devicesRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the devices
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, devices.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Devices> devicesList = devicesRepository.findAll().collectList().block();
        assertThat(devicesList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(devicesSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDevices() {
        // Initialize the database
        devices = devicesRepository.save(devices).block();
        devicesSearchRepository.save(devices).block();

        // Search the devices
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + devices.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(devices.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].recordId")
            .value(hasItem(DEFAULT_RECORD_ID))
            .jsonPath("$.[*].deviceId")
            .value(hasItem(DEFAULT_DEVICE_ID))
            .jsonPath("$.[*].deviceName")
            .value(hasItem(DEFAULT_DEVICE_NAME))
            .jsonPath("$.[*].deviceType")
            .value(hasItem(DEFAULT_DEVICE_TYPE))
            .jsonPath("$.[*].deviceDescription")
            .value(hasItem(DEFAULT_DEVICE_DESCRIPTION))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].delflg")
            .value(hasItem(DEFAULT_DELFLG.booleanValue()))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS.toString()))
            .jsonPath("$.[*].freeField")
            .value(hasItem(DEFAULT_FREE_FIELD))
            .jsonPath("$.[*].freeField1")
            .value(hasItem(DEFAULT_FREE_FIELD_1))
            .jsonPath("$.[*].freeField2")
            .value(hasItem(DEFAULT_FREE_FIELD_2))
            .jsonPath("$.[*].freeField3")
            .value(hasItem(DEFAULT_FREE_FIELD_3))
            .jsonPath("$.[*].freeField4")
            .value(hasItem(DEFAULT_FREE_FIELD_4))
            .jsonPath("$.[*].freeField5")
            .value(hasItem(DEFAULT_FREE_FIELD_5))
            .jsonPath("$.[*].freeField6")
            .value(hasItem(DEFAULT_FREE_FIELD_6))
            .jsonPath("$.[*].freeField7")
            .value(hasItem(DEFAULT_FREE_FIELD_7))
            .jsonPath("$.[*].freeField8")
            .value(hasItem(DEFAULT_FREE_FIELD_8))
            .jsonPath("$.[*].freeField9")
            .value(hasItem(DEFAULT_FREE_FIELD_9))
            .jsonPath("$.[*].freeField10")
            .value(hasItem(DEFAULT_FREE_FIELD_10))
            .jsonPath("$.[*].freeField11")
            .value(hasItem(DEFAULT_FREE_FIELD_11))
            .jsonPath("$.[*].freeField12")
            .value(hasItem(DEFAULT_FREE_FIELD_12))
            .jsonPath("$.[*].freeField13")
            .value(hasItem(DEFAULT_FREE_FIELD_13))
            .jsonPath("$.[*].freeField14")
            .value(hasItem(DEFAULT_FREE_FIELD_14))
            .jsonPath("$.[*].freeField15ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].freeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_15)))
            .jsonPath("$.[*].freeField16")
            .value(hasItem(DEFAULT_FREE_FIELD_16.toString()))
            .jsonPath("$.[*].freeField17")
            .value(hasItem(DEFAULT_FREE_FIELD_17.toString()))
            .jsonPath("$.[*].freeField18ContentType")
            .value(hasItem(DEFAULT_FREE_FIELD_18_CONTENT_TYPE))
            .jsonPath("$.[*].freeField18")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_FREE_FIELD_18)))
            .jsonPath("$.[*].freeField19")
            .value(hasItem(DEFAULT_FREE_FIELD_19))
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
