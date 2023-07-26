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
import java.time.temporal.ChronoUnit;
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
import ug.co.absa.microsrvc.gateway.domain.DTransaction;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.DTransactionRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionSearchRepository;

/**
 * Integration tests for the {@link DTransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DTransactionResourceIT {

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

    private static final String DEFAULT_EXTERNAL_TRANID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TRANID = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final Channel DEFAULT_PAYMENT_CHANNEL_CODE = Channel.ATM;
    private static final Channel UPDATED_PAYMENT_CHANNEL_CODE = Channel.CDM;

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEBIT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEBIT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREDIT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SETTLEMENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SETTLEMENT_AMOUNT = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_SETTLEMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETTLEMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRANSACTION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRANSACTION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_RETRIED = false;
    private static final Boolean UPDATED_IS_RETRIED = true;

    private static final ZonedDateTime DEFAULT_LAST_RETRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_RETRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_FIRST_RETRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FIRST_RETRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ErrorCodes DEFAULT_RETRY_RESPOSE_CODE = ErrorCodes.SUCCESS;
    private static final ErrorCodes UPDATED_RETRY_RESPOSE_CODE = ErrorCodes.PENDING;

    private static final ErrorMessage DEFAULT_RETRY_RESPONSE_MESSAGE = ErrorMessage.TRANSACTIONSUCCESS;
    private static final ErrorMessage UPDATED_RETRY_RESPONSE_MESSAGE = ErrorMessage.TRANSACTIONFAILED;

    private static final Integer DEFAULT_RETRY_COUNT = 1;
    private static final Integer UPDATED_RETRY_COUNT = 2;

    private static final Boolean DEFAULT_IS_RETRIABLE_FLG = false;
    private static final Boolean UPDATED_IS_RETRIABLE_FLG = true;

    private static final Boolean DEFAULT_DO_NOT_RETRY_D_TRANSACTION = false;
    private static final Boolean UPDATED_DO_NOT_RETRY_D_TRANSACTION = true;

    private static final TranStatus DEFAULT_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DETAILS = "BBBBBBBBBB";

    private static final Integer DEFAULT_RETRIES = 1;
    private static final Integer UPDATED_RETRIES = 2;

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_POSTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_POSTED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_POSTED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CROSS_CURRENCY = false;
    private static final Boolean UPDATED_IS_CROSS_CURRENCY = true;

    private static final Boolean DEFAULT_IS_CROSS_BANK = false;
    private static final Boolean UPDATED_IS_CROSS_BANK = true;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DEBIT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NUMBER = "BBBBBBBBBB";

    private static final TranStatus DEFAULT_TRAN_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_TRAN_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_TRAN_STATUS_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_STATUS_DETAILS = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_TRAN_FREE_FIELD_13 = 1;
    private static final Integer UPDATED_TRAN_FREE_FIELD_13 = 2;

    private static final byte[] DEFAULT_TRAN_FREE_FIELD_14 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TRAN_FREE_FIELD_14 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TRAN_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TRAN_FREE_FIELD_16 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRAN_FREE_FIELD_16 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_TRAN_FREE_FIELD_17 = false;
    private static final Boolean UPDATED_TRAN_FREE_FIELD_17 = true;

    private static final String DEFAULT_TRAN_FREE_FIELD_18 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_18 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TRAN_FREE_FIELD_20 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRAN_FREE_FIELD_20 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Instant DEFAULT_TRAN_FREE_FIELD_21 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRAN_FREE_FIELD_21 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_TRAN_FREE_FIELD_22 = false;
    private static final Boolean UPDATED_TRAN_FREE_FIELD_22 = true;

    private static final Instant DEFAULT_TRAN_FREE_FIELD_23 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRAN_FREE_FIELD_23 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_TRAN_FREE_FIELD_24 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_24 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_25 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_25 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_26 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_26 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_27 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_27 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_28 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_28 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/d-transactions";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DTransactionRepository dTransactionRepository;

    @Autowired
    private DTransactionSearchRepository dTransactionSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DTransaction dTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransaction createEntity(EntityManager em) {
        DTransaction dTransaction = new DTransaction()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(DEFAULT_EXTERNAL_TRANID)
            .token(DEFAULT_TOKEN)
            .transferId(DEFAULT_TRANSFER_ID)
            .productCode(DEFAULT_PRODUCT_CODE)
            .paymentChannelCode(DEFAULT_PAYMENT_CHANNEL_CODE)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .amount(DEFAULT_AMOUNT)
            .debitAmount(DEFAULT_DEBIT_AMOUNT)
            .creditAmount(DEFAULT_CREDIT_AMOUNT)
            .settlementAmount(DEFAULT_SETTLEMENT_AMOUNT)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .isRetried(DEFAULT_IS_RETRIED)
            .lastRetryDate(DEFAULT_LAST_RETRY_DATE)
            .firstRetryDate(DEFAULT_FIRST_RETRY_DATE)
            .retryResposeCode(DEFAULT_RETRY_RESPOSE_CODE)
            .retryResponseMessage(DEFAULT_RETRY_RESPONSE_MESSAGE)
            .retryCount(DEFAULT_RETRY_COUNT)
            .isRetriableFlg(DEFAULT_IS_RETRIABLE_FLG)
            .doNotRetryDTransaction(DEFAULT_DO_NOT_RETRY_D_TRANSACTION)
            .status(DEFAULT_STATUS)
            .statusCode(DEFAULT_STATUS_CODE)
            .statusDetails(DEFAULT_STATUS_DETAILS)
            .retries(DEFAULT_RETRIES)
            .timestamp(DEFAULT_TIMESTAMP)
            .postedBy(DEFAULT_POSTED_BY)
            .postedDate(DEFAULT_POSTED_DATE)
            .internalErrorCode(DEFAULT_INTERNAL_ERROR_CODE)
            .externalErrorCode(DEFAULT_EXTERNAL_ERROR_CODE)
            .isCrossCurrency(DEFAULT_IS_CROSS_CURRENCY)
            .isCrossBank(DEFAULT_IS_CROSS_BANK)
            .currency(DEFAULT_CURRENCY)
            .creditAccount(DEFAULT_CREDIT_ACCOUNT)
            .debitAccount(DEFAULT_DEBIT_ACCOUNT)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .customerNumber(DEFAULT_CUSTOMER_NUMBER)
            .tranStatus(DEFAULT_TRAN_STATUS)
            .tranStatusDetails(DEFAULT_TRAN_STATUS_DETAILS)
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
            .tranFreeField14ContentType(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE)
            .tranFreeField15(DEFAULT_TRAN_FREE_FIELD_15)
            .tranFreeField16(DEFAULT_TRAN_FREE_FIELD_16)
            .tranFreeField17(DEFAULT_TRAN_FREE_FIELD_17)
            .tranFreeField18(DEFAULT_TRAN_FREE_FIELD_18)
            .tranFreeField19(DEFAULT_TRAN_FREE_FIELD_19)
            .tranFreeField20(DEFAULT_TRAN_FREE_FIELD_20)
            .tranFreeField21(DEFAULT_TRAN_FREE_FIELD_21)
            .tranFreeField22(DEFAULT_TRAN_FREE_FIELD_22)
            .tranFreeField23(DEFAULT_TRAN_FREE_FIELD_23)
            .tranFreeField24(DEFAULT_TRAN_FREE_FIELD_24)
            .tranFreeField25(DEFAULT_TRAN_FREE_FIELD_25)
            .tranFreeField26(DEFAULT_TRAN_FREE_FIELD_26)
            .tranFreeField27(DEFAULT_TRAN_FREE_FIELD_27)
            .tranFreeField28(DEFAULT_TRAN_FREE_FIELD_28)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return dTransaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransaction createUpdatedEntity(EntityManager em) {
        DTransaction dTransaction = new DTransaction()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .amount(UPDATED_AMOUNT)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .isRetried(UPDATED_IS_RETRIED)
            .lastRetryDate(UPDATED_LAST_RETRY_DATE)
            .firstRetryDate(UPDATED_FIRST_RETRY_DATE)
            .retryResposeCode(UPDATED_RETRY_RESPOSE_CODE)
            .retryResponseMessage(UPDATED_RETRY_RESPONSE_MESSAGE)
            .retryCount(UPDATED_RETRY_COUNT)
            .isRetriableFlg(UPDATED_IS_RETRIABLE_FLG)
            .doNotRetryDTransaction(UPDATED_DO_NOT_RETRY_D_TRANSACTION)
            .status(UPDATED_STATUS)
            .statusCode(UPDATED_STATUS_CODE)
            .statusDetails(UPDATED_STATUS_DETAILS)
            .retries(UPDATED_RETRIES)
            .timestamp(UPDATED_TIMESTAMP)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .isCrossCurrency(UPDATED_IS_CROSS_CURRENCY)
            .isCrossBank(UPDATED_IS_CROSS_BANK)
            .currency(UPDATED_CURRENCY)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .tranStatusDetails(UPDATED_TRAN_STATUS_DETAILS)
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
            .tranFreeField14ContentType(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return dTransaction;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DTransaction.class).block();
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
        dTransactionSearchRepository.deleteAll().block();
        assertThat(dTransactionSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        dTransaction = createEntity(em);
    }

    @Test
    void createDTransaction() throws Exception {
        int databaseSizeBeforeCreate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // Create the DTransaction
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DTransaction testDTransaction = dTransactionList.get(dTransactionList.size() - 1);
        assertThat(testDTransaction.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransaction.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransaction.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransaction.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransaction.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransaction.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransaction.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransaction.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDTransaction.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransaction.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransaction.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransaction.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testDTransaction.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testDTransaction.getDebitAmount()).isEqualByComparingTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransaction.getCreditAmount()).isEqualByComparingTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransaction.getSettlementAmount()).isEqualByComparingTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testDTransaction.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransaction.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testDTransaction.getIsRetried()).isEqualTo(DEFAULT_IS_RETRIED);
        assertThat(testDTransaction.getLastRetryDate()).isEqualTo(DEFAULT_LAST_RETRY_DATE);
        assertThat(testDTransaction.getFirstRetryDate()).isEqualTo(DEFAULT_FIRST_RETRY_DATE);
        assertThat(testDTransaction.getRetryResposeCode()).isEqualTo(DEFAULT_RETRY_RESPOSE_CODE);
        assertThat(testDTransaction.getRetryResponseMessage()).isEqualTo(DEFAULT_RETRY_RESPONSE_MESSAGE);
        assertThat(testDTransaction.getRetryCount()).isEqualTo(DEFAULT_RETRY_COUNT);
        assertThat(testDTransaction.getIsRetriableFlg()).isEqualTo(DEFAULT_IS_RETRIABLE_FLG);
        assertThat(testDTransaction.getDoNotRetryDTransaction()).isEqualTo(DEFAULT_DO_NOT_RETRY_D_TRANSACTION);
        assertThat(testDTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransaction.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testDTransaction.getStatusDetails()).isEqualTo(DEFAULT_STATUS_DETAILS);
        assertThat(testDTransaction.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransaction.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransaction.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransaction.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransaction.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getIsCrossCurrency()).isEqualTo(DEFAULT_IS_CROSS_CURRENCY);
        assertThat(testDTransaction.getIsCrossBank()).isEqualTo(DEFAULT_IS_CROSS_BANK);
        assertThat(testDTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testDTransaction.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
        assertThat(testDTransaction.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
        assertThat(testDTransaction.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransaction.getCustomerNumber()).isEqualTo(DEFAULT_CUSTOMER_NUMBER);
        assertThat(testDTransaction.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransaction.getTranStatusDetails()).isEqualTo(DEFAULT_TRAN_STATUS_DETAILS);
        assertThat(testDTransaction.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransaction.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testDTransaction.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransaction.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransaction.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransaction.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransaction.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransaction.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransaction.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransaction.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransaction.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransaction.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransaction.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransaction.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testDTransaction.getTranFreeField14ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testDTransaction.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testDTransaction.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransaction.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testDTransaction.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransaction.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testDTransaction.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransaction.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransaction.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testDTransaction.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testDTransaction.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testDTransaction.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testDTransaction.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransaction.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testDTransaction.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testDTransaction.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransaction.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransaction.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createDTransactionWithExistingId() throws Exception {
        // Create the DTransaction with an existing ID
        dTransaction.setId(1L);

        int databaseSizeBeforeCreate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setBillerId(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setPaymentItemCode(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setProductCode(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentChannelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setPaymentChannelCode(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setAccountNumber(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setAmount(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setDebitAmount(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setCurrency(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreatedAtIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        // set the field null
        dTransaction.setCreatedAt(null);

        // Create the DTransaction, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDTransactionsAsStream() {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        List<DTransaction> dTransactionList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DTransaction.class)
            .getResponseBody()
            .filter(dTransaction::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(dTransactionList).isNotNull();
        assertThat(dTransactionList).hasSize(1);
        DTransaction testDTransaction = dTransactionList.get(0);
        assertThat(testDTransaction.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransaction.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransaction.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransaction.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransaction.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransaction.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransaction.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransaction.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDTransaction.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransaction.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransaction.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransaction.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testDTransaction.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testDTransaction.getDebitAmount()).isEqualByComparingTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransaction.getCreditAmount()).isEqualByComparingTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransaction.getSettlementAmount()).isEqualByComparingTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testDTransaction.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransaction.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testDTransaction.getIsRetried()).isEqualTo(DEFAULT_IS_RETRIED);
        assertThat(testDTransaction.getLastRetryDate()).isEqualTo(DEFAULT_LAST_RETRY_DATE);
        assertThat(testDTransaction.getFirstRetryDate()).isEqualTo(DEFAULT_FIRST_RETRY_DATE);
        assertThat(testDTransaction.getRetryResposeCode()).isEqualTo(DEFAULT_RETRY_RESPOSE_CODE);
        assertThat(testDTransaction.getRetryResponseMessage()).isEqualTo(DEFAULT_RETRY_RESPONSE_MESSAGE);
        assertThat(testDTransaction.getRetryCount()).isEqualTo(DEFAULT_RETRY_COUNT);
        assertThat(testDTransaction.getIsRetriableFlg()).isEqualTo(DEFAULT_IS_RETRIABLE_FLG);
        assertThat(testDTransaction.getDoNotRetryDTransaction()).isEqualTo(DEFAULT_DO_NOT_RETRY_D_TRANSACTION);
        assertThat(testDTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransaction.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testDTransaction.getStatusDetails()).isEqualTo(DEFAULT_STATUS_DETAILS);
        assertThat(testDTransaction.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransaction.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransaction.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransaction.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransaction.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getIsCrossCurrency()).isEqualTo(DEFAULT_IS_CROSS_CURRENCY);
        assertThat(testDTransaction.getIsCrossBank()).isEqualTo(DEFAULT_IS_CROSS_BANK);
        assertThat(testDTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testDTransaction.getCreditAccount()).isEqualTo(DEFAULT_CREDIT_ACCOUNT);
        assertThat(testDTransaction.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
        assertThat(testDTransaction.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransaction.getCustomerNumber()).isEqualTo(DEFAULT_CUSTOMER_NUMBER);
        assertThat(testDTransaction.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransaction.getTranStatusDetails()).isEqualTo(DEFAULT_TRAN_STATUS_DETAILS);
        assertThat(testDTransaction.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransaction.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testDTransaction.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransaction.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransaction.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransaction.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransaction.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransaction.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransaction.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransaction.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransaction.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransaction.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransaction.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransaction.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testDTransaction.getTranFreeField14ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testDTransaction.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testDTransaction.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransaction.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testDTransaction.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransaction.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testDTransaction.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransaction.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransaction.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testDTransaction.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testDTransaction.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testDTransaction.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testDTransaction.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransaction.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testDTransaction.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testDTransaction.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testDTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransaction.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransaction.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllDTransactions() {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        // Get all the dTransactionList
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
            .value(hasItem(dTransaction.getId().intValue()))
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
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].accountNumber")
            .value(hasItem(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].amount")
            .value(hasItem(sameNumber(DEFAULT_AMOUNT)))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.[*].settlementAmount")
            .value(hasItem(sameNumber(DEFAULT_SETTLEMENT_AMOUNT)))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].isRetried")
            .value(hasItem(DEFAULT_IS_RETRIED.booleanValue()))
            .jsonPath("$.[*].lastRetryDate")
            .value(hasItem(sameInstant(DEFAULT_LAST_RETRY_DATE)))
            .jsonPath("$.[*].firstRetryDate")
            .value(hasItem(sameInstant(DEFAULT_FIRST_RETRY_DATE)))
            .jsonPath("$.[*].retryResposeCode")
            .value(hasItem(DEFAULT_RETRY_RESPOSE_CODE.toString()))
            .jsonPath("$.[*].retryResponseMessage")
            .value(hasItem(DEFAULT_RETRY_RESPONSE_MESSAGE.toString()))
            .jsonPath("$.[*].retryCount")
            .value(hasItem(DEFAULT_RETRY_COUNT))
            .jsonPath("$.[*].isRetriableFlg")
            .value(hasItem(DEFAULT_IS_RETRIABLE_FLG.booleanValue()))
            .jsonPath("$.[*].doNotRetryDTransaction")
            .value(hasItem(DEFAULT_DO_NOT_RETRY_D_TRANSACTION.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].statusCode")
            .value(hasItem(DEFAULT_STATUS_CODE))
            .jsonPath("$.[*].statusDetails")
            .value(hasItem(DEFAULT_STATUS_DETAILS))
            .jsonPath("$.[*].retries")
            .value(hasItem(DEFAULT_RETRIES))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].postedBy")
            .value(hasItem(DEFAULT_POSTED_BY))
            .jsonPath("$.[*].postedDate")
            .value(hasItem(DEFAULT_POSTED_DATE))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].isCrossCurrency")
            .value(hasItem(DEFAULT_IS_CROSS_CURRENCY.booleanValue()))
            .jsonPath("$.[*].isCrossBank")
            .value(hasItem(DEFAULT_IS_CROSS_BANK.booleanValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].creditAccount")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT))
            .jsonPath("$.[*].debitAccount")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].customerNumber")
            .value(hasItem(DEFAULT_CUSTOMER_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].tranStatusDetails")
            .value(hasItem(DEFAULT_TRAN_STATUS_DETAILS))
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
            .jsonPath("$.[*].tranFreeField14ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_14)))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15.toString()))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_16)))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17.booleanValue()))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_18.toString()))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19.toString()))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_20)))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21.toString()))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.booleanValue()))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23.toString()))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_24))
            .jsonPath("$.[*].tranFreeField25")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.[*].tranFreeField26")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.[*].tranFreeField27")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.[*].tranFreeField28")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_28))
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
    void getDTransaction() {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        // Get the dTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dTransaction.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dTransaction.getId().intValue()))
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
            .jsonPath("$.externalTranid")
            .value(is(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.token")
            .value(is(DEFAULT_TOKEN))
            .jsonPath("$.transferId")
            .value(is(DEFAULT_TRANSFER_ID))
            .jsonPath("$.productCode")
            .value(is(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.paymentChannelCode")
            .value(is(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.accountNumber")
            .value(is(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.amount")
            .value(is(sameNumber(DEFAULT_AMOUNT)))
            .jsonPath("$.debitAmount")
            .value(is(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.creditAmount")
            .value(is(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.settlementAmount")
            .value(is(sameNumber(DEFAULT_SETTLEMENT_AMOUNT)))
            .jsonPath("$.settlementDate")
            .value(is(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.transactionDate")
            .value(is(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.isRetried")
            .value(is(DEFAULT_IS_RETRIED.booleanValue()))
            .jsonPath("$.lastRetryDate")
            .value(is(sameInstant(DEFAULT_LAST_RETRY_DATE)))
            .jsonPath("$.firstRetryDate")
            .value(is(sameInstant(DEFAULT_FIRST_RETRY_DATE)))
            .jsonPath("$.retryResposeCode")
            .value(is(DEFAULT_RETRY_RESPOSE_CODE.toString()))
            .jsonPath("$.retryResponseMessage")
            .value(is(DEFAULT_RETRY_RESPONSE_MESSAGE.toString()))
            .jsonPath("$.retryCount")
            .value(is(DEFAULT_RETRY_COUNT))
            .jsonPath("$.isRetriableFlg")
            .value(is(DEFAULT_IS_RETRIABLE_FLG.booleanValue()))
            .jsonPath("$.doNotRetryDTransaction")
            .value(is(DEFAULT_DO_NOT_RETRY_D_TRANSACTION.booleanValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.statusCode")
            .value(is(DEFAULT_STATUS_CODE))
            .jsonPath("$.statusDetails")
            .value(is(DEFAULT_STATUS_DETAILS))
            .jsonPath("$.retries")
            .value(is(DEFAULT_RETRIES))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.postedBy")
            .value(is(DEFAULT_POSTED_BY))
            .jsonPath("$.postedDate")
            .value(is(DEFAULT_POSTED_DATE))
            .jsonPath("$.internalErrorCode")
            .value(is(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.externalErrorCode")
            .value(is(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.isCrossCurrency")
            .value(is(DEFAULT_IS_CROSS_CURRENCY.booleanValue()))
            .jsonPath("$.isCrossBank")
            .value(is(DEFAULT_IS_CROSS_BANK.booleanValue()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.creditAccount")
            .value(is(DEFAULT_CREDIT_ACCOUNT))
            .jsonPath("$.debitAccount")
            .value(is(DEFAULT_DEBIT_ACCOUNT))
            .jsonPath("$.phoneNumber")
            .value(is(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.customerNumber")
            .value(is(DEFAULT_CUSTOMER_NUMBER))
            .jsonPath("$.tranStatus")
            .value(is(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.tranStatusDetails")
            .value(is(DEFAULT_TRAN_STATUS_DETAILS))
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
            .jsonPath("$.tranFreeField14ContentType")
            .value(is(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.tranFreeField14")
            .value(is(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_14)))
            .jsonPath("$.tranFreeField15")
            .value(is(DEFAULT_TRAN_FREE_FIELD_15.toString()))
            .jsonPath("$.tranFreeField16")
            .value(is(sameInstant(DEFAULT_TRAN_FREE_FIELD_16)))
            .jsonPath("$.tranFreeField17")
            .value(is(DEFAULT_TRAN_FREE_FIELD_17.booleanValue()))
            .jsonPath("$.tranFreeField18")
            .value(is(DEFAULT_TRAN_FREE_FIELD_18.toString()))
            .jsonPath("$.tranFreeField19")
            .value(is(DEFAULT_TRAN_FREE_FIELD_19.toString()))
            .jsonPath("$.tranFreeField20")
            .value(is(sameInstant(DEFAULT_TRAN_FREE_FIELD_20)))
            .jsonPath("$.tranFreeField21")
            .value(is(DEFAULT_TRAN_FREE_FIELD_21.toString()))
            .jsonPath("$.tranFreeField22")
            .value(is(DEFAULT_TRAN_FREE_FIELD_22.booleanValue()))
            .jsonPath("$.tranFreeField23")
            .value(is(DEFAULT_TRAN_FREE_FIELD_23.toString()))
            .jsonPath("$.tranFreeField24")
            .value(is(DEFAULT_TRAN_FREE_FIELD_24))
            .jsonPath("$.tranFreeField25")
            .value(is(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.tranFreeField26")
            .value(is(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.tranFreeField27")
            .value(is(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.tranFreeField28")
            .value(is(DEFAULT_TRAN_FREE_FIELD_28))
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
    void getNonExistingDTransaction() {
        // Get the dTransaction
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDTransaction() throws Exception {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        dTransactionSearchRepository.save(dTransaction).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());

        // Update the dTransaction
        DTransaction updatedDTransaction = dTransactionRepository.findById(dTransaction.getId()).block();
        updatedDTransaction
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .amount(UPDATED_AMOUNT)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .isRetried(UPDATED_IS_RETRIED)
            .lastRetryDate(UPDATED_LAST_RETRY_DATE)
            .firstRetryDate(UPDATED_FIRST_RETRY_DATE)
            .retryResposeCode(UPDATED_RETRY_RESPOSE_CODE)
            .retryResponseMessage(UPDATED_RETRY_RESPONSE_MESSAGE)
            .retryCount(UPDATED_RETRY_COUNT)
            .isRetriableFlg(UPDATED_IS_RETRIABLE_FLG)
            .doNotRetryDTransaction(UPDATED_DO_NOT_RETRY_D_TRANSACTION)
            .status(UPDATED_STATUS)
            .statusCode(UPDATED_STATUS_CODE)
            .statusDetails(UPDATED_STATUS_DETAILS)
            .retries(UPDATED_RETRIES)
            .timestamp(UPDATED_TIMESTAMP)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .isCrossCurrency(UPDATED_IS_CROSS_CURRENCY)
            .isCrossBank(UPDATED_IS_CROSS_BANK)
            .currency(UPDATED_CURRENCY)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .tranStatusDetails(UPDATED_TRAN_STATUS_DETAILS)
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
            .tranFreeField14ContentType(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDTransaction.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        DTransaction testDTransaction = dTransactionList.get(dTransactionList.size() - 1);
        assertThat(testDTransaction.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransaction.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransaction.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransaction.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransaction.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransaction.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransaction.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testDTransaction.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransaction.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransaction.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransaction.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransaction.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testDTransaction.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testDTransaction.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransaction.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransaction.getSettlementAmount()).isEqualByComparingTo(UPDATED_SETTLEMENT_AMOUNT);
        assertThat(testDTransaction.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransaction.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransaction.getIsRetried()).isEqualTo(UPDATED_IS_RETRIED);
        assertThat(testDTransaction.getLastRetryDate()).isEqualTo(UPDATED_LAST_RETRY_DATE);
        assertThat(testDTransaction.getFirstRetryDate()).isEqualTo(UPDATED_FIRST_RETRY_DATE);
        assertThat(testDTransaction.getRetryResposeCode()).isEqualTo(UPDATED_RETRY_RESPOSE_CODE);
        assertThat(testDTransaction.getRetryResponseMessage()).isEqualTo(UPDATED_RETRY_RESPONSE_MESSAGE);
        assertThat(testDTransaction.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
        assertThat(testDTransaction.getIsRetriableFlg()).isEqualTo(UPDATED_IS_RETRIABLE_FLG);
        assertThat(testDTransaction.getDoNotRetryDTransaction()).isEqualTo(UPDATED_DO_NOT_RETRY_D_TRANSACTION);
        assertThat(testDTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransaction.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testDTransaction.getStatusDetails()).isEqualTo(UPDATED_STATUS_DETAILS);
        assertThat(testDTransaction.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransaction.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransaction.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransaction.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransaction.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getIsCrossCurrency()).isEqualTo(UPDATED_IS_CROSS_CURRENCY);
        assertThat(testDTransaction.getIsCrossBank()).isEqualTo(UPDATED_IS_CROSS_BANK);
        assertThat(testDTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testDTransaction.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
        assertThat(testDTransaction.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
        assertThat(testDTransaction.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransaction.getCustomerNumber()).isEqualTo(UPDATED_CUSTOMER_NUMBER);
        assertThat(testDTransaction.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransaction.getTranStatusDetails()).isEqualTo(UPDATED_TRAN_STATUS_DETAILS);
        assertThat(testDTransaction.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransaction.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testDTransaction.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransaction.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransaction.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransaction.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransaction.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransaction.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransaction.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransaction.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransaction.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransaction.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransaction.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testDTransaction.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testDTransaction.getTranFreeField14ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testDTransaction.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testDTransaction.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testDTransaction.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransaction.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testDTransaction.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testDTransaction.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testDTransaction.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testDTransaction.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testDTransaction.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testDTransaction.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransaction.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransaction.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testDTransaction.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testDTransaction.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testDTransaction.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransaction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransaction.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransaction.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DTransaction> dTransactionSearchList = IterableUtils.toList(
                    dTransactionSearchRepository.findAll().collectList().block()
                );
                DTransaction testDTransactionSearch = dTransactionSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDTransactionSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDTransactionSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testDTransactionSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testDTransactionSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testDTransactionSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testDTransactionSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testDTransactionSearch.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
                assertThat(testDTransactionSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testDTransactionSearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testDTransactionSearch.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
                assertThat(testDTransactionSearch.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
                assertThat(testDTransactionSearch.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
                assertThat(testDTransactionSearch.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
                assertThat(testDTransactionSearch.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
                assertThat(testDTransactionSearch.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
                assertThat(testDTransactionSearch.getSettlementAmount()).isEqualByComparingTo(UPDATED_SETTLEMENT_AMOUNT);
                assertThat(testDTransactionSearch.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
                assertThat(testDTransactionSearch.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
                assertThat(testDTransactionSearch.getIsRetried()).isEqualTo(UPDATED_IS_RETRIED);
                assertThat(testDTransactionSearch.getLastRetryDate()).isEqualTo(UPDATED_LAST_RETRY_DATE);
                assertThat(testDTransactionSearch.getFirstRetryDate()).isEqualTo(UPDATED_FIRST_RETRY_DATE);
                assertThat(testDTransactionSearch.getRetryResposeCode()).isEqualTo(UPDATED_RETRY_RESPOSE_CODE);
                assertThat(testDTransactionSearch.getRetryResponseMessage()).isEqualTo(UPDATED_RETRY_RESPONSE_MESSAGE);
                assertThat(testDTransactionSearch.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
                assertThat(testDTransactionSearch.getIsRetriableFlg()).isEqualTo(UPDATED_IS_RETRIABLE_FLG);
                assertThat(testDTransactionSearch.getDoNotRetryDTransaction()).isEqualTo(UPDATED_DO_NOT_RETRY_D_TRANSACTION);
                assertThat(testDTransactionSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testDTransactionSearch.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
                assertThat(testDTransactionSearch.getStatusDetails()).isEqualTo(UPDATED_STATUS_DETAILS);
                assertThat(testDTransactionSearch.getRetries()).isEqualTo(UPDATED_RETRIES);
                assertThat(testDTransactionSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDTransactionSearch.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
                assertThat(testDTransactionSearch.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
                assertThat(testDTransactionSearch.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
                assertThat(testDTransactionSearch.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
                assertThat(testDTransactionSearch.getIsCrossCurrency()).isEqualTo(UPDATED_IS_CROSS_CURRENCY);
                assertThat(testDTransactionSearch.getIsCrossBank()).isEqualTo(UPDATED_IS_CROSS_BANK);
                assertThat(testDTransactionSearch.getCurrency()).isEqualTo(UPDATED_CURRENCY);
                assertThat(testDTransactionSearch.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
                assertThat(testDTransactionSearch.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
                assertThat(testDTransactionSearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testDTransactionSearch.getCustomerNumber()).isEqualTo(UPDATED_CUSTOMER_NUMBER);
                assertThat(testDTransactionSearch.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
                assertThat(testDTransactionSearch.getTranStatusDetails()).isEqualTo(UPDATED_TRAN_STATUS_DETAILS);
                assertThat(testDTransactionSearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testDTransactionSearch.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
                assertThat(testDTransactionSearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testDTransactionSearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testDTransactionSearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testDTransactionSearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testDTransactionSearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testDTransactionSearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testDTransactionSearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testDTransactionSearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testDTransactionSearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testDTransactionSearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testDTransactionSearch.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
                assertThat(testDTransactionSearch.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
                assertThat(testDTransactionSearch.getTranFreeField14ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE);
                assertThat(testDTransactionSearch.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
                assertThat(testDTransactionSearch.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
                assertThat(testDTransactionSearch.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
                assertThat(testDTransactionSearch.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
                assertThat(testDTransactionSearch.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
                assertThat(testDTransactionSearch.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
                assertThat(testDTransactionSearch.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
                assertThat(testDTransactionSearch.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
                assertThat(testDTransactionSearch.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
                assertThat(testDTransactionSearch.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
                assertThat(testDTransactionSearch.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
                assertThat(testDTransactionSearch.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
                assertThat(testDTransactionSearch.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
                assertThat(testDTransactionSearch.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
                assertThat(testDTransactionSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testDTransactionSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testDTransactionSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testDTransactionSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dTransaction.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDTransactionWithPatch() throws Exception {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();

        // Update the dTransaction using partial update
        DTransaction partialUpdatedDTransaction = new DTransaction();
        partialUpdatedDTransaction.setId(dTransaction.getId());

        partialUpdatedDTransaction
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .token(UPDATED_TOKEN)
            .productCode(UPDATED_PRODUCT_CODE)
            .amount(UPDATED_AMOUNT)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .isRetried(UPDATED_IS_RETRIED)
            .lastRetryDate(UPDATED_LAST_RETRY_DATE)
            .retryCount(UPDATED_RETRY_COUNT)
            .isRetriableFlg(UPDATED_IS_RETRIABLE_FLG)
            .doNotRetryDTransaction(UPDATED_DO_NOT_RETRY_D_TRANSACTION)
            .statusCode(UPDATED_STATUS_CODE)
            .postedDate(UPDATED_POSTED_DATE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .isCrossCurrency(UPDATED_IS_CROSS_CURRENCY)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .createdAt(UPDATED_CREATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransaction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        DTransaction testDTransaction = dTransactionList.get(dTransactionList.size() - 1);
        assertThat(testDTransaction.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransaction.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransaction.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransaction.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransaction.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransaction.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransaction.getExternalTranid()).isEqualTo(DEFAULT_EXTERNAL_TRANID);
        assertThat(testDTransaction.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransaction.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransaction.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransaction.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransaction.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testDTransaction.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testDTransaction.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransaction.getCreditAmount()).isEqualByComparingTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransaction.getSettlementAmount()).isEqualByComparingTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testDTransaction.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransaction.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransaction.getIsRetried()).isEqualTo(UPDATED_IS_RETRIED);
        assertThat(testDTransaction.getLastRetryDate()).isEqualTo(UPDATED_LAST_RETRY_DATE);
        assertThat(testDTransaction.getFirstRetryDate()).isEqualTo(DEFAULT_FIRST_RETRY_DATE);
        assertThat(testDTransaction.getRetryResposeCode()).isEqualTo(DEFAULT_RETRY_RESPOSE_CODE);
        assertThat(testDTransaction.getRetryResponseMessage()).isEqualTo(DEFAULT_RETRY_RESPONSE_MESSAGE);
        assertThat(testDTransaction.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
        assertThat(testDTransaction.getIsRetriableFlg()).isEqualTo(UPDATED_IS_RETRIABLE_FLG);
        assertThat(testDTransaction.getDoNotRetryDTransaction()).isEqualTo(UPDATED_DO_NOT_RETRY_D_TRANSACTION);
        assertThat(testDTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransaction.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testDTransaction.getStatusDetails()).isEqualTo(DEFAULT_STATUS_DETAILS);
        assertThat(testDTransaction.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransaction.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransaction.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransaction.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransaction.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getIsCrossCurrency()).isEqualTo(UPDATED_IS_CROSS_CURRENCY);
        assertThat(testDTransaction.getIsCrossBank()).isEqualTo(DEFAULT_IS_CROSS_BANK);
        assertThat(testDTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testDTransaction.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
        assertThat(testDTransaction.getDebitAccount()).isEqualTo(DEFAULT_DEBIT_ACCOUNT);
        assertThat(testDTransaction.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransaction.getCustomerNumber()).isEqualTo(DEFAULT_CUSTOMER_NUMBER);
        assertThat(testDTransaction.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransaction.getTranStatusDetails()).isEqualTo(DEFAULT_TRAN_STATUS_DETAILS);
        assertThat(testDTransaction.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransaction.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testDTransaction.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransaction.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransaction.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransaction.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransaction.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransaction.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransaction.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransaction.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransaction.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransaction.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransaction.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransaction.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testDTransaction.getTranFreeField14ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testDTransaction.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testDTransaction.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransaction.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransaction.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransaction.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testDTransaction.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransaction.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransaction.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testDTransaction.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testDTransaction.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransaction.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransaction.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransaction.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testDTransaction.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testDTransaction.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDTransaction.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testDTransaction.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateDTransactionWithPatch() throws Exception {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();

        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();

        // Update the dTransaction using partial update
        DTransaction partialUpdatedDTransaction = new DTransaction();
        partialUpdatedDTransaction.setId(dTransaction.getId());

        partialUpdatedDTransaction
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .externalTranid(UPDATED_EXTERNAL_TRANID)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .amount(UPDATED_AMOUNT)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .isRetried(UPDATED_IS_RETRIED)
            .lastRetryDate(UPDATED_LAST_RETRY_DATE)
            .firstRetryDate(UPDATED_FIRST_RETRY_DATE)
            .retryResposeCode(UPDATED_RETRY_RESPOSE_CODE)
            .retryResponseMessage(UPDATED_RETRY_RESPONSE_MESSAGE)
            .retryCount(UPDATED_RETRY_COUNT)
            .isRetriableFlg(UPDATED_IS_RETRIABLE_FLG)
            .doNotRetryDTransaction(UPDATED_DO_NOT_RETRY_D_TRANSACTION)
            .status(UPDATED_STATUS)
            .statusCode(UPDATED_STATUS_CODE)
            .statusDetails(UPDATED_STATUS_DETAILS)
            .retries(UPDATED_RETRIES)
            .timestamp(UPDATED_TIMESTAMP)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .isCrossCurrency(UPDATED_IS_CROSS_CURRENCY)
            .isCrossBank(UPDATED_IS_CROSS_BANK)
            .currency(UPDATED_CURRENCY)
            .creditAccount(UPDATED_CREDIT_ACCOUNT)
            .debitAccount(UPDATED_DEBIT_ACCOUNT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .customerNumber(UPDATED_CUSTOMER_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .tranStatusDetails(UPDATED_TRAN_STATUS_DETAILS)
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
            .tranFreeField14ContentType(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField26(UPDATED_TRAN_FREE_FIELD_26)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransaction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransaction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        DTransaction testDTransaction = dTransactionList.get(dTransactionList.size() - 1);
        assertThat(testDTransaction.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransaction.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransaction.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransaction.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransaction.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransaction.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransaction.getExternalTranid()).isEqualTo(UPDATED_EXTERNAL_TRANID);
        assertThat(testDTransaction.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransaction.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransaction.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransaction.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransaction.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testDTransaction.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testDTransaction.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransaction.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransaction.getSettlementAmount()).isEqualByComparingTo(UPDATED_SETTLEMENT_AMOUNT);
        assertThat(testDTransaction.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransaction.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testDTransaction.getIsRetried()).isEqualTo(UPDATED_IS_RETRIED);
        assertThat(testDTransaction.getLastRetryDate()).isEqualTo(UPDATED_LAST_RETRY_DATE);
        assertThat(testDTransaction.getFirstRetryDate()).isEqualTo(UPDATED_FIRST_RETRY_DATE);
        assertThat(testDTransaction.getRetryResposeCode()).isEqualTo(UPDATED_RETRY_RESPOSE_CODE);
        assertThat(testDTransaction.getRetryResponseMessage()).isEqualTo(UPDATED_RETRY_RESPONSE_MESSAGE);
        assertThat(testDTransaction.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
        assertThat(testDTransaction.getIsRetriableFlg()).isEqualTo(UPDATED_IS_RETRIABLE_FLG);
        assertThat(testDTransaction.getDoNotRetryDTransaction()).isEqualTo(UPDATED_DO_NOT_RETRY_D_TRANSACTION);
        assertThat(testDTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransaction.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testDTransaction.getStatusDetails()).isEqualTo(UPDATED_STATUS_DETAILS);
        assertThat(testDTransaction.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransaction.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransaction.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransaction.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransaction.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testDTransaction.getIsCrossCurrency()).isEqualTo(UPDATED_IS_CROSS_CURRENCY);
        assertThat(testDTransaction.getIsCrossBank()).isEqualTo(UPDATED_IS_CROSS_BANK);
        assertThat(testDTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testDTransaction.getCreditAccount()).isEqualTo(UPDATED_CREDIT_ACCOUNT);
        assertThat(testDTransaction.getDebitAccount()).isEqualTo(UPDATED_DEBIT_ACCOUNT);
        assertThat(testDTransaction.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransaction.getCustomerNumber()).isEqualTo(UPDATED_CUSTOMER_NUMBER);
        assertThat(testDTransaction.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransaction.getTranStatusDetails()).isEqualTo(UPDATED_TRAN_STATUS_DETAILS);
        assertThat(testDTransaction.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransaction.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testDTransaction.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransaction.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransaction.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransaction.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransaction.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransaction.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransaction.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransaction.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransaction.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransaction.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransaction.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testDTransaction.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testDTransaction.getTranFreeField14ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14_CONTENT_TYPE);
        assertThat(testDTransaction.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testDTransaction.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testDTransaction.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransaction.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testDTransaction.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testDTransaction.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testDTransaction.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testDTransaction.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testDTransaction.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testDTransaction.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransaction.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransaction.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testDTransaction.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testDTransaction.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testDTransaction.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testDTransaction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDTransaction.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testDTransaction.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dTransaction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDTransaction() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        dTransaction.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransaction))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransaction in the database
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDTransaction() {
        // Initialize the database
        dTransactionRepository.save(dTransaction).block();
        dTransactionRepository.save(dTransaction).block();
        dTransactionSearchRepository.save(dTransaction).block();

        int databaseSizeBeforeDelete = dTransactionRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the dTransaction
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dTransaction.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DTransaction> dTransactionList = dTransactionRepository.findAll().collectList().block();
        assertThat(dTransactionList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDTransaction() {
        // Initialize the database
        dTransaction = dTransactionRepository.save(dTransaction).block();
        dTransactionSearchRepository.save(dTransaction).block();

        // Search the dTransaction
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + dTransaction.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dTransaction.getId().intValue()))
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
            .jsonPath("$.[*].externalTranid")
            .value(hasItem(DEFAULT_EXTERNAL_TRANID))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].accountNumber")
            .value(hasItem(DEFAULT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].amount")
            .value(hasItem(sameNumber(DEFAULT_AMOUNT)))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.[*].settlementAmount")
            .value(hasItem(sameNumber(DEFAULT_SETTLEMENT_AMOUNT)))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(sameInstant(DEFAULT_TRANSACTION_DATE)))
            .jsonPath("$.[*].isRetried")
            .value(hasItem(DEFAULT_IS_RETRIED.booleanValue()))
            .jsonPath("$.[*].lastRetryDate")
            .value(hasItem(sameInstant(DEFAULT_LAST_RETRY_DATE)))
            .jsonPath("$.[*].firstRetryDate")
            .value(hasItem(sameInstant(DEFAULT_FIRST_RETRY_DATE)))
            .jsonPath("$.[*].retryResposeCode")
            .value(hasItem(DEFAULT_RETRY_RESPOSE_CODE.toString()))
            .jsonPath("$.[*].retryResponseMessage")
            .value(hasItem(DEFAULT_RETRY_RESPONSE_MESSAGE.toString()))
            .jsonPath("$.[*].retryCount")
            .value(hasItem(DEFAULT_RETRY_COUNT))
            .jsonPath("$.[*].isRetriableFlg")
            .value(hasItem(DEFAULT_IS_RETRIABLE_FLG.booleanValue()))
            .jsonPath("$.[*].doNotRetryDTransaction")
            .value(hasItem(DEFAULT_DO_NOT_RETRY_D_TRANSACTION.booleanValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].statusCode")
            .value(hasItem(DEFAULT_STATUS_CODE))
            .jsonPath("$.[*].statusDetails")
            .value(hasItem(DEFAULT_STATUS_DETAILS))
            .jsonPath("$.[*].retries")
            .value(hasItem(DEFAULT_RETRIES))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].postedBy")
            .value(hasItem(DEFAULT_POSTED_BY))
            .jsonPath("$.[*].postedDate")
            .value(hasItem(DEFAULT_POSTED_DATE))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].isCrossCurrency")
            .value(hasItem(DEFAULT_IS_CROSS_CURRENCY.booleanValue()))
            .jsonPath("$.[*].isCrossBank")
            .value(hasItem(DEFAULT_IS_CROSS_BANK.booleanValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].creditAccount")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT))
            .jsonPath("$.[*].debitAccount")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].customerNumber")
            .value(hasItem(DEFAULT_CUSTOMER_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].tranStatusDetails")
            .value(hasItem(DEFAULT_TRAN_STATUS_DETAILS))
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
            .jsonPath("$.[*].tranFreeField14ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_14)))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15.toString()))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_16)))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17.booleanValue()))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_18.toString()))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19.toString()))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_20)))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21.toString()))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.booleanValue()))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23.toString()))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_24))
            .jsonPath("$.[*].tranFreeField25")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_25))
            .jsonPath("$.[*].tranFreeField26")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_26))
            .jsonPath("$.[*].tranFreeField27")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_27))
            .jsonPath("$.[*].tranFreeField28")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_28))
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
