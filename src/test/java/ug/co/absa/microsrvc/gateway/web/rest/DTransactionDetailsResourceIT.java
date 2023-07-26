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
import ug.co.absa.microsrvc.gateway.domain.DTransactionDetails;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.DTransactionDetailsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionDetailsSearchRepository;

/**
 * Integration tests for the {@link DTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DTransactionDetailsResourceIT {

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

    private static final String DEFAULT_DEBIT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_CURRENCY = "BBBBBBBBBB";

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

    private static final String DEFAULT_NARRATION_4 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_4 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_5 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_5 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_6 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_6 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_7 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_7 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_8 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_8 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_9 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_9 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_10 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_10 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_11 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_11 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_12 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_12 = "BBBBBBBBBB";

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

    private static final byte[] DEFAULT_TRAN_FREE_FIELD_12 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TRAN_FREE_FIELD_12 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TRAN_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TRAN_FREE_FIELD_15 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TRAN_FREE_FIELD_15 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TRAN_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_18 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_18 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_19 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_19 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_20 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_20 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_23 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_23 = "BBBBBBBBBB";

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

    private static final String DEFAULT_INTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/d-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/d-transaction-details";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DTransactionDetailsRepository dTransactionDetailsRepository;

    @Autowired
    private DTransactionDetailsSearchRepository dTransactionDetailsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DTransactionDetails dTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionDetails createEntity(EntityManager em) {
        DTransactionDetails dTransactionDetails = new DTransactionDetails()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .paymentItemCode(DEFAULT_PAYMENT_ITEM_CODE)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
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
            .narration4(DEFAULT_NARRATION_4)
            .narration5(DEFAULT_NARRATION_5)
            .narration6(DEFAULT_NARRATION_6)
            .narration7(DEFAULT_NARRATION_7)
            .narration8(DEFAULT_NARRATION_8)
            .narration9(DEFAULT_NARRATION_9)
            .narration10(DEFAULT_NARRATION_10)
            .narration11(DEFAULT_NARRATION_11)
            .narration12(DEFAULT_NARRATION_12)
            .modeOfPayment(DEFAULT_MODE_OF_PAYMENT)
            .retries(DEFAULT_RETRIES)
            .posted(DEFAULT_POSTED)
            .apiId(DEFAULT_API_ID)
            .apiVersion(DEFAULT_API_VERSION)
            .postingApi(DEFAULT_POSTING_API)
            .isPosted(DEFAULT_IS_POSTED)
            .postedBy(DEFAULT_POSTED_BY)
            .postedDate(DEFAULT_POSTED_DATE)
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
            .tranFreeField12ContentType(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE)
            .tranFreeField13(DEFAULT_TRAN_FREE_FIELD_13)
            .tranFreeField14(DEFAULT_TRAN_FREE_FIELD_14)
            .tranFreeField15(DEFAULT_TRAN_FREE_FIELD_15)
            .tranFreeField15ContentType(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE)
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
            .internalErrorCode(DEFAULT_INTERNAL_ERROR_CODE)
            .externalErrorCode(DEFAULT_EXTERNAL_ERROR_CODE);
        return dTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DTransactionDetails createUpdatedEntity(EntityManager em) {
        DTransactionDetails dTransactionDetails = new DTransactionDetails()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
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
            .narration4(UPDATED_NARRATION_4)
            .narration5(UPDATED_NARRATION_5)
            .narration6(UPDATED_NARRATION_6)
            .narration7(UPDATED_NARRATION_7)
            .narration8(UPDATED_NARRATION_8)
            .narration9(UPDATED_NARRATION_9)
            .narration10(UPDATED_NARRATION_10)
            .narration11(UPDATED_NARRATION_11)
            .narration12(UPDATED_NARRATION_12)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
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
            .tranFreeField12ContentType(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField15ContentType(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE)
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
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE);
        return dTransactionDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DTransactionDetails.class).block();
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
        dTransactionDetailsSearchRepository.deleteAll().block();
        assertThat(dTransactionDetailsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        dTransactionDetails = createEntity(em);
    }

    @Test
    void createDTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // Create the DTransactionDetails
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        DTransactionDetails testDTransactionDetails = dTransactionDetailsList.get(dTransactionDetailsList.size() - 1);
        assertThat(testDTransactionDetails.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionDetails.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionDetails.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionDetails.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionDetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDTransactionDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransactionDetails.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransactionDetails.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionDetails.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getDebitAmount()).isEqualTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransactionDetails.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionDetails.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionDetails.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testDTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testDTransactionDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionDetails.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransactionDetails.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testDTransactionDetails.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionDetails.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDTransactionDetails.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testDTransactionDetails.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionDetails.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testDTransactionDetails.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testDTransactionDetails.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testDTransactionDetails.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionDetails.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransactionDetails.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testDTransactionDetails.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testDTransactionDetails.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testDTransactionDetails.getNarration4()).isEqualTo(DEFAULT_NARRATION_4);
        assertThat(testDTransactionDetails.getNarration5()).isEqualTo(DEFAULT_NARRATION_5);
        assertThat(testDTransactionDetails.getNarration6()).isEqualTo(DEFAULT_NARRATION_6);
        assertThat(testDTransactionDetails.getNarration7()).isEqualTo(DEFAULT_NARRATION_7);
        assertThat(testDTransactionDetails.getNarration8()).isEqualTo(DEFAULT_NARRATION_8);
        assertThat(testDTransactionDetails.getNarration9()).isEqualTo(DEFAULT_NARRATION_9);
        assertThat(testDTransactionDetails.getNarration10()).isEqualTo(DEFAULT_NARRATION_10);
        assertThat(testDTransactionDetails.getNarration11()).isEqualTo(DEFAULT_NARRATION_11);
        assertThat(testDTransactionDetails.getNarration12()).isEqualTo(DEFAULT_NARRATION_12);
        assertThat(testDTransactionDetails.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionDetails.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransactionDetails.getPosted()).isEqualTo(DEFAULT_POSTED);
        assertThat(testDTransactionDetails.getApiId()).isEqualTo(DEFAULT_API_ID);
        assertThat(testDTransactionDetails.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testDTransactionDetails.getPostingApi()).isEqualTo(DEFAULT_POSTING_API);
        assertThat(testDTransactionDetails.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionDetails.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionDetails.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransactionDetails.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionDetails.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionDetails.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionDetails.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionDetails.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionDetails.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionDetails.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionDetails.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionDetails.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionDetails.getTranFreeField12ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransactionDetails.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testDTransactionDetails.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testDTransactionDetails.getTranFreeField15ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransactionDetails.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testDTransactionDetails.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransactionDetails.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testDTransactionDetails.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransactionDetails.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransactionDetails.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testDTransactionDetails.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testDTransactionDetails.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testDTransactionDetails.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testDTransactionDetails.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransactionDetails.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testDTransactionDetails.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testDTransactionDetails.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionDetails.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
    }

    @Test
    void createDTransactionDetailsWithExistingId() throws Exception {
        // Create the DTransactionDetails with an existing ID
        dTransactionDetails.setId(1L);

        int databaseSizeBeforeCreate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkBillerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setBillerId(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentItemCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setPaymentItemCode(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setProductCode(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPaymentChannelCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setPaymentChannelCode(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setDebitAccountNumber(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreditAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setCreditAccountNumber(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setDebitAmount(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setDebitDate(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setCreditDate(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setDebitCurrency(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setPhoneNumber(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        // set the field null
        dTransactionDetails.setEmail(null);

        // Create the DTransactionDetails, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllDTransactionDetailsAsStream() {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        List<DTransactionDetails> dTransactionDetailsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(DTransactionDetails.class)
            .getResponseBody()
            .filter(dTransactionDetails::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(dTransactionDetailsList).isNotNull();
        assertThat(dTransactionDetailsList).hasSize(1);
        DTransactionDetails testDTransactionDetails = dTransactionDetailsList.get(0);
        assertThat(testDTransactionDetails.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testDTransactionDetails.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testDTransactionDetails.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionDetails.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionDetails.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testDTransactionDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransactionDetails.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testDTransactionDetails.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionDetails.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getDebitAmount()).isEqualTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransactionDetails.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionDetails.getTransactionAmount()).isEqualTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testDTransactionDetails.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testDTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testDTransactionDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionDetails.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransactionDetails.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testDTransactionDetails.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testDTransactionDetails.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDTransactionDetails.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testDTransactionDetails.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionDetails.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testDTransactionDetails.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testDTransactionDetails.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testDTransactionDetails.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionDetails.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testDTransactionDetails.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testDTransactionDetails.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testDTransactionDetails.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testDTransactionDetails.getNarration4()).isEqualTo(DEFAULT_NARRATION_4);
        assertThat(testDTransactionDetails.getNarration5()).isEqualTo(DEFAULT_NARRATION_5);
        assertThat(testDTransactionDetails.getNarration6()).isEqualTo(DEFAULT_NARRATION_6);
        assertThat(testDTransactionDetails.getNarration7()).isEqualTo(DEFAULT_NARRATION_7);
        assertThat(testDTransactionDetails.getNarration8()).isEqualTo(DEFAULT_NARRATION_8);
        assertThat(testDTransactionDetails.getNarration9()).isEqualTo(DEFAULT_NARRATION_9);
        assertThat(testDTransactionDetails.getNarration10()).isEqualTo(DEFAULT_NARRATION_10);
        assertThat(testDTransactionDetails.getNarration11()).isEqualTo(DEFAULT_NARRATION_11);
        assertThat(testDTransactionDetails.getNarration12()).isEqualTo(DEFAULT_NARRATION_12);
        assertThat(testDTransactionDetails.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionDetails.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransactionDetails.getPosted()).isEqualTo(DEFAULT_POSTED);
        assertThat(testDTransactionDetails.getApiId()).isEqualTo(DEFAULT_API_ID);
        assertThat(testDTransactionDetails.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testDTransactionDetails.getPostingApi()).isEqualTo(DEFAULT_POSTING_API);
        assertThat(testDTransactionDetails.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionDetails.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionDetails.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransactionDetails.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionDetails.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionDetails.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionDetails.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionDetails.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionDetails.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionDetails.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionDetails.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionDetails.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionDetails.getTranFreeField12ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransactionDetails.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testDTransactionDetails.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testDTransactionDetails.getTranFreeField15ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransactionDetails.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testDTransactionDetails.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransactionDetails.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testDTransactionDetails.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransactionDetails.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransactionDetails.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testDTransactionDetails.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testDTransactionDetails.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testDTransactionDetails.getTranFreeField25()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_25);
        assertThat(testDTransactionDetails.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransactionDetails.getTranFreeField27()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_27);
        assertThat(testDTransactionDetails.getTranFreeField28()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_28);
        assertThat(testDTransactionDetails.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionDetails.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
    }

    @Test
    void getAllDTransactionDetails() {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        // Get all the dTransactionDetailsList
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
            .value(hasItem(dTransactionDetails.getId().intValue()))
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
            .value(hasItem(DEFAULT_DEBIT_CURRENCY))
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
            .jsonPath("$.[*].narration4")
            .value(hasItem(DEFAULT_NARRATION_4))
            .jsonPath("$.[*].narration5")
            .value(hasItem(DEFAULT_NARRATION_5))
            .jsonPath("$.[*].narration6")
            .value(hasItem(DEFAULT_NARRATION_6))
            .jsonPath("$.[*].narration7")
            .value(hasItem(DEFAULT_NARRATION_7))
            .jsonPath("$.[*].narration8")
            .value(hasItem(DEFAULT_NARRATION_8))
            .jsonPath("$.[*].narration9")
            .value(hasItem(DEFAULT_NARRATION_9))
            .jsonPath("$.[*].narration10")
            .value(hasItem(DEFAULT_NARRATION_10))
            .jsonPath("$.[*].narration11")
            .value(hasItem(DEFAULT_NARRATION_11))
            .jsonPath("$.[*].narration12")
            .value(hasItem(DEFAULT_NARRATION_12))
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
            .jsonPath("$.[*].tranFreeField12ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_12)))
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13.toString()))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14.toString()))
            .jsonPath("$.[*].tranFreeField15ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_15)))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_18))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23))
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
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE));
    }

    @Test
    void getDTransactionDetails() {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        // Get the dTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dTransactionDetails.getId().intValue()))
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
            .value(is(DEFAULT_DEBIT_CURRENCY))
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
            .jsonPath("$.narration4")
            .value(is(DEFAULT_NARRATION_4))
            .jsonPath("$.narration5")
            .value(is(DEFAULT_NARRATION_5))
            .jsonPath("$.narration6")
            .value(is(DEFAULT_NARRATION_6))
            .jsonPath("$.narration7")
            .value(is(DEFAULT_NARRATION_7))
            .jsonPath("$.narration8")
            .value(is(DEFAULT_NARRATION_8))
            .jsonPath("$.narration9")
            .value(is(DEFAULT_NARRATION_9))
            .jsonPath("$.narration10")
            .value(is(DEFAULT_NARRATION_10))
            .jsonPath("$.narration11")
            .value(is(DEFAULT_NARRATION_11))
            .jsonPath("$.narration12")
            .value(is(DEFAULT_NARRATION_12))
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
            .jsonPath("$.tranFreeField12ContentType")
            .value(is(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE))
            .jsonPath("$.tranFreeField12")
            .value(is(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_12)))
            .jsonPath("$.tranFreeField13")
            .value(is(DEFAULT_TRAN_FREE_FIELD_13.toString()))
            .jsonPath("$.tranFreeField14")
            .value(is(DEFAULT_TRAN_FREE_FIELD_14.toString()))
            .jsonPath("$.tranFreeField15ContentType")
            .value(is(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.tranFreeField15")
            .value(is(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_15)))
            .jsonPath("$.tranFreeField16")
            .value(is(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.tranFreeField17")
            .value(is(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.tranFreeField18")
            .value(is(DEFAULT_TRAN_FREE_FIELD_18))
            .jsonPath("$.tranFreeField19")
            .value(is(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.tranFreeField20")
            .value(is(DEFAULT_TRAN_FREE_FIELD_20))
            .jsonPath("$.tranFreeField21")
            .value(is(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.tranFreeField22")
            .value(is(DEFAULT_TRAN_FREE_FIELD_22))
            .jsonPath("$.tranFreeField23")
            .value(is(DEFAULT_TRAN_FREE_FIELD_23))
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
            .jsonPath("$.internalErrorCode")
            .value(is(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.externalErrorCode")
            .value(is(DEFAULT_EXTERNAL_ERROR_CODE));
    }

    @Test
    void getNonExistingDTransactionDetails() {
        // Get the dTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDTransactionDetails() throws Exception {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        dTransactionDetailsSearchRepository.save(dTransactionDetails).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());

        // Update the dTransactionDetails
        DTransactionDetails updatedDTransactionDetails = dTransactionDetailsRepository.findById(dTransactionDetails.getId()).block();
        updatedDTransactionDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
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
            .narration4(UPDATED_NARRATION_4)
            .narration5(UPDATED_NARRATION_5)
            .narration6(UPDATED_NARRATION_6)
            .narration7(UPDATED_NARRATION_7)
            .narration8(UPDATED_NARRATION_8)
            .narration9(UPDATED_NARRATION_9)
            .narration10(UPDATED_NARRATION_10)
            .narration11(UPDATED_NARRATION_11)
            .narration12(UPDATED_NARRATION_12)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
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
            .tranFreeField12ContentType(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField15ContentType(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE)
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
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDTransactionDetails.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedDTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DTransactionDetails testDTransactionDetails = dTransactionDetailsList.get(dTransactionDetailsList.size() - 1);
        assertThat(testDTransactionDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionDetails.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionDetails.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionDetails.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionDetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransactionDetails.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransactionDetails.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransactionDetails.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransactionDetails.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionDetails.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testDTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testDTransactionDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransactionDetails.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransactionDetails.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testDTransactionDetails.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionDetails.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionDetails.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testDTransactionDetails.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testDTransactionDetails.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testDTransactionDetails.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testDTransactionDetails.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionDetails.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionDetails.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransactionDetails.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testDTransactionDetails.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testDTransactionDetails.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionDetails.getNarration4()).isEqualTo(UPDATED_NARRATION_4);
        assertThat(testDTransactionDetails.getNarration5()).isEqualTo(UPDATED_NARRATION_5);
        assertThat(testDTransactionDetails.getNarration6()).isEqualTo(UPDATED_NARRATION_6);
        assertThat(testDTransactionDetails.getNarration7()).isEqualTo(UPDATED_NARRATION_7);
        assertThat(testDTransactionDetails.getNarration8()).isEqualTo(UPDATED_NARRATION_8);
        assertThat(testDTransactionDetails.getNarration9()).isEqualTo(UPDATED_NARRATION_9);
        assertThat(testDTransactionDetails.getNarration10()).isEqualTo(UPDATED_NARRATION_10);
        assertThat(testDTransactionDetails.getNarration11()).isEqualTo(UPDATED_NARRATION_11);
        assertThat(testDTransactionDetails.getNarration12()).isEqualTo(UPDATED_NARRATION_12);
        assertThat(testDTransactionDetails.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
        assertThat(testDTransactionDetails.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransactionDetails.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionDetails.getApiId()).isEqualTo(UPDATED_API_ID);
        assertThat(testDTransactionDetails.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testDTransactionDetails.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionDetails.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
        assertThat(testDTransactionDetails.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransactionDetails.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransactionDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionDetails.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionDetails.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionDetails.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionDetails.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionDetails.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionDetails.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionDetails.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionDetails.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionDetails.getTranFreeField12ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testDTransactionDetails.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testDTransactionDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testDTransactionDetails.getTranFreeField15ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testDTransactionDetails.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransactionDetails.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testDTransactionDetails.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testDTransactionDetails.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testDTransactionDetails.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testDTransactionDetails.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testDTransactionDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testDTransactionDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransactionDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransactionDetails.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testDTransactionDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testDTransactionDetails.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testDTransactionDetails.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionDetails.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<DTransactionDetails> dTransactionDetailsSearchList = IterableUtils.toList(
                    dTransactionDetailsSearchRepository.findAll().collectList().block()
                );
                DTransactionDetails testDTransactionDetailsSearch = dTransactionDetailsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testDTransactionDetailsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testDTransactionDetailsSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testDTransactionDetailsSearch.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
                assertThat(testDTransactionDetailsSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testDTransactionDetailsSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testDTransactionDetailsSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testDTransactionDetailsSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testDTransactionDetailsSearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testDTransactionDetailsSearch.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
                assertThat(testDTransactionDetailsSearch.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
                assertThat(testDTransactionDetailsSearch.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
                assertThat(testDTransactionDetailsSearch.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
                assertThat(testDTransactionDetailsSearch.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
                assertThat(testDTransactionDetailsSearch.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
                assertThat(testDTransactionDetailsSearch.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
                assertThat(testDTransactionDetailsSearch.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
                assertThat(testDTransactionDetailsSearch.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
                assertThat(testDTransactionDetailsSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testDTransactionDetailsSearch.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
                assertThat(testDTransactionDetailsSearch.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
                assertThat(testDTransactionDetailsSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testDTransactionDetailsSearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testDTransactionDetailsSearch.getEmail()).isEqualTo(UPDATED_EMAIL);
                assertThat(testDTransactionDetailsSearch.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
                assertThat(testDTransactionDetailsSearch.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
                assertThat(testDTransactionDetailsSearch.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
                assertThat(testDTransactionDetailsSearch.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
                assertThat(testDTransactionDetailsSearch.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
                assertThat(testDTransactionDetailsSearch.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
                assertThat(testDTransactionDetailsSearch.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
                assertThat(testDTransactionDetailsSearch.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
                assertThat(testDTransactionDetailsSearch.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
                assertThat(testDTransactionDetailsSearch.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
                assertThat(testDTransactionDetailsSearch.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
                assertThat(testDTransactionDetailsSearch.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
                assertThat(testDTransactionDetailsSearch.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
                assertThat(testDTransactionDetailsSearch.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
                assertThat(testDTransactionDetailsSearch.getNarration4()).isEqualTo(UPDATED_NARRATION_4);
                assertThat(testDTransactionDetailsSearch.getNarration5()).isEqualTo(UPDATED_NARRATION_5);
                assertThat(testDTransactionDetailsSearch.getNarration6()).isEqualTo(UPDATED_NARRATION_6);
                assertThat(testDTransactionDetailsSearch.getNarration7()).isEqualTo(UPDATED_NARRATION_7);
                assertThat(testDTransactionDetailsSearch.getNarration8()).isEqualTo(UPDATED_NARRATION_8);
                assertThat(testDTransactionDetailsSearch.getNarration9()).isEqualTo(UPDATED_NARRATION_9);
                assertThat(testDTransactionDetailsSearch.getNarration10()).isEqualTo(UPDATED_NARRATION_10);
                assertThat(testDTransactionDetailsSearch.getNarration11()).isEqualTo(UPDATED_NARRATION_11);
                assertThat(testDTransactionDetailsSearch.getNarration12()).isEqualTo(UPDATED_NARRATION_12);
                assertThat(testDTransactionDetailsSearch.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
                assertThat(testDTransactionDetailsSearch.getRetries()).isEqualTo(UPDATED_RETRIES);
                assertThat(testDTransactionDetailsSearch.getPosted()).isEqualTo(UPDATED_POSTED);
                assertThat(testDTransactionDetailsSearch.getApiId()).isEqualTo(UPDATED_API_ID);
                assertThat(testDTransactionDetailsSearch.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
                assertThat(testDTransactionDetailsSearch.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
                assertThat(testDTransactionDetailsSearch.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
                assertThat(testDTransactionDetailsSearch.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
                assertThat(testDTransactionDetailsSearch.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
                assertThat(testDTransactionDetailsSearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testDTransactionDetailsSearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testDTransactionDetailsSearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testDTransactionDetailsSearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testDTransactionDetailsSearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testDTransactionDetailsSearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testDTransactionDetailsSearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testDTransactionDetailsSearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testDTransactionDetailsSearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testDTransactionDetailsSearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testDTransactionDetailsSearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testDTransactionDetailsSearch.getTranFreeField12ContentType())
                    .isEqualTo(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE);
                assertThat(testDTransactionDetailsSearch.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
                assertThat(testDTransactionDetailsSearch.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
                assertThat(testDTransactionDetailsSearch.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
                assertThat(testDTransactionDetailsSearch.getTranFreeField15ContentType())
                    .isEqualTo(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE);
                assertThat(testDTransactionDetailsSearch.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
                assertThat(testDTransactionDetailsSearch.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
                assertThat(testDTransactionDetailsSearch.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
                assertThat(testDTransactionDetailsSearch.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
                assertThat(testDTransactionDetailsSearch.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
                assertThat(testDTransactionDetailsSearch.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
                assertThat(testDTransactionDetailsSearch.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
                assertThat(testDTransactionDetailsSearch.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
                assertThat(testDTransactionDetailsSearch.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
                assertThat(testDTransactionDetailsSearch.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
                assertThat(testDTransactionDetailsSearch.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
                assertThat(testDTransactionDetailsSearch.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
                assertThat(testDTransactionDetailsSearch.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
                assertThat(testDTransactionDetailsSearch.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
                assertThat(testDTransactionDetailsSearch.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
            });
    }

    @Test
    void putNonExistingDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, dTransactionDetails.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateDTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the dTransactionDetails using partial update
        DTransactionDetails partialUpdatedDTransactionDetails = new DTransactionDetails();
        partialUpdatedDTransactionDetails.setId(dTransactionDetails.getId());

        partialUpdatedDTransactionDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .productCode(UPDATED_PRODUCT_CODE)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .timestamp(UPDATED_TIMESTAMP)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration3(UPDATED_NARRATION_3)
            .narration4(UPDATED_NARRATION_4)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .postingApi(UPDATED_POSTING_API)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField12ContentType(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField15ContentType(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .tranFreeField25(UPDATED_TRAN_FREE_FIELD_25)
            .tranFreeField27(UPDATED_TRAN_FREE_FIELD_27)
            .tranFreeField28(UPDATED_TRAN_FREE_FIELD_28)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DTransactionDetails testDTransactionDetails = dTransactionDetailsList.get(dTransactionDetailsList.size() - 1);
        assertThat(testDTransactionDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionDetails.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionDetails.getPaymentItemCode()).isEqualTo(DEFAULT_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionDetails.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionDetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransactionDetails.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testDTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransactionDetails.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getDebitAmount()).isEqualTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testDTransactionDetails.getCreditAmount()).isEqualTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testDTransactionDetails.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionDetails.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testDTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testDTransactionDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTransactionDetails.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testDTransactionDetails.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testDTransactionDetails.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionDetails.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionDetails.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testDTransactionDetails.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testDTransactionDetails.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testDTransactionDetails.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testDTransactionDetails.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionDetails.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionDetails.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransactionDetails.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testDTransactionDetails.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testDTransactionDetails.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionDetails.getNarration4()).isEqualTo(UPDATED_NARRATION_4);
        assertThat(testDTransactionDetails.getNarration5()).isEqualTo(DEFAULT_NARRATION_5);
        assertThat(testDTransactionDetails.getNarration6()).isEqualTo(DEFAULT_NARRATION_6);
        assertThat(testDTransactionDetails.getNarration7()).isEqualTo(DEFAULT_NARRATION_7);
        assertThat(testDTransactionDetails.getNarration8()).isEqualTo(DEFAULT_NARRATION_8);
        assertThat(testDTransactionDetails.getNarration9()).isEqualTo(DEFAULT_NARRATION_9);
        assertThat(testDTransactionDetails.getNarration10()).isEqualTo(DEFAULT_NARRATION_10);
        assertThat(testDTransactionDetails.getNarration11()).isEqualTo(DEFAULT_NARRATION_11);
        assertThat(testDTransactionDetails.getNarration12()).isEqualTo(DEFAULT_NARRATION_12);
        assertThat(testDTransactionDetails.getModeOfPayment()).isEqualTo(DEFAULT_MODE_OF_PAYMENT);
        assertThat(testDTransactionDetails.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testDTransactionDetails.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionDetails.getApiId()).isEqualTo(UPDATED_API_ID);
        assertThat(testDTransactionDetails.getApiVersion()).isEqualTo(DEFAULT_API_VERSION);
        assertThat(testDTransactionDetails.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionDetails.getIsPosted()).isEqualTo(DEFAULT_IS_POSTED);
        assertThat(testDTransactionDetails.getPostedBy()).isEqualTo(DEFAULT_POSTED_BY);
        assertThat(testDTransactionDetails.getPostedDate()).isEqualTo(DEFAULT_POSTED_DATE);
        assertThat(testDTransactionDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionDetails.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionDetails.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionDetails.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionDetails.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionDetails.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionDetails.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionDetails.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionDetails.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionDetails.getTranFreeField12ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testDTransactionDetails.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testDTransactionDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testDTransactionDetails.getTranFreeField15ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testDTransactionDetails.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransactionDetails.getTranFreeField18()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testDTransactionDetails.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testDTransactionDetails.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testDTransactionDetails.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testDTransactionDetails.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testDTransactionDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testDTransactionDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransactionDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransactionDetails.getTranFreeField26()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_26);
        assertThat(testDTransactionDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testDTransactionDetails.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testDTransactionDetails.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionDetails.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
    }

    @Test
    void fullUpdateDTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();

        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the dTransactionDetails using partial update
        DTransactionDetails partialUpdatedDTransactionDetails = new DTransactionDetails();
        partialUpdatedDTransactionDetails.setId(dTransactionDetails.getId());

        partialUpdatedDTransactionDetails
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .paymentItemCode(UPDATED_PAYMENT_ITEM_CODE)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
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
            .narration4(UPDATED_NARRATION_4)
            .narration5(UPDATED_NARRATION_5)
            .narration6(UPDATED_NARRATION_6)
            .narration7(UPDATED_NARRATION_7)
            .narration8(UPDATED_NARRATION_8)
            .narration9(UPDATED_NARRATION_9)
            .narration10(UPDATED_NARRATION_10)
            .narration11(UPDATED_NARRATION_11)
            .narration12(UPDATED_NARRATION_12)
            .modeOfPayment(UPDATED_MODE_OF_PAYMENT)
            .retries(UPDATED_RETRIES)
            .posted(UPDATED_POSTED)
            .apiId(UPDATED_API_ID)
            .apiVersion(UPDATED_API_VERSION)
            .postingApi(UPDATED_POSTING_API)
            .isPosted(UPDATED_IS_POSTED)
            .postedBy(UPDATED_POSTED_BY)
            .postedDate(UPDATED_POSTED_DATE)
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
            .tranFreeField12ContentType(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField15ContentType(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE)
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
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        DTransactionDetails testDTransactionDetails = dTransactionDetailsList.get(dTransactionDetailsList.size() - 1);
        assertThat(testDTransactionDetails.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testDTransactionDetails.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testDTransactionDetails.getPaymentItemCode()).isEqualTo(UPDATED_PAYMENT_ITEM_CODE);
        assertThat(testDTransactionDetails.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testDTransactionDetails.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testDTransactionDetails.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testDTransactionDetails.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testDTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testDTransactionDetails.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testDTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testDTransactionDetails.getDebitAmount()).isEqualTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testDTransactionDetails.getCreditAmount()).isEqualTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testDTransactionDetails.getTransactionAmount()).isEqualTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testDTransactionDetails.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testDTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testDTransactionDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTransactionDetails.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testDTransactionDetails.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testDTransactionDetails.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testDTransactionDetails.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDTransactionDetails.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testDTransactionDetails.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testDTransactionDetails.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testDTransactionDetails.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testDTransactionDetails.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testDTransactionDetails.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testDTransactionDetails.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testDTransactionDetails.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testDTransactionDetails.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testDTransactionDetails.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testDTransactionDetails.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testDTransactionDetails.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testDTransactionDetails.getNarration4()).isEqualTo(UPDATED_NARRATION_4);
        assertThat(testDTransactionDetails.getNarration5()).isEqualTo(UPDATED_NARRATION_5);
        assertThat(testDTransactionDetails.getNarration6()).isEqualTo(UPDATED_NARRATION_6);
        assertThat(testDTransactionDetails.getNarration7()).isEqualTo(UPDATED_NARRATION_7);
        assertThat(testDTransactionDetails.getNarration8()).isEqualTo(UPDATED_NARRATION_8);
        assertThat(testDTransactionDetails.getNarration9()).isEqualTo(UPDATED_NARRATION_9);
        assertThat(testDTransactionDetails.getNarration10()).isEqualTo(UPDATED_NARRATION_10);
        assertThat(testDTransactionDetails.getNarration11()).isEqualTo(UPDATED_NARRATION_11);
        assertThat(testDTransactionDetails.getNarration12()).isEqualTo(UPDATED_NARRATION_12);
        assertThat(testDTransactionDetails.getModeOfPayment()).isEqualTo(UPDATED_MODE_OF_PAYMENT);
        assertThat(testDTransactionDetails.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testDTransactionDetails.getPosted()).isEqualTo(UPDATED_POSTED);
        assertThat(testDTransactionDetails.getApiId()).isEqualTo(UPDATED_API_ID);
        assertThat(testDTransactionDetails.getApiVersion()).isEqualTo(UPDATED_API_VERSION);
        assertThat(testDTransactionDetails.getPostingApi()).isEqualTo(UPDATED_POSTING_API);
        assertThat(testDTransactionDetails.getIsPosted()).isEqualTo(UPDATED_IS_POSTED);
        assertThat(testDTransactionDetails.getPostedBy()).isEqualTo(UPDATED_POSTED_BY);
        assertThat(testDTransactionDetails.getPostedDate()).isEqualTo(UPDATED_POSTED_DATE);
        assertThat(testDTransactionDetails.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testDTransactionDetails.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testDTransactionDetails.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testDTransactionDetails.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testDTransactionDetails.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testDTransactionDetails.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testDTransactionDetails.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testDTransactionDetails.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testDTransactionDetails.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testDTransactionDetails.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testDTransactionDetails.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testDTransactionDetails.getTranFreeField12ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testDTransactionDetails.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testDTransactionDetails.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testDTransactionDetails.getTranFreeField15ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15_CONTENT_TYPE);
        assertThat(testDTransactionDetails.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testDTransactionDetails.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testDTransactionDetails.getTranFreeField18()).isEqualTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testDTransactionDetails.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testDTransactionDetails.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testDTransactionDetails.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testDTransactionDetails.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testDTransactionDetails.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testDTransactionDetails.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testDTransactionDetails.getTranFreeField25()).isEqualTo(UPDATED_TRAN_FREE_FIELD_25);
        assertThat(testDTransactionDetails.getTranFreeField26()).isEqualTo(UPDATED_TRAN_FREE_FIELD_26);
        assertThat(testDTransactionDetails.getTranFreeField27()).isEqualTo(UPDATED_TRAN_FREE_FIELD_27);
        assertThat(testDTransactionDetails.getTranFreeField28()).isEqualTo(UPDATED_TRAN_FREE_FIELD_28);
        assertThat(testDTransactionDetails.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testDTransactionDetails.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
    }

    @Test
    void patchNonExistingDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, dTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamDTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        dTransactionDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(dTransactionDetails))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DTransactionDetails in the database
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteDTransactionDetails() {
        // Initialize the database
        dTransactionDetailsRepository.save(dTransactionDetails).block();
        dTransactionDetailsRepository.save(dTransactionDetails).block();
        dTransactionDetailsSearchRepository.save(dTransactionDetails).block();

        int databaseSizeBeforeDelete = dTransactionDetailsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the dTransactionDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, dTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DTransactionDetails> dTransactionDetailsList = dTransactionDetailsRepository.findAll().collectList().block();
        assertThat(dTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(dTransactionDetailsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchDTransactionDetails() {
        // Initialize the database
        dTransactionDetails = dTransactionDetailsRepository.save(dTransactionDetails).block();
        dTransactionDetailsSearchRepository.save(dTransactionDetails).block();

        // Search the dTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + dTransactionDetails.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dTransactionDetails.getId().intValue()))
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
            .value(hasItem(DEFAULT_DEBIT_CURRENCY))
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
            .jsonPath("$.[*].narration4")
            .value(hasItem(DEFAULT_NARRATION_4))
            .jsonPath("$.[*].narration5")
            .value(hasItem(DEFAULT_NARRATION_5))
            .jsonPath("$.[*].narration6")
            .value(hasItem(DEFAULT_NARRATION_6))
            .jsonPath("$.[*].narration7")
            .value(hasItem(DEFAULT_NARRATION_7))
            .jsonPath("$.[*].narration8")
            .value(hasItem(DEFAULT_NARRATION_8))
            .jsonPath("$.[*].narration9")
            .value(hasItem(DEFAULT_NARRATION_9))
            .jsonPath("$.[*].narration10")
            .value(hasItem(DEFAULT_NARRATION_10))
            .jsonPath("$.[*].narration11")
            .value(hasItem(DEFAULT_NARRATION_11))
            .jsonPath("$.[*].narration12")
            .value(hasItem(DEFAULT_NARRATION_12))
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
            .jsonPath("$.[*].tranFreeField12ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_12)))
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13.toString()))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14.toString()))
            .jsonPath("$.[*].tranFreeField15ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_15)))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_18))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23))
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
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE));
    }
}
